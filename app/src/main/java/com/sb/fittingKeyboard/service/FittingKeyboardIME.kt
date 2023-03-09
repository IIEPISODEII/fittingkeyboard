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
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputConnection.CURSOR_UPDATE_IMMEDIATE
import android.view.inputmethod.InputConnection.GET_TEXT_WITH_STYLES
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.core.content.res.ResourcesCompat
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


    private val goSettingImageButton by lazy {
        ImageButton(kbView.context).apply {
            setImageDrawable(
                ResourcesCompat.getDrawable(
                    this.resources,
                    R.drawable.ic_settings,
                    null
                )
            )
            setOnClickListener {
                startApp()
            }
            setBackgroundColor(
                ResourcesCompat.getColor(
                    this.resources,
                    R.color.white,
                    null
                )
            )
        }
    }
    private val showBoilerPlateImageButton by lazy {
        ImageButton(kbView.context).apply {
            setImageDrawable(
                ResourcesCompat.getDrawable(
                    this.resources,
                    R.drawable.ic_boilerplatetext_black,
                    null
                )
            )
            setOnClickListener {
                vm.changeMode(7)
            }
            setBackgroundColor(
                ResourcesCompat.getColor(
                    this.resources,
                    R.color.white,
                    null
                )
            )
        }
    }
    private val selectAllImageButton by lazy {
        ImageButton(kbView.context).apply {
            setImageDrawable(
                ResourcesCompat.getDrawable(
                    this.resources,
                    R.drawable.ic_select,
                    null
                )
            )
            setOnClickListener {
                selectAllTexts()
            }
            setBackgroundColor(
                ResourcesCompat.getColor(
                    this.resources,
                    R.color.white,
                    null
                )
            )
        }
    }
    private val copyImageButton by lazy {
        ImageButton(kbView.context).apply {
            setImageDrawable(
                ResourcesCompat.getDrawable(
                    this.resources,
                    R.drawable.ic_copy,
                    null
                )
            )
            setOnClickListener {
                copyText()
            }
            setBackgroundColor(
                ResourcesCompat.getColor(
                    this.resources,
                    R.color.white,
                    null
                )
            )
        }
    }
    private val cutImageButton by lazy {
        ImageButton(kbView.context).apply {
            setImageDrawable(
                ResourcesCompat.getDrawable(
                    this.resources,
                    R.drawable.ic_cut,
                    null
                )
            )
            setOnClickListener {
                cutText()
            }
            setBackgroundColor(
                ResourcesCompat.getColor(
                    this.resources,
                    R.color.white,
                    null
                )
            )
        }
    }
    private val pasteImageButton by lazy {
        ImageButton(kbView.context).apply {
            setImageDrawable(
                ResourcesCompat.getDrawable(
                    this.resources,
                    R.drawable.ic_paste,
                    null
                )
            )
            setOnClickListener {
                pasteText()
            }
            setBackgroundColor(
                ResourcesCompat.getColor(
                    this.resources,
                    R.color.white,
                    null
                )
            )
        }
    }
    private val showCursorImageButton by lazy {
        ImageButton(kbView.context).apply {
            setImageDrawable(
                ResourcesCompat.getDrawable(
                    this.resources,
                    R.drawable.ic_move,
                    null
                )
            )
            setOnClickListener {
                vm.changeMode(8)
            }
            setBackgroundColor(
                ResourcesCompat.getColor(
                    this.resources,
                    R.color.white,
                    null
                )
            )
        }
    }
    private val showNumberImageButton by lazy {
        ImageButton(kbView.context).apply {
            setImageDrawable(
                ResourcesCompat.getDrawable(
                    this.resources,
                    R.drawable.ic_number_keypad,
                    null
                )
            )
            setOnClickListener {
                vm.changeMode(9)
            }
            setBackgroundColor(
                ResourcesCompat.getColor(
                    this.resources,
                    R.color.white,
                    null
                )
            )
        }
    }
    private val showEmojiImageButton by lazy {
        ImageButton(kbView.context).apply {
            setImageDrawable(
                ResourcesCompat.getDrawable(
                    this.resources,
                    R.drawable.ic_outline_emoji_emotions_24,
                    null
                )
            )
            setOnClickListener {
                vm.changeMode(1)
            }
            setBackgroundColor(
                ResourcesCompat.getColor(
                    this.resources,
                    R.color.white,
                    null
                )
            )
        }
    }

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
        vm.observeBottomMargin.observe(this) {
            val lP = kbLayoutBottomMargin.layoutParams
            lP.height = it * (resources.displayMetrics.density).toInt()
            kbLayoutBottomMargin.layoutParams = lP
        }
        vm.observeKBIME.observe(this) {
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
        }
        vm.mode.observe(this) {
            when (it) {
                0, 1, 2 -> {
                    if (latestMode == it) return@observe
                    kbFrame.removeAllViews()
                    kbFrame.addView(qwertyEnNormalKBView)
                }
                3, 4 -> {
                    if (latestMode == it) return@observe
                    kbFrame.removeAllViews()
                    kbFrame.addView(currentKRView)
                }
                5, 6 -> {
                    if (latestMode == it) return@observe
                    kbFrame.removeAllViews()
                    kbFrame.addView(qwertySpecialKBView)
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
        }
        vm.observeKBFontSize.observe(
            this
        ) {
            _fontSize.value = (vm.kbSettingSP.intLiveData(
                KEYBOARD_FONT_SIZE,
                16
            ).value!! * (resources.displayMetrics.scaledDensity)).toInt()
        }
        vm.observeKBHoldingTime.observe(this) {
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
            if (SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                rListenerCursorFirst.changeInitialInterval(mKeyboardHolding)
                rListenerCursorLast.changeInitialInterval(mKeyboardHolding)
            }
            rListenerCursorForeDel.changeInitialInterval(mKeyboardHolding)
        }
        vm.observeKBVibrationUse.observe(this) { myKeyboardVibration = it }
        vm.observeKBVibrationIntensity.observe(this) { myKeyboardVibrationIntensity = it }
        vm.observeKBTheme.observe(this) { myKeyboardTheme = it }
        vm.observeKBLeftSideMargin.observe(this) {
            val lPNum = kbNumLeftSide.layoutParams
            val lPChar = kbCharLeftSide.layoutParams
            lPNum.width = it.toInt() * 3 * (resources.displayMetrics.density).toInt()
            lPChar.width = it.toInt() * 3 * (resources.displayMetrics.density).toInt()
            kbNumLeftSide.layoutParams = lPNum
            kbCharLeftSide.layoutParams = lPChar
        }
        vm.observeKBRightSideMargin.observe(this) {
            val lPNum = kbNumRightSide.layoutParams
            val lPChar = kbCharRightSide.layoutParams
            lPNum.width = it.toInt() * 3 * (resources.displayMetrics.density).toInt()
            lPChar.width = it.toInt() * 3 * (resources.displayMetrics.density).toInt()
            kbNumRightSide.layoutParams = lPNum
            kbCharRightSide.layoutParams = lPChar
        }
        vm.observeHeight.observe(this) {
            kbLayout.layoutParams.height = it.toInt()
            val bgImg = kbView.findViewById<ImageView>(R.id.keyboardBackgroundImage)
            bgImg.layoutParams.height = it.toInt()
        }
        vm.prefSettingToolbarSetting.observe(this) {
            val toolbarLinearLayout = kbBinding.keyboardToolBarLine

            val sortedToolbarSetting = it.toList().sortedBy { (_, value) -> kotlin.math.abs(value) }.toMap()
            toolbarLinearLayout.removeAllViews()
            sortedToolbarSetting.keys.toList().forEachIndexed { _, key ->
                val ordering = sortedToolbarSetting[key]!!-1
                when (key) {
                    KeyboardUtil.KEYBOARD_TOOLBAR_ACTIVE_GO_SETTING -> toolbarLinearLayout.reorderChild(goSettingImageButton, ordering)
                    KeyboardUtil.KEYBOARD_TOOLBAR_ACTIVE_SHOW_BOILERPLATE -> toolbarLinearLayout.reorderChild(showBoilerPlateImageButton, ordering)
                    KeyboardUtil.KEYBOARD_TOOLBAR_ACTIVE_SELECT_ALL -> toolbarLinearLayout.reorderChild(selectAllImageButton, ordering)
                    KeyboardUtil.KEYBOARD_TOOLBAR_ACTIVE_COPY -> toolbarLinearLayout.reorderChild(copyImageButton, ordering)
                    KeyboardUtil.KEYBOARD_TOOLBAR_ACTIVE_CUT -> toolbarLinearLayout.reorderChild(cutImageButton, ordering)
                    KeyboardUtil.KEYBOARD_TOOLBAR_ACTIVE_PASTE -> toolbarLinearLayout.reorderChild(pasteImageButton, ordering)
                    KeyboardUtil.KEYBOARD_TOOLBAR_ACTIVE_SHOW_CURSOR -> toolbarLinearLayout.reorderChild(showCursorImageButton, ordering)
                    KeyboardUtil.KEYBOARD_TOOLBAR_ACTIVE_SHOW_NUMBER -> toolbarLinearLayout.reorderChild(showNumberImageButton, ordering)
                    KeyboardUtil.KEYBOARD_TOOLBAR_ACTIVE_SHOW_EMOJI -> toolbarLinearLayout.reorderChild(showEmojiImageButton, ordering)
                }
            }
        }

        emojisViewPager.adapter = emojiPagerAdapter
        vm.emojiColumnsCounts.observe(
            this
        ) { (emojisViewPager.adapter as EmojiViewPagerAdapter).changeColumns(it) }
        vm.observeE0RecentlyUsedEmoticons.observe(this) {
            val jsonArray = JSONArray(it)
            val arr = mutableListOf<String>()

            for (i in 0 until jsonArray.length()) {
                if (jsonArray.optString(i) != "") arr.add(jsonArray.optString(i))
            }
            (emojisViewPager.adapter as EmojiViewPagerAdapter).changeAdapter(arr)
        }

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

        // 무한스크롤 뷰페이저
        emojisViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                val mPosition = emojisViewPager.currentItem
                customEmojiIndicator.selectPosition(position = mPosition)
                emojiScrollView.smoothScrollTo(getEmojiIconXPosition(emojiScrollView, mPosition), 0)

                /** emojisViewPager.changeAdapter()로 데이터셋 변경 알림시킬 경우, 뷰페이저 1페이지에서 스크롤이 자꾸 고정되는 현상을 방지하고자
                 *  데이터셋 변경 알림은 notifyE0DataSetChanged으로 뷰페이저 페이지 변경 시 실행하도록 함
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
        } else if (newSelStart == 0 && newSelEnd == 0 && candidatesEnd == -1) {
            clearComposing()
        }
        /** TextField의 첫글자로 돌아갔을 때 대문자로 수정 **/
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

        // vm(SharedKBViewModel.kt)에 있는 방향 전환 메소드 실행
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

    fun startApp(uri: String = KeyboardUtil.PACKAGE_NAME) {
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

    // 엔터키, 특수문자 전환 키를 LongClick할 경우 작동
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
    fun copyText() {
        KeyboardInputFuctions.copyText(
            mIMEService = this,
            clearComposing = { clearComposing() },
            switchSelectingMode = { vm.switchSelectingMode(false) },
            context = this
        )
    }

    // 텍스트 잘라내기
    fun cutText() {
        KeyboardInputFuctions.cutText(
            mIMEService = this,
            clearComposing = { clearComposing() },
            switchSelectingMode = { vm.switchSelectingMode(false) },
            context = this
        )
    }

    // 텍스트 붙여넣기
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