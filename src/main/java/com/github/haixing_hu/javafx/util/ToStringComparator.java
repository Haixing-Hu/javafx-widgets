/*
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
 */
package com.github.haixing_hu.javafx.util;

import java.util.Comparator;

/**
 * An simple implementation of comparator which compares objects with their
 * string representation.
 *
 * @author Haixing Hu
 */
public final class ToStringComparator<T> implements Comparator<T> {

  @Override
  public int compare(T o1, T o2) {
    final String o1str = o1.toString();
    final String o2str = o2.toString();
    return o1str.compareTo(o2str);
  }
}
