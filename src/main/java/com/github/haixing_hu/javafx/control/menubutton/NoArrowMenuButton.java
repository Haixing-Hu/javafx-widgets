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

import javafx.scene.Node;
import javafx.scene.control.MenuButton;
import javafx.scene.control.Skin;

/**
 * An extension of {@link MenuButton} without the context menu arrow.
 *
 * @author Haixing Hu
 */
@SuppressWarnings("restriction")
public class NoArrowMenuButton extends MenuButton {

  public static final String NO_ARROW_STYLE_CLASS = "no-arrow";

  /**
   * Creates a new empty menu button. Use {@link #setText(String)},
   * {@link #setGraphic(Node)} and {@link #getItems()} to set the content.
   */
  public NoArrowMenuButton() {
    super();
    getStyleClass().add(NO_ARROW_STYLE_CLASS);
  }

  /**
   * Creates a new empty menu button with the given text to display on the menu.
   * Use {@link #setGraphic(Node)} and {@link #getItems()} to set the content.
   *
   * @param text
   *          the text to display on the menu button
   */
  public NoArrowMenuButton(String text) {
    super(text);
    getStyleClass().add(NO_ARROW_STYLE_CLASS);
  }

  /**
   * Creates a new empty menu button with the given text and graphic to display
   * on the menu. Use {@link #getItems()} to set the content.
   *
   * @param text
   *          the text to display on the menu button
   * @param graphic
   *          the graphic to display on the menu button
   */
  public NoArrowMenuButton(String text, Node graphic) {
    super(text, graphic);
    getStyleClass().add(NO_ARROW_STYLE_CLASS);
  }

  @Override
  protected Skin<?> createDefaultSkin() {
    return new NoArrowMenuButtonSkin(this);
  }
}
