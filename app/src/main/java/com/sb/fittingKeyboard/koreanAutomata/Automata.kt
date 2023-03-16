package com.sb.fittingKeyboard.koreanAutomata

abstract class Automata {
    val BASE_INT: Int = 0xAC00
    val nullChar: Char = '\u0000'
    var currChar = nullChar
    val firstCharArray: Array<Int> = arrayOf(
        0x3131,
        0x3132,
        0x3134,
        0x3137,
        0x3138,
        0x3139,
        0x3141,
        0x3142,
        0x3143,
        0x3145,
        0x3146,
        0x3147,
        0x3148,
        0x3149,
        0x314a,
        0x314b,
        0x314c,
        0x314d,
        0x314e
    )
    val middleCharArray: Array<Int> = arrayOf(
        0x314f,
        0x3150,
        0x3151,
        0x3152,
        0x3153,
        0x3154,
        0x3155,
        0x3156,
        0x3157,
        0x3158,
        0x3159,
        0x315a,
        0x315b,
        0x315c,
        0x315d,
        0x315e,
        0x315f,
        0x3160,
        0x3161,
        0x3162,
        0x3163
    )
    private val finalCharArray: Array<Int> = arrayOf(
        0x0000,
        0x3131,
        0x3132,
        0x3133,
        0x3134,
        0x3135,
        0x3136,
        0x3137,
        0x3139,
        0x313a,
        0x313b,
        0x313c,
        0x313d,
        0x313e,
        0x313f,
        0x3140,
        0x3141,
        0x3142,
        0x3144,
        0x3145,
        0x3146,
        0x3147,
        0x3148,
        0x314a,
        0x314b,
        0x314c,
        0x314d,
        0x314e
    )

    var state = 0
    var firstChar: Char = nullChar
    var firstSubChar: Char = nullChar
    var middleChar: Char = nullChar
    var middleSubChar: Char = nullChar
    var finalChar: Char = nullChar
    var finalSubChar: Char = nullChar
    var composedResult = nullChar

    private var firstCharIndex = firstCharArray.indexOf(firstChar.toInt())
    private var middleCharIndex = middleCharArray.indexOf(middleChar.toInt())
    private var finalCharIndex = finalCharArray.indexOf(finalChar.toInt())

    fun composeResult() {
        firstCharIndex = firstCharArray.indexOf(firstChar.toInt())
        middleCharIndex = middleCharArray.indexOf(middleChar.toInt())
        finalCharIndex = finalCharArray.indexOf(finalChar.toInt())
        composedResult =
            (BASE_INT + firstCharIndex * 21 * 28 + middleCharIndex * 28 + finalCharIndex).toChar()
    }

    fun initState() {
        state = 0
    }

    fun initChar() {
        firstChar = nullChar
        firstSubChar = nullChar
        middleChar = nullChar
        middleSubChar = nullChar
        finalChar = nullChar
        finalSubChar = nullChar
    }

    fun initResult() {
        composedResult = nullChar
    }
}