/******************************************************************************
 *
 * Copyright (c) 2014  Haixing Hu
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 ******************************************************************************/

package com.github.haixing_hu.javafx.util;

import java.util.Comparator;

import javafx.util.StringConverter;

import static com.github.haixing_hu.lang.Argument.requireNonNull;

/**
 * A simple comparator compares objects with their string representations
 * getting from a specified string converter.
 *
 * @author Haixing Hu
 */
public final class StringConverterComparator<T> implements Comparator<T> {

  private final StringConverter<T> converter;

  /**
   * Constructs a {@link StringConverterComparator} with a specified string
   * converter.
   *
   * @param converter
   *          a specified string converter.
   */
  public StringConverterComparator(StringConverter<T> converter) {
    this.converter = requireNonNull("converter", converter);
  }

  @Override
  public int compare(T o1, T o2) {
    final String o1str = converter.toString(o1);
    final String o2str = converter.toString(o2);
    return o1str.compareTo(o2str);
  }

}
