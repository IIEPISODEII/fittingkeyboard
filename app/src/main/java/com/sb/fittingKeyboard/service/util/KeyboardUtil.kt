package com.sb.fittingKeyboard.service.util

import android.content.res.Resources
import android.widget.FrameLayout
import androidx.core.content.res.ResourcesCompat
import com.bumptech.glide.load.engine.Resource
import com.sb.fittingKeyboard.R

class KeyboardUtil {
    companion object {
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

        const val PACKAGE_NAME = "com.sb.fittingKeyboard"

        const val KEYBOARD_SETTING = "keyboardSetting"
        const val KEYBOARD_HEIGHT = "KeyboardHeight"
        const val KEYBOARD_TOGGLE_NUMBER = "KeyboardToggleNum"
        const val KEYBOARD_BOTTOM_MARGIN = "KeyboardBottomMargin"
        const val KEYBOARD_FONT_SIZE = "KeyboardFontSize"
        const val KEYBOARD_FONT_TYPE = "KeyboardFontType"
        const val KEYBOARD_MO_SIZE = "KeyboardMoSize"
        const val KEYBOARD_DIVISION = "KeyboardDivision"
        const val KEYBOARD_HOLDING_TIME = "KeyboardHolding"
        const val KEYBOARD_VIBRATION_USE = "KeyboardVibrationUse"
        const val KEYBOARD_VIBRATION_INTENSITY = "KeyboardVibrationIntensity"
        const val KEYBOARD_THEME = "KeyboardTheme"
        const val KEYBOARD_FONT_COLOR = "KeyboardDefaultFontColor"
        const val KEYBOARD_FUNCTION_FONT_COLOR = "KeyboardFunctionFontColor"
        const val KEYBOARD_LEFTSIDE_MARGIN = "KeyboardLeftSideMargin"
        const val KEYBOARD_RIGHTSIDE_MARGIN = "KeyboardRightSideMargin"
        const val KEYBOARD_TOGGLE_TOOLBAR = "KeyboardToggleToolBar"
        const val KEYBOARD_AUTO_CAPITALIZATION = "KeyboardAutoCapital"
        const val KEYBOARD_AUTO_MODE_CHANGE = "KeyboardAutoModeChange"
        const val KEYBOARD_SPECIALKEY_LONGCLICK = "KeyboardSpecialkeyAddon"
        const val KEYBOARD_ENTERKEY_LONGCLICK = "KeyboardEnterkeyAddon"
        const val KEYBOARD_IME_KR = "KeyboardInputMethodKR"
        const val KEYBOARD_BP_0 = "AutoText0"
        const val KEYBOARD_BP_1 = "AutoText1"
        const val KEYBOARD_BP_2 = "AutoText2"
        const val KEYBOARD_BP_3 = "AutoText3"
        const val KEYBOARD_BP_4 = "AutoText4"
        const val KEYBOARD_BP_5 = "AutoText5"
        const val KEYBOARD_BP_6 = "AutoText6"
        const val KEYBOARD_BP_7 = "AutoText7"
        const val KEYBOARD_BP_8 = "AutoText8"
        const val KEYBOARD_BP_9 = "AutoText9"
        const val KEYBOARD_BP_10 = "AutoText10"
        const val KEYBOARD_BP_11 = "AutoText11"
        const val KEYBOARD_BP_12 = "AutoText12"
        const val KEYBOARD_BP_13 = "AutoText13"
        const val KEYBOARD_BP_14 = "AutoText14"
        const val KEYBOARD_BP_15 = "AutoText15"
        const val RECENTLY_USED_EMOTICONS = "RecentlyUsedEmotions"


        // 한글키보드 세팅값
        const val QWERTY = 0
        const val CHUN = 1
        const val NARAT = 2
        const val CHUN_AMBI = 3
        const val DAN = 4

        // 이모지 세팅
        const val emojiIconWidth = 50
        fun getEmojiIconXPosition(view: FrameLayout, position: Int): Int {
            val _position: Float = (view.width/changeDpToPx(emojiIconWidth)) / 2F
            return (changeDpToPx(emojiIconWidth) * (position - _position) + changeDpToPx(emojiIconWidth)/2).toInt()
        }

        fun changeDpToPx(dp: Int): Int {
            return (dp * Resources.getSystem().displayMetrics.density).toInt()
        }

        fun decToHex(dec: Int): String {
            val hex = StringBuilder("")
            var _dec = dec
            if (_dec == 0) return hex.append("0x0").toString()
            while (_dec > 0) {
                val remain = _dec % 16
                when (remain){
                    10 -> hex.insert(0, "A")
                    11 -> hex.insert(0, "B")
                    12 -> hex.insert(0, "C")
                    13 -> hex.insert(0, "D")
                    14 -> hex.insert(0, "E")
                    15 -> hex.insert(0, "F")
                    else -> hex.insert(0, remain)
                }
                _dec = (_dec-remain)/16
            }
            hex.insert(0, "0x")
            return hex.toString().padEnd(7, '0')
        }
    }
}