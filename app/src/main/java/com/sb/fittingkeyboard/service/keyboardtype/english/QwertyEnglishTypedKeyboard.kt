package com.sb.fittingkeyboard.service.keyboardtype.english

import android.annotation.SuppressLint
import android.graphics.PorterDuff
import android.os.Build
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
    ): TypedKeyboard(binding.kbviewmodel!!, imeService) {

    @SuppressLint("ClickableViewAccessibility")
    override fun init() {
        val viewModel: KeyboardViewModel = binding.kbviewmodel!!
        val charKeyList = listOf(
            binding.btnEnQ,
            binding.btnEnW,
            binding.btnEnE,
            binding.btnEnR,
            binding.btnEnT,
            binding.btnEnY,
            binding.btnEnU,
            binding.btnEnI,
            binding.btnEnO,
            binding.btnEnP,
            binding.btnEnA,
            binding.btnEnS,
            binding.btnEnD,
            binding.btnEnF,
            binding.btnEnG,
            binding.btnEnH,
            binding.btnEnJ,
            binding.btnEnK,
            binding.btnEnL,
            binding.btnEnZ,
            binding.btnEnX,
            binding.btnEnC,
            binding.btnEnV,
            binding.btnEnB,
            binding.btnEnN,
            binding.btnEnM
        )

        viewModel.kbHasSwipeableSpace.observe(imeService) {
            binding.btnEnSpace.apply {
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
                                viewModel.setInputTypeState(InputTypeState.KR_NORMAL)
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

            binding.imgbtnEnDel.setOnTouchListener(
                RepeatTouchListener(
                    initialInterval = longClickInterval,
                    normalInterval = normalInterval,
                    actionDownEvent = { _, _ ->
                        deleteChar()
                    }
                )
            )

            binding.btnEnComma.setOnTouchListener(
                RepeatTouchListener(
                    initialInterval = longClickInterval,
                    normalInterval = normalInterval,
                    actionDownEvent = { view, _ ->
                        inputSpecialKey(view)
                    }
                )
            )

            binding.btnEnDot.setOnTouchListener(
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
            btnEnSpecial.setOnTouchListener(
                SingleTouchListener(
                    actionDownEvent = { _, _ ->
                        vibrate()
                        viewModel.setInputTypeState(InputTypeState.SPECIAL_FIRST)
                    }
                )
            )

            btnEnSpecial.setOnLongClickListener { view ->
                inputKeyLong(keyType = KeyType.Special)
            }

            imgbtnEnLang.setOnTouchListener(
                SingleTouchListener(
                    actionDownEvent = { _, _ ->
                        vibrate()
                        viewModel.setInputTypeState(InputTypeState.KR_NORMAL)
                    }
                )
            )

            imgbtnEnEnter.setOnClickListener {
                inputEnter()
            }

            imgbtnEnEnter.setOnLongClickListener { view ->
                inputKeyLong(keyType = KeyType.Enter)
            }

            imgbtnEnShift.setOnTouchListener(
                SingleTouchListener(
                    actionDownEvent = { _, _ ->
                        vibrate()
                        viewModel.setInputTypeState(InputTypeState.EN_UPPER)
                    }
                )
            )
        }
    }
}