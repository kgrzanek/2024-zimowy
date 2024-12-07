// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.patterns.builders;

import java.util.Optional;

public interface Address {

  String street();

  String number();

  Optional<Integer> appartmentNumber();

  Optional<String> city();

  String country();

  Optional<String> postalCode();

}
