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
package com.github.haixing_hu.javafx.control.toolbar;

import javafx.geometry.Orientation;
import javafx.scene.control.Separator;

/**
 * A {@link HorizontalSpace} will be rendered as a horizontal space.
 *
 * @author Haixing Hu
 */
public final class HorizontalSpace extends Separator {

  /**
   * Constructs a space for horizontal tool bars.
   */
  public HorizontalSpace() {
    super(Orientation.VERTICAL);
    setVisible(false);
  }

  /**
   * Constructs a space for horizontal tool bars.
   *
   * @param width
   *    the preferred width of the space.
   */
  public HorizontalSpace(double width) {
    super(Orientation.VERTICAL);
    setVisible(false);
    setPrefWidth(width);
  }

}
