package com.example.OrdersAPI.Exception;

import com.example.OrdersAPI.Dto.ErrorByMe;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(NoFoundException.class)
    public ResponseEntity<ErrorByMe> notFound(NoFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorByMe(exception.getMessage()));
    }
    @ExceptionHandler(IllegalStatusException.class)
    public ResponseEntity<ErrorByMe> statusError(IllegalStatusException exception){
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new ErrorByMe(exception.getMessage()));
    }
    @ExceptionHandler(IllegalProductException.class)
    public ResponseEntity<ErrorByMe> productNoSolicited(IllegalProductException exception){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorByMe(exception.getMessage()));
    }
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorByMe> others(BusinessException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorByMe(exception.getMessage()));
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorByMe> validationError(MethodArgumentNotValidException exception){
        String msg = exception.getBindingResult().getFieldErrors().stream().findFirst()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .orElse("Error de validación");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorByMe(msg));
    }
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorByMe> constrainError(ConstraintViolationException exception){
        String msg = exception.getConstraintViolations().stream().findFirst()
                .map(v -> v.getPropertyPath() + ": " + v.getMessage())
                .orElse("Error de validación");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorByMe(msg));
    }
    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<ErrorByMe> MethodError(HandlerMethodValidationException exception) {
        String msg = exception.getAllErrors().stream().map(m -> m.getDefaultMessage())
                .findFirst().orElse("Errror de validacion");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorByMe(msg));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorByMe> catchOthersEx(Exception exception){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorByMe("Error interno del servidor."));
    }

}
