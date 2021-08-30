package com.sb.fittingKeyboard.service

import RepeatListener
import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Resources
import android.graphics.Typeface
import android.inputmethodservice.InputMethodService
import android.os.Build
import android.os.Build.VERSION.SDK_INT
import android.os.SystemClock
import android.os.VibrationEffect
import android.os.Vibrator
import android.text.TextUtils
import android.util.TypedValue
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.ExtractedTextRequest
import android.view.inputmethod.InputConnection.CURSOR_UPDATE_IMMEDIATE
import android.view.inputmethod.InputConnection.GET_TEXT_WITH_STYLES
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.*
import com.sb.fittingKeyboard.R
import com.sb.fittingKeyboard.keyboardSettings.SetTheme
import com.sb.fittingKeyboard.koreanAutomata.*
import com.sb.fittingKeyboard.service.util.KeyboardUtil
import com.sb.fittingKeyboard.service.util.KeyboardUtil.Companion.enChar
import com.sb.fittingKeyboard.service.viewmodel.SharedKBViewModel
import java.util.*
import kotlin.properties.Delegates


@Suppress("DEPRECATION")
class InputMethodServiceViewRefactor : InputMethodService(), LifecycleOwner {
    companion object {
        const val PACKAGE_NAME = "com.sb.fittingKeyboard"
    }
    private lateinit var vm: SharedKBViewModel
    private lateinit var kbFrame: FrameLayout
    var mode = 0
    private lateinit var kbView: View
    private lateinit var kbLayout: LinearLayout
    private lateinit var kbToolbar: LinearLayout
    private lateinit var kbLayoutBottomMargin: View
    private lateinit var kbCharLeftSide: FrameLayout
    private lateinit var kbCharRightSide: FrameLayout
    private lateinit var kbNumLeftSide: Button
    private lateinit var kbNumRightSide: Button

    private val mDispatcher = ServiceLifecycleDispatcher(this)
    override fun getLifecycle(): Lifecycle = mDispatcher.lifecycle

    //<editor-fold desc="변수 선언">
    private val inputTypeNumbers = arrayOf(2, 4098, 8194, 18, 3, 4, 14, 24, 24578, 16387)

    private lateinit var theme: SetTheme
    private lateinit var fontTypeIndex: Typeface
    private var myKeyboardHeight: Float = 0F
    var myKeyboardRightSize: Float = 1F
    private var myKeyboardDivision: Boolean = true
    private var myKeyboardHolding: Int = 300
    private var myKeyboardVibration: Int = 2
    private var myKeyboardVibrationIntensity: Int = 50
    var myKeyboardToggleNum: Int = View.VISIBLE
    private var myKeyboardBotMargin: Int = 0
    private var myKeyboardTheme: Int = 0
    var myDefaultFontColor: Int = 0xFF000000.toInt()
    var myFunctionFontColor: Int = 0xFF000000.toInt()
    var myKeyboardLeftSideMargin: Int = 1
    var myKeyboardRightSideMargin: Int = 1
    private var myKeyboardFontType: Int = 0
    var myKeyboardToggleToolbar: Boolean = true
    var myKeyboardFontSize: Float = 0F
    private var myKeyboardAutoCapital: Boolean = true
    private var myKeyboardAutoModeChange: Boolean = true
    private var myKeyboardAutoText1: String? = ""
    private var myKeyboardAutoText2: String? = ""
    private var myKeyboardAutoText3: String? = ""
    private var myKeyboardAutoText4: String? = ""
    private var myKeyboardAutoText5: String? = ""
    private var myKeyboardAutoText6: String? = ""
    private var myKeyboardAutoText7: String? = ""
    private var myKeyboardAutoText8: String? = ""
    private var myKeyboardAutoText9: String? = ""
    private var myKeyboardAutoText10: String? = ""
    private var myKeyboardAutoText11: String? = ""
    private var myKeyboardAutoText12: String? = ""
    private var myKeyboardAutoText13: String? = ""
    private var myKeyboardAutoText14: String? = ""
    private var myKeyboardAutoText15: String? = ""
    private var myKeyboardAutoText16: String? = ""
    private var myKeyboardSpecialKeyAddon: Int = 0
    private var myKeyboardEnterKeyAddon: Int = 0
    private var myKeyboardInputMethodKR: Int = 0

    private var langSaveValue = 1
    private var delBoolean = false
    private var normalInterval: Long = 37
    private var bpPage = 1

    private var shiftIcon_activated = R.drawable.keyic_shift_activated_black
    private var shiftIcon_deactivated = R.drawable.keyic_shift_deactivated_black
    private var shiftIcon_hyperactivated = R.drawable.keyic_shift_hyperactivated_black

    private val paramNormal =
        LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1.0f)

    private var isSelectingActivated = false
    private var savedCursorPosition = 0

    //</editor-fold>

    override fun onCreate() {
        super.onCreate()
        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(application).create(SharedKBViewModel::class.java)

        val qwertyEnNormalKBView = layoutInflater.inflate(R.layout.fragment_keyboard_qwerty_en_normal, null)
        val qwertyKrNormalKBView = layoutInflater.inflate(R.layout.fragment_keyboard_qwerty_kr_normal, null)
        val qwertySpecialKBView = layoutInflater.inflate(R.layout.fragment_keyboard_qwerty_kr_normal, null)
        val boilerPlateKBView = layoutInflater.inflate(R.layout.fragment_bp, null)
        val cursorKBView = layoutInflater.inflate(R.layout.fragment_cursor, null)
        val numberKBView = layoutInflater.inflate(R.layout.fragment_keyboard_number, null)
        val chunjiinKBView = layoutInflater.inflate(R.layout.fragment_keyboard_chunjiin_basic, null)
        val chunjiinKBViewAmbi = layoutInflater.inflate(R.layout.fragment_keyboard_chunjiin_ambi, null)
        val danmoKBView = layoutInflater.inflate(R.layout.fragment_keyboard_danmoum, null)
        val naratguelKBView = layoutInflater.inflate(R.layout.fragment_keyboard_naratgul_basic, null)

        var currentKRView = qwertyKrNormalKBView
        vm.currentKR.observeForever {
            currentKRView =
                when (it) {
                    KeyboardUtil.QWERTY -> qwertyKrNormalKBView
                    KeyboardUtil.CHUN -> chunjiinKBView
                    KeyboardUtil.CHUN_AMBI -> chunjiinKBViewAmbi
                    KeyboardUtil.NARAT -> naratguelKBView
                    KeyboardUtil.DAN -> danmoKBView
                    else -> qwertyKrNormalKBView
                }
        }

        kbView = layoutInflater.inflate(R.layout.key_layout_normal, null)
        kbLayout = kbView.findViewById(R.id.keyboardLayout)
        kbFrame = kbView.findViewById(R.id.keyboardViewFrameLayout)
        kbLayoutBottomMargin = kbView.findViewById(R.id.keyboardBotMargin)
        vm.mode.observeForever {
            mode = it
            kbFrame.removeAllViews()
            when (it) {
                0, 1, 2 -> kbFrame.addView(qwertyEnNormalKBView)
                3, 4 -> kbFrame.addView(currentKRView)
                5, 6 -> kbFrame.addView(qwertySpecialKBView)
                7 -> kbFrame.addView(boilerPlateKBView)
                8 -> kbFrame.addView(cursorKBView)
                9 -> kbFrame.addView(numberKBView)
            }
        }
        vm.observeNumberVisibility.observeForever {
            myKeyboardToggleNum = if (it) View.VISIBLE else View.GONE
        }
        vm.observeKBBottomMargin.observeForever {
            val lP = kbLayoutBottomMargin.layoutParams
            lP.height = it
            kbLayoutBottomMargin.layoutParams = lP
        }
        vm.observeKBFontSize.observeForever {
            myKeyboardFontSize = it
        }
        vm.observeKBFontType.observeForever {
            myKeyboardFontType = it
        }
        vm.observeRightSize.observeForever {
            myKeyboardRightSize = it
        }
        vm.observeKBHoldingTime.observeForever {
            myKeyboardHolding = it + 100
        }
        vm.observeKBVibrationType.observeForever {
            myKeyboardVibration = it
        }
        vm.observeKBVibrationIntensity.observeForever {
            myKeyboardVibrationIntensity = it
        }
        vm.observeKBTheme.observeForever {
            myKeyboardTheme = it
        }
        vm.observeKBFontColor.observeForever {
            myDefaultFontColor = it
        }
        vm.observeKBFunctionFontColor.observeForever {
            myFunctionFontColor = it
        }
        vm.observeKBLeftSideMargin.observeForever {
            val lPNum = kbNumLeftSide.layoutParams
            val lPChar = kbCharLeftSide.layoutParams
            lPNum.width = it * 3
            lPChar.width = it * 3
            kbNumLeftSide.layoutParams = lPNum
            kbCharLeftSide.layoutParams = lPChar
        }
        vm.observeKBRightSideMargin.observeForever {
            val lPNum = kbNumRightSide.layoutParams
            val lPChar = kbCharRightSide.layoutParams
            lPNum.width = it * 3
            lPChar.width = it * 3
            kbNumRightSide.layoutParams = lPNum
            kbCharRightSide.layoutParams = lPChar
        }
        vm.observeKBToolBarVisibility.observeForever {
            myKeyboardToggleToolbar = it
        }
        vm.observeHeight.observeForever {
            myKeyboardHeight = it
            val lP = kbLayout.layoutParams
            lP.height = changeDPtoPX(it.toInt()).toInt()
            kbLayout.layoutParams = lP
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("ClickableViewAccessibility", "DefaultLocale", "NewApi")
    override fun onCreateInputView(): View {
        super.onCreateInputView()
        return kbView
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onUpdateSelection(
        oldSelStart: Int,
        oldSelEnd: Int,
        newSelStart: Int,
        newSelEnd: Int,
        candidatesStart: Int,
        candidatesEnd: Int
    ) {
        if (!delBoolean && candidatesStart != -1) {
            if ((candidatesStart != oldSelStart && candidatesEnd != newSelStart)) {
                clearComposing()
            }
        } else if (!delBoolean && newSelStart == 0 && newSelEnd == 0 && candidatesStart == -1 && candidatesEnd == -1) {
            clearComposing()
        }
        /** TextField의 첫글자로 돌아갔을 때 대문자로 수정 **/
        if (myKeyboardAutoCapital
            && currentInputConnection.requestCursorUpdates(CURSOR_UPDATE_IMMEDIATE)
            && mode == 2) {
            var autoCapitalCondition: Boolean = false
            for (i in 0..3) {
                if (currentInputConnection.getTextBeforeCursor(i + 2, GET_TEXT_WITH_STYLES)
                    .toString().replace("\\s".toRegex(), "") == ".") {
                    autoCapitalCondition = true
                }
            }
            if ((newSelStart == 0 && newSelEnd == 0 && candidatesStart == -1 && candidatesEnd == -1) || autoCapitalCondition) {
                vm._mode.value = 0
                btnSHIFT.setImageResource(shiftIcon_activated)
                btnSHIFT.setColorFilter(myFunctionFontColor)
                btnSHIFT.setPadding(changeDPtoPX(10).toInt())
            }
        }
    }

    @SuppressLint("ClickableViewAccessibility", "NewApi")
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onStartInputView(info: EditorInfo?, restarting: Boolean) {
        super.onStartInputView(info, restarting)
        fontTypeIndex = when (myKeyboardFontType) {
            0 -> Typeface.DEFAULT
            1 -> ResourcesCompat.getFont(this.applicationContext, R.font.aritta)!!
            2 -> ResourcesCompat.getFont(this.applicationContext, R.font.dovemayo)!!
            3 -> ResourcesCompat.getFont(this.applicationContext, R.font.imcresoojin)!!
            4 -> ResourcesCompat.getFont(this.applicationContext, R.font.maplestorylight)!!
            5 -> ResourcesCompat.getFont(this.applicationContext, R.font.nanumbarungothic)!!
            6 -> ResourcesCompat.getFont(this.applicationContext, R.font.nanumsquarer)!!
            7 -> ResourcesCompat.getFont(this.applicationContext, R.font.seoulnamsan)!!
            8 -> ResourcesCompat.getFont(this.applicationContext, R.font.tttogether)!!
            9 -> ResourcesCompat.getFont(this.applicationContext, R.font.cookierun)!!
            10 -> ResourcesCompat.getFont(this.applicationContext, R.font.tmoney)!!
            11 -> ResourcesCompat.getFont(this.applicationContext, R.font.tadaktadak)!!
            else -> Typeface.DEFAULT
        }
        //<editor-fold desc="모양 초기화">
        bpPage = 1
        if (currentInputEditorInfo.inputType in inputTypeNumbers) {
            vm.changeMode(9)
        }
//        if (!myKeyboardAutoCapital) { /** 자동대문자 비활성화 상태에서 영어키보드로 텍스트 입력을 시작할 경우 무조건 소문자 키보드로 바꿈 **/
//            if (modeValue in arrayOf(0, 1, 2)) {
//                modeValue = 2
//                setLayoutByMode(fontTypeIndex, modeValue)
//            }
//        }
        /** 한글자판/구별 설정 시 모음만 따로 표시 **/
        for (item in themeBtnChar) {
            item.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontSizeIndex)
            item.setTextColor(myDefaultFontColor)
            item.setTypeface(fontTypeIndex, Typeface.NORMAL)
        }
        for (item in themeBtnFun) {
            item.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontSizeIndex)
            item.setTextColor(myFunctionFontColor)
            item.setTypeface(fontTypeIndex, Typeface.NORMAL)
        }
        for (item in themeImgBtnChar) {
            item.setColorFilter(myDefaultFontColor)
        }
        for (item in themeImgBtnFun) {
            item.setColorFilter(myFunctionFontColor)
        }
        for (item in themeBtnEnter) {
            item.setColorFilter(myFunctionFontColor)
        }
        btnSPECIAL.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12.0F)
        btnChunSPECIAL.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12.0F)
        btnChunSPECIALa.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12.0F)
        btnNaSPECIAL.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12.0F)
        fragmentKeyboardQWERTY.findViewById<Button>(R.id.blank)
            .setBackgroundColor(applicationContext.resources.getColor(R.color.transparent))
        keyboardViewBotMargin.setBackgroundColor(applicationContext.resources.getColor(R.color.transparent))
        keyboardViewNumLeftMargin.setBackgroundColor(applicationContext.resources.getColor(R.color.transparent))
        keyboardViewNumRightMargin.setBackgroundColor(applicationContext.resources.getColor(R.color.transparent))
        keyboardViewCharLeftMargin.setBackgroundColor(applicationContext.resources.getColor(R.color.transparent))
        keyboardViewCharRightMargin.setBackgroundColor(applicationContext.resources.getColor(R.color.transparent))
        when (myKeyboardTheme) {
            0 -> theme.setTheme00()
            1 -> theme.setTheme01()
            2 -> theme.setTheme02()
            3 -> theme.setTheme03()
            4 -> theme.setTheme04()
            5 -> theme.setTheme05()
            6 -> theme.setTheme06()
            7 -> {
                theme.setTheme07()
                fragmentKeyboardQWERTY.findViewById<Button>(R.id.blank)
                    .setBackgroundColor(applicationContext.resources.getColor(R.color.black))
                fragmentKeyboardDan.findViewById<Button>(R.id.blank)
                    .setBackgroundColor(applicationContext.resources.getColor(R.color.black))
                fragmentKeyboardDan.findViewById<Button>(R.id.blank2)
                    .setBackgroundColor(applicationContext.resources.getColor(R.color.black))
                keyboardViewBotMargin.setBackgroundColor(applicationContext.resources.getColor(R.color.black))
                keyboardViewNumLeftMargin.setBackgroundColor(applicationContext.resources.getColor(R.color.black))
                keyboardViewNumRightMargin.setBackgroundColor(
                    applicationContext.resources.getColor(
                        R.color.black
                    )
                )
                keyboardViewCharLeftMargin.setBackgroundColor(
                    applicationContext.resources.getColor(
                        R.color.black
                    )
                )
                keyboardViewCharRightMargin.setBackgroundColor(
                    applicationContext.resources.getColor(
                        R.color.black
                    )
                )
            }
            8 -> theme.setTheme08()
            9 -> theme.setTheme09()
            10 -> theme.setTheme10()
            11 -> theme.setTheme11()
            12 -> theme.setTheme12()
            13 -> theme.setTheme13()
            14 -> theme.setTheme14()
            15 -> theme.setTheme15()
            16 -> theme.setTheme16()
            17 -> theme.setTheme17(keyboardViewLayout)
            18 -> theme.setTheme18()
            else -> theme.setTheme00()
        }
        //</editor-fold>
//        shortCutKeyboardSetting.setOnClickListener {
//            startApp()
//        }
//        selectAll.setOnClickListener {
//            selectAll()
//        }
        getNumberFrag.setOnClickListener {
            bpPage = 1
            buttonBoilerPlateTxt.setImageResource(R.drawable.ic_boilerplatetext_black)
            buttonCursorFragment.setImageResource(R.drawable.ic_move)
            isSelectingActivated = false
            fragCursorSelectWord.text = "선택"
            if (currentFragment != 3) {
                currentFragment = 3
                keyboardView.keyboardViewFrameLayout.removeAllViews()
                keyboardView.keyboardViewFrameLayout.addView(fragmentKeyboardNumber)
                getNumberFrag.setImageResource(R.drawable.ic_keyboard_black)
                keyboardViewFirstLine.visibility = View.GONE
                keyboardViewLayout.layoutParams.height =
                    changeDPtoPX(180 * myKeyboardHeight / 100).toInt() + myKeyboardBotMargin
            } else {
                keyboardView.keyboardViewFrameLayout.removeAllViews()
                getNumberFrag.setImageResource(R.drawable.ic_number_keypad)
                if (currentInputEditorInfo.inputType in inputTypeNumbers) {
                    currentFragment = 3
                    keyboardViewFirstLine.visibility = View.GONE
                    keyboardViewLayout.layoutParams.height =
                        changeDPtoPX(180 * myKeyboardHeight / 100).toInt() + myKeyboardBotMargin
                    keyboardView.keyboardViewFrameLayout.addView(fragmentKeyboardNumber)
                } //숫자키보드로 되돌아가기
                else {
                    currentFragment = 0
                    when (myKeyboardInputMethodKR) {
                        1 -> keyboardView.keyboardViewFrameLayout.addView(fragmentKeyboardChunjiin)
                        2 -> keyboardView.keyboardViewFrameLayout.addView(fragmentKeyboardNaratgul)
                        3 -> keyboardView.keyboardViewFrameLayout.addView(fragmentKeyboardChunjiinA)
                        4 -> keyboardView.keyboardViewFrameLayout.addView(fragmentKeyboardDan)
                        else -> keyboardView.keyboardViewFrameLayout.addView(fragmentKeyboardQWERTY)
                    }
                    adjustLayoutSize()
                } //일반키보드로 되돌아가기
            }
            keyboardBackgroundImage.layoutParams.height = keyboardViewLayout.layoutParams.height
        }
        toolBarCopy.setOnClickListener {
            copyText()
            isSelectingActivated = false
            fragCursorSelectWord.text = "선택"
        }
        toolBarCut.setOnClickListener {
            cutText()
            isSelectingActivated = false
            fragCursorSelectWord.text = "선택"
        }
        toolBarPaste.setOnClickListener {
            pasteText()
            isSelectingActivated = false
            fragCursorSelectWord.text = "선택"
        }
        //<editor-fold desc="쿼티키보드">
        for (btn in btnBasicList) {
            when (btn) {
                btnSPACE -> {
                    btn.setOnTouchListener(
                        RepeatListener(
                            myKeyboardHolding.toLong(),
                            normalInterval,
                            View.OnClickListener {
                                clearComposing()
                                hanguelQwerty.composeChar(' ', currentInputConnection)
                                if (myKeyboardAutoModeChange) { //스페이스바로 자동으로 모드 전환
                                    when (modeValue) {
                                        5, 6 -> {
                                            modeValue = langSaveValue
                                            setLayoutByMode(fontTypeIndex, modeValue)
                                            btnSPECIAL.visibility = View.VISIBLE
                                            keyboardViewLayout.layoutParams.height =
                                                when (myKeyboardToggleNum) {
                                                    true -> changeDPtoPX(220 * myKeyboardHeight / 100).toInt() + myKeyboardBotMargin
                                                    false -> changeDPtoPX(180 * myKeyboardHeight / 100).toInt() + myKeyboardBotMargin
                                                }
                                            langSaveValue = 1 //초기화
                                        }
                                    }
                                }
                                if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(
                                    myKeyboardVibrationIntensity
                                )
                            })
                    )
                }
                btnDOT -> {
                    btn.setOnTouchListener(
                        RepeatListener(
                            myKeyboardHolding.toLong(),
                            normalInterval,
                            View.OnClickListener {
                                clearComposing()
                                currentInputConnection.commitText(btnDOT.text.toString(), 1)
                                if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(
                                    myKeyboardVibrationIntensity
                                )
                            })
                    )
                }
                btnCOMMA -> {
                    btn.setOnTouchListener(
                        RepeatListener(
                            myKeyboardHolding.toLong(),
                            normalInterval,
                            View.OnClickListener {
                                clearComposing()
                                hanguelQwerty.composeChar(
                                    btnCOMMA.text.single(),
                                    currentInputConnection
                                )
                                if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(
                                    myKeyboardVibrationIntensity
                                )
                            })
                    )
                }
                else -> {
                    btn.setOnTouchListener(
                        RepeatListener(
                            myKeyboardHolding.toLong(),
                            normalInterval,
                            View.OnClickListener {
                                val cursorCS: CharSequence? =
                                    currentInputConnection?.getSelectedText(GET_TEXT_WITH_STYLES)
                                /**선택한 글자들이 있는데 키보드 입력 시 선택된 글자들을 삭제하고 새 글자를 입력**/
                                if (cursorCS != null && cursorCS.isNotEmpty()) {
                                    clearComposing()
                                }
                                else {
                                    hanguelChunjiin.initState()
                                    hanguelChunjiin.initChar()
                                    hanguelChunjiin.initResult()
                                    hanguelNaratgul.initState()
                                    hanguelNaratgul.initChar()
                                    hanguelNaratgul.initResult()
                                    danHangul.initState()
                                    danHangul.initChar()
                                    danHangul.initResult()
                                }
                                if (myKeyboardInputMethodKR != 0) currentInputConnection.finishComposingText()
                                hanguelQwerty.composeChar(btn.text.toString().single(), currentInputConnection)
                                if (modeValue == 4 && btn in btnCharList) { //쌍자음 모드에서 아무 버튼을 누르면 기본 자모로 돌아옴
                                    modeValue = 3
                                    setLayoutByMode(fontTypeIndex, modeValue)
                                }
                                if (modeValue == 1 && btn in btnCharList) { //대문자 모드에서 Char 클릭 시 소문자로 돌아옴
                                    modeValue = 2
                                    setLayoutByMode(fontTypeIndex, modeValue)
                                }
                                /**진동 세기 설정**/
                                if (myKeyboardVibration == 1) {
                                    if (btn in buttonVibrationList) vibrateByButton(
                                        myKeyboardVibrationIntensity
                                    )
                                } else if (myKeyboardVibration == 2) {
                                    if (btn in btnBasicList) vibrateByButton(
                                        myKeyboardVibrationIntensity
                                    )
                                }
                            })
                    )
                }
            }
        }
        btnSHIFT.setOnClickListener {
            when (modeValue) {
                0 -> modeValue = 2 //영구대문자 -> 소문자
                1 -> modeValue = 0 //대문자 -> 영구대문자
                2 -> modeValue = 1 //소문자 -> 대문자
                3 -> modeValue = 4 //자음 -> 쌍자음
                4 -> modeValue = 3 //쌍자음 -> 자음
                5 -> modeValue = 6
                6 -> modeValue = 5
            }
            setLayoutByMode(fontTypeIndex, modeValue)
            if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(
                myKeyboardVibrationIntensity
            )
        }
        btnCOMMA.run {
            text = when (currentInputEditorInfo.inputType) {
                EditorInfo.TYPE_TEXT_VARIATION_WEB_EMAIL_ADDRESS, EditorInfo.TYPE_TEXT_VARIATION_EMAIL_ADDRESS, 720929, 209 -> {
                    "@"
                }
                else -> {
                    ","
                }
            }
        }
        btnDOT.run {
            text = when (currentInputEditorInfo.inputType) {
                EditorInfo.TYPE_TEXT_VARIATION_WEB_EMAIL_ADDRESS, EditorInfo.TYPE_TEXT_VARIATION_EMAIL_ADDRESS, 720929, 209 -> {
                    ".com".toLowerCase(Locale.ROOT)
                }
                else -> {
                    "."
                }
            }
        }
        btnSPECIAL.run {
            setOnClickListener {
                when (modeValue) {
                    0, 1, 2, 3, 4 -> {
                        langSaveValue = modeValue
                        modeValue = 5
                        setLayoutByMode(fontTypeIndex, modeValue)
                        keyboardViewLayout.layoutParams.height =
                            changeDPtoPX(220 * myKeyboardHeight / 100).toInt() + myKeyboardBotMargin
                    }
                }
                if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(
                    myKeyboardVibrationIntensity
                )
            }
            setOnLongClickListener {
                if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(
                    myKeyboardVibrationIntensity
                )
                addFunction(myKeyboardSpecialKeyAddon)
                true
            }
        }
        when (modeValue) {
            0 -> {
                btnSHIFT.setImageResource(shiftIcon_hyperactivated)
                btnSHIFT.setColorFilter(myFunctionFontColor)
                btnSHIFT.setPadding(changeDPtoPX(10).toInt())
            }
            1, 4, 6 -> {
                btnSHIFT.setImageResource(shiftIcon_activated)
                btnSHIFT.setColorFilter(myFunctionFontColor)
                btnSHIFT.setPadding(changeDPtoPX(10).toInt())
            }
            2, 3, 5 -> {
                btnSHIFT.setImageResource(shiftIcon_deactivated)
                btnSHIFT.setColorFilter(myFunctionFontColor)
                btnSHIFT.setPadding(changeDPtoPX(10).toInt())
            }
        }
        btnDEL.run {
            setPadding(changeDPtoPX(8).toInt())
            setOnTouchListener(
                RepeatListener(
                    myKeyboardHolding.toLong(),
                    normalInterval,
                    View.OnClickListener {
                        delFunction(myKeyboardInputMethodKR)
                        if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(
                            myKeyboardVibrationIntensity
                        )
                    })
            )
        }
        btnENTER.run {
            setPadding(changeDPtoPX(10).toInt())
            btnENTER.setOnClickListener {
                enterFunction()
            }
            setOnLongClickListener {
                if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(
                    myKeyboardVibrationIntensity
                )
                addFunction(myKeyboardEnterKeyAddon)
                true
            }
        }
        btnLANG.run {
            setPadding(changeDPtoPX(8).toInt())
            setOnClickListener {
                when (modeValue) {
                    0, 1, 2 -> {
                        langSaveValue = modeValue //langSaveValue = 1 || 2
                        modeValue = 3
                        setLayoutByMode(fontTypeIndex, modeValue)
                    }
                    3, 4 -> {
                        modeValue = langSaveValue //modeValue = 1 || 2
                        setLayoutByMode(fontTypeIndex, modeValue)
                    }
                    5, 6 -> {
                        when (myKeyboardInputMethodKR) {
                            0 -> {
                                modeValue = langSaveValue
                                setLayoutByMode(fontTypeIndex, modeValue)
                                fragmentKeyboardQWERTY.btnSPECIAL.visibility = View.VISIBLE
                                keyboardViewLayout.layoutParams.height =
                                    when (myKeyboardToggleNum) {
                                        true -> changeDPtoPX(220 * myKeyboardHeight / 100).toInt() + myKeyboardBotMargin
                                        false -> changeDPtoPX(180 * myKeyboardHeight / 100).toInt() + myKeyboardBotMargin
                                    }
                                langSaveValue = 1 //초기화
                            }
                            1, 2, 3, 4 -> {
                                modeValue = 3
                                setLayoutByMode(fontTypeIndex, modeValue)
                            }
                        }
                    }
                }
                if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(
                    myKeyboardVibrationIntensity
                )
            }
        }
        //</editor-fold>
        //<editor-fold desc="천지인키보드">
        for (item in chunjiinCharacterArray) {
            item.run {
                setOnClickListener {
                    hanguelChunjiin.composeChar(item.text[0], currentInputConnection)
                    if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(
                        myKeyboardVibrationIntensity
                    )
                }
            }
        }
        btnChunSPACE.run {
            setOnClickListener {
                clearComposing()
                currentInputConnection.commitText(" ", 1)
                if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(
                    myKeyboardVibrationIntensity
                )
            }
        }
        btnChunENTER.run {
            setPadding(changeDPtoPX(10).toInt())
            setOnClickListener {
                enterFunction()
                isSelectingActivated = false
                fragCursorSelectWord.text = "선택"
            }
            setOnLongClickListener {
                if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(
                    myKeyboardVibrationIntensity
                )
                addFunction(myKeyboardEnterKeyAddon)
                true
            }
        }
        btnChunDEL.run {
            setPadding(changeDPtoPX(10).toInt())
            setOnTouchListener(
                RepeatListener(
                    myKeyboardHolding.toLong(),
                    normalInterval,
                    View.OnClickListener {
                        delFunction(myKeyboardInputMethodKR)
                        if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(
                            myKeyboardVibrationIntensity
                        )
                    })
            )
        }
        btnChunLANG.run {
            setPadding(changeDPtoPX(10).toInt())
            setOnClickListener {
                modeValue = langSaveValue //modeValue = 1 || 2
                setLayoutByMode(fontTypeIndex, modeValue)
                if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(
                    myKeyboardVibrationIntensity
                )
            }
        }
        btnChunDOT.run {
            setOnClickListener {
                clearComposing()
                currentInputConnection.commitText(".", 1)
                if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(
                    myKeyboardVibrationIntensity
                )
            }
            setOnLongClickListener {
                clearComposing()
                currentInputConnection.commitText(",", 1)
                if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(
                    myKeyboardVibrationIntensity
                )
                true
            }
        }
        btnChunAT.run {
            setOnClickListener {
                clearComposing()
                currentInputConnection.commitText("@", 1)
                if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(
                    myKeyboardVibrationIntensity
                )
            }
        }
        btnChunSPECIAL.run {
            setOnClickListener {
                modeValue = 5
                langSaveValue = 1
                setLayoutByMode(fontTypeIndex, modeValue)
                for (item in btnRight) item.setTextColor(myDefaultFontColor)
                keyboardViewLayout.layoutParams.height =
                    changeDPtoPX(220 * myKeyboardHeight / 100).toInt() + myKeyboardBotMargin
                if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(
                    myKeyboardVibrationIntensity
                )
            }
            setOnLongClickListener {
                if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(
                    myKeyboardVibrationIntensity
                )
                addFunction(myKeyboardSpecialKeyAddon)
                true
            }
        }
        btnChunInit.run {
            setPadding(changeDPtoPX(10).toInt())
            setOnClickListener {
                clearComposing()
                if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(
                    myKeyboardVibrationIntensity
                )
            }
        }
        btnChunSPACEa.run {
            setOnClickListener {
                clearComposing()
                currentInputConnection.commitText(" ", 1)
                if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(
                    myKeyboardVibrationIntensity
                )
            }
        }
        btnChunENTERa.run {
            setPadding(changeDPtoPX(10).toInt())
            setOnClickListener {
                enterFunction()
                isSelectingActivated = false
                fragCursorSelectWord.text = "선택"
            }
            setOnLongClickListener {
                if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(
                    myKeyboardVibrationIntensity
                )
                addFunction(myKeyboardEnterKeyAddon)
                true
            }
        }
        btnChunDELa.run {
            setPadding(changeDPtoPX(10).toInt())
            setOnTouchListener(
                RepeatListener(
                    myKeyboardHolding.toLong(),
                    normalInterval,
                    View.OnClickListener {
                        delFunction(myKeyboardInputMethodKR)
                        if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(
                            myKeyboardVibrationIntensity
                        )
                    })
            )
        }
        btnChunLANGa.run {
            setPadding(changeDPtoPX(10).toInt())
            setOnClickListener {
                modeValue = langSaveValue //modeValue = 1 || 2
                setLayoutByMode(fontTypeIndex, modeValue)
                if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(
                    myKeyboardVibrationIntensity
                )
            }
        }
        btnChunDOTa.run {
            setOnClickListener {
                clearComposing()
                currentInputConnection.commitText(".", 1)
                if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(
                    myKeyboardVibrationIntensity
                )
            }
            setOnLongClickListener {
                clearComposing()
                currentInputConnection.commitText(",", 1)
                if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(
                    myKeyboardVibrationIntensity
                )
                true
            }
        }
        btnChunATa.run {
            setOnClickListener {
                clearComposing()
                currentInputConnection.commitText("@", 1)
                if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(
                    myKeyboardVibrationIntensity
                )
            }
        }
        btnChunSPECIALa.run {
            setOnClickListener {
                modeValue = 5
                langSaveValue = 1
                setLayoutByMode(fontTypeIndex, modeValue)
                for (item in btnRight) item.setTextColor(myDefaultFontColor)
                keyboardViewLayout.layoutParams.height =
                    changeDPtoPX(220 * myKeyboardHeight / 100).toInt() + myKeyboardBotMargin
                if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(
                    myKeyboardVibrationIntensity
                )
            }
            setOnLongClickListener {
                if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(
                    myKeyboardVibrationIntensity
                )
                addFunction(myKeyboardSpecialKeyAddon)
                true
            }
        }
        btnChunInita.run {
            setPadding(changeDPtoPX(10).toInt())
            setOnClickListener {
                if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(
                    myKeyboardVibrationIntensity
                )
                clearComposing()
            }
        }
        //</editor-fold>
        //<editor-fold desc="나랏글키보드">
        for (item in naratgulCharacterArray) {
            item.run {
                setOnClickListener {
                    hanguelNaratgul.composeChar(item.text[0], currentInputConnection)
                    if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(
                        myKeyboardVibrationIntensity
                    )
                }
            }
        }
        btnNaSHIFT.run {
            setOnClickListener {
                hanguelNaratgul.composeChar('ᆢ', currentInputConnection)
                if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(
                    myKeyboardVibrationIntensity
                )
            }
        }
        btnNaADD.run {
            setOnClickListener {
                hanguelNaratgul.composeChar('ᆞ', currentInputConnection)
                if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(
                    myKeyboardVibrationIntensity
                )
            }
        }
        btnNaSPACE.run {
            setOnClickListener {
                clearComposing()
                currentInputConnection.commitText(" ", 1)
                if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(
                    myKeyboardVibrationIntensity
                )
            }
        }
        btnNaENTER.run {
            setPadding(changeDPtoPX(10).toInt())
            setOnClickListener {
                enterFunction()
                isSelectingActivated = false
                fragCursorSelectWord.text = "선택"
            }
            setOnLongClickListener {
                if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(
                    myKeyboardVibrationIntensity
                )
                addFunction(myKeyboardEnterKeyAddon)
                true
            }
        }
        btnNaDEL.run {
            setPadding(changeDPtoPX(10).toInt())
            setOnTouchListener(
                RepeatListener(
                    myKeyboardHolding.toLong(),
                    normalInterval,
                    View.OnClickListener {
                        delFunction(myKeyboardInputMethodKR)
                        if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(
                            myKeyboardVibrationIntensity
                        )
                    })
            )
        }
        btnNaLANG.run {
            setPadding(changeDPtoPX(12).toInt())
            setOnClickListener {
                modeValue = langSaveValue //modeValue = 1 || 2
                setLayoutByMode(fontTypeIndex, modeValue)
                if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(
                    myKeyboardVibrationIntensity
                )
            }
        }
        btnNaDOT.run {
            setOnClickListener {
                clearComposing()
                currentInputConnection.commitText(".", 1)
                if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(
                    myKeyboardVibrationIntensity
                )
            }
            setOnLongClickListener {
                clearComposing()
                currentInputConnection.commitText(",", 1)
                if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(
                    myKeyboardVibrationIntensity
                )
                true
            }
        }
        btnNaSPECIAL.run {
            setOnClickListener {
                modeValue = 5
                langSaveValue = 1
                setLayoutByMode(fontTypeIndex, modeValue)
                for (item in btnRight) item.setTextColor(myDefaultFontColor)
                keyboardViewLayout.layoutParams.height =
                    changeDPtoPX(220 * myKeyboardHeight / 100).toInt() + myKeyboardBotMargin
                if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(
                    myKeyboardVibrationIntensity
                )
            }
            setOnLongClickListener {
                if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(
                    myKeyboardVibrationIntensity
                )
                addFunction(myKeyboardSpecialKeyAddon)
                true
            }
        }
        //</editor-fold>
        //<editor-fold desc="단모음키보드">
        for (item in danmoumCharacterArray) {
            item.run {
                setOnTouchListener(
                    RepeatListener(
                        myKeyboardHolding.toLong(),
                        normalInterval,
                        View.OnClickListener {
                            danHangul.composeChar(item.text[0], currentInputConnection, inputTime = System.currentTimeMillis())
                            if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(
                                myKeyboardVibrationIntensity
                            )
                        })
                )
            }
        }
        btnDanSPACE.run {
            setOnTouchListener(
                RepeatListener(
                    myKeyboardHolding.toLong(),
                    normalInterval,
                    View.OnClickListener {
                        clearComposing()
                        currentInputConnection.commitText(" ", 1)
                        if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(
                            myKeyboardVibrationIntensity
                        )
                    })
            )
        }
        btnDanENTER.run {
            setPadding(changeDPtoPX(10).toInt())
            setOnClickListener {
                enterFunction()
                isSelectingActivated = false
                fragCursorSelectWord.text = "선택"
            }
            setOnLongClickListener {
                if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(
                    myKeyboardVibrationIntensity
                )
                addFunction(myKeyboardEnterKeyAddon)
                true
            }
        }
        btnDanDEL.run {
            setPadding(changeDPtoPX(10).toInt())
            setOnTouchListener(
                RepeatListener(
                    myKeyboardHolding.toLong(),
                    normalInterval,
                    View.OnClickListener {
                        delFunction(myKeyboardInputMethodKR)
                        if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(
                            myKeyboardVibrationIntensity
                        )
                    })
            )
        }
        btnDanLANG.run {
            setPadding(changeDPtoPX(12).toInt())
            setOnClickListener {
                modeValue = langSaveValue // modeValue = 1 || 2
                setLayoutByMode(fontTypeIndex, modeValue)
                if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(
                    myKeyboardVibrationIntensity
                )
            }
        }
        btnDanDOT.run {
            setOnTouchListener(
                RepeatListener(
                    myKeyboardHolding.toLong(),
                    normalInterval,
                    View.OnClickListener {
                        clearComposing()
                        currentInputConnection.commitText(".", 1)
                        if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(
                            myKeyboardVibrationIntensity
                        )
                    })
            )
        }
        btnDanCOMMA.run {
            setOnTouchListener(
                RepeatListener(
                    myKeyboardHolding.toLong(),
                    normalInterval,
                    View.OnClickListener {
                        clearComposing()
                        currentInputConnection.commitText(",", 1)
                        if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(
                            myKeyboardVibrationIntensity
                        )
                    })
            )
        }
        btnDanSPECIAL.run {
            setOnClickListener {
                modeValue = 5
                langSaveValue = 1
                setLayoutByMode(fontTypeIndex, modeValue)
                for (item in btnRight) item.setTextColor(myDefaultFontColor)
                keyboardViewLayout.layoutParams.height =
                    changeDPtoPX(220 * myKeyboardHeight / 100).toInt() + myKeyboardBotMargin
                if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(
                    myKeyboardVibrationIntensity
                )
            }
            setOnLongClickListener {
                if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(
                    myKeyboardVibrationIntensity
                )
                addFunction(myKeyboardSpecialKeyAddon)
                true
            }
        }
        //</editor-fold>
        //<editor-fold desc="숫자키보드">
        for (item in numberArray) {
            item.run {
                setOnClickListener {
                    clearComposing()
                    currentInputConnection.commitText(item.text[0].toString(), 1)
                    if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(
                        myKeyboardVibrationIntensity
                    )
                }
            }
        }
        numBtnSPACE.setOnClickListener {
            clearComposing()
            currentInputConnection.commitText(" ", 1)
            if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(
                myKeyboardVibrationIntensity
            )
        }
        numBtnENTER.run {
            setPadding(changeDPtoPX(10).toInt())
            setOnClickListener {
                enterFunction()
                isSelectingActivated = false
                fragCursorSelectWord.text = "선택"
            }
            setOnLongClickListener {
                if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(
                    myKeyboardVibrationIntensity
                )
                addFunction(myKeyboardEnterKeyAddon)
                true
            }
        }
        numBtnDEL.run {
            setPadding(changeDPtoPX(10).toInt())
            setOnTouchListener(
                RepeatListener(
                    myKeyboardHolding.toLong(),
                    normalInterval,
                    View.OnClickListener {
                        delFunction(myKeyboardInputMethodKR)
                        if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(
                            myKeyboardVibrationIntensity
                        )
                    })
            )
        }
        numBtnLANG.run {
            setPadding(changeDPtoPX(10).toInt())
            setOnClickListener {
                when (modeValue) {
                    0, 1, 2 -> {
                        langSaveValue = modeValue //langSaveValue = 1 || 2
                        modeValue = 3
                        setLayoutByMode(fontTypeIndex, modeValue)
                    }
                    3, 4 -> {
                        modeValue = langSaveValue //modeValue = 1 || 2
                        setLayoutByMode(fontTypeIndex, modeValue)
                    }
                    5, 6 -> {
                        when (myKeyboardInputMethodKR) {
                            0 -> {
                                modeValue = langSaveValue
                                setLayoutByMode(fontTypeIndex, modeValue)
                                fragmentKeyboardQWERTY.btnSPECIAL.visibility = View.VISIBLE
                                keyboardViewLayout.layoutParams.height =
                                    when (myKeyboardToggleNum) {
                                        true -> changeDPtoPX(220 * myKeyboardHeight / 100).toInt() + myKeyboardBotMargin
                                        false -> changeDPtoPX(180 * myKeyboardHeight / 100).toInt() + myKeyboardBotMargin
                                    }
                                langSaveValue = 1 //초기화
                            }
                            1 -> {
                                modeValue = 3
                                setLayoutByMode(fontTypeIndex, modeValue)
                                keyboardView.keyboardViewFrameLayout.removeAllViews()
                                keyboardView.keyboardViewFrameLayout.addView(
                                    fragmentKeyboardChunjiin
                                )
                            }
                            2 -> {
                                modeValue = 3
                                setLayoutByMode(fontTypeIndex, modeValue)
                                keyboardView.keyboardViewFrameLayout.removeAllViews()
                                keyboardView.keyboardViewFrameLayout.addView(
                                    fragmentKeyboardNaratgul
                                )
                            }
                            3 -> {
                                modeValue = 3
                                setLayoutByMode(fontTypeIndex, modeValue)
                                keyboardView.keyboardViewFrameLayout.removeAllViews()
                                keyboardView.keyboardViewFrameLayout.addView(
                                    fragmentKeyboardChunjiinA
                                )
                            }
                            4 -> {
                                modeValue = 3
                                setLayoutByMode(fontTypeIndex, modeValue)
                                keyboardView.keyboardViewFrameLayout.removeAllViews()
                                keyboardView.keyboardViewFrameLayout.addView(
                                    fragmentKeyboardDan
                                )
                            }
                        }
                    }
                }
                if (currentFragment == 3) {
                    currentFragment = 0
                    getNumberFrag.setImageResource(R.drawable.ic_number_keypad)
                }
                if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(
                    myKeyboardVibrationIntensity
                )
            }
        }
        //</editor-fold>
        //<editor-fold desc="상용구키보드">
        for (index in autoTextArray.indices) {
            autoTextArray[index].run {
                setTextColor(myDefaultFontColor)
                setOnClickListener {
                    clearComposing()
                    currentInputConnection.commitText(autoTextArray[index].text.toString(), 1)
                    if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(
                        myKeyboardVibrationIntensity
                    )
                }
                setOnLongClickListener {
                    if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(
                        myKeyboardVibrationIntensity
                    )
                    val intent =
                        applicationContext.packageManager.getLaunchIntentForPackage("com.sb.fittingKeyboard")
                    clearComposing()
                    if (bpPage == 1) {
                        if (intent != null) {
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            intent.putExtra("Index", index)
                            startActivity(intent)
                        }
                    }
                    true
                }
            }
        }
        fragAutoENTER.run {
            setPadding(changeDPtoPX(5).toInt())
            setOnClickListener {
                enterFunction()
                isSelectingActivated = false
                fragCursorSelectWord.text = "선택"
            }
        }
        fragAutoDEL.run {
            setPadding(changeDPtoPX(5).toInt())
            setOnTouchListener(
                RepeatListener(
                    myKeyboardHolding.toLong(),
                    normalInterval,
                    View.OnClickListener {
                        delFunction(myKeyboardInputMethodKR)
                        if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(
                            myKeyboardVibrationIntensity
                        )
                    })
            )
        }
        fragAutoSPACE.run {
            textSize = 12.0F
            setOnTouchListener(
                RepeatListener(
                    myKeyboardHolding.toLong(),
                    normalInterval,
                    View.OnClickListener {
                        clearComposing()
                        hanguelQwerty.composeChar(' ', currentInputConnection)
                        if (myKeyboardAutoModeChange) { //스페이스바로 자동으로 모드 전환
                            when (modeValue) {
                                5, 6 -> {
                                    modeValue = langSaveValue
                                    setLayoutByMode(fontTypeIndex, modeValue)
                                    btnSPECIAL.visibility = View.VISIBLE
                                    langSaveValue = 1 //초기화
                                }
                            }
                        }
                        if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(
                            myKeyboardVibrationIntensity
                        )
                    })
            )
        }
        fragAutoNEXT.run {
            setOnClickListener {
                if (bpPage == 1) {
                    autoText1.text = myKeyboardAutoText9
                    autoText2.text = myKeyboardAutoText10
                    autoText3.text = myKeyboardAutoText11
                    autoText4.text = myKeyboardAutoText12
                    autoText5.text = myKeyboardAutoText13
                    autoText6.text = myKeyboardAutoText14
                    autoText7.text = myKeyboardAutoText15
                    autoText8.text = myKeyboardAutoText16
                    bpPage = 2
                } else {
                    autoText1.text = myKeyboardAutoText1
                    autoText2.text = myKeyboardAutoText2
                    autoText3.text = myKeyboardAutoText3
                    autoText4.text = myKeyboardAutoText4
                    autoText5.text = myKeyboardAutoText5
                    autoText6.text = myKeyboardAutoText6
                    autoText7.text = myKeyboardAutoText7
                    autoText8.text = myKeyboardAutoText8
                    bpPage = 1
                }
                fragAutoNEXT.text = "$bpPage/2"
                if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(
                    myKeyboardVibrationIntensity
                )
            }
        }
        fragBpKBD.run {
            setPadding(changeDPtoPX(5).toInt())
            setOnClickListener {
                currentFragment = 0
                keyboardView.keyboardViewFrameLayout.removeAllViews()
                when (myKeyboardInputMethodKR) {
                    1 -> keyboardView.keyboardViewFrameLayout.addView(fragmentKeyboardChunjiin)
                    2 -> keyboardView.keyboardViewFrameLayout.addView(fragmentKeyboardNaratgul)
                    3 -> keyboardView.keyboardViewFrameLayout.addView(fragmentKeyboardChunjiinA)
                    4 -> keyboardView.keyboardViewFrameLayout.addView(fragmentKeyboardDan)
                    else -> keyboardView.keyboardViewFrameLayout.addView(fragmentKeyboardQWERTY)
                }
                buttonBoilerPlateTxt.setImageResource(R.drawable.ic_boilerplatetext_black)
                if (myKeyboardToggleNum || (!myKeyboardToggleNum && (modeValue == 5 || modeValue == 6))) {
                    keyboardViewFirstLine.visibility = View.VISIBLE
                    keyboardViewLayout.layoutParams.height =
                        changeDPtoPX(220 * myKeyboardHeight / 100).toInt() + myKeyboardBotMargin
                } else {
                    keyboardViewFirstLine.visibility = View.GONE
                    keyboardViewLayout.layoutParams.height =
                        changeDPtoPX(180 * myKeyboardHeight / 100).toInt() + myKeyboardBotMargin
                }
                if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(
                    myKeyboardVibrationIntensity
                )
            }
        }
        //</editor-fold>
        //<editor-fold desc="커서키보드">
        fragCursorUP.run {
            setPadding(changeDPtoPX(10).toInt())
            setOnTouchListener(
                RepeatListener(myKeyboardHolding.toLong(), normalInterval,
                    View.OnClickListener {
                        moveCursorToUp()
                        if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(
                            myKeyboardVibrationIntensity
                        )
                    })
            )
        }
        fragCursorDOWN.run {
            setPadding(changeDPtoPX(10).toInt())
            setOnTouchListener(
                RepeatListener(myKeyboardHolding.toLong(), normalInterval,
                    View.OnClickListener {
                        moveCursorToDown()
                        if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(
                            myKeyboardVibrationIntensity
                        )
                    })
            )
        }
        fragCursorLEFT.run {
            setPadding(changeDPtoPX(10).toInt())
            setOnTouchListener(
                RepeatListener(myKeyboardHolding.toLong(), normalInterval,
                    View.OnClickListener {
                        moveCursorToLeft()
                        if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(
                            myKeyboardVibrationIntensity
                        )
                    })
            )
        }
        fragCursorRIGHT.run {
            setPadding(changeDPtoPX(10).toInt())
            setOnTouchListener(
                RepeatListener(myKeyboardHolding.toLong(), normalInterval,
                    View.OnClickListener {
                        moveCursorToRight()
                        if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(
                            myKeyboardVibrationIntensity
                        )
                    })
            )
        }
        fragCursorFIRSTCHAR.run {
            setPadding(changeDPtoPX(10).toInt())
            setOnClickListener {
                moveCursorToFirst()
                if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(
                    myKeyboardVibrationIntensity
                )
            }
        }
        fragCursorLASTCHAR.run {
            setPadding(changeDPtoPX(10).toInt())
            setOnClickListener {
                moveCursorToLast()
                if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(
                    myKeyboardVibrationIntensity
                )
            }
        }
        fragCursorSelectAll.run {
            setOnClickListener {
                selectAll()
                if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(
                    myKeyboardVibrationIntensity
                )
            }
        }
        fragCursorSelectWord.run {
            setOnClickListener {
                val temp = currentInputConnection.getExtractedText(
                    ExtractedTextRequest(),
                    0
                ).selectionStart
                savedCursorPosition = temp
                selectWord()
                if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(
                    myKeyboardVibrationIntensity
                )
            }
        }
        fragCursorSelectCut.run {
            setOnClickListener {
                cutText()
                isSelectingActivated = false
                fragCursorSelectWord.text = "선택"
                if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(
                    myKeyboardVibrationIntensity
                )
            }
        }
        fragCursorSelectCopy.run {
            setOnClickListener {
                copyText()
                isSelectingActivated = false
                fragCursorSelectWord.text = "선택"
                if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(
                    myKeyboardVibrationIntensity
                )
            }
        }
        fragCursorSelectPaste.run {
            setOnClickListener {
                pasteText()
                isSelectingActivated = false
                fragCursorSelectWord.text = "선택"
                if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(
                    myKeyboardVibrationIntensity
                )
            }
        }
        fragCursorENTER.run {
            setPadding(changeDPtoPX(5).toInt())
            setOnClickListener {
                enterFunction()
                isSelectingActivated = false
                fragCursorSelectWord.text = "선택"
            }
        }
        fragCursorSpace.run {
            setOnTouchListener(
                RepeatListener(myKeyboardHolding.toLong(), normalInterval, View.OnClickListener {
                    isSelectingActivated = false
                    fragCursorSelectWord.text = "선택"
                    clearComposing()
                    hanguelQwerty.composeChar(' ', currentInputConnection)
                    if (myKeyboardAutoModeChange) { //스페이스바로 자동으로 모드 전환
                        when (modeValue) {
                            5, 6 -> {
                                modeValue = langSaveValue
                                setLayoutByMode(fontTypeIndex, modeValue)
                                btnSPECIAL.visibility = View.VISIBLE
                                langSaveValue = 1 //초기화
                            }
                        }
                    }
                    if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(
                        myKeyboardVibrationIntensity
                    )
                })
            )
        }
        fragCursorDEL.run {
            setPadding(changeDPtoPX(10).toInt())
            setOnTouchListener(
                RepeatListener(
                    myKeyboardHolding.toLong(),
                    normalInterval,
                    View.OnClickListener {
                        isSelectingActivated = false
                        fragCursorSelectWord.text = "선택"
                        delFunction(myKeyboardInputMethodKR)
                        if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(
                            myKeyboardVibrationIntensity
                        )
                    })
            )
        }
        fragCursorForeDEL.run {
            setPadding(changeDPtoPX(10).toInt())
            setOnTouchListener(
                RepeatListener(myKeyboardHolding.toLong(), normalInterval,
                    View.OnClickListener {
                        isSelectingActivated = false
                        fragCursorSelectWord.text = "선택"
                        foreDelFunction()
                        if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(
                            myKeyboardVibrationIntensity
                        )
                    })
            )
        }
        fragCursorKBD.run {
            setPadding(changeDPtoPX(5).toInt())
            setOnClickListener {
                currentFragment = 0
                buttonCursorFragment.setImageResource(R.drawable.ic_move)
                isSelectingActivated = false
                fragCursorSelectWord.text = "선택"
//                setLayoutByMode(fontTypeIndex, modeValue)
//                if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(
//                    myKeyboardVibrationIntensity
//                )
                keyboardView.keyboardViewFrameLayout.removeAllViews()
                when (myKeyboardInputMethodKR) {
                    1 -> keyboardView.keyboardViewFrameLayout.addView(fragmentKeyboardChunjiin)
                    2 -> keyboardView.keyboardViewFrameLayout.addView(fragmentKeyboardNaratgul)
                    3 -> keyboardView.keyboardViewFrameLayout.addView(fragmentKeyboardChunjiinA)
                    4 -> keyboardView.keyboardViewFrameLayout.addView(fragmentKeyboardDan)
                    else -> keyboardView.keyboardViewFrameLayout.addView(fragmentKeyboardQWERTY)
                }
                if (myKeyboardToggleNum || (!myKeyboardToggleNum && (modeValue == 5 || modeValue == 6))) {
                    keyboardViewFirstLine.visibility = View.VISIBLE
                    keyboardViewLayout.layoutParams.height =
                        changeDPtoPX(220 * myKeyboardHeight / 100).toInt() + myKeyboardBotMargin
                } else {
                    keyboardViewFirstLine.visibility = View.GONE
                    keyboardViewLayout.layoutParams.height =
                        changeDPtoPX(180 * myKeyboardHeight / 100).toInt() + myKeyboardBotMargin
                }
                if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(
                    myKeyboardVibrationIntensity
                )
            }
        }
        //</editor-fold>
    }

    override fun onFinishInputView(finishingInput: Boolean) {
        super.onFinishInputView(finishingInput)
        clearComposing()
    }

    private fun setLayoutNormally() {
        for (item in btnCharList) {
            item.layoutParams = paramNormal
        }
        val paramOfAL = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1.3f)
        btnA.layoutParams = paramOfAL
        btnL.layoutParams = paramOfAL
    }

    private fun setLayoutByMode(typeface: Typeface, modeValue: Int) {
        if (myKeyboardDivision) changeRightSize(myKeyboardRightSize)
        when (modeValue) {
            0 -> {
                for (index in btnCharList.indices) {
                    btnCharList[index].text = enChar[index].toUpperCase()
                    btnCharList[index].setTypeface(typeface, Typeface.BOLD)
                }
                btnSHIFT.setImageResource(shiftIcon_hyperactivated)
                btnSHIFT.setColorFilter(myFunctionFontColor)
                btnSHIFT.setPadding(changeDPtoPX(10).toInt())
                btnSPACE.text = getString(R.string.english)
                keyboardView.keyboardViewFrameLayout.removeAllViews()
                keyboardView.keyboardViewFrameLayout.addView(fragmentKeyboardQWERTY)
//                if ((myKeyboardToggleNum && currentInputEditorInfo.inputType !in inputTypeNumbers) || (!myKeyboardToggleNum && (modeValue == 5 || modeValue == 6))) {
//                    keyboardViewFirstLine.visibility = View.VISIBLE
//                    keyboardViewLayout.layoutParams.height =
//                        changeDPtoPX(220 * myKeyboardHeight / 100).toInt() + myKeyboardBotMargin
//                } else {
//                    keyboardViewFirstLine.visibility = View.GONE
//                    keyboardViewLayout.layoutParams.height =
//                        changeDPtoPX(180 * myKeyboardHeight / 100).toInt() + myKeyboardBotMargin
//                }
                adjustLayoutSize()
            }
            1 -> {
                for (index in btnCharList.indices) {
                    btnCharList[index].text = enChar[index].toUpperCase()
                    btnCharList[index].setTypeface(typeface, Typeface.NORMAL)
                }
                btnSHIFT.setImageResource(shiftIcon_activated)
                btnSHIFT.setColorFilter(myFunctionFontColor)
                btnSHIFT.setPadding(changeDPtoPX(10).toInt())
                btnSPACE.text = getString(R.string.english)
                keyboardView.keyboardViewFrameLayout.removeAllViews()
                keyboardView.keyboardViewFrameLayout.addView(fragmentKeyboardQWERTY)
                btnSPECIAL.visibility = View.VISIBLE
                adjustLayoutSize()
            }
            2 -> {
                for (index in btnCharList.indices) {
                    btnCharList[index].text = enChar[index].toLowerCase()
                    btnCharList[index].setTypeface(typeface, Typeface.NORMAL)
                }
                btnSHIFT.setImageResource(shiftIcon_deactivated)
                btnSHIFT.setColorFilter(myFunctionFontColor)
                btnSHIFT.setPadding(changeDPtoPX(10).toInt())
                btnSPACE.text = getString(R.string.english)
                keyboardView.keyboardViewFrameLayout.removeAllViews()
                keyboardView.keyboardViewFrameLayout.addView(fragmentKeyboardQWERTY)
                btnSPECIAL.visibility = View.VISIBLE
                adjustLayoutSize()
            }
            3 -> {
                for (index in btnCharList.indices) {
                    btnCharList[index].text = krChar[index]
                    btnCharList[index].setTypeface(typeface, Typeface.NORMAL)
                }
                btnSHIFT.setImageResource(shiftIcon_deactivated)
                btnSHIFT.setColorFilter(myFunctionFontColor)
                btnSHIFT.setPadding(changeDPtoPX(10).toInt())
                btnSPACE.text = getString(R.string.korean)
                keyboardView.keyboardViewFrameLayout.removeAllViews()
                keyboardView.keyboardViewFrameLayout.addView(
                    when (myKeyboardInputMethodKR) {
                        0 -> fragmentKeyboardQWERTY
                        1 -> fragmentKeyboardChunjiin
                        2 -> fragmentKeyboardNaratgul
                        3 -> fragmentKeyboardChunjiinA
                        4 -> fragmentKeyboardDan
                        else -> fragmentKeyboardQWERTY
                    }
                )
                adjustLayoutSize()
            }
            4 -> {
                for (index in btnCharList.indices) {
                    btnCharList[index].text = krChar[index]
                    btnCharList[index].setTypeface(typeface, Typeface.NORMAL)
                }
                for (index in btnShiftList.indices) btnShiftList[index].text =
                    krShiftChar[index + 7]
                btnSHIFT.setImageResource(shiftIcon_activated)
                btnSHIFT.setColorFilter(myFunctionFontColor)
                btnSHIFT.setPadding(changeDPtoPX(10).toInt())
                btnSPACE.text = getString(R.string.korean)
                adjustLayoutSize()
            }
            5 -> {
                for (index in btnCharList.indices) {
                    btnCharList[index].text = specialChar1[index]
                    btnCharList[index].setTypeface(typeface, Typeface.NORMAL)
                }
                btnSPECIAL.visibility = View.GONE
                btnSHIFT.setImageResource(shiftIcon_deactivated)
                btnSHIFT.setColorFilter(myFunctionFontColor)
                btnSHIFT.setPadding(changeDPtoPX(10).toInt())
                btnSPACE.text = getString(R.string.special1)
                keyboardViewFirstLine.visibility = View.VISIBLE
                keyboardView.keyboardViewFrameLayout.removeAllViews()
                keyboardView.keyboardViewFrameLayout.addView(fragmentKeyboardQWERTY)
                keyboardViewLayout.layoutParams.height =
                    changeDPtoPX(220 * myKeyboardHeight / 100).toInt() + myKeyboardBotMargin
            }
            6 -> {
                for (index in btnCharList.indices) {
                    btnCharList[index].text = specialChar2[index]
                    btnCharList[index].setTypeface(typeface, Typeface.NORMAL)
                }
                btnSPECIAL.visibility = View.GONE
                btnSHIFT.setImageResource(shiftIcon_activated)
                btnSHIFT.setColorFilter(myFunctionFontColor)
                btnSHIFT.setPadding(changeDPtoPX(10).toInt())
                btnSPACE.text = getString(R.string.special2)
                keyboardViewFirstLine.visibility = View.VISIBLE
                keyboardView.keyboardViewFrameLayout.removeAllViews()
                keyboardView.keyboardViewFrameLayout.addView(fragmentKeyboardQWERTY)
                keyboardViewLayout.layoutParams.height =
                    changeDPtoPX(220 * myKeyboardHeight / 100).toInt() + myKeyboardBotMargin
            }
        }
        keyboardBackgroundImage.layoutParams.height = keyboardViewLayout.layoutParams.height
    }

    private fun enterFunction() {
        if (currentInputConnection == null) return
        clearComposing()
        currentInputConnection.finishComposingText()
        val eventTime = SystemClock.uptimeMillis()
        when (currentInputEditorInfo.imeOptions) {
            EditorInfo.IME_ACTION_SEARCH + EditorInfo.IME_FLAG_NAVIGATE_NEXT -> {
                currentInputConnection.performEditorAction(EditorInfo.IME_ACTION_SEARCH)
            }
            else -> {
                currentInputConnection.sendKeyEvent(
                    KeyEvent(
                        eventTime,
                        eventTime,
                        KeyEvent.ACTION_DOWN,
                        KeyEvent.KEYCODE_ENTER,
                        0,
                        0,
                        0,
                        0,
                        KeyEvent.FLAG_SOFT_KEYBOARD
                    )
                )
                currentInputConnection.sendKeyEvent(
                    KeyEvent(
                        SystemClock.uptimeMillis(),
                        eventTime,
                        KeyEvent.ACTION_UP,
                        KeyEvent.KEYCODE_ENTER,
                        0,
                        0,
                        0,
                        0,
                        KeyEvent.FLAG_SOFT_KEYBOARD
                    )
                )
            }
        }
        if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(
            myKeyboardVibrationIntensity
        )
    }

    private fun delFunction(inputMethodKR: Int) {
        if (currentInputConnection == null) return
        delBoolean = true
        val selectedText = currentInputConnection.getSelectedText(GET_TEXT_WITH_STYLES)
        val cursorPosition = currentInputConnection?.getSelectedText(GET_TEXT_WITH_STYLES)?.get(0)
        if (TextUtils.isEmpty(selectedText)) {
            when (inputMethodKR) {
                0 -> HanguelQWERTY.delete(cursorPosition, currentInputConnection)
                1 -> HanguelChunjiin.delete(cursorPosition, currentInputConnection)
                2 -> Hang
                3 -> HanguelChunjiin.delete(cursorPosition, currentInputConnection)
                4 -> HanguelDanmoum.delete(cursorPosition, currentInputConnection, inputTime = System.currentTimeMillis())
                else -> HanguelQWERTY.delete(cursorPosition, currentInputConnection)
            }
        } else {
            clearComposing()
            currentInputConnection.finishComposingText()
            currentInputConnection.commitText("", 1)
        }
        delBoolean = false
    }

    private fun foreDelFunction() {
        if (currentInputConnection == null) return
        delBoolean = true
        val selectedText = currentInputConnection.getSelectedText(GET_TEXT_WITH_STYLES)
        if (TextUtils.isEmpty(selectedText)) {
            clearComposing()
            currentInputConnection.deleteSurroundingText(0, 1)
        } else {
            clearComposing()
            currentInputConnection.finishComposingText()
            currentInputConnection.commitText("", 1)
        }
        delBoolean = false
    }

    private fun vibrateByButton() {
        val vibrator: Vibrator = getSystemService(VIBRATOR_SERVICE) as Vibrator
        if (vibrator.hasVibrator()) {
            if (SDK_INT >= Build.VERSION_CODES.O) {
                vibrator.vibrate(VibrationEffect.createOneShot((myKeyboardVibrationIntensity + 25).toLong(), 25 + myKeyboardVibrationIntensity))
            } else {
                vibrator.vibrate((myKeyboardVibrationIntensity + 25).toLong())
            }
        }
    }

    private fun changeDPtoPX(dp: Int): Float {
        return dp * (Resources.getSystem().displayMetrics.density)
    }

    fun startApp(uri: String) {
        val intent =
            applicationContext.packageManager.getLaunchIntentForPackage(uri)
        if (intent != null) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        } else {
            return
        }
    }

    @RequiresApi(Build.VERSION_CODES.R)
    fun selectWord() {
        isSelectingActivated = !isSelectingActivated
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun selectAll() {
        if (currentInputConnection == null) return
        if (currentInputConnection.requestCursorUpdates(CURSOR_UPDATE_IMMEDIATE)) {
            val wholeText =
                currentInputConnection.getExtractedText(ExtractedTextRequest(), 0).text.length
            clearComposing()
            currentInputConnection.setSelection(0, wholeText)
            savedCursorPosition = -1
        }
    }

    fun moveCursorToUp() {
        if (currentInputConnection == null) return
        val currentCursorPositionStart =
            currentInputConnection.getExtractedText(ExtractedTextRequest(), 0).selectionStart
        val currentCursorPositionEnd =
            currentInputConnection.getExtractedText(ExtractedTextRequest(), 0).selectionEnd
        clearComposing()
        if (isSelectingActivated) {
            currentInputConnection.sendKeyEvent(
                KeyEvent(
                    KeyEvent.ACTION_DOWN,
                    KeyEvent.KEYCODE_SHIFT_LEFT
                )
            )
            if (currentCursorPositionStart != 0 || currentCursorPositionStart >= savedCursorPosition) {
                currentInputConnection.sendKeyEvent(
                    KeyEvent(
                        KeyEvent.ACTION_DOWN,
                        KeyEvent.KEYCODE_DPAD_UP
                    )
                )
                currentInputConnection.sendKeyEvent(
                    KeyEvent(
                        KeyEvent.ACTION_UP,
                        KeyEvent.KEYCODE_DPAD_UP
                    )
                )
            } else {
                currentInputConnection.setSelection(
                    currentCursorPositionStart,
                    currentCursorPositionEnd
                )
            }
            currentInputConnection.sendKeyEvent(
                KeyEvent(
                    KeyEvent.ACTION_UP,
                    KeyEvent.KEYCODE_SHIFT_LEFT
                )
            )
        } else {
            if (currentCursorPositionStart != 0) {
                currentInputConnection.sendKeyEvent(
                    KeyEvent(
                        KeyEvent.ACTION_DOWN,
                        KeyEvent.KEYCODE_DPAD_UP
                    )
                )
                currentInputConnection.sendKeyEvent(
                    KeyEvent(
                        KeyEvent.ACTION_UP,
                        KeyEvent.KEYCODE_DPAD_UP
                    )
                )
            } else {
                currentInputConnection.setSelection(0, 0)
            }
        }
    }

    fun moveCursorToDown() {
        if (currentInputConnection == null) return
        val currentCursorPositionStart =
            currentInputConnection.getExtractedText(ExtractedTextRequest(), 0).selectionStart
        val currentCursorPositionEnd =
            currentInputConnection.getExtractedText(ExtractedTextRequest(), 0).selectionEnd
        val wholeText =
            currentInputConnection.getExtractedText(ExtractedTextRequest(), 0).text.length
        clearComposing()
        if (isSelectingActivated) {
            currentInputConnection.sendKeyEvent(
                KeyEvent(
                    KeyEvent.ACTION_DOWN,
                    KeyEvent.KEYCODE_SHIFT_LEFT
                )
            )
            if (currentCursorPositionStart != 0 || currentCursorPositionStart >= savedCursorPosition) {
                currentInputConnection.sendKeyEvent(
                    KeyEvent(
                        KeyEvent.ACTION_DOWN,
                        KeyEvent.KEYCODE_DPAD_DOWN
                    )
                )
                currentInputConnection.sendKeyEvent(
                    KeyEvent(
                        KeyEvent.ACTION_UP,
                        KeyEvent.KEYCODE_DPAD_DOWN
                    )
                )
            } else {
                currentInputConnection.setSelection(
                    currentCursorPositionStart,
                    currentCursorPositionEnd
                )
            }
            currentInputConnection.sendKeyEvent(
                KeyEvent(
                    KeyEvent.ACTION_UP,
                    KeyEvent.KEYCODE_SHIFT_LEFT
                )
            )
        } else {
            if (currentCursorPositionStart != wholeText && savedCursorPosition != -1) {
                currentInputConnection.sendKeyEvent(
                    KeyEvent(
                        KeyEvent.ACTION_DOWN,
                        KeyEvent.KEYCODE_DPAD_DOWN
                    )
                )
                currentInputConnection.sendKeyEvent(
                    KeyEvent(
                        KeyEvent.ACTION_UP,
                        KeyEvent.KEYCODE_DPAD_DOWN
                    )
                )
            } else {
                currentInputConnection.setSelection(wholeText, wholeText)
            }
        }
    }

    fun moveCursorToLeft() {
        if (currentInputConnection == null) return
        val currentCursorPositionStart =
            currentInputConnection.getExtractedText(ExtractedTextRequest(), 0).selectionStart
        val currentCursorPositionEnd =
            currentInputConnection.getExtractedText(ExtractedTextRequest(), 0).selectionEnd
        clearComposing()
        if (isSelectingActivated) {
            currentInputConnection.sendKeyEvent(
                KeyEvent(
                    KeyEvent.ACTION_DOWN,
                    KeyEvent.KEYCODE_SHIFT_LEFT
                )
            )
            if (currentCursorPositionStart != 0 || currentCursorPositionStart >= savedCursorPosition) {
                currentInputConnection.sendKeyEvent(
                    KeyEvent(
                        KeyEvent.ACTION_DOWN,
                        KeyEvent.KEYCODE_DPAD_LEFT
                    )
                )
                currentInputConnection.sendKeyEvent(
                    KeyEvent(
                        KeyEvent.ACTION_UP,
                        KeyEvent.KEYCODE_DPAD_LEFT
                    )
                )
            } else {
                currentInputConnection.setSelection(
                    currentCursorPositionStart,
                    currentCursorPositionEnd
                )
            }
            currentInputConnection.sendKeyEvent(
                KeyEvent(
                    KeyEvent.ACTION_UP,
                    KeyEvent.KEYCODE_SHIFT_LEFT
                )
            )
        } else {
            if (currentCursorPositionStart != 0) {
                currentInputConnection.sendKeyEvent(
                    KeyEvent(
                        KeyEvent.ACTION_DOWN,
                        KeyEvent.KEYCODE_DPAD_LEFT
                    )
                )
                currentInputConnection.sendKeyEvent(
                    KeyEvent(
                        KeyEvent.ACTION_UP,
                        KeyEvent.KEYCODE_DPAD_LEFT
                    )
                )
            } else {
                currentInputConnection.setSelection(0, 0)
            }
        }
    }

    fun moveCursorToRight() {
        if (currentInputConnection == null) return
        val currentCursorPositionStart =
            currentInputConnection.getExtractedText(ExtractedTextRequest(), 0).selectionStart
        val currentCursorPositionEnd =
            currentInputConnection.getExtractedText(ExtractedTextRequest(), 0).selectionEnd
        val wholeText =
            currentInputConnection.getExtractedText(ExtractedTextRequest(), 0).text.length
        clearComposing()
        if (isSelectingActivated) {
            currentInputConnection.sendKeyEvent(
                KeyEvent(
                    KeyEvent.ACTION_DOWN,
                    KeyEvent.KEYCODE_SHIFT_LEFT
                )
            )
            if (currentCursorPositionEnd != wholeText && savedCursorPosition <= currentCursorPositionStart) {
                currentInputConnection.sendKeyEvent(
                    KeyEvent(
                        KeyEvent.ACTION_DOWN,
                        KeyEvent.KEYCODE_DPAD_RIGHT
                    )
                )
                currentInputConnection.sendKeyEvent(
                    KeyEvent(
                        KeyEvent.ACTION_UP,
                        KeyEvent.KEYCODE_DPAD_RIGHT
                    )
                )
            } else if (savedCursorPosition > currentCursorPositionStart) {
                currentInputConnection.setSelection(
                    currentCursorPositionStart + 1,
                    savedCursorPosition
                )
            } else {
                currentInputConnection.setSelection(
                    currentCursorPositionStart,
                    currentCursorPositionEnd
                )
            }
            currentInputConnection.sendKeyEvent(
                KeyEvent(
                    KeyEvent.ACTION_UP,
                    KeyEvent.KEYCODE_SHIFT_LEFT
                )
            )
        } else {
            if (currentCursorPositionStart != wholeText) {
                currentInputConnection.sendKeyEvent(
                    KeyEvent(
                        KeyEvent.ACTION_DOWN,
                        KeyEvent.KEYCODE_DPAD_RIGHT
                    )
                )
                currentInputConnection.sendKeyEvent(
                    KeyEvent(
                        KeyEvent.ACTION_UP,
                        KeyEvent.KEYCODE_DPAD_RIGHT
                    )
                )
            } else {
                currentInputConnection.setSelection(wholeText, wholeText)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun moveCursorToFirst() {
        if (currentInputConnection == null) return
        if (currentInputConnection.requestCursorUpdates(CURSOR_UPDATE_IMMEDIATE)) {
            clearComposing()
            if (isSelectingActivated) {
                currentInputConnection.sendKeyEvent(
                    KeyEvent(
                        KeyEvent.ACTION_DOWN,
                        KeyEvent.KEYCODE_SHIFT_LEFT
                    )
                )
                currentInputConnection.setSelection(0, savedCursorPosition)
                currentInputConnection.sendKeyEvent(
                    KeyEvent(
                        KeyEvent.ACTION_UP,
                        KeyEvent.KEYCODE_SHIFT_LEFT
                    )
                )
            } else {
                currentInputConnection.setSelection(0, 0)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun moveCursorToLast() {
        val wholeText =
            currentInputConnection.getExtractedText(ExtractedTextRequest(), 0).text.length
        if (currentInputConnection == null) return
        if (currentInputConnection.requestCursorUpdates(CURSOR_UPDATE_IMMEDIATE)) {
            clearComposing()
            if (isSelectingActivated) {
                currentInputConnection.sendKeyEvent(
                    KeyEvent(
                        KeyEvent.ACTION_DOWN,
                        KeyEvent.KEYCODE_SHIFT_LEFT
                    )
                )
                currentInputConnection.setSelection(savedCursorPosition, wholeText)
                currentInputConnection.sendKeyEvent(
                    KeyEvent(
                        KeyEvent.ACTION_UP,
                        KeyEvent.KEYCODE_SHIFT_LEFT
                    )
                )
            } else {
                currentInputConnection.setSelection(wholeText, wholeText)
            }
        }
    }

    /**
     * When user longclicked special button or enter button,
     * current keyboard fragment changes to boilerplate texts frag or cursor frag
     *  **/
    fun addFunction(int: Int) {
        when (int) {
            1 -> {
                vm.changeMode(7)
            }
            2 -> {
                vm.changeMode(8)
            }
            else -> {
            }
        }
    }

    fun inputChar(button: View) {
        if (currentInputConnection == null) return
        val cursorCS = currentInputConnection.getSelectedText(GET_TEXT_WITH_STYLES)
        if (cursorCS.isNotEmpty()) {
            clearComposing() // 선택 중인 글자가 있으면 초기화
        }
        when (vm.mode.value) {
            3, 4 -> {
                when (vm.currentKR.value) {
                    KeyboardUtil.QWERTY -> {
                        val (c1, c2) = HanguelQWERTY.composeChar((button as Button).text!!.single())
                        if (c1 != null) {
                            currentInputConnection.commitText(c1, 1)
                            if (c2 != null) currentInputConnection.setComposingText(c2, 1)
                        } else {
                            if (c2 != null) currentInputConnection.setComposingText(c2, 1)
                        }
                    }
                    KeyboardUtil.CHUN -> {
                        val (c1, c2) = HanguelChunjiin.composeChar((button as Button).text!![0].toChar())
                        if (c1 != null) {
                            currentInputConnection.commitText(c1, 1)
                            if (c2 != null) currentInputConnection.setComposingText(c2, 1)
                        } else {
                            if (c2 != null) currentInputConnection.setComposingText(c2, 1)
                        }
                    }
                }
            }
            else -> {
                currentInputConnection.commitText((button as Button).text.toString(), 1)
            }
        }
        if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton()
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun copyText() {
        if (currentInputConnection == null) return
        isSelectingActivated = false
        if (currentInputConnection.requestCursorUpdates(CURSOR_UPDATE_IMMEDIATE)) {
            clearComposing()
            if (currentInputConnection.getSelectedText(GET_TEXT_WITH_STYLES) == null) Toast.makeText(
                applicationContext,
                "선택된 문구가 없습니다.",
                Toast.LENGTH_SHORT
            ).show()
            else currentInputConnection.performContextMenuAction(android.R.id.copy)
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun cutText() {
        if (currentInputConnection == null) return
        isSelectingActivated = false
        if (currentInputConnection.requestCursorUpdates(CURSOR_UPDATE_IMMEDIATE)) {
            clearComposing()
            if (currentInputConnection.getSelectedText(GET_TEXT_WITH_STYLES) == null) Toast.makeText(
                applicationContext,
                "선택된 문구가 없습니다.",
                Toast.LENGTH_SHORT
            ).show()
            else currentInputConnection.performContextMenuAction(android.R.id.cut)
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun pasteText() {
        if (currentInputConnection == null) return
        isSelectingActivated = false
        if (currentInputConnection.requestCursorUpdates(CURSOR_UPDATE_IMMEDIATE)) {
            clearComposing()
            currentInputConnection.performContextMenuAction(android.R.id.paste)
        }
    }

    fun clearComposing() {
        currentInputConnection?.finishComposingText()

        HanguelQWERTY.initChar()
        HanguelQWERTY.initState()
        HanguelQWERTY.initResult()

        HanguelChunjiin.initChar()
        HanguelChunjiin.initState()
        HanguelChunjiin.initResult()

        HanguelNARATGUL.initChar()
        HanguelNARATGUL.initState()
        HanguelNARATGUL.initResult()

        HanguelDanmoum.initChar()
        HanguelDanmoum.initState()
        HanguelDanmoum.initResult()
    }
}