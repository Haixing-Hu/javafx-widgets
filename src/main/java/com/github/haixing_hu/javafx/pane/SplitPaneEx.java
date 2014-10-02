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
 * <li>Showing a hidden node will try to restore its original occupied proportion.
 * Until now I don't know any better algorithm to do that. Therefore, if the
 * node is the first or last node, the effect of this implementation is O.K., but
 * if the node is a middle node, the effect may be not satisfiable.</li>
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
    if (allOthersHiddenExcept(index)) {
      logger.warn("All other children panes were hidden, so ignore this action.");
      return;
    }
    final Node node = originalItems[index];
    //  find the node in the item list
    final ObservableList<Node> items = getItems();
    final int pos = findNode(items, node);
    if (pos >= items.size()) {
      logger.error("Try to hide a non-exist node.");
    } else {
      //  member the proportion that the node occupies
      final double[] positions = getDividerPositions();
      if (pos == 0) {
        proportions[index] = positions[0];
      } else if (pos == (items.size() - 1)) {
        proportions[index] = 1.0 - positions[pos - 1];
      } else {
        proportions[index] = positions[pos] - positions[pos - 1];
      }
      logger.debug("positions = {}", positions);
      logger.debug("proportions[{}] = {}", index, proportions[index]);
      //  remove the node from the item list
      items.remove(pos);
      visibles[index] = false;
    }
  }

  private final boolean allOthersHiddenExcept(int index) {
    for (int i = 0; i < visibles.length; ++i) {
      if ((i != index) && visibles[i]) {
        return false;
      }
    }
    return true;
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
    //  find the insertion position of the node
    final ObservableList<Node> items = getItems();
    final int pos = findInsertPosition(items, node);
    //  insert the node to the item list
    items.add(pos, node);
    //  restore the original proportion occupied by the node
    final double[] positions = getDividerPositions();
    logger.debug("positions = {}", positions);
    logger.debug("proportions[{}] = {}", index, proportions[index]);
    if (pos == 0) {
      setDividerPosition(0, proportions[index]);
    } else if (pos == (items.size() - 1)) {
      setDividerPosition(pos -1, 1.0 - proportions[index]);
    } else {
      positions[pos - 1] -= proportions[index] / 2.0;
      positions[pos] += proportions[index] / 2.0;
      setDividerPositions(positions);
    }
    logger.debug("positions = {}", positions);
    logger.debug("proportions[{}] = {}", index, proportions[index]);
    visibles[index] = true;
  }

  private final int findInsertPosition(ObservableList<Node> items, Node node) {
    final int n = items.size();
    int i = 0;
    for ( ; i < n; ++i) {
      if (isBefore(node, items.get(i))) {
        return i;
      }
    }
    return n;
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
