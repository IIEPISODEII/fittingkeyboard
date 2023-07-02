package com.sb.fittingkeyboard.com.sb.fittingkeyboard.service.keyboardtype.number

import android.content.Context.VIBRATOR_SERVICE
import android.os.Vibrator
import android.util.TypedValue
import androidx.core.content.res.ResourcesCompat
import com.sb.fittingKeyboard.R
import com.sb.fittingKeyboard.databinding.FragmentKeyboardNumberBinding
import com.sb.fittingKeyboard.databinding.FragmentKeyboardQwertyEnNormalBinding
import com.sb.fittingkeyboard.com.sb.fittingkeyboard.service.keyboardtype.core.defaultFontSize
import com.sb.fittingkeyboard.service.MainKeyboardService
import com.sb.fittingkeyboard.service.keyboardtype.core.InputTypeState
import com.sb.fittingkeyboard.service.keyboardtype.core.TypedKeyboard
import com.sb.fittingkeyboard.service.util.RepeatTouchListener
import com.sb.fittingkeyboard.service.util.SwipeableButtonTouchListener
import com.sb.fittingkeyboard.service.viewmodel.KeyboardViewModel

class NumberTypedKeyboard(
    private val binding: FragmentKeyboardNumberBinding,
    private val imeService: MainKeyboardService
    ): TypedKeyboard() {

    override fun init() {
        val viewModel: KeyboardViewModel = binding.kbviewmodel!!
        val vibrator = binding.root.context.getSystemService(VIBRATOR_SERVICE) as Vibrator
    }
}