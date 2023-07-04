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
    ): TypedKeyboard(binding.kbviewmodel!!, imeService) {

    @SuppressLint("ClickableViewAccessibility")
    override fun init() {
        val viewModel: KeyboardViewModel = binding.kbviewmodel!!

        binding.btnCursorkeypadCopy.setOnClickListener {
            copyText()
        }

        binding.btnCursorkeypadPaste.setOnClickListener {
            pasteText()
        }

        binding.btnCursorkeypadCut.setOnClickListener {
            cutText()
        }

        binding.btnCursorkeypadSelectAll.setOnClickListener {
            selectAllText()
        }

        binding.btnCursorkeypadSelect.setOnClickListener {
            selectText()
        }

        binding.imgbtnCursorkeypadBack.setOnClickListener {
            vibrate()
            viewModel.setInputTypeState(InputTypeState.KR_NORMAL)
        }

        binding.imgbtnCursorkeypadEnter.setOnClickListener {
            inputEnter()
        }

        viewModel.kbLongClickInterval.observe(imeService) {
            val longClickInterval = it.toLong() + 100L

            binding.imgbtnCursorkeypadMoveToHead.setOnTouchListener(
                RepeatTouchListener(
                    initialInterval = longClickInterval,
                    normalInterval = normalInterval,
                    actionDownEvent = { _, _ ->
                        moveCursorToHead()
                    }
                )
            )

            binding.imgbtnCursorkeypadMoveUp.setOnTouchListener(
                RepeatTouchListener(
                    initialInterval = longClickInterval,
                    normalInterval = normalInterval,
                    actionDownEvent = { _, _ ->
                        moveCursorUp()
                    }
                )
            )

            binding.imgbtnCursorkeypadMoveToTail.setOnTouchListener(
                RepeatTouchListener(
                    initialInterval = longClickInterval,
                    normalInterval = normalInterval,
                    actionDownEvent = { _, _ ->
                        moveCursorToTail()
                    }
                )
            )

            binding.imgbtnCursorkeypadDeletePreviousChar.setOnTouchListener(
                RepeatTouchListener(
                    initialInterval = longClickInterval,
                    normalInterval = normalInterval,
                    actionDownEvent = { _, _ ->
                        deletePrevChar()
                    }
                )
            )

            binding.imgbtnCursorkeypadMoveLeft.setOnTouchListener(
                RepeatTouchListener(
                    initialInterval = longClickInterval,
                    normalInterval = normalInterval,
                    actionDownEvent = { _, _ ->
                        moveCursorLeft()
                    }
                )
            )

            binding.imgbtnCursorkeypadMoveDown.setOnTouchListener(
                RepeatTouchListener(
                    initialInterval = longClickInterval,
                    normalInterval = normalInterval,
                    actionDownEvent = { _, _ ->
                        moveCursorDown()
                    }
                )
            )

            binding.imgbtnCursorkeypadMoveRight.setOnTouchListener(
                RepeatTouchListener(
                    initialInterval = longClickInterval,
                    normalInterval = normalInterval,
                    actionDownEvent = { _, _ ->
                        moveCursorRight()
                    }
                )
            )

            binding.imgbtnCursorkeypadDelete.setOnTouchListener(
                RepeatTouchListener(
                    initialInterval = longClickInterval,
                    normalInterval = normalInterval,
                    actionDownEvent = { _, _ ->
                        deleteChar()
                    }
                )
            )

            binding.btnCursorkeypadSpace.setOnTouchListener(
                RepeatTouchListener(
                    initialInterval = longClickInterval,
                    normalInterval = normalInterval,
                    actionDownEvent = { view, _ ->
                        inputSpecialKey(view)
                    }
                )
            )
        }
    }
}