package com.sb.fittingkeyboard.service.util

import android.os.Handler
import android.os.Looper
import android.view.MotionEvent
import android.view.View

/**
 * A class, that can be used as a TouchListener on any view (e.g. a Button).
 * It cyclically runs a clickListener, emulating keyboard-like behaviour. First
 * click is fired immediately, next one after the initialInterval, and subsequent
 * ones after the normalInterval.
 *
 *
 * Interval is scheduled after the onClick completes, so it has to run fast.
 * If it runs slow, it does not generate skipped onClicks. Can be rewritten to
 * achieve this.
 */

class RepeatTouchListener(
    private var initialInterval: Long,
    private val normalInterval: Long,
    private val actionDownEvent: ((View, MotionEvent) -> Unit)? = null,
    private val actionUpEvent: ((View, MotionEvent) -> Unit)? = null,
    private val actionCancelEvent: ((View, MotionEvent) -> Unit)? = null
) :
    View.OnTouchListener {
    private val handler = Handler(Looper.getMainLooper())
    private var touchedView: View? = null
    private var touchedMotionEvent: MotionEvent? = null
    private val handlerRunnable: Runnable = object : Runnable {
        override fun run() {
            if (touchedView == null || touchedMotionEvent == null) return

            handler.postDelayed(this, normalInterval)
            actionDownEvent?.invoke(touchedView!!, touchedMotionEvent!!)
        }
    }

    override fun onTouch(view: View, motionEvent: MotionEvent): Boolean {
        view.performClick()
        when (motionEvent.action) {
            MotionEvent.ACTION_DOWN -> {
                view.isPressed = true
                handler.removeCallbacks(handlerRunnable)
                touchedView = view
                touchedView!!.isPressed = true
                touchedMotionEvent = motionEvent
                handler.post { actionDownEvent?.invoke(view, motionEvent) }
                handler.postDelayed(handlerRunnable, initialInterval)
            }
            MotionEvent.ACTION_UP -> {
                handler.removeCallbacks(handlerRunnable)
                actionUpEvent?.invoke(view, motionEvent)
                if (touchedView == null || touchedMotionEvent == null) return false
                touchedView!!.isPressed = false
                touchedView = null
                touchedMotionEvent = null
            }
            MotionEvent.ACTION_CANCEL -> {
                handler.removeCallbacks(handlerRunnable)
                actionCancelEvent?.invoke(view, motionEvent)
                if (touchedView == null || touchedMotionEvent == null) return false
                touchedView?.isPressed = false
                touchedView = null
                touchedMotionEvent = null
            }
        }
        return true
    }

    fun setInitialInterval(newInterval: Long) {
        initialInterval = newInterval
    }
}