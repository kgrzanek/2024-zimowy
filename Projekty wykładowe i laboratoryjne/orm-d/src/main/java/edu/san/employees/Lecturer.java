// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.employees;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
// @DiscriminatorValue("Lect")
public class Lecturer extends Employee {

  @Column(nullable = false)
  private String degree;

  public String getDegree() {
    return Objects.requireNonNull(degree);
  }

  public void setDegree(String degree) {
    this.degree = Objects.requireNonNull(degree);
  }

}
