package com.rubejuca.proyectointegrador.controllers;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.PropertyValueException;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


import java.time.LocalDateTime;
import java.util.Map;

@Order(-1000)
@ControllerAdvice
@Slf4j
public class ApiErrorHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler({ IllegalArgumentException.class })
  public ResponseEntity<Object> handle(IllegalArgumentException exception, WebRequest webRequest) {
    log.error("Exception capturada", exception);
    return new ResponseEntity<>(errorAttributes(exception.getMessage()), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler({ EntityNotFoundException.class })
  public ResponseEntity<Object> handle(EntityNotFoundException exception, WebRequest webRequest) {
    log.error("Exception capturada", exception);
    return new ResponseEntity<>(errorAttributes(exception.getMessage()), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler({ DataIntegrityViolationException.class })
  public ResponseEntity<Object> handle(DataIntegrityViolationException exception, WebRequest webRequest) {
    log.error("Exception capturada", exception);
    if (exception.getCause() instanceof PropertyValueException) {
      PropertyValueException pve = (PropertyValueException) exception.getCause();
      String message = String.format("El campo '%s' debe tener un valor", pve.getPropertyName());
      return new ResponseEntity<>(errorAttributes(message), HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<>(errorAttributes(exception.getMessage()), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler({ Exception.class })
  public ResponseEntity<Object> handle(Exception exception, WebRequest webRequest) {
    log.error("Exception capturada", exception);
    return new ResponseEntity<>(errorAttributes(exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  private Map<String, Object> errorAttributes(String mensaje) {
    return Map.of(
        "timestamp", LocalDateTime.now(),
        "message", mensaje);
  }

}
