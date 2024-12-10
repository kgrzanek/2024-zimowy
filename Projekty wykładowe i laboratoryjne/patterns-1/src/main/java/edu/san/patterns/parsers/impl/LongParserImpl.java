// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.patterns.parsers.impl;

import static java.lang.Character.digit;

import java.util.Optional;

import edu.san.patterns.parsers.LongParser;

class LongParserImpl implements LongParser {

  @Override
  public Optional<Long> apply(String s) {
    if (s == null)
      return Optional.empty();

    int len = s.length();
    if (len == 0)
      return Optional.empty();
    int digit = ~0xFF;
    int i = 0;
    char firstChar = s.charAt(i++);
    if (firstChar != '-' && firstChar != '+') {
      digit = digit(firstChar, 10);
    }
    if (digit >= 0 || digit == ~0xFF && len > 1) {
      long limit = firstChar != '-' ? Long.MIN_VALUE + 1 : Long.MIN_VALUE;
      long multmin = limit / 10;
      long result = -(digit & 0xFF);
      boolean inRange = true;
      /* Accumulating negatively avoids surprises near MAX_VALUE */
      while (i < len && (digit = digit(s.charAt(i++), 10)) >= 0
          && (inRange = result > multmin
              || result == multmin
                  && digit <= (int) (10 * multmin - limit))) {
        result = 10 * result - digit;
      }
      if (inRange && i == len && digit >= 0)
        return Optional.of(firstChar != '-' ? -result : result);
    }
    return Optional.empty();
  }

}
