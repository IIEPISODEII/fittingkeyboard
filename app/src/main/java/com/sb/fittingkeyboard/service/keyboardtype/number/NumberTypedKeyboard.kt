package com.sb.fittingkeyboard.service.keyboardtype.number

import android.content.Context.VIBRATOR_SERVICE
import android.os.Vibrator
import com.sb.fittingKeyboard.databinding.FragmentKeyboardNumberBinding
import com.sb.fittingkeyboard.service.MainKeyboardService
import com.sb.fittingkeyboard.service.keyboardtype.core.TypedKeyboard
import com.sb.fittingkeyboard.service.viewmodel.KeyboardViewModel

class NumberTypedKeyboard(
    private val binding: FragmentKeyboardNumberBinding,
    private val imeService: MainKeyboardService
    ): TypedKeyboard(binding.kbviewmodel, imeService) {

    override fun init() {
        val viewModel: KeyboardViewModel = binding.kbviewmodel!!
        val vibrator = binding.root.context.getSystemService(VIBRATOR_SERVICE) as Vibrator
    }
}