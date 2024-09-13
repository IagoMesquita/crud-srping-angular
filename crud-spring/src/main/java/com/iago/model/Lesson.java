package com.iago.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "lessons")
public class Lesson {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @NotNull
  @NotBlank
  @Length(min = 5, max = 100)
  @Column(length = 100, nullable = false)
  String name;

  @NotNull
  @NotBlank
  @Length(min = 10, max = 11)
  @Column(length = 10, nullable = false)
  String youtubeUrl;

  @NotNull
  @ManyToOne(fetch = FetchType.LAZY, optional = false) // -> Somente quando get course for chamado e que esse mapeamento sera carregado
  @JoinColumn(name = "course_id", nullable = false)
//  @JsonProperty(access = Access.WRITE_ONLY) -> Essa anotacao seria pra nao dar erro de dependencia ciclica quando utilizo o entidade.
  private Course course;

  public Lesson() {
  }

  public Lesson(Long id, String name, String youtubeUrl) {
    this.id = id;
    this.name = name;
    this.youtubeUrl = youtubeUrl;
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

  public String getYoutubeUrl() {
    return youtubeUrl;
  }

  public void setYoutubeUrl(String youtubeUrl) {
    this.youtubeUrl = youtubeUrl;
  }

  public Course getCourse(Course course) {
    return this.course;
  }

  public void setCourse(Course course) {
    this.course = course;
  }
}
