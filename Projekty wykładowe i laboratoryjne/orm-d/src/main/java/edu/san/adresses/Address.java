// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.adresses;

import edu.san.employees.Employee;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Version;

@Entity
public class Address extends PanacheEntityBase {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @Version
  private short version;

  private String country;

  private String postalCode;

  private String street;

  private String number;

  public Long getId() {
    return id;
  }

  public short getVersion() {
    return version;
  }

  public void setVersion(short version) {
    this.version = version;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getPostalCode() {
    return postalCode;
  }

  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  @Override
  public final int hashCode() {
    return Long.hashCode(getId());
  }

  @Override
  public final boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (!(obj instanceof Employee))
      return false;
    Employee other = (Employee) obj;
    return getId() == other.getId();
  }

}
