package com.iago.dto;

import jakarta.persistence.Column;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.util.List;
import org.hibernate.validator.constraints.Length;

public record CourseDTO(
    Long id,
    @NotBlank
    @NotNull
    @Length(min = 4, max = 100)
    @Column(length = 200, nullable = false)
    String name,

    @NotNull
    @Length(max = 10)
    @Pattern(regexp = "BACK_END|FRONT_END")
    String category,


    @NotNull @NotEmpty @Valid List<LessonDTO> lessons

) {

}
