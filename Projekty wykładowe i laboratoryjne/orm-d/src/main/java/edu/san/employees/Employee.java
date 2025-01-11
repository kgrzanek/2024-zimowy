// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.employees;

import edu.san.adresses.Address;
import edu.san.jpa.utils.AbstractEnityWithId;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Version;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
// @DiscriminatorColumn(name = "employee_type")
public abstract class Employee extends AbstractEnityWithId<Long, Employee> {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @Version
  private short version;

  @Column(unique = true)
  private String email;

  private String firstName;

  private String lastName;

  @ManyToOne
  private Address address;

  @Override
  public Long getId() {
    return id;
  }

  public short getVersion() {
    return version;
  }

  public void setVersion(short version) {
    this.version = version;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

}
