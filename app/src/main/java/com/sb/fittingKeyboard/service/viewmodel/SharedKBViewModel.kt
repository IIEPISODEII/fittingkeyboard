package com.sb.fittingKeyboard.service.viewmodel

import android.app.Application
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.util.Log
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.sb.fittingKeyboard.service.*
import com.sb.fittingKeyboard.service.util.KeyboardUtil
import kotlin.coroutines.coroutineContext

class SharedKBViewModel(application: Application) : AndroidViewModel(application) {
    /** mode
     * 0: English, Always UpperCase
     * 1: English, UpperCase
     * 2: English, LowerCase
     * 3: KR
     * 4: KR with Shift
     * 5: Special 1
     * 6: Special 2
     * 7: BoilerPlate Text
     * 8: Cursor Pad
     * 9: Number Pad
     * **/
    private var _mode: MutableLiveData<Int> = MutableLiveData(0)
    val mode: LiveData<Int>
        get() = _mode

    var savedLangMode = 0
    fun changeMode(new: Int) {
        println("before: ${mode.value}")
        println("before saved: $savedLangMode")
        when (_mode.value) {
            0 -> {
                when (new) {
                    1 -> {
                        _mode.value = 2
                        savedLangMode = 2
                    }
                    3 -> {
                        _mode.value = new
                        savedLangMode = new
                    }
                    5, 7, 8, 9 -> {
                        savedLangMode = _mode.value!!
                        _mode.value = new
                    }
                    else -> return
                }
            }
            1 -> {
                when (new) {
                    1 -> {
                        _mode.value = 0
                        savedLangMode = 0
                    }
                    3 -> {
                        _mode.value = new
                        savedLangMode = new
                    }
                    5, 7, 8, 9 -> {
                        savedLangMode = _mode.value!!
                        _mode.value = new
                    }
                    else -> return
                }
            }
            2 -> {
                when (new) {
                    1 -> {
                        _mode.value = 1
                        savedLangMode = 1
                    }
                    3 -> {
                        _mode.value = new
                        savedLangMode = new
                    }
                    5, 7, 8, 9 -> {
                        savedLangMode = _mode.value!!
                        _mode.value = new
                    }
                    else -> return
                }
            }
            3 -> {
                when (new) {
                    1 -> {
                        _mode.value = new
                        savedLangMode = new
                    }
                    3 -> {
                        _mode.value = 4
                    }
                    5, 7, 8, 9 -> {
                        savedLangMode = _mode.value!!
                        _mode.value = new
                    }
                    else -> return
                }
            }
            4 -> {
                when (new) {
                    1 -> {
                        _mode.value = new
                        savedLangMode = _mode.value!!
                    }
                    3 -> {
                        _mode.value = 3
                    }
                    5, 7, 8, 9 -> {
                        _mode.value = new
                        savedLangMode = 3
                    }
                    else -> return
                }
            }
            5 -> {
                when (new) {
                    1, 3 -> {
                        _mode.value = savedLangMode
                        savedLangMode = _mode.value!!
                    }
                    6 -> {
                        _mode.value = new
                    }
                    7, 8, 9 -> {
                        savedLangMode = _mode.value!!
                        _mode.value = new
                    }
                    else -> return
                }
            }
            6 -> {
                when (new) {
                    1, 3 -> {
                        _mode.value = savedLangMode
                        savedLangMode = _mode.value!!
                    }
                    6 -> {
                        _mode.value = 5
                    }
                    7, 8, 9 -> {
                        savedLangMode = _mode.value!!
                        _mode.value = new
                    }
                    else -> return
                }
            }
            7, 8, 9 -> {
                when (new) {
                    _mode.value -> {
                        _mode.value = savedLangMode
                        savedLangMode = 3
                    }
                    0, 1, 2, 3, 4 -> {
                        _mode.value = new
                        savedLangMode = _mode.value!!
                    }
                    else -> {
                        _mode.value = new
                    }
                }
            }
        }
        println("after: ${mode.value}")
        println("after saved: $savedLangMode \n")
    }

    val kbSettingSP: SharedPreferences = application.applicationContext.getSharedPreferences(
        KeyboardUtil.KEYBOARD_SETTING,
        MODE_PRIVATE
    )

    var observeKBHeight = kbSettingSP.intLiveData(KeyboardUtil.KEYBOARD_HEIGHT, 25)
    var observeKBBottomMargin = kbSettingSP.intLiveData(KeyboardUtil.KEYBOARD_BOTTOM_MARGIN, 0)
    var observeKBFontSize = kbSettingSP.intLiveData(KeyboardUtil.KEYBOARD_FONT_SIZE, 16)
    var observeKBFontType = kbSettingSP.intLiveData(KeyboardUtil.KEYBOARD_FONT_TYPE, 0)
    var observeNumberVisibility =
        kbSettingSP.intLiveData(KeyboardUtil.KEYBOARD_TOGGLE_NUMBER, View.VISIBLE)
    var observeKBMoSize = kbSettingSP.intLiveData(KeyboardUtil.KEYBOARD_MO_SIZE, 20)
    var observeKBDivision = kbSettingSP.booleanLiveData(KeyboardUtil.KEYBOARD_DIVISION, true)
    var observeKBHoldingTime = kbSettingSP.intLiveData(KeyboardUtil.KEYBOARD_HOLDING_TIME, 200)
    var observeKBVibrationType = kbSettingSP.intLiveData(KeyboardUtil.KEYBOARD_VIBRATION_TYPE, 2)
    var observeKBVibrationIntensity =
        kbSettingSP.intLiveData(KeyboardUtil.KEYBOARD_VIBRATION_INTENSITY, 0)
    var observeKBTheme = kbSettingSP.intLiveData(KeyboardUtil.KEYBOARD_THEME, 0)
    var observeKBFontColor =
        kbSettingSP.intLiveData(KeyboardUtil.KEYBOARD_FONT_COLOR, 0xFF000000.toInt())
    var observeKBFunctionFontColor =
        kbSettingSP.intLiveData(KeyboardUtil.KEYBOARD_FUNCTION_FONT_COLOR, 0xFF000000.toInt())
    var observeKBLeftSideMargin = kbSettingSP.intLiveData(KeyboardUtil.KEYBOARD_LEFTSIDE_MARGIN, 1)
    var observeKBRightSideMargin =
        kbSettingSP.intLiveData(KeyboardUtil.KEYBOARD_RIGHTSIDE_MARGIN, 1)
    var observeKBToolBarVisibility =
        kbSettingSP.intLiveData(KeyboardUtil.KEYBOARD_TOGGLE_TOOLBAR, View.VISIBLE)
    var observeKBAutoCapitalization =
        kbSettingSP.booleanLiveData(KeyboardUtil.KEYBOARD_AUTO_CAPITALIZATION, true)
    var observeKBAutoModeChange =
        kbSettingSP.booleanLiveData(KeyboardUtil.KEYBOARD_AUTO_MODE_CHANGE, true)
    var observeKBSpecialKeyHolding =
        kbSettingSP.intLiveData(KeyboardUtil.KEYBOARD_SPECIALKEY_LONGCLICK, 0)
    var observeKBEnterKeyHolding =
        kbSettingSP.intLiveData(KeyboardUtil.KEYBOARD_ENTERKEY_LONGCLICK, 0)
    var observeKBIME = kbSettingSP.intLiveData(KeyboardUtil.KEYBOARD_IME_KR, 0)
    var observeBP0 = kbSettingSP.stringLiveData(KeyboardUtil.KEYBOARD_BP_0, "")
    var observeBP1 = kbSettingSP.stringLiveData(KeyboardUtil.KEYBOARD_BP_1, "")
    var observeBP2 = kbSettingSP.stringLiveData(KeyboardUtil.KEYBOARD_BP_2, "")
    var observeBP3 = kbSettingSP.stringLiveData(KeyboardUtil.KEYBOARD_BP_3, "")
    var observeBP4 = kbSettingSP.stringLiveData(KeyboardUtil.KEYBOARD_BP_4, "")
    var observeBP5 = kbSettingSP.stringLiveData(KeyboardUtil.KEYBOARD_BP_5, "")
    var observeBP6 = kbSettingSP.stringLiveData(KeyboardUtil.KEYBOARD_BP_6, "")
    var observeBP7 = kbSettingSP.stringLiveData(KeyboardUtil.KEYBOARD_BP_7, "")
    var observeBP8 = kbSettingSP.stringLiveData(KeyboardUtil.KEYBOARD_BP_8, "")
    var observeBP9 = kbSettingSP.stringLiveData(KeyboardUtil.KEYBOARD_BP_9, "")
    var observeBP10 = kbSettingSP.stringLiveData(KeyboardUtil.KEYBOARD_BP_10, "")
    var observeBP11 = kbSettingSP.stringLiveData(KeyboardUtil.KEYBOARD_BP_11, "")
    var observeBP12 = kbSettingSP.stringLiveData(KeyboardUtil.KEYBOARD_BP_12, "")
    var observeBP13 = kbSettingSP.stringLiveData(KeyboardUtil.KEYBOARD_BP_13, "")
    var observeBP14 = kbSettingSP.stringLiveData(KeyboardUtil.KEYBOARD_BP_14, "")
    var observeBP15 = kbSettingSP.stringLiveData(KeyboardUtil.KEYBOARD_BP_15, "")

    var observeHeight: MediatorLiveData<Float> = MediatorLiveData()
    var observeRightSize: MediatorLiveData<Float> = MediatorLiveData()

    init {
        observeHeight.addSource(observeKBHeight) {
            observeHeight.value = getKBHeight() + getNumberHeight() + observeKBBottomMargin.value!!
        }
        observeHeight.addSource(mode) {
            observeHeight.value = getKBHeight() + getNumberHeight() + observeKBBottomMargin.value!!
        }
        observeHeight.addSource(observeKBBottomMargin) {
            observeHeight.value = getKBHeight() + getNumberHeight() + observeKBBottomMargin.value!!
        }
        observeHeight.addSource(observeNumberVisibility) {
            observeHeight.value = getKBHeight() + getNumberHeight() + observeKBBottomMargin.value!!
        }

        observeRightSize.addSource(observeKBDivision) {
            observeRightSize.value = getRightSize()
        }
        observeRightSize.addSource(observeKBMoSize) {
            observeRightSize.value = getRightSize()
        }
    }

    fun getKBHeight(): Float {
        return ((180 * (75 + observeKBHeight.value!!) / 100)).toFloat()
    }

    fun getNumberHeight(): Float {
        return if (mode.value in arrayOf(5, 6, 9) || observeNumberVisibility.value == View.VISIBLE)
            ((40 * (75 + observeKBHeight.value!!) / 100)).toFloat()
        else 0F
    }

    fun getRightSize(): Float {
        return if (observeKBDivision.value!!) ((observeKBMoSize.value!!.plus(80)) / 100).toFloat() else 1.toFloat()
    }
}

