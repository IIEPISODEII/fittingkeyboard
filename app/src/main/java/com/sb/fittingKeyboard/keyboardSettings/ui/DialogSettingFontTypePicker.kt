package com.sb.fittingKeyboard.keyboardSettings.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.sb.fittingKeyboard.R

class DialogSettingFontTypePicker(fontTypeIndex: Int) : DialogFragment() {
    private var checkedFontTypeIndex = fontTypeIndex
    private var mOnCheckFontTypeListener: OnCheckFontTypeListener? = null
    fun setOnCheckFontTypeListener(listener: OnCheckFontTypeListener) {
        this.mOnCheckFontTypeListener = listener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val dialogView = inflater.inflate(R.layout.dialog_setting_font_type, container, false)

        dialogView.findViewById<RadioGroup>(R.id.radiogroup_settingfonttype_container).check(
            when (checkedFontTypeIndex) {
                0 -> R.id.radiobtn_settingfonttype_basic
                1 -> R.id.radiobtn_settingfonttype_aritta
                2 -> R.id.radiobtn_settingfonttype_dovemayo
                3 -> R.id.radiobtn_settingfonttype_imcresoojin
                4 -> R.id.radiobtn_settingfonttype_maplestorylight
                5 -> R.id.radiobtn_settingfonttype_nanumbarungothic
                6 -> R.id.radiobtn_settingfonttype_nanumsquarer
                7 -> R.id.radiobtn_settingfonttype_seoulnamsan
                8 -> R.id.radiobtn_settingfonttype_tttogether
                9 -> R.id.radiobtn_settingfonttype_cookierun
                10 -> R.id.radiobtn_settingfonttype_tmoney
                11 -> R.id.radiobtn_settingfonttype_tadaktadak
                else -> R.id.radiobtn_settingfonttype_basic
            }
        )
        dialogView.findViewById<Button>(R.id.btn_settingfonttype_save).setOnClickListener {
            checkedFontTypeIndex =
                when (dialog?.findViewById<RadioGroup>(R.id.radiogroup_settingfonttype_container)?.checkedRadioButtonId) {
                    R.id.radiobtn_settingfonttype_basic -> 0
                    R.id.radiobtn_settingfonttype_aritta -> 1
                    R.id.radiobtn_settingfonttype_dovemayo -> 2
                    R.id.radiobtn_settingfonttype_imcresoojin -> 3
                    R.id.radiobtn_settingfonttype_maplestorylight -> 4
                    R.id.radiobtn_settingfonttype_nanumbarungothic -> 5
                    R.id.radiobtn_settingfonttype_nanumsquarer -> 6
                    R.id.radiobtn_settingfonttype_seoulnamsan -> 7
                    R.id.radiobtn_settingfonttype_tttogether -> 8
                    R.id.radiobtn_settingfonttype_cookierun -> 9
                    R.id.radiobtn_settingfonttype_tmoney -> 10
                    R.id.radiobtn_settingfonttype_tadaktadak -> 11
                    else -> 0
                }
            mOnCheckFontTypeListener?.onCheck(checkedFontTypeIndex)
            dismiss()
            Toast.makeText(context, "저장되었습니다.", Toast.LENGTH_SHORT).show()
        }

        return dialogView
    }

    interface OnCheckFontTypeListener {
        fun onCheck(index: Int)
    }
}