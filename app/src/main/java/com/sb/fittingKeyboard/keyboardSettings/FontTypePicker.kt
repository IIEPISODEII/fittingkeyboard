package com.sb.fittingKeyboard.keyboardSettings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.sb.fittingKeyboard.R

class FontTypePicker : DialogFragment() {

    var checkedFontType: Int = 1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val dialogView = inflater.inflate(R.layout.dialog_font_type, container, false)

        dialogView.findViewById<RadioGroup>(R.id.dialog_FontType_rg).check(
            when (checkedFontType) {
                0 -> R.id.dialog_FontType_basic
                1 -> R.id.dialog_FontType_aritta
                2 -> R.id.dialog_FontType_dovemayo
                3 -> R.id.dialog_FontType_imcresoojin
                4 -> R.id.dialog_FontType_maplestorylight
                5 -> R.id.dialog_FontType_nanumbarungothic
                6 -> R.id.dialog_FontType_nanumsquarer
                7 -> R.id.dialog_FontType_seoulnamsan
                8 -> R.id.dialog_FontType_tttogether
                9 -> R.id.dialog_FontType_cookierun
                10 -> R.id.dialog_FontType_tmoney
                11 -> R.id.dialog_FontType_tadaktadak
                else -> R.id.dialog_FontType_basic
            }
        )
        dialogView.findViewById<Button>(R.id.dialog_FontType_ok).setOnClickListener {
            checkedFontType =
                when (dialog?.findViewById<RadioGroup>(R.id.dialog_FontType_rg)?.checkedRadioButtonId) {
                    R.id.dialog_FontType_basic -> 0
                    R.id.dialog_FontType_aritta -> 1
                    R.id.dialog_FontType_dovemayo -> 2
                    R.id.dialog_FontType_imcresoojin -> 3
                    R.id.dialog_FontType_maplestorylight -> 4
                    R.id.dialog_FontType_nanumbarungothic -> 5
                    R.id.dialog_FontType_nanumsquarer -> 6
                    R.id.dialog_FontType_seoulnamsan -> 7
                    R.id.dialog_FontType_tttogether -> 8
                    R.id.dialog_FontType_cookierun -> 9
                    R.id.dialog_FontType_tmoney -> 10
                    R.id.dialog_FontType_tadaktadak -> 11
                    else -> 0
                }
            dismiss()
            Toast.makeText(context, "?????????????????????.", Toast.LENGTH_SHORT).show()
        }

        return dialogView
    }

    fun setFontType(checkPosition: Int) {
        this.checkedFontType = checkPosition
    }
}