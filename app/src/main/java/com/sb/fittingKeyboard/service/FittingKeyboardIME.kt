package com.sb.fittingKeyboard.service

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Configuration
import android.inputmethodservice.InputMethodService
import android.os.Build
import android.os.Build.VERSION.SDK_INT
import android.os.VibrationEffect
import android.os.Vibrator
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
import androidx.viewpager2.widget.ViewPager2
import com.sb.fittingKeyboard.BR
import com.sb.fittingKeyboard.R
import com.sb.fittingKeyboard.databinding.*
import com.sb.fittingKeyboard.keyboardSettings.MainActivity
import com.sb.fittingKeyboard.koreanAutomata.HanguelChunjiin
import com.sb.fittingKeyboard.koreanAutomata.HanguelDanmoum
import com.sb.fittingKeyboard.koreanAutomata.HanguelNARATGUL
import com.sb.fittingKeyboard.koreanAutomata.HanguelQWERTY
import com.sb.fittingKeyboard.service.emoji.EmojiRecyclerAdapter
import com.sb.fittingKeyboard.service.emoji.EmojiRecyclerLiveDataAdapter
import com.sb.fittingKeyboard.service.emoji.EmojiViewPagerAdapter
import com.sb.fittingKeyboard.service.emoji.indicator.CustomIndicator
import com.sb.fittingKeyboard.service.util.EmojiCollections
import com.sb.fittingKeyboard.service.util.KeyboardUtil
import com.sb.fittingKeyboard.service.util.KeyboardUtil.Companion.KEYBOARD_FONT_SIZE
import com.sb.fittingKeyboard.service.util.KeyboardUtil.Companion.KEYBOARD_SETTING
import com.sb.fittingKeyboard.service.util.KeyboardUtil.Companion.emojiIconList
import com.sb.fittingKeyboard.service.util.KeyboardUtil.Companion.getEmojiIconXPosition
import com.sb.fittingKeyboard.service.util.RepeatListener
import com.sb.fittingKeyboard.service.viewmodel.SharedKBViewModel
import org.json.JSONArray
import java.security.Key

@SuppressLint("ClickableViewAccessibility")
class FittingKeyboardIME : InputMethodService(), LifecycleOwner {

    private lateinit var kbBinding: KeyLayoutNormalBinding
    private lateinit var qwertyEnNormalBinding: FragmentKeyboardQwertyEnNormalBinding
    private lateinit var qwertyKrNormalBinding: FragmentKeyboardQwertyKrNormalBinding
    private lateinit var chunjiinBinding: FragmentKeyboardChunjiinBasicBinding
    private lateinit var chunAKBBinding: FragmentKeyboardChunjiinAmbiBinding
    private lateinit var naratguelBinding: FragmentKeyboardNaratgulBasicBinding
    private lateinit var danmoumBinding: FragmentKeyboardDanmoumBinding
    private lateinit var specialKBBinding: FragmentKeyboardQwertySpecialBinding
    private lateinit var numberBinding: FragmentKeyboardNumberBinding
    private lateinit var boilerPlateBinding: FragmentBpBinding
    private lateinit var cursorBinding: FragmentCursorBinding
    private lateinit var emojiBinding: FragmentEmojiBinding

    private lateinit var vm: SharedKBViewModel
    private lateinit var kbFrame: FrameLayout
    private lateinit var kbView: View
    private lateinit var kbLayout: LinearLayout
    private lateinit var kbLayoutBottomMargin: View
    private lateinit var kbCharLeftSide: FrameLayout
    private lateinit var kbCharRightSide: FrameLayout
    private lateinit var kbNumLeftSide: Button
    private lateinit var kbNumRightSide: Button
    private lateinit var customEmojiIndicator: CustomIndicator
    private lateinit var emojisViewPager: ViewPager2
    private lateinit var emojiScrollView: HorizontalScrollView
    private val mLifecycle = LifecycleRegistry(this)

    override fun getLifecycle(): Lifecycle = mLifecycle

    private val inputTypeNumbers = arrayOf(2, 4098, 8194, 18, 3, 4, 14, 24, 24578, 16387)

    private var mKeyboardHolding: Long = 300
    private var myKeyboardVibration: Boolean = true
    private var myKeyboardVibrationIntensity: Int = 50
    private var myKeyboardTheme: Int = 0
    private val normalInterval: Long = 37
    private var latestMode = 3
    private var savedCursorPosition = 0
    private lateinit var prefs: SharedPreferences
    private var _fontSize = MutableLiveData<Int>(15)
    val fontSize: LiveData<Int> get() = _fontSize
    private val emojiAdapterList = listOf(
        EmojiRecyclerAdapter(EmojiCollections.e1SmileysAndEmoticons),
        EmojiRecyclerAdapter(EmojiCollections.e2PeopleAndBody),
        EmojiRecyclerAdapter(EmojiCollections.e3AnimalsAndNature),
        EmojiRecyclerAdapter(EmojiCollections.e4FoodAndDrink),
        EmojiRecyclerAdapter(EmojiCollections.e5TravelAndPlaces),
        EmojiRecyclerAdapter(EmojiCollections.e6Activities),
        EmojiRecyclerAdapter(EmojiCollections.e7Objects),
        EmojiRecyclerAdapter(EmojiCollections.e8Symbols),
        EmojiRecyclerAdapter(EmojiCollections.e9Flags)
    )
    private val emojiPagerAdapter: EmojiViewPagerAdapter
        get() = EmojiViewPagerAdapter(mutableListOf(), emojiAdapterList, 0, null, null)

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
        val emojiKBView =
            layoutInflater.inflate(R.layout.fragment_emoji, null)

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
        emojiBinding = DataBindingUtil.bind(emojiKBView)!!
        emojiBinding.setVariable(BR.kbservice, this)
        emojiBinding.lifecycleOwner = this
        emojiBinding.kbviewmodel = vm

        kbNumLeftSide = kbView.findViewById(R.id.keyboardNumberLeftMargin)
        kbNumRightSide = kbView.findViewById(R.id.keyboardNumberRightMargin)
        kbCharLeftSide = kbView.findViewById(R.id.keyboardLeftMargin)
        kbCharRightSide = kbView.findViewById(R.id.keyboardRightMargin)
        kbLayout = kbView.findViewById(R.id.keyboardLayout)
        kbFrame = kbView.findViewById(R.id.keyboardViewFrameLayout)
        kbLayoutBottomMargin = kbView.findViewById(R.id.keyboardBotMargin)
        emojisViewPager = emojiKBView.findViewById(R.id.emoji_viewpager)
        emojiScrollView = emojiKBView.findViewById(R.id.emoji_scrollview)
        customEmojiIndicator = emojiKBView.findViewById(R.id.emoji_viewpager_indicator)

        var currentKRView = qwertyKrNormalKBView
        vm.observeBottomMargin.observe(this, {
            val lP = kbLayoutBottomMargin.layoutParams
            lP.height = it * (resources.displayMetrics.density).toInt()
            kbLayoutBottomMargin.layoutParams = lP
        })
        vm.observeKBIME.observe(this, {
            currentKRView =
                when (it) {
                    KeyboardUtil.QWERTY -> qwertyKrNormalKBView
                    KeyboardUtil.CHUN -> chunjiinKBView
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
            when (it) {
                0, 1, 2 -> {
                    if (latestMode !in arrayOf(0, 1, 2)) {
                        kbFrame.removeAllViews()
                        kbFrame.addView(qwertyEnNormalKBView)
                    }
                }
                3, 4 -> {
                    if (latestMode !in arrayOf(3, 4)) {
                        kbFrame.removeAllViews()
                        kbFrame.addView(currentKRView)
                    }
                }
                5, 6 -> {
                    if (latestMode !in arrayOf(5, 6)) {
                        kbFrame.removeAllViews()
                        kbFrame.addView(qwertySpecialKBView)
                    }
                }
                7 -> {
                    kbFrame.removeAllViews()
                    kbFrame.addView(boilerPlateKBView)
                }
                8 -> {
                    kbFrame.removeAllViews()
                    kbFrame.addView(cursorKBView)
                }
                9 -> {
                    kbFrame.removeAllViews()
                    kbFrame.addView(numberKBView)
                }
                10 -> {
                    kbFrame.removeAllViews()
                    kbFrame.addView(emojiKBView)
                }
            }
            latestMode = it
        })
        vm.observeNumberVisibility.observe(this, {})
        vm.observeKBBottomMargin.observe(this, {})
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
            lPNum.width = it.toInt() * 3 * (resources.displayMetrics.density).toInt()
            lPChar.width = it.toInt() * 3 * (resources.displayMetrics.density).toInt()
            kbNumLeftSide.layoutParams = lPNum
            kbCharLeftSide.layoutParams = lPChar
        })
        vm.observeKBRightSideMargin.observe(this, {
            val lPNum = kbNumRightSide.layoutParams
            val lPChar = kbCharRightSide.layoutParams
            lPNum.width = it.toInt() * 3 * (resources.displayMetrics.density).toInt()
            lPChar.width = it.toInt() * 3 * (resources.displayMetrics.density).toInt()
            kbNumRightSide.layoutParams = lPNum
            kbCharRightSide.layoutParams = lPChar
        })
        vm.observeKBToolBarVisibility.observe(this, {})
        vm.observeKBHeight.observe(this, {})
        vm.observeHeight.observe(this, {
            kbLayout.layoutParams.height = it.toInt()
            val bgImg = kbView.findViewById<ImageView>(R.id.keyboardBackgroundImage)
            bgImg.layoutParams.height = it.toInt()
        })
        vm.observeKBEnterKeyHolding.observe(this, {})
        vm.observeKBSpecialKeyHolding.observe(this, {})
        vm.observeKBAutoCapitalization.observe(this, {})
        vm.observeKBAutoModeChange.observe(this, {})
        vm.isSelecting.observe(this, {})
        vm.orientation.observe(this, {})

        emojisViewPager.adapter = emojiPagerAdapter
        vm.emojiColumnsCounts.observe(
            this,
            { (emojisViewPager.adapter as EmojiViewPagerAdapter).changeColumns(it) })
        vm.observeE0RecentlyUsedEmoticons.observe(this, {
            val jsonArray = JSONArray(it)
            val arr = mutableListOf<String>()

            for (i in 0 until jsonArray.length()) {
                if (jsonArray.optString(i) != "") arr.add(jsonArray.optString(i))
            }
            (emojisViewPager.adapter as EmojiViewPagerAdapter).changeAdapter(arr)
        })

        val emojiIconClickListeners = MutableList(emojiIconList.size) { View.OnClickListener { } }
        for (i in emojiIconClickListeners.indices) {
            emojiIconClickListeners[i] = View.OnClickListener {
                emojisViewPager.currentItem = i
            }
        }

        customEmojiIndicator.createIconPanel(
            iconsList = emojiIconList,
            position = 1,
            clickListeners = emojiIconClickListeners
        )
        (emojisViewPager.adapter as EmojiViewPagerAdapter).initListener(object :
            EmojiRecyclerAdapter.OnItemClickListener {
            override fun onItemClick(v: View, pos: Int) {
                clearComposing()
                currentInputConnection.commitText((v as Button).text.toString(), 1)
                vm.setRecentlyUsedEmoticon(v.text.toString())
            }
        },
            object : EmojiRecyclerLiveDataAdapter.OnItemClickListener {
                override fun onItemClick(v: View, pos: Int) {
                    clearComposing()
                    currentInputConnection.commitText((v as Button).text.toString(), 1)
                }
            })
        emojisViewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        emojisViewPager.offscreenPageLimit = 1

        // ??????????????? ????????????
        emojisViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                val mPosition = emojisViewPager.currentItem
                customEmojiIndicator.selectPosition(position = mPosition)
                emojiScrollView.smoothScrollTo(getEmojiIconXPosition(emojiScrollView, mPosition), 0)

                /** emojisViewPager.changeAdapter()??? ???????????? ?????? ???????????? ??????, ???????????? 1??????????????? ???????????? ?????? ???????????? ????????? ???????????????
                 *  ???????????? ?????? ????????? notifyE0DataSetChanged?????? ???????????? ????????? ?????? ??? ??????????????? ???
                 **/
                if (mPosition == 0) (emojisViewPager.adapter as EmojiViewPagerAdapter).notifyE0DataSetChanged()
            }
        })
    }

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("ClickableViewAccessibility", "DefaultLocale", "NewApi")
    override fun onCreateInputView(): View {
        super.onCreateInputView()
        onConfigurationChanged(resources.configuration)
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
        /** TextField??? ???????????? ???????????? ??? ???????????? ?????? **/
        if (vm.observeKBAutoCapitalization.value!!
            && currentInputConnection.requestCursorUpdates(CURSOR_UPDATE_IMMEDIATE)
            && vm.mode.value == 2
        ) {
            var autoCapitalCondition = false
            for (i in 0..3) {
                if (currentInputConnection
                        .getTextBeforeCursor(i + 2, GET_TEXT_WITH_STYLES)
                        .toString()
                        .replace("\\s".toRegex(), "") == "."
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
        } else if (vm.mode.value in listOf(5, 6, 7, 8, 9, 10)) {
            vm.changeMode(1)
        }
        emojisViewPager.setCurrentItem(1, false)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        onFinishInputView(finishingInput = true)

        // vm(SharedKBViewModel.kt)??? ?????? ?????? ?????? ????????? ??????
        vm.changeOrientation(newConfig.orientation)
    }

    override fun onStartInput(attribute: EditorInfo?, restarting: Boolean) {
        super.onStartInput(attribute, restarting)
        clearComposing()
    }

    override fun onFinishInputView(finishingInput: Boolean) {
        super.onFinishInputView(finishingInput)
        clearComposing()
        vm.switchSelectingMode(false)
    }

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onDestroy() {
        super.onDestroy()
        mLifecycle.markState(Lifecycle.State.DESTROYED)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    var rListenerCursorFirst = RepeatListener(mKeyboardHolding, normalInterval) {
        moveCursorFirst()
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    var rListenerCursorLast = RepeatListener(mKeyboardHolding, normalInterval) {
        moveCursorLast()
    }
    var rListenerCursorForeDel = RepeatListener(mKeyboardHolding, normalInterval) {
        inputForwardDelete()
    }

    fun inputChar(button: View) {
        KeyboardInputFuctions.inputChar(
            mIMEService = this,
            clearComposing = { clearComposing() },
            button = button,
            mode = vm.mode.value!!,
            krIME = vm.observeKBIME.value!!,
            myKeyboardVibration = myKeyboardVibration,
            vibrateByButton = { vibrateByButton() },
            changeMode3 = { changeMode(3) },
            changeMode2 = { changeMode(2) }
        )
    }

    fun initChunJiIn() {
        clearComposing()
    }

    fun inputSpecial(button: View) {
        KeyboardInputFuctions.inputSpecialButton(
            mIMEService = this,
            clearComposing = { clearComposing() },
            button = button,
            autoModeChange = vm.observeKBAutoModeChange.value!!,
            changeMode3 = { vm.changeMode(3) },
            myKeyboardVibration = myKeyboardVibration,
            vibrateByButton = { vibrateByButton() }
        )
    }

    fun longClickInputSpecial(button: View): Boolean {
        return KeyboardInputFuctions.inputSpecialLongClicked(
            mIMEService = this,
            clearComposing = { clearComposing() },
            button = button
        )
    }

    fun inputBPStrings(button: View) {
        KeyboardInputFuctions.inputBPStrings(
            mIMEService = this,
            clearComposing = { clearComposing() },
            button = button,
            myKeyboardVibration = myKeyboardVibration,
            vibrateByButton = { vibrateByButton() }
        )
    }

    fun inputEnter() {
        KeyboardInputFuctions.inputEnter(
            mIMEService = this,
            clearComposing = { clearComposing() },
            myKeyboardVibration = myKeyboardVibration,
            vibrateByButton = { vibrateByButton() })
    }

    private fun inputDelete() {
        KeyboardInputFuctions.inputDelete(
            mIMEService = this,
            mode = vm.mode.value!!,
            krIME = vm.observeKBIME.value!!,
            clearComposing = { clearComposing() },
            changeMode3 = { vm.changeMode(3) },
            myKeyboardVibration = myKeyboardVibration,
            vibrateByButton = { vibrateByButton() }
        )
    }

    private fun inputForwardDelete() {
        KeyboardInputFuctions.inputForwardDelete(
            mIMEService = this,
            clearComposing = { clearComposing() },
            myKeyboardVibration = myKeyboardVibration,
            vibrateByButton = { vibrateByButton() }
        )
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
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.putExtra("Index", index)
        startActivity(intent)
        return false
    }

    @RequiresApi(Build.VERSION_CODES.R)
    fun selectWord() {
        vm.switchSelectingMode(!vm.isSelecting.value!!)
    }

    fun selectAllTexts() {
        KeyboardInputFuctions.selectAllTexts(
            mIMEService = this,
            clearComposing = { clearComposing() },
            setSavedCursorPositionDefault = { setSavedCursorPositionDefault() },
            context = this
        )
    }

    private fun moveCursorUp() {
        KeyboardInputFuctions.moveCursorUp(
            mIMEService = this,
            clearComposing = { clearComposing() },
            isSelectingMode = vm.isSelecting.value!!,
            savedCursorPosition = savedCursorPosition
        )
    }

    private fun moveCursorDown() {
        KeyboardInputFuctions.moveCursorDown(
            mIMEService = this,
            clearComposing = { clearComposing() },
            isSelectingMode = vm.isSelecting.value!!,
            savedCursorPosition = savedCursorPosition
        )
    }

    private fun moveCursorLeft() {
        KeyboardInputFuctions.moveCursorLeft(
            mIMEService = this,
            clearComposing = { clearComposing() },
            isSelectingMode = vm.isSelecting.value!!,
            savedCursorPosition = savedCursorPosition
        )
    }

    private fun moveCursorRight() {
        KeyboardInputFuctions.moveCursorRight(
            mIMEService = this,
            clearComposing = { clearComposing() },
            isSelectingMode = vm.isSelecting.value!!,
            savedCursorPosition = savedCursorPosition
        )
    }

    private fun moveCursorFirst() {
        KeyboardInputFuctions.moveCursorFirst(
            mIMEService = this,
            clearComposing = { clearComposing() },
            isSelectingMode = vm.isSelecting.value!!,
            savedCursorPosition = savedCursorPosition
        )
    }

    private fun moveCursorLast() {
        KeyboardInputFuctions.moveCursorLast(
            mIMEService = this,
            clearComposing = { clearComposing() },
            isSelectingMode = vm.isSelecting.value!!,
            savedCursorPosition = savedCursorPosition
        )
    }

    // ?????????, ???????????? ?????? ?????? LongClick??? ?????? ??????
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

    // ????????? ??????
    fun changeMode(new: Int) {
        vm.changeMode(new)
        if (myKeyboardVibration) vibrateByButton()
    }

    // ????????? ????????????
    fun copyText() {
        KeyboardInputFuctions.copyText(
            mIMEService = this,
            clearComposing = { clearComposing() },
            switchSelectingMode = { vm.switchSelectingMode(false) },
            context = this
        )
    }

    // ????????? ????????????
    fun cutText() {
        KeyboardInputFuctions.cutText(
            mIMEService = this,
            clearComposing = { clearComposing() },
            switchSelectingMode = { vm.switchSelectingMode(false) },
            context = this
        )
    }

    // ????????? ????????????
    fun pasteText() {
        KeyboardInputFuctions.pasteText(
            mIMEService = this,
            clearComposing = { clearComposing() },
            switchSelectingMode = { vm.switchSelectingMode(false) }
        )
    }

    private fun setSavedCursorPositionDefault() {
        savedCursorPosition = -1
    }

    // ??????????????? ?????? ??? ?????? ????????? ?????? ???/?????? ???????????? ?????????
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

    var repeatListenerChars = Array(200) {
        RepeatListener(mKeyboardHolding, normalInterval) {
            inputChar(it)
        }
    }
    var repeatListenerSpecials = Array(100) {
        RepeatListener(mKeyboardHolding, normalInterval) {
            inputSpecial(it)
        }
    }
    var repeatListenerDels = Array(100) {
        RepeatListener(mKeyboardHolding, normalInterval) {
            inputDelete()
        }
    }
    var rListenerCursorLeft = RepeatListener(mKeyboardHolding, normalInterval) {
        moveCursorLeft()
    }
    var rListenerCursorRight = RepeatListener(mKeyboardHolding, normalInterval) {
        moveCursorRight()
    }
    var rListenerCursorUp = RepeatListener(mKeyboardHolding, normalInterval) {
        moveCursorUp()
    }
    var rListenerCursorDown = RepeatListener(mKeyboardHolding, normalInterval) {
        moveCursorDown()
    }
    var singleListenerSpecialSpace = View.OnClickListener {
        inputSpecial(it)
    }
}