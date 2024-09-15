package com.iago.dto;

import com.iago.enums.Category;
import com.iago.enums.validation.ValueOfEnum;
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
//    @Pattern(regexp = "Back-end|Front-end")
    @ValueOfEnum(enumClass = Category.class)
    String category,



    @NotNull @NotEmpty @Valid List<LessonDTO> lessons

) {

}
