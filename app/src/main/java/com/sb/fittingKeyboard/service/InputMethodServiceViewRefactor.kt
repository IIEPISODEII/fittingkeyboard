package com.sb.fittingKeyboard.service

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.inputmethodservice.InputMethodService
import android.os.Build
import android.os.Build.VERSION.SDK_INT
import android.os.SystemClock
import android.os.VibrationEffect
import android.os.Vibrator
import android.text.TextUtils
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.ExtractedTextRequest
import android.view.inputmethod.InputConnection.CURSOR_UPDATE_IMMEDIATE
import android.view.inputmethod.InputConnection.GET_TEXT_WITH_STYLES
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.*
import com.sb.fittingKeyboard.BR
import com.sb.fittingKeyboard.R
import com.sb.fittingKeyboard.databinding.*
import com.sb.fittingKeyboard.keyboardSettings.SetTheme
import com.sb.fittingKeyboard.koreanAutomata.HanguelChunjiin
import com.sb.fittingKeyboard.koreanAutomata.HanguelDanmoum
import com.sb.fittingKeyboard.koreanAutomata.HanguelNARATGUL
import com.sb.fittingKeyboard.koreanAutomata.HanguelQWERTY
import com.sb.fittingKeyboard.service.util.KeyboardUtil
import com.sb.fittingKeyboard.service.util.KeyboardUtil.Companion.KEYBOARD_FONT_SIZE
import com.sb.fittingKeyboard.service.util.KeyboardUtil.Companion.KEYBOARD_SETTING
import com.sb.fittingKeyboard.service.viewmodel.SharedKBViewModel


@Suppress("DEPRECATION")
class InputMethodServiceViewRefactor : InputMethodService(), LifecycleOwner {
    lateinit var kbBinding: KeyLayoutNormalBinding
    lateinit var qwertyEnNormalBinding: FragmentKeyboardQwertyEnNormalBinding
    lateinit var qwertyKrNormalBinding: FragmentKeyboardQwertyKrNormalBinding
    lateinit var chunjiinBinding: FragmentKeyboardChunjiinBasicBinding
    lateinit var chunAKBBinding: FragmentKeyboardChunjiinAmbiBinding
    lateinit var naratguelBinding: FragmentKeyboardNaratgulBasicBinding
    lateinit var danmoumBinding: FragmentKeyboardDanmoumBinding
    lateinit var specialKBBinding: FragmentKeyboardQwertySpecialBinding
    lateinit var numberBinding: FragmentKeyboardNumberBinding
    lateinit var boilerPlateBinding: FragmentBpBinding
    lateinit var cursorBinding: FragmentCursorBinding
    private lateinit var vm: SharedKBViewModel
    private lateinit var kbFrame: FrameLayout
    private lateinit var kbView: View
    private lateinit var kbLayout: LinearLayout
    private lateinit var kbLayoutBottomMargin: View
    private lateinit var kbCharLeftSide: FrameLayout
    private lateinit var kbCharRightSide: FrameLayout
    private lateinit var kbNumLeftSide: Button
    private lateinit var kbNumRightSide: Button
    private val mLifecycle = LifecycleRegistry(this)
    override fun getLifecycle(): Lifecycle = mLifecycle

    //<editor-fold desc="변수 선언">
    private val inputTypeNumbers = arrayOf(2, 4098, 8194, 18, 3, 4, 14, 24, 24578, 16387)

    private lateinit var theme: SetTheme
    private var mKeyboardHolding: Long = 300
    private var myKeyboardVibration: Boolean = true
    private var myKeyboardVibrationIntensity: Int = 50
    private var myKeyboardTheme: Int = 0
    private val normalInterval: Long = 37

    private var savedCursorPosition = 0

    //</editor-fold>
    private lateinit var prefs: SharedPreferences
    private var _fontSize = MutableLiveData<Int>(15)
    val fontSize: LiveData<Int> get() = _fontSize
    private var _layoutSize = MutableLiveData<Int>()
    val layoutSize: LiveData<Int> get() = _layoutSize

    override fun onCreate() {
        super.onCreate()
        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(application)
            .create(SharedKBViewModel::class.java)
        kbView = layoutInflater.inflate(R.layout.key_layout_normal, null)
        kbBinding = DataBindingUtil.bind(kbView)!!
        kbBinding.setVariable(BR.kbservice, this)
        kbBinding.lifecycleOwner = this
        kbBinding.kbviewmodel = vm
        mLifecycle.handleLifecycleEvent(Lifecycle.Event.ON_CREATE)
        mLifecycle.handleLifecycleEvent(Lifecycle.Event.ON_START)
        prefs = applicationContext.getSharedPreferences(KEYBOARD_SETTING, MODE_PRIVATE)

        val qwertyEnNormalKBView =
            layoutInflater.inflate(R.layout.fragment_keyboard_qwerty_en_normal, null)
        val qwertyKrNormalKBView =
            layoutInflater.inflate(R.layout.fragment_keyboard_qwerty_kr_normal, null)
        val qwertySpecialKBView =
            layoutInflater.inflate(R.layout.fragment_keyboard_qwerty_special, null)
        val boilerPlateKBView = layoutInflater.inflate(R.layout.fragment_bp, null)
        val cursorKBView = layoutInflater.inflate(R.layout.fragment_cursor, null)
        val numberKBView = layoutInflater.inflate(R.layout.fragment_keyboard_number, null)
        val chunjiinKBView = layoutInflater.inflate(R.layout.fragment_keyboard_chunjiin_basic, null)
        val chunjiinKBViewAmbi =
            layoutInflater.inflate(R.layout.fragment_keyboard_chunjiin_ambi, null)
        val danmoKBView = layoutInflater.inflate(R.layout.fragment_keyboard_danmoum, null)
        val naratguelKBView =
            layoutInflater.inflate(R.layout.fragment_keyboard_naratgul_basic, null)
        qwertyEnNormalBinding = DataBindingUtil.bind(qwertyEnNormalKBView)!!
        qwertyEnNormalBinding.setVariable(BR.kbservice, this)
        qwertyEnNormalBinding.lifecycleOwner = this
        qwertyEnNormalBinding.kbviewmodel = vm
        qwertyKrNormalBinding = DataBindingUtil.bind(qwertyKrNormalKBView)!!
        qwertyKrNormalBinding.setVariable(BR.kbservice, this)
        qwertyKrNormalBinding.lifecycleOwner = this
        qwertyKrNormalBinding.kbviewmodel = vm
        chunjiinBinding = DataBindingUtil.bind(chunjiinKBView)!!
        chunjiinBinding.setVariable(BR.kbservice, this)
        chunjiinBinding.lifecycleOwner = this
        chunjiinBinding.kbviewmodel = vm
        naratguelBinding = DataBindingUtil.bind(naratguelKBView)!!
        naratguelBinding.setVariable(BR.kbservice, this)
        naratguelBinding.lifecycleOwner = this
        naratguelBinding.kbviewmodel = vm
        danmoumBinding = DataBindingUtil.bind(danmoKBView)!!
        danmoumBinding.setVariable(BR.kbservice, this)
        danmoumBinding.lifecycleOwner = this
        danmoumBinding.kbviewmodel = vm
        specialKBBinding = DataBindingUtil.bind(qwertySpecialKBView)!!
        specialKBBinding.setVariable(BR.kbservice, this)
        specialKBBinding.lifecycleOwner = this
        specialKBBinding.kbviewmodel = vm
        chunAKBBinding = DataBindingUtil.bind(chunjiinKBViewAmbi)!!
        chunAKBBinding.setVariable(BR.kbservice, this)
        chunAKBBinding.lifecycleOwner = this
        chunAKBBinding.kbviewmodel = vm
        boilerPlateBinding = DataBindingUtil.bind(boilerPlateKBView)!!
        boilerPlateBinding.setVariable(BR.kbservice, this)
        boilerPlateBinding.lifecycleOwner = this
        boilerPlateBinding.kbviewmodel = vm
        cursorBinding = DataBindingUtil.bind(cursorKBView)!!
        cursorBinding.setVariable(BR.kbservice, this)
        cursorBinding.lifecycleOwner = this
        cursorBinding.kbviewmodel = vm
        numberBinding = DataBindingUtil.bind(numberKBView)!!
        numberBinding.setVariable(BR.kbservice, this)
        numberBinding.lifecycleOwner = this
        numberBinding.kbviewmodel = vm

        kbNumLeftSide = kbView.findViewById(R.id.keyboardNumberLeftMargin)
        kbNumRightSide = kbView.findViewById(R.id.keyboardNumberRightMargin)
        kbCharLeftSide = kbView.findViewById(R.id.keyboardLeftMargin)
        kbCharRightSide = kbView.findViewById(R.id.keyboardRightMargin)
        kbLayout = kbView.findViewById(R.id.keyboardLayout)
        kbFrame = kbView.findViewById(R.id.keyboardViewFrameLayout)
        kbLayoutBottomMargin = kbView.findViewById(R.id.keyboardBotMargin)

        var currentKRView = qwertyKrNormalKBView
        vm.observeKBIME.observe(this, {
            currentKRView =
                when (it) {
                    KeyboardUtil.QWERTY -> {
                        qwertyKrNormalKBView
                    }
                    KeyboardUtil.CHUN -> {
                        chunjiinKBView
                    }
                    KeyboardUtil.CHUN_AMBI -> chunjiinKBViewAmbi
                    KeyboardUtil.NARAT -> naratguelKBView
                    KeyboardUtil.DAN -> danmoKBView
                    else -> qwertyKrNormalKBView
                }
            if (vm.mode.value == 3) {
                kbFrame.removeAllViews()
                kbFrame.addView(currentKRView)
            }
        })
        vm.mode.observe(this, {
            kbFrame.removeAllViews()
            when (it) {
                0, 1, 2 -> kbFrame.addView(qwertyEnNormalKBView)
                3, 4 -> kbFrame.addView(currentKRView)
                5, 6 -> kbFrame.addView(qwertySpecialKBView)
                7 -> kbFrame.addView(boilerPlateKBView)
                8 -> kbFrame.addView(cursorKBView)
                9 -> kbFrame.addView(numberKBView)
            }
        })
        vm.observeNumberVisibility.observe(this, {})
        vm.observeKBBottomMargin.observe(this, {
            val lP = kbLayoutBottomMargin.layoutParams
            lP.height = it * (resources.displayMetrics.density).toInt()
            kbLayoutBottomMargin.layoutParams = lP
        })
        vm.observeKBFontSize.observe(
            this,
            {
                _fontSize.value = (prefs.intLiveData(
                    KEYBOARD_FONT_SIZE,
                    16
                ).value!! * (resources.displayMetrics.scaledDensity)).toInt()
            })
        vm.observeKBFontType.observe(this, {})
        vm.observeKBDivision.observe(this, {})
        vm.observeKBMoSize.observe(this, {})
        vm.observeRightSize.observe(this, {})
        vm.observeKBHoldingTime.observe(this, {
            mKeyboardHolding = it.toLong() + 100
            for (rListener in repeatListenerChars) {
                rListener.changeInitialInterval(mKeyboardHolding)
            }
            for (rListener in repeatListenerSpecials) {
                rListener.changeInitialInterval(mKeyboardHolding)
            }
            for (rListener in repeatListenerDels) {
                rListener.changeInitialInterval(mKeyboardHolding)
            }
            rListenerCursorLeft.changeInitialInterval(mKeyboardHolding)
            rListenerCursorRight.changeInitialInterval(mKeyboardHolding)
            rListenerCursorUp.changeInitialInterval(mKeyboardHolding)
            rListenerCursorDown.changeInitialInterval(mKeyboardHolding)
            rListenerCursorFirst.changeInitialInterval(mKeyboardHolding)
            rListenerCursorLast.changeInitialInterval(mKeyboardHolding)
            rListenerCursorForeDel.changeInitialInterval(mKeyboardHolding)
        })
        vm.observeKBVibrationUse.observe(this, { myKeyboardVibration = it })
        vm.observeKBVibrationIntensity.observe(this, { myKeyboardVibrationIntensity = it })
        vm.observeKBTheme.observe(this, { myKeyboardTheme = it })
        vm.observeKBFontColor.observe(this, {})
        vm.observeKBFunctionFontColor.observe(this, {})
        vm.observeKBLeftSideMargin.observe(this, {
            val lPNum = kbNumLeftSide.layoutParams
            val lPChar = kbCharLeftSide.layoutParams
            lPNum.width = it * 3 * (resources.displayMetrics.density).toInt()
            lPChar.width = it * 3 * (resources.displayMetrics.density).toInt()
            kbNumLeftSide.layoutParams = lPNum
            kbCharLeftSide.layoutParams = lPChar
        })
        vm.observeKBRightSideMargin.observe(this, {
            val lPNum = kbNumRightSide.layoutParams
            val lPChar = kbCharRightSide.layoutParams
            lPNum.width = it * 3 * (resources.displayMetrics.density).toInt()
            lPChar.width = it * 3 * (resources.displayMetrics.density).toInt()
            kbNumRightSide.layoutParams = lPNum
            kbCharRightSide.layoutParams = lPChar
        })
        vm.observeKBToolBarVisibility.observe(this, {})
        vm.observeKBHeight.observe(this, {})
        vm.observeHeight.observe(this, {
            val lP = kbLayout.layoutParams
            lP.height = it.toInt() * resources.displayMetrics.density.toInt()
            kbLayout.layoutParams = lP

            val bgImg = kbView.findViewById<ImageView>(R.id.keyboardBackgroundImage)
            val bgImglP = bgImg.layoutParams
            bgImglP.height = it.toInt() * resources.displayMetrics.density.toInt()
            bgImg.layoutParams = bgImglP
        })
        vm.observeKBEnterKeyHolding.observe(this, {})
        vm.observeKBSpecialKeyHolding.observe(this, {})
        vm.observeKBAutoCapitalization.observe(this, {})
        vm.isSelecting.observe(this, {})
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
        if (candidatesStart != -1) {
            if ((candidatesStart != oldSelStart && candidatesEnd != newSelStart)) {
                clearComposing()
            }
        } else if (newSelStart == 0 && newSelEnd == 0 && candidatesStart == -1 && candidatesEnd == -1) {
            clearComposing()
        }
        /** TextField의 첫글자로 돌아갔을 때 대문자로 수정 **/
        if (vm.observeKBAutoCapitalization.value!!
            && currentInputConnection.requestCursorUpdates(CURSOR_UPDATE_IMMEDIATE)
            && vm.mode.value == 2
        ) {
            var autoCapitalCondition: Boolean = false
            for (i in 0..3) {
                if (currentInputConnection
                        .getTextBeforeCursor(
                            i + 2
                            , GET_TEXT_WITH_STYLES)
                        .toString()
                        .replace(
                            "\\s"
                            .toRegex()
                            , "") == "."
                ) {
                    autoCapitalCondition = true
                }
            }
            if ((newSelStart == 0 && newSelEnd == 0 && candidatesStart == -1 && candidatesEnd == -1) || autoCapitalCondition) {
                vm.changeMode(1)
            }
        }
    }

    @SuppressLint("ClickableViewAccessibility", "NewApi")
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onStartInputView(info: EditorInfo?, restarting: Boolean) {
        super.onStartInputView(info, restarting)
        mLifecycle.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)

        if (currentInputEditorInfo.inputType in inputTypeNumbers) {
            vm.changeMode(9)
        }
    }

    override fun onFinishInputView(finishingInput: Boolean) {
        super.onFinishInputView(finishingInput)
        clearComposing()
        vm.switchSelectingMode(false)
    }

    override fun onDestroy() {
        super.onDestroy()
        mLifecycle.markState(Lifecycle.State.DESTROYED)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    var rListenerCursorFirst = RepeatListener(mKeyboardHolding, normalInterval) {
        moveCursorToFirst()
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    var rListenerCursorLast = RepeatListener(mKeyboardHolding, normalInterval) {
        moveCursorToLast()
    }
    var rListenerCursorForeDel = RepeatListener(mKeyboardHolding, normalInterval) {
        foreDelFunction()
    }

    fun inputChar(button: View) {
        if (currentInputConnection == null) return
        val cursorCS = currentInputConnection.getSelectedText(GET_TEXT_WITH_STYLES)
        if (cursorCS != null && cursorCS.isNotEmpty()) {
            clearComposing() // 선택 중인 글자가 있으면 초기화
        }
        when (vm.mode.value) {
            3, 4 -> {
                when (vm.observeKBIME.value) {
                    KeyboardUtil.QWERTY -> {
                        val (c1, c2) = HanguelQWERTY.composeChar((button as Button).text!!.single())
                        if (c1 != null) {
                            currentInputConnection.commitText(c1, 1)
                            if (c2 != null) currentInputConnection.setComposingText(c2, 1)
                        } else {
                            if (c2 != null) currentInputConnection.setComposingText(c2, 1)
                        }
                        if (vm.mode.value == 4) vm.changeMode(3)
                    }
                    KeyboardUtil.CHUN, KeyboardUtil.CHUN_AMBI -> {
                        val c1: String?
                        val c2: String?

                        when (button.id) {
                            in arrayOf(R.id.btnChunK, R.id.btnChunKa) -> {
                                val chars = HanguelChunjiin.composeChar('ᆞ')
                                c1 = chars.commited
                                c2 = chars.composing
                            }
                            else -> {
                                val chars = HanguelChunjiin.composeChar((button as Button).text[0])
                                c1 = chars.commited
                                c2 = chars.composing
                            }
                        }

                        if (c1 != null) {
                            currentInputConnection.commitText(c1, 1)
                            if (c2 != null) currentInputConnection.setComposingText(c2, 1)
                        } else {
                            if (c2 != null) currentInputConnection.setComposingText(c2, 1)
                        }
                    }
                    KeyboardUtil.NARAT -> {
                        var c1: String? = null
                        var c2: String? = null

                        if (button.id == R.id.btnNaADD) {
                            val chars = HanguelNARATGUL.composeChar('ᆞ')
                            c1 = chars.commited
                            c2 = chars.composing
                        } else if (button.id == R.id.btnNaSHIFT) {
                            val chars = HanguelNARATGUL.composeChar('ᆢ')
                            c1 = chars.commited
                            c2 = chars.composing
                        } else {
                            val chars = HanguelNARATGUL.composeChar((button as Button).text[0])
                            c1 = chars.commited
                            c2 = chars.composing
                        }

                        if (c1 != null) {
                            currentInputConnection.commitText(c1, 1)
                            if (c2 != null) currentInputConnection.setComposingText(c2, 1)
                        } else {
                            if (c2 != null) currentInputConnection.setComposingText(c2, 1)
                        }
                    }
                    KeyboardUtil.DAN -> {
                        val (c1, c2) = HanguelDanmoum.composeChar(
                            (button as Button).text!!.single(),
                            System.currentTimeMillis()
                        )
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
                clearComposing()
                currentInputConnection.commitText((button as Button).text.toString(), 1)
                if (vm.mode.value == 1) {
                    changeMode(2)
                }
            }
        }
        if (myKeyboardVibration) vibrateByButton()
    }

    fun initChunJiIn() {
        clearComposing()
    }

    fun inputSpecial(button: View) {
        if (currentInputConnection == null) return
        clearComposing()
        if ((button as Button).text in arrayOf(
                "한글",
                "english",
                "English",
                "SPACE",
                "특수 1",
                "특수 2"
            )
        ) currentInputConnection.commitText(" ", 1)
        else currentInputConnection.commitText((button as Button).text[0].toString(), 1)

        if (myKeyboardVibration) vibrateByButton()
    }

    fun longClickInputSpecial(button: View): Boolean {
        if (currentInputConnection == null) return false
        clearComposing()
        currentInputConnection.commitText((button as Button).text[1].toString(), 1)
        return true
    }

    fun inputBPStrings(button: View) {
        if (currentInputConnection == null) return
        clearComposing()
        currentInputConnection.commitText((button as Button).text, 1)

        if (myKeyboardVibration) vibrateByButton()
    }

    fun enterFunction() {
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
        if (myKeyboardVibration) vibrateByButton()
    }

    fun delFunction() {
        if (currentInputConnection == null) return
        val selectedText = currentInputConnection.getSelectedText(GET_TEXT_WITH_STYLES)
        if (TextUtils.isEmpty(selectedText)) {
            if (vm.mode.value in arrayOf(3, 4)) {
                when (vm.observeKBIME.value) {
                    KeyboardUtil.QWERTY -> {
                        val (c1, c2) = HanguelQWERTY.delete()
                        if (c1 == null) {
                            if (c2 == null) {
                                clearComposing()
                                currentInputConnection.deleteSurroundingText(1, 0)
                            } else {
                                currentInputConnection.setComposingText(c2, 1)
                            }
                        }
                        if (vm.mode.value == 4) vm.changeMode(3)
                    }
                    KeyboardUtil.CHUN, KeyboardUtil.CHUN_AMBI -> {
                        val (c1, c2) = HanguelChunjiin.delete()
                        if (c1 == null) {
                            if (c2 == null) {
                                clearComposing()
                                currentInputConnection.deleteSurroundingText(1, 0)
                            } else {
                                currentInputConnection.setComposingText(c2, 1)
                            }
                        }
                    }
                    KeyboardUtil.NARAT -> {
                        val (c1, c2) = HanguelNARATGUL.delete()
                        if (c1 == null) {
                            if (c2 == null) {
                                clearComposing()
                                currentInputConnection.deleteSurroundingText(1, 0)
                            } else {
                                currentInputConnection.setComposingText(c2, 1)
                            }
                        }
                    }
                    KeyboardUtil.DAN -> {
                        val (c1, c2) = HanguelDanmoum.delete(inputTime = System.currentTimeMillis())
                        if (c1 == null) {
                            if (c2 == null) {
                                clearComposing()
                                currentInputConnection.deleteSurroundingText(1, 0)
                            } else {
                                currentInputConnection.setComposingText(c2, 1)
                            }
                        }
                    }
                    else -> HanguelQWERTY.delete()
                }
            } else {
                clearComposing()
                currentInputConnection.deleteSurroundingText(1, 0)
            }
        } else {
            clearComposing()
            currentInputConnection.finishComposingText()
            currentInputConnection.commitText("", 0)
        }
        if (myKeyboardVibration) vibrateByButton()
    }

    private fun foreDelFunction() {
        if (currentInputConnection == null) return
        val selectedText = currentInputConnection.getSelectedText(GET_TEXT_WITH_STYLES)
        if (TextUtils.isEmpty(selectedText)) {
            clearComposing()
            currentInputConnection.deleteSurroundingText(0, 1)
        } else {
            clearComposing()
            currentInputConnection.finishComposingText()
            currentInputConnection.commitText("", 1)
        }
        if (myKeyboardVibration) vibrateByButton()
    }

    private fun vibrateByButton() {
        val vibrator: Vibrator = getSystemService(VIBRATOR_SERVICE) as Vibrator
        if (vibrator.hasVibrator()) {
            if (SDK_INT >= Build.VERSION_CODES.O) {
                vibrator.vibrate(
                    VibrationEffect.createOneShot(
                        (myKeyboardVibrationIntensity + 25).toLong(),
                        25 + myKeyboardVibrationIntensity
                    )
                )
            } else {
                vibrator.vibrate((myKeyboardVibrationIntensity + 25).toLong())
            }
        }
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

    fun jumpToBp(view: View, index: Int): Boolean {
        val intent: Intent? =
            applicationContext.packageManager.getLaunchIntentForPackage("com.sb.fittingKeyboard")
        if (intent != null) {
            intent.putExtra("Index", index + vm.bpPage.value!! * 8)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
        return false
    }

    @RequiresApi(Build.VERSION_CODES.R)
    fun selectWord() {
        vm.switchSelectingMode(!vm.isSelecting.value!!)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun selectAll() {
        if (currentInputConnection == null) {
            Toast.makeText(this, "?", Toast.LENGTH_SHORT).show()
            return
        }
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
        if (vm.isSelecting.value!!) {
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
        if (vm.isSelecting.value!!) {
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
        if (vm.isSelecting.value!!) {
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
        if (vm.isSelecting.value!!) {
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
            if (vm.isSelecting.value!!) {
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
            if (vm.isSelecting.value!!) {
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
    fun addFunction(addOn: Int): Boolean {
        when (addOn) {
            1 -> {
                vm.changeMode(7)
            }
            2 -> {
                vm.changeMode(8)
            }
            else -> {
            }
        }
        vibrateByButton()
        return true
    }

    // 키보드 전환
    fun changeMode(new: Int) {
        vm.changeMode(new)
        if (myKeyboardVibration) vibrateByButton()
    }

    // 텍스트 복사하기
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun copyText() {
        if (currentInputConnection == null) return
        vm.switchSelectingMode(false)
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

    // 텍스트 잘라내기
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun cutText() {
        if (currentInputConnection == null) return
        vm.switchSelectingMode(false)
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

    // 텍스트 붙여넣기
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun pasteText() {
        if (currentInputConnection == null) return
        vm.switchSelectingMode(false)
        if (currentInputConnection.requestCursorUpdates(CURSOR_UPDATE_IMMEDIATE)) {
            clearComposing()
            currentInputConnection.performContextMenuAction(android.R.id.paste)
        }
    }

    // 한글키보드 사용 중 작성 중이던 한글 자/모음 결합체계 초기화
    fun clearComposing() {
        currentInputConnection?.finishComposingText()

        HanguelQWERTY.initChar()
        HanguelQWERTY.initState()
        HanguelQWERTY.composeResult()
        HanguelQWERTY.initResult()

        HanguelChunjiin.initChar()
        HanguelChunjiin.initState()
        HanguelChunjiin.composeResult()
        HanguelChunjiin.initResult()

        HanguelNARATGUL.initChar()
        HanguelNARATGUL.initState()
        HanguelNARATGUL.composeResult()
        HanguelNARATGUL.initResult()

        HanguelDanmoum.initChar()
        HanguelDanmoum.initState()
        HanguelDanmoum.composeResult()
        HanguelDanmoum.initResult()
    }

    var repeatListenerChars = Array<RepeatListener>(200) { _ ->
        RepeatListener(mKeyboardHolding, normalInterval) {
            inputChar(it)
        }
    }
    var repeatListenerSpecials = Array<RepeatListener>(100) { _ ->
        RepeatListener(mKeyboardHolding, normalInterval) {
            inputSpecial(it)
        }
    }
    var repeatListenerDels = Array<RepeatListener>(100) { _ ->
        RepeatListener(mKeyboardHolding, normalInterval) {
            delFunction()
        }
    }
    var rListenerSpecial = RepeatListener(mKeyboardHolding, normalInterval) {
        inputSpecial(it)
    }
    var rListenerCursorLeft = RepeatListener(mKeyboardHolding, normalInterval) {
        moveCursorToLeft()
    }
    var rListenerCursorRight = RepeatListener(mKeyboardHolding, normalInterval) {
        moveCursorToRight()
    }
    var rListenerCursorUp = RepeatListener(mKeyboardHolding, normalInterval) {
        moveCursorToUp()
    }
    var rListenerCursorDown = RepeatListener(mKeyboardHolding, normalInterval) {
        moveCursorToDown()
    }
}