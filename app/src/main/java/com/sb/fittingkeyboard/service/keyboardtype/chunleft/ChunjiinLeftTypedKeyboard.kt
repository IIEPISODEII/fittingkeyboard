package com.sb.fittingkeyboard.service.keyboardtype.chunleft

import android.annotation.SuppressLint
import android.graphics.PorterDuff
import android.os.Build
import android.util.TypedValue
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import com.sb.fittingKeyboard.R
import com.sb.fittingKeyboard.databinding.FragmentKeyboardChunjiinLeftBinding
import com.sb.fittingkeyboard.service.keyboardtype.core.defaultFontSize
import com.sb.fittingkeyboard.service.MainKeyboardService
import com.sb.fittingkeyboard.service.keyboardtype.core.InputTypeState
import com.sb.fittingkeyboard.service.keyboardtype.core.TypedKeyboard
import com.sb.fittingkeyboard.service.util.RepeatTouchListener
import com.sb.fittingkeyboard.service.util.SwipeableButtonTouchListener
import com.sb.fittingkeyboard.service.viewmodel.KeyboardViewModel

class ChunjiinLeftTypedKeyboard(
    private val binding: FragmentKeyboardChunjiinLeftBinding,
    private val imeService: MainKeyboardService
    ): TypedKeyboard(binding.kbviewmodel, imeService) {

    @SuppressLint("ClickableViewAccessibility")
    override fun init() {
        val viewModel: KeyboardViewModel = binding.kbviewmodel!!

        viewModel.kbHasSwipeableSpace.observe(imeService) {
            binding.btnKrChunLeftSpace.apply {
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
            binding.btnKrChunLeftSpace.setCompoundDrawablesWithIntrinsicBounds(
                if (viewModel.kbHasSwipeableSpace.value!!) ResourcesCompat.getDrawable(binding.btnKrChunLeftSpace.resources, R.drawable.keyic_arrowleft_black, null)?.let { drawable ->
                    drawable.clearColorFilter()
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) drawable.colorFilter = BlendModeColorFilterCompat.createBlendModeColorFilterCompat(it, BlendModeCompat.SRC_ATOP)
                    else drawable.setColorFilter(it, PorterDuff.Mode.SRC_ATOP)
                    drawable
                } else null,
                null,
                if (viewModel.kbHasSwipeableSpace.value!!) ResourcesCompat.getDrawable(binding.btnKrChunLeftSpace.resources, R.drawable.keyic_arrowright_black, null)?.let { drawable ->
                    drawable.clearColorFilter()
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) drawable.colorFilter = BlendModeColorFilterCompat.createBlendModeColorFilterCompat(it, BlendModeCompat.SRC_ATOP)
                    else drawable.setColorFilter(it, PorterDuff.Mode.SRC_ATOP)
                    drawable
                } else null,
                null
            )
        }

        viewModel.kbFontSize.observe(imeService) {
            if (viewModel.kbHasSwipeableSpace.value!!) binding.btnKrChunLeftSpace.setTextSize(TypedValue.COMPLEX_UNIT_SP, defaultFontSize)
            else binding.btnKrChunLeftSpace.textSize = it / binding.btnKrChunLeftSpace.resources.displayMetrics.density
        }

        viewModel.kbLongClickInterval.observe(imeService) {
            val longClickInterval = it.toLong() + 100L

            spaceRepeatTouchListener.setInitialInterval(longClickInterval)

            binding.imgbtnKrChunLeftDel.setOnTouchListener(
                RepeatTouchListener(
                    initialInterval = longClickInterval,
                    normalInterval = normalInterval,
                    actionDownEvent = { _, _ -> deleteChar() }
                )
            )

            binding.btnKrChunLeftAt.setOnTouchListener(
                RepeatTouchListener(
                    initialInterval = longClickInterval,
                    normalInterval = normalInterval,
                    actionDownEvent = { view, _ -> inputSpecialKey(view) }
                )
            )
        }

        binding.apply {
            btnChunLeftRz.setOnClickListener { view ->
                inputCharKey(view)
            }

            btnKrChunLeftSf.setOnClickListener { view ->
                inputCharKey(view)
            }

            btnKrChunLeftL.setOnClickListener { view ->
                inputCharKey(view)
            }

            btnKrChunLeftEx.setOnClickListener { view ->
                inputCharKey(view)
            }

            btnKrChunLeftQv.setOnClickListener { view ->
                inputCharKey(view)
            }

            btnKrChunLeftK.setOnClickListener { view ->
                inputCharKey(view)
            }

            btnKrChunLeftTg.setOnClickListener { view ->
                inputCharKey(view)
            }

            btnKrChunLeftWc.setOnClickListener { view ->
                inputCharKey(view)
            }

            btnKrChunLeftM.setOnClickListener { view ->
                inputCharKey(view)
            }

            imgbtnKrChunLeftEnter.setOnClickListener {
                inputEnter()
            }

            imgbtnKrChunLeftEnter.setOnLongClickListener { view ->
                inputKeyLong(view = view, keyType = KeyType.Enter)
            }

            btnKrChunLeftSpecial.setOnClickListener {
                viewModel.setInputTypeState(InputTypeState.SPECIAL_FIRST)
                vibrate()
            }

            btnKrChunLeftSpecial.setOnLongClickListener { view ->
                inputKeyLong(view = view, keyType = KeyType.Special)
            }

            btnKrChunLeftSpecial.setOnClickListener {
                viewModel.setInputTypeState(InputTypeState.EN_UPPER)
                vibrate()
            }

            btnKrChunLeftDa.setOnClickListener { view ->
                inputCharKey(view)
            }

            imgbtnKrChunLeftInitialize.setOnClickListener { view ->
                clearComposingStep()
            }

            btnKrChunLeftDot.setOnClickListener { view ->
                inputSpecialKey(view)
            }

            btnKrChunLeftDot.setOnLongClickListener {
                clearComposingStep()
                vibrate()
                imeService.currentInputConnection.commitText(",", 1)

                true
            }
        }
    }
}