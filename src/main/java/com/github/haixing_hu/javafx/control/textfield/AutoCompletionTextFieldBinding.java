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

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

import com.github.haixing_hu.javafx.util.ToStringConverter;

/**
 * Represents a binding between a text field and a auto-completion popup
 *
 * @param <T>
 *    the type of suggestions.
 */
public class AutoCompletionTextFieldBinding<T> extends AutoCompletionBinding<T> {

  private final StringConverter<T> converter;
  private final ChangeListener<String> textChangeListener;
  private final ChangeListener<Boolean> focusChangedListener;

  /**
   * Creates a new auto-completion binding between the given text field and the
   * given suggestion provider.
   *
   * @param textField
   *          a given text field.
   * @param suggestionProvider
   *          a given suggestion provider.
   */
  public AutoCompletionTextFieldBinding(final TextField textField,
      SuggestionProvider<T> suggestionProvider) {
    this(textField, suggestionProvider, new ToStringConverter<T>());
  }

  /**
   * Creates a new auto-completion binding between the given textField and the
   * given suggestion provider.
   *
   * @param textField
   *          a given text field.
   * @param suggestionProvider
   *          a given suggestion provider.
   * @param converter
   *          the string converter used to convert between suggestions and
   *          strings.
   */
  public AutoCompletionTextFieldBinding(final TextField textField,
      SuggestionProvider<T> suggestionProvider,
      final StringConverter<T> converter) {
    super(textField, suggestionProvider, converter);
    this.converter = converter;
    textChangeListener = new ChangeListener<String>() {
      @Override
      public void changed(ObservableValue<? extends String> obs, String oldText,
          String newText) {
        setUserInput(newText);
      }
    };
    focusChangedListener = new ChangeListener<Boolean>() {
      @Override
      public void changed(ObservableValue<? extends Boolean> obs,
          Boolean oldFocused, Boolean newFocused) {
        if (newFocused == false) {
          hidePopup();
        }
      }
    };

    textField.textProperty().addListener(textChangeListener);
    textField.focusedProperty().addListener(focusChangedListener);
  }

  @Override
  public TextField getCompletionTarget() {
    return (TextField) super.getCompletionTarget();
  }

  @Override
  public void dispose() {
    final TextField target = getCompletionTarget();
    target.textProperty().removeListener(textChangeListener);
    target.focusedProperty().removeListener(focusChangedListener);
  }

  @Override
  protected void completeUserInput(T completion) {
    final String newText = converter.toString(completion);
    final TextField target = getCompletionTarget();
    target.setText(newText);
    target.positionCaret(newText.length());
  }
}
