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
package com.github.haixing_hu.javafx.control.textfield;

/**
 * A text field with clear content button on the right.
 *
 * @author ControlsFX
 * @author Haixing Hu
 */
public class ClearableTextField extends CustomTextField {

  public static final String STYLE_CLASS = "clearable-text-field";

  public ClearableTextField() {
    super();
    getStyleClass().add(STYLE_CLASS);
    TextFields.addClearContentButton(this, rightProperty());
  }
}
