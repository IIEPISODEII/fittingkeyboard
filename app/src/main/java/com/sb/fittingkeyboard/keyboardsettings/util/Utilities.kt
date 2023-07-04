package com.sb.fittingkeyboard.keyboardsettings.util

import android.content.Context
import android.content.DialogInterface
import android.content.res.Resources

fun changeDPtoPX(dp: Int): Float {
    return dp * (Resources.getSystem().displayMetrics.density)
}

fun showHelpDialog(text: String, context: Context) {
    val builder = android.app.AlertDialog.Builder(context)
    builder.setTitle("도움말")
    builder.setMessage(text)
    builder.setPositiveButton("확인") { _: DialogInterface?, _: Int -> }
    builder.show()
}