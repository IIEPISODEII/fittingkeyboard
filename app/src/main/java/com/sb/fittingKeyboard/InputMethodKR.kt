package com.sb.fittingKeyboard

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.dialog_inputmethodkr.*
import kotlinx.android.synthetic.main.dialog_inputmethodkr.view.*

class InputMethodKR : DialogFragment() {
    var checkedFunction: Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val dialogView = inflater.inflate(R.layout.dialog_inputmethodkr, container, false)

        dialogView.dialog_InputMethodKRSelect_rg.check(when ( checkedFunction ) {
            0 -> R.id.dialog_InputMethodKRSelect_QWERTY
            1 -> R.id.dialog_InputMethodKRSelect_CHUN
            2 -> R.id.dialog_InputMethodKRSelect_NA
            3 -> R.id.dialog_InputMethodKRSelect_CHUNA
            else -> R.id.dialog_InputMethodKRSelect_QWERTY
        })
        dialogView.dialog_InputMethodKRSelect_ok.setOnClickListener {
            checkedFunction = when ( dialog_InputMethodKRSelect_rg.checkedRadioButtonId ) {
                R.id.dialog_InputMethodKRSelect_QWERTY -> 0
                R.id.dialog_InputMethodKRSelect_CHUN -> 1
                R.id.dialog_InputMethodKRSelect_NA -> 2
                R.id.dialog_InputMethodKRSelect_CHUNA -> 3
                else -> 0
            }
            dismiss()
            Toast.makeText(context, "저장되었습니다.", Toast.LENGTH_SHORT).show()
            activity?.findViewById<TextView>(R.id.setting_inputMethodKR)?.text = when ( checkedFunction ) {
                0 -> "쿼티(기본)"
                1 -> "천지인"
                2 -> "나랏글"
                3 -> "천지인 양손"
                else -> "쿼티(기본)"
            }
            activity?.getSharedPreferences("keyboardSetting", Context.MODE_PRIVATE)?.edit()?.putInt("KeyboardInputMethodKR", checkedFunction)?.apply()
        }

        return dialogView
    }

    fun setFunction(checkPosition: Int) {
        this.checkedFunction = checkPosition
    }
}