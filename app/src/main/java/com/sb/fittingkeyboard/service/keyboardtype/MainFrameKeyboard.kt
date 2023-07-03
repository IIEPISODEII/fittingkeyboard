package com.sb.fittingkeyboard.service.keyboardtype

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Vibrator
import android.view.inputmethod.ExtractedTextRequest
import android.view.inputmethod.InputConnection
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import com.sb.fittingKeyboard.R
import com.sb.fittingKeyboard.databinding.FragmentKeyboardChunjiinBasicBinding
import com.sb.fittingKeyboard.databinding.LayoutKeyboardBinding
import com.sb.fittingkeyboard.Constants
import com.sb.fittingkeyboard.service.MainKeyboardService
import com.sb.fittingkeyboard.service.keyboardtype.core.InputTypeState
import com.sb.fittingkeyboard.service.keyboardtype.core.TypedKeyboard
import com.sb.fittingkeyboard.service.viewmodel.KeyboardViewModel

class MainFrameKeyboard(
    private val binding: LayoutKeyboardBinding,
    private val imeService: MainKeyboardService
): TypedKeyboard(binding.kbviewmodel, imeService) {

    @SuppressLint("ClickableViewAccessibility")
    override fun init() {
        val viewModel: KeyboardViewModel = binding.kbviewmodel!!
        val vibrator = binding.root.context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

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
                        .getLaunchIntentForPackage(Constants.PACKAGE_NAME) ?: return@setOnClickListener
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
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        if (imeService.currentInputConnection.requestCursorUpdates(InputConnection.CURSOR_UPDATE_IMMEDIATE)) {
                            val textLength =
                                imeService.currentInputConnection.getExtractedText(ExtractedTextRequest(), 0).text.length
                            clearComposingStep(imeService)
                            imeService.currentInputConnection.setSelection(0, textLength)
                            viewModel.initializeSavedCursorPosition()
                            if (textLength == 0) Toast.makeText(imeService, "선택할 문구가 없습니다.", Toast.LENGTH_SHORT).show()
                        }
                    }
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
                    viewModel.switchSelectingTextMode(false)
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        if (imeService.currentInputConnection.requestCursorUpdates(InputConnection.CURSOR_UPDATE_IMMEDIATE)) {
                            clearComposingStep(imeService)
                            if (imeService.currentInputConnection.getSelectedText(InputConnection.GET_TEXT_WITH_STYLES) == null) {
                                Toast.makeText(imeService, "문구를 복사하시려면\n문구를 먼저 선택해주세요.", Toast.LENGTH_SHORT).show()
                            } else {
                                imeService.currentInputConnection.performContextMenuAction(android.R.id.copy)
                            }
                        }
                    }
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
                    viewModel.switchSelectingTextMode(false)
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        if (imeService.currentInputConnection.requestCursorUpdates(InputConnection.CURSOR_UPDATE_IMMEDIATE)) {
                            clearComposingStep(imeService)
                            if (imeService.currentInputConnection.getSelectedText(InputConnection.GET_TEXT_WITH_STYLES) == null) {
                                Toast.makeText(
                                    imeService,
                                    "문구를 잘라내시려면\n문구를 먼저 선택해주세요.",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else {
                                imeService.currentInputConnection.performContextMenuAction(android.R.id.cut)
                            }
                        }
                    }
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
                    viewModel.switchSelectingTextMode(false)
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        if (imeService.currentInputConnection.requestCursorUpdates(InputConnection.CURSOR_UPDATE_IMMEDIATE)) {
                            clearComposingStep(imeService)
                            imeService.currentInputConnection.performContextMenuAction(android.R.id.paste)
                        }
                    }
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

        viewModel.prefSettingToolbarSetting.observe(imeService) {
            val toolbarLinearLayout = binding.keyboardToolBarLine

            val sortedToolbarSetting = it.toList().sortedBy { (_, value) -> kotlin.math.abs(value) }.toMap()
            toolbarLinearLayout.removeAllViews()
            sortedToolbarSetting.keys.toList().forEachIndexed { _, key ->
                val ordering = sortedToolbarSetting[key]!!-1
                when (key) {
                    Constants.KEYBOARD_TOOLBAR_ACTIVE_GO_SETTING -> toolbarLinearLayout.reorderChild(goSettingImageButton, ordering)
                    Constants.KEYBOARD_TOOLBAR_ACTIVE_SHOW_BOILERPLATE -> toolbarLinearLayout.reorderChild(showBoilerPlateImageButton, ordering)
                    Constants.KEYBOARD_TOOLBAR_ACTIVE_SELECT_ALL -> toolbarLinearLayout.reorderChild(selectAllImageButton, ordering)
                    Constants.KEYBOARD_TOOLBAR_ACTIVE_COPY -> toolbarLinearLayout.reorderChild(copyImageButton, ordering)
                    Constants.KEYBOARD_TOOLBAR_ACTIVE_CUT -> toolbarLinearLayout.reorderChild(cutImageButton, ordering)
                    Constants.KEYBOARD_TOOLBAR_ACTIVE_PASTE -> toolbarLinearLayout.reorderChild(pasteImageButton, ordering)
                    Constants.KEYBOARD_TOOLBAR_ACTIVE_SHOW_CURSOR -> toolbarLinearLayout.reorderChild(showCursorImageButton, ordering)
                    Constants.KEYBOARD_TOOLBAR_ACTIVE_SHOW_NUMBER -> toolbarLinearLayout.reorderChild(showNumberImageButton, ordering)
                    Constants.KEYBOARD_TOOLBAR_ACTIVE_SHOW_EMOJI -> toolbarLinearLayout.reorderChild(showEmojiImageButton, ordering)
                }
            }
        }
    }

}