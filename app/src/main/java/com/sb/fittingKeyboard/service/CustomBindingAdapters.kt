package com.sb.fittingKeyboard.service

import RepeatListener
import android.graphics.Typeface
import android.os.Build
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.setPadding
import androidx.databinding.BindingAdapter
import com.sb.fittingKeyboard.R

object CustomBindingAdapters {
    @RequiresApi(Build.VERSION_CODES.O)
    @JvmStatic
    @BindingAdapter("fontType")
    fun setFontType(view: View, fontType: Int) {
        val typeFace = when (fontType) {
            1 -> ResourcesCompat.getFont(view.context, R.font.aritta)
            2 -> ResourcesCompat.getFont(view.context, R.font.dovemayo)
            3 -> ResourcesCompat.getFont(view.context, R.font.imcresoojin)
            4 -> ResourcesCompat.getFont(view.context, R.font.maplestorylight)
            5 -> ResourcesCompat.getFont(view.context, R.font.nanumbarungothic)
            6 -> ResourcesCompat.getFont(view.context, R.font.nanumsquarer)
            7 -> ResourcesCompat.getFont(view.context, R.font.seoulnamsan)
            8 -> ResourcesCompat.getFont(view.context, R.font.tttogether)
            9 -> ResourcesCompat.getFont(view.context, R.font.cookierun)
            10 -> ResourcesCompat.getFont(view.context, R.font.tmoney)
            11 -> ResourcesCompat.getFont(view.context, R.font.tadaktadak)
            else -> Typeface.DEFAULT
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @JvmStatic
    @BindingAdapter("fontStyle", "fontBold")
    fun setFontStyle(view: View, fontType: Int, mode: Int) {
        val typeFace = when (fontType) {
            1 -> ResourcesCompat.getFont(view.context, R.font.aritta)
            2 -> ResourcesCompat.getFont(view.context, R.font.dovemayo)
            3 -> ResourcesCompat.getFont(view.context, R.font.imcresoojin)
            4 -> ResourcesCompat.getFont(view.context, R.font.maplestorylight)
            5 -> ResourcesCompat.getFont(view.context, R.font.nanumbarungothic)
            6 -> ResourcesCompat.getFont(view.context, R.font.nanumsquarer)
            7 -> ResourcesCompat.getFont(view.context, R.font.seoulnamsan)
            8 -> ResourcesCompat.getFont(view.context, R.font.tttogether)
            9 -> ResourcesCompat.getFont(view.context, R.font.cookierun)
            10 -> ResourcesCompat.getFont(view.context, R.font.tmoney)
            11 -> ResourcesCompat.getFont(view.context, R.font.tadaktadak)
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
    @BindingAdapter("padding")
    fun setPadding(view: View, padding: Float) {
        view.setPadding((padding * 0.175).toInt())
    }

    @JvmStatic
    @BindingAdapter("onTouch")
    fun setOnTouchListener(view: View, mListener: View.OnTouchListener) {
        view.setOnTouchListener(mListener)
    }
}