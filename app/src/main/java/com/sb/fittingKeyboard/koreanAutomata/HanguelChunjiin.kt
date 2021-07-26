package com.sb.fittingKeyboard.koreanAutomata

object HanguelChunjiin: Automata() {
    private val rrr: Array<Char> = arrayOf('ㄱ', 'ㅋ', 'ㄲ')
    private val ss: Array<Char> = arrayOf('ㄴ', 'ㄹ')
    private val ee: Array<Char> = arrayOf('ㄷ', 'ㅌ')
    private val qqq: Array<Char> = arrayOf('ㅂ', 'ㅍ', 'ㅃ')
    private val ttt: Array<Char> = arrayOf('ㅅ', 'ㅎ', 'ㅆ')
    private val www: Array<Char> = arrayOf('ㅈ', 'ㅊ', 'ㅉ')
    private val dd: Array<Char> = arrayOf('ㅇ', 'ㅁ')

    fun composeChar(inputChar: Char): UpdatedChars {
        when (state) {
            0 -> when {
                inputChar in rrr -> {
                    firstChar = inputChar
                    firstSubChar = nullChar
                    middleChar = nullChar
                    finalChar = nullChar
                    finalSubChar = nullChar
                    composeResult()
                    composedResult = nullChar
                    state = 1
                    return UpdatedChars(null, firstChar.toString())
                }
                inputChar in ss -> {
                    firstChar = inputChar
                    firstSubChar = nullChar
                    middleChar = nullChar
                    finalChar = nullChar
                    finalSubChar = nullChar
                    composeResult()
                    composedResult = nullChar
                    state = 1
                    return UpdatedChars(null, firstChar.toString())
                }
                inputChar in ee -> {
                    firstChar = inputChar
                    firstSubChar = nullChar
                    middleChar = nullChar
                    finalChar = nullChar
                    finalSubChar = nullChar
                    composeResult()
                    composedResult = nullChar
                    state = 1
                    return UpdatedChars(null, firstChar.toString())
                }
                inputChar in qqq -> {
                    firstChar = inputChar
                    firstSubChar = nullChar
                    middleChar = nullChar
                    finalChar = nullChar
                    finalSubChar = nullChar
                    composeResult()
                    composedResult = nullChar
                    state = 1
                    return UpdatedChars(null, firstChar.toString())
                }
                inputChar in ttt -> {
                    firstChar = inputChar
                    firstSubChar = nullChar
                    middleChar = nullChar
                    finalChar = nullChar
                    finalSubChar = nullChar
                    composeResult()
                    composedResult = nullChar
                    state = 1
                    return UpdatedChars(null, firstChar.toString())
                }
                inputChar in www -> {
                    firstChar = inputChar
                    firstSubChar = nullChar
                    middleChar = nullChar
                    finalChar = nullChar
                    finalSubChar = nullChar
                    composeResult()
                    composedResult = nullChar
                    state = 1
                    return UpdatedChars(null, firstChar.toString())
                }
                inputChar in dd -> {
                    firstChar = inputChar
                    firstSubChar = nullChar
                    middleChar = nullChar
                    finalChar = nullChar
                    finalSubChar = nullChar
                    composeResult()
                    composedResult = nullChar
                    state = 1
                    return UpdatedChars(null, firstChar.toString())
                }
                inputChar == 'ㅣ' -> {
                    firstChar = nullChar
                    firstSubChar = nullChar
                    middleChar = inputChar
                    finalChar = nullChar
                    finalSubChar = nullChar
                    composeResult()
                    composedResult = nullChar
                    state = -1
                    return UpdatedChars(null, middleChar.toString())
                }
                inputChar == 'ᆞ' -> {
                    firstChar = nullChar
                    firstSubChar = nullChar
                    middleChar = inputChar
                    finalChar = nullChar
                    finalSubChar = nullChar
                    composeResult()
                    composedResult = nullChar
                    state = -1
                    return UpdatedChars(null, middleChar.toString())
                }
                inputChar == 'ㅡ' -> {
                    firstChar = nullChar
                    firstSubChar = nullChar
                    middleChar = inputChar
                    finalChar = nullChar
                    finalSubChar = nullChar
                    composeResult()
                    composedResult = nullChar
                    state = -1
                    return UpdatedChars(null, middleChar.toString())
                }
                else -> {
                    composeResult()
                    composedResult = nullChar
                    state = 0
                    return UpdatedChars(inputChar.toString(), null)
                }
            }
            -1 -> when (middleChar) {
                'ㅣ' -> when (inputChar) {
                    'ㅣ' -> {
                        val temp = middleChar.toString()
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(temp, middleChar.toString())
                    }
                    'ᆞ' -> {
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = 'ㅏ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(null, middleChar.toString())
                    }
                    'ㅡ' -> {
                        val temp = middleChar.toString()
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(temp, middleChar.toString())
                    }
                    else -> {
                        val temp = middleChar.toString()
                        state = 1
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ᆞ' -> when (inputChar) {
                    'ㅣ' -> {
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = 'ㅓ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(null, middleChar.toString())
                    }
                    'ᆞ' -> {
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = 'ᆢ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(null, middleChar.toString())
                    }
                    'ㅡ' -> {
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = 'ㅗ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(null, middleChar.toString())
                    }
                    else -> {
                        val temp = middleChar.toString()
                        state = 1
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(null, firstChar.toString())
                    }
                }
                'ㅡ' -> when (inputChar) {
                    'ㅣ' -> {
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = 'ㅢ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(null, middleChar.toString())
                    }
                    'ᆞ' -> {
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = 'ㅜ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(null, middleChar.toString())
                    }
                    'ㅡ' -> {
                        val temp = middleChar.toString()
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(temp, middleChar.toString())
                    }
                    else -> {
                        val temp = middleChar.toString()
                        state = 1
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(null, firstChar.toString())
                    }
                }
                'ᆢ' -> when (inputChar) {
                    'ㅣ' -> {
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = 'ㅕ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(null, middleChar.toString())
                    }
                    'ᆞ' -> {
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = 'ᆞ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(null, middleChar.toString())
                    }
                    'ㅡ' -> {
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = 'ㅛ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(null, middleChar.toString())
                    }
                    else -> {
                        val temp = middleChar.toString()
                        state = 1
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㅏ' -> when (inputChar) {
                    'ㅣ' -> {
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = 'ㅐ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(null, middleChar.toString())
                    }
                    'ᆞ' -> {
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = 'ㅑ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(null, middleChar.toString())
                    }
                    'ㅡ' -> {
                        val temp = middleChar.toString()
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(temp, middleChar.toString())
                    }
                    else -> {
                        val temp = middleChar.toString()
                        state = 1
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㅐ' -> when (inputChar) {
                    'ㅣ' -> {
                        val temp = middleChar.toString()
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(temp, middleChar.toString())
                    }
                    'ᆞ' -> {
                        val temp = middleChar.toString()
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(temp, middleChar.toString())
                    }
                    'ㅡ' -> {
                        val temp = middleChar.toString()
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(temp, middleChar.toString())
                    }
                    else -> {
                        val temp = middleChar.toString()
                        state = 1
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㅑ' -> when (inputChar) {
                    'ㅣ' -> {
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = 'ㅒ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(null, middleChar.toString())
                    }
                    'ᆞ' -> {
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = 'ㅏ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(null, middleChar.toString())
                    }
                    'ㅡ' -> {
                        val temp = middleChar.toString()
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(temp, middleChar.toString())
                    }
                    else -> {
                        val temp = middleChar.toString()
                        state = 1
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㅒ', 'ㅖ', 'ㅙ', 'ㅛ', 'ㅞ', 'ㅟ', 'ㅢ' -> when (inputChar) {
                    'ㅣ', 'ᆞ', 'ㅡ' -> {
                        val temp = middleChar.toString()
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(temp, middleChar.toString())
                    }
                    else -> {
                        val temp = middleChar.toString()
                        state = 1
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㅓ' -> when (inputChar) {
                    'ㅣ' -> {
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = 'ㅔ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(null, middleChar.toString())
                    }
                    'ᆞ' -> {
                        val temp = middleChar.toString()
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(temp, middleChar.toString())
                    }
                    'ㅡ' -> {
                        val temp = middleChar.toString()
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(temp, middleChar.toString())
                    }
                    else -> {
                        val temp = middleChar.toString()
                        state = 1
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㅔ' -> when (inputChar) {
                    'ㅣ' -> {
                        val temp = middleChar.toString()
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(temp, middleChar.toString())
                    }
                    'ᆞ' -> {
                        val temp = middleChar.toString()
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(temp, middleChar.toString())
                    }
                    'ㅡ' -> {
                        val temp = middleChar.toString()
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(temp, middleChar.toString())
                    }
                    else -> {
                        val temp = middleChar.toString()
                        state = 1
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㅕ' -> when (inputChar) {
                    'ㅣ' -> {
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = 'ㅖ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(null, middleChar.toString())
                    }
                    'ᆞ' -> {
                        val temp = middleChar.toString()
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(temp, middleChar.toString())
                    }
                    'ㅡ' -> {
                        val temp = middleChar.toString()
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(temp, middleChar.toString())
                    }
                    else -> {
                        val temp = middleChar.toString()
                        state = 1
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㅗ' -> when (inputChar) {
                    'ㅣ' -> {
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = 'ㅚ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(null, middleChar.toString())
                    }
                    'ᆞ' -> {
                        val temp = middleChar.toString()
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(temp, middleChar.toString())
                    }
                    'ㅡ' -> {
                        val temp = middleChar.toString()
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(temp, middleChar.toString())
                    }
                    else -> {
                        val temp = middleChar.toString()
                        state = 1
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㅘ' -> when (inputChar) {
                    'ㅣ' -> {
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = 'ㅙ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(null, middleChar.toString())
                    }
                    'ᆞ' -> {
                        val temp = middleChar.toString()
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(temp, middleChar.toString())
                    }
                    'ㅡ' -> {
                        val temp = middleChar.toString()
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(temp, middleChar.toString())
                    }
                    else -> {
                        val temp = middleChar.toString()
                        state = 1
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㅚ' -> when (inputChar) {
                    'ㅣ' -> {
                        val temp = middleChar.toString()
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(temp, middleChar.toString())
                    }
                    'ᆞ' -> {
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = 'ㅘ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(null, middleChar.toString())
                    }
                    'ㅡ' -> {
                        val temp = middleChar.toString()
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(temp, middleChar.toString())
                    }
                    else -> {
                        val temp = middleChar.toString()
                        state = 1
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㅜ' -> when (inputChar) {
                    'ㅣ' -> {
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = 'ㅟ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(null, middleChar.toString())
                    }
                    'ᆞ' -> {
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = 'ㅠ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(null, middleChar.toString())
                    }
                    'ㅡ' -> {
                        val temp = middleChar.toString()
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(temp, middleChar.toString())
                    }
                    else -> {
                        val temp = middleChar.toString()
                        state = 1
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㅝ' -> when (inputChar) {
                    'ㅣ' -> {
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = 'ㅞ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(null, middleChar.toString())
                    }
                    'ᆞ' -> {
                        val temp = middleChar.toString()
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(temp, middleChar.toString())
                    }
                    'ㅡ' -> {
                        val temp = middleChar.toString()
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(temp, middleChar.toString())
                    }
                    else -> {
                        val temp = middleChar.toString()
                        state = 1
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㅠ' -> when (inputChar) {
                    'ㅣ' -> {
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = 'ㅝ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(null, middleChar.toString())
                    }
                    'ᆞ' -> {
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = 'ㅜ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(null, middleChar.toString())
                    }
                    'ㅡ' -> {
                        val temp = middleChar.toString()
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(temp, middleChar.toString())
                    }
                    else -> {
                        val temp = middleChar.toString()
                        state = 1
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                else -> {
                    initChar()
                    state = 0
                    composedResult = nullChar
                    return UpdatedChars(inputChar.toString(), null)
                }
            }
            1 -> when (firstChar) {
                'ㄱ' -> when (inputChar) {
                    'ㄱ' -> {
                        firstChar = 'ㅋ'
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(null, firstChar.toString())
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
                                return UpdatedChars(null,
                                    firstChar.toString() + firstSubChar.toString()
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
                                return UpdatedChars(null, firstChar.toString())
                            }
                        }
                    }
                    'ㅣ', 'ㅡ' -> {
                        state = 2
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        return UpdatedChars(null, composedResult.toString())
                    }
                    'ᆞ' -> {
                        state = 2
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(null,
                            firstChar.toString() + middleChar.toString()
                        )
                    }
                    else -> {
                        val temp = firstChar.toString()
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㄲ' -> when (inputChar) {
                    'ㄱ' -> {
                        firstChar = 'ㄱ'
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(null, firstChar.toString())
                    }
                    'ㅣ', 'ㅡ' -> {
                        state = 2
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ᆞ' -> {
                        state = 2
                        firstSubChar = nullChar
                        middleChar = inputChar
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
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㄳ' -> when (inputChar) {
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
                        middleChar = inputChar
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
                        middleChar = inputChar
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
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㄴ' -> when (inputChar) {
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
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ᆞ' -> {
                        state = 2
                        firstSubChar = nullChar
                        middleChar = inputChar
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
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㄵ' -> when (inputChar) {
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
                        middleChar = inputChar
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
                        middleChar = inputChar
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
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㄶ' -> when (inputChar) {
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
                        middleChar = inputChar
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
                        middleChar = inputChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(
                            firstChar.toString() + middleChar.toString(),
                            1
                        )
                    }
                    else -> {
                        inputConnection.commitText(firstChar.toString(), 1)
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㄷ' -> when (inputChar) {
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
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ᆞ' -> {
                        state = 2
                        firstSubChar = nullChar
                        middleChar = inputChar
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
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㄸ' -> when (inputChar) {
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
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ᆞ' -> {
                        state = 2
                        firstSubChar = nullChar
                        middleChar = inputChar
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
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㄹ' -> when (inputChar) {
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
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ᆞ' -> {
                        state = 2
                        firstSubChar = nullChar
                        middleChar = inputChar
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
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㄺ' -> when (inputChar) {
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
                        middleChar = inputChar
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
                        middleChar = inputChar
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
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㄻ' -> when (inputChar) {
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
                        middleChar = inputChar
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
                        middleChar = inputChar
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
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㄼ' -> when (inputChar) {
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
                        middleChar = inputChar
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
                        middleChar = inputChar
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
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㄽ' -> when (inputChar) {
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
                        middleChar = inputChar
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
                        middleChar = inputChar
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
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㅀ' -> when (inputChar) {
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
                        middleChar = inputChar
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
                        middleChar = inputChar
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
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㅁ' -> when (inputChar) {
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
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ᆞ' -> {
                        state = 2
                        firstSubChar = nullChar
                        middleChar = inputChar
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
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㅂ' -> when (inputChar) {
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
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ᆞ' -> {
                        state = 2
                        firstSubChar = nullChar
                        middleChar = inputChar
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
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㅃ' -> when (inputChar) {
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
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ᆞ' -> {
                        state = 2
                        firstSubChar = nullChar
                        middleChar = inputChar
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
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㅄ' -> when (inputChar) {
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
                        middleChar = inputChar
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
                        middleChar = inputChar
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
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㅅ' -> when (inputChar) {
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
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ᆞ' -> {
                        state = 2
                        firstSubChar = nullChar
                        middleChar = inputChar
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
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㅆ' -> when (inputChar) {
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
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ᆞ' -> {
                        state = 2
                        firstSubChar = nullChar
                        middleChar = inputChar
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
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㅇ' -> when (inputChar) {
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
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ᆞ' -> {
                        state = 2
                        firstSubChar = nullChar
                        middleChar = inputChar
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
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㅈ' -> when (inputChar) {
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
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ᆞ' -> {
                        state = 2
                        firstSubChar = nullChar
                        middleChar = inputChar
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
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㅉ' -> when (inputChar) {
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
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ᆞ' -> {
                        state = 2
                        firstSubChar = nullChar
                        middleChar = inputChar
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
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㅊ' -> when (inputChar) {
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
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ᆞ' -> {
                        state = 2
                        firstSubChar = nullChar
                        middleChar = inputChar
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
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㅋ' -> when (inputChar) {
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
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ᆞ' -> {
                        state = 2
                        firstSubChar = nullChar
                        middleChar = inputChar
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
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㅌ' -> when (inputChar) {
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
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ᆞ' -> {
                        state = 2
                        firstSubChar = nullChar
                        middleChar = inputChar
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
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㅍ' -> when (inputChar) {
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
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ᆞ' -> {
                        state = 2
                        firstSubChar = nullChar
                        middleChar = inputChar
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
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㅎ' -> when (inputChar) {
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
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                    'ᆞ' -> {
                        state = 2
                        firstSubChar = nullChar
                        middleChar = inputChar
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
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                else -> {
                    initChar()
                    state = 0
                    composedResult = nullChar
                    inputConnection.commitText(inputChar.toString(), 1)
                }
            }
            2 -> when (middleChar) {
                'ㅐ', 'ㅒ', 'ㅔ', 'ㅖ', 'ㅙ', 'ㅛ', 'ㅞ', 'ㅟ', 'ㅢ' -> when (inputChar) {
                    'ㅣ', 'ᆞ', 'ㅡ' -> {
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = -1
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    else -> {
                        state = 3
                        firstSubChar = nullChar
                        finalChar = inputChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                }
                'ㅏ' -> when (inputChar) {
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
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    else -> {
                        state = 3
                        firstSubChar = nullChar
                        finalChar = inputChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                }
                'ㅑ' -> when (inputChar) {
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
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    else -> {
                        state = 3
                        firstSubChar = nullChar
                        finalChar = inputChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                }
                'ㅓ' -> when (inputChar) {
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
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    else -> {
                        state = 3
                        firstSubChar = nullChar
                        finalChar = inputChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                }
                'ㅕ' -> when (inputChar) {
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
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    else -> {
                        state = 3
                        firstSubChar = nullChar
                        finalChar = inputChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                }
                'ㅗ' -> when (inputChar) {
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
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    else -> {
                        state = 3
                        firstSubChar = nullChar
                        finalChar = inputChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                }
                'ㅘ' -> when (inputChar) {
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
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    else -> {
                        state = 3
                        firstSubChar = nullChar
                        finalChar = inputChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                }
                'ㅚ' -> when (inputChar) {
                    'ㅣ', 'ㅡ' -> {
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = -1
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = inputChar
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
                        finalChar = inputChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                }
                'ㅜ' -> when (inputChar) {
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
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    else -> {
                        state = 3
                        firstSubChar = nullChar
                        finalChar = inputChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                }
                'ㅝ' -> when (inputChar) {
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
                        middleChar = inputChar
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
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    else -> {
                        state = 3
                        firstSubChar = nullChar
                        finalChar = inputChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                }
                'ㅠ' -> when (inputChar) {
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
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    else -> {
                        state = 3
                        firstSubChar = nullChar
                        finalChar = inputChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                }
                'ㅡ' -> when (inputChar) {
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
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    else -> {
                        state = 3
                        firstSubChar = nullChar
                        finalChar = inputChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                }
                'ㅣ' -> when (inputChar) {
                    'ㅣ' -> {
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = -1
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = inputChar
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
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(middleChar.toString(), 1)
                    }
                    else -> {
                        state = 3
                        firstSubChar = nullChar
                        finalChar = inputChar
                        finalSubChar = nullChar
                        composeResult()
                        inputConnection.setComposingText(composedResult.toString(), 1)
                    }
                }
                'ᆞ' -> when (inputChar) {
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
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ᆢ' -> when (inputChar) {
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
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                else -> {
                    initChar()
                    state = 0
                    composedResult = nullChar
                    inputConnection.commitText(inputChar.toString(), 1)
                }
            }
            3 -> when (finalChar) {
                'ㄱ' -> when (inputChar) {
                    'ㅣ', 'ㅡ' -> {
                        if (finalSubChar != nullChar) {
                            inputConnection.commitText(composedResult.toString(), 1)
                            state = 2
                            firstChar = finalSubChar
                            firstSubChar = nullChar
                            middleChar = inputChar
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
                            middleChar = inputChar
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
                            middleChar = inputChar
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
                            middleChar = inputChar
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
                        firstChar = inputChar
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
                'ㄲ' -> when (inputChar) {
                    'ㅣ', 'ㅡ' -> {
                        finalChar = nullChar
                        composeResult()
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 2
                        firstChar = 'ㄲ'
                        firstSubChar = nullChar
                        middleChar = inputChar
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
                        middleChar = inputChar
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
                        firstChar = inputChar
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
                'ㄳ' -> when (inputChar) {
                    'ㅣ', 'ㅡ' -> {
                        finalChar = 'ㄱ'
                        composeResult()
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 2
                        firstChar = 'ㅅ'
                        firstSubChar = nullChar
                        middleChar = inputChar
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
                        middleChar = inputChar
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
                        firstChar = inputChar
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
                'ㄴ' -> when (inputChar) {
                    'ㅣ', 'ㅡ' -> {
                        if (finalSubChar != nullChar) {
                            inputConnection.commitText(composedResult.toString(), 1)
                            state = 2
                            firstChar = finalSubChar
                            firstSubChar = nullChar
                            middleChar = inputChar
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
                            middleChar = inputChar
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
                            middleChar = inputChar
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
                            middleChar = inputChar
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
                        firstChar = inputChar
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
                'ㄵ' -> when (inputChar) {
                    'ㅣ', 'ㅡ' -> {
                        finalChar = 'ㄴ'
                        composeResult()
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 2
                        firstChar = 'ㅈ'
                        firstSubChar = nullChar
                        middleChar = inputChar
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
                        middleChar = inputChar
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
                        firstChar = inputChar
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
                'ㄶ' -> when (inputChar) {
                    'ㅣ', 'ㅡ' -> {
                        finalChar = 'ㄴ'
                        composeResult()
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 2
                        firstChar = 'ㅎ'
                        firstSubChar = nullChar
                        middleChar = inputChar
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
                        middleChar = inputChar
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
                        firstChar = inputChar
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
                'ㄷ' -> when (inputChar) {
                    'ㅣ', 'ㅡ' -> {
                        finalChar = nullChar
                        composeResult()
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 2
                        firstChar = 'ㄷ'
                        firstSubChar = nullChar
                        middleChar = inputChar
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
                        middleChar = inputChar
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
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㄹ' -> when (inputChar) {
                    'ㅣ', 'ㅡ' -> {
                        if (finalSubChar != nullChar) {
                            inputConnection.commitText(composedResult.toString(), 1)
                            state = 2
                            firstChar = finalSubChar
                            firstSubChar = nullChar
                            middleChar = inputChar
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
                            middleChar = inputChar
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
                            middleChar = inputChar
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
                            middleChar = inputChar
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
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        inputConnection.setComposingText(firstChar.toString(), 1)
                    }
                }
                'ㄺ' -> when (inputChar) {
                    'ㅣ', 'ㅡ' -> {
                        finalChar = 'ㄹ'
                        composeResult()
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 2
                        firstChar = 'ㄱ'
                        firstSubChar = nullChar
                        middleChar = inputChar
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
                        middleChar = inputChar
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
                        firstChar = inputChar
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
                'ㄻ' -> when (inputChar) {
                    'ㅣ', 'ㅡ' -> {
                        finalChar = 'ㄹ'
                        composeResult()
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 2
                        firstChar = 'ㅁ'
                        firstSubChar = nullChar
                        middleChar = inputChar
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
                        middleChar = inputChar
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
                        firstChar = inputChar
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
                'ㄼ' -> when (inputChar) {
                    'ㅣ', 'ㅡ' -> {
                        finalChar = 'ㄹ'
                        composeResult()
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 2
                        firstChar = 'ㅂ'
                        firstSubChar = nullChar
                        middleChar = inputChar
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
                        middleChar = inputChar
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
                        firstChar = inputChar
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
                'ㄽ' -> when (inputChar) {
                    'ㅣ', 'ㅡ' -> {
                        finalChar = 'ㄹ'
                        composeResult()
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 2
                        firstChar = 'ㅅ'
                        firstSubChar = nullChar
                        middleChar = inputChar
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
                        middleChar = inputChar
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
                        firstChar = inputChar
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
                'ㄿ' -> when (inputChar) {
                    'ㅣ', 'ㅡ' -> {
                        finalChar = 'ㄹ'
                        composeResult()
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 2
                        firstChar = 'ㅍ'
                        firstSubChar = nullChar
                        middleChar = inputChar
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
                        middleChar = inputChar
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
                        firstChar = inputChar
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
                'ㅀ' -> when (inputChar) {
                    'ㅣ', 'ㅡ' -> {
                        finalChar = 'ㄹ'
                        composeResult()
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 2
                        firstChar = 'ㅎ'
                        firstSubChar = nullChar
                        middleChar = inputChar
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
                        middleChar = inputChar
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
                        firstChar = inputChar
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
                'ㅁ' -> when (inputChar) {
                    'ㅣ', 'ㅡ' -> {
                        finalChar = nullChar
                        composeResult()
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 2
                        firstChar = 'ㅁ'
                        firstSubChar = nullChar
                        middleChar = inputChar
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
                        middleChar = inputChar
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
                        firstChar = inputChar
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
                'ㅂ' -> when (inputChar) {
                    'ㅣ', 'ㅡ' -> {
                        if (finalSubChar != nullChar) {
                            inputConnection.commitText(composedResult.toString(), 1)
                            state = 2
                            firstChar = finalSubChar
                            firstSubChar = nullChar
                            middleChar = inputChar
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
                            middleChar = inputChar
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
                            middleChar = inputChar
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
                            middleChar = inputChar
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
                        firstChar = inputChar
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
                'ㅄ' -> when (inputChar) {
                    'ㅣ', 'ㅡ' -> {
                        finalChar = 'ㅂ'
                        composeResult()
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 2
                        firstChar = 'ㅅ'
                        firstSubChar = nullChar
                        middleChar = inputChar
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
                        middleChar = inputChar
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
                        firstChar = inputChar
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
                'ㅅ' -> when (inputChar) {
                    'ㅣ', 'ㅡ' -> {
                        finalChar = nullChar
                        composeResult()
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 2
                        firstChar = 'ㅅ'
                        firstSubChar = nullChar
                        middleChar = inputChar
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
                        middleChar = inputChar
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
                        firstChar = inputChar
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
                'ㅆ' -> when (inputChar) {
                    'ㅣ', 'ㅡ' -> {
                        finalChar = nullChar
                        composeResult()
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 2
                        firstChar = 'ㅆ'
                        firstSubChar = nullChar
                        middleChar = inputChar
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
                        middleChar = inputChar
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
                        firstChar = inputChar
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
                'ㅇ' -> when (inputChar) {
                    'ㅣ', 'ㅡ' -> {
                        finalChar = nullChar
                        composeResult()
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 2
                        firstChar = 'ㅇ'
                        firstSubChar = nullChar
                        middleChar = inputChar
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
                        middleChar = inputChar
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
                        firstChar = inputChar
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
                'ㅈ' -> when (inputChar) {
                    'ㅣ', 'ㅡ' -> {
                        finalChar = nullChar
                        composeResult()
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 2
                        firstChar = 'ㅈ'
                        firstSubChar = nullChar
                        middleChar = inputChar
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
                        middleChar = inputChar
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
                        firstChar = inputChar
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
                'ㅊ' -> when (inputChar) {
                    'ㅣ', 'ㅡ' -> {
                        finalChar = nullChar
                        composeResult()
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 2
                        firstChar = 'ㅊ'
                        firstSubChar = nullChar
                        middleChar = inputChar
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
                        middleChar = inputChar
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
                        firstChar = inputChar
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
                'ㅋ' -> when (inputChar) {
                    'ㅣ', 'ㅡ' -> {
                        finalChar = nullChar
                        composeResult()
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 2
                        firstChar = 'ㅋ'
                        firstSubChar = nullChar
                        middleChar = inputChar
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
                        middleChar = inputChar
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
                        firstChar = inputChar
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
                'ㅌ' -> when (inputChar) {
                    'ㅣ', 'ㅡ' -> {
                        finalChar = nullChar
                        composeResult()
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 2
                        firstChar = 'ㅌ'
                        firstSubChar = nullChar
                        middleChar = inputChar
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
                        middleChar = inputChar
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
                        firstChar = inputChar
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
                'ㅍ' -> when (inputChar) {
                    'ㅣ', 'ㅡ' -> {
                        finalChar = nullChar
                        composeResult()
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 2
                        firstChar = 'ㅍ'
                        firstSubChar = nullChar
                        middleChar = inputChar
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
                        middleChar = inputChar
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
                        firstChar = inputChar
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
                'ㅎ' -> when (inputChar) {
                    'ㅣ', 'ㅡ' -> {
                        finalChar = nullChar
                        composeResult()
                        inputConnection.commitText(composedResult.toString(), 1)
                        state = 2
                        firstChar = 'ㅎ'
                        firstSubChar = nullChar
                        middleChar = inputChar
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
                        middleChar = inputChar
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
                        firstChar = inputChar
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
                        when (inputChar) {
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
                                middleChar = inputChar
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
                                firstChar = inputChar
                                middleChar = nullChar
                                finalChar = nullChar
                                firstSubChar = nullChar
                                finalSubChar = nullChar
                                inputConnection.setComposingText(firstChar.toString(), 1)
                            }
                        }
                    }
                    'ㅃ' -> {
                        when (inputChar) {
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
                                middleChar = inputChar
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
                                firstChar = inputChar
                                middleChar = nullChar
                                finalChar = nullChar
                                firstSubChar = nullChar
                                finalSubChar = nullChar
                                inputConnection.setComposingText(firstChar.toString(), 1)
                            }
                        }
                    }
                    'ㅉ' -> {
                        when (inputChar) {
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
                                middleChar = inputChar
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
                                firstChar = inputChar
                                middleChar = nullChar
                                finalChar = nullChar
                                firstSubChar = nullChar
                                finalSubChar = nullChar
                                inputConnection.setComposingText(firstChar.toString(), 1)
                            }
                        }
                    }
                }
                else -> {
                    initChar()
                    state = 0
                    composedResult = nullChar
                    inputConnection.commitText(inputChar.toString(), 1)
                }
            }
        }
    }

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
    }
}