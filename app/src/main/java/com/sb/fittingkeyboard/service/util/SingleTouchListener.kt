package com.sb.fittingKeyboard.service.util

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Looper
import android.view.MotionEvent
import android.view.View

class SingleTouchListener(
    private val actionCancelEvent: ((View, MotionEvent) -> Unit)? = null,
    private val actionUpEvent: ((View, MotionEvent) -> Unit)? = null,
    private val actionDownEvent: ((View, MotionEvent) -> Unit)? = null
): View.OnTouchListener {

    private val handler = Handler(Looper.getMainLooper())

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouch(view: View, motionEvent: MotionEvent?): Boolean {
        if (motionEvent == null) return false

        when (motionEvent.action) {
            MotionEvent.ACTION_CANCEL -> {
                view.isPressed = false
                val downTime = System.currentTimeMillis()
                view.dispatchTouchEvent(
                    MotionEvent.obtain(
                    downTime,
                    downTime+20L,
                    MotionEvent.ACTION_UP,
                    view.x,
                    view.y,
                    0
                ))
                actionCancelEvent?.invoke(view, motionEvent)
            }
            MotionEvent.ACTION_UP -> {
                view.isPressed = false
                actionUpEvent?.invoke(view, motionEvent)
            }
            MotionEvent.ACTION_DOWN -> {
                view.isPressed = true
                handler.postDelayed({
                    actionDownEvent?.invoke(view, motionEvent)
                }, 30L)
            }
        }
        return true
    }
}