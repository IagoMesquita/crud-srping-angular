package com.iago.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record CourseDTO(
    Long id,
    @NotBlank
    @NotNull
    @Length(min = 4, max = 100)
    @Column(length = 200, nullable = false)
    String name,

    //    @Length(max = 10) @Pattern(regexp = "Back-end|Front-end")
    String category

) {

}
