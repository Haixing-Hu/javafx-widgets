/******************************************************************************
 *
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
 ******************************************************************************/

package com.github.haixing_hu.javafx.control.treeview;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;

import com.github.haixing_hu.collection.tree.Tree;

import static com.github.haixing_hu.lang.Argument.requireNonNull;

/**
 * A sub-class of {@link TreeItem} which could construct a tree view with a
 * {@link Tree} node.
 *
 * @param <KEY>
 *    the type of keys of tree nodes.
 * @param <VALUE>
 *    the type of values stored in the tree nodes.
 * @author Haixing Hu
 */
public class GenericTreeItem<KEY, VALUE> extends TreeItem<Tree<KEY, VALUE>> {

  private boolean hasFetchedChildren = false;

  /**
   * Constructs a {@link GenericTreeItem} with a tree node.
   *
   * @param tree
   *      a tree node.
   */
  public GenericTreeItem(Tree<KEY, VALUE> tree) {
    super();
    setValue(requireNonNull("tree", tree));
  }

  /**
   * Refreshes the children of this node.
   * <p>
   * The function will call the {@link Tree#getChildren()} of the tree node
   * stored in this tree item, and rebuild the children list of this tree item.
   */
  public void refreshChildren() {
    final Tree<KEY, VALUE> tree = getValue();
    if (tree == null) {
      return;
    }
    final Collection<Tree<KEY, VALUE>> children = tree.getChildren();
    final List<TreeItem<Tree<KEY, VALUE>>> result = new ArrayList<>();
    for (final Tree<KEY, VALUE> child : children) {
      result.add(new GenericTreeItem<>(child));
    }
    super.getChildren().setAll(result);
  }

  @Override
  public boolean isLeaf() {
    if (! hasFetchedChildren) {
      refreshChildren();
      hasFetchedChildren = true;
    }
    return super.isLeaf();
  }

  @Override
  public ObservableList<TreeItem<Tree<KEY, VALUE>>> getChildren() {
    if (! hasFetchedChildren) {
      refreshChildren();
      hasFetchedChildren = true;
    }
    return super.getChildren();
  }
}
