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

package com.github.haixing_hu.javafx.geometry;

/**
 * A trivial class representing the size of a node.
 *
 * @author Haixing Hu
 */
public class Size {

  public double width;

  public double height;

  public Size() {
    width = 0;
    height = 0;
  }

  public Size(double width, double height) {
    this.width = width;
    this.height = height;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    long temp;
    temp = Double.doubleToLongBits(height);
    result = (prime * result) + (int) (temp ^ (temp >>> 32));
    temp = Double.doubleToLongBits(width);
    result = (prime * result) + (int) (temp ^ (temp >>> 32));
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final Size other = (Size) obj;
    if (Double.doubleToLongBits(height) != Double
        .doubleToLongBits(other.height)) {
      return false;
    }
    if (Double.doubleToLongBits(width) != Double.doubleToLongBits(other.width)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "Size [width=" + width + ", height=" + height + "]";
  }

}
