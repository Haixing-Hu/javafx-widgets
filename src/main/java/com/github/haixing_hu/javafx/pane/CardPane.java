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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

/**
 * A {@link CardPane} is a {@link Pane} providing the AWT's
 * {@link java.awt.CardLayout CardLayout} or SWT's {@code StackLayout}.
 *
 * The following code shows how to use it.
 *
 * <pre>
 * <code>
 * StackPane card1 = new StackPane();
 * card1.getChildren().add(new Label("Card 1"));
 * StackPane card2 = new StackPane();
 * card2.getChildren().add(new Label("Card 2"));
 * StackPane card3 = new StackPane();
 * card3.getChildren().add(new Label("Card 3"));
 * CardPane pane = new CardPane();
 * pane.addAllCards(card1, card2, card3);
 * ...
 * pane.showCard(1);
 * </code>
 * </pre>
 *
 * @author Haixing Hu
 */
public class CardPane extends FillPane {

  private final List<Node> cards;
  private int index;

  /**
   * Constructs a {@link CardPane}.
   */
  public CardPane() {
    super();
    cards = new ArrayList<Node>();
    index = - 1;
  }

  /**
   * Tests whether the cards list in this {@link CardPane} is empty.
   *
   * @return {@code true} if the cards list in this {@link CardPane} is empty;
   *         {@code false} otherwise.
   */
  public boolean isEmpty() {
    return cards.isEmpty();
  }

  /**
   * Gets the number of cards added to this {@link CardPane}.
   *
   * @return the number of cards added to this {@link CardPane}.
   */
  public int size() {
    return cards.size();
  }

  /**
   * Adds a card to this {@link CardPane}.
   *
   * @param card
   *          the card to be added to this {@link CardPane}.
   */
  public void addCard(Node card) {
    cards.add(card);
    if (index < 0) {
      showCard(0);
    }
  }

  /**
   * Adds an array of card nodes to this {@link CardPane}.
   *
   * @param cards
   *          the card nodes to be added to this {@link CardPane}.
   */
  public void addAllCards(Node... cards) {
    if (cards.length == 0) {
      return;
    }
    for (final Node card : cards) {
      this.cards.add(card);
    }
    if (index < 0) { // default to show the first card
      showCard(0);
    }
  }

  /**
   * Adds a collection of card nodes to this {@link CardPane}.
   *
   * @param cards
   *          the card nodes to be added to this {@link CardPane}.
   */
  public void addAllCards(Collection<Node> cards) {
    if (cards.size() == 0) {
      return;
    }
    this.cards.addAll(cards);
    if (index < 0) { // default to show the first card
      showCard(0);
    }
  }

  /**
   * Clears all card nodes in this {@link CardPane}.
   */
  public void clearCards() {
    cards.clear();
    index = - 1;
  }

  /**
   * Gets a card in this {@link CardPane}.
   *
   * @param i
   *          the index of the card to be get.
   * @return the card with the specified index in this {@link CardPane}..
   * @throws IndexOutOfBoundsException
   *           if the index is out of bounds.
   */
  public Node getCard(int i) {
    return cards.get(i);
  }

  /**
   * Gets the current displayed card in this {@link CardPane}.
   *
   * @return the current displayed card in this {@link CardPane}, or
   *         {@code null} if there is no card in this {@link CardPane}.
   */
  public Node getCurrentCard() {
    return (index < 0 ? null : cards.get(index));
  }

  /**
   * Gets the index of the current displayed card in this {@link CardPane}.
   *
   * @return the index of the current displayed card in this {@link CardPane},
   *         or -1 if there is no card in this {@link CardPane}.
   */
  public int getCurrentCardIndex() {
    return index;
  }

  /**
   * Tests whether the current displayed card is the first card.
   *
   * @return {@code true} if the current displayed card is the first card;
   *         {@code false} otherwise.
   */
  public boolean atFirstCard() {
    return (index == 0);
  }

  /**
   * Tests whether the current displayed card is the last card.
   *
   * @return {@code true} if the current displayed card is the last card;
   *         {@code false} otherwise.
   */
  public boolean atLastCard() {
    return (index == (cards.size() - 1));
  }

  /**
   * Display the first card.
   * <p>
   * If there is no card in this {@link CardPane}, calling this function has no
   * effect.
   */
  public void showFirstCard() {
    if (cards.size() > 0) {
      showCard(0);
    }
  }

  /**
   * Display the last card.
   * <p>
   * If there is no card in this {@link CardPane}, calling this function has no
   * effect.
   */
  public void showLastCard() {
    if (cards.size() > 0) {
      showCard(cards.size() - 1);
    }
  }

  /**
   * Display the previous card.
   * <p>
   * If the current card is already the fist card, or there is no card in this
   * {@link CardPane}, calling this function has no effect.
   */
  public void showPreviousCard() {
    if (index > 0) {
      showCard(index - 1);
    }
  }

  /**
   * Display the next card.
   * <p>
   * If the current card is already the last card, or there is no card in this
   * {@link CardPane}, calling this function has no effect.
   */
  public void showNextCard() {
    if ((cards.size() > 0) && (index < (cards.size() - 1))) {
      showCard(index + 1);
    }
  }

  /**
   * Display a specified card.
   *
   * @param index
   *          the index of the card to be displayed.
   * @throws IndexOutOfBoundsException
   *           if the index is out of bounds.
   */
  public void showCard(int index) {
    if (this.index != index) {
      final Node node = cards.get(index);
      getChildren().setAll(node);
      this.index = index;
    }
  }
}
