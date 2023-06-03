package com.sb.fittingKeyboard.com.sb.fittingKeyboard.service.util

import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import java.lang.Math.abs

class SwipeableButtonTouchListener(
    private val actionDownEvent: ((View, MotionEvent) -> Unit)? = null,
    private val actionSwipeEvent: ((View, MotionEvent) -> Unit)? = null
): OnTouchListener {

    private var x = 0F
    private var y = 0F

    private var touchedView: View? = null
    private var touchedMotionEvent: MotionEvent? = null

    private val handler = Handler(Looper.getMainLooper())
    private val handlerRunnable = object: Runnable {
        override fun run() {
            if (touchedView == null || touchedMotionEvent == null) return
            actionDownEvent?.invoke(touchedView!!, touchedMotionEvent!!)
        }
    }

    override fun onTouch(view: View, motionEvent: MotionEvent?): Boolean {
        if (motionEvent == null) return false
        view.performClick()

        when (motionEvent.action) {
            MotionEvent.ACTION_CANCEL -> {
                    handler.removeCallbacks(handlerRunnable)

                    val downTime = System.currentTimeMillis()
                    view.dispatchTouchEvent(MotionEvent.obtain(
                        downTime,
                        downTime+20L,
                        MotionEvent.ACTION_UP,
                        view.x,
                        view.y,
                        0
                    ))
            }
            MotionEvent.ACTION_UP -> {
                    view.isPressed = false
                    handler.removeCallbacks(handlerRunnable)
                    touchedView?.isPressed = false
                    touchedView = null
                    touchedMotionEvent = null
            }
            MotionEvent.ACTION_DOWN -> {
                    x = motionEvent.x
                    y = motionEvent.y
                    view.isPressed = true
                    handler.removeCallbacks(handlerRunnable)
                    touchedView = view
                    touchedView!!.isPressed = true
                    touchedMotionEvent = motionEvent
                    handler.post(handlerRunnable)
            }
            MotionEvent.ACTION_MOVE -> {
                    val dx = abs(motionEvent.x - x)
                    val dy = abs(motionEvent.y - y)

                    if (dx > 150) {
                        actionSwipeEvent?.invoke(view, motionEvent)
                    }
            }
        }
        return true
    }
}