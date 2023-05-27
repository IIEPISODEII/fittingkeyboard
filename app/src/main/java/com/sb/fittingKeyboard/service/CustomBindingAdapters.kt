package com.sb.fittingKeyboard.service

import android.content.Intent
import android.content.res.Resources
import android.graphics.Typeface
import android.os.Build
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.startActivity
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.setPadding
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.sb.fittingKeyboard.R

object CustomBindingAdapters {

    @JvmStatic
    @BindingAdapter("height", "height_weight")
    fun setLiveHeight(view: View, height: Float, ratio: Int) {
        val layoutParams = view.layoutParams.apply {
            this.height = (height * ratio * 0.01).toInt()
        }
        view.layoutParams = layoutParams
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @JvmStatic
    @BindingAdapter("customFontStyle", "fontBold")
    fun setFontStyle(view: View, fontType: Int, mode: Int) {
        val mContext = view.context

        val typeFace = when (fontType) {
            1 -> ResourcesCompat.getFont(mContext, R.font.aritta)
            2 -> ResourcesCompat.getFont(mContext, R.font.dovemayo)
            3 -> ResourcesCompat.getFont(mContext, R.font.imcresoojin)
            4 -> ResourcesCompat.getFont(mContext, R.font.maplestorylight)
            5 -> ResourcesCompat.getFont(mContext, R.font.nanumbarungothic)
            6 -> ResourcesCompat.getFont(mContext, R.font.nanumsquarer)
            7 -> ResourcesCompat.getFont(mContext, R.font.seoulnamsan)
            8 -> ResourcesCompat.getFont(mContext, R.font.tttogether)
            9 -> ResourcesCompat.getFont(mContext, R.font.cookierun)
            10 -> ResourcesCompat.getFont(mContext, R.font.tmoney)
            11 -> ResourcesCompat.getFont(mContext, R.font.tadaktadak)
            else -> Typeface.DEFAULT
        }

        if (mode == 0) {
            (view as Button).setTypeface(typeFace, Typeface.BOLD)
        } else {
            (view as Button).setTypeface(typeFace, Typeface.NORMAL)
        }
    }

    @JvmStatic
    @BindingAdapter("image")
    fun setImage(view: View, mode: Int) {
        val mImage: Int = when (mode) {
            1, 4, 6 -> R.drawable.keyic_shift_activated_black
            2, 3, 5 -> R.drawable.keyic_shift_deactivated_black
            0 -> R.drawable.keyic_shift_hyperactivated_black
            else -> R.drawable.keyic_shift_deactivated_black
        }
        (view as ImageView).setImageResource(mImage)
    }

    @JvmStatic
    @BindingAdapter("onTouch")
    fun setOnTouchListener(view: View, mListener: View.OnTouchListener) {
        view.setOnTouchListener(mListener)
    }

    /**
     * @btnClass: indicates view kind.
     * 0 - Button(char key),
     * 1 - ImageButton(char key),
     * 2 - Button(function key),
     * 3 - ImageButton(function key)
     * 4 - ImageButton(enter key)
     * 5 -> BackgroundImage
     * 6 -> Button(Blank)
     *
     * @theme: indicates theme index user has selected.
     */
    @JvmStatic
    @BindingAdapter("customTheme_view", "customTheme_theme", "customTheme_padding")
    fun setCustomTheme(view: View, btnClass: Int, theme: Int, padding: Float) {
        val mDrawableResource = when (theme) {
            0 -> {
                when (btnClass) {
                    0 -> R.drawable.keydesign_14_char
                    1 -> R.drawable.keydesign_14_char
                    2 -> R.drawable.keydesign_14_function
                    3 -> R.drawable.keydesign_14_function
                    4 -> R.drawable.keydesign_14_function
                    5 -> R.color.theme_grey
                    6 -> R.color.transparent
                    else -> return
                }
            }
            1 -> {
                when (btnClass) {
                    0 -> R.drawable.keydesign_00_char
                    1 -> R.drawable.keydesign_00_char
                    2 -> R.drawable.keydesign_00_function
                    3 -> R.drawable.keydesign_00_function
                    4 -> R.drawable.keydesign_00_function
                    5 -> R.color.black
                    6 -> R.color.transparent
                    else -> return
                }
            }
            2 -> {
                when (btnClass) {
                    0 -> R.drawable.keydesign_04_char
                    1 -> R.drawable.keydesign_04_char
                    2 -> R.drawable.keydesign_04_function
                    3 -> R.drawable.keydesign_04_function
                    4 -> R.drawable.keydesign_04_function
                    5 -> R.color.pink
                    6 -> R.color.transparent
                    else -> return
                }
            }
            3 -> {
                when (btnClass) {
                    0 -> R.drawable.keydesign_08_char
                    1 -> R.drawable.keydesign_08_char
                    2 -> R.drawable.keydesign_08_function
                    3 -> R.drawable.keydesign_08_function
                    4 -> R.drawable.keydesign_08_function
                    5 -> R.color.mint
                    6 -> R.color.transparent
                    else -> return
                }
            }
            4 -> {
                when (btnClass) {
                    0 -> R.drawable.keydesign_09_char
                    1 -> R.drawable.keydesign_09_char
                    2 -> R.drawable.keydesign_09_function
                    3 -> R.drawable.keydesign_09_function
                    4 -> R.drawable.keydesign_09_function
                    5 -> R.color.blue
                    6 -> R.color.transparent
                    else -> return
                }
            }
            5 -> {
                when (btnClass) {
                    0 -> R.drawable.keydesign_05_char
                    1 -> R.drawable.keydesign_05_char
                    2 -> R.drawable.keydesign_05_function
                    3 -> R.drawable.keydesign_05_function
                    4 -> R.drawable.keydesign_05_function
                    5 -> R.color.purple
                    6 -> R.color.transparent
                    else -> return
                }
            }
            6 -> {
                when (btnClass) {
                    0 -> R.drawable.keydesign_10_char
                    1 -> R.drawable.keydesign_10_char
                    2 -> R.drawable.keydesign_10_function
                    3 -> R.drawable.keydesign_10_function
                    4 -> R.drawable.keydesign_10_function
                    5 -> R.color.orange
                    6 -> R.color.transparent
                    else -> return
                }
            }
            7 -> {
                when (btnClass) {
                    0 -> R.drawable.keydesign_02_a
                    1 -> R.drawable.keydesign_02_a
                    2 -> R.drawable.keydesign_02_a
                    3 -> R.drawable.keydesign_02_a
                    4 -> R.drawable.keydesign_02_a
                    5 -> R.drawable.theme_gradation07
                    6 -> R.color.black
                    else -> return
                }
            }
            8 -> {
                when (btnClass) {
                    0 -> R.drawable.keydesign_03_char
                    1 -> R.drawable.keydesign_03_char
                    2 -> R.drawable.keydesign_03_function
                    3 -> R.drawable.keydesign_03_function
                    4 -> R.drawable.keydesign_03_enter
                    5 -> R.color.black
                    6 -> R.color.transparent
                    else -> return
                }
            }
            9 -> {
                when (btnClass) {
                    0 -> R.drawable.keydesign_06_char
                    1 -> R.drawable.keydesign_06_char
                    2 -> R.drawable.keydesign_06_function
                    3 -> R.drawable.keydesign_06_function
                    4 -> R.drawable.keydesign_06_enter
                    5 -> R.drawable.theme_abstract
                    6 -> R.color.transparent
                    else -> return
                }
            }
            10 -> {
                when (btnClass) {
                    0 -> R.drawable.keydesign_07_char
                    1 -> R.drawable.keydesign_07_char
                    2 -> R.drawable.keydesign_07_function
                    3 -> R.drawable.keydesign_07_function
                    4 -> R.drawable.keydesign_07_enter
                    5 -> R.drawable.theme_gradient01
                    6 -> R.color.transparent
                    else -> return
                }
            }
            11 -> {
                when (btnClass) {
                    0 -> R.drawable.keydesign_07_char
                    1 -> R.drawable.keydesign_07_char
                    2 -> R.drawable.keydesign_07_function
                    3 -> R.drawable.keydesign_07_function
                    4 -> R.drawable.keydesign_07_enter
                    5 -> R.drawable.theme_gradient02
                    6 -> R.color.transparent
                    else -> return
                }
            }
            12 -> {
                when (btnClass) {
                    0 -> R.drawable.keydesign_07_char
                    1 -> R.drawable.keydesign_07_char
                    2 -> R.drawable.keydesign_07_function
                    3 -> R.drawable.keydesign_07_function
                    4 -> R.drawable.keydesign_07_enter
                    5 -> R.drawable.theme_gradient03
                    6 -> R.color.transparent
                    else -> return
                }
            }
            13 -> {
                when (btnClass) {
                    0 -> R.drawable.keydesign_07_char
                    1 -> R.drawable.keydesign_07_char
                    2 -> R.drawable.keydesign_07_function
                    3 -> R.drawable.keydesign_07_function
                    4 -> R.drawable.keydesign_07_enter
                    5 -> R.drawable.theme_gradient04
                    6 -> R.color.transparent
                    else -> return
                }
            }
            14 -> {
                when (btnClass) {
                    0 -> R.drawable.keydesign_07_char
                    1 -> R.drawable.keydesign_07_char
                    2 -> R.drawable.keydesign_07_function
                    3 -> R.drawable.keydesign_07_function
                    4 -> R.drawable.keydesign_07_enter
                    5 -> R.drawable.theme_gradient05
                    6 -> R.color.transparent
                    else -> return
                }
            }
            15 -> {
                when (btnClass) {
                    0 -> R.drawable.keydesign_15_char
                    1 -> R.drawable.keydesign_15_char
                    2 -> R.drawable.keydesign_15_function
                    3 -> R.drawable.keydesign_15_function
                    4 -> R.drawable.keydesign_15_function
                    5 -> R.drawable.theme_gradient06
                    6 -> R.color.transparent
                    else -> return
                }
            }
            16 -> {
                when (btnClass) {
                    0 -> R.drawable.keydesign_16_char
                    1 -> R.drawable.keydesign_16_char
                    2 -> R.drawable.keydesign_16_function
                    3 -> R.drawable.keydesign_16_function
                    4 -> R.drawable.keydesign_16_enter
                    5 -> R.drawable.theme_wood
                    6 -> R.color.transparent
                    else -> return
                }
            }
            17 -> {
                when (btnClass) {
                    0 -> R.drawable.keydesign_07_char
                    1 -> R.drawable.keydesign_07_char
                    2 -> R.drawable.keydesign_07_function
                    3 -> R.drawable.keydesign_07_function
                    4 -> R.drawable.keydesign_07_enter
                    5 -> {
                        Glide.with(view).load(R.raw.theme_falling_snow).into((view as ImageView))
                        0
                    }
                    6 -> R.color.transparent
                    else -> return
                }
            }
            18 -> {
                when (btnClass) {
                    0 -> R.drawable.keydesign_18_char
                    1 -> R.drawable.keydesign_18_char
                    2 -> R.drawable.keydesign_18_function
                    3 -> R.drawable.keydesign_18_function
                    4 -> R.drawable.keydesign_18_function
                    5 -> R.drawable.design_theme_18_background
                    6 -> R.color.transparent
                    else -> return
                }
            }
            else -> return
        }

        when (view) {
            is ImageButton -> {
                view.setBackgroundResource(mDrawableResource)
                view.setPadding((padding * 0.05).toInt())
            }
            is ImageView -> view.setImageResource(mDrawableResource)
            else -> view.setBackgroundResource(mDrawableResource)
        }
    }

    @JvmStatic
    @BindingAdapter("emojiButton_theme")
    fun setEmojiTheme(view: View, theme: Int) {
        if (theme == 7) view.setBackgroundResource(R.color.black)
        else view.setBackgroundResource(0)
    }

    @JvmStatic
    @BindingAdapter("colorFilter")
    fun setImageColorFilter(view: View, color: Int) {
        if (view is ImageView) view.setColorFilter(color)
    }

    @JvmStatic
    @BindingAdapter("layout_weight", "weight_constant")
    fun setLayoutWeight(view: View, mWeight: Float, weightConstant: Float) {
        val mLayoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, mWeight * weightConstant)
        view.layoutParams = mLayoutParams
    }
}