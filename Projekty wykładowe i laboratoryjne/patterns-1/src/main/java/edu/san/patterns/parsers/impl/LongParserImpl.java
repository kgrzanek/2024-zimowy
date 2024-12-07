// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.patterns.parsers.impl;

import java.util.Optional;

import edu.san.patterns.parsers.LongParser;

class LongParserImpl implements LongParser {

  @Override
  public Optional<Long> parseLong(String s) {
    try {
      return Optional.of(Long.parseLong(s));
    } catch (NumberFormatException e) {
      return Optional.empty();
    }
  }

}
