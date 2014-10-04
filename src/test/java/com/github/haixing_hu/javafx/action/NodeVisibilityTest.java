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

package com.github.haixing_hu.javafx.action;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Haixing Hu
 */
public class NodeVisibilityTest extends Application {

  public static void main(String[] args) {
      launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    final VBox root = new VBox();
    final ToolBar tb1 = new ToolBar();
    final Button bt1 = new Button("Button 1");
    final Button bt2 = new Button("Button 2");
    tb1.getItems().addAll(bt1, bt2);

    final ToolBar tb2 = new ToolBar();
    final Button bt3 = new Button("Hide button 1");
    bt3.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        bt1.setVisible(false);
        bt1.setManaged(false);
      }
    });
    final Button bt4 = new Button("Show button 1");
    bt4.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        bt1.setVisible(true);
        bt1.setManaged(true);
      }
    });
    tb2.getItems().addAll(bt3, bt4);

    root.getChildren().addAll(tb1, tb2);

    primaryStage.setScene(new Scene(root, 600, 400));
    primaryStage.show();
  }
}
