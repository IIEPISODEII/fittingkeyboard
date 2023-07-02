package com.sb.fittingkeyboard.service.keyboardtype.core

import android.inputmethodservice.InputMethodService
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.inputmethod.InputMethod
import com.sb.fittingkeyboard.service.koreanautomata.HanguelChunjiin
import com.sb.fittingkeyboard.service.koreanautomata.HanguelDanmoum
import com.sb.fittingkeyboard.service.koreanautomata.HanguelNARATGUL
import com.sb.fittingkeyboard.service.koreanautomata.HanguelQWERTY

abstract class TypedKeyboard {

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
}