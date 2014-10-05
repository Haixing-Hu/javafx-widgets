/**
 * Copyright (c) 2013, ControlsFX
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 *     * Neither the name of ControlsFX, any associated website, nor the
 * names of its contributors may be used to endorse or promote products
 * derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL CONTROLSFX BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.github.haixing_hu.javafx.control.popover;

import java.text.NumberFormat;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.Slider;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

public class PopOverTest extends Application {

  private PopOver popOver;
  private DoubleProperty masterArrowSize;
  private DoubleProperty masterArrowIndent;
  private DoubleProperty masterCornerRadius;
  private ObjectProperty<ArrowLocation> masterArrowLocation;
  private StringProperty masterTitle;
  private double targetX;
  private double targetY;
  private CheckBox detached;
  private CheckBox detachable;
  private CheckBox autoPosition;
  private Circle circle;
  private Line line1;
  private Line line2;

  public Node getPanel(Stage stage) {
    final Group group = new Group();

    final Rectangle rect = new Rectangle();
    rect.setStroke(Color.BLACK);
    rect.setFill(Color.CORAL);
    rect.setWidth(220);
    rect.setHeight(220);
    group.getChildren().add(rect);

    circle = new Circle();
    circle.setStroke(Color.BLACK);
    circle.setFill(Color.WHITE);
    group.getChildren().add(circle);

    line1 = new Line();
    line1.setFill(Color.BLACK);
    group.getChildren().add(line1);

    line2 = new Line();
    line2.setFill(Color.BLACK);
    group.getChildren().add(line2);

    /*
     * These master properties are only needed for this demo as we want to make
     * sure that the settings done by the user via the demo controls will be
     * applied to all popovers that are currently visible (this includes the
     * detached ones).
     */
    masterArrowSize = new SimpleDoubleProperty(12);
    masterArrowIndent = new SimpleDoubleProperty(12);
    masterCornerRadius = new SimpleDoubleProperty(6);
    masterArrowLocation = new SimpleObjectProperty<>(ArrowLocation.LEFT_TOP);
    masterTitle = new SimpleStringProperty();

    rect.setOnScroll(new EventHandler<ScrollEvent>() {
      @Override
      public void handle(ScrollEvent evt) {
        final double delta = evt.getDeltaY();
        rect.setWidth(Math.max(100, Math.min(500, rect.getWidth() + delta)));
        rect.setHeight(Math.max(100, Math.min(500, rect.getHeight() + delta)));
      }
    });

    rect.setOnMouseClicked(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent evt) {
        if ((popOver != null) && ! popOver.isDetached()) {
          popOver.hide();
        }

        if (evt.getClickCount() == 2) {
          if ((popOver != null) && popOver.isShowing()) {
            popOver.hide(Duration.seconds(1));
          }

          targetX = evt.getScreenX();
          targetY = evt.getScreenY();

          popOver = createPopOver();


          final double size = 3;
          line1.setStartX(evt.getX() - size);
          line1.setStartY(evt.getY() - size);
          line1.setEndX(evt.getX() + size);
          line1.setEndY(evt.getY() + size);

          line2.setStartX(evt.getX() + size);
          line2.setStartY(evt.getY() - size);
          line2.setEndX(evt.getX() - size);
          line2.setEndY(evt.getY() + size);

          circle.setCenterX(evt.getX());
          circle.setCenterY(evt.getY());
          circle.setRadius(size * 3);

          if (autoPosition.isSelected()) {
            popOver.show(rect);
          } else {
            popOver.show(rect, targetX, targetY);
          }
        }
      }
    });

    final StackPane stackPane = new StackPane();
    stackPane.getChildren().add(group);
    final Label label = new Label(
        "Double Click for PopOver. Scroll for resize.");
    label.setWrapText(true);
    label.setAlignment(Pos.CENTER);
    label.setTextAlignment(TextAlignment.CENTER);
    label.requestFocus();
    label.maxWidthProperty().bind(rect.widthProperty());

    stackPane.getChildren().add(label);
    BorderPane.setMargin(stackPane, new Insets(10));

    final BorderPane borderPane = new BorderPane();
    borderPane.setCenter(stackPane);

    return borderPane;
  }

  public Node getControlPanel() {
    final Slider arrowSize = new Slider(0, 50, masterArrowSize.getValue());
    masterArrowSize.bind(arrowSize.valueProperty());
    GridPane.setFillWidth(arrowSize, true);

    final Slider arrowIndent = new Slider(0, 30, masterArrowIndent.getValue());
    masterArrowIndent.bind(arrowIndent.valueProperty());
    GridPane.setFillWidth(arrowIndent, true);

    final Slider cornerRadius = new Slider(0, 32, masterCornerRadius.getValue());
    masterCornerRadius.bind(cornerRadius.valueProperty());
    GridPane.setFillWidth(cornerRadius, true);

    final TextField title = new TextField();
    masterTitle.bind(title.textProperty());
    GridPane.setFillWidth(title, true);

    final GridPane controls = new GridPane();
    controls.setHgap(10);
    controls.setVgap(10);

    BorderPane.setMargin(controls, new Insets(10));
    BorderPane.setAlignment(controls, Pos.BOTTOM_CENTER);

    final Label titleLabel = new Label("Title:");
    GridPane.setHalignment(titleLabel, HPos.RIGHT);
    controls.add(titleLabel, 0, 0);
    controls.add(title, 1, 0);


    final Label arrowSizeLabel = new Label("Arrow Size:");
    GridPane.setHalignment(arrowSizeLabel, HPos.RIGHT);
    controls.add(arrowSizeLabel, 0, 1);
    controls.add(arrowSize, 1, 1);

    final Label arrowIndentLabel = new Label("Arrow Indent:");
    GridPane.setHalignment(arrowIndentLabel, HPos.RIGHT);
    controls.add(arrowIndentLabel, 0, 2);
    controls.add(arrowIndent, 1, 2);

    final Label cornerRadiusLabel = new Label("Corner Radius:");
    GridPane.setHalignment(cornerRadiusLabel, HPos.RIGHT);
    controls.add(cornerRadiusLabel, 0, 3);
    controls.add(cornerRadius, 1, 3);

    final Label arrowSizeValue = new Label();
    controls.add(arrowSizeValue, 2, 1);

    final Label arrowIndentValue = new Label();
    controls.add(arrowIndentValue, 2, 2);

    final Label cornerRadiusValue = new Label();
    controls.add(cornerRadiusValue, 2, 3);

    arrowSize.valueProperty().addListener(new ChangeListener<Number>() {
      @Override
      public void changed(ObservableValue<? extends Number> value,
          Number oldSize, Number newSize) {
        arrowSizeValue.setText(NumberFormat.getIntegerInstance()
            .format(newSize));
      }
    });

    arrowIndent.valueProperty().addListener(new ChangeListener<Number>() {
      @Override
      public void changed(ObservableValue<? extends Number> value,
          Number oldSize, Number newSize) {
        arrowIndentValue.setText(NumberFormat.getIntegerInstance().format(
            newSize));
      }
    });

    cornerRadius.valueProperty().addListener(new ChangeListener<Number>() {
      @Override
      public void changed(ObservableValue<? extends Number> value,
          Number oldSize, Number newSize) {
        cornerRadiusValue.setText(NumberFormat.getIntegerInstance().format(
            newSize));
      }
    });

    final Label arrowLocationLabel = new Label("Arrow Location:");
    GridPane.setHalignment(arrowLocationLabel, HPos.RIGHT);
    controls.add(arrowLocationLabel, 0, 4);

    final ComboBox<ArrowLocation> locationBox = new ComboBox<>();
    locationBox.getItems().addAll(ArrowLocation.values());
    locationBox.setValue(ArrowLocation.TOP_CENTER);
    Bindings
        .bindBidirectional(masterArrowLocation, locationBox.valueProperty());
    controls.add(locationBox, 1, 4);

    detachable = new CheckBox("Detachable");
    detachable.setSelected(true);
    controls.add(detachable, 0, 5);
    GridPane.setColumnSpan(detachable, 2);

    detached = new CheckBox("Initially detached");
    controls.add(detached, 0, 6);
    GridPane.setColumnSpan(detached, 2);

    autoPosition = new CheckBox("Auto Position");
    controls.add(autoPosition, 0, 7);
    GridPane.setColumnSpan(autoPosition, 2);

    autoPosition.setOnAction(evt -> {
      if (popOver != null) {
        popOver.hide();
      }
    });

    circle.visibleProperty()
        .bind(Bindings.not(autoPosition.selectedProperty()));
    line1.visibleProperty().bind(Bindings.not(autoPosition.selectedProperty()));
    line2.visibleProperty().bind(Bindings.not(autoPosition.selectedProperty()));

    return controls;
  }

  private PopOver createPopOver() {
    final PopOver popOver = new PopOver();
    popOver.setDetachable(detachable.isSelected());
    popOver.setDetached(detached.isSelected());
    popOver.arrowSizeProperty().bind(masterArrowSize);
    popOver.arrowIndentProperty().bind(masterArrowIndent);
    popOver.arrowLocationProperty().bind(masterArrowLocation);
    popOver.cornerRadiusProperty().bind(masterCornerRadius);
    popOver.titleProperty().bind(masterTitle);
    return popOver;
  }

  public PopOver getPopOver() {
    return popOver;
  }

  public String getSampleName() {
    return "PopOver";
  }

  public String getSampleDescription() {
    return "An implementation of a pop over control as used by Apple for its iCal application. A pop over allows"
        + " the user to see and edit an objects properties. The pop over gets displayed in its own popup window and"
        + " can be torn off in order to create several instances of it.";
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    primaryStage.setTitle(getSampleName());
    final Scene scene = new Scene((Parent)buildSample(this, primaryStage), 800, 600);
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  /**
   * Utility method to create the default look for samples.
   */
  public static Node buildSample(PopOverTest sample, Stage stage) {
      final SplitPane splitPane = new SplitPane();


      // we guarantee that the build order is panel then control panel.
      final Node samplePanel = sample.getPanel(stage);
      final Node controlPanel = sample.getControlPanel();
      splitPane.setDividerPosition(0, 0.6);

      if (samplePanel != null) {
          splitPane.getItems().add(samplePanel);
      }

      final VBox rightPanel = new VBox();
      rightPanel.getStyleClass().add("right-panel");
      rightPanel.setMaxHeight(Double.MAX_VALUE);

      boolean addRightPanel = false;

      final Label sampleName = new Label(sample.getSampleName());
      sampleName.getStyleClass().add("sample-name");
      rightPanel.getChildren().add(sampleName);

      // --- description
      final String description = sample.getSampleDescription();
      if ((description != null) && ! description.isEmpty()) {
          final Label descriptionLabel = new Label(description);
          descriptionLabel.getStyleClass().add("description");
          descriptionLabel.setWrapText(true);
          rightPanel.getChildren().add(descriptionLabel);

          addRightPanel = true;
      }

      if (controlPanel != null) {
          rightPanel.getChildren().add(new Separator());

          controlPanel.getStyleClass().add("control-panel");
          rightPanel.getChildren().add(controlPanel);
          VBox.setVgrow(controlPanel, Priority.ALWAYS);
          addRightPanel = true;
      }

      if (addRightPanel) {
          final ScrollPane scrollPane = new ScrollPane(rightPanel);
          scrollPane.setMaxHeight(Double.MAX_VALUE);
          scrollPane.setFitToWidth(true);
          scrollPane.setFitToHeight(true);
          SplitPane.setResizableWithParent(scrollPane, false);
          splitPane.getItems().add(scrollPane);
      }

      return splitPane;
  }

  public static void main(String[] args) {
    launch(args);
  }
}
