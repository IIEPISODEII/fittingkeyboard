package com.sb.fittingkeyboard.service

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import android.content.res.Resources
import android.graphics.*
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
import com.sb.fittingKeyboard.databinding.*
import com.sb.fittingkeyboard.Constants
import com.sb.fittingkeyboard.keyboardsettings.ui.MainActivity
import com.sb.fittingkeyboard.service.keyboardtype.MainFrameKeyboard
import com.sb.fittingkeyboard.service.keyboardtype.boilerplate.BoilerplateTypedKeyboard
import com.sb.fittingkeyboard.service.keyboardtype.chun.ChunjiinBasicTypedKeyboard
import com.sb.fittingkeyboard.service.keyboardtype.chunleft.ChunjiinLeftTypedKeyboard
import com.sb.fittingkeyboard.service.keyboardtype.core.InputTypeState
import com.sb.fittingkeyboard.service.keyboardtype.danmo.DanmoumTypedKeyboard
import com.sb.fittingkeyboard.service.keyboardtype.emoji.EmojiRecyclerAdapter
import com.sb.fittingkeyboard.service.keyboardtype.emoji.EmojiRecyclerLiveDataAdapter
import com.sb.fittingkeyboard.service.keyboardtype.emoji.EmojiViewPagerAdapter
import com.sb.fittingkeyboard.service.keyboardtype.emoji.indicator.CustomIndicator
import com.sb.fittingkeyboard.service.keyboardtype.english.QwertyEnglishTypedKeyboard
import com.sb.fittingkeyboard.service.keyboardtype.narat.NaratguelTypedKeyboard
import com.sb.fittingkeyboard.service.keyboardtype.qwertykr.QwertyKrTypedKeyboard
import com.sb.fittingkeyboard.service.keyboardtype.special.QwertySpecialTypedKeyboard
import com.sb.fittingkeyboard.service.koreanautomata.HanguelChunjiin
import com.sb.fittingkeyboard.service.koreanautomata.HanguelDanmoum
import com.sb.fittingkeyboard.service.koreanautomata.HanguelNARATGUL
import com.sb.fittingkeyboard.service.koreanautomata.HanguelQWERTY
import com.sb.fittingkeyboard.service.util.*
import com.sb.fittingkeyboard.service.viewmodel.KeyboardViewModel
import org.json.JSONArray

@SuppressLint("ClickableViewAccessibility")
class MainKeyboardService : InputMethodService(), LifecycleOwner {

    private lateinit var kbBinding: LayoutKeyboardBinding
    private lateinit var mainFrameKeyboard: MainFrameKeyboard
    private lateinit var qwertyEnNormalBinding: FragmentKeyboardQwertyEnNormalBinding
    private lateinit var qwertyEnKeyboard: QwertyEnglishTypedKeyboard
    private lateinit var qwertyKrNormalBinding: FragmentKeyboardQwertyKrNormalBinding
    private lateinit var qwertyKrKeyboard: QwertyKrTypedKeyboard
    private lateinit var chunjiinBinding: FragmentKeyboardChunjiinBasicBinding
    private lateinit var chunjiinBasicKeyboard: ChunjiinBasicTypedKeyboard
    private lateinit var chunLeftKBBinding: FragmentKeyboardChunjiinLeftBinding
    private lateinit var chunjiinLeftKeyboard: ChunjiinLeftTypedKeyboard
    private lateinit var naratguelBinding: FragmentKeyboardNaratgulBasicBinding
    private lateinit var naratguelKeyboard: NaratguelTypedKeyboard
    private lateinit var danmoumKBBinding: FragmentKeyboardDanmoumBinding
    private lateinit var danmoumKeyboard: DanmoumTypedKeyboard
    private lateinit var specialKBBinding: FragmentKeyboardQwertySpecialBinding
    private lateinit var specialKeyboard: QwertySpecialTypedKeyboard
    private lateinit var numberBinding: FragmentKeyboardNumberBinding
    private lateinit var boilerPlateBinding: FragmentBoilerplatetextBinding
    private lateinit var boilerPlateKeyboard: BoilerplateTypedKeyboard
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
                vm.setInputTypeState(InputTypeState.BOILERPLATE)
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
                vm.setInputTypeState(InputTypeState.CURSOR)
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
                vm.setInputTypeState(InputTypeState.NUMBER)
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
                vm.setInputTypeState(InputTypeState.EMOJI)
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
    private var latestInputTypeState = InputTypeState.KR_NORMAL
    private var savedCursorPosition = 0
    private val emojiAdapterList = listOf(
        EmojiRecyclerAdapter(e1SmileysAndEmoticons),
        EmojiRecyclerAdapter(e2PeopleAndBody),
        EmojiRecyclerAdapter(e3AnimalsAndNature),
        EmojiRecyclerAdapter(e4FoodAndDrink),
        EmojiRecyclerAdapter(e5TravelAndPlaces),
        EmojiRecyclerAdapter(e6Activities),
        EmojiRecyclerAdapter(e7Objects),
        EmojiRecyclerAdapter(e8Symbols),
        EmojiRecyclerAdapter(e9Flags)
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
        mainFrameKeyboard = MainFrameKeyboard(
            binding = kbBinding,
            imeService = this
        )
        mainFrameKeyboard.init()

        val qwertyEnNormalKBView = layoutInflater.inflate(R.layout.fragment_keyboard_qwerty_en_normal, null)
        val qwertyKrNormalKBView = layoutInflater.inflate(R.layout.fragment_keyboard_qwerty_kr_normal, null)
        val qwertySpecialKBView = layoutInflater.inflate(R.layout.fragment_keyboard_qwerty_special, null)
        val boilerPlateKBView = layoutInflater.inflate(R.layout.fragment_boilerplatetext, null)
        val cursorKBView = layoutInflater.inflate(R.layout.fragment_cursorkeypad, null)
        val numberKBView = layoutInflater.inflate(R.layout.fragment_keyboard_number, null)
        val chunjiinKBView = layoutInflater.inflate(R.layout.fragment_keyboard_chunjiin_basic, null)
        val chunjiinLeftKBView = layoutInflater.inflate(R.layout.fragment_keyboard_chunjiin_left, null)
        val danmoKBView = layoutInflater.inflate(R.layout.fragment_keyboard_danmoum, null)
        val naratguelKBView = layoutInflater.inflate(R.layout.fragment_keyboard_naratgul_basic, null)

        qwertyEnNormalBinding = DataBindingUtil.bind(qwertyEnNormalKBView)!!
        qwertyEnNormalBinding.setVariable(BR.kbservice, this)
        qwertyEnNormalBinding.lifecycleOwner = this
        qwertyEnNormalBinding.kbviewmodel = vm
        qwertyEnKeyboard = QwertyEnglishTypedKeyboard(
            binding = qwertyEnNormalBinding,
            imeService = this
        )
        qwertyEnKeyboard.init()

        qwertyKrNormalBinding = DataBindingUtil.bind(qwertyKrNormalKBView)!!
        qwertyKrNormalBinding.setVariable(BR.kbservice, this)
        qwertyKrNormalBinding.lifecycleOwner = this
        qwertyKrNormalBinding.kbviewmodel = vm
        qwertyKrKeyboard = QwertyKrTypedKeyboard(
            binding = qwertyKrNormalBinding,
            imeService = this
        )
        qwertyKrKeyboard.init()

        chunjiinBinding = DataBindingUtil.bind(chunjiinKBView)!!
        chunjiinBinding.setVariable(BR.kbservice, this)
        chunjiinBinding.lifecycleOwner = this
        chunjiinBinding.kbviewmodel = vm
        chunjiinBasicKeyboard = ChunjiinBasicTypedKeyboard(
            binding = chunjiinBinding,
            imeService = this
        )
        chunjiinBasicKeyboard.init()

        naratguelBinding = DataBindingUtil.bind(naratguelKBView)!!
        naratguelBinding.setVariable(BR.kbservice, this)
        naratguelBinding.lifecycleOwner = this
        naratguelBinding.kbviewmodel = vm
        naratguelKeyboard = NaratguelTypedKeyboard(
            binding = naratguelBinding,
            imeService = this
        )
        naratguelKeyboard.init()

        danmoumKBBinding = DataBindingUtil.bind(danmoKBView)!!
        danmoumKBBinding.setVariable(BR.kbservice, this)
        danmoumKBBinding.lifecycleOwner = this
        danmoumKBBinding.kbviewmodel = vm
        danmoumKeyboard = DanmoumTypedKeyboard(
            binding = danmoumKBBinding,
            imeService = this
        )
        danmoumKeyboard.init()

        specialKBBinding = DataBindingUtil.bind(qwertySpecialKBView)!!
        specialKBBinding.setVariable(BR.kbservice, this)
        specialKBBinding.lifecycleOwner = this
        specialKBBinding.kbviewmodel = vm
        specialKeyboard = QwertySpecialTypedKeyboard(
            binding = specialKBBinding,
            imeService = this
        )
        specialKeyboard.init()

        chunLeftKBBinding = DataBindingUtil.bind(chunjiinLeftKBView)!!
        chunLeftKBBinding.setVariable(BR.kbservice, this)
        chunLeftKBBinding.lifecycleOwner = this
        chunLeftKBBinding.kbviewmodel = vm
        chunjiinLeftKeyboard = ChunjiinLeftTypedKeyboard(
            binding = chunLeftKBBinding,
            imeService = this
        )
        chunjiinLeftKeyboard.init()

        boilerPlateBinding = DataBindingUtil.bind(boilerPlateKBView)!!
        boilerPlateBinding.setVariable(BR.kbservice, this)
        boilerPlateBinding.lifecycleOwner = this
        boilerPlateBinding.kbviewmodel = vm
        boilerPlateKeyboard = BoilerplateTypedKeyboard(
            binding = boilerPlateBinding,
            imeService = this
        )

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

        // 상용구창 어댑터 설정
        val bpOnClick: (View) -> Unit = { view ->
            inputBPStrings(view)
        }
        val bpOnLongClick: (View, Int) -> Unit = { view, i ->
            jumpToBp(view, i)
        }
        val boilerplateTextsAdapter = BoilerplateTextAdapter(mutableMapOf(), bpOnClick, bpOnLongClick)

        // 설정값으로 키보드 레이아웃 및 기능 변경 
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
                    Constants.IME_KR_FLAG_CHUN_AMBI -> chunjiinLeftKBView
                    Constants.IME_KR_FLAG_NARAT -> naratguelKBView
                    Constants.IME_KR_FLAG_DAN -> danmoKBView
                    else -> qwertyKrNormalKBView
                }
            if (vm.inputTypeState.value == InputTypeState.KR_NORMAL) {
                kbdCharaterAreaFramelayout.removeAllViews()
                kbdCharaterAreaFramelayout.addView(currentKRView)
            }
        }
        vm.inputTypeState.observe(this) {
            when (it) {
                InputTypeState.EN_BOLD_UPPER, InputTypeState.EN_UPPER, InputTypeState.EN_LOWER -> {
                    if (latestInputTypeState == it) return@observe
                    kbdCharaterAreaFramelayout.removeAllViews()
                    kbdCharaterAreaFramelayout.addView(qwertyEnNormalKBView)
                }
                InputTypeState.KR_NORMAL, InputTypeState.KR_SHIFT -> {
                    if (latestInputTypeState == it) return@observe
                    kbdCharaterAreaFramelayout.removeAllViews()
                    kbdCharaterAreaFramelayout.addView(currentKRView)
                }
                InputTypeState.SPECIAL_FIRST, InputTypeState.SPECIAL_SECOND -> {
                    if (latestInputTypeState == it) return@observe
                    kbdCharaterAreaFramelayout.removeAllViews()
                    kbdCharaterAreaFramelayout.addView(qwertySpecialKBView)
                }
                InputTypeState.BOILERPLATE -> {
                    kbdCharaterAreaFramelayout.removeAllViews()
                    kbdCharaterAreaFramelayout.addView(boilerPlateKBView)
                }
                InputTypeState.CURSOR -> {
                    kbdCharaterAreaFramelayout.removeAllViews()
                    kbdCharaterAreaFramelayout.addView(cursorKBView)
                }
                InputTypeState.NUMBER -> {
                    kbdCharaterAreaFramelayout.removeAllViews()
                    kbdCharaterAreaFramelayout.addView(numberKBView)
                }
                InputTypeState.EMOJI -> {
                    kbdCharaterAreaFramelayout.removeAllViews()
                    kbdCharaterAreaFramelayout.addView(emojiKBView)
                }
                else -> {}
            }
            showBoilerPlateImageButton.setImageDrawable(
                ResourcesCompat.getDrawable(
                    this.resources,
                    if (it == InputTypeState.BOILERPLATE) R.drawable.ic_keyboard_black else R.drawable.ic_boilerplatetext_black,
                    null
                )
            )
            showCursorImageButton.setImageDrawable(
                ResourcesCompat.getDrawable(
                    this.resources,
                    if (it == InputTypeState.CURSOR) R.drawable.ic_keyboard_black else R.drawable.ic_move,
                    null
                )
            )
            showNumberImageButton.setImageDrawable(
                ResourcesCompat.getDrawable(
                    this.resources,
                    if (it == InputTypeState.NUMBER) R.drawable.ic_keyboard_black else R.drawable.ic_number_keypad,
                    null
                )
            )
            showEmojiImageButton.setImageDrawable(
                ResourcesCompat.getDrawable(
                    this.resources,
                    if (it == InputTypeState.EMOJI) R.drawable.ic_keyboard_black else R.drawable.ic_outline_emoji_emotions_24,
                    null
                )
            )
            latestInputTypeState = it
        }
        vm.kbLongClickInterval.observe(this) {
            mKeyboardHolding = it.toLong() + 100
            for (rListener in repeatTouchListenerChars) {
                rListener.setInitialInterval(mKeyboardHolding)
            }
            for (rListener in repeatTouchListenerSpecials) {
                rListener.setInitialInterval(mKeyboardHolding)
            }
            for (rListener in repeatTouchListenerDels) {
                rListener.setInitialInterval(mKeyboardHolding)
            }
            rListenerCursorDeleteNextChar.setInitialInterval(mKeyboardHolding)
        }
        vm.kbHasVibration.observe(this) { myKeyboardVibration = it }
        vm.kbVibrationIntensity.observe(this) { myKeyboardVibrationIntensity = it }
        vm.kbTheme.observe(this) {
            boilerplateTextsAdapter.setTheme(it)
        }
        vm.kbFunctionKeysFontColor.observe(this) {}
        vm.kbFontSize.observe(this) {
            boilerplateTextsAdapter.setFontSize(it)
        }
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
        vm.kbHasSwipeableSpace.observe(this) {}
        
        emojisViewPager.adapter = emojiPagerAdapter
        vm.kbEmojiColumns.observe(this) { (emojisViewPager.adapter as EmojiViewPagerAdapter).changeColumns(it) }
        vm.kbRecentlyUsedEmoticons.observe(this) {
            val jsonArray = JSONArray(it)
            val arr = mutableListOf<String>()

            for (i in 0 until jsonArray.length()) {
                if (jsonArray.optString(i).isNotBlank()) arr.add(jsonArray.optString(i))
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
            && vm.inputTypeState.value == InputTypeState.EN_LOWER
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
                vm.setInputTypeState(InputTypeState.EN_UPPER)
            }
        }
    }

    @SuppressLint("ClickableViewAccessibility", "NewApi")
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onStartInputView(info: EditorInfo?, restarting: Boolean) {
        super.onStartInputView(info, restarting)
        mLifecycle.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
        for (type in inputTypeTextFlags) {
            if (currentInputEditorInfo.inputType or type == currentInputEditorInfo.inputType) {
                vm.setInputTypeState(newState = InputTypeState.EN_UPPER, restart= true)
                emojisViewPager.setCurrentItem(1, false)
                return
            }
        }
        for (type in inputTypeNumbers) {
            if (currentInputEditorInfo.inputType or type == currentInputEditorInfo.inputType) {
                vm.setInputTypeState(newState = InputTypeState.NUMBER, restart = true)
                return
            }
        }
        emojisViewPager.setCurrentItem(1, false)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        onFinishInputView(finishingInput = true)

        vm.changeOrientation(newConfig.orientation)
    }

    override fun onStartInput(attribute: EditorInfo?, restarting: Boolean) {
        super.onStartInput(attribute, restarting)
        clearComposing()
    }

    override fun onFinishInputView(finishingInput: Boolean) {
        super.onFinishInputView(finishingInput)
        clearComposing()
        vm.switchSelectingTextMode(false)
    }

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onDestroy() {
        super.onDestroy()
        mLifecycle.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        emojisViewPager.unregisterOnPageChangeCallback(emojiPageChangeCallback!!)
        emojiPageChangeCallback = null
    }

    var rListenerCursorDeleteNextChar =
        RepeatTouchListener(
            initialInterval = mKeyboardHolding,
            normalInterval = normalInterval,
            actionDownEvent = { _, _ -> deleteNextChar() }
        )

    fun inputChar(button: View) {
        keyboardFunctions.inputChar(
            clearComposing = { clearComposing() },
            button = button,
            inputTypeState = vm.inputTypeState.value!!,
            krIME = vm.kbKrImeMode.value!!,
            myKeyboardVibration = myKeyboardVibration,
            vibrateByButton = { vibrateByButton() },
            setInputTypeStateToKR = { setInputTypeState(InputTypeState.KR_NORMAL) },
            setInputTypeStateToEN = { setInputTypeState(InputTypeState.EN_LOWER) }
        )
    }

    fun initChunJiIn() {
        clearComposing()
    }

    fun inputSpecial(button: View) {
        keyboardFunctions.inputSpecialButton(
            button = button,
            onClick = {
                clearComposing()
                if (myKeyboardVibration) vibrateByButton()
            }
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
            inputTypeState = vm.inputTypeState.value!!,
            inputMethodKR = vm.kbKrImeMode.value!!,
            clearComposing = { clearComposing() },
            setInputTypeStateToKR = { vm.setInputTypeState(InputTypeState.KR_NORMAL) },
            myKeyboardVibration = myKeyboardVibration,
            vibrateByButton = { vibrateByButton() }
        )
    }

    private fun deleteNextChar() {
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

    fun selectAllTexts() {
        keyboardFunctions.selectAllTexts(
            clearComposing = { clearComposing() },
            setSavedCursorPositionDefault = { setSavedCursorPositionDefault() }
        )
    }

    // 엔터키, 특수문자 전환 키를 LongClick할 경우 작동
    fun addFunction(addOn: Int): Boolean {
        when (addOn) {
            1 -> vm.setInputTypeState(InputTypeState.BOILERPLATE)
            2 -> vm.setInputTypeState(InputTypeState.CURSOR)
            3 -> vm.setInputTypeState(InputTypeState.NUMBER)
            4 -> vm.setInputTypeState(InputTypeState.EMOJI)
            else -> {}
        }
        vibrateByButton()
        return true
    }

    // 키보드 전환
    fun setInputTypeState(newState: InputTypeState) {
        vm.setInputTypeState(newState)
        if (myKeyboardVibration) vibrateByButton()
    }

    // 텍스트 복사하기
    fun copyText() {
        keyboardFunctions.copyText(
            clearComposing = { clearComposing() },
            switchSelectingMode = { vm.switchSelectingTextMode(false) }
        )
    }

    // 텍스트 잘라내기
    fun cutText() {
        keyboardFunctions.cutText(
            clearComposing = { clearComposing() },
            switchSelectingMode = { vm.switchSelectingTextMode(false) }
        )
    }

    // 텍스트 붙여넣기
    fun pasteText() {
        keyboardFunctions.pasteText(
            clearComposing = { clearComposing() },
            switchSelectingMode = { vm.switchSelectingTextMode(false) }
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

    var repeatTouchListenerChars = Array(200) {
        RepeatTouchListener(
            initialInterval = mKeyboardHolding,
            normalInterval = normalInterval,
            actionDownEvent = { view, _ -> inputChar(view) }
        )
    }
    var repeatTouchListenerSpecials = Array(100) {
        RepeatTouchListener(
            initialInterval = mKeyboardHolding,
            normalInterval = normalInterval,
            actionDownEvent = { view, _ -> inputSpecial(view) }
        )
    }
    var repeatTouchListenerDels = Array(100) {
        RepeatTouchListener(
            initialInterval = mKeyboardHolding,
            normalInterval = normalInterval,
            actionDownEvent = { _, _ -> inputDelete() }
        )
    }

    private fun getEmojiIconXPosition(view: FrameLayout, position: Int): Int {
        val _position: Float = (view.width/ changeDpToPx(Constants.EMOJI_ICON_WIDTH)) / 2F
        return (changeDpToPx(Constants.EMOJI_ICON_WIDTH) * (position - _position) + changeDpToPx(Constants.EMOJI_ICON_WIDTH) /2).toInt()
    }

    private fun changeDpToPx(dp: Int): Int {
        return (dp * Resources.getSystem().displayMetrics.density).toInt()
    }
}