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

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author Haixing Hu
 */
public class BorderPaneTest extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    final BorderPane root = new BorderPane();
    final LabelPane top = new LabelPane("Top");
    final LabelPane left = new LabelPane("Left");
    final LabelPane right = new LabelPane("Right");
    final LabelPane bottom = new LabelPane("Bottom");
    final LabelPane center = new LabelPane("Center");
    root.setTop(top);
    root.setBottom(bottom);
    root.setLeft(left);
    root.setRight(right);
    root.setCenter(center);
    primaryStage.setScene(new Scene(root, 400, 200));
    primaryStage.show();
  }

}
