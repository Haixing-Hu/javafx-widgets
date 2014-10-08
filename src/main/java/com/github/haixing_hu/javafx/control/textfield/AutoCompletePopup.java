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
import javafx.beans.property.ObjectPropertyBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventDispatchChain;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.PopupControl;
import javafx.scene.control.Skin;
import javafx.stage.Window;
import javafx.util.StringConverter;

import com.sun.javafx.event.EventHandlerManager;

/**
 * The auto-complete-popup provides an list of available suggestions in order to
 * complete current user input.
 *
 * @param <T>
 *    the type of suggestions.
 * @author ControlsFX
 * @author Haixing Hu
 */
@SuppressWarnings("restriction")
public class AutoCompletePopup<T> extends PopupControl {

  public static final String STYLE_CLASS = "auto-complete-popup";

  private final static int TITLE_HEIGHT = 28; // HACK: Hard-coded title-bar height

  private final ObservableList<T> suggestions;
  private StringConverter<T> converter;
  private final EventHandlerManager eventHandlerManager;
  private final ObjectProperty<EventHandler<SuggestionEvent<T>>> onSuggestion;

  /**
   * Creates a {@link AutoCompletePopup}.
   */
  public AutoCompletePopup() {
    super();
    suggestions = FXCollections.observableArrayList();
    converter = null;
    eventHandlerManager = new EventHandlerManager(this);
    onSuggestion = new ObjectPropertyBase<EventHandler<SuggestionEvent<T>>>() {
      @SuppressWarnings({ "rawtypes", "unchecked" })
      @Override
      protected void invalidated() {
        eventHandlerManager.setEventHandler(SuggestionEvent.SUGGESTION,
            (EventHandler<SuggestionEvent>) (Object) get());
      }

      @Override
      public Object getBean() {
        return AutoCompletePopup.this;
      }

      @Override
      public String getName() {
        return "onSuggestion";
      }
    };

    setAutoFix(true);
    setAutoHide(true);
    setHideOnEscape(true);
    getStyleClass().add(STYLE_CLASS);
  }

  /**
   * Get the list of suggestions presented by this {@link AutoCompletePopup}.
   *
   * @return the list of suggestions presented by this {@link AutoCompletePopup}.
   */
  public ObservableList<T> getSuggestions() {
    return suggestions;
  }

  /**
   * Show this pop-up right below the given node.
   *
   * @param node
   *          a given node.
   * @throws IllegalStateException
   *           if the node is not attached to a screen or window.
   */
  public void show(Node node) {
    if ((node.getScene() == null) || (node.getScene().getWindow() == null)) {
      throw new IllegalStateException(
          "Can not show popup. The node must be attached to a scene/window.");
    }
    final Window parent = node.getScene().getWindow();
    final double x = parent.getX() + node.localToScene(0, 0).getX() + node.getScene().getX();
    final double y = parent.getY() + node.localToScene(0, 0).getY() + node.getScene().getY()
                   + TITLE_HEIGHT;    //  FIXME: fix this hard coded hack
    this.show(parent, x, y);
  }

  /**
   * Get the string converter used to turn a generic suggestion into a string
   *
   * @return the string converter used to turn a generic suggestion into a
   *         string.
   */
  public StringConverter<T> getConverter() {
    return converter;
  }

  /**
   * Set the string converter used to turn a generic suggestion into a string.
   *
   * @param converter
   *          the new string converter used to turn a generic suggestion into a
   *          string.
   */
  public void setConverter(StringConverter<T> converter) {
    this.converter = converter;
  }

  /**
   * Gets the event handler triggered when suggestions should be shown.
   *
   * @return the event handler triggered when suggestions should be shown.
   */
  public final ObjectProperty<EventHandler<SuggestionEvent<T>>> onSuggestionProperty() {
    return onSuggestion;
  }

  /**
   * Sets the event handler triggered when suggestions should be shown.
   *
   * @param value
   *          the new event handler triggered when suggestions should be shown.
   */
  public final void setOnSuggestion(EventHandler<SuggestionEvent<T>> value) {
    onSuggestionProperty().set(value);
  }

  /**
   * Gets the event handler triggered when suggestions should be shown.
   *
   * @return the event handler triggered when suggestions should be shown.
   */
  public final EventHandler<SuggestionEvent<T>> getOnSuggestion() {
    return onSuggestionProperty().get();
  }

  @Override
  public EventDispatchChain buildEventDispatchChain(EventDispatchChain tail) {
    return super.buildEventDispatchChain(tail).append(eventHandlerManager);
  }

  @Override
  protected Skin<?> createDefaultSkin() {
    return new AutoCompletePopupSkin<>(this);
  }
}
