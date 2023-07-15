package com.sb.fittingKeyboard.service.ui

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.TypedValue
import android.widget.Button
import android.widget.ImageView

class CompoundButton: Button {
    constructor(context: Context): super(context, null)

    constructor(context: Context, attrs: AttributeSet?): super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr)

    private var viewWidth = 0
    private var textAreaWidth = 0
    private val ivLeft = ImageView(context)
    private val ivRight = ImageView(context)

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        viewWidth = width
        textAreaWidth = width
    }

    override fun setCompoundDrawablesWithIntrinsicBounds(
        left: Drawable?,
        top: Drawable?,
        right: Drawable?,
        bottom: Drawable?
    ) {
        super.setCompoundDrawablesWithIntrinsicBounds(left, top, right, bottom)
        if (left == null || right == null) return

        // Left, Right Drawable이 있을 때 텍스트 사이즈 결정
        val drawableWidth = left.intrinsicWidth
        textAreaWidth = viewWidth - 2*drawableWidth
        val textSize = textAreaWidth / resources.displayMetrics.scaledDensity / text.length

        setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize)
    }
}