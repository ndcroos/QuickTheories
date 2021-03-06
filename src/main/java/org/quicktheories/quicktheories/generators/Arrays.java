package org.quicktheories.quicktheories.generators;

import org.quicktheories.quicktheories.api.AsString;
import org.quicktheories.quicktheories.core.Source;

import java.lang.reflect.Array;
import java.util.function.Function;
import java.util.stream.Collectors;

final class Arrays {

  @SuppressWarnings("unchecked")
  static <T> Source<T[]> arraysOf(Source<T> values, Class<T> c,
      int minLength, int maxLength) {
    return Lists
        .listsOf(values, Lists.arrayListCollector(), minLength, maxLength)
        .as(l -> l.toArray((T[]) Array.newInstance(c, 0)), // will generate
                                                           // correct size if
                                                           // zero is less than
                                                           // the length of the
                                                           // array
            a -> java.util.Arrays.asList(a)).describedAs(arrayDescriber(values::asString));
  }
  
  private static <T> AsString<T[]> arrayDescriber(Function<T, String> valueDescriber) {
    return a -> java.util.Arrays.stream(a).map(valueDescriber).collect(Collectors.joining(", ", "[", "]"));
  }

}
