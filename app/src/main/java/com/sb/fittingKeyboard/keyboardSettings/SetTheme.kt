package com.sb.fittingKeyboard.keyboardSettings

import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.sb.fittingKeyboard.R

class SetTheme(
    private val layout: ImageView,
    private val array1: Array<Button>,
    private val array2: Array<ImageButton>,
    private val array3: Array<Button>,
    private val array4: Array<ImageButton>,
    private val array5: Array<ImageButton>
) {

    fun setTheme00() {
        layout.setImageResource(R.color.theme_grey)
        for (item in array1) {
            item.setBackgroundResource(R.drawable.keydesign_14_char)
        }
        for (item in array2) {
            item.setBackgroundResource(R.drawable.keydesign_14_char)
        }
        for (item in array3) {
            item.setBackgroundResource(R.drawable.keydesign_14_function)
        }
        for (item in array4) {
            item.setBackgroundResource(R.drawable.keydesign_14_function)
        }
        for (item in array5) {
            item.setBackgroundResource(R.drawable.keydesign_14_function)
        }
    }

    fun setTheme01() {
        layout.setImageResource(R.color.black)
        for (item in array1) {
            item.setBackgroundResource(R.drawable.keydesign_00_char)
        }
        for (item in array2) {
            item.setBackgroundResource(R.drawable.keydesign_00_char)
        }
        for (item in array3) {
            item.setBackgroundResource(R.drawable.keydesign_00_function)
        }
        for (item in array4) {
            item.setBackgroundResource(R.drawable.keydesign_00_function)
        }
        for (item in array5) {
            item.setBackgroundResource(R.drawable.keydesign_00_function)
        }
    }

    fun setTheme02() {
        layout.setImageResource(R.color.pink)
        for (item in array1) {
            item.setBackgroundResource(R.drawable.keydesign_04_char)
        }
        for (item in array2) {
            item.setBackgroundResource(R.drawable.keydesign_04_char)
        }
        for (item in array3) {
            item.setBackgroundResource(R.drawable.keydesign_04_function)
        }
        for (item in array4) {
            item.setBackgroundResource(R.drawable.keydesign_04_function)
        }
        for (item in array5) {
            item.setBackgroundResource(R.drawable.keydesign_04_function)
        }
    }

    fun setTheme03() {
        layout.setImageResource(R.color.mint)
        for (item in array1) {
            item.setBackgroundResource(R.drawable.keydesign_08_char)
        }
        for (item in array2) {
            item.setBackgroundResource(R.drawable.keydesign_08_char)
        }
        for (item in array3) {
            item.setBackgroundResource(R.drawable.keydesign_08_function)
        }
        for (item in array4) {
            item.setBackgroundResource(R.drawable.keydesign_08_function)
        }
        for (item in array5) {
            item.setBackgroundResource(R.drawable.keydesign_08_function)
        }
    }

    fun setTheme04() {
        layout.setImageResource(R.color.blue)
        for (item in array1) {
            item.setBackgroundResource(R.drawable.keydesign_09_char)
        }
        for (item in array2) {
            item.setBackgroundResource(R.drawable.keydesign_09_char)
        }
        for (item in array3) {
            item.setBackgroundResource(R.drawable.keydesign_09_function)
        }
        for (item in array4) {
            item.setBackgroundResource(R.drawable.keydesign_09_function)
        }
        for (item in array5) {
            item.setBackgroundResource(R.drawable.keydesign_09_function)
        }
    }

    fun setTheme05() {
        layout.setImageResource(R.color.purple)
        for (item in array1) {
            item.setBackgroundResource(R.drawable.keydesign_05_char)
        }
        for (item in array2) {
            item.setBackgroundResource(R.drawable.keydesign_05_char)
        }
        for (item in array3) {
            item.setBackgroundResource(R.drawable.keydesign_05_function)
        }
        for (item in array4) {
            item.setBackgroundResource(R.drawable.keydesign_05_function)
        }
        for (item in array5) {
            item.setBackgroundResource(R.drawable.keydesign_05_function)
        }
    }

    fun setTheme06() {
        layout.setImageResource(R.color.orange)
        for (item in array1) {
            item.setBackgroundResource(R.drawable.keydesign_10_char)
        }
        for (item in array2) {
            item.setBackgroundResource(R.drawable.keydesign_10_char)
        }
        for (item in array3) {
            item.setBackgroundResource(R.drawable.keydesign_10_function)
        }
        for (item in array4) {
            item.setBackgroundResource(R.drawable.keydesign_10_function)
        }
        for (item in array5) {
            item.setBackgroundResource(R.drawable.keydesign_10_function)
        }
    }

    fun setTheme07() {
        layout.setImageResource(R.drawable.theme_gradation07)
        for (item in array1) {
            item.setBackgroundResource(R.drawable.keydesign_02_a)
        }
        for (item in array2) {
            item.setBackgroundResource(R.drawable.keydesign_02_a)
        }
        for (item in array3) {
            item.setBackgroundResource(R.drawable.keydesign_02_a)
        }
        for (item in array4) {
            item.setBackgroundResource(R.drawable.keydesign_02_a)
        }
        for (item in array5) {
            item.setBackgroundResource(R.drawable.keydesign_02_a)
        }
    }

    fun setTheme08() {
        layout.setImageResource(R.color.black)
        for (item in array1) {
            item.setBackgroundResource(R.drawable.keydesign_03_char)
        }
        for (item in array2) {
            item.setBackgroundResource(R.drawable.keydesign_03_char)
        }
        for (item in array3) {
            item.setBackgroundResource(R.drawable.keydesign_03_function)
        }
        for (item in array4) {
            item.setBackgroundResource(R.drawable.keydesign_03_function)
        }
        for (item in array5) {
            item.setBackgroundResource(R.drawable.keydesign_03_enter)
        }
    }

    fun setTheme09() {
        layout.setImageResource(R.drawable.theme_abstract)
        for (item in array1) {
            item.setBackgroundResource(R.drawable.keydesign_06_char)
        }
        for (item in array2) {
            item.setBackgroundResource(R.drawable.keydesign_06_char)
        }
        for (item in array3) {
            item.setBackgroundResource(R.drawable.keydesign_06_function)
        }
        for (item in array4) {
            item.setBackgroundResource(R.drawable.keydesign_06_function)
        }
        for (item in array5) {
            item.setBackgroundResource(R.drawable.keydesign_06_enter)
        }
    }

    fun setTheme10() {
        layout.setImageResource(R.drawable.theme_gradient01)
        for (item in array1) {
            item.setBackgroundResource(R.drawable.keydesign_07_char)
        }
        for (item in array2) {
            item.setBackgroundResource(R.drawable.keydesign_07_char)
        }
        for (item in array3) {
            item.setBackgroundResource(R.drawable.keydesign_07_function)
        }
        for (item in array4) {
            item.setBackgroundResource(R.drawable.keydesign_07_function)
        }
        for (item in array5) {
            item.setBackgroundResource(R.drawable.keydesign_07_enter)
        }
    }

    fun setTheme11() {
        layout.setImageResource(R.drawable.theme_gradient02)
        for (item in array1) {
            item.setBackgroundResource(R.drawable.keydesign_07_char)
        }
        for (item in array2) {
            item.setBackgroundResource(R.drawable.keydesign_07_char)
        }
        for (item in array3) {
            item.setBackgroundResource(R.drawable.keydesign_07_function)
        }
        for (item in array4) {
            item.setBackgroundResource(R.drawable.keydesign_07_function)
        }
        for (item in array5) {
            item.setBackgroundResource(R.drawable.keydesign_07_enter)
        }
    }

    fun setTheme12() {
        layout.setImageResource(R.drawable.theme_gradient03)
        for (item in array1) {
            item.setBackgroundResource(R.drawable.keydesign_07_char)
        }
        for (item in array2) {
            item.setBackgroundResource(R.drawable.keydesign_07_char)
        }
        for (item in array3) {
            item.setBackgroundResource(R.drawable.keydesign_07_function)
        }
        for (item in array4) {
            item.setBackgroundResource(R.drawable.keydesign_07_function)
        }
        for (item in array5) {
            item.setBackgroundResource(R.drawable.keydesign_07_enter)
        }
    }

    fun setTheme13() {
        layout.setImageResource(R.drawable.theme_gradient04)
        for (item in array1) {
            item.setBackgroundResource(R.drawable.keydesign_07_char)
        }
        for (item in array2) {
            item.setBackgroundResource(R.drawable.keydesign_07_char)
        }
        for (item in array3) {
            item.setBackgroundResource(R.drawable.keydesign_07_function)
        }
        for (item in array4) {
            item.setBackgroundResource(R.drawable.keydesign_07_function)
        }
        for (item in array5) {
            item.setBackgroundResource(R.drawable.keydesign_07_enter)
        }
    }

    fun setTheme14() {
        layout.setImageResource(R.drawable.theme_gradient05)
        for (item in array1) {
            item.setBackgroundResource(R.drawable.keydesign_07_char)
        }
        for (item in array2) {
            item.setBackgroundResource(R.drawable.keydesign_07_char)
        }
        for (item in array3) {
            item.setBackgroundResource(R.drawable.keydesign_07_function)
        }
        for (item in array4) {
            item.setBackgroundResource(R.drawable.keydesign_07_function)
        }
        for (item in array5) {
            item.setBackgroundResource(R.drawable.keydesign_07_enter)
        }
    }

    fun setTheme15() {
        layout.setImageResource(R.drawable.theme_gradient06)
        for (item in array1) {
            item.setBackgroundResource(R.drawable.keydesign_15_char)
        }
        for (item in array2) {
            item.setBackgroundResource(R.drawable.keydesign_15_char)
        }
        for (item in array3) {
            item.setBackgroundResource(R.drawable.keydesign_15_function)
        }
        for (item in array4) {
            item.setBackgroundResource(R.drawable.keydesign_15_function)
        }
        for (item in array5) {
            item.setBackgroundResource(R.drawable.keydesign_15_function)
        }
    }

    fun setTheme16() {
        layout.setImageResource(R.drawable.theme_wood)
        for (item in array1) {
            item.setBackgroundResource(R.drawable.keydesign_16_char)
        }
        for (item in array2) {
            item.setBackgroundResource(R.drawable.keydesign_16_char)
        }
        for (item in array3) {
            item.setBackgroundResource(R.drawable.keydesign_16_function)
        }
        for (item in array4) {
            item.setBackgroundResource(R.drawable.keydesign_16_function)
        }
        for (item in array5) {
            item.setBackgroundResource(R.drawable.keydesign_16_enter)
        }
    }

    fun setTheme17(view: View) {
        layout.setImageResource(0)
        Glide.with(view).load(R.raw.theme_falling_snow).into(layout)
        for (item in array1) {
            item.setBackgroundResource(R.drawable.keydesign_07_char)
        }
        for (item in array2) {
            item.setBackgroundResource(R.drawable.keydesign_07_char)
        }
        for (item in array3) {
            item.setBackgroundResource(R.drawable.keydesign_07_function)
        }
        for (item in array4) {
            item.setBackgroundResource(R.drawable.keydesign_07_function)
        }
        for (item in array5) {
            item.setBackgroundResource(R.drawable.keydesign_07_enter)
        }
    }

    fun setTheme18() {
        layout.setImageResource(R.drawable.design_theme_18_background)
        for (item in array1) {
            item.setBackgroundResource(R.drawable.keydesign_18_char)
        }
        for (item in array2) {
            item.setBackgroundResource(R.drawable.keydesign_18_char)
        }
        for (item in array3) {
            item.setBackgroundResource(R.drawable.keydesign_18_function)
        }
        for (item in array4) {
            item.setBackgroundResource(R.drawable.keydesign_18_function)
        }
        for (item in array5) {
            item.setBackgroundResource(R.drawable.keydesign_18_function)
        }
    }
}