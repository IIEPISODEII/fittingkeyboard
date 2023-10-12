package com.sb.fittingKeyboard.service.keyboardtype.qwertykr

import android.annotation.SuppressLint
import android.graphics.PorterDuff
import android.os.Build
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import com.sb.fittingKeyboard.R
import com.sb.fittingKeyboard.databinding.FragmentKeyboardQwertyKrNormalBinding
import com.sb.fittingKeyboard.service.MainKeyboardService
import com.sb.fittingKeyboard.service.keyboardtype.core.InputTypeState
import com.sb.fittingKeyboard.service.keyboardtype.core.TypedKeyboard
import com.sb.fittingKeyboard.service.util.RepeatTouchListener
import com.sb.fittingKeyboard.service.util.SingleTouchListener
import com.sb.fittingKeyboard.service.util.SwipeableButtonTouchListener
import com.sb.fittingKeyboard.service.viewmodel.KeyboardViewModel

class QwertyKrTypedKeyboard(
    private val binding: FragmentKeyboardQwertyKrNormalBinding,
    private val imeService: MainKeyboardService
    ): TypedKeyboard(binding.kbviewmodel!!, imeService) {

    @SuppressLint("ClickableViewAccessibility")
    override fun init() {
        val viewModel: KeyboardViewModel = binding.kbviewmodel!!
        val charKeyList = listOf(
            binding.btnKrQwertyQ,
            binding.btnKrQwertyW,
            binding.btnKrQwertyE,
            binding.btnKrQwertyR,
            binding.btnKrQwertyT,
            binding.btnKrQwertyY,
            binding.btnKrQwertyU,
            binding.btnKrQwertyI,
            binding.btnKrQwertyO,
            binding.btnKrQwertyP,
            binding.btnKrQwertyA,
            binding.btnKrQwertyS,
            binding.btnKrQwertyD,
            binding.btnKrQwertyF,
            binding.btnKrQwertyG,
            binding.btnKrQwertyH,
            binding.btnKrQwertyJ,
            binding.btnKrQwertyK,
            binding.btnKrQwertyL,
            binding.btnKrQwertyZ,
            binding.btnKrQwertyX,
            binding.btnKrQwertyC,
            binding.btnKrQwertyV,
            binding.btnKrQwertyB,
            binding.btnKrQwertyN,
            binding.btnKrQwertyM
        )

        viewModel.kbHasSwipeableSpace.observe(imeService) {
            binding.btnKrQwertySpace.apply {
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
            }
        }

        viewModel.kbFunctionKeysFontColor.observe(imeService) {
            binding.btnKrQwertySpace.setCompoundDrawablesWithIntrinsicBounds(
                if (viewModel.kbHasSwipeableSpace.value!!) ResourcesCompat.getDrawable(binding.btnKrQwertySpace.resources, R.drawable.keyic_arrowleft_black, null)?.let { drawable ->
                    drawable.clearColorFilter()
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) drawable.colorFilter = BlendModeColorFilterCompat.createBlendModeColorFilterCompat(it, BlendModeCompat.SRC_ATOP)
                    else drawable.setColorFilter(it, PorterDuff.Mode.SRC_ATOP)
                    drawable
                } else null,
                null,
                if (viewModel.kbHasSwipeableSpace.value!!) ResourcesCompat.getDrawable(binding.btnKrQwertySpace.resources, R.drawable.keyic_arrowright_black, null)?.let { drawable ->
                    drawable.clearColorFilter()
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) drawable.colorFilter = BlendModeColorFilterCompat.createBlendModeColorFilterCompat(it, BlendModeCompat.SRC_ATOP)
                    else drawable.setColorFilter(it, PorterDuff.Mode.SRC_ATOP)
                    drawable
                } else null,
                null
            )
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

            binding.imgbtnKrQwertyDel.setOnTouchListener(
                RepeatTouchListener(
                    initialInterval = longClickInterval,
                    normalInterval = normalInterval,
                    actionDownEvent = { _, _ ->
                        deleteChar()
                    }
                )
            )

            binding.btnKrQwertyComma.setOnTouchListener(
                RepeatTouchListener(
                    initialInterval = longClickInterval,
                    normalInterval = normalInterval,
                    actionDownEvent = { view, _ ->
                        inputSpecialKey(view)
                    }
                )
            )

            binding.btnKrQwertyDot.setOnTouchListener(
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
            btnKrQwertySpecial.setOnTouchListener(
                SingleTouchListener(
                    actionDownEvent = { _, _ ->
                        if (viewModel.kbHasVibration.value!!) vibrate()
                        viewModel.setInputTypeState(InputTypeState.SPECIAL_FIRST)
                    }
                )
            )

            btnKrQwertySpecial.setOnLongClickListener { view ->
                inputKeyLong(keyType = KeyType.Special)
            }

            imgbtnKrQwertyLang.setOnClickListener {
                if (viewModel.kbHasVibration.value!!) vibrate()
                viewModel.setInputTypeState(InputTypeState.EN_UPPER)
            }

            imgbtnKrQwertyEnter.setOnClickListener {
                inputEnter()
            }

            imgbtnKrQwertyEnter.setOnLongClickListener { view ->
                inputKeyLong(keyType = KeyType.Enter)
            }

            imgbtnKrQwertyShift.setOnTouchListener(
                SingleTouchListener(
                    actionDownEvent = { _, _ ->
                        vibrate()
                        viewModel.setInputTypeState(InputTypeState.KR_NORMAL)
                    }
                )
            )
        }
    }
}