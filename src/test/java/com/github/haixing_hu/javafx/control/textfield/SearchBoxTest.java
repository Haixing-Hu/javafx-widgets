/*
 * Copyright (c) 2014  Haixing Hu
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.github.haixing_hu.javafx.control.textfield;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Sample application for testing the {@link SearchBox} control.
 *
 * @author Haixing Hu
 */
public class SearchBoxTest extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    final VBox root = new VBox();
    root.setSpacing(20);
    root.setPadding(new Insets(20));
    final SearchBox box1 = new SearchBox();
    box1.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        //  TODO
      }
    });
    final SearchBox box2 = new SearchBox();
    box2.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        //  TODO
      }
    });

    root.getChildren().addAll(box1, box2);
    primaryStage.setTitle("SearchBox Sample");
    primaryStage.setScene(new Scene(root, 300, 110));
    primaryStage.show();
  }

}
