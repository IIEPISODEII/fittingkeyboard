package com.sb.fittingKeyboard.keyboardSettings.ui.adapter

interface ItemTouchHelperListener {
    fun onItemMove(from: Int, to: Int): Boolean

    fun onItemSwipe(position: Int)
}