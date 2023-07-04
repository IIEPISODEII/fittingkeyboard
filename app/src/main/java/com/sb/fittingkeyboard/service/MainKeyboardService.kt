package com.sb.fittingkeyboard.service

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.graphics.*
import android.inputmethodservice.InputMethodService
import android.os.Build
import android.text.InputType
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputConnection.CURSOR_UPDATE_IMMEDIATE
import android.view.inputmethod.InputConnection.GET_TEXT_WITH_STYLES
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.*
import com.sb.fittingKeyboard.BR
import com.sb.fittingKeyboard.R
import com.sb.fittingKeyboard.databinding.*
import com.sb.fittingkeyboard.Constants
import com.sb.fittingkeyboard.service.keyboardtype.MainFrameKeyboard
import com.sb.fittingkeyboard.service.keyboardtype.boilerplate.BoilerplateTypedKeyboard
import com.sb.fittingkeyboard.service.keyboardtype.chun.ChunjiinBasicTypedKeyboard
import com.sb.fittingkeyboard.service.keyboardtype.chunleft.ChunjiinLeftTypedKeyboard
import com.sb.fittingkeyboard.service.keyboardtype.core.InputTypeState
import com.sb.fittingkeyboard.service.keyboardtype.cursor.CursorTypedKeyboard
import com.sb.fittingkeyboard.service.keyboardtype.danmo.DanmoumTypedKeyboard
import com.sb.fittingkeyboard.service.keyboardtype.emoji.EmojiTypedKeyboard
import com.sb.fittingkeyboard.service.keyboardtype.english.QwertyEnglishTypedKeyboard
import com.sb.fittingkeyboard.service.keyboardtype.narat.NaratguelTypedKeyboard
import com.sb.fittingkeyboard.service.keyboardtype.number.NumberTypedKeyboard
import com.sb.fittingkeyboard.service.keyboardtype.qwertykr.QwertyKrTypedKeyboard
import com.sb.fittingkeyboard.service.keyboardtype.special.QwertySpecialTypedKeyboard
import com.sb.fittingkeyboard.service.koreanautomata.HanguelChunjiin
import com.sb.fittingkeyboard.service.koreanautomata.HanguelDanmoum
import com.sb.fittingkeyboard.service.koreanautomata.HanguelNARATGUL
import com.sb.fittingkeyboard.service.koreanautomata.HanguelQWERTY
import com.sb.fittingkeyboard.service.util.*
import com.sb.fittingkeyboard.service.viewmodel.KeyboardViewModel

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
    private lateinit var numberKeyboard: NumberTypedKeyboard
    private lateinit var boilerPlateBinding: FragmentBoilerplatetextBinding
    private lateinit var boilerPlateKeyboard: BoilerplateTypedKeyboard
    private lateinit var cursorBinding: FragmentCursorkeypadBinding
    private lateinit var cursorKeyboard: CursorTypedKeyboard
    private lateinit var emojiBinding: FragmentEmojiBinding
    private lateinit var emojiKeyboard: EmojiTypedKeyboard

    private val viewModel: KeyboardViewModel by lazy { ViewModelProvider.AndroidViewModelFactory.getInstance(application).create(KeyboardViewModel::class.java) }
    private val kbdLayout: View by lazy { layoutInflater.inflate(R.layout.layout_keyboard, null) }
    private val kbdCharaterAreaFramelayout: FrameLayout by lazy { kbdLayout.findViewById(R.id.framelayout_keyboard_character_rows) }
    private val kbdBackgroundImageView: ImageView by lazy { kbdLayout.findViewById(R.id.iv_keyboard_bg_image) }
    private val kbLayoutBottomMargin: View by lazy { kbdLayout.findViewById(R.id.view_keyboard_bottom_margin) }
    private val kbCharLeftSide: FrameLayout by lazy { kbdLayout.findViewById(R.id.framelayout_keyboard_character_rows_left_margin) }
    private val kbCharRightSide: FrameLayout by lazy { kbdLayout.findViewById(R.id.framelayout_keyboard_character_rows_right_margin) }
    private val kbNumLeftSide: Button by lazy { kbdLayout.findViewById(R.id.view_keyboard_number_row_left_margin) }
    private val kbNumRightSide: Button by lazy { kbdLayout.findViewById(R.id.view_keyboard_number_row_right_margin) }
    private val emojiKBView by lazy { layoutInflater.inflate(R.layout.fragment_emoji, null) }
    private val mLifecycle = LifecycleRegistry(this)

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

    private var latestInputTypeState = InputTypeState.KR_NORMAL

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
        kbBinding.kbviewmodel = viewModel
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
        qwertyEnNormalBinding.kbviewmodel = viewModel
        qwertyEnKeyboard = QwertyEnglishTypedKeyboard(
            binding = qwertyEnNormalBinding,
            imeService = this
        )
        qwertyEnKeyboard.init()

        qwertyKrNormalBinding = DataBindingUtil.bind(qwertyKrNormalKBView)!!
        qwertyKrNormalBinding.setVariable(BR.kbservice, this)
        qwertyKrNormalBinding.lifecycleOwner = this
        qwertyKrNormalBinding.kbviewmodel = viewModel
        qwertyKrKeyboard = QwertyKrTypedKeyboard(
            binding = qwertyKrNormalBinding,
            imeService = this
        )
        qwertyKrKeyboard.init()

        chunjiinBinding = DataBindingUtil.bind(chunjiinKBView)!!
        chunjiinBinding.setVariable(BR.kbservice, this)
        chunjiinBinding.lifecycleOwner = this
        chunjiinBinding.kbviewmodel = viewModel
        chunjiinBasicKeyboard = ChunjiinBasicTypedKeyboard(
            binding = chunjiinBinding,
            imeService = this
        )
        chunjiinBasicKeyboard.init()

        naratguelBinding = DataBindingUtil.bind(naratguelKBView)!!
        naratguelBinding.setVariable(BR.kbservice, this)
        naratguelBinding.lifecycleOwner = this
        naratguelBinding.kbviewmodel = viewModel
        naratguelKeyboard = NaratguelTypedKeyboard(
            binding = naratguelBinding,
            imeService = this
        )
        naratguelKeyboard.init()

        danmoumKBBinding = DataBindingUtil.bind(danmoKBView)!!
        danmoumKBBinding.setVariable(BR.kbservice, this)
        danmoumKBBinding.lifecycleOwner = this
        danmoumKBBinding.kbviewmodel = viewModel
        danmoumKeyboard = DanmoumTypedKeyboard(
            binding = danmoumKBBinding,
            imeService = this
        )
        danmoumKeyboard.init()

        specialKBBinding = DataBindingUtil.bind(qwertySpecialKBView)!!
        specialKBBinding.setVariable(BR.kbservice, this)
        specialKBBinding.lifecycleOwner = this
        specialKBBinding.kbviewmodel = viewModel
        specialKeyboard = QwertySpecialTypedKeyboard(
            binding = specialKBBinding,
            imeService = this
        )
        specialKeyboard.init()

        chunLeftKBBinding = DataBindingUtil.bind(chunjiinLeftKBView)!!
        chunLeftKBBinding.setVariable(BR.kbservice, this)
        chunLeftKBBinding.lifecycleOwner = this
        chunLeftKBBinding.kbviewmodel = viewModel
        chunjiinLeftKeyboard = ChunjiinLeftTypedKeyboard(
            binding = chunLeftKBBinding,
            imeService = this
        )
        chunjiinLeftKeyboard.init()

        boilerPlateBinding = DataBindingUtil.bind(boilerPlateKBView)!!
        boilerPlateBinding.setVariable(BR.kbservice, this)
        boilerPlateBinding.lifecycleOwner = this
        boilerPlateBinding.kbviewmodel = viewModel
        boilerPlateKeyboard = BoilerplateTypedKeyboard(
            binding = boilerPlateBinding,
            imeService = this
        )
        boilerPlateKeyboard.init()

        cursorBinding = DataBindingUtil.bind(cursorKBView)!!
        cursorBinding.setVariable(BR.kbservice, this)
        cursorBinding.lifecycleOwner = this
        cursorBinding.kbviewmodel = viewModel
        cursorKeyboard = CursorTypedKeyboard(
            binding = cursorBinding,
            imeService = this
        )
        cursorKeyboard.init()

        numberBinding = DataBindingUtil.bind(numberKBView)!!
        numberBinding.setVariable(BR.kbservice, this)
        numberBinding.lifecycleOwner = this
        numberBinding.kbviewmodel = viewModel
        numberKeyboard = NumberTypedKeyboard(
            binding = numberBinding,
            imeService = this
        )
        numberKeyboard.init()

        emojiBinding = DataBindingUtil.bind(emojiKBView)!!
        emojiBinding.setVariable(BR.kbservice, this)
        emojiBinding.lifecycleOwner = this
        emojiBinding.kbviewmodel = viewModel
        emojiKeyboard = EmojiTypedKeyboard(
            binding = emojiBinding,
            imeService = this
        )
        emojiKeyboard.init()


        // 설정값으로 키보드 레이아웃 및 기능 변경 
        var currentKRView = qwertyKrNormalKBView
        viewModel.kbResultBottomMargin.observe(this) {
            val lP = kbLayoutBottomMargin.layoutParams
            val newBottomMarginHeight = it * (resources.displayMetrics.density).toInt()
            savedBottomMarginHeight = newBottomMarginHeight
            lP.height = newBottomMarginHeight
            kbdBackgroundImageView.layoutParams.height = newBottomMarginHeight + savedKbdMainboardHeight
            kbLayoutBottomMargin.layoutParams = lP
        }
        viewModel.kbKrImeMode.observe(this) {
            currentKRView =
                when (it) {
                    Constants.IME_KR_FLAG_QWERTY -> qwertyKrNormalKBView
                    Constants.IME_KR_FLAG_CHUN -> chunjiinKBView
                    Constants.IME_KR_FLAG_CHUN_AMBI -> chunjiinLeftKBView
                    Constants.IME_KR_FLAG_NARAT -> naratguelKBView
                    Constants.IME_KR_FLAG_DAN -> danmoKBView
                    else -> qwertyKrNormalKBView
                }
            if (viewModel.inputTypeState.value == InputTypeState.KR_NORMAL) {
                kbdCharaterAreaFramelayout.removeAllViews()
                kbdCharaterAreaFramelayout.addView(currentKRView)
            }
        }
        viewModel.inputTypeState.observe(this) {
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
            latestInputTypeState = it
        }
        viewModel.kbLongClickInterval.observe(this) {}
        viewModel.kbHasVibration.observe(this) {}
        viewModel.kbVibrationIntensity.observe(this) {}
        viewModel.kbTheme.observe(this) {}
        viewModel.kbFunctionKeysFontColor.observe(this) {}
        viewModel.kbLeftSideMargin.observe(this) {
            val lPNum = kbNumLeftSide.layoutParams
            val lPChar = kbCharLeftSide.layoutParams
            lPNum.width = it.toInt() * 3 * (resources.displayMetrics.density).toInt()
            lPChar.width = it.toInt() * 3 * (resources.displayMetrics.density).toInt()
            kbNumLeftSide.layoutParams = lPNum
            kbCharLeftSide.layoutParams = lPChar
        }
        viewModel.kbRightSideMargin.observe(this) {
            val lPNum = kbNumRightSide.layoutParams
            val lPChar = kbCharRightSide.layoutParams
            lPNum.width = it.toInt() * 3 * (resources.displayMetrics.density).toInt()
            lPChar.width = it.toInt() * 3 * (resources.displayMetrics.density).toInt()
            kbNumRightSide.layoutParams = lPNum
            kbCharRightSide.layoutParams = lPChar
        }
        viewModel.kbResultedHeight.observe(this) {
            savedKbdMainboardHeight = it.toInt()
            kbdBackgroundImageView.layoutParams.height = (if (viewModel.kbNumberRowVisibility.value == View.VISIBLE) it.toInt() else (it*0.82).toInt()) + savedBottomMarginHeight
        }

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
        if (viewModel.kbHasAutoCapitalization.value!!
            && currentInputConnection.requestCursorUpdates(CURSOR_UPDATE_IMMEDIATE)
            && viewModel.inputTypeState.value == InputTypeState.EN_LOWER
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
                viewModel.setInputTypeState(InputTypeState.EN_UPPER)
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
                viewModel.setInputTypeState(newState = InputTypeState.EN_UPPER, restart= true)
                emojiKeyboard.setViewPagerDefault()
                return
            }
        }
        for (type in inputTypeNumbers) {
            if (currentInputEditorInfo.inputType or type == currentInputEditorInfo.inputType) {
                viewModel.setInputTypeState(newState = InputTypeState.NUMBER, restart = true)
                return
            }
        }
        emojiKeyboard.setViewPagerDefault()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        onFinishInputView(finishingInput = true)

        viewModel.changeOrientation(newConfig.orientation)
    }

    override fun onStartInput(attribute: EditorInfo?, restarting: Boolean) {
        super.onStartInput(attribute, restarting)
        clearComposing()
    }

    override fun onFinishInputView(finishingInput: Boolean) {
        super.onFinishInputView(finishingInput)
        clearComposing()
        viewModel.switchSelectingTextMode(false)
    }

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onDestroy() {
        super.onDestroy()
        mLifecycle.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        emojiKeyboard.unregisterPageChangeCallback()
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
}