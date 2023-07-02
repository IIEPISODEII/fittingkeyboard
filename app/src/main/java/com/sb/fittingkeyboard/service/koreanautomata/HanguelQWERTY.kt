package com.sb.fittingkeyboard.service.koreanautomata

object HanguelQWERTY : Automata() {
    fun composeChar(inputChar: Char): UpdatedChars {
        val isInputCharFirstChar = inputChar.code in firstCharArray
        val isInputCharMiddleChar = inputChar.code in middleCharArray
        when (state) {
            0 -> { // 아무것도 없는 상태. 자음 혹은 모음이 들어올 수 있다
                when {
                    isInputCharFirstChar -> { //자음이 들어온다면
                        state = 1
                        firstChar = inputChar
                        composedResult = nullChar
                        return UpdatedChars(null, firstChar.toString())
                    }
                    isInputCharMiddleChar -> { //모음이 들어온다면
                        state = -1
                        middleChar = inputChar
                        composedResult = nullChar
                        return UpdatedChars(null, middleChar.toString())
                    }
                    else -> {
                        initChar()
                        composedResult = nullChar
                        return UpdatedChars(inputChar.toString(), null)
                    }
                }
            }
            -1 -> { //모음이 들어온 상태
                when {
                    isInputCharFirstChar -> { // ㅏ+ㄱ -> ㅏㄱ
                        val temp = middleChar.toString()
                        firstChar = inputChar
                        middleChar = nullChar
                        state = 1
                        composedResult = nullChar
                        return UpdatedChars(temp, firstChar.toString())
                    }
                    isInputCharMiddleChar -> {
                        return if (canBeDoubleVowelChar(middleChar, inputChar)) { // ㅗ+ㅐ -> ㅙ, ㅜ+ㅣ -> ㅟ ...
                            middleChar = doubleChar(middleChar, inputChar)
                            composeResult()
                            composedResult = nullChar
                            UpdatedChars(null, middleChar.toString())
                        } else {
                            val temp = middleChar.toString()
                            middleChar = inputChar
                            composeResult()
                            composedResult = nullChar
                            UpdatedChars(temp, middleChar.toString())
                        }
                    }
                    else -> {
                        val temp = middleChar.toString()
                        state = 0
                        initChar()
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(temp + inputChar.toString(), null)
                    }
                }
            }
            1 -> { //자음이 들어온 상태(초성)
                when {
                    isInputCharFirstChar -> { // 자음이 들어온다면 ex) ㄱㄱ-> ㄲ, ㄹㄱ -> ㄺ, ㄱㄴ
                        if (canBeDoubleConsonantChar(
                                firstChar,
                                inputChar
                            )
                        ) { // ex) ㄱㄱ -> ㄲ, ㄹㄱ -> ㄺ, ㄷㄷ -> ㄸ  ISSUE: ㅂ, ㅈ, ㄷ에 대해선 쌍자음 적용 x
                            firstChar = doubleChar(firstChar, inputChar)
                            composeResult()
                            composedResult = nullChar
                            return UpdatedChars(null, firstChar.toString())
                        } else if (firstChar == inputChar && !canBeDoubleConsonantChar(
                                firstChar,
                                inputChar
                            )
                        ) {
                            when (firstChar) {
                                'ㅂ' -> {
                                    firstChar = 'ㅃ'
                                    composeResult()
                                    composedResult = nullChar
                                    return UpdatedChars(null, firstChar.toString())
                                }
                                'ㅈ' -> {
                                    firstChar = 'ㅉ'
                                    composeResult()
                                    composedResult = nullChar
                                    return UpdatedChars(null, firstChar.toString())
                                }
                                'ㄷ' -> {
                                    firstChar = 'ㄸ'
                                    composeResult()
                                    composedResult = nullChar
                                    return UpdatedChars(null, firstChar.toString())
                                }
                                else -> {
                                    val temp = firstChar.toString() //이전 자음을 commit 후
                                    firstChar = inputChar
                                    composeResult()
                                    composedResult = nullChar
                                    return UpdatedChars(
                                        temp,
                                        firstChar.toString()
                                    ) //새로운 자음으로 새 글자 구성 시작
                                }
                            }
                        } else if (firstChar != inputChar && !canBeDoubleConsonantChar(
                                firstChar,
                                inputChar
                            )
                        ) { //ex) ㄱㄴ, ㄱㄷ
                            val temp = firstChar.toString() //이전 자음을 commit 후
                            firstChar = inputChar
                            composeResult()
                            composedResult = nullChar
                            return UpdatedChars(temp, firstChar.toString()) //새로운 자음으로 새 글자 구성 시작
                        }
                    }
                    isInputCharMiddleChar -> { // 모음이 들어온다면 ex) ㄱ+ㅏ -> 가
                        var temp: String? = null
                        when (firstChar) {
                            'ㄳ' -> {
                                temp = "ㄱ"
                                firstChar = 'ㅅ'
                            }
                            'ㄵ' -> {
                                temp = "ㄴ"
                                firstChar = 'ㅈ'
                            }
                            'ㄶ' -> {
                                temp = "ㄴ"
                                firstChar = 'ㅎ'
                            }
                            'ㄺ' -> {
                                temp = "ㄹ"
                                firstChar = 'ㄱ'
                            }
                            'ㄻ' -> {
                                temp = "ㄹ"
                                firstChar = 'ㅁ'
                            }
                            'ㄼ' -> {
                                temp = "ㄹ"
                                firstChar = 'ㅂ'
                            }
                            'ㄽ' -> {
                                temp = "ㄹ"
                                firstChar = 'ㅅ'
                            }
                            'ㄾ' -> {
                                temp = "ㄹ"
                                firstChar = 'ㅌ'
                            }
                            'ㄿ' -> {
                                temp = "ㄹ"
                                firstChar = 'ㅍ'
                            }
                            'ㅀ' -> {
                                temp = "ㄹ"
                                firstChar = 'ㅎ'
                            }
                            'ㅄ' -> {
                                temp = "ㅂ"
                                firstChar = 'ㅅ'
                            }
                        }
                        middleChar = inputChar
                        state = 2
                        composeResult()
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    else -> {
                        val temp = firstChar.toString()
                        state = 0
                        initChar()
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(temp + inputChar.toString(), null)
                    }
                }
            }
            2 -> { // 자음+모음이 들어온 상태(중성)
                when {
                    isInputCharFirstChar -> {
                        if (inputChar in arrayOf('ㅃ', 'ㄸ', 'ㅉ')) {
                            val temp = composedResult.toString()
                            initChar()
                            state = 1
                            firstChar = inputChar
                            composeResult()
                            return UpdatedChars(temp, inputChar.toString())
                        } else {
                            finalChar = inputChar
                            state = 3
                            composeResult()
                            return UpdatedChars(null, composedResult.toString())
                        }
                    }
                    isInputCharMiddleChar -> {
                        if (canBeDoubleVowelChar(middleChar, inputChar)) { //자음+이중모음 글자 완성
                            middleChar = doubleChar(middleChar, inputChar)
                            composeResult()
                            return UpdatedChars(null, composedResult.toString())
                        } else { //글자를 완성하고 다음 글자로 모음을 넘김 ex) 가ㅏ, 나ㅗ
                            val temp = composedResult.toString()
                            state = -1
                            initChar()
                            middleChar = inputChar
                            composeResult()
                            composedResult = nullChar
                            return UpdatedChars(temp, inputChar.toString())
                        }
                    }
                    else -> {
                        val temp = composedResult.toString()
                        state = 0
                        initChar()
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(temp + inputChar.toString(), null)
                    }
                }
            }
            3 -> { // 자음+모음+자음이 들어온 상태(종성)
                when {
                    isInputCharFirstChar -> {
                        if (canBeDoubleConsonantChar(finalChar, inputChar)) { //이중받침이 가능하다면
                            if (finalChar != inputChar) {
                                finalChar = doubleChar(finalChar, inputChar) //종성을 이중받침으로 설정
                                composeResult()
                                return UpdatedChars(null, composedResult.toString())
                            } else {
                                val temp = composedResult.toString()
                                state = 1
                                initChar()
                                firstChar = inputChar
                                composeResult()
                                composedResult = nullChar
                                return UpdatedChars(temp, inputChar.toString())
                            }
                        } else { //이중받침이 불가하다면 ex)간+ㄴ -> 간ㄴ
                            val temp = composedResult.toString()
                            state = 1
                            initChar()
                            firstChar = inputChar
                            composeResult()
                            composedResult = nullChar
                            return UpdatedChars(temp, inputChar.toString())
                        }
                    }
                    isInputCharMiddleChar -> {
                        var temp: String? = null
                        when (finalChar) {
                            'ㄳ' -> {
                                finalChar = 'ㄱ'
                                composeResult()
                                temp = composedResult.toString()
                                initChar()
                                state = 2
                                firstChar = 'ㅅ'
                            }
                            'ㄵ' -> {
                                finalChar = 'ㄴ'
                                composeResult()
                                temp = composedResult.toString()
                                initChar()
                                state = 2
                                firstChar = 'ㅈ'
                            }
                            'ㄶ' -> {
                                finalChar = 'ㄴ'
                                composeResult()
                                temp = composedResult.toString()
                                initChar()
                                state = 2
                                firstChar = 'ㅎ'
                            }
                            'ㄺ' -> {
                                finalChar = 'ㄹ'
                                composeResult()
                                temp = composedResult.toString()
                                initChar()
                                state = 2
                                firstChar = 'ㄱ'
                            }
                            'ㄻ' -> {
                                finalChar = 'ㄹ'
                                composeResult()
                                temp = composedResult.toString()
                                initChar()
                                state = 2
                                firstChar = 'ㅁ'
                            }
                            'ㄼ' -> {
                                finalChar = 'ㄹ'
                                composeResult()
                                temp = composedResult.toString()
                                initChar()
                                state = 2
                                firstChar = 'ㅂ'
                            }
                            'ㄽ' -> {
                                finalChar = 'ㄹ'
                                composeResult()
                                temp = composedResult.toString()
                                initChar()
                                state = 2
                                firstChar = 'ㅅ'
                            }
                            'ㄾ' -> {
                                finalChar = 'ㄹ'
                                composeResult()
                                temp = composedResult.toString()
                                initChar()
                                state = 2
                                firstChar = 'ㅌ'
                            }
                            'ㄿ' -> {
                                finalChar = 'ㄹ'
                                composeResult()
                                temp = composedResult.toString()
                                initChar()
                                state = 2
                                firstChar = 'ㅍ'
                            }
                            'ㅀ' -> {
                                finalChar = 'ㄹ'
                                composeResult()
                                temp = composedResult.toString()
                                initChar()
                                state = 2
                                firstChar = 'ㅎ'
                            }
                            'ㅄ' -> {
                                finalChar = 'ㅂ'
                                composeResult()
                                temp = composedResult.toString()
                                initChar()
                                state = 2
                                firstChar = 'ㅅ'
                            }
                            else -> {
                                val temp1 = finalChar //temp 값에 종성 저장
                                finalChar = nullChar // 간 -> 가
                                composeResult()
                                temp = composedResult.toString() //가+ㄴ+ㅏ
                                state = 2
                                firstChar = temp1 //원래 글자의 종성을 새 초성으로 재입력, ㄴ
                            }
                        }
                        middleChar = inputChar
                        composeResult()
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    else -> {
                        val temp = composedResult.toString()
                        state = 0
                        initChar()
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(temp + inputChar.toString(), null)
                    }
                }
            }
        }
        return UpdatedChars(null, null)
    } //한글 오토마타

    /** commited = null, composing = null -> 한 칸 지우고, 아무것도 띄우지 않음 == currentInputConnection.finishComposingText(), currentInputConnection.deleteSurround(1, 0)
     *  commited = null, composing != null -> 이전의 composing 글자를 새로운 composing 글자로 바꿈 == currentInputConnection.setComposingText(composing, 1)
     * **/
    fun delete(): UpdatedChars {
        when (state) {
            0 -> {
                return UpdatedChars(null, null)
            }
            1 -> {
                when (firstChar) {
                    'ㄲ', 'ㄳ' -> {
                        firstChar = 'ㄱ'
                        composeResult()
                        return UpdatedChars(null, firstChar.toString())
                    }
                    'ㄵ', 'ㄶ' -> {
                        firstChar = 'ㄴ'
                        composeResult()
                        return UpdatedChars(null, firstChar.toString())
                    }
                    'ㄸ' -> {
                        firstChar = 'ㄷ'
                        composeResult()
                        return UpdatedChars(null, firstChar.toString())
                    }
                    'ㄺ', 'ㄻ', 'ㄼ', 'ㄽ', 'ㄾ', 'ㄿ', 'ㅀ' -> {
                        firstChar = 'ㄹ'
                        composeResult()
                        return UpdatedChars(null, firstChar.toString())
                    }
                    'ㅃ', 'ㅄ' -> {
                        firstChar = 'ㅂ'
                        composeResult()
                        return UpdatedChars(null, firstChar.toString())
                    }
                    'ㅆ' -> {
                        firstChar = 'ㅅ'
                        composeResult()
                        return UpdatedChars(null, firstChar.toString())
                    }
                    'ㅉ' -> {
                        firstChar = 'ㅈ'
                        composeResult()
                        return UpdatedChars(null, firstChar.toString())
                    }
                    else -> {
                        state = 0
                        middleChar = nullChar
                        composeResult()
                        return UpdatedChars(null, null)
                    }
                }
            }
            -1 -> {
                when (middleChar) {
                    'ㅘ', 'ㅙ', 'ㅚ' -> {
                        middleChar = 'ㅗ'
                        composeResult()
                        return UpdatedChars(null, middleChar.toString())
                    }
                    'ㅝ', 'ㅞ', 'ㅟ' -> {
                        middleChar = 'ㅜ'
                        composeResult()
                        return UpdatedChars(null, middleChar.toString())
                    }
                    'ㅢ' -> {
                        middleChar = 'ㅡ'
                        composeResult()
                        return UpdatedChars(null, middleChar.toString())
                    }
                    else -> {
                        state = 0
                        middleChar = nullChar
                        composeResult()
                        return UpdatedChars(null, null)
                    }
                }
            }
            2 -> {
                when (middleChar) {
                    'ㅘ', 'ㅙ', 'ㅚ' -> {
                        middleChar = 'ㅗ'
                        composeResult()
                        return UpdatedChars(null, composedResult.toString())
                    }
                    'ㅝ', 'ㅞ', 'ㅟ' -> {
                        middleChar = 'ㅜ'
                        composeResult()
                        return UpdatedChars(null, composedResult.toString())
                    }
                    'ㅢ' -> {
                        middleChar = 'ㅡ'
                        composeResult()
                        return UpdatedChars(null, composedResult.toString())
                    }
                    else -> {
                        state = 1
                        middleChar = nullChar
                        composeResult()
                        return UpdatedChars(null, firstChar.toString())
                    }
                }
            }
            3 -> {
                when (finalChar) {
                    'ㄲ', 'ㄳ' -> {
                        finalChar = 'ㄱ'
                        composeResult()
                        return UpdatedChars(null, composedResult.toString())
                    }
                    'ㄵ', 'ㄶ' -> {
                        finalChar = 'ㄴ'
                        composeResult()
                        return UpdatedChars(null, composedResult.toString())
                    }
                    'ㄺ', 'ㄻ', 'ㄼ', 'ㄽ', 'ㄾ', 'ㄿ', 'ㅀ' -> {
                        finalChar = 'ㄹ'
                        composeResult()
                        return UpdatedChars(null, composedResult.toString())
                    }
                    'ㅄ' -> {
                        finalChar = 'ㅂ'
                        composeResult()
                        return UpdatedChars(null, composedResult.toString())
                    }
                    'ㅆ' -> {
                        finalChar = 'ㅅ'
                        composeResult()
                        return UpdatedChars(null, composedResult.toString())
                    }
                    else -> {
                        finalChar = nullChar
                        state = 2
                        composeResult()
                        return UpdatedChars(null, composedResult.toString())
                    }
                }
            }
        }
        return UpdatedChars(null, null)
    } //delete 발생

    private fun canBeDoubleConsonantChar(finalChar: Char, c: Char): Boolean { //이중자음이 가능한지 판별
        when (finalChar) {
            'ㄱ' -> {
                return when (c) {
                    'ㄱ', 'ㅅ' -> true
                    else -> false
                }
            }
            'ㄴ' -> {
                return when (c) {
                    'ㅈ', 'ㅎ' -> true
                    else -> false
                }
            }
            'ㄹ' -> {
                return when (c) {
                    'ㄱ', 'ㅁ', 'ㅂ', 'ㅅ', 'ㅌ', 'ㅍ', 'ㅎ' -> true
                    else -> false
                }
            }
            'ㅂ' -> {
                return when (c) {
                    'ㅅ' -> true
                    else -> false
                }
            }
            'ㅅ' -> {
                return when (c) {
                    'ㅅ' -> true
                    else -> false
                }
            }
            else -> return false
        }
    } //이중자음 가능여부 체크

    private fun canBeDoubleVowelChar(middleChar: Char, c: Char): Boolean { //이중모음이 가능한지 판별
        when (middleChar) {
            'ㅗ' -> {
                return when (c) {
                    'ㅏ', 'ㅐ', 'ㅣ' -> true
                    else -> false
                }
            }
            'ㅜ' -> {
                return when (c) {
                    'ㅓ', 'ㅔ', 'ㅣ' -> true
                    else -> false
                }
            }
            'ㅡ' -> {
                return when (c) {
                    'ㅣ' -> true
                    else -> false
                }
            }
            else -> return false
        }
    } //이중모음 가능여부 체크

    private fun doubleChar(first: Char, second: Char): Char {
        when {
            first == 'ㄱ' && second == 'ㄱ' -> {
                return 'ㄲ'
            }
            first == 'ㄱ' && second == 'ㅅ' -> {
                return 'ㄳ'
            }
            first == 'ㄴ' && second == 'ㅈ' -> {
                return 'ㄵ'
            }
            first == 'ㄴ' && second == 'ㅎ' -> {
                return 'ㄶ'
            }
            first == 'ㄷ' && second == 'ㄷ' -> {
                return 'ㄸ'
            }
            first == 'ㄹ' && second == 'ㄱ' -> {
                return 'ㄺ'
            }
            first == 'ㄹ' && second == 'ㅁ' -> {
                return 'ㄻ'
            }
            first == 'ㄹ' && second == 'ㅂ' -> {
                return 'ㄼ'
            }
            first == 'ㄹ' && second == 'ㅅ' -> {
                return 'ㄽ'
            }
            first == 'ㄹ' && second == 'ㅌ' -> {
                return 'ㄾ'
            }
            first == 'ㄹ' && second == 'ㅍ' -> {
                return 'ㄿ'
            }
            first == 'ㄹ' && second == 'ㅎ' -> {
                return 'ㅀ'
            }
            first == 'ㅂ' && second == 'ㅂ' -> {
                return 'ㅃ'
            }
            first == 'ㅂ' && second == 'ㅅ' -> {
                return 'ㅄ'
            }
            first == 'ㅅ' && second == 'ㅅ' -> {
                return 'ㅆ'
            }
            first == 'ㅈ' && second == 'ㅈ' -> {
                return 'ㅉ'
            }
            first == 'ㅗ' && second == 'ㅏ' -> {
                return 'ㅘ'
            }
            first == 'ㅗ' && second == 'ㅐ' -> {
                return 'ㅙ'
            }
            first == 'ㅗ' && second == 'ㅣ' -> {
                return 'ㅚ'
            }
            first == 'ㅜ' && second == 'ㅓ' -> {
                return 'ㅝ'
            }
            first == 'ㅜ' && second == 'ㅔ' -> {
                return 'ㅞ'
            }
            first == 'ㅜ' && second == 'ㅣ' -> {
                return 'ㅟ'
            }
            first == 'ㅡ' && second == 'ㅣ' -> {
                return 'ㅢ'
            }
            else -> return nullChar
        }
    }
}