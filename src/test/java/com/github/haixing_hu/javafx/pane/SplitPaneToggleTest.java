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

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Haixing Hu
 */
public class SplitPaneToggleTest extends Application {

  @Override
  public void start(Stage primaryStage) {
      final ToggleButton settings = new ToggleButton("Settings");

      final SplitPane splitPane = new SplitPane();

      final TitledPane titledPane = new TitledPane("Options", new Label("An option"));
      final VBox settingsPane = new VBox(titledPane);
      settingsPane.setMinWidth(0);
      splitPane.getItems().addAll(new BorderPane(new Label("Main content")), settingsPane);

      final DoubleProperty splitPaneDividerPosition = splitPane.getDividers().get(0).positionProperty();

      //update toggle button status if user moves divider:
      splitPaneDividerPosition.addListener((obs, oldPos, newPos) ->
          settings.setSelected(newPos.doubleValue() < 0.95));

      splitPaneDividerPosition.set(0.8);

      settings.setOnAction(event -> {
        KeyValue end ;
        if (settings.isSelected()) {
            end = new KeyValue(splitPaneDividerPosition, 0.8);
        } else {
            end = new KeyValue(splitPaneDividerPosition, 1.0);
        }
        new Timeline(new KeyFrame(Duration.seconds(0.5), end)).play();
      });

      final BorderPane root = new BorderPane(splitPane, new HBox(settings), null, null, null);
      final Scene scene = new Scene(root, 800, 600);
      primaryStage.setScene(scene);
      primaryStage.show();
  }

  public static void main(String[] args) {
      launch(args);
  }
}
