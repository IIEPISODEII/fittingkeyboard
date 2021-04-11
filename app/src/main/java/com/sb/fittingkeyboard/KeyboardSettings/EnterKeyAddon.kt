package com.sb.fittingKeyboard.KeyboardSettings

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

class EnterKeyAddon : DialogFragment() {
    var checkedFunction: Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val dialogView = inflater.inflate(R.layout.dialog_enterkeyaddon, container, false)

        val rg = dialogView.findViewById<RadioGroup>(R.id.dialog_EnterkeyAddonSelect_rg)

        rg.check(when ( checkedFunction ) {
            0 -> R.id.dialog_EnterkeyAddonSelect_none
            1 -> R.id.dialog_EnterkeyAddonSelect_autotext
            2 -> R.id.dialog_EnterkeyAddonSelect_cursor
            else -> R.id.dialog_EnterkeyAddonSelect_none
        })
        dialogView.findViewById<Button>(R.id.dialog_EnterkeyAddonSelect_ok).setOnClickListener {
            checkedFunction = when ( rg.checkedRadioButtonId ) {
                R.id.dialog_EnterkeyAddonSelect_none -> 0
                R.id.dialog_EnterkeyAddonSelect_autotext -> 1
                R.id.dialog_EnterkeyAddonSelect_cursor -> 2
                else -> 0
            }
            activity?.findViewById<Button>(R.id.setting_enterkeyAddon)?.text = when ( checkedFunction ) {
                0 -> "없음"
                1 -> "상용구"
                2 -> "커서이동 및 편집"
                else -> "없음"
            }
            dismiss()
            Toast.makeText(context, "저장되었습니다.", Toast.LENGTH_SHORT).show()
            activity?.getSharedPreferences("keyboardSetting", Context.MODE_PRIVATE)?.edit()?.putInt("KeyboardEnterkeyAddon", checkedFunction)?.apply()
        }

        return dialogView
    }

    fun setFunction(checkPosition: Int) {
        this.checkedFunction = checkPosition
    }
}