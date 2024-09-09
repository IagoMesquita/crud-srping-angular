package com.iago.exception;

public class RecordNotFoundException extends RuntimeException{
  public RecordNotFoundException(Long id) {
    super("Registro nao encontro para o id: " + id);
  }
}
