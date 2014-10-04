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

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;

import javax.annotation.Nullable;

import com.github.haixing_hu.javafx.control.menubutton.NoArrowMenuButton;
import com.github.haixing_hu.lang.Argument;

/**
 * An {@link ActionGroup} groups together zero or more {@link IAction}
 * instances, allowing for more complex controls like {@link ToolBar},
 * {@link MenuBar} and {@link ContextMenu} to be automatically generated from
 * the collection of actions inside the {@link ActionGroup}.
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
public class ActionGroup extends AbstractAction {

  protected final ObservableList<IAction> actions;

  /**
   * Creates an empty {@link ActionGroup}.
   *
   * @param buttonType
   *          the type of buttons this action created.
   */
  public ActionGroup() {
    this(null, ActionOption.DEFAULT, Collections.emptyList());
  }

  /**
   * Creates an empty {@link ActionGroup}.
   *
   * @param id
   *          the id of the new action group.
   */
  public ActionGroup(@Nullable String id) {
    this(id, ActionOption.DEFAULT, Collections.emptyList());
  }

  /**
   * Creates an empty {@link ActionGroup}.
   *
   * @param id
   *          the id of the new action group.Ï
   * @param options
   *          the options of new action group.
   */
  public ActionGroup(@Nullable String id, int options) {
    this(id, options, Collections.emptyList());
  }

  /**
   * Creates an {@link ActionGroup}.
   *
   * @param id
   *          the id of the new action group.
   * @param actions
   *          the actions in this action group.
   */
  public ActionGroup(@Nullable String id, IAction... actions) {
    this(id, ActionOption.DEFAULT, Arrays.asList(actions));
  }

  /**
   * Creates an {@link ActionGroup}.
   *
   * @param id
   *          the id of the new action group.
   * @param options
   *          the options of the new action group.
   * @param actions
   *          the actions in the new action group.
   */
  public ActionGroup(@Nullable String id, int options, IAction... actions) {
    this(id, options, Arrays.asList(actions));
  }

  /**
   * Creates an {@link ActionGroup}.
   *
   * @param id
   *          the id of the new action group.
   * @param options
   *          the options of the new action group.
   * @param actions
   *          the actions in the new action group.
   */
  public ActionGroup(@Nullable String id, int options,
      Collection<? extends IAction> actions) {
    super(id, options);
    this.options = Argument.requireNonNull("options", options);
    this.actions = FXCollections.<IAction> observableArrayList();
    this.actions.addAll(actions);
  }

  /**
   * Gets the list of {@link IAction} instances that exist within this
   * {@link ActionGroup}.
   * <p>
   * This list may be modified, as shown in the class documentation.
   *
   * @return the list of {@link IAction} instances that exist within this
   *         {@link ActionGroup}.
   */
  public final ObservableList<IAction> getActions() {
    return actions;
  }

  /**
   * Adds an action to this action group.
   *
   * @param action
   *          the action to be added to this action group.
   */
  public final void add(IAction action) {
    actions.add(action);
  }

  @Override
  public ButtonBase createButton() {
    switch (options & ActionOption.BUTTON_TYPE_MASK) {
      case ActionOption.SPLIT_MENU_BUTTON: {
        final SplitMenuButton button = new SplitMenuButton();
        configMenuButton(button);
        return button;
      }
      case ActionOption.MENU_BUTTON:
      default: {
        final MenuButton button;
        if ((options & ActionOption.NO_ARROW) == 0) {
          button = new MenuButton();
        } else {
          button = new NoArrowMenuButton();
        }
        configMenuButton(button);
        return button;
      }
    }
  }

  private void configMenuButton(MenuButton button) {
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
    if ((graphic.get() != null)
        && ((options & ActionOption.HIDE_BUTTON_GRAPHIC) == 0)) {
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
    button.setOnAction(this);

    final ObservableList<MenuItem> buttonItems = button.getItems();
    for (final IAction action : actions) {
      final MenuItem item = action.createMenuItem();
      buttonItems.add(item);
    }
  }

  @Override
  public MenuItem createMenuItem() {
    switch (options & ActionOption.MENU_ITEM_TYPE_MASK) {
      case ActionOption.MENU:
      default: {
        final Menu menu = new Menu();
        configMenu(menu);
        return menu;
      }
    }
  }

  private void configMenu(Menu menu) {
    if (menuItemId != null) {
      menu.setId(menuItemId);
    }
    if ((options & ActionOption.HIDE_MENU_ITEM_TEXT) == 0) {
      menu.textProperty().bindBidirectional(text);
    }
    if (style.get() != null) {
      menu.styleProperty().bindBidirectional(style);
    }
    if (accelerator.get() != null) {
      menu.acceleratorProperty().bindBidirectional(accelerator);
    }
    if ((graphic.get() != null)
        && ((options & ActionOption.HIDE_MENU_ITEM_GRAPHIC) == 0)) {
      menu.graphicProperty().bindBidirectional(graphic);
    }
    menu.visibleProperty().bindBidirectional(visible);
    menu.mnemonicParsingProperty().bindBidirectional(mnemonicParsing);

    menu.getStyleClass().setAll(styleClass);
    menu.setOnAction(this);

    final ObservableList<MenuItem> menuItems = menu.getItems();
    for (final IAction action : actions) {
      final MenuItem item = action.createMenuItem();
      menuItems.add(item);
    }
  }

  @Override
  public Menu createMenu() {
    final Menu menu = new Menu();
    configMenu(menu);
    return menu;
  }

  @Override
  public void handle(ActionEvent event) {
    // do nothing
  }
}
