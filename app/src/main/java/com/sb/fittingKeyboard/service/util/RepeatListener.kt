package com.sb.fittingKeyboard.service.util

import android.os.Handler
import android.os.Looper
import android.view.MotionEvent
import android.view.View
import android.widget.ImageButton
import com.sb.fittingKeyboard.R

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
    private val handler = Handler(Looper.getMainLooper())
    private val normalInterval: Long
    private val clickListener: View.OnClickListener
    private var touchedView: View? = null
    private val handlerRunnable: Runnable = object : Runnable {
        override fun run() {
            if (touchedView == null) return

            handler.postDelayed(this, normalInterval)
            clickListener!!.onClick(touchedView)
        }
    }

    override fun onTouch(view: View, motionEvent: MotionEvent): Boolean {
        view.performClick()
        when (motionEvent.action) {
            MotionEvent.ACTION_DOWN -> {
                view.isPressed = true
                handler.removeCallbacks(handlerRunnable)
                clickListener.onClick(view)
                touchedView = view
                touchedView!!.isPressed = true
                handler.postDelayed(handlerRunnable, initialInterval)
                return false
            }
            MotionEvent.ACTION_UP -> {
                handler.removeCallbacks(handlerRunnable)
                if (touchedView == null) return false
                touchedView!!.isPressed = false
                touchedView = null
                return true
            }
            MotionEvent.ACTION_CANCEL -> {
                handler.removeCallbacks(handlerRunnable)
                if (touchedView == null) return false
                return false
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