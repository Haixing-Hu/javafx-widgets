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

import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;

/**
 * The enumeration of types of menu items created from an action.
 *
 * @author Haixing Hu
 */
public enum MenuItemType {

  /**
   * Indicates to create a {@link MenuItem}.
   */
  DEFAULT,

  /**
   * Indicates to create a {@link CheckMenuItem}.
   */
  CHECK,

  /**
   * Indicates to create a {@link RadioMenuItem}.
   */
  RADIO,
}
