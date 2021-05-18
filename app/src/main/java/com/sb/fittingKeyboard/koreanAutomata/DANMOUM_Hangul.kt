package com.sb.fittingKeyboard.koreanAutomata

import android.util.Log
import android.view.inputmethod.InputConnection

class DANMOUM_Hangul : AutomataInterface {
    private val baseInt: Int = 0xAC00
    private val nullChar: Char = '\u0000'

    private var firstChar: Char = nullChar
    private var middleChar: Char = nullChar
    private var finalChar: Char = nullChar

    private val firstCharArray: Array<Int> = arrayOf(0x3131, 0x3132, 0x3134, 0x3137, 0x3138, 0x3139, 0x3141, 0x3142, 0x3143, 0x3145, 0x3146, 0x3147, 0x3148, 0x3149, 0x314a, 0x314b, 0x314c, 0x314d, 0x314e)
    private val middleCharArray: Array<Int> = arrayOf(0x314f, 0x3150, 0x3151, 0x3152, 0x3153, 0x3154, 0x3155, 0x3156, 0x3157, 0x3158, 0x3159, 0x315a, 0x315b, 0x315c, 0x315d, 0x315e, 0x315f, 0x3160, 0x3161, 0x3162, 0x3163)
    private val finalCharArray: Array<Int> = arrayOf(0x0000, 0x3131, 0x3132, 0x3133, 0x3134, 0x3135, 0x3136, 0x3137, 0x3139, 0x313a, 0x313b, 0x313c, 0x313d, 0x313e, 0x313f, 0x3140, 0x3141, 0x3142, 0x3144, 0x3145, 0x3146, 0x3147, 0x3148, 0x314a, 0x314b, 0x314c, 0x314d, 0x314e)
    private var state: Int = 0
    //</editor-fold>

    private var firstCharIndex = firstCharArray.indexOf(firstChar.toInt())
    private var middleCharIndex = middleCharArray.indexOf(middleChar.toInt())
    private var finalCharIndex = finalCharArray.indexOf(finalChar.toInt())
    private var composedResult: Char = '\u0000'

    override fun composeResult() {
        firstCharIndex = firstCharArray.indexOf(firstChar.toInt())
        middleCharIndex = middleCharArray.indexOf(middleChar.toInt())
        finalCharIndex = finalCharArray.indexOf(finalChar.toInt())
        composedResult = (baseInt + firstCharIndex * 21 * 28 + middleCharIndex * 28 + finalCharIndex).toChar()
    }

    fun composeChar(c: Char, inputConnection: InputConnection, inputTime: Long) { // 한글완성 오토마타
        val isInputCharFirstChar = firstCharArray.indexOf(c.toInt()) >= 0
        val isInputCharMiddleChar = middleCharArray.indexOf(c.toInt()) >= 0

        when (state) {
            0 -> { //아무것도 없는 상태. 자음 혹은 모음이 들어올 수 있다
                when {
                    isInputCharFirstChar -> { // 자음이 들어온다면
                        firstChar = c
                        composedResult = nullChar
                        state = 1
                        inputConnection.setComposingText(firstChar.toString(), 1)
                        setFirstInputTimer(inputTime = inputTime)
                    }
                    isInputCharMiddleChar -> { // 모음이 들어온다면
                        state = -1
                        middleChar = c
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                        setFirstInputTimer(inputTime = inputTime)
                    }
                    else -> {
                        initChar()
                        composedResult = nullChar
                        inputConnection.commitText(c.toString(), 1)
                    }
                }
            }
            -1 -> { // 모음이 들어온 상태
                when {
                    isInputCharFirstChar -> { // 자음 입력 시
                        inputConnection.commitText(middleChar.toString(), 1)
                        state = 1
                        firstChar = c
                        middleChar = nullChar
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                        setFirstInputTimer(inputTime = inputTime)
                    }
                    isInputCharMiddleChar -> { // 모음 입력 시 = 경우의 수를 따져야 함
                        setSecondInputTimer(inputTime = inputTime)
                        when (c) {
                            'ㅏ' -> {
                                when (middleChar) {
                                    'ㅏ' -> {
                                        middleChar =
                                            if (secondInputTime - firstInputTime >= separationTime1) {
                                                inputConnection.commitText(middleChar.toString(), 1)
                                                c
                                            } else {
                                                'ㅑ'
                                            }
                                        composedResult = nullChar
                                        inputConnection.setComposingText(middleChar.toString(), 1)
                                        setFirstInputTimer(inputTime = inputTime)
                                    }
                                    'ㅗ' -> {
                                        middleChar = 'ㅘ'
                                        composedResult = nullChar
                                        inputConnection.setComposingText(middleChar.toString(), 1)
                                    }
                                    else -> {
                                        inputConnection.commitText(middleChar.toString(), 1)
                                        middleChar = c
                                        composedResult = nullChar
                                        inputConnection.setComposingText(middleChar.toString(), 1)
                                        setFirstInputTimer(inputTime = inputTime)
                                    }
                                }
                            }
                            'ㅐ' -> {
                                when (middleChar) {
                                    'ㅐ' -> {
                                        middleChar =
                                            if (secondInputTime - firstInputTime >= separationTime1) {
                                                inputConnection.commitText(middleChar.toString(), 1)
                                                c
                                            } else {
                                                'ㅒ'
                                            }
                                        composedResult = nullChar
                                        inputConnection.setComposingText(middleChar.toString(), 1)
                                        setFirstInputTimer(inputTime = inputTime)
                                    }
                                    'ㅗ' -> {
                                        middleChar = 'ㅙ'
                                        composedResult = nullChar
                                        inputConnection.setComposingText(middleChar.toString(), 1)
                                    }
                                    else -> {
                                        inputConnection.commitText(middleChar.toString(), 1)
                                        middleChar = c
                                        composedResult = nullChar
                                        inputConnection.setComposingText(middleChar.toString(), 1)
                                        setFirstInputTimer(inputTime = inputTime)
                                    }
                                }
                            }
                            'ㅓ' -> {
                                when (middleChar) {
                                    'ㅓ' -> {
                                        middleChar =
                                            if (secondInputTime - firstInputTime >= separationTime1) {
                                                inputConnection.commitText(middleChar.toString(), 1)
                                                c
                                            } else {
                                                'ㅕ'
                                            }
                                        composedResult = nullChar
                                        inputConnection.setComposingText(middleChar.toString(), 1)
                                        setFirstInputTimer(inputTime = inputTime)
                                    }
                                    'ㅜ' -> {
                                        middleChar = 'ㅝ'
                                        composedResult = nullChar
                                        inputConnection.setComposingText(middleChar.toString(), 1)
                                    }
                                    else -> {
                                        inputConnection.commitText(middleChar.toString(), 1)
                                        middleChar = c
                                        composedResult = nullChar
                                        inputConnection.setComposingText(middleChar.toString(), 1)
                                        setFirstInputTimer(inputTime = inputTime)
                                    }
                                }
                            }
                            'ㅔ' -> {
                                when (middleChar) {
                                    'ㅔ' -> {
                                        middleChar =
                                            if (secondInputTime - firstInputTime >= separationTime1) {
                                                inputConnection.commitText(middleChar.toString(), 1)
                                                c
                                            } else {
                                                'ㅖ'
                                            }
                                        composedResult = nullChar
                                        inputConnection.setComposingText(middleChar.toString(), 1)
                                        setFirstInputTimer(inputTime = inputTime)
                                    }
                                    'ㅜ' -> {
                                        middleChar = 'ㅞ'
                                        composedResult = nullChar
                                        inputConnection.setComposingText(middleChar.toString(), 1)
                                    }
                                    else -> {
                                        inputConnection.commitText(middleChar.toString(), 1)
                                        middleChar = c
                                        composedResult = nullChar
                                        inputConnection.setComposingText(middleChar.toString(), 1)
                                        setFirstInputTimer(inputTime = inputTime)
                                    }
                                }
                            }
                            'ㅗ' -> {
                                when (middleChar) {
                                    'ㅗ' -> {
                                        middleChar =
                                            if (secondInputTime - firstInputTime >= separationTime1) {
                                                inputConnection.commitText(middleChar.toString(), 1)
                                                c
                                            } else {
                                                'ㅛ'
                                            }
                                        composedResult = nullChar
                                        inputConnection.setComposingText(middleChar.toString(), 1)
                                        setFirstInputTimer(inputTime = inputTime)
                                    }
                                    else -> {
                                        inputConnection.commitText(middleChar.toString(), 1)
                                        middleChar = c
                                        composedResult = nullChar
                                        inputConnection.setComposingText(middleChar.toString(), 1)
                                        setFirstInputTimer(inputTime = inputTime)
                                    }
                                }
                            }
                            'ㅜ' -> {
                                when (middleChar) {
                                    'ㅜ' -> {
                                        middleChar =
                                            if (secondInputTime - firstInputTime >= separationTime1) {
                                                inputConnection.commitText(middleChar.toString(), 1)
                                                c
                                            } else {
                                                'ㅠ'
                                            }
                                        composedResult = nullChar
                                        inputConnection.setComposingText(middleChar.toString(), 1)
                                        setFirstInputTimer(inputTime = inputTime)
                                    }
                                    else -> {
                                        inputConnection.commitText(middleChar.toString(), 1)
                                        middleChar = c
                                        composedResult = nullChar
                                        inputConnection.setComposingText(middleChar.toString(), 1)
                                        setFirstInputTimer(inputTime = inputTime)
                                    }
                                }
                            }
                            'ㅡ' -> {
                                inputConnection.commitText(middleChar.toString(), 1)
                                middleChar = c
                                composedResult = nullChar
                                inputConnection.setComposingText(middleChar.toString(), 1)
                                setFirstInputTimer(inputTime = inputTime)
                            }
                            'ㅣ' -> {
                                when (middleChar) {
                                    'ㅗ' -> {
                                        middleChar = 'ㅚ'
                                        composedResult = nullChar
                                        inputConnection.setComposingText(middleChar.toString(), 1)
                                    }
                                    'ㅜ' -> {
                                        middleChar = 'ㅟ'
                                        composedResult = nullChar
                                        inputConnection.setComposingText(middleChar.toString(), 1)
                                    }
                                    'ㅡ' -> {
                                        middleChar = 'ㅢ'
                                        composedResult = nullChar
                                        inputConnection.setComposingText(middleChar.toString(), 1)
                                    }
                                    else -> {
                                        inputConnection.commitText(middleChar.toString(), 1)
                                        middleChar = c
                                        composedResult = nullChar
                                        inputConnection.setComposingText(middleChar.toString(), 1)
                                        setFirstInputTimer(inputTime = inputTime)
                                    }
                                }
                            }
                        }
                    }
                    else -> {
                        inputConnection.finishComposingText()
                        state = 0
                        initChar()
                        composedResult = nullChar
                        inputConnection.commitText(c.toString(), 1)
                    }
                }
            }
            1 -> { //자음이 들어온 상태(초성)
                when {
                    isInputCharFirstChar -> { //자음이 들어온다면 ex) ㄱㄱ-> ㄲ, ㄹㄱ -> ㄺ, ㄱㄴ
                        setSecondInputTimer(inputTime = inputTime)
                        when (c) {
                            'ㄱ' -> {
                                when (firstChar) {
                                    'ㄱ' -> {
                                        firstChar =
                                            if (secondInputTime - firstInputTime >= separationTime1) {
                                                inputConnection.commitText(firstChar.toString(), 1)
                                                c
                                            } else {
                                                'ㄲ'
                                            }
                                    }
                                    'ㄹ' -> {
                                        firstChar =
                                            if (secondInputTime - firstInputTime >= separationTime1) {
                                                inputConnection.commitText(firstChar.toString(), 1)
                                                c
                                            } else {
                                                'ㄺ'
                                            }
                                    }
                                    'ㄺ' -> {
                                        firstChar =
                                            if (secondInputTime - firstInputTime >= separationTime1) {
                                                inputConnection.commitText(firstChar.toString(), 1)
                                                c
                                            } else {
                                                inputConnection.commitText('ㄹ'.toString(), 1)
                                                'ㄲ'
                                            }
                                    }
                                    else -> {
                                        inputConnection.commitText(firstChar.toString(), 1)
                                        firstChar = c
                                    }
                                }
                            }
                            'ㄷ' -> {
                                firstChar = when (firstChar) {
                                    'ㄷ' -> {
                                        if (secondInputTime - firstInputTime >= separationTime1) {
                                            inputConnection.commitText(firstChar.toString(), 1)
                                            c
                                        } else {
                                            'ㄸ'
                                        }
                                    }
                                    else -> {
                                        inputConnection.commitText(firstChar.toString(), 1)
                                        c
                                    }
                                }
                            }
                            'ㅁ' -> {
                                firstChar = when (firstChar) {
                                    'ㄹ' -> {
                                        if (secondInputTime - firstInputTime >= separationTime1) {
                                            inputConnection.commitText(firstChar.toString(), 1)
                                            c
                                        } else {
                                            'ㄻ'
                                        }
                                    }
                                    else -> {
                                        inputConnection.commitText(firstChar.toString(), 1)
                                        c
                                    }
                                }
                            }
                            'ㅂ' -> {
                                when (firstChar) {
                                    'ㄹ' -> {
                                        firstChar =
                                            if (secondInputTime - firstInputTime >= separationTime1) {
                                                inputConnection.commitText(firstChar.toString(), 1)
                                                c
                                            } else {
                                                'ㄼ'
                                            }
                                    }
                                    'ㅂ' -> {
                                        firstChar =
                                            if (secondInputTime - firstInputTime >= separationTime1) {
                                                inputConnection.commitText(firstChar.toString(), 1)
                                                c
                                            } else {
                                                'ㅃ'
                                            }
                                    }
                                    'ㄼ' -> {
                                        firstChar =
                                            if (secondInputTime - firstInputTime >= separationTime1) {
                                                inputConnection.commitText(firstChar.toString(), 1)
                                                c
                                            } else {
                                                inputConnection.commitText('ㄹ'.toString(), 1)
                                                'ㅃ'
                                            }
                                    }
                                    else -> {
                                        inputConnection.commitText(firstChar.toString(), 1)
                                        firstChar = c
                                    }
                                }
                            }
                            'ㅅ' -> {
                                when (firstChar) {
                                    'ㄱ' -> {
                                        firstChar =
                                            if (secondInputTime - firstInputTime >= separationTime1) {
                                                inputConnection.commitText(firstChar.toString(), 1)
                                                c
                                            } else {
                                                'ㄳ'
                                            }
                                    }
                                    'ㄹ' -> {
                                        firstChar =
                                            if (secondInputTime - firstInputTime >= separationTime1) {
                                                inputConnection.commitText(firstChar.toString(), 1)
                                                c
                                            } else {
                                                'ㄽ'
                                            }
                                    }
                                    'ㄽ' -> {
                                        firstChar =
                                            if (secondInputTime - firstInputTime >= separationTime1) {
                                                inputConnection.commitText(firstChar.toString(), 1)
                                                c
                                            } else {
                                                inputConnection.commitText('ㄹ'.toString(), 1)
                                                'ㅆ'
                                            }
                                    }
                                    'ㅂ' -> {
                                        firstChar =
                                            if (secondInputTime - firstInputTime >= separationTime1) {
                                                inputConnection.commitText(firstChar.toString(), 1)
                                                c
                                            } else {
                                                'ㅄ'
                                            }
                                    }
                                    'ㅄ' -> {
                                        firstChar =
                                            if (secondInputTime - firstInputTime >= separationTime1) {
                                                inputConnection.commitText(firstChar.toString(), 1)
                                                c
                                            } else {
                                                inputConnection.commitText('ㅂ'.toString(), 1)
                                                'ㅆ'
                                            }
                                    }
                                    'ㅅ' -> {
                                        firstChar =
                                            if (secondInputTime - firstInputTime >= separationTime1) {
                                                inputConnection.commitText(firstChar.toString(), 1)
                                                c
                                            } else {
                                                'ㅆ'
                                            }
                                    }
                                    else -> {
                                        inputConnection.commitText(firstChar.toString(), 1)
                                        firstChar = c
                                    }
                                }
                            }
                            'ㅈ' -> {
                                when (firstChar) {
                                    'ㄴ' -> {
                                        firstChar =
                                            if (secondInputTime - firstInputTime >= separationTime1) {
                                                inputConnection.commitText(firstChar.toString(), 1)
                                                c
                                            } else {
                                                'ㄵ'
                                            }
                                    }
                                    'ㄵ' -> {
                                        firstChar =
                                            if (secondInputTime - firstInputTime >= separationTime1) {
                                                inputConnection.commitText(firstChar.toString(), 1)
                                                c
                                            } else {
                                                inputConnection.commitText('ㄴ'.toString(), 1)
                                                'ㅉ'
                                            }
                                    }
                                    'ㅈ' -> {
                                        firstChar =
                                            if (secondInputTime - firstInputTime >= separationTime1) {
                                                inputConnection.commitText(firstChar.toString(), 1)
                                                c
                                            } else {
                                                'ㅉ'
                                            }
                                    }
                                    else -> {
                                        inputConnection.commitText(firstChar.toString(), 1)
                                        firstChar = c
                                    }
                                }
                            }
                            'ㅌ' -> {
                                firstChar = when (firstChar) {
                                    'ㄹ' -> {
                                        if (secondInputTime - firstInputTime >= separationTime1) {
                                            inputConnection.commitText(firstChar.toString(), 1)
                                            c
                                        } else {
                                            'ㄾ'
                                        }
                                    }
                                    else -> {
                                        inputConnection.commitText(firstChar.toString(), 1)
                                        c
                                    }
                                }
                            }
                            'ㅍ' -> {
                                firstChar = when (firstChar) {
                                    'ㄹ' -> {
                                        if (secondInputTime - firstInputTime >= separationTime1) {
                                            inputConnection.commitText(firstChar.toString(), 1)
                                            c
                                        } else {
                                            'ㄿ'
                                        }
                                    }
                                    else -> {
                                        inputConnection.commitText(firstChar.toString(), 1)
                                        c
                                    }
                                }
                            }
                            'ㅎ' -> {
                                when (firstChar) {
                                    'ㄴ' -> {
                                        firstChar =
                                            if (secondInputTime - firstInputTime >= separationTime1) {
                                                inputConnection.commitText(firstChar.toString(), 1)
                                                c
                                            } else {
                                                'ㄶ'
                                            }
                                    }
                                    'ㄹ' -> {
                                        firstChar =
                                            if (secondInputTime - firstInputTime >= separationTime1) {
                                                inputConnection.commitText(firstChar.toString(), 1)
                                                c
                                            } else {
                                                'ㅀ'
                                            }
                                    }
                                    else -> {
                                        inputConnection.commitText(firstChar.toString(), 1)
                                        firstChar = c
                                    }
                                }
                            }
                            else -> { // ㄴ ㄹ ㅇ ㅊ ㅋ
                                inputConnection.commitText(firstChar.toString(), 1)
                                firstChar = c
                            }
                        }
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                        setFirstInputTimer(inputTime = inputTime)
                    }
                    isInputCharMiddleChar -> { //모음이 들어온다면 ex) ㄱ+ㅏ -> 가
                        when (firstChar) {
                            'ㄳ' -> {
                                inputConnection.commitText("ㄱ", 1)
                                firstChar = 'ㅅ'
                            }
                            'ㄵ' -> {
                                inputConnection.commitText("ㄴ", 1)
                                firstChar = 'ㅈ'
                            }
                            'ㄶ' -> {
                                inputConnection.commitText("ㄴ", 1)
                                firstChar = 'ㅎ'
                            }
                            'ㄺ' -> {
                                inputConnection.commitText("ㄹ", 1)
                                firstChar = 'ㄱ'
                            }
                            'ㄻ' -> {
                                inputConnection.commitText("ㄹ", 1)
                                firstChar = 'ㅁ'
                            }
                            'ㄼ' -> {
                                inputConnection.commitText("ㄹ", 1)
                                firstChar = 'ㅂ'
                            }
                            'ㄽ' -> {
                                inputConnection.commitText("ㄹ", 1)
                                firstChar = 'ㅅ'
                            }
                            'ㄾ' -> {
                                inputConnection.commitText("ㄹ", 1)
                                firstChar = 'ㅌ'
                            }
                            'ㄿ' -> {
                                inputConnection.commitText("ㄹ", 1)
                                firstChar = 'ㅍ'
                            }
                            'ㅀ' -> {
                                inputConnection.commitText("ㄹ", 1)
                                firstChar = 'ㅎ'
                            }
                            'ㅄ' -> {
                                inputConnection.commitText("ㅂ", 1)
                                firstChar = 'ㅅ'
                            }
                        }
                        middleChar = c
                        state = 2
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                        setFirstInputTimer(inputTime = inputTime)
                    }
                    else -> { // 한글자모를 입력하지 않는다면
                        inputConnection.finishComposingText()
                        state = 0
                        initChar()
                        composedResult = nullChar
                        inputConnection.commitText(c.toString(), 1)
                    }
                }
            }
            2 -> { //자음+모음이 들어온 상태(중성)
                when {
                    isInputCharFirstChar -> { // 자음(종성)이 들어온다면
                        finalChar = c
                        state = 3
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                        setFirstInputTimer(inputTime = inputTime)
                    }
                    isInputCharMiddleChar -> { // 모음 입력 시 = 경우의 수를 따져야 함
                        setSecondInputTimer(inputTime = inputTime)
                        when (c) {
                            'ㅏ' -> {
                                when (middleChar) {
                                    'ㅏ' -> {
                                        if (secondInputTime - firstInputTime >= separationTime1) {
                                            inputConnection.commitText(composedResult.toString(), 1)
                                            state = -1
                                            firstChar = nullChar
                                            middleChar = c
                                            composeResult()
                                            composedResult = nullChar
                                            inputConnection.setComposingText(
                                                middleChar.toString(),
                                                1
                                            )
                                            setFirstInputTimer(inputTime = inputTime)
                                        } else {
                                            middleChar = 'ㅑ'
                                            composeResult()
                                            inputConnection.setComposingText(
                                                composedResult.toString(),
                                                1
                                            )
                                        }
                                    }
                                    'ㅗ' -> {
                                        middleChar = 'ㅘ'
                                        composeResult()
                                        inputConnection.setComposingText(
                                            composedResult.toString(),
                                            1
                                        )
                                    }
                                    else -> {
                                        inputConnection.commitText(composedResult.toString(), 1)
                                        state = -1
                                        firstChar = nullChar
                                        middleChar = c
                                        composeResult()
                                        composedResult = nullChar
                                        inputConnection.setComposingText(middleChar.toString(), 1)
                                        setFirstInputTimer(inputTime = inputTime)
                                    }
                                }
                            }
                            'ㅐ' -> {
                                when (middleChar) {
                                    'ㅏ' -> {
                                        if (secondInputTime - firstInputTime >= separationTime1) {
                                            inputConnection.commitText(composedResult.toString(), 1)
                                            state = -1
                                            firstChar = nullChar
                                            middleChar = c
                                            composeResult()
                                            composedResult = nullChar
                                            inputConnection.setComposingText(
                                                middleChar.toString(),
                                                1
                                            )
                                            setFirstInputTimer(inputTime = inputTime)
                                        } else {
                                            middleChar = 'ㅒ'
                                            composeResult()
                                            inputConnection.setComposingText(
                                                composedResult.toString(),
                                                1
                                            )
                                        }
                                    }
                                    'ㅗ' -> {
                                        middleChar = 'ㅙ'
                                        composeResult()
                                        inputConnection.setComposingText(
                                            composedResult.toString(),
                                            1
                                        )
                                    }
                                    else -> {
                                        inputConnection.commitText(composedResult.toString(), 1)
                                        state = -1
                                        firstChar = nullChar
                                        middleChar = c
                                        composeResult()
                                        composedResult = nullChar
                                        inputConnection.setComposingText(middleChar.toString(), 1)
                                        setFirstInputTimer(inputTime = inputTime)
                                    }
                                }
                            }
                            'ㅓ' -> {
                                when (middleChar) {
                                    'ㅓ' -> {
                                        if (secondInputTime - firstInputTime >= separationTime1) {
                                            inputConnection.commitText(composedResult.toString(), 1)
                                            state = -1
                                            firstChar = nullChar
                                            middleChar = c
                                            composeResult()
                                            composedResult = nullChar
                                            inputConnection.setComposingText(
                                                middleChar.toString(),
                                                1
                                            )
                                            setFirstInputTimer(inputTime = inputTime)
                                        } else {
                                            middleChar = 'ㅕ'
                                            composeResult()
                                            inputConnection.setComposingText(
                                                composedResult.toString(),
                                                1
                                            )
                                        }
                                    }
                                    'ㅜ' -> {
                                        middleChar = 'ㅝ'
                                        composeResult()
                                        inputConnection.setComposingText(
                                            composedResult.toString(),
                                            1
                                        )
                                    }
                                    else -> {
                                        inputConnection.commitText(composedResult.toString(), 1)
                                        state = -1
                                        firstChar = nullChar
                                        middleChar = c
                                        composeResult()
                                        composedResult = nullChar
                                        inputConnection.setComposingText(middleChar.toString(), 1)
                                        setFirstInputTimer(inputTime = inputTime)
                                    }
                                }
                            }
                            'ㅔ' -> {
                                when (middleChar) {
                                    'ㅔ' -> {
                                        if (secondInputTime - firstInputTime >= separationTime1) {
                                            inputConnection.commitText(composedResult.toString(), 1)
                                            state = -1
                                            firstChar = nullChar
                                            middleChar = c
                                            composeResult()
                                            composedResult = nullChar
                                            inputConnection.setComposingText(
                                                middleChar.toString(),
                                                1
                                            )
                                            setFirstInputTimer(inputTime = inputTime)
                                        } else {
                                            middleChar = 'ㅖ'
                                            composeResult()
                                            inputConnection.setComposingText(
                                                composedResult.toString(),
                                                1
                                            )
                                        }
                                    }
                                    'ㅜ' -> {
                                        middleChar = 'ㅞ'
                                        composeResult()
                                        inputConnection.setComposingText(
                                            composedResult.toString(),
                                            1
                                        )
                                    }
                                    else -> {
                                        inputConnection.commitText(composedResult.toString(), 1)
                                        state = -1
                                        firstChar = nullChar
                                        middleChar = c
                                        composeResult()
                                        composedResult = nullChar
                                        inputConnection.setComposingText(middleChar.toString(), 1)
                                        setFirstInputTimer(inputTime = inputTime)
                                    }
                                }
                            }
                            'ㅗ' -> {
                                when (middleChar) {
                                    'ㅗ' -> {
                                        if (secondInputTime - firstInputTime >= separationTime1) {
                                            inputConnection.commitText(composedResult.toString(), 1)
                                            state = -1
                                            firstChar = nullChar
                                            middleChar = c
                                            composeResult()
                                            composedResult = nullChar
                                            inputConnection.setComposingText(
                                                middleChar.toString(),
                                                1
                                            )
                                            setFirstInputTimer(inputTime = inputTime)
                                        } else {
                                            middleChar = 'ㅛ'
                                            composeResult()
                                            inputConnection.setComposingText(
                                                composedResult.toString(),
                                                1
                                            )
                                        }
                                    }
                                    else -> {
                                        inputConnection.commitText(composedResult.toString(), 1)
                                        state = -1
                                        firstChar = nullChar
                                        middleChar = c
                                        composeResult()
                                        composedResult = nullChar
                                        inputConnection.setComposingText(middleChar.toString(), 1)
                                        setFirstInputTimer(inputTime = inputTime)
                                    }
                                }
                            }
                            'ㅜ' -> {
                                when (middleChar) {
                                    'ㅜ' -> {
                                        if (secondInputTime - firstInputTime >= separationTime1) {
                                            inputConnection.commitText(composedResult.toString(), 1)
                                            state = -1
                                            firstChar = nullChar
                                            middleChar = c
                                            composeResult()
                                            composedResult = nullChar
                                            inputConnection.setComposingText(
                                                middleChar.toString(),
                                                1
                                            )
                                            setFirstInputTimer(inputTime = inputTime)
                                        } else {
                                            middleChar = 'ㅠ'
                                            composeResult()
                                            inputConnection.setComposingText(
                                                composedResult.toString(),
                                                1
                                            )
                                        }
                                    }
                                    else -> {
                                        inputConnection.commitText(composedResult.toString(), 1)
                                        state = -1
                                        firstChar = nullChar
                                        middleChar = c
                                        composeResult()
                                        composedResult = nullChar
                                        inputConnection.setComposingText(middleChar.toString(), 1)
                                        setFirstInputTimer(inputTime = inputTime)
                                    }
                                }
                            }
                            'ㅡ' -> {
                                inputConnection.commitText(composedResult.toString(), 1)
                                state = -1
                                firstChar = nullChar
                                middleChar = c
                                composeResult()
                                composedResult = nullChar
                                inputConnection.setComposingText(middleChar.toString(), 1)
                                setFirstInputTimer(inputTime = inputTime)
                            }
                            'ㅣ' -> {
                                when (middleChar) {
                                    'ㅗ' -> {
                                        if (secondInputTime - firstInputTime >= separationTime1) {
                                            inputConnection.commitText(composedResult.toString(), 1)
                                            state = -1
                                            firstChar = nullChar
                                            middleChar = c
                                            composeResult()
                                            composedResult = nullChar
                                            inputConnection.setComposingText(
                                                middleChar.toString(),
                                                1
                                            )
                                            setFirstInputTimer(inputTime = inputTime)
                                        } else {
                                            middleChar = 'ㅚ'
                                            composeResult()
                                            inputConnection.setComposingText(
                                                composedResult.toString(),
                                                1
                                            )
                                        }
                                    }
                                    'ㅜ' -> {
                                        if (secondInputTime - firstInputTime >= separationTime1) {
                                            inputConnection.commitText(composedResult.toString(), 1)
                                            state = -1
                                            firstChar = nullChar
                                            middleChar = c
                                            composeResult()
                                            composedResult = nullChar
                                            inputConnection.setComposingText(
                                                middleChar.toString(),
                                                1
                                            )
                                            setFirstInputTimer(inputTime = inputTime)
                                        } else {
                                            middleChar = 'ㅟ'
                                            composeResult()
                                            inputConnection.setComposingText(
                                                composedResult.toString(),
                                                1
                                            )
                                        }
                                    }
                                    'ㅡ' -> {
                                        if (secondInputTime - firstInputTime >= separationTime1) {
                                            inputConnection.commitText(composedResult.toString(), 1)
                                            state = -1
                                            firstChar = nullChar
                                            middleChar = c
                                            composeResult()
                                            composedResult = nullChar
                                            inputConnection.setComposingText(
                                                middleChar.toString(),
                                                1
                                            )
                                            setFirstInputTimer(inputTime = inputTime)
                                        } else {
                                            middleChar = 'ㅢ'
                                            composeResult()
                                            inputConnection.setComposingText(
                                                composedResult.toString(),
                                                1
                                            )
                                        }
                                    }
                                    else -> {
                                        inputConnection.commitText(composedResult.toString(), 1)
                                        state = -1
                                        firstChar = nullChar
                                        middleChar = c
                                        composeResult()
                                        composedResult = nullChar
                                        inputConnection.setComposingText(middleChar.toString(), 1)
                                        setFirstInputTimer(inputTime = inputTime)
                                    }
                                }
                            }
                        }
                    }
                    else -> {
                        inputConnection.finishComposingText()
                        state = 0
                        initChar()
                        composeResult()
                        composedResult = nullChar
                        inputConnection.commitText(c.toString(), 1)
                    }
                }
            }
            3 -> { // 자음+모음+자음이 들어온 상태(종성)
                when {
                    isInputCharFirstChar -> { // 자음이 들어온다면
                        setSecondInputTimer(inputTime = inputTime)
                        when (c) {
                            'ㄱ' -> {
                                when (finalChar) {
                                    'ㄱ' -> {
                                        if (secondInputTime - firstInputTime >= separationTime2) {
                                            inputConnection.commitText(composedResult.toString(), 1)
                                            state = 1
                                            firstChar = c
                                            middleChar = nullChar
                                            finalChar = nullChar
                                            composeResult()
                                            composedResult = nullChar
                                            inputConnection.setComposingText(
                                                firstChar.toString(),
                                                1
                                            )
                                            setFirstInputTimer(inputTime = inputTime)
                                        } else {
                                            finalChar = 'ㄲ'
                                            composeResult()
                                            inputConnection.setComposingText(
                                                composedResult.toString(),
                                                1
                                            )
                                        }
                                    }
                                    'ㄹ' -> {
                                        finalChar = 'ㄺ'
                                        composeResult()
                                        inputConnection.setComposingText(
                                            composedResult.toString(),
                                            1
                                        )
                                    }
                                    'ㄺ' -> {
                                        if (secondInputTime - firstInputTime >= separationTime2) { // 갉+ㄱ -> 갉ㄱ
                                            inputConnection.commitText(composedResult.toString(), 1)
                                            state = 1
                                            firstChar = c
                                            middleChar = nullChar
                                            finalChar = nullChar
                                            composeResult()
                                            composedResult = nullChar
                                            inputConnection.setComposingText(
                                                firstChar.toString(),
                                                1
                                            )
                                            setFirstInputTimer(inputTime = inputTime)
                                        } else { // 갉+ㄱ -> 갈ㄲ
                                            finalChar = 'ㄹ'
                                            composeResult()
                                            inputConnection.commitText(
                                                composedResult.toString(),
                                                1
                                            )
                                            state = 1
                                            firstChar = 'ㄲ'
                                            middleChar = nullChar
                                            finalChar = nullChar
                                            composeResult()
                                            composedResult = nullChar
                                            inputConnection.setComposingText(
                                                firstChar.toString(),
                                                1
                                            )
                                            setFirstInputTimer(inputTime = inputTime)
                                        }
                                    }
                                    else -> {
                                        inputConnection.commitText(composedResult.toString(), 1)
                                        state = 1
                                        firstChar = c
                                        middleChar = nullChar
                                        finalChar = nullChar
                                        composedResult = nullChar
                                        inputConnection.setComposingText(firstChar.toString(), 1)
                                        setFirstInputTimer(inputTime = inputTime)
                                    }
                                }
                            }
                            'ㄷ' -> {
                                when (finalChar) {
                                    'ㄷ' -> {
                                        if (secondInputTime - firstInputTime >= separationTime2) { // 닫+ㄷ -> 닫ㄷ
                                            inputConnection.commitText(composedResult.toString(), 1)
                                            state = 1
                                            firstChar = c
                                            middleChar = nullChar
                                            finalChar = nullChar
                                            composeResult()
                                            composedResult = nullChar
                                            inputConnection.setComposingText(
                                                firstChar.toString(),
                                                1
                                            )
                                            setFirstInputTimer(inputTime = inputTime)
                                        } else { // 닫+ㄷ -> 다ㄸ
                                            finalChar = nullChar
                                            composeResult()
                                            inputConnection.commitText(
                                                composedResult.toString(),
                                                1
                                            )
                                            state = 1
                                            firstChar = 'ㄸ'
                                            middleChar = nullChar
                                            finalChar = nullChar
                                            composeResult()
                                            composedResult = nullChar
                                            inputConnection.setComposingText(
                                                firstChar.toString(),
                                                1
                                            )
                                            setFirstInputTimer(inputTime = inputTime)
                                        }
                                    }
                                    else -> {
                                        inputConnection.commitText(composedResult.toString(), 1)
                                        state = 1
                                        firstChar = c
                                        middleChar = nullChar
                                        finalChar = nullChar
                                        composedResult = nullChar
                                        inputConnection.setComposingText(firstChar.toString(), 1)
                                        setFirstInputTimer(inputTime = inputTime)
                                    }
                                }
                            }
                            'ㅁ' -> {
                                when (finalChar) {
                                    'ㄹ' -> {
                                        finalChar = 'ㄻ'
                                        composeResult()
                                        inputConnection.setComposingText(
                                            composedResult.toString(),
                                            1
                                        )
                                    }
                                    else -> {
                                        inputConnection.commitText(composedResult.toString(), 1)
                                        state = 1
                                        firstChar = c
                                        middleChar = nullChar
                                        finalChar = nullChar
                                        composedResult = nullChar
                                        inputConnection.setComposingText(firstChar.toString(), 1)
                                        setFirstInputTimer(inputTime = inputTime)
                                    }
                                }
                            }
                            'ㅂ' -> {
                                when (finalChar) {
                                    'ㄹ' -> {
                                        finalChar = 'ㄼ'
                                        composeResult()
                                        inputConnection.setComposingText(
                                            composedResult.toString(),
                                            1
                                        )
                                    }
                                    'ㄼ' -> {
                                        if (secondInputTime - firstInputTime >= separationTime2) { // 밟+ㅂ -> 밟ㅂ
                                            inputConnection.commitText(composedResult.toString(), 1)
                                            state = 1
                                            firstChar = c
                                            middleChar = nullChar
                                            finalChar = nullChar
                                            composeResult()
                                            composedResult = nullChar
                                            inputConnection.setComposingText(
                                                firstChar.toString(),
                                                1
                                            )
                                            setFirstInputTimer(inputTime = inputTime)
                                        } else { // 밟+ㅂ -> 발ㅃ
                                            finalChar = 'ㄹ'
                                            composeResult()
                                            inputConnection.commitText(
                                                composedResult.toString(),
                                                1
                                            )
                                            state = 1
                                            firstChar = 'ㅃ'
                                            middleChar = nullChar
                                            finalChar = nullChar
                                            composeResult()
                                            composedResult = nullChar
                                            inputConnection.setComposingText(
                                                firstChar.toString(),
                                                1
                                            )
                                            setFirstInputTimer(inputTime = inputTime)
                                        }
                                    }
                                    'ㅂ' -> {
                                        if (secondInputTime - firstInputTime >= separationTime2) { // 밥+ㅂ -> 밥ㅂ
                                            inputConnection.commitText(composedResult.toString(), 1)
                                            state = 1
                                            firstChar = c
                                            middleChar = nullChar
                                            finalChar = nullChar
                                            composeResult()
                                            composedResult = nullChar
                                            inputConnection.setComposingText(
                                                firstChar.toString(),
                                                1
                                            )
                                            setFirstInputTimer(inputTime = inputTime)
                                        } else { // 밥+ㅂ -> 바ㅃ
                                            finalChar = nullChar
                                            composeResult()
                                            inputConnection.commitText(
                                                composedResult.toString(),
                                                1
                                            )
                                            state = 1
                                            firstChar = 'ㅃ'
                                            middleChar = nullChar
                                            finalChar = nullChar
                                            composeResult()
                                            composedResult = nullChar
                                            inputConnection.setComposingText(
                                                firstChar.toString(),
                                                1
                                            )
                                            setFirstInputTimer(inputTime = inputTime)
                                        }
                                    }
                                    else -> {
                                        inputConnection.commitText(composedResult.toString(), 1)
                                        state = 1
                                        firstChar = c
                                        middleChar = nullChar
                                        finalChar = nullChar
                                        composedResult = nullChar
                                        inputConnection.setComposingText(firstChar.toString(), 1)
                                        setFirstInputTimer(inputTime = inputTime)
                                    }
                                }
                            }
                            'ㅅ' -> {
                                when (finalChar) {
                                    'ㄱ' -> {
                                        finalChar = 'ㄳ'
                                        composeResult()
                                        inputConnection.setComposingText(
                                            composedResult.toString(),
                                            1
                                        )
                                    }
                                    'ㄳ' -> {
                                        if (secondInputTime - firstInputTime >= separationTime2) { // 삯+ㅅ -> 삯ㅅ
                                            inputConnection.commitText(composedResult.toString(), 1)
                                            state = 1
                                            firstChar = c
                                            middleChar = nullChar
                                            finalChar = nullChar
                                            composeResult()
                                            composedResult = nullChar
                                            inputConnection.setComposingText(
                                                firstChar.toString(),
                                                1
                                            )
                                            setFirstInputTimer(inputTime = inputTime)
                                        } else { // 삯+ㅅ -> 삭ㅆ
                                            finalChar = 'ㄱ'
                                            composeResult()
                                            inputConnection.commitText(
                                                composedResult.toString(),
                                                1
                                            )
                                            state = 1
                                            firstChar = 'ㅆ'
                                            middleChar = nullChar
                                            finalChar = nullChar
                                            composeResult()
                                            composedResult = nullChar
                                            inputConnection.setComposingText(
                                                firstChar.toString(),
                                                1
                                            )
                                            setFirstInputTimer(inputTime = inputTime)
                                        }
                                    }
                                    'ㄹ' -> {
                                        finalChar = 'ㄽ'
                                        composeResult()
                                        inputConnection.setComposingText(
                                            composedResult.toString(),
                                            1
                                        )
                                    }
                                    'ㄽ' -> {
                                        if (secondInputTime - firstInputTime >= separationTime2) { // 삸+ㅅ -> 삸ㅅ
                                            inputConnection.commitText(composedResult.toString(), 1)
                                            state = 1
                                            firstChar = c
                                            middleChar = nullChar
                                            finalChar = nullChar
                                            composeResult()
                                            composedResult = nullChar
                                            inputConnection.setComposingText(
                                                firstChar.toString(),
                                                1
                                            )
                                            setFirstInputTimer(inputTime = inputTime)
                                        } else { // 삸+ㅅ -> 살ㅆ
                                            finalChar = 'ㄹ'
                                            composeResult()
                                            inputConnection.commitText(
                                                composedResult.toString(),
                                                1
                                            )
                                            state = 1
                                            firstChar = 'ㅆ'
                                            middleChar = nullChar
                                            finalChar = nullChar
                                            composeResult()
                                            composedResult = nullChar
                                            inputConnection.setComposingText(
                                                firstChar.toString(),
                                                1
                                            )
                                            setFirstInputTimer(inputTime = inputTime)
                                        }
                                    }
                                    'ㅂ' -> {
                                        finalChar = 'ㅄ'
                                        composeResult()
                                        inputConnection.setComposingText(
                                            composedResult.toString(),
                                            1
                                        )
                                    }
                                    'ㅄ' -> {
                                        if (secondInputTime - firstInputTime >= separationTime2) { // 삾+ㅅ -> 삾ㅅ
                                            inputConnection.commitText(composedResult.toString(), 1)
                                            state = 1
                                            firstChar = c
                                            middleChar = nullChar
                                            finalChar = nullChar
                                            composeResult()
                                            composedResult = nullChar
                                            inputConnection.setComposingText(
                                                firstChar.toString(),
                                                1
                                            )
                                            setFirstInputTimer(inputTime = inputTime)
                                        } else { // 삾+ㅅ -> 삽ㅆ
                                            finalChar = 'ㅂ'
                                            composeResult()
                                            inputConnection.commitText(
                                                composedResult.toString(),
                                                1
                                            )
                                            state = 1
                                            firstChar = 'ㅆ'
                                            middleChar = nullChar
                                            finalChar = nullChar
                                            composeResult()
                                            composedResult = nullChar
                                            inputConnection.setComposingText(
                                                firstChar.toString(),
                                                1
                                            )
                                            setFirstInputTimer(inputTime = inputTime)
                                        }
                                    }
                                    'ㅅ' -> {
                                        if (secondInputTime - firstInputTime >= separationTime2) {
                                            inputConnection.commitText(composedResult.toString(), 1)
                                            state = 1
                                            firstChar = c
                                            middleChar = nullChar
                                            finalChar = nullChar
                                            composeResult()
                                            composedResult = nullChar
                                            inputConnection.setComposingText(
                                                firstChar.toString(),
                                                1
                                            )
                                            setFirstInputTimer(inputTime = inputTime)
                                        } else {
                                            finalChar = 'ㅆ'
                                            composeResult()
                                            inputConnection.setComposingText(
                                                composedResult.toString(),
                                                1
                                            )
                                        }
                                    }
                                    else -> {
                                        inputConnection.commitText(composedResult.toString(), 1)
                                        state = 1
                                        firstChar = c
                                        middleChar = nullChar
                                        finalChar = nullChar
                                        composedResult = nullChar
                                        inputConnection.setComposingText(firstChar.toString(), 1)
                                        setFirstInputTimer(inputTime = inputTime)
                                    }
                                }
                            }
                            'ㅈ' -> {
                                when (finalChar) {
                                    'ㄴ' -> {
                                        finalChar = 'ㄵ'
                                        composeResult()
                                        inputConnection.setComposingText(
                                            composedResult.toString(),
                                            1
                                        )
                                    }
                                    'ㄵ' -> {
                                        if (secondInputTime - firstInputTime >= separationTime2) { // 잕+ㅈ -> 잕ㅈ
                                            inputConnection.commitText(composedResult.toString(), 1)
                                            state = 1
                                            firstChar = c
                                            middleChar = nullChar
                                            finalChar = nullChar
                                            composeResult()
                                            composedResult = nullChar
                                            inputConnection.setComposingText(
                                                firstChar.toString(),
                                                1
                                            )
                                            setFirstInputTimer(inputTime = inputTime)
                                        } else { // 잕+ㅈ -> 잔ㅉ
                                            finalChar = 'ㄴ'
                                            composeResult()
                                            inputConnection.commitText(
                                                composedResult.toString(),
                                                1
                                            )
                                            state = 1
                                            firstChar = 'ㅉ'
                                            middleChar = nullChar
                                            finalChar = nullChar
                                            composeResult()
                                            composedResult = nullChar
                                            inputConnection.setComposingText(
                                                firstChar.toString(),
                                                1
                                            )
                                            setFirstInputTimer(inputTime = inputTime)
                                        }
                                    }
                                    'ㅈ' -> {
                                        if (secondInputTime - firstInputTime >= separationTime2) { // 잦+ㅈ -> 잦ㅈ
                                            inputConnection.commitText(composedResult.toString(), 1)
                                            state = 1
                                            firstChar = c
                                            middleChar = nullChar
                                            finalChar = nullChar
                                            composeResult()
                                            composedResult = nullChar
                                            inputConnection.setComposingText(
                                                firstChar.toString(),
                                                1
                                            )
                                            setFirstInputTimer(inputTime = inputTime)
                                        } else { // 잦+ㅈ -> 자ㅉ
                                            finalChar = nullChar
                                            composeResult()
                                            inputConnection.commitText(
                                                composedResult.toString(),
                                                1
                                            )
                                            state = 1
                                            firstChar = 'ㅉ'
                                            middleChar = nullChar
                                            finalChar = nullChar
                                            composeResult()
                                            composedResult = nullChar
                                            inputConnection.setComposingText(
                                                firstChar.toString(),
                                                1
                                            )
                                            setFirstInputTimer(inputTime = inputTime)
                                        }
                                    }
                                    else -> {
                                        inputConnection.commitText(composedResult.toString(), 1)
                                        state = 1
                                        firstChar = c
                                        middleChar = nullChar
                                        finalChar = nullChar
                                        composedResult = nullChar
                                        inputConnection.setComposingText(firstChar.toString(), 1)
                                        setFirstInputTimer(inputTime = inputTime)
                                    }
                                }
                            }
                            'ㅌ' -> {
                                when (finalChar) {
                                    'ㄹ' -> {
                                        finalChar = 'ㄾ'
                                        composeResult()
                                        inputConnection.setComposingText(
                                            composedResult.toString(),
                                            1
                                        )
                                    }
                                    else -> {
                                        inputConnection.commitText(composedResult.toString(), 1)
                                        state = 1
                                        firstChar = c
                                        middleChar = nullChar
                                        finalChar = nullChar
                                        composedResult = nullChar
                                        inputConnection.setComposingText(firstChar.toString(), 1)
                                        setFirstInputTimer(inputTime = inputTime)
                                    }
                                }
                            }
                            'ㅍ' -> {
                                when (finalChar) {
                                    'ㄹ' -> {
                                        finalChar = 'ㄿ'
                                        composeResult()
                                        inputConnection.setComposingText(
                                            composedResult.toString(),
                                            1
                                        )
                                    }
                                    else -> {
                                        inputConnection.commitText(composedResult.toString(), 1)
                                        state = 1
                                        firstChar = c
                                        middleChar = nullChar
                                        finalChar = nullChar
                                        composedResult = nullChar
                                        inputConnection.setComposingText(firstChar.toString(), 1)
                                        setFirstInputTimer(inputTime = inputTime)
                                    }
                                }
                            }
                            'ㅎ' -> {
                                when (finalChar) {
                                    'ㄴ' -> {
                                        finalChar = 'ㄶ'
                                        composeResult()
                                        inputConnection.setComposingText(
                                            composedResult.toString(),
                                            1
                                        )
                                    }
                                    'ㄹ' -> {
                                        finalChar = 'ㅀ'
                                        composeResult()
                                        inputConnection.setComposingText(
                                            composedResult.toString(),
                                            1
                                        )
                                    }
                                    else -> {
                                        inputConnection.commitText(composedResult.toString(), 1)
                                        state = 1
                                        firstChar = c
                                        middleChar = nullChar
                                        finalChar = nullChar
                                        composedResult = nullChar
                                        inputConnection.setComposingText(firstChar.toString(), 1)
                                        setFirstInputTimer(inputTime = inputTime)
                                    }
                                }
                            }
                            else -> { // ㄴㄹㅇㅊㅋ
                                inputConnection.commitText(composedResult.toString(), 1)
                                state = 1
                                firstChar = c
                                middleChar = nullChar
                                finalChar = nullChar
                                composedResult = nullChar
                                inputConnection.setComposingText(firstChar.toString(), 1)
                                setFirstInputTimer(inputTime = inputTime)
                            }
                        }
                    }
                    isInputCharMiddleChar -> { // 모음이 들어온다면 ex) 간+ㅏ -> 가나
                        when (finalChar) {
                            'ㄳ' -> {
                                finalChar = 'ㄱ'
                                composeResult()
                                inputConnection.commitText(composedResult.toString(), 1)
                                initChar()
                                state = 2
                                firstChar = 'ㅅ'
                            }
                            'ㄵ' -> {
                                finalChar = 'ㄴ'
                                composeResult()
                                inputConnection.commitText(composedResult.toString(), 1)
                                initChar()
                                state = 2
                                firstChar = 'ㅈ'
                            }
                            'ㄶ' -> {
                                finalChar = 'ㄴ'
                                composeResult()
                                inputConnection.commitText(composedResult.toString(), 1)
                                initChar()
                                state = 2
                                firstChar = 'ㅎ'
                            }
                            'ㄺ' -> {
                                finalChar = 'ㄹ'
                                composeResult()
                                inputConnection.commitText(composedResult.toString(), 1)
                                initChar()
                                state = 2
                                firstChar = 'ㄱ'
                            }
                            'ㄻ' -> {
                                finalChar = 'ㄹ'
                                composeResult()
                                inputConnection.commitText(composedResult.toString(), 1)
                                initChar()
                                state = 2
                                firstChar = 'ㅁ'
                            }
                            'ㄼ' -> {
                                finalChar = 'ㄹ'
                                composeResult()
                                inputConnection.commitText(composedResult.toString(), 1)
                                initChar()
                                state = 2
                                firstChar = 'ㅂ'
                            }
                            'ㄽ' -> {
                                finalChar = 'ㄹ'
                                composeResult()
                                inputConnection.commitText(composedResult.toString(), 1)
                                initChar()
                                state = 2
                                firstChar = 'ㅅ'
                            }
                            'ㄾ' -> {
                                finalChar = 'ㄹ'
                                composeResult()
                                inputConnection.commitText(composedResult.toString(), 1)
                                initChar()
                                state = 2
                                firstChar = 'ㅌ'
                            }
                            'ㄿ' -> {
                                finalChar = 'ㄹ'
                                composeResult()
                                inputConnection.commitText(composedResult.toString(), 1)
                                initChar()
                                state = 2
                                firstChar = 'ㅍ'
                            }
                            'ㅀ' -> {
                                finalChar = 'ㄹ'
                                composeResult()
                                inputConnection.commitText(composedResult.toString(), 1)
                                initChar()
                                state = 2
                                firstChar = 'ㅎ'
                            }
                            'ㅄ' -> {
                                finalChar = 'ㅂ'
                                composeResult()
                                inputConnection.commitText(composedResult.toString(), 1)
                                initChar()
                                state = 2
                                firstChar = 'ㅅ'
                            }
                            else -> {
                                val temp = finalChar //temp 값에 종성 저장
                                finalChar = nullChar // 간 -> 가
                                composeResult()
                                inputConnection.commitText(composedResult.toString(), 1) //가+ㄴ+ㅏ
                                state = 2
                                firstChar = temp //원래 글자의 종성을 새 초성으로 재입력, ㄴ
                            }
                        }
                        middleChar = c
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                        setFirstInputTimer(inputTime = inputTime)
                    }
                    else -> {
                        inputConnection.finishComposingText()
                        state = 0
                        initChar()
                        composeResult()
                        composedResult = nullChar
                        inputConnection.commitText(" ", 1)
                    }
                }
            }
        }
    }

    fun delete(c: Char?, inputConnection: InputConnection, inputTime: Long) { // 삭제 버튼
        if ( c == null ) {
            inputConnection.commitText("", 1)
        }
        when ( state ) {
            0-> {
                inputConnection.commitText("", 1)
                inputConnection.deleteSurroundingText(1, 0)
            }
            1 -> {
                when ( firstChar ) {
                    'ㄲ', 'ㄳ' -> {
                        firstChar = 'ㄱ'
                        composeResult()
                        inputConnection.setComposingText(firstChar.toString(), 1)
                        setFirstInputTimer(inputTime = inputTime)
                    }
                    'ㄵ', 'ㄶ' -> {
                        firstChar = 'ㄴ'
                        composeResult()
                        inputConnection.setComposingText(firstChar.toString(), 1)
                        setFirstInputTimer(inputTime = inputTime)
                    }
                    'ㄸ' -> {
                        firstChar = 'ㄷ'
                        composeResult()
                        inputConnection.setComposingText(firstChar.toString(), 1)
                        setFirstInputTimer(inputTime = inputTime)
                    }
                    'ㄺ', 'ㄻ', 'ㄼ', 'ㄽ', 'ㄾ', 'ㄿ', 'ㅀ' -> {
                        firstChar = 'ㄹ'
                        composeResult()
                        inputConnection.setComposingText(firstChar.toString(), 1)
                        setFirstInputTimer(inputTime = inputTime)
                    }
                    'ㅃ', 'ㅄ' -> {
                        firstChar = 'ㅂ'
                        composeResult()
                        inputConnection.setComposingText(firstChar.toString(), 1)
                        setFirstInputTimer(inputTime = inputTime)
                    }
                    'ㅆ' -> {
                        firstChar = 'ㅅ'
                        composeResult()
                        inputConnection.setComposingText(firstChar.toString(), 1)
                        setFirstInputTimer(inputTime = inputTime)
                    }
                    'ㅉ' -> {
                        firstChar = 'ㅈ'
                        composeResult()
                        inputConnection.setComposingText(firstChar.toString(), 1)
                        setFirstInputTimer(inputTime = inputTime)
                    }
                    else -> {
                        state = 0
                        middleChar = nullChar
                        composeResult()
                        inputConnection.setComposingText("", 1)
                    }
                }
            }
            -1 -> {
                when ( middleChar ) {
                    'ㅘ', 'ㅙ', 'ㅚ' -> {
                        middleChar = 'ㅗ'
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                        setFirstInputTimer(inputTime = inputTime)
                    }
                    'ㅝ', 'ㅞ', 'ㅟ' -> {
                        middleChar = 'ㅜ'
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                        setFirstInputTimer(inputTime = inputTime)
                    }
                    'ㅢ' -> {
                        middleChar = 'ㅡ'
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                        setFirstInputTimer(inputTime = inputTime)
                    }
                    else -> {
                        state = 0
                        middleChar = nullChar
                        composeResult()
                        inputConnection.setComposingText("", 1)
                    }
                }
            }
            2 -> {
                when ( middleChar ) {
                    'ㅘ', 'ㅙ', 'ㅚ' -> {
                        middleChar = 'ㅗ'
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                        setFirstInputTimer(inputTime = inputTime)
                    }
                    'ㅝ', 'ㅞ', 'ㅟ' -> {
                        middleChar = 'ㅜ'
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                        setFirstInputTimer(inputTime = inputTime)
                    }
                    'ㅢ' -> {
                        middleChar = 'ㅡ'
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                        setFirstInputTimer(inputTime = inputTime)
                    }
                    else -> {
                        state = 1
                        middleChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(firstChar.toString(), 1)
                        setFirstInputTimer(inputTime = inputTime)
                    }
                }
            }
            3 -> {
                when ( finalChar ) {
                    'ㄲ', 'ㄳ' -> {
                        finalChar = 'ㄱ'
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                        setFirstInputTimer(inputTime = inputTime)
                    }
                    'ㄵ', 'ㄶ' -> {
                        finalChar = 'ㄴ'
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                        setFirstInputTimer(inputTime = inputTime)
                    }
                    'ㄺ', 'ㄻ', 'ㄼ', 'ㄽ', 'ㄾ', 'ㄿ', 'ㅀ' -> {
                        finalChar = 'ㄹ'
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                        setFirstInputTimer(inputTime = inputTime)
                    }
                    'ㅄ' -> {
                        finalChar = 'ㅂ'
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                        setFirstInputTimer(inputTime = inputTime)
                    }
                    'ㅆ' -> {
                        finalChar = 'ㅅ'
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                        setFirstInputTimer(inputTime = inputTime)
                    }
                    else -> {
                        finalChar = nullChar
                        state = 2
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                        setFirstInputTimer(inputTime = inputTime)
                    }
                }
            }
        }
    }

    override fun initChar() { // 초중성 초기화
        firstChar = nullChar
        middleChar = nullChar
        finalChar = nullChar
    }

    override fun initState() { // 현재 한글완성상태 초기화
        state = 0
    }

    override fun initResult() { // 완성된 글자 초기화
        composedResult = nullChar
    }

    var firstInputTime = 0.toLong()
    var secondInputTime = 0.toLong()

    private fun setFirstInputTimer(inputTime: Long) {
        firstInputTime = inputTime
    }
    private fun setSecondInputTimer(inputTime: Long) {
        secondInputTime = inputTime
    }

    private val separationTime1 = 300
    private val separationTime2 = 500
}