package com.sb.fittingKeyboard.service

import android.text.TextUtils
import android.view.inputmethod.InputConnection
import android.widget.Button
import com.sb.fittingKeyboard.koreanAutomata.HanguelQWERTY

class QWERTYKrNormalKBView(private val ic: InputConnection) {
    fun inputChar(button: Button) {
        val (c1, c2) = HanguelQWERTY.composeChar(button.text!!.single())
        if (c1 != null) ic.commitText(c1, 1)
        if (c2 != null) ic.setComposingText(c2, 1)
    }

    fun deleteChar() {
        val selectedText = ic.getSelectedText(InputConnection.GET_TEXT_WITH_STYLES)

        if (TextUtils.isEmpty(selectedText)) {
            val (c1, c2) = HanguelQWERTY.delete()
            if (c1 != null) {
                ic.commitText(c1, 1)
                if (c2 != null) ic.setComposingText(c2, 1)
            } else {
                if (c2 != null) ic.setComposingText(c2, 1)
                else {
                    ic.finishComposingText()
                    ic.deleteSurroundingText(1, 0)
                }
            }
        } else {
            ic.finishComposingText()
            HanguelQWERTY.run {
                composedResult = HanguelQWERTY.nullChar
                firstChar = HanguelQWERTY.nullChar
                middleChar = HanguelQWERTY.nullChar
                finalChar = HanguelQWERTY.nullChar
                firstSubChar = HanguelQWERTY.nullChar
                middleSubChar = HanguelQWERTY.nullChar
                finalSubChar = HanguelQWERTY.nullChar
                state = 0
            }
            ic.commitText("", 1)
        }
    }
}