package com.sb.fittingKeyboard.keyboardSettings

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

class InputMethodKR : DialogFragment() {
    var checkedFunction: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val dialogView = inflater.inflate(R.layout.dialog_inputmethodkr, container, false)

        dialogView.findViewById<RadioGroup>(R.id.dialog_InputMethodKRSelect_rg).check(
            when (checkedFunction) {
                0 -> R.id.dialog_InputMethodKRSelect_QWERTY
                1 -> R.id.dialog_InputMethodKRSelect_CHUN
                2 -> R.id.dialog_InputMethodKRSelect_NA
                3 -> R.id.dialog_InputMethodKRSelect_CHUNA
                4 -> R.id.dialog_InputMethodKRSelect_DANMOUM
                else -> R.id.dialog_InputMethodKRSelect_QWERTY
            }
        )
        dialogView.findViewById<Button>(R.id.dialog_InputMethodKRSelect_ok).setOnClickListener {
            checkedFunction =
                when (dialogView.findViewById<RadioGroup>(R.id.dialog_InputMethodKRSelect_rg).checkedRadioButtonId) {
                    R.id.dialog_InputMethodKRSelect_QWERTY -> 0
                    R.id.dialog_InputMethodKRSelect_CHUN -> 1
                    R.id.dialog_InputMethodKRSelect_NA -> 2
                    R.id.dialog_InputMethodKRSelect_CHUNA -> 3
                    R.id.dialog_InputMethodKRSelect_DANMOUM -> 4
                    else -> 0
                }
            dismiss()
            Toast.makeText(context, "저장되었습니다.", Toast.LENGTH_SHORT).show()
            activity?.findViewById<TextView>(R.id.setting_inputMethodKR)?.text =
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