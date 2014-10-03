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
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author Haixing Hu
 */
public class TabPaneTest extends Application {

  public static void main(String[] args) {
    Application.launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    primaryStage.setTitle("Tabs");
    final Group root = new Group();
    final Scene scene = new Scene(root, 400, 250, Color.WHITE);

    final TabPane tabPane = new TabPane();

    final BorderPane borderPane = new BorderPane();
    for (int i = 0; i < 5; i++) {
      final Tab tab = new Tab();
      tab.setText("Tab" + i);
      final HBox hbox = new HBox();
      hbox.getChildren().add(new Label("Tab" + i));
      hbox.setAlignment(Pos.CENTER);
      tab.setContent(hbox);
      tabPane.getTabs().add(tab);
    }
    //tabPane.setSide(Side.LEFT);
     tabPane.setSide(Side.TOP);
    // tabPane.setSide(Side.RIGHT);
    // tabPane.setSide(Side.BOTTOM);

    // bind to take available space
    borderPane.prefHeightProperty().bind(scene.heightProperty());
    borderPane.prefWidthProperty().bind(scene.widthProperty());

    borderPane.setCenter(tabPane);
    root.getChildren().add(borderPane);
    primaryStage.setScene(scene);
    primaryStage.show();
  }
}
