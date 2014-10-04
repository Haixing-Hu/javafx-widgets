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

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.MenuButton;

import com.sun.javafx.scene.control.skin.ContextMenuContent;

/**
 * Skin for {@link NoArrowMenuButton} control.
 *
 * @author Haixing Hu
 */
@SuppressWarnings("restriction")
public class NoArrowMenuButtonSkin extends
    NoArrowMenuButtonSkinBase<NoArrowMenuButton, NoArrowMenuButtonBehavior> {

  static final String AUTOHIDE = "autoHide";

  /***************************************************************************
   * * Constructors * *
   **************************************************************************/

  /**
   * Creates a new MenuButtonSkin for the given MenuButton
   *
   * @param menuButton
   *          the MenuButton
   */
  public NoArrowMenuButtonSkin(final NoArrowMenuButton menuButton) {
    super(menuButton, new NoArrowMenuButtonBehavior(menuButton));
    // MenuButton's showing does not get updated when autoHide happens,
    // as that hide happens under the covers. So we add to the menuButton's
    // properties map to which the MenuButton can react and update accordingly..
    popup.setOnAutoHide(new EventHandler<Event>() {
      @Override
      public void handle(Event t) {
        final MenuButton menuButton = getSkinnable();
        // work around for the fact autohide happens twice
        // remove this check when that is fixed.
        if (! menuButton.getProperties().containsKey(AUTOHIDE)) {
          menuButton.getProperties().put(AUTOHIDE, Boolean.TRUE);
        }
      }
    });
    // request focus on content when the popup is shown
    popup.setOnShown(event -> {
      final ContextMenuContent cmContent =
          (ContextMenuContent) popup.getSkin().getNode();
      if (cmContent != null) {
        cmContent.requestFocus();
      }
    });

    if (menuButton.getOnAction() == null) {
      menuButton.setOnAction(e -> {
        menuButton.show();
      });
    }

    label.setLabelFor(menuButton);
  }
}
