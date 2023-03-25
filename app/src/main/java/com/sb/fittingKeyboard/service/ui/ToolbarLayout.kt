package com.sb.fittingKeyboard.service.ui

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.view.children

class ToolbarLayout: LinearLayoutCompat {

    private var mContext: Context? = null

    constructor(context: Context): super(context) {
        mContext = context
    }

    constructor(context: Context, attr: AttributeSet) : super(context, attr) {
        mContext = context
    }

    fun reorderChild(view: View, to: Int) {
        if (to < 0) {
            this.removeView(view)
            return
        }
        this.addView(view, LayoutParams(0, MATCH_PARENT, 1F))
    }
}