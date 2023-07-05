package com.sb.fittingkeyboard.service.viewmodel

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
import com.sb.fittingkeyboard.data.KEYBOARD_AUTO_CAPITALIZATION
import com.sb.fittingkeyboard.data.KEYBOARD_AUTO_MODE_CHANGE
import com.sb.fittingkeyboard.data.KEYBOARD_BOILERPLATE_TEXTS_LIST
import com.sb.fittingkeyboard.data.KEYBOARD_BOTTOM_MARGIN
import com.sb.fittingkeyboard.data.KEYBOARD_CHUN_SPACEBAR_POSITION
import com.sb.fittingkeyboard.data.KEYBOARD_DIVISION
import com.sb.fittingkeyboard.data.KEYBOARD_ENTERKEY_LONGCLICK
import com.sb.fittingkeyboard.data.KEYBOARD_FONT_COLOR
import com.sb.fittingkeyboard.data.KEYBOARD_FONT_SIZE
import com.sb.fittingkeyboard.data.KEYBOARD_FONT_TYPE
import com.sb.fittingkeyboard.data.KEYBOARD_FUNCTION_FONT_COLOR
import com.sb.fittingkeyboard.data.KEYBOARD_HEIGHT
import com.sb.fittingkeyboard.data.KEYBOARD_HOLDING_TIME
import com.sb.fittingkeyboard.data.KEYBOARD_IME_KR
import com.sb.fittingkeyboard.data.KEYBOARD_LEFTSIDE_MARGIN
import com.sb.fittingkeyboard.data.KEYBOARD_MO_SIZE
import com.sb.fittingkeyboard.data.KEYBOARD_RIGHTSIDE_MARGIN
import com.sb.fittingkeyboard.data.KEYBOARD_SETTING
import com.sb.fittingkeyboard.data.KEYBOARD_SPECIALKEY_LONGCLICK
import com.sb.fittingkeyboard.data.KEYBOARD_SWIPEABLE_SPACE
import com.sb.fittingkeyboard.data.KEYBOARD_THEME
import com.sb.fittingkeyboard.data.KEYBOARD_TOGGLE_NUMBER
import com.sb.fittingkeyboard.data.KEYBOARD_TOGGLE_TOOLBAR
import com.sb.fittingkeyboard.data.KEYBOARD_TOOLBAR_ACTIVE_COPY
import com.sb.fittingkeyboard.data.KEYBOARD_TOOLBAR_ACTIVE_CUT
import com.sb.fittingkeyboard.data.KEYBOARD_TOOLBAR_ACTIVE_GO_SETTING
import com.sb.fittingkeyboard.data.KEYBOARD_TOOLBAR_ACTIVE_PASTE
import com.sb.fittingkeyboard.data.KEYBOARD_TOOLBAR_ACTIVE_SELECT_ALL
import com.sb.fittingkeyboard.data.KEYBOARD_TOOLBAR_ACTIVE_SHOW_BOILERPLATE
import com.sb.fittingkeyboard.data.KEYBOARD_TOOLBAR_ACTIVE_SHOW_CURSOR
import com.sb.fittingkeyboard.data.KEYBOARD_TOOLBAR_ACTIVE_SHOW_EMOJI
import com.sb.fittingkeyboard.data.KEYBOARD_TOOLBAR_ACTIVE_SHOW_NUMBER
import com.sb.fittingkeyboard.data.KEYBOARD_TOP_ROW_GAP
import com.sb.fittingkeyboard.data.KEYBOARD_VIBRATION_INTENSITY
import com.sb.fittingkeyboard.data.KEYBOARD_VIBRATION_USE
import com.sb.fittingkeyboard.data.RECENTLY_USED_EMOTICONS
import com.sb.fittingkeyboard.service.keyboardtype.core.InputTypeState
import org.json.JSONArray

class KeyboardViewModel(application: Application) : AndroidViewModel(application) {

    private val _inputTypeState: MutableLiveData<InputTypeState> = MutableLiveData(InputTypeState.EN_UPPER)
    val inputTypeState: LiveData<InputTypeState>
        get() = _inputTypeState

    private val _orientation = MutableLiveData(Orientation.VERTICAL)
    val orientation: LiveData<Orientation>
        get() = _orientation
    fun changeOrientation(config: Int) {
        _orientation.value = if (config == Configuration.ORIENTATION_PORTRAIT) Orientation.VERTICAL else Orientation.HORIZONTAL
    }

    private var _isSelectingText = MutableLiveData(false)
    val isSelectingText: LiveData<Boolean>
        get() = _isSelectingText

    fun switchSelectingTextMode(bool: Boolean) {
        _isSelectingText.value = bool
    }

    private var _savedCursorPosition = 0
    val savedCursorPosition: Int
        get() = _savedCursorPosition

    fun initializeSavedCursorPosition() {
        _savedCursorPosition = -1
    }

    /**
     * @savedLangMode: saves mode value before change. It could be used while mode value complicatedly varies.
     * e.g. English KB -> SpecialChar KB -> English KB
     */
    private var prevInputTypeState = InputTypeState.EN_BOLD_UPPER
    fun setInputTypeState(newState: InputTypeState, restart: Boolean = false) {
        when (_inputTypeState.value) {
            InputTypeState.EN_BOLD_UPPER -> {
                when (newState) {
                    InputTypeState.EN_UPPER -> {
                        _inputTypeState.value = InputTypeState.EN_LOWER
                        prevInputTypeState = _inputTypeState.value!!
                    }
                    InputTypeState.KR_NORMAL -> {
                        _inputTypeState.value = newState
                        prevInputTypeState = newState
                    }
                    InputTypeState.SPECIAL_FIRST, InputTypeState.BOILERPLATE, InputTypeState.CURSOR, InputTypeState.NUMBER, InputTypeState.EMOJI -> {
                        prevInputTypeState = _inputTypeState.value!!
                        _inputTypeState.value = newState
                    }
                    else -> return
                }
            }
            InputTypeState.EN_UPPER -> {
                when (newState) {
                    InputTypeState.EN_UPPER -> {
                        if (restart) return
                        _inputTypeState.value = InputTypeState.EN_BOLD_UPPER
                        prevInputTypeState = InputTypeState.EN_BOLD_UPPER
                    }
                    InputTypeState.EN_LOWER -> {
                        _inputTypeState.value = newState
                        prevInputTypeState = _inputTypeState.value!!
                    }
                    InputTypeState.KR_NORMAL -> {
                        _inputTypeState.value = newState
                        prevInputTypeState = _inputTypeState.value!!
                    }
                    InputTypeState.SPECIAL_FIRST, InputTypeState.BOILERPLATE, InputTypeState.CURSOR, InputTypeState.NUMBER, InputTypeState.EMOJI -> {
                        prevInputTypeState = _inputTypeState.value!!
                        _inputTypeState.value = newState
                    }
                    else -> return
                }
            }
            InputTypeState.EN_LOWER -> {
                when (newState) {
                    InputTypeState.EN_UPPER -> {
                        if (restart) return
                        _inputTypeState.value = newState
                        prevInputTypeState = _inputTypeState.value!!
                    }
                    InputTypeState.KR_NORMAL -> {
                        _inputTypeState.value = newState
                        prevInputTypeState = _inputTypeState.value!!
                    }
                    InputTypeState.SPECIAL_FIRST, InputTypeState.BOILERPLATE, InputTypeState.CURSOR, InputTypeState.NUMBER, InputTypeState.EMOJI -> {
                        prevInputTypeState = _inputTypeState.value!!
                        _inputTypeState.value = newState
                    }
                    else -> return
                }
            }
            InputTypeState.KR_NORMAL -> {
                when (newState) {
                    InputTypeState.EN_UPPER -> {
                        if (restart) return
                        if (kbHasAutoCapitalization.value == true) _inputTypeState.value = newState
                        else _inputTypeState.value = InputTypeState.EN_LOWER
                        prevInputTypeState = newState
                    }
                    InputTypeState.KR_NORMAL -> {
                        _inputTypeState.value = InputTypeState.KR_SHIFT
                    }
                    InputTypeState.SPECIAL_FIRST, InputTypeState.BOILERPLATE, InputTypeState.CURSOR, InputTypeState.NUMBER, InputTypeState.EMOJI -> {
                        prevInputTypeState = _inputTypeState.value!!
                        _inputTypeState.value = newState
                    }
                    else -> return
                }
            }
            InputTypeState.KR_SHIFT -> {
                when (newState) {
                    InputTypeState.EN_UPPER -> {
                        if (restart) return
                        if (kbHasAutoCapitalization.value == true) _inputTypeState.value = newState
                        else _inputTypeState.value = InputTypeState.EN_LOWER
                        prevInputTypeState = _inputTypeState.value!!
                    }
                    InputTypeState.KR_NORMAL -> {
                        _inputTypeState.value = newState
                    }
                    InputTypeState.SPECIAL_FIRST, InputTypeState.BOILERPLATE, InputTypeState.CURSOR, InputTypeState.NUMBER, InputTypeState.EMOJI -> {
                        _inputTypeState.value = newState
                        prevInputTypeState = InputTypeState.KR_NORMAL
                    }
                    else -> return
                }
            }
            InputTypeState.SPECIAL_FIRST -> {
                when (newState) {
                    InputTypeState.EN_UPPER, InputTypeState.KR_NORMAL -> {
                        if (prevInputTypeState in listOf(InputTypeState.EN_UPPER, InputTypeState.EN_LOWER)) {
                            if (kbHasAutoCapitalization.value == true) _inputTypeState.value = InputTypeState.EN_UPPER
                            else _inputTypeState.value = InputTypeState.EN_LOWER
                        }
                        else _inputTypeState.value = prevInputTypeState
                        prevInputTypeState = _inputTypeState.value!!
                    }
                    InputTypeState.SPECIAL_SECOND -> {
                        _inputTypeState.value = newState
                    }
                    InputTypeState.BOILERPLATE, InputTypeState.CURSOR, InputTypeState.NUMBER, InputTypeState.EMOJI -> {
                        prevInputTypeState = _inputTypeState.value!!
                        _inputTypeState.value = newState
                    }
                    else -> return
                }
            }
            InputTypeState.SPECIAL_SECOND -> {
                when (newState) {
                    InputTypeState.EN_UPPER, InputTypeState.KR_NORMAL -> {
                        if (prevInputTypeState in listOf(InputTypeState.EN_UPPER, InputTypeState.EN_LOWER)) {
                            if (kbHasAutoCapitalization.value == true) _inputTypeState.value = InputTypeState.EN_UPPER
                            else _inputTypeState.value = InputTypeState.EN_LOWER
                        }
                        else _inputTypeState.value = newState
                        prevInputTypeState = _inputTypeState.value!!
                    }
                    InputTypeState.SPECIAL_SECOND -> {
                        _inputTypeState.value = InputTypeState.SPECIAL_FIRST
                    }
                    InputTypeState.BOILERPLATE, InputTypeState.CURSOR, InputTypeState.NUMBER, InputTypeState.EMOJI -> {
                        prevInputTypeState = _inputTypeState.value!!
                        _inputTypeState.value = newState
                    }
                    else -> return
                }
            }
            InputTypeState.BOILERPLATE -> {
                when (newState) {
                    InputTypeState.BOILERPLATE -> {
                        if (restart) return
                        _inputTypeState.value = prevInputTypeState
                        prevInputTypeState = InputTypeState.KR_NORMAL
                    }
                    InputTypeState.CURSOR, InputTypeState.NUMBER, InputTypeState.EMOJI -> {
                        if (restart) return
                        _inputTypeState.value = newState
                        prevInputTypeState = InputTypeState.KR_NORMAL
                    }
                    else -> {
                        _inputTypeState.value = newState
                    }
                }
            }
            InputTypeState.CURSOR -> {
                when (newState) {
                    InputTypeState.CURSOR -> {
                        if (restart) return
                        _inputTypeState.value = prevInputTypeState
                        prevInputTypeState = InputTypeState.KR_NORMAL
                    }
                    InputTypeState.BOILERPLATE, InputTypeState.NUMBER, InputTypeState.EMOJI -> {
                        if (restart) return
                        _inputTypeState.value = newState
                        prevInputTypeState = InputTypeState.KR_NORMAL
                    }
                    else -> {
                        _inputTypeState.value = newState
                    }
                }
            }
            InputTypeState.NUMBER -> {
                when (newState) {
                    InputTypeState.NUMBER -> {
                        if (restart) return
                        _inputTypeState.value = prevInputTypeState
                        prevInputTypeState = InputTypeState.KR_NORMAL
                    }
                    InputTypeState.BOILERPLATE, InputTypeState.CURSOR, InputTypeState.EMOJI -> {
                        if (restart) return
                        _inputTypeState.value = newState
                        prevInputTypeState = InputTypeState.KR_NORMAL
                    }
                    else -> {
                        _inputTypeState.value = newState
                    }
                }
            }
            InputTypeState.EMOJI -> {
                when (newState) {
                    InputTypeState.EMOJI -> {
                        if (restart) return
                        _inputTypeState.value = prevInputTypeState
                        prevInputTypeState = InputTypeState.KR_NORMAL
                    }
                    InputTypeState.BOILERPLATE, InputTypeState.CURSOR, InputTypeState.NUMBER -> {
                        if (restart) return
                        _inputTypeState.value = newState
                        prevInputTypeState = InputTypeState.KR_NORMAL
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
        KEYBOARD_SETTING,
        MODE_PRIVATE
    )
    private val _kbHeight = MutableLiveData(kbSettingSP?.getInt(KEYBOARD_HEIGHT, 25) ?: 25)
    val kbHeight: LiveData<Int>
        get() = _kbHeight
    private val _kbBottomMargin = MutableLiveData(kbSettingSP?.getInt(KEYBOARD_BOTTOM_MARGIN, 0) ?: 0)
    val kbBottomMargin: LiveData<Int>
        get() = _kbBottomMargin
    private val _kbFontSize = MutableLiveData(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, (kbSettingSP?.getInt(KEYBOARD_FONT_SIZE, 16) ?: 16).toFloat(), application.resources.displayMetrics).toInt())
    val kbFontSize: LiveData<Int>
        get() = _kbFontSize
    private val _kbFontType = MutableLiveData(kbSettingSP?.getInt(KEYBOARD_FONT_TYPE, 0) ?: 0)
    val kbFontType: LiveData<Int>
        get() = _kbFontType
    private val _kbNumberRowVisibility = MutableLiveData(kbSettingSP?.getInt(KEYBOARD_TOGGLE_NUMBER, View.VISIBLE) ?: View.VISIBLE)
    val kbNumberRowVisibility: LiveData<Int>
        get() = _kbNumberRowVisibility
    private val _kbMoeumSize = MutableLiveData(kbSettingSP?.getInt(KEYBOARD_MO_SIZE, 40) ?: 40)
    val kbMoeumSize: LiveData<Int>
        get() = _kbMoeumSize
    private val _kbHasDivision = MutableLiveData(kbSettingSP?.getBoolean(KEYBOARD_DIVISION, true) ?: true)
    val kbHasDivision: LiveData<Boolean>
        get() = _kbHasDivision
    private val _kbHasTopRowGap = MutableLiveData(kbSettingSP?.getInt(KEYBOARD_TOP_ROW_GAP, View.VISIBLE) ?: View.VISIBLE)
    val kbHasTopRowGap: LiveData<Int>
        get() = _kbHasTopRowGap
    private val _kbLongClickInterval = MutableLiveData(kbSettingSP?.getInt(KEYBOARD_HOLDING_TIME, 200) ?: 200)
    val kbLongClickInterval: LiveData<Int>
        get() = _kbLongClickInterval
    private val _kbHasVibration = MutableLiveData(kbSettingSP?.getBoolean(KEYBOARD_VIBRATION_USE, true) ?: true)
    val kbHasVibration: LiveData<Boolean>
        get() = _kbHasVibration
    private val _kbVibrationIntensity = MutableLiveData(kbSettingSP?.getInt(KEYBOARD_VIBRATION_INTENSITY, 0) ?: 0)
    val kbVibrationIntensity: LiveData<Int>
        get() = _kbVibrationIntensity
    private val _kbTheme = MutableLiveData(kbSettingSP?.getInt(KEYBOARD_THEME, 0) ?: 0)
    val kbTheme: LiveData<Int>
        get() = _kbTheme
    private val _kbNormalKeysFontColor = MutableLiveData(kbSettingSP?.getInt(KEYBOARD_FONT_COLOR, 0xFF000000.toInt()) ?: 0xFF000000.toInt())
    val kbNormalKeysFontColor: LiveData<Int>
        get() = _kbNormalKeysFontColor
    private val _kbFunctionKeysFontColor = MutableLiveData(kbSettingSP?.getInt(KEYBOARD_FUNCTION_FONT_COLOR, 0xFF000000.toInt()) ?: 0xFF000000.toInt())
    val kbFunctionKeysFontColor: LiveData<Int>
        get() = _kbFunctionKeysFontColor
    private val _kbLeftSideMargin = MutableLiveData(kbSettingSP?.getInt(KEYBOARD_LEFTSIDE_MARGIN, 0) ?: 0)
    val kbLeftSideMargin: LiveData<Int>
        get() = _kbLeftSideMargin
    private val _kbRightSideMargin = MutableLiveData(kbSettingSP?.getInt(KEYBOARD_RIGHTSIDE_MARGIN, 0) ?: 0)
    val kbRightSideMargin: LiveData<Int>
        get() = _kbRightSideMargin
    private val _kbHasSwipeableSpace = MutableLiveData(kbSettingSP?.getBoolean(KEYBOARD_SWIPEABLE_SPACE, false) ?: false)
    val kbHasSwipeableSpace: LiveData<Boolean>
        get() = _kbHasSwipeableSpace
    private val _kbToolbarVisibility = MutableLiveData(kbSettingSP?.getInt(KEYBOARD_TOGGLE_TOOLBAR, View.VISIBLE) ?: View.VISIBLE)
    val kbToolbarVisibility: LiveData<Int>
        get() = _kbToolbarVisibility
    private val _kbHasAutoCapitalization = MutableLiveData(kbSettingSP?.getBoolean(KEYBOARD_AUTO_CAPITALIZATION, true) ?: true)
    val kbHasAutoCapitalization: LiveData<Boolean>
        get() = _kbHasAutoCapitalization
    private val _kbHasAutoTypeChange  = MutableLiveData(kbSettingSP?.getBoolean(KEYBOARD_AUTO_MODE_CHANGE, true) ?: true)
    val kbHasTypeChange: LiveData<Boolean>
        get() = _kbHasAutoTypeChange
    private val _kbSpecialKeyOnLongClickFunction = MutableLiveData(kbSettingSP?.getInt(KEYBOARD_SPECIALKEY_LONGCLICK, 0) ?: 0)
    val kbSpecialKeyOnLongClickFunction: LiveData<Int>
        get() = _kbSpecialKeyOnLongClickFunction
    private val _kbEnterKeyOnLongClickFunction = MutableLiveData(kbSettingSP?.getInt(KEYBOARD_ENTERKEY_LONGCLICK, 0) ?: 0)
    val kbEnterKeyOnLongClickFunction: LiveData<Int>
        get() = _kbEnterKeyOnLongClickFunction
    private val _kbKrImeMode = MutableLiveData(kbSettingSP?.getInt(KEYBOARD_IME_KR, 0) ?: 0)
    val kbKrImeMode: LiveData<Int>
        get() = _kbKrImeMode
    private val _kbRecentlyUsedEmoticons = MutableLiveData(kbSettingSP?.getString(RECENTLY_USED_EMOTICONS, JSONArray().put("").toString()) ?: JSONArray().put("").toString())
    val kbRecentlyUsedEmoticons: LiveData<String>
        get() = _kbRecentlyUsedEmoticons
    private val _kbKrChunSpacebarPosition = MutableLiveData(kbSettingSP?.getBoolean(KEYBOARD_CHUN_SPACEBAR_POSITION, false) ?: false)
    val kbKrChunSpacebarPosition: LiveData<Boolean>
        get() = _kbKrChunSpacebarPosition

    private val boilerplateTextMap = KEYBOARD_BOILERPLATE_TEXTS_LIST.groupBy { it }.mapValues { it.value.map { key -> kbSettingSP?.getString(key, "") ?: "" }.single() }.toMutableMap()
    private val _boilerplateTextList = MutableLiveData(boilerplateTextMap)
    val boilerplateTexts: LiveData<MutableMap<String, String>>
        get() = _boilerplateTextList

    private val toolbarSettingMap = hashMapOf(
        KEYBOARD_TOOLBAR_ACTIVE_GO_SETTING to (kbSettingSP?.getInt(KEYBOARD_TOOLBAR_ACTIVE_GO_SETTING, 1) ?: 1),
        KEYBOARD_TOOLBAR_ACTIVE_SHOW_BOILERPLATE to (kbSettingSP?.getInt(KEYBOARD_TOOLBAR_ACTIVE_SHOW_BOILERPLATE, 2) ?: 2),
        KEYBOARD_TOOLBAR_ACTIVE_SELECT_ALL to (kbSettingSP?.getInt(KEYBOARD_TOOLBAR_ACTIVE_SELECT_ALL, 3) ?: 3),
        KEYBOARD_TOOLBAR_ACTIVE_COPY to (kbSettingSP?.getInt(KEYBOARD_TOOLBAR_ACTIVE_COPY, 4) ?: 4),
        KEYBOARD_TOOLBAR_ACTIVE_CUT to (kbSettingSP?.getInt(KEYBOARD_TOOLBAR_ACTIVE_CUT, 5) ?: 5),
        KEYBOARD_TOOLBAR_ACTIVE_PASTE to (kbSettingSP?.getInt(KEYBOARD_TOOLBAR_ACTIVE_PASTE, 6) ?: 6),
        KEYBOARD_TOOLBAR_ACTIVE_SHOW_CURSOR to (kbSettingSP?.getInt(KEYBOARD_TOOLBAR_ACTIVE_SHOW_CURSOR, 7) ?: 7),
        KEYBOARD_TOOLBAR_ACTIVE_SHOW_NUMBER to (kbSettingSP?.getInt(KEYBOARD_TOOLBAR_ACTIVE_SHOW_NUMBER, 8) ?: 8),
        KEYBOARD_TOOLBAR_ACTIVE_SHOW_EMOJI to (kbSettingSP?.getInt(KEYBOARD_TOOLBAR_ACTIVE_SHOW_EMOJI, 9) ?: 9)
    )
    private val _prefSettingToolbarSetting = MutableLiveData(toolbarSettingMap)
    val prefSettingToolbarSetting: LiveData<HashMap<String, Int>>
        get() = _prefSettingToolbarSetting

    private val onPrefSettingChangeListener = OnSharedPreferenceChangeListener { pref, key ->
        when (key) {
            KEYBOARD_HEIGHT -> _kbHeight.postValue(pref.getInt(key, 1))
            KEYBOARD_BOTTOM_MARGIN -> _kbBottomMargin.postValue(pref.getInt(key, 0))
            KEYBOARD_FONT_SIZE -> _kbFontSize.postValue(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, pref.getInt(key, 16).toFloat(), application.resources.displayMetrics).toInt())
            KEYBOARD_FONT_TYPE -> { _kbFontType.postValue(pref.getInt(key, 0)) }
            KEYBOARD_TOGGLE_NUMBER -> _kbNumberRowVisibility.postValue(pref.getInt(key, View.VISIBLE))
            KEYBOARD_MO_SIZE -> _kbMoeumSize.postValue(pref.getInt(key, 20))
            KEYBOARD_DIVISION -> _kbHasDivision.postValue(pref.getBoolean(key, true))
            KEYBOARD_TOP_ROW_GAP -> _kbHasTopRowGap.postValue(pref.getInt(key, View.VISIBLE))
            KEYBOARD_HOLDING_TIME -> _kbLongClickInterval.postValue(pref.getInt(key, 200))
            KEYBOARD_VIBRATION_USE -> _kbHasVibration.postValue(pref.getBoolean(key, true))
            KEYBOARD_VIBRATION_INTENSITY -> _kbVibrationIntensity.postValue(pref.getInt(key, 0))
            KEYBOARD_THEME -> _kbTheme.postValue(pref.getInt(key, 0))
            KEYBOARD_FONT_COLOR -> _kbNormalKeysFontColor.postValue(pref.getInt(key, 0xFF000000.toInt()))
            KEYBOARD_FUNCTION_FONT_COLOR -> _kbFunctionKeysFontColor.postValue(pref.getInt(key, 0xFF000000.toInt()))
            KEYBOARD_LEFTSIDE_MARGIN -> _kbLeftSideMargin.postValue(pref.getInt(key, 0))
            KEYBOARD_RIGHTSIDE_MARGIN -> _kbRightSideMargin.postValue(pref.getInt(key, 0))
            KEYBOARD_TOGGLE_TOOLBAR -> _kbToolbarVisibility.postValue(pref.getInt(key, View.VISIBLE))
            KEYBOARD_AUTO_CAPITALIZATION -> _kbHasAutoCapitalization.postValue(pref.getBoolean(key, true))
            KEYBOARD_AUTO_MODE_CHANGE -> _kbHasAutoTypeChange.postValue(pref.getBoolean(key, true))
            KEYBOARD_SWIPEABLE_SPACE -> _kbHasSwipeableSpace.postValue(pref.getBoolean(key, false))
            KEYBOARD_SPECIALKEY_LONGCLICK -> _kbSpecialKeyOnLongClickFunction.postValue(pref.getInt(key, 0))
            KEYBOARD_ENTERKEY_LONGCLICK -> _kbEnterKeyOnLongClickFunction.postValue(pref.getInt(key, 0))
            KEYBOARD_IME_KR -> _kbKrImeMode.postValue(pref.getInt(key, 0))
            RECENTLY_USED_EMOTICONS -> _kbRecentlyUsedEmoticons.postValue(pref.getString(key, ""))
            KEYBOARD_CHUN_SPACEBAR_POSITION -> _kbKrChunSpacebarPosition.postValue(pref.getBoolean(key, false))
            in KEYBOARD_BOILERPLATE_TEXTS_LIST -> boilerplateTextMap[key] = pref.getString(key, "") ?: ""
            KEYBOARD_TOOLBAR_ACTIVE_GO_SETTING -> toolbarSettingMap[key] = pref.getInt(key, 1)
            KEYBOARD_TOOLBAR_ACTIVE_SHOW_BOILERPLATE -> toolbarSettingMap[key] = pref.getInt(key, 2)
            KEYBOARD_TOOLBAR_ACTIVE_SELECT_ALL -> toolbarSettingMap[key] = pref.getInt(key, 3)
            KEYBOARD_TOOLBAR_ACTIVE_COPY -> toolbarSettingMap[key] = pref.getInt(key, 4)
            KEYBOARD_TOOLBAR_ACTIVE_CUT -> toolbarSettingMap[key] = pref.getInt(key, 5)
            KEYBOARD_TOOLBAR_ACTIVE_PASTE -> toolbarSettingMap[key] = pref.getInt(key, 6)
            KEYBOARD_TOOLBAR_ACTIVE_SHOW_CURSOR -> toolbarSettingMap[key] = pref.getInt(key, 7)
            KEYBOARD_TOOLBAR_ACTIVE_SHOW_NUMBER -> toolbarSettingMap[key] = pref.getInt(key, 8)
            KEYBOARD_TOOLBAR_ACTIVE_SHOW_EMOJI -> toolbarSettingMap[key] = pref.getInt(key, 9)
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
        kbSettingSP?.edit()?.putString(RECENTLY_USED_EMOTICONS, newJsonArray.toString())?.apply()
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
}