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
        const val KEYBOARD_TOOLBAR_ACTIVE_GO_SETTING = "KeyboardToolbarGoSetting"
        const val KEYBOARD_TOOLBAR_ACTIVE_SHOW_BOILERPLATE = "KeyboardToolbarShowBoilerPlate"
        const val KEYBOARD_TOOLBAR_ACTIVE_SELECT_ALL = "KeyboardToolbarSelectAll"
        const val KEYBOARD_TOOLBAR_ACTIVE_COPY = "KeyboardToolbarCopy"
        const val KEYBOARD_TOOLBAR_ACTIVE_CUT = "KeyboardToolbarCut"
        const val KEYBOARD_TOOLBAR_ACTIVE_PASTE = "KeyboardToolbarPaste"
        const val KEYBOARD_TOOLBAR_ACTIVE_SHOW_CURSOR = "KeyboardToolbarShowCursor"
        const val KEYBOARD_TOOLBAR_ACTIVE_SHOW_NUMBER = "KeyboardToolbarShowNumber"
        const val KEYBOARD_TOOLBAR_ACTIVE_SHOW_EMOJI = "KeyboardToolbarShowEmoji"
        const val KEYBOARD_IME_KR = "KeyboardInputMethodKR"
        val KEYBOARD_BOILERPLATE_TEXTS_LIST = listOf(
            "AutoText0",
            "AutoText1",
            "AutoText2",
            "AutoText3",
            "AutoText4",
            "AutoText5",
            "AutoText6",
            "AutoText7",
            "AutoText8",
            "AutoText9",
            "AutoText10",
            "AutoText11",
            "AutoText12",
            "AutoText13",
            "AutoText14",
            "AutoText15"
        )
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
            return "0x" + hex.toString().padStart(7, '0')
        }
    }
}