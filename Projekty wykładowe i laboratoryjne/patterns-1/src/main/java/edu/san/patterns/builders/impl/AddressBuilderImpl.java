// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.patterns.builders.impl;

import edu.san.patterns.builders.Address;
import edu.san.patterns.builders.AddressBuilder;

public final class AddressBuilderImpl implements AddressBuilder {

  private String street = "Piotrkowska";

  private String number = "215";

  private Integer appartmentNumber;

  private String city = "Łódź";

  private String country = "Poland";

  private String postalCode = "43-256";

  @Override
  public AddressBuilder withStreet(String street) {
    this.street = street;
    return this;
  }

  @Override
  public AddressBuilder withNumber(String number) {
    this.number = number;
    return this;
  }

  @Override
  public AddressBuilder withAppartmentNumber(Integer appartmentNumber) {
    this.appartmentNumber = appartmentNumber;
    return this;
  }

  @Override
  public AddressBuilder withCity(String city) {
    this.city = city;
    return this;
  }

  @Override
  public AddressBuilder withCountry(String country) {
    this.country = country;
    return this;
  }

  @Override
  public AddressBuilder withPostalCode(String postalCode) {
    this.postalCode = postalCode;
    return this;
  }

  @Override
  public Address build() {
    return new AddressImpl(street, number, appartmentNumber, city, country,
        postalCode);
  }

}
