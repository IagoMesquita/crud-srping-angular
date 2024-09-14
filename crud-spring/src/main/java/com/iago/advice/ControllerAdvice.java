package com.iago.advice;

import com.iago.exception.RecordNotFoundException;
import jakarta.validation.ConstraintViolationException;
import java.util.Objects;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class ControllerAdvice {

  @ExceptionHandler(RecordNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public String handleNotFoundException(RecordNotFoundException e) {
    return e.getMessage();
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public String handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
    return e.getBindingResult().getFieldErrors()
        .stream().map(error -> error.getField() + " " + error.getDefaultMessage())
        .reduce("", (acc, error) -> acc + error + "\n");
  }

  @ExceptionHandler(ConstraintViolationException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public String handleConstraintViolationException(ConstraintViolationException e) {
    return e.getConstraintViolations()
        .stream().map(error -> error.getPropertyPath() + " " + error.getMessage())
        .reduce("", (acc, error) -> acc + error + "\n");
  }

  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public String handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
    if(e != null && e.getRequiredType() != null) {
      String type = e.getRequiredType().getName();
      // java.lang.Long
      String[] typeParts = type.split("\\.");
      //Long
      String typeName = typeParts[typeParts.length -1];

      return e.getName() + "  deve ser do tipo " + typeName;

    }
    return " Argument type not valid";
  }

}
