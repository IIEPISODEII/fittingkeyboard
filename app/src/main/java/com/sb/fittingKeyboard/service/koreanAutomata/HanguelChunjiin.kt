package com.sb.fittingKeyboard.service.koreanautomata

object HanguelChunjiin: Automata() {
    private val rrr: Array<Char> = arrayOf('ㄱ', 'ㅋ', 'ㄲ')
    private val ss: Array<Char> = arrayOf('ㄴ', 'ㄹ')
    private val ee: Array<Char> = arrayOf('ㄷ', 'ㅌ')
    private val qqq: Array<Char> = arrayOf('ㅂ', 'ㅍ', 'ㅃ')
    private val ttt: Array<Char> = arrayOf('ㅅ', 'ㅎ', 'ㅆ')
    private val www: Array<Char> = arrayOf('ㅈ', 'ㅊ', 'ㅉ')
    private val dd: Array<Char> = arrayOf('ㅇ', 'ㅁ')

    fun composeChar(inputChar: Char, inputTime: Long): UpdatedChars {
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
                    setFirstInputTimer(inputTime = inputTime)
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
                    setFirstInputTimer(inputTime = inputTime)
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
                    setFirstInputTimer(inputTime = inputTime)
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
                    setFirstInputTimer(inputTime = inputTime)
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
                    setFirstInputTimer(inputTime = inputTime)
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
                    setFirstInputTimer(inputTime = inputTime)
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
                    setFirstInputTimer(inputTime = inputTime)
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
                    setFirstInputTimer(inputTime = inputTime)
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
                    setFirstInputTimer(inputTime = inputTime)
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
                    setFirstInputTimer(inputTime = inputTime)
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
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, middleChar.toString())
                    }
                    'ᆞ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = middleChar.toString()
                            'ᆞ'
                        } else {
                            'ㅏ'
                        }
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        setFirstInputTimer(inputTime = inputTime)
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
                        setFirstInputTimer(inputTime = inputTime)
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
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ᆞ' -> when (inputChar) {
                    'ㅣ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = middleChar.toString()
                            'ㅣ'
                        } else 'ㅓ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, middleChar.toString())
                    }
                    'ᆞ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = middleChar.toString()
                            'ᆞ'
                        } else 'ᆢ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, middleChar.toString())
                    }
                    'ㅡ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = middleChar.toString()
                            'ㅡ'
                        } else 'ㅗ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        setFirstInputTimer(inputTime = inputTime)
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
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㅡ' -> when (inputChar) {
                    'ㅣ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = middleChar.toString()
                            'ㅣ'
                        } else 'ㅢ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, middleChar.toString())
                    }
                    'ᆞ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = middleChar.toString()
                            'ᆞ'
                        } else 'ㅜ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        setFirstInputTimer(inputTime = inputTime)
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
                        setFirstInputTimer(inputTime = inputTime)
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
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ᆢ' -> when (inputChar) {
                    'ㅣ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = middleChar.toString()
                            'ㅣ'
                        } else 'ㅕ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, middleChar.toString())
                    }
                    'ᆞ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = middleChar.toString()
                            'ᆞ'
                        } else 'ᆞ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, middleChar.toString())
                    }
                    'ㅡ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = middleChar.toString()
                            'ㅡ'
                        } else 'ㅛ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        setFirstInputTimer(inputTime = inputTime)
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
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㅏ' -> when (inputChar) {
                    'ㅣ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = middleChar.toString()
                            'ㅣ'
                        } else 'ㅐ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, middleChar.toString())
                    }
                    'ᆞ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = middleChar.toString()
                            'ᆞ'
                        } else 'ㅑ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        setFirstInputTimer(inputTime = inputTime)
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
                        setFirstInputTimer(inputTime = inputTime)
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
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㅐ' -> when (inputChar) {
                    'ㅣ', 'ᆞ', 'ㅡ' -> {
                        val temp = middleChar.toString()
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        setFirstInputTimer(inputTime = inputTime)
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
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㅑ' -> when (inputChar) {
                    'ㅣ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = middleChar.toString()
                            'ㅣ'
                        } else 'ㅒ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        setFirstInputTimer(inputTime = inputTime)
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
                        setFirstInputTimer(inputTime = inputTime)
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
                        setFirstInputTimer(inputTime = inputTime)
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
                        setFirstInputTimer(inputTime = inputTime)
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
                        setFirstInputTimer(inputTime = inputTime)
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
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㅓ' -> when (inputChar) {
                    'ㅣ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = middleChar.toString()
                            'ㅣ'
                        } else 'ㅔ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, middleChar.toString())
                    }
                    'ᆞ', 'ㅡ' -> {
                        val temp = middleChar.toString()
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        setFirstInputTimer(inputTime = inputTime)
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
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㅔ' -> when (inputChar) {
                    'ㅣ', 'ᆞ', 'ㅡ' -> {
                        val temp = middleChar.toString()
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        setFirstInputTimer(inputTime = inputTime)
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
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㅕ' -> when (inputChar) {
                    'ㅣ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = middleChar.toString()
                            'ㅣ'
                        } else 'ㅖ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, middleChar.toString())
                    }
                    'ᆞ', 'ㅡ' -> {
                        val temp = middleChar.toString()
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        setFirstInputTimer(inputTime = inputTime)
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
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㅗ' -> when (inputChar) {
                    'ㅣ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = middleChar.toString()
                            'ㅣ'
                        } else 'ㅚ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        setFirstInputTimer(inputTime = inputTime)
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
                        setFirstInputTimer(inputTime = inputTime)
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
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㅘ' -> when (inputChar) {
                    'ㅣ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = middleChar.toString()
                            'ㅣ'
                        } else 'ㅙ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, middleChar.toString())
                    }
                    'ᆞ', 'ㅡ'  -> {
                        val temp = middleChar.toString()
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        setFirstInputTimer(inputTime = inputTime)
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
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㅚ' -> when (inputChar) {
                    'ㅣ', 'ㅡ' -> {
                        val temp = middleChar.toString()
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, middleChar.toString())
                    }
                    'ᆞ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = middleChar.toString()
                            'ᆞ'
                        } else 'ㅘ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        setFirstInputTimer(inputTime = inputTime)
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
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㅜ' -> when (inputChar) {
                    'ㅣ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = middleChar.toString()
                            'ㅣ'
                        } else 'ㅟ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, middleChar.toString())
                    }
                    'ᆞ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = middleChar.toString()
                            'ᆞ'
                        } else 'ㅠ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        setFirstInputTimer(inputTime = inputTime)
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
                        setFirstInputTimer(inputTime = inputTime)
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
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㅝ' -> when (inputChar) {
                    'ㅣ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = middleChar.toString()
                            'ㅣ'
                        } else 'ㅞ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, middleChar.toString())
                    }
                    'ᆞ', 'ㅡ' -> {
                        val temp = middleChar.toString()
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        setFirstInputTimer(inputTime = inputTime)
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
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㅠ' -> when (inputChar) {
                    'ㅣ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = middleChar.toString()
                            'ㅣ'
                        } else 'ㅝ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, middleChar.toString())
                    }
                    'ᆞ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = middleChar.toString()
                            'ᆞ'
                        } else 'ㅜ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        setFirstInputTimer(inputTime = inputTime)
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
                        setFirstInputTimer(inputTime = inputTime)
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
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                else -> {
                    initChar()
                    state = 0
                    composedResult = nullChar
                    setFirstInputTimer(inputTime = inputTime)
                    return UpdatedChars(inputChar.toString(), null)
                }
            }
            1 -> when (firstChar) {
                'ㄱ' -> when (inputChar) {
                    'ㄱ' -> {
                        if (firstSubChar != nullChar) {
                            val temp = firstChar.toString() + firstSubChar.toString()
                            firstChar = inputChar
                            firstSubChar = nullChar
                            firstSubChar = nullChar
                            middleChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            setFirstInputTimer(inputTime = inputTime)
                            return UpdatedChars(temp, firstChar.toString())
                        } else {
                            var temp: String? = null
                            setSecondInputTimer(inputTime = inputTime)
                            firstChar = if (secondInputTime - firstInputTime >= separationTime1) {
                                temp = firstChar.toString()
                                'ㄱ'
                            } else 'ㅋ'
                            firstSubChar = nullChar
                            middleChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            setFirstInputTimer(inputTime = inputTime)
                            return UpdatedChars(temp, firstChar.toString())
                        }
                    }
                    'ㅅ' -> {
                        when (firstSubChar) {
                            'ㅎ' -> {
                                var temp: String? = null
                                setSecondInputTimer(inputTime = inputTime)
                                firstChar = if (secondInputTime - firstInputTime >= separationTime1) {
                                    temp = firstChar.toString() + firstSubChar.toString()
                                    inputChar
                                } else 'ㄱ'
                                firstSubChar = if (secondInputTime - firstInputTime >= separationTime1) nullChar else 'ㅆ'
                                middleChar = nullChar
                                finalChar = nullChar
                                finalSubChar = nullChar
                                composeResult()
                                composedResult = nullChar
                                setFirstInputTimer(inputTime = inputTime)
                                return UpdatedChars(temp,
                                    firstChar.toString() + firstSubChar.toString()
                                )
                            }
                            'ㅆ', nullChar -> {
                                var temp: String? = null
                                setSecondInputTimer(inputTime = inputTime)
                                firstChar = if (secondInputTime - firstInputTime >= separationTime1) {
                                    temp = firstChar.toString() + firstSubChar.toString()
                                    'ㅅ'
                                } else 'ㄳ'
                                firstSubChar = nullChar
                                middleChar = nullChar
                                finalChar = nullChar
                                finalSubChar = nullChar
                                composeResult()
                                composedResult = nullChar
                                setFirstInputTimer(inputTime = inputTime)
                                return UpdatedChars(temp, firstChar.toString())
                            }
                        }
                    }
                    'ㅣ', 'ㅡ' -> {
                        if (firstSubChar != nullChar) {
                            var temp: String? = null
                            setSecondInputTimer(inputTime = inputTime)
                            if (secondInputTime - firstInputTime >= separationTime1) {
                                temp = firstChar.toString() + firstSubChar.toString()
                                state = -1
                                firstChar = nullChar
                                firstSubChar = nullChar
                                middleChar = inputChar
                                composedResult = inputChar
                            } else {
                                temp = firstChar.toString()
                                state = 2
                                firstChar = firstSubChar
                                firstSubChar = nullChar
                                middleChar = inputChar
                                finalChar = nullChar
                                finalSubChar = nullChar
                                composeResult()
                            }
                            setFirstInputTimer(inputTime = inputTime)
                            return UpdatedChars(temp, composedResult.toString())
                        } else {
                            var temp: String? = null
                            setSecondInputTimer(inputTime = inputTime)
                            if (secondInputTime - firstInputTime >= separationTime1) {
                                temp = firstChar.toString()
                                state = -1
                                firstChar = nullChar
                                middleChar = inputChar
                                composedResult = inputChar
                            } else {
                                state = 2
                                middleChar = inputChar
                                finalChar = nullChar
                                finalSubChar = nullChar
                                composeResult()
                            }
                            setFirstInputTimer(inputTime = inputTime)
                            return UpdatedChars(temp, composedResult.toString())
                        }
                    }
                    'ᆞ' -> {
                        if (firstSubChar != nullChar) {
                            var temp: String? = null
                            setSecondInputTimer(inputTime = inputTime)
                            if (secondInputTime - firstInputTime >= separationTime1) {
                                temp = firstChar.toString() + firstSubChar.toString()
                                state = -1
                                firstChar = nullChar
                                firstSubChar = nullChar
                                middleChar = inputChar
                                composedResult = inputChar
                            } else {
                                temp = firstChar.toString()
                                state = 2
                                firstChar = firstSubChar
                                firstSubChar = nullChar
                                middleChar = inputChar
                                finalChar = nullChar
                                finalSubChar = nullChar
                                composeResult()
                            }
                            setFirstInputTimer(inputTime = inputTime)
                            return UpdatedChars(temp, firstChar.toString() + middleChar.toString())
                        } else {
                            var temp: String? = null
                            setSecondInputTimer(inputTime = inputTime)
                            if (secondInputTime - firstInputTime >= separationTime1) {
                                temp = firstChar.toString()
                                state = -1
                                firstChar = nullChar
                                middleChar = inputChar
                                composedResult = inputChar
                            } else {
                                state = 2
                                middleChar = inputChar
                                composeResult()
                            }
                            setFirstInputTimer(inputTime = inputTime)
                            return UpdatedChars(
                                temp,
                                firstChar.toString() + middleChar.toString()
                            )
                        }
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
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㄲ' -> when (inputChar) {
                    'ㄱ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        firstChar = if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = firstChar.toString()
                            'ㄱ'
                        } else 'ㄲ'
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, firstChar.toString())
                    }
                    'ㅣ', 'ㅡ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = firstChar.toString()
                            state = -1
                            firstChar = nullChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            composedResult = inputChar
                        } else {
                            state = 2
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    'ᆞ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = firstChar.toString()
                            state = -1
                            firstChar = nullChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            composedResult = inputChar
                        } else {
                            state = 2
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
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
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㄳ' -> when (inputChar) {
                    'ㅅ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        firstChar = 'ㄱ'
                        firstSubChar = 'ㅎ'
                        firstSubChar = if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = "ㅅ"
                            'ㅅ'
                        } else 'ㅎ'
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp,
                            firstChar.toString() + firstSubChar.toString()
                        )
                    }
                    'ㅣ', 'ㅡ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = firstChar.toString()
                            state = -1
                            firstChar = nullChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            composedResult = inputChar
                        } else {
                            temp = "ㄱ"
                            state = 2
                            firstChar = 'ㅅ'
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    'ᆞ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = firstChar.toString()
                            state = -1
                            firstChar = nullChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            composedResult = inputChar
                        } else {
                            temp = "ㄱ"
                            state = 2
                            firstChar = 'ㅅ'
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
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
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㄴ' -> when (inputChar) {
                    'ㄴ' -> {
                        if (firstSubChar != nullChar) {
                            val temp = firstChar.toString() + firstSubChar.toString()
                            firstChar = inputChar
                            firstSubChar = nullChar
                            setFirstInputTimer(inputTime = inputTime)
                            return UpdatedChars(temp, firstChar.toString())
                        } else {
                            var temp: String? = null
                            setSecondInputTimer(inputTime = inputTime)
                            if (secondInputTime - firstInputTime >= separationTime1) {
                                temp = firstChar.toString()
                                state = 1
                                firstChar = inputChar
                                firstSubChar = nullChar
                                middleChar = nullChar
                                composedResult = inputChar
                            } else {
                                firstChar = 'ㄹ'
                            }
                            setFirstInputTimer(inputTime = inputTime)
                            return UpdatedChars(temp, firstChar.toString())
                        }
                    }
                    'ㅅ' -> {
                        when (firstSubChar) {
                            'ㅅ' -> {
                                var temp: String? = null
                                setSecondInputTimer(inputTime = inputTime)
                                firstChar = if (secondInputTime - firstInputTime > separationTime1) {
                                    temp = firstChar.toString() + firstSubChar.toString()
                                    inputChar
                                } else 'ㄶ'
                                firstSubChar = nullChar
                                middleChar = nullChar
                                finalChar = nullChar
                                finalSubChar = nullChar
                                composeResult()
                                composedResult = nullChar
                                setFirstInputTimer(inputTime = inputTime)
                                return UpdatedChars(temp, firstChar.toString())
                            }
                            'ㅆ', nullChar -> {
                                var temp: String? = null
                                setSecondInputTimer(inputTime = inputTime)
                                firstChar = 'ㄴ'
                                firstChar = if (secondInputTime - firstInputTime >= separationTime1) {
                                    temp = firstChar.toString() + firstSubChar.toString()
                                    inputChar
                                } else 'ㄴ'
                                firstSubChar = if (secondInputTime - firstInputTime >= separationTime1) nullChar else 'ㅅ'
                                middleChar = nullChar
                                finalChar = nullChar
                                finalSubChar = nullChar
                                composeResult()
                                composedResult = nullChar
                                setFirstInputTimer(inputTime = inputTime)
                                return UpdatedChars(temp,
                                    firstChar.toString() + firstSubChar.toString()
                                )
                            }
                        }
                    }
                    'ㅈ' -> {
                        when (firstSubChar) {
                            'ㅈ' -> {
                                var temp: String? = null
                                setSecondInputTimer(inputTime = inputTime)
                                firstChar = if (secondInputTime - firstInputTime >= separationTime1) {
                                    temp = firstChar.toString() + firstSubChar.toString()
                                    inputChar
                                } else 'ㄵ'
                                firstSubChar = nullChar
                                composeResult()
                                composedResult = nullChar
                                setFirstInputTimer(inputTime = inputTime)
                                return UpdatedChars(temp, firstChar.toString())
                            }
                            'ㅉ', nullChar -> {
                                var temp: String? = null
                                setSecondInputTimer(inputTime = inputTime)
                                firstChar = 'ㄴ'
                                firstChar = if (secondInputTime - firstInputTime >= separationTime1) {
                                    temp = firstChar.toString() + firstSubChar.toString()
                                    inputChar
                                } else 'ㄴ'
                                firstSubChar = if (secondInputTime - firstInputTime >= separationTime1) {
                                    nullChar
                                } else 'ㅈ'
                                composeResult()
                                composedResult = nullChar
                                return UpdatedChars(temp,
                                    firstChar.toString() + firstSubChar.toString()
                                )
                            }
                        }
                    }
                    'ㅣ', 'ㅡ' -> {
                        if (firstSubChar != nullChar) {
                            var temp: String? = null
                            setSecondInputTimer(inputTime = inputTime)
                            if (secondInputTime - firstInputTime >= separationTime1) {
                                temp = firstChar.toString() + firstSubChar.toString()
                                state = -1
                                firstChar = nullChar
                                firstSubChar = nullChar
                                middleChar = inputChar
                                composedResult = inputChar
                            } else {
                                temp = firstChar.toString()
                                state = 2
                                firstChar = firstSubChar
                                firstSubChar = nullChar
                                middleChar = inputChar
                                finalChar = nullChar
                                finalSubChar = nullChar
                                composeResult()
                            }
                            setFirstInputTimer(inputTime = inputTime)
                            return UpdatedChars(temp, composedResult.toString())
                        } else {
                            var temp: String? = null
                            setSecondInputTimer(inputTime = inputTime)
                            if (secondInputTime - firstInputTime >= separationTime1) {
                                temp = firstChar.toString()
                                state = -1
                                firstChar = nullChar
                                firstSubChar = nullChar
                                middleChar = inputChar
                                composedResult = inputChar
                            } else {
                                state = 2
                                firstSubChar = nullChar
                                middleChar = inputChar
                                finalChar = nullChar
                                finalSubChar = nullChar
                                composeResult()
                            }
                            setFirstInputTimer(inputTime = inputTime)
                            return UpdatedChars(temp, composedResult.toString())
                        }
                    }
                    'ᆞ' -> {
                        if (firstSubChar != nullChar) {
                            var temp: String? = null
                            setSecondInputTimer(inputTime = inputTime)
                            if (secondInputTime - firstInputTime >= separationTime1) {
                                temp = firstChar.toString() + firstSubChar.toString()
                                state = -1
                                firstChar = nullChar
                                firstSubChar = nullChar
                                middleChar = inputChar
                                composedResult = inputChar
                            } else {
                                temp = firstChar.toString()
                                state = 2
                                firstChar = firstSubChar
                                firstSubChar = nullChar
                                middleChar = inputChar
                                finalChar = nullChar
                                finalSubChar = nullChar
                                composeResult()
                            }
                            setFirstInputTimer(inputTime = inputTime)
                            return UpdatedChars(temp, firstChar.toString() + middleChar.toString())
                        } else {
                            var temp: String? = null
                            setSecondInputTimer(inputTime = inputTime)
                            if (secondInputTime - firstInputTime >= separationTime1) {
                                temp = firstChar.toString()
                                state = -1
                                firstChar = nullChar
                                firstSubChar = nullChar
                                middleChar = inputChar
                                composedResult = inputChar
                            } else {
                                state = 2
                                firstSubChar = nullChar
                                middleChar = inputChar
                                finalChar = nullChar
                                finalSubChar = nullChar
                                composeResult()
                            }
                            setFirstInputTimer(inputTime = inputTime)
                            return UpdatedChars(
                                temp,
                                firstChar.toString() + middleChar.toString()
                            )
                        }
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
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㄵ' -> when (inputChar) {
                    'ㅈ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        firstChar = if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = firstChar.toString()
                            inputChar
                        } else 'ㄴ'
                        firstSubChar = if (secondInputTime - firstInputTime >= separationTime1) {
                            nullChar
                        } else 'ㅊ'
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(temp,
                            firstChar.toString() + firstSubChar.toString()
                        )
                    }
                    'ㅣ', 'ㅡ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = firstChar.toString()
                            state = -1
                            firstChar = nullChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            composedResult = inputChar
                        } else {
                            temp = "ㄴ"
                            state = 2
                            firstChar = 'ㅈ'
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    'ᆞ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = firstChar.toString()
                            state = -1
                            firstChar = nullChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            composedResult = inputChar
                        } else {
                            temp = "ㄴ"
                            state = 2
                            firstChar = 'ㅈ'
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
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
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㄶ' -> when (inputChar) {
                    'ㅅ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        firstChar = if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = firstChar.toString()
                            inputChar
                        } else 'ㄴ'
                        firstSubChar = if (secondInputTime - firstInputTime >= separationTime1) {
                            nullChar
                        } else 'ㅆ'
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp,
                            firstChar.toString() + firstSubChar.toString()
                        )
                    }
                    'ㅣ', 'ㅡ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = firstChar.toString()
                            state = -1
                            firstChar = nullChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            composedResult = inputChar
                        } else {
                            temp = "ㄴ"
                            state = 2
                            firstChar = 'ㅎ'
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    'ᆞ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = firstChar.toString()
                            state = -1
                            firstChar = nullChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            composedResult = inputChar
                        } else {
                            temp = "ㄴ"
                            state = 2
                            firstChar = 'ㅎ'
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
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
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㄷ' -> when (inputChar) {
                    'ㄷ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        firstChar = if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = firstChar.toString()
                            inputChar
                        } else 'ㅌ'
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, firstChar.toString())
                    }
                    'ㅣ', 'ㅡ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = firstChar.toString()
                            state = -1
                            firstChar = nullChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            composedResult = inputChar
                        } else {
                            state = 2
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    'ᆞ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = firstChar.toString()
                            state = -1
                            firstChar = nullChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            composedResult = inputChar
                        } else {
                            state = 2
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
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
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㄸ' -> when (inputChar) {
                    'ㄷ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        firstChar = if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = firstChar.toString()
                            inputChar
                        } else 'ㄷ'
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, firstChar.toString())
                    }
                    'ㅣ', 'ㅡ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = firstChar.toString()
                            state = -1
                            firstChar = nullChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            composedResult = inputChar
                        } else {
                            state = 2
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    'ᆞ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = firstChar.toString()
                            state = -1
                            firstChar = nullChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            composedResult = inputChar
                        } else {
                            state = 2
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
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
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㄹ' -> when (inputChar) {
                    'ㄱ' -> {
                        when (firstSubChar) {
                            'ㅋ' -> {
                                var temp: String? = null
                                setSecondInputTimer(inputTime = inputTime)
                                firstChar = if (secondInputTime - firstInputTime >= separationTime1) {
                                    temp = firstChar.toString() + firstSubChar.toString()
                                    inputChar
                                } else 'ㄹ'
                                firstSubChar = if (secondInputTime - firstInputTime >= separationTime1) {
                                    nullChar
                                } else 'ㄲ'
                                middleChar = nullChar
                                finalChar = nullChar
                                finalSubChar = nullChar
                                composeResult()
                                composedResult = nullChar
                                setFirstInputTimer(inputTime = inputTime)
                                return UpdatedChars(temp,
                                    firstChar.toString() + firstSubChar.toString()
                                )
                            }
                            'ㄲ', nullChar -> {
                                var temp: String? = null
                                setSecondInputTimer(inputTime = inputTime)
                                firstChar = if (secondInputTime - firstInputTime >= separationTime1) {
                                    temp = firstChar.toString() + firstSubChar.toString()
                                    inputChar
                                } else 'ㄺ'
                                firstSubChar = nullChar
                                middleChar = nullChar
                                finalChar = nullChar
                                finalSubChar = nullChar
                                composeResult()
                                composedResult = nullChar
                                setFirstInputTimer(inputTime = inputTime)
                                return UpdatedChars(temp, firstChar.toString())
                            }
                            else -> {
                                val temp = firstChar.toString() + firstSubChar.toString()
                                firstChar = inputChar
                                firstSubChar = nullChar
                                composedResult = inputChar
                                setFirstInputTimer(inputTime = inputTime)
                                return UpdatedChars(temp, firstChar.toString())
                            }
                        }
                    }
                    'ㄴ' -> {
                        if (firstSubChar != nullChar) {
                            val temp = firstChar.toString() + firstSubChar.toString()
                            firstChar = inputChar
                            firstSubChar = nullChar
                            composedResult = inputChar
                            setFirstInputTimer(inputTime = inputTime)
                            return UpdatedChars(temp, firstChar.toString())
                        } else {
                            var temp: String? = null
                            setSecondInputTimer(inputTime = inputTime)
                            firstChar = if (secondInputTime - firstInputTime >= separationTime1) {
                                temp = firstChar.toString()
                                inputChar
                            } else 'ㄴ'
                            firstSubChar = nullChar
                            middleChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            setFirstInputTimer(inputTime = inputTime)
                            return UpdatedChars(temp, firstChar.toString())
                        }
                    }
                    'ㄷ' -> {
                        if (firstSubChar == 'ㄷ') {
                            var temp: String? = null
                            setSecondInputTimer(inputTime = inputTime)
                            if (secondInputTime - firstInputTime >= separationTime1) {
                                temp = firstChar.toString() + firstSubChar.toString()
                                firstChar = 'ㄷ'
                                firstSubChar = nullChar
                                composedResult = inputChar
                            } else {
                                firstChar = 'ㄾ'
                                firstSubChar = nullChar
                                composeResult()
                                composedResult = firstChar
                            }
                            setFirstInputTimer(inputTime = inputTime)
                            return UpdatedChars(temp, composedResult.toString())
                        } else {
                            val temp = firstChar.toString() + firstSubChar.toString()
                            firstChar = inputChar
                            firstSubChar = nullChar
                            composedResult = inputChar
                            setFirstInputTimer(inputTime = inputTime)
                            return UpdatedChars(temp, composedResult.toString())
                        }
                    }
                    'ㅂ' -> {
                        when (firstSubChar) {
                            'ㅃ' -> {
                                var temp: String? = null
                                setSecondInputTimer(inputTime = inputTime)
                                firstChar = if (secondInputTime - firstInputTime >= separationTime1) {
                                    temp = firstChar.toString() + firstSubChar.toString()
                                    inputChar
                                } else 'ㄹ'
                                firstSubChar = if (secondInputTime - firstInputTime >= separationTime1) {
                                    nullChar
                                } else 'ㅍ'
                                middleChar = nullChar
                                finalChar = nullChar
                                finalSubChar = nullChar
                                composeResult()
                                composedResult = nullChar
                                setFirstInputTimer(inputTime = inputTime)
                                return UpdatedChars(temp,
                                    firstChar.toString() + firstSubChar.toString()
                                )
                            }
                            'ㅍ', nullChar -> {
                                var temp: String? = null
                                setSecondInputTimer(inputTime = inputTime)
                                firstChar = if (secondInputTime - firstInputTime >= separationTime1) {
                                    temp = firstChar.toString() + firstSubChar.toString()
                                    inputChar
                                } else 'ㄼ'
                                firstSubChar = nullChar
                                middleChar = nullChar
                                finalChar = nullChar
                                finalSubChar = nullChar
                                composeResult()
                                composedResult = nullChar
                                setFirstInputTimer(inputTime = inputTime)
                                return UpdatedChars(temp, firstChar.toString())
                            }
                            else -> {
                                val temp = firstChar.toString() + firstSubChar.toString()
                                firstChar = inputChar
                                firstSubChar = nullChar
                                composedResult = inputChar
                                setFirstInputTimer(inputTime = inputTime)
                                return UpdatedChars(temp, composedResult.toString())
                            }
                        }
                    }
                    'ㅅ' -> {
                        when (firstSubChar) {
                            'ㅆ' -> {
                                var temp: String? = null
                                setSecondInputTimer(inputTime = inputTime)
                                firstChar = if (secondInputTime - firstInputTime >= separationTime1) {
                                    temp = firstChar.toString() + firstSubChar.toString()
                                    inputChar
                                } else 'ㅀ'
                                firstSubChar = nullChar
                                middleChar = nullChar
                                finalChar = nullChar
                                finalSubChar = nullChar
                                composeResult()
                                composedResult = nullChar
                                setFirstInputTimer(inputTime = inputTime)
                                return UpdatedChars(temp, firstChar.toString())
                            }
                            nullChar -> {
                                var temp: String? = null
                                setSecondInputTimer(inputTime = inputTime)
                                firstChar = if (secondInputTime - firstInputTime >= separationTime1) {
                                    temp = firstChar.toString()
                                    inputChar
                                } else 'ㄽ'
                                firstSubChar = nullChar
                                middleChar = nullChar
                                finalChar = nullChar
                                finalSubChar = nullChar
                                composeResult()
                                composedResult = nullChar
                                setFirstInputTimer(inputTime = inputTime)
                                return UpdatedChars(temp, firstChar.toString())
                            }
                            else -> {
                                val temp = firstChar.toString() + firstSubChar.toString()
                                firstChar = inputChar
                                firstSubChar = nullChar
                                composedResult = inputChar
                                setFirstInputTimer(inputTime = inputTime)
                                return UpdatedChars(temp, composedResult.toString())
                            }
                        }
                    }
                    'ㅇ' -> {
                        when (firstSubChar) {
                            'ㅇ' -> {
                                var temp: String? = null
                                setSecondInputTimer(inputTime = inputTime)
                                firstChar = if (secondInputTime - firstInputTime >= separationTime1) {
                                    temp = firstChar.toString() + firstSubChar.toString()
                                    inputChar
                                } else 'ㄻ'
                                firstSubChar = nullChar
                                middleChar = nullChar
                                finalChar = nullChar
                                finalSubChar = nullChar
                                composeResult()
                                composedResult = nullChar
                                setFirstInputTimer(inputTime = inputTime)
                                return UpdatedChars(temp, firstChar.toString())
                            }
                            nullChar -> {
                                var temp: String? = null
                                setSecondInputTimer(inputTime = inputTime)
                                firstChar = if (secondInputTime - firstInputTime >= separationTime1) {
                                    temp = firstChar.toString()
                                    inputChar
                                } else 'ㄹ'
                                firstSubChar = if (secondInputTime - firstInputTime >= separationTime1) {
                                    nullChar
                                } else 'ㅇ'
                                middleChar = nullChar
                                finalChar = nullChar
                                finalSubChar = nullChar
                                composeResult()
                                composedResult = nullChar
                                setFirstInputTimer(inputTime = inputTime)
                                return UpdatedChars(temp,
                                    firstChar.toString() + firstSubChar.toString()
                                )
                            }
                            else -> {
                                val temp = firstChar.toString() + firstSubChar.toString()
                                firstChar = inputChar
                                firstSubChar = nullChar
                                composedResult = inputChar
                                setFirstInputTimer(inputTime = inputTime)
                                return UpdatedChars(temp, composedResult.toString())
                            }
                        }
                    }
                    'ㅣ', 'ㅡ' -> {
                        if (firstSubChar != nullChar) {
                            var temp: String? = null
                            setSecondInputTimer(inputTime = inputTime)
                            if (secondInputTime - firstInputTime >= separationTime1) {
                                temp = firstChar.toString() + firstSubChar.toString()
                                state = -1
                                firstChar = nullChar
                                firstSubChar = nullChar
                                middleChar = inputChar
                                composedResult = inputChar
                            } else {
                                temp = firstChar.toString()
                                state = 2
                                firstChar = firstSubChar
                                firstSubChar = nullChar
                                middleChar = inputChar
                                finalChar = nullChar
                                finalSubChar = nullChar
                                composeResult()
                            }
                            setFirstInputTimer(inputTime = inputTime)
                            return UpdatedChars(temp, composedResult.toString())
                        } else {
                            var temp: String? = null
                            setSecondInputTimer(inputTime = inputTime)
                            if (secondInputTime - firstInputTime >= separationTime1) {
                                temp = firstChar.toString()
                                state = -1
                                firstChar = nullChar
                                firstSubChar = nullChar
                                middleChar = inputChar
                                composedResult = inputChar
                            } else {
                                state = 2
                                firstSubChar = nullChar
                                middleChar = inputChar
                                finalChar = nullChar
                                finalSubChar = nullChar
                                composeResult()
                            }
                            setFirstInputTimer(inputTime = inputTime)
                            return UpdatedChars(temp, composedResult.toString())
                        }
                    }
                    'ᆞ' -> {
                        if (firstSubChar != nullChar) {
                            var temp: String? = null
                            setSecondInputTimer(inputTime = inputTime)
                            if (secondInputTime - firstInputTime >= separationTime1) {
                                temp = firstChar.toString()
                                state = -1
                                firstChar = nullChar
                                firstSubChar = nullChar
                                middleChar = inputChar
                                composedResult = inputChar
                            } else {
                                state = 2
                                firstSubChar = nullChar
                                middleChar = inputChar
                                finalChar = nullChar
                                finalSubChar = nullChar
                                composeResult()
                            }
                            setFirstInputTimer(inputTime = inputTime)
                            return UpdatedChars(temp, firstChar.toString() + middleChar.toString())
                        } else {
                            var temp: String? = null
                            setSecondInputTimer(inputTime = inputTime)
                            if (secondInputTime - firstInputTime >= separationTime1) {
                                temp = firstChar.toString()
                                state = -1
                                firstChar = nullChar
                                firstSubChar = nullChar
                                middleChar = inputChar
                                composedResult = inputChar
                            } else {
                                state = 2
                                firstSubChar = nullChar
                                middleChar = inputChar
                                composeResult()
                            }
                            setFirstInputTimer(inputTime = inputTime)
                            return UpdatedChars(temp, firstChar.toString() + middleChar.toString())
                        }
                    }
                    else -> {
                        val temp = firstChar.toString() + firstSubChar.toString()
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = inputChar
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, composedResult.toString())
                    }
                }
                'ㄺ' -> when (inputChar) {
                    'ㄱ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        firstChar = if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = firstChar.toString()
                            inputChar
                        } else 'ㄹ'
                        firstSubChar = if (secondInputTime - firstInputTime >= separationTime1) {
                            nullChar
                        } else 'ㅋ'
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp,
                            firstChar.toString() + firstSubChar.toString()
                        )
                    }
                    'ㅣ', 'ㅡ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = firstChar.toString()
                            state = -1
                            firstChar = nullChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            composedResult = inputChar
                        } else {
                            temp = "ㄹ"
                            state = 2
                            firstChar = 'ㄱ'
                            firstSubChar = nullChar
                            middleChar = inputChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    'ᆞ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = firstChar.toString()
                            state = -1
                            firstChar = nullChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            composedResult = inputChar
                        } else {
                            temp = "ㄹ"
                            state = 2
                            firstChar = 'ㄱ'
                            firstSubChar = nullChar
                            middleChar = inputChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp,
                            firstChar.toString() + middleChar.toString()
                        )
                    }
                    else -> {
                        val temp = firstChar.toString() + firstSubChar.toString()
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㄻ' -> when (inputChar) {
                    'ㅇ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        firstChar = if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = firstChar.toString()
                            inputChar
                        } else 'ㄹ'
                        firstSubChar = if (secondInputTime - firstInputTime >= separationTime1) {
                            nullChar
                        } else 'ㅇ'
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp,
                            firstChar.toString() + firstSubChar.toString()
                        )
                    }
                    'ㅣ', 'ㅡ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = firstChar.toString()
                            state = -1
                            firstChar = nullChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            composedResult = inputChar
                        } else {
                            temp = "ㄹ"
                            state = 2
                            firstChar = 'ㅁ'
                            firstSubChar = nullChar
                            middleChar = inputChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    'ᆞ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = firstChar.toString()
                            state = -1
                            firstChar = nullChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            composedResult = inputChar
                        } else {
                            temp = "ㄹ"
                            state = 2
                            firstChar = 'ㅁ'
                            firstSubChar = nullChar
                            middleChar = inputChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp,
                            firstChar.toString() + middleChar.toString()
                        )
                    }
                    else -> {
                        val temp = firstChar.toString() + firstSubChar.toString()
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㄼ' -> when (inputChar) {
                    'ㅂ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        firstChar = if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = firstChar.toString()
                            inputChar
                        } else 'ㄹ'
                        firstSubChar = if (secondInputTime - firstInputTime >= separationTime1) {
                            nullChar
                        } else 'ㅃ'
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp,
                            firstChar.toString() + firstSubChar.toString()
                        )
                    }
                    'ㅣ', 'ㅡ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = firstChar.toString()
                            state = -1
                            firstChar = nullChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            composedResult = inputChar
                        } else {
                            temp = "ㄹ"
                            state = 2
                            firstChar = 'ㅂ'
                            firstSubChar = nullChar
                            middleChar = inputChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    'ᆞ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = firstChar.toString()
                            state = -1
                            firstChar = nullChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            composedResult = inputChar
                        } else {
                            temp = "ㄹ"
                            state = 2
                            firstChar = 'ㅂ'
                            firstSubChar = nullChar
                            middleChar = inputChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp,
                            firstChar.toString() + middleChar.toString()
                        )
                    }
                    else -> {
                        val temp = firstChar.toString() + firstSubChar.toString()
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㄽ' -> when (inputChar) {
                    'ㅅ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        firstChar = if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = firstChar.toString()
                            inputChar
                        } else 'ㅀ'
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, firstChar.toString())
                    }
                    'ㅣ', 'ㅡ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = firstChar.toString()
                            state = -1
                            firstChar = nullChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            composedResult = inputChar
                        } else {
                            temp = "ㄹ"
                            state = 2
                            firstChar = 'ㅅ'
                            firstSubChar = nullChar
                            middleChar = inputChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    'ᆞ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = firstChar.toString()
                            state = -1
                            firstChar = nullChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            composedResult = inputChar
                        } else {
                            temp = "ㄹ"
                            state = 2
                            firstChar = 'ㅅ'
                            firstSubChar = nullChar
                            middleChar = inputChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp,
                            firstChar.toString() + middleChar.toString()
                        )
                    }
                    else -> {
                        val temp = firstChar.toString() + firstSubChar.toString()
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㄾ' -> when (inputChar) {
                    'ㄷ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = firstChar.toString()
                            firstChar = inputChar
                            firstSubChar = nullChar
                            composedResult = inputChar
                        } else {
                            firstChar = 'ㄹ'
                            firstSubChar = 'ㄸ'
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, firstChar.toString() + firstSubChar.toString())
                    }
                    'ㅣ', 'ㅡ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = firstChar.toString()
                            state = -1
                            firstChar = nullChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            composedResult = inputChar
                        } else {
                            temp = "ㄹ"
                            state = 2
                            firstChar = 'ㅌ'
                            firstSubChar = nullChar
                            middleChar = inputChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    'ᆞ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = firstChar.toString()
                            state = -1
                            firstChar = nullChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            composedResult = inputChar
                        } else {
                            temp = "ㄹ"
                            state = 2
                            firstChar = 'ㅌ'
                            firstSubChar = nullChar
                            middleChar = inputChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp,
                            firstChar.toString() + middleChar.toString()
                        )
                    }
                    else -> {
                        val temp = firstChar.toString() + firstSubChar.toString()
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㄿ' -> when (inputChar) {
                    'ㅂ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = firstChar.toString()
                            state = -1
                            firstChar = nullChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            composedResult = inputChar
                        } else {
                            firstChar = 'ㄹ'
                            firstSubChar = 'ㅃ'
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, firstChar.toString() + firstSubChar.toString())
                    }
                    'ㅣ', 'ㅡ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = firstChar.toString()
                            state = -1
                            firstChar = nullChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            composedResult = inputChar
                        } else {
                            temp = "ㄹ"
                            state = 2
                            firstChar = 'ㅍ'
                            firstSubChar = nullChar
                            middleChar = inputChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    'ᆞ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = firstChar.toString()
                            state = -1
                            firstChar = nullChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            composedResult = inputChar
                        } else {
                            temp = "ㄹ"
                            state = 2
                            firstChar = 'ㅍ'
                            firstSubChar = nullChar
                            middleChar = inputChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp,
                            firstChar.toString() + middleChar.toString()
                        )
                    }
                    else -> {
                        val temp = firstChar.toString() + firstSubChar.toString()
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㅀ' -> when (inputChar) {
                    'ㅅ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        firstChar = if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = firstChar.toString()
                            inputChar
                        } else 'ㄹ'
                        firstSubChar = if (secondInputTime - firstInputTime >= separationTime1) {
                            nullChar
                        } else 'ㅆ'
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp,
                            firstChar.toString() + firstSubChar.toString()
                        )
                    }
                    'ㅣ', 'ㅡ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = firstChar.toString()
                            state = -1
                            firstChar = nullChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            composedResult = inputChar
                        } else {
                            temp = "ㄹ"
                            state = 2
                            firstChar = 'ㅎ'
                            firstSubChar = nullChar
                            middleChar = inputChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    'ᆞ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = firstChar.toString()
                            state = -1
                            firstChar = nullChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            composedResult = inputChar
                        } else {
                            temp = "ㄹ"
                            state = 2
                            firstChar = 'ㅎ'
                            firstSubChar = nullChar
                            middleChar = inputChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp,
                            firstChar.toString() + middleChar.toString()
                        )
                    }
                    else -> {
                        val temp = firstChar.toString() + firstSubChar.toString()
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㅁ' -> when (inputChar) {
                    'ㅇ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        firstChar = if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = firstChar.toString()
                            inputChar
                        } else 'ㅇ'
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, firstChar.toString())
                    }
                    'ㅣ', 'ㅡ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = firstChar.toString()
                            state = -1
                            firstChar = nullChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            composedResult = inputChar
                        } else {
                            state = 2
                            firstSubChar = nullChar
                            middleChar = inputChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    'ᆞ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = firstChar.toString()
                            state = -1
                            firstChar = nullChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            composedResult = inputChar
                        } else {
                            state = 2
                            firstSubChar = nullChar
                            middleChar = inputChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
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
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㅂ' -> when (inputChar) {
                    'ㅂ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        firstChar = if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = firstChar.toString() + firstSubChar.toString()
                            inputChar
                        } else 'ㅍ'
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, firstChar.toString())
                    }
                    'ㅅ' -> {
                        when (firstSubChar) {
                            'ㅎ' -> {
                                var temp: String? = null
                                setSecondInputTimer(inputTime = inputTime)
                                firstChar = if (secondInputTime - firstInputTime >= separationTime1) {
                                    temp = firstChar.toString() + firstSubChar.toString()
                                    inputChar
                                } else 'ㅂ'
                                firstSubChar = if (secondInputTime - firstInputTime >= separationTime1) {
                                    nullChar
                                } else 'ㅆ'
                                middleChar = nullChar
                                finalChar = nullChar
                                finalSubChar = nullChar
                                composeResult()
                                composedResult = nullChar
                                setFirstInputTimer(inputTime = inputTime)
                                return UpdatedChars(temp,
                                    firstChar.toString() + firstSubChar.toString()
                                )
                            }
                            'ㅆ', nullChar -> {
                                var temp: String? = null
                                setSecondInputTimer(inputTime = inputTime)
                                firstChar = if (secondInputTime - firstInputTime >= separationTime1) {
                                    temp = firstChar.toString() + firstSubChar.toString()
                                    inputChar
                                } else 'ㅄ'
                                firstSubChar = nullChar
                                middleChar = nullChar
                                finalChar = nullChar
                                finalSubChar = nullChar
                                composeResult()
                                composedResult = nullChar
                                setFirstInputTimer(inputTime = inputTime)
                                return UpdatedChars(temp, firstChar.toString())
                            }
                            else -> {
                                val temp = firstChar.toString() + firstSubChar.toString()
                                firstChar = inputChar
                                firstSubChar = nullChar
                                composedResult = inputChar
                                setFirstInputTimer(inputTime = inputTime)
                                return UpdatedChars(temp, composedResult.toString())
                            }
                        }
                    }
                    'ㅣ', 'ㅡ' -> {
                        if (firstSubChar != nullChar) {
                            var temp: String? = null
                            setSecondInputTimer(inputTime = inputTime)
                            if (secondInputTime - firstInputTime >= separationTime1) {
                                temp = firstChar.toString() + firstSubChar.toString()
                                state = -1
                                firstChar = nullChar
                                firstSubChar = nullChar
                                middleChar = inputChar
                                composedResult = inputChar
                            } else {
                                temp = "ㅂ"
                                state = 2
                                firstChar = firstSubChar
                                firstSubChar = nullChar
                                middleChar = inputChar
                                composeResult()
                            }
                            setFirstInputTimer(inputTime = inputTime)
                            return UpdatedChars(temp, composedResult.toString())
                        } else {
                            var temp: String? = null
                            setSecondInputTimer(inputTime = inputTime)
                            if (secondInputTime - firstInputTime >= separationTime1) {
                                temp = firstChar.toString()
                                state = -1
                                firstChar = nullChar
                                firstSubChar = nullChar
                                middleChar = inputChar
                                composedResult = inputChar
                            } else {
                                state = 2
                                firstSubChar = nullChar
                                middleChar = inputChar
                                composeResult()
                            }
                            setFirstInputTimer(inputTime = inputTime)
                            return UpdatedChars(temp, composedResult.toString())
                        }
                    }
                    'ᆞ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = firstChar.toString()
                            state = -1
                            firstChar = nullChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            composedResult = inputChar
                        } else {
                            state = 2
                            firstSubChar = nullChar
                            middleChar = inputChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp,
                            firstChar.toString() + middleChar.toString()
                        )
                    }
                    else -> {
                        val temp = firstChar.toString() + firstSubChar.toString()
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        composedResult = inputChar
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, composedResult.toString())
                    }
                }
                'ㅃ' -> when (inputChar) {
                    'ㅂ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        firstChar = if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = firstChar.toString()
                            inputChar
                        } else 'ㅂ'
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, firstChar.toString())
                    }
                    'ㅣ', 'ㅡ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = firstChar.toString()
                            state = -1
                            firstChar = nullChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            composedResult = inputChar
                        } else {
                            state = 2
                            firstSubChar = nullChar
                            middleChar = inputChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    'ᆞ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = firstChar.toString()
                            state = -1
                            firstChar = nullChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            composedResult = inputChar
                        } else {
                            state = 2
                            firstSubChar = nullChar
                            middleChar = inputChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
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
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㅄ' -> when (inputChar) {
                    'ㅅ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        firstChar = if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = firstChar.toString()
                            inputChar
                        } else 'ㅂ'
                        firstSubChar = if (secondInputTime - firstInputTime >= separationTime1) {
                            nullChar
                        } else 'ㅎ'
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp,
                            firstChar.toString() + firstSubChar.toString()
                        )
                    }
                    'ㅣ', 'ㅡ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = firstChar.toString()
                            state = -1
                            firstChar = nullChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            composedResult = inputChar
                        } else {
                            temp = "ㅂ"
                            state = 2
                            firstChar = 'ㅅ'
                            firstSubChar = nullChar
                            middleChar = inputChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    'ᆞ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = firstChar.toString()
                            state = -1
                            firstChar = nullChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            composedResult = inputChar
                        } else {
                            temp = "ㅂ"
                            state = 2
                            firstChar = 'ㅅ'
                            firstSubChar = nullChar
                            middleChar = inputChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
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
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㅅ' -> when (inputChar) {
                    'ㅅ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        firstChar = if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = firstChar.toString()
                            inputChar
                        } else 'ㅎ'
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, firstChar.toString())
                    }
                    'ㅣ', 'ㅡ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = firstChar.toString()
                            state = -1
                            firstChar = nullChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            composedResult = inputChar
                        } else {
                            state = 2
                            firstSubChar = nullChar
                            middleChar = inputChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    'ᆞ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = firstChar.toString()
                            state = -1
                            firstChar = nullChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            composedResult = inputChar
                        } else {
                            state = 2
                            firstSubChar = nullChar
                            middleChar = inputChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
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
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㅆ' -> when (inputChar) {
                    'ㅅ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        firstChar = if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = firstChar.toString()
                            inputChar
                        } else 'ㅅ'
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, firstChar.toString())
                    }
                    'ㅣ', 'ㅡ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = firstChar.toString()
                            state = -1
                            firstChar = nullChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            composedResult = inputChar
                        } else {
                            state = 2
                            firstSubChar = nullChar
                            middleChar = inputChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    'ᆞ' -> {
                        state = 2
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        setFirstInputTimer(inputTime = inputTime)
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
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㅇ' -> when (inputChar) {
                    'ㅇ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        firstChar = if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = firstChar.toString()
                            inputChar
                        } else 'ㅁ'
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, firstChar.toString())
                    }
                    'ㅣ', 'ㅡ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = firstChar.toString()
                            state = -1
                            firstChar = nullChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            composedResult = inputChar
                        } else {
                            state = 2
                            firstSubChar = nullChar
                            middleChar = inputChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    'ᆞ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = firstChar.toString()
                            state = -1
                            firstChar = nullChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            composedResult = inputChar
                        } else {
                            state = 2
                            firstSubChar = nullChar
                            middleChar = inputChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
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
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㅈ' -> when (inputChar) {
                    'ㅈ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        firstChar = if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = firstChar.toString()
                            inputChar
                        } else 'ㅊ'
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, firstChar.toString())
                    }
                    'ㅣ', 'ㅡ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = firstChar.toString()
                            state = -1
                            firstChar = nullChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            composedResult = inputChar
                        } else {
                            state = 2
                            firstSubChar = nullChar
                            middleChar = inputChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    'ᆞ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = firstChar.toString()
                            state = -1
                            firstChar = nullChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            composedResult = inputChar
                        } else {
                            state = 2
                            firstSubChar = nullChar
                            middleChar = inputChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
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
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㅉ' -> when (inputChar) {
                    'ㅈ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        firstChar = if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = firstChar.toString()
                            inputChar
                        } else 'ㅈ'
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, firstChar.toString())
                    }
                    'ㅣ', 'ㅡ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = firstChar.toString()
                            state = -1
                            firstChar = nullChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            composedResult = inputChar
                        } else {
                            state = 2
                            firstSubChar = nullChar
                            middleChar = inputChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    'ᆞ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = firstChar.toString()
                            state = -1
                            firstChar = nullChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            composedResult = inputChar
                        } else {
                            state = 2
                            firstSubChar = nullChar
                            middleChar = inputChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
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
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㅊ' -> when (inputChar) {
                    'ㅈ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        firstChar = if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = firstChar.toString()
                            inputChar
                        } else 'ㅉ'
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, firstChar.toString())
                    }
                    'ㅣ', 'ㅡ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = firstChar.toString()
                            state = -1
                            firstChar = nullChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            composedResult = inputChar
                        } else {
                            state = 2
                            firstSubChar = nullChar
                            middleChar = inputChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    'ᆞ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = firstChar.toString()
                            state = -1
                            firstChar = nullChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            composedResult = inputChar
                        } else {
                            state = 2
                            firstSubChar = nullChar
                            middleChar = inputChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
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
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㅋ' -> when (inputChar) {
                    'ㄱ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        firstChar = if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = firstChar.toString()
                            inputChar
                        } else 'ㄲ'
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, firstChar.toString())
                    }
                    'ㅣ', 'ㅡ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = firstChar.toString()
                            state = -1
                            firstChar = nullChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            composedResult = inputChar
                        } else {
                            state = 2
                            firstSubChar = nullChar
                            middleChar = inputChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    'ᆞ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = firstChar.toString()
                            state = -1
                            firstChar = nullChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            composedResult = inputChar
                        } else {
                            state = 2
                            firstSubChar = nullChar
                            middleChar = inputChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
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
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㅌ' -> when (inputChar) {
                    'ㄷ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        firstChar = if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = firstChar.toString()
                            inputChar
                        } else 'ㄸ'
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, firstChar.toString())
                    }
                    'ㅣ', 'ㅡ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = firstChar.toString()
                            state = -1
                            firstChar = nullChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            composedResult = inputChar
                        } else {
                            state = 2
                            firstSubChar = nullChar
                            middleChar = inputChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    'ᆞ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = firstChar.toString()
                            state = -1
                            firstChar = nullChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            composedResult = inputChar
                        } else {
                            state = 2
                            firstSubChar = nullChar
                            middleChar = inputChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
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
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㅍ' -> when (inputChar) {
                    'ㅂ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        firstChar = if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = firstChar.toString()
                            inputChar
                        } else 'ㅃ'
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, firstChar.toString())
                    }
                    'ㅣ', 'ㅡ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = firstChar.toString()
                            state = -1
                            firstChar = nullChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            composedResult = inputChar
                        } else {
                            state = 2
                            firstSubChar = nullChar
                            middleChar = inputChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(null, composedResult.toString())
                    }
                    'ᆞ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = firstChar.toString()
                            state = -1
                            firstChar = nullChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            composedResult = inputChar
                        } else {
                            state = 2
                            firstSubChar = nullChar
                            middleChar = inputChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
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
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㅎ' -> when (inputChar) {
                    'ㅅ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        firstChar = if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = firstChar.toString()
                            inputChar
                        } else 'ㅆ'
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, firstChar.toString())
                    }
                    'ㅣ', 'ㅡ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = firstChar.toString()
                            state = -1
                            firstChar = nullChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            composedResult = inputChar
                        } else {
                            state = 2
                            firstSubChar = nullChar
                            middleChar = inputChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    'ᆞ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = firstChar.toString()
                            state = -1
                            firstChar = nullChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            composedResult = inputChar
                        } else {
                            state = 2
                            firstSubChar = nullChar
                            middleChar = inputChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
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
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                else -> {
                    initChar()
                    state = 0
                    composedResult = nullChar
                    setFirstInputTimer(inputTime = inputTime)
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
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, middleChar.toString())
                    }
                    else -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime1) {
                            composeResult()
                            temp = composedResult.toString()
                            state = 1
                            firstChar = inputChar
                            firstSubChar = nullChar
                            middleChar = nullChar
                            composedResult = inputChar
                        } else {
                            state = 3
                            firstSubChar = nullChar
                            finalChar = inputChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, composedResult.toString())
                    }
                }
                'ㅏ' -> when (inputChar) {
                    'ㅣ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        firstSubChar = nullChar
                        if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = composedResult.toString()
                            state = -1
                            firstChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = inputChar
                        } else {
                            middleChar = 'ㅐ'
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    'ᆞ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        firstSubChar = nullChar
                        if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = composedResult.toString()
                            state = -1
                            firstChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = inputChar
                        } else {
                            middleChar = 'ㅑ'
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, composedResult.toString())
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
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, middleChar.toString())
                    }
                    else -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime1) {
                            composeResult()
                            temp = composedResult.toString()
                            state = 1
                            firstChar = inputChar
                            firstSubChar = nullChar
                            middleChar = nullChar
                            composedResult = inputChar
                        } else {
                            state = 3
                            firstSubChar = nullChar
                            finalChar = inputChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, composedResult.toString())
                    }
                }
                'ㅑ' -> when (inputChar) {
                    'ㅣ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        firstSubChar = nullChar
                        if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = composedResult.toString()
                            state = -1
                            firstChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = inputChar
                        } else {
                            middleChar = 'ㅒ'
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    'ᆞ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        firstSubChar = nullChar
                        if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = composedResult.toString()
                            state = -1
                            firstChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = inputChar
                        } else {
                            middleChar = 'ㅏ'
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, composedResult.toString())
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
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, middleChar.toString())
                    }
                    else -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime1) {
                            composeResult()
                            temp = composedResult.toString()
                            state = 1
                            firstChar = inputChar
                            firstSubChar = nullChar
                            middleChar = nullChar
                            composedResult = inputChar
                        } else {
                            state = 3
                            firstSubChar = nullChar
                            finalChar = inputChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, composedResult.toString())
                    }
                }
                'ㅓ' -> when (inputChar) {
                    'ㅣ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        firstSubChar = nullChar
                        if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = composedResult.toString()
                            state = -1
                            firstChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = inputChar
                        } else {
                            middleChar = 'ㅔ'
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, composedResult.toString())
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
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, middleChar.toString())
                    }
                    else -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime1) {
                            composeResult()
                            temp = composedResult.toString()
                            state = 1
                            firstChar = inputChar
                            firstSubChar = nullChar
                            middleChar = nullChar
                            composedResult = inputChar
                        } else {
                            state = 3
                            firstSubChar = nullChar
                            finalChar = inputChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, composedResult.toString())
                    }
                }
                'ㅕ' -> when (inputChar) {
                    'ㅣ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        firstSubChar = nullChar
                        if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = composedResult.toString()
                            state = -1
                            firstChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = inputChar
                        } else {
                            middleChar = 'ㅖ'
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, composedResult.toString())
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
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, middleChar.toString())
                    }
                    else -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime1) {
                            composeResult()
                            temp = composedResult.toString()
                            state = 1
                            firstChar = inputChar
                            firstSubChar = nullChar
                            middleChar = nullChar
                            composedResult = inputChar
                        } else {
                            state = 3
                            firstSubChar = nullChar
                            finalChar = inputChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, composedResult.toString())
                    }
                }
                'ㅗ' -> when (inputChar) {
                    'ㅣ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        firstSubChar = nullChar
                        if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = composedResult.toString()
                            state = -1
                            firstChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = inputChar
                        } else {
                            middleChar = 'ㅚ'
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, composedResult.toString())
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
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, middleChar.toString())
                    }
                    else -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime1) {
                            composeResult()
                            temp = composedResult.toString()
                            state = 1
                            firstChar = inputChar
                            firstSubChar = nullChar
                            middleChar = nullChar
                            composedResult = inputChar
                        } else {
                            state = 3
                            firstSubChar = nullChar
                            finalChar = inputChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, composedResult.toString())
                    }
                }
                'ㅘ' -> when (inputChar) {
                    'ㅣ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        firstSubChar = nullChar
                        if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = composedResult.toString()
                            state = -1
                            firstChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = inputChar
                        } else {
                            middleChar = 'ㅙ'
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, composedResult.toString())
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
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, middleChar.toString())
                    }
                    else -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime1) {
                            composeResult()
                            temp = composedResult.toString()
                            state = 1
                            firstChar = inputChar
                            firstSubChar = nullChar
                            middleChar = nullChar
                            composedResult = inputChar
                        } else {
                            state = 3
                            firstSubChar = nullChar
                            finalChar = inputChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, composedResult.toString())
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
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, middleChar.toString())
                    }
                    'ᆞ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        firstSubChar = nullChar
                        if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = composedResult.toString()
                            state = -1
                            firstChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = inputChar
                        } else {
                            middleChar = 'ㅘ'
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    else -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime1) {
                            composeResult()
                            temp = composedResult.toString()
                            state = 1
                            firstChar = inputChar
                            firstSubChar = nullChar
                            middleChar = nullChar
                            composedResult = inputChar
                        } else {
                            state = 3
                            firstSubChar = nullChar
                            finalChar = inputChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, composedResult.toString())
                    }
                }
                'ㅜ' -> when (inputChar) {
                    'ㅣ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        firstSubChar = nullChar
                        if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = composedResult.toString()
                            state = -1
                            firstChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = inputChar
                        } else {
                            middleChar = 'ㅟ'
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    'ᆞ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        firstSubChar = nullChar
                        if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = composedResult.toString()
                            state = -1
                            firstChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = inputChar
                        } else {
                            middleChar = 'ㅠ'
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, composedResult.toString())
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
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, middleChar.toString())
                    }
                    else -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime1) {
                            composeResult()
                            temp = composedResult.toString()
                            state = 1
                            firstChar = inputChar
                            firstSubChar = nullChar
                            middleChar = nullChar
                            composedResult = inputChar
                        } else {
                            state = 3
                            firstSubChar = nullChar
                            finalChar = inputChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, composedResult.toString())
                    }
                }
                'ㅝ' -> when (inputChar) {
                    'ㅣ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        firstSubChar = nullChar
                        if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = composedResult.toString()
                            state = -1
                            firstChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = inputChar
                        } else {
                            middleChar = 'ㅞ'
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, composedResult.toString())
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
                        setFirstInputTimer(inputTime = inputTime)
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
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, middleChar.toString())
                    }
                    else -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime1) {
                            composeResult()
                            temp = composedResult.toString()
                            state = 1
                            firstChar = inputChar
                            firstSubChar = nullChar
                            middleChar = nullChar
                            composedResult = inputChar
                        } else {
                            state = 3
                            firstSubChar = nullChar
                            finalChar = inputChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, composedResult.toString())
                    }
                }
                'ㅠ' -> when (inputChar) {
                    'ㅣ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        firstSubChar = nullChar
                        if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = composedResult.toString()
                            state = -1
                            firstChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = inputChar
                        } else {
                            middleChar = 'ㅝ'
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    'ᆞ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        firstSubChar = nullChar
                        if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = composedResult.toString()
                            state = -1
                            firstChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = inputChar
                        } else {
                            middleChar = 'ㅜ'
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, composedResult.toString())
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
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, middleChar.toString())
                    }
                    else -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime1) {
                            composeResult()
                            temp = composedResult.toString()
                            state = 1
                            firstChar = inputChar
                            firstSubChar = nullChar
                            middleChar = nullChar
                            composedResult = inputChar
                        } else {
                            state = 3
                            firstSubChar = nullChar
                            finalChar = inputChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, composedResult.toString())
                    }
                }
                'ㅡ' -> when (inputChar) {
                    'ㅣ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        firstSubChar = nullChar
                        if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = composedResult.toString()
                            state = -1
                            firstChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = inputChar
                        } else {
                            middleChar = 'ㅢ'
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    'ᆞ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        firstSubChar = nullChar
                        if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = composedResult.toString()
                            state = -1
                            firstChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = inputChar
                        } else {
                            middleChar = 'ㅜ'
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, composedResult.toString())
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
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, middleChar.toString())
                    }
                    else -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime1) {
                            composeResult()
                            temp = composedResult.toString()
                            state = 1
                            firstChar = inputChar
                            firstSubChar = nullChar
                            middleChar = nullChar
                            composedResult = inputChar
                        } else {
                            state = 3
                            firstSubChar = nullChar
                            finalChar = inputChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, composedResult.toString())
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
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, middleChar.toString())
                    }
                    'ᆞ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        firstSubChar = nullChar
                        if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = composedResult.toString()
                            state = -1
                            firstChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = inputChar
                        } else {
                            middleChar = 'ㅏ'
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, composedResult.toString())
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
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, middleChar.toString())
                    }
                    else -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime1) {
                            composeResult()
                            temp = composedResult.toString()
                            state = 1
                            firstChar = inputChar
                            firstSubChar = nullChar
                            middleChar = nullChar
                            composedResult = inputChar
                        } else {
                            state = 3
                            firstSubChar = nullChar
                            finalChar = inputChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, composedResult.toString())
                    }
                }
                'ᆞ' -> when (inputChar) {
                    'ㅣ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        firstSubChar = nullChar
                        if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = firstChar.toString() + middleChar.toString()
                            state = -1
                            firstChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = inputChar
                        } else {
                            middleChar = 'ㅓ'
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    'ᆞ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        firstSubChar = nullChar
                        if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = firstChar.toString() + middleChar.toString()
                            state = -1
                            firstChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = inputChar
                        } else {
                            middleChar = 'ᆢ'
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp,
                            firstChar.toString() + middleChar.toString()
                        )
                    }
                    'ㅡ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        firstSubChar = nullChar
                        if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = firstChar.toString() + middleChar.toString()
                            state = -1
                            firstChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = inputChar
                        } else {
                            middleChar = 'ㅗ'
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, composedResult.toString())
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
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ᆢ' -> when (inputChar) {
                    'ㅣ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        firstSubChar = nullChar
                        if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = firstChar.toString() + middleChar.toString()
                            state = -1
                            firstChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = inputChar
                        } else {
                            middleChar = 'ㅕ'
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    'ᆞ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        firstSubChar = nullChar
                        if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = firstChar.toString() + middleChar.toString()
                            state = -1
                            firstChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = inputChar
                        } else {
                            middleChar = 'ᆞ'
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp,
                            firstChar.toString() + middleChar.toString()
                        )
                    }
                    'ㅡ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        firstSubChar = nullChar
                        if (secondInputTime - firstInputTime >= separationTime1) {
                            temp = firstChar.toString() + middleChar.toString()
                            state = -1
                            firstChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = inputChar
                        } else {
                            middleChar = 'ㅛ'
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, composedResult.toString())
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
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                else -> {
                    initChar()
                    state = 0
                    composedResult = nullChar
                    setFirstInputTimer(inputTime = inputTime)
                    return UpdatedChars(inputChar.toString(), null)
                }
            }
            3 -> when (finalChar) {
                'ㄱ' -> when (inputChar) {
                    'ㅣ', 'ㅡ' -> {
                        if (finalSubChar != nullChar) {
                            var temp: String? = null
                            setSecondInputTimer(inputTime = inputTime)
                            if (secondInputTime - firstInputTime >= separationTime2) {
                                temp = composedResult.toString() + finalSubChar.toString()
                                state = -1
                                firstChar = nullChar
                                firstSubChar = nullChar
                                middleChar = inputChar
                                finalChar = nullChar
                                finalSubChar = nullChar
                                composedResult = inputChar
                            } else {
                                temp = composedResult.toString()
                                state = 2
                                firstChar = finalSubChar
                                firstSubChar = nullChar
                                middleChar = inputChar
                                finalChar = nullChar
                                finalSubChar = nullChar
                                composeResult()
                            }
                            setFirstInputTimer(inputTime = inputTime)
                            return UpdatedChars(temp, composedResult.toString())
                        } else {
                            var temp: String? = null
                            setSecondInputTimer(inputTime = inputTime)
                            if (secondInputTime - firstInputTime >= separationTime2) {
                                temp = composedResult.toString()
                                state = -1
                                firstChar = nullChar
                                firstSubChar = nullChar
                                middleChar = inputChar
                                finalChar = nullChar
                                composedResult = inputChar
                            } else {
                                finalChar = nullChar
                                composeResult()
                                temp = composedResult.toString()
                                state = 2
                                firstChar = 'ㄱ'
                                firstSubChar = nullChar
                                middleChar = inputChar
                                finalChar = nullChar
                                composeResult()
                            }
                            setFirstInputTimer(inputTime = inputTime)
                            return UpdatedChars(temp, composedResult.toString())
                        }
                    }
                    'ᆞ' -> {
                        if (finalSubChar != nullChar) {
                            var temp: String? = null
                            setSecondInputTimer(inputTime = inputTime)
                            if (secondInputTime - firstInputTime >= separationTime2) {
                                temp = composedResult.toString() + finalSubChar.toString()
                                state = -1
                                firstChar = nullChar
                                firstSubChar = nullChar
                                middleChar = inputChar
                                finalChar = nullChar
                                finalSubChar = nullChar
                                composedResult = inputChar
                            } else {
                                temp = composedResult.toString()
                                state = 2
                                firstChar = finalSubChar
                                firstSubChar = nullChar
                                middleChar = inputChar
                                finalChar = nullChar
                                finalSubChar = nullChar
                                composeResult()
                            }
                            setFirstInputTimer(inputTime = inputTime)
                            return UpdatedChars(temp, firstChar.toString() + middleChar.toString())
                        } else {
                            var temp: String? = null
                            setSecondInputTimer(inputTime = inputTime)
                            if (secondInputTime - firstInputTime >= separationTime2) {
                                temp = composedResult.toString()
                                state = -1
                                firstChar = nullChar
                                firstSubChar = nullChar
                                middleChar = inputChar
                                finalChar = nullChar
                                composedResult = inputChar
                            } else {
                                finalChar = nullChar
                                composeResult()
                                temp = composedResult.toString()
                                state = 2
                                firstChar = 'ㄱ'
                                firstSubChar = nullChar
                                middleChar = inputChar
                                finalChar = nullChar
                                composeResult()
                            }
                            setFirstInputTimer(inputTime = inputTime)
                            return UpdatedChars(temp, firstChar.toString() + middleChar.toString())
                        }
                    }
                    'ㄱ' -> {
                        if (finalSubChar != nullChar) {
                            val temp = composedResult.toString() + finalSubChar.toString()
                            state = 1
                            firstChar = inputChar
                            firstSubChar = nullChar
                            middleChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composedResult = inputChar
                            setFirstInputTimer(inputTime = inputTime)
                            return UpdatedChars(temp, composedResult.toString())
                        } else {
                            var temp: String? = null
                            setSecondInputTimer(inputTime = inputTime)
                            if (secondInputTime - firstInputTime >= separationTime2) {
                                temp = composedResult.toString()
                                state = 1
                                firstChar = inputChar
                                firstSubChar = nullChar
                                middleChar = nullChar
                                finalChar = nullChar
                                finalSubChar = nullChar
                                composedResult = inputChar
                            } else {
                                finalChar = 'ㅋ'
                                firstSubChar = nullChar
                                finalSubChar = nullChar
                                composeResult()
                            }
                            setFirstInputTimer(inputTime = inputTime)
                            return UpdatedChars(temp, composedResult.toString())
                        }
                    }
                    'ㄴ', 'ㄷ', 'ㅂ', 'ㅇ', 'ㅈ' -> {
                        val temp = composedResult.toString() + finalSubChar.toString()
                        state = 1
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, firstChar.toString())
                    }
                    'ㅅ' -> when (finalSubChar) {
                        nullChar, 'ㅆ' -> {
                            var temp: String? = null
                            setSecondInputTimer(inputTime = inputTime)
                            if (secondInputTime - firstInputTime >= separationTime2) {
                                temp = composedResult.toString()
                                state = 1
                                firstChar = inputChar
                                firstSubChar = nullChar
                                middleChar = nullChar
                                finalChar = nullChar
                                finalSubChar = nullChar
                                composedResult = inputChar
                            } else {
                                finalChar = 'ㄳ'
                                finalSubChar = nullChar
                                composeResult()
                            }
                            setFirstInputTimer(inputTime = inputTime)
                            return UpdatedChars(temp, composedResult.toString())
                        }
                        'ㅎ' -> {
                            var temp: String? = null
                            setSecondInputTimer(inputTime = inputTime)
                            if (secondInputTime - firstInputTime >= separationTime2) {
                                temp = composedResult.toString() + finalSubChar.toString()
                                state = 1
                                firstChar = inputChar
                                firstSubChar = nullChar
                                middleChar = nullChar
                                finalChar = nullChar
                                finalSubChar = nullChar
                                composedResult = inputChar
                            } else {
                                firstSubChar = nullChar
                                finalChar = 'ㄱ'
                                finalSubChar = 'ㅆ'
                                composeResult()
                            }
                            setFirstInputTimer(inputTime = inputTime)
                            return UpdatedChars(temp,
                                composedResult.toString() + finalSubChar.toString()
                            )
                        }
                    }
                }
                'ㄲ' -> when (inputChar) {
                    'ㅣ', 'ㅡ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime2) {
                            temp = composedResult.toString()
                            state = -1
                            firstChar = nullChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            composedResult = inputChar
                        } else {
                            finalChar = nullChar
                            composeResult()
                            temp = composedResult.toString()
                            state = 2
                            firstChar = 'ㄲ'
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    'ᆞ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime2) {
                            temp = composedResult.toString()
                            state = -1
                            firstChar = nullChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            composedResult = inputChar
                        } else {
                            finalChar = nullChar
                            composeResult()
                            temp = composedResult.toString()
                            state = 2
                            firstChar = 'ㄲ'
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
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
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, firstChar.toString())
                    }
                    'ㄱ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime2) {
                            temp = composedResult.toString()
                            state = 1
                            firstChar = inputChar
                            firstSubChar = nullChar
                            middleChar = nullChar
                            finalChar = nullChar
                            composedResult = inputChar
                        } else {
                            firstSubChar = nullChar
                            finalChar = 'ㄱ'
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, composedResult.toString())
                    }
                }
                'ㄳ' -> when (inputChar) {
                    'ㅣ', 'ㅡ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime2) {
                            temp = composedResult.toString()
                            state = -1
                            firstChar = nullChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            composedResult = inputChar
                        } else {
                            finalChar = 'ㄱ'
                            composeResult()
                            temp = composedResult.toString()
                            state = 2
                            firstChar = 'ㅅ'
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    'ᆞ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime2) {
                            temp = composedResult.toString()
                            state = -1
                            firstChar = nullChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            composedResult = inputChar
                        } else {
                            finalChar = 'ㄱ'
                            composeResult()
                            temp = composedResult.toString()
                            state = 2
                            firstChar = 'ㅅ'
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
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
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, firstChar.toString())
                    }
                    'ㅅ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime2) {
                            temp = composedResult.toString()
                            state = 1
                            firstChar = inputChar
                            firstSubChar = nullChar
                            middleChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composedResult = inputChar
                        } else {
                            finalChar = 'ㄱ'
                            firstSubChar = nullChar
                            finalSubChar = 'ㅎ'
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp,
                            composedResult.toString() + finalSubChar.toString()
                        )
                    }
                }
                'ㄴ' -> when (inputChar) {
                    'ㅣ', 'ㅡ' -> {
                        if (finalSubChar != nullChar) {
                            var temp: String? = null
                            setSecondInputTimer(inputTime = inputTime)
                            if (secondInputTime - firstInputTime >= separationTime2) {
                                temp = composedResult.toString() + finalSubChar.toString()
                                state = -1
                                firstChar = nullChar
                                firstSubChar = nullChar
                                middleChar = inputChar
                                finalChar = nullChar
                                finalSubChar = nullChar
                                composedResult = inputChar
                            } else {
                                temp = composedResult.toString()
                                state = 2
                                firstChar = finalSubChar
                                firstSubChar = nullChar
                                middleChar = inputChar
                                finalChar = nullChar
                                finalSubChar = nullChar
                                composeResult()
                            }
                            setFirstInputTimer(inputTime = inputTime)
                            return UpdatedChars(temp, composedResult.toString())
                        } else {
                            var temp: String? = null
                            setSecondInputTimer(inputTime = inputTime)
                            if (secondInputTime - firstInputTime >= separationTime2) {
                                temp = composedResult.toString()
                                state = -1
                                firstChar = nullChar
                                firstSubChar = nullChar
                                middleChar = inputChar
                                finalChar = nullChar
                                composedResult = inputChar
                            } else {
                                finalChar = nullChar
                                composeResult()
                                temp = composedResult.toString()
                                state = 2
                                firstChar = 'ㄴ'
                                firstSubChar = nullChar
                                middleChar = inputChar
                                finalChar = nullChar
                                composeResult()
                            }
                            setFirstInputTimer(inputTime = inputTime)
                            return UpdatedChars(temp, composedResult.toString())
                        }
                    }
                    'ᆞ' -> {
                        if (finalSubChar != nullChar) {
                            var temp: String? = null
                            setSecondInputTimer(inputTime = inputTime)
                            if (secondInputTime - firstInputTime >= separationTime2) {
                                temp = composedResult.toString() + finalSubChar.toString()
                                state = -1
                                firstChar = nullChar
                                firstSubChar = nullChar
                                middleChar = inputChar
                                finalChar = nullChar
                                finalSubChar = nullChar
                                composedResult = inputChar
                            } else {
                                temp = composedResult.toString()
                                state = 2
                                firstChar = finalSubChar
                                firstSubChar = nullChar
                                middleChar = inputChar
                                finalChar = nullChar
                                finalSubChar = nullChar
                                composeResult()
                            }
                            setFirstInputTimer(inputTime = inputTime)
                            return UpdatedChars(temp, firstChar.toString() + middleChar.toString())
                        } else {
                            var temp: String? = null
                            setSecondInputTimer(inputTime = inputTime)
                            if (secondInputTime - firstInputTime >= separationTime2) {
                                temp = composedResult.toString()
                                state = -1
                                firstChar = nullChar
                                firstSubChar = nullChar
                                middleChar = inputChar
                                finalChar = nullChar
                                composedResult = inputChar
                            } else {
                                finalChar = nullChar
                                composeResult()
                                temp = composedResult.toString()
                                state = 2
                                firstChar = 'ㄴ'
                                firstSubChar = nullChar
                                middleChar = inputChar
                                finalChar = nullChar
                                composeResult()
                            }
                            setFirstInputTimer(inputTime = inputTime)
                            return UpdatedChars(temp, firstChar.toString() + middleChar.toString())
                        }
                    }
                    'ㄴ' -> {
                        if (finalSubChar != nullChar) {
                            var temp: String? = null
                            setSecondInputTimer(inputTime = inputTime)
                            if (secondInputTime - firstInputTime >= separationTime2) {
                                temp = composedResult.toString() + finalSubChar.toString()
                                state = -1
                                firstChar = nullChar
                                firstSubChar = nullChar
                                middleChar = inputChar
                                finalChar = nullChar
                                composedResult = inputChar
                            } else {
                                temp = composedResult.toString() + finalSubChar.toString()
                                state = 1
                                firstChar = inputChar
                                firstSubChar = nullChar
                                middleChar = nullChar
                                finalChar = nullChar
                                finalSubChar = nullChar
                                composeResult()
                            }
                            setFirstInputTimer(inputTime = inputTime)
                            return UpdatedChars(temp, composedResult.toString())
                        } else {
                            var temp: String? = null
                            setSecondInputTimer(inputTime = inputTime)
                            if (secondInputTime - firstInputTime >= separationTime2) {
                                temp = composedResult.toString()
                                state = 1
                                firstChar = inputChar
                                firstSubChar = nullChar
                                middleChar = nullChar
                                finalChar = nullChar
                                composedResult = inputChar
                            } else {
                                firstSubChar = nullChar
                                finalChar = 'ㄹ'
                                composeResult()
                            }
                            setFirstInputTimer(inputTime = inputTime)
                            return UpdatedChars(temp, composedResult.toString())
                        }
                    }
                    'ㄱ', 'ㄷ', 'ㅂ', 'ㅇ' -> {
                        val temp = composedResult.toString() + finalSubChar.toString()
                        state = 1
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, firstChar.toString())
                    }
                    'ㅅ' -> {
                        when (finalSubChar) {
                            'ㅅ' -> {
                                var temp: String? = null
                                setSecondInputTimer(inputTime = inputTime)
                                if (secondInputTime - firstInputTime >= separationTime2) {
                                    temp = composedResult.toString() + finalSubChar.toString()
                                    state = 1
                                    firstChar = inputChar
                                    firstSubChar = nullChar
                                    middleChar = nullChar
                                    finalChar = nullChar
                                    finalSubChar = nullChar
                                    composedResult = inputChar
                                } else {
                                    finalChar = 'ㄶ'
                                    firstSubChar = nullChar
                                    finalSubChar = nullChar
                                    composeResult()
                                }
                                setFirstInputTimer(inputTime = inputTime)
                                return UpdatedChars(null, composedResult.toString())
                            }
                            nullChar, 'ㅆ' -> {
                                var temp: String? = null
                                setSecondInputTimer(inputTime = inputTime)
                                if (secondInputTime - firstInputTime >= separationTime2) {
                                    temp = composedResult.toString() + finalSubChar.toString()
                                    state = 1
                                    firstChar = inputChar
                                    firstSubChar = nullChar
                                    middleChar = nullChar
                                    finalChar = nullChar
                                    finalSubChar = nullChar
                                    composedResult = inputChar
                                } else {
                                    finalChar = 'ㄴ'
                                    firstSubChar = nullChar
                                    finalSubChar = 'ㅅ'
                                    composeResult()
                                }
                                setFirstInputTimer(inputTime = inputTime)
                                return UpdatedChars(temp,
                                    composedResult.toString() + finalSubChar.toString()
                                )
                            }
                            else -> {
                                val temp = composedResult.toString() + finalSubChar.toString()
                                state = 1
                                firstChar = inputChar
                                firstSubChar = nullChar
                                middleChar = nullChar
                                finalChar = nullChar
                                finalSubChar = nullChar
                                composedResult = inputChar
                                setFirstInputTimer(inputTime = inputTime)
                                return UpdatedChars(temp, composedResult.toString())
                            }
                        }
                    }
                    'ㅈ' -> {
                        when (finalSubChar) {
                            nullChar, 'ㅉ' -> {
                                var temp: String? = null
                                setSecondInputTimer(inputTime = inputTime)
                                if (secondInputTime - firstInputTime >= separationTime2) {
                                    temp = composedResult.toString() + finalSubChar.toString()
                                    state = 1
                                    firstChar = inputChar
                                    firstSubChar = nullChar
                                    middleChar = nullChar
                                    finalChar = nullChar
                                    finalSubChar = nullChar
                                    composedResult = inputChar
                                } else {
                                    finalChar = 'ㄵ'
                                    firstSubChar = nullChar
                                    finalSubChar = nullChar
                                    composeResult()
                                }
                                setFirstInputTimer(inputTime = inputTime)
                                return UpdatedChars(temp, composedResult.toString())
                            }
                            'ㅊ' -> {
                                var temp: String? = null
                                setSecondInputTimer(inputTime = inputTime)
                                if (secondInputTime - firstInputTime >= separationTime2) {
                                    temp = composedResult.toString() + finalSubChar.toString()
                                    state = 1
                                    firstChar = inputChar
                                    firstSubChar = nullChar
                                    middleChar = nullChar
                                    finalChar = nullChar
                                    finalSubChar = nullChar
                                    composedResult = inputChar
                                } else {
                                    firstSubChar = nullChar
                                    finalSubChar = 'ㅉ'
                                    composeResult()
                                }
                                setFirstInputTimer(inputTime = inputTime)
                                return UpdatedChars(temp,
                                    composedResult.toString() + finalSubChar.toString()
                                )
                            }
                            else -> {
                                val temp = composedResult.toString() + finalSubChar.toString()
                                state = 1
                                firstChar = inputChar
                                firstSubChar = nullChar
                                middleChar = nullChar
                                finalChar = nullChar
                                finalSubChar = nullChar
                                composedResult = inputChar
                                setFirstInputTimer(inputTime = inputTime)
                                return UpdatedChars(temp, composedResult.toString())
                            }
                        }
                    }
                }
                'ㄵ' -> when (inputChar) {
                    'ㅣ', 'ㅡ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime2) {
                            temp = composedResult.toString()
                            state = -1
                            firstChar = nullChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            composedResult = inputChar
                        } else {
                            finalChar = 'ㄴ'
                            composeResult()
                            temp = composedResult.toString()
                            state = 2
                            firstChar = 'ㅈ'
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    'ᆞ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime2) {
                            temp = composedResult.toString()
                            state = -1
                            firstChar = nullChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            composedResult = inputChar
                        } else {
                            finalChar = 'ㄴ'
                            composeResult()
                            temp = composedResult.toString()
                            state = 2
                            firstChar = 'ㅈ'
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
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
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, firstChar.toString())
                    }
                    'ㅈ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime2) {
                            temp = composedResult.toString()
                            state = 1
                            firstChar = inputChar
                            firstSubChar = nullChar
                            middleChar = nullChar
                            finalChar = nullChar
                            composedResult = inputChar
                        } else {
                            firstSubChar = nullChar
                            finalChar = 'ㄴ'
                            finalSubChar = 'ㅊ'
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp,
                            composedResult.toString() + finalSubChar.toString()
                        )
                    }
                }
                'ㄶ' -> when (inputChar) {
                    'ㅣ', 'ㅡ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime2) {
                            temp = composedResult.toString()
                            state = -1
                            firstChar = nullChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            composedResult = inputChar
                        } else {
                            finalChar = 'ㄴ'
                            composeResult()
                            temp = composedResult.toString()
                            state = 2
                            firstChar = 'ㅎ'
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    'ᆞ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime2) {
                            temp = composedResult.toString()
                            state = -1
                            firstChar = nullChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            composedResult = inputChar
                        } else {
                            finalChar = 'ㄴ'
                            composeResult()
                            temp = composedResult.toString()
                            state = 2
                            firstChar = 'ㅎ'
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
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
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, firstChar.toString())
                    }
                    'ㅅ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime2) {
                            temp = composedResult.toString()
                            state = 1
                            firstChar = inputChar
                            firstSubChar = nullChar
                            middleChar = nullChar
                            finalChar = nullChar
                            composedResult = inputChar
                        } else {
                            finalChar = 'ㄴ'
                            firstSubChar = nullChar
                            finalChar = 'ㅆ'
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp,
                            composedResult.toString() + finalSubChar.toString()
                        )
                    }
                }
                'ㄷ' -> when (inputChar) {
                    'ㅣ', 'ㅡ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime2) {
                            temp = composedResult.toString()
                            state = -1
                            firstChar = nullChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            composedResult = inputChar
                        } else {
                            finalChar = nullChar
                            composeResult()
                            temp = composedResult.toString()
                            state = 2
                            firstChar = 'ㄷ'
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    'ᆞ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime2) {
                            temp = composedResult.toString()
                            state = -1
                            firstChar = nullChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            composedResult = inputChar
                        } else {
                            finalChar = nullChar
                            composeResult()
                            temp = composedResult.toString()
                            state = 2
                            firstChar = 'ㄷ'
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp,
                            firstChar.toString() + middleChar.toString()
                        )
                    }
                    'ㄷ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime2) {
                            temp = composedResult.toString()
                            state = 1
                            firstChar = inputChar
                            firstSubChar = nullChar
                            middleChar = nullChar
                            finalChar = nullChar
                            composedResult = inputChar
                        } else {
                            firstSubChar = nullChar
                            finalChar = 'ㅌ'
                            finalSubChar = nullChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
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
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㄹ' -> when (inputChar) {
                    'ㅣ', 'ㅡ' -> {
                        if (finalSubChar != nullChar) {
                            var temp: String? = null
                            setSecondInputTimer(inputTime = inputTime)
                            if (secondInputTime - firstInputTime >= separationTime2) {
                                temp = composedResult.toString() + finalSubChar.toString()
                                state = -1
                                firstChar = nullChar
                                firstSubChar = nullChar
                                middleChar = inputChar
                                finalChar = nullChar
                                finalSubChar = nullChar
                                composedResult = inputChar
                            } else {
                                temp = composedResult.toString()
                                state = 2
                                firstChar = finalSubChar
                                firstSubChar = nullChar
                                middleChar = inputChar
                                finalChar = nullChar
                                finalSubChar = nullChar
                                composeResult()
                            }
                            setFirstInputTimer(inputTime = inputTime)
                            return UpdatedChars(temp, composedResult.toString())
                        } else {
                            var temp: String? = null
                            setSecondInputTimer(inputTime = inputTime)
                            if (secondInputTime - firstInputTime >= separationTime2) {
                                temp = composedResult.toString()
                                state = -1
                                firstChar = nullChar
                                firstSubChar = nullChar
                                middleChar = inputChar
                                finalChar = nullChar
                                composedResult = inputChar
                            } else {
                                finalChar = nullChar
                                composeResult()
                                temp = composedResult.toString()
                                state = 2
                                firstChar = 'ㄹ'
                                firstSubChar = nullChar
                                middleChar = inputChar
                                finalChar = nullChar
                                composeResult()
                            }
                            setFirstInputTimer(inputTime = inputTime)
                            return UpdatedChars(temp, composedResult.toString())
                        }
                    }
                    'ᆞ' -> {
                        if (finalSubChar != nullChar) {
                            var temp: String? = null
                            setSecondInputTimer(inputTime = inputTime)
                            if (secondInputTime - firstInputTime >= separationTime2) {
                                temp = composedResult.toString() + finalSubChar.toString()
                                state = -1
                                firstChar = nullChar
                                firstSubChar = nullChar
                                middleChar = inputChar
                                finalChar = nullChar
                                composedResult = inputChar
                            } else {
                                temp = composedResult.toString()
                                state = 2
                                firstChar = finalSubChar
                                firstSubChar = nullChar
                                middleChar = inputChar
                                finalChar = nullChar
                                finalSubChar = nullChar
                                composeResult()
                            }
                            setFirstInputTimer(inputTime = inputTime)
                            return UpdatedChars(temp,
                                firstChar.toString() + middleChar.toString()
                            )
                        } else {
                            var temp: String? = null
                            setSecondInputTimer(inputTime = inputTime)
                            if (secondInputTime - firstInputTime >= separationTime2) {
                                temp = composedResult.toString()
                                state = -1
                                firstChar = nullChar
                                firstSubChar = nullChar
                                middleChar = inputChar
                                finalChar = nullChar
                                composedResult = inputChar
                            } else {
                                finalChar = nullChar
                                composeResult()
                                temp = composedResult.toString()
                                state = 2
                                firstChar = 'ㄹ'
                                firstSubChar = nullChar
                                middleChar = inputChar
                                finalChar = nullChar
                                composeResult()
                            }
                            setFirstInputTimer(inputTime = inputTime)
                            return UpdatedChars(temp,
                                firstChar.toString() + middleChar.toString()
                            )
                        }
                    }
                    'ㄱ' -> {
                        when (finalSubChar) {
                            nullChar, 'ㄲ' -> {
                                var temp: String? = null
                                setSecondInputTimer(inputTime = inputTime)
                                if (secondInputTime - firstInputTime >= separationTime2) {
                                    temp = composedResult.toString() + finalSubChar.toString()
                                    state = 1
                                    firstChar = inputChar
                                    firstSubChar = nullChar
                                    middleChar = nullChar
                                    finalChar = nullChar
                                    finalSubChar = nullChar
                                    composedResult = inputChar
                                } else {
                                    finalChar = 'ㄺ'
                                    firstSubChar = nullChar
                                    finalSubChar = nullChar
                                    composeResult()
                                }
                                setFirstInputTimer(inputTime = inputTime)
                                return UpdatedChars(temp, composedResult.toString())
                            }
                            'ㅋ' -> {
                                var temp: String? = null
                                setSecondInputTimer(inputTime = inputTime)
                                if (secondInputTime - firstInputTime >= separationTime2) {
                                    temp = composedResult.toString() + finalSubChar.toString()
                                    state = 1
                                    firstChar = inputChar
                                    firstSubChar = nullChar
                                    middleChar = nullChar
                                    finalChar = nullChar
                                    finalSubChar = nullChar
                                    composedResult = inputChar
                                } else {
                                    finalChar = 'ㄹ'
                                    firstSubChar = nullChar
                                    finalSubChar = 'ㄲ'
                                    composeResult()
                                }
                                setFirstInputTimer(inputTime = inputTime)
                                return UpdatedChars(temp,
                                    composedResult.toString() + finalSubChar.toString()
                                )
                            }
                            else -> {
                                val temp = composedResult.toString() + finalSubChar.toString()
                                state = 1
                                firstChar = inputChar
                                firstSubChar = nullChar
                                middleChar = nullChar
                                finalChar = nullChar
                                finalSubChar = nullChar
                                composedResult = inputChar
                                setFirstInputTimer(inputTime = inputTime)
                                return UpdatedChars(temp, composedResult.toString())
                            }
                        }
                    }
                    'ㄴ' -> {
                        if (finalSubChar != nullChar) {
                            val temp = composedResult.toString() + finalSubChar.toString()
                            state = 1
                            firstChar = inputChar
                            firstSubChar = nullChar
                            middleChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composedResult = inputChar
                            setFirstInputTimer(inputTime = inputTime)
                            return UpdatedChars(temp, composedResult.toString())
                        } else {
                            var temp: String? = null
                            setSecondInputTimer(inputTime = inputTime)
                            if (secondInputTime - firstInputTime >= separationTime2) {
                                temp = composedResult.toString()
                                state = 1
                                firstChar = inputChar
                                firstSubChar = nullChar
                                middleChar = nullChar
                                finalChar = nullChar
                                composedResult = inputChar
                            } else {
                                firstSubChar = nullChar
                                finalChar = 'ㄴ'
                                composeResult()
                            }
                            setFirstInputTimer(inputTime = inputTime)
                            return UpdatedChars(temp, composedResult.toString())
                        }
                    }
                    'ㄷ' -> {
                        when (finalSubChar) {
                            'ㄷ' -> {
                                var temp: String? = null
                                setSecondInputTimer(inputTime = inputTime)
                                if (secondInputTime - firstInputTime >= separationTime2) {
                                    temp = composedResult.toString() + finalSubChar.toString()
                                    state = 1
                                    firstChar = inputChar
                                    firstSubChar = nullChar
                                    middleChar = nullChar
                                    finalChar = nullChar
                                    finalSubChar = nullChar
                                    composedResult = inputChar
                                } else {
                                    firstSubChar = nullChar
                                    finalChar = 'ㄾ'
                                    finalSubChar = nullChar
                                    composeResult()
                                }
                                setFirstInputTimer(inputTime = inputTime)
                                return UpdatedChars(temp, composedResult.toString())
                            }
                            nullChar, 'ㄸ' -> {
                                var temp: String? = null
                                setSecondInputTimer(inputTime = inputTime)
                                if (secondInputTime - firstInputTime >= separationTime2) {
                                    temp = composedResult.toString() + finalSubChar.toString()
                                    state = 1
                                    firstChar = inputChar
                                    firstSubChar = nullChar
                                    middleChar = nullChar
                                    finalChar = nullChar
                                    finalSubChar = nullChar
                                    composedResult = inputChar
                                } else {
                                    firstSubChar = nullChar
                                    finalChar = 'ㄹ'
                                    finalSubChar = 'ㄷ'
                                    composeResult()
                                }
                                setFirstInputTimer(inputTime = inputTime)
                                return UpdatedChars(temp, composedResult.toString())
                            }
                            else -> {
                                val temp = composedResult.toString() + finalSubChar.toString()
                                state = 1
                                firstChar = inputChar
                                firstSubChar = nullChar
                                middleChar = nullChar
                                finalChar = nullChar
                                finalSubChar = nullChar
                                composedResult = inputChar
                                setFirstInputTimer(inputTime = inputTime)
                                return UpdatedChars(temp, composedResult.toString())
                            }
                        }
                    }
                    'ㅂ' -> {
                        when (finalSubChar) {
                            nullChar, 'ㅃ' -> {
                                var temp: String? = null
                                setSecondInputTimer(inputTime = inputTime)
                                if (secondInputTime - firstInputTime >= separationTime2) {
                                    temp = composedResult.toString() + finalSubChar.toString()
                                    state = 1
                                    firstChar = inputChar
                                    firstSubChar = nullChar
                                    middleChar = nullChar
                                    finalChar = nullChar
                                    finalSubChar = nullChar
                                    composedResult = inputChar
                                } else {
                                    firstSubChar = nullChar
                                    finalChar = 'ㄼ'
                                    finalSubChar = nullChar
                                    composeResult()
                                }
                                setFirstInputTimer(inputTime = inputTime)
                                return UpdatedChars(temp, composedResult.toString())
                            }
                            else -> {
                                val temp = composedResult.toString() + finalSubChar.toString()
                                state = 1
                                firstChar = inputChar
                                firstSubChar = nullChar
                                middleChar = nullChar
                                finalChar = nullChar
                                finalSubChar = nullChar
                                composedResult = inputChar
                                return UpdatedChars(temp, composedResult.toString())
                            }
                        }
                    }
                    'ㅅ' -> {
                        when (finalSubChar) {
                            nullChar, 'ㅆ' -> {
                                var temp: String? = null
                                setSecondInputTimer(inputTime = inputTime)
                                if (secondInputTime - firstInputTime >= separationTime2) {
                                    temp = composedResult.toString() + finalSubChar.toString()
                                    state = 1
                                    firstChar = inputChar
                                    firstSubChar = nullChar
                                    middleChar = nullChar
                                    finalChar = nullChar
                                    finalSubChar = nullChar
                                    composedResult = inputChar
                                } else {
                                    firstSubChar = nullChar
                                    finalChar = 'ㄽ'
                                    finalSubChar = nullChar
                                    composeResult()
                                }
                                setFirstInputTimer(inputTime = inputTime)
                                return UpdatedChars(temp, composedResult.toString())
                            }
                            else -> {
                                val temp = composedResult.toString() + finalSubChar.toString()
                                state = 1
                                firstChar = inputChar
                                firstSubChar = nullChar
                                middleChar = nullChar
                                finalChar = nullChar
                                finalSubChar = nullChar
                                composedResult = inputChar
                                setFirstInputTimer(inputTime = inputTime)
                                return UpdatedChars(temp, composedResult.toString())
                            }
                        }
                    }
                    'ㅇ' -> {
                        when (finalSubChar) {
                            'ㅇ' -> {
                                var temp: String? = null
                                setSecondInputTimer(inputTime = inputTime)
                                if (secondInputTime - firstInputTime >= separationTime2) {
                                    temp = composedResult.toString() + finalSubChar.toString()
                                    state = 1
                                    firstChar = inputChar
                                    firstSubChar = nullChar
                                    middleChar = nullChar
                                    finalChar = nullChar
                                    finalSubChar = nullChar
                                    composedResult = inputChar
                                } else {
                                    firstSubChar = nullChar
                                    finalChar = 'ㄻ'
                                    finalSubChar = nullChar
                                    composeResult()
                                }
                                setFirstInputTimer(inputTime = inputTime)
                                return UpdatedChars(temp, composedResult.toString())
                            }
                            nullChar, 'ㅁ' -> {
                                var temp: String? = null
                                setSecondInputTimer(inputTime = inputTime)
                                if (secondInputTime - firstInputTime >= separationTime2) {
                                    temp = composedResult.toString() + finalSubChar.toString()
                                    state = 1
                                    firstChar = inputChar
                                    firstSubChar = nullChar
                                    middleChar = nullChar
                                    finalChar = nullChar
                                    finalSubChar = nullChar
                                    composedResult = inputChar
                                } else {
                                    firstSubChar = nullChar
                                    finalChar = 'ㄹ'
                                    finalSubChar = 'ㅇ'
                                    composeResult()
                                }
                                setFirstInputTimer(inputTime = inputTime)
                                return UpdatedChars(temp,
                                    composedResult.toString() + finalSubChar.toString()
                                )
                            }
                            else -> {
                                val temp = composedResult.toString() + finalSubChar.toString()
                                state = 1
                                firstChar = inputChar
                                firstSubChar = nullChar
                                middleChar = nullChar
                                finalChar = nullChar
                                finalSubChar = nullChar
                                composedResult = inputChar
                                setFirstInputTimer(inputTime = inputTime)
                                return UpdatedChars(temp, composedResult.toString())
                            }
                        }
                    }
                    'ㄷ', 'ㅈ' -> {
                        val temp = composedResult.toString() + finalSubChar.toString()
                        state = 1
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㄺ' -> when (inputChar) {
                    'ㅣ', 'ㅡ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime2) {
                            temp = composedResult.toString()
                            state = -1
                            firstChar = nullChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            composedResult = inputChar
                        } else {
                            finalChar = 'ㄹ'
                            composeResult()
                            temp = composedResult.toString()
                            state = 2
                            firstChar = 'ㄱ'
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    'ᆞ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime2) {
                            temp = composedResult.toString()
                            state = -1
                            firstChar = nullChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            composedResult = inputChar
                        } else {
                            finalChar = 'ㄹ'
                            composeResult()
                            temp = composedResult.toString()
                            state = 2
                            firstChar = 'ㄱ'
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
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
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, firstChar.toString())
                    }
                    'ㄱ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime2) {
                            temp = composedResult.toString()
                            state = 1
                            firstChar = inputChar
                            firstSubChar = nullChar
                            middleChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composedResult = inputChar
                        } else {
                            firstSubChar = nullChar
                            finalChar = 'ㄹ'
                            finalSubChar = 'ㅋ'
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp,
                            composedResult.toString() + finalSubChar.toString()
                        )
                    }
                }
                'ㄻ' -> when (inputChar) {
                    'ㅣ', 'ㅡ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime2) {
                            temp = composedResult.toString()
                            state = -1
                            firstChar = nullChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composedResult = inputChar
                        } else {
                            finalChar = 'ㄹ'
                            composeResult()
                            temp = composedResult.toString()
                            state = 2
                            firstChar = 'ㅁ'
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    'ᆞ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime2) {
                            temp = composedResult.toString()
                            state = -1
                            firstChar = nullChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composedResult = inputChar
                        } else {
                            finalChar = 'ㄹ'
                            composeResult()
                            temp = composedResult.toString()
                            state = 2
                            firstChar = 'ㅁ'
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
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
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, firstChar.toString())
                    }
                    'ㅇ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime2) {
                            temp = composedResult.toString()
                            state = 1
                            firstChar = inputChar
                            firstSubChar = nullChar
                            middleChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composedResult = inputChar
                        } else {
                            firstSubChar = nullChar
                            finalChar = 'ㄹ'
                            finalSubChar = 'ㅇ'
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp,
                            composedResult.toString() + finalSubChar.toString()
                        )
                    }
                }
                'ㄼ' -> when (inputChar) {
                    'ㅣ', 'ㅡ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime2) {
                            temp = composedResult.toString()
                            state = -1
                            firstChar = nullChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composedResult = inputChar
                        } else {
                            finalChar = 'ㄹ'
                            composeResult()
                            temp = composedResult.toString()
                            state = 2
                            firstChar = 'ㅂ'
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    'ᆞ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime2) {
                            temp = composedResult.toString()
                            state = -1
                            firstChar = nullChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composedResult = inputChar
                        } else {
                            finalChar = 'ㄹ'
                            composeResult()
                            temp = composedResult.toString()
                            state = 2
                            firstChar = 'ㅂ'
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
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
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, firstChar.toString())
                    }
                    'ㅂ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime2) {
                            temp = composedResult.toString()
                            state = 1
                            firstChar = inputChar
                            firstSubChar = nullChar
                            middleChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composedResult = inputChar
                        } else {
                            finalChar = 'ㄿ'
                            finalSubChar = nullChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp,
                            composedResult.toString() + finalSubChar.toString()
                        )
                    }
                }
                'ㄽ' -> when (inputChar) {
                    'ㅣ', 'ㅡ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime2) {
                            temp = composedResult.toString()
                            state = -1
                            firstChar = nullChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composedResult = inputChar
                        } else {
                            finalChar = 'ㄹ'
                            composeResult()
                            temp = composedResult.toString()
                            state = 2
                            firstChar = 'ㅅ'
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    'ᆞ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime2) {
                            temp = composedResult.toString()
                            state = -1
                            firstChar = nullChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composedResult = inputChar
                        } else {
                            finalChar = 'ㄹ'
                            composeResult()
                            temp = composedResult.toString()
                            state = 2
                            firstChar = 'ㅅ'
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                        }
                        setFirstInputTimer(inputTime = inputTime)
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
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, firstChar.toString())
                    }
                    'ㅅ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime2) {
                            temp = composedResult.toString()
                            state = 1
                            firstChar = inputChar
                            firstSubChar = nullChar
                            middleChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composedResult = inputChar
                        } else {
                            finalChar = 'ㅀ'
                            firstSubChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, composedResult.toString())
                    }
                }
                'ㄾ' -> when (inputChar) {
                    'ㅣ', 'ㅡ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime2) {
                            temp = composedResult.toString()
                            state = -1
                            firstChar = nullChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composedResult = inputChar
                        } else {
                            finalChar = 'ㄹ'
                            composeResult()
                            temp = composedResult.toString()
                            state = 2
                            firstChar = 'ㅌ'
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    'ᆞ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime2) {
                            temp = composedResult.toString()
                            state = -1
                            firstChar = nullChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composedResult = inputChar
                        } else {
                            finalChar = 'ㄹ'
                            composeResult()
                            temp = composedResult.toString()
                            state = 2
                            firstChar = 'ㅌ'
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                        }
                        setFirstInputTimer(inputTime = inputTime)
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
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, firstChar.toString())
                    }
                    'ㄷ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime2) {
                            temp = composedResult.toString()
                            state = 1
                            firstChar = inputChar
                            firstSubChar = nullChar
                            middleChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composedResult = inputChar
                        } else {
                            firstSubChar = nullChar
                            finalChar = 'ㄹ'
                            finalSubChar = 'ㄸ'
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, composedResult.toString() + finalSubChar.toString())
                    }
                }
                'ㄿ' -> when (inputChar) {
                    'ㅣ', 'ㅡ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime2) {
                            temp = composedResult.toString()
                            state = -1
                            firstChar = nullChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composedResult = inputChar
                        } else {
                            finalChar = 'ㄹ'
                            composeResult()
                            temp = composedResult.toString()
                            state = 2
                            firstChar = 'ㅍ'
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    'ᆞ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime2) {
                            temp = composedResult.toString()
                            state = -1
                            firstChar = nullChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composedResult = inputChar
                        } else {
                            finalChar = 'ㄹ'
                            composeResult()
                            temp = composedResult.toString()
                            state = 2
                            firstChar = 'ㅍ'
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
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
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, firstChar.toString())
                    }
                    'ㅂ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime2) {
                            temp = composedResult.toString()
                            state = 1
                            firstChar = inputChar
                            firstSubChar = nullChar
                            middleChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composedResult = inputChar
                        } else {
                            firstSubChar = nullChar
                            finalChar = 'ㄹ'
                            finalSubChar = nullChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp,
                            composedResult.toString() + finalSubChar.toString()
                        )
                    }
                }
                'ㅀ' -> when (inputChar) {
                    'ㅣ', 'ㅡ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime2) {
                            temp = composedResult.toString()
                            state = -1
                            firstChar = nullChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            composedResult = inputChar
                        } else {
                            finalChar = 'ㄹ'
                            composeResult()
                            temp = composedResult.toString()
                            state = 2
                            firstChar = 'ㅎ'
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    'ᆞ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime2) {
                            temp = composedResult.toString()
                            state = -1
                            firstChar = nullChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            composedResult = inputChar
                        } else {
                            finalChar = 'ㄹ'
                            composeResult()
                            temp = composedResult.toString()
                            state = 2
                            firstChar = 'ㅎ'
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
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
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, firstChar.toString())
                    }
                    'ㅅ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime2) {
                            temp = composedResult.toString()
                            state = 1
                            firstChar = inputChar
                            firstSubChar = nullChar
                            middleChar = nullChar
                            finalChar = nullChar
                            composedResult = inputChar
                        } else {
                            firstSubChar = nullChar
                            finalChar = 'ㄹ'
                            finalSubChar = 'ㅆ'
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp,
                            composedResult.toString() + finalSubChar.toString()
                        )
                    }
                }
                'ㅁ' -> when (inputChar) {
                    'ㅣ', 'ㅡ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime2) {
                            temp = composedResult.toString()
                            state = -1
                            firstChar = nullChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composedResult = inputChar
                        } else {
                            finalChar = nullChar
                            composeResult()
                            temp = composedResult.toString()
                            state = 2
                            firstChar = 'ㅁ'
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    'ᆞ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime2) {
                            temp = composedResult.toString()
                            state = -1
                            firstChar = nullChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            composedResult = inputChar
                        } else {
                            finalChar = nullChar
                            composeResult()
                            temp = composedResult.toString()
                            state = 2
                            firstChar = 'ㅁ'
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
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
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, firstChar.toString())
                    }
                    'ㅇ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime2) {
                            temp = composedResult.toString()
                            state = 1
                            firstChar = inputChar
                            firstSubChar = nullChar
                            middleChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composedResult = inputChar
                        } else {
                            firstSubChar = nullChar
                            finalChar = 'ㅇ'
                            finalSubChar=  nullChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, composedResult.toString())
                    }
                }
                'ㅂ' -> when (inputChar) {
                    'ㅣ', 'ㅡ' -> {
                        if (finalSubChar != nullChar) {
                            var temp: String? = null
                            setSecondInputTimer(inputTime = inputTime)
                            if (secondInputTime - firstInputTime >= separationTime2) {
                                temp = composedResult.toString() + finalSubChar.toString()
                                state = -1
                                firstChar = nullChar
                                firstSubChar = nullChar
                                middleChar = inputChar
                                finalChar = nullChar
                                finalSubChar = nullChar
                                composedResult = inputChar
                            } else {
                                temp = composedResult.toString()
                                state = 2
                                firstChar = finalSubChar
                                firstSubChar = nullChar
                                middleChar = inputChar
                                finalChar = nullChar
                                finalSubChar=  nullChar
                                composeResult()
                            }
                            setFirstInputTimer(inputTime = inputTime)
                            return UpdatedChars(temp, composedResult.toString())
                        } else {
                            var temp: String? = null
                            setSecondInputTimer(inputTime = inputTime)
                            if (secondInputTime - firstInputTime >= separationTime2) {
                                temp = composedResult.toString()
                                state = -1
                                firstChar = nullChar
                                firstSubChar = nullChar
                                middleChar = inputChar
                                finalChar = nullChar
                                finalSubChar = nullChar
                                composedResult = inputChar
                            } else {
                                finalChar = nullChar
                                composeResult()
                                temp = composedResult.toString()
                                state = 2
                                firstChar = 'ㅂ'
                                firstSubChar = nullChar
                                middleChar = inputChar
                                finalChar = nullChar
                                finalSubChar=  nullChar
                                composeResult()
                            }
                            setFirstInputTimer(inputTime = inputTime)
                            return UpdatedChars(temp, composedResult.toString())
                        }
                    }
                    'ᆞ' -> {
                        if (finalSubChar != nullChar) {
                            var temp: String? = null
                            setSecondInputTimer(inputTime = inputTime)
                            if (secondInputTime - firstInputTime >= separationTime2) {
                                temp = composedResult.toString() + finalSubChar.toString()
                                state = -1
                                firstChar = nullChar
                                firstSubChar = nullChar
                                middleChar = inputChar
                                finalChar = nullChar
                                finalSubChar = nullChar
                                composedResult = inputChar
                            } else {
                                temp = composedResult.toString()
                                state = 2
                                firstChar = finalSubChar
                                firstSubChar = nullChar
                                middleChar = inputChar
                                finalChar = nullChar
                                finalSubChar=  nullChar
                                composeResult()
                            }
                            setFirstInputTimer(inputTime = inputTime)
                            return UpdatedChars(temp, firstChar.toString() + middleChar.toString())
                        } else {
                            var temp: String? = null
                            setSecondInputTimer(inputTime = inputTime)
                            if (secondInputTime - firstInputTime >= separationTime2) {
                                temp = composedResult.toString()
                                state = -1
                                firstChar = nullChar
                                firstSubChar = nullChar
                                middleChar = inputChar
                                finalChar = nullChar
                                finalSubChar = nullChar
                                composedResult = inputChar
                            } else {
                                finalChar = nullChar
                                composeResult()
                                temp = composedResult.toString()
                                state = 2
                                firstChar = 'ㅂ'
                                firstSubChar = nullChar
                                middleChar = inputChar
                                finalChar = nullChar
                                composeResult()
                            }
                            setFirstInputTimer(inputTime = inputTime)
                            return UpdatedChars(temp, firstChar.toString() + middleChar.toString())
                        }
                    }
                    'ㅂ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime2) {
                            temp = composedResult.toString()
                            state = 1
                            firstChar = inputChar
                            firstSubChar = nullChar
                            middleChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composedResult = inputChar
                        } else {
                            firstSubChar = nullChar
                            finalChar = 'ㅍ'
                            finalSubChar = nullChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    'ㄱ', 'ㄴ', 'ㄷ', 'ㅇ', 'ㅈ' -> {
                        val temp = composedResult.toString()
                        state = 1
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, firstChar.toString())
                    }
                    'ㅅ' -> {
                        when (finalSubChar) {
                            nullChar, 'ㅆ' -> {
                                var temp: String? = null
                                setSecondInputTimer(inputTime = inputTime)
                                if (secondInputTime - firstInputTime >= separationTime2) {
                                    temp = composedResult.toString() + finalSubChar.toString()
                                    state = 1
                                    firstChar = inputChar
                                    firstSubChar = nullChar
                                    middleChar = nullChar
                                    finalChar = nullChar
                                    finalSubChar = nullChar
                                    composedResult = inputChar
                                } else {
                                    firstSubChar = nullChar
                                    finalChar = 'ㅄ'
                                    finalSubChar = nullChar
                                    composeResult()
                                }
                                setFirstInputTimer(inputTime = inputTime)
                                return UpdatedChars(temp, composedResult.toString())
                            }
                            'ㅎ' -> {
                                var temp: String? = null
                                setSecondInputTimer(inputTime = inputTime)
                                if (secondInputTime - firstInputTime >= separationTime2) {
                                    temp = composedResult.toString() + finalSubChar.toString()
                                    state = 1
                                    firstChar = inputChar
                                    firstSubChar = nullChar
                                    middleChar = nullChar
                                    finalChar = nullChar
                                    finalSubChar = nullChar
                                    composedResult = inputChar
                                } else {
                                    firstSubChar = nullChar
                                    finalSubChar=  'ㅆ'
                                }
                                setFirstInputTimer(inputTime = inputTime)
                                return UpdatedChars(temp,
                                    composedResult.toString() + finalSubChar.toString()
                                )
                            }
                        }
                    }
                }
                'ㅄ' -> when (inputChar) {
                    'ㅣ', 'ㅡ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime2) {
                            temp = composedResult.toString()
                            state = -1
                            firstChar = nullChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composedResult = inputChar
                        } else {
                            finalChar = 'ㅂ'
                            composeResult()
                            temp = composedResult.toString()
                            state = 2
                            firstChar = 'ㅅ'
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar=  nullChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    'ᆞ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime2) {
                            temp = composedResult.toString()
                            state = -1
                            firstChar = nullChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composedResult = inputChar
                        } else {
                            finalChar = 'ㅂ'
                            composeResult()
                            temp = composedResult.toString()
                            state = 2
                            firstChar = 'ㅅ'
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar=  nullChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
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
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, firstChar.toString())
                    }
                    'ㅅ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime2) {
                            temp = composedResult.toString()
                            state = 1
                            firstChar = inputChar
                            firstSubChar = nullChar
                            middleChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composedResult = inputChar
                        } else {
                            firstSubChar = nullChar
                            finalChar = 'ㅂ'
                            finalSubChar=  'ㅎ'
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp,
                            composedResult.toString() + finalSubChar.toString()
                        )
                    }
                }
                'ㅅ' -> when (inputChar) {
                    'ㅣ', 'ㅡ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime2) {
                            temp = composedResult.toString()
                            state = -1
                            firstChar = nullChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composedResult = inputChar
                        } else {
                            finalChar = nullChar
                            composeResult()
                            temp = composedResult.toString()
                            state = 2
                            firstChar = 'ㅅ'
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar=  nullChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    'ᆞ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime2) {
                            temp = composedResult.toString()
                            state = -1
                            firstChar = nullChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composedResult = inputChar
                        } else {
                            finalChar = nullChar
                            composeResult()
                            temp = composedResult.toString()
                            state = 2
                            firstChar = 'ㅅ'
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar=  nullChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
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
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, firstChar.toString())
                    }
                    'ㅅ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime2) {
                            temp = composedResult.toString()
                            state = 1
                            firstChar = inputChar
                            firstSubChar = nullChar
                            middleChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composedResult = inputChar
                        } else {
                            firstSubChar = nullChar
                            finalChar = 'ㅎ'
                            finalSubChar=  nullChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, composedResult.toString())
                    }
                }
                'ㅆ' -> when (inputChar) {
                    'ㅣ', 'ㅡ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime2) {
                            temp = composedResult.toString()
                            state = -1
                            firstChar = nullChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composedResult = inputChar
                        } else {
                            finalChar = nullChar
                            composeResult()
                            temp = composedResult.toString()
                            state = 2
                            firstChar = 'ㅆ'
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar=  nullChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    'ᆞ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime2) {
                            temp = composedResult.toString()
                            state = -1
                            firstChar = nullChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composedResult = inputChar
                        } else {
                            finalChar = nullChar
                            composeResult()
                            temp = composedResult.toString()
                            state = 2
                            firstChar = 'ㅆ'
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar=  nullChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
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
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, firstChar.toString())
                    }
                    'ㅅ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime2) {
                            temp = composedResult.toString()
                            state = 1
                            firstChar = inputChar
                            firstSubChar = nullChar
                            middleChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composedResult = inputChar
                        } else {
                            firstSubChar = nullChar
                            finalChar = 'ㅅ'
                            finalSubChar=  nullChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, composedResult.toString())
                    }
                }
                'ㅇ' -> when (inputChar) {
                    'ㅣ', 'ㅡ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime2) {
                            temp = composedResult.toString()
                            state = 1
                            firstChar = inputChar
                            firstSubChar = nullChar
                            middleChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composedResult = inputChar
                        } else {
                            finalChar = nullChar
                            composeResult()
                            temp = composedResult.toString()
                            state = 2
                            firstChar = 'ㅇ'
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar=  nullChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    'ᆞ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime2) {
                            temp = composedResult.toString()
                            state = -1
                            firstChar = nullChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composedResult = inputChar
                        } else {
                            finalChar = nullChar
                            composeResult()
                            temp = composedResult.toString()
                            state = 2
                            firstChar = 'ㅇ'
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar=  nullChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
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
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, firstChar.toString())
                    }
                    'ㅇ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime2) {
                            temp = composedResult.toString()
                            state = 1
                            firstChar = inputChar
                            firstSubChar = nullChar
                            middleChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composedResult = inputChar
                        } else {
                            firstSubChar = nullChar
                            finalChar = 'ㅁ'
                            finalSubChar=  nullChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, composedResult.toString())
                    }
                }
                'ㅈ' -> when (inputChar) {
                    'ㅣ', 'ㅡ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime2) {
                            temp = composedResult.toString()
                            state = -1
                            firstChar = nullChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composedResult = inputChar
                        } else {
                            finalChar = nullChar
                            composeResult()
                            temp = composedResult.toString()
                            state = 2
                            firstChar = 'ㅈ'
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar=  nullChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    'ᆞ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime2) {
                            temp = composedResult.toString()
                            state = -1
                            firstChar = nullChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composedResult = inputChar
                        } else {
                            finalChar = nullChar
                            composeResult()
                            temp = composedResult.toString()
                            state = 2
                            firstChar = 'ㅈ'
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar=  nullChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
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
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, firstChar.toString())
                    }
                    'ㅈ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime2) {
                            temp = composedResult.toString()
                            state = 1
                            firstChar = inputChar
                            firstSubChar = nullChar
                            middleChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composedResult = inputChar
                        } else {
                            firstSubChar = nullChar
                            finalChar = 'ㅊ'
                            finalSubChar=  nullChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, composedResult.toString())
                    }
                }
                'ㅊ' -> when (inputChar) {
                    'ㅣ', 'ㅡ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime2) {
                            temp = composedResult.toString()
                            state = -1
                            firstChar = nullChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composedResult = inputChar
                        } else {
                            finalChar = nullChar
                            composeResult()
                            temp = composedResult.toString()
                            state = 2
                            firstChar = 'ㅊ'
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar=  nullChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    'ᆞ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime2) {
                            temp = composedResult.toString()
                            state = -1
                            firstChar = nullChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composedResult = inputChar
                        } else {
                            finalChar = nullChar
                            composeResult()
                            temp = composedResult.toString()
                            state = 2
                            firstChar = 'ㅊ'
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar=  nullChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
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
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, firstChar.toString())
                    }
                    'ㅈ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime2) {
                            temp = composedResult.toString()
                            state = 1
                            firstChar = inputChar
                            firstSubChar = nullChar
                            middleChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composedResult = inputChar
                        } else {
                            firstSubChar = nullChar
                            finalChar = nullChar
                            finalSubChar=  'ㅉ'
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp,
                            composedResult.toString() + finalSubChar.toString()
                        )
                    }
                }
                'ㅋ' -> when (inputChar) {
                    'ㅣ', 'ㅡ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime2) {
                            temp = composedResult.toString()
                            state = -1
                            firstChar = nullChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composedResult = inputChar
                        } else {
                            finalChar = nullChar
                            composeResult()
                            temp = composedResult.toString()
                            state = 2
                            firstChar = 'ㅋ'
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar=  nullChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    'ᆞ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime2) {
                            temp = composedResult.toString()
                            state = -1
                            firstChar = nullChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composedResult = inputChar
                        } else {
                            finalChar = nullChar
                            composeResult()
                            temp = composedResult.toString()
                            state = 2
                            firstChar = 'ㅋ'
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar=  nullChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
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
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, firstChar.toString())
                    }
                    'ㄱ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime2) {
                            temp = composedResult.toString()
                            state = 1
                            firstChar = inputChar
                            firstSubChar = nullChar
                            middleChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composedResult = inputChar
                        } else {
                            firstSubChar = nullChar
                            finalChar = 'ㄲ'
                            finalSubChar = nullChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, composedResult.toString())
                    }
                }
                'ㅌ' -> when (inputChar) {
                    'ㅣ', 'ㅡ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime2) {
                            temp = composedResult.toString()
                            state = -1
                            firstChar = nullChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composedResult = inputChar
                        } else {
                            finalChar = nullChar
                            composeResult()
                            temp = composedResult.toString()
                            state = 2
                            firstChar = 'ㅌ'
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar=  nullChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    'ᆞ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime2) {
                            temp = composedResult.toString()
                            state = -1
                            firstChar = nullChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composedResult = inputChar
                        } else {
                            finalChar = nullChar
                            composeResult()
                            temp = composedResult.toString()
                            state = 2
                            firstChar = 'ㅌ'
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar=  nullChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
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
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, firstChar.toString())
                    }
                    'ㄷ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime2) {
                            temp = composedResult.toString()
                            state = 1
                            firstChar = inputChar
                            firstSubChar = nullChar
                            middleChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composedResult = inputChar
                        } else {
                            firstSubChar = nullChar
                            finalChar = nullChar
                            finalSubChar = 'ㄸ'
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp,
                            composedResult.toString() + finalSubChar.toString()
                        )
                    }
                }
                'ㅍ' -> when (inputChar) {
                    'ㅣ', 'ㅡ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime2) {
                            temp = composedResult.toString()
                            state = -1
                            firstChar = nullChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composedResult = inputChar
                        } else {
                            finalChar = nullChar
                            composeResult()
                            temp = composedResult.toString()
                            state = 2
                            firstChar = 'ㅍ'
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar=  nullChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    'ᆞ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime2) {
                            temp = composedResult.toString()
                            state = -1
                            firstChar = nullChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composedResult = inputChar
                        } else {
                            finalChar = nullChar
                            composeResult()
                            temp = composedResult.toString()
                            state = 2
                            firstChar = 'ㅍ'
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar=  nullChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
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
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, firstChar.toString())
                    }
                    'ㅂ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime2) {
                            temp = composedResult.toString()
                            state = 1
                            firstChar = inputChar
                            firstSubChar = nullChar
                            middleChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composedResult = inputChar
                        } else {
                            firstSubChar = nullChar
                            finalChar = nullChar
                            finalSubChar=  'ㅃ'
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp,
                            composedResult.toString() + finalSubChar.toString()
                        )
                    }
                }
                'ㅎ' -> when (inputChar) {
                    'ㅣ', 'ㅡ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime2) {
                            temp = composedResult.toString()
                            state = -1
                            firstChar = nullChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composedResult = inputChar
                        } else {
                            finalChar = nullChar
                            composeResult()
                            temp = composedResult.toString()
                            state = 2
                            firstChar = 'ㅎ'
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar=  nullChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    'ᆞ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime2) {
                            temp = composedResult.toString()
                            state = -1
                            firstChar = nullChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composedResult = inputChar
                        } else {
                            finalChar = nullChar
                            composeResult()
                            temp = composedResult.toString()
                            state = 2
                            firstChar = 'ㅎ'
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar=  nullChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
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
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, firstChar.toString())
                    }
                    'ㅅ' -> {
                        var temp: String? = null
                        setSecondInputTimer(inputTime = inputTime)
                        if (secondInputTime - firstInputTime >= separationTime2) {
                            temp = composedResult.toString()
                            state = 1
                            firstChar = inputChar
                            firstSubChar = nullChar
                            middleChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composedResult = inputChar
                        } else {
                            firstSubChar = nullChar
                            finalChar = 'ㅆ'
                            finalSubChar=  nullChar
                            composeResult()
                        }
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, composedResult.toString())
                    }
                }
                nullChar -> when (finalSubChar) {
                    'ㄸ' -> {
                        when (inputChar) {
                            'ㄷ' -> {
                                var temp: String? = null
                                setSecondInputTimer(inputTime = inputTime)
                                if (secondInputTime - firstInputTime >= separationTime2) {
                                    temp = composedResult.toString() + finalSubChar.toString()
                                    state = 1
                                    firstChar = inputChar
                                    firstSubChar = nullChar
                                    middleChar = nullChar
                                    finalChar = nullChar
                                    finalSubChar = nullChar
                                    composedResult = inputChar
                                } else {
                                    firstSubChar = nullChar
                                    finalChar = 'ㄷ'
                                    finalSubChar=  nullChar
                                    composeResult()
                                }
                                setFirstInputTimer(inputTime = inputTime)
                                return UpdatedChars(temp, composedResult.toString())
                            }
                            'ㅣ', 'ㅡ' -> {
                                var temp: String? = null
                                setSecondInputTimer(inputTime = inputTime)
                                if (secondInputTime - firstInputTime >= separationTime2) {
                                    temp = composedResult.toString() + finalSubChar.toString()
                                    state = -1
                                    firstChar = nullChar
                                    firstSubChar = nullChar
                                    middleChar = inputChar
                                    finalChar = nullChar
                                    finalSubChar = nullChar
                                    composedResult = inputChar
                                } else {
                                    composeResult()
                                    temp = composedResult.toString()
                                    state = 2
                                    firstChar = finalSubChar
                                    firstSubChar = nullChar
                                    middleChar = inputChar
                                    finalChar = nullChar
                                    finalSubChar=  nullChar
                                    composeResult()
                                }
                                setFirstInputTimer(inputTime = inputTime)
                                return UpdatedChars(temp, composedResult.toString())
                            }
                            'ᆞ' -> {
                                var temp: String? = null
                                setSecondInputTimer(inputTime = inputTime)
                                if (secondInputTime - firstInputTime >= separationTime2) {
                                    temp = composedResult.toString() + finalSubChar.toString()
                                    state = -1
                                    firstChar = nullChar
                                    firstSubChar = nullChar
                                    middleChar = inputChar
                                    finalChar = nullChar
                                    finalSubChar = nullChar
                                    composedResult = inputChar
                                } else {
                                    composeResult()
                                    temp = composedResult.toString()
                                    state = 2
                                    firstChar = finalSubChar
                                    firstSubChar = nullChar
                                    middleChar = inputChar
                                    finalChar = nullChar
                                    finalSubChar=  nullChar
                                    composeResult()
                                }
                                setFirstInputTimer(inputTime = inputTime)
                                return UpdatedChars(temp, firstChar.toString() + middleChar.toString())
                            }
                            else -> {
                                val temp = composedResult.toString() + finalSubChar.toString()
                                state = 1
                                firstChar = inputChar
                                middleChar = nullChar
                                finalChar = nullChar
                                firstSubChar = nullChar
                                finalSubChar = nullChar
                                setFirstInputTimer(inputTime = inputTime)
                                return UpdatedChars(temp, firstChar.toString())
                            }
                        }
                    }
                    'ㅃ' -> {
                        when (inputChar) {
                            'ㅂ' -> {
                                var temp: String? = null
                                setSecondInputTimer(inputTime = inputTime)
                                if (secondInputTime - firstInputTime >= separationTime2) {
                                    temp = composedResult.toString() + finalSubChar.toString()
                                    state = 1
                                    firstChar = inputChar
                                    firstSubChar = nullChar
                                    middleChar = nullChar
                                    finalChar = nullChar
                                    finalSubChar = nullChar
                                    composedResult = inputChar
                                } else {
                                    firstSubChar = nullChar
                                    finalChar = 'ㅂ'
                                    finalSubChar=  nullChar
                                    composeResult()
                                }
                                setFirstInputTimer(inputTime = inputTime)
                                return UpdatedChars(temp, composedResult.toString())
                            }
                            'ㅣ', 'ㅡ' -> {
                                var temp: String? = null
                                setSecondInputTimer(inputTime = inputTime)
                                if (secondInputTime - firstInputTime >= separationTime2) {
                                    temp = composedResult.toString() + finalSubChar.toString()
                                    state = -1
                                    firstChar = nullChar
                                    firstSubChar = nullChar
                                    middleChar = inputChar
                                    finalChar = nullChar
                                    finalSubChar = nullChar
                                    composedResult = inputChar
                                } else {
                                    composeResult()
                                    temp = composedResult.toString()
                                    state = 2
                                    firstChar = finalSubChar
                                    firstSubChar = nullChar
                                    middleChar = inputChar
                                    finalChar = nullChar
                                    finalSubChar=  nullChar
                                    composeResult()
                                }
                                setFirstInputTimer(inputTime = inputTime)
                                return UpdatedChars(temp, composedResult.toString())
                            }
                            'ᆞ' -> {
                                var temp: String? = null
                                setSecondInputTimer(inputTime = inputTime)
                                if (secondInputTime - firstInputTime >= separationTime2) {
                                    temp = composedResult.toString() + finalSubChar.toString()
                                    state = -1
                                    firstChar = nullChar
                                    firstSubChar = nullChar
                                    middleChar = inputChar
                                    finalChar = nullChar
                                    finalSubChar = nullChar
                                    composedResult = inputChar
                                } else {
                                    composeResult()
                                    temp = composedResult.toString()
                                    state = 2
                                    firstChar = finalSubChar
                                    firstSubChar = nullChar
                                    middleChar = inputChar
                                    finalChar = nullChar
                                    finalSubChar=  nullChar
                                }
                                setFirstInputTimer(inputTime = inputTime)
                                return UpdatedChars(temp, firstChar.toString() + middleChar.toString())
                            }
                            else -> {
                                val temp = composedResult.toString() + finalSubChar.toString()
                                state = 1
                                firstChar = inputChar
                                middleChar = nullChar
                                finalChar = nullChar
                                firstSubChar = nullChar
                                finalSubChar = nullChar
                                setFirstInputTimer(inputTime = inputTime)
                                return UpdatedChars(temp, firstChar.toString())
                            }
                        }
                    }
                    'ㅉ' -> {
                        when (inputChar) {
                            'ㅈ' -> {
                                var temp: String? = null
                                setSecondInputTimer(inputTime = inputTime)
                                if (secondInputTime - firstInputTime >= separationTime2) {
                                    temp = composedResult.toString() + finalSubChar.toString()
                                    state = 1
                                    firstChar = inputChar
                                    firstSubChar = nullChar
                                    middleChar = nullChar
                                    finalChar = nullChar
                                    finalSubChar = nullChar
                                    composedResult = inputChar
                                } else {
                                    firstSubChar = nullChar
                                    finalChar = 'ㅈ'
                                    finalSubChar=  nullChar
                                    composeResult()
                                }
                                setFirstInputTimer(inputTime = inputTime)
                                return UpdatedChars(temp, composedResult.toString())
                            }
                            'ㅣ', 'ㅡ' -> {
                                var temp: String? = null
                                setSecondInputTimer(inputTime = inputTime)
                                if (secondInputTime - firstInputTime >= separationTime2) {
                                    temp = composedResult.toString() + finalSubChar.toString()
                                    state = -1
                                    firstChar = nullChar
                                    firstSubChar = nullChar
                                    middleChar = inputChar
                                    finalChar = nullChar
                                    finalSubChar = nullChar
                                    composedResult = inputChar
                                } else {
                                    composeResult()
                                    temp = composedResult.toString()
                                    state = 2
                                    firstChar = finalSubChar
                                    firstSubChar = nullChar
                                    middleChar = inputChar
                                    finalChar = nullChar
                                    finalSubChar=  nullChar
                                    composeResult()
                                }
                                setFirstInputTimer(inputTime = inputTime)
                                return UpdatedChars(temp, composedResult.toString())
                            }
                            'ᆞ' -> {
                                var temp: String? = null
                                setSecondInputTimer(inputTime = inputTime)
                                if (secondInputTime - firstInputTime >= separationTime2) {
                                    temp = composedResult.toString() + finalSubChar.toString()
                                    state = -1
                                    firstChar = nullChar
                                    firstSubChar = nullChar
                                    middleChar = inputChar
                                    finalChar = nullChar
                                    finalSubChar = nullChar
                                    composedResult = inputChar
                                } else {
                                    composeResult()
                                    temp = composedResult.toString()
                                    state = 2
                                    firstChar = finalSubChar
                                    firstSubChar = nullChar
                                    middleChar = inputChar
                                    finalChar = nullChar
                                    finalSubChar=  nullChar
                                    composeResult()
                                }
                                setFirstInputTimer(inputTime = inputTime)
                                return UpdatedChars(temp, firstChar.toString() + middleChar.toString())
                            }
                            else -> {
                                val temp = composedResult.toString() + finalSubChar.toString()
                                state = 1
                                firstChar = inputChar
                                middleChar = nullChar
                                finalChar = nullChar
                                firstSubChar = nullChar
                                finalSubChar = nullChar
                                setFirstInputTimer(inputTime = inputTime)
                                return UpdatedChars(temp, firstChar.toString())
                            }
                        }
                    }
                }
                else -> {
                    initChar()
                    state = 0
                    composedResult = nullChar
                    setFirstInputTimer(inputTime = inputTime)
                    return UpdatedChars(inputChar.toString(), null)
                }
            }
        }
        return UpdatedChars(null, null)
    }

    fun delete(inputTime: Long): UpdatedChars {
        when (state) {
            0 -> {
                initState()
                initChar()
                initResult()
                setFirstInputTimer(inputTime = inputTime)
                return UpdatedChars(null, null)
            }
            -1 -> when (middleChar) {
                'ㅏ' -> {
                    middleChar = 'ㅣ'
                    composeResult()
                    composedResult = nullChar
                    setFirstInputTimer(inputTime = inputTime)
                    return UpdatedChars(null, middleChar.toString())
                }
                'ㅐ', 'ㅑ' -> {
                    middleChar = 'ㅏ'
                    composeResult()
                    composedResult = nullChar
                    setFirstInputTimer(inputTime = inputTime)
                    return UpdatedChars(null, middleChar.toString())
                }
                'ㅒ' -> {
                    middleChar = 'ㅑ'
                    composeResult()
                    composedResult = nullChar
                    setFirstInputTimer(inputTime = inputTime)
                    return UpdatedChars(null, middleChar.toString())
                }
                'ㅓ', 'ㅗ', 'ᆢ' -> {
                    middleChar = 'ᆞ'
                    composeResult()
                    composedResult = nullChar
                    setFirstInputTimer(inputTime = inputTime)
                    return UpdatedChars(null, middleChar.toString())
                }
                'ㅔ' -> {
                    middleChar = 'ㅓ'
                    composeResult()
                    composedResult = nullChar
                    setFirstInputTimer(inputTime = inputTime)
                    return UpdatedChars(null, middleChar.toString())
                }
                'ㅕ', 'ㅛ' -> {
                    middleChar = 'ᆢ'
                    composeResult()
                    composedResult = nullChar
                    setFirstInputTimer(inputTime = inputTime)
                    return UpdatedChars(null, middleChar.toString())
                }
                'ㅖ' -> {
                    middleChar = 'ㅕ'
                    composeResult()
                    composedResult = nullChar
                    setFirstInputTimer(inputTime = inputTime)
                    return UpdatedChars(null, middleChar.toString())
                }
                'ㅘ' -> {
                    middleChar = 'ㅚ'
                    composeResult()
                    composedResult = nullChar
                    setFirstInputTimer(inputTime = inputTime)
                    return UpdatedChars(null, middleChar.toString())
                }
                'ㅙ' -> {
                    middleChar = 'ㅘ'
                    composeResult()
                    composedResult = nullChar
                    setFirstInputTimer(inputTime = inputTime)
                    return UpdatedChars(null, middleChar.toString())
                }
                'ㅚ' -> {
                    middleChar = 'ㅗ'
                    composeResult()
                    composedResult = nullChar
                    setFirstInputTimer(inputTime = inputTime)
                    return UpdatedChars(null, middleChar.toString())
                }
                'ㅜ', 'ㅢ' -> {
                    middleChar = 'ㅡ'
                    composeResult()
                    composedResult = nullChar
                    setFirstInputTimer(inputTime = inputTime)
                    return UpdatedChars(null, middleChar.toString())
                }
                'ㅠ', 'ㅟ' -> {
                    middleChar = 'ㅜ'
                    composeResult()
                    composedResult = nullChar
                    setFirstInputTimer(inputTime = inputTime)
                    return UpdatedChars(null, middleChar.toString())
                }
                'ㅝ' -> {
                    middleChar = 'ㅠ'
                    composeResult()
                    composedResult = nullChar
                    setFirstInputTimer(inputTime = inputTime)
                    return UpdatedChars(null, middleChar.toString())
                }
                'ㅞ' -> {
                    middleChar = 'ㅝ'
                    composeResult()
                    composedResult = nullChar
                    setFirstInputTimer(inputTime = inputTime)
                    return UpdatedChars(null, middleChar.toString())
                }
                'ㅡ', 'ㅣ', 'ᆞ' -> {
                    state = 0
                    middleChar = nullChar
                    composeResult()
                    composedResult = nullChar
                    setFirstInputTimer(inputTime = inputTime)
                    return UpdatedChars(null, null)
                }
                }
            1 -> when (firstChar) {
                'ㄳ' -> {
                    firstChar = 'ㄱ'
                    firstSubChar = nullChar
                    composeResult()
                    composedResult = nullChar
                    setFirstInputTimer(inputTime = inputTime)
                    return UpdatedChars(null, firstChar.toString())
                }
                'ㄵ', 'ㄶ' -> {
                    firstChar = 'ㄴ'
                    firstSubChar = nullChar
                    composeResult()
                    composedResult = nullChar
                    setFirstInputTimer(inputTime = inputTime)
                    return UpdatedChars(null, firstChar.toString())
                }
                'ㄺ', 'ㄻ', 'ㄼ', 'ㄽ', 'ㄾ', 'ㄿ', 'ㅀ' -> {
                    firstChar = 'ㄹ'
                    firstSubChar = nullChar
                    composeResult()
                    composedResult = nullChar
                    setFirstInputTimer(inputTime = inputTime)
                    return UpdatedChars(null, firstChar.toString())
                }
                'ㅄ' -> {
                    firstChar = 'ㅂ'
                    firstSubChar = nullChar
                    composeResult()
                    composedResult = nullChar
                    setFirstInputTimer(inputTime = inputTime)
                    return UpdatedChars(null, firstChar.toString())
                }
                else -> when ( firstSubChar ) {
                    nullChar -> {
                        state = 0
                        firstChar = nullChar
                        firstSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(null, null)
                    }
                    else -> {
                        firstSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(null, firstChar.toString())
                    }
                }
            }
            2 -> when (middleChar) {
                'ㅏ' -> {
                    middleChar = 'ㅣ'
                    composeResult()
                    setFirstInputTimer(inputTime = inputTime)
                    return UpdatedChars(null, composedResult.toString())
                }
                'ㅐ', 'ㅑ' -> {
                    middleChar = 'ㅏ'
                    composeResult()
                    setFirstInputTimer(inputTime = inputTime)
                    return UpdatedChars(null, composedResult.toString())
                }
                'ㅒ' -> {
                    middleChar = 'ㅑ'
                    composeResult()
                    setFirstInputTimer(inputTime = inputTime)
                    return UpdatedChars(null, composedResult.toString())
                }
                'ㅓ', 'ㅗ', 'ᆢ' -> {
                    middleChar = 'ᆞ'
                    composeResult()
                    composedResult = nullChar
                    setFirstInputTimer(inputTime = inputTime)
                    return UpdatedChars(null,
                        firstChar.toString() + middleChar.toString()
                    )
                }
                'ㅔ' -> {
                    middleChar = 'ㅓ'
                    composeResult()
                    setFirstInputTimer(inputTime = inputTime)
                    return UpdatedChars(null, composedResult.toString())
                }
                'ㅕ', 'ㅛ' -> {
                    middleChar = 'ᆢ'
                    composeResult()
                    composedResult = nullChar
                    setFirstInputTimer(inputTime = inputTime)
                    return UpdatedChars(null,
                        firstChar.toString() + middleChar.toString()
                    )
                }
                'ㅖ' -> {
                    middleChar = 'ㅕ'
                    composeResult()
                    setFirstInputTimer(inputTime = inputTime)
                    return UpdatedChars(null, composedResult.toString())
                }
                'ㅘ' -> {
                    middleChar = 'ㅚ'
                    composeResult()
                    setFirstInputTimer(inputTime = inputTime)
                    return UpdatedChars(null, composedResult.toString())
                }
                'ㅙ' -> {
                    middleChar = 'ㅘ'
                    composeResult()
                    setFirstInputTimer(inputTime = inputTime)
                    return UpdatedChars(null, composedResult.toString())
                }
                'ㅚ' -> {
                    middleChar = 'ㅗ'
                    composeResult()
                    setFirstInputTimer(inputTime = inputTime)
                    return UpdatedChars(null, composedResult.toString())
                }
                'ㅜ', 'ㅢ' -> {
                    middleChar = 'ㅡ'
                    composeResult()
                    setFirstInputTimer(inputTime = inputTime)
                    return UpdatedChars(null, composedResult.toString())
                }
                'ㅠ', 'ㅟ' -> {
                    middleChar = 'ㅜ'
                    composeResult()
                    setFirstInputTimer(inputTime = inputTime)
                    return UpdatedChars(null, composedResult.toString())
                }
                'ㅝ' -> {
                    middleChar = 'ㅠ'
                    composeResult()
                    setFirstInputTimer(inputTime = inputTime)
                    return UpdatedChars(null, composedResult.toString())
                }
                'ㅞ' -> {
                    middleChar = 'ㅝ'
                    composeResult()
                    setFirstInputTimer(inputTime = inputTime)
                    return UpdatedChars(null, composedResult.toString())
                }
                'ㅡ', 'ㅣ', 'ᆞ' -> {
                    state = 1
                    middleChar = nullChar
                    composeResult()
                    composedResult = nullChar
                    setFirstInputTimer(inputTime = inputTime)
                    return UpdatedChars(null, firstChar.toString())
                }
            }
            3 -> when (finalChar) {
                'ㄳ' -> {
                    finalChar = 'ㄱ'
                    finalSubChar = nullChar
                    composeResult()
                    setFirstInputTimer(inputTime = inputTime)
                    return UpdatedChars(null, composedResult.toString())
                }
                'ㄵ', 'ㄶ' -> {
                    finalChar = 'ㄴ'
                    finalSubChar = nullChar
                    composeResult()
                    setFirstInputTimer(inputTime = inputTime)
                    return UpdatedChars(null, composedResult.toString())
                }
                'ㄺ', 'ㄻ', 'ㄼ', 'ㄽ', 'ㄾ', 'ㄿ', 'ㅀ' -> {
                    finalChar = 'ㄹ'
                    finalSubChar = nullChar
                    composeResult()
                    setFirstInputTimer(inputTime = inputTime)
                    return UpdatedChars(null, composedResult.toString())
                }
                'ㅄ' -> {
                    finalChar = 'ㅂ'
                    finalSubChar = nullChar
                    composeResult()
                    setFirstInputTimer(inputTime = inputTime)
                    return UpdatedChars(null, composedResult.toString())
                }
                else -> when ( finalSubChar ) {
                    nullChar -> {
                        state = 2
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(null, composedResult.toString())
                    }
                    else -> {
                        finalSubChar = nullChar
                        composeResult()
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(null, composedResult.toString())
                    }
                }
            }
        }
        return UpdatedChars(null, null)
    }

    private var firstInputTime = 0.toLong()
    private var secondInputTime = 0.toLong()

    fun setFirstInputTimer(inputTime: Long) {
        firstInputTime = inputTime
    }
    private fun setSecondInputTimer(inputTime: Long) {
        secondInputTime = inputTime
    }

    private const val separationTime1 = 750
    private const val separationTime2 = 1000
}

