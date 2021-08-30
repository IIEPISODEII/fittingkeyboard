package com.sb.fittingKeyboard.service

import android.text.TextUtils
import android.view.inputmethod.InputConnection
import android.widget.Button

class QWERTYEnNormalKBView(private val ic: InputConnection) {

    fun inputChar(button: Button) {
        ic.commitText(button.text.toString(), 1)
    }

    fun deleteChar() {
        val selectedText = ic.getSelectedText(InputConnection.GET_TEXT_WITH_STYLES)

        if (TextUtils.isEmpty(selectedText)) {
            ic.deleteSurroundingText(1, 0)
        } else {
            ic.finishComposingText()
            ic.commitText("", 1)
        }
    }
}