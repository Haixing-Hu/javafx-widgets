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
 * Copyright (c) 2014, ControlsFX
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

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ObjectPropertyBase;
import javafx.event.Event;
import javafx.event.EventDispatchChain;
import javafx.event.EventHandler;
import javafx.event.EventTarget;
import javafx.event.EventType;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.Skin;
import javafx.util.StringConverter;

import javax.annotation.Nullable;

import com.sun.javafx.event.EventHandlerManager;

/**
 * The AutoCompletionBinding is the abstract base class of all auto-completion
 * bindings. This class is the core logic for the auto-completion feature but
 * highly customizable.
 *
 * <p>
 * To use the auto-completion functionality, refer to the {@link TextFields}
 * class.
 *
 * @param <T>
 *          the type of the suggestions.
 * @see TextFields
 */
@SuppressWarnings("restriction")
public abstract class AutoCompletionBinding<T> implements EventTarget {

  private final Node completionTarget;
  final SuggestionProvider<T> suggestionProvider;
  final AutoCompletePopup<T> autoCompletionPopup;
  final EventHandlerManager eventHandlerManager;
  private final Object suggestionsTaskLock;
  private FetchSuggestionsTask<T> suggestionsTask;
  private boolean ignoreInputChanges;
  private final ObjectProperty<AutoCompletionEventHandler<T>> onAutoCompleted;

  /**
   * Creates a new AutoCompletionBinding
   *
   * @param completionTarget
   *          the target node to which auto-completion shall be added.
   * @param suggestionProvider
   *          the strategy to retrieve suggestions, or {@code null} if none.
   * @param converter
   *          the converter to be used to convert suggestions to strings.
   */
  protected AutoCompletionBinding(Node completionTarget,
      @Nullable SuggestionProvider<T> suggestionProvider,
      StringConverter<T> converter) {

    this.completionTarget = completionTarget;
    this.suggestionProvider = suggestionProvider;
    this.autoCompletionPopup = new AutoCompletePopup<>();
    this.autoCompletionPopup.setConverter(converter);
    this.eventHandlerManager = new EventHandlerManager(this);
    this.suggestionsTaskLock = new Object();
    this.suggestionsTask = null;
    this.ignoreInputChanges = false;

    autoCompletionPopup.setOnSuggestion(sce -> {
      try {
        setIgnoreInputChanges(true);
        completeUserInput(sce.getSuggestion());
        fireAutoCompletion(sce.getSuggestion());
        hidePopup();
      } finally {
        // Ensure that ignore is always set back to false
        setIgnoreInputChanges(false);
      }
    });

    onAutoCompleted = new ObjectPropertyBase<AutoCompletionEventHandler<T>>() {
      @SuppressWarnings({ "rawtypes", "unchecked" })
      @Override
      protected void invalidated() {
        eventHandlerManager.setEventHandler(AutoCompletionEvent.AUTO_COMPLETED,
            (EventHandler<AutoCompletionEvent>) (Object) get());
      }

      @Override
      public Object getBean() {
        return AutoCompletionBinding.this;
      }

      @Override
      public String getName() {
        return "onAutoCompleted"; //$NON-NLS-1$
      }
    };
  }

  /**
   * Set the current text the user has entered.
   *
   * @param userText
   *          the new text the user has entered.
   */
  public final void setUserInput(String userText) {
    if (! isIgnoreInputChanges()) {
      onUserInputChanged(userText);
    }
  }

  /**
   * Gets the target node for auto completion.
   *
   * @return the target node for auto completion.
   */
  public Node getCompletionTarget() {
    return completionTarget;
  }

  /**
   * Disposes the binding.
   */
  public abstract void dispose();

  /**
   * Complete the current user-input with the provided completion.
   *
   * @param completion
   *          a provided completion.
   */
  protected abstract void completeUserInput(T completion);

  /**
   * Show the auto completion popup.
   */
  protected void showPopup() {
    autoCompletionPopup.show(completionTarget);
    selectFirstSuggestion(autoCompletionPopup);
  }

  /**
   * Hide the auto completion popup.
   */
  protected void hidePopup() {
    autoCompletionPopup.hide();
  }

  /**
   * Fire an auto completion event with a provided completion.
   *
   * @param completion
   *      a provided completion.
   */
  protected void fireAutoCompletion(T completion) {
    Event.fireEvent(this, new AutoCompletionEvent<>(completion));
  }

  /**
   * Selects the first suggestion (if any), so the user can choose it by
   * pressing enter immediately.
   *
   * @param autoCompletionPopup
   *          the auto completion popup.
   */
  private void selectFirstSuggestion(AutoCompletePopup<?> autoCompletionPopup) {
    final Skin<?> skin = autoCompletionPopup.getSkin();
    if (skin instanceof AutoCompletePopupSkin) {
      final AutoCompletePopupSkin<?> au = (AutoCompletePopupSkin<?>) skin;
      final ListView<?> li = (ListView<?>) au.getNode();
      if ((li.getItems() != null) && ! li.getItems().isEmpty()) {
        li.getSelectionModel().select(0);
      }
    }
  }

  /**
   * Occurs when the user text has changed and the suggestions require an
   * update.
   *
   * @param userText
   *          the new user text.
   */
  private final void onUserInputChanged(final String userText) {
    autoCompletionPopup.getSuggestions().clear();
    synchronized (suggestionsTaskLock) {
      if ((suggestionsTask != null) && suggestionsTask.isRunning()) {
        // cancel the current running task
        suggestionsTask.cancel();
      }
      // create a new fetcher task
      suggestionsTask = new FetchSuggestionsTask<T>(this, userText);
      new Thread(suggestionsTask).start();
    }
  }

  /**
   * Tests whether changes to the user input should be ignored.
   *
   * @return {@code true} if changes to the user input should be ignored;
   *         {@code false} otherwise.
   */
  private boolean isIgnoreInputChanges() {
    return ignoreInputChanges;
  }

  /**
   * If IgnoreInputChanges is set to true, all changes to the user input are
   * ignored. This is primary used to avoid self triggering while auto
   * completing.
   *
   * @param state
   *          the new state.
   */
  private void setIgnoreInputChanges(boolean state) {
    ignoreInputChanges = state;
  }

  /**
   * Set a event handler which is invoked after an auto completion.
   *
   * @param value
   *          the new event handler.
   */
  public final void setOnAutoCompleted(AutoCompletionEventHandler<T> value) {
    onAutoCompleted.set(value);
  }

  /**
   * Gets the event handler which is invoked after an auto completion.
   *
   * @return the event handler which is invoked after an auto completion.
   */
  public final AutoCompletionEventHandler<T> getOnAutoCompleted() {
    return onAutoCompleted.get();
  }

  /**
   * Gets the property of event handler which is invoked after an auto
   * completion.
   *
   * @return the property of event handler which is invoked after an auto
   *         completion.
   */
  public final ObjectProperty<AutoCompletionEventHandler<T>> onAutoCompletedProperty() {
    return onAutoCompleted;
  }

  /**
   * Registers an event handler to this EventTarget. The handler is called when
   * the menu item receives an {@code Event} of the specified type during the
   * bubbling phase of event delivery.
   *
   * @param <E>
   *          the specific event class of the handler
   * @param eventType
   *          the type of the events to receive by the handler
   * @param eventHandler
   *          the handler to register
   * @throws NullPointerException
   *           if the event type or handler is null
   */
  public final <E extends Event> void addEventHandler(EventType<E> eventType,
      EventHandler<E> eventHandler) {
    eventHandlerManager.addEventHandler(eventType, eventHandler);
  }

  /**
   * Unregisters a previously registered event handler from this EventTarget.
   * One handler might have been registered for different event types, so the
   * caller needs to specify the particular event type from which to unregister
   * the handler.
   *
   * @param <E>
   *          the specific event class of the handler
   * @param eventType
   *          the event type from which to unregister
   * @param eventHandler
   *          the handler to unregister
   * @throws NullPointerException
   *           if the event type or handler is null
   */
  public final <E extends Event> void removeEventHandler(EventType<E> eventType,
      EventHandler<E> eventHandler) {
    eventHandlerManager.removeEventHandler(eventType, eventHandler);
  }

  @Override
  public EventDispatchChain buildEventDispatchChain(EventDispatchChain tail) {
    return tail.prepend(eventHandlerManager);
  }

}
