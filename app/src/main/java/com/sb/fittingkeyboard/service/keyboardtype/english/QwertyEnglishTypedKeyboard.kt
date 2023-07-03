package com.sb.fittingkeyboard.service.keyboardtype.english

import android.annotation.SuppressLint
import android.content.Context.VIBRATOR_SERVICE
import android.graphics.PorterDuff
import android.os.Build
import android.os.Vibrator
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import com.sb.fittingKeyboard.R
import com.sb.fittingKeyboard.databinding.FragmentKeyboardQwertyEnNormalBinding
import com.sb.fittingkeyboard.service.MainKeyboardService
import com.sb.fittingkeyboard.service.keyboardtype.core.InputTypeState
import com.sb.fittingkeyboard.service.keyboardtype.core.TypedKeyboard
import com.sb.fittingkeyboard.service.util.RepeatTouchListener
import com.sb.fittingkeyboard.service.util.SingleTouchListener
import com.sb.fittingkeyboard.service.util.SwipeableButtonTouchListener
import com.sb.fittingkeyboard.service.viewmodel.KeyboardViewModel

class QwertyEnglishTypedKeyboard(
    private val binding: FragmentKeyboardQwertyEnNormalBinding,
    private val imeService: MainKeyboardService
    ): TypedKeyboard(binding.kbviewmodel, imeService) {

    @SuppressLint("ClickableViewAccessibility")
    override fun init() {
        val viewModel: KeyboardViewModel = binding.kbviewmodel!!
        val vibrator = binding.root.context.getSystemService(VIBRATOR_SERVICE) as Vibrator

        viewModel.kbHasSwipeableSpace.observe(imeService) {
            binding.btnEnSpace.apply {
                setOnTouchListener(
                    if (it) {
                        SwipeableButtonTouchListener(
                            actionUpEvent = { _, _ ->
                                clearComposingStep(imeService)
                                if (viewModel.kbHasVibration.value!!) vibrate(vibrator, viewModel.kbVibrationIntensity.value!!.toLong())
                                imeService.currentInputConnection.commitText(" ", 1)
                            },
                            actionSwipeEvent = { _, _ ->
                                if (viewModel.kbHasVibration.value!!) vibrate(vibrator, viewModel.kbVibrationIntensity.value!!.toLong())
                                viewModel.setInputTypeState(InputTypeState.KR_NORMAL)
                            }
                        )
                    } else {
                        RepeatTouchListener(
                            initialInterval = viewModel.kbLongClickInterval.value!!.toLong(),
                            normalInterval = normalInterval,
                            actionDownEvent = { _, _ ->
                                clearComposingStep(imeService)
                                if (viewModel.kbHasVibration.value!!) vibrate(vibrator, viewModel.kbVibrationIntensity.value!!.toLong())
                                imeService.currentInputConnection.commitText(" ", 1)
                            }
                        )
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
            binding.btnEnSpace.setCompoundDrawablesWithIntrinsicBounds(
                if (viewModel.kbHasSwipeableSpace.value!!) ResourcesCompat.getDrawable(binding.btnEnSpace.resources, R.drawable.keyic_arrowleft_black, null)?.let { drawable ->
                    drawable.clearColorFilter()
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) drawable.colorFilter = BlendModeColorFilterCompat.createBlendModeColorFilterCompat(it, BlendModeCompat.SRC_ATOP)
                    else drawable.setColorFilter(it, PorterDuff.Mode.SRC_ATOP)
                    drawable
                } else null,
                null,
                if (viewModel.kbHasSwipeableSpace.value!!) ResourcesCompat.getDrawable(binding.btnEnSpace.resources, R.drawable.keyic_arrowright_black, null)?.let { drawable ->
                    drawable.clearColorFilter()
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) drawable.colorFilter = BlendModeColorFilterCompat.createBlendModeColorFilterCompat(it, BlendModeCompat.SRC_ATOP)
                    else drawable.setColorFilter(it, PorterDuff.Mode.SRC_ATOP)
                    drawable
                } else null,
                null
            )
        }

        viewModel.kbHasVibration.observe(imeService) {
            binding.btnEnSpace.setOnTouchListener(
                if (it) {
                    SwipeableButtonTouchListener(
                        actionUpEvent = { _, _ ->
                            clearComposingStep(imeService)
                            if (viewModel.kbHasVibration.value!!) vibrate(vibrator, viewModel.kbVibrationIntensity.value!!.toLong())
                            imeService.currentInputConnection.commitText(" ", 1)
                        },
                        actionSwipeEvent = { _, _ ->
                            if (viewModel.kbHasVibration.value!!) vibrate(vibrator, viewModel.kbVibrationIntensity.value!!.toLong())
                            viewModel.setInputTypeState(InputTypeState.KR_NORMAL)
                        }
                    )
                } else {
                    RepeatTouchListener(
                        initialInterval = viewModel.kbLongClickInterval.value!!.toLong(),
                        normalInterval = normalInterval,
                        actionDownEvent = { _, _ ->
                            clearComposingStep(imeService)
                            if (it) vibrate(vibrator, viewModel.kbVibrationIntensity.value!!.toLong())
                            imeService.currentInputConnection.commitText(" ", 1)
                        }
                    )
                }
            )

            binding.imgbtnEnLang.setOnTouchListener(
                SingleTouchListener(
                    actionDownEvent = { _, _ ->
                        if (it) vibrate(vibrator, viewModel.kbVibrationIntensity.value!!.toLong())
                        viewModel.setInputTypeState(InputTypeState.KR_NORMAL)
                    }
                )
            )

            binding.imgbtnEnShift.setOnTouchListener(
                SingleTouchListener(
                    actionDownEvent = { _, _ ->
                        if (it) vibrate(vibrator, viewModel.kbVibrationIntensity.value!!.toLong())
                        viewModel.setInputTypeState(InputTypeState.EN_UPPER)
                    }
                )
            )

            binding.btnEnSpecial.setOnTouchListener(
                SingleTouchListener(
                    actionDownEvent = { _, _ ->
                        if (it) vibrate(vibrator, viewModel.kbVibrationIntensity.value!!.toLong())
                        viewModel.setInputTypeState(InputTypeState.SPECIAL_FIRST)
                    }
                )
            )
        }

        viewModel.kbVibrationIntensity.observe(imeService) {
            binding.btnEnSpace.setOnTouchListener(
                if (viewModel.kbHasVibration.value!!) {
                    SwipeableButtonTouchListener(
                        actionUpEvent = { _, _ ->
                            clearComposingStep(imeService)
                            if (viewModel.kbHasVibration.value!!) vibrate(vibrator, it.toLong())
                            imeService.currentInputConnection.commitText(" ", 1)
                        },
                        actionSwipeEvent = { _, _ ->
                            if (viewModel.kbHasVibration.value!!) vibrate(vibrator, it.toLong())
                            viewModel.setInputTypeState(InputTypeState.KR_NORMAL)
                        }
                    )
                } else {
                    RepeatTouchListener(
                        initialInterval = viewModel.kbLongClickInterval.value!!.toLong(),
                        normalInterval = normalInterval,
                        actionDownEvent = { _, _ ->
                            clearComposingStep(imeService)
                            if (viewModel.kbHasVibration.value!!) vibrate(vibrator, it.toLong())
                            imeService.currentInputConnection.commitText(" ", 1)
                        }
                    )
                }
            )

            binding.imgbtnEnLang.setOnTouchListener(
                SingleTouchListener(
                    actionDownEvent = { _, _ ->
                        if (viewModel.kbHasVibration.value!!) vibrate(vibrator, it.toLong())
                        viewModel.setInputTypeState(InputTypeState.KR_NORMAL)
                    }
                )
            )

            binding.imgbtnEnShift.setOnTouchListener(
                SingleTouchListener(
                    actionDownEvent = { _, _ ->
                        if (viewModel.kbHasVibration.value!!) vibrate(vibrator, it.toLong())
                        viewModel.setInputTypeState(InputTypeState.EN_UPPER)
                    }
                )
            )

            binding.btnEnSpecial.setOnTouchListener(
                SingleTouchListener(
                    actionDownEvent = { _, _ ->
                        if (viewModel.kbHasVibration.value!!) vibrate(vibrator, it.toLong())
                        viewModel.setInputTypeState(InputTypeState.SPECIAL_FIRST)
                    }
                )
            )
        }

        viewModel.kbLongClickInterval.observe(imeService) {
            binding.btnEnSpace.setOnTouchListener(
                if (viewModel.kbHasVibration.value!!) {
                    SwipeableButtonTouchListener(
                        actionUpEvent = { _, _ ->
                            clearComposingStep(imeService)
                            if (viewModel.kbHasVibration.value!!) vibrate(vibrator, viewModel.kbVibrationIntensity.value!!.toLong())
                            imeService.currentInputConnection.commitText(" ", 1)
                        },
                        actionSwipeEvent = { _, _ ->
                            if (viewModel.kbHasVibration.value!!) vibrate(vibrator, viewModel.kbVibrationIntensity.value!!.toLong())
                            viewModel.setInputTypeState(InputTypeState.KR_NORMAL)
                        }
                    )
                } else {
                    RepeatTouchListener(
                        initialInterval = it.toLong(),
                        normalInterval = normalInterval,
                        actionDownEvent = { _, _ ->
                            clearComposingStep(imeService)
                            if (viewModel.kbHasVibration.value!!) vibrate(vibrator, viewModel.kbVibrationIntensity.value!!.toLong())
                            imeService.currentInputConnection.commitText(" ", 1)
                        }
                    )
                }
            )
        }
    }
}