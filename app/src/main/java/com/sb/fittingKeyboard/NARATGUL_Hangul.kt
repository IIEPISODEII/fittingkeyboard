package com.sb.fittingKeyboard

import android.view.inputmethod.InputConnection

class NARATGUL_Hangul {
    private val baseInt: Int = 0xAC00
    private val nullChar: Char = '\u0000'

    private var firstChar: Char = nullChar
    private var firstSubChar: Char = nullChar
    private var middleChar: Char = nullChar
    private var middleSubChar: Char = nullChar
    private var finalChar: Char = nullChar
    private var finalSubChar: Char = nullChar

    private val firstCharArray: Array<Int> = arrayOf(0x3131, 0x3132, 0x3134, 0x3137, 0x3138, 0x3139, 0x3141, 0x3142, 0x3143, 0x3145, 0x3146, 0x3147, 0x3148, 0x3149, 0x314a, 0x314b, 0x314c, 0x314d, 0x314e)
    private val middleCharArray: Array<Int> = arrayOf(0x314f, 0x3150, 0x3151, 0x3152, 0x3153, 0x3154, 0x3155, 0x3156, 0x3157, 0x3158, 0x3159, 0x315a, 0x315b, 0x315c, 0x315d, 0x315e, 0x315f, 0x3160, 0x3161, 0x3162, 0x3163)
    private val finalCharArray: Array<Int> = arrayOf(0x0000, 0x3131, 0x3132, 0x3133, 0x3134, 0x3135, 0x3136, 0x3137, 0x3139, 0x313a, 0x313b, 0x313c, 0x313d, 0x313e, 0x313f, 0x3140, 0x3141, 0x3142, 0x3144, 0x3145, 0x3146, 0x3147, 0x3148, 0x314a, 0x314b, 0x314c, 0x314d, 0x314e)

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
            0 -> when (c) {
                'ㄱ', 'ㄴ', 'ㄹ', 'ㅁ', 'ㅅ', 'ㅇ' -> {
                    firstChar = c
                    firstSubChar = nullChar
                    middleChar = nullChar
                    middleSubChar = nullChar
                    finalChar = nullChar
                    finalSubChar = nullChar
                    composeResult()
                    composedResult = nullChar
                    state = 1
                    inputConnection.setComposingText(firstChar.toString(), 1)
                }
                'ᆞ', 'ᆢ' -> {

                }
                'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                    firstChar = nullChar
                    firstSubChar = nullChar
                    middleChar = c
                    middleSubChar = nullChar
                    finalChar = nullChar
                    finalSubChar = nullChar
                    composeResult()
                    composedResult = nullChar
                    state = -1
                    inputConnection.setComposingText(middleChar.toString(), 1)
                }
            }
            -1 -> when (middleChar) {
                'ㅏ' -> when (c) {
                    'ㅏ' -> {
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = 'ㅓ'
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    'ㅗ', 'ㅡ' -> {
                        inputConnection.commitText(middleChar.toString(), 1)
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = c
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    'ㅣ' -> {
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = 'ㅐ'
                        middleSubChar = nullChar
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
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    'ᆢ' -> {

                    }
                    else -> {
                        inputConnection.commitText(middleChar.toString(), 1)
                        state = 1
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㅐ', 'ㅔ', 'ㅒ', 'ㅖ', 'ㅙ', 'ㅚ', 'ㅞ', 'ㅟ', 'ㅢ' -> when (c) {
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        inputConnection.commitText(middleChar.toString(), 1)
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = c
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    'ᆞ', 'ᆢ' -> {

                    }
                    else -> {
                        inputConnection.commitText(middleChar.toString(), 1)
                        state = 1
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㅑ' -> when (c) {
                    'ㅏ', 'ㅗ', 'ㅡ' -> {
                        inputConnection.commitText(middleChar.toString(), 1)
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = c
                        middleSubChar = nullChar
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
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    'ㅣ' -> {
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = 'ㅒ'
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    'ᆢ' -> {

                    }
                    else -> {
                        inputConnection.commitText(middleChar.toString(), 1)
                        state = 1
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㅓ' -> when (c) {
                    'ㅏ' -> {
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = 'ㅏ'
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    'ㅗ', 'ㅡ' -> {
                        inputConnection.commitText(middleChar.toString(), 1)
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = c
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    'ㅣ' -> {
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = 'ㅔ'
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    'ᆞ' -> {
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = 'ㅕ'
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    'ᆢ' -> {

                    }
                    else -> {
                        inputConnection.commitText(middleChar.toString(), 1)
                        state = 1
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㅕ' -> when (c) {
                    'ㅏ', 'ㅗ', 'ㅡ' -> {
                        inputConnection.commitText(middleChar.toString(), 1)
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = c
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    'ᆞ' -> {
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = 'ㅓ'
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    'ᆢ' -> {

                    }
                    'ㅣ' -> {
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = 'ㅖ'
                        middleSubChar = nullChar
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
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㅗ' -> when (c) {
                    'ㅏ' -> {
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = 'ㅘ'
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    'ㅗ' -> {
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = 'ㅜ'
                        middleSubChar = nullChar
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
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    'ㅣ' -> {
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = 'ㅚ'
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    'ᆞ' -> {
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = 'ㅛ'
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    'ᆢ' -> {

                    }
                    else -> {
                        inputConnection.commitText(middleChar.toString(), 1)
                        state = 1
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㅛ' -> when (c) {
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        inputConnection.commitText(middleChar.toString(), 1)
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = c
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    'ᆞ' -> {
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = 'ㅗ'
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    'ᆢ' -> {

                    }
                    else -> {
                        inputConnection.commitText(middleChar.toString(), 1)
                        state = 1
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㅘ' -> when (c) {
                    'ㅏ', 'ㅗ', 'ㅡ' -> {
                        inputConnection.commitText(middleChar.toString(), 1)
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = c
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    'ㅣ' -> {
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = 'ㅙ'
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    'ᆞ', 'ᆢ' -> {

                    }
                    else -> {
                        inputConnection.commitText(middleChar.toString(), 1)
                        state = 1
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㅜ' -> when (c) {
                    'ㅏ' -> {
                        if (middleSubChar == nullChar) {
                            firstChar = nullChar
                            firstSubChar = nullChar
                            middleChar = 'ㅜ'
                            middleSubChar = 'ㅏ'
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            inputConnection.setComposingText(
                                middleChar.toString() + middleSubChar.toString(),
                                1
                            )
                        } else {
                            firstChar = nullChar
                            firstSubChar = nullChar
                            middleChar = 'ㅝ'
                            middleSubChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            inputConnection.setComposingText(middleChar.toString(), 1)
                        }
                    }
                    'ㅗ' -> {
                        if (middleSubChar == nullChar) {
                            firstChar = nullChar
                            firstSubChar = nullChar
                            middleChar = 'ㅗ'
                            middleSubChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            inputConnection.setComposingText(middleChar.toString(), 1)
                        } else {
                            inputConnection.finishComposingText()
                            firstChar = nullChar
                            firstSubChar = nullChar
                            middleChar = c
                            middleSubChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            inputConnection.setComposingText(middleChar.toString(), 1)
                        }
                    }
                    'ㅡ' -> {
                        inputConnection.finishComposingText()
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = c
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    'ㅣ' -> {
                        if (middleSubChar == nullChar) {
                            firstChar = nullChar
                            firstSubChar = nullChar
                            middleChar = 'ㅟ'
                            middleSubChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            inputConnection.setComposingText(middleChar.toString(), 1)
                        } else {
                            inputConnection.finishComposingText()
                            firstChar = nullChar
                            firstSubChar = nullChar
                            middleChar = 'ㅐ'
                            middleSubChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            inputConnection.setComposingText(middleChar.toString(), 1)
                        }
                    }
                    'ᆞ' -> {
                        if (middleSubChar == nullChar) {
                            firstChar = nullChar
                            firstSubChar = nullChar
                            middleChar = 'ㅠ'
                            middleSubChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            inputConnection.setComposingText(middleChar.toString(), 1)
                        } else {
                            inputConnection.finishComposingText()
                            firstChar = nullChar
                            firstSubChar = nullChar
                            middleChar = 'ㅑ'
                            middleSubChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            inputConnection.setComposingText(middleChar.toString(), 1)
                        }
                    }
                    'ᆢ' -> {

                    }
                    else -> {
                        if (middleSubChar == nullChar) {
                            inputConnection.commitText(middleChar.toString(), 1)
                            state = 1
                            firstChar = c
                            firstSubChar = nullChar
                            middleChar = nullChar
                            middleSubChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            inputConnection.setComposingText(firstChar.toString(), 1)
                        } else {
                            inputConnection.finishComposingText()
                            state = 1
                            firstChar = c
                            firstSubChar = nullChar
                            middleChar = nullChar
                            middleSubChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            inputConnection.setComposingText(firstChar.toString(), 1)
                        }
                    }
                }
                'ㅠ' -> when (c) {
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        inputConnection.commitText(middleChar.toString(), 1)
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = c
                        middleSubChar = nullChar
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
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    'ᆢ' -> {

                    }
                    else -> {
                        inputConnection.commitText(middleChar.toString(), 1)
                        state = 1
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㅝ' -> when (c) {
                    'ㅏ', 'ㅗ', 'ㅡ' -> {
                        inputConnection.commitText(middleChar.toString(), 1)
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = c
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    'ㅣ' -> {
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = 'ㅞ'
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    'ᆞ', 'ᆢ' -> {

                    }
                    else -> {
                        inputConnection.commitText(middleChar.toString(), 1)
                        state = 1
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㅡ' -> when (c) {
                    'ㅏ', 'ㅗ', 'ㅡ' -> {
                        inputConnection.commitText(middleChar.toString(), 1)
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = c
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    'ㅣ' -> {
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = 'ㅢ'
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    'ᆞ', 'ᆢ' -> {

                    }
                    else -> {
                        inputConnection.commitText(middleChar.toString(), 1)
                        state = 1
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㅣ' -> when (c) {
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        inputConnection.commitText(middleChar.toString(), 1)
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = c
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    'ᆞ', 'ᆢ' -> {

                    }
                    else -> {
                        inputConnection.commitText(middleChar.toString(), 1)
                        state = 1
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        middleSubChar = nullChar
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
                    'ᆞ' -> {
                        when (firstSubChar) {
                            nullChar -> {
                                firstChar = 'ㅋ'
                                firstSubChar = nullChar
                                middleChar = nullChar
                                middleSubChar = nullChar
                                finalChar = nullChar
                                finalSubChar = nullChar
                                composeResult()
                                composedResult = nullChar
                                inputConnection.setComposingText(firstChar.toString(), 1)
                            }
                            'ㅈ' -> {
                                firstChar = 'ㄱ'
                                firstSubChar = 'ㅊ'
                                middleChar = nullChar
                                middleSubChar = nullChar
                                finalChar = nullChar
                                finalSubChar = nullChar
                                composeResult()
                                composedResult = nullChar
                                inputConnection.setComposingText(
                                    firstChar.toString() + firstSubChar.toString(),
                                    1
                                )
                            }
                            'ㅊ' -> {
                                firstChar = 'ㄳ'
                                firstSubChar = nullChar
                                middleChar = nullChar
                                middleSubChar = nullChar
                                finalChar = nullChar
                                finalSubChar = nullChar
                                composeResult()
                                composedResult = nullChar
                                inputConnection.setComposingText(firstChar.toString(), 1)
                            }
                            else -> {

                            }
                        }
                    }
                    'ᆢ' -> when (firstSubChar) {
                        nullChar -> {
                            firstChar = 'ㄲ'
                            firstSubChar = nullChar
                            middleChar = nullChar
                            middleSubChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            inputConnection.setComposingText(firstChar.toString(), 1)
                        }
                        'ㅆ' -> {
                            firstChar = 'ㄳ'
                            firstSubChar = nullChar
                            middleChar = nullChar
                            middleSubChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            inputConnection.setComposingText(firstChar.toString(), 1)
                        }
                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> when ( firstSubChar ) {
                        nullChar -> {
                            state = 2
                            firstSubChar = nullChar
                            middleChar = c
                            middleSubChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            inputConnection.setComposingText(composedResult.toString(), 1)
                        }
                        'ㅆ' -> {
                            inputConnection.commitText(firstChar.toString(), 1)
                            state = 2
                            firstChar = firstSubChar
                            firstSubChar = nullChar
                            middleChar = c
                            middleSubChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            inputConnection.setComposingText(composedResult.toString(), 1)
                        }
                    }
                    'ㅅ' -> when ( firstSubChar ) {
                        nullChar -> {
                            firstChar = 'ㄳ'
                            firstSubChar = nullChar
                            middleChar = nullChar
                            middleSubChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            inputConnection.setComposingText(firstChar.toString(), 1)
                        }
                        else -> {
                            inputConnection.commitText(firstChar.toString()+firstSubChar.toString(), 1)
                            firstChar = c
                            firstSubChar = nullChar
                            middleChar = nullChar
                            middleSubChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            inputConnection.setComposingText(firstChar.toString(), 1)
                        }
                    }
                    else -> when ( firstSubChar ) {
                        nullChar -> {
                            inputConnection.commitText(firstChar.toString(), 1)
                            firstChar = c
                            firstSubChar = nullChar
                            middleChar = nullChar
                            middleSubChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            inputConnection.setComposingText(firstChar.toString(), 1)
                        }
                        else -> {
                            inputConnection.commitText(firstChar.toString()+firstSubChar.toString(), 1)
                            firstChar = c
                            firstSubChar = nullChar
                            middleChar = nullChar
                            middleSubChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            inputConnection.setComposingText(firstChar.toString(), 1)
                        }
                    }
                }
                'ㄲ' -> when (c) {
                    'ᆞ' -> {

                    }
                    'ᆢ' -> {
                        firstChar = 'ㄱ'
                        firstSubChar = nullChar
                        middleChar = nullChar
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        state = 2
                        firstSubChar = nullChar
                        middleChar = c
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    else -> {
                        inputConnection.commitText(firstChar.toString(), 1)
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㄳ' -> when (c) {
                    'ᆞ' -> {
                        firstChar = 'ㄱ'
                        firstSubChar = 'ㅈ'
                        middleChar = nullChar
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(
                            firstChar.toString() + firstSubChar.toString(),
                            1
                        )
                    }
                    'ᆢ' -> {
                        inputConnection.commitText(firstChar.toString(), 1)
                        firstChar = 'ㅆ'
                        firstSubChar = nullChar
                        middleChar = nullChar
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        inputConnection.commitText("ㄱ", 1)
                        state = 2
                        firstChar = 'ㅅ'
                        firstSubChar = nullChar
                        middleChar = c
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    else -> {
                        inputConnection.commitText(firstChar.toString(), 1)
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㄴ' -> when (c) {
                    'ᆞ' -> when ( firstSubChar ) {
                        nullChar -> {
                            firstChar = 'ㄷ'
                            firstSubChar = nullChar
                            middleChar = nullChar
                            middleSubChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            inputConnection.setComposingText(firstChar.toString(), 1)
                        }
                        'ㅅ' -> {
                            firstChar = 'ㄵ'
                            firstSubChar = nullChar
                            middleChar = nullChar
                            middleSubChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            inputConnection.setComposingText(firstChar.toString(), 1)
                        }
                        'ㅇ' -> {
                            firstChar = 'ㄶ'
                            firstSubChar = nullChar
                            middleChar = nullChar
                            middleSubChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            inputConnection.setComposingText(firstChar.toString(), 1)
                        }
                        'ㅊ' -> {
                            firstChar = 'ㄴ'
                            firstSubChar = 'ㅅ'
                            middleChar = nullChar
                            middleSubChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            inputConnection.setComposingText(firstChar.toString(), 1)
                        }
                        else -> {

                        }
                    }
                    'ᆢ' -> when ( firstSubChar ) {
                        'ㅉ' -> {
                            firstChar = 'ㄵ'
                            firstSubChar = nullChar
                            middleChar = nullChar
                            middleSubChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            inputConnection.setComposingText(firstChar.toString(), 1)
                        }
                        else -> {

                        }
                    }
                    'ㅅ' -> when ( firstSubChar ) {
                        nullChar -> {
                            firstChar = 'ㄴ'
                            firstSubChar = 'ㅅ'
                            middleChar = nullChar
                            middleSubChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            inputConnection.setComposingText(firstChar.toString() + firstSubChar.toString(), 1)
                        }
                        else -> {
                            inputConnection.commitText(firstChar.toString()+firstSubChar.toString(), 1)
                            firstChar = c
                            firstSubChar = nullChar
                            middleChar = nullChar
                            middleSubChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            inputConnection.setComposingText(firstChar.toString(), 1)

                        }
                    }
                    'ㅇ' -> when ( firstSubChar ) {
                        nullChar -> {
                            firstChar = 'ㄴ'
                            firstSubChar = 'ㅇ'
                            middleChar = nullChar
                            middleSubChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            inputConnection.setComposingText(firstChar.toString() + firstSubChar.toString(), 1)
                        }
                        else -> {
                            inputConnection.commitText(firstChar.toString()+firstSubChar.toString(), 1)
                            firstChar = c
                            firstSubChar = nullChar
                            middleChar = nullChar
                            middleSubChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            inputConnection.setComposingText(firstChar.toString(), 1)
                        }
                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> when ( firstSubChar ) {
                        nullChar -> {
                            state = 2
                            firstSubChar = nullChar
                            middleChar = c
                            middleSubChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            inputConnection.setComposingText(composedResult.toString(), 1)
                        }
                        else -> {
                            inputConnection.commitText(firstChar.toString(), 1)
                            state = 2
                            firstChar = firstSubChar
                            firstSubChar = nullChar
                            middleChar = c
                            middleSubChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            inputConnection.setComposingText(composedResult.toString(), 1)
                        }
                    }
                    else -> when ( firstSubChar ) {
                        nullChar -> {
                            inputConnection.commitText(firstChar.toString(), 1)
                            firstChar = c
                            firstSubChar = nullChar
                            middleChar = nullChar
                            middleSubChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            inputConnection.setComposingText(firstChar.toString(), 1)
                        }
                        else -> {
                            inputConnection.commitText(firstChar.toString()+firstSubChar.toString(), 1)
                            firstChar = c
                            firstSubChar = nullChar
                            middleChar = nullChar
                            middleSubChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            inputConnection.setComposingText(firstChar.toString(), 1)
                        }
                    }
                }
                'ㄵ' -> when (c) {
                    'ᆞ' -> {
                        firstChar = 'ㄴ'
                        firstSubChar = 'ㅊ'
                        middleChar = nullChar
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(
                            firstChar.toString() + firstSubChar.toString(),
                            1
                        )
                    }
                    'ᆢ' -> {
                        firstChar = 'ㄴ'
                        firstSubChar = 'ㅉ'
                        middleChar = nullChar
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString()+firstSubChar.toString(), 1)
                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        inputConnection.commitText("ㄴ", 1)
                        state = 2
                        firstChar = 'ㅈ'
                        firstSubChar = nullChar
                        middleChar = c
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    else -> {
                        inputConnection.commitText(firstChar.toString(), 1)
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㄶ' -> when (c) {
                    'ᆞ' -> {
                        firstChar = 'ㄴ'
                        firstSubChar = 'ㅇ'
                        middleChar = nullChar
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(
                            firstChar.toString() + firstSubChar.toString(),
                            1
                        )
                    }
                    'ᆢ' -> {

                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        inputConnection.commitText("ㄴ", 1)
                        state = 2
                        firstChar = 'ㅎ'
                        firstSubChar = nullChar
                        middleChar = c
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    else -> {
                        inputConnection.commitText(firstChar.toString(), 1)
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㄷ' -> when (c) {
                    'ᆞ' -> {
                        firstChar = 'ㅌ'
                        firstSubChar = nullChar
                        middleChar = nullChar
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                    'ᆢ' -> {
                        firstChar = 'ㄸ'
                        firstSubChar = nullChar
                        middleChar = nullChar
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        state = 2
                        firstSubChar = nullChar
                        middleChar = c
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    else -> {
                        inputConnection.commitText(firstChar.toString(), 1)
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㄸ' -> when (c) {
                    'ᆞ' -> {

                    }
                    'ᆢ' -> {
                        firstChar = 'ㄷ'
                        firstSubChar = nullChar
                        middleChar = nullChar
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        state = 2
                        firstSubChar = nullChar
                        middleChar = c
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    else -> {
                        inputConnection.commitText(firstChar.toString(), 1)
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㄹ' -> when (c) {
                    'ᆞ' -> when ( firstSubChar ) {
                        nullChar -> {

                        }
                        'ㅇ' -> {
                            firstChar = 'ㅀ'
                            firstSubChar = nullChar
                            middleChar = nullChar
                            middleSubChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            inputConnection.setComposingText(firstChar.toString(), 1)
                        }
                        'ㅈ' -> {
                            firstChar = 'ㄹ'
                            firstSubChar = 'ㅊ'
                            middleChar = nullChar
                            middleSubChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            inputConnection.setComposingText(
                                firstChar.toString() + firstSubChar.toString(),
                                1
                            )
                        }
                        'ㅊ' -> {
                            firstChar = 'ㄽ'
                            firstSubChar = nullChar
                            middleChar = nullChar
                            middleSubChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            inputConnection.setComposingText(firstChar.toString(), 1)
                        }
                        'ㅋ' -> {
                            firstChar = 'ㄺ'
                            firstSubChar = nullChar
                            middleChar = nullChar
                            middleSubChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            inputConnection.setComposingText(firstChar.toString(), 1)
                        }
                        else -> {

                        }
                    }
                    'ᆢ' -> when ( firstSubChar ) {
                        nullChar -> {

                        }
                        'ㄲ' -> {
                            firstChar = 'ㄺ'
                            firstSubChar = nullChar
                            middleChar = nullChar
                            middleSubChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            inputConnection.setComposingText(firstChar.toString(), 1)
                        }
                        'ㅃ' -> {
                            firstChar = 'ㄼ'
                            firstSubChar = nullChar
                            middleChar = nullChar
                            middleSubChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            inputConnection.setComposingText(firstChar.toString(), 1)
                        }
                        'ㅆ' -> {
                            firstChar = 'ㄽ'
                            firstSubChar = nullChar
                            middleChar = nullChar
                            middleSubChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            inputConnection.setComposingText(firstChar.toString(), 1)
                        }
                        else -> {

                        }
                    }
                    'ㄱ' -> when ( firstSubChar ) {
                        nullChar -> {
                            firstChar = 'ㄺ'
                            firstSubChar = nullChar
                            middleChar = nullChar
                            middleSubChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            inputConnection.setComposingText(firstChar.toString(), 1)
                        }
                        else -> {
                            inputConnection.commitText(firstChar.toString()+firstSubChar.toString(), 1)
                            firstChar = c
                            firstSubChar = nullChar
                            middleChar = nullChar
                            middleSubChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            inputConnection.setComposingText(firstChar.toString(), 1)
                        }
                    }
                    'ㅁ' -> when ( firstSubChar ) {
                        nullChar -> {
                            firstChar = 'ㄻ'
                            firstSubChar = nullChar
                            middleChar = nullChar
                            middleSubChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            inputConnection.setComposingText(firstChar.toString(), 1)
                        }
                        else -> {
                            inputConnection.commitText(firstChar.toString()+firstSubChar.toString(), 1)
                            firstChar = c
                            firstSubChar = nullChar
                            middleChar = nullChar
                            middleSubChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            inputConnection.setComposingText(firstChar.toString(), 1)
                        }
                    }
                    'ㅅ' -> when ( firstSubChar ) {
                        nullChar -> {
                            firstChar = 'ㄽ'
                            firstSubChar = nullChar
                            middleChar = nullChar
                            middleSubChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            inputConnection.setComposingText(firstChar.toString(), 1)
                        }
                        else -> {
                            inputConnection.commitText(firstChar.toString()+firstSubChar.toString(), 1)
                            firstChar = c
                            firstSubChar = nullChar
                            middleChar = nullChar
                            middleSubChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            inputConnection.setComposingText(firstChar.toString(), 1)
                        }
                    }
                    'ㅇ' -> when ( firstSubChar ) {
                        nullChar -> {
                            firstChar = 'ㄹ'
                            firstSubChar = 'ㅇ'
                            middleChar = nullChar
                            middleSubChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            inputConnection.setComposingText(firstChar.toString() + firstSubChar.toString(), 1)
                        }
                        else -> {
                            inputConnection.commitText(firstChar.toString()+firstSubChar.toString(), 1)
                            firstChar = c
                            firstSubChar = nullChar
                            middleChar = nullChar
                            middleSubChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            inputConnection.setComposingText(firstChar.toString() + firstSubChar.toString(), 1)
                        }
                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> when ( firstSubChar ) {
                        nullChar -> {
                            state = 2
                            firstSubChar = nullChar
                            middleChar = c
                            middleSubChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            inputConnection.setComposingText(composedResult.toString(), 1)
                        }
                        else -> {
                            inputConnection.commitText(firstChar.toString(), 1)
                            state = 2
                            firstChar = firstSubChar
                            firstSubChar = nullChar
                            middleChar = c
                            middleSubChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            inputConnection.setComposingText(composedResult.toString(), 1)
                        }
                    }
                    else -> when ( firstSubChar ) {
                        nullChar -> {
                            inputConnection.commitText(firstChar.toString(), 1)
                            firstChar = c
                            firstSubChar = nullChar
                            middleChar = nullChar
                            middleSubChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            inputConnection.setComposingText(firstChar.toString(), 1)
                        }
                        else -> {
                            inputConnection.commitText(firstChar.toString()+firstSubChar.toString(), 1)
                            firstChar = c
                            firstSubChar = nullChar
                            middleChar = nullChar
                            middleSubChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            inputConnection.setComposingText(firstChar.toString(), 1)
                        }
                    }
                }
                'ㄺ' -> when (c) {
                    'ᆞ' -> {
                        firstChar = 'ㄹ'
                        firstSubChar = 'ㅋ'
                        middleChar = nullChar
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(
                            firstChar.toString() + firstSubChar.toString(),
                            1
                        )
                    }
                    'ᆢ' -> {
                        firstChar = 'ㄹ'
                        firstSubChar = 'ㄲ'
                        middleChar = nullChar
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(
                            firstChar.toString() + firstSubChar.toString(),
                            1
                        )
                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        inputConnection.commitText("ㄹ", 1)
                        state = 2
                        firstChar = 'ㄱ'
                        firstSubChar = nullChar
                        middleChar = c
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    else -> {
                        inputConnection.commitText(firstChar.toString(), 1)
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㄻ' -> when (c) {
                    'ᆞ' -> {
                        firstChar = 'ㄼ'
                        firstSubChar = nullChar
                        middleChar = nullChar
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                    'ᆢ' -> {

                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        inputConnection.commitText("ㄹ", 1)
                        state = 2
                        firstChar = 'ㅁ'
                        firstSubChar = nullChar
                        middleChar = c
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    else -> {
                        inputConnection.commitText(firstChar.toString(), 1)
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㄼ' -> when (c) {
                    'ᆞ' -> {
                        firstChar = 'ㄿ'
                        firstSubChar = nullChar
                        middleChar = nullChar
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                    'ᆢ' -> {
                        firstChar = 'ㄹ'
                        firstSubChar = 'ㅃ'
                        middleChar = nullChar
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(
                            firstChar.toString() + firstSubChar.toString(),
                            1
                        )
                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        inputConnection.commitText("ㄹ", 1)
                        state = 2
                        firstChar = 'ㅂ'
                        firstSubChar = nullChar
                        middleChar = c
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    else -> {
                        inputConnection.commitText(firstChar.toString(), 1)
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㄽ' -> when (c) {
                    'ᆞ' -> {
                        firstChar = 'ㄹ'
                        firstSubChar = 'ㅈ'
                        middleChar = nullChar
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(
                            firstChar.toString() + firstSubChar.toString(),
                            1
                        )
                    }
                    'ᆢ' -> {
                        firstChar = 'ㄹ'
                        firstSubChar = 'ㅆ'
                        middleChar = nullChar
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(
                            firstChar.toString() + firstSubChar.toString(),
                            1
                        )
                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        inputConnection.commitText("ㄹ", 1)
                        state = 2
                        firstChar = 'ㅅ'
                        firstSubChar = nullChar
                        middleChar = c
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    else -> {
                        inputConnection.commitText(firstChar.toString(), 1)
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㄿ' -> when (c) {
                    'ᆞ' -> {
                        firstChar = 'ㄹ'
                        firstSubChar = 'ㅁ'
                        middleChar = nullChar
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString()+firstSubChar.toString(), 1)
                    }
                    'ᆢ' -> {

                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        inputConnection.commitText("ㄹ", 1)
                        state = 2
                        firstChar = 'ㅍ'
                        firstSubChar = nullChar
                        middleChar = c
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    else -> {
                        inputConnection.commitText(firstChar.toString(), 1)
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㅀ' -> when (c) {
                    'ᆞ' -> {
                        firstChar = 'ㄹ'
                        firstSubChar = 'ㅇ'
                        middleChar = nullChar
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(
                            firstChar.toString() + firstSubChar.toString(),
                            1
                        )
                    }
                    'ᆢ' -> {

                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        inputConnection.commitText("ㄹ", 1)
                        state = 2
                        firstChar = 'ㅎ'
                        firstSubChar = nullChar
                        middleChar = c
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    else -> {
                        inputConnection.commitText(firstChar.toString(), 1)
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㅁ' -> when (c) {
                    'ᆞ' -> {
                        firstChar = 'ㅂ'
                        firstSubChar = nullChar
                        middleChar = nullChar
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                    'ᆢ' -> {

                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        state = 2
                        firstSubChar = nullChar
                        middleChar = c
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    else -> {
                        inputConnection.commitText(firstChar.toString(), 1)
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㅂ' -> when (c) {
                    'ᆞ' -> when ( firstSubChar ) {
                        nullChar -> {
                            firstChar = 'ㅍ'
                            firstSubChar = nullChar
                            middleChar = nullChar
                            middleSubChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            inputConnection.setComposingText(firstChar.toString(), 1)
                        }
                        'ㅈ' -> {
                            firstChar = 'ㅂ'
                            firstSubChar = 'ㅈ'
                            middleChar = nullChar
                            middleSubChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            inputConnection.setComposingText(firstChar.toString()+firstSubChar.toString(), 1)
                        }
                        'ㅊ' -> {
                            firstChar = 'ㅄ'
                            firstSubChar = nullChar
                            middleChar = nullChar
                            middleSubChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            inputConnection.setComposingText(firstChar.toString(), 1)
                        }
                        else -> {

                        }
                    }
                    'ᆢ' -> when ( firstSubChar ) {
                        nullChar -> {
                            firstChar = 'ㅃ'
                            firstSubChar = nullChar
                            middleChar = nullChar
                            middleSubChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            inputConnection.setComposingText(firstChar.toString(), 1)
                        }
                        'ㅆ' -> {
                            firstChar = 'ㅄ'
                            firstSubChar = nullChar
                            middleChar = nullChar
                            middleSubChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            inputConnection.setComposingText(firstChar.toString(), 1)
                        }
                        'ㅈ' -> {
                            firstChar = 'ㅂ'
                            firstSubChar = 'ㅉ'
                            middleChar = nullChar
                            middleSubChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            inputConnection.setComposingText(firstChar.toString()+firstSubChar.toString(), 1)
                        }
                        'ㅉ' -> {
                            firstChar = 'ㅂ'
                            firstSubChar = 'ㅈ'
                            middleChar = nullChar
                            middleSubChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            inputConnection.setComposingText(firstChar.toString()+firstSubChar.toString(), 1)
                        }
                        else -> {

                        }
                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> when ( firstSubChar ) {
                        nullChar -> {
                            state = 2
                            firstSubChar = nullChar
                            middleChar = c
                            middleSubChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            inputConnection.setComposingText(composedResult.toString(), 1)
                        }
                        else -> {
                            inputConnection.commitText(firstChar.toString(), 1)
                            state = 2
                            firstChar = firstSubChar
                            firstSubChar = nullChar
                            middleChar = c
                            middleSubChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            inputConnection.setComposingText(composedResult.toString(), 1)
                        }
                    }
                    'ㅅ' -> when ( firstSubChar ) {
                        nullChar -> {
                            firstChar = 'ㅄ'
                            firstSubChar = nullChar
                            middleChar = nullChar
                            middleSubChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            inputConnection.setComposingText(firstChar.toString(), 1)
                        }
                        else -> {
                            inputConnection.commitText(firstChar.toString()+firstSubChar.toString(), 1)
                            firstChar = c
                            firstSubChar = nullChar
                            middleChar = nullChar
                            middleSubChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            inputConnection.setComposingText(firstChar.toString(), 1)
                        }
                    }
                    else -> when ( firstSubChar ) {
                        nullChar -> {
                            inputConnection.commitText(firstChar.toString(), 1)
                            firstChar = c
                            firstSubChar = nullChar
                            middleChar = nullChar
                            middleSubChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            inputConnection.setComposingText(firstChar.toString(), 1)
                        }
                        else -> {
                            inputConnection.commitText(firstChar.toString()+firstSubChar.toString(), 1)
                            firstChar = c
                            firstSubChar = nullChar
                            middleChar = nullChar
                            middleSubChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            inputConnection.setComposingText(firstChar.toString(), 1)
                        }
                    }
                }
                'ㅃ' -> when (c) {
                    'ᆞ' -> {

                    }
                    'ᆢ' -> {
                        firstChar = 'ㅂ'
                        firstSubChar = nullChar
                        middleChar = nullChar
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        state = 2
                        firstSubChar = nullChar
                        middleChar = c
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    else -> {
                        inputConnection.commitText(firstChar.toString(), 1)
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㅄ' -> when (c) {
                    'ᆞ' -> {
                        firstChar = 'ㅂ'
                        firstSubChar = 'ㅈ'
                        middleChar = nullChar
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(
                            firstChar.toString() + firstSubChar.toString(),
                            1
                        )
                    }
                    'ᆢ' -> {
                        firstChar = 'ㅂ'
                        firstSubChar = 'ㅆ'
                        middleChar = nullChar
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString() + firstSubChar.toString(), 1)
                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        inputConnection.commitText(firstChar.toString(), 1)
                        state = 2
                        firstChar = 'ㅅ'
                        firstSubChar = nullChar
                        middleChar = c
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    else -> {
                        inputConnection.commitText(firstChar.toString(), 1)
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㅅ' -> when (c) {
                    'ᆞ' -> {
                        firstChar = 'ㅈ'
                        firstSubChar = nullChar
                        middleChar = nullChar
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                    'ᆢ' -> {
                        firstChar = 'ㅆ'
                        firstSubChar = nullChar
                        middleChar = nullChar
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        state = 2
                        firstSubChar = nullChar
                        middleChar = c
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    else -> {
                        inputConnection.commitText(firstChar.toString(), 1)
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㅆ' -> when (c) {
                    'ᆞ' -> {

                    }
                    'ᆢ' -> {
                        firstChar = 'ㅅ'
                        firstSubChar = nullChar
                        middleChar = nullChar
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        state = 2
                        firstSubChar = nullChar
                        middleChar = c
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    else -> {
                        inputConnection.commitText(firstChar.toString(), 1)
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㅇ' -> when (c) {
                    'ᆞ' -> {
                        firstChar = 'ㅎ'
                        firstSubChar = nullChar
                        middleChar = nullChar
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                    'ᆢ' -> {

                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        state = 2
                        firstSubChar = nullChar
                        middleChar = c
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    else -> {
                        inputConnection.commitText(firstChar.toString(), 1)
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㅈ' -> when (c) {
                    'ᆞ' -> {
                        firstChar = 'ㅊ'
                        firstSubChar = nullChar
                        middleChar = nullChar
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                    'ᆢ' -> {
                        firstChar = 'ㅉ'
                        firstSubChar = nullChar
                        middleChar = nullChar
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        state = 2
                        firstSubChar = nullChar
                        middleChar = c
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    else -> {
                        inputConnection.commitText(firstChar.toString(), 1)
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㅉ' -> when (c) {
                    'ᆞ' -> {

                    }
                    'ᆢ' -> {
                        firstChar = 'ㅈ'
                        firstSubChar = nullChar
                        middleChar = nullChar
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        state = 2
                        firstSubChar = nullChar
                        middleChar = c
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    else -> {
                        inputConnection.commitText(firstChar.toString(), 1)
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㅊ' -> when (c) {
                    'ᆞ' -> {
                        firstChar = 'ㅅ'
                        firstSubChar = nullChar
                        middleChar = nullChar
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                    'ᆢ' -> {

                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        state = 2
                        firstSubChar = nullChar
                        middleChar = c
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    else -> {
                        inputConnection.commitText(firstChar.toString(), 1)
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㅋ' -> when (c) {
                    'ᆞ' -> {
                        firstChar = 'ㄱ'
                        firstSubChar = nullChar
                        middleChar = nullChar
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                    'ᆢ' -> {

                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        state = 2
                        firstSubChar = nullChar
                        middleChar = c
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    else -> {
                        inputConnection.commitText(firstChar.toString(), 1)
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㅌ' -> when (c) {
                    'ᆞ' -> {
                        firstChar = 'ㄷ'
                        firstSubChar = nullChar
                        middleChar = nullChar
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                    'ᆢ' -> {

                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        state = 2
                        firstSubChar = nullChar
                        middleChar = c
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    else -> {
                        inputConnection.commitText(firstChar.toString(), 1)
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㅍ' -> when (c) {
                    'ᆞ' -> {
                        firstChar = 'ㅁ'
                        firstSubChar = nullChar
                        middleChar = nullChar
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                    'ᆢ' -> {

                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        state = 2
                        firstSubChar = nullChar
                        middleChar = c
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    else -> {
                        inputConnection.commitText(firstChar.toString(), 1)
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㅎ' -> when (c) {
                    'ᆞ' -> {
                        firstChar = 'ㅇ'
                        firstSubChar = nullChar
                        middleChar = nullChar
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                    'ᆢ' -> {

                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        state = 2
                        firstSubChar = nullChar
                        middleChar = c
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    else -> {
                        inputConnection.commitText(firstChar.toString(), 1)
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
            }
            2 -> when (middleChar) {
                'ㅏ' -> when (c) {
                    'ᆞ' -> {
                        firstSubChar = nullChar
                        middleChar = 'ㅑ'
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ᆢ' -> {

                    }
                    'ㅏ' -> {
                        firstSubChar = nullChar
                        middleChar = 'ㅓ'
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ㅗ', 'ㅡ' -> {
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = -1
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = c
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    'ㅣ' -> {
                        firstSubChar = nullChar
                        middleChar = 'ㅐ'
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    else -> {
                        state = 3
                        firstSubChar = nullChar
                        middleSubChar = nullChar
                        finalChar = c
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                }
                'ㅐ', 'ㅔ', 'ㅒ', 'ㅖ', 'ㅙ', 'ㅚ', 'ㅞ', 'ㅟ', 'ㅢ' -> when (c) {
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = -1
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = c
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    'ᆞ', 'ᆢ' -> {

                    }
                    else -> {
                        state = 3
                        firstSubChar = nullChar
                        middleSubChar = nullChar
                        finalChar = c
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                }
                'ㅑ' -> when (c) {
                    'ㅏ', 'ㅗ', 'ㅡ' -> {
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = -1
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = c
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    'ᆞ' -> {
                        firstSubChar = nullChar
                        middleChar = 'ㅏ'
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ㅣ' -> {
                        firstSubChar = nullChar
                        middleChar = 'ㅒ'
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ᆢ' -> {

                    }
                    else -> {
                        state = 3
                        firstSubChar = nullChar
                        middleSubChar = nullChar
                        finalChar = c
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                }
                'ㅓ' -> when (c) {
                    'ㅏ' -> {
                        firstSubChar = nullChar
                        middleChar = 'ㅏ'
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ㅗ', 'ㅡ'  -> {
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = -1
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = c
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    'ㅣ' -> {
                        firstSubChar = nullChar
                        middleChar = 'ㅔ'
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ᆞ' -> {
                        firstSubChar = nullChar
                        middleChar = 'ㅕ'
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ᆢ' -> {

                    }
                    else -> {
                        state = 3
                        firstSubChar = nullChar
                        middleSubChar = nullChar
                        finalChar = c
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                }
                'ㅕ' -> when (c) {
                    'ㅏ', 'ㅗ', 'ㅡ' -> {
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = -1
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = c
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    'ᆞ' -> {
                        firstSubChar = nullChar
                        middleChar = 'ㅓ'
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ᆢ' -> {

                    }
                    'ㅣ' -> {
                        firstSubChar = nullChar
                        middleChar = 'ㅖ'
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    else -> {
                        state = 3
                        firstSubChar = nullChar
                        middleSubChar = nullChar
                        finalChar = c
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                }
                'ㅗ' -> when (c) {
                    'ㅏ' -> {
                        firstSubChar = nullChar
                        middleChar = 'ㅘ'
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ㅗ' -> {
                        firstSubChar = nullChar
                        middleChar = 'ㅜ'
                        middleSubChar = nullChar
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
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    'ㅣ' -> {
                        firstSubChar = nullChar
                        middleChar = 'ㅚ'
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ᆞ' -> {
                        firstSubChar = nullChar
                        middleChar = 'ㅛ'
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ᆢ' -> {

                    }
                    else -> {
                        state = 3
                        firstSubChar = nullChar
                        middleSubChar = nullChar
                        finalChar = c
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                }
                'ㅛ' -> when (c) {
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = -1
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = c
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    'ᆞ' -> {
                        firstSubChar = nullChar
                        middleChar = 'ㅗ'
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ᆢ' -> {

                    }
                    else -> {
                        state = 3
                        firstSubChar = nullChar
                        middleSubChar = nullChar
                        finalChar = c
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                }
                'ㅘ' -> when (c) {
                    'ㅏ', 'ㅗ', 'ㅡ' -> {
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = -1
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = c
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    'ㅣ' -> {
                        firstSubChar = nullChar
                        middleChar = 'ㅙ'
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ᆞ', 'ᆢ' -> {

                    }
                    else -> {
                        state = 3
                        firstSubChar = nullChar
                        middleSubChar = nullChar
                        finalChar = c
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                }
                'ㅜ' -> when (c) {
                    'ㅏ' -> {
                        if (middleSubChar == nullChar) {
                            firstSubChar = nullChar
                            middleChar = 'ㅜ'
                            middleSubChar = 'ㅏ'
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            inputConnection.setComposingText(
                                composedResult.toString() + middleSubChar.toString(),
                                1
                            )
                        } else {
                            firstSubChar = nullChar
                            middleChar = 'ㅝ'
                            middleSubChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            inputConnection.setComposingText(composedResult.toString(), 1)
                        }
                    }
                    'ㅗ' -> {
                        if (middleSubChar == nullChar) {
                            firstSubChar = nullChar
                            middleChar = 'ㅗ'
                            middleSubChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            inputConnection.setComposingText(composedResult.toString(), 1)
                        } else {
                            inputConnection.commitText(
                                composedResult.toString() + middleSubChar.toString(),
                                1
                            )
                            state = -1
                            firstChar = nullChar
                            firstSubChar = nullChar
                            middleChar = c
                            middleSubChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            inputConnection.setComposingText(middleChar.toString(), 1)
                        }
                    }
                    'ㅡ' -> {
                        if (middleSubChar == nullChar) {
                            inputConnection.commitText(composedResult.toString(), 1)
                        } else {
                            inputConnection.commitText(
                                composedResult.toString() + middleSubChar.toString(),
                                1
                            )
                        }
                        state = -1
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = c
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    'ㅣ' -> {
                        if (middleSubChar == nullChar) {
                            firstSubChar = nullChar
                            middleChar = 'ㅟ'
                            middleSubChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            inputConnection.setComposingText(composedResult.toString(), 1)
                        } else {
                            inputConnection.commitText(composedResult.toString(), 1)
                            state = -1
                            firstSubChar = nullChar
                            middleChar = 'ㅐ'
                            middleSubChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            inputConnection.setComposingText(middleChar.toString(), 1)
                        }
                    }
                    'ᆞ' -> {
                        if (middleSubChar == nullChar) {
                            firstSubChar = nullChar
                            middleChar = 'ㅠ'
                            middleSubChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            inputConnection.setComposingText(composedResult.toString(), 1)
                        } else {
                            inputConnection.commitText(composedResult.toString(), 1)
                            state = -1
                            firstChar = nullChar
                            firstSubChar = nullChar
                            middleChar = 'ㅑ'
                            middleSubChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            inputConnection.setComposingText(middleChar.toString(), 1)
                        }
                    }
                    'ᆢ' -> {

                    }
                    else -> {
                        if (middleSubChar == nullChar) {
                            state = 3
                            firstSubChar = nullChar
                            middleSubChar = nullChar
                            finalChar = c
                            finalSubChar = nullChar
                            composeResult()
                            inputConnection.setComposingText(composedResult.toString(), 1)
                        } else {
                            inputConnection.commitText(composedResult.toString() + middleSubChar.toString(), 1)
                            state = 1
                            firstChar = c
                            firstSubChar = nullChar
                            middleChar = nullChar
                            middleSubChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            inputConnection.setComposingText(firstChar.toString(), 1)
                        }
                    }
                }
                'ㅠ' -> when (c) {
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = -1
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = c
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    'ᆞ' -> {
                        firstSubChar = nullChar
                        middleChar = 'ㅜ'
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ᆢ' -> {

                    }
                    else -> {
                        state = 3
                        firstSubChar = nullChar
                        middleSubChar = nullChar
                        finalChar = c
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                }
                'ㅝ' -> when (c) {
                    'ㅏ', 'ㅗ', 'ㅡ' -> {
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = -1
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = c
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    'ㅣ' -> {
                        firstSubChar = nullChar
                        middleChar = 'ㅞ'
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ᆞ', 'ᆢ' -> {

                    }
                    else -> {
                        state = 3
                        firstSubChar = nullChar
                        middleSubChar = nullChar
                        finalChar = c
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                }
                'ㅡ' -> when (c) {
                    'ㅏ', 'ㅗ', 'ㅡ' -> {
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = -1
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = c
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    'ㅣ' -> {
                        firstSubChar = nullChar
                        middleChar = 'ㅢ'
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ᆞ', 'ᆢ' -> {

                    }
                    else -> {
                        state = 3
                        firstSubChar = nullChar
                        middleSubChar = nullChar
                        finalChar = c
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                }
                'ㅣ' -> when (c) {
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = -1
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = c
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    'ᆞ', 'ᆢ' -> {

                    }
                    else -> {
                        state = 3
                        firstSubChar = nullChar
                        middleSubChar = nullChar
                        finalChar = c
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                }
            }
            3 -> when (finalChar) {
                'ㄱ' -> when (c) {
                    'ᆞ' -> when (finalSubChar) {
                        nullChar -> {
                            firstSubChar = nullChar
                            middleSubChar = nullChar
                            finalChar = 'ㅋ'
                            finalSubChar = nullChar
                            composeResult()
                            inputConnection.setComposingText(composedResult.toString(), 1)
                        }
                        'ㅈ' -> {
                            firstSubChar = nullChar
                            middleSubChar = nullChar
                            finalChar = 'ㄱ'
                            finalSubChar = 'ㅊ'
                            composeResult()
                            inputConnection.setComposingText(
                                composedResult.toString() + finalSubChar.toString(),
                                1
                            )
                        }
                        'ㅊ' -> {
                            firstSubChar = nullChar
                            middleSubChar = nullChar
                            finalChar = 'ㄳ'
                            finalSubChar = nullChar
                            composeResult()
                            inputConnection.setComposingText(composedResult.toString(), 1)
                        }
                        else -> {

                        }
                    }
                    'ᆢ' -> when (finalSubChar) {
                        nullChar -> {
                            firstSubChar = nullChar
                            middleSubChar = nullChar
                            finalChar = 'ㄲ'
                            finalSubChar = nullChar
                            composeResult()
                            inputConnection.setComposingText(composedResult.toString(), 1)
                        }
                        'ㅈ' -> {
                            firstSubChar = nullChar
                            middleSubChar = nullChar
                            finalChar = 'ㄱ'
                            finalSubChar = 'ㅉ'
                            composeResult()
                            inputConnection.setComposingText(
                                composedResult.toString() + finalSubChar.toString(),
                                1
                            )
                        }
                        'ㅆ' -> {
                            firstSubChar = nullChar
                            middleSubChar = nullChar
                            finalChar = 'ㄳ'
                            finalSubChar = nullChar
                            composeResult()
                            inputConnection.setComposingText(composedResult.toString(), 1)
                        }
                        'ㅉ' -> {
                            firstSubChar = nullChar
                            middleSubChar = nullChar
                            finalChar = 'ㄱ'
                            finalSubChar = 'ㅈ'
                            composeResult()
                            inputConnection.setComposingText(
                                composedResult.toString() + finalSubChar.toString(),
                                1
                            )
                        }
                        else -> {

                        }
                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> when (finalSubChar) {
                        nullChar -> {
                            finalChar = nullChar
                            composeResult()
                            inputConnection.commitText(composedResult.toString(), 1)
                            state = 2
                            firstChar = 'ㄱ'
                            firstSubChar = nullChar
                            middleChar = c
                            middleSubChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            inputConnection.setComposingText(composedResult.toString(), 1)
                        }
                        else -> {
                            inputConnection.commitText(composedResult.toString(), 1)
                            state = 2
                            firstChar = finalSubChar
                            firstSubChar = nullChar
                            middleChar = c
                            middleSubChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            inputConnection.setComposingText(composedResult.toString(), 1)
                        }
                    }
                    'ㅅ' -> when (finalSubChar) {
                        nullChar -> {
                            firstSubChar = nullChar
                            middleSubChar = nullChar
                            finalChar = 'ㄳ'
                            finalSubChar = nullChar
                            composeResult()
                            inputConnection.setComposingText(composedResult.toString(), 1)
                        }
                        else -> {
                            inputConnection.commitText(
                                composedResult.toString() + finalSubChar.toString(),
                                1
                            )
                            state = 1
                            firstChar = c
                            firstSubChar = nullChar
                            middleChar = nullChar
                            middleSubChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            inputConnection.setComposingText(firstChar.toString(), 1)
                        }
                    }
                    else -> when (finalSubChar) {
                        nullChar -> {
                            inputConnection.commitText(composedResult.toString(), 1)
                            state = 1
                            firstChar = c
                            firstSubChar = nullChar
                            middleChar = nullChar
                            middleSubChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            inputConnection.setComposingText(firstChar.toString(), 1)
                        }
                        else -> {
                            inputConnection.commitText(
                                composedResult.toString() + finalSubChar.toString(),
                                1
                            )
                            state = 1
                            firstChar = c
                            firstSubChar = nullChar
                            middleChar = nullChar
                            middleSubChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            inputConnection.setComposingText(firstChar.toString(), 1)
                        }
                    }
                }
                'ㄲ' -> when (c) {
                    'ᆞ' -> {

                    }
                    'ᆢ' -> {
                        firstSubChar = nullChar
                        middleSubChar = nullChar
                        finalChar = 'ㄱ'
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        finalChar = nullChar
                        composeResult()
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 2
                        firstChar = 'ㄲ'
                        firstSubChar = nullChar
                        middleChar = c
                        middleSubChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    else -> {
                        inputConnection.finishComposingText()
                        state = 1
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㄳ' -> when (c) {
                    'ᆞ' -> {
                        firstSubChar = nullChar
                        middleSubChar = nullChar
                        finalChar = 'ㄱ'
                        finalSubChar = 'ㅈ'
                        composeResult()
                        inputConnection.setComposingText(
                            composedResult.toString() + finalSubChar.toString(),
                            1
                        )
                    }
                    'ᆢ' -> {
                        firstSubChar = nullChar
                        middleSubChar = nullChar
                        finalChar = 'ㄱ'
                        finalSubChar = 'ㅆ'
                        composeResult()
                        inputConnection.setComposingText(
                            composedResult.toString() + finalSubChar.toString(),
                            1
                        )
                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        finalChar = 'ㄱ'
                        composeResult()
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 2
                        firstChar = 'ㅅ'
                        firstSubChar = nullChar
                        middleChar = c
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    else -> {
                        inputConnection.finishComposingText()
                        state = 1
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㄴ' -> when (c) {
                    'ᆞ' -> when (finalSubChar) {
                        nullChar -> {
                            firstSubChar = nullChar
                            middleSubChar = nullChar
                            finalChar = 'ㄷ'
                            finalSubChar = nullChar
                            composeResult()
                            inputConnection.setComposingText(composedResult.toString(), 1)
                        }
                        'ㅅ' -> {
                            firstSubChar = nullChar
                            middleSubChar = nullChar
                            finalChar = 'ㄵ'
                            finalSubChar = nullChar
                            composeResult()
                            inputConnection.setComposingText(composedResult.toString(), 1)
                        }
                        'ㅇ' -> {
                            firstSubChar = nullChar
                            middleSubChar = nullChar
                            finalChar = 'ㄶ'
                            finalSubChar = nullChar
                            composeResult()
                            inputConnection.setComposingText(composedResult.toString(), 1)
                        }
                        'ㅊ' -> {
                            firstSubChar = nullChar
                            middleSubChar = nullChar
                            finalChar = 'ㄴ'
                            finalSubChar = 'ㅅ'
                            composeResult()
                            inputConnection.setComposingText(
                                composedResult.toString() + finalSubChar.toString(),
                                1
                            )
                        }
                    }
                    'ᆢ' -> when (finalSubChar) {
                        nullChar -> {

                        }
                        'ㅆ' -> {
                            firstSubChar = nullChar
                            middleSubChar = nullChar
                            finalChar = 'ㄴ'
                            finalSubChar = 'ㅅ'
                            composeResult()
                            inputConnection.setComposingText(
                                composedResult.toString() + finalSubChar.toString(),
                                1
                            )
                        }
                        'ㅉ' -> {
                            firstSubChar = nullChar
                            middleSubChar = nullChar
                            finalChar = 'ㄵ'
                            finalSubChar = nullChar
                            composeResult()
                            inputConnection.setComposingText(composedResult.toString(), 1)
                        }
                        else -> {

                        }
                    }
                    'ㅅ' -> when (finalSubChar) {
                        nullChar -> {
                            firstSubChar = nullChar
                            middleSubChar = nullChar
                            finalChar = 'ㄴ'
                            finalSubChar = 'ㅅ'
                            composeResult()
                            inputConnection.setComposingText(
                                composedResult.toString() + finalSubChar.toString(),
                                1
                            )
                        }
                        else -> {
                            inputConnection.commitText(
                                composedResult.toString() + finalSubChar.toString(),
                                1
                            )
                            state = 1
                            firstChar = c
                            firstSubChar = nullChar
                            middleChar = nullChar
                            middleSubChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            inputConnection.setComposingText(firstChar.toString(), 1)
                        }
                    }
                    'ㅇ' -> when (finalSubChar) {
                        nullChar -> {
                            firstSubChar = nullChar
                            middleSubChar = nullChar
                            finalChar = 'ㄴ'
                            finalSubChar = 'ㅇ'
                            composeResult()
                            inputConnection.setComposingText(composedResult.toString() + finalSubChar.toString(), 1)
                        }
                        else -> {
                            inputConnection.commitText(
                                composedResult.toString() + finalSubChar.toString(),
                                1
                            )
                            state = 1
                            firstChar = c
                            firstSubChar = nullChar
                            middleChar = nullChar
                            middleSubChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            inputConnection.setComposingText(firstChar.toString(), 1)
                        }
                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> when (finalSubChar) {
                        nullChar -> {
                            finalChar = nullChar
                            composeResult()
                            inputConnection.commitText(composedResult.toString(), 1)
                            state = 2
                            firstChar = 'ㄴ'
                            firstSubChar = nullChar
                            middleChar = c
                            middleSubChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            inputConnection.setComposingText(composedResult.toString(), 1)
                        }
                        else -> {
                            inputConnection.commitText(composedResult.toString(), 1)
                            state = 2
                            firstChar = finalSubChar
                            firstSubChar = nullChar
                            middleChar = c
                            middleSubChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            inputConnection.setComposingText(composedResult.toString(), 1)
                        }
                    }
                    else -> when (finalSubChar) {
                        nullChar -> {
                            inputConnection.finishComposingText()
                            state = 1
                            firstChar = c
                            firstSubChar = nullChar
                            middleChar = nullChar
                            middleSubChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            inputConnection.setComposingText(firstChar.toString(), 1)
                        }
                        else -> {
                            inputConnection.commitText(
                                composedResult.toString() + finalSubChar.toString(),
                                1
                            )
                            state = 1
                            firstChar = c
                            firstSubChar = nullChar
                            middleChar = nullChar
                            middleSubChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            inputConnection.setComposingText(firstChar.toString(), 1)
                        }
                    }
                }
                'ㄵ' -> when (c) {
                    'ᆞ' -> {
                        firstSubChar = nullChar
                        middleSubChar = nullChar
                        finalChar = 'ㄴ'
                        finalSubChar = 'ㅊ'
                        composeResult()
                        inputConnection.setComposingText(
                            composedResult.toString() + finalSubChar.toString(),
                            1
                        )
                    }
                    'ᆢ' -> {
                        firstSubChar = nullChar
                        middleSubChar = nullChar
                        finalChar = 'ㄴ'
                        finalSubChar = 'ㅉ'
                        composeResult()
                        inputConnection.setComposingText(
                            composedResult.toString() + finalSubChar.toString(),
                            1
                        )
                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        finalChar = 'ㄴ'
                        composeResult()
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 2
                        firstChar = 'ㅈ'
                        firstSubChar = nullChar
                        middleChar = c
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    else -> {
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 1
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㄶ' -> when (c) {
                    'ᆞ' -> {
                        firstSubChar = nullChar
                        middleSubChar = nullChar
                        finalChar = 'ㄴ'
                        finalSubChar = 'ㅇ'
                        composeResult()
                        inputConnection.setComposingText(
                            composedResult.toString() + finalSubChar.toString(),
                            1
                        )
                    }
                    'ᆢ' -> {

                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        finalChar = 'ㄴ'
                        composeResult()
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 2
                        firstChar = 'ㅎ'
                        firstSubChar = nullChar
                        middleChar = c
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    else -> {
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 1
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㄷ' -> when (c) {
                    'ᆞ' -> {
                        firstSubChar = nullChar
                        middleSubChar = nullChar
                        finalChar = 'ㅌ'
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ᆢ' -> {
                        firstSubChar = nullChar
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = 'ㄸ'
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString() + finalSubChar.toString(), 1)
                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        finalChar = nullChar
                        composeResult()
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 2
                        firstChar = 'ㄷ'
                        firstSubChar = nullChar
                        middleChar = c
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    else -> {
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 1
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㄹ' -> when (c) {
                    'ᆞ' -> when (finalSubChar) {
                        nullChar -> {

                        }
                        'ㅋ' -> {
                            firstSubChar = nullChar
                            middleSubChar = nullChar
                            finalChar = 'ㄺ'
                            finalSubChar = nullChar
                            composeResult()
                            inputConnection.setComposingText(composedResult.toString(), 1)
                        }
                        'ㅇ' -> {
                            firstSubChar = nullChar
                            middleSubChar = nullChar
                            finalChar = 'ㅀ'
                            finalSubChar = nullChar
                            composeResult()
                            inputConnection.setComposingText(composedResult.toString(), 1)
                        }
                        'ㅈ' -> {
                            firstSubChar = nullChar
                            middleSubChar = nullChar
                            finalChar = 'ㄹ'
                            finalSubChar = 'ㅊ'
                            composeResult()
                            inputConnection.setComposingText(
                                composedResult.toString() + finalSubChar.toString(),
                                1
                            )
                        }
                        'ㅊ' -> {
                            firstSubChar = nullChar
                            middleSubChar = nullChar
                            finalChar = 'ㄽ'
                            finalSubChar = nullChar
                            composeResult()
                            inputConnection.setComposingText(composedResult.toString(), 1)
                        }
                        else -> {

                        }
                    }
                    'ᆢ' -> when (finalSubChar) {
                        nullChar -> {

                        }
                        'ㄲ' -> {
                            firstSubChar = nullChar
                            middleSubChar = nullChar
                            finalChar = 'ㄺ'
                            finalSubChar = nullChar
                            composeResult()
                            inputConnection.setComposingText(composedResult.toString(), 1)
                        }
                        'ㅃ' -> {
                            firstSubChar = nullChar
                            middleSubChar = nullChar
                            finalChar = 'ㄼ'
                            finalSubChar = nullChar
                            composeResult()
                            inputConnection.setComposingText(composedResult.toString(), 1)
                        }
                        'ㅆ' -> {
                            firstSubChar = nullChar
                            middleSubChar = nullChar
                            finalChar = 'ㄽ'
                            finalSubChar = nullChar
                            composeResult()
                            inputConnection.setComposingText(composedResult.toString(), 1)
                        }
                        'ㅈ' -> {
                            firstSubChar = nullChar
                            middleSubChar = nullChar
                            finalChar = 'ㄹ'
                            finalSubChar = 'ㅉ'
                            composeResult()
                            inputConnection.setComposingText(
                                composedResult.toString() + finalSubChar.toString(),
                                1
                            )
                        }
                        'ㅉ' -> {
                            firstSubChar = nullChar
                            middleSubChar = nullChar
                            finalChar = 'ㄹ'
                            finalSubChar = 'ㅈ'
                            composeResult()
                            inputConnection.setComposingText(
                                composedResult.toString() + finalSubChar.toString(),
                                1
                            )
                        }
                        else -> {

                        }
                    }
                    'ㄱ' -> when (finalSubChar) {
                        nullChar -> {
                            firstSubChar = nullChar
                            middleSubChar = nullChar
                            finalChar = 'ㄺ'
                            finalSubChar = nullChar
                            composeResult()
                            inputConnection.setComposingText(composedResult.toString(), 1)
                        }
                        else -> {
                            inputConnection.commitText(composedResult.toString() + finalSubChar.toString(), 1)
                            state = 1
                            firstChar = c
                            firstSubChar = nullChar
                            middleChar = nullChar
                            middleSubChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            inputConnection.setComposingText(firstChar.toString(), 1)
                        }
                    }
                    'ㅁ' -> when (finalSubChar) {
                        nullChar -> {
                            firstSubChar = nullChar
                            middleSubChar = nullChar
                            finalChar = 'ㄻ'
                            finalSubChar = nullChar
                            composeResult()
                            inputConnection.setComposingText(composedResult.toString(), 1)
                        }
                        else -> {
                            inputConnection.commitText(
                                composedResult.toString() + finalSubChar.toString(),
                                1
                            )
                            state = 1
                            firstChar = c
                            firstSubChar = nullChar
                            middleChar = nullChar
                            middleSubChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            inputConnection.setComposingText(firstChar.toString(), 1)
                        }
                    }
                    'ㅅ' -> when (finalSubChar) {
                        nullChar -> {
                            firstSubChar = nullChar
                            middleSubChar = nullChar
                            finalChar = 'ㄽ'
                            finalSubChar = nullChar
                            composeResult()
                            inputConnection.setComposingText(composedResult.toString(), 1)
                        }
                        else -> {
                            inputConnection.commitText(
                                composedResult.toString() + finalSubChar.toString(),
                                1
                            )
                            state = 1
                            firstChar = c
                            firstSubChar = nullChar
                            middleChar = nullChar
                            middleSubChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            inputConnection.setComposingText(firstChar.toString(), 1)
                        }
                    }
                    'ㅇ' -> when (finalSubChar) {
                        nullChar -> {
                            firstSubChar = nullChar
                            middleSubChar = nullChar
                            finalChar = 'ㄹ'
                            finalSubChar = 'ㅇ'
                            composeResult()
                            inputConnection.setComposingText(
                                composedResult.toString() + finalSubChar.toString(),
                                1
                            )
                        }
                        else -> {
                            inputConnection.commitText(
                                composedResult.toString() + finalSubChar.toString(),
                                1
                            )
                            state = 1
                            firstChar = c
                            firstSubChar = nullChar
                            middleChar = nullChar
                            middleSubChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            inputConnection.setComposingText(firstChar.toString(), 1)
                        }
                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> when (finalSubChar) {
                        nullChar -> {
                            finalChar = nullChar
                            composeResult()
                            inputConnection.commitText(composedResult.toString(), 1)
                            state = 2
                            firstChar = 'ㄹ'
                            firstSubChar = nullChar
                            middleChar = c
                            middleSubChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            inputConnection.setComposingText(composedResult.toString(), 1)
                        }
                        else -> {
                            inputConnection.commitText(composedResult.toString(), 1)
                            state = 2
                            firstChar = finalSubChar
                            firstSubChar = nullChar
                            middleChar = c
                            middleSubChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            inputConnection.setComposingText(composedResult.toString(), 1)
                        }
                    }
                    else -> when (finalSubChar) {
                        nullChar -> {
                            inputConnection.commitText(composedResult.toString(), 1)
                            state = 1
                            firstChar = c
                            firstSubChar = nullChar
                            middleChar = nullChar
                            middleSubChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            inputConnection.setComposingText(firstChar.toString(), 1)
                        }
                        else -> {
                            inputConnection.commitText(
                                composedResult.toString() + finalSubChar.toString(),
                                1
                            )
                            state = 1
                            firstChar = c
                            firstSubChar = nullChar
                            middleChar = nullChar
                            middleSubChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            inputConnection.setComposingText(firstChar.toString(), 1)
                        }
                    }
                }
                'ㄺ' -> when (c) {
                    'ᆞ' -> {
                        firstSubChar = nullChar
                        middleSubChar = nullChar
                        finalChar = 'ㄹ'
                        finalSubChar = 'ㅋ'
                        composeResult()
                        inputConnection.setComposingText(
                            composedResult.toString() + finalSubChar.toString(),
                            1
                        )
                    }
                    'ᆢ' -> {
                        firstSubChar = nullChar
                        middleSubChar = nullChar
                        finalChar = 'ㄹ'
                        finalSubChar = 'ㄲ'
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString() + finalSubChar.toString(), 1)
                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        finalChar = 'ㄹ'
                        composeResult()
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 2
                        firstChar = 'ㄱ'
                        firstSubChar = nullChar
                        middleChar = c
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    else -> {
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 1
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㄻ' -> when (c) {
                    'ᆞ' -> {
                        firstSubChar = nullChar
                        middleSubChar = nullChar
                        finalChar = 'ㄼ'
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ᆢ' -> {

                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        finalChar = 'ㄹ'
                        composeResult()
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 2
                        firstChar = 'ㅁ'
                        firstSubChar = nullChar
                        middleChar = c
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    else -> {
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 1
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㄼ' -> when (c) {
                    'ᆞ' -> {
                        firstSubChar = nullChar
                        middleSubChar = nullChar
                        finalChar = 'ㄿ'
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ᆢ' -> {
                        firstSubChar = nullChar
                        middleSubChar = nullChar
                        finalChar = 'ㄹ'
                        finalSubChar = 'ㅃ'
                        composeResult()
                        inputConnection.setComposingText(
                            composedResult.toString() + finalSubChar.toString(),
                            1
                        )
                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        finalChar = 'ㄹ'
                        composeResult()
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 2
                        firstChar = 'ㅂ'
                        firstSubChar = nullChar
                        middleChar = c
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    else -> {
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 1
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㄽ' -> when (c) {
                    'ᆞ' -> {
                        firstSubChar = nullChar
                        middleSubChar = nullChar
                        finalChar = 'ㄹ'
                        finalSubChar = 'ㅈ'
                        composeResult()
                        inputConnection.setComposingText(
                            composedResult.toString() + finalSubChar.toString(),
                            1
                        )
                    }
                    'ᆢ' -> {
                        firstSubChar = nullChar
                        middleSubChar = nullChar
                        finalChar = 'ㄹ'
                        finalSubChar = 'ㅆ'
                        composeResult()
                        inputConnection.setComposingText(
                            composedResult.toString() + finalSubChar.toString(),
                            1
                        )
                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        finalChar = 'ㄹ'
                        composeResult()
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 2
                        firstChar = 'ㅅ'
                        firstSubChar = nullChar
                        middleChar = c
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    else -> {
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 1
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㄿ' -> when (c) {
                    'ᆞ' -> {
                        firstSubChar = nullChar
                        middleSubChar = nullChar
                        finalChar = 'ㄻ'
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ᆢ' -> {

                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        finalChar = 'ㄹ'
                        composeResult()
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 2
                        firstChar = 'ㅍ'
                        firstSubChar = nullChar
                        middleChar = c
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    else -> {
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 1
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㅀ' -> when (c) {
                    'ᆞ' -> {
                        firstSubChar = nullChar
                        middleSubChar = nullChar
                        finalChar = 'ㄹ'
                        finalSubChar = 'ㅇ'
                        composeResult()
                        inputConnection.setComposingText(
                            composedResult.toString() + finalSubChar.toString(),
                            1
                        )
                    }
                    'ᆢ' -> {

                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        finalChar = 'ㄹ'
                        composeResult()
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 2
                        firstChar = 'ㅎ'
                        firstSubChar = nullChar
                        middleChar = c
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    else -> {
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 1
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㅁ' -> when (c) {
                    'ᆞ' -> {
                        firstSubChar = nullChar
                        middleSubChar = nullChar
                        finalChar = 'ㅂ'
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ᆢ' -> {

                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        finalChar = nullChar
                        composeResult()
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 2
                        firstChar = 'ㅁ'
                        firstSubChar = nullChar
                        middleChar = c
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    else -> {
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 1
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㅂ' -> when (c) {
                    'ᆞ' -> when (finalSubChar) {
                        nullChar -> {
                            firstSubChar = nullChar
                            middleSubChar = nullChar
                            finalChar = 'ㅍ'
                            finalSubChar = nullChar
                            composeResult()
                            inputConnection.setComposingText(composedResult.toString(), 1)
                        }
                        'ㅈ' -> {
                            firstSubChar = nullChar
                            middleSubChar = nullChar
                            finalChar = 'ㅂ'
                            finalSubChar = 'ㅊ'
                            composeResult()
                            inputConnection.setComposingText(
                                composedResult.toString() + finalSubChar.toString(),
                                1
                            )
                        }
                        'ㅊ' -> {
                            firstSubChar = nullChar
                            middleSubChar = nullChar
                            finalChar = 'ㅄ'
                            finalSubChar = nullChar
                            composeResult()
                            inputConnection.setComposingText(composedResult.toString(), 1)
                        }
                        else -> {

                        }
                    }
                    'ᆢ' -> when (finalSubChar) {
                        nullChar -> {
                            firstSubChar = nullChar
                            middleSubChar = nullChar
                            finalChar = nullChar
                            finalSubChar = 'ㅃ'
                            composeResult()
                            inputConnection.setComposingText(
                                composedResult.toString() + finalSubChar.toString(),
                                1
                            )
                        }
                        'ㅆ' -> {
                            firstSubChar = nullChar
                            middleSubChar = nullChar
                            finalChar = 'ㅄ'
                            finalSubChar = nullChar
                            composeResult()
                            inputConnection.setComposingText(composedResult.toString(), 1)
                        }
                        'ㅉ' -> {
                            firstSubChar = nullChar
                            middleSubChar = nullChar
                            finalChar = 'ㅂ'
                            finalSubChar = 'ㅈ'
                            composeResult()
                            inputConnection.setComposingText(
                                composedResult.toString() + finalSubChar.toString(),
                                1
                            )
                        }
                        else -> {

                        }
                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> when (finalSubChar) {
                        nullChar -> {
                            finalChar = nullChar
                            composeResult()
                            inputConnection.commitText(composedResult.toString(), 1)
                            state = 2
                            firstChar = 'ㅂ'
                            firstSubChar = nullChar
                            middleChar = c
                            middleSubChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            inputConnection.setComposingText(composedResult.toString(), 1)
                        }
                        else -> {
                            inputConnection.commitText(composedResult.toString(), 1)
                            state = 2
                            firstChar = finalSubChar
                            firstSubChar = nullChar
                            middleChar = c
                            middleSubChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            inputConnection.setComposingText(composedResult.toString(), 1)
                        }
                    }
                    'ㅅ' -> when (finalSubChar) {
                        nullChar -> {
                            firstSubChar = nullChar
                            middleSubChar = nullChar
                            finalChar = 'ㅄ'
                            finalSubChar = nullChar
                            composeResult()
                            inputConnection.setComposingText(composedResult.toString(), 1)
                        }
                        else -> {
                            inputConnection.commitText(
                                composedResult.toString() + finalSubChar.toString(),
                                1
                            )
                            state = 1
                            firstChar = c
                            firstSubChar = nullChar
                            middleChar = nullChar
                            middleSubChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            inputConnection.setComposingText(firstChar.toString(), 1)
                        }
                    }
                    else -> when (finalSubChar) {
                        nullChar -> {
                            inputConnection.commitText(composedResult.toString(), 1)
                            state = 1
                            firstChar = c
                            firstSubChar = nullChar
                            middleChar = nullChar
                            middleSubChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            inputConnection.setComposingText(firstChar.toString(), 1)
                        }
                        else -> {
                            inputConnection.commitText(composedResult.toString() + finalSubChar, 1)
                            state = 1
                            firstChar = c
                            firstSubChar = nullChar
                            middleChar = nullChar
                            middleSubChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            inputConnection.setComposingText(firstChar.toString(), 1)
                        }
                    }
                }
                'ㅄ' -> when (c) {
                    'ᆞ' -> {
                        firstSubChar = nullChar
                        middleSubChar = nullChar
                        finalChar = 'ㅂ'
                        finalSubChar = 'ㅈ'
                        composeResult()
                        inputConnection.setComposingText(
                            composedResult.toString() + finalSubChar.toString(),
                            1
                        )
                    }
                    'ᆢ' -> {
                        firstSubChar = nullChar
                        middleSubChar = nullChar
                        finalChar = 'ㅂ'
                        finalSubChar = 'ㅆ'
                        composeResult()
                        inputConnection.setComposingText(
                            composedResult.toString() + finalSubChar.toString(),
                            1
                        )
                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        finalChar = 'ㅂ'
                        composeResult()
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 2
                        firstChar = 'ㅅ'
                        firstSubChar = nullChar
                        middleChar = c
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    else -> {
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 1
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㅅ' -> when (c) {
                    'ᆞ' -> {
                        firstSubChar = nullChar
                        middleSubChar = nullChar
                        finalChar = 'ㅈ'
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ᆢ' -> {
                        firstSubChar = nullChar
                        middleSubChar = nullChar
                        finalChar = 'ㅆ'
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        finalChar = nullChar
                        composeResult()
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 2
                        firstChar = 'ㅅ'
                        firstSubChar = nullChar
                        middleChar = c
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    else -> {
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 1
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㅆ' -> when (c) {
                    'ᆞ' -> {

                    }
                    'ᆢ' -> {
                        firstSubChar = nullChar
                        middleSubChar = nullChar
                        finalChar = 'ㅅ'
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        finalChar = nullChar
                        composeResult()
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 2
                        firstChar = 'ㅆ'
                        firstSubChar = nullChar
                        middleChar = c
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    else -> {
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 1
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㅇ' -> when (c) {
                    'ᆞ' -> {
                        firstSubChar = nullChar
                        middleSubChar = nullChar
                        finalChar = 'ㅎ'
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ᆢ' -> {

                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        finalChar = nullChar
                        composeResult()
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 2
                        firstChar = 'ㅇ'
                        firstSubChar = nullChar
                        middleChar = c
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    else -> {
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 1
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㅈ' -> when (c) {
                    'ᆞ' -> {
                        firstSubChar = nullChar
                        middleSubChar = nullChar
                        finalChar = 'ㅊ'
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ᆢ' -> {
                        firstSubChar = nullChar
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = 'ㅉ'
                        composeResult()
                        inputConnection.setComposingText(
                            composedResult.toString() + finalSubChar.toString(),
                            1
                        )
                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        finalChar = nullChar
                        composeResult()
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 2
                        firstChar = 'ㅈ'
                        firstSubChar = nullChar
                        middleChar = c
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    else -> {
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 1
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㅊ' -> when (c) {
                    'ᆞ' -> {
                        firstSubChar = nullChar
                        middleSubChar = nullChar
                        finalChar = 'ㅅ'
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ᆢ' -> {

                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        finalChar = nullChar
                        composeResult()
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 2
                        firstChar = 'ㅊ'
                        firstSubChar = nullChar
                        middleChar = c
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    else -> {
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 1
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㅋ' -> when (c) {
                    'ᆞ' -> {
                        firstSubChar = nullChar
                        middleSubChar = nullChar
                        finalChar = 'ㄱ'
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ᆢ' -> {

                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        finalChar = nullChar
                        composeResult()
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 2
                        firstChar = 'ㅋ'
                        firstSubChar = nullChar
                        middleChar = c
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    else -> {
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 1
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㅌ' -> when (c) {
                    'ᆞ' -> {
                        firstSubChar = nullChar
                        middleSubChar = nullChar
                        finalChar = 'ㄴ'
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ᆢ' -> {

                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        finalChar = nullChar
                        composeResult()
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 2
                        firstChar = 'ㅌ'
                        firstSubChar = nullChar
                        middleChar = c
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    else -> {
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 1
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㅍ' -> when (c) {
                    'ᆞ' -> {
                        firstSubChar = nullChar
                        middleSubChar = nullChar
                        finalChar = 'ㅁ'
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ᆢ' -> {

                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        finalChar = nullChar
                        composeResult()
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 2
                        firstChar = 'ㅍ'
                        firstSubChar = nullChar
                        middleChar = c
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    else -> {
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 1
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㅎ' -> when (c) {
                    'ᆞ' -> {
                        firstSubChar = nullChar
                        middleSubChar = nullChar
                        finalChar = 'ㅇ'
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ᆢ' -> {

                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        finalChar = nullChar
                        composeResult()
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 2
                        firstChar = 'ㅎ'
                        firstSubChar = nullChar
                        middleChar = c
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    else -> {
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 1
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                nullChar -> when (c) {
                    'ᆞ' -> {

                    }
                    'ᆢ' -> when (finalSubChar) {
                        'ㄸ' -> {
                            firstSubChar = nullChar
                            middleSubChar = nullChar
                            finalChar = 'ㄷ'
                            finalSubChar = nullChar
                            composeResult()
                            inputConnection.setComposingText(composedResult.toString(), 1)
                        }
                        'ㅃ' -> {
                            firstSubChar = nullChar
                            middleSubChar = nullChar
                            finalChar = 'ㅂ'
                            finalSubChar = nullChar
                            composeResult()
                            inputConnection.setComposingText(composedResult.toString(), 1)
                        }
                        'ㅉ' -> {
                            firstSubChar = nullChar
                            middleSubChar = nullChar
                            finalChar = 'ㅈ'
                            finalSubChar = nullChar
                            composeResult()
                            inputConnection.setComposingText(composedResult.toString(), 1)
                        }
                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 2
                        firstChar = finalSubChar
                        firstSubChar = nullChar
                        middleChar = c
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    else -> {
                        inputConnection.commitText(composedResult.toString() + finalSubChar.toString(), 1)
                        state = 1
                        firstChar = c
                        firstSubChar = nullChar
                        middleChar = nullChar
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
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
                'ㅏ', 'ㅑ', 'ㅓ', 'ㅕ', 'ㅗ', 'ㅛ', 'ㅠ', 'ㅡ', 'ㅣ' -> {
                    state = 0
                    middleChar = nullChar
                    middleSubChar = nullChar
                    composeResult()
                    composedResult = nullChar
                    inputConnection.setComposingText("", 1)
                }
                'ㅐ' -> {
                    middleChar = 'ㅏ'
                    middleSubChar = nullChar
                    composeResult()
                    composedResult = nullChar
                    inputConnection.setComposingText(middleChar.toString(), 1)
                }
                'ㅒ' -> {
                    middleChar = 'ㅑ'
                    middleSubChar = nullChar
                    composeResult()
                    composedResult = nullChar
                    inputConnection.setComposingText(middleChar.toString(), 1)
                }
                'ㅔ' -> {
                    middleChar = 'ㅓ'
                    middleSubChar = nullChar
                    composeResult()
                    composedResult = nullChar
                    inputConnection.setComposingText(middleChar.toString(), 1)
                }
                'ㅖ' -> {
                    middleChar = 'ㅕ'
                    middleSubChar = nullChar
                    composeResult()
                    composedResult = nullChar
                    inputConnection.setComposingText(middleChar.toString(), 1)
                }
                'ㅘ', 'ㅚ' -> {
                    middleChar = 'ㅗ'
                    middleSubChar = nullChar
                    composeResult()
                    composedResult = nullChar
                    inputConnection.setComposingText(middleChar.toString(), 1)
                }
                'ㅙ' -> {
                    middleChar = 'ㅘ'
                    middleSubChar = nullChar
                    composeResult()
                    composedResult = nullChar
                    inputConnection.setComposingText(middleChar.toString(), 1)
                }
                'ㅜ' -> when ( middleSubChar ) {
                    nullChar -> {
                        state = 0
                        middleChar = nullChar
                        middleSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText("", 1)
                    }
                    else -> {
                        middleChar = 'ㅜ'
                        middleSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                }
                'ㅝ', 'ㅟ' -> {
                    middleChar = 'ㅜ'
                    middleSubChar = nullChar
                    composeResult()
                    composedResult = nullChar
                    inputConnection.setComposingText(middleChar.toString(), 1)
                }
                'ㅞ' -> {
                    middleChar = 'ㅝ'
                    middleSubChar = nullChar
                    composeResult()
                    composedResult = nullChar
                    inputConnection.setComposingText(middleChar.toString(), 1)
                }
                'ㅢ' -> {
                    middleChar = 'ㅡ'
                    middleSubChar = nullChar
                    composeResult()
                    composedResult = nullChar
                    inputConnection.setComposingText(middleChar.toString(), 1)
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
                'ㄺ', 'ㄻ', 'ㄼ', 'ㄽ', 'ㄿ', 'ㅀ' -> {
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
                'ㅏ', 'ㅑ', 'ㅓ', 'ㅕ', 'ㅗ', 'ㅛ', 'ㅠ', 'ㅡ', 'ㅣ' -> {
                    state = 1
                    firstSubChar = nullChar
                    middleChar = nullChar
                    middleSubChar = nullChar
                    finalChar = nullChar
                    finalSubChar = nullChar
                    composeResult()
                    composedResult = nullChar
                    inputConnection.setComposingText(firstChar.toString(), 1)
                }
                'ㅐ' -> {
                    middleChar = 'ㅏ'
                    middleSubChar = nullChar
                    finalChar = nullChar
                    finalSubChar = nullChar
                    composeResult()
                    inputConnection.setComposingText(composedResult.toString(), 1)
                }
                'ㅒ' -> {
                    middleChar = 'ㅑ'
                    middleSubChar = nullChar
                    finalChar = nullChar
                    finalSubChar = nullChar
                    composeResult()
                    inputConnection.setComposingText(composedResult.toString(), 1)
                }
                'ㅔ' -> {
                    middleChar = 'ㅓ'
                    middleSubChar = nullChar
                    finalChar = nullChar
                    finalSubChar = nullChar
                    composeResult()
                    inputConnection.setComposingText(composedResult.toString(), 1)
                }
                'ㅖ' -> {
                    middleChar = 'ㅕ'
                    middleSubChar = nullChar
                    finalChar = nullChar
                    finalSubChar = nullChar
                    composeResult()
                    inputConnection.setComposingText(composedResult.toString(), 1)
                }
                'ㅘ', 'ㅚ' -> {
                    middleChar = 'ㅗ'
                    middleSubChar = nullChar
                    finalChar = nullChar
                    finalSubChar = nullChar
                    composeResult()
                    inputConnection.setComposingText(composedResult.toString(), 1)
                }
                'ㅙ' -> {
                    middleChar = 'ㅘ'
                    middleSubChar = nullChar
                    finalChar = nullChar
                    finalSubChar = nullChar
                    composeResult()
                    inputConnection.setComposingText(composedResult.toString(), 1)
                }
                'ㅜ' -> when ( middleSubChar ) {
                    nullChar -> {
                        state = 1
                        firstSubChar = nullChar
                        middleChar = nullChar
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                    else -> {
                        middleSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                }
                'ㅝ', 'ㅟ' -> {
                    middleChar = 'ㅜ'
                    middleSubChar = nullChar
                    finalChar = nullChar
                    finalSubChar = nullChar
                    composeResult()
                    inputConnection.setComposingText(composedResult.toString(), 1)
                }
                'ㅞ' -> {
                    middleChar = 'ㅝ'
                    middleSubChar = nullChar
                    finalChar = nullChar
                    finalSubChar = nullChar
                    composeResult()
                    inputConnection.setComposingText(composedResult.toString(), 1)
                }
                'ㅢ' -> {
                    middleChar = 'ㅡ'
                    middleSubChar = nullChar
                    finalChar = nullChar
                    finalSubChar = nullChar
                    composeResult()
                    inputConnection.setComposingText(composedResult.toString(), 1)
                }
            }
            3 -> {
                when (finalChar) {
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
        }
    } //delete 발생

    fun initChar() {
        firstChar = nullChar
        firstSubChar = nullChar
        middleChar = nullChar
        middleSubChar = nullChar
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