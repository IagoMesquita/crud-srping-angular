package com.iago.model;

import com.iago.enums.Category;
import com.iago.enums.Status;
import com.iago.enums.converters.CategoryConverter;
import com.iago.enums.converters.StatusConverter;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "courses")
@SQLDelete(sql = "UPDATE courses SET status = 'Inativo' WHERE id = ?")
@Where(clause = "status = 'Ativo'")
public class Course {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @NotBlank // -> Pelo menos um caracter que nao seja espaco
  @NotNull //-> Nao deixar ser nulo nem vazio
  @Length(min = 2, max = 100)
  @Column(length = 200, nullable = false)
  String name;

  @NotNull
//  @Length(max = 10) -> Nao sera mais util com ENUM, pois valida apenas strings
//  @Pattern(regexp = "Back-end|Front-end")
  @Column(length = 20, nullable = false)
//  @Enumerated(EnumType.STRING) --> e substituido pelo Conversor
  @Convert(converter = CategoryConverter.class)
  Category category;

  @NotNull
//  @Length(max = 10)
//  @Pattern(regexp = "Ativo|Inativo")
  @Column(length = 10, nullable = false)
//  @Enumerated(EnumType.STRING)
  @Convert(converter = StatusConverter.class)
  private Status status = Status.ATIVO;

  @NotNull
  @NotEmpty
  @Valid
  @OneToMany(mappedBy = "course" ,cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Lesson> lessons = new ArrayList<>();

  public Course() {
  }

  public Course(String name, Category category, Status status) {
    this.name = name;
    this.category = category;
    this.status = status;
  }


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public List<Lesson> getLessons() {
    return lessons;
  }

  public void setLessons(List<Lesson> lessons) {
    this.lessons = lessons;
  }
}
