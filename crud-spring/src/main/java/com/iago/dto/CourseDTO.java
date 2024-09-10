package com.iago.dto;

import com.iago.enums.Category;
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
//    @Length(max = 10) @Pattern(regexp = "Back-end|Front-end")
    Category category

) {

}
