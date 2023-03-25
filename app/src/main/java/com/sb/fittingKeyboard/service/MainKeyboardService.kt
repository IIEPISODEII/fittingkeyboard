package com.sb.fittingKeyboard.service

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import android.content.res.Resources
import android.inputmethodservice.InputMethodService
import android.os.Build
import android.os.Build.VERSION.SDK_INT
import android.os.VibrationEffect
import android.os.Vibrator
import android.text.InputType
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputConnection.CURSOR_UPDATE_IMMEDIATE
import android.view.inputmethod.InputConnection.GET_TEXT_WITH_STYLES
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.*
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.sb.fittingKeyboard.BR
import com.sb.fittingKeyboard.R
import com.sb.fittingKeyboard.Constants
import com.sb.fittingKeyboard.keyboardSettings.ui.MainActivity
import com.sb.fittingKeyboard.databinding.*
import com.sb.fittingKeyboard.service.koreanAutomata.HanguelChunjiin
import com.sb.fittingKeyboard.service.koreanAutomata.HanguelDanmoum
import com.sb.fittingKeyboard.service.koreanAutomata.HanguelNARATGUL
import com.sb.fittingKeyboard.service.koreanAutomata.HanguelQWERTY
import com.sb.fittingKeyboard.service.emoji.EmojiRecyclerAdapter
import com.sb.fittingKeyboard.service.emoji.EmojiRecyclerLiveDataAdapter
import com.sb.fittingKeyboard.service.emoji.EmojiViewPagerAdapter
import com.sb.fittingKeyboard.service.emoji.indicator.CustomIndicator
import com.sb.fittingKeyboard.service.util.EmojiCollections
import com.sb.fittingKeyboard.service.util.KeyboardUtil
import com.sb.fittingKeyboard.service.util.RepeatListener
import com.sb.fittingKeyboard.service.viewmodel.KeyboardViewModel
import org.json.JSONArray

@SuppressLint("ClickableViewAccessibility")
class MainKeyboardService : InputMethodService(), LifecycleOwner {

    private lateinit var kbBinding: LayoutKeyboardBinding
    private lateinit var qwertyEnNormalBinding: FragmentKeyboardQwertyEnNormalBinding
    private lateinit var qwertyKrNormalBinding: FragmentKeyboardQwertyKrNormalBinding
    private lateinit var chunjiinBinding: FragmentKeyboardChunjiinBasicBinding
    private lateinit var chunAKBBinding: FragmentKeyboardChunjiinAmbiBinding
    private lateinit var naratguelBinding: FragmentKeyboardNaratgulBasicBinding
    private lateinit var danmoumBinding: FragmentKeyboardDanmoumBinding
    private lateinit var specialKBBinding: FragmentKeyboardQwertySpecialBinding
    private lateinit var numberBinding: FragmentKeyboardNumberBinding
    private lateinit var boilerPlateBinding: FragmentBoilerplatetextBinding
    private lateinit var cursorBinding: FragmentCursorkeypadBinding
    private lateinit var emojiBinding: FragmentEmojiBinding

    private val vm: KeyboardViewModel by lazy { ViewModelProvider.AndroidViewModelFactory.getInstance(application).create(KeyboardViewModel::class.java) }
    private val kbdLayout: View by lazy { layoutInflater.inflate(R.layout.layout_keyboard, null) }
    private val kbdCharaterAreaFramelayout: FrameLayout by lazy { kbdLayout.findViewById(R.id.framelayout_keyboard_character_rows) }
    private val kbdBackgroundImageView: ImageView by lazy { kbdLayout.findViewById(R.id.iv_keyboard_bg_image) }
    private val kbLayoutBottomMargin: View by lazy { kbdLayout.findViewById(R.id.view_keyboard_bottom_margin) }
    private val kbCharLeftSide: FrameLayout by lazy { kbdLayout.findViewById(R.id.framelayout_keyboard_character_rows_left_margin) }
    private val kbCharRightSide: FrameLayout by lazy { kbdLayout.findViewById(R.id.framelayout_keyboard_character_rows_right_margin) }
    private val kbNumLeftSide: Button by lazy { kbdLayout.findViewById(R.id.view_keyboard_number_row_left_margin) }
    private val kbNumRightSide: Button by lazy { kbdLayout.findViewById(R.id.view_keyboard_number_row_right_margin) }
    private val emojiKBView by lazy { layoutInflater.inflate(R.layout.fragment_emoji, null) }
    private val customEmojiIndicator: CustomIndicator by lazy { emojiKBView.findViewById(R.id.indicator_emoji_list) }
    private val emojisViewPager: ViewPager2 by lazy { emojiKBView.findViewById(R.id.viewpager_emoji) }
    private val emojiScrollView: HorizontalScrollView by lazy { emojiKBView.findViewById(R.id.scrollview_emoji_container) }
    private val mLifecycle = LifecycleRegistry(this)

    private val keyboardFunctions = KeyboardInputFuctions(this)

    private val goSettingImageButton by lazy {
        ImageButton(kbdLayout.context).apply {
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
        ImageButton(kbdLayout.context).apply {
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
        ImageButton(kbdLayout.context).apply {
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
        ImageButton(kbdLayout.context).apply {
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
        ImageButton(kbdLayout.context).apply {
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
        ImageButton(kbdLayout.context).apply {
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
        ImageButton(kbdLayout.context).apply {
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
        ImageButton(kbdLayout.context).apply {
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
        ImageButton(kbdLayout.context).apply {
            setImageDrawable(
                ResourcesCompat.getDrawable(
                    this.resources,
                    R.drawable.ic_outline_emoji_emotions_24,
                    null
                )
            )
            setOnClickListener {
                vm.changeMode(10)
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

    private val inputTypeNumbers = arrayOf(
        InputType.TYPE_CLASS_NUMBER,
        InputType.TYPE_NUMBER_FLAG_DECIMAL,
        InputType.TYPE_CLASS_PHONE,
        InputType.TYPE_CLASS_DATETIME,
        InputType.TYPE_DATETIME_VARIATION_TIME,
        InputType.TYPE_NUMBER_VARIATION_PASSWORD
    )
    private val inputTypeTextFlags = listOf(
        InputType.TYPE_CLASS_TEXT
    )

    private var mKeyboardHolding: Long = 300
    private var myKeyboardVibration: Boolean = true
    private var myKeyboardVibrationIntensity: Int = 50
    private val normalInterval: Long = 37
    private var latestMode = 3
    private var savedCursorPosition = 0
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
    private var emojiPageChangeCallback: OnPageChangeCallback? = null
    private val emojiPagerAdapter: EmojiViewPagerAdapter
        get() = EmojiViewPagerAdapter(mutableListOf(), emojiAdapterList, 0, null, null)

    private var savedBottomMarginHeight = 0
    private var savedKbdMainboardHeight = 0

    override fun onCreate() {
        super.onCreate()
        mLifecycle.handleLifecycleEvent(Lifecycle.Event.ON_CREATE)
        mLifecycle.handleLifecycleEvent(Lifecycle.Event.ON_START)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("ClickableViewAccessibility", "DefaultLocale", "NewApi")
    override fun onCreateInputView(): View {
        super.onCreateInputView()
        onConfigurationChanged(resources.configuration)

        kbBinding = DataBindingUtil.bind(kbdLayout)!!
        kbBinding.setVariable(BR.kbservice, this)
        kbBinding.lifecycleOwner = this
        kbBinding.kbviewmodel = vm

        val qwertyEnNormalKBView =
            layoutInflater.inflate(R.layout.fragment_keyboard_qwerty_en_normal, null)
        val qwertyKrNormalKBView =
            layoutInflater.inflate(R.layout.fragment_keyboard_qwerty_kr_normal, null)
        val qwertySpecialKBView =
            layoutInflater.inflate(R.layout.fragment_keyboard_qwerty_special, null)
        val boilerPlateKBView = layoutInflater.inflate(R.layout.fragment_boilerplatetext, null)
        val cursorKBView = layoutInflater.inflate(R.layout.fragment_cursorkeypad, null)
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
        emojiBinding = DataBindingUtil.bind(emojiKBView)!!
        emojiBinding.setVariable(BR.kbservice, this)
        emojiBinding.lifecycleOwner = this
        emojiBinding.kbviewmodel = vm
//        viewholderBpBinding = DataBindingUtil.bind(layoutInflater.inflate(R.layout.viewholder_boilerplates, null))!!
//        viewholderBpBinding.setVariable(BR.kbservice, this)
//        viewholderBpBinding.lifecycleOwner = this
//        viewholderBpBinding.kbviewmodel = vm

        // 상용구창 어댑터 설정
        val bpOnClick: (View) -> Unit = { view ->
            inputBPStrings(view)
        }
        val bpOnLongClick: (View, Int) -> Unit = { view, i ->
            jumpToBp(view, i)
        }
        val boilerplateTextsAdapter = BoilerplateTextAdapter(mutableMapOf(), bpOnClick, bpOnLongClick)

        var currentKRView = qwertyKrNormalKBView
        vm.kbResultBottomMargin.observe(this) {
            val lP = kbLayoutBottomMargin.layoutParams
            val newBottomMarginHeight = it * (resources.displayMetrics.density).toInt()
            savedBottomMarginHeight = newBottomMarginHeight
            lP.height = newBottomMarginHeight
            kbdBackgroundImageView.layoutParams.height = newBottomMarginHeight + savedKbdMainboardHeight
            kbLayoutBottomMargin.layoutParams = lP
        }
        vm.kbKrImeMode.observe(this) {
            currentKRView =
                when (it) {
                    Constants.IME_KR_FLAG_QWERTY -> qwertyKrNormalKBView
                    Constants.IME_KR_FLAG_CHUN -> chunjiinKBView
                    Constants.IME_KR_FLAG_CHUN_AMBI -> chunjiinKBViewAmbi
                    Constants.IME_KR_FLAG_NARAT -> naratguelKBView
                    Constants.IME_KR_FLAG_DAN -> danmoKBView
                    else -> qwertyKrNormalKBView
                }
            if (vm.mode.value == 3) {
                kbdCharaterAreaFramelayout.removeAllViews()
                kbdCharaterAreaFramelayout.addView(currentKRView)
            }
        }
        vm.mode.observe(this) {
            when (it) {
                0, 1, 2 -> {
                    if (latestMode == it) return@observe
                    kbdCharaterAreaFramelayout.removeAllViews()
                    kbdCharaterAreaFramelayout.addView(qwertyEnNormalKBView)
                }
                3, 4 -> {
                    if (latestMode == it) return@observe
                    kbdCharaterAreaFramelayout.removeAllViews()
                    kbdCharaterAreaFramelayout.addView(currentKRView)
                }
                5, 6 -> {
                    if (latestMode == it) return@observe
                    kbdCharaterAreaFramelayout.removeAllViews()
                    kbdCharaterAreaFramelayout.addView(qwertySpecialKBView)
                }
                7 -> {
                    kbdCharaterAreaFramelayout.removeAllViews()
                    kbdCharaterAreaFramelayout.addView(boilerPlateKBView)
                }
                8 -> {
                    kbdCharaterAreaFramelayout.removeAllViews()
                    kbdCharaterAreaFramelayout.addView(cursorKBView)
                }
                9 -> {
                    kbdCharaterAreaFramelayout.removeAllViews()
                    kbdCharaterAreaFramelayout.addView(numberKBView)
                }
                10 -> {
                    kbdCharaterAreaFramelayout.removeAllViews()
                    kbdCharaterAreaFramelayout.addView(emojiKBView)
                }
            }
            showBoilerPlateImageButton.setImageDrawable(
                ResourcesCompat.getDrawable(
                    this.resources,
                    if (it==7) R.drawable.ic_keyboard_black else R.drawable.ic_boilerplatetext_black,
                    null
                )
            )
            showCursorImageButton.setImageDrawable(
                ResourcesCompat.getDrawable(
                    this.resources,
                    if (it==8) R.drawable.ic_keyboard_black else R.drawable.ic_move,
                    null
                )
            )
            showNumberImageButton.setImageDrawable(
                ResourcesCompat.getDrawable(
                    this.resources,
                    if (it==9) R.drawable.ic_keyboard_black else R.drawable.ic_number_keypad,
                    null
                )
            )
            showEmojiImageButton.setImageDrawable(
                ResourcesCompat.getDrawable(
                    this.resources,
                    if (it==10) R.drawable.ic_keyboard_black else R.drawable.ic_outline_emoji_emotions_24,
                    null
                )
            )
            latestMode = it
        }
        vm.kbLongClickInterval.observe(this) {
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
        vm.kbHasVibration.observe(this) { myKeyboardVibration = it }
        vm.kbVibrationIntensity.observe(this) { myKeyboardVibrationIntensity = it }
        vm.kbTheme.observe(this) {
            boilerplateTextsAdapter.setTheme(it)
        }
        vm.kbFontSize.observe(this) { boilerplateTextsAdapter.setFontSize(it) }
        vm.kbNormalKeysFontColor.observe(this) { boilerplateTextsAdapter.setFontColor(it) }
        vm.kbFontType.observe(this) { boilerplateTextsAdapter.setFontType(it) }
        vm.kbLeftSideMargin.observe(this) {
            val lPNum = kbNumLeftSide.layoutParams
            val lPChar = kbCharLeftSide.layoutParams
            lPNum.width = it.toInt() * 3 * (resources.displayMetrics.density).toInt()
            lPChar.width = it.toInt() * 3 * (resources.displayMetrics.density).toInt()
            kbNumLeftSide.layoutParams = lPNum
            kbCharLeftSide.layoutParams = lPChar
        }
        vm.kbRightSideMargin.observe(this) {
            val lPNum = kbNumRightSide.layoutParams
            val lPChar = kbCharRightSide.layoutParams
            lPNum.width = it.toInt() * 3 * (resources.displayMetrics.density).toInt()
            lPChar.width = it.toInt() * 3 * (resources.displayMetrics.density).toInt()
            kbNumRightSide.layoutParams = lPNum
            kbCharRightSide.layoutParams = lPChar
        }
        vm.kbResultedHeight.observe(this) {
            savedKbdMainboardHeight = it.toInt()
            kbdBackgroundImageView.layoutParams.height = (if (vm.kbNumberRowVisibility.value == View.VISIBLE) it.toInt() else (it*0.82).toInt()) + savedBottomMarginHeight
        }
        vm.prefSettingToolbarSetting.observe(this) {
            val toolbarLinearLayout = kbBinding.keyboardToolBarLine

            val sortedToolbarSetting = it.toList().sortedBy { (_, value) -> kotlin.math.abs(value) }.toMap()
            toolbarLinearLayout.removeAllViews()
            sortedToolbarSetting.keys.toList().forEachIndexed { _, key ->
                val ordering = sortedToolbarSetting[key]!!-1
                when (key) {
                    Constants.KEYBOARD_TOOLBAR_ACTIVE_GO_SETTING -> toolbarLinearLayout.reorderChild(goSettingImageButton, ordering)
                    Constants.KEYBOARD_TOOLBAR_ACTIVE_SHOW_BOILERPLATE -> toolbarLinearLayout.reorderChild(showBoilerPlateImageButton, ordering)
                    Constants.KEYBOARD_TOOLBAR_ACTIVE_SELECT_ALL -> toolbarLinearLayout.reorderChild(selectAllImageButton, ordering)
                    Constants.KEYBOARD_TOOLBAR_ACTIVE_COPY -> toolbarLinearLayout.reorderChild(copyImageButton, ordering)
                    Constants.KEYBOARD_TOOLBAR_ACTIVE_CUT -> toolbarLinearLayout.reorderChild(cutImageButton, ordering)
                    Constants.KEYBOARD_TOOLBAR_ACTIVE_PASTE -> toolbarLinearLayout.reorderChild(pasteImageButton, ordering)
                    Constants.KEYBOARD_TOOLBAR_ACTIVE_SHOW_CURSOR -> toolbarLinearLayout.reorderChild(showCursorImageButton, ordering)
                    Constants.KEYBOARD_TOOLBAR_ACTIVE_SHOW_NUMBER -> toolbarLinearLayout.reorderChild(showNumberImageButton, ordering)
                    Constants.KEYBOARD_TOOLBAR_ACTIVE_SHOW_EMOJI -> toolbarLinearLayout.reorderChild(showEmojiImageButton, ordering)
                }
            }
        }
        boilerPlateBinding.recyviewBoilerplateBoilerplatetextItemsContainer.apply {
            adapter = boilerplateTextsAdapter
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        }
        vm.boilerplateTexts.observe(this) {
            boilerplateTextsAdapter.setBoilerplateTextsList(it)
        }

        emojisViewPager.adapter = emojiPagerAdapter
        vm.kbEmojiColumns.observe(
            this
        ) { (emojisViewPager.adapter as EmojiViewPagerAdapter).changeColumns(it) }
        vm.kbRecentlyUsedEmoticons.observe(this) {
            val jsonArray = JSONArray(it)
            val arr = mutableListOf<String>()

            for (i in 0 until jsonArray.length()) {
                if (jsonArray.optString(i) != "") arr.add(jsonArray.optString(i))
            }
            (emojisViewPager.adapter as EmojiViewPagerAdapter).changeAdapter(arr)
        }

        val emojiIconClickListeners = MutableList(KeyboardUtil.emojiIconList.size) { View.OnClickListener { } }
        for (i in emojiIconClickListeners.indices) {
            emojiIconClickListeners[i] = View.OnClickListener {
                emojisViewPager.currentItem = i
            }
        }

        customEmojiIndicator.createIconPanel(
            iconsList = KeyboardUtil.emojiIconList,
            position = 1,
            clickListeners = emojiIconClickListeners
        )
        (emojisViewPager.adapter as EmojiViewPagerAdapter).initListener(
            object : EmojiRecyclerAdapter.OnItemClickListener {
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
        emojiPageChangeCallback = object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                val mPosition = emojisViewPager.currentItem
                customEmojiIndicator.selectPosition(position = mPosition)
                emojiScrollView.smoothScrollTo(getEmojiIconXPosition(emojiScrollView, mPosition), 0)

                /** emojisViewPager.changeAdapter()로 데이터셋 변경 알림시킬 경우, 뷰페이저 1페이지에서 스크롤이 자꾸 고정되는 현상을 방지하고자
                 *  데이터셋 변경 알림은 notifyE0DataSetChanged으로 뷰페이저 페이지 변경 시 실행하도록 함
                 **/
                if (mPosition == 0) (emojisViewPager.adapter as EmojiViewPagerAdapter).notifyE0DataSetChanged()
            }
        }
        emojisViewPager.registerOnPageChangeCallback(emojiPageChangeCallback!!)

        return kbdLayout
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
        if (vm.kbHasAutoCapitalization.value!!
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
        println("인풋타입 : ${currentInputEditorInfo.inputType}")
        println("숫자타입 : ${inputTypeNumbers.contentDeepToString()}")
        println("숫자: ${currentInputEditorInfo.inputType}")
        for (type in inputTypeTextFlags) {
            if (currentInputEditorInfo.inputType or type == currentInputEditorInfo.inputType) {

                println("겹침? ${currentInputEditorInfo.inputType or type} vs $type")
                vm.changeMode(new = 1, restart= true)
                emojisViewPager.setCurrentItem(1, false)
                return
            }
        }
        vm.changeMode(new = 9, restart = true)
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
        mLifecycle.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        emojisViewPager.unregisterOnPageChangeCallback(emojiPageChangeCallback!!)
        emojiPageChangeCallback = null
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
        keyboardFunctions.inputChar(
            clearComposing = { clearComposing() },
            button = button,
            mode = vm.mode.value!!,
            krIME = vm.kbKrImeMode.value!!,
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
        keyboardFunctions.inputSpecialButton(
            clearComposing = { clearComposing() },
            button = button,
            autoModeChange = vm.kbHasAutoModeChange.value!!,
            changeMode3 = { vm.changeMode(3) },
            myKeyboardVibration = myKeyboardVibration,
            vibrateByButton = { vibrateByButton() }
        )
    }

    fun longClickInputSpecial(button: View): Boolean {
        return keyboardFunctions.inputSpecialLongClicked(
            clearComposing = { clearComposing() },
            button = button
        )
    }

    private fun inputBPStrings(button: View) {
        keyboardFunctions.inputBPStrings(
            clearComposing = { clearComposing() },
            button = button,
            myKeyboardVibration = myKeyboardVibration,
            vibrateByButton = { vibrateByButton() }
        )
    }

    fun inputEnter() {
        keyboardFunctions.inputEnter(
            clearComposing = { clearComposing() },
            myKeyboardVibration = myKeyboardVibration,
            vibrateByButton = { vibrateByButton() })
    }

    private fun inputDelete() {
        keyboardFunctions.inputDelete(
            mode = vm.mode.value!!,
            krIME = vm.kbKrImeMode.value!!,
            clearComposing = { clearComposing() },
            changeMode3 = { vm.changeMode(3) },
            myKeyboardVibration = myKeyboardVibration,
            vibrateByButton = { vibrateByButton() }
        )
    }

    private fun inputForwardDelete() {
        keyboardFunctions.inputForwardDelete(
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

    fun startApp(uri: String = Constants.PACKAGE_NAME) {
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
        intent.putExtra(MainActivity.INTENT_BOILERPLATE_TAB, index)
        startActivity(intent)
        return false
    }

    @RequiresApi(Build.VERSION_CODES.R)
    fun selectWord() {
        vm.switchSelectingMode(!vm.isSelecting.value!!)
    }

    fun selectAllTexts() {
        keyboardFunctions.selectAllTexts(
            clearComposing = { clearComposing() },
            setSavedCursorPositionDefault = { setSavedCursorPositionDefault() }
        )
    }

    private fun moveCursorUp() {
        keyboardFunctions.moveCursorUp(
            clearComposing = { clearComposing() },
            isSelectingMode = vm.isSelecting.value!!,
            savedCursorPosition = savedCursorPosition
        )
    }

    private fun moveCursorDown() {
        keyboardFunctions.moveCursorDown(
            clearComposing = { clearComposing() },
            isSelectingMode = vm.isSelecting.value!!,
            savedCursorPosition = savedCursorPosition
        )
    }

    private fun moveCursorLeft() {
        keyboardFunctions.moveCursorLeft(
            clearComposing = { clearComposing() },
            isSelectingMode = vm.isSelecting.value!!,
            savedCursorPosition = savedCursorPosition
        )
    }

    private fun moveCursorRight() {
        keyboardFunctions.moveCursorRight(
            clearComposing = { clearComposing() },
            isSelectingMode = vm.isSelecting.value!!,
            savedCursorPosition = savedCursorPosition
        )
    }

    private fun moveCursorFirst() {
        keyboardFunctions.moveCursorFirst(
            clearComposing = { clearComposing() },
            isSelectingMode = vm.isSelecting.value!!,
            savedCursorPosition = savedCursorPosition
        )
    }

    private fun moveCursorLast() {
        keyboardFunctions.moveCursorLast(
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
        keyboardFunctions.copyText(
            clearComposing = { clearComposing() },
            switchSelectingMode = { vm.switchSelectingMode(false) }
        )
    }

    // 텍스트 잘라내기
    fun cutText() {
        keyboardFunctions.cutText(
            clearComposing = { clearComposing() },
            switchSelectingMode = { vm.switchSelectingMode(false) }
        )
    }

    // 텍스트 붙여넣기
    fun pasteText() {
        keyboardFunctions.pasteText(
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

    private fun getEmojiIconXPosition(view: FrameLayout, position: Int): Int {
        val _position: Float = (view.width/ changeDpToPx(Constants.EMOJI_ICON_WIDTH)) / 2F
        return (changeDpToPx(Constants.EMOJI_ICON_WIDTH) * (position - _position) + changeDpToPx(Constants.EMOJI_ICON_WIDTH) /2).toInt()
    }

    private fun changeDpToPx(dp: Int): Int {
        return (dp * Resources.getSystem().displayMetrics.density).toInt()
    }
}