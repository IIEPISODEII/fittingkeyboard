package com.sb.fittingKeyboard.service.koreanautomata

object HanguelNARATGUL : Automata() {
    fun composeChar(inputChar: Char): UpdatedChars {
        when (state) {
            0 -> when (inputChar) {
                'ㄱ', 'ㄴ', 'ㄹ', 'ㅁ', 'ㅅ', 'ㅇ' -> {
                    firstChar = inputChar
                    firstSubChar = nullChar
                    middleChar = nullChar
                    finalChar = nullChar
                    finalSubChar = nullChar
                    composeResult()
                    composedResult = nullChar
                    state = 1
                    currChar = firstChar
                    return UpdatedChars(null, firstChar.toString())
                }
                'ᆞ', 'ᆢ' -> {
                    return UpdatedChars(null, null)
                }
                'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                    firstChar = nullChar
                    firstSubChar = nullChar
                    middleChar = inputChar
                    finalChar = nullChar
                    finalSubChar = nullChar
                    composeResult()
                    composedResult = nullChar
                    state = -1
                    currChar = middleChar
                    return UpdatedChars(null, middleChar.toString())
                }
                else -> {
                    return UpdatedChars(null, null)
                }
            }
            -1 -> when (middleChar) {
                'ㅏ' -> when (inputChar) {
                    'ㅏ' -> {
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = 'ㅓ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        currChar = middleChar
                        return UpdatedChars(null, middleChar.toString())
                    }
                    'ㅗ', 'ㅡ' -> {
                        val temp = middleChar.toString()
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        currChar = middleChar
                        return UpdatedChars(temp, middleChar.toString())
                    }
                    'ㅣ' -> {
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = 'ㅐ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        currChar = middleChar
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
                        currChar = middleChar
                        return UpdatedChars(null, middleChar.toString())
                    }
                    'ᆢ' -> {
                        return UpdatedChars(null, null)
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
                        currChar = firstChar
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㅐ', 'ㅔ', 'ㅒ', 'ㅖ', 'ㅙ', 'ㅚ', 'ㅞ', 'ㅟ', 'ㅢ' -> when (inputChar) {
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        val temp = middleChar.toString()
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        currChar = middleChar
                        return UpdatedChars(temp, middleChar.toString())
                    }
                    'ᆞ', 'ᆢ' -> {
                        return UpdatedChars(null, null)
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
                        currChar = firstChar
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㅑ' -> when (inputChar) {
                    'ㅏ', 'ㅗ', 'ㅡ' -> {
                        val temp = middleChar.toString()
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        currChar = middleChar
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
                        currChar = middleChar
                        return UpdatedChars(null, middleChar.toString())
                    }
                    'ㅣ' -> {
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = 'ㅒ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        currChar = middleChar
                        return UpdatedChars(null, middleChar.toString())
                    }
                    'ᆢ' -> {
                        return UpdatedChars(null, null)
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
                        currChar = firstChar
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㅓ' -> when (inputChar) {
                    'ㅏ' -> {
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = 'ㅏ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        currChar = middleChar
                        return UpdatedChars(null, middleChar.toString())
                    }
                    'ㅗ', 'ㅡ' -> {
                        val temp = middleChar.toString()
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        currChar = middleChar
                        return UpdatedChars(temp, middleChar.toString())
                    }
                    'ㅣ' -> {
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = 'ㅔ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        currChar = middleChar
                        return UpdatedChars(null, middleChar.toString())
                    }
                    'ᆞ' -> {
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = 'ㅕ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        currChar = middleChar
                        return UpdatedChars(null, middleChar.toString())
                    }
                    'ᆢ' -> {
                        return UpdatedChars(null, null)
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
                        currChar = firstChar
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㅕ' -> when (inputChar) {
                    'ㅏ', 'ㅗ', 'ㅡ' -> {
                        val temp = middleChar.toString()
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        currChar = middleChar
                        return UpdatedChars(temp, middleChar.toString())
                    }
                    'ᆞ' -> {
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = 'ㅓ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        currChar = middleChar
                        return UpdatedChars(null, middleChar.toString())
                    }
                    'ᆢ' -> {
                        return UpdatedChars(null, null)
                    }
                    'ㅣ' -> {
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = 'ㅖ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        currChar = middleChar
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
                        currChar = firstChar
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㅗ' -> when (inputChar) {
                    'ㅏ' -> {
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = 'ㅘ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        currChar = middleChar
                        return UpdatedChars(null, middleChar.toString())
                    }
                    'ㅗ' -> {
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = 'ㅜ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        currChar = middleChar
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
                        currChar = middleChar
                        return UpdatedChars(temp, middleChar.toString())
                    }
                    'ㅣ' -> {
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = 'ㅚ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        currChar = middleChar
                        return UpdatedChars(null, middleChar.toString())
                    }
                    'ᆞ' -> {
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = 'ㅛ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        currChar = middleChar
                        return UpdatedChars(null, middleChar.toString())
                    }
                    'ᆢ' -> {
                        return UpdatedChars(null, null)
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
                        currChar = firstChar
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㅛ' -> when (inputChar) {
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        val temp = middleChar.toString()
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        currChar = middleChar
                        return UpdatedChars(temp, middleChar.toString())
                    }
                    'ᆞ' -> {
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = 'ㅗ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        currChar = middleChar
                        return UpdatedChars(null, middleChar.toString())
                    }
                    'ᆢ' -> {
                        return UpdatedChars(null, null)
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
                        currChar = firstChar
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㅘ' -> when (inputChar) {
                    'ㅏ', 'ㅗ', 'ㅡ' -> {
                        val temp = middleChar.toString()
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        currChar = middleChar
                        return UpdatedChars(temp, middleChar.toString())
                    }
                    'ㅣ' -> {
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = 'ㅙ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        currChar = middleChar
                        return UpdatedChars(null, middleChar.toString())
                    }
                    'ᆞ', 'ᆢ' -> {
                        return UpdatedChars(null, null)
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
                        currChar = firstChar
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㅜ' -> when (inputChar) {
                    'ㅏ' -> {
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = 'ㅝ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        currChar = middleChar
                        return UpdatedChars(null, middleChar.toString())
                    }
                    'ㅗ' -> {
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        currChar = middleChar
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
                        currChar = middleChar
                        return UpdatedChars(null, middleChar.toString())
                    }
                    'ㅣ' -> {
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = 'ㅟ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        currChar = middleChar
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
                        currChar = middleChar
                        return UpdatedChars(null, middleChar.toString())
                    }
                    'ᆢ' -> {
                        return UpdatedChars(null, null)
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
                        currChar = firstChar
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㅠ' -> when (inputChar) {
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        val temp = middleChar.toString()
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        currChar = middleChar
                        return UpdatedChars(temp, middleChar.toString())
                    }
                    'ᆞ' -> {
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = 'ㅜ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        currChar = middleChar
                        return UpdatedChars(null, middleChar.toString())
                    }
                    'ᆢ' -> {
                        return UpdatedChars(null, null)
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
                        currChar = firstChar
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㅝ' -> when (inputChar) {
                    'ㅏ', 'ㅗ', 'ㅡ' -> {
                        val temp = middleChar.toString()
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        currChar = middleChar
                        return UpdatedChars(temp, middleChar.toString())
                    }
                    'ㅣ' -> {
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = 'ㅞ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        currChar = middleChar
                        return UpdatedChars(null, middleChar.toString())
                    }
                    'ᆞ', 'ᆢ' -> {
                        return UpdatedChars(null, null)
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
                        currChar = firstChar
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㅡ' -> when (inputChar) {
                    'ㅏ', 'ㅗ', 'ㅡ' -> {
                        val temp = middleChar.toString()
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        currChar = middleChar
                        return UpdatedChars(temp, middleChar.toString())
                    }
                    'ㅣ' -> {
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = 'ㅢ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        currChar = middleChar
                        return UpdatedChars(null, middleChar.toString())
                    }
                    'ᆞ', 'ᆢ' -> {
                        return UpdatedChars(null, null)
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
                        currChar = firstChar
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㅣ' -> when (inputChar) {
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        val temp = middleChar.toString()
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        currChar = middleChar
                        return UpdatedChars(temp, middleChar.toString())
                    }
                    'ᆞ', 'ᆢ' -> {
                        return UpdatedChars(null, null)
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
                        currChar = firstChar
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                else -> {
                    initChar()
                    state = 0
                    composedResult = nullChar
                    currChar = nullChar
                    return UpdatedChars(inputChar.toString(), null)
                }
            }
            1 -> when (firstChar) {
                'ㄱ' -> when (inputChar) {
                    'ᆞ' -> {
                        when (firstSubChar) {
                            nullChar -> {
                                firstChar = 'ㅋ'
                                firstSubChar = nullChar
                                middleChar = nullChar
                                finalChar = nullChar
                                finalSubChar = nullChar
                                composeResult()
                                composedResult = nullChar
                                currChar = firstChar
                                return UpdatedChars(null, firstChar.toString())
                            }
                            'ㅈ' -> {
                                firstChar = 'ㄱ'
                                firstSubChar = 'ㅊ'
                                middleChar = nullChar
                                finalChar = nullChar
                                finalSubChar = nullChar
                                composeResult()
                                composedResult = nullChar
                                currChar = firstSubChar
                                return UpdatedChars(
                                    null,
                                    firstChar.toString() + firstSubChar.toString()
                                )
                            }
                            'ㅊ' -> {
                                firstChar = 'ㄳ'
                                firstSubChar = nullChar
                                middleChar = nullChar
                                finalChar = nullChar
                                finalSubChar = nullChar
                                composeResult()
                                composedResult = nullChar
                                currChar = firstChar
                                return UpdatedChars(null, firstChar.toString())
                            }
                            else -> {
                                return UpdatedChars(null, null)
                            }
                        }
                    }
                    'ᆢ' -> when (firstSubChar) {
                        nullChar -> {
                            firstChar = 'ㄲ'
                            firstSubChar = nullChar
                            middleChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            currChar = firstChar
                            return UpdatedChars(null, firstChar.toString())
                        }
                        'ㅆ' -> {
                            firstChar = 'ㄳ'
                            firstSubChar = nullChar
                            middleChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            currChar = firstChar
                            return UpdatedChars(null, firstChar.toString())
                        }
                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> when (firstSubChar) {
                        nullChar -> {
                            state = 2
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            currChar = composedResult
                            return UpdatedChars(null, composedResult.toString())
                        }
                        'ㅆ' -> {
                            val temp = firstChar.toString()
                            state = 2
                            firstChar = firstSubChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            currChar = composedResult
                            return UpdatedChars(temp, composedResult.toString())
                        }
                    }
                    'ㅅ' -> when (firstSubChar) {
                        nullChar -> {
                            firstChar = 'ㄳ'
                            firstSubChar = nullChar
                            middleChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            currChar = firstChar
                            return UpdatedChars(null, firstChar.toString())
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
                            currChar = firstChar
                            return UpdatedChars(temp, firstChar.toString())
                        }
                    }
                    else -> when (firstSubChar) {
                        nullChar -> {
                            val temp = firstChar.toString()
                            firstChar = inputChar
                            firstSubChar = nullChar
                            middleChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            currChar = firstChar
                            return UpdatedChars(temp, firstChar.toString())
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
                            currChar = firstChar
                            return UpdatedChars(temp, firstChar.toString())
                        }
                    }
                }
                'ㄲ' -> when (inputChar) {
                    'ᆞ' -> {
                        return UpdatedChars(null, null)
                    }
                    'ᆢ' -> {
                        firstChar = 'ㄱ'
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        currChar = firstChar
                        return UpdatedChars(null, firstChar.toString())
                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        state = 2
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        currChar = composedResult
                        return UpdatedChars(null, composedResult.toString())
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
                        currChar = firstChar
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㄳ' -> when (inputChar) {
                    'ᆞ' -> {
                        firstChar = 'ㄱ'
                        firstSubChar = 'ㅈ'
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        currChar = firstSubChar
                        return UpdatedChars(null, firstChar.toString() + firstSubChar.toString())
                    }
                    'ᆢ' -> {
                        val temp = "ㄱ"
                        firstChar = 'ㅆ'
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        currChar = firstChar
                        return UpdatedChars(temp, firstChar.toString())
                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        val temp = firstChar.toString()
                        state = 2
                        firstChar = 'ㅅ'
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        currChar = composedResult
                        return UpdatedChars(temp, composedResult.toString())
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
                        currChar = firstChar
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㄴ' -> when (inputChar) {
                    'ᆞ' -> when (firstSubChar) {
                        nullChar -> {
                            firstChar = 'ㄷ'
                            firstSubChar = nullChar
                            middleChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            currChar = firstChar
                            return UpdatedChars(null, firstChar.toString())
                        }
                        'ㅅ' -> {
                            firstChar = 'ㄵ'
                            firstSubChar = nullChar
                            middleChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            currChar = firstChar
                            return UpdatedChars(null, firstChar.toString())
                        }
                        'ㅇ' -> {
                            firstChar = 'ㄶ'
                            firstSubChar = nullChar
                            middleChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            currChar = firstChar
                            return UpdatedChars(null, firstChar.toString())
                        }
                        'ㅊ' -> {
                            firstChar = 'ㄴ'
                            firstSubChar = 'ㅅ'
                            middleChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            currChar = firstChar
                            return UpdatedChars(null, firstChar.toString())
                        }
                        else -> {
                            return UpdatedChars(null, null)
                        }
                    }
                    'ᆢ' -> when (firstSubChar) {
                        'ㅉ' -> {
                            firstChar = 'ㄵ'
                            firstSubChar = nullChar
                            middleChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            currChar = firstChar
                            return UpdatedChars(null, firstChar.toString())
                        }
                        else -> {
                            return UpdatedChars(null, null)
                        }
                    }
                    'ㅅ' -> when (firstSubChar) {
                        nullChar -> {
                            firstChar = 'ㄴ'
                            firstSubChar = 'ㅅ'
                            middleChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            currChar = firstSubChar
                            return UpdatedChars(
                                null,
                                firstChar.toString() + firstSubChar.toString()
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
                            currChar = firstChar
                            return UpdatedChars(temp, firstChar.toString())
                        }
                    }
                    'ㅇ' -> when (firstSubChar) {
                        nullChar -> {
                            firstChar = 'ㄴ'
                            firstSubChar = 'ㅇ'
                            middleChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            currChar = firstSubChar
                            return UpdatedChars(
                                null,
                                firstChar.toString() + firstSubChar.toString()
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
                            currChar = firstChar
                            return UpdatedChars(temp, firstChar.toString())
                        }
                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> when (firstSubChar) {
                        nullChar -> {
                            state = 2
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            currChar = composedResult
                            return UpdatedChars(null, composedResult.toString())
                        }
                        else -> {
                            val temp = firstChar.toString()
                            state = 2
                            firstChar = firstSubChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            currChar = composedResult
                            return UpdatedChars(temp, composedResult.toString())
                        }
                    }
                    else -> when (firstSubChar) {
                        nullChar -> {
                            val temp = firstChar.toString()
                            firstChar = inputChar
                            firstSubChar = nullChar
                            middleChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            currChar = firstChar
                            return UpdatedChars(temp, firstChar.toString())
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
                            currChar = firstChar
                            return UpdatedChars(temp, firstChar.toString())
                        }
                    }
                }
                'ㄵ' -> when (inputChar) {
                    'ᆞ' -> {
                        firstChar = 'ㄴ'
                        firstSubChar = 'ㅊ'
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        currChar = firstSubChar
                        return UpdatedChars(null, firstChar.toString() + firstSubChar.toString())
                    }
                    'ᆢ' -> {
                        firstChar = 'ㄴ'
                        firstSubChar = 'ㅉ'
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        currChar = firstSubChar
                        return UpdatedChars(null, firstChar.toString() + firstSubChar.toString())
                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        val temp = 'ㄴ'.toString()
                        state = 2
                        firstChar = 'ㅈ'
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        currChar = composedResult
                        return UpdatedChars(temp, composedResult.toString())
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
                        currChar = firstChar
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㄶ' -> when (inputChar) {
                    'ᆞ' -> {
                        firstChar = 'ㄴ'
                        firstSubChar = 'ㅇ'
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        currChar = firstSubChar
                        return UpdatedChars(null, firstChar.toString() + firstSubChar.toString())
                    }
                    'ᆢ' -> {
                        return UpdatedChars(null, null)
                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        val temp = "ㄴ"
                        state = 2
                        firstChar = 'ㅎ'
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        currChar = composedResult
                        return UpdatedChars(temp, composedResult.toString())
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
                        currChar = firstChar
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㄷ' -> when (inputChar) {
                    'ᆞ' -> {
                        firstChar = 'ㅌ'
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        currChar = firstChar
                        return UpdatedChars(null, firstChar.toString())
                    }
                    'ᆢ' -> {
                        firstChar = 'ㄸ'
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        currChar = firstChar
                        return UpdatedChars(null, firstChar.toString())
                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        state = 2
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        currChar = composedResult
                        return UpdatedChars(null, composedResult.toString())
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
                        currChar = firstChar
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㄸ' -> when (inputChar) {
                    'ᆞ' -> {
                        return UpdatedChars(null, null)
                    }
                    'ᆢ' -> {
                        firstChar = 'ㄷ'
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        currChar = firstChar
                        return UpdatedChars(null, firstChar.toString())
                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        state = 2
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        currChar = composedResult
                        return UpdatedChars(null, composedResult.toString())
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
                        currChar = firstChar
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㄹ' -> when (inputChar) {
                    'ᆞ' -> when (firstSubChar) {
                        nullChar -> {
                            return UpdatedChars(null, null)
                        }
                        'ㄴ' -> {
                            firstChar = 'ㄹ'
                            firstSubChar = 'ㄷ'
                            middleChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            currChar = firstSubChar
                            return UpdatedChars(
                                null,
                                firstChar.toString() + firstSubChar.toString()
                            )
                        }
                        'ㄷ' -> {
                            firstChar = 'ㄾ'
                            firstSubChar = nullChar
                            middleChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            currChar = firstChar
                            return UpdatedChars(null, firstChar.toString())
                        }
                        'ㅇ' -> {
                            firstChar = 'ㅀ'
                            firstSubChar = nullChar
                            middleChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            currChar = firstChar
                            return UpdatedChars(null, firstChar.toString())
                        }
                        'ㅈ' -> {
                            firstChar = 'ㄹ'
                            firstSubChar = 'ㅊ'
                            middleChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            currChar = firstSubChar
                            return UpdatedChars(
                                null,
                                firstChar.toString() + firstSubChar.toString()
                            )
                        }
                        'ㅊ' -> {
                            firstChar = 'ㄽ'
                            firstSubChar = nullChar
                            middleChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            currChar = firstChar
                            return UpdatedChars(null, firstChar.toString())
                        }
                        'ㅋ' -> {
                            firstChar = 'ㄺ'
                            firstSubChar = nullChar
                            middleChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            currChar = firstChar
                            return UpdatedChars(null, firstChar.toString())
                        }
                        else -> {
                            return UpdatedChars(null, null)
                        }
                    }
                    'ᆢ' -> when (firstSubChar) {
                        'ㄲ' -> {
                            firstChar = 'ㄺ'
                            firstSubChar = nullChar
                            middleChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            currChar = firstChar
                            return UpdatedChars(null, firstChar.toString())
                        }
                        'ㄷ' -> {
                            firstChar = 'ㄹ'
                            firstSubChar = 'ㄸ'
                            middleChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            currChar = firstChar
                            return UpdatedChars(null, firstChar.toString() + firstSubChar.toString())
                        }
                        'ㄸ' -> {
                            firstChar = 'ㄹ'
                            firstSubChar = 'ㄷ'
                            middleChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            currChar = firstChar
                            return UpdatedChars(null, firstChar.toString() + firstSubChar.toString())
                        }
                        'ㅃ' -> {
                            firstChar = 'ㄼ'
                            firstSubChar = nullChar
                            middleChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            currChar = firstChar
                            return UpdatedChars(null, firstChar.toString())
                        }
                        'ㅆ' -> {
                            firstChar = 'ㄽ'
                            firstSubChar = nullChar
                            middleChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            currChar = firstChar
                            return UpdatedChars(null, firstChar.toString())
                        }
                        else -> {
                            return UpdatedChars(null, null)
                        }
                    }
                    'ㄱ' -> when (firstSubChar) {
                        nullChar -> {
                            firstChar = 'ㄺ'
                            firstSubChar = nullChar
                            middleChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            currChar = firstChar
                            return UpdatedChars(null, firstChar.toString())
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
                            currChar = firstChar
                            return UpdatedChars(temp, firstChar.toString())
                        }
                    }
                    'ㄴ' -> when (firstSubChar) {
                        nullChar -> {
                            firstChar = 'ㄹ'
                            firstSubChar = 'ㄴ'
                            middleChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            currChar = firstSubChar
                            return UpdatedChars(
                                null,
                                firstChar.toString() + firstSubChar.toString()
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
                            currChar = firstSubChar
                            return UpdatedChars(
                                temp,
                                firstChar.toString() + firstSubChar.toString()
                            )
                        }
                    }
                    'ㅁ' -> when (firstSubChar) {
                        nullChar -> {
                            firstChar = 'ㄻ'
                            firstSubChar = nullChar
                            middleChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            currChar = firstChar
                            return UpdatedChars(null, firstChar.toString())
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
                            currChar = firstChar
                            return UpdatedChars(temp, firstChar.toString())
                        }
                    }
                    'ㅅ' -> when (firstSubChar) {
                        nullChar -> {
                            firstChar = 'ㄽ'
                            firstSubChar = nullChar
                            middleChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            currChar = firstChar
                            return UpdatedChars(null, firstChar.toString())
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
                            currChar = firstChar
                            return UpdatedChars(temp, firstChar.toString())
                        }
                    }
                    'ㅇ' -> when (firstSubChar) {
                        nullChar -> {
                            firstChar = 'ㄹ'
                            firstSubChar = 'ㅇ'
                            middleChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            currChar = firstSubChar
                            return UpdatedChars(
                                null,
                                firstChar.toString() + firstSubChar.toString()
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
                            currChar = firstSubChar
                            return UpdatedChars(
                                temp,
                                firstChar.toString() + firstSubChar.toString()
                            )
                        }
                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> when (firstSubChar) {
                        nullChar -> {
                            state = 2
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            currChar = composedResult
                            return UpdatedChars(null, composedResult.toString())
                        }
                        else -> {
                            val temp = firstChar.toString()
                            state = 2
                            firstChar = firstSubChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            currChar = composedResult
                            return UpdatedChars(temp, composedResult.toString())
                        }
                    }
                    else -> when (firstSubChar) {
                        nullChar -> {
                            val temp = firstChar.toString()
                            firstChar = inputChar
                            firstSubChar = nullChar
                            middleChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            currChar = firstChar
                            return UpdatedChars(temp, firstChar.toString())
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
                            currChar = firstChar
                            return UpdatedChars(temp, firstChar.toString())
                        }
                    }
                }
                'ㄺ' -> when (inputChar) {
                    'ᆞ' -> {
                        firstChar = 'ㄹ'
                        firstSubChar = 'ㅋ'
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        currChar = firstSubChar
                        return UpdatedChars(null, firstChar.toString() + firstSubChar.toString())
                    }
                    'ᆢ' -> {
                        firstChar = 'ㄹ'
                        firstSubChar = 'ㄲ'
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        currChar = firstSubChar
                        return UpdatedChars(null, firstChar.toString() + firstSubChar.toString())
                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        val temp = "ㄹ"
                        state = 2
                        firstChar = 'ㄱ'
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        currChar = composedResult
                        return UpdatedChars(temp, composedResult.toString())
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
                        currChar = firstChar
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㄻ' -> when (inputChar) {
                    'ᆞ' -> {
                        firstChar = 'ㄼ'
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        currChar = firstChar
                        return UpdatedChars(null, firstChar.toString())
                    }
                    'ᆢ' -> {
                        return UpdatedChars(null, null)
                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        val temp = "ㄹ"
                        state = 2
                        firstChar = 'ㅁ'
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        currChar = composedResult
                        return UpdatedChars(temp, composedResult.toString())
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
                        currChar = firstChar
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㄼ' -> when (inputChar) {
                    'ᆞ' -> {
                        firstChar = 'ㄿ'
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        currChar = firstChar
                        return UpdatedChars(null, firstChar.toString())
                    }
                    'ᆢ' -> {
                        firstChar = 'ㄹ'
                        firstSubChar = 'ㅃ'
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        currChar = firstSubChar
                        return UpdatedChars(null, firstChar.toString() + firstSubChar.toString())
                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        val temp = "ㄹ"
                        state = 2
                        firstChar = 'ㅂ'
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        currChar = composedResult
                        return UpdatedChars(temp, composedResult.toString())
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
                        currChar = firstChar
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㄽ' -> when (inputChar) {
                    'ᆞ' -> {
                        firstChar = 'ㄹ'
                        firstSubChar = 'ㅈ'
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        currChar = firstSubChar
                        return UpdatedChars(null, firstChar.toString() + firstSubChar.toString())
                    }
                    'ᆢ' -> {
                        firstChar = 'ㄹ'
                        firstSubChar = 'ㅆ'
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        currChar = firstSubChar
                        return UpdatedChars(null, firstChar.toString() + firstSubChar.toString())
                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        val temp = "ㄹ"
                        state = 2
                        firstChar = 'ㅅ'
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        currChar = composedResult
                        return UpdatedChars(temp, composedResult.toString())
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
                        currChar = firstChar
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㄿ' -> when (inputChar) {
                    'ᆞ' -> {
                        firstChar = 'ㄹ'
                        firstSubChar = 'ㅁ'
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        currChar = firstSubChar
                        return UpdatedChars(null, firstChar.toString() + firstSubChar.toString())
                    }
                    'ᆢ' -> {
                        return UpdatedChars(null, null)
                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        val temp = "ㄹ"
                        state = 2
                        firstChar = 'ㅍ'
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        currChar = composedResult
                        return UpdatedChars(temp, composedResult.toString())
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
                        currChar = firstChar
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㄾ' -> when (inputChar) {
                    'ᆞ' -> {
                        firstChar = 'ㄹ'
                        firstSubChar = 'ㄴ'
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        currChar = firstSubChar
                        return UpdatedChars(null, firstChar.toString() + firstSubChar.toString())
                    }
                    'ᆢ' -> {
                        return UpdatedChars(null, null)
                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        val temp = "ㄹ"
                        state = 2
                        firstChar = 'ㅌ'
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        currChar = composedResult
                        return UpdatedChars(temp, composedResult.toString())
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
                        currChar = firstChar
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㅀ' -> when (inputChar) {
                    'ᆞ' -> {
                        firstChar = 'ㄹ'
                        firstSubChar = 'ㅇ'
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        currChar = firstSubChar
                        return UpdatedChars(null, firstChar.toString() + firstSubChar.toString())
                    }
                    'ᆢ' -> {
                        return UpdatedChars(null, null)
                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        val temp = "ㄹ"
                        state = 2
                        firstChar = 'ㅎ'
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        currChar = composedResult
                        return UpdatedChars(temp, composedResult.toString())
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
                        currChar = firstChar
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㅁ' -> when (inputChar) {
                    'ᆞ' -> {
                        firstChar = 'ㅂ'
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        currChar = firstChar
                        return UpdatedChars(null, firstChar.toString())
                    }
                    'ᆢ' -> {
                        return UpdatedChars(null, null)
                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        state = 2
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        currChar = composedResult
                        return UpdatedChars(null, composedResult.toString())
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
                        currChar = firstChar
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㅂ' -> when (inputChar) {
                    'ᆞ' -> when (firstSubChar) {
                        nullChar -> {
                            firstChar = 'ㅍ'
                            firstSubChar = nullChar
                            middleChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            currChar = firstChar
                            return UpdatedChars(null, firstChar.toString())
                        }
                        'ㅈ' -> {
                            firstChar = 'ㅂ'
                            firstSubChar = 'ㅈ'
                            middleChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            currChar = firstSubChar
                            return UpdatedChars(
                                null,
                                firstChar.toString() + firstSubChar.toString()
                            )
                        }
                        'ㅊ' -> {
                            firstChar = 'ㅄ'
                            firstSubChar = nullChar
                            middleChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            currChar = firstChar
                            return UpdatedChars(null, firstChar.toString())
                        }
                        else -> {
                            return UpdatedChars(null, null)
                        }
                    }
                    'ᆢ' -> when (firstSubChar) {
                        nullChar -> {
                            firstChar = 'ㅃ'
                            firstSubChar = nullChar
                            middleChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            currChar = firstChar
                            return UpdatedChars(null, firstChar.toString())
                        }
                        'ㅆ' -> {
                            firstChar = 'ㅄ'
                            firstSubChar = nullChar
                            middleChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            currChar = firstChar
                            return UpdatedChars(null, firstChar.toString())
                        }
                        'ㅈ' -> {
                            firstChar = 'ㅂ'
                            firstSubChar = 'ㅉ'
                            middleChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            currChar = firstSubChar
                            return UpdatedChars(
                                null,
                                firstChar.toString() + firstSubChar.toString()
                            )
                        }
                        'ㅉ' -> {
                            firstChar = 'ㅂ'
                            firstSubChar = 'ㅈ'
                            middleChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            currChar = firstSubChar
                            return UpdatedChars(
                                null,
                                firstChar.toString() + firstSubChar.toString()
                            )
                        }
                        else -> {
                            return UpdatedChars(null, null)
                        }
                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> when (firstSubChar) {
                        nullChar -> {
                            state = 2
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            currChar = composedResult
                            return UpdatedChars(null, composedResult.toString())
                        }
                        else -> {
                            val temp = firstChar.toString()
                            state = 2
                            firstChar = firstSubChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            currChar = composedResult
                            return UpdatedChars(temp, composedResult.toString())
                        }
                    }
                    'ㅅ' -> when (firstSubChar) {
                        nullChar -> {
                            firstChar = 'ㅄ'
                            firstSubChar = nullChar
                            middleChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            currChar = firstChar
                            return UpdatedChars(null, firstChar.toString())
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
                            currChar = firstChar
                            return UpdatedChars(temp, firstChar.toString())
                        }
                    }
                    else -> when (firstSubChar) {
                        nullChar -> {
                            val temp = firstChar.toString()
                            firstChar = inputChar
                            firstSubChar = nullChar
                            middleChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            currChar = firstChar
                            return UpdatedChars(temp, firstChar.toString())
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
                            currChar = firstChar
                            return UpdatedChars(temp, firstChar.toString())
                        }
                    }
                }
                'ㅃ' -> when (inputChar) {
                    'ᆞ' -> {
                        return UpdatedChars(null, null)
                    }
                    'ᆢ' -> {
                        firstChar = 'ㅂ'
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        currChar = firstChar
                        return UpdatedChars(null, firstChar.toString())
                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        state = 2
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        currChar = composedResult
                        return UpdatedChars(null, composedResult.toString())
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
                        currChar = firstChar
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㅄ' -> when (inputChar) {
                    'ᆞ' -> {
                        firstChar = 'ㅂ'
                        firstSubChar = 'ㅈ'
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        currChar = firstSubChar
                        return UpdatedChars(null, firstChar.toString() + firstSubChar.toString())
                    }
                    'ᆢ' -> {
                        firstChar = 'ㅂ'
                        firstSubChar = 'ㅆ'
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        currChar = firstSubChar
                        return UpdatedChars(null, firstChar.toString() + firstSubChar.toString())
                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        val temp = firstChar.toString()
                        state = 2
                        firstChar = 'ㅅ'
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        currChar = composedResult
                        return UpdatedChars(temp, composedResult.toString())
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
                        currChar = firstChar
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㅅ' -> when (inputChar) {
                    'ᆞ' -> {
                        firstChar = 'ㅈ'
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        currChar = firstChar
                        return UpdatedChars(null, firstChar.toString())
                    }
                    'ᆢ' -> {
                        firstChar = 'ㅆ'
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        currChar = firstChar
                        return UpdatedChars(null, firstChar.toString())
                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        state = 2
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        currChar = composedResult
                        return UpdatedChars(null, composedResult.toString())
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
                        currChar = firstChar
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㅆ' -> when (inputChar) {
                    'ᆞ' -> {
                        return UpdatedChars(null, null)
                    }
                    'ᆢ' -> {
                        firstChar = 'ㅅ'
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        currChar = firstChar
                        return UpdatedChars(null, firstChar.toString())
                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        state = 2
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        currChar = composedResult
                        return UpdatedChars(null, composedResult.toString())
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
                        currChar = firstChar
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㅇ' -> when (inputChar) {
                    'ᆞ' -> {
                        firstChar = 'ㅎ'
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        currChar = firstChar
                        return UpdatedChars(null, firstChar.toString())
                    }
                    'ᆢ' -> {
                        return UpdatedChars(null, null)
                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        state = 2
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        currChar = composedResult
                        return UpdatedChars(null, composedResult.toString())
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
                        currChar = firstChar
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㅈ' -> when (inputChar) {
                    'ᆞ' -> {
                        firstChar = 'ㅊ'
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        currChar = firstChar
                        return UpdatedChars(null, firstChar.toString())
                    }
                    'ᆢ' -> {
                        firstChar = 'ㅉ'
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        currChar = firstChar
                        return UpdatedChars(null, firstChar.toString())
                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        state = 2
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        currChar = composedResult
                        return UpdatedChars(null, composedResult.toString())
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
                        currChar = firstChar
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㅉ' -> when (inputChar) {
                    'ᆞ' -> {
                        return UpdatedChars(null, null)
                    }
                    'ᆢ' -> {
                        firstChar = 'ㅈ'
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        currChar = firstChar
                        return UpdatedChars(null, firstChar.toString())
                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        state = 2
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        currChar = composedResult
                        return UpdatedChars(null, composedResult.toString())
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
                        currChar = firstChar
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㅊ' -> when (inputChar) {
                    'ᆞ' -> {
                        firstChar = 'ㅅ'
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        currChar = firstChar
                        return UpdatedChars(null, firstChar.toString())
                    }
                    'ᆢ' -> {
                        return UpdatedChars(null, null)
                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        state = 2
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        currChar = composedResult
                        return UpdatedChars(null, composedResult.toString())
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
                        currChar = firstChar
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㅋ' -> when (inputChar) {
                    'ᆞ' -> {
                        firstChar = 'ㄱ'
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        currChar = firstChar
                        return UpdatedChars(null, firstChar.toString())
                    }
                    'ᆢ' -> {
                        return UpdatedChars(null, null)
                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        state = 2
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        currChar = composedResult
                        return UpdatedChars(null, composedResult.toString())
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
                        currChar = firstChar
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㅌ' -> when (inputChar) {
                    'ᆞ' -> {
                        firstChar = 'ㄷ'
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        currChar = firstChar
                        return UpdatedChars(null, firstChar.toString())
                    }
                    'ᆢ' -> {
                        return UpdatedChars(null, null)
                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        state = 2
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        currChar = composedResult
                        return UpdatedChars(null, composedResult.toString())
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
                        currChar = firstChar
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㅍ' -> when (inputChar) {
                    'ᆞ' -> {
                        firstChar = 'ㅁ'
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        currChar = firstChar
                        return UpdatedChars(null, firstChar.toString())
                    }
                    'ᆢ' -> {
                        return UpdatedChars(null, null)
                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        state = 2
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        currChar = composedResult
                        return UpdatedChars(null, composedResult.toString())
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
                        currChar = firstChar
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㅎ' -> when (inputChar) {
                    'ᆞ' -> {
                        firstChar = 'ㅇ'
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        currChar = firstChar
                        return UpdatedChars(null, firstChar.toString())
                    }
                    'ᆢ' -> {
                        return UpdatedChars(null, null)
                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        state = 2
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        currChar = composedResult
                        return UpdatedChars(null, composedResult.toString())
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
                        currChar = firstChar
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                else -> {
                    initChar()
                    state = 0
                    composedResult = nullChar
                    currChar = nullChar
                    return UpdatedChars(inputChar.toString(), null)
                }
            }
            2 -> when (middleChar) {
                'ㅏ' -> when (inputChar) {
                    'ᆞ' -> {
                        firstSubChar = nullChar
                        middleChar = 'ㅑ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        currChar = composedResult
                        return UpdatedChars(null, composedResult.toString())
                    }
                    'ᆢ' -> {
                        return UpdatedChars(null, null)
                    }
                    'ㅏ' -> {
                        firstSubChar = nullChar
                        middleChar = 'ㅓ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        currChar = composedResult
                        return UpdatedChars(null, composedResult.toString())
                    }
                    'ㅗ', 'ㅡ' -> {
                        val temp = composedResult.toString()
                        state = -1
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        currChar = middleChar
                        return UpdatedChars(temp, middleChar.toString())
                    }
                    'ㅣ' -> {
                        firstSubChar = nullChar
                        middleChar = 'ㅐ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        currChar = composedResult
                        return UpdatedChars(null, composedResult.toString())
                    }
                    else -> {
                        state = 3
                        firstSubChar = nullChar
                        finalChar = inputChar
                        finalSubChar = nullChar
                        composeResult()
                        currChar = composedResult
                        return UpdatedChars(null, composedResult.toString())
                    }
                }
                'ㅐ', 'ㅔ', 'ㅒ', 'ㅖ', 'ㅙ', 'ㅚ', 'ㅞ', 'ㅟ', 'ㅢ' -> when (inputChar) {
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        val temp = composedResult.toString()
                        state = -1
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        currChar = middleChar
                        return UpdatedChars(temp, middleChar.toString())
                    }
                    'ᆞ', 'ᆢ' -> {
                        return UpdatedChars(null, null)
                    }
                    else -> {
                        state = 3
                        firstSubChar = nullChar
                        finalChar = inputChar
                        finalSubChar = nullChar
                        composeResult()
                        currChar = composedResult
                        return UpdatedChars(null, composedResult.toString())
                    }
                }
                'ㅑ' -> when (inputChar) {
                    'ㅏ', 'ㅗ', 'ㅡ' -> {
                        val temp = composedResult.toString()
                        state = -1
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        currChar = middleChar
                        return UpdatedChars(temp, middleChar.toString())
                    }
                    'ᆞ' -> {
                        firstSubChar = nullChar
                        middleChar = 'ㅏ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        currChar = composedResult
                        return UpdatedChars(null, composedResult.toString())
                    }
                    'ㅣ' -> {
                        firstSubChar = nullChar
                        middleChar = 'ㅒ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        currChar = composedResult
                        return UpdatedChars(null, composedResult.toString())
                    }
                    'ᆢ' -> {
                        return UpdatedChars(null, null)
                    }
                    else -> {
                        state = 3
                        firstSubChar = nullChar
                        finalChar = inputChar
                        finalSubChar = nullChar
                        composeResult()
                        currChar = composedResult
                        return UpdatedChars(null, composedResult.toString())
                    }
                }
                'ㅓ' -> when (inputChar) {
                    'ㅏ' -> {
                        firstSubChar = nullChar
                        middleChar = 'ㅏ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        currChar = composedResult
                        return UpdatedChars(null, composedResult.toString())
                    }
                    'ㅗ', 'ㅡ' -> {
                        val temp = composedResult.toString()
                        state = -1
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        currChar = middleChar
                        return UpdatedChars(temp, middleChar.toString())
                    }
                    'ㅣ' -> {
                        firstSubChar = nullChar
                        middleChar = 'ㅔ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        currChar = composedResult
                        return UpdatedChars(null, composedResult.toString())
                    }
                    'ᆞ' -> {
                        firstSubChar = nullChar
                        middleChar = 'ㅕ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        currChar = composedResult
                        return UpdatedChars(null, composedResult.toString())
                    }
                    'ᆢ' -> {
                        return UpdatedChars(null, null)
                    }
                    else -> {
                        state = 3
                        firstSubChar = nullChar
                        finalChar = inputChar
                        finalSubChar = nullChar
                        composeResult()
                        currChar = composedResult
                        return UpdatedChars(null, composedResult.toString())
                    }
                }
                'ㅕ' -> when (inputChar) {
                    'ㅏ', 'ㅗ', 'ㅡ' -> {
                        val temp = composedResult.toString()
                        state = -1
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        currChar = middleChar
                        return UpdatedChars(temp, middleChar.toString())
                    }
                    'ᆞ' -> {
                        firstSubChar = nullChar
                        middleChar = 'ㅓ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        currChar = composedResult
                        return UpdatedChars(null, composedResult.toString())
                    }
                    'ᆢ' -> {
                        return UpdatedChars(null, null)
                    }
                    'ㅣ' -> {
                        firstSubChar = nullChar
                        middleChar = 'ㅖ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        currChar = composedResult
                        return UpdatedChars(null, composedResult.toString())
                    }
                    else -> {
                        state = 3
                        firstSubChar = nullChar
                        finalChar = inputChar
                        finalSubChar = nullChar
                        composeResult()
                        currChar = composedResult
                        return UpdatedChars(null, composedResult.toString())
                    }
                }
                'ㅗ' -> when (inputChar) {
                    'ㅏ' -> {
                        firstSubChar = nullChar
                        middleChar = 'ㅘ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        currChar = composedResult
                        return UpdatedChars(null, composedResult.toString())
                    }
                    'ㅗ' -> {
                        firstSubChar = nullChar
                        middleChar = 'ㅜ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        currChar = composedResult
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
                        currChar = middleChar
                        return UpdatedChars(temp, middleChar.toString())
                    }
                    'ㅣ' -> {
                        firstSubChar = nullChar
                        middleChar = 'ㅚ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        currChar = composedResult
                        return UpdatedChars(null, composedResult.toString())
                    }
                    'ᆞ' -> {
                        firstSubChar = nullChar
                        middleChar = 'ㅛ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        currChar = composedResult
                        return UpdatedChars(null, composedResult.toString())
                    }
                    'ᆢ' -> {
                        return UpdatedChars(null, null)
                    }
                    else -> {
                        state = 3
                        firstSubChar = nullChar
                        finalChar = inputChar
                        finalSubChar = nullChar
                        composeResult()
                        currChar = composedResult
                        return UpdatedChars(null, composedResult.toString())
                    }
                }
                'ㅛ' -> when (inputChar) {
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        val temp = composedResult.toString()
                        state = -1
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        currChar = middleChar
                        return UpdatedChars(temp, middleChar.toString())
                    }
                    'ᆞ' -> {
                        firstSubChar = nullChar
                        middleChar = 'ㅗ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        currChar = composedResult
                        return UpdatedChars(null, composedResult.toString())
                    }
                    'ᆢ' -> {
                        return UpdatedChars(null, null)
                    }
                    else -> {
                        state = 3
                        firstSubChar = nullChar
                        finalChar = inputChar
                        finalSubChar = nullChar
                        composeResult()
                        currChar = composedResult
                        return UpdatedChars(null, composedResult.toString())
                    }
                }
                'ㅘ' -> when (inputChar) {
                    'ㅏ', 'ㅗ', 'ㅡ' -> {
                        val temp = composedResult.toString()
                        state = -1
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        currChar = middleChar
                        return UpdatedChars(temp, middleChar.toString())
                    }
                    'ㅣ' -> {
                        firstSubChar = nullChar
                        middleChar = 'ㅙ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        currChar = composedResult
                        return UpdatedChars(null, composedResult.toString())
                    }
                    'ᆞ', 'ᆢ' -> {
                        return UpdatedChars(null, null)
                    }
                    else -> {
                        state = 3
                        firstSubChar = nullChar
                        finalChar = inputChar
                        finalSubChar = nullChar
                        composeResult()
                        currChar = composedResult
                        return UpdatedChars(null, composedResult.toString())
                    }
                }
                'ㅜ' -> when (inputChar) {
                    'ㅏ' -> {
                        firstSubChar = nullChar
                        middleChar = 'ㅝ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        currChar = composedResult
                        return UpdatedChars(null, composedResult.toString())
                    }
                    'ㅗ' -> {
                        firstSubChar = nullChar
                        middleChar = 'ㅗ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        currChar = composedResult
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
                        currChar = middleChar
                        return UpdatedChars(temp, middleChar.toString())
                    }
                    'ㅣ' -> {
                        firstSubChar = nullChar
                        middleChar = 'ㅟ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        currChar = composedResult
                        return UpdatedChars(null, composedResult.toString())
                    }
                    'ᆞ' -> {
                        firstSubChar = nullChar
                        middleChar = 'ㅠ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        currChar = composedResult
                        return UpdatedChars(null, composedResult.toString())
                    }
                    'ᆢ' -> {
                        return UpdatedChars(null, null)
                    }
                    else -> {
                        state = 3
                        firstSubChar = nullChar
                        finalChar = inputChar
                        finalSubChar = nullChar
                        composeResult()
                        currChar = composedResult
                        return UpdatedChars(null, composedResult.toString())
                    }
                }
                'ㅠ' -> when (inputChar) {
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        val temp = composedResult.toString()
                        state = -1
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        currChar = middleChar
                        return UpdatedChars(temp, middleChar.toString())
                    }
                    'ᆞ' -> {
                        firstSubChar = nullChar
                        middleChar = 'ㅜ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        currChar = composedResult
                        return UpdatedChars(null, composedResult.toString())
                    }
                    'ᆢ' -> {
                        return UpdatedChars(null, null)
                    }
                    else -> {
                        state = 3
                        firstSubChar = nullChar
                        finalChar = inputChar
                        finalSubChar = nullChar
                        composeResult()
                        currChar = composedResult
                        return UpdatedChars(null, composedResult.toString())
                    }
                }
                'ㅝ' -> when (inputChar) {
                    'ㅏ', 'ㅗ', 'ㅡ' -> {
                        val temp = composedResult.toString()
                        state = -1
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        currChar = middleChar
                        return UpdatedChars(temp, middleChar.toString())
                    }
                    'ㅣ' -> {
                        firstSubChar = nullChar
                        middleChar = 'ㅞ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        currChar = composedResult
                        return UpdatedChars(null, composedResult.toString())
                    }
                    'ᆞ', 'ᆢ' -> {
                        return UpdatedChars(null, null)
                    }
                    else -> {
                        state = 3
                        firstSubChar = nullChar
                        finalChar = inputChar
                        finalSubChar = nullChar
                        composeResult()
                        currChar = composedResult
                        return UpdatedChars(null, composedResult.toString())
                    }
                }
                'ㅡ' -> when (inputChar) {
                    'ㅏ', 'ㅗ', 'ㅡ' -> {
                        val temp = composedResult.toString()
                        state = -1
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        currChar = middleChar
                        return UpdatedChars(temp, middleChar.toString())
                    }
                    'ㅣ' -> {
                        firstSubChar = nullChar
                        middleChar = 'ㅢ'
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        currChar = composedResult
                        return UpdatedChars(null, composedResult.toString())
                    }
                    'ᆞ', 'ᆢ' -> {
                        return UpdatedChars(null, null)
                    }
                    else -> {
                        state = 3
                        firstSubChar = nullChar
                        finalChar = inputChar
                        finalSubChar = nullChar
                        composeResult()
                        currChar = composedResult
                        return UpdatedChars(null, composedResult.toString())
                    }
                }
                'ㅣ' -> when (inputChar) {
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        val temp = composedResult.toString()
                        state = -1
                        firstChar = nullChar
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        currChar = middleChar
                        return UpdatedChars(temp, middleChar.toString())
                    }
                    'ᆞ', 'ᆢ' -> {
                        return UpdatedChars(null, null)
                    }
                    else -> {
                        state = 3
                        firstSubChar = nullChar
                        finalChar = inputChar
                        finalSubChar = nullChar
                        composeResult()
                        currChar = composedResult
                        return UpdatedChars(null, composedResult.toString())
                    }
                }
                else -> {
                    initChar()
                    state = 0
                    composedResult = nullChar
                    currChar = nullChar
                    return UpdatedChars(inputChar.toString(), null)
                }
            }
            3 -> when (finalChar) {
                'ㄱ' -> when (inputChar) {
                    'ᆞ' -> when (finalSubChar) {
                        nullChar -> {
                            firstSubChar = nullChar
                            finalChar = 'ㅋ'
                            finalSubChar = nullChar
                            composeResult()
                            currChar = composedResult
                            return UpdatedChars(null, composedResult.toString())
                        }
                        'ㅈ' -> {
                            firstSubChar = nullChar
                            finalChar = 'ㄱ'
                            finalSubChar = 'ㅊ'
                            composeResult()
                            currChar = finalSubChar
                            return UpdatedChars(
                                null,
                                composedResult.toString() + finalSubChar.toString()
                            )
                        }
                        'ㅊ' -> {
                            firstSubChar = nullChar
                            finalChar = 'ㄳ'
                            finalSubChar = nullChar
                            composeResult()
                            currChar = composedResult
                            return UpdatedChars(null, composedResult.toString())
                        }
                        else -> {
                            return UpdatedChars(null, null)
                        }
                    }
                    'ᆢ' -> when (finalSubChar) {
                        nullChar -> {
                            firstSubChar = nullChar
                            finalChar = 'ㄲ'
                            finalSubChar = nullChar
                            composeResult()
                            currChar = composedResult
                            return UpdatedChars(null, composedResult.toString())
                        }
                        'ㅈ' -> {
                            firstSubChar = nullChar
                            finalChar = 'ㄱ'
                            finalSubChar = 'ㅉ'
                            composeResult()
                            currChar = finalSubChar
                            return UpdatedChars(
                                null,
                                composedResult.toString() + finalSubChar.toString()
                            )
                        }
                        'ㅆ' -> {
                            firstSubChar = nullChar
                            finalChar = 'ㄳ'
                            finalSubChar = nullChar
                            composeResult()
                            currChar = composedResult
                            return UpdatedChars(null, composedResult.toString())
                        }
                        'ㅉ' -> {
                            firstSubChar = nullChar
                            finalChar = 'ㄱ'
                            finalSubChar = 'ㅈ'
                            composeResult()
                            currChar = finalSubChar
                            return UpdatedChars(
                                null,
                                composedResult.toString() + finalSubChar.toString()
                            )
                        }
                        else -> {
                            return UpdatedChars(null, null)
                        }
                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> when (finalSubChar) {
                        nullChar -> {
                            finalChar = nullChar
                            composeResult()
                            val temp = composedResult.toString()
                            state = 2
                            firstChar = 'ㄱ'
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            currChar = composedResult
                            return UpdatedChars(temp, composedResult.toString())
                        }
                        else -> {
                            val temp = composedResult.toString()
                            state = 2
                            firstChar = finalSubChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            currChar = composedResult
                            return UpdatedChars(temp, composedResult.toString())
                        }
                    }
                    'ㅅ' -> when (finalSubChar) {
                        nullChar -> {
                            firstSubChar = nullChar
                            finalChar = 'ㄳ'
                            finalSubChar = nullChar
                            composeResult()
                            currChar = composedResult
                            return UpdatedChars(null, composedResult.toString())
                        }
                        else -> {
                            val temp = composedResult.toString() + finalSubChar.toString()
                            state = 1
                            firstChar = inputChar
                            firstSubChar = nullChar
                            middleChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            currChar = firstChar
                            return UpdatedChars(temp, firstChar.toString())
                        }
                    }
                    else -> when (finalSubChar) {
                        nullChar -> {
                            val temp = composedResult.toString()
                            state = 1
                            firstChar = inputChar
                            firstSubChar = nullChar
                            middleChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            currChar = firstChar
                            return UpdatedChars(temp, firstChar.toString())
                        }
                        else -> {
                            val temp = composedResult.toString() + finalSubChar.toString()
                            state = 1
                            firstChar = inputChar
                            firstSubChar = nullChar
                            middleChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            currChar = firstChar
                            return UpdatedChars(temp, firstChar.toString())
                        }
                    }
                }
                'ㄲ' -> when (inputChar) {
                    'ᆞ' -> {
                        return UpdatedChars(null, null)
                    }
                    'ᆢ' -> {
                        firstSubChar = nullChar
                        finalChar = 'ㄱ'
                        finalSubChar = nullChar
                        composeResult()
                        currChar = composedResult
                        return UpdatedChars(null, composedResult.toString())
                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        finalChar = nullChar
                        composeResult()
                        val temp = composedResult.toString()
                        state = 2
                        firstChar = 'ㄲ'
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalSubChar = nullChar
                        composeResult()
                        currChar = composedResult
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    else -> {
                        val temp = composedResult.toString()
                        state = 1
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        currChar = firstChar
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㄳ' -> when (inputChar) {
                    'ᆞ' -> {
                        firstSubChar = nullChar
                        finalChar = 'ㄱ'
                        finalSubChar = 'ㅈ'
                        composeResult()
                        currChar = finalSubChar
                        return UpdatedChars(
                            null,
                            composedResult.toString() + finalSubChar.toString()
                        )
                    }
                    'ᆢ' -> {
                        firstSubChar = nullChar
                        finalChar = 'ㄱ'
                        finalSubChar = 'ㅆ'
                        composeResult()
                        currChar = finalSubChar
                        return UpdatedChars(
                            null,
                            composedResult.toString() + finalSubChar.toString()
                        )
                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        finalChar = 'ㄱ'
                        composeResult()
                        val temp = composedResult.toString()
                        state = 2
                        firstChar = 'ㅅ'
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        currChar = composedResult
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    else -> {
                        val temp = composedResult.toString()
                        state = 1
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        currChar = firstChar
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㄴ' -> when (inputChar) {
                    'ᆞ' -> when (finalSubChar) {
                        nullChar -> {
                            firstSubChar = nullChar
                            finalChar = 'ㄷ'
                            finalSubChar = nullChar
                            composeResult()
                            currChar = composedResult
                            return UpdatedChars(null, composedResult.toString())
                        }
                        'ㅅ' -> {
                            firstSubChar = nullChar
                            finalChar = 'ㄵ'
                            finalSubChar = nullChar
                            composeResult()
                            currChar = composedResult
                            return UpdatedChars(null, composedResult.toString())
                        }
                        'ㅇ' -> {
                            firstSubChar = nullChar
                            finalChar = 'ㄶ'
                            finalSubChar = nullChar
                            composeResult()
                            currChar = composedResult
                            return UpdatedChars(null, composedResult.toString())
                        }
                        'ㅊ' -> {
                            firstSubChar = nullChar
                            finalChar = 'ㄴ'
                            finalSubChar = 'ㅅ'
                            composeResult()
                            currChar = finalSubChar
                            return UpdatedChars(
                                null,
                                composedResult.toString() + finalSubChar.toString()
                            )
                        }
                    }
                    'ᆢ' -> when (finalSubChar) {
                        nullChar -> {
                            return UpdatedChars(null, null)
                        }
                        'ㅅ' -> {
                            firstSubChar = nullChar
                            finalChar = 'ㄴ'
                            finalSubChar = 'ㅆ'
                            composeResult()
                            currChar = finalSubChar
                            return UpdatedChars(
                                null,
                                composedResult.toString() + finalSubChar.toString()
                            )
                        }
                        'ㅆ' -> {
                            firstSubChar = nullChar
                            finalChar = 'ㄴ'
                            finalSubChar = 'ㅅ'
                            composeResult()
                            currChar = finalSubChar
                            return UpdatedChars(
                                null,
                                composedResult.toString() + finalSubChar.toString()
                            )
                        }
                        'ㅉ' -> {
                            firstSubChar = nullChar
                            finalChar = 'ㄵ'
                            finalSubChar = nullChar
                            composeResult()
                            currChar = composedResult
                            return UpdatedChars(null, composedResult.toString())
                        }
                        else -> {
                            return UpdatedChars(null, null)
                        }
                    }
                    'ㅅ' -> when (finalSubChar) {
                        nullChar -> {
                            firstSubChar = nullChar
                            finalChar = 'ㄴ'
                            finalSubChar = 'ㅅ'
                            composeResult()
                            currChar = finalSubChar
                            return UpdatedChars(
                                null,
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
                            composeResult()
                            currChar = firstChar
                            return UpdatedChars(temp, firstChar.toString())
                        }
                    }
                    'ㅇ' -> when (finalSubChar) {
                        nullChar -> {
                            firstSubChar = nullChar
                            finalChar = 'ㄴ'
                            finalSubChar = 'ㅇ'
                            composeResult()
                            currChar = finalSubChar
                            return UpdatedChars(
                                null,
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
                            composeResult()
                            composedResult = nullChar
                            currChar = firstChar
                            return UpdatedChars(temp, firstChar.toString())
                        }
                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> when (finalSubChar) {
                        nullChar -> {
                            finalChar = nullChar
                            composeResult()
                            val temp = composedResult.toString()
                            state = 2
                            firstChar = 'ㄴ'
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            currChar = composedResult
                            return UpdatedChars(temp, composedResult.toString())
                        }
                        else -> {
                            val temp = composedResult.toString()
                            state = 2
                            firstChar = finalSubChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            currChar = composedResult
                            return UpdatedChars(temp, composedResult.toString())
                        }
                    }
                    else -> when (finalSubChar) {
                        nullChar -> {
                            val temp = composedResult.toString()
                            state = 1
                            firstChar = inputChar
                            firstSubChar = nullChar
                            middleChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            currChar = firstChar
                            return UpdatedChars(temp, firstChar.toString())
                        }
                        else -> {
                            val temp = composedResult.toString() + finalSubChar.toString()
                            state = 1
                            firstChar = inputChar
                            firstSubChar = nullChar
                            middleChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            currChar = firstChar
                            return UpdatedChars(temp, firstChar.toString())
                        }
                    }
                }
                'ㄵ' -> when (inputChar) {
                    'ᆞ' -> {
                        firstSubChar = nullChar
                        finalChar = 'ㄴ'
                        finalSubChar = 'ㅊ'
                        composeResult()
                        currChar = finalSubChar
                        return UpdatedChars(
                            null,
                            composedResult.toString() + finalSubChar.toString()
                        )
                    }
                    'ᆢ' -> {
                        firstSubChar = nullChar
                        finalChar = 'ㄴ'
                        finalSubChar = 'ㅉ'
                        composeResult()
                        currChar = finalSubChar
                        return UpdatedChars(
                            null,
                            composedResult.toString() + finalSubChar.toString()
                        )
                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        finalChar = 'ㄴ'
                        composeResult()
                        val temp = composedResult.toString()
                        state = 2
                        firstChar = 'ㅈ'
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        currChar = composedResult
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    else -> {
                        val temp = composedResult.toString()
                        state = 1
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        currChar = firstChar
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㄶ' -> when (inputChar) {
                    'ᆞ' -> {
                        firstSubChar = nullChar
                        finalChar = 'ㄴ'
                        finalSubChar = 'ㅇ'
                        composeResult()
                        currChar = finalSubChar
                        return UpdatedChars(
                            null,
                            composedResult.toString() + finalSubChar.toString()
                        )
                    }
                    'ᆢ' -> {
                        return UpdatedChars(null, null)
                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        finalChar = 'ㄴ'
                        composeResult()
                        val temp = composedResult.toString()
                        state = 2
                        firstChar = 'ㅎ'
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        currChar = composedResult
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    else -> {
                        val temp = composedResult.toString()
                        state = 1
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        currChar = firstChar
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㄷ' -> when (inputChar) {
                    'ᆞ' -> {
                        firstSubChar = nullChar
                        finalChar = 'ㅌ'
                        finalSubChar = nullChar
                        composeResult()
                        currChar = composedResult
                        return UpdatedChars(null, composedResult.toString())
                    }
                    'ᆢ' -> {
                        firstSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = 'ㄸ'
                        composeResult()
                        currChar = finalSubChar
                        return UpdatedChars(
                            null,
                            composedResult.toString() + finalSubChar.toString()
                        )
                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        finalChar = nullChar
                        composeResult()
                        val temp = composedResult.toString()
                        state = 2
                        firstChar = 'ㄷ'
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        currChar = composedResult
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    else -> {
                        val temp = composedResult.toString()
                        state = 1
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        currChar = firstChar
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㄹ' -> when (inputChar) {
                    'ᆞ' -> when (finalSubChar) {
                        nullChar -> {
                            return UpdatedChars(null, null)
                        }
                        'ㅋ' -> {
                            firstSubChar = nullChar
                            finalChar = 'ㄺ'
                            finalSubChar = nullChar
                            composeResult()
                            currChar = composedResult
                            return UpdatedChars(null, composedResult.toString())
                        }
                        'ㄴ' -> {
                            firstSubChar = nullChar
                            finalChar = 'ㄹ'
                            finalSubChar = 'ㄷ'
                            composeResult()
                            currChar = finalSubChar
                            return UpdatedChars(
                                null,
                                composedResult.toString() + finalSubChar.toString()
                            )
                        }
                        'ㄷ' -> {
                            firstSubChar = nullChar
                            finalChar = 'ㄾ'
                            finalSubChar = nullChar
                            composeResult()
                            currChar = composedResult
                            return UpdatedChars(null, composedResult.toString())
                        }
                        'ㅇ' -> {
                            firstSubChar = nullChar
                            finalChar = 'ㅀ'
                            finalSubChar = nullChar
                            composeResult()
                            currChar = composedResult
                            return UpdatedChars(null, composedResult.toString())
                        }
                        'ㅈ' -> {
                            firstSubChar = nullChar
                            finalChar = 'ㄹ'
                            finalSubChar = 'ㅊ'
                            composeResult()
                            currChar = finalSubChar
                            return UpdatedChars(
                                null,
                                composedResult.toString() + finalSubChar.toString()
                            )
                        }
                        'ㅊ' -> {
                            firstSubChar = nullChar
                            finalChar = 'ㄽ'
                            finalSubChar = nullChar
                            composeResult()
                            currChar = composedResult
                            return UpdatedChars(null, composedResult.toString())
                        }
                        else -> {
                            return UpdatedChars(null, null)
                        }
                    }
                    'ᆢ' -> when (finalSubChar) {
                        nullChar -> {
                            return UpdatedChars(null, null)
                        }
                        'ㄲ' -> {
                            firstSubChar = nullChar
                            finalChar = 'ㄺ'
                            finalSubChar = nullChar
                            composeResult()
                            currChar = composedResult
                            return UpdatedChars(null, composedResult.toString())
                        }
                        'ㄷ' -> {
                            firstSubChar = nullChar
                            finalChar = 'ㄹ'
                            finalSubChar = 'ㄸ'
                            composeResult()
                            currChar = finalSubChar
                            return UpdatedChars(
                                null,
                                composedResult.toString() + finalSubChar.toString()
                            )
                        }
                        'ㄸ' -> {
                            firstSubChar = nullChar
                            finalChar = 'ㄹ'
                            finalSubChar = 'ㄷ'
                            composeResult()
                            currChar = finalSubChar
                            return UpdatedChars(
                                null,
                                composedResult.toString() + finalSubChar.toString()
                            )
                        }
                        'ㅃ' -> {
                            firstSubChar = nullChar
                            finalChar = 'ㄼ'
                            finalSubChar = nullChar
                            composeResult()
                            currChar = composedResult
                            return UpdatedChars(null, composedResult.toString())
                        }
                        'ㅆ' -> {
                            firstSubChar = nullChar
                            finalChar = 'ㄽ'
                            finalSubChar = nullChar
                            composeResult()
                            currChar = composedResult
                            return UpdatedChars(null, composedResult.toString())
                        }
                        'ㅈ' -> {
                            firstSubChar = nullChar
                            finalChar = 'ㄹ'
                            finalSubChar = 'ㅉ'
                            composeResult()
                            currChar = finalSubChar
                            return UpdatedChars(
                                null,
                                composedResult.toString() + finalSubChar.toString()
                            )
                        }
                        'ㅉ' -> {
                            firstSubChar = nullChar
                            finalChar = 'ㄹ'
                            finalSubChar = 'ㅈ'
                            composeResult()
                            currChar = finalSubChar
                            return UpdatedChars(
                                null,
                                composedResult.toString() + finalSubChar.toString()
                            )
                        }
                        else -> {
                            return UpdatedChars(null, null)
                        }
                    }
                    'ㄱ' -> when (finalSubChar) {
                        nullChar -> {
                            firstSubChar = nullChar
                            finalChar = 'ㄺ'
                            finalSubChar = nullChar
                            composeResult()
                            currChar = composedResult
                            return UpdatedChars(null, composedResult.toString())
                        }
                        else -> {
                            val temp = composedResult.toString() + finalSubChar.toString()
                            state = 1
                            firstChar = inputChar
                            firstSubChar = nullChar
                            middleChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            currChar = firstChar
                            return UpdatedChars(temp, firstChar.toString())
                        }
                    }
                    'ㄴ' -> when (finalSubChar) {
                        nullChar -> {
                            firstSubChar = nullChar
                            finalChar = 'ㄹ'
                            finalSubChar = 'ㄴ'
                            composeResult()
                            currChar = finalSubChar
                            return UpdatedChars(
                                null,
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
                            composeResult()
                            composedResult = nullChar
                            currChar = firstChar
                            return UpdatedChars(temp, firstChar.toString())
                        }
                    }
                    'ㅁ' -> when (finalSubChar) {
                        nullChar -> {
                            firstSubChar = nullChar
                            finalChar = 'ㄻ'
                            finalSubChar = nullChar
                            composeResult()
                            currChar = composedResult
                            return UpdatedChars(null, composedResult.toString())
                        }
                        else -> {
                            val temp = composedResult.toString() + finalSubChar.toString()
                            state = 1
                            firstChar = inputChar
                            firstSubChar = nullChar
                            middleChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            currChar = firstChar
                            return UpdatedChars(temp, firstChar.toString())
                        }
                    }
                    'ㅅ' -> when (finalSubChar) {
                        nullChar -> {
                            firstSubChar = nullChar
                            finalChar = 'ㄽ'
                            finalSubChar = nullChar
                            composeResult()
                            currChar = composedResult
                            return UpdatedChars(null, composedResult.toString())
                        }
                        else -> {
                            val temp = composedResult.toString() + finalSubChar.toString()
                            state = 1
                            firstChar = inputChar
                            firstSubChar = nullChar
                            middleChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            currChar = firstChar
                            return UpdatedChars(temp, firstChar.toString())
                        }
                    }
                    'ㅇ' -> when (finalSubChar) {
                        nullChar -> {
                            firstSubChar = nullChar
                            finalChar = 'ㄹ'
                            finalSubChar = 'ㅇ'
                            composeResult()
                            currChar = finalSubChar
                            return UpdatedChars(
                                null,
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
                            composeResult()
                            composedResult = nullChar
                            currChar = firstChar
                            return UpdatedChars(temp, firstChar.toString())
                        }
                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> when (finalSubChar) {
                        nullChar -> {
                            finalChar = nullChar
                            composeResult()
                            val temp = composedResult.toString()
                            state = 2
                            firstChar = 'ㄹ'
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            currChar = composedResult
                            return UpdatedChars(temp, composedResult.toString())
                        }
                        else -> {
                            val temp = composedResult.toString()
                            state = 2
                            firstChar = finalSubChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            currChar = composedResult
                            return UpdatedChars(temp, composedResult.toString())
                        }
                    }
                    else -> when (finalSubChar) {
                        nullChar -> {
                            val temp = composedResult.toString()
                            state = 1
                            firstChar = inputChar
                            firstSubChar = nullChar
                            middleChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            currChar = firstChar
                            return UpdatedChars(temp, firstChar.toString())
                        }
                        else -> {
                            val temp = composedResult.toString() + finalSubChar.toString()
                            state = 1
                            firstChar = inputChar
                            firstSubChar = nullChar
                            middleChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            currChar = firstChar
                            return UpdatedChars(temp, firstChar.toString())
                        }
                    }
                }
                'ㄺ' -> when (inputChar) {
                    'ᆞ' -> {
                        firstSubChar = nullChar
                        finalChar = 'ㄹ'
                        finalSubChar = 'ㅋ'
                        composeResult()
                        currChar = finalSubChar
                        return UpdatedChars(
                            null,
                            composedResult.toString() + finalSubChar.toString()
                        )
                    }
                    'ᆢ' -> {
                        firstSubChar = nullChar
                        finalChar = 'ㄹ'
                        finalSubChar = 'ㄲ'
                        composeResult()
                        currChar = finalSubChar
                        return UpdatedChars(
                            null,
                            composedResult.toString() + finalSubChar.toString()
                        )
                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        finalChar = 'ㄹ'
                        composeResult()
                        val temp = composedResult.toString()
                        state = 2
                        firstChar = 'ㄱ'
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        currChar = composedResult
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    else -> {
                        val temp = composedResult.toString()
                        state = 1
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        currChar = firstChar
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㄻ' -> when (inputChar) {
                    'ᆞ' -> {
                        firstSubChar = nullChar
                        finalChar = 'ㄼ'
                        finalSubChar = nullChar
                        composeResult()
                        currChar = composedResult
                        return UpdatedChars(null, composedResult.toString())
                    }
                    'ᆢ' -> {
                        return UpdatedChars(null, null)
                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        finalChar = 'ㄹ'
                        composeResult()
                        val temp = composedResult.toString()
                        state = 2
                        firstChar = 'ㅁ'
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        currChar = composedResult
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    else -> {
                        val temp = composedResult.toString()
                        state = 1
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        currChar = firstChar
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㄼ' -> when (inputChar) {
                    'ᆞ' -> {
                        firstSubChar = nullChar
                        finalChar = 'ㄿ'
                        finalSubChar = nullChar
                        composeResult()
                        currChar = composedResult
                        return UpdatedChars(null, composedResult.toString())
                    }
                    'ᆢ' -> {
                        firstSubChar = nullChar
                        finalChar = 'ㄹ'
                        finalSubChar = 'ㅃ'
                        composeResult()
                        currChar = finalSubChar
                        return UpdatedChars(
                            null,
                            composedResult.toString() + finalSubChar.toString()
                        )
                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        finalChar = 'ㄹ'
                        composeResult()
                        val temp = composedResult.toString()
                        state = 2
                        firstChar = 'ㅂ'
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        currChar = composedResult
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    else -> {
                        val temp = composedResult.toString()
                        state = 1
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        currChar = firstChar
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㄽ' -> when (inputChar) {
                    'ᆞ' -> {
                        firstSubChar = nullChar
                        finalChar = 'ㄹ'
                        finalSubChar = 'ㅈ'
                        composeResult()
                        currChar = finalSubChar
                        return UpdatedChars(
                            null,
                            composedResult.toString() + finalSubChar.toString()
                        )
                    }
                    'ᆢ' -> {
                        firstSubChar = nullChar
                        finalChar = 'ㄹ'
                        finalSubChar = 'ㅆ'
                        composeResult()
                        currChar = finalSubChar
                        return UpdatedChars(
                            null,
                            composedResult.toString() + finalSubChar.toString()
                        )
                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        finalChar = 'ㄹ'
                        composeResult()
                        val temp = composedResult.toString()
                        state = 2
                        firstChar = 'ㅅ'
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        currChar = composedResult
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    else -> {
                        val temp = composedResult.toString()
                        state = 1
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        currChar = firstChar
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㄾ' -> when (inputChar) {
                    'ᆞ' -> {
                        firstSubChar = nullChar
                        finalChar = 'ㄹ'
                        finalSubChar = 'ㄴ'
                        composeResult()
                        currChar = composedResult
                        return UpdatedChars(null, composedResult.toString() + finalSubChar.toString())
                    }
                    'ᆢ' -> {
                        return UpdatedChars(null, null)
                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        finalChar = 'ㄹ'
                        composeResult()
                        val temp = composedResult.toString()
                        state = 2
                        firstChar = 'ㅌ'
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        currChar = composedResult
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    else -> {
                        val temp = composedResult.toString()
                        state = 1
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        currChar = firstChar
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㄿ' -> when (inputChar) {
                    'ᆞ' -> {
                        firstSubChar = nullChar
                        finalChar = 'ㄻ'
                        finalSubChar = nullChar
                        composeResult()
                        currChar = composedResult
                        return UpdatedChars(null, composedResult.toString())
                    }
                    'ᆢ' -> {
                        return UpdatedChars(null, null)
                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        finalChar = 'ㄹ'
                        composeResult()
                        val temp = composedResult.toString()
                        state = 2
                        firstChar = 'ㅍ'
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        currChar = composedResult
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    else -> {
                        val temp = composedResult.toString()
                        state = 1
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        currChar = firstChar
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㅀ' -> when (inputChar) {
                    'ᆞ' -> {
                        firstSubChar = nullChar
                        finalChar = 'ㄹ'
                        finalSubChar = 'ㅇ'
                        composeResult()
                        currChar = finalSubChar
                        return UpdatedChars(
                            null,
                            composedResult.toString() + finalSubChar.toString()
                        )
                    }
                    'ᆢ' -> {
                        return UpdatedChars(null, null)
                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        finalChar = 'ㄹ'
                        composeResult()
                        val temp = composedResult.toString()
                        state = 2
                        firstChar = 'ㅎ'
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        currChar = composedResult
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    else -> {
                        val temp = composedResult.toString()
                        state = 1
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        currChar = firstChar
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㅁ' -> when (inputChar) {
                    'ᆞ' -> {
                        firstSubChar = nullChar
                        finalChar = 'ㅂ'
                        finalSubChar = nullChar
                        composeResult()
                        currChar = composedResult
                        return UpdatedChars(null, composedResult.toString())
                    }
                    'ᆢ' -> {
                        return UpdatedChars(null, null)
                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        finalChar = nullChar
                        composeResult()
                        val temp = composedResult.toString()
                        state = 2
                        firstChar = 'ㅁ'
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        currChar = composedResult
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    else -> {
                        val temp = composedResult.toString()
                        state = 1
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        currChar = firstChar
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㅂ' -> when (inputChar) {
                    'ᆞ' -> when (finalSubChar) {
                        nullChar -> {
                            firstSubChar = nullChar
                            finalChar = 'ㅍ'
                            finalSubChar = nullChar
                            composeResult()
                            currChar = composedResult
                            return UpdatedChars(null, composedResult.toString())
                        }
                        'ㅈ' -> {
                            firstSubChar = nullChar
                            finalChar = 'ㅂ'
                            finalSubChar = 'ㅊ'
                            composeResult()
                            currChar = finalSubChar
                            return UpdatedChars(
                                null,
                                composedResult.toString() + finalSubChar.toString()
                            )
                        }
                        'ㅊ' -> {
                            firstSubChar = nullChar
                            finalChar = 'ㅄ'
                            finalSubChar = nullChar
                            composeResult()
                            currChar = composedResult
                            return UpdatedChars(null, composedResult.toString())
                        }
                        else -> {
                            return UpdatedChars(null, null)
                        }
                    }
                    'ᆢ' -> when (finalSubChar) {
                        nullChar -> {
                            firstSubChar = nullChar
                            finalChar = nullChar
                            finalSubChar = 'ㅃ'
                            composeResult()
                            currChar = finalSubChar
                            return UpdatedChars(
                                null,
                                composedResult.toString() + finalSubChar.toString()
                            )
                        }
                        'ㅆ' -> {
                            firstSubChar = nullChar
                            finalChar = 'ㅄ'
                            finalSubChar = nullChar
                            composeResult()
                            currChar = composedResult
                            return UpdatedChars(null, composedResult.toString())
                        }
                        'ㅉ' -> {
                            firstSubChar = nullChar
                            finalChar = 'ㅂ'
                            finalSubChar = 'ㅈ'
                            composeResult()
                            currChar = finalSubChar
                            return UpdatedChars(
                                null,
                                composedResult.toString() + finalSubChar.toString()
                            )
                        }
                        else -> {
                            return UpdatedChars(null, null)
                        }
                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> when (finalSubChar) {
                        nullChar -> {
                            finalChar = nullChar
                            composeResult()
                            val temp = composedResult.toString()
                            state = 2
                            firstChar = 'ㅂ'
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            currChar = composedResult
                            return UpdatedChars(temp, composedResult.toString())
                        }
                        else -> {
                            val temp = composedResult.toString()
                            state = 2
                            firstChar = finalSubChar
                            firstSubChar = nullChar
                            middleChar = inputChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            currChar = composedResult
                            return UpdatedChars(temp, composedResult.toString())
                        }
                    }
                    'ㅅ' -> when (finalSubChar) {
                        nullChar -> {
                            firstSubChar = nullChar
                            finalChar = 'ㅄ'
                            finalSubChar = nullChar
                            composeResult()
                            currChar = composedResult
                            return UpdatedChars(null, composedResult.toString())
                        }
                        else -> {
                            val temp = composedResult.toString() + finalSubChar.toString()
                            state = 1
                            firstChar = inputChar
                            firstSubChar = nullChar
                            middleChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            currChar = firstChar
                            return UpdatedChars(temp, firstChar.toString())
                        }
                    }
                    else -> when (finalSubChar) {
                        nullChar -> {
                            val temp = composedResult.toString()
                            state = 1
                            firstChar = inputChar
                            firstSubChar = nullChar
                            middleChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            currChar = firstChar
                            return UpdatedChars(temp, firstChar.toString())
                        }
                        else -> {
                            val temp = composedResult.toString() + finalSubChar.toString()
                            state = 1
                            firstChar = inputChar
                            firstSubChar = nullChar
                            middleChar = nullChar
                            finalChar = nullChar
                            finalSubChar = nullChar
                            composeResult()
                            composedResult = nullChar
                            currChar = firstChar
                            return UpdatedChars(temp, firstChar.toString())
                        }
                    }
                }
                'ㅄ' -> when (inputChar) {
                    'ᆞ' -> {
                        firstSubChar = nullChar
                        finalChar = 'ㅂ'
                        finalSubChar = 'ㅈ'
                        composeResult()
                        currChar = finalSubChar
                        return UpdatedChars(
                            null,
                            composedResult.toString() + finalSubChar.toString()
                        )
                    }
                    'ᆢ' -> {
                        firstSubChar = nullChar
                        finalChar = 'ㅂ'
                        finalSubChar = 'ㅆ'
                        composeResult()
                        currChar = finalSubChar
                        return UpdatedChars(
                            null,
                            composedResult.toString() + finalSubChar.toString()
                        )
                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        finalChar = 'ㅂ'
                        composeResult()
                        val temp = composedResult.toString()
                        state = 2
                        firstChar = 'ㅅ'
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        currChar = composedResult
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    else -> {
                        val temp = composedResult.toString()
                        state = 1
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        currChar = firstChar
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㅅ' -> when (inputChar) {
                    'ᆞ' -> {
                        firstSubChar = nullChar
                        finalChar = 'ㅈ'
                        finalSubChar = nullChar
                        composeResult()
                        currChar = composedResult
                        return UpdatedChars(null, composedResult.toString())
                    }
                    'ᆢ' -> {
                        firstSubChar = nullChar
                        finalChar = 'ㅆ'
                        finalSubChar = nullChar
                        composeResult()
                        currChar = composedResult
                        return UpdatedChars(null, composedResult.toString())
                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        finalChar = nullChar
                        composeResult()
                        val temp = composedResult.toString()
                        state = 2
                        firstChar = 'ㅅ'
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        currChar = composedResult
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    else -> {
                        val temp = composedResult.toString()
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
                'ㅆ' -> when (inputChar) {
                    'ᆞ' -> {
                        return UpdatedChars(null, null)
                    }
                    'ᆢ' -> {
                        firstSubChar = nullChar
                        finalChar = 'ㅅ'
                        finalSubChar = nullChar
                        composeResult()
                        currChar = composedResult
                        return UpdatedChars(null, composedResult.toString())
                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        finalChar = nullChar
                        composeResult()
                        val temp = composedResult.toString()
                        state = 2
                        firstChar = 'ㅆ'
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        currChar = composedResult
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    else -> {
                        val temp = composedResult.toString()
                        state = 1
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        currChar = firstChar
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㅇ' -> when (inputChar) {
                    'ᆞ' -> {
                        firstSubChar = nullChar
                        finalChar = 'ㅎ'
                        finalSubChar = nullChar
                        composeResult()
                        currChar = composedResult
                        return UpdatedChars(null, composedResult.toString())
                    }
                    'ᆢ' -> {
                        return UpdatedChars(null, null)
                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        finalChar = nullChar
                        composeResult()
                        val temp = composedResult.toString()
                        state = 2
                        firstChar = 'ㅇ'
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        currChar = composedResult
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    else -> {
                        val temp = composedResult.toString()
                        state = 1
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        currChar = firstChar
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㅈ' -> when (inputChar) {
                    'ᆞ' -> {
                        firstSubChar = nullChar
                        finalChar = 'ㅊ'
                        finalSubChar = nullChar
                        composeResult()
                        currChar = composedResult
                        return UpdatedChars(null, composedResult.toString())
                    }
                    'ᆢ' -> {
                        firstSubChar = nullChar
                        finalChar = nullChar
                        finalSubChar = 'ㅉ'
                        composeResult()
                        currChar = finalSubChar
                        return UpdatedChars(
                            null,
                            composedResult.toString() + finalSubChar.toString()
                        )
                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        finalChar = nullChar
                        composeResult()
                        val temp = composedResult.toString()
                        state = 2
                        firstChar = 'ㅈ'
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        currChar = composedResult
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    else -> {
                        val temp = composedResult.toString()
                        state = 1
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        currChar = firstChar
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㅊ' -> when (inputChar) {
                    'ᆞ' -> {
                        firstSubChar = nullChar
                        finalChar = 'ㅅ'
                        finalSubChar = nullChar
                        composeResult()
                        currChar = composedResult
                        return UpdatedChars(null, composedResult.toString())
                    }
                    'ᆢ' -> {
                        return UpdatedChars(null, null)
                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        finalChar = nullChar
                        composeResult()
                        val temp = composedResult.toString()
                        state = 2
                        firstChar = 'ㅊ'
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        currChar = composedResult
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    else -> {
                        val temp = composedResult.toString()
                        state = 1
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        currChar = firstChar
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㅋ' -> when (inputChar) {
                    'ᆞ' -> {
                        firstSubChar = nullChar
                        finalChar = 'ㄱ'
                        finalSubChar = nullChar
                        composeResult()
                        currChar = composedResult
                        return UpdatedChars(null, composedResult.toString())
                    }
                    'ᆢ' -> {
                        return UpdatedChars(null, null)
                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        finalChar = nullChar
                        composeResult()
                        val temp = composedResult.toString()
                        state = 2
                        firstChar = 'ㅋ'
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        currChar = composedResult
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    else -> {
                        val temp = composedResult.toString()
                        state = 1
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        currChar = firstChar
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㅌ' -> when (inputChar) {
                    'ᆞ' -> {
                        firstSubChar = nullChar
                        finalChar = 'ㄴ'
                        finalSubChar = nullChar
                        composeResult()
                        currChar = composedResult
                        return UpdatedChars(null, composedResult.toString())
                    }
                    'ᆢ' -> {
                        return UpdatedChars(null, null)
                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        finalChar = nullChar
                        composeResult()
                        val temp = composedResult.toString()
                        state = 2
                        firstChar = 'ㅌ'
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        currChar = composedResult
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    else -> {
                        val temp = composedResult.toString()
                        state = 1
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        currChar = firstChar
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㅍ' -> when (inputChar) {
                    'ᆞ' -> {
                        firstSubChar = nullChar
                        finalChar = 'ㅁ'
                        finalSubChar = nullChar
                        composeResult()
                        currChar = composedResult
                        return UpdatedChars(null, composedResult.toString())
                    }
                    'ᆢ' -> {
                        return UpdatedChars(null, null)
                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        finalChar = nullChar
                        composeResult()
                        val temp = composedResult.toString()
                        state = 2
                        firstChar = 'ㅍ'
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        currChar = composedResult
                        return UpdatedChars(temp, composedResult.toString())
                    }
                    else -> {
                        val temp = composedResult.toString()
                        state = 1
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        currChar = firstChar
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                'ㅎ' -> when (inputChar) {
                    'ᆞ' -> {
                        firstSubChar = nullChar
                        finalChar = 'ㅇ'
                        finalSubChar = nullChar
                        composeResult()
                        currChar = composedResult
                        return UpdatedChars(null, composedResult.toString())
                    }
                    'ᆢ' -> {
                        return UpdatedChars(null, null)
                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        finalChar = nullChar
                        composeResult()
                        val temp = composedResult.toString()
                        state = 2
                        firstChar = 'ㅎ'
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        currChar = composedResult

                        return UpdatedChars(temp, composedResult.toString())
                    }
                    else -> {
                        val temp = composedResult.toString()
                        state = 1
                        firstChar = inputChar
                        firstSubChar = nullChar
                        middleChar = nullChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        currChar = firstChar
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                nullChar -> when (inputChar) {
                    'ᆞ' -> {
                        return UpdatedChars(null, null)
                    }
                    'ᆢ' -> when (finalSubChar) {
                        'ㄸ' -> {
                            firstSubChar = nullChar
                            finalChar = 'ㄷ'
                            finalSubChar = nullChar
                            composeResult()
                            currChar = composedResult
                            return UpdatedChars(null, composedResult.toString())
                        }
                        'ㅃ' -> {
                            firstSubChar = nullChar
                            finalChar = 'ㅂ'
                            finalSubChar = nullChar
                            composeResult()
                            currChar = composedResult
                            return UpdatedChars(null, composedResult.toString())
                        }
                        'ㅉ' -> {
                            firstSubChar = nullChar
                            finalChar = 'ㅈ'
                            finalSubChar = nullChar
                            composeResult()
                            currChar = composedResult
                            return UpdatedChars(null, composedResult.toString())
                        }
                    }
                    'ㅏ', 'ㅗ', 'ㅡ', 'ㅣ' -> {
                        val temp = composedResult.toString()
                        state = 2
                        firstChar = finalSubChar
                        firstSubChar = nullChar
                        middleChar = inputChar
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        currChar = composedResult
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
                        composeResult()
                        composedResult = nullChar
                        currChar = firstChar
                        return UpdatedChars(temp, firstChar.toString())
                    }
                }
                else -> {
                    initChar()
                    state = 0
                    composedResult = nullChar
                    currChar = composedResult
                    return UpdatedChars(inputChar.toString(), null)
                }
            }
        }
        return UpdatedChars(null, null)
    } //한글 오토마타

    fun delete(): UpdatedChars {
        when (state) {
            0 -> {
                initState()
                initChar()
                initResult()
                currChar = nullChar
                return UpdatedChars(null, null)
            }
            -1 -> when (middleChar) {
                'ㅏ', 'ㅑ', 'ㅓ', 'ㅕ', 'ㅗ', 'ㅛ', 'ㅠ', 'ㅡ', 'ㅣ' -> {
                    state = 0
                    middleChar = nullChar
                    composeResult()
                    composedResult = nullChar
                    currChar = nullChar
                    return UpdatedChars(null, null)
                }
                'ㅐ' -> {
                    middleChar = 'ㅏ'
                    composeResult()
                    composedResult = nullChar
                    currChar = middleChar
                    return UpdatedChars(null, middleChar.toString())
                }
                'ㅒ' -> {
                    middleChar = 'ㅑ'
                    composeResult()
                    composedResult = nullChar
                    currChar = middleChar
                    return UpdatedChars(null, middleChar.toString())
                }
                'ㅔ' -> {
                    middleChar = 'ㅓ'
                    composeResult()
                    composedResult = nullChar
                    currChar = middleChar
                    return UpdatedChars(null, middleChar.toString())
                }
                'ㅖ' -> {
                    middleChar = 'ㅕ'
                    composeResult()
                    composedResult = nullChar
                    currChar = middleChar
                    return UpdatedChars(null, middleChar.toString())
                }
                'ㅘ', 'ㅚ' -> {
                    middleChar = 'ㅗ'
                    composeResult()
                    composedResult = nullChar
                    currChar = middleChar
                    return UpdatedChars(null, middleChar.toString())
                }
                'ㅙ' -> {
                    middleChar = 'ㅘ'
                    composeResult()
                    composedResult = nullChar
                    currChar = middleChar
                    return UpdatedChars(null, middleChar.toString())
                }
                'ㅜ' -> {
                    state = 0
                    middleChar = nullChar
                    composeResult()
                    composedResult = nullChar
                    currChar = nullChar
                    return UpdatedChars(null, null)
                }
                'ㅝ', 'ㅟ' -> {
                    middleChar = 'ㅜ'
                    composeResult()
                    composedResult = nullChar
                    currChar = middleChar
                    return UpdatedChars(null, middleChar.toString())
                }
                'ㅞ' -> {
                    middleChar = 'ㅝ'
                    composeResult()
                    composedResult = nullChar
                    currChar = middleChar
                    return UpdatedChars(null, middleChar.toString())
                }
                'ㅢ' -> {
                    middleChar = 'ㅡ'
                    composeResult()
                    composedResult = nullChar
                    currChar = middleChar
                    return UpdatedChars(null, middleChar.toString())
                }
            }
            1 -> when (firstChar) {
                'ㄳ' -> {
                    firstChar = 'ㄱ'
                    firstSubChar = nullChar
                    composeResult()
                    composedResult = nullChar
                    currChar = firstChar
                    return UpdatedChars(null, firstChar.toString())
                }
                'ㄵ', 'ㄶ' -> {
                    firstChar = 'ㄴ'
                    firstSubChar = nullChar
                    composeResult()
                    composedResult = nullChar
                    currChar = firstChar
                    return UpdatedChars(null, firstChar.toString())
                }
                'ㄺ', 'ㄻ', 'ㄼ', 'ㄽ', 'ㄿ', 'ㅀ' -> {
                    firstChar = 'ㄹ'
                    firstSubChar = nullChar
                    composeResult()
                    composedResult = nullChar
                    currChar = firstChar
                    return UpdatedChars(null, firstChar.toString())
                }
                'ㅄ' -> {
                    firstChar = 'ㅂ'
                    firstSubChar = nullChar
                    composeResult()
                    composedResult = nullChar
                    currChar = firstChar
                    return UpdatedChars(null, firstChar.toString())
                }
                else -> when (firstSubChar) {
                    nullChar -> {
                        state = 0
                        firstChar = nullChar
                        firstSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        currChar = nullChar
                        return UpdatedChars(null, null)
                    }
                    else -> {
                        firstSubChar = nullChar
                        composeResult()
                        composedResult = nullChar
                        currChar = firstChar
                        return UpdatedChars(null, firstChar.toString())
                    }
                }
            }
            2 -> when (middleChar) {
                'ㅏ', 'ㅑ', 'ㅓ', 'ㅕ', 'ㅗ', 'ㅛ', 'ㅠ', 'ㅡ', 'ㅣ' -> {
                    state = 1
                    firstSubChar = nullChar
                    middleChar = nullChar
                    finalChar = nullChar
                    finalSubChar = nullChar
                    composeResult()
                    composedResult = nullChar
                    currChar = firstChar
                    return UpdatedChars(null, firstChar.toString())
                }
                'ㅐ' -> {
                    middleChar = 'ㅏ'
                    finalChar = nullChar
                    finalSubChar = nullChar
                    composeResult()
                    currChar = composedResult
                    return UpdatedChars(null, composedResult.toString())
                }
                'ㅒ' -> {
                    middleChar = 'ㅑ'
                    finalChar = nullChar
                    finalSubChar = nullChar
                    composeResult()
                    currChar = composedResult
                    return UpdatedChars(null, composedResult.toString())
                }
                'ㅔ' -> {
                    middleChar = 'ㅓ'
                    finalChar = nullChar
                    finalSubChar = nullChar
                    composeResult()
                    currChar = composedResult
                    return UpdatedChars(null, composedResult.toString())
                }
                'ㅖ' -> {
                    middleChar = 'ㅕ'
                    finalChar = nullChar
                    finalSubChar = nullChar
                    composeResult()
                    currChar = composedResult
                    return UpdatedChars(null, composedResult.toString())
                }
                'ㅘ', 'ㅚ' -> {
                    middleChar = 'ㅗ'
                    finalChar = nullChar
                    finalSubChar = nullChar
                    composeResult()
                    currChar = composedResult
                    return UpdatedChars(null, composedResult.toString())
                }
                'ㅙ' -> {
                    middleChar = 'ㅘ'
                    finalChar = nullChar
                    finalSubChar = nullChar
                    composeResult()
                    currChar = composedResult
                    return UpdatedChars(null, composedResult.toString())
                }
                'ㅜ' -> {
                    state = 1
                    firstSubChar = nullChar
                    middleChar = nullChar
                    finalChar = nullChar
                    finalSubChar = nullChar
                    composeResult()
                    composedResult = nullChar
                    currChar = firstChar
                    return UpdatedChars(null, firstChar.toString())
                }
                'ㅝ', 'ㅟ' -> {
                    middleChar = 'ㅜ'
                    finalChar = nullChar
                    finalSubChar = nullChar
                    composeResult()
                    currChar = composedResult
                    return UpdatedChars(null, composedResult.toString())
                }
                'ㅞ' -> {
                    middleChar = 'ㅝ'
                    finalChar = nullChar
                    finalSubChar = nullChar
                    composeResult()
                    currChar = composedResult
                    return UpdatedChars(null, composedResult.toString())
                }
                'ㅢ' -> {
                    middleChar = 'ㅡ'
                    finalChar = nullChar
                    finalSubChar = nullChar
                    composeResult()
                    currChar = composedResult
                    return UpdatedChars(null, composedResult.toString())
                }
            }
            3 -> when (finalChar) {
                'ㄳ' -> {
                    finalChar = 'ㄱ'
                    finalSubChar = nullChar
                    composeResult()
                    currChar = composedResult
                    return UpdatedChars(null, composedResult.toString())
                }
                'ㄵ', 'ㄶ' -> {
                    finalChar = 'ㄴ'
                    finalSubChar = nullChar
                    composeResult()
                    currChar = composedResult
                    return UpdatedChars(null, composedResult.toString())
                }
                'ㄺ', 'ㄻ', 'ㄼ', 'ㄽ', 'ㅀ' -> {
                    finalChar = 'ㄹ'
                    finalSubChar = nullChar
                    composeResult()
                    currChar = composedResult
                    return UpdatedChars(null, composedResult.toString())
                }
                'ㅄ' -> {
                    finalChar = 'ㅂ'
                    finalSubChar = nullChar
                    composeResult()
                    currChar = composedResult
                    return UpdatedChars(null, composedResult.toString())
                }
                else -> when (finalSubChar) {
                    nullChar -> {
                        state = 2
                        finalChar = nullChar
                        finalSubChar = nullChar
                        composeResult()
                        currChar = composedResult
                        return UpdatedChars(null, composedResult.toString())
                    }
                    else -> {
                        finalSubChar = nullChar
                        composeResult()
                        currChar = composedResult
                        return UpdatedChars(null, composedResult.toString())
                    }
                }
            }
        }
        return UpdatedChars(null, null)
    } //delete 발생
}