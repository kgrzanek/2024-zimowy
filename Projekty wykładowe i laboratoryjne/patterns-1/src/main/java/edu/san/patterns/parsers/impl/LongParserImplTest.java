// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.patterns.parsers.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.san.patterns.parsers.LongParser;

class LongParserImplTest {

  LongParser longParser;

  @BeforeEach
  void setUp() {
    longParser = new LongParserImpl();
  }

  @Test
  void testPositive() {
    var n = longParser.apply("12345");
    assertTrue(n.isPresent());
    assertEquals(12345, n.get());
  }

  @Test
  void testNegative() {
    var n = longParser.apply("aaaa12345");
    assertTrue(n.isEmpty());
  }

  @Test
  void testPerformance() {
    int count = 0;
    for (int i = 0; i < 10_000_000; i++) {
      final var s = i + "abcd";
      var result = longParser.apply(s);
      // var result = parseLong(s);
      if (result.isEmpty()) {
        count++;
      }
    }

    assertEquals(10_000_000, count);
  }

  static Optional<Long> parseLong(String s) {
    try {
      return Optional.of(Long.parseLong(s));
    } catch (NumberFormatException e) {
      return Optional.empty();
    }
  }

}
