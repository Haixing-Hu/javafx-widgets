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
package com.github.haixing_hu.javafx.action;

import javafx.beans.binding.Bindings;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.CheckBox;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Tooltip;

import javax.annotation.Nullable;

/**
 * The default implementation of {@link IAction}.
 * <p>
 * <b>NOTE: </b> In this implementation, some style related properties (style,
 * graphic, alignment, contentDisplay, graphicTextGap, etc) of the action are
 * not bind to the corresponding properties of the created button or menu item,
 * unless they have been set to some value before the creation of button or menu
 * item. This is because some times we want to use the CSS style sheet to
 * control the outlook of the button or menu item, while if we bind the style
 * related property of the button to the action, the CSS styling may be not work
 * in some case.
 *
 * @author Haixing Hu
 */
public abstract class Action extends AbstractAction {

  /**
   * Creates an {@link Action} use the default options.
   */
  public Action() {
    this(null, ActionOption.DEFAULT);
  }

  /**
   * Creates an {@link Action}.
   *
   * @param id
   *          the id of the new action.
   * @param options
   *          the options of the new action.
   */
  public Action(@Nullable String id) {
    this(id, ActionOption.DEFAULT);
  }

  /**
   * Creates an {@link Action}.
   *
   * @param options
   *          the options of the new action.
   */
  public Action(int options) {
    this(null, options);
  }

  /**
   * Creates an {@link Action}.
   *
   * @param id
   *          the id of the new action.
   * @param options
   *          the options of the new action.
   */
  public Action(@Nullable String id, int options) {
    super(id, options);
  }

  @Override
  public ButtonBase createButton() {
    switch (options & ActionOption.BUTTON_TYPE_MASK) {
      case ActionOption.CHECK_BOX: {
        final CheckBox button = new CheckBox();
        configButton(button);
        configCheckBox(button);
        return button;
      }
      case ActionOption.RADIO_BUTTON: {
        final RadioButton button = new RadioButton();
        configButton(button);
        configRadioButon(button);
        return button;
      }
      case ActionOption.TOGGLE_BUTTON: {
        final ToggleButton button = new ToggleButton();
        configButton(button);
        configToggleButon(button);
        return button;
      }
      case ActionOption.HYPERLINK: {
        final Hyperlink button = new Hyperlink();
        configButton(button);
        configHyperlink(button);
        return button;
      }
      case ActionOption.BUTTON:
      default: {
        final Button button = new Button();
        configButton(button);
        return button;
      }
    }
  }

  private void configButton(ButtonBase button) {
    if (buttonId != null) {
      button.setId(buttonId);
    }
    if ((options & ActionOption.HIDE_BUTTON_TEXT) == 0) {
      button.textProperty().bindBidirectional(text);
    }
    if (description.get() != null) {
      final Tooltip tooltip = new Tooltip();
      tooltip.textProperty().bindBidirectional(description);
      button.setTooltip(tooltip);
    }
    if (style.get() != null) {
      button.styleProperty().bindBidirectional(style);
    }
    if ((graphic.get() != null) && ((options & ActionOption.HIDE_BUTTON_GRAPHIC) == 0)) {
      button.graphicProperty().bindBidirectional(graphic);
    }
    if (alignment.get() != null) {
      button.alignmentProperty().bindBidirectional(alignment);
    }
    if (contentDisplay.get() != null) {
      button.contentDisplayProperty().bindBidirectional(contentDisplay);
    }
    if (graphicTextGap.get() >= 0) {
      button.graphicTextGapProperty().bindBidirectional(graphicTextGap);
    }

    button.visibleProperty().bindBidirectional(visible);
    button.managedProperty().bindBidirectional(managed);
    button.mnemonicParsingProperty().bindBidirectional(mnemonicParsing);

    button.getStyleClass().addAll(styleClass);
    if (bindStyleClass) {
      styleClass.setAll(button.getStyleClass());
      Bindings.bindContentBidirectional(styleClass, button.getStyleClass());
    }

    button.setOnAction(this);
  }

  private void configCheckBox(CheckBox button) {
    button.selectedProperty().bindBidirectional(selected);
    button.allowIndeterminateProperty().bindBidirectional(allowIndeterminate);
    button.indeterminateProperty().bindBidirectional(indeterminate);
  }

  private void configRadioButon(RadioButton button) {
    button.selectedProperty().bindBidirectional(selected);
  }

  private void configToggleButon(ToggleButton button) {
    button.selectedProperty().bindBidirectional(selected);
  }

  private void configHyperlink(Hyperlink button) {
    button.visitedProperty().bindBidirectional(visited);
  }

  @Override
  public MenuItem createMenuItem() {
    switch (options & ActionOption.MENU_ITEM_TYPE_MASK) {
      case ActionOption.CHECK_MENU_ITEM: {
        final CheckMenuItem item = new CheckMenuItem();
        configMenuItem(item);
        configCheckMenuItem(item);
        return item;
      }
      case ActionOption.RADIO_MENU_ITEM: {
        final RadioMenuItem item = new RadioMenuItem();
        configMenuItem(item);
        configRadioMenuItem(item);
        return item;
      }
      case ActionOption.MENU: {
        final Menu item = new Menu();
        configMenuItem(item);
        return item;
      }
      case ActionOption.CUSTOM_MENU_ITEM: {
        final CustomMenuItem item = new CustomMenuItem();
        configMenuItem(item);
        return item;
      }
      case ActionOption.MENU_ITEM:
      default: {
        final MenuItem item = new MenuItem();
        configMenuItem(item);
        return item;
      }
    }
  }

  private void configMenuItem(MenuItem item) {
    if (menuItemId != null) {
      item.setId(menuItemId);
    }
    if ((options & ActionOption.HIDE_MENU_ITEM_TEXT) == 0) {
      item.textProperty().bindBidirectional(text);
    }
    if (style.get() != null) {
      item.styleProperty().bindBidirectional(style);
    }
    if (accelerator.get() != null) {
      item.acceleratorProperty().bindBidirectional(accelerator);
    }
    if ((graphic.get() != null)
        && ((options & ActionOption.HIDE_MENU_ITEM_GRAPHIC) == 0)) {
      item.graphicProperty().bindBidirectional(graphic);
    }

    item.visibleProperty().bindBidirectional(visible);
    item.mnemonicParsingProperty().bindBidirectional(mnemonicParsing);

    item.getStyleClass().addAll(styleClass);
    if (bindStyleClass) {
      styleClass.setAll(item.getStyleClass());
      Bindings.bindContentBidirectional(styleClass, item.getStyleClass());
    }

    item.setOnAction(this);
  }

  private void configCheckMenuItem(CheckMenuItem item) {
    item.selectedProperty().bindBidirectional(selected);
  }

  private void configRadioMenuItem(RadioMenuItem item) {
    item.selectedProperty().bindBidirectional(selected);
  }

  @Override
  public Menu createMenu() {
    final Menu item = new Menu();
    configMenuItem(item);
    return item;
  }
}
