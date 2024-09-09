package com.iago.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public record CourseDTO(
    Long id,
    @NotBlank
    @NotNull
    @Length(min = 4, max = 100)
    @Column(length = 200, nullable = false) String name,

    @NotNull(message = "Campo category e obrigatório!")
    @Length(max = 10) @Pattern(regexp = "Back-end|Front-end")
    @Column(length = 20, nullable = false) String category,

    @NotNull @Length(max = 10) @Pattern(regexp = "Ativo|Inativo") String status
) {

}
