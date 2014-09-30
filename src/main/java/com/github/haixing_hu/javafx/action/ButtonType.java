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
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.MenuButton;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.ToggleButton;

/**
 * The enumeration of types of button created from an action.
 *
 * @author Haixing Hu
 */
public enum ButtonType {

  /**
   * Indicates to create a {@link Button}.
   */
  DEFAULT,

  /**
   * Indicates to create a {@link CheckBox}.
   */
  CHECK_BOX,

  /**
   * Indicates to create a {@link RadioButton}.
   */
  RADIO,

  /**
   * Indicates to create a {@link ToggleButton}.
   */
  TOGGLE,

  /**
   * Indicates to create a {@link Hyperlink}.
   */
  HYPERLINK,

  /**
   * Indicates to create a {@link MenuButton}.
   */
  MENU,

  /**
   * Indicates to create a {@link SplitMenuButton}.
   */
  SPLIT_MENU,
}
