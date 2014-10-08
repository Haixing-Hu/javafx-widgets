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
 *     ControlsFX -  Initial implementation and API.
 *     Haixing Hu (https://github.com/Haixing-Hu/) - Refactor.
 *
 ******************************************************************************/

/**
 * Copyright (c) 2013, ControlsFX
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 *     * Neither the name of ControlsFX, any associated website, nor the
 * names of its contributors may be used to endorse or promote products
 * derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL CONTROLSFX BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.github.haixing_hu.javafx.control.textfield;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * This is a simple implementation of a generic suggestion provider callback.
 * The complexity of suggestion generation is O(n) where n is the number of
 * possible suggestions.
 *
 * @param <T>
 *          type of suggestions.
 */
public abstract class AbstractSuggestionProvider<T> implements SuggestionProvider<T> {

  private final List<T> suggestions;

  /**
   * Constructs a {@link AbstractSuggestionProvider}.
   */
  public AbstractSuggestionProvider() {
    suggestions = new ArrayList<>();
  }

  /**
   * Adds the given new possible suggestions to this suggestion provider.
   *
   * @param suggestions
   *    the new possible suggestions to be added.
   */
  @SuppressWarnings("unchecked")
  public void addSuggestions(final T... suggestions) {
    synchronized (this.suggestions) {
      for (final T suggestion : suggestions) {
        this.suggestions.add(suggestion);
      }
    }
  }

  /**
   * Adds the given new possible suggestions to this suggestion provider.
   *
   * @param suggestions
   *    the new possible suggestions to be added.
   */
  public void addSuggestions(final Collection<T> suggestions) {
    synchronized (this.suggestions) {
      this.suggestions.addAll(suggestions);
    }
  }

  /**
   * Removes all current possible suggestions.
   */
  public void clearSuggestions() {
    synchronized (suggestions) {
      suggestions.clear();
    }
  }

  @Override
  public final Collection<T> call(final SuggestionRequest request) {
    final List<T> result = new ArrayList<>();
    if (! request.getUserText().isEmpty()) {
      synchronized (suggestions) {
        for (final T suggestion : suggestions) {
          if (isMatch(suggestion, request)) {
            result.add(suggestion);
          }
        }
      }
      Collections.sort(result, getComparator());
    }
    return result;
  }

  /**
   * Get the comparator to order the suggestions.
   *
   * @return the comparator to order the suggestions.
   */
  protected abstract Comparator<T> getComparator();

  /**
   * Checks whether the given possible suggestion is a match (i.e., is a valid
   * suggestion).
   *
   * @param suggestion
   *          a given possible suggestion.
   * @param request
   *          a suggestion request.
   * @return {@code true} if the given possible suggestion is a match (i.e., is
   *         a valid suggestion); {@code false} otherwise.
   */
  protected abstract boolean isMatch(T suggestion, SuggestionRequest request);
}
