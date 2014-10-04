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

package com.github.haixing_hu.javafx.control.menubutton;

import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;

import com.sun.javafx.scene.control.ControlAcceleratorSupport;
import com.sun.javafx.scene.control.behavior.MenuButtonBehaviorBase;
import com.sun.javafx.scene.control.skin.BehaviorSkinBase;
import com.sun.javafx.scene.control.skin.ContextMenuContent;
import com.sun.javafx.scene.control.skin.LabeledImpl;

/**
 * Base class for MenuButtonSkin and SplitMenuButtonSkin. It consists of the
 * label, the arrowButton with its arrow shape, and the popup.
 *
 * @author Haixing Hu
 */
@SuppressWarnings("restriction")
public class NoArrowMenuButtonSkinBase<C extends MenuButton, B extends MenuButtonBehaviorBase<C>>
    extends BehaviorSkinBase<C, B> {

  /***************************************************************************
   * * UI Subcomponents * *
   **************************************************************************/

  protected final LabeledImpl label;
  protected ContextMenu popup;

  /**
   * If true, the control should behave like a button for mouse button events.
   */
  protected boolean behaveLikeButton = false;
  private final ListChangeListener<MenuItem> itemsChangedListener;

  /***************************************************************************
   * * Constructors * *
   **************************************************************************/

  public NoArrowMenuButtonSkinBase(final C control, final B behavior) {
    super(control, behavior);

    if (control.getOnMousePressed() == null) {
      control.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
        getBehavior().mousePressed(e, behaveLikeButton);
      });
    }

    if (control.getOnMouseReleased() == null) {
      control.addEventHandler(MouseEvent.MOUSE_RELEASED, e -> {
        getBehavior().mouseReleased(e, behaveLikeButton);
      });
    }

    /*
     * Create the objects we will be displaying.
     */
    label = new MenuLabeledImpl(getSkinnable());
    label.setMnemonicParsing(control.isMnemonicParsing());
    label.setLabelFor(control);

    popup = new ContextMenu();
    popup.getItems().clear();
    popup.getItems().addAll(getSkinnable().getItems());

    getChildren().clear();
    getChildren().addAll(label);

    getSkinnable().requestLayout();

    itemsChangedListener = c -> {
      while (c.next()) {
        popup.getItems().removeAll(c.getRemoved());
        popup.getItems().addAll(c.getFrom(), c.getAddedSubList());
      }
    };
    control.getItems().addListener(itemsChangedListener);

    if (getSkinnable().getScene() != null) {
      ControlAcceleratorSupport.addAcceleratorsIntoScene(getSkinnable()
          .getItems(), getSkinnable());
    }
    control.sceneProperty().addListener(
        (scene, oldValue, newValue) -> {
          if ((getSkinnable() != null) && (getSkinnable().getScene() != null)) {
            ControlAcceleratorSupport.addAcceleratorsIntoScene(getSkinnable()
                .getItems(), getSkinnable());
          }
        });

    // If setOnAction() is overridden the code below causes the popup to show
    // and hide.
    // control.addEventHandler(ActionEvent.ACTION, new
    // EventHandler<ActionEvent>() {
    // @Override public void handle(ActionEvent e) {
    // if (!popup.isVisible()) {
    // show();
    // }
    // else {
    // hide();
    // }
    //
    // }
    // });

    // Register listeners
    registerChangeListener(control.showingProperty(), "SHOWING");
    registerChangeListener(control.focusedProperty(), "FOCUSED");
    registerChangeListener(control.mnemonicParsingProperty(),
        "MNEMONIC_PARSING");
    registerChangeListener(popup.showingProperty(), "POPUP_VISIBLE");
  }

  /** {@inheritDoc} */
  @Override
  public void dispose() {
    getSkinnable().getItems().removeListener(itemsChangedListener);
    super.dispose();
    if (popup != null) {
      if ((popup.getSkin() != null) && (popup.getSkin().getNode() != null)) {
        ContextMenuContent cmContent = (ContextMenuContent) popup.getSkin()
            .getNode();
        cmContent.dispose();
        cmContent = null;
      }
      popup.setSkin(null);
      popup = null;
    }
  }

  /***************************************************************************
   * * Control change handlers * *
   **************************************************************************/

  private void show() {
    if (! popup.isShowing()) {
      popup.show(getSkinnable(), getSkinnable().getPopupSide(), 0, 0);

      // if (getSkinnable().isOpenVertically()) {
      // // FIXME ugly hack - need to work out why we need '12' for
      // // MenuButton/SplitMenuButton, but not for Menus
      // double indent = getSkinnable().getStyleClass().contains("menu") ? 0 :
      // 12;
      // popup.show(getSkinnable(), Side.BOTTOM, indent, 0);
      // } else {
      // popup.show(getSkinnable(), Side.RIGHT, 0, 12);
      // }
    }
  }

  private void hide() {
    if (popup.isShowing()) {
      popup.hide();
      // popup.getAnchor().requestFocus();
    }
  }

  /**
   * Handles changes to properties of the MenuButton.
   */
  @Override
  protected void handleControlPropertyChanged(String p) {
    super.handleControlPropertyChanged(p);

    if ("SHOWING".equals(p)) {
      if (getSkinnable().isShowing()) {
        show();
      } else {
        hide();
      }
    } else if ("FOCUSED".equals(p)) {
      // Handle tabbing away from an open MenuButton
      if (! getSkinnable().isFocused() && getSkinnable().isShowing()) {
        hide();
      }
      if (! getSkinnable().isFocused() && popup.isShowing()) {
        hide();
      }
    } else if ("POPUP_VISIBLE".equals(p)) {
      if (! popup.isShowing() && getSkinnable().isShowing()) {
        // Popup was dismissed. Maybe user clicked outside or typed ESCAPE.
        // Make sure button is in sync.
        getSkinnable().hide();
      }

      // FIXME: due to the package access limitation, we ignore the following
      //  code about the mnemonics

//      if (popup.isShowing()) {
//        Utils.addMnemonics(popup, getSkinnable().getScene(),
//            getSkinnable().impl_isShowMnemonics());
//      } else {
//        Utils.removeMnemonics(popup, getSkinnable().getScene());
//      }

    } else if ("MNEMONIC_PARSING".equals(p)) {
      label.setMnemonicParsing(getSkinnable().isMnemonicParsing());
      getSkinnable().requestLayout();
    }
  }

  /***************************************************************************
   * * Layout * *
   **************************************************************************/

  @Override
  protected double computeMinWidth(double height, double topInset,
      double rightInset, double bottomInset, double leftInset) {
    return leftInset + label.minWidth(height) + rightInset;
  }

  @Override
  protected double computeMinHeight(double width, double topInset,
      double rightInset, double bottomInset, double leftInset) {
    return topInset + label.minHeight(width) + bottomInset;
  }

  @Override
  protected double computePrefWidth(double height, double topInset,
      double rightInset, double bottomInset, double leftInset) {
    return leftInset + label.prefWidth(height) + rightInset;
  }

  @Override
  protected double computePrefHeight(double width, double topInset,
      double rightInset, double bottomInset, double leftInset) {
    return topInset + label.prefHeight(width) + bottomInset;
  }

  @Override
  protected double computeMaxWidth(double height, double topInset,
      double rightInset, double bottomInset, double leftInset) {
    return getSkinnable().prefWidth(height);
  }

  @Override
  protected double computeMaxHeight(double width, double topInset,
      double rightInset, double bottomInset, double leftInset) {
    return getSkinnable().prefHeight(width);
  }

  @Override
  protected void layoutChildren(final double x, final double y, final double w,
      final double h) {
    label.resizeRelocate(x, y, w, h);
  }

  private class MenuLabeledImpl extends LabeledImpl {
    MenuButton button;

    public MenuLabeledImpl(MenuButton b) {
      super(b);
      button = b;
      addEventHandler(ActionEvent.ACTION, e -> {
        button.fireEvent(new ActionEvent());
        e.consume();
      });
    }
  }

}
