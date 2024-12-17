// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.employees;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Admin.")
public class Administrator extends Employee {

  @Column(nullable = false)
  private AdministratorRole role;

  public AdministratorRole getRole() {
    return role;
  }

  public void setRole(AdministratorRole role) {
    this.role = role;
  }

}
