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

import java.util.List;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Control;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.input.KeyCombination;

/**
 * A {@link SeparatorAction} will create a separator button or separator menu
 * item.
 * <p>
 * <b>NOTE:</b> Calling all getter and setter functions of this class will cause
 * an exception.
 *
 * @author Haixing Hu
 */
public class SeparatorAction implements IAction {

  /**
   * The ID of all the separator actions.
   */
  public static final String ID = "__SEPARATOR__";

  @Override
  public void handle(ActionEvent event) {
    // do nothing
  }

  @Override
  public String getId() {
    return ID;
  }

  @Override
  public void setId(String id) {
    throw new UnsupportedOperationException();
  }

  @Override
  public String getButtonId() {
    throw new UnsupportedOperationException();
  }

  @Override
  public void setButtonId(String id) {
    throw new UnsupportedOperationException();
  }

  @Override
  public String getMenuItemId() {
    throw new UnsupportedOperationException();
  }

  @Override
  public void setMenuItemId(String id) {
    throw new UnsupportedOperationException();
  }

  @Override
  public String getText() {
    throw new UnsupportedOperationException();
  }

  @Override
  public void setText(String text) {
    throw new UnsupportedOperationException();
  }

  @Override
  public StringProperty textProperty() {
    throw new UnsupportedOperationException();
  }

  @Override
  public String getDescription() {
    throw new UnsupportedOperationException();
  }

  @Override
  public void setDescription(String description) {
    throw new UnsupportedOperationException();
  }

  @Override
  public StringProperty descriptionProperty() {
    throw new UnsupportedOperationException();
  }

  @Override
  public String getStyle() {
    throw new UnsupportedOperationException();
  }

  @Override
  public void setStyle(String description) {
    throw new UnsupportedOperationException();
  }

  @Override
  public StringProperty styleProperty() {
    throw new UnsupportedOperationException();
  }

  @Override
  public KeyCombination getAccelerator() {
    throw new UnsupportedOperationException();
  }

  @Override
  public void setAccelerator(KeyCombination accelerator) {
    throw new UnsupportedOperationException();
  }

  @Override
  public ObjectProperty<KeyCombination> acceleratorProperty() {
    throw new UnsupportedOperationException();
  }

  @Override
  public Node getGraphic() {
    throw new UnsupportedOperationException();
  }

  @Override
  public void setGraphic(Node graphic) {
    throw new UnsupportedOperationException();
  }

  @Override
  public ObjectProperty<Node> graphicProperty() {
    throw new UnsupportedOperationException();
  }

  @Override
  public Pos getAlignment() {
    throw new UnsupportedOperationException();
  }

  @Override
  public void setAlignment(Pos alignment) {
    throw new UnsupportedOperationException();
  }

  @Override
  public ObjectProperty<Pos> alignmentProperty() {
    throw new UnsupportedOperationException();
  }

  @Override
  public ContentDisplay getContentDisplayProperty() {
    throw new UnsupportedOperationException();
  }

  @Override
  public void setContentDisplayProperty(ContentDisplay contentDisplay) {
    throw new UnsupportedOperationException();
  }

  @Override
  public ObjectProperty<ContentDisplay> contentDisplayProperty() {
    throw new UnsupportedOperationException();
  }

  @Override
  public double getGraphicTextGap() {
    throw new UnsupportedOperationException();
  }

  @Override
  public void setGraphicTextGap(double graphicTextGap) {
    throw new UnsupportedOperationException();
  }

  @Override
  public DoubleProperty graphicTextGapProperty() {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean isSelected() {
    throw new UnsupportedOperationException();
  }

  @Override
  public void setSelected(boolean selected) {
    throw new UnsupportedOperationException();
  }

  @Override
  public BooleanProperty selectedProperty() {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean isAllowIndeterminateProperty() {
    throw new UnsupportedOperationException();
  }

  @Override
  public void setAllowIndeterminateProperty(boolean allowIndeterminate) {
    throw new UnsupportedOperationException();
  }

  @Override
  public BooleanProperty allowIndeterminateProperty() {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean isIndeterminate() {
    throw new UnsupportedOperationException();
  }

  @Override
  public void setIndeterminate(boolean indeterminate) {
    throw new UnsupportedOperationException();
  }

  @Override
  public BooleanProperty indeterminateProperty() {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean isVisited() {
    throw new UnsupportedOperationException();
  }

  @Override
  public void setVisited(boolean visited) {
    throw new UnsupportedOperationException();

  }

  @Override
  public BooleanProperty visitedProperty() {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean isVisible() {
    throw new UnsupportedOperationException();
  }

  @Override
  public void setVisible(boolean visible) {
    throw new UnsupportedOperationException();
  }

  @Override
  public BooleanProperty visibleProperty() {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean isManaged() {
    throw new UnsupportedOperationException();
  }

  @Override
  public void setManaged(boolean managed) {
    throw new UnsupportedOperationException();
  }

  @Override
  public BooleanProperty managedProperty() {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean isMnemonicParsing() {
    throw new UnsupportedOperationException();
  }

  @Override
  public void setMnemonicParsing(boolean mnemonicParsing) {
    throw new UnsupportedOperationException();

  }

  @Override
  public BooleanProperty mnemonicParsingProperty() {
    throw new UnsupportedOperationException();
  }

  @Override
  public List<String> getStyleClass() {
    throw new UnsupportedOperationException();
  }

  @Override
  public Control createButton() {
    return new Separator();
  }

  @Override
  public MenuItem createMenuItem() {
    return new SeparatorMenuItem();
  }

  @Override
  public Menu createMenu() {
    throw new UnsupportedOperationException();
  }

  @Override
  public void hide() {
    throw new UnsupportedOperationException();
  }

  @Override
  public void show() {
    throw new UnsupportedOperationException();
  }
}
