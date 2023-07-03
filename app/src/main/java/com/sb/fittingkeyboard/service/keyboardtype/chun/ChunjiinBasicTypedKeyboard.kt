package com.sb.fittingkeyboard.service.keyboardtype.chun

import android.annotation.SuppressLint
import android.content.Context.VIBRATOR_SERVICE
import android.graphics.PorterDuff
import android.os.Build
import android.os.Vibrator
import android.util.TypedValue
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import com.sb.fittingKeyboard.R
import com.sb.fittingKeyboard.databinding.FragmentKeyboardChunjiinBasicBinding
import com.sb.fittingkeyboard.service.keyboardtype.core.defaultFontSize
import com.sb.fittingkeyboard.service.MainKeyboardService
import com.sb.fittingkeyboard.service.keyboardtype.core.InputTypeState
import com.sb.fittingkeyboard.service.keyboardtype.core.TypedKeyboard
import com.sb.fittingkeyboard.service.util.RepeatTouchListener
import com.sb.fittingkeyboard.service.util.SwipeableButtonTouchListener
import com.sb.fittingkeyboard.service.viewmodel.KeyboardViewModel

class ChunjiinBasicTypedKeyboard(
    private val binding: FragmentKeyboardChunjiinBasicBinding,
    private val imeService: MainKeyboardService
    ): TypedKeyboard(binding.kbviewmodel, imeService) {

    @SuppressLint("ClickableViewAccessibility")
    override fun init() {
        val viewModel: KeyboardViewModel = binding.kbviewmodel!!
        val vibrator = binding.root.context.getSystemService(VIBRATOR_SERVICE) as Vibrator

        viewModel.kbHasSwipeableSpace.observe(imeService) {
            binding.btnKrChunSpace.apply {
                setOnTouchListener(
                    if (it) {
                        SwipeableButtonTouchListener(
                            actionUpEvent = { _, _ ->
                                clearComposingStep(imeService)
                                if (viewModel.kbHasVibration.value!!) vibrate(vibrator, viewModel.kbVibrationIntensity.value!!.toLong() + 100L)
                                imeService.currentInputConnection.commitText(" ", 1)
                            },
                            actionSwipeEvent = { _, _ ->
                                if (viewModel.kbHasVibration.value!!) vibrate(vibrator, viewModel.kbVibrationIntensity.value!!.toLong() + 100L)
                                viewModel.setInputTypeState(InputTypeState.EN_UPPER)
                            }
                        )
                    } else {
                        RepeatTouchListener(
                            initialInterval = viewModel.kbLongClickInterval.value!!.toLong() + 100L,
                            normalInterval = normalInterval,
                            actionDownEvent = { _, _ ->
                                clearComposingStep(imeService)
                                if (viewModel.kbHasVibration.value!!) vibrate(vibrator, viewModel.kbVibrationIntensity.value!!.toLong() + 100L)
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

                if (it) setTextSize(TypedValue.COMPLEX_UNIT_SP, defaultFontSize)
                else textSize = viewModel.kbFontSize.value!! / resources.displayMetrics.density
            }
        }

        viewModel.kbFunctionKeysFontColor.observe(imeService) {
            binding.btnKrChunSpace.setCompoundDrawablesWithIntrinsicBounds(
                if (viewModel.kbHasSwipeableSpace.value!!) ResourcesCompat.getDrawable(binding.btnKrChunSpace.resources, R.drawable.keyic_arrowleft_black, null)?.let { drawable ->
                    drawable.clearColorFilter()
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) drawable.colorFilter = BlendModeColorFilterCompat.createBlendModeColorFilterCompat(it, BlendModeCompat.SRC_ATOP)
                    else drawable.setColorFilter(it, PorterDuff.Mode.SRC_ATOP)
                    drawable
                } else null,
                null,
                if (viewModel.kbHasSwipeableSpace.value!!) ResourcesCompat.getDrawable(binding.btnKrChunSpace.resources, R.drawable.keyic_arrowright_black, null)?.let { drawable ->
                    drawable.clearColorFilter()
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) drawable.colorFilter = BlendModeColorFilterCompat.createBlendModeColorFilterCompat(it, BlendModeCompat.SRC_ATOP)
                    else drawable.setColorFilter(it, PorterDuff.Mode.SRC_ATOP)
                    drawable
                } else null,
                null
            )
        }

        viewModel.kbFontSize.observe(imeService) {
            if (viewModel.kbHasSwipeableSpace.value!!) binding.btnKrChunSpace.setTextSize(TypedValue.COMPLEX_UNIT_SP, defaultFontSize)
            else binding.btnKrChunSpace.textSize = it / binding.btnKrChunSpace.resources.displayMetrics.density
        }

        viewModel.kbLongClickInterval.observe(imeService) {
            binding.btnKrChunSpace.setOnTouchListener(
                if (viewModel.kbHasSwipeableSpace.value!!) {
                    SwipeableButtonTouchListener(
                        actionUpEvent = { _, _ ->
                            clearComposingStep(imeService)
                            if (viewModel.kbHasVibration.value!!) vibrate(vibrator, viewModel.kbVibrationIntensity.value!!.toLong() + 100L)
                            imeService.currentInputConnection.commitText(" ", 1)
                        },
                        actionSwipeEvent = { _, _ ->
                            if (viewModel.kbHasVibration.value!!) vibrate(vibrator, viewModel.kbVibrationIntensity.value!!.toLong() + 100L)
                            viewModel.setInputTypeState(InputTypeState.EN_UPPER)
                        }
                    )
                } else {
                    RepeatTouchListener(
                        initialInterval = it.toLong() + 100L,
                        normalInterval = normalInterval,
                        actionDownEvent = { _, _ ->
                            clearComposingStep(imeService)
                            if (viewModel.kbHasVibration.value!!) vibrate(vibrator, viewModel.kbVibrationIntensity.value!!.toLong() + 100L)
                            imeService.currentInputConnection.commitText(" ", 1)
                        }
                    )
                }
            )

            binding.btnKrChunAt.setOnTouchListener(
                RepeatTouchListener(
                    initialInterval = it.toLong() + 100L,
                    normalInterval = normalInterval,
                    actionDownEvent = { view, _ ->
                        inputSpecialKey(view, vibrator)
                    }
                )
            )

            binding.imgbtnKrChunDel.setOnTouchListener(
                RepeatTouchListener(
                    initialInterval = it.toLong() + 100L,
                    normalInterval = normalInterval,
                    actionDownEvent = { _, _ ->
                        deleteChar(vibrator)
                    }
                )
            )
        }

        binding.apply {
            btnKrChunL.setOnClickListener { view ->
                inputCharKey(view, vibrator)
            }

            btnKrChunK.setOnClickListener { view ->
                inputCharKey(view, vibrator)
            }

            btnKrChunM.setOnClickListener { view ->
                inputCharKey(view, vibrator)
            }

            btnKrChunRz.setOnClickListener { view ->
                inputCharKey(view, vibrator)
            }

            btnKrChunSf.setOnClickListener { view ->
                inputCharKey(view, vibrator)
            }

            btnKrChunEx.setOnClickListener { view ->
                inputCharKey(view, vibrator)
            }

            btnKrChunQv.setOnClickListener { view ->
                inputCharKey(view, vibrator)
            }

            btnKrChunTg.setOnClickListener { view ->
                inputCharKey(view, vibrator)
            }

            btnKrChunWc.setOnClickListener { view ->
                inputCharKey(view, vibrator)
            }

            btnKrChunWc.setOnClickListener { view ->
                inputCharKey(view, vibrator)
            }

            imgbtnKrChunEnter.setOnClickListener {
                inputEnter(vibrator)
            }

            imgbtnKrChunEnter.setOnLongClickListener { view ->
                inputKeyLong(view = view, keyType = KeyType.Enter, vibrator = vibrator)
            }

            btnKrChunSpecial.setOnClickListener { view ->
                inputSpecialKey(view, vibrator)
            }

            btnKrChunSpecial.setOnLongClickListener { view ->
                inputKeyLong(view = view, keyType = KeyType.Special, vibrator = vibrator)
            }

            imgbtnKrChunLang.setOnClickListener {
                vibrate(vibratorService = vibrator, intensity = viewModel.kbVibrationIntensity.value!!.toLong() + 100L)
                viewModel.setInputTypeState(InputTypeState.EN_UPPER)
            }

            btnKrChunDa.setOnClickListener { view ->
                inputCharKey(view, vibrator)
            }

            imgbtnKrChunInitialize.setOnClickListener {
                clearComposingStep(imeService)
            }

            btnKrChunDot.setOnClickListener { view ->
                inputSpecialKey(view, vibrator)
            }

            btnKrChunDot.setOnLongClickListener { view ->
                inputKeyLong(view = view, keyType = KeyType.Dot, vibrator = vibrator)
            }
        }
    }
}