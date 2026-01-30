# OrderManagement API REST

Proyecto personal y autodidacta de **API REST** desarrollado con **Spring Boot**, orientado a la gestión de órdenes de compra y stock de productos.

El objetivo principal es consolidar fundamentos de desarrollo backend con Java, profundizando cómo interactúan una API, la persistencia de datos y las herramientas del ecosistema Spring.
Permite gestionar órdenes de compra y stock de productos mediante endpoints REST.
---

## Objetivo del proyecto

* Profundizar cómo funciona una **API REST**
* Profundizar aún más el flujo completo **Controller → Service → Repository**
* Implementar **persistencia con base de datos relacional**
* Utilizar **Spring Boot + Spring Data JPA**
* Probar endpoints usando **Postman**
* Aplicar buenas prácticas básicas de backend

---

## Tecnologías utilizadas

* **Java 17+** (probado con JDK 25.0.1)
* **Spring Boot** (v4.0.2)
* **Spring Web**
* **Spring Data JPA**
* **MySQL** (gestionado con phpMyAdmin / XAMPP)
* **Maven**
* **Postman** (testing de endpoints)

---

## Arquitectura

Arquitectura en capas (Controller -> Service -> Repository -> Model) siguiendo buenas prácticas de Spring:

### Controller

* Exposición de endpoints REST
* Manejo de requests y responses HTTP

### Service

* Lógica de negocio
* Validaciones
* Orquestación de operaciones

### Repository

* Acceso a datos
* Persistencia mediante **JPA**

### Model / Entity

* Entidades mapeadas a la base de datos:
  
#### Product
- id
- name
- description
- stock
- price

#### OrderItem
- id
- itemName
- itemDescription
- amount
- price
- order
- product

#### Order
- id
- itemList
- createdAt
- total
- status
- customerName

---

## Endpoints principales

### Productos

| Método | Endpoint                | Descripción                        |
|--------|-------------------------|------------------------------------|
| GET    | /api/products           | Obtener todas los productos        |
| GET    | /api/products/{id}      | Obtener productos por ID           |
| POST   | /api/products           | Crear nuevo producto               |
| PUT    | /api/products/{id}      | Actualizar un producto             |
| DELETE | /api/products/{id}      | Eliminar un producto               |

### Ordenes

| Método | Endpoint                 | Descripción                       |
|--------|--------------------------|-----------------------------------|
| GET    | /api/orders              | Obtener todas las órdenes         |
| GET    | /api/orders/{id}         | Obtener órdenes por id            |
| POST   | /api/orders              | Crea una nueva orden              |
| POST   | /api/orders/add/{id}     | Añade items a una orden existente |
| POST   | /api/orders/remove/{id}  | Quita items a una orden existente |
| POST   | /api/orders/confirm/{id} | Confirma una orden existente      |
| POST   | /api/orders/cancel/{id}  | Cancela una orden existente       |
---

## Persistencia

* Base de datos **MySQL**
* Mapeo ORM con **JPA / Hibernate**
* Configuración mediante archivos `application.properties`
* Uso de perfiles (`local`) para proteger credenciales

---

## Testing

* Pruebas manuales de la API realizadas con **Postman**
* Verificación de:

  * Creación de productos
  * Obtención de productos
  * Actualización de productos
  * Eliminación de productos
  * Creación de orden
  * Adición de items a una orden
  * Sustracción de items a una orden
  * Confirmación de una orden
  * Cancelación de una orden

---

## Cómo ejecutar el proyecto

Este proyecto está pensado para ejecutarse en un entorno de desarrollo (IDE).

### Requisitos

* Java JDK 17 o superior
* Maven
* MySQL (local)
* IDE compatible con Spring Boot (IntelliJ IDEA recomendado)

### Pasos

1. Clonar el repositorio

   ```bash
   git clone https://github.com/SdrNahui/OrderManagementAPI-REST.git
   ```

2. Crear una base de datos MySQL local

3. Configurar credenciales en:

   * `application-local.properties`

4. Asegurarse de tener activo el perfil `local`

5. Ejecutar la aplicación desde la clase principal de Spring Boot

6. Probar los endpoints con Postman

---

## Conceptos aplicados

- Arquitectura MVC
- Programación Orientada a Objetos
- Uso de @Transactional
- Utilización de DTOs
- Endpoints REST
- Lógica de negocio
- Manejo de excepciones personalizadas
- Manejo global de excepciones
- Refactorización incremental
- Base de datos relacional
- Validaciones de Request y Response
- Relaciones entre entidades
- Respuestas HTTP 
- Aprendizaje prueba y error

---

## Conceptos aprendidos

- Qué es una entidad intermedia
- Ciclo de vida de la entidad intermedia
- Estados
- Lógica de estados
- Relaciones entre entidades con intermediario

---

## Estado del proyecto

* Funcional
* Persistente
* Probado con Postman
* Versión: **v1.0**

---

## Notas

Proyecto enfocado en el **aprendizaje progresivo**, priorizando la comprensión de conceptos por sobre la complejidad o la optimización.
