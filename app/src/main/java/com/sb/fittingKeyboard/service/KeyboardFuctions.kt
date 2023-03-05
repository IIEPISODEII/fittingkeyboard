package com.sb.fittingKeyboard.service

import android.content.Context
import android.inputmethodservice.InputMethodService
import android.os.Build
import android.os.SystemClock
import android.text.TextUtils
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.ExtractedTextRequest
import android.view.inputmethod.InputConnection
import android.widget.Button
import android.widget.Toast
import com.sb.fittingKeyboard.R
import com.sb.fittingKeyboard.koreanAutomata.HanguelChunjiin
import com.sb.fittingKeyboard.koreanAutomata.HanguelDanmoum
import com.sb.fittingKeyboard.koreanAutomata.HanguelNARATGUL
import com.sb.fittingKeyboard.koreanAutomata.HanguelQWERTY
import com.sb.fittingKeyboard.service.util.KeyboardUtil
import com.sb.fittingKeyboard.service.util.KeyboardUtil.Companion.decToHex

class KeyboardInputFuctions {
    companion object {
        fun inputChar(
            mIMEService: InputMethodService,
            clearComposing: () -> Unit,
            button: View,
            mode: Int,
            krIME: Int,
            myKeyboardVibration: Boolean,
            vibrateByButton: () -> Unit,
            changeMode3: (Int) -> Unit,
            changeMode2: (Int) -> Unit
        ) {
            if (mIMEService.currentInputConnection == null) return
            val cursorCS = mIMEService.currentInputConnection.getSelectedText(InputConnection.GET_TEXT_WITH_STYLES)
            if (cursorCS != null && cursorCS.isNotEmpty()) {
                clearComposing() // 선택 중인 글자가 있으면 초기화
            }
            when (mode) {
                3, 4 -> {
                    when (krIME) {
                        KeyboardUtil.QWERTY -> {
                            val (c1, c2) = HanguelQWERTY.composeChar((button as Button).text!!.single())
                            if (c1 != null) {
                                mIMEService.currentInputConnection.commitText(c1, 1)
                                if (c2 != null) mIMEService.currentInputConnection.setComposingText(c2, 1)
                            } else {
                                if (c2 != null) mIMEService.currentInputConnection.setComposingText(c2, 1)
                            }
                            if (mode == 4) changeMode3(3)
                        }
                        KeyboardUtil.CHUN, KeyboardUtil.CHUN_AMBI -> {
                            var c1: String?
                            var c2: String?
                            when (button.id) {
                                in arrayOf(R.id.btnChunK, R.id.btnChunKa) -> {
                                    val chars = HanguelChunjiin.composeChar('ᆞ', System.currentTimeMillis())
                                    c1 = chars.commited
                                    c2 = chars.composing
                                }
                                else -> {
                                    val chars = HanguelChunjiin.composeChar((button as Button).text[0], System.currentTimeMillis())
                                    c1 = chars.commited
                                    c2 = chars.composing
                                }
                            }

                            if (c1 != null) {
                                println("nullChar: ${"\u0000" in c1}")
                                if ("\u0000" in c1) c1 = c1.filterNot { it == '\u0000'}
                                mIMEService.currentInputConnection.commitText(c1, 1)
                                if (c2 != null) {
                                    if ("\u0000" in c2) c2 = c2.filterNot { it == '\u0000'}
                                    mIMEService.currentInputConnection.setComposingText(c2, 1)
                                }
                            } else {
                                if (c2 != null) {
                                    if ("\u0000" in c2) c2 = c2.filterNot { it == '\u0000'}
                                    mIMEService.currentInputConnection.setComposingText(c2, 1)
                                }
                            }
                        }
                        KeyboardUtil.NARAT -> {
                            var c1: String? = null
                            var c2: String? = null

                            if (button.id == R.id.btnNaADD) {
                                val chars = HanguelNARATGUL.composeChar('ᆞ')
                                c1 = chars.commited
                                c2 = chars.composing
                            } else if (button.id == R.id.btnNaSHIFT) {
                                val chars = HanguelNARATGUL.composeChar('ᆢ')
                                c1 = chars.commited
                                c2 = chars.composing
                            } else {
                                val chars = HanguelNARATGUL.composeChar((button as Button).text[0])
                                c1 = chars.commited
                                c2 = chars.composing
                            }

                            if (c1 != null) {
                                mIMEService.currentInputConnection.commitText(c1, 1)
                                if (c2 != null) mIMEService.currentInputConnection.setComposingText(c2, 1)
                            } else {
                                if (c2 != null) mIMEService.currentInputConnection.setComposingText(c2, 1)
                            }
                        }
                        KeyboardUtil.DAN -> {
                            val (c1, c2) = HanguelDanmoum.composeChar(
                                (button as Button).text!!.single(),
                                System.currentTimeMillis()
                            )
                            if (c1 != null) {
                                mIMEService.currentInputConnection.commitText(c1, 1)
                                if (c2 != null) mIMEService.currentInputConnection.setComposingText(c2, 1)
                            } else {
                                if (c2 != null) mIMEService.currentInputConnection.setComposingText(c2, 1)
                            }
                        }
                    }
                }
                else -> {
                    clearComposing()
                    mIMEService.currentInputConnection.commitText((button as Button).text.toString(), 1)
                    if (mode == 1) {
                        changeMode2(2)
                    }
                }
            }
            if (myKeyboardVibration) vibrateByButton()
        }

        fun inputSpecialButton(
            mIMEService: InputMethodService,
            clearComposing: () -> Unit,
            button: View,
            autoModeChange: Boolean,
            changeMode3: (Int) -> Unit,
            myKeyboardVibration: Boolean,
            vibrateByButton: () -> Unit
        ) {
            if (mIMEService.currentInputConnection == null) return
            clearComposing()
            when {
                (button as Button).text in arrayOf(
                    "한글",
                    "english",
                    "English",
                    "SPACE"
                ) -> {
                    mIMEService.currentInputConnection.commitText(" ", 1)
                }
                button.text in arrayOf("특수 1", "특수 2") -> {
                    if (autoModeChange == true) changeMode3(3)
                    mIMEService.currentInputConnection.commitText(" ", 1)
                }
                else -> {
                    mIMEService.currentInputConnection.commitText(button.text[0].toString(), 1)
                }
            }
            if (myKeyboardVibration) vibrateByButton()
        }

        fun inputSpecialLongClicked(
            mIMEService: InputMethodService,
            clearComposing: () -> Unit,
            button: View,
        ): Boolean {
            if (mIMEService.currentInputConnection == null) return false
            clearComposing()
            mIMEService.currentInputConnection.commitText((button as Button).text[1].toString(), 1)
            return true
        }

        fun inputBPStrings(
            mIMEService: InputMethodService,
            clearComposing: () -> Unit,
            button: View,
            myKeyboardVibration: Boolean,
            vibrateByButton: () -> Unit
        ) {
            if (mIMEService.currentInputConnection == null) return
            clearComposing()
            mIMEService.currentInputConnection.commitText((button as Button).text, 1)

            if (myKeyboardVibration) vibrateByButton()
        }

        fun inputEnter(
            mIMEService: InputMethodService,
            clearComposing: () -> Unit,
            myKeyboardVibration: Boolean,
            vibrateByButton: () -> Unit
        ) {
            if (mIMEService.currentInputConnection == null) return
            clearComposing()
            mIMEService.currentInputConnection.finishComposingText()
            val eventTime = SystemClock.uptimeMillis()
            println("엔터키 누름 : 작동옵션 = ${mIMEService.currentInputEditorInfo.imeOptions}  ${decToHex(mIMEService.currentInputEditorInfo.imeOptions)}")
            when (decToHex(mIMEService.currentInputEditorInfo.imeOptions).last()) {
                '2' -> mIMEService.currentInputConnection.performEditorAction(EditorInfo.IME_ACTION_GO)
                '3' -> mIMEService.currentInputConnection.performEditorAction(EditorInfo.IME_ACTION_SEARCH)
                '5' -> mIMEService.currentInputConnection.performEditorAction(EditorInfo.IME_ACTION_NEXT)
                '7' -> mIMEService.currentInputConnection.performEditorAction(EditorInfo.IME_ACTION_PREVIOUS)
                else -> {
                    mIMEService.currentInputConnection.sendKeyEvent(
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
                    mIMEService.currentInputConnection.sendKeyEvent(
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
            if (myKeyboardVibration) vibrateByButton()
        }

        fun inputDelete(
            mIMEService: InputMethodService,
            mode: Int,
            krIME: Int,
            clearComposing: () -> Unit,
            changeMode3: (Int) -> Unit,
            myKeyboardVibration: Boolean,
            vibrateByButton: () -> Unit
        ) {
            if (mIMEService.currentInputConnection == null) return
            val selectedText = mIMEService.currentInputConnection.getSelectedText(InputConnection.GET_TEXT_WITH_STYLES)
            if (TextUtils.isEmpty(selectedText)) {
                val c = mIMEService.currentInputConnection.getTextBeforeCursor(1,
                    InputConnection.GET_TEXT_WITH_STYLES
                )
                // 이모지 삭제 / 일반 삭제 나눔
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && c!!.isNotEmpty() && Character.isSurrogate(c[0])) {
                    val deleteKeyEvent = KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL)
                    mIMEService.currentInputConnection.sendKeyEvent(deleteKeyEvent)
                } else {
                    if (mode in arrayOf(3, 4)) {
                        when (krIME) {
                            KeyboardUtil.QWERTY -> {
                                val (c1, c2) = HanguelQWERTY.delete()
                                if (c1 == null) {
                                    if (c2 == null) {
                                        clearComposing()
                                        mIMEService.currentInputConnection.deleteSurroundingText(1, 0)
                                    } else {
                                        mIMEService.currentInputConnection.setComposingText(c2, 1)
                                    }
                                }
                                if (mode == 4) changeMode3(3)
                            }
                            KeyboardUtil.CHUN, KeyboardUtil.CHUN_AMBI -> {
                                val (c1, c2) = HanguelChunjiin.delete(System.currentTimeMillis())
                                if (c1 == null) {
                                    if (c2 == null) {
                                        clearComposing()
                                        mIMEService.currentInputConnection.deleteSurroundingText(1, 0)
                                    } else {
                                        mIMEService.currentInputConnection.setComposingText(c2, 1)
                                    }
                                }
                            }
                            KeyboardUtil.NARAT -> {
                                val (c1, c2) = HanguelNARATGUL.delete()
                                if (c1 == null) {
                                    if (c2 == null) {
                                        clearComposing()
                                        mIMEService.currentInputConnection.deleteSurroundingText(1, 0)
                                    } else {
                                        mIMEService.currentInputConnection.setComposingText(c2, 1)
                                    }
                                }
                            }
                            KeyboardUtil.DAN -> {
                                val (c1, c2) = HanguelDanmoum.delete(inputTime = System.currentTimeMillis())
                                if (c1 == null) {
                                    if (c2 == null) {
                                        clearComposing()
                                        mIMEService.currentInputConnection.deleteSurroundingText(1, 0)
                                    } else {
                                        mIMEService.currentInputConnection.setComposingText(c2, 1)
                                    }
                                }
                            }
                            else -> HanguelQWERTY.delete()
                        }
                    } else {
                        clearComposing()
                        mIMEService.currentInputConnection.deleteSurroundingText(1, 0)
                    }
                }
            } else {
                clearComposing()
                mIMEService.currentInputConnection.finishComposingText()
                mIMEService.currentInputConnection.commitText("", 0)
            }
            if (myKeyboardVibration) vibrateByButton()
        }

        fun inputForwardDelete(
            mIMEService: InputMethodService,
            clearComposing: () -> Unit,
            myKeyboardVibration: Boolean,
            vibrateByButton: () -> Unit
        ) {
            if (mIMEService.currentInputConnection == null) return
            val selectedText = mIMEService.currentInputConnection.getSelectedText(InputConnection.GET_TEXT_WITH_STYLES)
            if (TextUtils.isEmpty(selectedText)) {
                clearComposing()
                mIMEService.currentInputConnection.deleteSurroundingText(0, 1)
            } else {
                clearComposing()
                mIMEService.currentInputConnection.finishComposingText()
                mIMEService.currentInputConnection.commitText("", 1)
            }
            if (myKeyboardVibration) vibrateByButton()
        }

        fun selectAllTexts(
            mIMEService: InputMethodService,
            clearComposing: () -> Unit,
            setSavedCursorPositionDefault: () -> Unit,
            context: Context
        ) {
            if (mIMEService.currentInputConnection == null) {
                return
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                if (mIMEService.currentInputConnection.requestCursorUpdates(InputConnection.CURSOR_UPDATE_IMMEDIATE)) {
                    val wholeText =
                        mIMEService.currentInputConnection.getExtractedText(ExtractedTextRequest(), 0).text.length
                    clearComposing()
                    mIMEService.currentInputConnection.setSelection(0, wholeText)
                    setSavedCursorPositionDefault()
                    if (wholeText == 0) Toast.makeText(context, "선택할 문구가 없습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }

        fun moveCursorUp(
            mIMEService: InputMethodService,
            clearComposing: () -> Unit,
            isSelectingMode: Boolean,
            savedCursorPosition: Int
        ) {
            if (mIMEService.currentInputConnection == null) return
            val currentCursorPositionStart =
                mIMEService.currentInputConnection.getExtractedText(ExtractedTextRequest(), 0).selectionStart
            val currentCursorPositionEnd =
                mIMEService.currentInputConnection.getExtractedText(ExtractedTextRequest(), 0).selectionEnd
            clearComposing()
            if (isSelectingMode) {
                mIMEService.currentInputConnection.sendKeyEvent(
                    KeyEvent(
                        KeyEvent.ACTION_DOWN,
                        KeyEvent.KEYCODE_SHIFT_LEFT
                    )
                )
                if (currentCursorPositionStart != 0 || currentCursorPositionStart >= savedCursorPosition) {
                    mIMEService.currentInputConnection.sendKeyEvent(
                        KeyEvent(
                            KeyEvent.ACTION_DOWN,
                            KeyEvent.KEYCODE_DPAD_UP
                        )
                    )
                    mIMEService.currentInputConnection.sendKeyEvent(
                        KeyEvent(
                            KeyEvent.ACTION_UP,
                            KeyEvent.KEYCODE_DPAD_UP
                        )
                    )
                } else {
                    mIMEService.currentInputConnection.setSelection(
                        currentCursorPositionStart,
                        currentCursorPositionEnd
                    )
                }
                mIMEService.currentInputConnection.sendKeyEvent(
                    KeyEvent(
                        KeyEvent.ACTION_UP,
                        KeyEvent.KEYCODE_SHIFT_LEFT
                    )
                )
            } else {
                if (currentCursorPositionStart != 0) {
                    mIMEService.currentInputConnection.sendKeyEvent(
                        KeyEvent(
                            KeyEvent.ACTION_DOWN,
                            KeyEvent.KEYCODE_DPAD_UP
                        )
                    )
                    mIMEService.currentInputConnection.sendKeyEvent(
                        KeyEvent(
                            KeyEvent.ACTION_UP,
                            KeyEvent.KEYCODE_DPAD_UP
                        )
                    )
                } else {
                    mIMEService.currentInputConnection.setSelection(0, 0)
                }
            }
        }

        fun moveCursorDown(
            mIMEService: InputMethodService,
            clearComposing: () -> Unit,
            isSelectingMode: Boolean,
            savedCursorPosition: Int
        ) {
            if (mIMEService.currentInputConnection == null) return
            val currentCursorPositionStart =
                mIMEService.currentInputConnection.getExtractedText(ExtractedTextRequest(), 0).selectionStart
            val currentCursorPositionEnd =
                mIMEService.currentInputConnection.getExtractedText(ExtractedTextRequest(), 0).selectionEnd
            val wholeText =
                mIMEService.currentInputConnection.getExtractedText(ExtractedTextRequest(), 0).text.length
            clearComposing()
            if (isSelectingMode) {
                mIMEService.currentInputConnection.sendKeyEvent(
                    KeyEvent(
                        KeyEvent.ACTION_DOWN,
                        KeyEvent.KEYCODE_SHIFT_LEFT
                    )
                )
                if (currentCursorPositionStart != 0 || currentCursorPositionStart >= savedCursorPosition) {
                    mIMEService.currentInputConnection.sendKeyEvent(
                        KeyEvent(
                            KeyEvent.ACTION_DOWN,
                            KeyEvent.KEYCODE_DPAD_DOWN
                        )
                    )
                    mIMEService.currentInputConnection.sendKeyEvent(
                        KeyEvent(
                            KeyEvent.ACTION_UP,
                            KeyEvent.KEYCODE_DPAD_DOWN
                        )
                    )
                } else {
                    mIMEService.currentInputConnection.setSelection(
                        currentCursorPositionStart,
                        currentCursorPositionEnd
                    )
                }
                mIMEService.currentInputConnection.sendKeyEvent(
                    KeyEvent(
                        KeyEvent.ACTION_UP,
                        KeyEvent.KEYCODE_SHIFT_LEFT
                    )
                )
            } else {
                if (currentCursorPositionStart != wholeText && savedCursorPosition != -1) {
                    mIMEService.currentInputConnection.sendKeyEvent(
                        KeyEvent(
                            KeyEvent.ACTION_DOWN,
                            KeyEvent.KEYCODE_DPAD_DOWN
                        )
                    )
                    mIMEService.currentInputConnection.sendKeyEvent(
                        KeyEvent(
                            KeyEvent.ACTION_UP,
                            KeyEvent.KEYCODE_DPAD_DOWN
                        )
                    )
                } else {
                    mIMEService.currentInputConnection.setSelection(wholeText, wholeText)
                }
            }
        }

        fun moveCursorLeft(
            mIMEService: InputMethodService,
            clearComposing: () -> Unit,
            isSelectingMode: Boolean,
            savedCursorPosition: Int
        ) {
            if (mIMEService.currentInputConnection == null) return
            val currentCursorPositionStart =
                mIMEService.currentInputConnection.getExtractedText(ExtractedTextRequest(), 0).selectionStart
            val currentCursorPositionEnd =
                mIMEService.currentInputConnection.getExtractedText(ExtractedTextRequest(), 0).selectionEnd
            clearComposing()
            if (isSelectingMode) {
                mIMEService.currentInputConnection.sendKeyEvent(
                    KeyEvent(
                        KeyEvent.ACTION_DOWN,
                        KeyEvent.KEYCODE_SHIFT_LEFT
                    )
                )
                if (currentCursorPositionStart != 0 || currentCursorPositionStart >= savedCursorPosition) {
                    mIMEService.currentInputConnection.sendKeyEvent(
                        KeyEvent(
                            KeyEvent.ACTION_DOWN,
                            KeyEvent.KEYCODE_DPAD_LEFT
                        )
                    )
                    mIMEService.currentInputConnection.sendKeyEvent(
                        KeyEvent(
                            KeyEvent.ACTION_UP,
                            KeyEvent.KEYCODE_DPAD_LEFT
                        )
                    )
                } else {
                    mIMEService.currentInputConnection.setSelection(
                        currentCursorPositionStart,
                        currentCursorPositionEnd
                    )
                }
                mIMEService.currentInputConnection.sendKeyEvent(
                    KeyEvent(
                        KeyEvent.ACTION_UP,
                        KeyEvent.KEYCODE_SHIFT_LEFT
                    )
                )
            } else {
                if (currentCursorPositionStart != 0) {
                    mIMEService.currentInputConnection.sendKeyEvent(
                        KeyEvent(
                            KeyEvent.ACTION_DOWN,
                            KeyEvent.KEYCODE_DPAD_LEFT
                        )
                    )
                    mIMEService.currentInputConnection.sendKeyEvent(
                        KeyEvent(
                            KeyEvent.ACTION_UP,
                            KeyEvent.KEYCODE_DPAD_LEFT
                        )
                    )
                } else {
                    mIMEService.currentInputConnection.setSelection(0, 0)
                }
            }
        }

        fun moveCursorRight(
            mIMEService: InputMethodService,
            clearComposing: () -> Unit,
            isSelectingMode: Boolean,
            savedCursorPosition: Int
        ) {
            if (mIMEService.currentInputConnection == null) return
            val currentCursorPositionStart =
                mIMEService.currentInputConnection.getExtractedText(ExtractedTextRequest(), 0).selectionStart
            val currentCursorPositionEnd =
                mIMEService.currentInputConnection.getExtractedText(ExtractedTextRequest(), 0).selectionEnd
            val wholeText =
                mIMEService.currentInputConnection.getExtractedText(ExtractedTextRequest(), 0).text.length
            clearComposing()
            if (isSelectingMode) {
                mIMEService.currentInputConnection.sendKeyEvent(
                    KeyEvent(
                        KeyEvent.ACTION_DOWN,
                        KeyEvent.KEYCODE_SHIFT_LEFT
                    )
                )
                if (currentCursorPositionEnd != wholeText && savedCursorPosition <= currentCursorPositionStart) {
                    mIMEService.currentInputConnection.sendKeyEvent(
                        KeyEvent(
                            KeyEvent.ACTION_DOWN,
                            KeyEvent.KEYCODE_DPAD_RIGHT
                        )
                    )
                    mIMEService.currentInputConnection.sendKeyEvent(
                        KeyEvent(
                            KeyEvent.ACTION_UP,
                            KeyEvent.KEYCODE_DPAD_RIGHT
                        )
                    )
                } else if (savedCursorPosition > currentCursorPositionStart) {
                    mIMEService.currentInputConnection.setSelection(
                        currentCursorPositionStart + 1,
                        savedCursorPosition
                    )
                } else {
                    mIMEService.currentInputConnection.setSelection(
                        currentCursorPositionStart,
                        currentCursorPositionEnd
                    )
                }
                mIMEService.currentInputConnection.sendKeyEvent(
                    KeyEvent(
                        KeyEvent.ACTION_UP,
                        KeyEvent.KEYCODE_SHIFT_LEFT
                    )
                )
            } else {
                if (currentCursorPositionStart != wholeText) {
                    mIMEService.currentInputConnection.sendKeyEvent(
                        KeyEvent(
                            KeyEvent.ACTION_DOWN,
                            KeyEvent.KEYCODE_DPAD_RIGHT
                        )
                    )
                    mIMEService.currentInputConnection.sendKeyEvent(
                        KeyEvent(
                            KeyEvent.ACTION_UP,
                            KeyEvent.KEYCODE_DPAD_RIGHT
                        )
                    )
                } else {
                    mIMEService.currentInputConnection.setSelection(wholeText, wholeText)
                }
            }
        }

        fun moveCursorFirst(
            mIMEService: InputMethodService,
            clearComposing: () -> Unit,
            isSelectingMode: Boolean,
            savedCursorPosition: Int
        ) {
            if (mIMEService.currentInputConnection == null) return
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                if (mIMEService.currentInputConnection.requestCursorUpdates(InputConnection.CURSOR_UPDATE_IMMEDIATE)) {
                    clearComposing()
                    if (isSelectingMode) {
                        mIMEService.currentInputConnection.sendKeyEvent(
                            KeyEvent(
                                KeyEvent.ACTION_DOWN,
                                KeyEvent.KEYCODE_SHIFT_LEFT
                            )
                        )
                        mIMEService.currentInputConnection.setSelection(0, savedCursorPosition)
                        mIMEService.currentInputConnection.sendKeyEvent(
                            KeyEvent(
                                KeyEvent.ACTION_UP,
                                KeyEvent.KEYCODE_SHIFT_LEFT
                            )
                        )
                    } else {
                        mIMEService.currentInputConnection.setSelection(0, 0)
                    }
                }
            }
        }

        fun moveCursorLast(
            mIMEService: InputMethodService,
            clearComposing: () -> Unit,
            isSelectingMode: Boolean,
            savedCursorPosition: Int
        ) {
            val wholeText =
                mIMEService.currentInputConnection.getExtractedText(ExtractedTextRequest(), 0).text.length
            if (mIMEService.currentInputConnection == null) return
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                if (mIMEService.currentInputConnection.requestCursorUpdates(InputConnection.CURSOR_UPDATE_IMMEDIATE)) {
                    clearComposing()
                    if (isSelectingMode) {
                        mIMEService.currentInputConnection.sendKeyEvent(
                            KeyEvent(
                                KeyEvent.ACTION_DOWN,
                                KeyEvent.KEYCODE_SHIFT_LEFT
                            )
                        )
                        mIMEService.currentInputConnection.setSelection(savedCursorPosition, wholeText)
                        mIMEService.currentInputConnection.sendKeyEvent(
                            KeyEvent(
                                KeyEvent.ACTION_UP,
                                KeyEvent.KEYCODE_SHIFT_LEFT
                            )
                        )
                    } else {
                        mIMEService.currentInputConnection.setSelection(wholeText, wholeText)
                    }
                }
            }
        }

        fun copyText(
            mIMEService: InputMethodService,
            clearComposing: () -> Unit,
            switchSelectingMode: (Boolean) -> Unit,
            context: Context
        ) {
            if (mIMEService.currentInputConnection == null) return
            switchSelectingMode(false)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                if (mIMEService.currentInputConnection.requestCursorUpdates(InputConnection.CURSOR_UPDATE_IMMEDIATE)) {
                    clearComposing()
                    if (mIMEService.currentInputConnection.getSelectedText(InputConnection.GET_TEXT_WITH_STYLES) == null) {
                        Toast.makeText(
                            context,
                            "문구를 복사하시려면\n문구를 먼저 선택해주세요.",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        mIMEService.currentInputConnection.performContextMenuAction(android.R.id.copy)
                    }
                }
            }
        }

        fun cutText(
            mIMEService: InputMethodService,
            clearComposing: () -> Unit,
            switchSelectingMode: (Boolean) -> Unit,
            context: Context
        ) {
            if (mIMEService.currentInputConnection == null) return
            switchSelectingMode(false)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                if (mIMEService.currentInputConnection.requestCursorUpdates(InputConnection.CURSOR_UPDATE_IMMEDIATE)) {
                    clearComposing()
                    if (mIMEService.currentInputConnection.getSelectedText(InputConnection.GET_TEXT_WITH_STYLES) == null) {
                        Toast.makeText(
                            context,
                            "문구를 잘라내시려면\n문구를 먼저 선택해주세요.",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        mIMEService.currentInputConnection.performContextMenuAction(android.R.id.cut)
                    }
                }
            }
        }

        fun pasteText(
            mIMEService: InputMethodService,
            clearComposing: () -> Unit,
            switchSelectingMode: (Boolean) -> Unit
        ) {
            if (mIMEService.currentInputConnection == null) return
            switchSelectingMode(false)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                if (mIMEService.currentInputConnection.requestCursorUpdates(InputConnection.CURSOR_UPDATE_IMMEDIATE)) {
                    clearComposing()
                    mIMEService.currentInputConnection.performContextMenuAction(android.R.id.paste)
                }
            }
        }
    }
}