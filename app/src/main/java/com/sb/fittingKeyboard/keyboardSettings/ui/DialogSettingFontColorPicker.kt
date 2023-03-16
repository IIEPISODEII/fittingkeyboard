package com.sb.fittingKeyboard.com.sb.fittingKeyboard.keyboardSettings.ui

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.sb.fittingKeyboard.R
import top.defaults.colorpicker.ColorPickerView
import java.util.*


class DialogSettingFontColorPicker : DialogFragment() {
    private var mColor: Int = 0xFF000000.toInt()

    fun setFontColor(color: Int) {
        mColor = color
    }

    private var onPickListener: OnPickColorListener? = null
    fun setOnPickColorListener(listener: OnPickColorListener) {
        this.onPickListener = listener
    }

    private var dialogView: View? = null
    private val colorPickerView by lazy { dialogView!!.findViewById<ColorPickerView>(R.id.colorPicker) }
    private val btnSaveColor by lazy { dialogView!!.findViewById<Button>(R.id.fontColorSave) }
    private val btnCancel by lazy { dialogView!!.findViewById<Button>(R.id.fontColorCancel) }
    private val vPickedColor by lazy { dialogView!!.findViewById<View>(R.id.fontPickedColor) }
    private val tvColorHex by lazy { dialogView!!.findViewById<TextView>(R.id.fontColorHex) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialogView = inflater.inflate(R.layout.dialog_setting_font_color, container, false)

        colorPickerView.apply {
            setInitialColor(mColor)
            setEnabledAlpha(true)
            setEnabledBrightness(true)
            subscribe { selectedColor: Int, _, _ ->
                mColor = selectedColor
                vPickedColor.setBackgroundColor(selectedColor)
                tvColorHex.text = convertColorToHex(selectedColor)
            }
        }

        btnSaveColor.setOnClickListener {
            onPickListener?.onPick(mColor)
            dismiss()
            Toast.makeText(context, "저장되었습니다.", Toast.LENGTH_SHORT).show()
        }

        btnCancel.setOnClickListener {
            dismiss()
        }

        return dialogView
    }

    private fun convertColorToHex(color: Int): String? {
        val a: Int = Color.alpha(color)
        val r: Int = Color.red(color)
        val g: Int = Color.green(color)
        val b: Int = Color.blue(color)
        return java.lang.String.format(Locale.getDefault(), "0x%02X%02X%02X%02X", a, r, g, b)
    }

    interface OnPickColorListener {
        fun onPick(color: Int)
    }
}
