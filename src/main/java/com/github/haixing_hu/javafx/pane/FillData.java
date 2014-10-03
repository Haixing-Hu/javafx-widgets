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

import javafx.scene.Node;

import com.github.haixing_hu.javafx.geometry.Size;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

/**
 * The layout data of the {@link FillPane}.
 *
 * @author Haixing Hu
 */
class FillData {

  double defaultWidth = - 1;
  double defaultHeight = - 1;
  double currentWhint;
  double currentHhint;
  double currentWidth = - 1;
  double currentHeight = - 1;

  Size computeSize(Node control, double wHint, double hHint, boolean flushCache) {
    if (flushCache) {
      flushCache();
    }
    if ((wHint == USE_COMPUTED_SIZE) && (hHint == USE_COMPUTED_SIZE)) {
      if ((defaultWidth == - 1) || (defaultHeight == - 1)) {
        // Size size = computeSize (wHint, hHint, flushCache);
        defaultWidth = control.prefWidth(hHint);
        defaultHeight = control.prefHeight(wHint);
      }
      return new Size(defaultWidth, defaultHeight);
    }
    if ((currentWidth == - 1) || (currentHeight == - 1)
        || (wHint != currentWhint) || (hHint != currentHhint)) {
      // Point size = control.computeSize (wHint, hHint, flushCache);
      currentWhint = wHint;
      currentHhint = hHint;
      currentWidth = control.getLayoutBounds().getWidth();
      currentHeight = control.getLayoutBounds().getHeight();
    }
    return new Size(currentWidth, currentHeight);
  }

  void flushCache() {
    defaultWidth = defaultHeight = - 1;
    currentWidth = currentHeight = - 1;
  }
}
