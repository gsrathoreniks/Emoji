/*
 * Copyright (C) 2016 - Niklas Baudy, Ruben Gees, Mario Đanić and contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.vanniktech.emoji.search

import com.vanniktech.emoji.Emoji

data class SearchEmojiResult(
  val emoji: Emoji,
  val shortcode: String,
  /** The range in [shortcode], which matches the query. This is used for highlighting. */
  val range: IntRange,
) {
  init {
    require(range.first in shortcode.indices) { "Index ${range.first} is out of bounds in $shortcode" }
    require(range.last in shortcode.indices) { "Index ${range.last} is out of bounds in $shortcode" }
  }
}
