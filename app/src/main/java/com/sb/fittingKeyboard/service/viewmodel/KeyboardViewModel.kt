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
import com.sb.fittingKeyboard.Constants
import org.json.JSONArray
import com.sb.fittingKeyboard.service.viewmodel.KeyboardViewModel.InputTypeState.EN_BOLD_UPPER
import com.sb.fittingKeyboard.service.viewmodel.KeyboardViewModel.InputTypeState.EN_UPPER
import com.sb.fittingKeyboard.service.viewmodel.KeyboardViewModel.InputTypeState.EN_LOWER
import com.sb.fittingKeyboard.service.viewmodel.KeyboardViewModel.InputTypeState.KR_NORMAL
import com.sb.fittingKeyboard.service.viewmodel.KeyboardViewModel.InputTypeState.KR_SHIFT
import com.sb.fittingKeyboard.service.viewmodel.KeyboardViewModel.InputTypeState.SPECIAL_FIRST
import com.sb.fittingKeyboard.service.viewmodel.KeyboardViewModel.InputTypeState.SPECIAL_SECOND
import com.sb.fittingKeyboard.service.viewmodel.KeyboardViewModel.InputTypeState.BOILERPLATE
import com.sb.fittingKeyboard.service.viewmodel.KeyboardViewModel.InputTypeState.CURSOR
import com.sb.fittingKeyboard.service.viewmodel.KeyboardViewModel.InputTypeState.NUMBER
import com.sb.fittingKeyboard.service.viewmodel.KeyboardViewModel.InputTypeState.EMOJI

class KeyboardViewModel(application: Application) : AndroidViewModel(application) {

    private val _inputTypeState: MutableLiveData<InputTypeState> = MutableLiveData(EN_UPPER)
    val inputTypeState: LiveData<InputTypeState>
        get() = _inputTypeState

    private val _orientation = MutableLiveData(Orientation.VERTICAL)
    val orientation: LiveData<Orientation>
        get() = _orientation
    fun changeOrientation(config: Int) {
        _orientation.value = if (config == Configuration.ORIENTATION_PORTRAIT) Orientation.VERTICAL else Orientation.HORIZONTAL
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
    private var preInputTypeState = EN_BOLD_UPPER
    fun setInputTypeState(newState: InputTypeState, restart: Boolean = false) {
        when (_inputTypeState.value) {
            EN_BOLD_UPPER -> {
                when (newState) {
                    EN_UPPER -> {
                        _inputTypeState.value = EN_LOWER
                        preInputTypeState = _inputTypeState.value!!
                    }
                    KR_NORMAL -> {
                        _inputTypeState.value = newState
                        preInputTypeState = newState
                    }
                    SPECIAL_FIRST, BOILERPLATE, CURSOR, NUMBER, EMOJI -> {
                        preInputTypeState = _inputTypeState.value!!
                        _inputTypeState.value = newState
                    }
                    else -> return
                }
            }
            EN_UPPER -> {
                when (newState) {
                    EN_UPPER -> {
                        if (restart) return
                        _inputTypeState.value = EN_BOLD_UPPER
                        preInputTypeState = EN_BOLD_UPPER
                    }
                    EN_LOWER -> {
                        _inputTypeState.value = newState
                        preInputTypeState = _inputTypeState.value!!
                    }
                    KR_NORMAL -> {
                        _inputTypeState.value = newState
                        preInputTypeState = _inputTypeState.value!!
                    }
                    SPECIAL_FIRST, BOILERPLATE, CURSOR, NUMBER, EMOJI -> {
                        preInputTypeState = _inputTypeState.value!!
                        _inputTypeState.value = newState
                    }
                    else -> return
                }
            }
            EN_LOWER -> {
                when (newState) {
                    EN_UPPER -> {
                        if (restart) return
                        _inputTypeState.value = newState
                        preInputTypeState = _inputTypeState.value!!
                    }
                    KR_NORMAL -> {
                        _inputTypeState.value = newState
                        preInputTypeState = _inputTypeState.value!!
                    }
                    SPECIAL_FIRST, BOILERPLATE, CURSOR, NUMBER, EMOJI -> {
                        preInputTypeState = _inputTypeState.value!!
                        _inputTypeState.value = newState
                    }
                    else -> return
                }
            }
            KR_NORMAL -> {
                when (newState) {
                    EN_UPPER -> {
                        if (restart) return
                        if (kbHasAutoCapitalization.value == true) _inputTypeState.value = newState
                        else _inputTypeState.value = EN_LOWER
                        preInputTypeState = newState
                    }
                    KR_NORMAL -> {
                        _inputTypeState.value = KR_SHIFT
                    }
                    SPECIAL_FIRST, BOILERPLATE, CURSOR, NUMBER, EMOJI -> {
                        preInputTypeState = _inputTypeState.value!!
                        _inputTypeState.value = newState
                    }
                    else -> return
                }
            }
            KR_SHIFT -> {
                when (newState) {
                    EN_UPPER -> {
                        if (restart) return
                        if (kbHasAutoCapitalization.value == true) _inputTypeState.value = newState
                        else _inputTypeState.value = EN_LOWER
                        preInputTypeState = _inputTypeState.value!!
                    }
                    KR_NORMAL -> {
                        _inputTypeState.value = newState
                    }
                    SPECIAL_FIRST, BOILERPLATE, CURSOR, NUMBER, EMOJI -> {
                        _inputTypeState.value = newState
                        preInputTypeState = KR_NORMAL
                    }
                    else -> return
                }
            }
            SPECIAL_FIRST -> {
                when (newState) {
                    EN_UPPER, KR_NORMAL -> {
                        if (preInputTypeState in listOf(EN_UPPER, EN_LOWER)) {
                            if (kbHasAutoCapitalization.value == true) _inputTypeState.value = EN_UPPER
                            else _inputTypeState.value = EN_LOWER
                        }
                        else _inputTypeState.value = preInputTypeState
                        preInputTypeState = _inputTypeState.value!!
                    }
                    SPECIAL_SECOND -> {
                        _inputTypeState.value = newState
                    }
                    BOILERPLATE, CURSOR, NUMBER, EMOJI -> {
                        preInputTypeState = _inputTypeState.value!!
                        _inputTypeState.value = newState
                    }
                    else -> return
                }
            }
            SPECIAL_SECOND -> {
                when (newState) {
                    EN_UPPER, KR_NORMAL -> {
                        if (preInputTypeState in listOf(EN_UPPER, EN_LOWER)) {
                            if (kbHasAutoCapitalization.value == true) _inputTypeState.value = EN_UPPER
                            else _inputTypeState.value = EN_LOWER
                        }
                        else _inputTypeState.value = newState
                        preInputTypeState = _inputTypeState.value!!
                    }
                    SPECIAL_SECOND -> {
                        _inputTypeState.value = SPECIAL_FIRST
                    }
                    BOILERPLATE, CURSOR, NUMBER, EMOJI -> {
                        preInputTypeState = _inputTypeState.value!!
                        _inputTypeState.value = newState
                    }
                    else -> return
                }
            }
            BOILERPLATE, CURSOR, NUMBER, EMOJI -> {
                when(newState) {
                    BOILERPLATE, CURSOR, NUMBER, EMOJI -> {
                        if (restart) return
                        _inputTypeState.value = preInputTypeState
                        preInputTypeState = KR_NORMAL
                    }
                    EN_BOLD_UPPER, EN_UPPER, EN_LOWER, KR_NORMAL, KR_SHIFT -> {
                        _inputTypeState.value = newState
                        preInputTypeState = _inputTypeState.value!!
                    }
                    else -> {
                        _inputTypeState.value = newState
                    }
                }
            }
            else -> return
        }
    }

    private var kbSettingSP: SharedPreferences? = application.applicationContext.getSharedPreferences(
        Constants.KEYBOARD_SETTING,
        MODE_PRIVATE
    )
    private val _kbHeight = MutableLiveData(kbSettingSP?.getInt(Constants.KEYBOARD_HEIGHT, 25) ?: 25)
    val kbHeight: LiveData<Int>
        get() = _kbHeight
    private val _kbBottomMargin = MutableLiveData(kbSettingSP?.getInt(Constants.KEYBOARD_BOTTOM_MARGIN, 0) ?: 0)
    val kbBottomMargin: LiveData<Int>
        get() = _kbBottomMargin
    private val _kbFontSize = MutableLiveData(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, (kbSettingSP?.getInt(Constants.KEYBOARD_FONT_SIZE, 16) ?: 16).toFloat(), application.resources.displayMetrics).toInt())
    val kbFontSize: LiveData<Int>
        get() = _kbFontSize
    private val _kbFontType = MutableLiveData(kbSettingSP?.getInt(Constants.KEYBOARD_FONT_TYPE, 0) ?: 0)
    val kbFontType: LiveData<Int>
        get() = _kbFontType
    private val _kbNumberRowVisibility = MutableLiveData(kbSettingSP?.getInt(Constants.KEYBOARD_TOGGLE_NUMBER, View.VISIBLE) ?: View.VISIBLE)
    val kbNumberRowVisibility: LiveData<Int>
        get() = _kbNumberRowVisibility
    private val _kbMoeumSize = MutableLiveData(kbSettingSP?.getInt(Constants.KEYBOARD_MO_SIZE, 40) ?: 40)
    val kbMoeumSize: LiveData<Int>
        get() = _kbMoeumSize
    private val _kbHasDivision = MutableLiveData(kbSettingSP?.getBoolean(Constants.KEYBOARD_DIVISION, true) ?: true)
    val kbHasDivision: LiveData<Boolean>
        get() = _kbHasDivision
    private val _kbHasTopRowGap = MutableLiveData(kbSettingSP?.getInt(Constants.KEYBOARD_TOP_ROW_GAP, View.VISIBLE) ?: View.VISIBLE)
    val kbHasTopRowGap: LiveData<Int>
        get() = _kbHasTopRowGap
    private val _kbLongClickInterval = MutableLiveData(kbSettingSP?.getInt(Constants.KEYBOARD_HOLDING_TIME, 200) ?: 200)
    val kbLongClickInterval: LiveData<Int>
        get() = _kbLongClickInterval
    private val _kbHasVibration = MutableLiveData(kbSettingSP?.getBoolean(Constants.KEYBOARD_VIBRATION_USE, true) ?: true)
    val kbHasVibration: LiveData<Boolean>
        get() = _kbHasVibration
    private val _kbVibrationIntensity = MutableLiveData(kbSettingSP?.getInt(Constants.KEYBOARD_VIBRATION_INTENSITY, 0) ?: 0)
    val kbVibrationIntensity: LiveData<Int>
        get() = _kbVibrationIntensity
    private val _kbTheme = MutableLiveData(kbSettingSP?.getInt(Constants.KEYBOARD_THEME, 0) ?: 0)
    val kbTheme: LiveData<Int>
        get() = _kbTheme
    private val _kbNormalKeysFontColor = MutableLiveData(kbSettingSP?.getInt(Constants.KEYBOARD_FONT_COLOR, 0xFF000000.toInt()) ?: 0xFF000000.toInt())
    val kbNormalKeysFontColor: LiveData<Int>
        get() = _kbNormalKeysFontColor
    private val _kbFunctionKeysFontColor = MutableLiveData(kbSettingSP?.getInt(Constants.KEYBOARD_FUNCTION_FONT_COLOR, 0xFF000000.toInt()) ?: 0xFF000000.toInt())
    val kbFunctionKeysFontColor: LiveData<Int>
        get() = _kbFunctionKeysFontColor
    private val _kbLeftSideMargin = MutableLiveData(kbSettingSP?.getInt(Constants.KEYBOARD_LEFTSIDE_MARGIN, 0) ?: 0)
    val kbLeftSideMargin: LiveData<Int>
        get() = _kbLeftSideMargin
    private val _kbRightSideMargin = MutableLiveData(kbSettingSP?.getInt(Constants.KEYBOARD_RIGHTSIDE_MARGIN, 0) ?: 0)
    val kbRightSideMargin: LiveData<Int>
        get() = _kbRightSideMargin
    private val _kbSwipeableSpace = MutableLiveData(kbSettingSP?.getBoolean(Constants.KEYBOARD_SWIPEABLE_SPACE, false) ?: false)
    val kbSwipeableSpace: LiveData<Boolean>
        get() = _kbSwipeableSpace
    private val _kbToolbarVisibility = MutableLiveData(kbSettingSP?.getInt(Constants.KEYBOARD_TOGGLE_TOOLBAR, View.VISIBLE) ?: View.VISIBLE)
    val kbToolbarVisibility: LiveData<Int>
        get() = _kbToolbarVisibility
    private val _kbHasAutoCapitalization = MutableLiveData(kbSettingSP?.getBoolean(Constants.KEYBOARD_AUTO_CAPITALIZATION, true) ?: true)
    val kbHasAutoCapitalization: LiveData<Boolean>
        get() = _kbHasAutoCapitalization
    private val _kbHasAutoModeChange  = MutableLiveData(kbSettingSP?.getBoolean(Constants.KEYBOARD_AUTO_MODE_CHANGE, true) ?: true)
    val kbHasAutoModeChange: LiveData<Boolean>
        get() = _kbHasAutoModeChange
    private val _kbSpecialKeyOnLongClickFunction = MutableLiveData(kbSettingSP?.getInt(Constants.KEYBOARD_SPECIALKEY_LONGCLICK, 0) ?: 0)
    val kbSpecialKeyOnLongClickFunction: LiveData<Int>
        get() = _kbSpecialKeyOnLongClickFunction
    private val _kbEnterKeyOnLongClickFunction = MutableLiveData(kbSettingSP?.getInt(Constants.KEYBOARD_ENTERKEY_LONGCLICK, 0) ?: 0)
    val kbEnterKeyOnLongClickFunction: LiveData<Int>
        get() = _kbEnterKeyOnLongClickFunction
    private val _kbKrImeMode = MutableLiveData(kbSettingSP?.getInt(Constants.KEYBOARD_IME_KR, 0) ?: 0)
    val kbKrImeMode: LiveData<Int>
        get() = _kbKrImeMode
    private val _kbRecentlyUsedEmoticons = MutableLiveData(kbSettingSP?.getString(Constants.RECENTLY_USED_EMOTICONS, JSONArray().put("").toString()) ?: JSONArray().put("").toString())
    val kbRecentlyUsedEmoticons: LiveData<String>
        get() = _kbRecentlyUsedEmoticons

    private val boilerplateTextMap = Constants.KEYBOARD_BOILERPLATE_TEXTS_LIST.groupBy { it }.mapValues { it.value.map { key -> kbSettingSP?.getString(key, "") ?: "" }.single() }.toMutableMap()
    private val _boilerplateTextList = MutableLiveData(boilerplateTextMap)
    val boilerplateTexts: LiveData<MutableMap<String, String>>
        get() = _boilerplateTextList

    private val toolbarSettingMap = hashMapOf(
        Constants.KEYBOARD_TOOLBAR_ACTIVE_GO_SETTING to (kbSettingSP?.getInt(Constants.KEYBOARD_TOOLBAR_ACTIVE_GO_SETTING, 1) ?: 1),
        Constants.KEYBOARD_TOOLBAR_ACTIVE_SHOW_BOILERPLATE to (kbSettingSP?.getInt(Constants.KEYBOARD_TOOLBAR_ACTIVE_SHOW_BOILERPLATE, 2) ?: 2),
        Constants.KEYBOARD_TOOLBAR_ACTIVE_SELECT_ALL to (kbSettingSP?.getInt(Constants.KEYBOARD_TOOLBAR_ACTIVE_SELECT_ALL, 3) ?: 3),
        Constants.KEYBOARD_TOOLBAR_ACTIVE_COPY to (kbSettingSP?.getInt(Constants.KEYBOARD_TOOLBAR_ACTIVE_COPY, 4) ?: 4),
        Constants.KEYBOARD_TOOLBAR_ACTIVE_CUT to (kbSettingSP?.getInt(Constants.KEYBOARD_TOOLBAR_ACTIVE_CUT, 5) ?: 5),
        Constants.KEYBOARD_TOOLBAR_ACTIVE_PASTE to (kbSettingSP?.getInt(Constants.KEYBOARD_TOOLBAR_ACTIVE_PASTE, 6) ?: 6),
        Constants.KEYBOARD_TOOLBAR_ACTIVE_SHOW_CURSOR to (kbSettingSP?.getInt(Constants.KEYBOARD_TOOLBAR_ACTIVE_SHOW_CURSOR, 7) ?: 7),
        Constants.KEYBOARD_TOOLBAR_ACTIVE_SHOW_NUMBER to (kbSettingSP?.getInt(Constants.KEYBOARD_TOOLBAR_ACTIVE_SHOW_NUMBER, 8) ?: 8),
        Constants.KEYBOARD_TOOLBAR_ACTIVE_SHOW_EMOJI to (kbSettingSP?.getInt(Constants.KEYBOARD_TOOLBAR_ACTIVE_SHOW_EMOJI, 9) ?: 9)
    )
    private val _prefSettingToolbarSetting = MutableLiveData(toolbarSettingMap)
    val prefSettingToolbarSetting: LiveData<HashMap<String, Int>>
        get() = _prefSettingToolbarSetting

    private val onPrefSettingChangeListener = OnSharedPreferenceChangeListener { pref, key ->
        when (key) {
            Constants.KEYBOARD_HEIGHT -> _kbHeight.postValue(pref.getInt(key, 1))
            Constants.KEYBOARD_BOTTOM_MARGIN -> _kbBottomMargin.postValue(pref.getInt(key, 0))
            Constants.KEYBOARD_FONT_SIZE -> _kbFontSize.postValue(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, pref.getInt(key, 16).toFloat(), application.resources.displayMetrics).toInt())
            Constants.KEYBOARD_FONT_TYPE -> { _kbFontType.postValue(pref.getInt(key, 0)) }
            Constants.KEYBOARD_TOGGLE_NUMBER -> _kbNumberRowVisibility.postValue(pref.getInt(key, View.VISIBLE))
            Constants.KEYBOARD_MO_SIZE -> _kbMoeumSize.postValue(pref.getInt(key, 20))
            Constants.KEYBOARD_DIVISION -> _kbHasDivision.postValue(pref.getBoolean(key, true))
            Constants.KEYBOARD_TOP_ROW_GAP -> _kbHasTopRowGap.postValue(pref.getInt(key, View.VISIBLE))
            Constants.KEYBOARD_HOLDING_TIME -> _kbLongClickInterval.postValue(pref.getInt(key, 200))
            Constants.KEYBOARD_VIBRATION_USE -> _kbHasVibration.postValue(pref.getBoolean(key, true))
            Constants.KEYBOARD_VIBRATION_INTENSITY -> _kbVibrationIntensity.postValue(pref.getInt(key, 0))
            Constants.KEYBOARD_THEME -> _kbTheme.postValue(pref.getInt(key, 0))
            Constants.KEYBOARD_FONT_COLOR -> _kbNormalKeysFontColor.postValue(pref.getInt(key, 0xFF000000.toInt()))
            Constants.KEYBOARD_FUNCTION_FONT_COLOR -> _kbFunctionKeysFontColor.postValue(pref.getInt(key, 0xFF000000.toInt()))
            Constants.KEYBOARD_LEFTSIDE_MARGIN -> _kbLeftSideMargin.postValue(pref.getInt(key, 0))
            Constants.KEYBOARD_RIGHTSIDE_MARGIN -> _kbRightSideMargin.postValue(pref.getInt(key, 0))
            Constants.KEYBOARD_TOGGLE_TOOLBAR -> _kbToolbarVisibility.postValue(pref.getInt(key, View.VISIBLE))
            Constants.KEYBOARD_AUTO_CAPITALIZATION -> _kbHasAutoCapitalization.postValue(pref.getBoolean(key, true))
            Constants.KEYBOARD_AUTO_MODE_CHANGE -> _kbHasAutoModeChange.postValue(pref.getBoolean(key, true))
            Constants.KEYBOARD_SWIPEABLE_SPACE -> _kbSwipeableSpace.postValue(pref.getBoolean(key, false))
            Constants.KEYBOARD_SPECIALKEY_LONGCLICK -> _kbSpecialKeyOnLongClickFunction.postValue(pref.getInt(key, 0))
            Constants.KEYBOARD_ENTERKEY_LONGCLICK -> _kbEnterKeyOnLongClickFunction.postValue(pref.getInt(key, 0))
            Constants.KEYBOARD_IME_KR -> _kbKrImeMode.postValue(pref.getInt(key, 0))
            Constants.RECENTLY_USED_EMOTICONS -> _kbRecentlyUsedEmoticons.postValue(pref.getString(key, ""))
            in Constants.KEYBOARD_BOILERPLATE_TEXTS_LIST -> boilerplateTextMap[key] = pref.getString(key, "") ?: ""
            Constants.KEYBOARD_TOOLBAR_ACTIVE_GO_SETTING -> toolbarSettingMap[key] = pref.getInt(key, 1)
            Constants.KEYBOARD_TOOLBAR_ACTIVE_SHOW_BOILERPLATE -> toolbarSettingMap[key] = pref.getInt(key, 2)
            Constants.KEYBOARD_TOOLBAR_ACTIVE_SELECT_ALL -> toolbarSettingMap[key] = pref.getInt(key, 3)
            Constants.KEYBOARD_TOOLBAR_ACTIVE_COPY -> toolbarSettingMap[key] = pref.getInt(key, 4)
            Constants.KEYBOARD_TOOLBAR_ACTIVE_CUT -> toolbarSettingMap[key] = pref.getInt(key, 5)
            Constants.KEYBOARD_TOOLBAR_ACTIVE_PASTE -> toolbarSettingMap[key] = pref.getInt(key, 6)
            Constants.KEYBOARD_TOOLBAR_ACTIVE_SHOW_CURSOR -> toolbarSettingMap[key] = pref.getInt(key, 7)
            Constants.KEYBOARD_TOOLBAR_ACTIVE_SHOW_NUMBER -> toolbarSettingMap[key] = pref.getInt(key, 8)
            Constants.KEYBOARD_TOOLBAR_ACTIVE_SHOW_EMOJI -> toolbarSettingMap[key] = pref.getInt(key, 9)
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
                this.value = (
                    if (orientation.value == Orientation.HORIZONTAL) 0
                    else kbBottomMargin.value
                )
            }
            addSource(orientation) {
                this.setValue(
                    if (orientation.value == Orientation.HORIZONTAL) 0
                    else kbBottomMargin.value
                )
            }
        }

        _kbResultedHeight.apply {
            addSource(kbHeight) {
                this.setValue(setKbdHeightsInternally())
            }
            addSource(inputTypeState) {
                this.setValue(setKbdHeightsInternally())
            }
            addSource(kbBottomMargin) {
                this.setValue(setKbdHeightsInternally())
            }
            addSource(kbNumberRowVisibility) {
                this.setValue(setKbdHeightsInternally())
            }
            addSource(orientation) {
                this.setValue(setKbdHeightsInternally())
            }
        }

        _kbRightSize.apply {
            addSource(kbHasDivision) {
                this.setValue(getRightSize())
            }
            addSource(kbMoeumSize) {
                this.setValue(getRightSize())
            }
        }

        _kbEmojiColumns.addSource(orientation) {
            val emojiPixelSize: Float = (Resources.getSystem().displayMetrics.density * 50 + 0.5).toFloat()
            _kbEmojiColumns.value = (Resources.getSystem().displayMetrics.widthPixels.toFloat()/emojiPixelSize).toInt()
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
            if (jsonArray.optString(i).isNotBlank()) arr.add(jsonArray.optString(i))
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
        kbSettingSP?.edit()?.putString(Constants.RECENTLY_USED_EMOTICONS, newJsonArray.toString())?.apply()
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

    enum class InputTypeState {
        EN_BOLD_UPPER,
        EN_UPPER,
        EN_LOWER,
        KR_NORMAL,
        KR_SHIFT,
        SPECIAL_FIRST,
        SPECIAL_SECOND,
        BOILERPLATE,
        CURSOR,
        NUMBER,
        EMOJI
    }
}