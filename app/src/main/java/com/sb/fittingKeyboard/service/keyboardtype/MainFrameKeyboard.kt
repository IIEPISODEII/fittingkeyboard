package com.sb.fittingKeyboard.service.keyboardtype

import android.annotation.SuppressLint
import android.content.Intent
import android.widget.ImageButton
import androidx.core.content.res.ResourcesCompat
import com.sb.fittingKeyboard.R
import com.sb.fittingKeyboard.databinding.LayoutKeyboardBinding
import com.sb.fittingKeyboard.PACKAGE_NAME
import com.sb.fittingKeyboard.data.KEYBOARD_TOOLBAR_ACTIVE_COPY
import com.sb.fittingKeyboard.data.KEYBOARD_TOOLBAR_ACTIVE_CUT
import com.sb.fittingKeyboard.data.KEYBOARD_TOOLBAR_ACTIVE_GO_SETTING
import com.sb.fittingKeyboard.data.KEYBOARD_TOOLBAR_ACTIVE_PASTE
import com.sb.fittingKeyboard.data.KEYBOARD_TOOLBAR_ACTIVE_SELECT_ALL
import com.sb.fittingKeyboard.data.KEYBOARD_TOOLBAR_ACTIVE_SHOW_BOILERPLATE
import com.sb.fittingKeyboard.data.KEYBOARD_TOOLBAR_ACTIVE_SHOW_CURSOR
import com.sb.fittingKeyboard.data.KEYBOARD_TOOLBAR_ACTIVE_SHOW_EMOJI
import com.sb.fittingKeyboard.data.KEYBOARD_TOOLBAR_ACTIVE_SHOW_NUMBER
import com.sb.fittingKeyboard.service.MainKeyboardService
import com.sb.fittingKeyboard.service.keyboardtype.core.InputTypeState
import com.sb.fittingKeyboard.service.keyboardtype.core.TypedKeyboard
import com.sb.fittingKeyboard.service.util.RepeatTouchListener
import com.sb.fittingKeyboard.service.viewmodel.KeyboardViewModel

class MainFrameKeyboard(
    private val binding: LayoutKeyboardBinding,
    private val imeService: MainKeyboardService
): TypedKeyboard(binding.kbviewmodel!!, imeService) {

    @SuppressLint("ClickableViewAccessibility")
    override fun init() {
        val viewModel: KeyboardViewModel = binding.kbviewmodel!!
        val charKeyList = listOf(
            binding.btn0,
            binding.btn1,
            binding.btn2,
            binding.btn3,
            binding.btn4,
            binding.btn5,
            binding.btn6,
            binding.btn7,
            binding.btn8,
            binding.btn9
        )

        val goSettingImageButton by lazy {
            ImageButton(binding.root.context).apply {
                setImageDrawable(
                    ResourcesCompat.getDrawable(
                        this.resources,
                        R.drawable.ic_settings,
                        null
                    )
                )
                setOnClickListener {
                    val intent = binding.root.context.packageManager
                        .getLaunchIntentForPackage(PACKAGE_NAME) ?: return@setOnClickListener
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    binding.root.context.startActivity(intent)
                }
                setBackgroundColor(
                    ResourcesCompat.getColor(
                        this.resources,
                        R.color.white,
                        null
                    )
                )
            }
        }
        val showBoilerPlateImageButton by lazy {
            ImageButton(binding.root.context).apply {
                setImageDrawable(
                    ResourcesCompat.getDrawable(
                        this.resources,
                        R.drawable.ic_boilerplatetext_black,
                        null
                    )
                )
                setOnClickListener {
                    viewModel.setInputTypeState(InputTypeState.BOILERPLATE)
                }
                setBackgroundColor(
                    ResourcesCompat.getColor(
                        this.resources,
                        R.color.white,
                        null
                    )
                )
            }
        }
        val selectAllImageButton by lazy {
            ImageButton(binding.root.context).apply {
                setImageDrawable(
                    ResourcesCompat.getDrawable(
                        this.resources,
                        R.drawable.ic_select,
                        null
                    )
                )
                setOnClickListener {
                    selectAllText()
                }
                setBackgroundColor(
                    ResourcesCompat.getColor(
                        this.resources,
                        R.color.white,
                        null
                    )
                )
            }
        }
        val copyImageButton by lazy {
            ImageButton(binding.root.context).apply {
                setImageDrawable(ResourcesCompat.getDrawable(this.resources, R.drawable.ic_copy, null))
                setOnClickListener {
                    copyText()
                }
                setBackgroundColor(ResourcesCompat.getColor(this.resources, R.color.white, null))
            }
        }
        val cutImageButton by lazy {
            ImageButton(binding.root.context).apply {
                setImageDrawable(
                    ResourcesCompat.getDrawable(
                        this.resources,
                        R.drawable.ic_cut,
                        null
                    )
                )
                setOnClickListener {
                    cutText()
                }
                setBackgroundColor(
                    ResourcesCompat.getColor(
                        this.resources,
                        R.color.white,
                        null
                    )
                )
            }
        }
        val pasteImageButton by lazy {
            ImageButton(binding.root.context).apply {
                setImageDrawable(
                    ResourcesCompat.getDrawable(
                        this.resources,
                        R.drawable.ic_paste,
                        null
                    )
                )
                setOnClickListener {
                    pasteText()
                }
                setBackgroundColor(
                    ResourcesCompat.getColor(
                        this.resources,
                        R.color.white,
                        null
                    )
                )
            }
        }
        val showCursorImageButton by lazy {
            ImageButton(binding.root.context).apply {
                setImageDrawable(
                    ResourcesCompat.getDrawable(
                        this.resources,
                        R.drawable.ic_move,
                        null
                    )
                )
                setOnClickListener {
                    viewModel.setInputTypeState(InputTypeState.CURSOR)
                }
                setBackgroundColor(
                    ResourcesCompat.getColor(
                        this.resources,
                        R.color.white,
                        null
                    )
                )
            }
        }
        val showNumberImageButton by lazy {
            ImageButton(binding.root.context).apply {
                setImageDrawable(
                    ResourcesCompat.getDrawable(
                        this.resources,
                        R.drawable.ic_number_keypad,
                        null
                    )
                )
                setOnClickListener {
                    viewModel.setInputTypeState(InputTypeState.NUMBER)
                }
                setBackgroundColor(
                    ResourcesCompat.getColor(
                        this.resources,
                        R.color.white,
                        null
                    )
                )
            }
        }
        val showEmojiImageButton by lazy {
            ImageButton(binding.root.context).apply {
                setImageDrawable(
                    ResourcesCompat.getDrawable(
                        this.resources,
                        R.drawable.ic_outline_emoji_emotions_24,
                        null
                    )
                )
                setOnClickListener {
                    viewModel.setInputTypeState(InputTypeState.EMOJI)
                }
                setBackgroundColor(
                    ResourcesCompat.getColor(
                        this.resources,
                        R.color.white,
                        null
                    )
                )
            }
        }

        viewModel.inputTypeState.observe(imeService) {
            showBoilerPlateImageButton.setImageDrawable(
                ResourcesCompat.getDrawable(
                    showBoilerPlateImageButton.resources,
                    if (it == InputTypeState.BOILERPLATE) R.drawable.ic_keyboard_black else R.drawable.ic_boilerplatetext_black,
                    null
                )
            )

            showCursorImageButton.setImageDrawable(
                ResourcesCompat.getDrawable(
                    showCursorImageButton.resources,
                    if (it == InputTypeState.CURSOR) R.drawable.ic_keyboard_black else R.drawable.ic_move,
                    null
                )
            )

            showNumberImageButton.setImageDrawable(
                ResourcesCompat.getDrawable(
                    showNumberImageButton.resources,
                    if (it == InputTypeState.NUMBER) R.drawable.ic_keyboard_black else R.drawable.ic_number_keypad,
                    null
                )
            )

            showEmojiImageButton.setImageDrawable(
                ResourcesCompat.getDrawable(
                    showEmojiImageButton.resources,
                    if (it == InputTypeState.EMOJI) R.drawable.ic_keyboard_black else R.drawable.ic_outline_emoji_emotions_24,
                    null
                )
            )
        }

        viewModel.kbLongClickInterval.observe(imeService) {
            val longClickInterval = it.toLong() + 100L

            charKeyList.forEach { btn ->
                btn.setOnTouchListener(
                    RepeatTouchListener(
                        initialInterval = longClickInterval,
                        normalInterval = normalInterval,
                        actionDownEvent = { view, _ -> inputSpecialKey(view) }
                    )
                )
            }
        }

        viewModel.prefSettingToolbarSetting.observe(imeService) {
            val toolbarLinearLayout = binding.keyboardToolBarLine

            val sortedToolbarSetting = it.toList().sortedBy { (_, value) -> kotlin.math.abs(value) }.toMap()
            toolbarLinearLayout.removeAllViews()
            sortedToolbarSetting.keys.toList().forEachIndexed { _, key ->
                val ordering = sortedToolbarSetting[key]!!-1
                when (key) {
                    KEYBOARD_TOOLBAR_ACTIVE_GO_SETTING -> toolbarLinearLayout.reorderChild(goSettingImageButton, ordering)
                    KEYBOARD_TOOLBAR_ACTIVE_SHOW_BOILERPLATE -> toolbarLinearLayout.reorderChild(showBoilerPlateImageButton, ordering)
                    KEYBOARD_TOOLBAR_ACTIVE_SELECT_ALL -> toolbarLinearLayout.reorderChild(selectAllImageButton, ordering)
                    KEYBOARD_TOOLBAR_ACTIVE_COPY -> toolbarLinearLayout.reorderChild(copyImageButton, ordering)
                    KEYBOARD_TOOLBAR_ACTIVE_CUT -> toolbarLinearLayout.reorderChild(cutImageButton, ordering)
                    KEYBOARD_TOOLBAR_ACTIVE_PASTE -> toolbarLinearLayout.reorderChild(pasteImageButton, ordering)
                    KEYBOARD_TOOLBAR_ACTIVE_SHOW_CURSOR -> toolbarLinearLayout.reorderChild(showCursorImageButton, ordering)
                    KEYBOARD_TOOLBAR_ACTIVE_SHOW_NUMBER -> toolbarLinearLayout.reorderChild(showNumberImageButton, ordering)
                    KEYBOARD_TOOLBAR_ACTIVE_SHOW_EMOJI -> toolbarLinearLayout.reorderChild(showEmojiImageButton, ordering)
                }
            }
        }
    }

}