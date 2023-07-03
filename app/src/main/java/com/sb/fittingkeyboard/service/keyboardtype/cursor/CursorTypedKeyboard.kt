package com.sb.fittingkeyboard.service.keyboardtype.cursor

import android.annotation.SuppressLint
import android.content.Context.VIBRATOR_SERVICE
import android.os.Build
import android.os.Vibrator
import android.view.inputmethod.ExtractedTextRequest
import android.view.inputmethod.InputConnection
import android.widget.Toast
import com.sb.fittingKeyboard.databinding.FragmentCursorkeypadBinding
import com.sb.fittingkeyboard.service.MainKeyboardService
import com.sb.fittingkeyboard.service.keyboardtype.core.InputTypeState
import com.sb.fittingkeyboard.service.keyboardtype.core.TypedKeyboard
import com.sb.fittingkeyboard.service.util.RepeatTouchListener
import com.sb.fittingkeyboard.service.viewmodel.KeyboardViewModel

class CursorTypedKeyboard(
    private val binding: FragmentCursorkeypadBinding,
    private val imeService: MainKeyboardService
    ): TypedKeyboard(binding.kbviewmodel, imeService) {

    @SuppressLint("ClickableViewAccessibility")
    override fun init() {
        val viewModel: KeyboardViewModel = binding.kbviewmodel!!
        val vibrator = binding.root.context.getSystemService(VIBRATOR_SERVICE) as Vibrator

        binding.btnCursorkeypadCopy.setOnClickListener {
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

        binding.btnCursorkeypadPaste.setOnClickListener {
            viewModel.switchSelectingTextMode(false)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                if (imeService.currentInputConnection.requestCursorUpdates(InputConnection.CURSOR_UPDATE_IMMEDIATE)) {
                    clearComposingStep(imeService)
                    imeService.currentInputConnection.performContextMenuAction(android.R.id.paste)
                }
            }
        }

        binding.btnCursorkeypadCut.setOnClickListener {
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

        binding.btnCursorkeypadSelectAll.setOnClickListener {
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

        binding.btnCursorkeypadSelect.setOnClickListener {
            viewModel.switchSelectingTextMode(!viewModel.isSelectingText)
        }

        binding.imgbtnCursorkeypadBack.setOnClickListener {
            viewModel.setInputTypeState(InputTypeState.KR_NORMAL)
            if (viewModel.kbHasVibration.value!!) vibrate(vibratorService = vibrator, intensity = viewModel.kbVibrationIntensity.value!!.toLong())
        }

        binding.imgbtnCursorkeypadEnter.setOnClickListener {
            inputEnter(vibrator)
        }

        viewModel.kbLongClickInterval.observe(imeService) {
            binding.imgbtnCursorkeypadMoveToHead.setOnTouchListener(
                RepeatTouchListener(
                    initialInterval = it.toLong() + 100L,
                    normalInterval = normalInterval,
                    actionDownEvent = { _, _ ->
                        moveCursorToHead()
                    }
                )
            )

            binding.imgbtnCursorkeypadMoveUp.setOnTouchListener(
                RepeatTouchListener(
                    initialInterval = it.toLong() + 100L,
                    normalInterval = normalInterval,
                    actionDownEvent = { _, _ ->
                        moveCursorUp()
                    }
                )
            )

            binding.imgbtnCursorkeypadMoveToTail.setOnTouchListener(
                RepeatTouchListener(
                    initialInterval = it.toLong() + 100L,
                    normalInterval = normalInterval,
                    actionDownEvent = { _, _ ->
                        moveCursorToTail()
                    }
                )
            )

            binding.imgbtnCursorkeypadDeletePreviousChar.setOnTouchListener(
                RepeatTouchListener(
                    initialInterval = it.toLong() + 100L,
                    normalInterval = normalInterval,
                    actionDownEvent = { _, _ ->
                        deletePrevChar(vibrator)
                    }
                )
            )

            binding.imgbtnCursorkeypadMoveLeft.setOnTouchListener(
                RepeatTouchListener(
                    initialInterval = it.toLong() + 100L,
                    normalInterval = normalInterval,
                    actionDownEvent = { _, _ ->
                        moveCursorLeft()
                    }
                )
            )

            binding.imgbtnCursorkeypadMoveDown.setOnTouchListener(
                RepeatTouchListener(
                    initialInterval = it.toLong() + 100L,
                    normalInterval = normalInterval,
                    actionDownEvent = { _, _ ->
                        moveCursorDown()
                    }
                )
            )

            binding.imgbtnCursorkeypadMoveRight.setOnTouchListener(
                RepeatTouchListener(
                    initialInterval = it.toLong() + 100L,
                    normalInterval = normalInterval,
                    actionDownEvent = { _, _ ->
                        moveCursorRight()
                    }
                )
            )

            binding.imgbtnCursorkeypadDelete.setOnTouchListener(
                RepeatTouchListener(
                    initialInterval = it.toLong() + 100L,
                    normalInterval = normalInterval,
                    actionDownEvent = { _, _ ->
                        deleteChar(vibrator)
                    }
                )
            )

            binding.btnCursorkeypadSpace.setOnTouchListener(
                RepeatTouchListener(
                    initialInterval = it.toLong() + 100L,
                    normalInterval = normalInterval,
                    actionDownEvent = { view, _ ->
                        inputSpecialKey(view, vibrator)
                    }
                )
            )
        }
    }
}