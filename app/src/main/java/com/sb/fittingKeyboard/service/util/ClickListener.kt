package com.sb.fittingKeyboard.service.util

import android.view.MotionEvent
import android.view.View

class ClickListener(
    private var clickListener: View.OnClickListener?
) :
    View.OnTouchListener {
    private var touchedView: View? = null

    override fun onTouch(view: View, motionEvent: MotionEvent): Boolean {
        when (motionEvent.action) {
            MotionEvent.ACTION_DOWN -> {
                touchedView = view
                touchedView!!.isPressed = true
                clickListener?.onClick(view)
                return true
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                touchedView!!.isPressed = false
                touchedView = null
                return false
            }
        }
        return false
    }
}