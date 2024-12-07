// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.patterns.parsers;

import java.util.Optional;

public interface LongParser {

  Optional<Long> parseLong(String s);

}
