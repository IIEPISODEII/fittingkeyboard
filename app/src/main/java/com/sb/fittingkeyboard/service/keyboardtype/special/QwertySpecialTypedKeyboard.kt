package com.sb.fittingkeyboard.service.keyboardtype.special

import android.annotation.SuppressLint
import android.graphics.PorterDuff
import android.os.Build
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import com.sb.fittingKeyboard.R
import com.sb.fittingKeyboard.databinding.FragmentKeyboardQwertySpecialBinding
import com.sb.fittingkeyboard.service.MainKeyboardService
import com.sb.fittingkeyboard.service.keyboardtype.core.InputTypeState
import com.sb.fittingkeyboard.service.keyboardtype.core.TypedKeyboard
import com.sb.fittingkeyboard.service.util.RepeatTouchListener
import com.sb.fittingkeyboard.service.util.SingleTouchListener
import com.sb.fittingkeyboard.service.util.SwipeableButtonTouchListener
import com.sb.fittingkeyboard.service.viewmodel.KeyboardViewModel

class QwertySpecialTypedKeyboard(
    private val binding: FragmentKeyboardQwertySpecialBinding,
    private val imeService: MainKeyboardService
    ): TypedKeyboard(binding.kbviewmodel!!, imeService) {

    @SuppressLint("ClickableViewAccessibility")
    override fun init() {
        val viewModel: KeyboardViewModel = binding.kbviewmodel!!
        val charKeyList = listOf(
            binding.btnSpecialQ,
            binding.btnSpecialW,
            binding.btnSpecialE,
            binding.btnSpecialR,
            binding.btnSpecialT,
            binding.btnSpecialY,
            binding.btnSpecialU,
            binding.btnSpecialI,
            binding.btnSpecialO,
            binding.btnSpecialP,
            binding.btnSpecialA,
            binding.btnSpecialS,
            binding.btnSpecialD,
            binding.btnSpecialF,
            binding.btnSpecialG,
            binding.btnSpecialH,
            binding.btnSpecialJ,
            binding.btnSpecialK,
            binding.btnSpecialL,
            binding.btnSpecialZ,
            binding.btnSpecialX,
            binding.btnSpecialC,
            binding.btnSpecialV,
            binding.btnSpecialB,
            binding.btnSpecialN,
            binding.btnSpecialM
        )

        val spacebarRepeatListener =
            RepeatTouchListener(
                initialInterval = viewModel.kbLongClickInterval.value!!.toLong() + 100L,
                normalInterval = normalInterval,
                actionDownEvent = { _, _ ->
                    clearComposingStep()
                    if (viewModel.kbHasVibration.value!!) vibrate()
                    if (viewModel.kbHasTypeChange.value!!) viewModel.setInputTypeState(InputTypeState.KR_NORMAL)
                    imeService.currentInputConnection.commitText(" ", 1)
                }
            )

        viewModel.kbHasSwipeableSpace.observe(imeService) {
            binding.btnSpecialSpace.apply {
                setOnTouchListener(
                    if (it) {
                        SwipeableButtonTouchListener(
                            actionUpEvent = { _, _ ->
                                clearComposingStep()
                                if (viewModel.kbHasVibration.value!!) vibrate()
                                if (viewModel.kbHasTypeChange.value!!) viewModel.setInputTypeState(InputTypeState.KR_NORMAL)
                                imeService.currentInputConnection.commitText(" ", 1)
                            },
                            actionSwipeEvent = { _, _ ->
                                if (viewModel.kbHasVibration.value!!) vibrate()
                                viewModel.setInputTypeState(InputTypeState.SPECIAL_SECOND)
                            }
                        )
                    } else {
                        spacebarRepeatListener
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
            binding.btnSpecialSpace.setCompoundDrawablesWithIntrinsicBounds(
                if (viewModel.kbHasSwipeableSpace.value!!) ResourcesCompat.getDrawable(binding.btnSpecialSpace.resources, R.drawable.keyic_arrowleft_black, null)?.let { drawable ->
                    drawable.clearColorFilter()
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) drawable.colorFilter = BlendModeColorFilterCompat.createBlendModeColorFilterCompat(it, BlendModeCompat.SRC_ATOP)
                    else drawable.setColorFilter(it, PorterDuff.Mode.SRC_ATOP)
                    drawable
                } else null,
                null,
                if (viewModel.kbHasSwipeableSpace.value!!) ResourcesCompat.getDrawable(binding.btnSpecialSpace.resources, R.drawable.keyic_arrowright_black, null)?.let { drawable ->
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

            spacebarRepeatListener.setInitialInterval(longClickInterval)

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

            binding.imgbtnSpecialDel.setOnTouchListener(
                RepeatTouchListener(
                    initialInterval = longClickInterval,
                    normalInterval = normalInterval,
                    actionDownEvent = { _, _ ->
                        deleteChar()
                    }
                )
            )

            binding.btnSpecialComma.setOnTouchListener(
                RepeatTouchListener(
                    initialInterval = longClickInterval,
                    normalInterval = normalInterval,
                    actionDownEvent = { view, _ ->
                        inputSpecialKey(view)
                    }
                )
            )

            binding.btnSpecialDot.setOnTouchListener(
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
            imgbtnSpecialLang.setOnTouchListener(
                SingleTouchListener(
                    actionDownEvent = { _, _ ->
                        vibrate()
                        viewModel.setInputTypeState(InputTypeState.EN_UPPER)
                    }
                )
            )

            imgbtnSpecialShift.setOnTouchListener(
                SingleTouchListener(
                    actionDownEvent = { _, _ ->
                        vibrate()
                        viewModel.setInputTypeState(InputTypeState.SPECIAL_SECOND)
                    }
                )
            )

            imgbtnSpecialEnter.setOnClickListener {
                inputEnter()
            }

            imgbtnSpecialEnter.setOnLongClickListener { view ->
                inputKeyLong(keyType = KeyType.Enter)
            }
        }
    }
}