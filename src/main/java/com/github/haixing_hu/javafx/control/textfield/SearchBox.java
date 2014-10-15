/*
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
 */
package com.github.haixing_hu.javafx.control.textfield;

import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A text field used as the search box.
 *
 * @author Haixing Hu
 */
public class SearchBox extends CustomTextField {

  public static final String STYLE_CLASS = "search-box";

  public static final String STYLE_SHEET = "/textfield/search-box.css";

  public static final String DEFAULT_PROMPT_TEXT = "Search";

  /**
   * Constructs a {@link SearchBox}.
   */
  public SearchBox() {
    super();
    getStyleClass().add(STYLE_CLASS);
    setPromptText(DEFAULT_PROMPT_TEXT);
    final URL cssUrl = SearchBox.class.getResource(STYLE_SHEET);
    if (cssUrl != null) {
      getStylesheets().add(cssUrl.toExternalForm());
    } else {
      final Logger logger = LoggerFactory.getLogger(SearchBox.class);
      logger.error("Failed to load the resource: {}", STYLE_SHEET);
    }
    TextFields.addClearContentButton(this, rightProperty());
    TextFields.addSearchButton(this, leftProperty());
  }
}
