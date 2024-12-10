// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.patterns.builders.impl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.san.patterns.builders.Address;

class AddressBuilderImplTest {

  @Test
  void testBuild1() {
    var address = Address.builder()
        .withCountry("UK")
        .withCity("London")
        .withStreet("Baker St.")
        .withNumber("14")
        .withAppartmentNumber(245)
        .build();

    assertTrue(address != null);
  }

  @Test
  void testBuild2() {
    var address = Address.builder()
        .withCountry("UK")
        .withAppartmentNumber(245)
        .build();

    assertTrue(address != null);
  }

  @Test
  void testBuild3() {
    var address = Address.builder()
        .withCountry("UK")
        .build();

    assertTrue(address != null);
  }

}
