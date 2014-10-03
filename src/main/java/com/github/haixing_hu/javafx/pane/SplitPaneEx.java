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

import java.util.Arrays;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.Pane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * An extension of {@link SplitPane} which supports hiding and showing a child
 * pane.
 * <p>
 * <b>NOTE:</b> Current implementation of this class has the following
 * limitations:
 * <ul>
 * <li>It <b>does not</b> support the dynamically changing of children panes.
 * That is, after the first call to {@link #hideItem(int)},
 * {@link #showItem(int)} or {@link #setItemVisible(int, boolean)}, the items of
 * this split pane should not be added or removed anymore.</li>
 * <li>Showing a hidden node will try to restore its original occupied
 * proportion. Until now I don't know any better algorithm to do that.
 * Therefore, if the split pane has only two children, the effect of this
 * implementation is O.K., but if the split pane has more than two children, the
 * effect may be not satisfiable.</li>
 * </ul>
 *
 * @author Haixing Hu
 */
public class SplitPaneEx extends SplitPane {

  private final Logger logger;
  private Node[] originalItems;
  private boolean[] visibles;
  private double[] proportions;

  public SplitPaneEx() {
    super();
    logger = LoggerFactory.getLogger(this.getClass());
    visibles = null;
    originalItems = null;
    proportions = null;
  }

  private final void init() {
    originalItems = getItems().toArray(new Node[0]);
    visibles = new boolean[originalItems.length];
    proportions = new double[originalItems.length];
    Arrays.fill(visibles, true);
    Arrays.fill(proportions, 0.0);
  }

  /**
   * Hides a child node of this pane.
   * <p>
   * If the child node is already hidden, calling this function has no effect.
   *
   * @param index
   *          the index of the child node to hide.
   * @throws IllegalArgumentException
   *           if the index is invalid.
   */
  public final void hideItem(int index) {
    logger.trace("Hide item {}", index);
    if (visibles == null) {
      init();
    }
    if ((index < 0) || (index >= visibles.length)) {
      throw new IllegalArgumentException("Invalid index of item.");
    }
    if (! visibles[index]) { // the node has already been hidden
      return;
    }
    final ObservableList<Node> items = getItems();
    if (items.size() <= 1) {
      logger
          .warn("All other children panes were hidden, so ignore this action.");
      return;
    }
    final Node node = originalItems[index];
    // find the node in the item list
    final int pos = findNode(items, node);
    if (pos >= items.size()) {
      logger.error("Try to hide a non-exist node.");
      return;
    }
    // member the proportion that the node occupies
    final double[] positions = getDividerPositions();
    logger.debug("pos = {}", pos);
    logger.debug("positions = {}", positions);
    proportions[index] = getProportion(positions, pos);
    logger.debug("proportions[index] = {}", proportions[index]);
    // remove the node from the item list
    items.remove(pos);
    // set the new divider positions
    final double[] newPositions = getPositiosAfterRemoving(positions, pos);
    logger.debug("newPositions = {}", newPositions);
    setDividerPositions(newPositions);
    logger.debug("After setting, positions = {}", getDividerPositions());
    visibles[index] = false;
  }

  private int findNode(ObservableList<Node> items, Node node) {
    final int n = items.size();
    for (int i = 0; i < n; ++i) {
      if (items.get(i) == node) {
        return i;
      }
    }
    return n;
  }

  /**
   * Shows a child node of this pane.
   * <p>
   * If the child node is already shown, calling this function has no effect.
   *
   * @param index
   *          the index of the child node to show.
   * @throws IllegalArgumentException
   *           if the index is invalid.
   */
  public final void showItem(int index) {
    logger.trace("Show item {}", index);
    if (visibles == null) {
      init();
    }
    if ((index < 0) || (index >= visibles.length)) {
      throw new IllegalArgumentException("Invalid index of item.");
    }
    if (visibles[index]) { // the node has already been shown
      return;
    }
    final Node node = originalItems[index];
    if (! (node instanceof Pane)) {
      throw new IllegalArgumentException("Only pane node can be hidden.");
    }
    // find the insertion position of the node
    final ObservableList<Node> items = getItems();
    final int pos = findInsertPosition(items, node);
    logger.debug("pos = {}", pos);
    final double[] positions = getDividerPositions();
    logger.debug("positions = {}", positions);
    // insert the node to the item list
    items.add(pos, node);
    // restore the original proportion occupied by the node
    final double[] newPositions = getPositionsAfterInsertion(positions, pos,
        proportions[index]);
    logger.debug("proportions[index] = {}", proportions[index]);
    logger.debug("newPositions = {}", newPositions);
    setDividerPositions(newPositions);
    logger.debug("After setting, positions = {}", getDividerPositions());

    visibles[index] = true;
  }

  private final int findInsertPosition(ObservableList<Node> items, Node node) {
    final int n = items.size();
    int i = 0;
    for (; i < n; ++i) {
      if (isBefore(node, items.get(i))) {
        return i;
      }
    }
    return n;
  }

  private double getProportion(double[] positions, int pos) {
    final int n = positions.length;
    if (n == 0) {
      return 1.0;
    }
    if (pos == 0) { // this is the first child
      return positions[0];
    } else if (pos == n) { // this is the last child
      return 1.0 - positions[n - 1];
    } else { // this is the middle child
      return positions[pos] - positions[pos - 1];
    }
  }

  private double[] getPositiosAfterRemoving(double[] positions, int pos) {
    final int n = positions.length;
    if (n == 1) {
      return new double[0];
    }
    final double[] result = new double[n - 1];
    if (pos == 0) { // remove the first child
      // all spaces are allocated to its right sibling
      System.arraycopy(positions, 1, result, 0, n - 1);
    } else if (pos == n) { // remove the last child
      // all spaces are allocated to its left sibling
      System.arraycopy(positions, 0, result, 0, n - 1);
    } else { // remove the middle child
      // the spaces are allocated to its left and right sibling equally
      System.arraycopy(positions, 0, result, 0, pos - 1);
      System.arraycopy(positions, pos + 1, result, pos, n - pos - 1);
      result[pos - 1] = (positions[pos - 1] + positions[pos]) / 2;
    }
    return result;
  }

  private double[] getPositionsAfterInsertion(double[] positions, int pos,
      double proportion) {
    final int n = positions.length;
    final double[] result = new double[n + 1];
    if (pos == 0) { // insert as the first child
      // all spaces are collected from its right sibling
      result[0] = proportion;
      System.arraycopy(positions, 0, result, 1, n);
    } else if (pos == (n + 1)) { // insert as the last child
      // all spaces are collected from its left sibling
      System.arraycopy(positions, 0, result, 0, n);
      result[n] = 1.0 - proportion;
    } else { // insert as the middle child
      // the spaces are collected from its left and right sibling equally
      System.arraycopy(positions, 0, result, 0, pos - 1);
      System.arraycopy(positions, pos, result, pos + 1, n - pos);
      result[pos - 1] = positions[pos - 1] - (proportion / 2);
      result[pos] = positions[pos - 1] + (proportion / 2);
    }
    return result;
  }

  private final boolean isBefore(Node node1, Node node2) {
    for (int i = 0; i < originalItems.length; ++i) {
      if (originalItems[i] == node1) {
        return true;
      } else if (originalItems[i] == node2) {
        return false;
      }
    }
    return false;
  }

  /**
   * Sets the visibility of a child pane.
   *
   * @param index
   *          the index of a child pane.
   * @param visible
   *          indicates whether the child pane is to be visible.
   * @throws IllegalArgumentException
   *           if the index is invalid.
   */
  public final void setItemVisible(int index, boolean visible) {
    logger.trace("Set item {} visible to {}", index, visible);
    if (visible) {
      showItem(index);
    } else {
      hideItem(index);
    }
  }

}
