package com.iago.enums.converters;

import com.iago.enums.Category;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true) // --> jpa aplicar essa conversao sempre que necessario
public class CategoryConverter implements AttributeConverter<Category, String> {

  @Override
  public String convertToDatabaseColumn(Category category) {
    if (category == null) { // — > Ja possuem várias validacoes, mas aqui e apenas um reforco
      return null;
    }

    return category.getValue();
  }

  @Override
  public Category convertToEntityAttribute(String categoryValue) {
    if (categoryValue == null) {
      return null;
    }

    return Stream.of(Category.values())
        .filter(category -> category.getValue().equals(categoryValue))
        .findFirst()
        .orElseThrow(IllegalArgumentException::new);
  }
}
