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

import java.util.Collection;

import javafx.animation.FadeTransition;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.ObjectProperty;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import javafx.util.StringConverter;

/**
 * A class containing useful customizations for the JavaFX {@link TextField}.
 * Note that this class is experimental and the API may change in future
 * releases. Note also that this class makes use of the {@link CustomTextField}
 * class.
 *
 * @see CustomTextField
 */
public class TextFields {

  public static final String GRAPHIC_STYLE_CLASS = "graphic";

  public static final String CLEAR_BUTTON_STYLE_CLASS = "clear-button";

  public static final String SEARCH_BUTTON_STYLE_CLASS = "search-button";

  private static final Duration FADE_DURATION = Duration.millis(350);

  private TextFields() {
    // no-op
  }

  /**
   * Sets up a text field with a clear button.
   *
   * @param field
   *          a text field.
   * @param node
   *          the property of the node on the left or right hand side of the
   *          text field, used to hold the clear content button.
   */
  public static void addClearContentButton(TextField field,
      ObjectProperty<Node> node) {
    final Region clearButton = new Region();
    clearButton.getStyleClass().addAll(GRAPHIC_STYLE_CLASS);

    final StackPane clearButtonPane = new StackPane(clearButton);
    clearButtonPane.getStyleClass().addAll(CLEAR_BUTTON_STYLE_CLASS);
    clearButtonPane.setOpacity(0.0);
    clearButtonPane.setCursor(Cursor.DEFAULT);
    clearButtonPane.setOnMouseReleased(e -> field.clear());
    node.set(clearButtonPane);

    final FadeTransition fader = new FadeTransition(FADE_DURATION, clearButtonPane);
    fader.setCycleCount(1);

    field.textProperty().addListener(new InvalidationListener() {
      @Override
      public void invalidated(Observable observable) {
        final String text = field.getText();
        final boolean isTextEmpty = (text == null) || text.isEmpty();
        final boolean isButtonVisible = (fader.getNode().getOpacity() > 0);
        if (isTextEmpty && isButtonVisible) {
          setButtonVisible(false);
        } else if ((! isTextEmpty) && (! isButtonVisible)) {
          setButtonVisible(true);
        }
      }

      private void setButtonVisible(boolean visible) {
        fader.setFromValue(visible ? 0.0 : 1.0);
        fader.setToValue(visible ? 1.0 : 0.0);
        fader.play();
      }
    });
  }

  public static void addSearchButton(TextField field, ObjectProperty<Node> node) {
    final Region searchButton = new Region();
    searchButton.getStyleClass().addAll(GRAPHIC_STYLE_CLASS);

    final StackPane searchButtonPane = new StackPane(searchButton);
    searchButtonPane.getStyleClass().addAll(SEARCH_BUTTON_STYLE_CLASS);
    searchButtonPane.setCursor(Cursor.DEFAULT);
    node.set(searchButtonPane);
  }

  /**
   * Create a new auto-completion binding between the given textField and the
   * given suggestion provider.
   * <p>
   * The {@link TextFields} API has some suggestion-provider builder methods for
   * simple use cases.
   *
   * @param textField
   *          The {@link TextField} to which auto-completion shall be added
   * @param suggestionProvider
   *          A suggestion-provider strategy to use
   * @param converter
   *          The converter to be used to convert suggestions to strings.
   * @return the {@link AutoCompletionBinding}.
   */
  public static <T> AutoCompletionBinding<T> bindAutoCompletion(
      TextField textField, SuggestionProvider<T> suggestionProvider,
      StringConverter<T> converter) {
    return new AutoCompletionTextFieldBinding<>(textField, suggestionProvider,
        converter);
  }

  /**
   * Create a new auto-completion binding between the given textField and the
   * given suggestion provider.
   * <p>
   * The {@link TextFields} API has some suggestion-provider builder methods for
   * simple use cases.
   *
   * @param textField
   *          The {@link TextField} to which auto-completion shall be added
   * @param suggestionProvider
   *          A suggestion-provider strategy to use
   * @return The {@link AutoCompletionBinding}.
   */
  public static <T> AutoCompletionBinding<T> bindAutoCompletion(
      TextField textField, SuggestionProvider<T> suggestionProvider) {
    return new AutoCompletionTextFieldBinding<>(textField, suggestionProvider);
  }

  /**
   * Create a new auto-completion binding between the given {@link TextField}
   * using the given auto-complete suggestions.
   *
   * @param textField
   *          The {@link TextField} to which auto-completion shall be added
   * @param possibleSuggestions
   *          Possible auto-complete suggestions
   * @return The {@link AutoCompletionBinding}.
   */
  @SuppressWarnings("unchecked")
  public static <T> AutoCompletionBinding<T> bindAutoCompletion(
      TextField textField, T... possibleSuggestions) {
    final DefaultSuggestionProvider<T> provider = new DefaultSuggestionProvider<T>();
    provider.addSuggestions(possibleSuggestions);
    return new AutoCompletionTextFieldBinding<>(textField, provider);
  }

  /**
   * Create a new auto-completion binding between the given {@link TextField}
   * using the given auto-complete suggestions.
   *
   * @param textField
   *          The {@link TextField} to which auto-completion shall be added
   * @param possibleSuggestions
   *          Possible auto-complete suggestions
   * @return The {@link AutoCompletionBinding}.
   */
  public static <T> AutoCompletionBinding<T> bindAutoCompletion(
      TextField textField, Collection<T> possibleSuggestions) {
    final DefaultSuggestionProvider<T> provider = new DefaultSuggestionProvider<T>();
    provider.addSuggestions(possibleSuggestions);
    return new AutoCompletionTextFieldBinding<>(textField, provider);
  }
}
