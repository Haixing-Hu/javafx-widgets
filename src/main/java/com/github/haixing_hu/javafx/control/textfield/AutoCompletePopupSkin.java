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

import java.net.URL;

import javafx.beans.binding.Bindings;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.Skin;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.input.MouseButton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The skin for the {@link AutoCompletePopup} control.
 *
 * @author ControlsFX
 * @author Haixing Hu
 */
public class AutoCompletePopupSkin<T> implements Skin<AutoCompletePopup<T>> {

  public static final String STYLE_SHEET = "/textfield/auto-completion.css";

  private final int LIST_CELL_HEIGHT = 24;  //  FIXME: fix this hard coded hack

  private final AutoCompletePopup<T> control;
  private final ListView<T> suggestionList;

  public AutoCompletePopupSkin(AutoCompletePopup<T> control) {
    this.control = control;
    suggestionList = new ListView<>(control.getSuggestions());
    suggestionList.getStyleClass().add(AutoCompletePopup.STYLE_CLASS);
    final URL cssUrl = AutoCompletionBinding.class.getResource(STYLE_SHEET);
    if (cssUrl != null) {
      suggestionList.getStylesheets().add(cssUrl.toExternalForm());
    } else {
      final Logger logger = LoggerFactory.getLogger(AutoCompletePopupSkin.class);
      logger.error("Failed to load the resource: {}", STYLE_SHEET);
    }
    //  FIXME: fix this hard coded hack.
    suggestionList.prefHeightProperty().bind(
        Bindings.size(suggestionList.getItems()).multiply(LIST_CELL_HEIGHT).add(15));

    suggestionList.maxHeightProperty().bind(control.maxHeightProperty());
    suggestionList.setCellFactory(
        TextFieldListCell.forListView(control.getConverter()));
    registerEventListener();
  }

  private void registerEventListener() {
    suggestionList.setOnMouseClicked(event -> {
      if (event.getButton() == MouseButton.PRIMARY) {
        final T item = suggestionList.getSelectionModel().getSelectedItem();
        onSuggestionChoosen(item);
      }
    });

    suggestionList.setOnKeyPressed(event -> {
      final T item = suggestionList.getSelectionModel().getSelectedItem();
      switch (event.getCode()) {
        case ENTER:
          onSuggestionChoosen(item);
          break;
        default:
          break;
      }
    });
  }

  private void onSuggestionChoosen(T suggestion) {
    if (suggestion != null) {
      Event.fireEvent(control, new SuggestionEvent<>(suggestion));
    }
  }

  @Override
  public Node getNode() {
    return suggestionList;
  }

  @Override
  public AutoCompletePopup<T> getSkinnable() {
    return control;
  }

  @Override
  public void dispose() {
    //  do nothing
  }
}
