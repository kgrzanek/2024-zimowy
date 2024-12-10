// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.patterns.parsers;

import java.util.Optional;
import java.util.function.Function;

@FunctionalInterface
public interface LongParser extends Function<String, Optional<Long>> {}
