package com.github.haixing_hu.javafx.control.textfield;

import java.util.Comparator;

import javafx.util.StringConverter;

import com.github.haixing_hu.javafx.util.StringConverterComparator;
import com.github.haixing_hu.javafx.util.ToStringComparator;
import com.github.haixing_hu.javafx.util.ToStringConverter;

import static com.github.haixing_hu.lang.Argument.requireNonNull;

/**
 * This is a simple string based suggestion provider. All generic suggestions T
 * are turned into strings for processing.
 *
 * @param <T>
 *          the type of suggestions.
 */
public class DefaultSuggestionProvider<T> extends AbstractSuggestionProvider<T> {

  private final StringConverter<T> converter;
  private final Comparator<T> comparator;

  /**
   * Create a new {@link DefaultSuggestionProvider}, using the default
   * {@link ToStringConverter} and {@link ToStringComparator}.
   */
  public DefaultSuggestionProvider() {
    converter = new ToStringConverter<T>();
    comparator = new ToStringComparator<T>();
  }

  /**
   * Create a new {@link DefaultSuggestionProvider}, using the specified string
   * converter to construct a suggestion comparator.
   *
   * @param converter
   *          a string converter.
   */
  public DefaultSuggestionProvider(StringConverter<T> converter) {
    this.converter = requireNonNull("converter", converter);
    this.comparator = new StringConverterComparator<T>(converter);
  }

  @Override
  protected Comparator<T> getComparator() {
    return comparator;
  }

  @Override
  protected boolean isMatch(T suggestion, SuggestionRequest request) {
    final String userText = request.getUserText().toLowerCase();
    final String suggestionText = converter.toString(suggestion).toLowerCase();
    return suggestionText.startsWith(userText);
  }
}