package com.sb.fittingKeyboard

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import top.defaults.colorpicker.ColorPickerView
import java.util.*


class DefaultFontColorPicker() : DialogFragment() {
    companion object {
        var fontColor: Int = 0xFF000000.toInt()
        fun setFontColour(color: Int) {
            fontColor = color
        }
    }

    val companion = Companion

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val dialogView = inflater.inflate(R.layout.dialog_default_font_color, container, false)
        val colorPickerView = dialogView.findViewById<ColorPickerView>(R.id.colorPicker)

        colorPickerView.setInitialColor(fontColor)

        colorPickerView.run {
            setEnabledAlpha(true)
            setEnabledBrightness(true)
            subscribe { color: Int, _, _ ->
                dialogView.findViewById<View>(R.id.fontPickedColor).setBackgroundColor(color)
                dialogView.findViewById<TextView>(R.id.fontColorHex).text = colorHex(color)
            }
        }

        dialogView.findViewById<Button>(R.id.fontColorSave).setOnClickListener {
            fontColor = colorPickerView.color
            dismiss()
            Toast.makeText(context, "저장되었습니다.", Toast.LENGTH_SHORT).show()
        }

        dialogView.findViewById<Button>(R.id.fontColorCancel).setOnClickListener {
            dismiss()
        }

        return dialogView
    }

    private fun colorHex(color: Int): String? {
        val a: Int = Color.alpha(color)
        val r: Int = Color.red(color)
        val g: Int = Color.green(color)
        val b: Int = Color.blue(color)
        return java.lang.String.format(Locale.getDefault(), "0x%02X%02X%02X%02X", a, r, g, b)
    }
}
