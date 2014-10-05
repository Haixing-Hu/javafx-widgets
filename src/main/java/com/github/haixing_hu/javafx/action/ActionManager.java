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

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Control;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToolBar;
import javafx.scene.input.KeyCombination;

import javax.annotation.Nullable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * An {@link ActionManager} is a map from the id to the action.
 *
 * @author Haixing Hu
 */
public class ActionManager {

  private final Map<String, IAction> map;
  private final Logger logger;

  public ActionManager() {
    map = new HashMap<String, IAction>();
    logger = LoggerFactory.getLogger(ActionManager.class);
  }

  public final boolean isEmpty() {
    return map.isEmpty();
  }

  public final int size() {
    return map.size();
  }

  public final boolean contains(String id) {
    return map.containsKey(id);
  }

  public final IAction get(String id) {
    return map.get(id);
  }

  public final Collection<IAction> getAll() {
    return map.values();
  }

  public final void add(IAction action) {
    final String id = action.getId();
    if (map.containsKey(id)) {
      logger.warn("The action already exists: {}", id);
    }
    map.put(id, action);
  }

  public final IAction remove(String id) {
    return map.remove(id);
  }

  public final void clear() {
    map.clear();
  }

  /**
   * Gets the text (i.e., title) of the specified action.
   *
   * @param id
   *          the id of an action.
   * @return the text (i.e., title) of the specified action, or
   *         <code>null</code> if it has none.
   */
  public final String getText(String id) {
    final IAction action = map.get(id);
    if (action == null) {
      logger.error("Unknown action id: {}", id);
      return null;
    } else {
      return action.getText();
    }
  }

  /**
   * Sets the text (i.e., title) of the specified action.
   *
   * @param id
   *          the id of an action.
   * @param text
   *          the new text (i.e., title) to be set to the specified action, or
   *          <code>null</code> to set none.
   */
  public final void setText(String id, @Nullable String text) {
    final IAction action = map.get(id);
    if (action == null) {
      logger.error("Unknown action id: {}", id);
    } else {
      action.setText(text);
    }
  }

  /**
   * Gets the text (i.e., title) property of the specified action.
   *
   * @param id
   *          the id of an action.
   * @return the text (i.e., title) property of the specified action, or null if
   *         no such action.
   */
  public final StringProperty textProperty(String id) {
    final IAction action = map.get(id);
    if (action == null) {
      logger.error("Unknown action id: {}", id);
      return null;
    } else {
      return action.textProperty();
    }
  }

  /**
   * Gets the description (i.e., tool tip) of the specified action.
   * <p>
   * <b>NOTE: </b> this property is only useful for buttons created from this
   * action.
   *
   * @param id
   *          the id of an action.
   * @return the description (i.e., tool tip) of this action, or
   *         <code>null</code> if it has none.
   */
  public final String getDescription(String id) {
    final IAction action = map.get(id);
    if (action == null) {
      logger.error("Unknown action id: {}", id);
      return null;
    } else {
      return action.getDescription();
    }
  }

  /**
   * Sets the description (i.e., tool tip) of the specified action.
   * <p>
   * <b>NOTE: </b> this property is only useful for buttons created from this
   * action.
   *
   * @param id
   *          the id of an action.
   * @param description
   *          the new description (i.e., tool tip) to be set to this action, or
   *          <code>null</code> to set none.
   */
  public final void setDescription(String id, @Nullable String description) {
    final IAction action = map.get(id);
    if (action == null) {
      logger.error("Unknown action id: {}", id);
    } else {
      action.setDescription(description);
    }
  }

  /**
   * Gets the description (i.e., tool tip) property of this action
   * <p>
   * <b>NOTE: </b> this property is only useful for buttons created from this
   * action.
   *
   * @param id
   *          the id of an action.
   * @return the description (i.e., tool tip) property of the specified action.
   */
  public final StringProperty descriptionProperty(String id) {
    final IAction action = map.get(id);
    if (action == null) {
      logger.error("Unknown action id: {}", id);
      return null;
    } else {
      return action.descriptionProperty();
    }
  }

  /**
   * Gets the style of the specified action.
   * <p>
   * The style of an action is a string representation of the CSS style
   * associated with this specific MenuItem. This is analogous to the "style"
   * attribute of an HTML element. Note that, like the HTML style attribute,
   * this variable contains style properties and values and not the selector
   * portion of a style rule.
   *
   * @param id
   *          the id of an action.
   * @return the style of this action, or <code>null</code> if it has none.
   */
  public final String getStyle(String id) {
    final IAction action = map.get(id);
    if (action == null) {
      logger.error("Unknown action id: {}", id);
      return null;
    } else {
      return action.getStyle();
    }
  }

  /**
   * Sets the style of the specified action.
   * <p>
   * The style of an action is a string representation of the CSS style
   * associated with this specific MenuItem. This is analogous to the "style"
   * attribute of an HTML element. Note that, like the HTML style attribute,
   * this variable contains style properties and values and not the selector
   * portion of a style rule.
   *
   * @param id
   *          the id of an action.
   * @param style
   *          the new style to be set to this action, or <code>null</code> to
   *          set none.
   */
  public final void setStyle(String id, @Nullable String style) {
    final IAction action = map.get(id);
    if (action == null) {
      logger.error("Unknown action id: {}", id);
    } else {
      action.setStyle(style);
    }
  }

  /**
   * Gets the style property of the specified action.
   * <p>
   * The style of an action is a string representation of the CSS style
   * associated with this specific MenuItem. This is analogous to the "style"
   * attribute of an HTML element. Note that, like the HTML style attribute,
   * this variable contains style properties and values and not the selector
   * portion of a style rule.
   *
   * @param id
   *          the id of an action.
   * @return the style property of the specified action.
   */
  public final StringProperty styleProperty(String id) {
    final IAction action = map.get(id);
    if (action == null) {
      logger.error("Unknown action id: {}", id);
      return null;
    } else {
      return action.styleProperty();
    }
  }

  /**
   * Gets the accelerator of the specified action.
   * <p>
   * The accelerator property enables accessing the associated action in one
   * keystroke. It is a convenience offered to perform quickly a given action.
   * <p>
   * <b>NOTE: </b> this property is only useful for menu items created from this
   * action.
   *
   * @param id
   *          the id of an action.
   * @return the accelerator of this action, or <code>null</code> if it has
   *         none.
   */
  public final KeyCombination getAccelerator(String id) {
    final IAction action = map.get(id);
    if (action == null) {
      logger.error("Unknown action id: {}", id);
      return null;
    } else {
      return action.getAccelerator();
    }
  }

  /**
   * Sets the accelerator of the specified action.
   * <p>
   * The accelerator property enables accessing the associated action in one
   * keystroke. It is a convenience offered to perform quickly a given action.
   * <p>
   * <b>NOTE: </b> this property is only useful for menu items created from this
   * action.
   *
   * @param id
   *          the id of an action.
   * @param accelerator
   *          the new accelerator to be set to this action, or <code>null</code>
   *          to set none.
   */
  public final void setAccelerator(String id, @Nullable KeyCombination accelerator) {
    final IAction action = map.get(id);
    if (action == null) {
      logger.error("Unknown action id: {}", id);
    } else {
      action.setAccelerator(accelerator);
    }
  }

  /**
   * Gets the accelerator property of the specified action.
   * <p>
   * The accelerator property enables accessing the associated action in one
   * keystroke. It is a convenience offered to perform quickly a given action.
   * <p>
   * <b>NOTE: </b> this property is only useful for menu items created from this
   * action.
   *
   * @param id
   *          the id of an action.
   * @return the accelerator property of the specified action.
   */
  public final ObjectProperty<KeyCombination> acceleratorProperty(String id) {
    final IAction action = map.get(id);
    if (action == null) {
      logger.error("Unknown action id: {}", id);
      return null;
    } else {
      return action.acceleratorProperty();
    }
  }

  /**
   * Gets the optional graphic of the specified action.
   * <p>
   * The graphic of an action is normally an {@link ImageView} node, but there
   * is no requirement for this to be the case.
   *
   * @param id
   *          the id of an action.
   * @return the graphic of this action, or <code>null</code> if it has none.
   */
  public final Node getGraphic(String id) {
    final IAction action = map.get(id);
    if (action == null) {
      logger.error("Unknown action id: {}", id);
      return null;
    } else {
      return action.getGraphic();
    }
  }

  /**
   * Sets the optional graphic of the specified action.
   * <p>
   * The graphic of an action is normally an {@link ImageView} node, but there
   * is no requirement for this to be the case.
   *
   * @param id
   *          the id of an action.
   * @param graphic
   *          the new graphic to be set to this action, or <code>null</code> to
   *          set none.
   */
  public final void setGraphic(String id, @Nullable Node graphic) {
    final IAction action = map.get(id);
    if (action == null) {
      logger.error("Unknown action id: {}", id);
    } else {
      action.setGraphic(graphic);
    }
  }

  /**
   * Gets the graphic property of the specified action.
   * <p>
   * The graphic of an action is normally an {@link ImageView} node, but there
   * is no requirement for this to be the case.
   *
   * @param id
   *          the id of an action.
   * @return the graphic property of the specified action.
   */
  public final ObjectProperty<Node> graphicProperty(String id) {
    final IAction action = map.get(id);
    if (action == null) {
      logger.error("Unknown action id: {}", id);
      return null;
    } else {
      return action.graphicProperty();
    }
  }

  /**
   * Gets the alignment of the specified action.
   * <p>
   * The alignment property specifies how the text and graphic within the
   * Labeled should be aligned when there is empty space within the Labeled.
   * <p>
   * The default value of this property is {@link Pos#CENTER_LEFT}.
   * <p>
   * <b>NOTE: </b> this property is only useful for buttons created from this
   * action.
   *
   * @param id
   *          the id of an action.
   * @return the alignment of the specified action.
   */
  public final Pos getAlignment(String id) {
    final IAction action = map.get(id);
    if (action == null) {
      logger.error("Unknown action id: {}", id);
      return null;
    } else {
      return action.getAlignment();
    }
  }

  /**
   * Gets the alignment of the specified action.
   * <p>
   * The alignment property specifies how the text and graphic within the
   * Labeled should be aligned when there is empty space within the Labeled.
   * <p>
   * The default value of this property is {@link Pos#CENTER_LEFT}.
   * <p>
   * <b>NOTE: </b> this property is only useful for buttons created from this
   * action.
   *
   * @param id
   *          the id of an action.
   * @param alignment
   *          the new alignment to be set to this action.
   */
  public final void setAlignment(String id, Pos alignment) {
    final IAction action = map.get(id);
    if (action == null) {
      logger.error("Unknown action id: {}", id);
    } else {
      action.setAlignment(alignment);
    }
  }

  /**
   * Gets the alignment property of the specified action.
   * <p>
   * The alignment property specifies how the text and graphic within the
   * Labeled should be aligned when there is empty space within the Labeled.
   * <p>
   * The default value of this property is {@link Pos#CENTER_LEFT}.
   * <p>
   * <b>NOTE: </b> this property is only useful for buttons created from this
   * action.
   *
   * @param id
   *          the id of an action.
   * @return the alignment property of the specified action.
   */
  public final ObjectProperty<Pos> alignmentProperty(String id) {
    final IAction action = map.get(id);
    if (action == null) {
      logger.error("Unknown action id: {}", id);
      return null;
    } else {
      return action.alignmentProperty();
    }
  }

  /**
   * Gets the content display of the specified action.
   * <p>
   * The content display property specifies the positioning of the graphic
   * relative to the text.
   * <p>
   * The default value of this property is {@link ContentDisplay#LEFT}.
   * <p>
   * <b>NOTE: </b> this property is only useful for buttons created from this
   * action.
   *
   * @param id
   *          the id of an action.
   * @return the content display of the specified action.
   */
  public final ContentDisplay getContentDisplayProperty(String id) {
    final IAction action = map.get(id);
    if (action == null) {
      logger.error("Unknown action id: {}", id);
      return null;
    } else {
      return action.getContentDisplayProperty();
    }
  }

  /**
   * Gets the content display property of the specified action.
   * <p>
   * The content display property specifies the positioning of the graphic
   * relative to the text.
   * <p>
   * The default value of this property is {@link ContentDisplay#LEFT}.
   * <p>
   * <b>NOTE: </b> this property is only useful for buttons created from this
   * action.
   *
   * @param id
   *          the id of an action.
   * @param contentDisplay
   *          the new content display to be set to this action.
   */
  public final void setContentDisplayProperty(String id, ContentDisplay contentDisplay) {
    final IAction action = map.get(id);
    if (action == null) {
      logger.error("Unknown action id: {}", id);
    } else {
      action.setContentDisplayProperty(contentDisplay);
    }
  }

  /**
   * Gets the content display property of the specified action.
   * <p>
   * The content display property specifies the positioning of the graphic
   * relative to the text.
   * <p>
   * The default value of this property is {@link ContentDisplay#LEFT}.
   * <p>
   * <b>NOTE: </b> this property is only useful for buttons created from this
   * action.
   *
   * @param id
   *          the id of an action.
   * @return the content display property of the specified action.
   */
  public final ObjectProperty<ContentDisplay> contentDisplayProperty(String id) {
    final IAction action = map.get(id);
    if (action == null) {
      logger.error("Unknown action id: {}", id);
      return null;
    } else {
      return action.contentDisplayProperty();
    }
  }

  /**
   * Gets the graphic and text gap of the specified action.
   * <p>
   * The gap is the amount of space between the graphic and text of the buttons
   * created from this action.
   * <p>
   * The default value of this property is 4.
   * <p>
   * <b>NOTE: </b> this property is only useful for buttons created from this
   * action.
   *
   * @param id
   *          the id of an action.
   * @return the graphic and text gap of the specified action.
   */
  public final double getGraphicTextGap(String id) {
    final IAction action = map.get(id);
    if (action == null) {
      logger.error("Unknown action id: {}", id);
      return 0;
    } else {
      return action.getGraphicTextGap();
    }
  }

  /**
   * Sets the amount of space between the graphic and text of the specified
   * action.
   * <p>
   * The gap is the amount of space between the graphic and text of the buttons
   * created from this action.
   * <p>
   * The default value of this property is 4.
   * <p>
   * <b>NOTE: </b> this property is only useful for buttons created from this
   * action.
   *
   * @param id
   *          the id of an action.
   * @param graphicTextGap
   *          the new graphic and text gap to be set to this action.
   */
  public final void setGraphicTextGap(String id, double graphicTextGap) {
    final IAction action = map.get(id);
    if (action == null) {
      logger.error("Unknown action id: {}", id);
    } else {
      action.setGraphicTextGap(graphicTextGap);
    }
  }

  /**
   * Gets the graphic and text gap property of the specified action.
   * <p>
   * The gap is the amount of space between the graphic and text of the buttons
   * created from this action.
   * <p>
   * The default value of this property is 4.
   * <p>
   * <b>NOTE: </b> this property is only useful for buttons created from this
   * action.
   *
   * @param id
   *          the id of an action.
   * @return the graphic and text gap property of the specified action.
   */
  public final DoubleProperty graphicTextGapProperty(String id) {
    final IAction action = map.get(id);
    if (action == null) {
      logger.error("Unknown action id: {}", id);
      return null;
    } else {
      return action.graphicTextGapProperty();
    }
  }

  /**
   * Tests whether this action is selected.
   * <p>
   * The default value of this property is {@code false}.
   *
   * @param id
   *          the id of an action.
   * @return whether this action is selected.
   */
  public final boolean isSelected(String id) {
    final IAction action = map.get(id);
    if (action == null) {
      logger.error("Unknown action id: {}", id);
      return false;
    } else {
      return action.isSelected();
    }
  }

  /**
   * Sets the selected property of the specified action.
   * <p>
   * The default value of this property is {@code false}.
   *
   * @param id
   *          the id of an action.
   * @param selected
   *          the new value to be set to the selected property of the specified
   *          action.
   */
  public final void setSelected(String id, boolean selected) {
    final IAction action = map.get(id);
    if (action == null) {
      logger.error("Unknown action id: {}", id);
    } else {
      action.setSelected(selected);
    }
  }

  /**
   * Gets the selected property of the specified action.
   * <p>
   * The default value of this property is {@code false}.
   *
   * @param id
   *          the id of an action.
   * @return the selected property of the specified action.
   */
  public final BooleanProperty selectedProperty(String id) {
    final IAction action = map.get(id);
    if (action == null) {
      logger.error("Unknown action id: {}", id);
      return null;
    } else {
      return action.selectedProperty();
    }
  }

  /**
   * Tests whether this action is allow indeterminate.
   * <p>
   * The default value of this property is {@code false}.
   * <p>
   * <b>NOTE: </b> this property is only useful for the {@link CheckBox} created
   * from this action.
   *
   * @param id
   *          the id of an action.
   * @return whether this action is allow indeterminate.
   */
  public final boolean isAllowIndeterminateProperty(String id) {
    final IAction action = map.get(id);
    if (action == null) {
      logger.error("Unknown action id: {}", id);
      return false;
    } else {
      return action.isAllowIndeterminateProperty();
    }
  }

  /**
   * Sets the allow indeterminate property of the specified action.
   * <p>
   * The default value of this property is {@code false}.
   * <p>
   * <b>NOTE: </b> this property is only useful for the {@link CheckBox} created
   * from this action.
   *
   * @param id
   *          the id of an action.
   * @param allowIndeterminate
   *          the new value to be set to the allow indeterminate property of
   *          this action.
   */
  public final void setAllowIndeterminateProperty(String id,
      boolean allowIndeterminate) {
        final IAction action = map.get(id);
        if (action == null) {
          logger.error("Unknown action id: {}", id);
        } else {
          action.setAllowIndeterminateProperty(allowIndeterminate);
        }
      }

  /**
   * Gets the allow indeterminate property of the specified action.
   * <p>
   * The default value of this property is {@code false}.
   * <p>
   * <b>NOTE: </b> this property is only useful for the {@link CheckBox} created
   * from this action.
   *
   * @param id
   *          the id of an action.
   * @return the allow indeterminate property of the specified action.
   */
  public final BooleanProperty allowIndeterminateProperty(String id) {
    final IAction action = map.get(id);
    if (action == null) {
      logger.error("Unknown action id: {}", id);
      return null;
    } else {
      return action.allowIndeterminateProperty();
    }
  }

  /**
   * Tests whether this action is indeterminate.
   * <p>
   * The default value of this property is {@code false}.
   * <p>
   * <b>NOTE: </b> this property is only useful for the {@link CheckBox} created
   * from this action.
   *
   * @param id
   *          the id of an action.
   * @return whether this action is indeterminate.
   */
  public final boolean isIndeterminate(String id) {
    final IAction action = map.get(id);
    if (action == null) {
      logger.error("Unknown action id: {}", id);
      return false;
    } else {
      return action.isIndeterminate();
    }
  }

  /**
   * Sets the indeterminate property of the specified action.
   * <p>
   * The default value of this property is {@code false}.
   * <p>
   * <b>NOTE: </b> this property is only useful for the {@link CheckBox} created
   * from this action.
   *
   * @param id
   *          the id of an action.
   * @param indeterminate
   *          the new value to be set to the indeterminate property of this
   *          action.
   */
  public final void setIndeterminate(String id, boolean indeterminate) {
    final IAction action = map.get(id);
    if (action == null) {
      logger.error("Unknown action id: {}", id);
    } else {
      action.setIndeterminate(indeterminate);
    }
  }

  /**
   * Gets the indeterminate property of the specified action.
   * <p>
   * The default value of this property is {@code false}.
   * <p>
   * <b>NOTE: </b> this property is only useful for the {@link CheckBox} created
   * from this action.
   *
   * @param id
   *          the id of an action.
   * @return the indeterminate property of the specified action.
   */
  public final BooleanProperty indeterminateProperty(String id) {
    final IAction action = map.get(id);
    if (action == null) {
      logger.error("Unknown action id: {}", id);
      return null;
    } else {
      return action.indeterminateProperty();
    }
  }

  /**
   * Tests whether this action is visited.
   * <p>
   * The default value of this property is {@code false}.
   * <p>
   * <b>NOTE: </b> this property is only useful for the {@link Hyperlink}
   * created from this action.
   *
   * @param id
   *          the id of an action.
   * @return whether this action is visited.
   */
  public final boolean isVisited(String id) {
    final IAction action = map.get(id);
    if (action == null) {
      logger.error("Unknown action id: {}", id);
      return false;
    } else {
      return action.isVisited();
    }
  }

  /**
   * Sets the visited property of the specified action.
   * <p>
   * The default value of this property is {@code false}.
   * <p>
   * <b>NOTE: </b> this property is only useful for the {@link Hyperlink}
   * created from this action.
   *
   * @param id
   *          the id of an action.
   * @param visited
   *          the new value to be set to the visited property of the specified
   *          action.
   */
  public final void setVisited(String id, boolean visited) {
    final IAction action = map.get(id);
    if (action == null) {
      logger.error("Unknown action id: {}", id);
    } else {
      action.setVisited(visited);
    }
  }

  /**
   * Gets the visited property of the specified action.
   * <p>
   * The default value of this property is {@code false}.
   * <p>
   * <b>NOTE: </b> this property is only useful for the {@link Hyperlink}
   * created from this action.
   *
   * @param id
   *          the id of an action.
   * @return the visited property of the specified action.
   */
  public final BooleanProperty visitedProperty(String id) {
    final IAction action = map.get(id);
    if (action == null) {
      logger.error("Unknown action id: {}", id);
      return null;
    } else {
      return action.visitedProperty();
    }
  }

  /**
   * Tests whether this action is visible.
   * <p>
   * The widgets (buttons, menu items, sub-menus, context menus, etc) created by
   * an invisible action will not be rendered as part of the scene graph.
   * <p>
   * The default value of this property is {@code true}.
   *
   * @param id
   *          the id of an action.
   * @return whether this action is visible.
   */
  public final boolean isVisible(String id) {
    final IAction action = map.get(id);
    if (action == null) {
      logger.error("Unknown action id: {}", id);
      return true;
    } else {
      return action.isVisible();
    }
  }

  /**
   * Sets the visible property of the specified action.
   * <p>
   * The widgets (buttons, menu items, sub-menus, context menus, etc) created by
   * an invisible action will not be rendered as part of the scene graph.
   * <p>
   * The default value of this property is {@code true}.
   *
   * @param id
   *          the id of an action.
   * @param visible
   *          the new value to be set to the visible property of the specified
   *          action.
   */
  public final void setVisible(String id, boolean visible) {
    final IAction action = map.get(id);
    if (action == null) {
      logger.error("Unknown action id: {}", id);
    } else {
      action.setVisible(visible);
    }
  }

  /**
   * Gets the visible property of the specified action.
   * <p>
   * The widgets (buttons, menu items, sub-menus, context menus, etc) created by
   * an invisible action will not be rendered as part of the scene graph.
   * <p>
   * The default value of this property is {@code true}.
   *
   * @param id
   *          the id of an action.
   * @return the visible property of the specified action.
   */
  public final BooleanProperty visibleProperty(String id) {
    final IAction action = map.get(id);
    if (action == null) {
      logger.error("Unknown action id: {}", id);
      return null;
    } else {
      return action.visibleProperty();
    }
  }


  /**
   * Tests whether the specified action is managed.
   * <p>
   * If a node is managed, it's parent will factor the node's geometry into its
   * own preferred size and {@link #layoutBoundsProperty layoutBounds}
   * calculations and will lay it out during the scene's layout pass. If a
   * managed node's layoutBounds changes, it will automatically trigger
   * re-layout up the scene-graph to the nearest layout root (which is typically
   * the scene's root node).
   * <p>
   * If a node is unmanaged, its parent will ignore the child in both preferred
   * size computations and layout. Changes in layoutBounds will not trigger
   * re-layout above it. If an unmanaged node is of type
   * {@link javafx.scene.Parent Parent}, it will act as a "layout root", meaning
   * that calls to {@link Parent#requestLayout()} beneath it will cause only the
   * branch rooted by the node to be relayed out, thereby isolating layout
   * changes to that root and below. It's the application's responsibility to
   * set the size and position of an unmanaged node.
   * <p>
   * The default value of this property is {@code true}.
   *
   * @param id
   *          the id of an action.
   * @return whether the specified action is managed.
   */
  public boolean isManaged(String id) {
    final IAction action = map.get(id);
    if (action == null) {
      logger.error("Unknown action id: {}", id);
      return true;
    } else {
      return action.isManaged();
    }
  }

  /**
   * Sets the managed property of the specified action.
   * <p>
   * If a node is managed, it's parent will factor the node's geometry into its
   * own preferred size and {@link #layoutBoundsProperty layoutBounds}
   * calculations and will lay it out during the scene's layout pass. If a
   * managed node's layoutBounds changes, it will automatically trigger
   * re-layout up the scene-graph to the nearest layout root (which is typically
   * the scene's root node).
   * <p>
   * If a node is unmanaged, its parent will ignore the child in both preferred
   * size computations and layout. Changes in layoutBounds will not trigger
   * re-layout above it. If an unmanaged node is of type
   * {@link javafx.scene.Parent Parent}, it will act as a "layout root", meaning
   * that calls to {@link Parent#requestLayout()} beneath it will cause only the
   * branch rooted by the node to be relayed out, thereby isolating layout
   * changes to that root and below. It's the application's responsibility to
   * set the size and position of an unmanaged node.
   * <p>
   * The default value of this property is {@code true}.
   *
   * @param id
   *          the id of an action.
   * @param managed
   *          the new value to be set to the managed property of the specified
   *          action.
   */
  public void setManaged(String id, boolean managed) {
    final IAction action = map.get(id);
    if (action == null) {
      logger.error("Unknown action id: {}", id);
    } else {
      action.setManaged(managed);
    }
  }

  /**
   * Gets the managed property of the specified action.
   * <p>
   * If a node is managed, it's parent will factor the node's geometry into its
   * own preferred size and {@link #layoutBoundsProperty layoutBounds}
   * calculations and will lay it out during the scene's layout pass. If a
   * managed node's layoutBounds changes, it will automatically trigger
   * re-layout up the scene-graph to the nearest layout root (which is typically
   * the scene's root node).
   * <p>
   * If a node is unmanaged, its parent will ignore the child in both preferred
   * size computations and layout. Changes in layoutBounds will not trigger
   * re-layout above it. If an unmanaged node is of type
   * {@link javafx.scene.Parent Parent}, it will act as a "layout root", meaning
   * that calls to {@link Parent#requestLayout()} beneath it will cause only the
   * branch rooted by the node to be relayed out, thereby isolating layout
   * changes to that root and below. It's the application's responsibility to
   * set the size and position of an unmanaged node.
   * <p>
   * The default value of this property is {@code true}.
   *
   * @param id
   *          the id of an action.
   * @return the managed property of the specified action.
   */
  public BooleanProperty managedProperty(String id) {
    final IAction action = map.get(id);
    if (action == null) {
      logger.error("Unknown action id: {}", id);
      return null;
    } else {
      return action.managedProperty();
    }
  }


  /**
   * Tests whether this action is mnemonic parsing.
   * <p>
   * The mnemonic, on Windows, is when underscored letter of a menu of a menu
   * bar. When you press "Alt+&lt;the letter&gt;" the menu will be opened. It
   * gives you a quick access to the menu.
   * <p>
   * If this is set to true, then the text of menu items created from this
   * action will be parsed to see if it contains the mnemonic parsing character
   * '_'. When a mnemonic is detected the key combination will be determined
   * based on the succeeding character, and the mnemonic added.
   * <p>
   * The default value of this property is {@code true}.
   *
   * @param id
   *          the id of an action.
   * @return whether this action is mnemonic parsing.
   */
  public final boolean isMnemonicParsing(String id) {
    final IAction action = map.get(id);
    if (action == null) {
      logger.error("Unknown action id: {}", id);
      return true;
    } else {
      return action.isMnemonicParsing();
    }
  }

  /**
   * Sets the mnemonic parsing property of the specified action.
   * <p>
   * The mnemonic, on Windows, is when underscored letter of a menu of a menu
   * bar. When you press "Alt+&lt;the letter&gt;" the menu will be opened. It
   * gives you a quick access to the menu.
   * <p>
   * If this is set to true, then the text of menu items created from this
   * action will be parsed to see if it contains the mnemonic parsing character
   * '_'. When a mnemonic is detected the key combination will be determined
   * based on the succeeding character, and the mnemonic added.
   * <p>
   * The default value of this property is {@code true}.
   *
   * @param id
   *          the id of an action.
   * @param mnemonicParsing
   *          the new value to be set to the mnemonic parsing property of this
   *          action.
   */
  public final void setMnemonicParsing(String id, boolean mnemonicParsing) {
    final IAction action = map.get(id);
    if (action == null) {
      logger.error("Unknown action id: {}", id);
    } else {
      action.setMnemonicParsing(mnemonicParsing);
    }
  }

  /**
   * Gets the mnemonic parsing property of the specified action.
   * <p>
   * The mnemonic, on Windows, is when underscored letter of a menu of a menu
   * bar. When you press "Alt+&lt;the letter&gt;" the menu will be opened. It
   * gives you a quick access to the menu.
   * <p>
   * If this is set to true, then the text of menu items created from this
   * action will be parsed to see if it contains the mnemonic parsing character
   * '_'. When a mnemonic is detected the key combination will be determined
   * based on the succeeding character, and the mnemonic added.
   * <p>
   * The default value of this property is {@code true}.
   *
   * @param id
   *          the id of an action.
   * @return the mnemonic parsing property of the specified action.
   */
  public final BooleanProperty mnemonicParsingProperty(String id) {
    final IAction action = map.get(id);
    if (action == null) {
      logger.error("Unknown action id: {}", id);
      return null;
    } else {
      return action.mnemonicParsingProperty();
    }
  }

  /**
   * Creates a button from the specified action.
   *
   * @param id
   *          the id of the specified action, which cannot be the id of the
   *          {@link SeparatorAction}.
   * @return the button created from the specified action, or null if no such
   *         action
   */
  public final ButtonBase createButton(String id) {
    if (SeparatorAction.ID.equals(id)) {
      logger.error("Cannot create button from a SeparatorAction.");
      return null;
    }
    final IAction action = map.get(id);
    if (action == null) {
      logger.error("Unknown action id: {}", id);
      return null;
    } else {
      return (ButtonBase) action.createButton();
    }
  }

  /**
   * Creates a menu item from the specified action.
   *
   * @param id
   *          the id of the specified action, which cannot be the id of the
   *          {@link SeparatorAction}.
   * @return the menu item created from the specified action, or null if no such
   *         action
   */
  public final MenuItem createMenuItem(String id) {
    final IAction action = map.get(id);
    if (action == null) {
      logger.error("Unknown action id: {}", id);
      return null;
    } else {
      return action.createMenuItem();
    }
  }

  /**
   * Creates a menu from the specified action.
   *
   * @param id
   *          the id of the specified action, which cannot be the id of the
   *          {@link SeparatorAction}.
   * @return the menu created from the specified action, or null if no such
   *         action
   */
  public final Menu createMenu(String id) {
    if (SeparatorAction.ID.equals(id)) {
      logger.error("Cannot create menu from a SeparatorAction.");
      return null;
    }
    final IAction action = map.get(id);
    if (action == null) {
      logger.error("Unknown action id: {}", id);
      return null;
    } else {
      return action.createMenu();
    }
  }

  /**
   * Creates a menu bar from specified actions.
   *
   * @param ids
   *          the ids of the specified actions.
   * @return the menu bar created from the specified actions.
   */
  public final MenuBar createMenuBar(String... ids) {
    final MenuBar menuBar = new MenuBar();
    for (final String id : ids) {
      final IAction action = map.get(id);
      if (action == null) {
        final Logger logger = LoggerFactory.getLogger(ActionManager.class);
        logger.error("Unknown action id: {}", id);
      } else {
        final Menu menu = action.createMenu();
        menuBar.getMenus().add(menu);
      }
    }
    return menuBar;
  }

  /**
   * Creates a menu bar from specified actions.
   *
   * @param ids
   *          the ids of the specified actions.
   * @return the menu bar created from the specified actions.
   */
  public final MenuBar createMenuBar(Collection<String> ids) {
    final MenuBar menuBar = new MenuBar();
    for (final String id : ids) {
      final IAction action = map.get(id);
      if (action == null) {
        final Logger logger = LoggerFactory.getLogger(ActionManager.class);
        logger.error("Unknown action id: {}", id);
      } else {
        final Menu menu = action.createMenu();
        menuBar.getMenus().add(menu);
      }
    }
    return menuBar;
  }

  /**
   * Creates a tool bar from specified actions.
   *
   * @param ids
   *          the ids of the specified actions.
   * @return the tool bar created from the specified actions.
   */
  public final ToolBar createToolBar(String... ids) {
    final ToolBar toolBar = new ToolBar();
    for (final String id : ids) {
      final IAction action = map.get(id);
      if (action == null) {
        final Logger logger = LoggerFactory.getLogger(ActionManager.class);
        logger.error("Unknown action id: {}", id);
      } else {
        final Control control = action.createButton();
        toolBar.getItems().add(control);
      }
    }
    return toolBar;
  }

  /**
   * Creates a tool bar from specified actions.
   *
   * @param ids
   *          the ids of the specified actions.
   * @return the tool bar created from the specified actions.
   */
  public final ToolBar createToolBar(Collection<String> ids) {
    final ToolBar toolBar = new ToolBar();
    for (final String id : ids) {
      final IAction action = map.get(id);
      if (action == null) {
        logger.error("Unknown action id: {}", id);
      } else {
        final Control control = action.createButton();
        toolBar.getItems().add(control);
      }
    }
    return toolBar;
  }

  /**
   * Hides an action.
   * <p>
   * Hiding an action will makes it in-visible and un-managed.
   *
   * @param id
   *    the id of an action.
   */
  public final void hide(String id) {
    final IAction action = map.get(id);
    if (action == null) {
      logger.error("Unknown action id: {}", id);
    } else {
      action.setVisible(false);
      action.setManaged(false);
    }
  }

  /**
   * Shows an action.
   * <p>
   * Showing an action will makes it visible and managed.
   *
   * @param id
   *    the id of an action.
   */
  public final void show(String id) {
    final IAction action = map.get(id);
    if (action == null) {
      logger.error("Unknown action id: {}", id);
    } else {
      action.setVisible(true);
      action.setManaged(true);
    }
  }

}
