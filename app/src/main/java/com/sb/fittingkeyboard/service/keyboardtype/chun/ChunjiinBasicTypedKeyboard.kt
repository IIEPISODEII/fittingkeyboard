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
        val charKeyList = listOf(
            binding.btnKrChunL,
            binding.btnKrChunK,
            binding.btnKrChunM,
            binding.btnKrChunRz,
            binding.btnKrChunSf,
            binding.btnKrChunEx,
            binding.btnKrChunQv,
            binding.btnKrChunTg,
            binding.btnKrChunWc,
            binding.btnKrChunDa
        )

        viewModel.kbHasSwipeableSpace.observe(imeService) {
            binding.btnKrChunSpace.apply {
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
            val longClickInterval = it.toLong() + 100L

            spaceRepeatTouchListener.setInitialInterval(longClickInterval)

            binding.btnKrChunAt.setOnTouchListener(
                RepeatTouchListener(
                    initialInterval = longClickInterval,
                    normalInterval = normalInterval,
                    actionDownEvent = { view, _ -> inputSpecialKey(view) }
                )
            )

            binding.imgbtnKrChunDel.setOnTouchListener(
                RepeatTouchListener(
                    initialInterval = longClickInterval,
                    normalInterval = normalInterval,
                    actionDownEvent = { _, _ -> deleteChar() }
                )
            )

            binding.btnKrChunAt.setOnTouchListener(
                RepeatTouchListener(
                    initialInterval = longClickInterval,
                    normalInterval = normalInterval,
                    actionDownEvent = { view, _ -> inputSpecialKey(view) }
                )
            )
        }

        charKeyList.forEach { btn ->
            btn.setOnClickListener { view ->
                inputCharKey(view)
            }
        }

        binding.apply {
            imgbtnKrChunEnter.setOnClickListener {
                inputEnter()
            }

            imgbtnKrChunEnter.setOnLongClickListener { view ->
                inputKeyLong(view = view, keyType = KeyType.Enter)
            }

            btnKrChunSpecial.setOnClickListener { view ->
                vibrate()
                viewModel.setInputTypeState(InputTypeState.SPECIAL_FIRST)
            }

            btnKrChunSpecial.setOnLongClickListener { view ->
                inputKeyLong(view = view, keyType = KeyType.Special)
            }

            imgbtnKrChunLang.setOnClickListener {
                vibrate()
                viewModel.setInputTypeState(InputTypeState.EN_UPPER)
            }

            imgbtnKrChunInitialize.setOnClickListener {
                clearComposingStep()
            }

            btnKrChunDot.setOnClickListener { view ->
                inputSpecialKey(view)
            }

            btnKrChunDot.setOnLongClickListener {
                clearComposingStep()
                vibrate()
                imeService.currentInputConnection.commitText(",", 1)

                true
            }
        }
    }
}