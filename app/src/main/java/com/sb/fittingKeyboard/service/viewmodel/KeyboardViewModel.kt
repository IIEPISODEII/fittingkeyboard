package com.sb.fittingKeyboard.service.viewmodel

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.content.SharedPreferences.OnSharedPreferenceChangeListener
import android.content.res.Configuration
import android.content.res.Resources
import android.util.TypedValue
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.sb.fittingKeyboard.service.util.KeyboardUtil
import org.json.JSONArray

class KeyboardViewModel(application: Application) : AndroidViewModel(application) {
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

    private val _orientation = MutableLiveData(Orientation.VERTICAL)
    val orientation: LiveData<Orientation>
        get() = _orientation
    fun changeOrientation(config: Int) {
        _orientation.postValue(if (config == Configuration.ORIENTATION_PORTRAIT) Orientation.VERTICAL else Orientation.HORIZONTAL)
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
    fun changeMode(new: Int, restart: Boolean = false) {
        if (restart) return
        when (_mode.value) {
            0 -> {
                when (new) {
                    1 -> {
                        _mode.postValue(2)
                        savedLangMode = 2
                    }
                    3 -> {
                        _mode.postValue(new)
                        savedLangMode = new
                    }
                    5, 7, 8, 9, 10 -> {
                        savedLangMode = _mode.value!!
                        _mode.postValue(new)
                    }
                    else -> return
                }
            }
            1 -> {
                when (new) {
                    1 -> {
                        _mode.postValue(0)
                        savedLangMode = 0
                    }
                    2 -> {
                        _mode.postValue(new)
                        savedLangMode = _mode.value!!
                    }
                    3 -> {
                        _mode.postValue(new)
                        savedLangMode = new
                    }
                    5, 7, 8, 9, 10 -> {
                        savedLangMode = _mode.value!!
                        _mode.postValue(new)
                    }
                    else -> return
                }
            }
            2 -> {
                when (new) {
                    1 -> {
                        _mode.postValue(1)
                        savedLangMode = 1
                    }
                    3 -> {
                        _mode.postValue(new)
                        savedLangMode = new
                    }
                    5, 7, 8, 9, 10 -> {
                        savedLangMode = _mode.value!!
                        _mode.postValue(new)
                    }
                    else -> return
                }
            }
            3 -> {
                when (new) {
                    1 -> {
                        if (kbHasAutoCapitalization.value == true) _mode.postValue(new)
                        else _mode.postValue(2)
                        savedLangMode = new
                    }
                    3 -> {
                        _mode.postValue(4)
                    }
                    5, 7, 8, 9, 10 -> {
                        savedLangMode = _mode.value!!
                        _mode.postValue(new)
                    }
                    else -> return
                }
            }
            4 -> {
                when (new) {
                    1 -> {
                        if (kbHasAutoCapitalization.value == true) _mode.postValue(new)
                        else _mode.postValue(2)
                        savedLangMode = _mode.value!!
                    }
                    3 -> {
                        _mode.postValue(3)
                    }
                    5, 7, 8, 9, 10 -> {
                        _mode.postValue(new)
                        savedLangMode = 3
                    }
                    else -> return
                }
            }
            5 -> {
                when (new) {
                    1, 3 -> {
                        if (savedLangMode == 1 || savedLangMode == 2) {
                            if (kbHasAutoCapitalization.value == true) _mode.postValue(1)
                            else _mode.postValue(2)
                        }
                        else _mode.postValue(savedLangMode)
                        savedLangMode = _mode.value!!
                    }
                    6 -> {
                        _mode.postValue(new)
                    }
                    7, 8, 9, 10 -> {
                        savedLangMode = _mode.value!!
                        _mode.postValue(new)
                    }
                    else -> return
                }
            }
            6 -> {
                when (new) {
                    1, 3 -> {
                        if (savedLangMode == 1 || savedLangMode == 2) {
                            if (kbHasAutoCapitalization.value == true) _mode.postValue(1)
                            else _mode.postValue(2)
                        }
                        else _mode.postValue(new)
                        savedLangMode = _mode.value!!
                    }
                    6 -> {
                        _mode.postValue(5)
                    }
                    7, 8, 9, 10 -> {
                        savedLangMode = _mode.value!!
                        _mode.postValue(new)
                    }
                    else -> return
                }
            }
            7, 8, 9, 10 -> {
                when(new) {
                    _mode.value -> {
                        _mode.postValue(savedLangMode)
                        savedLangMode = 3
                    }
                    0, 1, 2, 3, 4 -> {
                        _mode.postValue(new)
                        savedLangMode = _mode.value!!
                    }
                    else -> {
                        _mode.postValue(new)
                    }
                }
            }
            else -> return
        }
    }

    private var kbSettingSP: SharedPreferences? = application.applicationContext.getSharedPreferences(
        KeyboardUtil.KEYBOARD_SETTING,
        MODE_PRIVATE
    )
    private val _kbHeight = MutableLiveData(kbSettingSP?.getInt(KeyboardUtil.KEYBOARD_HEIGHT, 25) ?: 25)
    val kbHeight: LiveData<Int>
        get() = _kbHeight
    private val _kbBottomMargin = MutableLiveData(kbSettingSP?.getInt(KeyboardUtil.KEYBOARD_BOTTOM_MARGIN, 0) ?: 0)
    val kbBottomMargin: LiveData<Int>
        get() = _kbBottomMargin
    private val _kbFontSize = MutableLiveData(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, (kbSettingSP?.getInt(KeyboardUtil.KEYBOARD_FONT_SIZE, 16) ?: 16).toFloat(), application.resources.displayMetrics).toInt())
    val kbFontSize: LiveData<Int>
        get() = _kbFontSize
    private val _kbFontType = MutableLiveData(kbSettingSP?.getInt(KeyboardUtil.KEYBOARD_FONT_TYPE, 0) ?: 0)
    val kbFontType: LiveData<Int>
        get() = _kbFontType
    private val _kbNumberRowVisibility = MutableLiveData(kbSettingSP?.getInt(KeyboardUtil.KEYBOARD_TOGGLE_NUMBER, View.VISIBLE) ?: View.VISIBLE)
    val kbNumberRowVisibility: LiveData<Int>
        get() = _kbNumberRowVisibility
    private val _kbMoeumSize = MutableLiveData(kbSettingSP?.getInt(KeyboardUtil.KEYBOARD_MO_SIZE, 20) ?: 20)
    val kbMoeumSize: LiveData<Int>
        get() = _kbMoeumSize
    private val _kbHasDivision = MutableLiveData(kbSettingSP?.getBoolean(KeyboardUtil.KEYBOARD_DIVISION, true) ?: true)
    val kbHasDivision: LiveData<Boolean>
        get() = _kbHasDivision
    private val _kbLongClickInterval = MutableLiveData(kbSettingSP?.getInt(KeyboardUtil.KEYBOARD_HOLDING_TIME, 200) ?: 200)
    val kbLongClickInterval: LiveData<Int>
        get() = _kbLongClickInterval
    private val _kbHasVibration = MutableLiveData(kbSettingSP?.getBoolean(KeyboardUtil.KEYBOARD_VIBRATION_USE, true) ?: true)
    val kbHasVibration: LiveData<Boolean>
        get() = _kbHasVibration
    private val _kbVibrationIntensity = MutableLiveData(kbSettingSP?.getInt(KeyboardUtil.KEYBOARD_VIBRATION_INTENSITY, 0) ?: 0)
    val kbVibrationIntensity: LiveData<Int>
        get() = _kbVibrationIntensity
    private val _kbTheme = MutableLiveData(kbSettingSP?.getInt(KeyboardUtil.KEYBOARD_THEME, 0) ?: 0)
    val kbTheme: LiveData<Int>
        get() = _kbTheme
    private val _kbNormalKeysFontColor = MutableLiveData(kbSettingSP?.getInt(KeyboardUtil.KEYBOARD_FONT_COLOR, 0xFF000000.toInt()) ?: 0xFF000000.toInt())
    val kbNormalKeysFontColor: LiveData<Int>
        get() = _kbNormalKeysFontColor
    private val _kbFunctionKeysFontColor = MutableLiveData(kbSettingSP?.getInt(KeyboardUtil.KEYBOARD_FUNCTION_FONT_COLOR, 0xFF000000.toInt()) ?: 0xFF000000.toInt())
    val kbFunctionKeysFontColor: LiveData<Int>
        get() = _kbFunctionKeysFontColor
    private val _kbLeftSideMargin = MutableLiveData(kbSettingSP?.getInt(KeyboardUtil.KEYBOARD_LEFTSIDE_MARGIN, 0) ?: 0)
    val kbLeftSideMargin: LiveData<Int>
        get() = _kbLeftSideMargin
    private val _kbRightSideMargin = MutableLiveData(kbSettingSP?.getInt(KeyboardUtil.KEYBOARD_RIGHTSIDE_MARGIN, 0) ?: 0)
    val kbRightSideMargin: LiveData<Int>
        get() = _kbRightSideMargin
    private val _kbToolbarVisibility = MutableLiveData(kbSettingSP?.getInt(KeyboardUtil.KEYBOARD_TOGGLE_TOOLBAR, View.VISIBLE) ?: View.VISIBLE)
    val kbToolbarVisibility: LiveData<Int>
        get() = _kbToolbarVisibility
    private val _kbHasAutoCapitalization = MutableLiveData(kbSettingSP?.getBoolean(KeyboardUtil.KEYBOARD_AUTO_CAPITALIZATION, true) ?: true)
    val kbHasAutoCapitalization: LiveData<Boolean>
        get() = _kbHasAutoCapitalization
    private val _kbHasAutoModeChange  = MutableLiveData(kbSettingSP?.getBoolean(KeyboardUtil.KEYBOARD_AUTO_MODE_CHANGE, true) ?: true)
    val kbHasAutoModeChange: LiveData<Boolean>
        get() = _kbHasAutoModeChange
    private val _kbSpecialKeyOnLongClickFunction = MutableLiveData(kbSettingSP?.getInt(KeyboardUtil.KEYBOARD_SPECIALKEY_LONGCLICK, 0) ?: 0)
    val kbSpecialKeyOnLongClickFunction: LiveData<Int>
        get() = _kbSpecialKeyOnLongClickFunction
    private val _kbEnterKeyOnLongClickFunction = MutableLiveData(kbSettingSP?.getInt(KeyboardUtil.KEYBOARD_ENTERKEY_LONGCLICK, 0) ?: 0)
    val kbEnterKeyOnLongClickFunction: LiveData<Int>
        get() = _kbEnterKeyOnLongClickFunction
    private val _kbKrImeMode = MutableLiveData(kbSettingSP?.getInt(KeyboardUtil.KEYBOARD_IME_KR, 0) ?: 0)
    val kbKrImeMode: LiveData<Int>
        get() = _kbKrImeMode
    private val _kbRecentlyUsedEmoticons = MutableLiveData(kbSettingSP?.getString(KeyboardUtil.RECENTLY_USED_EMOTICONS, JSONArray().put("").toString()) ?: JSONArray().put("").toString())
    val kbRecentlyUsedEmoticons: LiveData<String>
        get() = _kbRecentlyUsedEmoticons

    private val boilerplateTextMap = KeyboardUtil.KEYBOARD_BOILERPLATE_TEXTS_LIST.groupBy { it }.mapValues { it.value.map { key -> kbSettingSP?.getString(key, "") ?: "" }.single() }.toMutableMap()
    private val _boilerplateTextList = MutableLiveData(boilerplateTextMap)
    val boilerplateTexts: LiveData<MutableMap<String, String>>
        get() = _boilerplateTextList

    private val toolbarSettingMap = hashMapOf(
        KeyboardUtil.KEYBOARD_TOOLBAR_ACTIVE_GO_SETTING to (kbSettingSP?.getInt(KeyboardUtil.KEYBOARD_TOOLBAR_ACTIVE_GO_SETTING, 1) ?: 1),
        KeyboardUtil.KEYBOARD_TOOLBAR_ACTIVE_SHOW_BOILERPLATE to (kbSettingSP?.getInt(KeyboardUtil.KEYBOARD_TOOLBAR_ACTIVE_SHOW_BOILERPLATE, 2) ?: 2),
        KeyboardUtil.KEYBOARD_TOOLBAR_ACTIVE_SELECT_ALL to (kbSettingSP?.getInt(KeyboardUtil.KEYBOARD_TOOLBAR_ACTIVE_SELECT_ALL, 3) ?: 3),
        KeyboardUtil.KEYBOARD_TOOLBAR_ACTIVE_COPY to (kbSettingSP?.getInt(KeyboardUtil.KEYBOARD_TOOLBAR_ACTIVE_COPY, 4) ?: 4),
        KeyboardUtil.KEYBOARD_TOOLBAR_ACTIVE_CUT to (kbSettingSP?.getInt(KeyboardUtil.KEYBOARD_TOOLBAR_ACTIVE_CUT, 5) ?: 5),
        KeyboardUtil.KEYBOARD_TOOLBAR_ACTIVE_PASTE to (kbSettingSP?.getInt(KeyboardUtil.KEYBOARD_TOOLBAR_ACTIVE_PASTE, 6) ?: 6),
        KeyboardUtil.KEYBOARD_TOOLBAR_ACTIVE_SHOW_CURSOR to (kbSettingSP?.getInt(KeyboardUtil.KEYBOARD_TOOLBAR_ACTIVE_SHOW_CURSOR, 7) ?: 7),
        KeyboardUtil.KEYBOARD_TOOLBAR_ACTIVE_SHOW_NUMBER to (kbSettingSP?.getInt(KeyboardUtil.KEYBOARD_TOOLBAR_ACTIVE_SHOW_NUMBER, 8) ?: 8),
        KeyboardUtil.KEYBOARD_TOOLBAR_ACTIVE_SHOW_EMOJI to (kbSettingSP?.getInt(KeyboardUtil.KEYBOARD_TOOLBAR_ACTIVE_SHOW_EMOJI, 9) ?: 9)
    )
    private val _prefSettingToolbarSetting = MutableLiveData(toolbarSettingMap)
    val prefSettingToolbarSetting: LiveData<HashMap<String, Int>>
        get() = _prefSettingToolbarSetting

    private val onPrefSettingChangeListener = OnSharedPreferenceChangeListener { pref, key ->
        when (key) {
            KeyboardUtil.KEYBOARD_HEIGHT -> _kbHeight.postValue(pref.getInt(key, 1))
            KeyboardUtil.KEYBOARD_BOTTOM_MARGIN -> _kbBottomMargin.postValue(pref.getInt(key, 0))
            KeyboardUtil.KEYBOARD_FONT_SIZE -> _kbFontSize.postValue(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, pref.getInt(key, 16).toFloat(), application.resources.displayMetrics).toInt())
            KeyboardUtil.KEYBOARD_FONT_TYPE -> _kbFontType.postValue(pref.getInt(key, 0))
            KeyboardUtil.KEYBOARD_TOGGLE_NUMBER -> _kbNumberRowVisibility.postValue(pref.getInt(key, View.VISIBLE))
            KeyboardUtil.KEYBOARD_MO_SIZE -> _kbMoeumSize.postValue(pref.getInt(key, 20))
            KeyboardUtil.KEYBOARD_DIVISION -> _kbHasDivision.postValue(pref.getBoolean(key, true))
            KeyboardUtil.KEYBOARD_HOLDING_TIME -> _kbLongClickInterval.postValue(pref.getInt(key, 200))
            KeyboardUtil.KEYBOARD_VIBRATION_USE -> _kbHasVibration.postValue(pref.getBoolean(key, true))
            KeyboardUtil.KEYBOARD_VIBRATION_INTENSITY -> _kbVibrationIntensity.postValue(pref.getInt(key, 0))
            KeyboardUtil.KEYBOARD_THEME -> _kbTheme.postValue(pref.getInt(key, 0))
            KeyboardUtil.KEYBOARD_FONT_COLOR -> _kbNormalKeysFontColor.postValue(pref.getInt(key, 0xFF000000.toInt()))
            KeyboardUtil.KEYBOARD_FUNCTION_FONT_COLOR -> _kbFunctionKeysFontColor.postValue(pref.getInt(key, 0xFF000000.toInt()))
            KeyboardUtil.KEYBOARD_LEFTSIDE_MARGIN -> _kbLeftSideMargin.postValue(pref.getInt(key, 0))
            KeyboardUtil.KEYBOARD_RIGHTSIDE_MARGIN -> _kbRightSideMargin.postValue(pref.getInt(key, 0))
            KeyboardUtil.KEYBOARD_TOGGLE_TOOLBAR -> _kbToolbarVisibility.postValue(pref.getInt(key, View.VISIBLE))
            KeyboardUtil.KEYBOARD_AUTO_CAPITALIZATION -> _kbHasAutoCapitalization.postValue(pref.getBoolean(key, true))
            KeyboardUtil.KEYBOARD_AUTO_MODE_CHANGE -> _kbHasAutoModeChange.postValue(pref.getBoolean(key, true))
            KeyboardUtil.KEYBOARD_SPECIALKEY_LONGCLICK -> _kbSpecialKeyOnLongClickFunction.postValue(pref.getInt(key, 0))
            KeyboardUtil.KEYBOARD_ENTERKEY_LONGCLICK -> _kbEnterKeyOnLongClickFunction.postValue(pref.getInt(key, 0))
            KeyboardUtil.KEYBOARD_IME_KR -> _kbKrImeMode.postValue(pref.getInt(key, 0))
            in KeyboardUtil.KEYBOARD_BOILERPLATE_TEXTS_LIST -> boilerplateTextMap[key] = pref.getString(key, "") ?: ""
            KeyboardUtil.KEYBOARD_TOOLBAR_ACTIVE_GO_SETTING -> toolbarSettingMap[key] = pref.getInt(key, 1)
            KeyboardUtil.KEYBOARD_TOOLBAR_ACTIVE_SHOW_BOILERPLATE -> toolbarSettingMap[key] = pref.getInt(key, 2)
            KeyboardUtil.KEYBOARD_TOOLBAR_ACTIVE_SELECT_ALL -> toolbarSettingMap[key] = pref.getInt(key, 3)
            KeyboardUtil.KEYBOARD_TOOLBAR_ACTIVE_COPY -> toolbarSettingMap[key] = pref.getInt(key, 4)
            KeyboardUtil.KEYBOARD_TOOLBAR_ACTIVE_CUT -> toolbarSettingMap[key] = pref.getInt(key, 5)
            KeyboardUtil.KEYBOARD_TOOLBAR_ACTIVE_PASTE -> toolbarSettingMap[key] = pref.getInt(key, 6)
            KeyboardUtil.KEYBOARD_TOOLBAR_ACTIVE_SHOW_CURSOR -> toolbarSettingMap[key] = pref.getInt(key, 7)
            KeyboardUtil.KEYBOARD_TOOLBAR_ACTIVE_SHOW_NUMBER -> toolbarSettingMap[key] = pref.getInt(key, 8)
            KeyboardUtil.KEYBOARD_TOOLBAR_ACTIVE_SHOW_EMOJI -> toolbarSettingMap[key] = pref.getInt(key, 9)
        }
        _boilerplateTextList.postValue(boilerplateTextMap)
        _prefSettingToolbarSetting.postValue(toolbarSettingMap)
    }

    private val _kbResultedHeight: MediatorLiveData<Float> = MediatorLiveData()
    val kbResultedHeight: LiveData<Float>
        get() = _kbResultedHeight
    private val _kbRightSize: MediatorLiveData<Float> = MediatorLiveData()
    val kbRightSize: LiveData<Float>
        get() = _kbRightSize
    private val _kbResultBottomMargin: MediatorLiveData<Int> = MediatorLiveData()
    val kbResultBottomMargin: LiveData<Int>
        get() = _kbResultBottomMargin
    private val _kbEmojiColumns: MediatorLiveData<Int> = MediatorLiveData()
    val kbEmojiColumns: LiveData<Int>
        get() = _kbEmojiColumns

    init {
        _kbResultBottomMargin.apply {
            addSource(kbBottomMargin) {
                this.postValue(
                    if (orientation.value == Orientation.HORIZONTAL) 0
                    else kbBottomMargin.value
                )
            }
            addSource(orientation) {
                this.postValue(
                    if (orientation.value == Orientation.HORIZONTAL) 0
                    else kbBottomMargin.value
                )
            }
        }

        _kbResultedHeight.apply {
            addSource(kbHeight) {
                this.postValue(setKbdHeightsInternally())
            }
            addSource(mode) {
                this.postValue(setKbdHeightsInternally())
            }
            addSource(kbBottomMargin) {
                this.postValue(setKbdHeightsInternally())
            }
            addSource(kbNumberRowVisibility) {
                this.postValue(setKbdHeightsInternally())
            }
            addSource(orientation) {
                this.postValue(setKbdHeightsInternally())
            }
        }

        _kbRightSize.apply {
            addSource(kbHasDivision) {
                this.postValue(getRightSize())
            }
            addSource(kbMoeumSize) {
                this.postValue(getRightSize())
            }
        }

        _kbEmojiColumns.addSource(orientation) {
            val emojiPixelSize: Float = (Resources.getSystem().displayMetrics.density * 50 + 0.5).toFloat()
            _kbEmojiColumns.postValue((Resources.getSystem().displayMetrics.widthPixels.toFloat()/emojiPixelSize).toInt())
        }

        kbSettingSP?.registerOnSharedPreferenceChangeListener(onPrefSettingChangeListener)
    }

    private fun getRightSize(): Float {
        return if (kbHasDivision.value!!) ((kbMoeumSize.value!! + 80) / 100.toFloat()) else 1.toFloat()
    }

    private fun setKbdHeightsInternally(): Float {
        val maxKbHeight = 600F
        val minKbHeight = 350F
        val screenHeight = Resources.getSystem().displayMetrics.heightPixels.toFloat()/2.5F
        val totalKbHeight = when {
            screenHeight >= maxKbHeight -> maxKbHeight * (75 + kbHeight.value!!) / 100
            screenHeight < maxKbHeight && screenHeight >= minKbHeight -> screenHeight * (75 + kbHeight.value!!) / 100
            else -> minKbHeight * (75 + kbHeight.value!!) / 100
        }
        return totalKbHeight
    }

    fun setRecentlyUsedEmoticon(emoji: String) {
        val jsonArray = JSONArray(_kbRecentlyUsedEmoticons.value!!)
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
        kbSettingSP?.edit()?.putString(KeyboardUtil.RECENTLY_USED_EMOTICONS, newJsonArray.toString())?.apply()
    }

    override fun onCleared() {
        super.onCleared()
        kbSettingSP?.unregisterOnSharedPreferenceChangeListener(onPrefSettingChangeListener)
        kbSettingSP = null
    }

    enum class Orientation {
        HORIZONTAL,
        VERTICAL
    }

    data class KbdHeight(val numberRowHeight: Float, val characterRowsHeight: Float) {
        val totalHeight = numberRowHeight + characterRowsHeight
    }
}