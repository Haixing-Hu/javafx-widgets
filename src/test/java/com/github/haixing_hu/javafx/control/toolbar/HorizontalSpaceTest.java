/******************************************************************************
 *
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
 ******************************************************************************/

package com.github.haixing_hu.javafx.control.toolbar;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Sample application for testing the {@link HorizontalSpace} control.
 *
 * @author Haixing Hu
 */
public class HorizontalSpaceTest extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    final VBox root = new VBox();
    final ToolBar toolbar = new ToolBar();
    toolbar.getItems().add(new Button("button 1"));
    toolbar.getItems().add(new Button("button 2"));
    toolbar.getItems().add(new HorizontalSpace());
    toolbar.getItems().add(new Button("button 3"));
    toolbar.getItems().add(new HorizontalSpace(100));
    toolbar.getItems().add(new Button("button 4"));
    toolbar.getItems().add(new Button("button 5"));

    root.getChildren().add(toolbar);
    primaryStage.setTitle("HorizontalSpace Sample");
    primaryStage.setScene(new Scene(root, 550, 100));
    primaryStage.show();
  }

}
