package com.iago.enums;

public enum Category {

  BACK_END("Back-end"), FRONT_END("Front-end");

  private final String categoryValue;

  private Category(String categoryValue) {
    this.categoryValue = categoryValue;
  }

  public String getValue() {
    return categoryValue;
  }

  @Override
  public String toString() {
    return categoryValue;
  }
}
