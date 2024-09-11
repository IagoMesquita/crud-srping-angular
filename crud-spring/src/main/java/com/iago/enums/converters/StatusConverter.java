package com.iago.enums.converters;

import com.iago.enums.Status;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.Arrays;

@Converter(autoApply = true)
public class StatusConverter implements AttributeConverter<Status, String> {

  @Override
  public String convertToDatabaseColumn(Status status) {
    if (status.getStatusValue() == null) {
      return null;
    }

    return status.getStatusValue();
  }

  @Override
  public Status convertToEntityAttribute(String statusValue) {
    if (statusValue == null) {
      return null;
    }

    return Arrays.stream(Status.values())
        .filter(status -> status.getStatusValue().equals(statusValue))
        .findFirst()
        .orElseThrow(IllegalArgumentException::new);
  }
}
