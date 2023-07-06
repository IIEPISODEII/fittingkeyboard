package com.sb.fittingKeyboard.service.keyboardtype.chunleft

import android.annotation.SuppressLint
import android.content.res.Resources
import android.graphics.PorterDuff
import android.graphics.Typeface
import android.os.Build
import android.util.TypedValue
import android.view.Gravity
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import androidx.core.view.setPadding
import com.sb.fittingKeyboard.R
import com.sb.fittingKeyboard.databinding.FragmentKeyboardChunjiinLeftBinding
import com.sb.fittingKeyboard.service.MainKeyboardService
import com.sb.fittingKeyboard.service.keyboardtype.core.InputTypeState
import com.sb.fittingKeyboard.service.keyboardtype.core.TypedKeyboard
import com.sb.fittingKeyboard.service.keyboardtype.core.defaultFontSize
import com.sb.fittingKeyboard.service.ui.CompoundButton
import com.sb.fittingKeyboard.service.util.RepeatTouchListener
import com.sb.fittingKeyboard.service.util.SwipeableButtonTouchListener
import com.sb.fittingKeyboard.service.viewmodel.KeyboardViewModel

class ChunjiinLeftTypedKeyboard(
    private val binding: FragmentKeyboardChunjiinLeftBinding,
    private val imeService: MainKeyboardService
    ): TypedKeyboard(binding.kbviewmodel!!, imeService) {

    @SuppressLint("ClickableViewAccessibility")
    override fun init() {
        val viewModel: KeyboardViewModel = binding.kbviewmodel!!
        val charKeyList = listOf(
            binding.btnKrChunLeftRz,
            binding.btnKrChunLeftSf,
            binding.btnKrChunLeftSf,
            binding.btnKrChunLeftL,
            binding.btnKrChunLeftEx,
            binding.btnKrChunLeftQv,
            binding.btnKrChunLeftK,
            binding.btnKrChunLeftTg,
            binding.btnKrChunLeftWc,
            binding.btnKrChunLeftM,
            binding.btnKrChunLeftDa
        )

        val imgbtnKrChunLeftInitialize = ImageButton(binding.root.context).apply {
            LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1F)
                .apply {
                    gravity = Gravity.CENTER_HORIZONTAL
                }.also { mLayoutParams ->
                    layoutParams = mLayoutParams
                }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) elevation = 2 * (Resources.getSystem().displayMetrics.density)

            setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.keyic_arrowright_black, null))
        }

        val btnKrChunLeftSpace = CompoundButton(binding.root.context).apply {
            LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1F)
                .apply {
                    gravity = Gravity.CENTER_HORIZONTAL
                }.also { mLayoutParams ->
                    layoutParams = mLayoutParams
                }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) elevation = 2 * (Resources.getSystem().displayMetrics.density)

            text = context.getString(R.string.space)
        }

        viewModel.kbHasSwipeableSpace.observe(imeService) {
            btnKrChunLeftSpace.apply {
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

        viewModel.kbNormalKeysFontColor.observe(imeService) {
            imgbtnKrChunLeftInitialize.setColorFilter(it)
        }

        viewModel.kbFunctionKeysFontColor.observe(imeService) {
            btnKrChunLeftSpace.setCompoundDrawablesWithIntrinsicBounds(
                if (viewModel.kbHasSwipeableSpace.value!!) ResourcesCompat.getDrawable(btnKrChunLeftSpace.resources, R.drawable.keyic_arrowleft_black, null)?.let { drawable ->
                    drawable.clearColorFilter()
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) drawable.colorFilter = BlendModeColorFilterCompat.createBlendModeColorFilterCompat(it, BlendModeCompat.SRC_ATOP)
                    else drawable.setColorFilter(it, PorterDuff.Mode.SRC_ATOP)
                    drawable
                } else null,
                null,
                if (viewModel.kbHasSwipeableSpace.value!!) ResourcesCompat.getDrawable(btnKrChunLeftSpace.resources, R.drawable.keyic_arrowright_black, null)?.let { drawable ->
                    drawable.clearColorFilter()
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) drawable.colorFilter = BlendModeColorFilterCompat.createBlendModeColorFilterCompat(it, BlendModeCompat.SRC_ATOP)
                    else drawable.setColorFilter(it, PorterDuff.Mode.SRC_ATOP)
                    drawable
                } else null,
                null
            )

            btnKrChunLeftSpace.setTextColor(it)
        }

        viewModel.kbFontSize.observe(imeService) {
            if (viewModel.kbHasSwipeableSpace.value!!) btnKrChunLeftSpace.setTextSize(TypedValue.COMPLEX_UNIT_SP, defaultFontSize)
            else btnKrChunLeftSpace.textSize = it / btnKrChunLeftSpace.resources.displayMetrics.density
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

        viewModel.kbTheme.observe(imeService) {
            imgbtnKrChunLeftInitialize.setBackgroundResource(
                when (it) {
                    0 -> R.drawable.keydesign_14_char
                    1 -> R.drawable.keydesign_00_char
                    2 -> R.drawable.keydesign_04_char
                    3 -> R.drawable.keydesign_08_char
                    4 -> R.drawable.keydesign_09_char
                    5 -> R.drawable.keydesign_05_char
                    6 -> R.drawable.keydesign_10_char
                    7 -> R.drawable.keydesign_02_a
                    8 -> R.drawable.keydesign_03_char
                    9 -> R.drawable.keydesign_06_char
                    10 -> R.drawable.keydesign_07_char
                    11 -> R.drawable.keydesign_07_char
                    12 -> R.drawable.keydesign_07_char
                    13 -> R.drawable.keydesign_07_char
                    14 -> R.drawable.keydesign_07_char
                    15 -> R.drawable.keydesign_15_char
                    16 -> R.drawable.keydesign_16_char
                    17 -> R.drawable.keydesign_07_char
                    18 -> R.drawable.keydesign_18_char
                    else -> return@observe
                }
            )

            btnKrChunLeftSpace.setBackgroundResource(
                when (it) {
                    0 -> R.drawable.keydesign_14_function
                    1 -> R.drawable.keydesign_00_function
                    2 -> R.drawable.keydesign_04_function
                    3 -> R.drawable.keydesign_08_function
                    4 -> R.drawable.keydesign_09_function
                    5 -> R.drawable.keydesign_05_function
                    6 -> R.drawable.keydesign_10_function
                    7 -> R.drawable.keydesign_02_a
                    8 -> R.drawable.keydesign_03_function
                    9 -> R.drawable.keydesign_06_function
                    10 -> R.drawable.keydesign_07_function
                    11 -> R.drawable.keydesign_07_function
                    12 -> R.drawable.keydesign_07_function
                    13 -> R.drawable.keydesign_07_function
                    14 -> R.drawable.keydesign_07_function
                    15 -> R.drawable.keydesign_15_function
                    16 -> R.drawable.keydesign_16_function
                    17 -> R.drawable.keydesign_07_function
                    18 -> R.drawable.keydesign_18_function
                    else -> return@observe
                }
            )
        }

        viewModel.kbResultedHeight.observe(imeService) {
            imgbtnKrChunLeftInitialize.setPadding((it * 0.05).toInt())
        }

        viewModel.kbKrChunSpacebarPosition.observe(imeService) {
            if (it) {
                binding.apply {
                    if (btnKrChunLeftSpace.parent != null) (btnKrChunLeftSpace.parent as ViewGroup).removeView(btnKrChunLeftSpace)
                    if (imgbtnKrChunLeftInitialize.parent != null) (imgbtnKrChunLeftInitialize.parent as ViewGroup).removeView(imgbtnKrChunLeftInitialize)

                    linearlayoutChunLeft2ndRow.addView(
                        imgbtnKrChunLeftInitialize,
                        3,
                        LinearLayoutCompat.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1F)
                    )

                    linearlayoutChunLeft4thRow.addView(
                        btnKrChunLeftSpace,
                        3,
                        LinearLayoutCompat.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1F)
                    )
                }
            } else {
                binding.apply {
                    if (btnKrChunLeftSpace.parent != null) (btnKrChunLeftSpace.parent as ViewGroup).removeView(btnKrChunLeftSpace)
                    if (imgbtnKrChunLeftInitialize.parent != null) (imgbtnKrChunLeftInitialize.parent as ViewGroup).removeView(imgbtnKrChunLeftInitialize)

                    linearlayoutChunLeft2ndRow.addView(
                        btnKrChunLeftSpace,
                        3,
                        LinearLayoutCompat.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1F)
                    )

                    linearlayoutChunLeft4thRow.addView(
                        imgbtnKrChunLeftInitialize,
                        3,
                        LinearLayoutCompat.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1F)
                    )
                }
            }
        }

        viewModel.kbFontType.observe(imeService) {
            val typeFace = when (it) {
                1 -> ResourcesCompat.getFont(btnKrChunLeftSpace.context, R.font.aritta)
                2 -> ResourcesCompat.getFont(btnKrChunLeftSpace.context, R.font.dovemayo)
                3 -> ResourcesCompat.getFont(btnKrChunLeftSpace.context, R.font.imcresoojin)
                4 -> ResourcesCompat.getFont(btnKrChunLeftSpace.context, R.font.maplestorylight)
                5 -> ResourcesCompat.getFont(btnKrChunLeftSpace.context, R.font.nanumbarungothic)
                6 -> ResourcesCompat.getFont(btnKrChunLeftSpace.context, R.font.nanumsquarer)
                7 -> ResourcesCompat.getFont(btnKrChunLeftSpace.context, R.font.seoulnamsan)
                8 -> ResourcesCompat.getFont(btnKrChunLeftSpace.context, R.font.tttogether)
                9 -> ResourcesCompat.getFont(btnKrChunLeftSpace.context, R.font.cookierun)
                10 -> ResourcesCompat.getFont(btnKrChunLeftSpace.context, R.font.tmoney)
                11 -> ResourcesCompat.getFont(btnKrChunLeftSpace.context, R.font.tadaktadak)
                else -> Typeface.DEFAULT
            }

            if (viewModel.inputTypeState.value!! == InputTypeState.EN_BOLD_UPPER) {
                btnKrChunLeftSpace.setTypeface(typeFace, Typeface.BOLD)
            } else {
                btnKrChunLeftSpace.setTypeface(typeFace, Typeface.NORMAL)
            }
        }

        charKeyList.forEach { btn ->
            btn.setOnClickListener { view ->
                inputCharKey(view)
            }
        }

        binding.apply {
            imgbtnKrChunLeftEnter.setOnClickListener {
                inputEnter()
            }

            imgbtnKrChunLeftEnter.setOnLongClickListener { view ->
                inputKeyLong(keyType = KeyType.Enter)
            }

            btnKrChunLeftSpecial.setOnClickListener {
                viewModel.setInputTypeState(InputTypeState.SPECIAL_FIRST)
                vibrate()
            }

            btnKrChunLeftSpecial.setOnLongClickListener { view ->
                inputKeyLong(keyType = KeyType.Special)
            }

            btnKrChunLeftSpecial.setOnClickListener {
                viewModel.setInputTypeState(InputTypeState.EN_UPPER)
                vibrate()
            }

            imgbtnKrChunLeftLang.setOnClickListener {
                vibrate()
                viewModel.setInputTypeState(InputTypeState.EN_UPPER)
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