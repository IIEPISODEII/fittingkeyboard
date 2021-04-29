package com.sb.fittingKeyboard.koreanAutomata

import android.view.inputmethod.InputConnection

class QWERTY_Hangul {
    private val baseInt: Int = 0xAC00
    private val nullChar: Char = '\u0000'

    private var firstChar: Char = nullChar
    private var middleChar: Char = nullChar
    private var finalChar: Char = nullChar

    private val firstCharArray: Array<Int> = arrayOf(0x3131, 0x3132, 0x3134, 0x3137, 0x3138, 0x3139, 0x3141,0x3142, 0x3143, 0x3145, 0x3146, 0x3147, 0x3148, 0x3149, 0x314a, 0x314b, 0x314c, 0x314d, 0x314e)
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
        composedResult = (baseInt + firstCharIndex * 21 * 28 + middleCharIndex * 28 + finalCharIndex).toChar()
    }

    fun composeChar(c: Char, inputConnection: InputConnection) {
        val inputIndexFirstChar = firstCharArray.indexOf(c.toInt())
        val inputIndexMiddleChar = middleCharArray.indexOf(c.toInt())
        val inputIndexFinalChar = finalCharArray.indexOf(c.toInt())
        when ( state ) {
            0 -> { //아무것도 없는 상태. 자음 혹은 모음이 들어올 수 있다
                if (inputIndexFirstChar >= 0 && inputIndexMiddleChar < 0) { //자음이 들어온다면
                    firstChar = c
                    composeResult()
                    composedResult = nullChar
                    state = 1
                    inputConnection.setComposingText(firstChar.toString(), 1)
                }
                else if (inputIndexMiddleChar >= 0  && inputIndexFirstChar < 0) { //모음이 들어온다면
                    state = -1
                    middleChar = c
                    composeResult()
                    composedResult = nullChar
                    inputConnection.setComposingText(middleChar.toString(), 1)
                }
                else if ( inputIndexFirstChar < 0 && inputIndexMiddleChar < 0 && c.toString() == " " ) {
                    initChar()
                    composeResult()
                    composedResult = nullChar
                    inputConnection.commitText(" ", 1)
                }
                else if ( inputIndexFirstChar < 0 && inputIndexMiddleChar < 0 && c.toString() == "," ) {
                    initChar()
                    composeResult()
                    composedResult = nullChar
                    inputConnection.commitText(",", 1)
                }
                else if ( inputIndexFirstChar < 0 && inputIndexMiddleChar < 0 && c.toString() == "." ) {
                    initChar()
                    composeResult()
                    composedResult = nullChar
                    inputConnection.commitText(".", 1)
                }
                else {
                    initChar()
                    composeResult()
                    composedResult = nullChar
                    inputConnection.commitText(c.toString(), 1)
                }
            }

            -1 -> { //모음이 들어온 상태
                if ( inputIndexFirstChar < 0 && inputIndexMiddleChar >= 0 && canBeDoubleVowelChar(middleChar, c) ) { //이중모음만으로 이루어진 글자라면
                    composeResult()
                    inputConnection.commitText(doubleChar(middleChar, c).toString(), 1)
                    state = 0
                    initChar()
                    composeResult()
                    composedResult = nullChar
                }
                else if ( inputIndexFirstChar < 0 && inputIndexMiddleChar >= 0 && !canBeDoubleVowelChar(middleChar, c) ) {
                    inputConnection.commitText(middleChar.toString(), 1)
                    middleChar = c
                    composeResult()
                    composedResult = nullChar
                    inputConnection.setComposingText(c.toString(), 1)
                }
                else if ( inputIndexFirstChar >= 0 && inputIndexMiddleChar < 0 ){ //ex) ㅠㅠ, ㅗㅗ, ㅗㄱ 등
                    inputConnection.commitText(middleChar.toString(), 1)
                    firstChar = c
                    middleChar = nullChar
                    state = 1
                    composeResult()
                    composedResult = nullChar
                    inputConnection.setComposingText(firstChar.toString(), 1)
                }
                else if ( inputIndexFirstChar < 0 && inputIndexMiddleChar < 0 && c.toString() == " " ) {
                    state = 0
                    initChar()
                    composeResult()
                    composedResult = nullChar
                    inputConnection.commitText(" ", 1)
                }
                else if ( inputIndexFirstChar < 0 && inputIndexMiddleChar < 0 && c.toString() == "," ) {
                    state = 0
                    initChar()
                    composeResult()
                    composedResult = nullChar
                    inputConnection.commitText(",", 1)
                }
                else if ( inputIndexFirstChar < 0 && inputIndexMiddleChar < 0 && c.toString() == "." ) {
                    state = 0
                    initChar()
                    composeResult()
                    composedResult = nullChar
                    inputConnection.commitText(".", 1)
                }
                else {
                    inputConnection.finishComposingText()
                    state = 0
                    initChar()
                    composeResult()
                    composedResult = nullChar
                    inputConnection.commitText(c.toString(), 1)
                }
            }
            1 -> { //자음이 들어온 상태(초성)
                if (inputIndexFirstChar >= 0 && inputIndexMiddleChar < 0) { //자음이 들어온다면 ex) ㄱㄱ-> ㄲ, ㄹㄱ -> ㄺ, ㄱㄴ
                    if( canBeDoubleConsonantChar(firstChar, c) ) { // ex) ㄱㄱ -> ㄲ, ㄹㄱ -> ㄺ, ㄷㄷ -> ㄸ  ISSUE: ㅂ, ㅈ, ㄷ에 대해선 쌍자음 적용 x
                        inputConnection.setComposingText(doubleChar(firstChar, c).toString(), 1)
                        firstChar = doubleChar(firstChar, c)
                        composeResult()
                        composedResult = nullChar
                    }
                    else if ( firstChar == c && !canBeDoubleConsonantChar(firstChar, c) ) {
                        when (firstChar) {
                            'ㅂ' -> {
                                firstChar = 'ㅃ'
                                composeResult()
                                composedResult = nullChar
                                inputConnection.setComposingText(firstChar.toString(), 1)
                            }
                            'ㅈ' -> {
                                firstChar = 'ㅉ'
                                composeResult()
                                composedResult = nullChar
                                inputConnection.setComposingText(firstChar.toString(), 1)
                            }
                            'ㄷ' -> {
                                firstChar = 'ㄸ'
                                composeResult()
                                composedResult = nullChar
                                inputConnection.setComposingText(firstChar.toString(), 1)
                            }
                            else -> {
                                inputConnection.commitText(firstChar.toString(), 1) //이전 자음을 commit 후
                                firstChar = c
                                composeResult()
                                composedResult = nullChar
                                inputConnection.setComposingText(firstChar.toString(), 1) //새로운 자음으로 새 글자 구성 시작
                            }
                        }
                    }
                    else if ( firstChar != c && !canBeDoubleConsonantChar(firstChar, c) ) { //ex) ㄱㄴ, ㄱㄷ
                        inputConnection.commitText(firstChar.toString(), 1) //이전 자음을 commit 후
                        firstChar = c
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1) //새로운 자음으로 새 글자 구성 시작
                    }
                }
                else if (inputIndexMiddleChar >= 0 && inputIndexFirstChar < 0) { //모음이 들어온다면 ex) ㄱ+ㅏ -> 가
                    when ( firstChar ) {
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
                }
                else if ( inputIndexFirstChar < 0 && inputIndexMiddleChar < 0 && c.toString() == " " ) {
                    state = 0
                    initChar()
                    composeResult()
                    composedResult = nullChar
                    inputConnection.commitText(" ", 1)
                }
                else if ( inputIndexFirstChar < 0 && inputIndexMiddleChar < 0 && c.toString() == "," ) {
                    state = 0
                    initChar()
                    composeResult()
                    composedResult = nullChar
                    inputConnection.commitText(",", 1)
                }
                else if ( inputIndexFirstChar < 0 && inputIndexMiddleChar < 0 && c.toString() == "." ) {
                    state = 0
                    initChar()
                    composeResult()
                    composedResult = nullChar
                    inputConnection.commitText(".", 1)
                }
                else {
                    inputConnection.finishComposingText()
                    state = 0
                    initChar()
                    composeResult()
                    composedResult = nullChar
                    inputConnection.commitText(c.toString(), 1)
                }
            }
            2 -> { //자음+모음이 들어온 상태(중성)
                if (inputIndexFinalChar >= 0) { //자음(종성)이 들어온다면
                    finalChar = c
                    state = 3
                    composeResult()
                    inputConnection.setComposingText(composedResult.toString(), 1)

                }
                else if (inputIndexMiddleChar >= 0) { //모음이 들어온다면
                    if ( canBeDoubleVowelChar(middleChar, c) ) { //자음+이중모음 글자 완성
                        middleChar = doubleChar(middleChar, c)
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    } else { //글자를 완성하고 다음 글자로 모음을 넘김 ex) 가ㅏ, 나ㅗ
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = -1
                        initChar()
                        middleChar = c
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(c.toString(), 1)
                    }
                }
                else if (c == 'ㅃ' || c == 'ㅉ' || c == 'ㄸ') {
                    inputConnection.commitText(composedResult.toString(), 1)
                    initChar()
                    state = 1
                    firstChar = c
                    composeResult()
                    inputConnection.setComposingText(c.toString(), 1)
                }
                else if ( inputIndexFirstChar < 0 && inputIndexMiddleChar < 0 && c.toString() == " " ) {
                    state = 0
                    initChar()
                    composeResult()
                    composedResult = nullChar
                    inputConnection.commitText(" ", 1)
                }
                else if ( inputIndexFirstChar < 0 && inputIndexMiddleChar < 0 && c.toString() == "," ) {
                    state = 0
                    initChar()
                    composeResult()
                    composedResult = nullChar
                    inputConnection.commitText(",", 1)
                }
                else if ( inputIndexFirstChar < 0 && inputIndexMiddleChar < 0 && c.toString() == "." ) {
                    state = 0
                    initChar()
                    composeResult()
                    composedResult = nullChar
                    inputConnection.commitText(".", 1)
                }
                else {
                    inputConnection.finishComposingText()
                    state = 0
                    initChar()
                    composeResult()
                    composedResult = nullChar
                    inputConnection.commitText(c.toString(), 1)
                }
            }
            3 -> { //자음+모음+자음이 들어온 상태(종성)
                if (inputIndexFirstChar >= 0) { //자음이 들어온다면
                    if ( canBeDoubleConsonantChar(finalChar, c) ) { //이중받침이 가능하다면
                        if ( finalChar != c) {
                            finalChar = doubleChar(finalChar, c) //종성을 이중받침으로 설정
                            composeResult()
                            inputConnection.setComposingText(composedResult.toString(), 1)
                        }
                        else {
                            inputConnection.commitText(composedResult.toString(), 1)
                            state = 1
                            initChar()
                            firstChar = c
                            composeResult()
                            composedResult = nullChar
                            inputConnection.setComposingText(c.toString(), 1)
                        }
                    } else { //이중받침이 불가하다면 ex)간+ㄴ -> 간ㄴ
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 1
                        initChar()
                        firstChar = c
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(c.toString(), 1)
                    }

                }
                else if (inputIndexMiddleChar >= 0) { //모음이 들어온다면 ex) 간+ㅏ -> 가나
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
                }
                else if ( inputIndexFirstChar < 0 && inputIndexMiddleChar < 0 && c.toString() == " " ) {
                    state = 0
                    initChar()
                    composeResult()
                    composedResult = nullChar
                    inputConnection.commitText(" ", 1)
                }
                else if ( inputIndexFirstChar < 0 && inputIndexMiddleChar < 0 && c.toString() == "," ) {
                    state = 0
                    initChar()
                    composeResult()
                    composedResult = nullChar
                    inputConnection.commitText(",", 1)
                }
                else if ( inputIndexFirstChar < 0 && inputIndexMiddleChar < 0 && c.toString() == "." ) {
                    state = 0
                    initChar()
                    composeResult()
                    composedResult = nullChar
                    inputConnection.commitText(".", 1)
                }
                else {
                    inputConnection.finishComposingText()
                    state = 0
                    initChar()
                    composeResult()
                    composedResult = nullChar
                    inputConnection.commitText(c.toString(), 1)
                }
            }
        }
    } //한글 오토마타

    fun delete(c: Char?, inputConnection: InputConnection) {
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
                    }
                    'ㄵ', 'ㄶ' -> {
                        firstChar = 'ㄴ'
                        composeResult()
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                    'ㄸ' -> {
                        firstChar = 'ㄷ'
                        composeResult()
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                    'ㄺ', 'ㄻ', 'ㄼ', 'ㄽ', 'ㄾ', 'ㅀ' -> {
                        firstChar = 'ㄹ'
                        composeResult()
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                    'ㅃ', 'ㅄ' -> {
                        firstChar = 'ㅂ'
                        composeResult()
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                    'ㅆ' -> {
                        firstChar = 'ㅅ'
                        composeResult()
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                    'ㅉ' -> {
                        firstChar = 'ㅈ'
                        composeResult()
                        inputConnection.setComposingText(firstChar.toString(), 1)
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
                    }
                    'ㅝ', 'ㅞ', 'ㅟ' -> {
                        middleChar = 'ㅜ'
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ㅢ' -> {
                        middleChar = 'ㅡ'
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
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
                    }
                    'ㅝ', 'ㅞ', 'ㅟ' -> {
                        middleChar = 'ㅜ'
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ㅢ' -> {
                        middleChar = 'ㅡ'
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    else -> {
                        state = 1
                        middleChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
            }
            3 -> {
                when ( finalChar ) {
                    'ㄲ', 'ㄳ' -> {
                        finalChar = 'ㄱ'
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ㄵ', 'ㄶ' -> {
                        finalChar = 'ㄴ'
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ㄺ', 'ㄻ', 'ㄼ', 'ㄽ', 'ㄾ', 'ㅀ' -> {
                        finalChar = 'ㄹ'
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ㅄ' -> {
                        finalChar = 'ㅂ'
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ㅆ' -> {
                        finalChar = 'ㅅ'
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    else -> {
                        finalChar = nullChar
                        state = 2
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                }
            }
        }
    } //delete 발생

    private fun canBeDoubleConsonantChar(finalChar: Char, c: Char): Boolean { //이중자음이 가능한지 판별
        when ( finalChar ) {
            'ㄱ' -> {
                return when ( c ) {
                    'ㄱ', 'ㅅ' -> true
                    else -> false
                }
            }
            'ㄴ' -> {
                return when ( c ) {
                    'ㅈ', 'ㅎ' -> true
                    else -> false
                }
            }
            'ㄹ' -> {
                return when ( c ) {
                    'ㄱ', 'ㅁ', 'ㅂ', 'ㅅ', 'ㅌ', 'ㅎ' -> true
                    else -> false
                }
            }
            'ㅂ' -> {
                return when ( c ) {
                    'ㅅ' -> true
                    else -> false
                }
            }
            'ㅅ' -> {
                return when ( c ) {
                    'ㅅ' -> true
                    else -> false
                }
            }
            else -> return false
        }
    } //이중자음 가능여부 체크

    private fun canBeDoubleVowelChar(middleChar: Char, c: Char): Boolean { //이중모음이 가능한지 판별
        when ( middleChar ) {
            'ㅗ' -> {
                return when ( c ) {
                    'ㅏ', 'ㅐ', 'ㅣ' -> true
                    else -> false
                }
            }
            'ㅜ' -> {
                return when ( c ) {
                    'ㅓ', 'ㅔ', 'ㅣ' -> true
                    else -> false
                }
            }
            'ㅡ' -> {
                return when ( c ) {
                    'ㅣ' -> true
                    else -> false
                }
            }
            else -> return false
        }
    } //이중모음 가능여부 체크

    fun initChar() {
        firstChar = nullChar
        middleChar = nullChar
        finalChar = nullChar
    } //자모 초기화

    fun initState() {
        state = 0
    }

    fun initResult() {
        composedResult = nullChar
    }

    private fun doubleChar(first: Char, second: Char): Char {
        when {
            first == 'ㄱ' && second == 'ㄱ' -> { return 'ㄲ' }
            first == 'ㄱ' && second == 'ㅅ' -> { return 'ㄳ' }
            first == 'ㄴ' && second == 'ㅈ' -> { return 'ㄵ' }
            first == 'ㄴ' && second == 'ㅎ' -> { return 'ㄶ' }
            first == 'ㄷ' && second == 'ㄷ' -> { return 'ㄸ' }
            first == 'ㄹ' && second == 'ㄱ' -> { return 'ㄺ' }
            first == 'ㄹ' && second == 'ㅁ' -> { return 'ㄻ' }
            first == 'ㄹ' && second == 'ㅂ' -> { return 'ㄼ' }
            first == 'ㄹ' && second == 'ㅅ' -> { return 'ㄽ' }
            first == 'ㄹ' && second == 'ㅌ' -> { return 'ㄾ' }
            first == 'ㄹ' && second == 'ㅎ' -> { return 'ㅀ' }
            first == 'ㅂ' && second == 'ㅂ' -> { return 'ㅃ' }
            first == 'ㅂ' && second == 'ㅅ' -> { return 'ㅄ' }
            first == 'ㅅ' && second == 'ㅅ' -> { return 'ㅆ' }
            first == 'ㅈ' && second == 'ㅈ' -> { return 'ㅉ' }
            first == 'ㅗ' && second == 'ㅏ' -> { return 'ㅘ' }
            first == 'ㅗ' && second == 'ㅐ' -> { return 'ㅙ' }
            first == 'ㅗ' && second == 'ㅣ' -> { return 'ㅚ' }
            first == 'ㅜ' && second == 'ㅓ' -> { return 'ㅝ' }
            first == 'ㅜ' && second == 'ㅔ' -> { return 'ㅞ' }
            first == 'ㅜ' && second == 'ㅣ' -> { return 'ㅟ' }
            first == 'ㅡ' && second == 'ㅣ' -> { return 'ㅢ' }
            else -> return nullChar
        }
    }
}