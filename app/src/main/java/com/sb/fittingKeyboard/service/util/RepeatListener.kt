package com.sb.fittingKeyboard.service.util

import android.os.Handler
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

class RepeatListener(
    var initialInterval: Long,
    normalInterval: Long,
    clickListener: View.OnClickListener?
) :
    View.OnTouchListener {
    private val handler = Handler()
    private val normalInterval: Long
    private val clickListener: View.OnClickListener
    private var touchedView: View? = null
    private val handlerRunnable: Runnable = object : Runnable {
        override fun run() {
            handler.postDelayed(this, normalInterval)
            clickListener!!.onClick(touchedView)
        }
    }

    override fun onTouch(view: View, motionEvent: MotionEvent): Boolean {
        when (motionEvent.action) {
            MotionEvent.ACTION_DOWN -> {
                handler.removeCallbacks(handlerRunnable)
                touchedView = view
                touchedView!!.isPressed = true
                if (view.id != com.sb.fittingKeyboard.R.id.btnSpecialSPACE) handler.postDelayed(handlerRunnable, initialInterval)
                clickListener.onClick(view)
                return false
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                handler.removeCallbacks(handlerRunnable)
                if (touchedView == null) return false
                else touchedView!!.isPressed = false
                touchedView = null
                return true
            }
        }
        return false
    }

    /**
     * @param initialInterval The interval after first click event
     * @param normalInterval The interval after second and subsequent click
     * events
     * @param clickListener The OnClickListener, that will be called
     * periodically
     */
    init {
        requireNotNull(clickListener) { "null runnable" }
        require(!(initialInterval < 0 || normalInterval < 0)) { "negative interval" }
        this.normalInterval = normalInterval
        this.clickListener = clickListener
    }

    fun changeInitialInterval(newInterval: Long) {
        initialInterval = newInterval
    }
}