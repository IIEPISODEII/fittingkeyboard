package com.sb.fittingKeyboard.keyboardSettings

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.sb.fittingKeyboard.R
import kotlinx.android.synthetic.main.admin_keyboard.view.*

class AdminKeyboard() : DialogFragment() {

    private lateinit var mainActivity: MainActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mainActivity = MainActivity()
        val dialogView = inflater.inflate(R.layout.admin_keyboard, container, false)


        dialogView.dialog_AdminKeyboard_later.setOnClickListener {
            dismiss()
        }

        dialogView.dialog_AdminKeyboard_ok.setOnClickListener {
            dismiss()
            startActivityForResult(Intent(Settings.ACTION_INPUT_METHOD_SETTINGS), 0)
        }

        return dialogView
    }
}