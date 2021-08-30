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
            0 -> when (inputChar) {
                in rrr -> {
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
                in ss -> {
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
                in ee -> {
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
                in qqq -> {
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
                in ttt -> {
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
                in www -> {
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
                in dd -> {
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
                'ㅣ' -> {
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
                'ᆞ' -> {
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
                'ㅡ' -> {
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
                        return UpdatedChars(temp, firstChar.toString())
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
                        return UpdatedChars(temp, firstChar.toString())
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
                'ㄳ' -> when (inputChar) {
                    'ㅅ' -> {
                        firstChar = 'ㄱ'
                        firstSubChar = 'ㅎ'
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(null,
                            firstChar.toString() + firstSubChar.toString()
                        )
                    }
                    'ㅣ', 'ㅡ' -> {
                        val temp = "ㄱ"
                        state = 2
                        firstChar = 'ㅅ'
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    'ᆞ' -> {
                        val temp = "ㄱ"
                        state = 2
                        firstChar = 'ㅅ'
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(temp,
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
                'ㄴ' -> when (inputChar) {
                    'ㄴ' -> {
                        firstChar = 'ㄹ'
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
                            'ㅅ' -> {
                                firstChar = 'ㄶ'
                                firstSubChar = nullChar
                                middleChar = nullChar
                                finalChar = nullChar
                                finalSubChar = nullChar
                                composeResult()
                                composedResult = nullChar
                                return UpdatedChars(null, firstChar.toString())
                            }
                            'ㅆ', nullChar -> {
                                firstChar = 'ㄴ'
                                firstSubChar = 'ㅅ'
                                middleChar = nullChar
                                finalChar = nullChar
                                finalSubChar = nullChar
                                composeResult()
                                composedResult = nullChar
                                return UpdatedChars(null,
                                    firstChar.toString() + firstSubChar.toString()
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
                                return UpdatedChars(null, firstChar.toString())
                            }
                            'ㅉ', nullChar -> {
                                firstChar = 'ㄴ'
                                firstSubChar = 'ㅈ'
                                composeResult()
                                composedResult = nullChar
                                return UpdatedChars(null,
                                    firstChar.toString() + firstSubChar.toString()
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
                'ㄵ' -> when (inputChar) {
                    'ㅈ' -> {
                        firstChar = 'ㄴ'
                        firstSubChar = 'ㅊ'
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(null,
                            firstChar.toString() + firstSubChar.toString()
                        )
                    }
                    'ㅣ', 'ㅡ' -> {
                        val temp = "ㄴ"
                        state = 2
                        firstChar = 'ㅈ'
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    'ᆞ' -> {
                        val temp = "ㄴ"
                        state = 2
                        firstChar = 'ㅈ'
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(temp,
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
                'ㄶ' -> when (inputChar) {
                    'ㅅ' -> {
                        firstChar = 'ㄴ'
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
                    'ㅣ', 'ㅡ' -> {
                        val temp = "ㄴ"
                        state = 2
                        firstChar = 'ㅎ'
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    'ᆞ' -> {
                        val temp = "ㄴ"
                        state = 2
                        firstChar = 'ㅎ'
                        firstSubChar = nullChar
                        middleChar = inputChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(temp,
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
                'ㄷ' -> when (inputChar) {
                    'ㄷ' -> {
                        firstChar = 'ㅌ'
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
                'ㄸ' -> when (inputChar) {
                    'ㄷ' -> {
                        firstChar = 'ㄷ'
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
                                return UpdatedChars(null,
                                    firstChar.toString() + firstSubChar.toString()
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
                                return UpdatedChars(null, firstChar.toString())
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
                        return UpdatedChars(null, firstChar.toString())
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
                                return UpdatedChars(null,
                                    firstChar.toString() + firstSubChar.toString()
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
                                return UpdatedChars(null, firstChar.toString())
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
                                return UpdatedChars(null, firstChar.toString())
                            }
                            nullChar -> {
                                firstChar = 'ㄽ'
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
                                return UpdatedChars(null, firstChar.toString())
                            }
                            nullChar -> {
                                firstChar = 'ㄹ'
                                firstSubChar = 'ㅇ'
                                middleChar = nullChar
                                finalChar = nullChar
                                finalSubChar = nullChar
                                composeResult()
                                composedResult = nullChar
                                return UpdatedChars(null,
                                    firstChar.toString() + firstSubChar.toString()
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
                'ㄺ' -> when (inputChar) {
                    'ㄱ' -> {
                        firstChar = 'ㄹ'
                        firstSubChar = 'ㅋ'
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(null,
                            firstChar.toString() + firstSubChar.toString()
                        )
                    }
                    'ㅣ', 'ㅡ' -> {
                        val temp = "ㄹ"
                        state = 2
                        firstChar = 'ㄱ'
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    'ᆞ' -> {
                        val temp = "ㄹ"
                        state = 2
                        firstChar = 'ㄱ'
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(temp,
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
                'ㄻ' -> when (inputChar) {
                    'ㅇ' -> {
                        firstChar = 'ㄹ'
                        firstSubChar = 'ㅇ'
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(null,
                            firstChar.toString() + firstSubChar.toString()
                        )
                    }
                    'ㅣ', 'ㅡ' -> {
                        val temp = "ㄹ"
                        state = 2
                        firstChar = 'ㅁ'
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    'ᆞ' -> {
                        val temp = "ㄹ"
                        state = 2
                        firstChar = 'ㅁ'
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(temp,
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
                'ㄼ' -> when (inputChar) {
                    'ㅂ' -> {
                        firstChar = 'ㄹ'
                        firstSubChar = 'ㅃ'
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(null,
                            firstChar.toString() + firstSubChar.toString()
                        )
                    }
                    'ㅣ', 'ㅡ' -> {
                        val temp = "ㄹ"
                        state = 2
                        firstChar = 'ㅂ'
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    'ᆞ' -> {
                        val temp = "ㄹ"
                        state = 2
                        firstChar = 'ㅂ'
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(temp,
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
                'ㄽ' -> when (inputChar) {
                    'ㅅ' -> {
                        firstChar = 'ㅀ'
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(null, firstChar.toString())
                    }
                    'ㅣ', 'ㅡ' -> {
                        val temp = "ㄹ"
                        state = 2
                        firstChar = 'ㅅ'
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    'ᆞ' -> {
                        val temp = "ㄹ"
                        state = 2
                        firstChar = 'ㅅ'
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(temp,
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
                'ㅀ' -> when (inputChar) {
                    'ㅅ' -> {
                        firstChar = 'ㄹ'
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
                    'ㅣ', 'ㅡ' -> {
                        val temp = "ㄹ"
                        state = 2
                        firstChar = 'ㅎ'
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    'ᆞ' -> {
                        val temp = "ㄹ"
                        state = 2
                        firstChar = 'ㅎ'
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(temp,
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
                'ㅁ' -> when (inputChar) {
                    'ㅇ' -> {
                        firstChar = 'ㅇ'
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
                'ㅂ' -> when (inputChar) {
                    'ㅂ' -> {
                        firstChar = 'ㅍ'
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
                                firstChar = 'ㅂ'
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
                                firstChar = 'ㅄ'
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
                'ㅃ' -> when (inputChar) {
                    'ㅂ' -> {
                        firstChar = 'ㅂ'
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
                'ㅄ' -> when (inputChar) {
                    'ㅅ' -> {
                        firstChar = 'ㅂ'
                        firstSubChar = 'ㅎ'
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(null,
                            firstChar.toString() + firstSubChar.toString()
                        )
                    }
                    'ㅣ', 'ㅡ' -> {
                        val temp = "ㅂ"
                        state = 2
                        firstChar = 'ㅅ'
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    'ᆞ' -> {
                        val temp = "ㅂ"
                        state = 2
                        firstChar = 'ㅅ'
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(temp,
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
                'ㅅ' -> when (inputChar) {
                    'ㅅ' -> {
                        firstChar = 'ㅎ'
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
                'ㅆ' -> when (inputChar) {
                    'ㅅ' -> {
                        firstChar = 'ㅅ'
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
                'ㅇ' -> when (inputChar) {
                    'ㅇ' -> {
                        firstChar = 'ㅁ'
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
                'ㅈ' -> when (inputChar) {
                    'ㅈ' -> {
                        firstChar = 'ㅊ'
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
                'ㅉ' -> when (inputChar) {
                    'ㅈ' -> {
                        firstChar = 'ㅈ'
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
                'ㅊ' -> when (inputChar) {
                    'ㅈ' -> {
                        firstChar = 'ㅉ'
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
                'ㅋ' -> when (inputChar) {
                    'ㄱ' -> {
                        firstChar = 'ㄲ'
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
                'ㅌ' -> when (inputChar) {
                    'ㄷ' -> {
                        firstChar = 'ㄸ'
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
                'ㅍ' -> when (inputChar) {
                    'ㅂ' -> {
                        firstChar = 'ㅃ'
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
                'ㅎ' -> when (inputChar) {
                    'ㅅ' -> {
                        firstChar = 'ㅆ'
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
                else -> {
                    initChar()
                    state = 0
                    composedResult = nullChar
                    return UpdatedChars(inputChar.toString(), null)
                }
            }
            2 -> when (middleChar) {
                'ㅐ', 'ㅒ', 'ㅔ', 'ㅖ', 'ㅙ', 'ㅛ', 'ㅞ', 'ㅟ', 'ㅢ' -> when (inputChar) {
                    'ㅣ', 'ᆞ', 'ㅡ' -> {
                        val temp = composedResult.toString()
                        state = -1
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(temp, middleChar.toString())
                    }
                    else -> {
                        state = 3
                        firstSubChar = nullChar
                        finalChar = inputChar
                        finalSubChar = nullChar
                        composeResult()
                        return UpdatedChars(null, composedResult.toString())
                    }
                }
                'ㅏ' -> when (inputChar) {
                    'ㅣ' -> {
                        firstSubChar = nullChar
                        middleChar = 'ㅐ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        return UpdatedChars(null, composedResult.toString())
                    }
                    'ᆞ' -> {
                        firstSubChar = nullChar
                        middleChar = 'ㅑ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        return UpdatedChars(null, composedResult.toString())
                    }
                    'ㅡ' -> {
                        val temp = composedResult.toString()
                        state = -1
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
                        state = 3
                        firstSubChar = nullChar
                        finalChar = inputChar
                        finalSubChar = nullChar
                        composeResult()
                        return UpdatedChars(null, composedResult.toString())
                    }
                }
                'ㅑ' -> when (inputChar) {
                    'ㅣ' -> {
                        firstSubChar = nullChar
                        middleChar = 'ㅒ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        return UpdatedChars(null, composedResult.toString())
                    }
                    'ᆞ' -> {
                        firstSubChar = nullChar
                        middleChar = 'ㅏ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        return UpdatedChars(null, composedResult.toString())
                    }
                    'ㅡ' -> {
                        val temp = composedResult.toString()
                        state = -1
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
                        state = 3
                        firstSubChar = nullChar
                        finalChar = inputChar
                        finalSubChar = nullChar
                        composeResult()
                        return UpdatedChars(null, composedResult.toString())
                    }
                }
                'ㅓ' -> when (inputChar) {
                    'ㅣ' -> {
                        firstSubChar = nullChar
                        middleChar = 'ㅔ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        return UpdatedChars(null, composedResult.toString())
                    }
                    'ᆞ', 'ㅡ' -> {
                        val temp = composedResult.toString()
                        state = -1
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
                        state = 3
                        firstSubChar = nullChar
                        finalChar = inputChar
                        finalSubChar = nullChar
                        composeResult()
                        return UpdatedChars(null, composedResult.toString())
                    }
                }
                'ㅕ' -> when (inputChar) {
                    'ㅣ' -> {
                        firstSubChar = nullChar
                        middleChar = 'ㅖ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        return UpdatedChars(null, composedResult.toString())
                    }
                    'ᆞ', 'ㅡ' -> {
                        val temp = composedResult.toString()
                        state = -1
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
                        state = 3
                        firstSubChar = nullChar
                        finalChar = inputChar
                        finalSubChar = nullChar
                        composeResult()
                        return UpdatedChars(null, composedResult.toString())
                    }
                }
                'ㅗ' -> when (inputChar) {
                    'ㅣ' -> {
                        firstSubChar = nullChar
                        middleChar = 'ㅚ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        return UpdatedChars(null, composedResult.toString())
                    }
                    'ᆞ', 'ㅡ' -> {
                        val temp = composedResult.toString()
                        state = -1
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
                        state = 3
                        firstSubChar = nullChar
                        finalChar = inputChar
                        finalSubChar = nullChar
                        composeResult()
                        return UpdatedChars(null, composedResult.toString())
                    }
                }
                'ㅘ' -> when (inputChar) {
                    'ㅣ' -> {
                        firstSubChar = nullChar
                        middleChar = 'ㅙ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        return UpdatedChars(null, composedResult.toString())
                    }
                    'ᆞ', 'ㅡ' -> {
                        val temp = composedResult.toString()
                        state = -1
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
                        state = 3
                        firstSubChar = nullChar
                        finalChar = inputChar
                        finalSubChar = nullChar
                        composeResult()
                        return UpdatedChars(null, composedResult.toString())
                    }
                }
                'ㅚ' -> when (inputChar) {
                    'ㅣ', 'ㅡ' -> {
                        val temp = composedResult.toString()
                        state = -1
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
                        firstSubChar = nullChar
                        middleChar = 'ㅘ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        return UpdatedChars(null, composedResult.toString())
                    }
                    else -> {
                        state = 3
                        firstSubChar = nullChar
                        finalChar = inputChar
                        finalSubChar = nullChar
                        composeResult()
                        return UpdatedChars(null, composedResult.toString())
                    }
                }
                'ㅜ' -> when (inputChar) {
                    'ㅣ' -> {
                        firstSubChar = nullChar
                        middleChar = 'ㅟ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        return UpdatedChars(null, composedResult.toString())
                    }
                    'ᆞ' -> {
                        firstSubChar = nullChar
                        middleChar = 'ㅠ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        return UpdatedChars(null, composedResult.toString())
                    }
                    'ㅡ' -> {
                        val temp = composedResult.toString()
                        state = -1
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
                        state = 3
                        firstSubChar = nullChar
                        finalChar = inputChar
                        finalSubChar = nullChar
                        composeResult()
                        return UpdatedChars(null, composedResult.toString())
                    }
                }
                'ㅝ' -> when (inputChar) {
                    'ㅣ' -> {
                        firstSubChar = nullChar
                        middleChar = 'ㅞ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        return UpdatedChars(null, composedResult.toString())
                    }
                    'ᆞ' -> {
                        val temp = composedResult.toString()
                        state = -1
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
                        val temp = composedResult.toString()
                        state = -1
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
                        state = 3
                        firstSubChar = nullChar
                        finalChar = inputChar
                        finalSubChar = nullChar
                        composeResult()
                        return UpdatedChars(null, composedResult.toString())
                    }
                }
                'ㅠ' -> when (inputChar) {
                    'ㅣ' -> {
                        firstSubChar = nullChar
                        middleChar = 'ㅝ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        return UpdatedChars(null, composedResult.toString())
                    }
                    'ᆞ' -> {
                        firstSubChar = nullChar
                        middleChar = 'ㅜ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        return UpdatedChars(null, composedResult.toString())
                    }
                    'ㅡ' -> {
                        val temp = composedResult.toString()
                        state = -1
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
                        state = 3
                        firstSubChar = nullChar
                        finalChar = inputChar
                        finalSubChar = nullChar
                        composeResult()
                        return UpdatedChars(null, composedResult.toString())
                    }
                }
                'ㅡ' -> when (inputChar) {
                    'ㅣ' -> {
                        firstSubChar = nullChar
                        middleChar = 'ㅢ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        return UpdatedChars(null, composedResult.toString())
                    }
                    'ᆞ' -> {
                        firstSubChar = nullChar
                        middleChar = 'ㅜ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        return UpdatedChars(null, composedResult.toString())
                    }
                    'ㅡ' -> {
                        val temp = composedResult.toString()
                        state = -1
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(temp, middleChar.toString())
                    }
                    else -> {
                        state = 3
                        firstSubChar = nullChar
                        finalChar = inputChar
                        finalSubChar = nullChar
                        composeResult()
                        return UpdatedChars(null, composedResult.toString())
                    }
                }
                'ㅣ' -> when (inputChar) {
                    'ㅣ' -> {
                        val temp = composedResult.toString()
                        state = -1
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
                        firstSubChar = nullChar
                        middleChar = 'ㅏ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        return UpdatedChars(null, composedResult.toString())
                    }
                    'ㅡ' -> {
                        val temp = composedResult.toString()
                        state = -1
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
                        state = 3
                        firstSubChar = nullChar
                        finalChar = inputChar
                        finalSubChar = nullChar
                        composeResult()
                        return UpdatedChars(null, composedResult.toString())
                    }
                }
                'ᆞ' -> when (inputChar) {
                    'ㅣ' -> {
                        firstSubChar = nullChar
                        middleChar = 'ㅓ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        return UpdatedChars(null, composedResult.toString())
                    }
                    'ᆞ' -> {
                        firstSubChar = nullChar
                        middleChar = 'ᆢ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(null,
                            firstChar.toString() + middleChar.toString()
                        )
                    }
                    'ㅡ' -> {
                        firstSubChar = nullChar
                        middleChar = 'ㅗ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        return UpdatedChars(null, composedResult.toString())
                    }
                    else -> {
                        val temp = firstChar.toString() + middleChar.toString()
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
                'ᆢ' -> when (inputChar) {
                    'ㅣ' -> {
                        firstSubChar = nullChar
                        middleChar = 'ㅕ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        return UpdatedChars(null, composedResult.toString())
                    }
                    'ᆞ' -> {
                        firstSubChar = nullChar
                        middleChar = 'ᆞ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(null,
                            firstChar.toString() + middleChar.toString()
                        )
                    }
                    'ㅡ' -> {
                        firstSubChar = nullChar
                        middleChar = 'ㅛ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        return UpdatedChars(null, composedResult.toString())
                    }
                    else -> {
                        val temp = firstChar.toString() + middleChar.toString()
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
            3 -> when (finalChar) {
                'ㄱ' -> when (inputChar) {
                    'ㅣ', 'ㅡ' -> {
                        if (finalSubChar != nullChar) {
                            val temp = composedResult.toString()
                            state = 2
                            firstChar = finalSubChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            return UpdatedChars(temp, composedResult.toString())
                        } else {
                            finalChar = nullChar
                            composeResult()
                            val temp = composedResult.toString()
                            state = 2
                            firstChar = 'ㄱ'
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            composeResult()
                            return UpdatedChars(temp, composedResult.toString())
                        }
                    }
                    'ᆞ' -> {
                        if (finalSubChar != nullChar) {
                            val temp = composedResult.toString()
                            state = 2
                            firstChar = finalSubChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            return UpdatedChars(temp, firstChar.toString() + middleChar.toString())
                        } else {
                            finalChar = nullChar
                            composeResult()
                            val temp = composedResult.toString()
                            state = 2
                            firstChar = 'ㄱ'
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            composeResult()
                            return UpdatedChars(temp, firstChar.toString() + middleChar.toString())
                        }
                    }
                    'ㄱ' -> {
                        finalChar = 'ㅋ'
                        firstSubChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        return UpdatedChars(null, composedResult.toString())
                    }
                    'ㄴ', 'ㄷ', 'ㅂ', 'ㅇ', 'ㅈ' -> {
                        val temp = composedResult.toString()
                        state = 1
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(temp, firstChar.toString())
                    }
                    'ㅅ' -> when (finalSubChar) {
                        nullChar, 'ㅆ' -> {
                            finalChar = 'ㄳ'
                            firstSubChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            return UpdatedChars(null, composedResult.toString())
                        }
                        'ㅎ' -> {
                            finalChar = 'ㄱ'
                            firstSubChar = nullChar
                            finalSubChar = 'ㅆ'
                            composeResult()
                            return UpdatedChars(null,
                                composedResult.toString() + finalSubChar.toString()
                            )
                        }
                    }
                }
                'ㄲ' -> when (inputChar) {
                    'ㅣ', 'ㅡ' -> {
                        finalChar = nullChar
                        composeResult()
                        val temp = composedResult.toString()
                        state = 2
                        firstChar = 'ㄲ'
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        composeResult()
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    'ᆞ' -> {
                        finalChar = nullChar
                        composeResult()
                        val temp = composedResult.toString()
                        state = 2
                        firstChar = 'ㄲ'
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        composeResult()
                        return UpdatedChars(temp,
                            firstChar.toString() + middleChar.toString()
                        )
                    }
                    'ㄴ', 'ㄷ', 'ㅂ', 'ㅅ', 'ㅇ', 'ㅈ' -> {
                        val temp = composedResult.toString()
                        state = 1
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(temp, firstChar.toString())
                    }
                    'ㄱ' -> {
                        finalChar = 'ㄱ'
                        firstSubChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        return UpdatedChars(null, composedResult.toString())
                    }
                }
                'ㄳ' -> when (inputChar) {
                    'ㅣ', 'ㅡ' -> {
                        finalChar = 'ㄱ'
                        composeResult()
                        val temp = composedResult.toString()
                        state = 2
                        firstChar = 'ㅅ'
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        composeResult()
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    'ᆞ' -> {
                        finalChar = 'ㄱ'
                        composeResult()
                        val temp = composedResult.toString()
                        state = 2
                        firstChar = 'ㅅ'
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        composeResult()
                        return UpdatedChars(temp,
                            firstChar.toString() + middleChar.toString()
                        )
                    }
                    'ㄱ', 'ㄴ', 'ㄷ', 'ㅂ', 'ㅇ', 'ㅈ' -> {
                        val temp = composedResult.toString()
                        state = 1
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(temp, firstChar.toString())
                    }
                    'ㅅ' -> {
                        finalChar = 'ㄱ'
                        firstSubChar = nullChar
                        finalSubChar = 'ㅎ'
                        composeResult()
                        return UpdatedChars(null,
                            composedResult.toString() + finalSubChar.toString()
                        )
                    }
                }
                'ㄴ' -> when (inputChar) {
                    'ㅣ', 'ㅡ' -> {
                        if (finalSubChar != nullChar) {
                            val temp = composedResult.toString()
                            state = 2
                            firstChar = finalSubChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            return UpdatedChars(temp, composedResult.toString())
                        } else {
                            finalChar = nullChar
                            composeResult()
                            val temp = composedResult.toString()
                            state = 2
                            firstChar = 'ㄴ'
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            composeResult()
                            return UpdatedChars(temp, composedResult.toString())
                        }
                    }
                    'ᆞ' -> {
                        if (finalSubChar != nullChar) {
                            val temp = composedResult.toString()
                            state = 2
                            firstChar = finalSubChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            return UpdatedChars(temp, firstChar.toString() + middleChar.toString())
                        } else {
                            finalChar = nullChar
                            composeResult()
                            val temp = composedResult.toString()
                            state = 2
                            firstChar = 'ㄴ'
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            composeResult()
                            return UpdatedChars(temp, firstChar.toString() + middleChar.toString())
                        }
                    }
                    'ㄴ' -> {
                        finalChar = 'ㄹ'
                        firstSubChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        return UpdatedChars(null, composedResult.toString())
                    }
                    'ㄱ', 'ㄷ', 'ㅂ', 'ㅇ' -> {
                        val temp = composedResult.toString()
                        state = 1
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(temp, firstChar.toString())
                    }
                    'ㅅ' -> {
                        when (finalSubChar) {
                            'ㅅ' -> {
                                finalChar = 'ㄶ'
                                firstSubChar = nullChar
                                finalSubChar = nullChar
                                composeResult()
                                return UpdatedChars(null, composedResult.toString())
                            }
                            nullChar, 'ㅆ' -> {
                                finalChar = 'ㄴ'
                                firstSubChar = nullChar
                                finalSubChar = 'ㅅ'
                                composeResult()
                                return UpdatedChars(null,
                                    composedResult.toString() + finalSubChar.toString()
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
                                return UpdatedChars(null, composedResult.toString())
                            }
                            'ㅊ' -> {
                                finalChar = 'ㄴ'
                                firstSubChar = nullChar
                                finalSubChar = 'ㅉ'
                                composeResult()
                                return UpdatedChars(null,
                                    composedResult.toString() + finalSubChar.toString()
                                )
                            }
                        }
                    }
                }
                'ㄵ' -> when (inputChar) {
                    'ㅣ', 'ㅡ' -> {
                        finalChar = 'ㄴ'
                        composeResult()
                        val temp = composedResult.toString()
                        state = 2
                        firstChar = 'ㅈ'
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        composeResult()
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    'ᆞ' -> {
                        finalChar = 'ㄴ'
                        composeResult()
                        val temp = composedResult.toString()
                        state = 2
                        firstChar = 'ㅈ'
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        composeResult()
                        return UpdatedChars(temp,
                            firstChar.toString() + middleChar.toString()
                        )
                    }
                    'ㄱ', 'ㄴ', 'ㄷ', 'ㅂ', 'ㅅ', 'ㅇ' -> {
                        val temp = composedResult.toString()
                        state = 1
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(temp, firstChar.toString())
                    }
                    'ㅈ' -> {
                        finalChar = 'ㄴ'
                        firstSubChar = nullChar
                        finalSubChar = 'ㅊ'
                        composeResult()
                        return UpdatedChars(null,
                            composedResult.toString() + finalSubChar.toString()
                        )
                    }
                }
                'ㄶ' -> when (inputChar) {
                    'ㅣ', 'ㅡ' -> {
                        finalChar = 'ㄴ'
                        composeResult()
                        val temp = composedResult.toString()
                        state = 2
                        firstChar = 'ㅎ'
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        composeResult()
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    'ᆞ' -> {
                        finalChar = 'ㄴ'
                        composeResult()
                        val temp = composedResult.toString()
                        state = 2
                        firstChar = 'ㅎ'
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        composeResult()
                        return UpdatedChars(temp,
                            firstChar.toString() + middleChar.toString()
                        )
                    }
                    'ㄱ', 'ㄴ', 'ㄷ', 'ㅂ', 'ㅇ', 'ㅈ' -> {
                        val temp = composedResult.toString()
                        state = 1
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(temp, firstChar.toString())
                    }
                    'ㅅ' -> {
                        finalChar = 'ㄴ'
                        firstSubChar = nullChar
                        finalSubChar = 'ㅆ'
                        composeResult()
                        return UpdatedChars(null,
                            composedResult.toString() + finalSubChar.toString()
                        )
                    }
                }
                'ㄷ' -> when (inputChar) {
                    'ㅣ', 'ㅡ' -> {
                        finalChar = nullChar
                        composeResult()
                        val temp = composedResult.toString()
                        state = 2
                        firstChar = 'ㄷ'
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        composeResult()
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    'ᆞ' -> {
                        finalChar = nullChar
                        composeResult()
                        val temp = composedResult.toString()
                        state = 2
                        firstChar = 'ㄷ'
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        composeResult()
                        return UpdatedChars(temp,
                            firstChar.toString() + middleChar.toString()
                        )
                    }
                    'ㄷ' -> {
                        finalChar = 'ㅌ'
                        firstSubChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        return UpdatedChars(null, composedResult.toString())
                    }
                    'ㄱ', 'ㄴ', 'ㅂ', 'ㅅ', 'ㅇ', 'ㅈ' -> {
                        val temp = composedResult.toString()
                        state = 1
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㄹ' -> when (inputChar) {
                    'ㅣ', 'ㅡ' -> {
                        if (finalSubChar != nullChar) {
                            val temp = composedResult.toString()
                            state = 2
                            firstChar = finalSubChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            return UpdatedChars(temp, composedResult.toString())
                        } else {
                            finalChar = nullChar
                            composeResult()
                            val temp = composedResult.toString()
                            state = 2
                            firstChar = 'ㄹ'
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            composeResult()
                            return UpdatedChars(temp, composedResult.toString())
                        }
                    }
                    'ᆞ' -> {
                        if (finalSubChar != nullChar) {
                            val temp = composedResult.toString()
                            state = 2
                            firstChar = finalSubChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            return UpdatedChars(temp,
                                firstChar.toString() + middleChar.toString()
                            )
                        } else {
                            finalChar = nullChar
                            composeResult()
                            val temp = composedResult.toString()
                            state = 2
                            firstChar = 'ㄹ'
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            composeResult()
                            return UpdatedChars(temp,
                                firstChar.toString() + middleChar.toString()
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
                                return UpdatedChars(null, composedResult.toString())
                            }
                            'ㅋ' -> {
                                finalChar = 'ㄹ'
                                firstSubChar = nullChar
                                finalSubChar = 'ㄲ'
                                composeResult()
                                return UpdatedChars(null,
                                    composedResult.toString() + finalSubChar.toString()
                                )
                            }
                        }
                    }
                    'ㄴ' -> {
                        finalChar = 'ㄴ'
                        firstSubChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        return UpdatedChars(null, composedResult.toString())
                    }
                    'ㅂ' -> {
                        when (finalSubChar) {
                            nullChar, 'ㅃ' -> {
                                finalChar = 'ㄼ'
                                firstSubChar = nullChar
                                finalSubChar = nullChar
                                composeResult()
                                return UpdatedChars(null, composedResult.toString())
                            }
                            'ㅍ' -> {
                                finalChar = 'ㄹ'
                                firstSubChar = nullChar
                                finalSubChar = 'ㅃ'
                                composeResult()
                                return UpdatedChars(null,
                                    composedResult.toString() + finalSubChar.toString()
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
                                return UpdatedChars(null, composedResult.toString())
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
                                return UpdatedChars(null, composedResult.toString())
                            }
                            nullChar, 'ㅁ' -> {
                                finalChar = 'ㄹ'
                                firstSubChar = nullChar
                                finalSubChar = 'ㅇ'
                                composeResult()
                                return UpdatedChars(null,
                                    composedResult.toString() + finalSubChar.toString()
                                )
                            }
                        }
                    }
                    'ㄷ', 'ㅈ' -> {
                        val temp = composedResult.toString()
                        state = 1
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㄺ' -> when (inputChar) {
                    'ㅣ', 'ㅡ' -> {
                        finalChar = 'ㄹ'
                        composeResult()
                        val temp = composedResult.toString()
                        state = 2
                        firstChar = 'ㄱ'
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        composeResult()
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    'ᆞ' -> {
                        finalChar = 'ㄹ'
                        composeResult()
                        val temp = composedResult.toString()
                        state = 2
                        firstChar = 'ㄱ'
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        composeResult()
                        return UpdatedChars(temp,
                            firstChar.toString() + middleChar.toString()
                        )
                    }
                    'ㄴ', 'ㄷ', 'ㅂ', 'ㅅ', 'ㅇ', 'ㅈ' -> {
                        val temp = composedResult.toString()
                        state = 1
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(temp, firstChar.toString())
                    }
                    'ㄱ' -> {
                        finalChar = 'ㄹ'
                        firstSubChar = nullChar
                        finalSubChar = 'ㅋ'
                        composeResult()
                        return UpdatedChars(null,
                            composedResult.toString() + finalSubChar.toString()
                        )
                    }
                }
                'ㄻ' -> when (inputChar) {
                    'ㅣ', 'ㅡ' -> {
                        finalChar = 'ㄹ'
                        composeResult()
                        val temp = composedResult.toString()
                        state = 2
                        firstChar = 'ㅁ'
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        composeResult()
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    'ᆞ' -> {
                        finalChar = 'ㄹ'
                        composeResult()
                        val temp = composedResult.toString()
                        state = 2
                        firstChar = 'ㅁ'
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        composeResult()
                        return UpdatedChars(temp,
                            firstChar.toString() + middleChar.toString()
                        )
                    }
                    'ㄱ', 'ㄴ', 'ㄷ', 'ㅂ', 'ㅅ', 'ㅈ' -> {
                        val temp = composedResult.toString()
                        state = 1
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(temp, firstChar.toString())
                    }
                    'ㅇ' -> {
                        finalChar = 'ㄹ'
                        firstSubChar = nullChar
                        finalSubChar = 'ㅇ'
                        composeResult()
                        return UpdatedChars(null,
                            composedResult.toString() + finalSubChar.toString()
                        )
                    }
                }
                'ㄼ' -> when (inputChar) {
                    'ㅣ', 'ㅡ' -> {
                        finalChar = 'ㄹ'
                        composeResult()
                        val temp = composedResult.toString()
                        state = 2
                        firstChar = 'ㅂ'
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        composeResult()
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    'ᆞ' -> {
                        finalChar = 'ㄹ'
                        composeResult()
                        val temp = composedResult.toString()
                        state = 2
                        firstChar = 'ㅂ'
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        composeResult()
                        return UpdatedChars(temp,
                            firstChar.toString() + middleChar.toString()
                        )
                    }
                    'ㄱ', 'ㄴ', 'ㄷ', 'ㅅ', 'ㅇ', 'ㅈ' -> {
                        val temp = composedResult.toString()
                        state = 1
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(temp, firstChar.toString())
                    }
                    'ㅂ' -> {
                        finalChar = 'ㄹ'
                        firstSubChar = nullChar
                        finalSubChar = 'ㅍ'
                        composeResult()
                        return UpdatedChars(null,
                            composedResult.toString() + finalSubChar.toString()
                        )
                    }
                }
                'ㄽ' -> when (inputChar) {
                    'ㅣ', 'ㅡ' -> {
                        finalChar = 'ㄹ'
                        composeResult()
                        val temp = composedResult.toString()
                        state = 2
                        firstChar = 'ㅅ'
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        composeResult()
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    'ᆞ' -> {
                        finalChar = 'ㄹ'
                        composeResult()
                        val temp = composedResult.toString()
                        state = 2
                        firstChar = 'ㅅ'
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        composeResult()
                        return UpdatedChars(temp,
                            firstChar.toString() + middleChar.toString()
                        )
                    }
                    'ㄱ', 'ㄴ', 'ㄷ', 'ㅂ', 'ㅇ', 'ㅈ' -> {
                        val temp = composedResult.toString()
                        state = 1
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(temp, firstChar.toString())
                    }
                    'ㅅ' -> {
                        finalChar = 'ㅀ'
                        firstSubChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        return UpdatedChars(null, composedResult.toString())
                    }
                }
                'ㄿ' -> when (inputChar) {
                    'ㅣ', 'ㅡ' -> {
                        finalChar = 'ㄹ'
                        composeResult()
                        val temp = composedResult.toString()
                        state = 2
                        firstChar = 'ㅍ'
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        composeResult()
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    'ᆞ' -> {
                        finalChar = 'ㄹ'
                        composeResult()
                        val temp = composedResult.toString()
                        state = 2
                        firstChar = 'ㅍ'
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        composeResult()
                        return UpdatedChars(temp,
                            firstChar.toString() + middleChar.toString()
                        )
                    }
                    'ㄱ', 'ㄴ', 'ㄷ', 'ㅅ', 'ㅇ', 'ㅈ' -> {
                        val temp = composedResult.toString()
                        state = 1
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(temp, firstChar.toString())
                    }
                    'ㅂ' -> {
                        finalChar = 'ㄹ'
                        firstSubChar = nullChar
                        finalSubChar = 'ㅃ'
                        composeResult()
                        return UpdatedChars(null,
                            composedResult.toString() + finalSubChar.toString()
                        )
                    }
                }
                'ㅀ' -> when (inputChar) {
                    'ㅣ', 'ㅡ' -> {
                        finalChar = 'ㄹ'
                        composeResult()
                        val temp = composedResult.toString()
                        state = 2
                        firstChar = 'ㅎ'
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        composeResult()
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    'ᆞ' -> {
                        finalChar = 'ㄹ'
                        composeResult()
                        val temp = composedResult.toString()
                        state = 2
                        firstChar = 'ㅎ'
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        composeResult()
                        return UpdatedChars(temp,
                            firstChar.toString() + middleChar.toString()
                        )
                    }
                    'ㄱ', 'ㄴ', 'ㄷ', 'ㅂ', 'ㅇ', 'ㅈ' -> {
                        val temp = composedResult.toString()
                        state = 1
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(temp, firstChar.toString())
                    }
                    'ㅅ' -> {
                        finalChar = 'ㄹ'
                        firstSubChar = nullChar
                        finalSubChar = 'ㅆ'
                        composeResult()
                        return UpdatedChars(null,
                            composedResult.toString() + finalSubChar.toString()
                        )
                    }
                }
                'ㅁ' -> when (inputChar) {
                    'ㅣ', 'ㅡ' -> {
                        finalChar = nullChar
                        composeResult()
                        val temp = composedResult.toString()
                        state = 2
                        firstChar = 'ㅁ'
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        composeResult()
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    'ᆞ' -> {
                        finalChar = nullChar
                        composeResult()
                        val temp = composedResult.toString()
                        state = 2
                        firstChar = 'ㅁ'
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        composeResult()
                        return UpdatedChars(temp,
                            firstChar.toString() + middleChar.toString()
                        )
                    }
                    'ㄱ', 'ㄴ', 'ㄷ', 'ㅂ', 'ㅅ', 'ㅈ' -> {
                        val temp = composedResult.toString()
                        state = 1
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(temp, firstChar.toString())
                    }
                    'ㅇ' -> {
                        finalChar = 'ㅇ'
                        firstSubChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        return UpdatedChars(null, composedResult.toString())
                    }
                }
                'ㅂ' -> when (inputChar) {
                    'ㅣ', 'ㅡ' -> {
                        if (finalSubChar != nullChar) {
                            val temp = composedResult.toString()
                            state = 2
                            firstChar = finalSubChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            return UpdatedChars(temp, composedResult.toString())
                        } else {
                            finalChar = nullChar
                            composeResult()
                            val temp = composedResult.toString()
                            state = 2
                            firstChar = 'ㅂ'
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            composeResult()
                            return UpdatedChars(temp, composedResult.toString())
                        }
                    }
                    'ᆞ' -> {
                        if (finalSubChar != nullChar) {
                            val temp = composedResult.toString()
                            state = 2
                            firstChar = finalSubChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            return UpdatedChars(temp, firstChar.toString() + middleChar.toString())
                        } else {
                            finalChar = nullChar
                            composeResult()
                            val temp = composedResult.toString()
                            state = 2
                            firstChar = 'ㅂ'
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            composeResult()
                            return UpdatedChars(temp, firstChar.toString() + middleChar.toString())
                        }
                    }
                    'ㅂ' -> {
                        finalChar = 'ㅍ'
                        firstSubChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        return UpdatedChars(null, composedResult.toString())
                    }
                    'ㄱ', 'ㄴ', 'ㄷ', 'ㅇ', 'ㅈ' -> {
                        val temp = composedResult.toString()
                        state = 1
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(temp, firstChar.toString())
                    }
                    'ㅅ' -> {
                        when (finalSubChar) {
                            nullChar, 'ㅆ' -> {
                                finalChar = 'ㅄ'
                                firstSubChar = nullChar
                                finalSubChar = nullChar
                                composeResult()
                                return UpdatedChars(null, composedResult.toString())
                            }
                            'ㅎ' -> {
                                finalChar = 'ㅂ'
                                firstSubChar = nullChar
                                finalSubChar = 'ㅆ'
                                composeResult()
                                return UpdatedChars(null,
                                    composedResult.toString() + finalSubChar.toString()
                                )
                            }
                        }
                    }
                }
                'ㅄ' -> when (inputChar) {
                    'ㅣ', 'ㅡ' -> {
                        finalChar = 'ㅂ'
                        composeResult()
                        val temp = composedResult.toString()
                        state = 2
                        firstChar = 'ㅅ'
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        composeResult()
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    'ᆞ' -> {
                        finalChar = 'ㅂ'
                        composeResult()
                        val temp = composedResult.toString()
                        state = 2
                        firstChar = 'ㅅ'
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        composeResult()
                        return UpdatedChars(temp,
                            firstChar.toString() + middleChar.toString()
                        )
                    }
                    'ㄱ', 'ㄴ', 'ㄷ', 'ㅂ', 'ㅇ', 'ㅈ' -> {
                        val temp = composedResult.toString()
                        state = 1
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(temp, firstChar.toString())
                    }
                    'ㅅ' -> {
                        finalChar = 'ㅂ'
                        firstSubChar = nullChar
                        finalSubChar = 'ㅎ'
                        composeResult()
                        return UpdatedChars(null,
                            composedResult.toString() + finalSubChar.toString()
                        )
                    }
                }
                'ㅅ' -> when (inputChar) {
                    'ㅣ', 'ㅡ' -> {
                        finalChar = nullChar
                        composeResult()
                        val temp = composedResult.toString()
                        state = 2
                        firstChar = 'ㅅ'
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        composeResult()
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    'ᆞ' -> {
                        finalChar = nullChar
                        composeResult()
                        val temp = composedResult.toString()
                        state = 2
                        firstChar = 'ㅅ'
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        composeResult()
                        return UpdatedChars(temp,
                            firstChar.toString() + middleChar.toString()
                        )
                    }
                    'ㄱ', 'ㄴ', 'ㄷ', 'ㅂ', 'ㅇ', 'ㅈ' -> {
                        val temp = composedResult.toString()
                        state = 1
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(temp, firstChar.toString())
                    }
                    'ㅅ' -> {
                        finalChar = 'ㅎ'
                        firstSubChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        return UpdatedChars(null, composedResult.toString())
                    }
                }
                'ㅆ' -> when (inputChar) {
                    'ㅣ', 'ㅡ' -> {
                        finalChar = nullChar
                        composeResult()
                        val temp = composedResult.toString()
                        state = 2
                        firstChar = 'ㅆ'
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        composeResult()
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    'ᆞ' -> {
                        finalChar = nullChar
                        composeResult()
                        val temp = composedResult.toString()
                        state = 2
                        firstChar = 'ㅆ'
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        composeResult()
                        return UpdatedChars(temp,
                            firstChar.toString() + middleChar.toString()
                        )
                    }
                    'ㄱ', 'ㄴ', 'ㄷ', 'ㅂ', 'ㅇ', 'ㅈ' -> {
                        val temp = composedResult.toString()
                        state = 1
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(temp, firstChar.toString())
                    }
                    'ㅅ' -> {
                        finalChar = 'ㅎ'
                        firstSubChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        return UpdatedChars(null, composedResult.toString())
                    }
                }
                'ㅇ' -> when (inputChar) {
                    'ㅣ', 'ㅡ' -> {
                        finalChar = nullChar
                        composeResult()
                        val temp = composedResult.toString()
                        state = 2
                        firstChar = 'ㅇ'
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        composeResult()
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    'ᆞ' -> {
                        finalChar = nullChar
                        composeResult()
                        val temp = composedResult.toString()
                        state = 2
                        firstChar = 'ㅇ'
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        composeResult()
                        return UpdatedChars(temp,
                            firstChar.toString() + middleChar.toString()
                        )
                    }
                    'ㄱ', 'ㄴ', 'ㄷ', 'ㅂ', 'ㅅ', 'ㅈ' -> {
                        val temp = composedResult.toString()
                        state = 1
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(temp, firstChar.toString())
                    }
                    'ㅇ' -> {
                        finalChar = 'ㅁ'
                        firstSubChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        return UpdatedChars(null, composedResult.toString())
                    }
                }
                'ㅈ' -> when (inputChar) {
                    'ㅣ', 'ㅡ' -> {
                        finalChar = nullChar
                        composeResult()
                        val temp = composedResult.toString()
                        state = 2
                        firstChar = 'ㅈ'
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        composeResult()
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    'ᆞ' -> {
                        finalChar = nullChar
                        composeResult()
                        val temp = composedResult.toString()
                        state = 2
                        firstChar = 'ㅈ'
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        composeResult()
                        return UpdatedChars(temp,
                            firstChar.toString() + middleChar.toString()
                        )
                    }
                    'ㄱ', 'ㄴ', 'ㄷ', 'ㅂ', 'ㅅ', 'ㅇ' -> {
                        val temp = composedResult.toString()
                        state = 1
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(temp, firstChar.toString())
                    }
                    'ㅈ' -> {
                        finalChar = 'ㅊ'
                        firstSubChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        return UpdatedChars(null, composedResult.toString())
                    }
                }
                'ㅊ' -> when (inputChar) {
                    'ㅣ', 'ㅡ' -> {
                        finalChar = nullChar
                        composeResult()
                        val temp = composedResult.toString()
                        state = 2
                        firstChar = 'ㅊ'
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        composeResult()
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    'ᆞ' -> {
                        finalChar = nullChar
                        composeResult()
                        val temp = composedResult.toString()
                        state = 2
                        firstChar = 'ㅊ'
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        composeResult()
                        return UpdatedChars(temp,
                            firstChar.toString() + middleChar.toString()
                        )
                    }
                    'ㄱ', 'ㄴ', 'ㄷ', 'ㅂ', 'ㅅ', 'ㅇ' -> {
                        val temp = composedResult.toString()
                        state = 1
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(temp, firstChar.toString())
                    }
                    'ㅈ' -> {
                        finalChar = nullChar
                        firstSubChar = nullChar
                        finalSubChar = 'ㅉ'
                        composeResult()
                        return UpdatedChars(null,
                            composedResult.toString() + finalSubChar.toString()
                        )
                    }
                }
                'ㅋ' -> when (inputChar) {
                    'ㅣ', 'ㅡ' -> {
                        finalChar = nullChar
                        composeResult()
                        val temp = composedResult.toString()
                        state = 2
                        firstChar = 'ㅋ'
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        composeResult()
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    'ᆞ' -> {
                        finalChar = nullChar
                        composeResult()
                        val temp = composedResult.toString()
                        state = 2
                        firstChar = 'ㅋ'
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        composeResult()
                        return UpdatedChars(temp,
                            firstChar.toString() + middleChar.toString()
                        )
                    }
                    'ㄴ', 'ㄷ', 'ㅂ', 'ㅅ', 'ㅇ', 'ㅈ' -> {
                        val temp = composedResult.toString()
                        state = 1
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(temp, firstChar.toString())
                    }
                    'ㄱ' -> {
                        finalChar = 'ㄲ'
                        firstSubChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        return UpdatedChars(null, composedResult.toString())
                    }
                }
                'ㅌ' -> when (inputChar) {
                    'ㅣ', 'ㅡ' -> {
                        finalChar = nullChar
                        composeResult()
                        val temp = composedResult.toString()
                        state = 2
                        firstChar = 'ㅌ'
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        composeResult()
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    'ᆞ' -> {
                        finalChar = nullChar
                        composeResult()
                        val temp = composedResult.toString()
                        state = 2
                        firstChar = 'ㅌ'
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        composeResult()
                        return UpdatedChars(temp,
                            firstChar.toString() + middleChar.toString()
                        )
                    }
                    'ㄱ', 'ㄴ', 'ㅂ', 'ㅅ', 'ㅇ', 'ㅈ' -> {
                        val temp = composedResult.toString()
                        state = 1
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(temp, firstChar.toString())
                    }
                    'ㄷ' -> {
                        finalChar = nullChar
                        firstSubChar = nullChar
                        finalSubChar = 'ㄸ'
                        composeResult()
                        return UpdatedChars(null,
                            composedResult.toString() + finalSubChar.toString()
                        )
                    }
                }
                'ㅍ' -> when (inputChar) {
                    'ㅣ', 'ㅡ' -> {
                        finalChar = nullChar
                        composeResult()
                        val temp = composedResult.toString()
                        state = 2
                        firstChar = 'ㅍ'
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        composeResult()
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    'ᆞ' -> {
                        finalChar = nullChar
                        composeResult()
                        val temp = composedResult.toString()
                        state = 2
                        firstChar = 'ㅍ'
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        composeResult()
                        return UpdatedChars(temp,
                            firstChar.toString() + middleChar.toString()
                        )
                    }
                    'ㄱ', 'ㄴ', 'ㄷ', 'ㅅ', 'ㅇ', 'ㅈ' -> {
                        val temp = composedResult.toString()
                        state = 1
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(temp, firstChar.toString())
                    }
                    'ㅂ' -> {
                        finalChar = nullChar
                        firstSubChar = nullChar
                        finalSubChar = 'ㅃ'
                        composeResult()
                        return UpdatedChars(null,
                            composedResult.toString() + finalSubChar.toString()
                        )
                    }
                }
                'ㅎ' -> when (inputChar) {
                    'ㅣ', 'ㅡ' -> {
                        finalChar = nullChar
                        composeResult()
                        val temp = composedResult.toString()
                        state = 2
                        firstChar = 'ㅎ'
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        composeResult()
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    'ᆞ' -> {
                        finalChar = nullChar
                        composeResult()
                        val temp = composedResult.toString()
                        state = 2
                        firstChar = 'ㅎ'
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        composeResult()
                        return UpdatedChars(temp,
                            firstChar.toString() + middleChar.toString()
                        )
                    }
                    'ㄱ', 'ㄴ', 'ㄷ', 'ㅂ', 'ㅇ', 'ㅈ' -> {
                        val temp = composedResult.toString()
                        state = 1
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(temp, firstChar.toString())
                    }
                    'ㅅ' -> {
                        finalChar = 'ㅆ'
                        firstSubChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        return UpdatedChars(null, composedResult.toString())
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
                                return UpdatedChars(null, composedResult.toString())
                            }
                            'ㅣ', 'ᆞ', 'ㅡ' -> {
                                finalChar = nullChar
                                composeResult()
                                val temp = composedResult.toString()
                                state = 2
                                firstChar = finalSubChar
                                middleChar = inputChar
                                finalChar = nullChar
                                firstSubChar = nullChar
                                finalSubChar = nullChar
                                return UpdatedChars(temp, composedResult.toString())
                            }
                            else -> {
                                val temp = composedResult.toString() + finalSubChar.toString()
                                state = 1
                                firstChar = inputChar
                                middleChar = nullChar
                                finalChar = nullChar
                                firstSubChar = nullChar
                                finalSubChar = nullChar
                                return UpdatedChars(temp, firstChar.toString())
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
                                return UpdatedChars(null, composedResult.toString())
                            }
                            'ㅣ', 'ᆞ', 'ㅡ' -> {
                                finalChar = nullChar
                                composeResult()
                                val temp = composedResult.toString()
                                state = 2
                                firstChar = finalSubChar
                                middleChar = inputChar
                                finalChar = nullChar
                                firstSubChar = nullChar
                                finalSubChar = nullChar
                                return UpdatedChars(temp, composedResult.toString())
                            }
                            else -> {
                                val temp = composedResult.toString() + finalSubChar.toString()
                                state = 1
                                firstChar = inputChar
                                middleChar = nullChar
                                finalChar = nullChar
                                firstSubChar = nullChar
                                finalSubChar = nullChar
                                return UpdatedChars(temp, firstChar.toString())
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
                                return UpdatedChars(null, composedResult.toString())
                            }
                            'ㅣ', 'ᆞ', 'ㅡ' -> {
                                finalChar = nullChar
                                composeResult()
                                val temp = composedResult.toString()
                                state = 2
                                firstChar = finalSubChar
                                middleChar = inputChar
                                finalChar = nullChar
                                firstSubChar = nullChar
                                finalSubChar = nullChar
                                return UpdatedChars(temp, composedResult.toString())
                            }
                            else -> {
                                val temp = composedResult.toString() + finalSubChar.toString()
                                state = 1
                                firstChar = inputChar
                                middleChar = nullChar
                                finalChar = nullChar
                                firstSubChar = nullChar
                                finalSubChar = nullChar
                                return UpdatedChars(temp, firstChar.toString())
                            }
                        }
                    }
                }
                else -> {
                    initChar()
                    state = 0
                    composedResult = nullChar
                    return UpdatedChars(inputChar.toString(), null)
                }
            }
        }
        return UpdatedChars(null, null)
    }

    fun delete(): UpdatedChars {
        when (state) {
            0 -> {
                initState()
                initChar()
                initResult()
                return UpdatedChars(null, null)
            }
            -1 -> when (middleChar) {
                'ㅏ' -> {
                    middleChar = 'ㅣ'
                    composeResult()
                    composedResult = nullChar
                    return UpdatedChars(null, middleChar.toString())
                }
                'ㅐ', 'ㅑ' -> {
                    middleChar = 'ㅏ'
                    composeResult()
                    composedResult = nullChar
                    return UpdatedChars(null, middleChar.toString())
                }
                'ㅒ' -> {
                    middleChar = 'ㅑ'
                    composeResult()
                    composedResult = nullChar
                    return UpdatedChars(null, middleChar.toString())
                }
                'ㅓ', 'ㅗ', 'ᆢ' -> {
                    middleChar = 'ᆞ'
                    composeResult()
                    composedResult = nullChar
                    return UpdatedChars(null, middleChar.toString())
                }
                'ㅔ' -> {
                    middleChar = 'ㅓ'
                    composeResult()
                    composedResult = nullChar
                    return UpdatedChars(null, middleChar.toString())
                }
                'ㅕ', 'ㅛ' -> {
                    middleChar = 'ᆢ'
                    composeResult()
                    composedResult = nullChar
                    return UpdatedChars(null, middleChar.toString())
                }
                'ㅖ' -> {
                    middleChar = 'ㅕ'
                    composeResult()
                    composedResult = nullChar
                    return UpdatedChars(null, middleChar.toString())
                }
                'ㅘ' -> {
                    middleChar = 'ㅚ'
                    composeResult()
                    composedResult = nullChar
                    return UpdatedChars(null, middleChar.toString())
                }
                'ㅙ' -> {
                    middleChar = 'ㅘ'
                    composeResult()
                    composedResult = nullChar
                    return UpdatedChars(null, middleChar.toString())
                }
                'ㅚ' -> {
                    middleChar = 'ㅗ'
                    composeResult()
                    composedResult = nullChar
                    return UpdatedChars(null, middleChar.toString())
                }
                'ㅜ', 'ㅢ' -> {
                    middleChar = 'ㅡ'
                    composeResult()
                    composedResult = nullChar
                    return UpdatedChars(null, middleChar.toString())
                }
                'ㅠ', 'ㅟ' -> {
                    middleChar = 'ㅜ'
                    composeResult()
                    composedResult = nullChar
                    return UpdatedChars(null, middleChar.toString())
                }
                'ㅝ' -> {
                    middleChar = 'ㅠ'
                    composeResult()
                    composedResult = nullChar
                    return UpdatedChars(null, middleChar.toString())
                }
                'ㅞ' -> {
                    middleChar = 'ㅝ'
                    composeResult()
                    composedResult = nullChar
                    return UpdatedChars(null, middleChar.toString())
                }
                'ㅡ', 'ㅣ', 'ᆞ' -> {
                    state = 0
                    middleChar = nullChar
                    composeResult()
                    composedResult = nullChar
                    return UpdatedChars(null, null)
                }
                }
            1 -> when (firstChar) {
                'ㄳ' -> {
                    firstChar = 'ㄱ'
                    firstSubChar = nullChar
                    composeResult()
                    composedResult = nullChar
                    return UpdatedChars(null, firstChar.toString())
                }
                'ㄵ', 'ㄶ' -> {
                    firstChar = 'ㄴ'
                    firstSubChar = nullChar
                    composeResult()
                    composedResult = nullChar
                    return UpdatedChars(null, firstChar.toString())
                }
                'ㄺ', 'ㄻ', 'ㄼ', 'ㄽ', 'ㅀ' -> {
                    firstChar = 'ㄹ'
                    firstSubChar = nullChar
                    composeResult()
                    composedResult = nullChar
                    return UpdatedChars(null, firstChar.toString())
                }
                'ㅄ' -> {
                    firstChar = 'ㅂ'
                    firstSubChar = nullChar
                    composeResult()
                    composedResult = nullChar
                    return UpdatedChars(null, firstChar.toString())
                }
                else -> when ( firstSubChar ) {
                    nullChar -> {
                        state = 0
                        firstChar = nullChar
                        firstSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(null, null)
                    }
                    else -> {
                        firstSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(null, firstChar.toString())
                    }
                }
            }
            2 -> when (middleChar) {
                'ㅏ' -> {
                    middleChar = 'ㅣ'
                    composeResult()
                    return UpdatedChars(null, composedResult.toString())
                }
                'ㅐ', 'ㅑ' -> {
                    middleChar = 'ㅏ'
                    composeResult()
                    return UpdatedChars(null, composedResult.toString())
                }
                'ㅒ' -> {
                    middleChar = 'ㅑ'
                    composeResult()
                    return UpdatedChars(null, composedResult.toString())
                }
                'ㅓ', 'ㅗ', 'ᆢ' -> {
                    middleChar = 'ᆞ'
                    composeResult()
                    composedResult = nullChar
                    return UpdatedChars(null,
                        firstChar.toString() + middleChar.toString()
                    )
                }
                'ㅔ' -> {
                    middleChar = 'ㅓ'
                    composeResult()
                    return UpdatedChars(null, composedResult.toString())
                }
                'ㅕ', 'ㅛ' -> {
                    middleChar = 'ᆢ'
                    composeResult()
                    composedResult = nullChar
                    return UpdatedChars(null,
                        firstChar.toString() + middleChar.toString()
                    )
                }
                'ㅖ' -> {
                    middleChar = 'ㅕ'
                    composeResult()
                    return UpdatedChars(null, composedResult.toString())
                }
                'ㅘ' -> {
                    middleChar = 'ㅚ'
                    composeResult()
                    return UpdatedChars(null, composedResult.toString())
                }
                'ㅙ' -> {
                    middleChar = 'ㅘ'
                    composeResult()
                    return UpdatedChars(null, composedResult.toString())
                }
                'ㅚ' -> {
                    middleChar = 'ㅗ'
                    composeResult()
                    return UpdatedChars(null, composedResult.toString())
                }
                'ㅜ', 'ㅢ' -> {
                    middleChar = 'ㅡ'
                    composeResult()
                    return UpdatedChars(null, composedResult.toString())
                }
                'ㅠ', 'ㅟ' -> {
                    middleChar = 'ㅜ'
                    composeResult()
                    return UpdatedChars(null, composedResult.toString())
                }
                'ㅝ' -> {
                    middleChar = 'ㅠ'
                    composeResult()
                    return UpdatedChars(null, composedResult.toString())
                }
                'ㅞ' -> {
                    middleChar = 'ㅝ'
                    composeResult()
                    return UpdatedChars(null, composedResult.toString())
                }
                'ㅡ', 'ㅣ', 'ᆞ' -> {
                    state = 1
                    middleChar = nullChar
                    composeResult()
                    composedResult = nullChar
                    return UpdatedChars(null, firstChar.toString())
                }
            }
            3 -> when (finalChar) {
                'ㄳ' -> {
                    finalChar = 'ㄱ'
                    finalSubChar = nullChar
                    composeResult()
                    return UpdatedChars(null, composedResult.toString())
                }
                'ㄵ', 'ㄶ' -> {
                    finalChar = 'ㄴ'
                    finalSubChar = nullChar
                    composeResult()
                    return UpdatedChars(null, composedResult.toString())
                }
                'ㄺ', 'ㄻ', 'ㄼ', 'ㄽ', 'ㅀ' -> {
                    finalChar = 'ㄹ'
                    finalSubChar = nullChar
                    composeResult()
                    return UpdatedChars(null, composedResult.toString())
                }
                'ㅄ' -> {
                    finalChar = 'ㅂ'
                    finalSubChar = nullChar
                    composeResult()
                    return UpdatedChars(null, composedResult.toString())
                }
                else -> when ( finalSubChar ) {
                    nullChar -> {
                        state = 2
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        return UpdatedChars(null, composedResult.toString())
                    }
                    else -> {
                        finalSubChar = nullChar
                        composeResult()
                        return UpdatedChars(null, composedResult.toString())
                    }
                }
            }
        }
        return UpdatedChars(null, null)
    }
}

