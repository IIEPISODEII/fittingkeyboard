package com.sb.fittingKeyboard.service.util

class KeyboardUtil {
    companion object {
        val enChar = arrayOf(
            "A",
            "B",
            "C",
            "D",
            "E",
            "F",
            "G",
            "H",
            "I",
            "J",
            "K",
            "L",
            "M",
            "N",
            "O",
            "P",
            "Q",
            "R",
            "S",
            "T",
            "U",
            "V",
            "W",
            "X",
            "Y",
            "Z"
        )
        val krChar = arrayOf(
            "ㅁ",
            "ㅠ",
            "ㅊ",
            "ㅇ",
            "ㄷ",
            "ㄹ",
            "ㅎ",
            "ㅗ",
            "ㅑ",
            "ㅓ",
            "ㅏ",
            "ㅣ",
            "ㅡ",
            "ㅜ",
            "ㅐ",
            "ㅔ",
            "ㅂ",
            "ㄱ",
            "ㄴ",
            "ㅅ",
            "ㅕ",
            "ㅍ",
            "ㅈ",
            "ㅌ",
            "ㅛ",
            "ㅋ"
        )
        val krShiftChar =
            arrayOf("ㅂ", "ㅈ", "ㄷ", "ㄱ", "ㅅ", "ㅐ", "ㅔ", "ㅃ", "ㅉ", "ㄸ", "ㄲ", "ㅆ", "ㅒ", "ㅖ")
        val specialChar1 = arrayOf(
            "(",
            ";",
            "~",
            "{",
            "$",
            "}",
            "[",
            "]",
            "-",
            "<",
            ">",
            "=",
            "?",
            "!",
            "*",
            "/",
            "@",
            "%",
            ")",
            "^",
            "+",
            ":",
            "#",
            "_",
            "&",
            "\""
        )
        val specialChar2 = arrayOf(
            "≤",
            "`",
            "\"",
            "≠",
            "÷",
            "∞",
            "→",
            "←",
            "-",
            "↑",
            "↓",
            "♡",
            "|",
            "\\",
            "*",
            "/",
            "±",
            "•",
            "≥",
            "√",
            "+",
            "'",
            "×",
            "」",
            "π",
            "「"
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

        const val QWERTY = 0
        const val CHUN = 1
        const val NARAT = 2
        const val CHUN_AMBI = 3
        const val DAN = 4

        const val BOILERPLATE_TEXTS_FRAGMENT = 1
    }
}