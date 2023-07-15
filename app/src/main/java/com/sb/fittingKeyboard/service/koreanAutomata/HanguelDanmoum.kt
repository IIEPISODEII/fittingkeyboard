package com.sb.fittingKeyboard.service.koreanautomata

object HanguelDanmoum : Automata() {
    fun composeChar(inputChar: Char, inputTime: Long): UpdatedChars { // 한글완성 오토마타
        val isInputCharFirstChar = inputChar.code in firstCharArray
        val isInputCharMiddleChar = inputChar.code in middleCharArray

        when (state) {
            0 -> { //아무것도 없는 상태. 자음 혹은 모음이 들어올 수 있다
                when {
                    isInputCharFirstChar -> { // 자음이 들어온다면
                        firstChar = inputChar
                        composedResult = nullChar
                        state = 1
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(null, firstChar.toString())
                    }
                    isInputCharMiddleChar -> { // 모음이 들어온다면
                        state = -1
                        middleChar = inputChar
                        composedResult = nullChar
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(null, middleChar.toString())
                    }
                    else -> {
                        initChar()
                        composedResult = nullChar
                        return UpdatedChars(inputChar.toString(), null)
                    }
                }
            }
            -1 -> { // 모음이 들어온 상태
                when {
                    isInputCharFirstChar -> { // 자음 입력 시
                        val temp = middleChar.toString()
                        state = 1
                        firstChar = inputChar
                        middleChar = nullChar
                        composedResult = nullChar
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, firstChar.toString())
                    }
                    isInputCharMiddleChar -> { // 모음 입력 시 = 경우의 수를 따져야 함
                        setSecondInputTimer(inputTime = inputTime)
                        when (inputChar) {
                            'ㅏ' -> {
                                when (middleChar) {
                                    'ㅏ' -> {
                                       var temp: String? = null
                                        middleChar =
                                            if (secondInputTime - firstInputTime >= separationTime1) {
                                                temp = middleChar.toString()
                                                inputChar
                                            } else {
                                                'ㅑ'
                                            }
                                        composedResult = nullChar
                                        setFirstInputTimer(inputTime = inputTime)
                                        return UpdatedChars(temp, middleChar.toString())
                                    }
                                    'ㅗ' -> {
                                        middleChar = 'ㅘ'
                                        composedResult = nullChar
                                        return UpdatedChars(null, middleChar.toString())
                                    }
                                    else -> {
                                        val temp = middleChar.toString()
                                        middleChar = inputChar
                                        composedResult = nullChar
                                        setFirstInputTimer(inputTime = inputTime)
                                        return UpdatedChars(temp, middleChar.toString())
                                    }
                                }
                            }
                            'ㅐ' -> {
                                when (middleChar) {
                                    'ㅐ' -> {
                                        var temp: String? = null
                                        middleChar =
                                            if (secondInputTime - firstInputTime >= separationTime1) {
                                                temp = middleChar.toString()
                                                inputChar
                                            } else {
                                                'ㅒ'
                                            }
                                        composedResult = nullChar
                                        setFirstInputTimer(inputTime = inputTime)
                                        return UpdatedChars(temp, middleChar.toString())
                                    }
                                    'ㅗ' -> {
                                        middleChar = 'ㅙ'
                                        composedResult = nullChar
                                        return UpdatedChars(null, middleChar.toString())
                                    }
                                    else -> {
                                        val temp = middleChar.toString()
                                        middleChar = inputChar
                                        composedResult = nullChar
                                        setFirstInputTimer(inputTime = inputTime)
                                        return UpdatedChars(temp, middleChar.toString())
                                    }
                                }
                            }
                            'ㅓ' -> {
                                when (middleChar) {
                                    'ㅓ' -> {
                                        var temp: String? = null
                                        middleChar =
                                            if (secondInputTime - firstInputTime >= separationTime1) {
                                                temp = middleChar.toString()
                                                inputChar
                                            } else {
                                                'ㅕ'
                                            }
                                        composedResult = nullChar
                                        setFirstInputTimer(inputTime = inputTime)
                                        return UpdatedChars(temp, middleChar.toString())
                                    }
                                    'ㅜ' -> {
                                        middleChar = 'ㅝ'
                                        composedResult = nullChar
                                        return UpdatedChars(null, middleChar.toString())
                                    }
                                    else -> {
                                        val temp = middleChar.toString()
                                        middleChar = inputChar
                                        composedResult = nullChar
                                        setFirstInputTimer(inputTime = inputTime)
                                        return UpdatedChars(temp, middleChar.toString())
                                    }
                                }
                            }
                            'ㅔ' -> {
                                when (middleChar) {
                                    'ㅔ' -> {
                                        var temp: String? = null
                                        middleChar =
                                            if (secondInputTime - firstInputTime >= separationTime1) {
                                                temp = middleChar.toString()
                                                inputChar
                                            } else {
                                                'ㅖ'
                                            }
                                        composedResult = nullChar
                                        setFirstInputTimer(inputTime = inputTime)
                                        return UpdatedChars(temp, middleChar.toString())
                                    }
                                    'ㅜ' -> {
                                        middleChar = 'ㅞ'
                                        composedResult = nullChar
                                        return UpdatedChars(null, middleChar.toString())
                                    }
                                    else -> {
                                        val temp = middleChar.toString()
                                        middleChar = inputChar
                                        composedResult = nullChar
                                        setFirstInputTimer(inputTime = inputTime)
                                        return UpdatedChars(temp, middleChar.toString())
                                    }
                                }
                            }
                            'ㅗ' -> {
                                when (middleChar) {
                                    'ㅗ' -> {
                                        var temp: String? = null
                                        middleChar =
                                            if (secondInputTime - firstInputTime >= separationTime1) {
                                                temp = middleChar.toString()
                                                inputChar
                                            } else {
                                                'ㅛ'
                                            }
                                        composedResult = nullChar
                                        setFirstInputTimer(inputTime = inputTime)
                                        return UpdatedChars(temp, middleChar.toString())
                                    }
                                    else -> {
                                        val temp = middleChar.toString()
                                        middleChar = inputChar
                                        composedResult = nullChar
                                        setFirstInputTimer(inputTime = inputTime)
                                        return UpdatedChars(temp, middleChar.toString())
                                    }
                                }
                            }
                            'ㅜ' -> {
                                when (middleChar) {
                                    'ㅜ' -> {
                                        var temp: String? = null
                                        middleChar =
                                            if (secondInputTime - firstInputTime >= separationTime1) {
                                                temp = middleChar.toString()
                                                inputChar
                                            } else {
                                                'ㅠ'
                                            }
                                        composedResult = nullChar
                                        setFirstInputTimer(inputTime = inputTime)
                                        return UpdatedChars(temp, middleChar.toString())
                                    }
                                    else -> {
                                        val temp = middleChar.toString()
                                        middleChar = inputChar
                                        composedResult = nullChar
                                        setFirstInputTimer(inputTime = inputTime)
                                        return UpdatedChars(temp, middleChar.toString())
                                    }
                                }
                            }
                            'ㅡ' -> {
                                val temp = middleChar.toString()
                                middleChar = inputChar
                                composedResult = nullChar
                                setFirstInputTimer(inputTime = inputTime)
                                return UpdatedChars(temp, middleChar.toString())
                            }
                            'ㅣ' -> {
                                when (middleChar) {
                                    'ㅗ' -> {
                                        middleChar = 'ㅚ'
                                        composedResult = nullChar
                                        return UpdatedChars(null, middleChar.toString())
                                    }
                                    'ㅜ' -> {
                                        middleChar = 'ㅟ'
                                        composedResult = nullChar
                                        return UpdatedChars(null, middleChar.toString())
                                    }
                                    'ㅡ' -> {
                                        middleChar = 'ㅢ'
                                        composedResult = nullChar
                                        return UpdatedChars(null, middleChar.toString())
                                    }
                                    else -> {
                                        val temp = middleChar.toString()
                                        middleChar = inputChar
                                        composedResult = nullChar
                                        setFirstInputTimer(inputTime = inputTime)
                                        return UpdatedChars(temp, middleChar.toString())
                                    }
                                }
                            }
                        }
                    }
                    else -> {
                        val temp = middleChar.toString()
                        state = 0
                        initChar()
                        composedResult = nullChar
                        return UpdatedChars(temp + inputChar.toString(), null)
                    }
                }
            }
            1 -> { //자음이 들어온 상태(초성)
                when {
                    isInputCharFirstChar -> { //자음이 들어온다면 ex) ㄱㄱ-> ㄲ, ㄹㄱ -> ㄺ, ㄱㄴ
                        setSecondInputTimer(inputTime = inputTime)
                        var temp: String? = null
                        when (inputChar) {
                            'ㄱ' -> {
                                when (firstChar) {
                                    'ㄱ' -> {
                                        firstChar =
                                            if (secondInputTime - firstInputTime >= separationTime1) {
                                                temp = firstChar.toString()
                                                inputChar
                                            } else {
                                                'ㄲ'
                                            }
                                    }
                                    'ㄹ' -> {
                                        firstChar =
                                            if (secondInputTime - firstInputTime >= separationTime1) {
                                                temp = firstChar.toString()
                                                inputChar
                                            } else {
                                                'ㄺ'
                                            }
                                    }
                                    'ㄺ' -> {
                                        firstChar =
                                            if (secondInputTime - firstInputTime >= separationTime1) {
                                                temp = firstChar.toString()
                                                inputChar
                                            } else {
                                                temp = "ㄹ"
                                                'ㄲ'
                                            }
                                    }
                                    else -> {
                                        temp = firstChar.toString()
                                        firstChar = inputChar
                                    }
                                }
                            }
                            'ㄷ' -> {
                                firstChar = when (firstChar) {
                                    'ㄷ' -> {
                                        if (secondInputTime - firstInputTime >= separationTime1) {
                                            temp = firstChar.toString()
                                            inputChar
                                        } else {
                                            'ㄸ'
                                        }
                                    }
                                    else -> {
                                        temp = firstChar.toString()
                                        inputChar
                                    }
                                }
                            }
                            'ㅁ' -> {
                                firstChar = when (firstChar) {
                                    'ㄹ' -> {
                                        if (secondInputTime - firstInputTime >= separationTime1) {
                                            temp = firstChar.toString()
                                            inputChar
                                        } else {
                                            'ㄻ'
                                        }
                                    }
                                    else -> {
                                        temp = firstChar.toString()
                                        inputChar
                                    }
                                }
                            }
                            'ㅂ' -> {
                                when (firstChar) {
                                    'ㄹ' -> {
                                        firstChar =
                                            if (secondInputTime - firstInputTime >= separationTime1) {
                                                temp = firstChar.toString()
                                                inputChar
                                            } else {
                                                'ㄼ'
                                            }
                                    }
                                    'ㅂ' -> {
                                        firstChar =
                                            if (secondInputTime - firstInputTime >= separationTime1) {
                                                temp = firstChar.toString()
                                                inputChar
                                            } else {
                                                'ㅃ'
                                            }
                                    }
                                    'ㄼ' -> {
                                        firstChar =
                                            if (secondInputTime - firstInputTime >= separationTime1) {
                                                temp = firstChar.toString()
                                                inputChar
                                            } else {
                                                temp = "ㄹ"
                                                'ㅃ'
                                            }
                                    }
                                    else -> {
                                        temp = firstChar.toString()
                                        firstChar = inputChar
                                    }
                                }
                            }
                            'ㅅ' -> {
                                when (firstChar) {
                                    'ㄱ' -> {
                                        firstChar =
                                            if (secondInputTime - firstInputTime >= separationTime1) {
                                                temp = firstChar.toString()
                                                inputChar
                                            } else {
                                                'ㄳ'
                                            }
                                    }
                                    'ㄹ' -> {
                                        firstChar =
                                            if (secondInputTime - firstInputTime >= separationTime1) {
                                                temp = firstChar.toString()
                                                inputChar
                                            } else {
                                                'ㄽ'
                                            }
                                    }
                                    'ㄽ' -> {
                                        firstChar =
                                            if (secondInputTime - firstInputTime >= separationTime1) {
                                                temp = firstChar.toString()
                                                inputChar
                                            } else {
                                                temp = "ㄹ"
                                                'ㅆ'
                                            }
                                    }
                                    'ㅂ' -> {
                                        firstChar =
                                            if (secondInputTime - firstInputTime >= separationTime1) {
                                                temp = firstChar.toString()
                                                inputChar
                                            } else {
                                                'ㅄ'
                                            }
                                    }
                                    'ㅄ' -> {
                                        firstChar =
                                            if (secondInputTime - firstInputTime >= separationTime1) {
                                                temp = firstChar.toString()
                                                inputChar
                                            } else {
                                                temp = "ㅂ"
                                                'ㅆ'
                                            }
                                    }
                                    'ㅅ' -> {
                                        firstChar =
                                            if (secondInputTime - firstInputTime >= separationTime1) {
                                                temp = firstChar.toString()
                                                inputChar
                                            } else {
                                                'ㅆ'
                                            }
                                    }
                                    else -> {
                                        temp = firstChar.toString()
                                        firstChar = inputChar
                                    }
                                }
                            }
                            'ㅈ' -> {
                                when (firstChar) {
                                    'ㄴ' -> {
                                        firstChar =
                                            if (secondInputTime - firstInputTime >= separationTime1) {
                                                temp = firstChar.toString()
                                                inputChar
                                            } else {
                                                'ㄵ'
                                            }
                                    }
                                    'ㄵ' -> {
                                        firstChar =
                                            if (secondInputTime - firstInputTime >= separationTime1) {
                                                temp = firstChar.toString()
                                                inputChar
                                            } else {
                                                temp = "ㄴ"
                                                'ㅉ'
                                            }
                                    }
                                    'ㅈ' -> {
                                        firstChar =
                                            if (secondInputTime - firstInputTime >= separationTime1) {
                                                temp = firstChar.toString()
                                                inputChar
                                            } else {
                                                'ㅉ'
                                            }
                                    }
                                    else -> {
                                        temp = firstChar.toString()
                                        firstChar = inputChar
                                    }
                                }
                            }
                            'ㅌ' -> {
                                firstChar = when (firstChar) {
                                    'ㄹ' -> {
                                        if (secondInputTime - firstInputTime >= separationTime1) {
                                            temp = firstChar.toString()
                                            inputChar
                                        } else {
                                            'ㄾ'
                                        }
                                    }
                                    else -> {
                                        temp = firstChar.toString()
                                        inputChar
                                    }
                                }
                            }
                            'ㅍ' -> {
                                firstChar = when (firstChar) {
                                    'ㄹ' -> {
                                        if (secondInputTime - firstInputTime >= separationTime1) {
                                            temp = firstChar.toString()
                                            inputChar
                                        } else {
                                            'ㄿ'
                                        }
                                    }
                                    else -> {
                                        temp = firstChar.toString()
                                        inputChar
                                    }
                                }
                            }
                            'ㅎ' -> {
                                when (firstChar) {
                                    'ㄴ' -> {
                                        firstChar =
                                            if (secondInputTime - firstInputTime >= separationTime1) {
                                                temp = firstChar.toString()
                                                inputChar
                                            } else {
                                                'ㄶ'
                                            }
                                    }
                                    'ㄹ' -> {
                                        firstChar =
                                            if (secondInputTime - firstInputTime >= separationTime1) {
                                                temp = firstChar.toString()
                                                inputChar
                                            } else {
                                                'ㅀ'
                                            }
                                    }
                                    else -> {
                                        temp = firstChar.toString()
                                        firstChar = inputChar
                                    }
                                }
                            }
                            else -> { // ㄴ ㄹ ㅇ ㅊ ㅋ
                                temp = firstChar.toString()
                                firstChar = inputChar
                            }
                        }
                        composedResult = nullChar
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, firstChar.toString())
                    }
                    isInputCharMiddleChar -> { //모음이 들어온다면 ex) ㄱ+ㅏ -> 가
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
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    else -> { // 한글자모를 입력하지 않는다면
                        val temp = firstChar.toString()
                        state = 0
                        initChar()
                        composedResult = nullChar
                        return UpdatedChars(temp + inputChar.toString(), null)
                    }
                }
            }
            2 -> { //자음+모음이 들어온 상태(중성)
                when {
                    isInputCharFirstChar -> { // 자음(종성)이 들어온다면
                        finalChar = inputChar
                        state = 3
                        composeResult()
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(null, composedResult.toString())
                    }
                    isInputCharMiddleChar -> { // 모음 입력 시 = 경우의 수를 따져야 함
                        setSecondInputTimer(inputTime = inputTime)
                        when (inputChar) {
                            'ㅏ' -> {
                                when (middleChar) {
                                    'ㅏ' -> {
                                        if (secondInputTime - firstInputTime >= separationTime1) {
                                            val temp = composedResult.toString()
                                            state = -1
                                            firstChar = nullChar
                                            middleChar = inputChar
                                            composeResult()
                                            composedResult = nullChar
                                            setFirstInputTimer(inputTime = inputTime)
                                            return UpdatedChars(temp,
                                                middleChar.toString()
                                            )
                                        } else {
                                            middleChar = 'ㅑ'
                                            composeResult()
                                            return UpdatedChars(null,
                                                composedResult.toString()
                                            )
                                        }
                                    }
                                    'ㅗ' -> {
                                        middleChar = 'ㅘ'
                                        composeResult()
                                        return UpdatedChars(null,
                                            composedResult.toString()
                                        )
                                    }
                                    else -> {
                                        val temp = composedResult.toString()
                                        state = -1
                                        firstChar = nullChar
                                        middleChar = inputChar
                                        composeResult()
                                        composedResult = nullChar
                                        setFirstInputTimer(inputTime = inputTime)
                                        return UpdatedChars(temp, middleChar.toString())
                                    }
                                }
                            }
                            'ㅐ' -> {
                                when (middleChar) {
                                    'ㅐ' -> {
                                        if (secondInputTime - firstInputTime >= separationTime1) {
                                            val temp = composedResult.toString()
                                            state = -1
                                            firstChar = nullChar
                                            middleChar = inputChar
                                            composeResult()
                                            composedResult = nullChar
                                            setFirstInputTimer(inputTime = inputTime)
                                            return UpdatedChars(temp,
                                                middleChar.toString()
                                            )
                                        } else {
                                            middleChar = 'ㅒ'
                                            composeResult()
                                            return UpdatedChars(null,
                                                composedResult.toString()
                                            )
                                        }
                                    }
                                    'ㅗ' -> {
                                        middleChar = 'ㅙ'
                                        composeResult()
                                        return UpdatedChars(null,
                                            composedResult.toString()
                                        )
                                    }
                                    else -> {
                                        val temp = composedResult.toString()
                                        state = -1
                                        firstChar = nullChar
                                        middleChar = inputChar
                                        composeResult()
                                        composedResult = nullChar
                                        setFirstInputTimer(inputTime = inputTime)
                                        return UpdatedChars(temp, middleChar.toString())
                                    }
                                }
                            }
                            'ㅓ' -> {
                                when (middleChar) {
                                    'ㅓ' -> {
                                        if (secondInputTime - firstInputTime >= separationTime1) {
                                            val temp = composedResult.toString()
                                            state = -1
                                            firstChar = nullChar
                                            middleChar = inputChar
                                            composeResult()
                                            composedResult = nullChar
                                            setFirstInputTimer(inputTime = inputTime)
                                            return UpdatedChars(temp,
                                                middleChar.toString()
                                            )
                                        } else {
                                            middleChar = 'ㅕ'
                                            composeResult()
                                            return UpdatedChars(null,
                                                composedResult.toString()
                                            )
                                        }
                                    }
                                    'ㅜ' -> {
                                        middleChar = 'ㅝ'
                                        composeResult()
                                        return UpdatedChars(null,
                                            composedResult.toString()
                                        )
                                    }
                                    else -> {
                                        val temp = composedResult.toString()
                                        state = -1
                                        firstChar = nullChar
                                        middleChar = inputChar
                                        composeResult()
                                        composedResult = nullChar
                                        setFirstInputTimer(inputTime = inputTime)
                                        return UpdatedChars(temp, middleChar.toString())
                                    }
                                }
                            }
                            'ㅔ' -> {
                                when (middleChar) {
                                    'ㅔ' -> {
                                        if (secondInputTime - firstInputTime >= separationTime1) {
                                            val temp = composedResult.toString()
                                            state = -1
                                            firstChar = nullChar
                                            middleChar = inputChar
                                            composeResult()
                                            composedResult = nullChar
                                            setFirstInputTimer(inputTime = inputTime)
                                            return UpdatedChars(temp,
                                                middleChar.toString()
                                            )
                                        } else {
                                            middleChar = 'ㅖ'
                                            composeResult()
                                            return UpdatedChars(null,
                                                composedResult.toString()
                                            )
                                        }
                                    }
                                    'ㅜ' -> {
                                        middleChar = 'ㅞ'
                                        composeResult()
                                        return UpdatedChars(null,
                                            composedResult.toString()
                                        )
                                    }
                                    else -> {
                                        val temp = composedResult.toString()
                                        state = -1
                                        firstChar = nullChar
                                        middleChar = inputChar
                                        composeResult()
                                        composedResult = nullChar
                                        setFirstInputTimer(inputTime = inputTime)
                                        return UpdatedChars(temp, middleChar.toString())
                                    }
                                }
                            }
                            'ㅗ' -> {
                                when (middleChar) {
                                    'ㅗ' -> {
                                        if (secondInputTime - firstInputTime >= separationTime1) {
                                            val temp = composedResult.toString()
                                            state = -1
                                            firstChar = nullChar
                                            middleChar = inputChar
                                            composeResult()
                                            composedResult = nullChar
                                            setFirstInputTimer(inputTime = inputTime)
                                            return UpdatedChars(temp,
                                                middleChar.toString()
                                            )
                                        } else {
                                            middleChar = 'ㅛ'
                                            composeResult()
                                            return UpdatedChars(null,
                                                composedResult.toString()
                                            )
                                        }
                                    }
                                    else -> {
                                        val temp = composedResult.toString()
                                        state = -1
                                        firstChar = nullChar
                                        middleChar = inputChar
                                        composeResult()
                                        composedResult = nullChar
                                        setFirstInputTimer(inputTime = inputTime)
                                        return UpdatedChars(temp, middleChar.toString())
                                    }
                                }
                            }
                            'ㅜ' -> {
                                when (middleChar) {
                                    'ㅜ' -> {
                                        if (secondInputTime - firstInputTime >= separationTime1) {
                                            val temp = composedResult.toString()
                                            state = -1
                                            firstChar = nullChar
                                            middleChar = inputChar
                                            composeResult()
                                            composedResult = nullChar
                                            setFirstInputTimer(inputTime = inputTime)
                                            return UpdatedChars(temp,
                                                middleChar.toString()
                                            )
                                        } else {
                                            middleChar = 'ㅠ'
                                            composeResult()
                                            return UpdatedChars(null,
                                                composedResult.toString()
                                            )
                                        }
                                    }
                                    else -> {
                                        val temp = composedResult.toString()
                                        state = -1
                                        firstChar = nullChar
                                        middleChar = inputChar
                                        composeResult()
                                        composedResult = nullChar
                                        setFirstInputTimer(inputTime = inputTime)
                                        return UpdatedChars(temp, middleChar.toString())
                                    }
                                }
                            }
                            'ㅡ' -> {
                                val temp = composedResult.toString()
                                state = -1
                                firstChar = nullChar
                                middleChar = inputChar
                                composeResult()
                                composedResult = nullChar
                                setFirstInputTimer(inputTime = inputTime)
                                return UpdatedChars(temp, middleChar.toString())
                            }
                            'ㅣ' -> {
                                when (middleChar) {
                                    'ㅗ' -> {
                                        if (secondInputTime - firstInputTime >= separationTime1) {
                                            val temp = composedResult.toString()
                                            state = -1
                                            firstChar = nullChar
                                            middleChar = inputChar
                                            composeResult()
                                            composedResult = nullChar
                                            setFirstInputTimer(inputTime = inputTime)
                                            return UpdatedChars(temp,
                                                middleChar.toString()
                                            )
                                        } else {
                                            middleChar = 'ㅚ'
                                            composeResult()
                                            return UpdatedChars(null,
                                                composedResult.toString()
                                            )
                                        }
                                    }
                                    'ㅜ' -> {
                                        if (secondInputTime - firstInputTime >= separationTime1) {
                                            val temp = composedResult.toString()
                                            state = -1
                                            firstChar = nullChar
                                            middleChar = inputChar
                                            composeResult()
                                            composedResult = nullChar
                                            setFirstInputTimer(inputTime = inputTime)
                                            return UpdatedChars(temp,
                                                middleChar.toString()
                                            )
                                        } else {
                                            middleChar = 'ㅟ'
                                            composeResult()
                                            return UpdatedChars(null,
                                                composedResult.toString()
                                            )
                                        }
                                    }
                                    'ㅡ' -> {
                                        if (secondInputTime - firstInputTime >= separationTime1) {
                                            val temp = composedResult.toString()
                                            state = -1
                                            firstChar = nullChar
                                            middleChar = inputChar
                                            composeResult()
                                            composedResult = nullChar
                                            setFirstInputTimer(inputTime = inputTime)
                                            return UpdatedChars(temp,
                                                middleChar.toString()
                                            )
                                        } else {
                                            middleChar = 'ㅢ'
                                            composeResult()
                                            return UpdatedChars(null,
                                                composedResult.toString()
                                            )
                                        }
                                    }
                                    else -> {
                                        val temp = composedResult.toString()
                                        state = -1
                                        firstChar = nullChar
                                        middleChar = inputChar
                                        composeResult()
                                        composedResult = nullChar
                                        setFirstInputTimer(inputTime = inputTime)
                                        return UpdatedChars(temp, middleChar.toString())
                                    }
                                }
                            }
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
                    isInputCharFirstChar -> { // 자음이 들어온다면
                        setSecondInputTimer(inputTime = inputTime)
                        when (inputChar) {
                            'ㄱ' -> {
                                when (finalChar) {
                                    'ㄱ' -> {
                                        if (secondInputTime - firstInputTime >= separationTime2) {
                                            val temp = composedResult.toString()
                                            state = 1
                                            firstChar = inputChar
                                            middleChar = nullChar
                                            finalChar = nullChar
                                            composeResult()
                                            composedResult = nullChar
                                            setFirstInputTimer(inputTime = inputTime)
                                            return UpdatedChars(temp,
                                                firstChar.toString()
                                            )
                                        } else {
                                            finalChar = 'ㄲ'
                                            composeResult()
                                            return UpdatedChars(null,
                                                composedResult.toString()
                                            )
                                        }
                                    }
                                    'ㄹ' -> {
                                        finalChar = 'ㄺ'
                                        composeResult()
                                        setFirstInputTimer(inputTime = inputTime)
                                        return UpdatedChars(null,
                                            composedResult.toString()
                                        )
                                    }
                                    'ㄺ' -> {
                                        if (secondInputTime - firstInputTime >= separationTime2) { // 갉+ㄱ -> 갉ㄱ
                                            val temp = composedResult.toString()
                                            state = 1
                                            firstChar = inputChar
                                            middleChar = nullChar
                                            finalChar = nullChar
                                            composeResult()
                                            composedResult = nullChar
                                            setFirstInputTimer(inputTime = inputTime)
                                            return UpdatedChars(temp,
                                                firstChar.toString()
                                            )
                                        } else { // 갉+ㄱ -> 갈ㄲ
                                            finalChar = 'ㄹ'
                                            composeResult()
                                            val temp = composedResult.toString()
                                            state = 1
                                            firstChar = 'ㄲ'
                                            middleChar = nullChar
                                            finalChar = nullChar
                                            composeResult()
                                            composedResult = nullChar
                                            setFirstInputTimer(inputTime = inputTime)
                                            return UpdatedChars(temp,
                                                firstChar.toString()
                                            )
                                        }
                                    }
                                    else -> {
                                        val temp = composedResult.toString()
                                        state = 1
                                        firstChar = inputChar
                                        middleChar = nullChar
                                        finalChar = nullChar
                                        composedResult = nullChar
                                        setFirstInputTimer(inputTime = inputTime)
                                        return UpdatedChars(temp, firstChar.toString())
                                    }
                                }
                            }
                            'ㄷ' -> {
                                when (finalChar) {
                                    'ㄷ' -> {
                                        if (secondInputTime - firstInputTime >= separationTime2) { // 닫+ㄷ -> 닫ㄷ
                                            val temp = composedResult.toString()
                                            state = 1
                                            firstChar = inputChar
                                            middleChar = nullChar
                                            finalChar = nullChar
                                            composeResult()
                                            composedResult = nullChar
                                            setFirstInputTimer(inputTime = inputTime)
                                            return UpdatedChars(temp,
                                                firstChar.toString()
                                            )
                                        } else { // 닫+ㄷ -> 다ㄸ
                                            finalChar = nullChar
                                            composeResult()
                                            val temp = composedResult.toString()
                                            state = 1
                                            firstChar = 'ㄸ'
                                            middleChar = nullChar
                                            finalChar = nullChar
                                            composeResult()
                                            composedResult = nullChar
                                            setFirstInputTimer(inputTime = inputTime)
                                            return UpdatedChars(temp,
                                                firstChar.toString()
                                            )
                                        }
                                    }
                                    else -> {
                                        val temp = composedResult.toString()
                                        state = 1
                                        firstChar = inputChar
                                        middleChar = nullChar
                                        finalChar = nullChar
                                        composedResult = nullChar
                                        setFirstInputTimer(inputTime = inputTime)
                                        return UpdatedChars(temp, firstChar.toString())
                                    }
                                }
                            }
                            'ㅁ' -> {
                                when (finalChar) {
                                    'ㄹ' -> {
                                        finalChar = 'ㄻ'
                                        composeResult()
                                        return UpdatedChars(null,
                                            composedResult.toString()
                                        )
                                    }
                                    else -> {
                                        val temp = composedResult.toString()
                                        state = 1
                                        firstChar = inputChar
                                        middleChar = nullChar
                                        finalChar = nullChar
                                        composedResult = nullChar
                                        setFirstInputTimer(inputTime = inputTime)
                                        return UpdatedChars(temp, firstChar.toString())
                                    }
                                }
                            }
                            'ㅂ' -> {
                                when (finalChar) {
                                    'ㄹ' -> {
                                        finalChar = 'ㄼ'
                                        composeResult()
                                        setFirstInputTimer(inputTime = inputTime)
                                        return UpdatedChars(null,
                                            composedResult.toString()
                                        )
                                    }
                                    'ㄼ' -> {
                                        if (secondInputTime - firstInputTime >= separationTime2) { // 밟+ㅂ -> 밟ㅂ
                                            val temp = composedResult.toString()
                                            state = 1
                                            firstChar = inputChar
                                            middleChar = nullChar
                                            finalChar = nullChar
                                            composeResult()
                                            composedResult = nullChar
                                            setFirstInputTimer(inputTime = inputTime)
                                            return UpdatedChars(temp,
                                                firstChar.toString()
                                            )
                                        } else { // 밟+ㅂ -> 발ㅃ
                                            finalChar = 'ㄹ'
                                            composeResult()
                                            val temp = composedResult.toString()
                                            state = 1
                                            firstChar = 'ㅃ'
                                            middleChar = nullChar
                                            finalChar = nullChar
                                            composeResult()
                                            composedResult = nullChar
                                            setFirstInputTimer(inputTime = inputTime)
                                            return UpdatedChars(temp,
                                                firstChar.toString()
                                            )
                                        }
                                    }
                                    'ㅂ' -> {
                                        if (secondInputTime - firstInputTime >= separationTime2) { // 밥+ㅂ -> 밥ㅂ
                                            val temp = composedResult.toString()
                                            state = 1
                                            firstChar = inputChar
                                            middleChar = nullChar
                                            finalChar = nullChar
                                            composeResult()
                                            composedResult = nullChar
                                            setFirstInputTimer(inputTime = inputTime)
                                            return UpdatedChars(temp,
                                                firstChar.toString()
                                            )
                                        } else { // 밥+ㅂ -> 바ㅃ
                                            finalChar = nullChar
                                            composeResult()
                                            val temp = composedResult.toString()
                                            state = 1
                                            firstChar = 'ㅃ'
                                            middleChar = nullChar
                                            finalChar = nullChar
                                            composeResult()
                                            composedResult = nullChar
                                            setFirstInputTimer(inputTime = inputTime)
                                            return UpdatedChars(temp,
                                                firstChar.toString()
                                            )
                                        }
                                    }
                                    else -> {
                                        val temp = composedResult.toString()
                                        state = 1
                                        firstChar = inputChar
                                        middleChar = nullChar
                                        finalChar = nullChar
                                        composedResult = nullChar
                                        setFirstInputTimer(inputTime = inputTime)
                                        return UpdatedChars(temp, firstChar.toString())
                                    }
                                }
                            }
                            'ㅅ' -> {
                                when (finalChar) {
                                    'ㄱ' -> {
                                        finalChar = 'ㄳ'
                                        composeResult()
                                        setFirstInputTimer(inputTime = inputTime)
                                        return UpdatedChars(null,
                                            composedResult.toString()
                                        )
                                    }
                                    'ㄳ' -> {
                                        if (secondInputTime - firstInputTime >= separationTime2) { // 삯+ㅅ -> 삯ㅅ
                                            val temp = composedResult.toString()
                                            state = 1
                                            firstChar = inputChar
                                            middleChar = nullChar
                                            finalChar = nullChar
                                            composeResult()
                                            composedResult = nullChar
                                            setFirstInputTimer(inputTime = inputTime)
                                            return UpdatedChars(temp,
                                                firstChar.toString()
                                            )
                                        } else { // 삯+ㅅ -> 삭ㅆ
                                            finalChar = 'ㄱ'
                                            composeResult()
                                            val temp = composedResult.toString()
                                            state = 1
                                            firstChar = 'ㅆ'
                                            middleChar = nullChar
                                            finalChar = nullChar
                                            composeResult()
                                            composedResult = nullChar
                                            setFirstInputTimer(inputTime = inputTime)
                                            return UpdatedChars(temp,
                                                firstChar.toString()
                                            )
                                        }
                                    }
                                    'ㄹ' -> {
                                        finalChar = 'ㄽ'
                                        composeResult()
                                        setFirstInputTimer(inputTime = inputTime)
                                        return UpdatedChars(null,
                                            composedResult.toString()
                                        )
                                    }
                                    'ㄽ' -> {
                                        if (secondInputTime - firstInputTime >= separationTime2) { // 삸+ㅅ -> 삸ㅅ
                                            val temp = composedResult.toString()
                                            state = 1
                                            firstChar = inputChar
                                            middleChar = nullChar
                                            finalChar = nullChar
                                            composeResult()
                                            composedResult = nullChar
                                            setFirstInputTimer(inputTime = inputTime)
                                            return UpdatedChars(temp,
                                                firstChar.toString()
                                            )
                                        } else { // 삸+ㅅ -> 살ㅆ
                                            finalChar = 'ㄹ'
                                            composeResult()
                                            val temp = composedResult.toString()
                                            state = 1
                                            firstChar = 'ㅆ'
                                            middleChar = nullChar
                                            finalChar = nullChar
                                            composeResult()
                                            composedResult = nullChar
                                            setFirstInputTimer(inputTime = inputTime)
                                            return UpdatedChars(temp,
                                                firstChar.toString()
                                            )
                                        }
                                    }
                                    'ㅂ' -> {
                                        finalChar = 'ㅄ'
                                        composeResult()
                                        setFirstInputTimer(inputTime = inputTime)
                                        return UpdatedChars(null,
                                            composedResult.toString())
                                    }
                                    'ㅄ' -> {
                                        if (secondInputTime - firstInputTime >= separationTime2) { // 삾+ㅅ -> 삾ㅅ
                                            val temp = composedResult.toString()
                                            state = 1
                                            firstChar = inputChar
                                            middleChar = nullChar
                                            finalChar = nullChar
                                            composeResult()
                                            composedResult = nullChar
                                            setFirstInputTimer(inputTime = inputTime)
                                            return UpdatedChars(temp,
                                                firstChar.toString()
                                            )
                                        } else { // 삾+ㅅ -> 삽ㅆ
                                            finalChar = 'ㅂ'
                                            composeResult()
                                            val temp = composedResult.toString()
                                            state = 1
                                            firstChar = 'ㅆ'
                                            middleChar = nullChar
                                            finalChar = nullChar
                                            composeResult()
                                            composedResult = nullChar
                                            setFirstInputTimer(inputTime = inputTime)
                                            return UpdatedChars(temp,
                                                firstChar.toString()
                                            )
                                        }
                                    }
                                    'ㅅ' -> {
                                        if (secondInputTime - firstInputTime >= separationTime2) {
                                            val temp = composedResult.toString()
                                            state = 1
                                            firstChar = inputChar
                                            middleChar = nullChar
                                            finalChar = nullChar
                                            composeResult()
                                            composedResult = nullChar
                                            setFirstInputTimer(inputTime = inputTime)
                                            return UpdatedChars(temp,
                                                firstChar.toString()
                                            )
                                        } else {
                                            finalChar = 'ㅆ'
                                            composeResult()
                                            return UpdatedChars(null,
                                                composedResult.toString()
                                            )
                                        }
                                    }
                                    else -> {
                                        val temp = composedResult.toString()
                                        state = 1
                                        firstChar = inputChar
                                        middleChar = nullChar
                                        finalChar = nullChar
                                        composedResult = nullChar
                                        setFirstInputTimer(inputTime = inputTime)
                                        return UpdatedChars(temp, firstChar.toString())
                                    }
                                }
                            }
                            'ㅈ' -> {
                                when (finalChar) {
                                    'ㄴ' -> {
                                        finalChar = 'ㄵ'
                                        composeResult()
                                        setFirstInputTimer(inputTime = inputTime)
                                        return UpdatedChars(null,
                                            composedResult.toString()
                                        )
                                    }
                                    'ㄵ' -> {
                                        if (secondInputTime - firstInputTime >= separationTime2) { // 잕+ㅈ -> 잕ㅈ
                                            val temp = composedResult.toString()
                                            state = 1
                                            firstChar = inputChar
                                            middleChar = nullChar
                                            finalChar = nullChar
                                            composeResult()
                                            composedResult = nullChar
                                            setFirstInputTimer(inputTime = inputTime)
                                            return UpdatedChars(temp,
                                                firstChar.toString()
                                            )
                                        } else { // 잕+ㅈ -> 잔ㅉ
                                            finalChar = 'ㄴ'
                                            composeResult()
                                            val temp = composedResult.toString()
                                            state = 1
                                            firstChar = 'ㅉ'
                                            middleChar = nullChar
                                            finalChar = nullChar
                                            composeResult()
                                            composedResult = nullChar
                                            setFirstInputTimer(inputTime = inputTime)
                                            return UpdatedChars(temp,
                                                firstChar.toString()
                                            )
                                        }
                                    }
                                    'ㅈ' -> {
                                        if (secondInputTime - firstInputTime >= separationTime2) { // 잦+ㅈ -> 잦ㅈ
                                            val temp = composedResult.toString()
                                            state = 1
                                            firstChar = inputChar
                                            middleChar = nullChar
                                            finalChar = nullChar
                                            composeResult()
                                            composedResult = nullChar
                                            setFirstInputTimer(inputTime = inputTime)
                                            return UpdatedChars(temp,
                                                firstChar.toString()
                                            )
                                        } else { // 잦+ㅈ -> 자ㅉ
                                            finalChar = nullChar
                                            composeResult()
                                            val temp = composedResult.toString()
                                            state = 1
                                            firstChar = 'ㅉ'
                                            middleChar = nullChar
                                            finalChar = nullChar
                                            composeResult()
                                            composedResult = nullChar
                                            setFirstInputTimer(inputTime = inputTime)
                                            return UpdatedChars(temp,
                                                firstChar.toString()
                                            )
                                        }
                                    }
                                    else -> {
                                        val temp = composedResult.toString()
                                        state = 1
                                        firstChar = inputChar
                                        middleChar = nullChar
                                        finalChar = nullChar
                                        composedResult = nullChar
                                        setFirstInputTimer(inputTime = inputTime)
                                        return UpdatedChars(temp, firstChar.toString())
                                    }
                                }
                            }
                            'ㅌ' -> {
                                when (finalChar) {
                                    'ㄹ' -> {
                                        finalChar = 'ㄾ'
                                        composeResult()
                                        return UpdatedChars(null,
                                            composedResult.toString()
                                        )
                                    }
                                    else -> {
                                        val temp = composedResult.toString()
                                        state = 1
                                        firstChar = inputChar
                                        middleChar = nullChar
                                        finalChar = nullChar
                                        composedResult = nullChar
                                        setFirstInputTimer(inputTime = inputTime)
                                        return UpdatedChars(temp, firstChar.toString())
                                    }
                                }
                            }
                            'ㅍ' -> {
                                when (finalChar) {
                                    'ㄹ' -> {
                                        finalChar = 'ㄿ'
                                        composeResult()
                                        return UpdatedChars(null,
                                            composedResult.toString()
                                        )
                                    }
                                    else -> {
                                        val temp = composedResult.toString()
                                        state = 1
                                        firstChar = inputChar
                                        middleChar = nullChar
                                        finalChar = nullChar
                                        composedResult = nullChar
                                        setFirstInputTimer(inputTime = inputTime)
                                        return UpdatedChars(temp, firstChar.toString())
                                    }
                                }
                            }
                            'ㅎ' -> {
                                when (finalChar) {
                                    'ㄴ' -> {
                                        finalChar = 'ㄶ'
                                        composeResult()
                                        return UpdatedChars(null,
                                            composedResult.toString()
                                        )
                                    }
                                    'ㄹ' -> {
                                        finalChar = 'ㅀ'
                                        composeResult()
                                        return UpdatedChars(null,
                                            composedResult.toString()
                                        )
                                    }
                                    else -> {
                                        val temp = composedResult.toString()
                                        state = 1
                                        firstChar = inputChar
                                        middleChar = nullChar
                                        finalChar = nullChar
                                        composedResult = nullChar
                                        setFirstInputTimer(inputTime = inputTime)
                                        return UpdatedChars(temp, firstChar.toString())
                                    }
                                }
                            }
                            else -> { // ㄴㄹㅇㅊㅋ
                                val temp = composedResult.toString()
                                state = 1
                                firstChar = inputChar
                                middleChar = nullChar
                                finalChar = nullChar
                                composedResult = nullChar
                                setFirstInputTimer(inputTime = inputTime)
                                return UpdatedChars(temp, firstChar.toString())
                            }
                        }
                    }
                    isInputCharMiddleChar -> { // 모음이 들어온다면 ex) 간+ㅏ -> 가나
                        val temp: String?
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
                                val finalCharTemp = finalChar //temp 값에 종성 저장
                                finalChar = nullChar // 간 -> 가
                                composeResult()
                                temp = composedResult.toString() //가+ㄴ+ㅏ
                                state = 2
                                firstChar = finalCharTemp //원래 글자의 종성을 새 초성으로 재입력, ㄴ
                            }
                        }
                        middleChar = inputChar
                        composeResult()
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    else -> {
                        val temp = composedResult.toString()
                        state = 0
                        initChar()
                        composeResult()
                        composedResult = nullChar
                        return UpdatedChars(temp + " ", null)
                    }
                }
            }
        }
        return UpdatedChars(null, null)
    }

    fun delete(inputTime: Long): UpdatedChars { // 삭제 버튼
        when ( state ) {
            0-> {
                return UpdatedChars(null, null)
            }
            1 -> {
                when ( firstChar ) {
                    'ㄲ', 'ㄳ' -> {
                        firstChar = 'ㄱ'
                        composeResult()
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(null, firstChar.toString())
                    }
                    'ㄵ', 'ㄶ' -> {
                        firstChar = 'ㄴ'
                        composeResult()
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(null, firstChar.toString())
                    }
                    'ㄸ' -> {
                        firstChar = 'ㄷ'
                        composeResult()
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(null, firstChar.toString())
                    }
                    'ㄺ', 'ㄻ', 'ㄼ', 'ㄽ', 'ㄾ', 'ㄿ', 'ㅀ' -> {
                        firstChar = 'ㄹ'
                        composeResult()
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(null, firstChar.toString())
                    }
                    'ㅃ', 'ㅄ' -> {
                        firstChar = 'ㅂ'
                        composeResult()
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(null, firstChar.toString())
                    }
                    'ㅆ' -> {
                        firstChar = 'ㅅ'
                        composeResult()
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(null, firstChar.toString())
                    }
                    'ㅉ' -> {
                        firstChar = 'ㅈ'
                        composeResult()
                        setFirstInputTimer(inputTime = inputTime)
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
                when ( middleChar ) {
                    'ㅘ', 'ㅙ', 'ㅚ' -> {
                        middleChar = 'ㅗ'
                        composeResult()
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(null, middleChar.toString())
                    }
                    'ㅝ', 'ㅞ', 'ㅟ' -> {
                        middleChar = 'ㅜ'
                        composeResult()
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(null, middleChar.toString())
                    }
                    'ㅢ' -> {
                        middleChar = 'ㅡ'
                        composeResult()
                        setFirstInputTimer(inputTime = inputTime)
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
                when ( middleChar ) {
                    'ㅘ', 'ㅙ', 'ㅚ' -> {
                        middleChar = 'ㅗ'
                        composeResult()
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(null, composedResult.toString())
                    }
                    'ㅝ', 'ㅞ', 'ㅟ' -> {
                        middleChar = 'ㅜ'
                        composeResult()
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(null, composedResult.toString())
                    }
                    'ㅢ' -> {
                        middleChar = 'ㅡ'
                        composeResult()
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(null, composedResult.toString())
                    }
                    else -> {
                        state = 1
                        middleChar = nullChar
                        composeResult()
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(null, firstChar.toString())
                    }
                }
            }
            3 -> {
                when ( finalChar ) {
                    'ㄲ', 'ㄳ' -> {
                        finalChar = 'ㄱ'
                        composeResult()
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(null, composedResult.toString())
                    }
                    'ㄵ', 'ㄶ' -> {
                        finalChar = 'ㄴ'
                        composeResult()
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(null, composedResult.toString())
                    }
                    'ㄺ', 'ㄻ', 'ㄼ', 'ㄽ', 'ㄾ', 'ㄿ', 'ㅀ' -> {
                        finalChar = 'ㄹ'
                        composeResult()
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(null, composedResult.toString())
                    }
                    'ㅄ' -> {
                        finalChar = 'ㅂ'
                        composeResult()
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(null, composedResult.toString())
                    }
                    'ㅆ' -> {
                        finalChar = 'ㅅ'
                        composeResult()
                        setFirstInputTimer(inputTime = inputTime)
                        return UpdatedChars(null, composedResult.toString())
                    }
                    else -> {
                        finalChar = nullChar
                        state = 2
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

    private fun setFirstInputTimer(inputTime: Long) {
        firstInputTime = inputTime
    }
    private fun setSecondInputTimer(inputTime: Long) {
        secondInputTime = inputTime
    }

    private const val separationTime1 = 300
    private const val separationTime2 = 500
}