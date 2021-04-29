package com.sb.fittingKeyboard.koreanAutomata

import android.view.inputmethod.InputConnection

class CHUNJIIN_Hangul {
    private val baseInt: Int = 0xAC00
    private val nullChar: Char = '\u0000'

    private var firstChar: Char = nullChar
    private var firstSubChar: Char = nullChar
    private var middleChar: Char = nullChar
    private var finalChar: Char = nullChar
    private var finalSubChar: Char = nullChar

    private val firstCharArray: Array<Int> = arrayOf(0x3131, 0x3132, 0x3134, 0x3137, 0x3138, 0x3139, 0x3141, 0x3142, 0x3143, 0x3145, 0x3146, 0x3147, 0x3148, 0x3149, 0x314a, 0x314b, 0x314c, 0x314d, 0x314e)
    private val middleCharArray: Array<Int> = arrayOf(0x314f, 0x3150, 0x3151, 0x3152, 0x3153, 0x3154, 0x3155, 0x3156, 0x3157, 0x3158, 0x3159, 0x315a, 0x315b, 0x315c, 0x315d, 0x315e, 0x315f, 0x3160, 0x3161, 0x3162, 0x3163)
    private val finalCharArray: Array<Int> = arrayOf(0x0000, 0x3131, 0x3132, 0x3133, 0x3134, 0x3135, 0x3136, 0x3137, 0x3139, 0x313a, 0x313b, 0x313c, 0x313d, 0x313e, 0x313f, 0x3140, 0x3141, 0x3142, 0x3144, 0x3145, 0x3146, 0x3147, 0x3148, 0x314a, 0x314b, 0x314c, 0x314d, 0x314e)

    private val rrr: Array<Char> = arrayOf('ㄱ', 'ㅋ', 'ㄲ')
    private val ss: Array<Char> = arrayOf('ㄴ', 'ㄹ')
    private val ee: Array<Char> = arrayOf('ㄷ', 'ㅌ')
    private val qqq: Array<Char> = arrayOf('ㅂ', 'ㅍ', 'ㅃ')
    private val ttt: Array<Char> = arrayOf('ㅅ', 'ㅎ', 'ㅆ')
    private val www: Array<Char> = arrayOf('ㅈ', 'ㅊ', 'ㅉ')
    private val dd: Array<Char> = arrayOf('ㅇ', 'ㅁ')

    private var state: Int = 0
    //</editor-fold>

    private var firstCharIndex = firstCharArray.indexOf(firstChar.toInt())
    private var middleCharIndex = middleCharArray.indexOf(middleChar.toInt())
    private var finalCharIndex = finalCharArray.indexOf(finalChar.toInt())
    private var composedResult: Char = '\u0000'

    fun composeResult() {
        firstCharIndex = firstCharArray.indexOf(firstChar.toInt())
        middleCharIndex = middleCharArray.indexOf(middleChar.toInt())
        finalCharIndex = finalCharArray.indexOf(finalChar.toInt())
        composedResult =
            (baseInt + firstCharIndex * 21 * 28 + middleCharIndex * 28 + finalCharIndex).toChar()
    }

    fun composeChar(c: Char, inputConnection: InputConnection) {
        when (state) {
            0 -> when {
                rrr.indexOf(c) >= 0 -> {
                    firstChar = c
                    firstSubChar = nullChar
                    middleChar = nullChar
                    finalChar = nullChar
                    finalSubChar = nullChar
                    composeResult()
                    composedResult = nullChar
                    state = 1
                    inputConnection.setComposingText(firstChar.toString(), 1)
                }
                ss.indexOf(c) >= 0 -> {
                    firstChar = c
                    firstSubChar = nullChar
                    middleChar = nullChar
                    finalChar = nullChar
                    finalSubChar = nullChar
                    composeResult()
                    composedResult = nullChar
                    state = 1
                    inputConnection.setComposingText(firstChar.toString(), 1)
                }
                ee.indexOf(c) >= 0 -> {
                    firstChar = c
                    firstSubChar = nullChar
                    middleChar = nullChar
                    finalChar = nullChar
                    finalSubChar = nullChar
                    composeResult()
                    composedResult = nullChar
                    state = 1
                    inputConnection.setComposingText(firstChar.toString(), 1)
                }
                qqq.indexOf(c) >= 0 -> {
                    firstChar = c
                    firstSubChar = nullChar
                    middleChar = nullChar
                    finalChar = nullChar
                    finalSubChar = nullChar
                    composeResult()
                    composedResult = nullChar
                    state = 1
                    inputConnection.setComposingText(firstChar.toString(), 1)
                }
                ttt.indexOf(c) >= 0 -> {
                    firstChar = c
                    firstSubChar = nullChar
                    middleChar = nullChar
                    finalChar = nullChar
                    finalSubChar = nullChar
                    composeResult()
                    composedResult = nullChar
                    state = 1
                    inputConnection.setComposingText(firstChar.toString(), 1)
                }
                www.indexOf(c) >= 0 -> {
                    firstChar = c
                    firstSubChar = nullChar
                    middleChar = nullChar
                    finalChar = nullChar
                    finalSubChar = nullChar
                    composeResult()
                    composedResult = nullChar
                    state = 1
                    inputConnection.setComposingText(firstChar.toString(), 1)
                }
                dd.indexOf(c) >= 0 -> {
                    firstChar = c
                    firstSubChar = nullChar
                    middleChar = nullChar
                    finalChar = nullChar
                    finalSubChar = nullChar
                    composeResult()
                    composedResult = nullChar
                    state = 1
                    inputConnection.setComposingText(firstChar.toString(), 1)
                }
                c == 'ㅣ' -> {
                    firstChar = nullChar
                    firstSubChar = nullChar
                    middleChar = c
                    finalChar = nullChar
                    finalSubChar = nullChar
                    composeResult()
                    composedResult = nullChar
                    state = -1
                    inputConnection.setComposingText(middleChar.toString(), 1)
                }
                c == 'ᆞ' -> {
                    firstChar = nullChar
                    firstSubChar = nullChar
                    middleChar = c
                    finalChar = nullChar
                    finalSubChar = nullChar
                    composeResult()
                    composedResult = nullChar
                    state = -1
                    inputConnection.setComposingText(middleChar.toString(), 1)
                }
                c == 'ㅡ' -> {
                    firstChar = nullChar
                    firstSubChar = nullChar
                    middleChar = c
                    finalChar = nullChar
                    finalSubChar = nullChar
                    composeResult()
                    composedResult = nullChar
                    state = -1
                    inputConnection.setComposingText(middleChar.toString(), 1)
                }
                else -> {
                    composeResult()
                    composedResult = nullChar
                    state = 0
                    inputConnection.commitText(c.toString(), 1)
                }
            }
            -1 -> when (middleChar) {
                'ㅣ' -> when (c) {
                    'ㅣ' -> {
                        inputConnection.commitText(middleChar.toString(), 1)
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    'ᆞ' -> {
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = 'ㅏ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    'ㅡ' -> {
                        inputConnection.commitText(middleChar.toString(), 1)
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    else -> {
                        inputConnection.commitText(middleChar.toString(), 1)
                        state = 1
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ᆞ' -> when (c) {
                    'ㅣ' -> {
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = 'ㅓ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    'ᆞ' -> {
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = 'ᆢ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    'ㅡ' -> {
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = 'ㅗ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    else -> {
                        inputConnection.commitText(middleChar.toString(), 1)
                        state = 1
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㅡ' -> when (c) {
                    'ㅣ' -> {
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = 'ㅢ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    'ᆞ' -> {
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = 'ㅜ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    'ㅡ' -> {
                        inputConnection.commitText(middleChar.toString(), 1)
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    else -> {
                        inputConnection.commitText(middleChar.toString(), 1)
                        state = 1
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ᆢ' -> when (c) {
                    'ㅣ' -> {
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = 'ㅕ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    'ᆞ' -> {
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = 'ᆞ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    'ㅡ' -> {
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = 'ㅛ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    else -> {
                        inputConnection.commitText(middleChar.toString(), 1)
                        state = 1
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㅏ' -> when (c) {
                    'ㅣ' -> {
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = 'ㅐ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    'ᆞ' -> {
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = 'ㅑ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    'ㅡ' -> {
                        inputConnection.commitText(middleChar.toString(), 1)
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    else -> {
                        inputConnection.commitText(middleChar.toString(), 1)
                        state = 1
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㅐ' -> when (c) {
                    'ㅣ' -> {
                        inputConnection.commitText(middleChar.toString(), 1)
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    'ᆞ' -> {
                        inputConnection.commitText(middleChar.toString(), 1)
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    'ㅡ' -> {
                        inputConnection.commitText(middleChar.toString(), 1)
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    else -> {
                        inputConnection.commitText(middleChar.toString(), 1)
                        state = 1
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㅑ' -> when (c) {
                    'ㅣ' -> {
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = 'ㅒ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    'ᆞ' -> {
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = 'ㅏ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    'ㅡ' -> {
                        inputConnection.commitText(middleChar.toString(), 1)
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    else -> {
                        inputConnection.commitText(middleChar.toString(), 1)
                        state = 1
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㅒ', 'ㅖ', 'ㅙ', 'ㅛ', 'ㅞ', 'ㅟ', 'ㅢ' -> when (c) {
                    'ㅣ', 'ᆞ', 'ㅡ' -> {
                        inputConnection.commitText(middleChar.toString(), 1)
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    else -> {
                        inputConnection.commitText(middleChar.toString(), 1)
                        state = 1
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㅓ' -> when (c) {
                    'ㅣ' -> {
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = 'ㅔ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    'ᆞ' -> {
                        inputConnection.commitText(middleChar.toString(), 1)
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    'ㅡ' -> {
                        inputConnection.commitText(middleChar.toString(), 1)
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    else -> {
                        inputConnection.commitText(middleChar.toString(), 1)
                        state = 1
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㅔ' -> when (c) {
                    'ㅣ' -> {
                        inputConnection.commitText(middleChar.toString(), 1)
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    'ᆞ' -> {
                        inputConnection.commitText(middleChar.toString(), 1)
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    'ㅡ' -> {
                        inputConnection.commitText(middleChar.toString(), 1)
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    else -> {
                        inputConnection.commitText(middleChar.toString(), 1)
                        state = 1
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㅕ' -> when (c) {
                    'ㅣ' -> {
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = 'ㅖ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    'ᆞ' -> {
                        inputConnection.commitText(middleChar.toString(), 1)
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    'ㅡ' -> {
                        inputConnection.commitText(middleChar.toString(), 1)
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    else -> {
                        inputConnection.commitText(middleChar.toString(), 1)
                        state = 1
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㅗ' -> when (c) {
                    'ㅣ' -> {
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = 'ㅚ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    'ᆞ' -> {
                        inputConnection.commitText(middleChar.toString(), 1)
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    'ㅡ' -> {
                        inputConnection.commitText(middleChar.toString(), 1)
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    else -> {
                        inputConnection.commitText(middleChar.toString(), 1)
                        state = 1
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㅘ' -> when (c) {
                    'ㅣ' -> {
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = 'ㅙ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    'ᆞ' -> {
                        inputConnection.commitText(middleChar.toString(), 1)
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    'ㅡ' -> {
                        inputConnection.commitText(middleChar.toString(), 1)
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    else -> {
                        inputConnection.commitText(middleChar.toString(), 1)
                        state = 1
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㅚ' -> when (c) {
                    'ㅣ' -> {
                        inputConnection.commitText(middleChar.toString(), 1)
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    'ᆞ' -> {
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = 'ㅘ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    'ㅡ' -> {
                        inputConnection.commitText(middleChar.toString(), 1)
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    else -> {
                        inputConnection.commitText(middleChar.toString(), 1)
                        state = 1
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㅜ' -> when (c) {
                    'ㅣ' -> {
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = 'ㅟ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    'ᆞ' -> {
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = 'ㅠ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    'ㅡ' -> {
                        inputConnection.commitText(middleChar.toString(), 1)
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    else -> {
                        inputConnection.commitText(middleChar.toString(), 1)
                        state = 1
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㅝ' -> when (c) {
                    'ㅣ' -> {
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = 'ㅞ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    'ᆞ' -> {
                        inputConnection.commitText(middleChar.toString(), 1)
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    'ㅡ' -> {
                        inputConnection.commitText(middleChar.toString(), 1)
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    else -> {
                        inputConnection.commitText(middleChar.toString(), 1)
                        state = 1
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㅠ' -> when (c) {
                    'ㅣ' -> {
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = 'ㅝ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    'ᆞ' -> {
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = 'ㅜ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    'ㅡ' -> {
                        inputConnection.commitText(middleChar.toString(), 1)
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    else -> {
                        inputConnection.commitText(middleChar.toString(), 1)
                        state = 1
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
            }
            1 -> when (firstChar) {
                'ㄱ' -> when (c) {
                    'ㄱ' -> {
                        firstChar = 'ㅋ'
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                    'ㅅ' -> {
                        when (firstSubChar) {
                            'ㅎ' -> {
                                firstChar = 'ㄱ'
                                firstSubChar = 'ㅆ'
                                middleChar = nullChar
                                finalChar = nullChar
                                finalSubChar = nullChar
                                composeResult()
                                composedResult = nullChar
                                inputConnection.setComposingText(
                                    firstChar.toString() + firstSubChar.toString(),
                                    1
                                )
                            }
                            'ㅆ', nullChar -> {
                                firstChar = 'ㄳ'
                                firstSubChar = nullChar
                                middleChar = nullChar
                                finalChar = nullChar
                                finalSubChar = nullChar
                                composeResult()
                                composedResult = nullChar
                                inputConnection.setComposingText(firstChar.toString(), 1)
                            }
                        }
                    }
                    'ㅣ', 'ㅡ' -> {
                        state = 2
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ᆞ' -> {
                        state = 2
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(
                            firstChar.toString() + middleChar.toString(),
                            1
                        )
                    }
                    else -> {
                        inputConnection.commitText(firstChar.toString(), 1)
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㄲ' -> when (c) {
                    'ㄱ' -> {
                        firstChar = 'ㄱ'
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                    'ㅣ', 'ㅡ' -> {
                        state = 2
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ᆞ' -> {
                        state = 2
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(
                            firstChar.toString() + middleChar.toString(),
                            1
                        )
                    }
                    else -> {
                        inputConnection.commitText(firstChar.toString(), 1)
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㄳ' -> when (c) {
                    'ㅅ' -> {
                        firstChar = 'ㄱ'
                        firstSubChar = 'ㅎ'
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(
                            firstChar.toString() + firstSubChar.toString(),
                            1
                        )
                    }
                    'ㅣ', 'ㅡ' -> {
                        inputConnection.commitText("ㄱ", 1)
                        state = 2
                        firstChar = 'ㅅ'
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ᆞ' -> {
                        inputConnection.commitText("ㄱ", 1)
                        state = 2
                        firstChar = 'ㅅ'
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(
                            firstChar.toString() + middleChar.toString(),
                            1
                        )
                    }
                    else -> {
                        inputConnection.commitText(firstChar.toString(), 1)
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㄴ' -> when (c) {
                    'ㄴ' -> {
                        firstChar = 'ㄹ'
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                    'ㅅ' -> {
                        when (firstSubChar) {
                            'ㅅ' -> {
                                firstChar = 'ㄶ'
                                firstSubChar = nullChar
                                middleChar = nullChar
                                finalChar = nullChar
                                finalSubChar = nullChar
                                composeResult()
                                composedResult = nullChar
                                inputConnection.setComposingText(firstChar.toString(), 1)
                            }
                            'ㅆ', nullChar -> {
                                firstChar = 'ㄴ'
                                firstSubChar = 'ㅅ'
                                middleChar = nullChar
                                finalChar = nullChar
                                finalSubChar = nullChar
                                composeResult()
                                composedResult = nullChar
                                inputConnection.setComposingText(
                                    firstChar.toString() + firstSubChar.toString(),
                                    1
                                )
                            }
                        }
                    }
                    'ㅈ' -> {
                        when (firstSubChar) {
                            'ㅈ' -> {
                                firstChar = 'ㄵ'
                                firstSubChar = nullChar
                                composeResult()
                                composedResult = nullChar
                                inputConnection.setComposingText(firstChar.toString(), 1)
                            }
                            'ㅉ', nullChar -> {
                                firstChar = 'ㄴ'
                                firstSubChar = 'ㅈ'
                                composeResult()
                                composedResult = nullChar
                                inputConnection.setComposingText(
                                    firstChar.toString() + firstSubChar.toString(),
                                    1
                                )
                            }
                        }
                    }
                    'ㅣ', 'ㅡ' -> {
                        state = 2
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ᆞ' -> {
                        state = 2
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(
                            firstChar.toString() + middleChar.toString(),
                            1
                        )
                    }
                    else -> {
                        inputConnection.commitText(firstChar.toString(), 1)
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㄵ' -> when (c) {
                    'ㅈ' -> {
                        firstChar = 'ㄴ'
                        firstSubChar = 'ㅊ'
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(
                            firstChar.toString() + firstSubChar.toString(),
                            1
                        )
                    }
                    'ㅣ', 'ㅡ' -> {
                        inputConnection.commitText("ㄴ", 1)
                        state = 2
                        firstChar = 'ㅈ'
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ᆞ' -> {
                        inputConnection.commitText("ㄴ", 1)
                        state = 2
                        firstChar = 'ㅈ'
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(
                            firstChar.toString() + middleChar.toString(),
                            1
                        )
                    }
                    else -> {
                        inputConnection.commitText(firstChar.toString(), 1)
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㄶ' -> when (c) {
                    'ㅅ' -> {
                        firstChar = 'ㄴ'
                        firstSubChar = 'ㅆ'
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(
                            firstChar.toString() + firstSubChar.toString(),
                            1
                        )
                    }
                    'ㅣ', 'ㅡ' -> {
                        inputConnection.commitText("ㄴ", 1)
                        state = 2
                        firstChar = 'ㅎ'
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ᆞ' -> {
                        inputConnection.commitText("ㄴ", 1)
                        state = 2
                        firstChar = 'ㅎ'
                        firstSubChar = nullChar
                        middleChar = c
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(
                            firstChar.toString() + middleChar.toString(),
                            1
                        )
                    }
                    else -> {
                        inputConnection.commitText(firstChar.toString(), 1)
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㄷ' -> when (c) {
                    'ㄷ' -> {
                        firstChar = 'ㅌ'
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                    'ㅣ', 'ㅡ' -> {
                        state = 2
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ᆞ' -> {
                        state = 2
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(
                            firstChar.toString() + middleChar.toString(),
                            1
                        )
                    }
                    else -> {
                        inputConnection.commitText(firstChar.toString(), 1)
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㄸ' -> when (c) {
                    'ㄷ' -> {
                        firstChar = 'ㄷ'
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                    'ㅣ', 'ㅡ' -> {
                        state = 2
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ᆞ' -> {
                        state = 2
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(
                            firstChar.toString() + middleChar.toString(),
                            1
                        )
                    }
                    else -> {
                        inputConnection.commitText(firstChar.toString(), 1)
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㄹ' -> when (c) {
                    'ㄱ' -> {
                        when (firstSubChar) {
                            'ㅋ' -> {
                                firstChar = 'ㄹ'
                                firstSubChar = 'ㄲ'
                                middleChar = nullChar
                                finalChar = nullChar
                                finalSubChar = nullChar
                                composeResult()
                                composedResult = nullChar
                                inputConnection.setComposingText(
                                    firstChar.toString() + firstSubChar.toString(),
                                    1
                                )
                            }
                            'ㄲ', nullChar -> {
                                firstChar = 'ㄺ'
                                firstSubChar = nullChar
                                middleChar = nullChar
                                finalChar = nullChar
                                finalSubChar = nullChar
                                composeResult()
                                composedResult = nullChar
                                inputConnection.setComposingText(firstChar.toString(), 1)
                            }
                        }
                    }
                    'ㄴ' -> {
                        firstChar = 'ㄴ'
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                    'ㅂ' -> {
                        when (firstSubChar) {
                            'ㅃ' -> {
                                firstChar = 'ㄹ'
                                firstSubChar = 'ㅍ'
                                middleChar = nullChar
                                finalChar = nullChar
                                finalSubChar = nullChar
                                composeResult()
                                composedResult = nullChar
                                inputConnection.setComposingText(
                                    firstChar.toString() + firstSubChar.toString(),
                                    1
                                )
                            }
                            'ㅍ', nullChar -> {
                                firstChar = 'ㄼ'
                                firstSubChar = nullChar
                                middleChar = nullChar
                                finalChar = nullChar
                                finalSubChar = nullChar
                                composeResult()
                                composedResult = nullChar
                                inputConnection.setComposingText(firstChar.toString(), 1)
                            }
                        }
                    }
                    'ㅅ' -> {
                        when (firstSubChar) {
                            'ㅆ' -> {
                                firstChar = 'ㅀ'
                                firstSubChar = nullChar
                                middleChar = nullChar
                                finalChar = nullChar
                                finalSubChar = nullChar
                                composeResult()
                                composedResult = nullChar
                                inputConnection.setComposingText(firstChar.toString(), 1)
                            }
                            nullChar -> {
                                firstChar = 'ㄽ'
                                firstSubChar = nullChar
                                middleChar = nullChar
                                finalChar = nullChar
                                finalSubChar = nullChar
                                composeResult()
                                composedResult = nullChar
                                inputConnection.setComposingText(firstChar.toString(), 1)
                            }
                        }
                    }
                    'ㅇ' -> {
                        when (firstSubChar) {
                            'ㅇ' -> {
                                firstChar = 'ㄻ'
                                firstSubChar = nullChar
                                middleChar = nullChar
                                finalChar = nullChar
                                finalSubChar = nullChar
                                composeResult()
                                composedResult = nullChar
                                inputConnection.setComposingText(firstChar.toString(), 1)
                            }
                            nullChar -> {
                                firstChar = 'ㄹ'
                                firstSubChar = 'ㅇ'
                                middleChar = nullChar
                                finalChar = nullChar
                                finalSubChar = nullChar
                                composeResult()
                                composedResult = nullChar
                                inputConnection.setComposingText(
                                    firstChar.toString() + firstSubChar.toString(),
                                    1
                                )
                            }
                        }
                    }
                    'ㅣ', 'ㅡ' -> {
                        state = 2
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ᆞ' -> {
                        state = 2
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(
                            firstChar.toString() + middleChar.toString(),
                            1
                        )
                    }
                    else -> {
                        inputConnection.commitText(firstChar.toString(), 1)
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㄺ' -> when (c) {
                    'ㄱ' -> {
                        firstChar = 'ㄹ'
                        firstSubChar = 'ㅋ'
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(
                            firstChar.toString() + firstSubChar.toString(),
                            1
                        )
                    }
                    'ㅣ', 'ㅡ' -> {
                        inputConnection.commitText("ㄹ", 1)
                        state = 2
                        firstChar = 'ㄱ'
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ᆞ' -> {
                        inputConnection.commitText("ㄹ", 1)
                        state = 2
                        firstChar = 'ㄱ'
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(
                            firstChar.toString() + middleChar.toString(),
                            1
                        )
                    }
                    else -> {
                        inputConnection.commitText(firstChar.toString(), 1)
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㄻ' -> when (c) {
                    'ㅇ' -> {
                        firstChar = 'ㄹ'
                        firstSubChar = 'ㅇ'
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(
                            firstChar.toString() + firstSubChar.toString(),
                            1
                        )
                    }
                    'ㅣ', 'ㅡ' -> {
                        inputConnection.commitText("ㄹ", 1)
                        state = 2
                        firstChar = 'ㅁ'
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ᆞ' -> {
                        inputConnection.commitText("ㄹ", 1)
                        state = 2
                        firstChar = 'ㅁ'
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(
                            firstChar.toString() + middleChar.toString(),
                            1
                        )
                    }
                    else -> {
                        inputConnection.commitText(firstChar.toString(), 1)
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㄼ' -> when (c) {
                    'ㅂ' -> {
                        firstChar = 'ㄹ'
                        firstSubChar = 'ㅃ'
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(
                            firstChar.toString() + firstSubChar.toString(),
                            1
                        )
                    }
                    'ㅣ', 'ㅡ' -> {
                        inputConnection.commitText("ㄹ", 1)
                        state = 2
                        firstChar = 'ㅂ'
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ᆞ' -> {
                        inputConnection.commitText("ㄹ", 1)
                        state = 2
                        firstChar = 'ㅂ'
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(
                            firstChar.toString() + middleChar.toString(),
                            1
                        )
                    }
                    else -> {
                        inputConnection.commitText(firstChar.toString(), 1)
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㄽ' -> when (c) {
                    'ㅅ' -> {
                        firstChar = 'ㅀ'
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                    'ㅣ', 'ㅡ' -> {
                        inputConnection.commitText("ㄹ", 1)
                        state = 2
                        firstChar = 'ㅅ'
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ᆞ' -> {
                        inputConnection.commitText("ㄹ", 1)
                        state = 2
                        firstChar = 'ㅅ'
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(
                            firstChar.toString() + middleChar.toString(),
                            1
                        )
                    }
                    else -> {
                        inputConnection.commitText(firstChar.toString(), 1)
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㅀ' -> when (c) {
                    'ㅅ' -> {
                        firstChar = 'ㄹ'
                        firstSubChar = 'ㅆ'
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(
                            firstChar.toString() + firstSubChar.toString(),
                            1
                        )
                    }
                    'ㅣ', 'ㅡ' -> {
                        inputConnection.commitText("ㄹ", 1)
                        state = 2
                        firstChar = 'ㅎ'
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ᆞ' -> {
                        inputConnection.commitText("ㄹ", 1)
                        state = 2
                        firstChar = 'ㅎ'
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(
                            firstChar.toString() + middleChar.toString(),
                            1
                        )
                    }
                    else -> {
                        inputConnection.commitText(firstChar.toString(), 1)
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㅁ' -> when (c) {
                    'ㅇ' -> {
                        firstChar = 'ㅇ'
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                    'ㅣ', 'ㅡ' -> {
                        state = 2
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ᆞ' -> {
                        state = 2
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(
                            firstChar.toString() + middleChar.toString(),
                            1
                        )
                    }
                    else -> {
                        inputConnection.commitText(firstChar.toString(), 1)
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㅂ' -> when (c) {
                    'ㅂ' -> {
                        firstChar = 'ㅍ'
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                    'ㅅ' -> {
                        when (firstSubChar) {
                            'ㅎ' -> {
                                firstChar = 'ㅂ'
                                firstSubChar = 'ㅆ'
                                middleChar = nullChar
                                finalChar = nullChar
                                finalSubChar = nullChar
                                composeResult()
                                composedResult = nullChar
                                inputConnection.setComposingText(
                                    firstChar.toString() + firstSubChar.toString(),
                                    1
                                )
                            }
                            'ㅆ', nullChar -> {
                                firstChar = 'ㅄ'
                                firstSubChar = nullChar
                                middleChar = nullChar
                                finalChar = nullChar
                                finalSubChar = nullChar
                                composeResult()
                                composedResult = nullChar
                                inputConnection.setComposingText(firstChar.toString(), 1)
                            }
                        }
                    }
                    'ㅣ', 'ㅡ' -> {
                        state = 2
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ᆞ' -> {
                        state = 2
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(
                            firstChar.toString() + middleChar.toString(),
                            1
                        )
                    }
                    else -> {
                        inputConnection.commitText(firstChar.toString(), 1)
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㅃ' -> when (c) {
                    'ㅂ' -> {
                        firstChar = 'ㅂ'
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                    'ㅣ', 'ㅡ' -> {
                        state = 2
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ᆞ' -> {
                        state = 2
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(
                            firstChar.toString() + middleChar.toString(),
                            1
                        )
                    }
                    else -> {
                        inputConnection.commitText(firstChar.toString(), 1)
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㅄ' -> when (c) {
                    'ㅅ' -> {
                        firstChar = 'ㅂ'
                        firstSubChar = 'ㅎ'
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(
                            firstChar.toString() + firstSubChar.toString(),
                            1
                        )
                    }
                    'ㅣ', 'ㅡ' -> {
                        inputConnection.commitText("ㅂ", 1)
                        state = 2
                        firstChar = 'ㅅ'
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ᆞ' -> {
                        inputConnection.commitText("ㅂ", 1)
                        state = 2
                        firstChar = 'ㅅ'
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(
                            firstChar.toString() + middleChar.toString(),
                            1
                        )
                    }
                    else -> {
                        inputConnection.commitText(firstChar.toString(), 1)
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㅅ' -> when (c) {
                    'ㅅ' -> {
                        firstChar = 'ㅎ'
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                    'ㅣ', 'ㅡ' -> {
                        state = 2
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ᆞ' -> {
                        state = 2
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(
                            firstChar.toString() + middleChar.toString(),
                            1
                        )
                    }
                    else -> {
                        inputConnection.commitText(firstChar.toString(), 1)
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㅆ' -> when (c) {
                    'ㅅ' -> {
                        firstChar = 'ㅅ'
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                    'ㅣ', 'ㅡ' -> {
                        state = 2
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ᆞ' -> {
                        state = 2
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(
                            firstChar.toString() + middleChar.toString(),
                            1
                        )
                    }
                    else -> {
                        inputConnection.commitText(firstChar.toString(), 1)
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㅇ' -> when (c) {
                    'ㅇ' -> {
                        firstChar = 'ㅁ'
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                    'ㅣ', 'ㅡ' -> {
                        state = 2
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ᆞ' -> {
                        state = 2
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(
                            firstChar.toString() + middleChar.toString(),
                            1
                        )
                    }
                    else -> {
                        inputConnection.commitText(firstChar.toString(), 1)
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㅈ' -> when (c) {
                    'ㅈ' -> {
                        firstChar = 'ㅊ'
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                    'ㅣ', 'ㅡ' -> {
                        state = 2
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ᆞ' -> {
                        state = 2
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(
                            firstChar.toString() + middleChar.toString(),
                            1
                        )
                    }
                    else -> {
                        inputConnection.commitText(firstChar.toString(), 1)
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㅉ' -> when (c) {
                    'ㅈ' -> {
                        firstChar = 'ㅈ'
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                    'ㅣ', 'ㅡ' -> {
                        state = 2
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ᆞ' -> {
                        state = 2
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(
                            firstChar.toString() + middleChar.toString(),
                            1
                        )
                    }
                    else -> {
                        inputConnection.commitText(firstChar.toString(), 1)
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㅊ' -> when (c) {
                    'ㅈ' -> {
                        firstChar = 'ㅉ'
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                    'ㅣ', 'ㅡ' -> {
                        state = 2
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ᆞ' -> {
                        state = 2
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(
                            firstChar.toString() + middleChar.toString(),
                            1
                        )
                    }
                    else -> {
                        inputConnection.commitText(firstChar.toString(), 1)
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㅋ' -> when (c) {
                    'ㄱ' -> {
                        firstChar = 'ㄲ'
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                    'ㅣ', 'ㅡ' -> {
                        state = 2
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ᆞ' -> {
                        state = 2
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(
                            firstChar.toString() + middleChar.toString(),
                            1
                        )
                    }
                    else -> {
                        inputConnection.commitText(firstChar.toString(), 1)
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㅌ' -> when (c) {
                    'ㄷ' -> {
                        firstChar = 'ㄸ'
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                    'ㅣ', 'ㅡ' -> {
                        state = 2
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ᆞ' -> {
                        state = 2
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(
                            firstChar.toString() + middleChar.toString(),
                            1
                        )
                    }
                    else -> {
                        inputConnection.commitText(firstChar.toString(), 1)
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㅍ' -> when (c) {
                    'ㅂ' -> {
                        firstChar = 'ㅃ'
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                    'ㅣ', 'ㅡ' -> {
                        state = 2
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ᆞ' -> {
                        state = 2
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(
                            firstChar.toString() + middleChar.toString(),
                            1
                        )
                    }
                    else -> {
                        inputConnection.commitText(firstChar.toString(), 1)
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㅎ' -> when (c) {
                    'ㅅ' -> {
                        firstChar = 'ㅆ'
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                    'ㅣ', 'ㅡ' -> {
                        state = 2
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ᆞ' -> {
                        state = 2
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(
                            firstChar.toString() + middleChar.toString(),
                            1
                        )
                    }
                    else -> {
                        inputConnection.commitText(firstChar.toString(), 1)
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
            }
            2 -> when (middleChar) {
                'ㅐ', 'ㅒ', 'ㅔ', 'ㅖ', 'ㅙ', 'ㅛ', 'ㅞ', 'ㅟ', 'ㅢ' -> when (c) {
                    'ㅣ', 'ᆞ', 'ㅡ' -> {
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = -1
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    else -> {
                        state = 3
                        firstSubChar = nullChar
                        finalChar = c
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                }
                'ㅏ' -> when (c) {
                    'ㅣ' -> {
                        firstSubChar = nullChar
                        middleChar = 'ㅐ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ᆞ' -> {
                        firstSubChar = nullChar
                        middleChar = 'ㅑ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ㅡ' -> {
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = -1
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    else -> {
                        state = 3
                        firstSubChar = nullChar
                        finalChar = c
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                }
                'ㅑ' -> when (c) {
                    'ㅣ' -> {
                        firstSubChar = nullChar
                        middleChar = 'ㅒ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ᆞ' -> {
                        firstSubChar = nullChar
                        middleChar = 'ㅏ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ㅡ' -> {
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = -1
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    else -> {
                        state = 3
                        firstSubChar = nullChar
                        finalChar = c
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                }
                'ㅓ' -> when (c) {
                    'ㅣ' -> {
                        firstSubChar = nullChar
                        middleChar = 'ㅔ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ᆞ', 'ㅡ' -> {
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = -1
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    else -> {
                        state = 3
                        firstSubChar = nullChar
                        finalChar = c
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                }
                'ㅕ' -> when (c) {
                    'ㅣ' -> {
                        firstSubChar = nullChar
                        middleChar = 'ㅖ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ᆞ', 'ㅡ' -> {
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = -1
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    else -> {
                        state = 3
                        firstSubChar = nullChar
                        finalChar = c
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                }
                'ㅗ' -> when (c) {
                    'ㅣ' -> {
                        firstSubChar = nullChar
                        middleChar = 'ㅚ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ᆞ', 'ㅡ' -> {
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = -1
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    else -> {
                        state = 3
                        firstSubChar = nullChar
                        finalChar = c
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                }
                'ㅘ' -> when (c) {
                    'ㅣ' -> {
                        firstSubChar = nullChar
                        middleChar = 'ㅙ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ᆞ', 'ㅡ' -> {
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = -1
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    else -> {
                        state = 3
                        firstSubChar = nullChar
                        finalChar = c
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                }
                'ㅚ' -> when (c) {
                    'ㅣ', 'ㅡ' -> {
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = -1
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    'ᆞ' -> {
                        firstSubChar = nullChar
                        middleChar = 'ㅘ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    else -> {
                        state = 3
                        firstSubChar = nullChar
                        finalChar = c
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                }
                'ㅜ' -> when (c) {
                    'ㅣ' -> {
                        firstSubChar = nullChar
                        middleChar = 'ㅟ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ᆞ' -> {
                        firstSubChar = nullChar
                        middleChar = 'ㅠ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ㅡ' -> {
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = -1
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    else -> {
                        state = 3
                        firstSubChar = nullChar
                        finalChar = c
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                }
                'ㅝ' -> when (c) {
                    'ㅣ' -> {
                        firstSubChar = nullChar
                        middleChar = 'ㅞ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ᆞ' -> {
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = -1
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    'ㅡ' -> {
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = -1
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    else -> {
                        state = 3
                        firstSubChar = nullChar
                        finalChar = c
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                }
                'ㅠ' -> when (c) {
                    'ㅣ' -> {
                        firstSubChar = nullChar
                        middleChar = 'ㅝ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ᆞ' -> {
                        firstSubChar = nullChar
                        middleChar = 'ㅜ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ㅡ' -> {
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = -1
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    else -> {
                        state = 3
                        firstSubChar = nullChar
                        finalChar = c
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                }
                'ㅡ' -> when (c) {
                    'ㅣ' -> {
                        firstSubChar = nullChar
                        middleChar = 'ㅢ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ᆞ' -> {
                        firstSubChar = nullChar
                        middleChar = 'ㅜ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ㅡ' -> {
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = -1
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    else -> {
                        state = 3
                        firstSubChar = nullChar
                        finalChar = c
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                }
                'ㅣ' -> when (c) {
                    'ㅣ' -> {
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = -1
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    'ᆞ' -> {
                        firstSubChar = nullChar
                        middleChar = 'ㅏ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ㅡ' -> {
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = -1
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    else -> {
                        state = 3
                        firstSubChar = nullChar
                        finalChar = c
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                }
                'ᆞ' -> when (c) {
                    'ㅣ' -> {
                        firstSubChar = nullChar
                        middleChar = 'ㅓ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ᆞ' -> {
                        firstSubChar = nullChar
                        middleChar = 'ᆢ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(
                            firstChar.toString() + middleChar.toString(),
                            1
                        )
                    }
                    'ㅡ' -> {
                        firstSubChar = nullChar
                        middleChar = 'ㅗ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    else -> {
                        inputConnection.commitText(firstChar.toString() + middleChar.toString(), 1)
                        state = 1
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ᆢ' -> when (c) {
                    'ㅣ' -> {
                        firstSubChar = nullChar
                        middleChar = 'ㅕ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ᆞ' -> {
                        firstSubChar = nullChar
                        middleChar = 'ᆞ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(
                            firstChar.toString() + middleChar.toString(),
                            1
                        )
                    }
                    'ㅡ' -> {
                        firstSubChar = nullChar
                        middleChar = 'ㅛ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    else -> {
                        inputConnection.commitText(firstChar.toString() + middleChar.toString(), 1)
                        state = 1
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
            }
            3 -> when (finalChar) {
                'ㄱ' -> when (c) {
                    'ㅣ', 'ㅡ' -> {
                        if (finalSubChar != nullChar) {
                            inputConnection.commitText(composedResult.toString(), 1)
                            state = 2
                            firstChar = finalSubChar
                            firstSubChar = nullChar
                            middleChar = c
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            inputConnection.setComposingText(composedResult.toString(), 1)
                        } else {
                            finalChar = nullChar
                            composeResult()
                            inputConnection.commitText(composedResult.toString(), 1)
                            state = 2
                            firstChar = 'ㄱ'
                            firstSubChar = nullChar
                            middleChar = c
                            finalChar = nullChar
                            composeResult()
                            inputConnection.setComposingText(composedResult.toString(), 1)
                        }
                    }
                    'ᆞ' -> {
                        if (finalSubChar != nullChar) {
                            inputConnection.commitText(composedResult.toString(), 1)
                            state = 2
                            firstChar = finalSubChar
                            firstSubChar = nullChar
                            middleChar = c
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            inputConnection.setComposingText(firstChar.toString() + middleChar.toString(), 1)
                        } else {
                            finalChar = nullChar
                            composeResult()
                            inputConnection.commitText(composedResult.toString(), 1)
                            state = 2
                            firstChar = 'ㄱ'
                            firstSubChar = nullChar
                            middleChar = c
                            finalChar = nullChar
                            composeResult()
                            inputConnection.setComposingText(firstChar.toString() + middleChar.toString(), 1)
                        }
                    }
                    'ㄱ' -> {
                        finalChar = 'ㅋ'
                        firstSubChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ㄴ', 'ㄷ', 'ㅂ', 'ㅇ', 'ㅈ' -> {
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 1
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                    'ㅅ' -> when (finalSubChar) {
                        nullChar, 'ㅆ' -> {
                            finalChar = 'ㄳ'
                            firstSubChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            inputConnection.setComposingText(composedResult.toString(), 1)
                        }
                        'ㅎ' -> {
                            finalChar = 'ㄱ'
                            firstSubChar = nullChar
                            finalSubChar = 'ㅆ'
                            composeResult()
                            inputConnection.setComposingText(
                                composedResult.toString() + finalSubChar.toString(),
                                1
                            )
                        }
                    }
                }
                'ㄲ' -> when (c) {
                    'ㅣ', 'ㅡ' -> {
                        finalChar = nullChar
                        composeResult()
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 2
                        firstChar = 'ㄲ'
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ᆞ' -> {
                        finalChar = nullChar
                        composeResult()
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 2
                        firstChar = 'ㄲ'
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(
                            firstChar.toString() + middleChar.toString(),
                            1
                        )
                    }
                    'ㄴ', 'ㄷ', 'ㅂ', 'ㅅ', 'ㅇ', 'ㅈ' -> {
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 1
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                    'ㄱ' -> {
                        finalChar = 'ㄱ'
                        firstSubChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                }
                'ㄳ' -> when (c) {
                    'ㅣ', 'ㅡ' -> {
                        finalChar = 'ㄱ'
                        composeResult()
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 2
                        firstChar = 'ㅅ'
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ᆞ' -> {
                        finalChar = 'ㄱ'
                        composeResult()
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 2
                        firstChar = 'ㅅ'
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(
                            firstChar.toString() + middleChar.toString(),
                            1
                        )
                    }
                    'ㄱ', 'ㄴ', 'ㄷ', 'ㅂ', 'ㅇ', 'ㅈ' -> {
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 1
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                    'ㅅ' -> {
                        finalChar = 'ㄱ'
                        firstSubChar = nullChar
                        finalSubChar = 'ㅎ'
                        composeResult()
                        inputConnection.setComposingText(
                            composedResult.toString() + finalSubChar.toString(),
                            1
                        )
                    }
                }
                'ㄴ' -> when (c) {
                    'ㅣ', 'ㅡ' -> {
                        if (finalSubChar != nullChar) {
                            inputConnection.commitText(composedResult.toString(), 1)
                            state = 2
                            firstChar = finalSubChar
                            firstSubChar = nullChar
                            middleChar = c
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            inputConnection.setComposingText(composedResult.toString(), 1)
                        } else {
                            finalChar = nullChar
                            composeResult()
                            inputConnection.commitText(composedResult.toString(), 1)
                            state = 2
                            firstChar = 'ㄴ'
                            firstSubChar = nullChar
                            middleChar = c
                            finalChar = nullChar
                            composeResult()
                            inputConnection.setComposingText(composedResult.toString(), 1)
                        }
                    }
                    'ᆞ' -> {
                        if (finalSubChar != nullChar) {
                            inputConnection.commitText(composedResult.toString(), 1)
                            state = 2
                            firstChar = finalSubChar
                            firstSubChar = nullChar
                            middleChar = c
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            inputConnection.setComposingText(firstChar.toString() + middleChar.toString(), 1)
                        } else {
                            finalChar = nullChar
                            composeResult()
                            inputConnection.commitText(composedResult.toString(), 1)
                            state = 2
                            firstChar = 'ㄴ'
                            firstSubChar = nullChar
                            middleChar = c
                            finalChar = nullChar
                            composeResult()
                            inputConnection.setComposingText(firstChar.toString() + middleChar.toString(), 1)
                        }
                    }
                    'ㄴ' -> {
                        finalChar = 'ㄹ'
                        firstSubChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ㄱ', 'ㄷ', 'ㅂ', 'ㅇ' -> {
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 1
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                    'ㅅ' -> {
                        when (finalSubChar) {
                            'ㅅ' -> {
                                finalChar = 'ㄶ'
                                firstSubChar = nullChar
                                finalSubChar = nullChar
                                composeResult()
                                inputConnection.setComposingText(composedResult.toString(), 1)
                            }
                            nullChar, 'ㅆ' -> {
                                finalChar = 'ㄴ'
                                firstSubChar = nullChar
                                finalSubChar = 'ㅅ'
                                composeResult()
                                inputConnection.setComposingText(
                                    composedResult.toString() + finalSubChar.toString(),
                                    1
                                )
                            }
                        }
                    }
                    'ㅈ' -> {
                        when (finalSubChar) {
                            nullChar, 'ㅉ' -> {
                                finalChar = 'ㄵ'
                                firstSubChar = nullChar
                                finalSubChar = nullChar
                                composeResult()
                                inputConnection.setComposingText(composedResult.toString(), 1)
                            }
                            'ㅊ' -> {
                                finalChar = 'ㄴ'
                                firstSubChar = nullChar
                                finalSubChar = 'ㅉ'
                                composeResult()
                                inputConnection.setComposingText(
                                    composedResult.toString() + finalSubChar.toString(),
                                    1
                                )
                            }
                        }
                    }
                }
                'ㄵ' -> when (c) {
                    'ㅣ', 'ㅡ' -> {
                        finalChar = 'ㄴ'
                        composeResult()
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 2
                        firstChar = 'ㅈ'
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ᆞ' -> {
                        finalChar = 'ㄴ'
                        composeResult()
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 2
                        firstChar = 'ㅈ'
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(
                            firstChar.toString() + middleChar.toString(),
                            1
                        )
                    }
                    'ㄱ', 'ㄴ', 'ㄷ', 'ㅂ', 'ㅅ', 'ㅇ' -> {
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 1
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                    'ㅈ' -> {
                        finalChar = 'ㄴ'
                        firstSubChar = nullChar
                        finalSubChar = 'ㅊ'
                        composeResult()
                        inputConnection.setComposingText(
                            composedResult.toString() + finalSubChar.toString(),
                            1
                        )
                    }
                }
                'ㄶ' -> when (c) {
                    'ㅣ', 'ㅡ' -> {
                        finalChar = 'ㄴ'
                        composeResult()
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 2
                        firstChar = 'ㅎ'
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ᆞ' -> {
                        finalChar = 'ㄴ'
                        composeResult()
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 2
                        firstChar = 'ㅎ'
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(
                            firstChar.toString() + middleChar.toString(),
                            1
                        )
                    }
                    'ㄱ', 'ㄴ', 'ㄷ', 'ㅂ', 'ㅇ', 'ㅈ' -> {
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 1
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                    'ㅅ' -> {
                        finalChar = 'ㄴ'
                        firstSubChar = nullChar
                        finalSubChar = 'ㅆ'
                        composeResult()
                        inputConnection.setComposingText(
                            composedResult.toString() + finalSubChar.toString(),
                            1
                        )
                    }
                }
                'ㄷ' -> when (c) {
                    'ㅣ', 'ㅡ' -> {
                        finalChar = nullChar
                        composeResult()
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 2
                        firstChar = 'ㄷ'
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ᆞ' -> {
                        finalChar = nullChar
                        composeResult()
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 2
                        firstChar = 'ㄷ'
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(
                            firstChar.toString() + middleChar.toString(),
                            1
                        )
                    }
                    'ㄷ' -> {
                        finalChar = 'ㅌ'
                        firstSubChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ㄱ', 'ㄴ', 'ㅂ', 'ㅅ', 'ㅇ', 'ㅈ' -> {
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 1
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㄹ' -> when (c) {
                    'ㅣ', 'ㅡ' -> {
                        if (finalSubChar != nullChar) {
                            inputConnection.commitText(composedResult.toString(), 1)
                            state = 2
                            firstChar = finalSubChar
                            firstSubChar = nullChar
                            middleChar = c
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            inputConnection.setComposingText(composedResult.toString(), 1)
                        } else {
                            finalChar = nullChar
                            composeResult()
                            inputConnection.commitText(composedResult.toString(), 1)
                            state = 2
                            firstChar = 'ㄹ'
                            firstSubChar = nullChar
                            middleChar = c
                            finalChar = nullChar
                            composeResult()
                            inputConnection.setComposingText(composedResult.toString(), 1)
                        }
                    }
                    'ᆞ' -> {
                        if (finalSubChar != nullChar) {
                            inputConnection.commitText(composedResult.toString(), 1)
                            state = 2
                            firstChar = finalSubChar
                            firstSubChar = nullChar
                            middleChar = c
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            inputConnection.setComposingText(
                                firstChar.toString() + middleChar.toString(),
                                1
                            )
                        } else {
                            finalChar = nullChar
                            composeResult()
                            inputConnection.commitText(composedResult.toString(), 1)
                            state = 2
                            firstChar = 'ㄹ'
                            firstSubChar = nullChar
                            middleChar = c
                            finalChar = nullChar
                            composeResult()
                            inputConnection.setComposingText(
                                firstChar.toString() + middleChar.toString(),
                                1
                            )
                        }
                    }
                    'ㄱ' -> {
                        when (finalSubChar) {
                            nullChar, 'ㄲ' -> {
                                finalChar = 'ㄺ'
                                firstSubChar = nullChar
                                finalSubChar = nullChar
                                composeResult()
                                inputConnection.setComposingText(composedResult.toString(), 1)
                            }
                            'ㅋ' -> {
                                finalChar = 'ㄹ'
                                firstSubChar = nullChar
                                finalSubChar = 'ㄲ'
                                composeResult()
                                inputConnection.setComposingText(
                                    composedResult.toString() + finalSubChar.toString(),
                                    1
                                )
                            }
                        }
                    }
                    'ㄴ' -> {
                        finalChar = 'ㄴ'
                        firstSubChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ㅂ' -> {
                        when (finalSubChar) {
                            nullChar, 'ㅃ' -> {
                                finalChar = 'ㄼ'
                                firstSubChar = nullChar
                                finalSubChar = nullChar
                                composeResult()
                                inputConnection.setComposingText(composedResult.toString(), 1)
                            }
                            'ㅍ' -> {
                                finalChar = 'ㄹ'
                                firstSubChar = nullChar
                                finalSubChar = 'ㅃ'
                                composeResult()
                                inputConnection.setComposingText(
                                    composedResult.toString() + finalSubChar.toString(),
                                    1
                                )
                            }
                        }
                    }
                    'ㅅ' -> {
                        when (finalSubChar) {
                            nullChar, 'ㅆ' -> {
                                finalChar = 'ㄽ'
                                firstSubChar = nullChar
                                finalSubChar = nullChar
                                composeResult()
                                inputConnection.setComposingText(composedResult.toString(), 1)
                            }
                        }
                    }
                    'ㅇ' -> {
                        when (finalSubChar) {
                            'ㅇ' -> {
                                finalChar = 'ㄻ'
                                firstSubChar = nullChar
                                finalSubChar = nullChar
                                composeResult()
                                inputConnection.setComposingText(composedResult.toString(), 1)
                            }
                            nullChar, 'ㅁ' -> {
                                finalChar = 'ㄹ'
                                firstSubChar = nullChar
                                finalSubChar = 'ㅇ'
                                composeResult()
                                inputConnection.setComposingText(
                                    composedResult.toString() + finalSubChar.toString(),
                                    1
                                )
                            }
                        }
                    }
                    'ㄷ', 'ㅈ' -> {
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 1
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㄺ' -> when (c) {
                    'ㅣ', 'ㅡ' -> {
                        finalChar = 'ㄹ'
                        composeResult()
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 2
                        firstChar = 'ㄱ'
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ᆞ' -> {
                        finalChar = 'ㄹ'
                        composeResult()
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 2
                        firstChar = 'ㄱ'
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(
                            firstChar.toString() + middleChar.toString(),
                            1
                        )
                    }
                    'ㄴ', 'ㄷ', 'ㅂ', 'ㅅ', 'ㅇ', 'ㅈ' -> {
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 1
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                    'ㄱ' -> {
                        finalChar = 'ㄹ'
                        firstSubChar = nullChar
                        finalSubChar = 'ㅋ'
                        composeResult()
                        inputConnection.setComposingText(
                            composedResult.toString() + finalSubChar.toString(),
                            1
                        )
                    }
                }
                'ㄻ' -> when (c) {
                    'ㅣ', 'ㅡ' -> {
                        finalChar = 'ㄹ'
                        composeResult()
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 2
                        firstChar = 'ㅁ'
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ᆞ' -> {
                        finalChar = 'ㄹ'
                        composeResult()
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 2
                        firstChar = 'ㅁ'
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(
                            firstChar.toString() + middleChar.toString(),
                            1
                        )
                    }
                    'ㄱ', 'ㄴ', 'ㄷ', 'ㅂ', 'ㅅ', 'ㅈ' -> {
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 1
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                    'ㅇ' -> {
                        finalChar = 'ㄹ'
                        firstSubChar = nullChar
                        finalSubChar = 'ㅇ'
                        composeResult()
                        inputConnection.setComposingText(
                            composedResult.toString() + finalSubChar.toString(),
                            1
                        )
                    }
                }
                'ㄼ' -> when (c) {
                    'ㅣ', 'ㅡ' -> {
                        finalChar = 'ㄹ'
                        composeResult()
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 2
                        firstChar = 'ㅂ'
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ᆞ' -> {
                        finalChar = 'ㄹ'
                        composeResult()
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 2
                        firstChar = 'ㅂ'
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(
                            firstChar.toString() + middleChar.toString(),
                            1
                        )
                    }
                    'ㄱ', 'ㄴ', 'ㄷ', 'ㅅ', 'ㅇ', 'ㅈ' -> {
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 1
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                    'ㅂ' -> {
                        finalChar = 'ㄹ'
                        firstSubChar = nullChar
                        finalSubChar = 'ㅍ'
                        composeResult()
                        inputConnection.setComposingText(
                            composedResult.toString() + finalSubChar.toString(),
                            1
                        )
                    }
                }
                'ㄽ' -> when (c) {
                    'ㅣ', 'ㅡ' -> {
                        finalChar = 'ㄹ'
                        composeResult()
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 2
                        firstChar = 'ㅅ'
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ᆞ' -> {
                        finalChar = 'ㄹ'
                        composeResult()
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 2
                        firstChar = 'ㅅ'
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(
                            firstChar.toString() + middleChar.toString(),
                            1
                        )
                    }
                    'ㄱ', 'ㄴ', 'ㄷ', 'ㅂ', 'ㅇ', 'ㅈ' -> {
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 1
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                    'ㅅ' -> {
                        finalChar = 'ㅀ'
                        firstSubChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                }
                'ㄿ' -> when (c) {
                    'ㅣ', 'ㅡ' -> {
                        finalChar = 'ㄹ'
                        composeResult()
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 2
                        firstChar = 'ㅍ'
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ᆞ' -> {
                        finalChar = 'ㄹ'
                        composeResult()
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 2
                        firstChar = 'ㅍ'
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(
                            firstChar.toString() + middleChar.toString(),
                            1
                        )
                    }
                    'ㄱ', 'ㄴ', 'ㄷ', 'ㅅ', 'ㅇ', 'ㅈ' -> {
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 1
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                    'ㅂ' -> {
                        finalChar = 'ㄹ'
                        firstSubChar = nullChar
                        finalSubChar = 'ㅃ'
                        composeResult()
                        inputConnection.setComposingText(
                            composedResult.toString() + finalSubChar.toString(),
                            1
                        )
                    }
                }
                'ㅀ' -> when (c) {
                    'ㅣ', 'ㅡ' -> {
                        finalChar = 'ㄹ'
                        composeResult()
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 2
                        firstChar = 'ㅎ'
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ᆞ' -> {
                        finalChar = 'ㄹ'
                        composeResult()
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 2
                        firstChar = 'ㅎ'
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(
                            firstChar.toString() + middleChar.toString(),
                            1
                        )
                    }
                    'ㄱ', 'ㄴ', 'ㄷ', 'ㅂ', 'ㅇ', 'ㅈ' -> {
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 1
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                    'ㅅ' -> {
                        finalChar = 'ㄹ'
                        firstSubChar = nullChar
                        finalSubChar = 'ㅆ'
                        composeResult()
                        inputConnection.setComposingText(
                            composedResult.toString() + finalSubChar.toString(),
                            1
                        )
                    }
                }
                'ㅁ' -> when (c) {
                    'ㅣ', 'ㅡ' -> {
                        finalChar = nullChar
                        composeResult()
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 2
                        firstChar = 'ㅁ'
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ᆞ' -> {
                        finalChar = nullChar
                        composeResult()
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 2
                        firstChar = 'ㅁ'
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(
                            firstChar.toString() + middleChar.toString(),
                            1
                        )
                    }
                    'ㄱ', 'ㄴ', 'ㄷ', 'ㅂ', 'ㅅ', 'ㅈ' -> {
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 1
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                    'ㅇ' -> {
                        finalChar = 'ㅇ'
                        firstSubChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                }
                'ㅂ' -> when (c) {
                    'ㅣ', 'ㅡ' -> {
                        if (finalSubChar != nullChar) {
                            inputConnection.commitText(composedResult.toString(), 1)
                            state = 2
                            firstChar = finalSubChar
                            firstSubChar = nullChar
                            middleChar = c
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            inputConnection.setComposingText(composedResult.toString(), 1)
                        } else {
                            finalChar = nullChar
                            composeResult()
                            inputConnection.commitText(composedResult.toString(), 1)
                            state = 2
                            firstChar = 'ㅂ'
                            firstSubChar = nullChar
                            middleChar = c
                            finalChar = nullChar
                            composeResult()
                            inputConnection.setComposingText(composedResult.toString(), 1)
                        }
                    }
                    'ᆞ' -> {
                        if (finalSubChar != nullChar) {
                            inputConnection.commitText(composedResult.toString(), 1)
                            state = 2
                            firstChar = finalSubChar
                            firstSubChar = nullChar
                            middleChar = c
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            inputConnection.setComposingText(firstChar.toString() + middleChar.toString(), 1)
                        } else {
                            finalChar = nullChar
                            composeResult()
                            inputConnection.commitText(composedResult.toString(), 1)
                            state = 2
                            firstChar = 'ㅂ'
                            firstSubChar = nullChar
                            middleChar = c
                            finalChar = nullChar
                            composeResult()
                            inputConnection.setComposingText(firstChar.toString() + middleChar.toString(), 1)
                        }
                    }
                    'ㅂ' -> {
                        finalChar = 'ㅍ'
                        firstSubChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ㄱ', 'ㄴ', 'ㄷ', 'ㅇ', 'ㅈ' -> {
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 1
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                    'ㅅ' -> {
                        when (finalSubChar) {
                            nullChar, 'ㅆ' -> {
                                finalChar = 'ㅄ'
                                firstSubChar = nullChar
                                finalSubChar = nullChar
                                composeResult()
                                inputConnection.setComposingText(composedResult.toString(), 1)
                            }
                            'ㅎ' -> {
                                finalChar = 'ㅂ'
                                firstSubChar = nullChar
                                finalSubChar = 'ㅆ'
                                composeResult()
                                inputConnection.setComposingText(
                                    composedResult.toString() + finalSubChar.toString(),
                                    1
                                )
                            }
                        }
                    }
                }
                'ㅄ' -> when (c) {
                    'ㅣ', 'ㅡ' -> {
                        finalChar = 'ㅂ'
                        composeResult()
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 2
                        firstChar = 'ㅅ'
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ᆞ' -> {
                        finalChar = 'ㅂ'
                        composeResult()
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 2
                        firstChar = 'ㅅ'
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(
                            firstChar.toString() + middleChar.toString(),
                            1
                        )
                    }
                    'ㄱ', 'ㄴ', 'ㄷ', 'ㅂ', 'ㅇ', 'ㅈ' -> {
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 1
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                    'ㅅ' -> {
                        finalChar = 'ㅂ'
                        firstSubChar = nullChar
                        finalSubChar = 'ㅎ'
                        composeResult()
                        inputConnection.setComposingText(
                            composedResult.toString() + finalSubChar.toString(),
                            1
                        )
                    }
                }
                'ㅅ' -> when (c) {
                    'ㅣ', 'ㅡ' -> {
                        finalChar = nullChar
                        composeResult()
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 2
                        firstChar = 'ㅅ'
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ᆞ' -> {
                        finalChar = nullChar
                        composeResult()
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 2
                        firstChar = 'ㅅ'
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(
                            firstChar.toString() + middleChar.toString(),
                            1
                        )
                    }
                    'ㄱ', 'ㄴ', 'ㄷ', 'ㅂ', 'ㅇ', 'ㅈ' -> {
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 1
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                    'ㅅ' -> {
                        finalChar = 'ㅎ'
                        firstSubChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                }
                'ㅆ' -> when (c) {
                    'ㅣ', 'ㅡ' -> {
                        finalChar = nullChar
                        composeResult()
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 2
                        firstChar = 'ㅆ'
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ᆞ' -> {
                        finalChar = nullChar
                        composeResult()
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 2
                        firstChar = 'ㅆ'
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(
                            firstChar.toString() + middleChar.toString(),
                            1
                        )
                    }
                    'ㄱ', 'ㄴ', 'ㄷ', 'ㅂ', 'ㅇ', 'ㅈ' -> {
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 1
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                    'ㅅ' -> {
                        finalChar = 'ㅎ'
                        firstSubChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                }
                'ㅇ' -> when (c) {
                    'ㅣ', 'ㅡ' -> {
                        finalChar = nullChar
                        composeResult()
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 2
                        firstChar = 'ㅇ'
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ᆞ' -> {
                        finalChar = nullChar
                        composeResult()
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 2
                        firstChar = 'ㅇ'
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(
                            firstChar.toString() + middleChar.toString(),
                            1
                        )
                    }
                    'ㄱ', 'ㄴ', 'ㄷ', 'ㅂ', 'ㅅ', 'ㅈ' -> {
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 1
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                    'ㅇ' -> {
                        finalChar = 'ㅁ'
                        firstSubChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                }
                'ㅈ' -> when (c) {
                    'ㅣ', 'ㅡ' -> {
                        finalChar = nullChar
                        composeResult()
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 2
                        firstChar = 'ㅈ'
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ᆞ' -> {
                        finalChar = nullChar
                        composeResult()
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 2
                        firstChar = 'ㅈ'
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(
                            firstChar.toString() + middleChar.toString(),
                            1
                        )
                    }
                    'ㄱ', 'ㄴ', 'ㄷ', 'ㅂ', 'ㅅ', 'ㅇ' -> {
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 1
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                    'ㅈ' -> {
                        finalChar = 'ㅊ'
                        firstSubChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                }
                'ㅊ' -> when (c) {
                    'ㅣ', 'ㅡ' -> {
                        finalChar = nullChar
                        composeResult()
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 2
                        firstChar = 'ㅊ'
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ᆞ' -> {
                        finalChar = nullChar
                        composeResult()
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 2
                        firstChar = 'ㅊ'
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(
                            firstChar.toString() + middleChar.toString(),
                            1
                        )
                    }
                    'ㄱ', 'ㄴ', 'ㄷ', 'ㅂ', 'ㅅ', 'ㅇ' -> {
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 1
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                    'ㅈ' -> {
                        finalChar = nullChar
                        firstSubChar = nullChar
                        finalSubChar = 'ㅉ'
                        composeResult()
                        inputConnection.setComposingText(
                            composedResult.toString() + finalSubChar.toString(),
                            1
                        )
                    }
                }
                'ㅋ' -> when (c) {
                    'ㅣ', 'ㅡ' -> {
                        finalChar = nullChar
                        composeResult()
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 2
                        firstChar = 'ㅋ'
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ᆞ' -> {
                        finalChar = nullChar
                        composeResult()
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 2
                        firstChar = 'ㅋ'
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(
                            firstChar.toString() + middleChar.toString(),
                            1
                        )
                    }
                    'ㄴ', 'ㄷ', 'ㅂ', 'ㅅ', 'ㅇ', 'ㅈ' -> {
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 1
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                    'ㄱ' -> {
                        finalChar = 'ㄲ'
                        firstSubChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                }
                'ㅌ' -> when (c) {
                    'ㅣ', 'ㅡ' -> {
                        finalChar = nullChar
                        composeResult()
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 2
                        firstChar = 'ㅌ'
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ᆞ' -> {
                        finalChar = nullChar
                        composeResult()
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 2
                        firstChar = 'ㅌ'
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(
                            firstChar.toString() + middleChar.toString(),
                            1
                        )
                    }
                    'ㄱ', 'ㄴ', 'ㅂ', 'ㅅ', 'ㅇ', 'ㅈ' -> {
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 1
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                    'ㄷ' -> {
                        finalChar = nullChar
                        firstSubChar = nullChar
                        finalSubChar = 'ㄸ'
                        composeResult()
                        inputConnection.setComposingText(
                            composedResult.toString() + finalSubChar.toString(),
                            1
                        )
                    }
                }
                'ㅍ' -> when (c) {
                    'ㅣ', 'ㅡ' -> {
                        finalChar = nullChar
                        composeResult()
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 2
                        firstChar = 'ㅍ'
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ᆞ' -> {
                        finalChar = nullChar
                        composeResult()
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 2
                        firstChar = 'ㅍ'
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(
                            firstChar.toString() + middleChar.toString(),
                            1
                        )
                    }
                    'ㄱ', 'ㄴ', 'ㄷ', 'ㅅ', 'ㅇ', 'ㅈ' -> {
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 1
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                    'ㅂ' -> {
                        finalChar = nullChar
                        firstSubChar = nullChar
                        finalSubChar = 'ㅃ'
                        composeResult()
                        inputConnection.setComposingText(
                            composedResult.toString() + finalSubChar.toString(),
                            1
                        )
                    }
                }
                'ㅎ' -> when (c) {
                    'ㅣ', 'ㅡ' -> {
                        finalChar = nullChar
                        composeResult()
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 2
                        firstChar = 'ㅎ'
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ᆞ' -> {
                        finalChar = nullChar
                        composeResult()
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 2
                        firstChar = 'ㅎ'
                        firstSubChar = nullChar
                        middleChar = c
                        finalChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(
                            firstChar.toString() + middleChar.toString(),
                            1
                        )
                    }
                    'ㄱ', 'ㄴ', 'ㄷ', 'ㅂ', 'ㅇ', 'ㅈ' -> {
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 1
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                    'ㅅ' -> {
                        finalChar = 'ㅆ'
                        firstSubChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                }
                nullChar -> when (finalSubChar) {
                    'ㄸ' -> {
                        when (c) {
                            'ㄷ' -> {
                                finalChar = 'ㄷ'
                                firstSubChar = nullChar
                                finalSubChar = nullChar
                                composeResult()
                                inputConnection.setComposingText(composedResult.toString(), 1)
                            }
                            'ㅣ', 'ᆞ', 'ㅡ' -> {
                                finalChar = nullChar
                                composeResult()
                                inputConnection.commitText(composedResult.toString(), 1)
                                state = 2
                                firstChar = finalSubChar
                                middleChar = c
                                finalChar = nullChar
                                firstSubChar = nullChar
                                finalSubChar = nullChar
                                inputConnection.setComposingText(composedResult.toString(), 1)
                            }
                            else -> {
                                inputConnection.commitText(
                                    composedResult.toString() + finalSubChar.toString(),
                                    1
                                )
                                state = 1
                                firstChar = c
                                middleChar = nullChar
                                finalChar = nullChar
                                firstSubChar = nullChar
                                finalSubChar = nullChar
                                inputConnection.setComposingText(firstChar.toString(), 1)
                            }
                        }
                    }
                    'ㅃ' -> {
                        when (c) {
                            'ㅂ' -> {
                                finalChar = 'ㅂ'
                                firstSubChar = nullChar
                                finalSubChar = nullChar
                                composeResult()
                                inputConnection.setComposingText(composedResult.toString(), 1)
                            }
                            'ㅣ', 'ᆞ', 'ㅡ' -> {
                                finalChar = nullChar
                                composeResult()
                                inputConnection.commitText(composedResult.toString(), 1)
                                state = 2
                                firstChar = finalSubChar
                                middleChar = c
                                finalChar = nullChar
                                firstSubChar = nullChar
                                finalSubChar = nullChar
                                inputConnection.setComposingText(composedResult.toString(), 1)
                            }
                            else -> {
                                inputConnection.commitText(
                                    composedResult.toString() + finalSubChar.toString(),
                                    1
                                )
                                state = 1
                                firstChar = c
                                middleChar = nullChar
                                finalChar = nullChar
                                firstSubChar = nullChar
                                finalSubChar = nullChar
                                inputConnection.setComposingText(firstChar.toString(), 1)
                            }
                        }
                    }
                    'ㅉ' -> {
                        when (c) {
                            'ㅈ' -> {
                                finalChar = 'ㅈ'
                                firstSubChar = nullChar
                                finalSubChar = nullChar
                                composeResult()
                                inputConnection.setComposingText(composedResult.toString(), 1)
                            }
                            'ㅣ', 'ᆞ', 'ㅡ' -> {
                                finalChar = nullChar
                                composeResult()
                                inputConnection.commitText(composedResult.toString(), 1)
                                state = 2
                                firstChar = finalSubChar
                                middleChar = c
                                finalChar = nullChar
                                firstSubChar = nullChar
                                finalSubChar = nullChar
                                inputConnection.setComposingText(composedResult.toString(), 1)
                            }
                            else -> {
                                inputConnection.commitText(
                                    composedResult.toString() + finalSubChar.toString(),
                                    1
                                )
                                state = 1
                                firstChar = c
                                middleChar = nullChar
                                finalChar = nullChar
                                firstSubChar = nullChar
                                finalSubChar = nullChar
                                inputConnection.setComposingText(firstChar.toString(), 1)
                            }
                        }
                    }
                }
            }
        }
    } //한글 오토마타

    fun delete(c: Char?, inputConnection: InputConnection) {
        if (c == null) {
            inputConnection.commitText("", 1)
        }
        when (state) {
            0 -> {
                initState()
                initChar()
                initResult()
                inputConnection.commitText("", 1)
                inputConnection.deleteSurroundingText(1, 0)
            }
            -1 -> when (middleChar) {
                'ㅏ' -> {
                    middleChar = 'ㅣ'
                    composeResult()
                    composedResult = nullChar
                    inputConnection.setComposingText(middleChar.toString(), 1)
                }
                'ㅐ', 'ㅑ' -> {
                    middleChar = 'ㅏ'
                    composeResult()
                    composedResult = nullChar
                    inputConnection.setComposingText(middleChar.toString(), 1)
                }
                'ㅒ' -> {
                    middleChar = 'ㅑ'
                    composeResult()
                    composedResult = nullChar
                    inputConnection.setComposingText(middleChar.toString(), 1)
                }
                'ㅓ', 'ㅗ', 'ᆢ' -> {
                    middleChar = 'ᆞ'
                    composeResult()
                    composedResult = nullChar
                    inputConnection.setComposingText(middleChar.toString(), 1)
                }
                'ㅔ' -> {
                    middleChar = 'ㅓ'
                    composeResult()
                    composedResult = nullChar
                    inputConnection.setComposingText(middleChar.toString(), 1)
                }
                'ㅕ', 'ㅛ' -> {
                    middleChar = 'ᆢ'
                    composeResult()
                    composedResult = nullChar
                    inputConnection.setComposingText(middleChar.toString(), 1)
                }
                'ㅖ' -> {
                    middleChar = 'ㅕ'
                    composeResult()
                    composedResult = nullChar
                    inputConnection.setComposingText(middleChar.toString(), 1)
                }
                'ㅘ' -> {
                    middleChar = 'ㅚ'
                    composeResult()
                    composedResult = nullChar
                    inputConnection.setComposingText(middleChar.toString(), 1)
                }
                'ㅙ' -> {
                    middleChar = 'ㅘ'
                    composeResult()
                    composedResult = nullChar
                    inputConnection.setComposingText(middleChar.toString(), 1)
                }
                'ㅚ' -> {
                    middleChar = 'ㅗ'
                    composeResult()
                    composedResult = nullChar
                    inputConnection.setComposingText(middleChar.toString(), 1)
                }
                'ㅜ', 'ㅢ' -> {
                    middleChar = 'ㅡ'
                    composeResult()
                    composedResult = nullChar
                    inputConnection.setComposingText(middleChar.toString(), 1)
                }
                'ㅠ', 'ㅟ' -> {
                    middleChar = 'ㅜ'
                    composeResult()
                    composedResult = nullChar
                    inputConnection.setComposingText(middleChar.toString(), 1)
                }
                'ㅝ' -> {
                    middleChar = 'ㅠ'
                    composeResult()
                    composedResult = nullChar
                    inputConnection.setComposingText(middleChar.toString(), 1)
                }
                'ㅞ' -> {
                    middleChar = 'ㅝ'
                    composeResult()
                    composedResult = nullChar
                    inputConnection.setComposingText(middleChar.toString(), 1)
                }
                'ㅡ', 'ㅣ', 'ᆞ' -> {
                    state = 0
                    middleChar = nullChar
                    composeResult()
                    composedResult = nullChar
                    inputConnection.commitText("", 1)
                }
                }
            1 -> when (firstChar) {
                'ㄳ' -> {
                    firstChar = 'ㄱ'
                    firstSubChar = nullChar
                    composeResult()
                    composedResult = nullChar
                    inputConnection.setComposingText(firstChar.toString(), 1)
                }
                'ㄵ', 'ㄶ' -> {
                    firstChar = 'ㄴ'
                    firstSubChar = nullChar
                    composeResult()
                    composedResult = nullChar
                    inputConnection.setComposingText(firstChar.toString(), 1)
                }
                'ㄺ', 'ㄻ', 'ㄼ', 'ㄽ', 'ㅀ' -> {
                    firstChar = 'ㄹ'
                    firstSubChar = nullChar
                    composeResult()
                    composedResult = nullChar
                    inputConnection.setComposingText(firstChar.toString(), 1)
                }
                'ㅄ' -> {
                    firstChar = 'ㅂ'
                    firstSubChar = nullChar
                    composeResult()
                    composedResult = nullChar
                    inputConnection.setComposingText(firstChar.toString(), 1)
                }
                else -> when ( firstSubChar ) {
                    nullChar -> {
                        state = 0
                        firstChar = nullChar
                        firstSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.commitText("", 1)
                    }
                    else -> {
                        firstSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
            }
            2 -> when (middleChar) {
                'ㅏ' -> {
                    middleChar = 'ㅣ'
                    composeResult()
                    inputConnection.setComposingText(composedResult.toString(), 1)
                }
                'ㅐ', 'ㅑ' -> {
                    middleChar = 'ㅏ'
                    composeResult()
                    inputConnection.setComposingText(composedResult.toString(), 1)
                }
                'ㅒ' -> {
                    middleChar = 'ㅑ'
                    composeResult()
                    inputConnection.setComposingText(composedResult.toString(), 1)
                }
                'ㅓ', 'ㅗ', 'ᆢ' -> {
                    middleChar = 'ᆞ'
                    composeResult()
                    composedResult = nullChar
                    inputConnection.setComposingText(
                        firstChar.toString() + middleChar.toString(),
                        1
                    )
                }
                'ㅔ' -> {
                    middleChar = 'ㅓ'
                    composeResult()
                    inputConnection.setComposingText(composedResult.toString(), 1)
                }
                'ㅕ', 'ㅛ' -> {
                    middleChar = 'ᆢ'
                    composeResult()
                    composedResult = nullChar
                    inputConnection.setComposingText(
                        firstChar.toString() + middleChar.toString(),
                        1
                    )
                }
                'ㅖ' -> {
                    middleChar = 'ㅕ'
                    composeResult()
                    inputConnection.setComposingText(composedResult.toString(), 1)
                }
                'ㅘ' -> {
                    middleChar = 'ㅚ'
                    composeResult()
                    inputConnection.setComposingText(composedResult.toString(), 1)
                }
                'ㅙ' -> {
                    middleChar = 'ㅘ'
                    composeResult()
                    inputConnection.setComposingText(composedResult.toString(), 1)
                }
                'ㅚ' -> {
                    middleChar = 'ㅗ'
                    composeResult()
                    inputConnection.setComposingText(composedResult.toString(), 1)
                }
                'ㅜ', 'ㅢ' -> {
                    middleChar = 'ㅡ'
                    composeResult()
                    inputConnection.setComposingText(composedResult.toString(), 1)
                }
                'ㅠ', 'ㅟ' -> {
                    middleChar = 'ㅜ'
                    composeResult()
                    inputConnection.setComposingText(composedResult.toString(), 1)
                }
                'ㅝ' -> {
                    middleChar = 'ㅠ'
                    composeResult()
                    inputConnection.setComposingText(composedResult.toString(), 1)
                }
                'ㅞ' -> {
                    middleChar = 'ㅝ'
                    composeResult()
                    inputConnection.setComposingText(composedResult.toString(), 1)
                }
                'ㅡ', 'ㅣ', 'ᆞ' -> {
                    state = 1
                    middleChar = nullChar
                    composeResult()
                    composedResult = nullChar
                    inputConnection.setComposingText(firstChar.toString(), 1)
                }
            }
            3 -> when (finalChar) {
                'ㄳ' -> {
                    finalChar = 'ㄱ'
                    finalSubChar = nullChar
                    composeResult()
                    inputConnection.setComposingText(composedResult.toString(), 1)
                }
                'ㄵ', 'ㄶ' -> {
                    finalChar = 'ㄴ'
                    finalSubChar = nullChar
                    composeResult()
                    inputConnection.setComposingText(composedResult.toString(), 1)
                }
                'ㄺ', 'ㄻ', 'ㄼ', 'ㄽ', 'ㅀ' -> {
                    finalChar = 'ㄹ'
                    finalSubChar = nullChar
                    composeResult()
                    inputConnection.setComposingText(composedResult.toString(), 1)
                }
                'ㅄ' -> {
                    finalChar = 'ㅂ'
                    finalSubChar = nullChar
                    composeResult()
                    inputConnection.setComposingText(composedResult.toString(), 1)
                }
                else -> when ( finalSubChar ) {
                    nullChar -> {
                        state = 2
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    else -> {
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                }
            }
        }
    } //delete 발생

    fun initChar() {
        firstChar = nullChar
        firstSubChar = nullChar
        middleChar = nullChar
        finalChar = nullChar
        finalSubChar = nullChar
    } //자모 초기화

    fun initState() {
        state = 0
    }

    fun initResult() {
        composedResult = nullChar
    }
}