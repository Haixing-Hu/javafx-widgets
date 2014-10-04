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
 *     Haixing Hu (https://github.com/Haixing-Hu/) - Initial implementation and API.
 *
 ******************************************************************************/

package com.github.haixing_hu.javafx.control.menubutton;

import java.util.ArrayList;
import java.util.List;

import com.sun.javafx.scene.control.behavior.KeyBinding;
import com.sun.javafx.scene.control.behavior.MenuButtonBehaviorBase;

import static javafx.scene.input.KeyCode.ENTER;
import static javafx.scene.input.KeyCode.SPACE;
import static javafx.scene.input.KeyEvent.KEY_PRESSED;

/**
 * Behavior for {@link NoArrowMenuButton}.
 *
 * @author Haixing Hu
 */
@SuppressWarnings("restriction")
public class NoArrowMenuButtonBehavior extends MenuButtonBehaviorBase<NoArrowMenuButton> {

  /***************************************************************************
   *                                                                         *
   * Constructors                                                            *
   *                                                                         *
   **************************************************************************/

  /**
   * Creates a new MenuButtonBehavior for the given MenuButton.
   *
   * @param menuButton the MenuButton
   */
  public NoArrowMenuButtonBehavior(final NoArrowMenuButton menuButton) {
      super(menuButton, MENU_BUTTON_BINDINGS);
  }

  /***************************************************************************
   *                                                                         *
   * Key event handling                                                      *
   *                                                                         *
   **************************************************************************/

  /**
   * The key bindings for the MenuButton. Sets up the keys to open the menu.
   */
  protected static final List<KeyBinding> MENU_BUTTON_BINDINGS = new ArrayList<KeyBinding>();
  static {
      MENU_BUTTON_BINDINGS.addAll(BASE_MENU_BUTTON_BINDINGS);
      MENU_BUTTON_BINDINGS.add(new KeyBinding(SPACE, KEY_PRESSED, OPEN_ACTION));
      MENU_BUTTON_BINDINGS.add(new KeyBinding(ENTER, KEY_PRESSED, OPEN_ACTION));
  }

}
