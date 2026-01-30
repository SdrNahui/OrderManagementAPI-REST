package com.example.OrdersAPI.Service;

import com.example.OrdersAPI.Dto.ProductOrderRequest;
import com.example.OrdersAPI.Exception.BusinessException;
import com.example.OrdersAPI.Exception.IllegalProductException;
import com.example.OrdersAPI.Exception.IllegalStatusException;
import com.example.OrdersAPI.Exception.NoFoundException;
import com.example.OrdersAPI.Model.Order;
import com.example.OrdersAPI.Model.OrderItem;
import com.example.OrdersAPI.Model.Product;
import com.example.OrdersAPI.Model.StatusOrder;
import com.example.OrdersAPI.Repository.OrderRepository;
import com.example.OrdersAPI.Repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    public OrderService(OrderRepository orderRepository, ProductRepository productRepository){
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    //Crea una nueva orden con estado CREATED y total 0
    @Transactional
    public Order createOrder(Order orderCreate){
        if(orderCreate.getCustomerName() == null || orderCreate.getCustomerName().isBlank()){
            throw new BusinessException("El nombre del cliente es requerido.");
        }
        orderCreate.setItemList(new ArrayList<>());
        orderCreate.setStatusOrder(StatusOrder.CREATED);
        orderCreate.setTotal(BigDecimal.ZERO);
        return orderRepository.save(orderCreate);
    }
    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }
    public Optional<Order> getOrderById(Long id){
        return orderRepository.findById(id);
    }

    //Añade items a la orden mientras este en CREATED
    @Transactional
    public Order addItem(List<ProductOrderRequest> requests, Long idOrder){
        Order order = orderRepository.findById(idOrder).orElseThrow(() -> new NoFoundException(idOrder));
        if(order.getStatusOrder() != StatusOrder.CREATED){
            throw new IllegalStatusException(order.getStatusOrder());
        }
        if(requests == null || requests.isEmpty()){
            throw new BusinessException("Tiene que añadir el id y lo que quiere agregar");
        }
        for(ProductOrderRequest req : requests) {
            if(req.getIdProduct() == null){
                throw new BusinessException("El id es necesario.");
            }
            if (req.getAmount() <= 0){
                throw new IllegalProductException();
            }
            Product p = productRepository.findById(req.getIdProduct())
                    .orElseThrow(() -> new NoFoundException(req.getIdProduct()));
            OrderItem orderItem = new OrderItem();
            orderItem.setItemName(p.getName());
            orderItem.setItemDescription(p.getDescription());
            orderItem.setPrice(p.getPrice());
            orderItem.setOrder(order);
            if(p.getStock() < req.getAmount()){
                throw new BusinessException("No hay suficiente stock para el producto " + p.getName());
            }
            orderItem.setAmount(req.getAmount());
            orderItem.setProduct(p);
            p.setStock(p.getStock() - req.getAmount());
            productRepository.save(p);
            Optional<OrderItem> exist = order.getItemList().stream()
                    .filter(i -> i.getProduct().getId().equals(p.getId())).findFirst();
            if (exist.isPresent()) {
                exist.get().setAmount(exist.get().getAmount() + req.getAmount());
            } else {
                order.getItemList().add(orderItem);
            }
        }
        order.setTotal(calculateTotal(order));
        return orderRepository.save(order);
    }

    //Quita items a la orden mientras este en CREATED
    @Transactional
    public Order removeItem (List<ProductOrderRequest> requestsRemove, Long idOrder){
        Order order = orderRepository.findById(idOrder).orElseThrow(() -> new NoFoundException(idOrder));
        if(order.getStatusOrder() != StatusOrder.CREATED){
            throw new IllegalStatusException(order.getStatusOrder());
        }
        if(requestsRemove == null || requestsRemove.isEmpty()){
            throw new BusinessException("Tiene que añadir un id y que quiere quitar");
        }
        for(ProductOrderRequest req : requestsRemove){
            if(req.getIdProduct() == null){
                throw new BusinessException("El id es necesario.");
            }
            if (req.getAmount() <= 0){
                throw new IllegalProductException();
            }
            Optional<OrderItem> itemRemove = order.getItemList().stream()
                    .filter(i -> i.getProduct().getId().equals(req.getIdProduct())).findFirst();
            if ((itemRemove.isPresent())){
                OrderItem orderItem = itemRemove.get();
                Product product = orderItem.getProduct();
                int remove = Math.min(orderItem.getAmount(), req.getAmount());
                orderItem.setAmount(orderItem.getAmount() - remove);
                if(orderItem.getAmount() <= 0){
                    order.getItemList().remove(orderItem);
                }
                product.setStock(product.getStock() + remove);
                productRepository.save(product);
            }
        }
        order.setTotal(calculateTotal(order));
        return orderRepository.save(order);
    }
    //Cancela la orden
    @Transactional
    public Order cancelOrder(Long id, boolean confirm) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new NoFoundException(id));
        if(order.getStatusOrder() == StatusOrder.CANCELED){
            throw new IllegalStatusException(order.getStatusOrder());
        }
        if(!confirm){
            return  order;
        }
        for(OrderItem item : order.getItemList()){
            Product p = item.getProduct();
            p.setStock(p.getStock() + item.getAmount());
            productRepository.save(p);
        }
        order.setStatusOrder(StatusOrder.CANCELED);
        return orderRepository.save(order);
    }

    //Confirma la orden y pasa a ser ACCEPTED
    @Transactional
    public Order confirmOrder(Long id, boolean confirm){
        Order order = orderRepository.findById(id).orElseThrow(() -> new NoFoundException(id));
        if(order.getStatusOrder() != StatusOrder.CREATED){
            throw new IllegalStatusException(order.getStatusOrder());
        }
        if(!confirm){
            return order;
        }
        if(order.getItemList() == null || order.getItemList().isEmpty()){
            throw new BusinessException("No se puede confirmar una orden vacia.");

        }
        order.setTotal(calculateTotal(order));
        order.setStatusOrder(StatusOrder.ACCEPTED);
        return orderRepository.save(order);
    }
    private BigDecimal calculateTotal(Order order){
        return order.getItemList().stream().map(item -> item.getPrice().
                multiply(BigDecimal.valueOf(item.getAmount()))).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
