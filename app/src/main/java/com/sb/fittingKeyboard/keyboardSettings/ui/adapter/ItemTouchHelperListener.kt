package com.sb.fittingKeyboard.keyboardsettings.ui.adapter

interface ItemTouchHelperListener {
    fun onItemMove(from: Int, to: Int): Boolean

    fun onItemSwipe(position: Int)
}