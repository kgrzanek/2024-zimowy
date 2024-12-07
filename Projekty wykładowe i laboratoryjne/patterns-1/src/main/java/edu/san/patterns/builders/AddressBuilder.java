// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.patterns.builders;

public interface AddressBuilder {

  AddressBuilder withStreet(String street);

  AddressBuilder withNumber(String number);

  AddressBuilder withAppartmentNumber(Integer appartmentNumber);

  AddressBuilder withCity(String city);

  AddressBuilder withCountry(String country);

  AddressBuilder withPostalCode(String postalCode);

  Address build();

}
