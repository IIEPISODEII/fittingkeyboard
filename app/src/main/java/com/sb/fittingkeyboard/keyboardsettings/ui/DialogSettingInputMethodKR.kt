package com.sb.fittingkeyboard.keyboardsettings.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.sb.fittingKeyboard.R

class DialogSettingInputMethodKR : DialogFragment() {
    var checkedFunction: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val dialogView = inflater.inflate(R.layout.dialog_setting_ime_kr, container, false)

        dialogView.findViewById<RadioGroup>(R.id.radiogroup_settingimekr_container).check(
            when (checkedFunction) {
                0 -> R.id.radiobtn_settingimekr_qwerty
                1 -> R.id.radiobtn_settingimekr_chun
                2 -> R.id.radiobtn_settingimekr_narat
                3 -> R.id.radiobtn_settingimekr_chun_ambi
                4 -> R.id.radiobtn_settingimekr_danmo
                else -> R.id.radiobtn_settingimekr_qwerty
            }
        )
        dialogView.findViewById<Button>(R.id.btn_settingimekr_save).setOnClickListener {
            checkedFunction =
                when (dialogView.findViewById<RadioGroup>(R.id.radiogroup_settingimekr_container).checkedRadioButtonId) {
                    R.id.radiobtn_settingimekr_qwerty -> 0
                    R.id.radiobtn_settingimekr_chun -> 1
                    R.id.radiobtn_settingimekr_narat -> 2
                    R.id.radiobtn_settingimekr_chun_ambi -> 3
                    R.id.radiobtn_settingimekr_danmo -> 4
                    else -> 0
                }
            dismiss()
            Toast.makeText(context, "저장되었습니다.", Toast.LENGTH_SHORT).show()
            activity?.findViewById<TextView>(R.id.btn_basicsetting_ime_kr)?.text =
                when (checkedFunction) {
                    0 -> "쿼티(기본)"
                    1 -> "천지인"
                    2 -> "나랏글"
                    3 -> "천지인 양손"
                    4 -> "단모음"
                    else -> "쿼티(기본)"
                }
            activity?.getSharedPreferences("keyboardSetting", Context.MODE_PRIVATE)?.edit()
                ?.putInt("KeyboardInputMethodKR", checkedFunction)?.apply()
        }

        return dialogView
    }

    fun setFunction(checkPosition: Int) {
        this.checkedFunction = checkPosition
    }
}