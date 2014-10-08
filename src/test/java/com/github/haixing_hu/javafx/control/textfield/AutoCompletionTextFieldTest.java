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

package com.github.haixing_hu.javafx.control.textfield;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Sample application for testing the {@link SearchBox} control.
 *
 * @author Haixing Hu
 */
public class AutoCompletionTextFieldTest extends Application {

  private final Logger logger = LoggerFactory.getLogger(AutoCompletionTextFieldTest.class);
  private AutoCompletionBinding<String> autoCompletionBinding;

  private final Set<String> possibleSuggestions = new HashSet<>(
      Arrays.asList("Hey", "Hello", "Hello World", "Apple", "Cool", "Costa",
          "Cola", "Coca Cola")
  );

  private TextField learningTextField;

  public Pane createPane(final Stage stage) {
    final BorderPane root = new BorderPane();
    final GridPane grid = new GridPane();
    grid.setVgap(10);
    grid.setHgap(10);
    grid.setPadding(new Insets(30, 30, 0, 30));
    //
    // TextField with static auto-complete functionality
    //
    final TextField textField = new TextField();

    TextFields.bindAutoCompletion(textField, "Hey", "Hello", "Hello World",
        "Apple", "Cool", "Costa", "Cola", "Coca Cola");

    grid.add(new Label("Auto-complete Text"), 0, 0);
    grid.add(textField, 1, 0);
    GridPane.setHgrow(textField, Priority.ALWAYS);

    //
    // TextField with learning auto-complete functionality
    // Learn the word when user presses ENTER
    //
    learningTextField = new TextField();
    autoCompletionBinding = TextFields.bindAutoCompletion(learningTextField,
        possibleSuggestions);
    learningTextField.setOnKeyPressed(new EventHandler<KeyEvent>() {
      @Override
      public void handle(KeyEvent ke) {
        switch (ke.getCode()) {
          case ENTER:
            autoCompletionLearnWord(learningTextField.getText().trim());
            break;
          default:
            break;
        }
      }
    });

    grid.add(new Label("Learning TextField"), 0, 1);
    grid.add(learningTextField, 1, 1);
    GridPane.setHgrow(learningTextField, Priority.ALWAYS);

    root.setTop(grid);
    return root;
  }

  private void autoCompletionLearnWord(String newWord) {
    logger.info("Learning new word: {}", newWord);
    possibleSuggestions.add(newWord);
    // we dispose the old binding and recreate a new binding
    if (autoCompletionBinding != null) {
      autoCompletionBinding.dispose();
    }
    autoCompletionBinding =
        TextFields.bindAutoCompletion(learningTextField, possibleSuggestions);
  }

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    final Pane root = createPane(primaryStage);
    primaryStage.setTitle("Test AutoCompletionTextField");
    primaryStage.setScene(new Scene(root, 400, 300));
    primaryStage.show();
  }

}
