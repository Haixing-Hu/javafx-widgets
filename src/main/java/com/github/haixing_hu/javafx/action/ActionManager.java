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

/**
 * An {@link ActionManager} is a map from the id to the action.
 *
 * @author Haixing Hu
 */
public class ActionManager {

  private final Map<String, IAction> map;

  public ActionManager() {
    map = new HashMap<String, IAction>();
  }

  public boolean isEmpty() {
    return map.isEmpty();
  }

  public int size() {
    return map.size();
  }

  public boolean contains(String id) {
    return map.containsKey(id);
  }

  public IAction get(String id) {
    return map.get(id);
  }

  public Collection<IAction> getAll() {
    return map.values();
  }

  public void add(IAction action) {
    map.put(action.getId(), action);
  }

  public IAction remove(String id) {
    return map.remove(id);
  }

  public void clear() {
    map.clear();
  }

}
