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
 */package com.github.haixing_hu.javafx.action;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ContentDisplay;
import javafx.scene.input.KeyCombination;

import javax.annotation.Nullable;

/**
 * The abstract class for implementing the {@link IAction} interface.
 *
 * @author Haixing Hu
 */
public abstract class AbstractAction implements IAction {

  protected int options;
  protected String id;
  protected String buttonId;
  protected String menuItemId;
  protected StringProperty text;
  protected StringProperty description;
  protected StringProperty style;
  protected ObjectProperty<KeyCombination> accelerator;
  protected ObjectProperty<Node> graphic;
  protected ObjectProperty<Pos> alignment;
  protected ObjectProperty<ContentDisplay> contentDisplay;
  protected DoubleProperty graphicTextGap;
//  protected BooleanProperty disable;
  protected BooleanProperty visible;
  protected BooleanProperty managed;
  protected BooleanProperty mnemonicParsing;
  protected BooleanProperty selected;
  protected BooleanProperty allowIndeterminate;
  protected BooleanProperty indeterminate;
  protected BooleanProperty visited;
  protected ObservableList<String> styleClass;
  protected boolean bindStyleClass;

  /**
   * Constructs an {@link AbstractAction}.
   */
  public AbstractAction(int options) {
    this(null, options);
  }

  /**
   * Constructs an {@link AbstractAction}.
   *
   * @param options
   *          the options of the new action.
   * @param id
   *          the id of the new action.
   */
  public AbstractAction(@Nullable String id, int options) {
    this.options = options;
    this.id = id;
    buttonId = null;
    menuItemId = null;
    text = new SimpleStringProperty(this, "text");
    description = new SimpleStringProperty(this, "description");
    style = new SimpleStringProperty(this, "style");
    accelerator = new SimpleObjectProperty<KeyCombination>(this, "accelerator");
    graphic = new SimpleObjectProperty<Node>(this, "graphic");
    alignment = new SimpleObjectProperty<Pos>(this, "alignment", Pos.CENTER_LEFT);
    contentDisplay = new SimpleObjectProperty<ContentDisplay>(this, "contentDisplay", ContentDisplay.LEFT);
    graphicTextGap = new SimpleDoubleProperty(this, "graphicTextGap", -1);
    visible = new SimpleBooleanProperty(this, "visibles", true);
    managed = new SimpleBooleanProperty(this, "managed", true);
    mnemonicParsing = new SimpleBooleanProperty(this, "mnemonicParsing", true);
    selected = new SimpleBooleanProperty(this, "selected", false);
    allowIndeterminate = new SimpleBooleanProperty(this, "allowIndeterminate", false);
    indeterminate = new SimpleBooleanProperty(this, "indeterminate", false);
    visited = new SimpleBooleanProperty(this, "visited", false);
    styleClass = FXCollections.<String>observableArrayList();
    bindStyleClass = false;
  }

  @Override
  public final String getId() {
    return id;
  }

  @Override
  public final void setId(String id) {
    this.id = id;
  }

  @Override
  public String getButtonId() {
    return buttonId;
  }

  @Override
  public void setButtonId(String buttonId) {
    this.buttonId = buttonId;
  }

  @Override
  public String getMenuItemId() {
    return menuItemId;
  }

  @Override
  public void setMenuItemId(String menuItemId) {
    this.menuItemId = menuItemId;
  }

  @Override
  public final String getText() {
    return text.get();
  }

  @Override
  public final void setText(String text) {
    if ((options & ActionOption.SHOW_DIALOG) != 0) {
      if (! text.endsWith("...")) {
        //  append a " ..." to the text
        text = text + " ...";
      }
    }
    this.text.set(text);
  }

  @Override
  public final StringProperty textProperty() {
    return text;
  }

  @Override
  public final String getDescription() {
    return description.get();
  }

  @Override
  public final void setDescription(String description) {
    this.description.set(description);
  }

  @Override
  public final StringProperty descriptionProperty() {
    return description;
  }

  @Override
  public final String getStyle() {
    return style.get();
  }

  @Override
  public final void setStyle(String style) {
    this.style.set(style);
  }

  @Override
  public final StringProperty styleProperty() {
    return style;
  }

  @Override
  public final KeyCombination getAccelerator() {
    return accelerator.get();
  }

  @Override
  public final void setAccelerator(KeyCombination accelerator) {
    this.accelerator.set(accelerator);
  }

  @Override
  public final ObjectProperty<KeyCombination> acceleratorProperty() {
    return accelerator;
  }

  @Override
  public final Node getGraphic() {
    return graphic.get();
  }

  @Override
  public final void setGraphic(Node graphic) {
    this.graphic.set(graphic);
  }

  @Override
  public final ObjectProperty<Node> graphicProperty() {
    return graphic;
  }

  @Override
  public final Pos getAlignment() {
    return alignment.get();
  }

  @Override
  public final void setAlignment(Pos alignment) {
    this.alignment.set(alignment);
  }

  @Override
  public final ObjectProperty<Pos> alignmentProperty() {
    return alignment;
  }

  @Override
  public final ContentDisplay getContentDisplayProperty() {
    return contentDisplay.get();
  }

  @Override
  public final void setContentDisplayProperty(ContentDisplay contentDisplay) {
    this.contentDisplay.set(contentDisplay);
  }

  @Override
  public final ObjectProperty<ContentDisplay> contentDisplayProperty() {
    return contentDisplay;
  }

  @Override
  public final double getGraphicTextGap() {
    return graphicTextGap.get();
  }

  @Override
  public final void setGraphicTextGap(double graphicTextGap) {
    this.graphicTextGap.set(graphicTextGap);
  }

  @Override
  public final DoubleProperty graphicTextGapProperty() {
    return graphicTextGap;
  }

//  @Override
//  public final boolean isDisable() {
//    return disable.get();
//  }
//
//  @Override
//  public final void setDisable(boolean disable) {
//    this.disable.set(disable);
//  }
//
//  @Override
//  public final BooleanProperty disableProperty() {
//    return disable;
//  }

  @Override
  public final boolean isVisible() {
    return visible.get();
  }

  @Override
  public final void setVisible(boolean visible) {
    this.visible.set(visible);
  }

  @Override
  public final BooleanProperty visibleProperty() {
    return visible;
  }

  @Override
  public final boolean isManaged() {
    return managed.get();
  }

  @Override
  public final void setManaged(boolean managed) {
    this.managed.set(managed);
  }

  @Override
  public final BooleanProperty managedProperty() {
    return managed;
  }

  @Override
  public final boolean isMnemonicParsing() {
    return mnemonicParsing.get();
  }

  @Override
  public final void setMnemonicParsing(boolean mnemonicParsing) {
    this.mnemonicParsing.set(mnemonicParsing);
  }

  @Override
  public final BooleanProperty mnemonicParsingProperty() {
    return mnemonicParsing;
  }

  @Override
  public final boolean isSelected() {
    return selected.get();
  }

  @Override
  public final void setSelected(boolean selected) {
    this.selected.set(selected);
  }

  @Override
  public final BooleanProperty selectedProperty() {
    return selected;
  }

  @Override
  public final boolean isAllowIndeterminateProperty() {
    return allowIndeterminate.get();
  }

  @Override
  public final void setAllowIndeterminateProperty(boolean allowIndeterminate) {
    this.allowIndeterminate.set(allowIndeterminate);
  }

  @Override
  public final BooleanProperty allowIndeterminateProperty() {
    return allowIndeterminate;
  }

  @Override
  public final boolean isIndeterminate() {
    return indeterminate.get();
  }

  @Override
  public final void setIndeterminate(boolean indeterminate) {
    this.indeterminate.set(indeterminate);
  }

  @Override
  public final BooleanProperty indeterminateProperty() {
    return indeterminate;
  }

  @Override
  public final boolean isVisited() {
    return visited.get();
  }

  @Override
  public final void setVisited(boolean visited) {
    this.visited.set(visited);
  }

  @Override
  public final BooleanProperty visitedProperty() {
    return visited;
  }

  @Override
  public final ObservableList<String> getStyleClass() {
    return styleClass;
  }

  @Override
  public final void hide() {
    visible.set(false);
    managed.set(false);
  }

  @Override
  public final void show() {
    visible.set(true);
    managed.set(true);
  }


  @Override
  public boolean isBindStyleClass() {
    return bindStyleClass;
  }

  @Override
  public void setBindStyleClass(boolean bindStyleClass) {
    this.bindStyleClass = bindStyleClass;
  }
}
