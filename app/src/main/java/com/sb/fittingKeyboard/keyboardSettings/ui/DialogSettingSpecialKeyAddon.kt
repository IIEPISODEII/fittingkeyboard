package com.sb.fittingKeyboard.com.sb.fittingKeyboard.keyboardSettings.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.sb.fittingKeyboard.R

class DialogSettingSpecialKeyAddon() : DialogFragment() {
    var checkedFunction: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val dialogView = inflater.inflate(R.layout.dialog_specialkeyaddon, container, false)

        dialogView.findViewById<RadioGroup>(R.id.dialog_SpecialkeyAddonSelect_rg).check(
            when (checkedFunction) {
                0 -> R.id.dialog_SpecialkeyAddonSelect_none
                1 -> R.id.dialog_SpecialkeyAddonSelect_autotext
                2 -> R.id.dialog_SpecialkeyAddonSelect_cursor
                else -> R.id.dialog_SpecialkeyAddonSelect_none
            }
        )
        dialogView.findViewById<Button>(R.id.dialog_SpecialkeyAddonSelect_ok).setOnClickListener {
            checkedFunction =
                when (dialogView.findViewById<RadioGroup>(R.id.dialog_SpecialkeyAddonSelect_rg).checkedRadioButtonId) {
                    R.id.dialog_SpecialkeyAddonSelect_none -> 0
                    R.id.dialog_SpecialkeyAddonSelect_autotext -> 1
                    R.id.dialog_SpecialkeyAddonSelect_cursor -> 2
                    else -> 0
                }
            activity?.findViewById<Button>(R.id.setting_specialkeyAddon)?.text =
                when (checkedFunction) {
                    0 -> "없음"
                    1 -> "상용구"
                    2 -> "커서이동 및 편집"
                    else -> "없음"
                }
            dismiss()
            Toast.makeText(context, "저장되었습니다.", Toast.LENGTH_SHORT).show()
            activity?.getSharedPreferences("keyboardSetting", Context.MODE_PRIVATE)?.edit()
                ?.putInt("KeyboardSpecialkeyAddon", checkedFunction)?.apply()
        }

        return dialogView
    }

    fun setFunction(checkPosition: Int) {
        this.checkedFunction = checkPosition
    }
}