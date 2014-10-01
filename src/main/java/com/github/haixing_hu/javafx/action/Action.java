/*
 * Copyright (C) 2014 Haixing Hu
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package com.github.haixing_hu.javafx.action;

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
 * The simple implementation of {@link IAction}.
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
  public ButtonBase creatButton() {
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
    button.idProperty().bind(id);
    if ((options & ActionOption.HIDE_BUTTON_TEXT) == 0) {
      button.textProperty().bind(text);
    }
    if (description.get() != null) {
      final Tooltip tooltip = new Tooltip();
      tooltip.textProperty().bind(description);
      button.setTooltip(tooltip);
    }
    button.styleProperty().bind(style);
    if ((options & ActionOption.HIDE_BUTTON_GRAPHIC) == 0) {
      button.graphicProperty().bind(graphic);
    }
    button.alignmentProperty().bind(alignment);
    button.contentDisplayProperty().bind(contentDisplay);
    button.graphicTextGapProperty().bind(graphicTextGap);
    button.visibleProperty().bind(visible);
    button.mnemonicParsingProperty().bind(mnemonicParsing);
    button.getStyleClass().addAll(styleClass);
    button.setOnAction(this);
  }

  private void configCheckBox(CheckBox button) {
    button.selectedProperty().bind(selected);
    button.allowIndeterminateProperty().bind(allowIndeterminate);
    button.indeterminateProperty().bind(indeterminate);
  }

  private void configRadioButon(RadioButton button) {
    button.selectedProperty().bind(selected);
  }

  private void configToggleButon(ToggleButton button) {
    button.selectedProperty().bind(selected);
  }

  private void configHyperlink(Hyperlink button) {
    button.visitedProperty().bind(visited);
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
    item.idProperty().bind(id);
    if ((options & ActionOption.HIDE_MENU_ITEM_TEXT) == 0) {
      item.textProperty().bind(text);
    }
    item.styleProperty().bind(style);
    item.acceleratorProperty().bind(accelerator);
    if ((options & ActionOption.HIDE_MENU_ITEM_GRAPHIC) == 0) {
      item.graphicProperty().bind(graphic);
    }
    item.visibleProperty().bind(visible);
    item.mnemonicParsingProperty().bind(mnemonicParsing);
    item.getStyleClass().addAll(styleClass);
    item.setOnAction(this);
  }

  private void configCheckMenuItem(CheckMenuItem item) {
    item.selectedProperty().bind(selected);
  }

  private void configRadioMenuItem(RadioMenuItem item) {
    item.selectedProperty().bind(selected);
  }
}
