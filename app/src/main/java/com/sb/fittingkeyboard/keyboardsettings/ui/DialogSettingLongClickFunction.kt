package com.sb.fittingkeyboard.keyboardsettings.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioGroup
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.sb.fittingKeyboard.R

class DialogSettingLongClickFunction : DialogFragment() {
    private var selectedFunction: Int = 0
    private var onRadioButtonSelectListener: OnRadioButtonSelectListener? = null
    fun setOnRadioButtonSelectListener(listener: OnRadioButtonSelectListener) {
        this.onRadioButtonSelectListener = listener
    }

    private lateinit var dialogView: View
    private lateinit var tvTitle: TextView
    private lateinit var radiogroup: RadioGroup
    private var titleText: String = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dialogView = inflater.inflate(R.layout.dialog_setting_longclick, container, false)

        tvTitle = dialogView.findViewById(R.id.tv_settinglongclick_title)
        radiogroup = dialogView.findViewById(R.id.radiogroup_settinglongclick_container)
        setTitle()

        radiogroup.check(
            when (selectedFunction) {
                0 -> R.id.radiobtn_settinglongclick_none
                1 -> R.id.radiobtn_settinglongclick_boilerplate
                2 -> R.id.radiobtn_settinglongclick_cursor_keypad
                3 -> R.id.radiobtn_settinglongclick_number_keypad
                4 -> R.id.radiobtn_settinglongclick_emoji
                else -> R.id.radiobtn_settinglongclick_none
            }
        )
        dialogView.findViewById<Button>(R.id.btn_settinglongclick_save).setOnClickListener {
            selectedFunction = when (radiogroup.checkedRadioButtonId) {
                R.id.radiobtn_settinglongclick_none -> 0
                R.id.radiobtn_settinglongclick_boilerplate -> 1
                R.id.radiobtn_settinglongclick_cursor_keypad -> 2
                R.id.radiobtn_settinglongclick_number_keypad -> 3
                R.id.radiobtn_settinglongclick_emoji -> 4
                else -> 0
            }
            onRadioButtonSelectListener?.onSelect(selectedFunction)
            dismiss()
        }

        return dialogView
    }

    fun initializeSetting(initialSelectedFunction: Int) {
        selectedFunction = initialSelectedFunction
    }

    fun setTitleText(title: String) {
        titleText = title
    }

    private fun setTitle() {
        tvTitle.text = titleText
    }

    interface OnRadioButtonSelectListener {
        fun onSelect(functionIndex: Int)
    }
}