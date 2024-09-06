package com.iago.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "courses")
@SQLDelete(sql = "UPDATE courses SET status = 'Inativo' WHERE id = ?")
@Where(clause = "status = 'Ativo'")
public class Course {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  Long id;

  @NotBlank // -> Pelo menos um caracter que nao seja espaco
  @NotNull //-> Nao deixar ser nulo nem vazio
  @Length(min = 5, max = 100)
  @Column(length = 200, nullable = false)
  String name;

  @NotNull
  @Length(max = 10)
  @Pattern(regexp = "Back-end | Front-end")
  @Column(length = 20, nullable = false)
  String category;

  @NotNull
  @Length(max = 10)
  @Pattern(regexp = "Ativa | Inativo")
  @Column(length = 10, nullable = false)
  private String status = "Ativo";

  public Course() {
  }

  public Course(String name, String category, String status) {
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

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

}
