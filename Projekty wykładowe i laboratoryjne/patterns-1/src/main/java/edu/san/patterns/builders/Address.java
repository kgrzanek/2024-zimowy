// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.patterns.builders;

import java.util.Optional;

import edu.san.patterns.builders.impl.AddressBuilderImpl;

public interface Address {

  String street();

  String number();

  Optional<Integer> appartmentNumber();

  Optional<String> city();

  String country();

  Optional<String> postalCode();

  static AddressBuilder builder() {
    return new AddressBuilderImpl();
  }

}
