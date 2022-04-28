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
 *
 */
package com.vanniktech.emoji.sample

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.vanniktech.emoji.EmojiPopup
import com.vanniktech.emoji.sample.databinding.ViewCustomBinding
import com.vanniktech.emoji.traits.EmojiTrait

class CustomViewActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val customView = CustomView(this, null)
    setContentView(customView)
    customView.setUpEmojiPopup()
  }

  internal class CustomView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
  ) : LinearLayout(context, attrs) {
    private val binding = ViewCustomBinding.inflate(LayoutInflater.from(context), this)

    private var forceSingleEmoji: EmojiTrait? = null

    init {
      orientation = VERTICAL
    }

    fun setUpEmojiPopup() {
      binding.onlyAllowSingleEmoji.setOnCheckedChangeListener { _, isChecked: Boolean ->
        if (isChecked) {
          forceSingleEmoji = binding.editText.installForceSingleEmoji()
        } else if (forceSingleEmoji != null) {
          forceSingleEmoji!!.uninstall()
        }
      }
      val emojiPopup = EmojiPopup.Builder.fromRootView(this)
        .setKeyboardAnimationStyle(R.style.emoji_fade_animation_style)
        .setPageTransformer(PageTransformer())
        .build(binding.editText)
      binding.editText.disableKeyboardInput(emojiPopup)
      binding.button.setOnClickListener { binding.editText.requestFocus() }
    }
  }
}