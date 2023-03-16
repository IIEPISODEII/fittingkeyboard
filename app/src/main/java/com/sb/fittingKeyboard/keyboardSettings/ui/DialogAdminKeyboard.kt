package com.sb.fittingKeyboard.com.sb.fittingKeyboard.keyboardSettings.ui

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import com.sb.fittingKeyboard.R

class DialogAdminKeyboard : DialogFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val dialogView = inflater.inflate(R.layout.admin_keyboard, container, false)


        dialogView.findViewById<Button>(R.id.dialog_AdminKeyboard_later).setOnClickListener {
            dismiss()
        }

        dialogView.findViewById<Button>(R.id.dialog_AdminKeyboard_ok).setOnClickListener {
            dismiss()
            startActivityForResult(Intent(Settings.ACTION_INPUT_METHOD_SETTINGS), 0)
        }

        return dialogView
    }
}