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
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Sample application for testing the {@link CustomTextField} and
 * {@link CustomPasswordField} controls.
 *
 * @author Haixing Hu
 */
public class CustomTextFieldTest extends Application {

  public Pane createPane(Stage stage) {
    final GridPane grid = new GridPane();
    grid.setVgap(10);
    grid.setHgap(10);
    grid.setPadding(new Insets(10));

    int row = 0;

    // TextField and PasswordField labels
    final Label textFieldLabel = new Label("TextField");
    textFieldLabel.setFont(Font.font(24));
    GridPane.setHalignment(textFieldLabel, HPos.CENTER);
    final Label passwordFieldLabel = new Label("PasswordField");
    passwordFieldLabel.setFont(Font.font(24));
    GridPane.setHalignment(passwordFieldLabel, HPos.CENTER);
    grid.add(textFieldLabel, 1, row);
    grid.add(passwordFieldLabel, 2, row);
    row++;

    // normal TextField / PasswordField
    grid.add(new Label("Normal TextField / PasswordField: "), 0, row);
    grid.add(new TextField(), 1, row);
    grid.add(new PasswordField(), 2, row++);

    // Clearable*Field
    grid.add(new Label("Clearable*Field: "), 0, row);
    grid.add(new ClearableTextField(), 1, row);
    grid.add(new ClearablePasswordField(), 2, row++);

    // Custom*Field
    grid.add(new Label("Custom*Field (no additional nodes): "), 0, row);
    grid.add(new CustomTextField(), 1, row);
    grid.add(new CustomPasswordField(), 2, row++);

    final Image image = new Image("/textfield/security-low.png");

    // Custom*Field (w/ right node)
    grid.add(new Label("Custom*Field (w/ right node): "), 0, row);
    final CustomTextField customTextField1 = new CustomTextField();
    customTextField1.setRight(new ImageView(image));
    grid.add(customTextField1, 1, row);

    final CustomPasswordField customPasswordField1 = new CustomPasswordField();
    customPasswordField1.setRight(new ImageView(image));
    grid.add(customPasswordField1, 2, row++);

    // Custom*Field (w/ left node)
    grid.add(new Label("Custom*Field (w/ left node): "), 0, row);
    final CustomTextField customTextField2 = new CustomTextField();
    customTextField2.setLeft(new ImageView(image));
    grid.add(customTextField2, 1, row);

    final CustomPasswordField customPasswordField2 = new CustomPasswordField();
    customPasswordField2.setLeft(new ImageView(image));
    grid.add(customPasswordField2, 2, row++);

    // Custom*Field (w/ left + right node)
    grid.add(new Label("Custom*Field (w/ left + right node): "), 0, row);
    final CustomTextField customTextField3 = new CustomTextField();
    customTextField3.setLeft(new ImageView(image));
    customTextField3.setRight(new ImageView(image));
    grid.add(customTextField3, 1, row);

    final CustomPasswordField customPasswordField3 = new CustomPasswordField();
    customPasswordField3.setLeft(new ImageView(image));
    customPasswordField3.setRight(new ImageView(image));
    grid.add(customPasswordField3, 2, row++);

    return grid;
  }

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    final Pane root = createPane(primaryStage);
    primaryStage.setTitle("CustomTextField and CustomPasswordField Samples");
    primaryStage.setScene(new Scene(root, 700, 400));
    primaryStage.show();
  }

}
