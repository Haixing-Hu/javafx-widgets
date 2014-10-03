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
import javafx.scene.control.ToolBar;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * Unit test of the {@link SplitPaneEx} class.
 *
 * @author Haixing Hu
 */
public class SplitPaneExTest extends Application {

  private static final int CHILD_MIN_WIDTH = 0;

  private static final int CHILD_PREF_WIDTH = 160;

  private static final int DEFAULT_HEIGHT = 400;

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    final GridPane root = new GridPane();
    root.setPadding(new Insets(20));
    root.setHgap(50);
    root.setVgap(20);

    for (int n = 2; n <= 5; ++n) {
      final Button button1 = createShowHorizontalChildrenButton(n);
      GridPane.setConstraints(button1, 0, n - 2);
      root.getChildren().add(button1);

      final Button button2 = createShowVerticalChildrenButton(n);
      GridPane.setConstraints(button2, 1, n - 2);
      root.getChildren().add(button2);
    }

    primaryStage.setScene(new Scene(root, 400, 200));
    primaryStage.show();
  }

  private static Button createShowHorizontalChildrenButton(final int n) {
    final Button button = new Button(n + " horizontal children");
    button.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        final HorizontalChildren pane = new HorizontalChildren(n);
        final Stage win = new Stage();
        win.setScene(new Scene(pane, CHILD_PREF_WIDTH * n, DEFAULT_HEIGHT));
        win.showAndWait();
      }
    });
    return button;
  }

  private static Button createShowVerticalChildrenButton(final int n) {
    final Button button = new Button(n + " horizontal children");
    button.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        final VerticalChildren pane = new VerticalChildren(n);
        final Stage win = new Stage();
        win.setScene(new Scene(pane, CHILD_PREF_WIDTH * n, DEFAULT_HEIGHT));
        win.showAndWait();
      }
    });
    return button;
  }

  private static class HorizontalChildren extends VBox {

    public HorizontalChildren(int n) {
      super();
      final SplitPaneEx pane = new SplitPaneEx();
      VBox.setVgrow(pane, Priority.ALWAYS);
      for (int i = 0; i < n; ++i) {
        final LabelPane child = new LabelPane("Child " + (i+1));
        child.setMinWidth(CHILD_MIN_WIDTH);
        child.setPrefWidth(CHILD_PREF_WIDTH);
        child.setMaxWidth(CHILD_PREF_WIDTH * 2);
        pane.getItems().add(child);
      }
      final double pos = 1.0 / n;
      for (int i = 0; i < (n - 1); ++i) {
        pane.setDividerPosition(i, pos * (i + 1));
      }

      final ToolBar toolBar = new ToolBar();
      VBox.setVgrow(toolBar, Priority.NEVER);
      for (int i = 0; i < n; ++i) {
        final Button hide = new Button("Hide " + (i+1));
        hide.setOnAction(new HideChildAction(pane, i));
        final Button show = new Button("Show " + (i+1));
        show.setOnAction(new ShowChildAction(pane, i));
        toolBar.getItems().addAll(hide, show);
      }

      getChildren().addAll(pane, toolBar);
    }
  }


  private static class VerticalChildren extends HBox {

    public VerticalChildren(int n) {
      super();
      final SplitPaneEx splitPane = new SplitPaneEx();
      splitPane.setOrientation(Orientation.VERTICAL);
      HBox.setHgrow(splitPane, Priority.ALWAYS);
      for (int i = 0; i < n; ++i) {
        final LabelPane child = new LabelPane("Child " + (i+1));
        child.setMinWidth(CHILD_MIN_WIDTH);
        child.setPrefWidth(CHILD_PREF_WIDTH);
        child.setMaxWidth(CHILD_PREF_WIDTH * 2);
        splitPane.getItems().add(child);
      }
      final double pos = 1.0 / n;
      for (int i = 0; i < (n - 1); ++i) {
        splitPane.setDividerPosition(i, pos * (i + 1));
      }

      final ToolBar toolBar = new ToolBar();
      toolBar.setOrientation(Orientation.VERTICAL);
      HBox.setHgrow(toolBar, Priority.NEVER);
      for (int i = 0; i < n; ++i) {
        final Button hide = new Button("Hide " + (i+1));
        hide.setOnAction(new HideChildAction(splitPane, i));
        final Button show = new Button("Show " + (i+1));
        show.setOnAction(new ShowChildAction(splitPane, i));
        toolBar.getItems().addAll(hide, show);
      }
      getChildren().addAll(splitPane, toolBar);
    }
  }

  private static class HideChildAction implements EventHandler<ActionEvent> {

    private final SplitPaneEx pane;
    private final int index;

    public HideChildAction(final SplitPaneEx pane, final int index) {
      this.pane = pane;
      this.index = index;
    }

    @Override
    public void handle(ActionEvent event) {
      pane.hideItem(index);
    }
  }

  private static class ShowChildAction implements EventHandler<ActionEvent> {

    private final SplitPaneEx pane;
    private final int index;

    public ShowChildAction(final SplitPaneEx pane, final int index) {
      this.pane = pane;
      this.index = index;
    }

    @Override
    public void handle(ActionEvent event) {
      pane.showItem(index);
    }
  }
}
