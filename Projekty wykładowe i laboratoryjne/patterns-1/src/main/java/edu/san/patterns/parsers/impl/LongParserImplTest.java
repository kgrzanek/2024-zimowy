// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.patterns.parsers.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
  void testParseLongPositive() {
    var n = longParser.parseLong("12345");
    assertTrue(n.isPresent());
    assertEquals(12345, n.get());
  }

  @Test
  void testParseLongNegative() {
    var n = longParser.parseLong("aaaa12345");
    assertTrue(n.isEmpty());
  }

}
