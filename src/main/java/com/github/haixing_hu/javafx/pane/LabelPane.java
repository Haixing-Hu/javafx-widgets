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

package com.github.haixing_hu.javafx.pane;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

/**
 * A simple {@link Pane} which displays a text in the center.
 *
 * @author Haixing Hu
 */
public class LabelPane extends StackPane {

  /**
   * Creates a {@link LabelPane} which displays a text in the center.
   *
   * @param text the text to be displayed.
   */
  public LabelPane(String text) {
    super();
    setAlignment(Pos.CENTER);
    final Label label = new Label(text);
    getChildren().add(label);
  }
}
