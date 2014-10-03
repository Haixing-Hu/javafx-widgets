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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Test the {@link StackPane}.
 *
 * @author Haixing Hu
 */
public class StackPaneTest extends Application {

  public static void main(String[] args) {
      launch(args);
  }

  private static final int PAGE_COUNT = 3;

  @Override
  public void start(Stage primaryStage) throws Exception {
    final VBox root = new VBox();
    final StackPane pane = new StackPane();
    final GridPane[] pages = new GridPane[PAGE_COUNT];
    for (int i = 0; i < PAGE_COUNT; ++i) {
      pages[i] = new GridPane();
      pages[i].getChildren().add(new Label("Page " + i));
      pane.getChildren().add(pages[i]);
    }
    VBox.setVgrow(pane, Priority.ALWAYS);
    root.getChildren().add(pane);

    final ToolBar toolBar = new ToolBar();
    for (int i = 0; i < PAGE_COUNT; ++i) {
      final Button button = new Button("Switch to page " + i);
      final int index = i;
      button.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
          pages[index].toFront();
        }
      });
      toolBar.getItems().add(button);
    }
    VBox.setVgrow(toolBar, Priority.NEVER);
    root.getChildren().add(toolBar);

    primaryStage.setScene(new Scene(root, 600, 400));
    primaryStage.show();
  }

}
