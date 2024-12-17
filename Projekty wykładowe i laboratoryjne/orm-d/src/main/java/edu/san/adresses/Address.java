// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.adresses;

import java.util.UUID;

import edu.san.jpa.utils.AbstractEnityWithId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Version;

@Entity
public class Address extends AbstractEnityWithId<UUID, Address> {

  @Id
  @GeneratedValue(generator = "UUID")
  private UUID id;

  @Version
  private short version;

  private String country;

  private String postalCode;

  private String street;

  private String number;

  @Override
  public UUID getId() {
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

}
