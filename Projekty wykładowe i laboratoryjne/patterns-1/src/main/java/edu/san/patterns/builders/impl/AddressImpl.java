// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.patterns.builders.impl;

import java.util.Optional;

import edu.san.patterns.builders.Address;

final class AddressImpl implements Address {

  private final String street;

  private final String number;

  private final Integer appartmentNumber;

  private final String city;

  private final String country;

  private final String postalCode;

  AddressImpl(String street, String number, Integer appartmentNumber,
      String city, String country, String postalCode) {
    this.street = street;
    this.number = number;
    this.appartmentNumber = appartmentNumber;
    this.city = city;
    this.country = country;
    this.postalCode = postalCode;
  }

  @Override
  public String street() {
    return street;
  }

  @Override
  public String number() {
    return number;
  }

  @Override
  public Optional<Integer> appartmentNumber() {
    return Optional.ofNullable(appartmentNumber);
  }

  @Override
  public Optional<String> city() {
    return Optional.ofNullable(city);
  }

  @Override
  public String country() {
    return country;
  }

  @Override
  public Optional<String> postalCode() {
    return Optional.ofNullable(postalCode);
  }

}
