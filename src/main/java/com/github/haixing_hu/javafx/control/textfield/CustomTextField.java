/******************************************************************************
 *
 * Copyright (c) 2014  Haixing Hu
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     ControlsFX -  Initial implementation and API.
 *     Haixing Hu (https://github.com/Haixing-Hu/) - Refactor.
 *
 ******************************************************************************/

/**
 * Copyright (c) 2013, ControlsFX
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 *     * Neither the name of ControlsFX, any associated website, nor the
 * names of its contributors may be used to endorse or promote products
 * derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL CONTROLSFX BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.github.haixing_hu.javafx.control.textfield;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;
import javafx.scene.control.Skin;
import javafx.scene.control.TextField;

import javax.annotation.Nullable;

/**
 * A base class for people wanting to customize a {@link TextField} to contain
 * nodes inside the text field itself, without being on top of the users
 * typed-in text.
 *
 * <h3>Screenshot</h3>
 * <p>
 * The following screenshot is taken from the HelloControlsFX sample
 * application, and shows a normal TextField, with a
 * {@link TextFields#createClearableTextField() clearable text field}, followed
 * by three CustomTextFields. Note what happens with long text input - it is
 * prevented from going beneath the left and right graphics. Of course, if the
 * keyboard caret moves to the right, the text will become visible, but this is
 * because it will all scroll to the left (as is the case in a normal
 * {@link TextField}.
 *
 * <br/>
 * <center> <img src="customTextField.png" /> </center>
 *
 * @see TextFields
 * @see CustomPasswordField
 * @author ControlsFX
 * @author Haixing Hu
 */
public class CustomTextField extends TextField {

  public static final String STYLE_CLASS = "custom-text-field";

  private final ObjectProperty<Node> left;
  private final ObjectProperty<Node> right;

  /**
   * Instantiates a default CustomTextField.
   */
  public CustomTextField() {
    super();
    left = new SimpleObjectProperty<>(this, "left");
    right = new SimpleObjectProperty<>(this, "right");
    getStyleClass().add(STYLE_CLASS);
  }

  /**
   * Gets the {@link ObjectProperty} wrapping the {@link Node} that is placed on
   * the left of the text field.
   *
   * @return An {@link ObjectProperty} wrapping the {@link Node} that is placed
   *         on the left of the text field.
   */
  public final ObjectProperty<Node> leftProperty() {
    return left;
  }

  /**
   * Gets the {@link Node} that is placed on the left of the text field.
   *
   * @return the {@link Node} that is placed on the left of the text field, or
   *         {@code null} if has none.
   */
  public final Node getLeft() {
    return left.get();
  }

  /**
   * Sets the {@link Node} that is placed on the left of the text field.
   *
   * @param value
   *          the new {@link Node} that is placed on the left of the text field,
   *          or {@code null} if has none.
   */
  public final void setLeft(@Nullable Node value) {
    left.set(value);
  }

  /**
   * Gets the {@link ObjectProperty} wrapping the {@link Node} that is placed on
   * the right of the text field.
   *
   * @return An {@link ObjectProperty} wrapping the {@link Node} that is placed
   *         on the right of the text field.
   */
  public final ObjectProperty<Node> rightProperty() {
    return right;
  }

  /**
   * Gets the {@link Node} that is placed on the right of the text field.
   *
   * @return the {@link Node} that is placed on the right of the text field, or
   *         {@code null} if has none.
   */
  public final Node getRight() {
    return right.get();
  }

  /**
   * Sets the {@link Node} that is placed on the right of the text field.
   *
   * @param value
   *          the new {@link Node} that is placed on the right of the text
   *          field, or {@code null} if has none.
   */
  public final void setRight(Node value) {
    right.set(value);
  }

  @Override
  protected Skin<?> createDefaultSkin() {
    return new CustomTextFieldSkin(this) {
      @Override
      public ObjectProperty<Node> leftProperty() {
        return CustomTextField.this.leftProperty();
      }

      @Override
      public ObjectProperty<Node> rightProperty() {
        return CustomTextField.this.rightProperty();
      }
    };
  }
}
