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

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Control;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.input.KeyCombination;

import javax.annotation.Nullable;

/**
 * The interface of actions.
 * <p>
 * This interface is inspired the IAction interface of JFace.
 * <p>
 * An action can be used to separate functionality and state from a control. For
 * example, if you have two or more controls that perform the same function
 * (e.g. one in a {@link Menu} and another on a {@link ToolBar}), consider using
 * an {@link IAction} object to implement the function. An {@link IAction}
 * object provides centralized handling of the state of action-event-firing
 * components such as buttons, menu items, etc. The state that an action can
 * handle includes text, graphic, tool tip text, disabled, and visible, etc.
 * <p>
 * An important feature of actions is, changing the properties (id, css style,
 * text, description, graphic, enable, visible, etc) of an action will
 * automatically changes the same properties of the widgets (buttons, menu
 * items, etc) created from that action, and will automatically refreshes the
 * GUI.
 * <p>
 * <b>NOTE: </b> In order to hide a button, it's not sufficient to set the
 * {@code visible} property of the action creating the button to {@code false};
 * the {@code managed} property of the action should also be set to
 * {@code false}.
 *
 * @author Haixing Hu
 */
public interface IAction extends EventHandler<ActionEvent> {

  /**
   * Gets the id of this action.
   * <p>
   * <b>NOTE:</b> the id of an action is <b>not</b> the id of the button and the
   * menu item created from that action. To get/set the id of button create from
   * an action, use the {@link #getButtonId()}, {@link #setButtonId(String)},
   * {@link #getMenuItemId()}, and {@link #setMenuItemId(String)}.
   *
   * @return the id of this action, or <code>null</code> if it has none.
   */
  public String getId();

  /**
   * Sets the id of this action.
   * <p>
   * <b>NOTE:</b> the id of an action is <b>not</b> the id of the button and the
   * menu item created from that action. To get/set the id of button create from
   * an action, use the {@link #getButtonId()}, {@link #setButtonId(String)},
   * {@link #getMenuItemId()}, and {@link #setMenuItemId(String)}.
   *
   * @param id
   *          the new id to be set to this action, or <code>null</code> to set
   *          none.
   */
  public void setId(@Nullable String id);

  /**
   * Gets the id of the button created from this action.
   *
   * @return the id of the button created from this action, or <code>null</code>
   *         if it has none.
   */
  public String getButtonId();

  /**
   * Sets the id of the button created from this action.
   *
   * @param buttonId
   *          the new id to be set to the button created from this action, or
   *          <code>null</code> to set none.
   */
  public void setButtonId(@Nullable String buttonId);

  /**
   * Gets the id of the menu item created from this action.
   *
   * @return the id of the menu item created from this action, or
   *         <code>null</code> if it has none.
   */
  public String getMenuItemId();

  /**
   * Sets the id of the menu item created from this action.
   *
   * @param menuItemId
   *          the new id to be set to the menu item created from this action, or
   *          <code>null</code> to set none.
   */
  public void setMenuItemId(@Nullable String menuItemId);

  /**
   * Gets the text (i.e., title) of this action.
   *
   * @return the text (i.e., title) of this action, or <code>null</code> if it
   *         has none.
   */
  public String getText();

  /**
   * Sets the text (i.e., title) of this action.
   *
   * @param text
   *          the new text (i.e., title) to be set to this action, or
   *          <code>null</code> to set none.
   */
  public void setText(@Nullable String text);

  /**
   * Gets the text (i.e., title) property of this action.
   *
   * @return the text (i.e., title) property of this action.
   */
  public StringProperty textProperty();

  /**
   * Gets the description (i.e., tool tip) of this action.
   * <p>
   * <b>NOTE: </b> this property is only useful for buttons created from this
   * action.
   *
   * @return the description (i.e., tool tip) of this action, or
   *         <code>null</code> if it has none.
   */
  public String getDescription();

  /**
   * Sets the description (i.e., tool tip) of this action.
   * <p>
   * <b>NOTE: </b> this property is only useful for buttons created from this
   * action.
   *
   * @param description
   *          the new description (i.e., tool tip) to be set to this action, or
   *          <code>null</code> to set none.
   */
  public void setDescription(@Nullable String description);

  /**
   * Gets the description (i.e., tool tip) property of this action
   * <p>
   * <b>NOTE: </b> this property is only useful for buttons created from this
   * action.
   *
   * @return the description (i.e., tool tip) property of this action.
   */
  public StringProperty descriptionProperty();

  /**
   * Gets the style of this action.
   * <p>
   * The style of an action is a string representation of the CSS style
   * associated with this specific MenuItem. This is analogous to the "style"
   * attribute of an HTML element. Note that, like the HTML style attribute,
   * this variable contains style properties and values and not the selector
   * portion of a style rule.
   *
   * @return the style of this action, or <code>null</code> if it has none.
   */
  public String getStyle();

  /**
   * Sets the style of this action.
   * <p>
   * The style of an action is a string representation of the CSS style
   * associated with this specific MenuItem. This is analogous to the "style"
   * attribute of an HTML element. Note that, like the HTML style attribute,
   * this variable contains style properties and values and not the selector
   * portion of a style rule.
   *
   * @param style
   *          the new style to be set to this action, or <code>null</code> to
   *          set none.
   */
  public void setStyle(@Nullable String style);

  /**
   * Gets the style property of this action.
   * <p>
   * The style of an action is a string representation of the CSS style
   * associated with this specific MenuItem. This is analogous to the "style"
   * attribute of an HTML element. Note that, like the HTML style attribute,
   * this variable contains style properties and values and not the selector
   * portion of a style rule.
   *
   * @return the style property of this action.
   */
  public StringProperty styleProperty();

  /**
   * Gets the accelerator of this action.
   * <p>
   * The accelerator property enables accessing the associated action in one
   * keystroke. It is a convenience offered to perform quickly a given action.
   * <p>
   * <b>NOTE: </b> this property is only useful for menu items created from this
   * action.
   *
   * @return the accelerator of this action, or <code>null</code> if it has
   *         none.
   */
  public KeyCombination getAccelerator();

  /**
   * Sets the accelerator of this action.
   * <p>
   * The accelerator property enables accessing the associated action in one
   * keystroke. It is a convenience offered to perform quickly a given action.
   * <p>
   * <b>NOTE: </b> this property is only useful for menu items created from this
   * action.
   *
   * @param accelerator
   *          the new accelerator to be set to this action, or <code>null</code>
   *          to set none.
   */
  public void setAccelerator(@Nullable KeyCombination accelerator);

  /**
   * Gets the accelerator property of this action.
   * <p>
   * The accelerator property enables accessing the associated action in one
   * keystroke. It is a convenience offered to perform quickly a given action.
   * <p>
   * <b>NOTE: </b> this property is only useful for menu items created from this
   * action.
   *
   * @return the accelerator property of this action.
   */
  public ObjectProperty<KeyCombination> acceleratorProperty();

  /**
   * Gets the optional graphic of this action.
   * <p>
   * The graphic of an action is normally an {@link ImageView} node, but there
   * is no requirement for this to be the case.
   *
   * @return the graphic of this action, or <code>null</code> if it has none.
   */
  public Node getGraphic();

  /**
   * Sets the optional graphic of this action.
   * <p>
   * The graphic of an action is normally an {@link ImageView} node, but there
   * is no requirement for this to be the case.
   *
   * @param graphic
   *          the new graphic to be set to this action, or <code>null</code> to
   *          set none.
   */
  public void setGraphic(@Nullable Node graphic);

  /**
   * Gets the graphic property of this action.
   * <p>
   * The graphic of an action is normally an {@link ImageView} node, but there
   * is no requirement for this to be the case.
   *
   * @return the graphic property of this action.
   */
  public ObjectProperty<Node> graphicProperty();

  /**
   * Gets the alignment of this action.
   * <p>
   * The alignment property specifies how the text and graphic within the
   * Labeled should be aligned when there is empty space within the Labeled.
   * <p>
   * The default value of this property is {@link Pos#CENTER_LEFT}.
   * <p>
   * <b>NOTE: </b> this property is only useful for buttons created from this
   * action.
   *
   * @return the alignment of this action.
   */
  public Pos getAlignment();

  /**
   * Gets the alignment of this action.
   * <p>
   * The alignment property specifies how the text and graphic within the
   * Labeled should be aligned when there is empty space within the Labeled.
   * <p>
   * The default value of this property is {@link Pos#CENTER_LEFT}.
   * <p>
   * <b>NOTE: </b> this property is only useful for buttons created from this
   * action.
   *
   * @param alignment
   *          the new alignment to be set to this action.
   */
  public void setAlignment(Pos alignment);

  /**
   * Gets the alignment property of this action.
   * <p>
   * The alignment property specifies how the text and graphic within the
   * Labeled should be aligned when there is empty space within the Labeled.
   * <p>
   * The default value of this property is {@link Pos#CENTER_LEFT}.
   * <p>
   * <b>NOTE: </b> this property is only useful for buttons created from this
   * action.
   *
   * @return the alignment property of this action.
   */
  public ObjectProperty<Pos> alignmentProperty();

  /**
   * Gets the content display of this action.
   * <p>
   * The content display property specifies the positioning of the graphic
   * relative to the text.
   * <p>
   * The default value of this property is {@link ContentDisplay#LEFT}.
   * <p>
   * <b>NOTE: </b> this property is only useful for buttons created from this
   * action.
   *
   * @return the content display of this action.
   */
  public ContentDisplay getContentDisplayProperty();

  /**
   * Gets the content display property of this action.
   * <p>
   * The content display property specifies the positioning of the graphic
   * relative to the text.
   * <p>
   * The default value of this property is {@link ContentDisplay#LEFT}.
   * <p>
   * <b>NOTE: </b> this property is only useful for buttons created from this
   * action.
   *
   * @param contentDisplay
   *          the new content display to be set to this action.
   */
  public void setContentDisplayProperty(ContentDisplay contentDisplay);

  /**
   * Gets the content display property of this action.
   * <p>
   * The content display property specifies the positioning of the graphic
   * relative to the text.
   * <p>
   * The default value of this property is {@link ContentDisplay#LEFT}.
   * <p>
   * <b>NOTE: </b> this property is only useful for buttons created from this
   * action.
   *
   * @return the content display property of this action.
   */
  public ObjectProperty<ContentDisplay> contentDisplayProperty();

  /**
   * Gets the graphic and text gap of this action.
   * <p>
   * The gap is the amount of space between the graphic and text of the buttons
   * created from this action.
   * <p>
   * The default value of this property is 4.
   * <p>
   * <b>NOTE: </b> this property is only useful for buttons created from this
   * action.
   *
   * @return the graphic and text gap of this action.
   */
  public double getGraphicTextGap();

  /**
   * Sets the amount of space between the graphic and text of this action.
   * <p>
   * The gap is the amount of space between the graphic and text of the buttons
   * created from this action.
   * <p>
   * The default value of this property is 4.
   * <p>
   * <b>NOTE: </b> this property is only useful for buttons created from this
   * action.
   *
   * @param graphicTextGap
   *          the new graphic and text gap to be set to this action.
   */
  public void setGraphicTextGap(double graphicTextGap);

  /**
   * Gets the graphic and text gap property of this action.
   * <p>
   * The gap is the amount of space between the graphic and text of the buttons
   * created from this action.
   * <p>
   * The default value of this property is 4.
   * <p>
   * <b>NOTE: </b> this property is only useful for buttons created from this
   * action.
   *
   * @return the graphic and text gap property of this action.
   */
  public DoubleProperty graphicTextGapProperty();

  /**
   * Tests whether this action is selected.
   * <p>
   * The default value of this property is {@code false}.
   *
   * @return whether this action is selected.
   */
  public boolean isSelected();

  /**
   * Sets the selected property of this action.
   * <p>
   * The default value of this property is {@code false}.
   *
   * @param selected
   *          the new value to be set to the selected property of this action.
   */
  public void setSelected(boolean selected);

  /**
   * Gets the selected property of this action.
   * <p>
   * The default value of this property is {@code false}.
   *
   * @return the selected property of this action.
   */
  public BooleanProperty selectedProperty();

  /**
   * Tests whether this action is allow indeterminate.
   * <p>
   * The default value of this property is {@code false}.
   * <p>
   * <b>NOTE: </b> this property is only useful for the {@link CheckBox} created
   * from this action.
   *
   * @return whether this action is allow indeterminate.
   */
  public boolean isAllowIndeterminateProperty();

  /**
   * Sets the allow indeterminate property of this action.
   * <p>
   * The default value of this property is {@code false}.
   * <p>
   * <b>NOTE: </b> this property is only useful for the {@link CheckBox} created
   * from this action.
   *
   * @param allowIndeterminate
   *          the new value to be set to the allow indeterminate property of
   *          this action.
   */
  public void setAllowIndeterminateProperty(boolean allowIndeterminate);

  /**
   * Gets the allow indeterminate property of this action.
   * <p>
   * The default value of this property is {@code false}.
   * <p>
   * <b>NOTE: </b> this property is only useful for the {@link CheckBox} created
   * from this action.
   *
   * @return the allow indeterminate property of this action.
   */
  public BooleanProperty allowIndeterminateProperty();

  /**
   * Tests whether this action is indeterminate.
   * <p>
   * The default value of this property is {@code false}.
   * <p>
   * <b>NOTE: </b> this property is only useful for the {@link CheckBox} created
   * from this action.
   *
   * @return whether this action is indeterminate.
   */
  public boolean isIndeterminate();

  /**
   * Sets the indeterminate property of this action.
   * <p>
   * The default value of this property is {@code false}.
   * <p>
   * <b>NOTE: </b> this property is only useful for the {@link CheckBox} created
   * from this action.
   *
   * @param indeterminate
   *          the new value to be set to the indeterminate property of this
   *          action.
   */
  public void setIndeterminate(boolean indeterminate);

  /**
   * Gets the indeterminate property of this action.
   * <p>
   * The default value of this property is {@code false}.
   * <p>
   * <b>NOTE: </b> this property is only useful for the {@link CheckBox} created
   * from this action.
   *
   * @return the indeterminate property of this action.
   */
  public BooleanProperty indeterminateProperty();

  /**
   * Tests whether this action is visited.
   * <p>
   * The default value of this property is {@code false}.
   * <p>
   * <b>NOTE: </b> this property is only useful for the {@link Hyperlink}
   * created from this action.
   *
   * @return whether this action is visited.
   */
  public boolean isVisited();

  /**
   * Sets the visited property of this action.
   * <p>
   * The default value of this property is {@code false}.
   * <p>
   * <b>NOTE: </b> this property is only useful for the {@link Hyperlink}
   * created from this action.
   *
   * @param visited
   *          the new value to be set to the visited property of this action.
   */
  public void setVisited(boolean visited);

  /**
   * Gets the visited property of this action.
   * <p>
   * The default value of this property is {@code false}.
   * <p>
   * <b>NOTE: </b> this property is only useful for the {@link Hyperlink}
   * created from this action.
   *
   * @return the visited property of this action.
   */
  public BooleanProperty visitedProperty();

  // /**
  // * Tests whether this action is disabled.
  // * <p>
  // * The default value of this property is {@code false}.
  // *
  // * @return whether this action is disabled.
  // */
  // public boolean isDisable();
  //
  // /**
  // * Sets the disable property of this action.
  // * <p>
  // * The default value of this property is {@code false}.
  // *
  // * @param disable
  // * the new value to be set to the disable property of this action.
  // */
  // public void setDisable(boolean disable);
  //
  // /**
  // * Gets the disable property of this action.
  // * <p>
  // * The default value of this property is {@code false}.
  // *
  // * @return the disable property of this action.
  // */
  // public BooleanProperty disableProperty();

  /**
   * Tests whether this action is visible.
   * <p>
   * The widgets (buttons, menu items, sub-menus, context menus, etc) created by
   * an invisible action will not be rendered as part of the scene graph.
   * <p>
   * The default value of this property is {@code true}.
   *
   * @return whether this action is visible.
   */
  public boolean isVisible();

  /**
   * Sets the visible property of this action.
   * <p>
   * The widgets (buttons, menu items, sub-menus, context menus, etc) created by
   * an invisible action will not be rendered as part of the scene graph.
   * <p>
   * The default value of this property is {@code true}.
   *
   * @param visible
   *          the new value to be set to the visible property of this action.
   */
  public void setVisible(boolean visible);

  /**
   * Gets the visible property of this action.
   * <p>
   * The widgets (buttons, menu items, sub-menus, context menus, etc) created by
   * an invisible action will not be rendered as part of the scene graph.
   * <p>
   * The default value of this property is {@code true}.
   *
   * @return the visible property of this action.
   */
  public BooleanProperty visibleProperty();

  /**
   * Tests whether this action is managed.
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
   * @return whether this action is managed.
   */
  public boolean isManaged();

  /**
   * Sets the managed property of this action.
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
   * @param managed
   *          the new value to be set to the managed property of this action.
   */
  public void setManaged(boolean managed);

  /**
   * Gets the managed property of this action.
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
   * @return the managed property of this action.
   */
  public BooleanProperty managedProperty();

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
   * @return whether this action is mnemonic parsing.
   */
  public boolean isMnemonicParsing();

  /**
   * Sets the mnemonic parsing property of this action.
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
   * @param mnemonicParsing
   *          the new value to be set to the mnemonic parsing property of this
   *          action.
   */
  public void setMnemonicParsing(boolean mnemonicParsing);

  /**
   * Gets the mnemonic parsing property of this action.
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
   * @return the mnemonic parsing property of this action.
   */
  public BooleanProperty mnemonicParsingProperty();

  /**
   * Gets the style class of this action.
   *
   * @return the list of names of the style class of this action, which cannot
   *         be {@code null}.
   */
  public ObservableList<String> getStyleClass();

  /**
   * Creates a button from this action.
   * <p>
   * <b>NOTE:</b> the returned type is {@link Control} instead of
   * {@link ButtonBase}, since we want the implementation of this function of
   * the {@link SeparatorAction} class returns a {@link Separator}.
   *
   * @return the button created from this action. The actual type of the
   *         returned button depended on the detailed implementation of this
   *         interface.
   */
  public Control createButton();

  /**
   * Creates a menu item from this action.
   *
   * @return the menu item created from this action. The actual type of the
   *         returned menu item depended on the detailed implementation of this
   *         interface.
   */
  public MenuItem createMenuItem();

  /**
   * Creates a menu from this action.
   *
   * @return the menu created from this action.
   */
  public Menu createMenu();

  /**
   * Hides this action.
   * <p>
   * Hiding an action will makes it in-visible and un-managed.
   */
  public void hide();

  /**
   * Shows this action.
   * <p>
   * Showing an action will makes it visible and managed.
   */
  public void show();

  /**
   * Tests whether this action will bind its style class property with the style
   * class property of the button or menu item created from it.
   * <p>
   * The default value of this property is {@code false}.
   * <p>
   * Sometime it's convenient to bind the style class of an action to that of
   * the button or menu item created from the action, such that changing the
   * style class of an action could also change the style class of the button or
   * menu item created from the action, hence avoid the button or menu item node
   * looking up. But it must be <b>very carefully</b> to set this property to
   * {@code true}, since if this action will create a button and a menu item,
   * binding the style class of the button and menu item to this action will
   * confuse the style classes of the button and the menu item.
   *
   * @return {@code true} if this action will bind its style class property with
   *         the style class property of the button or menu item created from
   *         it; {@code false} otherwise.
   */
  public boolean isBindStyleClass();

  /**
   * Sets whether this action will bind its style class property with the style
   * class property of the button or menu item created from it.
   * <p>
   * The default value of this property is {@code false}.
   * <p>
   * Sometime it's convenient to bind the style class of an action to that of
   * the button or menu item created from the action, such that changing the
   * style class of an action could also change the style class of the button or
   * menu item created from the action, hence avoid the button or menu item node
   * looking up. But it must be <b>very carefully</b> to set this property to
   * {@code true}, since if this action will create a button and a menu item,
   * binding the style class of the button and menu item to this action will
   * confuse the style classes of the button and the menu item.
   *
   * @param bindStyleClass
   *          {@code true} to make this action will bind its style class
   *          property with the style class property of the button or menu item
   *          created from it; {@code false} otherwise.
   */
  public void setBindStyleClass(boolean bindStyleClass);
}
