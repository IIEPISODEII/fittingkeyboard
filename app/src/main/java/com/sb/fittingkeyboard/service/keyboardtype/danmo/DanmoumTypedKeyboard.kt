package com.sb.fittingKeyboard.service.keyboardtype.danmo

import android.annotation.SuppressLint
import android.graphics.PorterDuff
import android.os.Build
import android.util.TypedValue
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import com.sb.fittingKeyboard.R
import com.sb.fittingKeyboard.databinding.FragmentKeyboardDanmoumBinding
import com.sb.fittingKeyboard.service.keyboardtype.core.defaultFontSize
import com.sb.fittingKeyboard.service.MainKeyboardService
import com.sb.fittingKeyboard.service.keyboardtype.core.InputTypeState
import com.sb.fittingKeyboard.service.keyboardtype.core.TypedKeyboard
import com.sb.fittingKeyboard.service.util.RepeatTouchListener
import com.sb.fittingKeyboard.service.util.SingleTouchListener
import com.sb.fittingKeyboard.service.util.SwipeableButtonTouchListener
import com.sb.fittingKeyboard.service.viewmodel.KeyboardViewModel

class DanmoumTypedKeyboard(
    private val binding: FragmentKeyboardDanmoumBinding,
    private val imeService: MainKeyboardService
    ): TypedKeyboard(binding.kbviewmodel!!, imeService) {

    @SuppressLint("ClickableViewAccessibility")
    override fun init() {
        val viewModel: KeyboardViewModel = binding.kbviewmodel!!
        val charKeyList = listOf(
            binding.btnKrDanmoQ,
            binding.btnKrDanmoW,
            binding.btnKrDanmoE,
            binding.btnKrDanmoR,
            binding.btnKrDanmoT,
            binding.btnKrDanmoH,
            binding.btnKrDanmoO,
            binding.btnKrDanmoP,
            binding.btnKrDanmoA,
            binding.btnKrDanmoS,
            binding.btnKrDanmoD,
            binding.btnKrDanmoF,
            binding.btnKrDanmoG,
            binding.btnKrDanmoJ,
            binding.btnKrDanmoK,
            binding.btnKrDanmoL,
            binding.btnKrDanmoZ,
            binding.btnKrDanmoX,
            binding.btnKrDanmoC,
            binding.btnKrDanmoV,
            binding.btnKrDanmoN,
            binding.btnKrDanmoM
        )

        viewModel.kbHasSwipeableSpace.observe(imeService) {
            binding.btnKrDanmoSpace.apply {
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
            binding.btnKrDanmoSpace.setCompoundDrawablesWithIntrinsicBounds(
                if (viewModel.kbHasSwipeableSpace.value!!) ResourcesCompat.getDrawable(binding.btnKrDanmoSpace.resources, R.drawable.keyic_arrowleft_black, null)?.let { drawable ->
                    drawable.clearColorFilter()
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) drawable.colorFilter = BlendModeColorFilterCompat.createBlendModeColorFilterCompat(it, BlendModeCompat.SRC_ATOP)
                    else drawable.setColorFilter(it, PorterDuff.Mode.SRC_ATOP)
                    drawable
                } else null,
                null,
                if (viewModel.kbHasSwipeableSpace.value!!) ResourcesCompat.getDrawable(binding.btnKrDanmoSpace.resources, R.drawable.keyic_arrowright_black, null)?.let { drawable ->
                    drawable.clearColorFilter()
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) drawable.colorFilter = BlendModeColorFilterCompat.createBlendModeColorFilterCompat(it, BlendModeCompat.SRC_ATOP)
                    else drawable.setColorFilter(it, PorterDuff.Mode.SRC_ATOP)
                    drawable
                } else null,
                null
            )
        }

        viewModel.kbFontSize.observe(imeService) {
            if (viewModel.kbHasSwipeableSpace.value!!) binding.btnKrDanmoSpace.setTextSize(TypedValue.COMPLEX_UNIT_SP, defaultFontSize)
            else binding.btnKrDanmoSpace.textSize = it / binding.btnKrDanmoSpace.resources.displayMetrics.density
        }

        viewModel.kbLongClickInterval.observe(imeService) {
            val longClickInterval = it.toLong() + 100L

            spaceRepeatTouchListener.setInitialInterval(longClickInterval)

            charKeyList.forEach { btn ->
                btn.setOnTouchListener(
                    RepeatTouchListener(
                        initialInterval = longClickInterval,
                        normalInterval = normalInterval,
                        actionDownEvent = { view, _ ->
                            inputCharKey(view)
                        }
                    )
                )
            }

            binding.imgbtnKrDanmoDel.setOnTouchListener(
                RepeatTouchListener(
                    initialInterval = longClickInterval,
                    normalInterval = normalInterval,
                    actionDownEvent = { _, _ ->
                        deleteChar()
                    }
                )
            )

            binding.btnKrDanmoComma.setOnTouchListener(
                RepeatTouchListener(
                    initialInterval = longClickInterval,
                    normalInterval = normalInterval,
                    actionDownEvent = { view, _ ->
                        inputSpecialKey(view)
                    }
                )
            )

            binding.btnKrDanmoDot.setOnTouchListener(
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
            btnKrDanmoSpecial.setOnClickListener {
                vibrate()
                viewModel.setInputTypeState(InputTypeState.SPECIAL_FIRST)
            }

            btnKrDanmoSpecial.setOnLongClickListener { view ->
                inputKeyLong(keyType = KeyType.Special)
            }

            imgbtnKrDanmoLang.setOnTouchListener(
                SingleTouchListener(
                    actionDownEvent = { _, _ ->
                        vibrate()
                        viewModel.setInputTypeState(InputTypeState.EN_UPPER)
                    }
                )
            )

            imgbtnKrDanmoEnter.setOnClickListener {
                inputEnter()
            }

            imgbtnKrDanmoEnter.setOnLongClickListener { view ->
                inputKeyLong(keyType = KeyType.Enter)
            }
        }
    }
}