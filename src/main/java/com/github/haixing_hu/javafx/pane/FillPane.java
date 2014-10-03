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

import java.util.WeakHashMap;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.WritableIntegerValue;
import javafx.beans.value.WritableObjectValue;
import javafx.geometry.Bounds;
import javafx.geometry.Orientation;
import javafx.scene.Node;

import com.github.haixing_hu.javafx.geometry.Size;


/**
 * A pane implements the SWT's fill layout.
 * <p>
 * This class is a modified version of the {@code FillPane} from the <a
 * href='https://github.com/tomsontom/e-fx-clipse/'>e(fx)clipse project</a>.
 *
 * @author Haixing Hu
 * @see https://github.com/tomsontom/e-fx-clipse/
 */
public class FillPane extends AbstractLayoutPane<FillData> {

  private static WeakHashMap<Node, FillData> CONSTRAINTS = new WeakHashMap<Node, FillData>();

  public static void setConstraint(Node n, FillData griddata) {
    CONSTRAINTS.put(n, griddata);
  }

  public static FillData getConstraint(Node n) {
    return CONSTRAINTS.get(n);
  }

  /**
   * The property {@code orientation} specifies how controls will be positioned
   * within the layout.
   *
   * The default value is {@link Orientation#HORIZONTAL}.
   *
   * Possible values are:
   * <ul>
   * <li>{@link Orientation#HORIZONTAL}: Position the controls horizontally from
   * left to right</li>
   * <li>{@link Orientation#VERTICAL}: Position the controls vertically from top
   * to bottom</li>
   * </ul>
   * <p>
   * The default value is {@link Orientation#HORIZONTAL}.
   */
  private final WritableObjectValue<Orientation> orientation;

  /**
   * The property {@code marginWidth} specifies the number of pixels of
   * horizontal margin that will be placed along the left and right edges of the
   * layout.
   * <p>
   * The default value is 0.
   */
  private final WritableIntegerValue marginWidth;

  /**
   * The property {@code marginHeight} specifies the number of pixels of
   * vertical margin that will be placed along the top and bottom edges of the
   * layout.
   * <p>
   * The default value is 0.
   */
  private final WritableIntegerValue marginHeight;

  /**
   * The property {@code spacing} specifies the number of pixels between the
   * edge of one cell and the edge of its neighboring cell.
   * <p>
   * The default value is 0.
   */
  private final WritableIntegerValue spacing;

  public FillPane() {
    this(Orientation.HORIZONTAL);
  }

  public FillPane(Orientation orientation) {
    super();
    this.orientation = new SimpleObjectProperty<Orientation>(this,
        "orientation", orientation);
    marginWidth = new SimpleIntegerProperty(this, "marginWidth", 0);
    marginHeight = new SimpleIntegerProperty(this, "marginHeight", 0);
    spacing = new SimpleIntegerProperty(this, "spacing", 0);
  }

  public void setOrientation(Orientation orientation) {
    this.orientation.set(orientation);
  }

  public Orientation getOrientation() {
    return orientation.get();
  }

  public WritableObjectValue<Orientation> orientationProperty() {
    return orientation;
  }

  public void setMarginWidth(int marginWidth) {
    this.marginWidth.set(marginWidth);
  }

  public int getMarginWidth() {
    return marginWidth.get();
  }

  public WritableIntegerValue marginHeightProperty() {
    return marginHeight;
  }

  public void setMarginHeight(int marginHeight) {
    this.marginHeight.set(marginHeight);
  }

  public int getMarginHeight() {
    return marginHeight.get();
  }

  public WritableIntegerValue marginWidthProperty() {
    return marginWidth;
  }

  @Override
  protected Size computeSize(double wHint, double hHint, boolean flushCache) {
    final Node[] children = getChildren().toArray(new Node[0]);
    final int count = children.length;
    double maxWidth = 0, maxHeight = 0;
    final Orientation orientation = this.orientation.get();
    final double marginWidth = this.marginWidth.get();
    final double marginHeight = this.marginHeight.get();
    final double spacing = this.spacing.get();
    for (int i = 0; i < count; ++i) {
      final Node child = children[i];
      double w = wHint, h = hHint;
      if (count > 0) {
        if ((orientation == Orientation.HORIZONTAL) && (wHint != USE_COMPUTED_SIZE)) {
          w = Math.max(0, (wHint - ((count - 1) * spacing)) / count);
        }
        if ((orientation == Orientation.VERTICAL) && (hHint != USE_COMPUTED_SIZE)) {
          h = Math.max(0, (hHint - ((count - 1) * spacing)) / count);
        }
      }
      final Size size = computeChildSize(child, w, h, flushCache);
      maxWidth = Math.max(maxWidth, size.width);
      maxHeight = Math.max(maxHeight, size.height);
    }
    double width = 0, height = 0;
    if (orientation == Orientation.HORIZONTAL) {
      width = count * maxWidth;
      if (count != 0) {
        width += (count - 1) * spacing;
      }
      height = maxHeight;
    } else {
      width = maxWidth;
      height = count * maxHeight;
      if (count != 0) {
        height += (count - 1) * spacing;
      }
    }
    width += marginWidth * 2;
    height += marginHeight * 2;
    if (wHint != USE_COMPUTED_SIZE) {
      width = wHint;
    }
    if (hHint != USE_COMPUTED_SIZE) {
      height = hHint;
    }
    return new Size(width, height);
  }

  Size computeChildSize(Node control, double wHint, double hHint,
      boolean flushCache) {
    FillData data = getConstraint(control);
    if (data == null) {
      data = new FillData();
      setConstraint(control, data);
    }
    Size size = null;
    if ((wHint == USE_COMPUTED_SIZE) && (hHint == USE_COMPUTED_SIZE)) {
      size = data.computeSize(control, wHint, hHint, flushCache);
    } else {
      // TEMPORARY CODE
      int trimX, trimY;
      trimX = trimY = 0;
      // FIXME
      // if (control instanceof Scrollable) {
      // Rectangle rect = ((Scrollable) control).computeTrim(0, 0, 0, 0);
      // trimX = rect.width;
      // trimY = rect.height;
      // } else {
      // trimX = trimY = control.getBorderWidth() * 2;
      // }
      final double w = (wHint == USE_COMPUTED_SIZE ? wHint : Math.max(0, wHint - trimX));
      final double h = (hHint == USE_COMPUTED_SIZE ? hHint : Math.max(0, hHint - trimY));
      size = data.computeSize(control, w, h, flushCache);
    }
    return size;
  }

  @Override
  protected void layoutChildren() {
    super.layoutChildren();
    final Bounds rect = getLayoutBounds();
    final Node[] children = getChildren().toArray(new Node[0]);
    final int count = children.length;
    if (count == 0) {
      return;
    }
    final Orientation orientation = this.orientation.get();
    final double marginWidth = this.marginWidth.get();
    final double marginHeight = this.marginHeight.get();
    final double spacing = this.spacing.get();

    double width = rect.getWidth() - (marginWidth * 2);
    double height = rect.getHeight() - (marginHeight * 2);
    if (orientation == Orientation.HORIZONTAL) {
      width -= (count - 1) * spacing;
      double x = rect.getMinX() + marginWidth;
      final double extra = width % count;
      final double y = rect.getMinY() + marginHeight, cellWidth = width / count;
      for (int i = 0; i < count; i++) {
        final Node child = children[i];
        double childWidth = cellWidth;
        if (i == 0) {
          childWidth += extra / 2;
        } else {
          if (i == (count - 1)) {
            childWidth += (extra + 1) / 2;
          }
        }
        child.resizeRelocate(x, y, childWidth, height);
        x += childWidth + spacing;
      }
    } else {
      height -= (count - 1) * spacing;
      final double x = rect.getMinX() + marginWidth, cellHeight = height
          / count;
      double y = rect.getMinY() + marginHeight;
      final double extra = height % count;
      for (int i = 0; i < count; i++) {
        final Node child = children[i];
        double childHeight = cellHeight;
        if (i == 0) {
          childHeight += extra / 2;
        } else {
          if (i == (count - 1)) {
            childHeight += (extra + 1) / 2;
          }
        }
        child.resizeRelocate(x, y, width, childHeight);
        y += childHeight + spacing;
      }
    }
  }
}
