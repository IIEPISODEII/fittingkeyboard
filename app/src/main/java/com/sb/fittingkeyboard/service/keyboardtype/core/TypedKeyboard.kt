package com.sb.fittingkeyboard.service.keyboardtype.core

import android.content.Intent
import android.inputmethodservice.InputMethodService
import android.os.Build
import android.os.SystemClock
import android.os.VibrationEffect
import android.os.Vibrator
import android.text.TextUtils
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.ExtractedTextRequest
import android.view.inputmethod.InputConnection
import android.widget.Button
import com.sb.fittingKeyboard.R
import com.sb.fittingkeyboard.Constants
import com.sb.fittingkeyboard.keyboardsettings.ui.MainActivity
import com.sb.fittingkeyboard.service.MainKeyboardService
import com.sb.fittingkeyboard.service.koreanautomata.HanguelChunjiin
import com.sb.fittingkeyboard.service.koreanautomata.HanguelDanmoum
import com.sb.fittingkeyboard.service.koreanautomata.HanguelNARATGUL
import com.sb.fittingkeyboard.service.koreanautomata.HanguelQWERTY
import com.sb.fittingkeyboard.service.util.decToHex
import com.sb.fittingkeyboard.service.viewmodel.KeyboardViewModel

abstract class TypedKeyboard(private val viewModel: KeyboardViewModel, private val imeService: MainKeyboardService) {

    abstract fun init()

    val normalInterval: Long = 37

    fun vibrate(vibratorService: Vibrator, intensity: Long) {
        val vibrationIntensity = intensity+25L

        if (vibratorService.hasVibrator()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibratorService.vibrate(
                    VibrationEffect.createOneShot(
                        vibrationIntensity,
                        vibrationIntensity.toInt()
                    )
                )
            } else {
                vibratorService.vibrate(vibrationIntensity)
            }
        }
    }

    fun inputCharKey(view: View, vibrator: Vibrator) {
        val cursorCS =
            imeService.currentInputConnection.getSelectedText(InputConnection.GET_TEXT_WITH_STYLES)
        if (cursorCS != null && cursorCS.isNotEmpty()) {
            clearComposingStep(imeService) // 선택 중인 글자가 있으면 초기화
        }
        when (viewModel.inputTypeState.value!!) {
            InputTypeState.KR_NORMAL, InputTypeState.KR_SHIFT -> {
                when (viewModel.kbKrImeMode.value!!) {
                    Constants.IME_KR_FLAG_QWERTY -> {
                        val (c1, c2) = HanguelQWERTY.composeChar((view as Button).text!!.single())
                        if (c1 != null) {
                            imeService.currentInputConnection.commitText(c1, 1)
                            if (c2 != null) imeService.currentInputConnection.setComposingText(c2, 1)
                        } else {
                            if (c2 != null) imeService.currentInputConnection.setComposingText(c2, 1)
                        }
                        if (viewModel.inputTypeState.value!! == InputTypeState.KR_SHIFT) viewModel.setInputTypeState(InputTypeState.KR_NORMAL)
                    }
                    Constants.IME_KR_FLAG_CHUN, Constants.IME_KR_FLAG_CHUN_AMBI -> {
                        var c1: String?
                        var c2: String?
                        when (view.id) {
                            in arrayOf(R.id.btn_kr_chun_k, R.id.btn_kr_chun_left_k) -> {
                                val chars =
                                    HanguelChunjiin.composeChar('ᆞ', System.currentTimeMillis())
                                c1 = chars.commited
                                c2 = chars.composing
                            }
                            else -> {
                                val chars = HanguelChunjiin.composeChar(
                                    (view as Button).text[0],
                                    System.currentTimeMillis()
                                )
                                c1 = chars.commited
                                c2 = chars.composing
                            }
                        }

                        if (c1 != null) {
                            if ("\u0000" in c1) c1 = c1.filterNot { it == '\u0000' }
                            imeService.currentInputConnection.commitText(c1, 1)
                            if (c2 != null) {
                                if ("\u0000" in c2) c2 = c2.filterNot { it == '\u0000' }
                                imeService.currentInputConnection.setComposingText(c2, 1)
                            }
                        } else {
                            if (c2 != null) {
                                if ("\u0000" in c2) c2 = c2.filterNot { it == '\u0000' }
                                imeService.currentInputConnection.setComposingText(c2, 1)
                            }
                        }
                    }
                    Constants.IME_KR_FLAG_NARAT -> {
                        val c1: String?
                        val c2: String?

                        if (view.id == R.id.btn_kr_narat_add) {
                            val chars = HanguelNARATGUL.composeChar('ᆞ')
                            c1 = chars.commited
                            c2 = chars.composing
                        } else if (view.id == R.id.btn_kr_narat_shift) {
                            val chars = HanguelNARATGUL.composeChar('ᆢ')
                            c1 = chars.commited
                            c2 = chars.composing
                        } else {
                            val chars = HanguelNARATGUL.composeChar((view as Button).text[0])
                            c1 = chars.commited
                            c2 = chars.composing
                        }

                        if (c1 != null) {
                            imeService.currentInputConnection.commitText(c1, 1)
                            if (c2 != null) imeService.currentInputConnection.setComposingText(c2, 1)
                        } else {
                            if (c2 != null) imeService.currentInputConnection.setComposingText(c2, 1)
                        }
                    }
                    Constants.IME_KR_FLAG_DAN -> {
                        val (c1, c2) = HanguelDanmoum.composeChar(
                            (view as Button).text!!.single(),
                            System.currentTimeMillis()
                        )
                        if (c1 != null) {
                            imeService.currentInputConnection.commitText(c1, 1)
                            if (c2 != null) imeService.currentInputConnection.setComposingText(c2, 1)
                        } else {
                            if (c2 != null) imeService.currentInputConnection.setComposingText(
                                c2,
                                1
                            )
                        }
                    }
                }
            }
            else -> {
                clearComposingStep(imeService)
                imeService.currentInputConnection.commitText((view as Button).text.toString(), 1)
                if (viewModel.inputTypeState.value!! == InputTypeState.EN_UPPER) viewModel.setInputTypeState(InputTypeState.EN_LOWER)
            }
        }
        if (viewModel.kbHasVibration.value!!) vibrate(vibratorService = vibrator, intensity = viewModel.kbVibrationIntensity.value!!.toLong() + 100L)
    }

    fun inputSpecialKey(view: View, vibrator: Vibrator) {
        clearComposingStep(imeService)
        vibrate(vibratorService = vibrator, intensity = viewModel.kbVibrationIntensity.value!!.toLong() + 100L)
        when {
            viewModel.inputTypeState.value!! in listOf(
                InputTypeState.KR_NORMAL,
                InputTypeState.KR_SHIFT,
                InputTypeState.EN_UPPER,
                InputTypeState.EN_LOWER,
                InputTypeState.EN_BOLD_UPPER,
                InputTypeState.BOILERPLATE,
                InputTypeState.CURSOR,
                InputTypeState.NUMBER,
                InputTypeState.EMOJI
            ) -> {
                imeService.currentInputConnection.commitText(" ", 1)
            }
            viewModel.inputTypeState.value!! in listOf(
                InputTypeState.SPECIAL_FIRST,
                InputTypeState.SPECIAL_SECOND
            ) -> {
                if (viewModel.kbHasTypeChange.value!!) viewModel.setInputTypeState(InputTypeState.KR_NORMAL)
                imeService.currentInputConnection.commitText(" ", 1)
            }
            else -> {
                if (view is Button) imeService.currentInputConnection.commitText(view.text[0].toString(), 1)
            }
        }
    }

    fun inputKeyLong(view: View, keyType: KeyType, vibrator: Vibrator): Boolean {
        val crit =
            when (keyType) {
                KeyType.Special -> viewModel.kbSpecialKeyOnLongClickFunction.value!!
                KeyType.Enter -> viewModel.kbEnterKeyOnLongClickFunction.value!!
                else -> -1
            }

        when (crit) {
            1 -> viewModel.setInputTypeState(InputTypeState.BOILERPLATE)
            2 -> viewModel.setInputTypeState(InputTypeState.CURSOR)
            3 -> viewModel.setInputTypeState(InputTypeState.NUMBER)
            4 -> viewModel.setInputTypeState(InputTypeState.EMOJI)
            else -> {
                clearComposingStep(imeService)
                imeService.currentInputConnection.commitText((view as Button).text[1].toString(), 1)
                return true
            }
        }
        vibrate(vibrator, viewModel.kbVibrationIntensity.value!!.toLong() + 100L)
        return true
    }

    fun deleteChar(vibrator: Vibrator) {
        val selectedText =
            imeService.currentInputConnection.getSelectedText(InputConnection.GET_TEXT_WITH_STYLES)
        if (TextUtils.isEmpty(selectedText)) {
            val c = imeService.currentInputConnection.getTextBeforeCursor(
                1,
                InputConnection.GET_TEXT_WITH_STYLES
            )
            // 이모지 삭제 / 일반 삭제 나눔
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && c != null && c.isNotEmpty() && Character.isSurrogate(c[0])) {
                val deleteKeyEvent = KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL)
                imeService.currentInputConnection.sendKeyEvent(deleteKeyEvent)
            } else {
                if (viewModel.inputTypeState.value!! in listOf(InputTypeState.KR_NORMAL, InputTypeState.KR_SHIFT)) {
                    when (viewModel.kbKrImeMode.value!!) {
                        Constants.IME_KR_FLAG_QWERTY -> {
                            val (c1, c2) = HanguelQWERTY.delete()
                            if (c1 == null) {
                                if (c2 == null) {
                                    clearComposingStep(imeService)
                                    imeService.currentInputConnection.deleteSurroundingText(1, 0)
                                } else {
                                    imeService.currentInputConnection.setComposingText(c2, 1)
                                }
                            }
                            if (viewModel.inputTypeState.value!! == InputTypeState.KR_SHIFT) viewModel.setInputTypeState(InputTypeState.KR_NORMAL)
                        }
                        Constants.IME_KR_FLAG_CHUN, Constants.IME_KR_FLAG_CHUN_AMBI -> {
                            val (c1, c2) = HanguelChunjiin.delete(System.currentTimeMillis())
                            if (c1 == null) {
                                if (c2 == null) {
                                    clearComposingStep(imeService)
                                    imeService.currentInputConnection.deleteSurroundingText(1, 0)
                                } else {
                                    imeService.currentInputConnection.setComposingText(c2, 1)
                                }
                            }
                        }
                        Constants.IME_KR_FLAG_NARAT -> {
                            val (c1, c2) = HanguelNARATGUL.delete()
                            if (c1 == null) {
                                if (c2 == null) {
                                    clearComposingStep(imeService)
                                    imeService.currentInputConnection.deleteSurroundingText(1, 0)
                                } else {
                                    imeService.currentInputConnection.setComposingText(c2, 1)
                                }
                            }
                        }
                        Constants.IME_KR_FLAG_DAN -> {
                            val (c1, c2) = HanguelDanmoum.delete(inputTime = System.currentTimeMillis())
                            if (c1 == null) {
                                if (c2 == null) {
                                    clearComposingStep(imeService)
                                    imeService.currentInputConnection.deleteSurroundingText(1, 0)
                                } else {
                                    imeService.currentInputConnection.setComposingText(c2, 1)
                                }
                            }
                        }
                        else -> HanguelQWERTY.delete()
                    }
                } else {
                    clearComposingStep(imeService)
                    imeService.currentInputConnection.deleteSurroundingText(1, 0)
                }
            }
        } else {
            clearComposingStep(imeService)
            imeService.currentInputConnection.finishComposingText()
            imeService.currentInputConnection.commitText("", 0)
        }
        if (viewModel.kbHasVibration.value!!) vibrate(vibratorService = vibrator, intensity = viewModel.kbVibrationIntensity.value!!.toLong() + 100L)
    }

    fun deletePrevChar(vibrator: Vibrator) {
        val selectedText =
            imeService.currentInputConnection.getSelectedText(InputConnection.GET_TEXT_WITH_STYLES)
        if (TextUtils.isEmpty(selectedText)) {
            clearComposingStep(imeService)
            imeService.currentInputConnection.deleteSurroundingText(0, 1)
        } else {
            clearComposingStep(imeService)
            imeService.currentInputConnection.finishComposingText()
            imeService.currentInputConnection.commitText("", 1)
        }
        if (viewModel.kbHasVibration.value!!) vibrate(vibratorService = vibrator, intensity = viewModel.kbVibrationIntensity.value!!.toLong() + 100L)
    }

    fun inputEnter(vibrator: Vibrator) {
        clearComposingStep(imeService)
        imeService.currentInputConnection.finishComposingText()
        val eventTime = SystemClock.uptimeMillis()
        when (decToHex(imeService.currentInputEditorInfo.imeOptions).last()) {
            '2' -> imeService.currentInputConnection.performEditorAction(EditorInfo.IME_ACTION_GO)
            '3' -> imeService.currentInputConnection.performEditorAction(EditorInfo.IME_ACTION_SEARCH)
            '5' -> imeService.currentInputConnection.performEditorAction(EditorInfo.IME_ACTION_NEXT)
            '7' -> imeService.currentInputConnection.performEditorAction(EditorInfo.IME_ACTION_PREVIOUS)
            else -> {
                imeService.currentInputConnection.sendKeyEvent(
                    KeyEvent(
                        eventTime,
                        eventTime,
                        KeyEvent.ACTION_DOWN,
                        KeyEvent.KEYCODE_ENTER,
                        0,
                        0,
                        0,
                        0,
                        KeyEvent.FLAG_SOFT_KEYBOARD
                    )
                )
                imeService.currentInputConnection.sendKeyEvent(
                    KeyEvent(
                        SystemClock.uptimeMillis(),
                        eventTime,
                        KeyEvent.ACTION_UP,
                        KeyEvent.KEYCODE_ENTER,
                        0,
                        0,
                        0,
                        0,
                        KeyEvent.FLAG_SOFT_KEYBOARD
                    )
                )
            }
        }
        if (viewModel.kbHasVibration.value!!) vibrate(vibratorService = vibrator, intensity = viewModel.kbVibrationIntensity.value!!.toLong() + 100L)
    }

    fun moveCursorToHead() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (imeService.currentInputConnection.requestCursorUpdates(InputConnection.CURSOR_UPDATE_IMMEDIATE)) {
                clearComposingStep(imeService)
                if (viewModel.isSelectingText) {
                    imeService.currentInputConnection.sendKeyEvent(
                        KeyEvent(
                            KeyEvent.ACTION_DOWN,
                            KeyEvent.KEYCODE_SHIFT_LEFT
                        )
                    )
                    imeService.currentInputConnection.setSelection(0, viewModel.savedCursorPosition)
                    imeService.currentInputConnection.sendKeyEvent(
                        KeyEvent(
                            KeyEvent.ACTION_UP,
                            KeyEvent.KEYCODE_SHIFT_LEFT
                        )
                    )
                } else {
                    imeService.currentInputConnection.setSelection(0, 0)
                }
            }
        }
    }

    fun moveCursorToTail() {
        val wholeText =
            imeService.currentInputConnection.getExtractedText(
                ExtractedTextRequest(),
                0
            ).text.length

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (imeService.currentInputConnection.requestCursorUpdates(InputConnection.CURSOR_UPDATE_IMMEDIATE)) {
                clearComposingStep(imeService)
                if (viewModel.isSelectingText) {
                    imeService.currentInputConnection.sendKeyEvent(
                        KeyEvent(
                            KeyEvent.ACTION_DOWN,
                            KeyEvent.KEYCODE_SHIFT_LEFT
                        )
                    )
                    imeService.currentInputConnection.setSelection(viewModel.savedCursorPosition, wholeText)
                    imeService.currentInputConnection.sendKeyEvent(
                        KeyEvent(
                            KeyEvent.ACTION_UP,
                            KeyEvent.KEYCODE_SHIFT_LEFT
                        )
                    )
                } else {
                    imeService.currentInputConnection.setSelection(wholeText, wholeText)
                }
            }
        }
    }

    fun moveCursorLeft() {
        val currentCursorPositionStart =
            imeService.currentInputConnection.getExtractedText(ExtractedTextRequest(), 0).selectionStart
        val currentCursorPositionEnd =
            imeService.currentInputConnection.getExtractedText(ExtractedTextRequest(), 0).selectionEnd

        clearComposingStep(imeService)
        if (viewModel.isSelectingText) {
            imeService.currentInputConnection.sendKeyEvent(
                KeyEvent(
                    KeyEvent.ACTION_DOWN,
                    KeyEvent.KEYCODE_SHIFT_LEFT
                )
            )
            if (currentCursorPositionStart != 0 || currentCursorPositionStart >= viewModel.savedCursorPosition) {
                imeService.currentInputConnection.sendKeyEvent(
                    KeyEvent(
                        KeyEvent.ACTION_DOWN,
                        KeyEvent.KEYCODE_DPAD_LEFT
                    )
                )
                imeService.currentInputConnection.sendKeyEvent(
                    KeyEvent(
                        KeyEvent.ACTION_UP,
                        KeyEvent.KEYCODE_DPAD_LEFT
                    )
                )
            } else {
                imeService.currentInputConnection.setSelection(
                    currentCursorPositionStart,
                    currentCursorPositionEnd
                )
            }
            imeService.currentInputConnection.sendKeyEvent(
                KeyEvent(
                    KeyEvent.ACTION_UP,
                    KeyEvent.KEYCODE_SHIFT_LEFT
                )
            )
        } else {
            if (currentCursorPositionStart != 0) {
                imeService.currentInputConnection.sendKeyEvent(
                    KeyEvent(
                        KeyEvent.ACTION_DOWN,
                        KeyEvent.KEYCODE_DPAD_LEFT
                    )
                )
                imeService.currentInputConnection.sendKeyEvent(
                    KeyEvent(
                        KeyEvent.ACTION_UP,
                        KeyEvent.KEYCODE_DPAD_LEFT
                    )
                )
            } else {
                imeService.currentInputConnection.setSelection(0, 0)
            }
        }
    }

    fun moveCursorUp() {
        val currentCursorPositionStart =
            imeService.currentInputConnection.getExtractedText(
                ExtractedTextRequest(),
                0
            ).selectionStart
        val currentCursorPositionEnd =
            imeService.currentInputConnection.getExtractedText(
                ExtractedTextRequest(),
                0
            ).selectionEnd
        clearComposingStep(imeService)
        if (viewModel.isSelectingText) {
            imeService.currentInputConnection.sendKeyEvent(
                KeyEvent(
                    KeyEvent.ACTION_DOWN,
                    KeyEvent.KEYCODE_SHIFT_LEFT
                )
            )
            if (currentCursorPositionStart != 0 || currentCursorPositionStart >= viewModel.savedCursorPosition) {
                imeService.currentInputConnection.sendKeyEvent(
                    KeyEvent(
                        KeyEvent.ACTION_DOWN,
                        KeyEvent.KEYCODE_DPAD_UP
                    )
                )
                imeService.currentInputConnection.sendKeyEvent(
                    KeyEvent(
                        KeyEvent.ACTION_UP,
                        KeyEvent.KEYCODE_DPAD_UP
                    )
                )
            } else {
                imeService.currentInputConnection.setSelection(
                    currentCursorPositionStart,
                    currentCursorPositionEnd
                )
            }
            imeService.currentInputConnection.sendKeyEvent(
                KeyEvent(
                    KeyEvent.ACTION_UP,
                    KeyEvent.KEYCODE_SHIFT_LEFT
                )
            )
        } else {
            if (currentCursorPositionStart != 0) {
                imeService.currentInputConnection.sendKeyEvent(
                    KeyEvent(
                        KeyEvent.ACTION_DOWN,
                        KeyEvent.KEYCODE_DPAD_UP
                    )
                )
                imeService.currentInputConnection.sendKeyEvent(
                    KeyEvent(
                        KeyEvent.ACTION_UP,
                        KeyEvent.KEYCODE_DPAD_UP
                    )
                )
            } else {
                imeService.currentInputConnection.setSelection(0, 0)
            }
        }
    }

    fun moveCursorDown() {
        val currentCursorPositionStart =
            imeService.currentInputConnection.getExtractedText(ExtractedTextRequest(), 0).selectionStart
        val currentCursorPositionEnd =
            imeService.currentInputConnection.getExtractedText(ExtractedTextRequest(), 0).selectionEnd
        val wholeText =
            imeService.currentInputConnection.getExtractedText(ExtractedTextRequest(), 0).text.length

        clearComposingStep(imeService)
        if (viewModel.isSelectingText) {
            imeService.currentInputConnection.sendKeyEvent(
                KeyEvent(
                    KeyEvent.ACTION_DOWN,
                    KeyEvent.KEYCODE_SHIFT_LEFT
                )
            )
            if (currentCursorPositionStart != 0 || currentCursorPositionStart >= viewModel.savedCursorPosition) {
                imeService.currentInputConnection.sendKeyEvent(
                    KeyEvent(
                        KeyEvent.ACTION_DOWN,
                        KeyEvent.KEYCODE_DPAD_DOWN
                    )
                )
                imeService.currentInputConnection.sendKeyEvent(
                    KeyEvent(
                        KeyEvent.ACTION_UP,
                        KeyEvent.KEYCODE_DPAD_DOWN
                    )
                )
            } else {
                imeService.currentInputConnection.setSelection(
                    currentCursorPositionStart,
                    currentCursorPositionEnd
                )
            }
            imeService.currentInputConnection.sendKeyEvent(
                KeyEvent(
                    KeyEvent.ACTION_UP,
                    KeyEvent.KEYCODE_SHIFT_LEFT
                )
            )
        } else {
            if (currentCursorPositionStart != wholeText && viewModel.savedCursorPosition != -1) {
                imeService.currentInputConnection.sendKeyEvent(
                    KeyEvent(
                        KeyEvent.ACTION_DOWN,
                        KeyEvent.KEYCODE_DPAD_DOWN
                    )
                )
                imeService.currentInputConnection.sendKeyEvent(
                    KeyEvent(
                        KeyEvent.ACTION_UP,
                        KeyEvent.KEYCODE_DPAD_DOWN
                    )
                )
            } else {
                imeService.currentInputConnection.setSelection(wholeText, wholeText)
            }
        }
    }

    fun moveCursorRight() {
        val currentCursorPositionStart =
            imeService.currentInputConnection.getExtractedText(
                ExtractedTextRequest(),
                0
            ).selectionStart
        val currentCursorPositionEnd =
            imeService.currentInputConnection.getExtractedText(
                ExtractedTextRequest(),
                0
            ).selectionEnd
        val wholeText =
            imeService.currentInputConnection.getExtractedText(
                ExtractedTextRequest(),
                0
            ).text.length
        clearComposingStep(imeService)
        if (viewModel.isSelectingText) {
            imeService.currentInputConnection.sendKeyEvent(
                KeyEvent(
                    KeyEvent.ACTION_DOWN,
                    KeyEvent.KEYCODE_SHIFT_LEFT
                )
            )
            if (currentCursorPositionEnd != wholeText && viewModel.savedCursorPosition <= currentCursorPositionStart) {
                imeService.currentInputConnection.sendKeyEvent(
                    KeyEvent(
                        KeyEvent.ACTION_DOWN,
                        KeyEvent.KEYCODE_DPAD_RIGHT
                    )
                )
                imeService.currentInputConnection.sendKeyEvent(
                    KeyEvent(
                        KeyEvent.ACTION_UP,
                        KeyEvent.KEYCODE_DPAD_RIGHT
                    )
                )
            } else if (viewModel.savedCursorPosition > currentCursorPositionStart) {
                imeService.currentInputConnection.setSelection(
                    currentCursorPositionStart + 1,
                    viewModel.savedCursorPosition
                )
            } else {
                imeService.currentInputConnection.setSelection(
                    currentCursorPositionStart,
                    currentCursorPositionEnd
                )
            }
            imeService.currentInputConnection.sendKeyEvent(
                KeyEvent(
                    KeyEvent.ACTION_UP,
                    KeyEvent.KEYCODE_SHIFT_LEFT
                )
            )
        } else {
            if (currentCursorPositionStart != wholeText) {
                imeService.currentInputConnection.sendKeyEvent(
                    KeyEvent(
                        KeyEvent.ACTION_DOWN,
                        KeyEvent.KEYCODE_DPAD_RIGHT
                    )
                )
                imeService.currentInputConnection.sendKeyEvent(
                    KeyEvent(
                        KeyEvent.ACTION_UP,
                        KeyEvent.KEYCODE_DPAD_RIGHT
                    )
                )
            } else {
                imeService.currentInputConnection.setSelection(wholeText, wholeText)
            }
        }
    }

    fun clearComposingStep(imeService: InputMethodService) {
        imeService.currentInputConnection.finishComposingText()

        HanguelQWERTY.initChar()
        HanguelQWERTY.initState()
        HanguelQWERTY.composeResult()
        HanguelQWERTY.initResult()

        HanguelChunjiin.initChar()
        HanguelChunjiin.initState()
        HanguelChunjiin.composeResult()
        HanguelChunjiin.initResult()

        HanguelNARATGUL.initChar()
        HanguelNARATGUL.initState()
        HanguelNARATGUL.composeResult()
        HanguelNARATGUL.initResult()

        HanguelDanmoum.initChar()
        HanguelDanmoum.initState()
        HanguelDanmoum.composeResult()
        HanguelDanmoum.initResult()
    }

    fun jumpToBoilerplateEditor(index: Int): Boolean {
        val intent = Intent(imeService.applicationContext, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.putExtra(MainActivity.INTENT_BOILERPLATE_TAB, index)
        imeService.applicationContext.startActivity(intent)
        return false
    }

    fun inputBoilerplateText(button: View, vibrator: Vibrator) {
        clearComposingStep(imeService)
        imeService.currentInputConnection.commitText((button as Button).text, 1)

        if (viewModel.kbHasVibration.value!!) vibrate(vibratorService = vibrator, intensity = viewModel.kbVibrationIntensity.value!!.toLong())
    }

    enum class KeyType {
        Enter,
        Special,
        Dot
    }
}