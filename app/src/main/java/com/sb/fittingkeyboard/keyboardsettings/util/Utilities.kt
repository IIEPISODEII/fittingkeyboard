package com.sb.fittingkeyboard.keyboardsettings.util

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.res.Resources

fun changeDPtoPX(dp: Int): Float {
    return dp * (Resources.getSystem().displayMetrics.density)
}

fun showHelpDialog(text: String, context: Context) {
    android.app.AlertDialog.Builder(context)
        .setTitle("도움말")
        .setMessage(text)
        .setPositiveButton("확인") { _: DialogInterface?, _: Int -> }
        .show()
}