/*
 * Copyright (C) 2014 Haixing Hu
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package com.github.haixing_hu.javafx.action;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ContentDisplay;
import javafx.scene.input.KeyCombination;

/**
 * The abstract class for implementing the {@link IAction} interface.
 *
 * @author Haixing Hu
 */
public abstract class AbstractAction implements IAction {

  protected final StringProperty id;
  protected final StringProperty text;
  protected final StringProperty description;
  protected final StringProperty style;
  protected final ObjectProperty<KeyCombination> accelerator;
  protected final ObjectProperty<Node> graphic;
  protected final ObjectProperty<Pos> alignment;
  protected final ObjectProperty<ContentDisplay> contentDisplay;
  protected final DoubleProperty graphicTextGap;
//  protected final BooleanProperty disable;
  protected final BooleanProperty visible;
  protected final BooleanProperty mnemonicParsing;
  protected final BooleanProperty selected;
  protected final BooleanProperty allowIndeterminate;
  protected final BooleanProperty indeterminate;
  protected final BooleanProperty visited;
  protected final List<String> styleClass;

  public AbstractAction() {
    id = new SimpleStringProperty(this, "id");
    text = new SimpleStringProperty(this, "text");
    description = new SimpleStringProperty(this, "description");
    style = new SimpleStringProperty(this, "style");
    accelerator = new SimpleObjectProperty<KeyCombination>(this, "accelerator");
    graphic = new SimpleObjectProperty<Node>(this, "graphic");
    alignment = new SimpleObjectProperty<Pos>(this, "alignment", Pos.CENTER_LEFT);
    contentDisplay = new SimpleObjectProperty<ContentDisplay>(this, "contentDisplay", ContentDisplay.LEFT);
    graphicTextGap = new SimpleDoubleProperty(this, "graphicTextGap", 4);
//    disable = new SimpleBooleanProperty(this, "disable", false);
    visible = new SimpleBooleanProperty(this, "visible", true);
    mnemonicParsing = new SimpleBooleanProperty(this, "mnemonicParsing", true);
    selected = new SimpleBooleanProperty(this, "selected", false);
    allowIndeterminate = new SimpleBooleanProperty(this, "allowIndeterminate", false);
    indeterminate = new SimpleBooleanProperty(this, "indeterminate", false);
    visited = new SimpleBooleanProperty(this, "visited", false);
    styleClass = new ArrayList<String>();
  }

  @Override
  public final String getId() {
    return id.get();
  }

  @Override
  public final void setId(String id) {
    this.id.set(id);
  }

  @Override
  public final StringProperty idProperty() {
    return id;
  }

  @Override
  public final String getText() {
    return text.get();
  }

  @Override
  public final void setText(String text) {
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
  public final List<String> getStyleClass() {
    return styleClass;
  }
}
