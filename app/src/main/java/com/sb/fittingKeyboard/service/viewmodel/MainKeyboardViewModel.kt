package com.sb.fittingKeyboard.service.viewmodel

import android.content.Context
import android.os.Build
import android.view.View
import android.view.inputmethod.InputConnection
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sb.fittingKeyboard.service.util.KeyboardUtil.Companion.KEYBOARD_BOTTOM_MARGIN
import com.sb.fittingKeyboard.service.util.KeyboardUtil.Companion.KEYBOARD_HEIGHT
import com.sb.fittingKeyboard.service.util.KeyboardUtil.Companion.KEYBOARD_TOGGLE_NUMBER
import com.sb.fittingKeyboard.service.viewLivedata
import kotlinx.android.synthetic.main.key_layout_normal.view.*

class MainKeyboardViewModel(context: Context): ViewModel() {
    companion object {
        var keyboardMode = 0
        var preKeyboardMode = 0
    }
    val keyboardSettingLibrary = context.getSharedPreferences("keyboardSetting", Context.MODE_PRIVATE)
    var keyboardHeight = keyboardSettingLibrary.viewLivedata(
        KEYBOARD_TOGGLE_NUMBER,
        KEYBOARD_HEIGHT,
        KEYBOARD_BOTTOM_MARGIN,
        true,
        25,
        0
    )



    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun copyText(currInputConnection: InputConnection?, context: Context) {
        if (currInputConnection == null) return
        if (currInputConnection.requestCursorUpdates(InputConnection.CURSOR_UPDATE_IMMEDIATE)) {
            clearComposing()
            if (currInputConnection.getSelectedText(InputConnection.GET_TEXT_WITH_STYLES) == null) Toast.makeText(
                context,
                "선택된 문구가 없습니다.",
                Toast.LENGTH_SHORT
            ).show()
            else currInputConnection.performContextMenuAction(android.R.id.copy)
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun cutText() {
        if (currentInputConnection == null) return
        if (currentInputConnection.requestCursorUpdates(InputConnection.CURSOR_UPDATE_IMMEDIATE)) {
            clearComposing()
            if (currentInputConnection.getSelectedText(InputConnection.GET_TEXT_WITH_STYLES) == null) Toast.makeText(
                applicationContext,
                "선택된 문구가 없습니다.",
                Toast.LENGTH_SHORT
            ).show()
            else currentInputConnection.performContextMenuAction(android.R.id.cut)
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun pasteText() {
        if (currentInputConnection == null) return
        if (currentInputConnection.requestCursorUpdates(InputConnection.CURSOR_UPDATE_IMMEDIATE)) {
            clearComposing()
            currentInputConnection.performContextMenuAction(android.R.id.paste)
        }
    }

    private fun clearComposing() {
        currentInputConnection.finishComposingText()
        hanguelQwerty.initState()
        hanguelQwerty.initChar()
        hanguelQwerty.composeResult()
        hanguelQwerty.initResult()
        hanguelChunjiin.initState()
        hanguelChunjiin.initChar()
        hanguelChunjiin.composeResult()
        hanguelChunjiin.initResult()
        hanguelNaratgul.initState()
        hanguelNaratgul.initChar()
        hanguelNaratgul.composeResult()
        hanguelNaratgul.initResult()
        danHangul.initState()
        danHangul.initChar()
        danHangul.composeResult()
        danHangul.initResult()
    }

}