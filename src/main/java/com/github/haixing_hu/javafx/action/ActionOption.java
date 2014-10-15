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
 */package com.github.haixing_hu.javafx.action;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.SplitMenuButton;

import com.github.haixing_hu.javafx.control.menubutton.NoArrowMenuButton;

/**
 * Defines the bit masks of options for actions.
 *
 * @author Haixing Hu
 */
public interface ActionOption {

  /**
   * If presented, the button created from the action will not display its text.
   */
  public static final int HIDE_BUTTON_TEXT = 0x00000001;

  /**
   * If presented, the button created from the action will not display its
   * graphic.
   */
  public static final int HIDE_BUTTON_GRAPHIC = 0x00000002;

  /**
   * If presented, the menu item created from the action will not display its
   * text.
   */
  public static final int HIDE_MENU_ITEM_TEXT = 0x00000004;

  /**
   * If presented, the menu item created from the action will not display its
   * graphic.
   */
  public static final int HIDE_MENU_ITEM_GRAPHIC = 0x00000008;

  /**
   * If presented, the button created from the action will be a {@link Button}
   * .ß
   */
  public static final int BUTTON = 0x00000010;

  /**
   * If presented, the button created from the action will be a {@link CheckBox}
   * .
   */
  public static final int CHECK_BOX = 0x00000020;

  /**
   * If presented, the button created from the action will be a
   * {@link RadioButton}.
   */
  public static final int RADIO_BUTTON = 0x00000040;

  /**
   * If presented, the button created from the action will be a
   * {@link TOGGLE_BUTTON}.
   */
  public static final int TOGGLE_BUTTON = 0x00000080;

  /**
   * If presented, the button created from the action will be a
   * {@link Hyperlink}.
   */
  public static final int HYPERLINK = 0x00000100;

  /**
   * If presented, the button created from the action will be a
   * {@link MenuButton}.
   */
  public static final int MENU_BUTTON = 0x00000200;

  /**
   * If presented, the button created from the action will be a
   * {@link SplitMenuButton}.
   */
  public static final int SPLIT_MENU_BUTTON = 0x00000400;

  /**
   * If presented, the menu item created from the action will be a
   * {@link MenuItem}.
   */
  public static final int MENU_ITEM = 0x00000800;

  /**
   * If presented, the menu item created from the action will be a
   * {@link CheckMenuItem}.
   */
  public static final int CHECK_MENU_ITEM = 0x00001000;

  /**
   * If presented, the menu item created from the action will be a
   * {@link RadioMenuItem}.
   */
  public static final int RADIO_MENU_ITEM = 0x00002000;

  /**
   * If presented, the menu item created from the action will be a {@link Menu}.
   */
  public static final int MENU = 0x00004000;

  /**
   * If presented, the menu item created from the action will be a
   * {@link CntextMenuItem}.
   */
  public static final int CUSTOM_MENU_ITEM = 0x00008000;

  /**
   * Indicates that the action will display a dialog.
   * <p>
   * If the action will display a dialog, the text of the menu item created from
   * the action should be appended with a "...".
   */
  public static final int SHOW_DIALOG = 0x00010000;

  /**
   * Indicates that the action should create a menu button without the context
   * menu arrow, i.e, a {@link NoArrowMenuButton}.
   */
  public static final int NO_ARROW = 0x00020000;

  /**
   * The default options for actions.
   */
  public static final int DEFAULT = BUTTON | MENU_ITEM | MENU_BUTTON | MENU;

  /**
   * The bit mask of all button types.
   */
  public static final int BUTTON_TYPE_MASK = BUTTON | CHECK_BOX | RADIO_BUTTON
      | TOGGLE_BUTTON | HYPERLINK | MENU_BUTTON | SPLIT_MENU_BUTTON;

  /**
   * The bit mask of all menu item types.
   */
  public static final int MENU_ITEM_TYPE_MASK = MENU_ITEM | CHECK_MENU_ITEM
      | RADIO_MENU_ITEM | MENU | CUSTOM_MENU_ITEM;
}
