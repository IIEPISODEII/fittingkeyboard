package com.sb.fittingKeyboard.koreanAutomata

import android.view.inputmethod.InputConnection

class HanguelQWERTY : Automata() {
    fun composeChar(c: Char, inputConnection: InputConnection) {
        val isInputCharFirstChar = c.toInt() in firstCharArray
        val isInputCharMiddleChar = c.toInt() in middleCharArray
        when ( state ) {
            0 -> { // 아무것도 없는 상태. 자음 혹은 모음이 들어올 수 있다
                when {
                    isInputCharFirstChar -> { //자음이 들어온다면
                        state = 1
                        firstChar = c
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                    isInputCharMiddleChar -> { //모음이 들어온다면
                        state = -1
                        middleChar = c
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    else -> {
                        initChar()
                        composedResult = nullChar
                        inputConnection.commitText(c.toString(), 1)
                    }
                }
            }
            -1 -> { //모음이 들어온 상태
                when {
                    isInputCharFirstChar -> { // ㅏ+ㄱ -> ㅏㄱ
                        inputConnection.commitText(middleChar.toString(), 1)
                        firstChar = c
                        middleChar = nullChar
                        state = 1
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                    isInputCharMiddleChar -> {
                        if (canBeDoubleVowelChar(middleChar, c)) { // ㅗ+ㅐ -> ㅙ, ㅜ+ㅣ -> ㅟ ...
                            inputConnection.commitText(doubleChar(middleChar, c).toString(), 1)
                            state = 0
                            initChar()
                            composedResult = nullChar
                        } else {
                            inputConnection.commitText(middleChar.toString(), 1)
                            middleChar = c
                            composeResult()
                            composedResult = nullChar
                            inputConnection.setComposingText(c.toString(), 1)
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
            1 -> { //자음이 들어온 상태(초성)
                when {
                    isInputCharFirstChar -> { // 자음이 들어온다면 ex) ㄱㄱ-> ㄲ, ㄹㄱ -> ㄺ, ㄱㄴ
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
                    isInputCharMiddleChar -> { // 모음이 들어온다면 ex) ㄱ+ㅏ -> 가
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
            2 -> { // 자음+모음이 들어온 상태(중성)
                when {
                    isInputCharFirstChar -> {
                        if (c in arrayOf('ㅃ', 'ㄸ', 'ㅉ')) {
                            inputConnection.commitText(composedResult.toString(), 1)
                            initChar()
                            state = 1
                            firstChar = c
                            composeResult()
                            inputConnection.setComposingText(c.toString(), 1)
                        } else {
                            finalChar = c
                            state = 3
                            composeResult()
                            inputConnection.setComposingText(composedResult.toString(), 1)
                        }
                    }
                    isInputCharMiddleChar -> {
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
                    isInputCharFirstChar -> {
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
                    isInputCharMiddleChar -> {
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
                    'ㄺ', 'ㄻ', 'ㄼ', 'ㄽ', 'ㄾ', 'ㄿ', 'ㅀ' -> {
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
                    'ㄺ', 'ㄻ', 'ㄼ', 'ㄽ', 'ㄾ', 'ㄿ', 'ㅀ' -> {
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
                    'ㄱ', 'ㅁ', 'ㅂ', 'ㅅ', 'ㅌ', 'ㅍ', 'ㅎ' -> true
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
            first == 'ㄹ' && second == 'ㅍ' -> { return 'ㄿ' }
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