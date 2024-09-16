package com.iago.enums;

public enum Status {
  ATIVO("Ativo"), INATIVO("Inativo");

  private final String statusValue;

  Status(String statusValue) {
    this.statusValue = statusValue;
  }

  public String getStatusValue() {
    return statusValue;
  }

  @Override
  public String toString() {
    return statusValue;
  }
}
