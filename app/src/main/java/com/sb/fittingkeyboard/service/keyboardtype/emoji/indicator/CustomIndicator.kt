package com.sb.fittingkeyboard.service.keyboardtype.emoji.indicator

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.LinearLayout
import com.sb.fittingkeyboard.Constants
import com.sb.fittingKeyboard.R
import com.sb.fittingkeyboard.keyboardsettings.util.changeDPtoPX

class CustomIndicator : LinearLayout {
    constructor(context: Context) : super(context) {
        mContext = context
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        mContext = context
    }

    private var mContext: Context? = null
    private var iconPanels: MutableList<ImageView> = mutableListOf()

    fun createIconPanel(iconsList: List<Int>, position: Int, clickListeners: List<OnClickListener>) {
        this.removeAllViews()

        for (i in iconsList.indices) {
            iconPanels.add(ImageView(mContext))
            iconPanels[i].setImageResource(iconsList[i])
            this.addView(iconPanels[i])
            this.getChildAt(i).layoutParams =
                LayoutParams(changeDPtoPX(Constants.EMOJI_ICON_WIDTH).toInt(), LayoutParams.MATCH_PARENT)
            this.getChildAt(i).setOnClickListener(clickListeners[i])
        }
        selectPosition(position)
    }

    fun selectPosition(position: Int) {
        for (i in iconPanels.indices) {
            if (i == position) {
                iconPanels[i].apply {
                    setColorFilter(resources.getColor(R.color.deep_blue))
                    scaleX = 1F
                    scaleY = 1F
                    scaleType = ImageView.ScaleType.FIT_CENTER
                }
            } else {
                iconPanels[i].apply {
                    setColorFilter(resources.getColor(R.color.white))
                    scaleX = 0.8F
                    scaleY = 0.8F
                    scaleType = ImageView.ScaleType.FIT_CENTER
                }
            }
        }
    }
}