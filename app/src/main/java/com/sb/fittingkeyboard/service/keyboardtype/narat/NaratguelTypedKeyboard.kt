package com.sb.fittingkeyboard.service.keyboardtype.narat

import android.annotation.SuppressLint
import android.graphics.PorterDuff
import android.os.Build
import android.util.TypedValue
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import com.sb.fittingKeyboard.R
import com.sb.fittingKeyboard.databinding.FragmentKeyboardNaratgulBasicBinding
import com.sb.fittingkeyboard.service.keyboardtype.core.defaultFontSize
import com.sb.fittingkeyboard.service.MainKeyboardService
import com.sb.fittingkeyboard.service.keyboardtype.core.InputTypeState
import com.sb.fittingkeyboard.service.keyboardtype.core.TypedKeyboard
import com.sb.fittingkeyboard.service.util.RepeatTouchListener
import com.sb.fittingkeyboard.service.util.SwipeableButtonTouchListener
import com.sb.fittingkeyboard.service.viewmodel.KeyboardViewModel

class NaratguelTypedKeyboard(
    private val binding: FragmentKeyboardNaratgulBasicBinding,
    private val imeService: MainKeyboardService
    ): TypedKeyboard(binding.kbviewmodel!!, imeService) {

    @SuppressLint("ClickableViewAccessibility")
    override fun init() {
        val viewModel: KeyboardViewModel = binding.kbviewmodel!!
        val charKeyList = listOf(
            binding.btnKrNaratR,
            binding.btnKrNaratS,
            binding.btnKrNaratK,
            binding.btnKrNaratF,
            binding.btnKrNaratA,
            binding.btnKrNaratH,
            binding.btnKrNaratT,
            binding.btnKrNaratD,
            binding.btnKrNaratL,
            binding.btnKrNaratM,
            binding.btnKrNaratAdd,
            binding.btnKrNaratShift
        )

        viewModel.kbHasSwipeableSpace.observe(imeService) {
            binding.btnKrNaratSpace.apply {
                setOnTouchListener(
                    if (it) {
                        SwipeableButtonTouchListener(
                            actionUpEvent = { _, _ ->
                                clearComposingStep()
                                if (viewModel.kbHasVibration.value!!) vibrate()
                                imeService.currentInputConnection.commitText(" ", 1)
                            },
                            actionSwipeEvent = { _, _ ->
                                if (viewModel.kbHasVibration.value!!) vibrate()
                                viewModel.setInputTypeState(InputTypeState.EN_UPPER)
                            }
                        )
                    } else {
                        spaceRepeatTouchListener
                    }
                )

                setCompoundDrawablesWithIntrinsicBounds(
                    if (it) ResourcesCompat.getDrawable(resources, R.drawable.keyic_arrowleft_black, null) else null,
                    null,
                    if (it) ResourcesCompat.getDrawable(resources, R.drawable.keyic_arrowright_black, null) else null,
                    null
                )

                if (it) setTextSize(TypedValue.COMPLEX_UNIT_SP, defaultFontSize)
                else textSize = viewModel.kbFontSize.value!! / resources.displayMetrics.density
            }
        }

        viewModel.kbFunctionKeysFontColor.observe(imeService) {
            binding.btnKrNaratSpace.setCompoundDrawablesWithIntrinsicBounds(
                if (viewModel.kbHasSwipeableSpace.value!!) ResourcesCompat.getDrawable(binding.btnKrNaratSpace.resources, R.drawable.keyic_arrowleft_black, null)?.let { drawable ->
                    drawable.clearColorFilter()
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) drawable.colorFilter = BlendModeColorFilterCompat.createBlendModeColorFilterCompat(it, BlendModeCompat.SRC_ATOP)
                    else drawable.setColorFilter(it, PorterDuff.Mode.SRC_ATOP)
                    drawable
                } else null,
                null,
                if (viewModel.kbHasSwipeableSpace.value!!) ResourcesCompat.getDrawable(binding.btnKrNaratSpace.resources, R.drawable.keyic_arrowright_black, null)?.let { drawable ->
                    drawable.clearColorFilter()
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) drawable.colorFilter = BlendModeColorFilterCompat.createBlendModeColorFilterCompat(it, BlendModeCompat.SRC_ATOP)
                    else drawable.setColorFilter(it, PorterDuff.Mode.SRC_ATOP)
                    drawable
                } else null,
                null
            )
        }

        viewModel.kbFontSize.observe(imeService) {
            if (viewModel.kbHasSwipeableSpace.value!!) binding.btnKrNaratSpace.setTextSize(TypedValue.COMPLEX_UNIT_SP, defaultFontSize)
            else binding.btnKrNaratSpace.textSize = it / binding.btnKrNaratSpace.resources.displayMetrics.density
        }

        viewModel.kbLongClickInterval.observe(imeService) {
            val longClickInterval = it.toLong() + 100L

            spaceRepeatTouchListener.setInitialInterval(longClickInterval)

            binding.imgbtnKrNaratDel.setOnTouchListener(
                RepeatTouchListener(
                    initialInterval = longClickInterval,
                    normalInterval = normalInterval,
                    actionDownEvent = { _, _ ->
                        deleteChar()
                    }
                )
            )

            binding.btnKrNaratDot.setOnTouchListener(
                RepeatTouchListener(
                    initialInterval = longClickInterval,
                    normalInterval = normalInterval,
                    actionDownEvent = { view, _ ->
                        inputSpecialKey(view)
                    }
                )
            )
        }

        binding.apply {
            imgbtnKrNaratEnter.setOnClickListener {
                inputEnter()
            }

            imgbtnKrNaratEnter.setOnLongClickListener { view ->
                inputKeyLong(keyType = KeyType.Enter)
            }

            imgbtnKrNaratLang.setOnClickListener {
                vibrate()
                viewModel.setInputTypeState(InputTypeState.EN_UPPER)
            }

            btnKrNaratSpecial.setOnClickListener {
                vibrate()
                viewModel.setInputTypeState(InputTypeState.SPECIAL_FIRST)
            }

            btnKrNaratSpecial.setOnLongClickListener { view ->
                inputKeyLong(keyType = KeyType.Special)
            }
        }

        charKeyList.forEach { btn ->
            btn.setOnClickListener {
                inputCharKey(btn)
            }
        }
    }
}