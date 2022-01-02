package com.sb.fittingKeyboard.service.viewmodel

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.content.res.Configuration
import android.content.res.Resources
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.sb.fittingKeyboard.service.*
import com.sb.fittingKeyboard.service.emoji.EmojiRecyclerAdapter
import com.sb.fittingKeyboard.service.util.KeyboardUtil
import org.json.JSONArray

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
     * 10 : Emojis
     * **/
    private var _mode: MutableLiveData<Int> = MutableLiveData(1)
    val mode: LiveData<Int>
        get() = _mode

    val orientation: MutableLiveData<Orientation> = MutableLiveData(
        Orientation.VERTICAL
    )
    fun changeOrientation(config: Int) {
        orientation.value = if (config == Configuration.ORIENTATION_PORTRAIT) Orientation.VERTICAL else Orientation.HORIZONTAL
    }

    private var _isSelecting: MutableLiveData<Boolean> = MutableLiveData(false)
    val isSelecting: LiveData<Boolean>
        get() = _isSelecting

    fun switchSelectingMode(bool: Boolean) {
        _isSelecting.value = bool
    }

    /**
     * @savedLangMode: saves mode value before change. It could be used while mode value complicatedly varies.
     * e.g. English KB -> SpecialChar KB -> English KB
     */
    private var savedLangMode = 0
    fun changeMode(new: Int) {
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
                    5, 7, 8, 9, 10 -> {
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
                    2 -> {
                        _mode.value = new
                        savedLangMode = _mode.value!!
                    }
                    3 -> {
                        _mode.value = new
                        savedLangMode = new
                    }
                    5, 7, 8, 9, 10 -> {
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
                    5, 7, 8, 9, 10 -> {
                        savedLangMode = _mode.value!!
                        _mode.value = new
                    }
                    else -> return
                }
            }
            3 -> {
                when (new) {
                    1 -> {
                        if (observeKBAutoCapitalization.value == true) _mode.value = new
                        else _mode.value = 2
                        savedLangMode = new
                    }
                    3 -> {
                        _mode.value = 4
                    }
                    5, 7, 8, 9, 10 -> {
                        savedLangMode = _mode.value!!
                        _mode.value = new
                    }
                    else -> return
                }
            }
            4 -> {
                when (new) {
                    1 -> {
                        if (observeKBAutoCapitalization.value == true) _mode.value = new
                        else _mode.value = 2
                        savedLangMode = _mode.value!!
                    }
                    3 -> {
                        _mode.value = 3
                    }
                    5, 7, 8, 9, 10 -> {
                        _mode.value = new
                        savedLangMode = 3
                    }
                    else -> return
                }
            }
            5 -> {
                when (new) {
                    1, 3 -> {
                        if (savedLangMode == 1 || savedLangMode == 2) {
                            if (observeKBAutoCapitalization.value == true) _mode.value = 1
                            else _mode.value = 2
                        }
                        else _mode.value = savedLangMode
                        savedLangMode = _mode.value!!
                    }
                    6 -> {
                        _mode.value = new
                    }
                    7, 8, 9, 10 -> {
                        savedLangMode = _mode.value!!
                        _mode.value = new
                    }
                    else -> return
                }
            }
            6 -> {
                when (new) {
                    1, 3 -> {
                        if (savedLangMode == 1 || savedLangMode == 2) {
                            if (observeKBAutoCapitalization.value == true) _mode.value = 1
                            else _mode.value = 2
                        }
                        else _mode.value = new
                        savedLangMode = _mode.value!!
                    }
                    6 -> {
                        _mode.value = 5
                    }
                    7, 8, 9, 10 -> {
                        savedLangMode = _mode.value!!
                        _mode.value = new
                    }
                    else -> return
                }
            }
            7, 8, 9, 10 -> {
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
            else -> return
        }
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
    var observeKBVibrationUse = kbSettingSP.booleanLiveData(KeyboardUtil.KEYBOARD_VIBRATION_USE, true)
    var observeKBVibrationIntensity =
        kbSettingSP.intLiveData(KeyboardUtil.KEYBOARD_VIBRATION_INTENSITY, 0)
    var observeKBTheme = kbSettingSP.intLiveData(KeyboardUtil.KEYBOARD_THEME, 0)
    var observeKBFontColor =
        kbSettingSP.intLiveData(KeyboardUtil.KEYBOARD_FONT_COLOR, 0xFF000000.toInt())
    var observeKBFunctionFontColor =
        kbSettingSP.intLiveData(KeyboardUtil.KEYBOARD_FUNCTION_FONT_COLOR, 0xFF000000.toInt())
    var observeKBLeftSideMargin = kbSettingSP.intLiveData(KeyboardUtil.KEYBOARD_LEFTSIDE_MARGIN, 0)
    var observeKBRightSideMargin =
        kbSettingSP.intLiveData(KeyboardUtil.KEYBOARD_RIGHTSIDE_MARGIN, 0)
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
    var observeE0RecentlyUsedEmoticons = kbSettingSP.stringLiveData(KeyboardUtil.RECENTLY_USED_EMOTICONS, JSONArray().put("").toString())

    var observeHeight: MediatorLiveData<Float> = MediatorLiveData()
    var observeRightSize: MediatorLiveData<Float> = MediatorLiveData()
    var observeBottomMargin: MediatorLiveData<Int> = MediatorLiveData()
    var emojiColumnsCounts: MediatorLiveData<Int> = MediatorLiveData()

    init {
        observeBottomMargin.run {
            addSource(observeKBBottomMargin) {
                observeBottomMargin.value =
                    if (orientation.value == Orientation.HORIZONTAL) 0
                    else observeKBBottomMargin.value
            }
            addSource(orientation) {
                observeBottomMargin.value =
                    if (orientation.value == Orientation.HORIZONTAL) 0
                    else observeKBBottomMargin.value
            }
        }

        observeHeight.run {
            addSource(observeKBHeight) {
                observeHeight.value = setTotalHeight()
            }
            addSource(mode) {
                observeHeight.value = setTotalHeight()
            }
            addSource(observeKBBottomMargin) {
                observeHeight.value = setTotalHeight()
            }
            addSource(observeNumberVisibility) {
                observeHeight.value = setTotalHeight()
            }
            addSource(orientation) {
                observeHeight.value = setTotalHeight()
            }
        }

        observeRightSize.run {
            addSource(observeKBDivision) {
                observeRightSize.value = getRightSize()
            }
            addSource(observeKBMoSize) {
                observeRightSize.value = getRightSize()
            }
        }

        emojiColumnsCounts.addSource(orientation) {
            val emojiPixelSize: Float = (Resources.getSystem().displayMetrics.density * 50 + 0.5).toFloat()
            emojiColumnsCounts.value = (Resources.getSystem().displayMetrics.widthPixels.toFloat()/emojiPixelSize).toInt()
        }
    }

    fun getRightSize(): Float {
        return if (observeKBDivision.value!!) ((observeKBMoSize.value!! + 80) / 100.toFloat()) else 1.toFloat()
    }

    private fun setTotalHeight(): Float {
        val maxHeight = 600F
        val minHeight = 350F
        val currentHeight = Resources.getSystem().displayMetrics.heightPixels.toFloat()/2.5F
        return when {
            currentHeight >= maxHeight -> maxHeight * (75 + observeKBHeight.value!!) / 100
            currentHeight < maxHeight && currentHeight >= minHeight -> currentHeight * (75 + observeKBHeight.value!!) / 100
            else -> minHeight * (75 + observeKBHeight.value!!) / 100
        }
    }

    fun setRecentlyUsedEmoticon(emoji: String) {
        val jsonArray = JSONArray(kbSettingSP.stringLiveData(KeyboardUtil.RECENTLY_USED_EMOTICONS, JSONArray().put("").toString()).value!!)
        val newJsonArray = JSONArray()

        val arr = mutableListOf<String>()

        for (i in 0 until jsonArray.length()) {
            if (jsonArray.optString(i) != "") arr.add(jsonArray.optString(i))
        }

        if (emoji in arr) {
            arr.apply {
                remove(emoji)
                add(0, emoji)
            }
        } else {
            arr.add(0, emoji)
            if (arr.size > 30) {
                while (arr.size > 30) {
                    arr.remove(arr.last())
                }
            }
        }
        arr.forEach {
            newJsonArray.put(it)
        }
        kbSettingSP.edit().putString(KeyboardUtil.RECENTLY_USED_EMOTICONS, newJsonArray.toString()).apply()
    }

    enum class Orientation {
        HORIZONTAL,
        VERTICAL
    }
}