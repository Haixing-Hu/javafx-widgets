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

import javafx.util.StringConverter;

/**
 * A default implementation of {@link StringConverter} using the
 * {@link Object#toString()} method to convert an object to a string.
 * <p>
 * <b>NOTE:</b> calling the {@link #fromString(String)} function of this class
 * will thrown an {@link UnsupportedOperationException}.
 *
 * @author Haixing Hu
 */
public final class ToStringConverter<T> extends StringConverter<T> {

  @Override
  public String toString(T object) {
    return (object == null ? "" : object.toString());
  }

  @Override
  public T fromString(String string) {
    throw new UnsupportedOperationException();
  }

}
