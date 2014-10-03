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
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Test of the {@link FillPane}.
 *
 * @author Haixing Hu
 */
public class FillPaneTest extends Application {

  public static void main(String[] args) {
      launch(args);
  }

  private static final int MAX_CHILDREN_COUNT = 5;

  private static final int CHILD_PREF_WIDTH = 100;

  private static final int DEFAULT_HEIGHT = 400;

  @Override
  public void start(Stage primaryStage) throws Exception {
    final GridPane root = new GridPane();
    root.setVgap(10);
    root.setHgap(20);
    root.setPadding(new Insets(20));
    for (int i = 0; i < MAX_CHILDREN_COUNT; ++i) {
      final Button horizontal = new Button(
          "Horizontal FillPane with " + (i + 1) + " children");
      horizontal.setOnAction(new ClickAction(Orientation.HORIZONTAL, i + 1));
      root.add(horizontal, 0, i);
      final Button vertical = new Button(
          "Vertical FillPane with " + (i + 1) + " children");
      vertical.setOnAction(new ClickAction(Orientation.VERTICAL, i + 1));
      root.add(vertical, 1, i);
    }

    primaryStage.setScene(new Scene(root, 600, 400));
    primaryStage.show();
  }

  static class ClickAction implements EventHandler<ActionEvent> {

    private final Orientation orientation;
    private final int count;

    public ClickAction(Orientation orientation, int count) {
      this.orientation = orientation;
      this.count = count;
    }

    @Override
    public void handle(ActionEvent event) {
      final FillPane pane = new FillPane(orientation);
      for (int i = 0; i < count; ++i) {
        final Button child = new Button("Child " + i);
        pane.getChildren().add(child);
      }
      final Stage win = new Stage();
      win.setScene(new Scene(pane, CHILD_PREF_WIDTH * count, DEFAULT_HEIGHT));
      win.showAndWait();
    }

  }

}
