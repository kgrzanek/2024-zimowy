// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.employees;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Administrator extends Employee {

  @Column(nullable = false)
  private AdministratorRole role;

  public AdministratorRole getRole() {
    return Objects.requireNonNull(role);
  }

  public void setRole(AdministratorRole role) {
    this.role = Objects.requireNonNull(role);
  }

}
