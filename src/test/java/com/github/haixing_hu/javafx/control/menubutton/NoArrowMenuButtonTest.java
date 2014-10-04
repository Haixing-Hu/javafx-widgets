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

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Test the {@link NoArrowMenuButton}.
 *
 * @author Haixing Hu
 */
public class NoArrowMenuButtonTest extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    final StackPane root = new StackPane();

    final NoArrowMenuButton button1 = new NoArrowMenuButton("No Arrow Menu Button");
    final MenuItem item1 = new MenuItem("Menu Item 1");
    final MenuItem item2 = new MenuItem("Menu Item 2");
    final MenuItem item3 = new MenuItem("Menu Item 3");
    final MenuItem item4 = new MenuItem("Menu Item 4");
    button1.getItems().addAll(item1, item2, item3, item4);
    root.getChildren().add(button1);

    primaryStage.setScene(new Scene(root, 600, 400));
    primaryStage.show();
  }

}
