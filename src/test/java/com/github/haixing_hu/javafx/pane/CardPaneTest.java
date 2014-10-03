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
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Test of the {@link CardPane}.
 *
 * @author Haixing Hu
 */
public class CardPaneTest extends Application {

  public static void main(String[] args) {
      launch(args);
  }

  private static final int CARD_COUNT = 3;

  @Override
  public void start(Stage primaryStage) throws Exception {
    final VBox root = new VBox();
    final CardPane pane = new CardPane();
    for (int i = 0; i < CARD_COUNT; ++i) {
      final Pane child = new Pane();
      child.getChildren().add(new Label("Card " + i));
      child.setStyle("-fx-background-color:white");
      pane.addCard(child);
    }
    pane.setStyle("-fx-background-color: blue");
    VBox.setVgrow(pane, Priority.ALWAYS);
    root.getChildren().add(pane);

    final ToolBar toolBar1 = new ToolBar();
    for (int i = 0; i < CARD_COUNT; ++i) {
      final Button button = new Button("Show card " + i);
      final int index = i;
      button.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
          pane.showCard(index);
        }
      });
      toolBar1.getItems().add(button);
    }
    VBox.setVgrow(toolBar1, Priority.NEVER);
    root.getChildren().add(toolBar1);

    final ToolBar toolBar2 = new ToolBar();
    final Button first = new Button("Show first card.");
    first.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        pane.showFirstCard();
      }
    });
    toolBar2.getItems().add(first);

    final Button last = new Button("Show last card.");
    last.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        pane.showLastCard();
      }
    });
    toolBar2.getItems().add(last);

    final Button next = new Button("Show next card.");
    next.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        pane.showNextCard();
      }
    });
    toolBar2.getItems().add(next);

    final Button previous = new Button("Show previous card.");
    previous.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        pane.showPreviousCard();
      }
    });
    toolBar2.getItems().add(previous);

    VBox.setVgrow(toolBar2, Priority.NEVER);
    root.getChildren().add(toolBar2);

    primaryStage.setScene(new Scene(root, 600, 400));
    primaryStage.show();
  }

}
