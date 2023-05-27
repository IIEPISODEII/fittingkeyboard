package com.sb.fittingKeyboard.service.util

import android.content.res.Resources
import android.widget.FrameLayout
import androidx.core.content.res.ResourcesCompat
import com.bumptech.glide.load.engine.Resource
import com.sb.fittingKeyboard.R

val emojiIconList = listOf(
    R.drawable.ic_outline_access_time_24,
    R.drawable.ic_outline_emoji_emotions_24,
    R.drawable.ic_outline_emoji_people_24,
    R.drawable.ic_outline_emoji_nature_24,
    R.drawable.ic_outline_emoji_food_beverage_24,
    R.drawable.ic_outline_emoji_transportation_24,
    R.drawable.ic_outline_emoji_events_24,
    R.drawable.ic_outline_emoji_objects_24,
    R.drawable.ic_outline_emoji_symbols_24,
    R.drawable.ic_outline_emoji_flags_24
)

fun decToHex(dec: Int): String {
    val hex = StringBuilder("")
    var _dec = dec
    if (_dec == 0) return hex.append("0x0").toString()
    while (_dec > 0) {
        val remain = _dec % 16
        when (remain) {
            10 -> hex.insert(0, "A")
            11 -> hex.insert(0, "B")
            12 -> hex.insert(0, "C")
            13 -> hex.insert(0, "D")
            14 -> hex.insert(0, "E")
            15 -> hex.insert(0, "F")
            else -> hex.insert(0, remain)
        }
        _dec = (_dec - remain) / 16
    }
    return "0x" + hex.toString().padStart(7, '0')
}