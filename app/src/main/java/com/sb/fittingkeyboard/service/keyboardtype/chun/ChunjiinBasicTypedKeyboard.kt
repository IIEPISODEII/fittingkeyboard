package com.sb.fittingKeyboard.service.keyboardtype.chun

import android.annotation.SuppressLint
import android.content.res.Resources
import android.graphics.PorterDuff
import android.graphics.Typeface
import android.os.Build
import android.util.TypedValue
import android.view.Gravity.CENTER_HORIZONTAL
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import androidx.core.view.children
import androidx.core.view.setPadding
import com.bumptech.glide.Glide
import com.sb.fittingKeyboard.R
import com.sb.fittingKeyboard.databinding.FragmentKeyboardChunjiinBasicBinding
import com.sb.fittingKeyboard.service.keyboardtype.core.defaultFontSize
import com.sb.fittingKeyboard.service.MainKeyboardService
import com.sb.fittingKeyboard.service.keyboardtype.core.InputTypeState
import com.sb.fittingKeyboard.service.keyboardtype.core.TypedKeyboard
import com.sb.fittingKeyboard.service.ui.CompoundButton
import com.sb.fittingKeyboard.service.util.RepeatTouchListener
import com.sb.fittingKeyboard.service.util.SwipeableButtonTouchListener
import com.sb.fittingKeyboard.service.viewmodel.KeyboardViewModel

class ChunjiinBasicTypedKeyboard(
    private val binding: FragmentKeyboardChunjiinBasicBinding,
    private val imeService: MainKeyboardService
    ): TypedKeyboard(binding.kbviewmodel!!, imeService) {

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

        val imgbtnKrChunInitialize = ImageButton(binding.root.context).apply {
            LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1F)
                .apply {
                    gravity = CENTER_HORIZONTAL
                }.also { mLayoutParams ->
                    layoutParams = mLayoutParams
                }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) elevation = 2 * (Resources.getSystem().displayMetrics.density)

            setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.keyic_arrowright_black, null))
        }

        val btnKrChunSpace = CompoundButton(binding.root.context).apply {
            LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1F)
                .apply {
                    gravity = CENTER_HORIZONTAL
                }.also { mLayoutParams ->
                    layoutParams = mLayoutParams
                }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) elevation = 2 * (Resources.getSystem().displayMetrics.density)

            text = context.getString(R.string.space)
        }

        if (viewModel.kbKrChunSpacebarPosition.value!!) {
            binding.apply {
                linearlayoutChun2ndRow.addView(
                    imgbtnKrChunInitialize,
                    3,
                    LinearLayoutCompat.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1F)
                )

                linearlayoutChun4thRow.addView(
                    btnKrChunSpace,
                    3,
                    LinearLayoutCompat.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1F)
                )
            }
        } else {
            binding.apply {
                linearlayoutChun2ndRow.addView(
                    btnKrChunSpace,
                    3,
                    LinearLayoutCompat.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1F)
                )

                linearlayoutChun4thRow.addView(
                    imgbtnKrChunInitialize,
                    3,
                    LinearLayoutCompat.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1F)
                )
            }
        }


        viewModel.kbHasSwipeableSpace.observe(imeService) {
            btnKrChunSpace.apply {
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
            imgbtnKrChunInitialize.setColorFilter(it)
        }

        viewModel.kbFunctionKeysFontColor.observe(imeService) {
            btnKrChunSpace.setCompoundDrawablesWithIntrinsicBounds(
                if (viewModel.kbHasSwipeableSpace.value!!) ResourcesCompat.getDrawable(btnKrChunSpace.resources, R.drawable.keyic_arrowleft_black, null)?.let { drawable ->
                    drawable.clearColorFilter()
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) drawable.colorFilter = BlendModeColorFilterCompat.createBlendModeColorFilterCompat(it, BlendModeCompat.SRC_ATOP)
                    else drawable.setColorFilter(it, PorterDuff.Mode.SRC_ATOP)
                    drawable
                } else null,
                null,
                if (viewModel.kbHasSwipeableSpace.value!!) ResourcesCompat.getDrawable(btnKrChunSpace.resources, R.drawable.keyic_arrowright_black, null)?.let { drawable ->
                    drawable.clearColorFilter()
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) drawable.colorFilter = BlendModeColorFilterCompat.createBlendModeColorFilterCompat(it, BlendModeCompat.SRC_ATOP)
                    else drawable.setColorFilter(it, PorterDuff.Mode.SRC_ATOP)
                    drawable
                } else null,
                null
            )

            btnKrChunSpace.setTextColor(it)
        }

        viewModel.kbFontSize.observe(imeService) {
            if (viewModel.kbHasSwipeableSpace.value!!) btnKrChunSpace.setTextSize(TypedValue.COMPLEX_UNIT_SP, defaultFontSize)
            else btnKrChunSpace.textSize = it / binding.root.context.resources.displayMetrics.density
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

        viewModel.kbTheme.observe(imeService) {
            imgbtnKrChunInitialize.setBackgroundResource(
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

            btnKrChunSpace.setBackgroundResource(
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
            imgbtnKrChunInitialize.setPadding((it * 0.05).toInt())
        }

        viewModel.kbKrChunSpacebarPosition.observe(imeService) {
            if (it) {
                binding.apply {
                    if (btnKrChunSpace.parent != null) (btnKrChunSpace.parent as ViewGroup).removeView(btnKrChunSpace)
                    if (imgbtnKrChunInitialize.parent != null) (imgbtnKrChunInitialize.parent as ViewGroup).removeView(imgbtnKrChunInitialize)

                    linearlayoutChun2ndRow.addView(
                        imgbtnKrChunInitialize,
                        3,
                        LinearLayoutCompat.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1F)
                    )

                    linearlayoutChun4thRow.addView(
                        btnKrChunSpace,
                        3,
                        LinearLayoutCompat.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1F)
                    )
                }
            } else {
                binding.apply {
                    if (btnKrChunSpace.parent != null) (btnKrChunSpace.parent as ViewGroup).removeView(btnKrChunSpace)
                    if (imgbtnKrChunInitialize.parent != null) (imgbtnKrChunInitialize.parent as ViewGroup).removeView(imgbtnKrChunInitialize)

                    linearlayoutChun2ndRow.addView(
                        btnKrChunSpace,
                        3,
                        LinearLayoutCompat.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1F)
                    )

                    linearlayoutChun4thRow.addView(
                        imgbtnKrChunInitialize,
                        3,
                        LinearLayoutCompat.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1F)
                    )
                }
            }
        }

        viewModel.kbFontType.observe(imeService) {
            val typeFace = when (it) {
                1 -> ResourcesCompat.getFont(btnKrChunSpace.context, R.font.aritta)
                2 -> ResourcesCompat.getFont(btnKrChunSpace.context, R.font.dovemayo)
                3 -> ResourcesCompat.getFont(btnKrChunSpace.context, R.font.imcresoojin)
                4 -> ResourcesCompat.getFont(btnKrChunSpace.context, R.font.maplestorylight)
                5 -> ResourcesCompat.getFont(btnKrChunSpace.context, R.font.nanumbarungothic)
                6 -> ResourcesCompat.getFont(btnKrChunSpace.context, R.font.nanumsquarer)
                7 -> ResourcesCompat.getFont(btnKrChunSpace.context, R.font.seoulnamsan)
                8 -> ResourcesCompat.getFont(btnKrChunSpace.context, R.font.tttogether)
                9 -> ResourcesCompat.getFont(btnKrChunSpace.context, R.font.cookierun)
                10 -> ResourcesCompat.getFont(btnKrChunSpace.context, R.font.tmoney)
                11 -> ResourcesCompat.getFont(btnKrChunSpace.context, R.font.tadaktadak)
                else -> Typeface.DEFAULT
            }

            if (viewModel.inputTypeState.value!! == InputTypeState.EN_BOLD_UPPER) {
                btnKrChunSpace.setTypeface(typeFace, Typeface.BOLD)
            } else {
                btnKrChunSpace.setTypeface(typeFace, Typeface.NORMAL)
            }
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
                inputKeyLong(keyType = KeyType.Enter)
            }

            btnKrChunSpecial.setOnClickListener { view ->
                vibrate()
                viewModel.setInputTypeState(InputTypeState.SPECIAL_FIRST)
            }

            btnKrChunSpecial.setOnLongClickListener { view ->
                inputKeyLong(keyType = KeyType.Special)
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