package com.sb.fittingKeyboard

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
import android.util.Log
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
import androidx.core.view.setPadding
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import com.chargingwatts.livedata.sharedpref.booleanLiveData
import com.chargingwatts.livedata.sharedpref.intLiveData
import com.chargingwatts.livedata.sharedpref.stringLiveData
import kotlinx.android.synthetic.main.fragment_autotxt.view.*
import kotlinx.android.synthetic.main.fragment_cursor.view.*
import kotlinx.android.synthetic.main.fragment_keyboard.view.*
import kotlinx.android.synthetic.main.fragment_keyboard_chunjiin_basic.view.*
import kotlinx.android.synthetic.main.fragment_keyboard_naratgul_basic.view.*
import kotlinx.android.synthetic.main.fragment_keyboard_number.view.*
import kotlinx.android.synthetic.main.key_layout_normal.view.*
import java.util.*
import kotlin.properties.Delegates


@Suppress("DEPRECATION")
class KeyboardService() : InputMethodService(), LifecycleOwner {

    //<editor-fold desc="변수 선언">
    private val inputTypeNumbers = arrayOf(2, 4098, 8194, 18, 3, 4, 14, 24, 24578, 16387)
    private lateinit var lifecycleRegistry: LifecycleRegistry

    private lateinit var theme: SetTheme
    private lateinit var qwertyHangul: QWERTY_Hangul
    private lateinit var chunjiinHangul: CHUNJIIN_Hangul
    private lateinit var naratgulHangul: NARATGUL_Hangul
    private lateinit var fragmentKeyboardQWERTY: FrameLayout
    private lateinit var fragmentKeyboardChunjiin: FrameLayout
    private lateinit var fragmentKeyboardNaratgul: FrameLayout
    private lateinit var fragmentKeyboardChunjiinA: FrameLayout
    private lateinit var fragmentKeyboardNumber: FrameLayout
    private lateinit var fragmentAutoText: FrameLayout
    private lateinit var fragmentCursor: FrameLayout

    private var fontSizeIndex by Delegates.notNull<Float>()
    private lateinit var fontTypeIndex : Typeface
    private var myKeyboardHeight: Int = 100
    private var myKeyboardRightSize: Int = 20
    private var myKeyboardDivision: Boolean = true
    private var myKeyboardHolding: Int = 300
    private var myKeyboardVibration: Int = 2
    private var myKeyboardVibrationIntensity: Int = 50
    private var myKeyboardToggleNum: Boolean = true
    private var myKeyboardBotMargin: Int = 0
    private var myKeyboardTheme: Int = 0
    private var myDefaultFontColor: Int = 0xFF000000.toInt()
    private var myFunctionFontColor: Int = 0xFF000000.toInt()
    private var myKeyboardLeftSideMargin: Int = 1
    private var myKeyboardRightSideMargin: Int = 1
    private var myKeyboardFontType: Int = 0
    private var myKeyboardToggleToolbar: Boolean = true
    private var myKeyboardFontSize: Int = 1
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

    private lateinit var keyboardView: View
    private lateinit var keyboardViewLayout: LinearLayout
    private lateinit var keyboardViewFirstLine: LinearLayout
    private lateinit var keyboardViewFragment: FrameLayout
    private lateinit var keyboardViewBotMargin: View
    private lateinit var keyboardViewCharLeftMargin: FrameLayout
    private lateinit var keyboardViewCharRightMargin: FrameLayout
    private lateinit var keyboardViewNumLeftMargin: Button
    private lateinit var keyboardViewNumRightMargin: Button
    private lateinit var keyboardViewLinearLayout: LinearLayout
    private lateinit var keyboardToolbar: LinearLayout
    private lateinit var keyboardBackgroundImage: ImageView
    private lateinit var shortCutKeyboardSetting: ImageButton
    private lateinit var buttonBoilerPlateTxt: ImageButton
    private lateinit var selectAll: ImageButton
    private lateinit var buttonCursorFragment: ImageButton
    private lateinit var toolBarCopy: Button
    private lateinit var toolBarCut: Button
    private lateinit var toolBarPaste: Button
    private lateinit var getNumberFrag: ImageButton

    private lateinit var enChar: Array<String>
    private lateinit var krChar: Array<String>
    private lateinit var krShiftChar: Array<String>
    private lateinit var specialChar1: Array<String>
    private lateinit var specialChar2: Array<String>

    private lateinit var btnENTER: ImageButton
    private lateinit var btnCOMMA: Button
    private lateinit var btnDOT: Button
    private lateinit var btnSPACE: Button
    private lateinit var btnSPECIAL: Button
    private lateinit var btnSHIFT: ImageButton
    private lateinit var btnDEL: ImageButton
    private lateinit var btnLANG: ImageButton
    private lateinit var fragAutoNEXT: Button
    private lateinit var fragAutoSPACE: Button
    private lateinit var fragAutoENTER: ImageButton
    private lateinit var fragAutoDEL: ImageButton
    private lateinit var fragAutoKBD: ImageButton
    private lateinit var fragCursorUP: ImageButton
    private lateinit var fragCursorDOWN: ImageButton
    private lateinit var fragCursorLEFT: ImageButton
    private lateinit var fragCursorRIGHT: ImageButton
    private lateinit var fragCursorFIRSTCHAR: ImageButton
    private lateinit var fragCursorLASTCHAR: ImageButton
    private lateinit var fragCursorSelectWord: Button
    private lateinit var fragCursorSelectAll: Button
    private lateinit var fragCursorSelectCopy: Button
    private lateinit var fragCursorSelectPaste: Button
    private lateinit var fragCursorSelectCut: Button
    private lateinit var fragCursorSpace: Button
    private lateinit var fragCursorDEL: ImageButton
    private lateinit var fragCursorForeDEL: ImageButton
    private lateinit var fragCursorENTER: ImageButton
    private lateinit var fragCursorKBD: ImageButton

    private lateinit var btn1: Button
    private lateinit var btn2: Button
    private lateinit var btn3: Button
    private lateinit var btn4: Button
    private lateinit var btn5: Button
    private lateinit var btn6: Button
    private lateinit var btn7: Button
    private lateinit var btn8: Button
    private lateinit var btn9: Button
    private lateinit var btn0: Button

    private lateinit var btnA: Button
    private lateinit var btnB: Button
    private lateinit var btnC: Button
    private lateinit var btnD: Button
    private lateinit var btnE: Button
    private lateinit var btnF: Button
    private lateinit var btnG: Button
    private lateinit var btnH: Button
    private lateinit var btnI: Button
    private lateinit var btnJ: Button
    private lateinit var btnK: Button
    private lateinit var btnL: Button
    private lateinit var btnM: Button
    private lateinit var btnN: Button
    private lateinit var btnO: Button
    private lateinit var btnP: Button
    private lateinit var btnQ: Button
    private lateinit var btnR: Button
    private lateinit var btnS: Button
    private lateinit var btnT: Button
    private lateinit var btnU: Button
    private lateinit var btnV: Button
    private lateinit var btnW: Button
    private lateinit var btnX: Button
    private lateinit var btnY: Button
    private lateinit var btnZ: Button
    private lateinit var btnChunL: Button
    private lateinit var btnChunK: Button
    private lateinit var btnChunM: Button
    private lateinit var btnChunRZ: Button
    private lateinit var btnChunSF: Button
    private lateinit var btnChunEX: Button
    private lateinit var btnChunQV: Button
    private lateinit var btnChunTG: Button
    private lateinit var btnChunWC: Button
    private lateinit var btnChunDG: Button
    private lateinit var btnChunSPACE: Button
    private lateinit var btnChunDOT: Button
    private lateinit var btnChunSPECIAL: Button
    private lateinit var btnChunAT: Button
    private lateinit var btnChunDEL: ImageButton
    private lateinit var btnChunLANG: ImageButton
    private lateinit var btnChunENTER: ImageButton
    private lateinit var btnChunInit: ImageButton
    private lateinit var btnChunLa: Button
    private lateinit var btnChunKa: Button
    private lateinit var btnChunMa: Button
    private lateinit var btnChunRZa: Button
    private lateinit var btnChunSFa: Button
    private lateinit var btnChunEXa: Button
    private lateinit var btnChunQVa: Button
    private lateinit var btnChunTGa: Button
    private lateinit var btnChunWCa: Button
    private lateinit var btnChunDGa: Button
    private lateinit var btnChunSPACEa: Button
    private lateinit var btnChunDOTa: Button
    private lateinit var btnChunSPECIALa: Button
    private lateinit var btnChunATa: Button
    private lateinit var btnChunDELa: ImageButton
    private lateinit var btnChunLANGa: ImageButton
    private lateinit var btnChunENTERa: ImageButton
    private lateinit var btnChunInita: ImageButton
    private lateinit var chunjiinCharacterArray: Array<Button>
    private lateinit var btnNaR: Button
    private lateinit var btnNaS: Button
    private lateinit var btnNaF: Button
    private lateinit var btnNaA: Button
    private lateinit var btnNaT: Button
    private lateinit var btnNaD: Button
    private lateinit var btnNaK: Button
    private lateinit var btnNaH: Button
    private lateinit var btnNaL: Button
    private lateinit var btnNaM: Button
    private lateinit var btnNaDOT: Button
    private lateinit var btnNaSPACE: Button
    private lateinit var btnNaSPECIAL: Button
    private lateinit var btnNaADD: Button
    private lateinit var btnNaSHIFT: Button
    private lateinit var btnNaDEL: ImageButton
    private lateinit var btnNaENTER: ImageButton
    private lateinit var btnNaLANG: ImageButton
    private lateinit var naratgulCharacterArray: Array<Button>
    private lateinit var numBtn1 : Button
    private lateinit var numBtn2 : Button
    private lateinit var numBtn3 : Button
    private lateinit var numBtn4 : Button
    private lateinit var numBtn5 : Button
    private lateinit var numBtn6 : Button
    private lateinit var numBtn7 : Button
    private lateinit var numBtn8 : Button
    private lateinit var numBtn9 : Button
    private lateinit var numBtn0 : Button
    private lateinit var numBtnSTAR : Button
    private lateinit var numBtnSHOP : Button
    private lateinit var numBtnDOT : Button
    private lateinit var numBtnCOMMA : Button
    private lateinit var numBtnDEL : ImageButton
    private lateinit var numBtnSPACE : Button
    private lateinit var numBtnENTER : ImageButton
    private lateinit var numBtnLANG : ImageButton
    private lateinit var numberArray: Array<Button>

    private lateinit var buttonSetVibrate: Array<Button>
    private lateinit var btnBasicList: Array<Button>
    private lateinit var btnShiftList: Array<Button>
    private lateinit var btnCharList: Array<Button>
    private lateinit var btnRight: Array<Button>
    private lateinit var themeBtnChar: Array<Button>
    private lateinit var themeImgBtnChar: Array<ImageButton>
    private lateinit var themeBtnFun: Array<Button>
    private lateinit var themeImgBtnFun: Array<ImageButton>
    private lateinit var themeBtnEnter: Array<ImageButton>

    private lateinit var autoText1: Button
    private lateinit var autoText2: Button
    private lateinit var autoText3: Button
    private lateinit var autoText4: Button
    private lateinit var autoText5: Button
    private lateinit var autoText6: Button
    private lateinit var autoText7: Button
    private lateinit var autoText8: Button
    private lateinit var autoTextArray: Array<Button>
    private lateinit var myKeyboardAutoTextArray: Array<String?>

    private var modeValue = 1
    private var langSaveValue = 1
    private var delBoolean = false
    private var normalInterval: Long = 37
    private var isAutoTextFragment = false
    private var isCursorFragment = false
    private var isNumberFragment = false
    private var autoTextMode = 1

    private var shiftIcon_activated = R.drawable.keyic_shift_activated_black
    private var shiftIcon_deactivated = R.drawable.keyic_shift_deactivated_black
    private var shiftIcon_hyperactivated = R.drawable.keyic_shift_hyperactivated_black

    private val paramNormal = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1.0f)

    private var isSelectingActivated = false
    private var savedCursorPosition = 0

    //</editor-fold>

    inner class SetAutomata : Thread() {
        override fun run() {
            qwertyHangul = QWERTY_Hangul()
            chunjiinHangul = CHUNJIIN_Hangul()
            naratgulHangul = NARATGUL_Hangul()
        }
    }

    override fun onCreate() {
        super.onCreate()

        lifecycleRegistry = LifecycleRegistry(this)
        lifecycleRegistry.currentState = Lifecycle.State.CREATED

        keyboardView = layoutInflater.inflate(R.layout.key_layout_normal, null)
        fragmentKeyboardQWERTY = layoutInflater.inflate(R.layout.fragment_keyboard, null) as FrameLayout
        fragmentKeyboardChunjiin = layoutInflater.inflate(R.layout.fragment_keyboard_chunjiin_basic, null) as FrameLayout
        fragmentKeyboardChunjiinA = layoutInflater.inflate(R.layout.fragment_keyboard_chunjiin_ambi, null) as FrameLayout
        fragmentKeyboardNaratgul = layoutInflater.inflate(R.layout.fragment_keyboard_naratgul_basic, null) as FrameLayout
        fragmentKeyboardNumber = layoutInflater.inflate(R.layout.fragment_keyboard_number, null) as FrameLayout
        fragmentAutoText = layoutInflater.inflate(R.layout.fragment_autotxt, null) as FrameLayout
        fragmentCursor = layoutInflater.inflate(R.layout.fragment_cursor, null) as FrameLayout

        keyboardViewLayout = keyboardView.findViewById(R.id.keyboardLayout)
        keyboardToolbar = keyboardView.findViewById(R.id.keyboardToolBarLine)
        keyboardViewFirstLine = keyboardView.findViewById(R.id.firstLine)
        keyboardViewFragment = keyboardView.findViewById(R.id.keyboardViewFrameLayout)
        keyboardViewBotMargin = keyboardView.findViewById(R.id.keyboardBotMargin)
        keyboardViewCharLeftMargin = keyboardView.findViewById(R.id.keyboardLeftMargin)
        keyboardViewCharRightMargin = keyboardView.findViewById(R.id.keyboardRightMargin)
        keyboardViewNumLeftMargin = keyboardView.findViewById(R.id.keyboardNumberLeftMargin)
        keyboardViewNumRightMargin = keyboardView.findViewById(R.id.keyboardNumberRightMargin)
        keyboardViewLinearLayout = keyboardView.findViewById(R.id.keyboardViewLinearLayout)
        keyboardBackgroundImage = keyboardView.findViewById(R.id.keyboardBackgroundImage)
        shortCutKeyboardSetting = keyboardView.findViewById(R.id.shortCutButton)

        //<editor-fold desc="키보드 버튼 선언">
        buttonBoilerPlateTxt = keyboardView.findViewById(R.id.autotxtButton)
        selectAll = keyboardView.findViewById(R.id.selectAll)
        buttonCursorFragment = keyboardView.findViewById(R.id.cursorButton)
        toolBarCopy = keyboardView.findViewById(R.id.toolBarCopy)
        toolBarCut = keyboardView.findViewById(R.id.toolBarCut)
        toolBarPaste = keyboardView.findViewById(R.id.toolBarPaste)
        getNumberFrag = keyboardView.findViewById(R.id.numberKeypadButton)

        btn1 = keyboardView.findViewById(R.id.btn1)
        btn2 = keyboardView.findViewById(R.id.btn2)
        btn3 = keyboardView.findViewById(R.id.btn3)
        btn4 = keyboardView.findViewById(R.id.btn4)
        btn5 = keyboardView.findViewById(R.id.btn5)
        btn6 = keyboardView.findViewById(R.id.btn6)
        btn7 = keyboardView.findViewById(R.id.btn7)
        btn8 = keyboardView.findViewById(R.id.btn8)
        btn9 = keyboardView.findViewById(R.id.btn9)
        btn0 = keyboardView.findViewById(R.id.btn0)

        btnA = fragmentKeyboardQWERTY.btnA
        btnB = fragmentKeyboardQWERTY.btnB
        btnC = fragmentKeyboardQWERTY.btnC
        btnD = fragmentKeyboardQWERTY.btnD
        btnE = fragmentKeyboardQWERTY.btnE
        btnF = fragmentKeyboardQWERTY.btnF
        btnG = fragmentKeyboardQWERTY.btnG
        btnH = fragmentKeyboardQWERTY.btnH
        btnI = fragmentKeyboardQWERTY.btnI
        btnJ = fragmentKeyboardQWERTY.btnJ
        btnK = fragmentKeyboardQWERTY.btnK
        btnL = fragmentKeyboardQWERTY.btnL
        btnM = fragmentKeyboardQWERTY.btnM
        btnN = fragmentKeyboardQWERTY.btnN
        btnO = fragmentKeyboardQWERTY.btnO
        btnP = fragmentKeyboardQWERTY.btnP
        btnQ = fragmentKeyboardQWERTY.btnQ
        btnR = fragmentKeyboardQWERTY.btnR
        btnS = fragmentKeyboardQWERTY.btnS
        btnT = fragmentKeyboardQWERTY.btnT
        btnU = fragmentKeyboardQWERTY.btnU
        btnV = fragmentKeyboardQWERTY.btnV
        btnW = fragmentKeyboardQWERTY.btnW
        btnX = fragmentKeyboardQWERTY.btnX
        btnY = fragmentKeyboardQWERTY.btnY
        btnZ = fragmentKeyboardQWERTY.btnZ
        btnCOMMA = fragmentKeyboardQWERTY.btnCOMMA
        btnDOT = fragmentKeyboardQWERTY.btnDOT

        btnChunL = fragmentKeyboardChunjiin.btnChunL as Button
        btnChunK = fragmentKeyboardChunjiin.btnChunK as Button
        btnChunM = fragmentKeyboardChunjiin.btnChunM as Button
        btnChunRZ = fragmentKeyboardChunjiin.btnChunRZ as Button
        btnChunSF = fragmentKeyboardChunjiin.btnChunSF as Button
        btnChunEX = fragmentKeyboardChunjiin.btnChunEX as Button
        btnChunQV = fragmentKeyboardChunjiin.btnChunQV as Button
        btnChunTG = fragmentKeyboardChunjiin.btnChunTG as Button
        btnChunWC = fragmentKeyboardChunjiin.btnChunWC as Button
        btnChunDG = fragmentKeyboardChunjiin.btnChunDG as Button
        btnChunDEL = fragmentKeyboardChunjiin.btnChunDEL as ImageButton
        btnChunSPACE = fragmentKeyboardChunjiin.btnChunSPACE as Button
        btnChunSPECIAL = fragmentKeyboardChunjiin.btnChunSPECIAL as Button
        btnChunDOT = fragmentKeyboardChunjiin.btnChunDOT as Button
        btnChunAT = fragmentKeyboardChunjiin.btnChunAT as Button
        btnChunENTER = fragmentKeyboardChunjiin.btnChunENTER as ImageButton
        btnChunLANG = fragmentKeyboardChunjiin.btnChunLANG as ImageButton
        btnChunInit = fragmentKeyboardChunjiin.btnChunInit as ImageButton
        btnChunLa = fragmentKeyboardChunjiinA.findViewById(R.id.btnChunLa)
        btnChunKa = fragmentKeyboardChunjiinA.findViewById(R.id.btnChunKa)
        btnChunMa = fragmentKeyboardChunjiinA.findViewById(R.id.btnChunMa)
        btnChunRZa = fragmentKeyboardChunjiinA.findViewById(R.id.btnChunRZa)
        btnChunSFa = fragmentKeyboardChunjiinA.findViewById(R.id.btnChunSFa)
        btnChunEXa = fragmentKeyboardChunjiinA.findViewById(R.id.btnChunEXa)
        btnChunQVa = fragmentKeyboardChunjiinA.findViewById(R.id.btnChunQVa)
        btnChunTGa = fragmentKeyboardChunjiinA.findViewById(R.id.btnChunTGa)
        btnChunWCa = fragmentKeyboardChunjiinA.findViewById(R.id.btnChunWCa)
        btnChunDGa = fragmentKeyboardChunjiinA.findViewById(R.id.btnChunDGa)
        btnChunDELa = fragmentKeyboardChunjiinA.findViewById(R.id.btnChunDELa)
        btnChunSPACEa = fragmentKeyboardChunjiinA.findViewById(R.id.btnChunSPACEa)
        btnChunSPECIALa = fragmentKeyboardChunjiinA.findViewById(R.id.btnChunSPECIALa)
        btnChunDOTa = fragmentKeyboardChunjiinA.findViewById(R.id.btnChunDOTa)
        btnChunATa = fragmentKeyboardChunjiinA.findViewById(R.id.btnChunATa)
        btnChunENTERa = fragmentKeyboardChunjiinA.findViewById(R.id.btnChunENTERa)
        btnChunLANGa = fragmentKeyboardChunjiinA.findViewById(R.id.btnChunLANGa)
        btnChunInita = fragmentKeyboardChunjiinA.findViewById(R.id.btnChunInita)

        btnNaR = fragmentKeyboardNaratgul.btnNaR
        btnNaS = fragmentKeyboardNaratgul.btnNaS
        btnNaF = fragmentKeyboardNaratgul.btnNaF
        btnNaA = fragmentKeyboardNaratgul.btnNaA
        btnNaT = fragmentKeyboardNaratgul.btnNaT
        btnNaD = fragmentKeyboardNaratgul.btnNaD
        btnNaK = fragmentKeyboardNaratgul.btnNaK
        btnNaH = fragmentKeyboardNaratgul.btnNaH
        btnNaL = fragmentKeyboardNaratgul.btnNaL
        btnNaM = fragmentKeyboardNaratgul.btnNaM
        btnNaDOT = fragmentKeyboardNaratgul.btnNaDOT
        btnNaSPACE = fragmentKeyboardNaratgul.btnNaSPACE
        btnNaSPECIAL = fragmentKeyboardNaratgul.btnNaSPECIAL
        btnNaSHIFT = fragmentKeyboardNaratgul.btnNaSHIFT
        btnNaADD = fragmentKeyboardNaratgul.btnNaADD
        btnNaDEL = fragmentKeyboardNaratgul.btnNaDEL
        btnNaENTER = fragmentKeyboardNaratgul.btnNaENTER
        btnNaLANG = fragmentKeyboardNaratgul.btnNaLANG

        numBtn0 = fragmentKeyboardNumber.numBtn0
        numBtn1 = fragmentKeyboardNumber.numBtn1
        numBtn2 = fragmentKeyboardNumber.numBtn2
        numBtn3 = fragmentKeyboardNumber.numBtn3
        numBtn4 = fragmentKeyboardNumber.numBtn4
        numBtn5 = fragmentKeyboardNumber.numBtn5
        numBtn6 = fragmentKeyboardNumber.numBtn6
        numBtn7 = fragmentKeyboardNumber.numBtn7
        numBtn8 = fragmentKeyboardNumber.numBtn8
        numBtn9 = fragmentKeyboardNumber.numBtn9
        numBtnSTAR = fragmentKeyboardNumber.numBtnSTAR
        numBtnSHOP = fragmentKeyboardNumber.numBtnSHOP
        numBtnDOT = fragmentKeyboardNumber.numBtnDOT
        numBtnSPACE = fragmentKeyboardNumber.numBtnSPACE
        numBtnCOMMA = fragmentKeyboardNumber.numBtnCOMMA
        numBtnENTER = fragmentKeyboardNumber.numBtnENTER
        numBtnDEL = fragmentKeyboardNumber.numBtnDEL
        numBtnLANG = fragmentKeyboardNumber.numBtnLANG

        btnDEL = fragmentKeyboardQWERTY.btnDEL
        btnSPACE = fragmentKeyboardQWERTY.btnSPACE
        btnSHIFT = fragmentKeyboardQWERTY.btnSHIFT
        btnLANG = fragmentKeyboardQWERTY.btnLANG
        btnSPECIAL = fragmentKeyboardQWERTY.btnSPECIAL
        btnENTER = fragmentKeyboardQWERTY.btnENTER

        fragAutoNEXT = fragmentAutoText.frag_next
        fragAutoDEL = fragmentAutoText.frag_delete
        fragAutoENTER = fragmentAutoText.frag_enter
        fragAutoSPACE = fragmentAutoText.frag_space
        fragAutoKBD = fragmentAutoText.frag_keyboard
        autoText1 = fragmentAutoText.frag_autoText1
        autoText2 = fragmentAutoText.frag_autoText2
        autoText3 = fragmentAutoText.frag_autoText3
        autoText4 = fragmentAutoText.frag_autoText4
        autoText5 = fragmentAutoText.frag_autoText5
        autoText6 = fragmentAutoText.frag_autoText6
        autoText7 = fragmentAutoText.frag_autoText7
        autoText8 = fragmentAutoText.frag_autoText8

        fragCursorUP = fragmentCursor.frag_cursor_up
        fragCursorDOWN = fragmentCursor.frag_cursor_down
        fragCursorLEFT = fragmentCursor.frag_cursor_left
        fragCursorRIGHT = fragmentCursor.frag_cursor_right
        fragCursorFIRSTCHAR = fragmentCursor.frag_cursor_firstChar
        fragCursorLASTCHAR = fragmentCursor.frag_cursor_lastChar
        fragCursorSelectAll = fragmentCursor.frag_cursor_selectAll
        fragCursorSelectWord = fragmentCursor.frag_cursor_selectWord
        fragCursorSelectCut = fragmentCursor.frag_cursor_cutSelected
        fragCursorSelectPaste = fragmentCursor.frag_cursor_pasteSelected
        fragCursorSelectCopy = fragmentCursor.frag_cursor_copySelected
        fragCursorSpace = fragmentCursor.frag_cursor_space
        fragCursorENTER = fragmentCursor.frag_cursor_enter
        fragCursorDEL = fragmentCursor.frag_cursor_delete
        fragCursorForeDEL = fragmentCursor.frag_cursor_foredelete
        fragCursorKBD = fragmentCursor.frag_cursor_kbd

        buttonSetVibrate = arrayOf(btnQ, btnW, btnA, btnS, btnZ, btnX, btnO, btnP, btnK, btnL, btnN, btnM)
        btnBasicList = arrayOf(btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0, btnA, btnB, btnC, btnD, btnE, btnF, btnG, btnH, btnI, btnJ, btnK, btnL, btnM, btnN, btnO, btnP, btnQ, btnR, btnS, btnT, btnU, btnV, btnW, btnX, btnY, btnZ, btnCOMMA, btnDOT, btnSPACE)
        btnCharList = arrayOf(btnA, btnB, btnC, btnD, btnE, btnF, btnG, btnH, btnI, btnJ, btnK, btnL, btnM, btnN, btnO, btnP, btnQ, btnR, btnS, btnT, btnU, btnV, btnW, btnX, btnY, btnZ)
        btnShiftList = arrayOf(btnQ, btnW, btnE, btnR, btnT, btnO, btnP)
        themeBtnChar = arrayOf(btnA, btnB, btnC, btnD, btnE, btnF, btnG, btnH, btnI, btnJ, btnK, btnL, btnM, btnN, btnO, btnP, btnQ, btnR, btnS, btnT, btnU, btnV, btnW, btnX, btnY, btnZ, autoText1, autoText2, autoText3, autoText4, autoText5, autoText6, autoText7, autoText8, btnChunRZ, btnChunSF, btnChunEX, btnChunQV, btnChunTG, btnChunWC, btnChunDG, btnChunK, btnChunL, btnChunM, btnChunRZa, btnChunSFa, btnChunEXa, btnChunQVa, btnChunTGa, btnChunWCa, btnChunDGa, btnChunKa, btnChunLa, btnChunMa, btnNaR, btnNaS, btnNaF, btnNaA, btnNaT, btnNaD, btnNaK, btnNaH, btnNaL, btnNaM, btnNaSHIFT, btnNaADD, numBtn0, numBtn1, numBtn2, numBtn3, numBtn4, numBtn5, numBtn6, numBtn7, numBtn8, numBtn9, numBtnSTAR, numBtnSHOP)
        themeImgBtnChar = arrayOf(fragCursorUP, fragCursorDOWN, fragCursorLEFT, fragCursorRIGHT, fragCursorFIRSTCHAR, fragCursorLASTCHAR)
        themeBtnFun = arrayOf(btnCOMMA, btnDOT, btnSPACE, btnSPECIAL, btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, fragAutoNEXT, fragAutoSPACE, fragCursorSpace, fragCursorSelectAll, fragCursorSelectCopy, fragCursorSelectCut, fragCursorSelectPaste, fragCursorSelectWord, btnChunDOT, btnChunSPECIAL, btnChunSPACE, btnChunAT, btnChunDOTa, btnChunSPECIALa, btnChunSPACEa, btnChunATa, btnNaDOT, btnNaSPECIAL, btnNaSPACE, numBtnSPACE, numBtnDOT, numBtnCOMMA)
        themeImgBtnFun = arrayOf(btnSHIFT, btnLANG, btnDEL, fragAutoDEL, fragAutoKBD, fragCursorKBD, fragCursorDEL, fragCursorForeDEL, btnChunDEL, btnChunLANG, btnChunInit, btnChunDELa, btnChunLANGa, btnChunInita, btnNaDEL, btnNaLANG, numBtnDEL, numBtnLANG)
        themeBtnEnter = arrayOf(btnENTER, btnNaENTER, btnChunENTER, btnChunENTERa, fragAutoENTER, fragCursorENTER, btnNaENTER, numBtnENTER)
        chunjiinCharacterArray = arrayOf(btnChunRZ, btnChunSF, btnChunEX, btnChunQV, btnChunTG, btnChunWC, btnChunDG, btnChunK, btnChunL, btnChunM, btnChunRZa, btnChunSFa, btnChunEXa, btnChunQVa, btnChunTGa, btnChunWCa, btnChunDGa, btnChunKa, btnChunLa, btnChunMa)
        naratgulCharacterArray = arrayOf(btnNaR, btnNaS, btnNaF, btnNaA, btnNaT, btnNaD, btnNaK, btnNaH, btnNaL, btnNaM)
        numberArray = arrayOf(numBtn0, numBtn1, numBtn2, numBtn3, numBtn4, numBtn5, numBtn6, numBtn7, numBtn8, numBtn9, numBtnCOMMA, numBtnDOT, numBtnSHOP, numBtnSTAR)

        enChar = arrayOf("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z")
        krChar = arrayOf("ㅁ", "ㅠ", "ㅊ", "ㅇ", "ㄷ", "ㄹ", "ㅎ", "ㅗ", "ㅑ", "ㅓ", "ㅏ", "ㅣ", "ㅡ", "ㅜ", "ㅐ", "ㅔ", "ㅂ", "ㄱ", "ㄴ", "ㅅ", "ㅕ", "ㅍ", "ㅈ", "ㅌ", "ㅛ", "ㅋ")
        krShiftChar = arrayOf("ㅂ", "ㅈ", "ㄷ", "ㄱ", "ㅅ", "ㅐ", "ㅔ", "ㅃ", "ㅉ", "ㄸ", "ㄲ", "ㅆ", "ㅒ", "ㅖ")
        specialChar1 = arrayOf("(", ";", "~", "{", "$", "}", "[", "]", "-", "<", ">", "=", "?", "!", "*", "/", "@", "%", ")", "^", "+", ":", "#", "_", "&", "\"")
        specialChar2 = arrayOf("≤", "`", "\"", "≠", "÷", "∞", "→", "←", "-", "↑", "↓", "♡", "|", "\\", "*", "/", "±", "•", "≥", "√", "+", "'", "×", "」", "π", "「")
        btnRight = arrayOf(btnY, btnU, btnI, btnO, btnP, btnH, btnJ, btnK, btnL, btnB, btnN, btnM)

        theme = SetTheme(keyboardBackgroundImage, themeBtnChar, themeImgBtnChar, themeBtnFun, themeImgBtnFun, themeBtnEnter)
        //</editor-fold>
    }

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("ClickableViewAccessibility", "DefaultLocale", "NewApi")
    override fun onCreateInputView(): View? {

        val thread = SetAutomata()
        thread.start()

        val keyboardSetting = applicationContext.getSharedPreferences("keyboardSetting", MODE_PRIVATE)

        val myKeyboardRightSizeLiveData = keyboardSetting.intLiveData("KeyboardMoSize", 20)
        myKeyboardRightSizeLiveData.observe(this, androidx.lifecycle.Observer {
            myKeyboardRightSize = it + 80
        })
        val myKeyboardDivisionLiveData = keyboardSetting.booleanLiveData("KeyboardDivision", true)
        myKeyboardDivisionLiveData.observe(this, androidx.lifecycle.Observer {
            myKeyboardDivision = it
        })
        val myKeyboardHoldingLiveData = keyboardSetting.intLiveData("KeyboardHolding", 200)
        myKeyboardHoldingLiveData.observe(this, androidx.lifecycle.Observer {
            myKeyboardHolding = it + 100
        })
        val myKeyboardViewLiveData = keyboardSetting.viewLivedata("KeyboardToggleNum", "KeyboardHeight", "KeyboardBottomMargin", true, 25, 0)
        myKeyboardViewLiveData.observe(this, androidx.lifecycle.Observer {
            myKeyboardToggleNum = it[0] as Boolean
            myKeyboardHeight = it[1] as Int + 75
            myKeyboardBotMargin = it[2] as Int
            if ((myKeyboardToggleNum && currentInputEditorInfo.inputType !in inputTypeNumbers) || (!myKeyboardToggleNum && (modeValue == 5 || modeValue == 6))) {
                keyboardViewLayout.layoutParams.height = changeDPtoPX(220 * myKeyboardHeight / 100).toInt() + myKeyboardBotMargin
                keyboardBackgroundImage.layoutParams.height = keyboardViewLayout.layoutParams.height
                keyboardViewFirstLine.visibility = View.VISIBLE
                keyboardViewFirstLine.layoutParams.height = changeDPtoPX(40 * myKeyboardHeight / 100).toInt()
                keyboardViewLinearLayout.layoutParams.height = changeDPtoPX(180 * myKeyboardHeight / 100).toInt() + myKeyboardBotMargin
            } else {
                keyboardViewLayout.layoutParams.height = changeDPtoPX(180 * myKeyboardHeight / 100).toInt() + myKeyboardBotMargin
                keyboardBackgroundImage.layoutParams.height = keyboardViewLayout.layoutParams.height
                keyboardViewFirstLine.visibility = View.GONE
                keyboardViewLinearLayout.layoutParams.height = changeDPtoPX(180 * myKeyboardHeight / 100).toInt() + myKeyboardBotMargin
            }
        })
        val myKeyboardVibrationLiveData = keyboardSetting.intLiveData("KeyboardVibration", 2)
        myKeyboardVibrationLiveData.observe(this, androidx.lifecycle.Observer {
            myKeyboardVibration = it
        })
        val myKeyboardVibrationIntensityLiveData = keyboardSetting.intLiveData("KeyboardVibrationIntensity", 0)
        myKeyboardVibrationIntensityLiveData.observe(this, androidx.lifecycle.Observer {
            myKeyboardVibrationIntensity = it
        })
        val myKeyboardThemeLiveData = keyboardSetting.intLiveData("KeyboardTheme", 0)
        myKeyboardThemeLiveData.observe(this, androidx.lifecycle.Observer {
            myKeyboardTheme = it
        })
        val myDefaultFontColorLiveData = keyboardSetting.intLiveData("KeyboardDefaultFontColor", 0xFF000000.toInt())
        myDefaultFontColorLiveData.observe(this, androidx.lifecycle.Observer {
            myDefaultFontColor = it
        })
        val myFunctionFontColorLiveData = keyboardSetting.intLiveData("KeyboardFunctionFontColor", 0xFF000000.toInt())
        myFunctionFontColorLiveData.observe(this, androidx.lifecycle.Observer {
            myFunctionFontColor = it
        })
        val myKeyboardLeftSideMarginLiveData = keyboardSetting.intLiveData("KeyboardLeftSideMargin", 1)
        myKeyboardLeftSideMarginLiveData.observe(this, androidx.lifecycle.Observer {
            myKeyboardLeftSideMargin = it
        })
        val myKeyboardRightSideMarginLiveData = keyboardSetting.intLiveData("KeyboardRightSideMargin", 1)
        myKeyboardRightSideMarginLiveData.observe(this, androidx.lifecycle.Observer {
            myKeyboardRightSideMargin = it
        })
        val myKeyboardFontTypeLiveData = keyboardSetting.intLiveData("KeyboardFontType", 0)
        myKeyboardFontTypeLiveData.observe(this, androidx.lifecycle.Observer {
            myKeyboardFontType = it
        })
        val myKeyboardToggleToolbarLiveData = keyboardSetting.booleanLiveData("KeyboardToggleToolBar", true)
        myKeyboardToggleToolbarLiveData.observe(this, androidx.lifecycle.Observer {
            myKeyboardToggleToolbar = it
            keyboardToolbar.visibility = when ( myKeyboardToggleToolbar ) {
                true -> View.VISIBLE
                else -> View.GONE
            }
        })
        val myKeyboardFontSizeLiveData = keyboardSetting.intLiveData("KeyboardFontSize", 1)
        myKeyboardFontSizeLiveData.observe(this, androidx.lifecycle.Observer {
            myKeyboardFontSize = it
        })
        val myKeyboardAutoCapitalLiveData = keyboardSetting.booleanLiveData("KeyboardAutoCapital", true)
        myKeyboardAutoCapitalLiveData.observe(this, androidx.lifecycle.Observer {
            myKeyboardAutoCapital = it
        })
        val myKeyboardAutoModeChangeLiveData = keyboardSetting.booleanLiveData("KeyboardAutoModeChange", true)
        myKeyboardAutoModeChangeLiveData.observe(this, androidx.lifecycle.Observer {
            myKeyboardAutoModeChange = it
        })
        val myKeyboardAutoText1LiveData = keyboardSetting.stringLiveData("AutoText0", "")
        myKeyboardAutoText1LiveData.observe(this, androidx.lifecycle.Observer {
            myKeyboardAutoText1 = it
        })
        val myKeyboardAutoText2LiveData = keyboardSetting.stringLiveData("AutoText1", "")
        myKeyboardAutoText2LiveData.observe(this, androidx.lifecycle.Observer {
            myKeyboardAutoText2 = it
        })
        val myKeyboardAutoText3LiveData = keyboardSetting.stringLiveData("AutoText2", "")
        myKeyboardAutoText3LiveData.observe(this, androidx.lifecycle.Observer {
            myKeyboardAutoText3 = it
        })
        val myKeyboardAutoText4LiveData = keyboardSetting.stringLiveData("AutoText3", "")
        myKeyboardAutoText4LiveData.observe(this, androidx.lifecycle.Observer {
            myKeyboardAutoText4 = it
        })
        val myKeyboardAutoText5LiveData = keyboardSetting.stringLiveData("AutoText4", "")
        myKeyboardAutoText5LiveData.observe(this, androidx.lifecycle.Observer {
            myKeyboardAutoText5 = it
        })
        val myKeyboardAutoText6LiveData = keyboardSetting.stringLiveData("AutoText5", "")
        myKeyboardAutoText6LiveData.observe(this, androidx.lifecycle.Observer {
            myKeyboardAutoText6 = it
        })
        val myKeyboardAutoText7LiveData = keyboardSetting.stringLiveData("AutoText6", "")
        myKeyboardAutoText7LiveData.observe(this, androidx.lifecycle.Observer {
            myKeyboardAutoText7 = it
        })
        val myKeyboardAutoText8LiveData = keyboardSetting.stringLiveData("AutoText7", "")
        myKeyboardAutoText8LiveData.observe(this, androidx.lifecycle.Observer {
            myKeyboardAutoText8 = it
        })
        val myKeyboardAutoText9LiveData = keyboardSetting.stringLiveData("AutoText8", "")
        myKeyboardAutoText9LiveData.observe(this, androidx.lifecycle.Observer {
            myKeyboardAutoText9 = it
        })
        val myKeyboardAutoText10LiveData = keyboardSetting.stringLiveData("AutoText9", "")
        myKeyboardAutoText10LiveData.observe(this, androidx.lifecycle.Observer {
            myKeyboardAutoText10 = it
        })
        val myKeyboardAutoText11LiveData = keyboardSetting.stringLiveData("AutoText10", "")
        myKeyboardAutoText11LiveData.observe(this, androidx.lifecycle.Observer {
            myKeyboardAutoText11 = it
        })
        val myKeyboardAutoText12LiveData = keyboardSetting.stringLiveData("AutoText11", "")
        myKeyboardAutoText12LiveData.observe(this, androidx.lifecycle.Observer {
            myKeyboardAutoText12 = it
        })
        val myKeyboardAutoText13LiveData = keyboardSetting.stringLiveData("AutoText12", "")
        myKeyboardAutoText13LiveData.observe(this, androidx.lifecycle.Observer {
            myKeyboardAutoText13 = it
        })
        val myKeyboardAutoText14LiveData = keyboardSetting.stringLiveData("AutoText13", "")
        myKeyboardAutoText14LiveData.observe(this, androidx.lifecycle.Observer {
            myKeyboardAutoText14 = it
        })
        val myKeyboardAutoText15LiveData = keyboardSetting.stringLiveData("AutoText14", "")
        myKeyboardAutoText15LiveData.observe(this, androidx.lifecycle.Observer {
            myKeyboardAutoText15 = it
        })
        val myKeyboardAutoText16LiveData = keyboardSetting.stringLiveData("AutoText15", "")
        myKeyboardAutoText16LiveData.observe(this, androidx.lifecycle.Observer {
            myKeyboardAutoText16 = it
        })
        val myKeyboardSpecialLiveData = keyboardSetting.intLiveData("KeyboardSpecialkeyAddon", 0)
        myKeyboardSpecialLiveData.observe(this, androidx.lifecycle.Observer {
            myKeyboardSpecialKeyAddon = it
        })
        val myKeyboardEnterLiveData = keyboardSetting.intLiveData("KeyboardEnterkeyAddon", 0)
        myKeyboardEnterLiveData.observe(this, androidx.lifecycle.Observer {
            myKeyboardEnterKeyAddon = it
        })
        val myKeyboardInputMethodKRLiveData = keyboardSetting.intLiveData("KeyboardInputMethodKR", 0)
        myKeyboardInputMethodKRLiveData.observe(this, androidx.lifecycle.Observer {
            myKeyboardInputMethodKR = it
        })

        return keyboardView
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onUpdateSelection(oldSelStart: Int, oldSelEnd: Int, newSelStart: Int, newSelEnd: Int, candidatesStart: Int, candidatesEnd: Int) {
        if (!delBoolean && candidatesStart != -1) {
            if ((candidatesStart != oldSelStart && candidatesEnd != newSelStart)) {
                clearComposing()
            }
        } else if (!delBoolean && newSelStart == 0 && newSelEnd == 0 && candidatesStart == -1 && candidatesEnd == -1) {
            clearComposing()
        }

        /**TextField의 첫글자로 돌아갔을 때 대문자로 수정 **/
        if (myKeyboardAutoCapital && currentInputConnection.requestCursorUpdates(
                CURSOR_UPDATE_IMMEDIATE
            ) && modeValue == 2
        ) {
            var autoCapitalConditionArray = BooleanArray(10)
            var autoCapitalCondition: Boolean = false
            for (i in autoCapitalConditionArray.indices) {
                autoCapitalConditionArray[i] =
                    currentInputConnection.getTextBeforeCursor(i + 2, GET_TEXT_WITH_STYLES)
                        .toString().replace("\\s".toRegex(), "") == "."
            }
            if (autoCapitalConditionArray.indexOf(true) in autoCapitalConditionArray.indices) {
                autoCapitalCondition = true
            }
            if ((newSelStart == 0 && newSelEnd == 0 && candidatesStart == -1 && candidatesEnd == -1) || autoCapitalCondition) {
                modeValue = 1
                for (index in btnCharList.indices) {
                    btnCharList[index].text = enChar[index].toUpperCase()
                }
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

        lifecycleRegistry.currentState = Lifecycle.State.STARTED

        fontSizeIndex = when (myKeyboardFontSize) {
            0 -> 14.0F
            1 -> 16.0F
            2 -> 18.0F
            3 -> 20.0F
            4 -> 22.0F
            else -> 16.0F
        }
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

        autoTextArray = arrayOf(autoText1, autoText2, autoText3, autoText4, autoText5, autoText6, autoText7, autoText8)
        myKeyboardAutoTextArray = arrayOf(myKeyboardAutoText1, myKeyboardAutoText2, myKeyboardAutoText3, myKeyboardAutoText4, myKeyboardAutoText5, myKeyboardAutoText6, myKeyboardAutoText7, myKeyboardAutoText8, myKeyboardAutoText9, myKeyboardAutoText10, myKeyboardAutoText11, myKeyboardAutoText12, myKeyboardAutoText13, myKeyboardAutoText14, myKeyboardAutoText15, myKeyboardAutoText16)

        //<editor-fold desc="모양 초기화">
        detNumKeyboard()

        autoTextMode = 1

        if ( isAutoTextFragment ) {
            isAutoTextFragment = !isAutoTextFragment
            buttonBoilerPlateTxt.setImageResource(R.drawable.ic_boilerplatetext_black)
        }
        if ( isCursorFragment ) {
            isCursorFragment = !isCursorFragment
            buttonCursorFragment.setImageResource(R.drawable.ic_move)
        }
        if ( isNumberFragment ) {
            isNumberFragment = !isNumberFragment
            getNumberFrag.setImageResource(R.drawable.ic_number_keypad)
        }

        if (myKeyboardDivision) {
            changeRightSize(myKeyboardRightSize)
        } else {
            setLayoutNormally()
        }

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

        keyboardViewNumLeftMargin.layoutParams.width = myKeyboardLeftSideMargin * 5
        keyboardViewNumRightMargin.layoutParams.width = myKeyboardRightSideMargin * 5
        keyboardViewCharLeftMargin.layoutParams.width = myKeyboardLeftSideMargin * 5
        keyboardViewCharRightMargin.layoutParams.width = myKeyboardRightSideMargin * 5
        keyboardViewBotMargin.layoutParams.height = myKeyboardBotMargin

        when (myKeyboardTheme) {
            0 -> theme.setTheme00()
            1 -> theme.setTheme01()
            2 -> theme.setTheme02()
            3 -> theme.setTheme03()
            4 -> theme.setTheme04()
            5 -> theme.setTheme05()
            6 -> theme.setTheme06()
            7 -> {
                keyboardBackgroundImage.setImageResource(R.color.black)
                btn1.setBackgroundResource(R.drawable.keydesign_02_q)
                btn2.setBackgroundResource(R.drawable.keydesign_02_w)
                btn3.setBackgroundResource(R.drawable.keydesign_02_e)
                btn4.setBackgroundResource(R.drawable.keydesign_02_r)
                btn5.setBackgroundResource(R.drawable.keydesign_02_t)
                btn6.setBackgroundResource(R.drawable.keydesign_02_y)
                btn7.setBackgroundResource(R.drawable.keydesign_02_u)
                btn8.setBackgroundResource(R.drawable.keydesign_02_i)
                btn9.setBackgroundResource(R.drawable.keydesign_02_o)
                btn0.setBackgroundResource(R.drawable.keydesign_02_p)
                btnQ.setBackgroundResource(R.drawable.keydesign_02_q)
                btnW.setBackgroundResource(R.drawable.keydesign_02_w)
                btnE.setBackgroundResource(R.drawable.keydesign_02_e)
                btnR.setBackgroundResource(R.drawable.keydesign_02_r)
                btnT.setBackgroundResource(R.drawable.keydesign_02_t)
                btnY.setBackgroundResource(R.drawable.keydesign_02_y)
                btnU.setBackgroundResource(R.drawable.keydesign_02_u)
                btnI.setBackgroundResource(R.drawable.keydesign_02_i)
                btnO.setBackgroundResource(R.drawable.keydesign_02_o)
                btnP.setBackgroundResource(R.drawable.keydesign_02_p)
                btnA.setBackgroundResource(R.drawable.keydesign_02_a)
                btnS.setBackgroundResource(R.drawable.keydesign_02_s)
                btnD.setBackgroundResource(R.drawable.keydesign_02_d)
                btnF.setBackgroundResource(R.drawable.keydesign_02_f)
                btnG.setBackgroundResource(R.drawable.keydesign_02_g)
                btnH.setBackgroundResource(R.drawable.keydesign_02_h)
                btnJ.setBackgroundResource(R.drawable.keydesign_02_j)
                btnK.setBackgroundResource(R.drawable.keydesign_02_k)
                btnL.setBackgroundResource(R.drawable.keydesign_02_l)
                btnZ.setBackgroundResource(R.drawable.keydesign_02_s)
                btnX.setBackgroundResource(R.drawable.keydesign_02_d)
                btnC.setBackgroundResource(R.drawable.keydesign_02_f)
                btnV.setBackgroundResource(R.drawable.keydesign_02_g)
                btnB.setBackgroundResource(R.drawable.keydesign_02_h)
                btnN.setBackgroundResource(R.drawable.keydesign_02_j)
                btnM.setBackgroundResource(R.drawable.keydesign_02_k)
                btnSHIFT.setBackgroundResource(R.drawable.keydesign_02_a)
                btnENTER.setBackgroundResource(R.drawable.keydesign_02_enter)
                btnDEL.setBackgroundResource(R.drawable.keydesign_02_l)
                btnLANG.setBackgroundResource(R.drawable.keydesign_02_lang)
                btnSPECIAL.setBackgroundResource(R.drawable.keydesign_02_special)
                btnSPACE.setBackgroundResource(R.drawable.keydesign_02_space)
                btnCOMMA.setBackgroundResource(R.drawable.keydesign_02_comma)
                btnDOT.setBackgroundResource(R.drawable.keydesign_02_dot)
                btnChunL.setBackgroundResource(R.drawable.keydesign_02_chun1)
                btnChunRZ.setBackgroundResource(R.drawable.keydesign_02_chun1)
                btnChunQV.setBackgroundResource(R.drawable.keydesign_02_chun1)
                btnChunK.setBackgroundResource(R.drawable.keydesign_02_chun2)
                btnChunSF.setBackgroundResource(R.drawable.keydesign_02_chun2)
                btnChunTG.setBackgroundResource(R.drawable.keydesign_02_chun2)
                btnChunDG.setBackgroundResource(R.drawable.keydesign_02_chun2)
                btnChunM.setBackgroundResource(R.drawable.keydesign_02_chun3)
                btnChunEX.setBackgroundResource(R.drawable.keydesign_02_chun3)
                btnChunWC.setBackgroundResource(R.drawable.keydesign_02_chun3)
                btnChunDEL.setBackgroundResource(R.drawable.keydesign_02_chun4)
                btnChunENTER.setBackgroundResource(R.drawable.keydesign_02_chun4)
                btnChunSPACE.setBackgroundResource(R.drawable.keydesign_02_chun4)
                btnChunSPECIAL.setBackgroundResource(R.drawable.keydesign_02_chun5)
                btnChunLANG.setBackgroundResource(R.drawable.keydesign_02_chun6)
                btnChunInit.setBackgroundResource(R.drawable.keydesign_02_chun3)
                btnChunDOT.setBackgroundResource(R.drawable.keydesign_02_chun9)
                btnChunAT.setBackgroundResource(R.drawable.keydesign_02_chun10)
                btnChunRZa.setBackgroundResource(R.drawable.keydesign_02_chun1)
                btnChunEXa.setBackgroundResource(R.drawable.keydesign_02_chun1)
                btnChunTGa.setBackgroundResource(R.drawable.keydesign_02_chun1)
                btnChunQVa.setBackgroundResource(R.drawable.keydesign_02_chun2)
                btnChunSFa.setBackgroundResource(R.drawable.keydesign_02_chun2)
                btnChunDGa.setBackgroundResource(R.drawable.keydesign_02_chun2)
                btnChunWCa.setBackgroundResource(R.drawable.keydesign_02_chun2)
                btnChunKa.setBackgroundResource(R.drawable.keydesign_02_chun3)
                btnChunMa.setBackgroundResource(R.drawable.keydesign_02_chun3)
                btnChunLa.setBackgroundResource(R.drawable.keydesign_02_chun3)
                btnChunDELa.setBackgroundResource(R.drawable.keydesign_02_chun4)
                btnChunENTERa.setBackgroundResource(R.drawable.keydesign_02_chun4)
                btnChunSPACEa.setBackgroundResource(R.drawable.keydesign_02_chun4)
                btnChunSPECIALa.setBackgroundResource(R.drawable.keydesign_02_chun5)
                btnChunLANGa.setBackgroundResource(R.drawable.keydesign_02_chun6)
                btnChunInita.setBackgroundResource(R.drawable.keydesign_02_chun3)
                btnChunDOTa.setBackgroundResource(R.drawable.keydesign_02_chun9)
                btnChunATa.setBackgroundResource(R.drawable.keydesign_02_chun10)
                btnNaR.setBackgroundResource(R.drawable.keydesign_02_chun1)
                btnNaF.setBackgroundResource(R.drawable.keydesign_02_chun1)
                btnNaT.setBackgroundResource(R.drawable.keydesign_02_chun1)
                btnNaADD.setBackgroundResource(R.drawable.keydesign_02_chun1)
                btnNaS.setBackgroundResource(R.drawable.keydesign_02_chun2)
                btnNaA.setBackgroundResource(R.drawable.keydesign_02_chun2)
                btnNaD.setBackgroundResource(R.drawable.keydesign_02_chun2)
                btnNaM.setBackgroundResource(R.drawable.keydesign_02_chun2)
                btnNaK.setBackgroundResource(R.drawable.keydesign_02_chun3)
                btnNaH.setBackgroundResource(R.drawable.keydesign_02_chun3)
                btnNaL.setBackgroundResource(R.drawable.keydesign_02_chun3)
                btnNaSHIFT.setBackgroundResource(R.drawable.keydesign_02_chun3)
                btnNaDEL.setBackgroundResource(R.drawable.keydesign_02_chun4)
                btnNaSPACE.setBackgroundResource(R.drawable.keydesign_02_chun4)
                btnNaDOT.setBackgroundResource(R.drawable.keydesign_02_na1)
                btnNaLANG.setBackgroundResource(R.drawable.keydesign_02_na1)
                btnNaENTER.setBackgroundResource(R.drawable.keydesign_02_na2)
                btnNaSPECIAL.setBackgroundResource(R.drawable.keydesign_02_na2)
                numBtn1.setBackgroundResource(R.drawable.keydesign_02_chun1)
                numBtn4.setBackgroundResource(R.drawable.keydesign_02_chun1)
                numBtn7.setBackgroundResource(R.drawable.keydesign_02_chun1)
                numBtn2.setBackgroundResource(R.drawable.keydesign_02_chun2)
                numBtn5.setBackgroundResource(R.drawable.keydesign_02_chun2)
                numBtn8.setBackgroundResource(R.drawable.keydesign_02_chun2)
                numBtn0.setBackgroundResource(R.drawable.keydesign_02_chun2)
                numBtn3.setBackgroundResource(R.drawable.keydesign_02_chun3)
                numBtn6.setBackgroundResource(R.drawable.keydesign_02_chun3)
                numBtn9.setBackgroundResource(R.drawable.keydesign_02_chun3)
                numBtnDEL.setBackgroundResource(R.drawable.keydesign_02_chun4)
                numBtnENTER.setBackgroundResource(R.drawable.keydesign_02_chun4)
                numBtnSPACE.setBackgroundResource(R.drawable.keydesign_02_chun4)
                numBtnLANG.setBackgroundResource(R.drawable.keydesign_02_chun6)
                numBtnSTAR.setBackgroundResource(R.drawable.keydesign_02_chun1)
                numBtnSHOP.setBackgroundResource(R.drawable.keydesign_02_chun3)
                numBtnDOT.setBackgroundResource(R.drawable.keydesign_02_chun9)
                numBtnCOMMA.setBackgroundResource(R.drawable.keydesign_02_chun10)

                for (index in autoTextArray.indices) {
                    if (index % 2 == 0) {
                        autoTextArray[index].setBackgroundResource(R.drawable.keydesign_02_auto1)
                    } else {
                        autoTextArray[index].setBackgroundResource(R.drawable.keydesign_02_auto2)
                    }
                }
                fragAutoNEXT.setBackgroundResource(R.drawable.keydesign_02_autofunction)
                fragAutoDEL.setBackgroundResource(R.drawable.keydesign_02_autofunction)
                fragAutoENTER.setBackgroundResource(R.drawable.keydesign_02_autofunction)
                fragAutoSPACE.setBackgroundResource(R.drawable.keydesign_02_autofunction)
                fragAutoKBD.setBackgroundResource(R.drawable.keydesign_02_autofunction)
                fragCursorSelectWord.setBackgroundResource(R.drawable.keydesign_02_cursor_selectword)
                fragCursorSelectAll.setBackgroundResource(R.drawable.keydesign_02_cursor_selectall)
                fragCursorSelectCopy.setBackgroundResource(R.drawable.keydesign_02_cursor_selectcopy)
                fragCursorSelectPaste.setBackgroundResource(R.drawable.keydesign_02_cursor_selectpaste)
                fragCursorSelectCut.setBackgroundResource(R.drawable.keydesign_02_cursor_selectcut)
                fragCursorFIRSTCHAR.setBackgroundResource(R.drawable.keydesign_02_cursor_left)
                fragCursorUP.setBackgroundResource(R.drawable.keydesign_02_cursor_down)
                fragCursorLASTCHAR.setBackgroundResource(R.drawable.keydesign_02_cursor_right)
                fragCursorForeDEL.setBackgroundResource(R.drawable.keydesign_02_cursor_del)
                fragCursorLEFT.setBackgroundResource(R.drawable.keydesign_02_cursor_left)
                fragCursorDOWN.setBackgroundResource(R.drawable.keydesign_02_cursor_down)
                fragCursorRIGHT.setBackgroundResource(R.drawable.keydesign_02_cursor_right)
                fragCursorDEL.setBackgroundResource(R.drawable.keydesign_02_cursor_del)
                fragCursorKBD.setBackgroundResource(R.drawable.keydesign_02_cursor_selectall)
                fragCursorSpace.setBackgroundResource(R.drawable.keydesign_02_cursor_space)
                fragCursorENTER.setBackgroundResource(R.drawable.keydesign_02_cursor_del)
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

        shortCutKeyboardSetting.setOnClickListener {
            startApp("com.sb.fittingKeyboard")
        }

        selectAll.setOnClickListener {
            selectAll()
        }

        buttonBoilerPlateTxt.setOnClickListener {
            autoTextMode = 1

            isCursorFragment = false
            isNumberFragment = false
            buttonCursorFragment.setImageResource(R.drawable.ic_move)
            getNumberFrag.setImageResource(R.drawable.ic_number_keypad)

            isSelectingActivated = false
            fragCursorSelectWord.text = "선택"

            if (!isAutoTextFragment) {
                isAutoTextFragment = true
                keyboardView.keyboardViewFrameLayout.removeAllViews()
                keyboardView.keyboardViewFrameLayout.addView(fragmentAutoText)
                buttonBoilerPlateTxt.setImageResource(R.drawable.ic_keyboard_black)
                fragAutoNEXT.text = "1/2"
                autoText1.text = myKeyboardAutoText1
                autoText2.text = myKeyboardAutoText2
                autoText3.text = myKeyboardAutoText3
                autoText4.text = myKeyboardAutoText4
                autoText5.text = myKeyboardAutoText5
                autoText6.text = myKeyboardAutoText6
                autoText7.text = myKeyboardAutoText7
                autoText8.text = myKeyboardAutoText8

                if (myKeyboardToggleNum) {
                    keyboardViewFirstLine.visibility = View.VISIBLE
                    keyboardViewLayout.layoutParams.height = changeDPtoPX(220 * myKeyboardHeight / 100).toInt() + myKeyboardBotMargin
                } else {
                    keyboardViewFirstLine.visibility = View.GONE
                    keyboardViewLayout.layoutParams.height = changeDPtoPX(180 * myKeyboardHeight / 100).toInt() + myKeyboardBotMargin
                }
            } else {
                isAutoTextFragment = false
                keyboardView.keyboardViewFrameLayout.removeAllViews()
                buttonBoilerPlateTxt.setImageResource(R.drawable.ic_boilerplatetext_black)

                if ( currentInputEditorInfo.inputType in inputTypeNumbers ) {
                    keyboardViewFirstLine.visibility = View.GONE
                    keyboardViewLayout.layoutParams.height = changeDPtoPX(180 * myKeyboardHeight / 100).toInt() + myKeyboardBotMargin
                    keyboardView.keyboardViewFrameLayout.addView(fragmentKeyboardNumber)
                } //숫자키보드로 되돌아가기
                else {
                    when (myKeyboardInputMethodKR) {
                        0 -> keyboardView.keyboardViewFrameLayout.addView(fragmentKeyboardQWERTY)
                        1 -> keyboardView.keyboardViewFrameLayout.addView(fragmentKeyboardChunjiin)
                        2 -> keyboardView.keyboardViewFrameLayout.addView(fragmentKeyboardNaratgul)
                        3 -> keyboardView.keyboardViewFrameLayout.addView(fragmentKeyboardChunjiinA)
                        else -> keyboardView.keyboardViewFrameLayout.addView(fragmentKeyboardQWERTY)
                    }
                    if (myKeyboardToggleNum || (!myKeyboardToggleNum && (modeValue == 5 || modeValue == 6))) {
                        keyboardViewFirstLine.visibility = View.VISIBLE
                        keyboardViewLayout.layoutParams.height = changeDPtoPX(220 * myKeyboardHeight / 100).toInt() + myKeyboardBotMargin
                    } else {
                        keyboardViewFirstLine.visibility = View.GONE
                        keyboardViewLayout.layoutParams.height = changeDPtoPX(180 * myKeyboardHeight / 100).toInt() + myKeyboardBotMargin
                    }
                } //일반키보드로 되돌아가기
            }
            keyboardBackgroundImage.layoutParams.height = keyboardViewLayout.layoutParams.height
        }

        buttonCursorFragment.setOnClickListener {
            autoTextMode = 1

            isAutoTextFragment = false
            isNumberFragment = false
            buttonBoilerPlateTxt.setImageResource(R.drawable.ic_boilerplatetext_black)
            getNumberFrag.setImageResource(R.drawable.ic_number_keypad)

            isSelectingActivated = false
            fragCursorSelectWord.text = "선택"

            if (!isCursorFragment) {
                isCursorFragment = true
                keyboardView.keyboardViewFrameLayout.removeAllViews()
                keyboardView.keyboardViewFrameLayout.addView(fragmentCursor)
                buttonCursorFragment.setImageResource(R.drawable.ic_keyboard_black)
                if (myKeyboardToggleNum) {
                    keyboardViewFirstLine.visibility = View.VISIBLE
                    keyboardViewLayout.layoutParams.height = changeDPtoPX(220 * myKeyboardHeight / 100).toInt() + myKeyboardBotMargin
                } else {
                    keyboardViewFirstLine.visibility = View.GONE
                    keyboardViewLayout.layoutParams.height = changeDPtoPX(180 * myKeyboardHeight / 100).toInt() + myKeyboardBotMargin
                }
            } else {
                isCursorFragment = false
                keyboardView.keyboardViewFrameLayout.removeAllViews()
                buttonCursorFragment.setImageResource(R.drawable.ic_move)

                if ( currentInputEditorInfo.inputType in inputTypeNumbers ) {
                    keyboardViewFirstLine.visibility = View.GONE
                    keyboardViewLayout.layoutParams.height = changeDPtoPX(180 * myKeyboardHeight / 100).toInt() + myKeyboardBotMargin
                    keyboardView.keyboardViewFrameLayout.addView(fragmentKeyboardNumber)
                } //숫자키보드로 되돌아가기
                else {
                    when (myKeyboardInputMethodKR) {
                        0 -> keyboardView.keyboardViewFrameLayout.addView(fragmentKeyboardQWERTY)
                        1 -> keyboardView.keyboardViewFrameLayout.addView(fragmentKeyboardChunjiin)
                        2 -> keyboardView.keyboardViewFrameLayout.addView(fragmentKeyboardNaratgul)
                        3 -> keyboardView.keyboardViewFrameLayout.addView(fragmentKeyboardChunjiinA)
                        else -> keyboardView.keyboardViewFrameLayout.addView(fragmentKeyboardQWERTY)
                    }
                    if (myKeyboardToggleNum || (!myKeyboardToggleNum && (modeValue == 5 || modeValue == 6))) {
                        keyboardViewFirstLine.visibility = View.VISIBLE
                        keyboardViewLayout.layoutParams.height = changeDPtoPX(220 * myKeyboardHeight / 100).toInt() + myKeyboardBotMargin
                    } else {
                        keyboardViewFirstLine.visibility = View.GONE
                        keyboardViewLayout.layoutParams.height = changeDPtoPX(180 * myKeyboardHeight / 100).toInt() + myKeyboardBotMargin
                    }
                } //일반키보드로 되돌아가기
            }
            keyboardBackgroundImage.layoutParams.height = keyboardViewLayout.layoutParams.height
        }

        getNumberFrag.setOnClickListener {
            autoTextMode = 1

            isAutoTextFragment = false
            isCursorFragment = false
            buttonBoilerPlateTxt.setImageResource(R.drawable.ic_boilerplatetext_black)
            buttonCursorFragment.setImageResource(R.drawable.ic_move)

            isSelectingActivated = false
            fragCursorSelectWord.text = "선택"

            if (!isNumberFragment) {
                isNumberFragment = true
                keyboardView.keyboardViewFrameLayout.removeAllViews()
                keyboardView.keyboardViewFrameLayout.addView(fragmentKeyboardNumber)
                getNumberFrag.setImageResource(R.drawable.ic_keyboard_black)
                keyboardViewFirstLine.visibility = View.GONE
                keyboardViewLayout.layoutParams.height = changeDPtoPX(180 * myKeyboardHeight / 100).toInt() + myKeyboardBotMargin
            } else {
                isNumberFragment = false
                keyboardView.keyboardViewFrameLayout.removeAllViews()
                getNumberFrag.setImageResource(R.drawable.ic_number_keypad)

                if ( currentInputEditorInfo.inputType in inputTypeNumbers ) {
                    keyboardViewFirstLine.visibility = View.GONE
                    keyboardViewLayout.layoutParams.height = changeDPtoPX(180 * myKeyboardHeight / 100).toInt() + myKeyboardBotMargin
                    keyboardView.keyboardViewFrameLayout.addView(fragmentKeyboardNumber)
                } //숫자키보드로 되돌아가기
                else {
                    when (myKeyboardInputMethodKR) {
                        0 -> keyboardView.keyboardViewFrameLayout.addView(fragmentKeyboardQWERTY)
                        1 -> keyboardView.keyboardViewFrameLayout.addView(fragmentKeyboardChunjiin)
                        2 -> keyboardView.keyboardViewFrameLayout.addView(fragmentKeyboardNaratgul)
                        3 -> keyboardView.keyboardViewFrameLayout.addView(fragmentKeyboardChunjiinA)
                        else -> keyboardView.keyboardViewFrameLayout.addView(fragmentKeyboardQWERTY)
                    }
                    if (myKeyboardToggleNum || (!myKeyboardToggleNum && (modeValue == 5 || modeValue == 6))) {
                        keyboardViewFirstLine.visibility = View.VISIBLE
                        keyboardViewLayout.layoutParams.height = changeDPtoPX(220 * myKeyboardHeight / 100).toInt() + myKeyboardBotMargin
                    } else {
                        keyboardViewFirstLine.visibility = View.GONE
                        keyboardViewLayout.layoutParams.height = changeDPtoPX(180 * myKeyboardHeight / 100).toInt() + myKeyboardBotMargin
                    }
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

        btnChunRZ.text = "ㄱㅋ"
        btnChunSF.text = "ㄴㄹ"
        btnChunEX.text = "ㄷㅌ"
        btnChunQV.text = "ㅂㅍ"
        btnChunTG.text = "ㅅㅎ"
        btnChunWC.text = "ㅈㅊ"
        btnChunDG.text = "ㅇㅁ"
        btnChunSPACE.text = "SPACE"
        btnChunSPECIAL.text = "!@#"
        btnChunL.text = "ㅣ"
        btnChunK.text = "ᆞ"
        btnChunM.text = "ㅡ"
        btnChunDOT.text = ".,"
        btnChunAT.text = "@"
        btnChunRZa.text = "ㄱㅋ"
        btnChunSFa.text = "ㄴㄹ"
        btnChunEXa.text = "ㄷㅌ"
        btnChunQVa.text = "ㅂㅍ"
        btnChunTGa.text = "ㅅㅎ"
        btnChunWCa.text = "ㅈㅊ"
        btnChunDGa.text = "ㅇㅁ"
        btnChunSPACEa.text = "SPACE"
        btnChunSPECIALa.text = "!@#"
        btnChunLa.text = "ㅣ"
        btnChunKa.text = "ᆞ"
        btnChunMa.text = "ㅡ"
        btnChunDOTa.text = ".,"
        btnChunATa.text = "@"

        btnNaR.text = "ㄱ"
        btnNaS.text = "ㄴ"
        btnNaF.text = "ㄹ"
        btnNaA.text = "ㅁ"
        btnNaT.text = "ㅅ"
        btnNaD.text = "ㅇ"
        btnNaK.text = "ㅏㅓ"
        btnNaH.text = "ㅗㅜ"
        btnNaL.text = "ㅣ"
        btnNaM.text = "ㅡ"
        btnNaDOT.text = ".,"
        btnNaSHIFT.text = "쌍자음"
        btnNaSPACE.text = "SPACE"
        btnNaSPECIAL.text = "!@#"
        btnNaADD.text = "획추가"

        numBtn0.text = "0"
        numBtn1.text = "1"
        numBtn2.text = "2"
        numBtn3.text = "3"
        numBtn4.text = "4"
        numBtn5.text = "5"
        numBtn6.text = "6"
        numBtn7.text = "7"
        numBtn8.text = "8"
        numBtn9.text = "9"
        numBtnSTAR.text = "*"
        numBtnSHOP.text = "#"
        numBtnSPACE.text = "SPACE"
        numBtnDOT.text = "."
        numBtnCOMMA.text = ","

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
                                qwertyHangul.composeChar(' ', currentInputConnection)
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
                                if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(myKeyboardVibrationIntensity)
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
                                if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(myKeyboardVibrationIntensity)
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
                                qwertyHangul.composeChar(btnCOMMA.text.single(), currentInputConnection)
                                if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(myKeyboardVibrationIntensity)
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

                                chunjiinHangul.initState()
                                chunjiinHangul.initChar()
                                chunjiinHangul.initResult()
                                naratgulHangul.initState()
                                naratgulHangul.initChar()
                                naratgulHangul.initResult()
                                if (myKeyboardInputMethodKR != 0) currentInputConnection.finishComposingText()

                                qwertyHangul.composeChar(btn.text.single(), currentInputConnection)
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
                                    if (btn in buttonSetVibrate) vibrateByButton(myKeyboardVibrationIntensity)
                                } else if (myKeyboardVibration == 2) {
                                    if (btn in btnBasicList) vibrateByButton(myKeyboardVibrationIntensity)
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
            if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(myKeyboardVibrationIntensity)
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
                        keyboardViewLayout.layoutParams.height = changeDPtoPX(220 * myKeyboardHeight / 100).toInt() + myKeyboardBotMargin
                    }
                }
                if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(myKeyboardVibrationIntensity)
            }
            setOnLongClickListener {
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
                        if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(myKeyboardVibrationIntensity)
                    })
            )
        }
        btnENTER.run {
            setPadding(changeDPtoPX(10).toInt())
            btnENTER.setOnClickListener {
                enterFunction()
            }
            setOnLongClickListener {
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
                            1, 2, 3 -> {
                                modeValue = 3
                                setLayoutByMode(fontTypeIndex, modeValue)
                            }
                        }
                    }
                }
                if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(myKeyboardVibrationIntensity)
            }
        }
        //</editor-fold>

        //<editor-fold desc="천지인키보드">
        for (item in chunjiinCharacterArray) {
            item.run {
                setOnClickListener {
                    chunjiinHangul.composeChar(item.text[0], currentInputConnection)
                    if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(myKeyboardVibrationIntensity)
                }
            }
        }
        btnChunSPACE.run {
            setOnClickListener {
                clearComposing()
                currentInputConnection.commitText(" ", 1)
                if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(myKeyboardVibrationIntensity)
            }
        }
        btnChunENTER.run {
            setPadding(changeDPtoPX(10).toInt())
            setOnClickListener {
                enterFunction()
                isSelectingActivated = false
                fragCursorSelectWord.text = "선택"
                if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(myKeyboardVibrationIntensity)
            }
            setOnLongClickListener {
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
                        if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(myKeyboardVibrationIntensity)
                    })
            )
        }
        btnChunLANG.run {
            setPadding(changeDPtoPX(10).toInt())
            setOnClickListener {
                modeValue = langSaveValue //modeValue = 1 || 2
                setLayoutByMode(fontTypeIndex, modeValue)
                if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(myKeyboardVibrationIntensity)
            }
        }
        btnChunDOT.run {
            setOnClickListener {
                clearComposing()
                currentInputConnection.commitText(".", 1)
                if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(myKeyboardVibrationIntensity)
            }
            setOnLongClickListener {
                clearComposing()
                currentInputConnection.commitText(",", 1)
                if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(myKeyboardVibrationIntensity)
                true
            }
        }
        btnChunAT.run {
            setOnClickListener {
                clearComposing()
                currentInputConnection.commitText("@", 1)
                if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(myKeyboardVibrationIntensity)
            }
        }
        btnChunSPECIAL.run {
            setOnClickListener {
                modeValue = 5
                langSaveValue = 1
                setLayoutByMode(fontTypeIndex, modeValue)
                for (item in btnRight) item.setTextColor(myDefaultFontColor)
                keyboardViewLayout.layoutParams.height = changeDPtoPX(220 * myKeyboardHeight / 100).toInt() + myKeyboardBotMargin
                if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(myKeyboardVibrationIntensity)
            }
            setOnLongClickListener {
                addFunction(myKeyboardSpecialKeyAddon)
                true
            }
        }
        btnChunInit.run {
            setPadding(changeDPtoPX(10).toInt())
            setOnClickListener {
                clearComposing()
            }
        }
        btnChunSPACEa.run {
            setOnClickListener {
                clearComposing()
                currentInputConnection.commitText(" ", 1)
                if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(myKeyboardVibrationIntensity)
            }
        }
        btnChunENTERa.run {
            setPadding(changeDPtoPX(10).toInt())
            setOnClickListener {
                enterFunction()
                isSelectingActivated = false
                fragCursorSelectWord.text = "선택"
                if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(myKeyboardVibrationIntensity)
            }
            setOnLongClickListener {
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
                        if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(myKeyboardVibrationIntensity)
                    })
            )
        }
        btnChunLANGa.run {
            setPadding(changeDPtoPX(10).toInt())
            setOnClickListener {
                modeValue = langSaveValue //modeValue = 1 || 2
                setLayoutByMode(fontTypeIndex, modeValue)
                if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(myKeyboardVibrationIntensity)
            }
        }
        btnChunDOTa.run {
            setOnClickListener {
                clearComposing()
                currentInputConnection.commitText(".", 1)
                if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(myKeyboardVibrationIntensity)
            }
            setOnLongClickListener {
                clearComposing()
                currentInputConnection.commitText(",", 1)
                if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(myKeyboardVibrationIntensity)
                true
            }
        }
        btnChunATa.run {
            setOnClickListener {
                clearComposing()
                currentInputConnection.commitText("@", 1)
                if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(myKeyboardVibrationIntensity)
            }
        }
        btnChunSPECIALa.run {
            setOnClickListener {
                modeValue = 5
                langSaveValue = 1
                setLayoutByMode(fontTypeIndex, modeValue)
                for (item in btnRight) item.setTextColor(myDefaultFontColor)
                keyboardViewLayout.layoutParams.height = keyboardViewLinearLayout.height + keyboardViewBotMargin.height + keyboardViewFirstLine.height
                if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(myKeyboardVibrationIntensity)
            }
            setOnLongClickListener {
                addFunction(myKeyboardSpecialKeyAddon)
                true
            }
        }
        btnChunInita.run {
            setPadding(changeDPtoPX(10).toInt())
            setOnClickListener {
                clearComposing()
            }
        }
        //</editor-fold>

        //<editor-fold desc="나랏글키보드">
        for (item in naratgulCharacterArray) {
            item.run {
                setOnClickListener {
                    naratgulHangul.composeChar(item.text[0], currentInputConnection)
                    if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(myKeyboardVibrationIntensity)
                }
            }
        }
        btnNaSHIFT.run {
            setOnClickListener {
                naratgulHangul.composeChar('ᆢ', currentInputConnection)
                if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(myKeyboardVibrationIntensity)
            }
        }
        btnNaADD.run {
            setOnClickListener {
                naratgulHangul.composeChar('ᆞ', currentInputConnection)
                if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(myKeyboardVibrationIntensity)
            }
        }
        btnNaSPACE.run {
            setOnClickListener {
                clearComposing()
                currentInputConnection.commitText(" ", 1)
                if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(myKeyboardVibrationIntensity)
            }
        }
        btnNaENTER.run {
            setPadding(changeDPtoPX(10).toInt())
            setOnClickListener {
                enterFunction()
                isSelectingActivated = false
                fragCursorSelectWord.text = "선택"
                if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(myKeyboardVibrationIntensity)
            }
            setOnLongClickListener {
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
                        if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(myKeyboardVibrationIntensity)
                    })
            )
        }
        btnNaLANG.run {
            setPadding(changeDPtoPX(12).toInt())
            setOnClickListener {
                modeValue = langSaveValue //modeValue = 1 || 2
                setLayoutByMode(fontTypeIndex, modeValue)
                if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(myKeyboardVibrationIntensity)
            }
        }
        btnNaDOT.run {
            setOnClickListener {
                clearComposing()
                currentInputConnection.commitText(".", 1)
                if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(myKeyboardVibrationIntensity)
            }
            setOnLongClickListener {
                clearComposing()
                currentInputConnection.commitText(",", 1)
                if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(myKeyboardVibrationIntensity)
                true
            }
        }
        btnNaSPECIAL.run {
            setOnClickListener {
                modeValue = 5
                langSaveValue = 1
                setLayoutByMode(fontTypeIndex, modeValue)
                for (item in btnRight) item.setTextColor(myDefaultFontColor)
                keyboardViewLayout.layoutParams.height = changeDPtoPX(220 * myKeyboardHeight / 100).toInt() + myKeyboardBotMargin
                if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(myKeyboardVibrationIntensity)
            }
            setOnLongClickListener {
                addFunction(myKeyboardSpecialKeyAddon)
                true
            }
        }
        //</editor-fold>

        //<editor-fold desc="숫자키보드">
        for ( item in numberArray ) {
            item.run {
                setOnClickListener {
                    currentInputConnection.commitText(item.text[0].toString(), 1)
                    if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(myKeyboardVibrationIntensity)
                }
            }
        }
        numBtnSPACE.setOnClickListener {
            clearComposing()
            currentInputConnection.commitText(" ", 1)
            if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(myKeyboardVibrationIntensity)
        }
        numBtnENTER.run {
            setPadding(changeDPtoPX(10).toInt())
            setOnClickListener {
                enterFunction()
                isSelectingActivated = false
                fragCursorSelectWord.text = "선택"
                if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(myKeyboardVibrationIntensity)
            }
            setOnLongClickListener {
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
                        if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(myKeyboardVibrationIntensity)
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
                                keyboardView.keyboardViewFrameLayout.addView(fragmentKeyboardChunjiin)
                            }
                            2 -> {
                                modeValue = 3
                                setLayoutByMode(fontTypeIndex, modeValue)
                                keyboardView.keyboardViewFrameLayout.removeAllViews()
                                keyboardView.keyboardViewFrameLayout.addView(fragmentKeyboardNaratgul)
                            }
                            3 -> {
                                modeValue = 3
                                setLayoutByMode(fontTypeIndex, modeValue)
                                keyboardView.keyboardViewFrameLayout.removeAllViews()
                                keyboardView.keyboardViewFrameLayout.addView(fragmentKeyboardChunjiinA)
                            }
                        }
                    }
                }
                if ( isNumberFragment ) {
                    isNumberFragment = !isNumberFragment
                    getNumberFrag.setImageResource(R.drawable.ic_number_keypad)
                }
                if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(myKeyboardVibrationIntensity)
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
                }
                setOnLongClickListener {
                    val intent = applicationContext.packageManager.getLaunchIntentForPackage("com.sb.fittingKeyboard")

                    clearComposing()
                    if (autoTextMode == 1) {
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
                        if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(myKeyboardVibrationIntensity)
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
                        qwertyHangul.composeChar(' ', currentInputConnection)
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
                        if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(myKeyboardVibrationIntensity)
                    })
            )
        }
        fragAutoNEXT.run {
            setOnClickListener {
                if (autoTextMode == 1) {
                    autoText1.text = myKeyboardAutoText9
                    autoText2.text = myKeyboardAutoText10
                    autoText3.text = myKeyboardAutoText11
                    autoText4.text = myKeyboardAutoText12
                    autoText5.text = myKeyboardAutoText13
                    autoText6.text = myKeyboardAutoText14
                    autoText7.text = myKeyboardAutoText15
                    autoText8.text = myKeyboardAutoText16
                    fragAutoNEXT.text = "2/2"
                    autoTextMode = 2
                } else {
                    autoText1.text = myKeyboardAutoText1
                    autoText2.text = myKeyboardAutoText2
                    autoText3.text = myKeyboardAutoText3
                    autoText4.text = myKeyboardAutoText4
                    autoText5.text = myKeyboardAutoText5
                    autoText6.text = myKeyboardAutoText6
                    autoText7.text = myKeyboardAutoText7
                    autoText8.text = myKeyboardAutoText8
                    fragAutoNEXT.text = "1/2"
                    autoTextMode = 1
                }
            }
        }
        fragAutoKBD.run {
            setPadding(changeDPtoPX(5).toInt())
            setOnClickListener {
                isAutoTextFragment = false
                keyboardView.keyboardViewFrameLayout.removeAllViews()
                when (myKeyboardInputMethodKR) {
                    0 -> keyboardView.keyboardViewFrameLayout.addView(fragmentKeyboardQWERTY)
                    1 -> keyboardView.keyboardViewFrameLayout.addView(fragmentKeyboardChunjiin)
                    2 -> keyboardView.keyboardViewFrameLayout.addView(fragmentKeyboardNaratgul)
                    3 -> keyboardView.keyboardViewFrameLayout.addView(fragmentKeyboardChunjiinA)
                    else -> keyboardView.keyboardViewFrameLayout.addView(fragmentKeyboardQWERTY)
                }
                buttonBoilerPlateTxt.setImageResource(R.drawable.ic_boilerplatetext_black)
                if (myKeyboardToggleNum || (!myKeyboardToggleNum && (modeValue == 5 || modeValue == 6))) {
                    keyboardViewFirstLine.visibility = View.VISIBLE
                    keyboardViewLayout.layoutParams.height = changeDPtoPX(220 * myKeyboardHeight / 100).toInt() + myKeyboardBotMargin
                } else {
                    keyboardViewFirstLine.visibility = View.GONE
                    keyboardViewLayout.layoutParams.height = changeDPtoPX(180 * myKeyboardHeight / 100).toInt() + myKeyboardBotMargin
                }
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
                        if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(myKeyboardVibrationIntensity)
                    })
            )
        }
        fragCursorDOWN.run {
            setPadding(changeDPtoPX(10).toInt())
            setOnTouchListener(
                RepeatListener(myKeyboardHolding.toLong(), normalInterval,
                    View.OnClickListener {
                        moveCursorToDown()
                        if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(myKeyboardVibrationIntensity)
                    })
            )
        }
        fragCursorLEFT.run {
            setPadding(changeDPtoPX(10).toInt())
            setOnTouchListener(
                RepeatListener(myKeyboardHolding.toLong(), normalInterval,
                    View.OnClickListener {
                        moveCursorToLeft()
                        if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(myKeyboardVibrationIntensity)
                    })
            )
        }
        fragCursorRIGHT.run {
            setPadding(changeDPtoPX(10).toInt())
            setOnTouchListener(
                RepeatListener(myKeyboardHolding.toLong(), normalInterval,
                    View.OnClickListener {
                        moveCursorToRight()
                        if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(myKeyboardVibrationIntensity)
                    })
            )
        }
        fragCursorFIRSTCHAR.run {
            setPadding(changeDPtoPX(10).toInt())
            setOnClickListener {
                moveCursorToFirst()
            }
        }
        fragCursorLASTCHAR.run {
            setPadding(changeDPtoPX(10).toInt())
            setOnClickListener {
                moveCursorToLast()
            }
        }
        fragCursorSelectAll.run {
            setOnClickListener {
                selectAll()
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
            }
        }
        fragCursorSelectCut.run {
            setOnClickListener {
                cutText()
                isSelectingActivated = false
                fragCursorSelectWord.text = "선택"
            }
        }
        fragCursorSelectCopy.run {
            setOnClickListener {
                copyText()
                isSelectingActivated = false
                fragCursorSelectWord.text = "선택"
            }
        }
        fragCursorSelectPaste.run {
            setOnClickListener {
                pasteText()
                isSelectingActivated = false
                fragCursorSelectWord.text = "선택"
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
                    qwertyHangul.composeChar(' ', currentInputConnection)
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
                    if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(myKeyboardVibrationIntensity)
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
                        if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(myKeyboardVibrationIntensity)
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
                        if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(myKeyboardVibrationIntensity)
                    })
            )
        }
        fragCursorKBD.run {
            setPadding(changeDPtoPX(5).toInt())
            setOnClickListener {
                isCursorFragment = false
                buttonCursorFragment.setImageResource(R.drawable.ic_move)
                isSelectingActivated = false
                fragCursorSelectWord.text = "선택"
                setLayoutByMode(fontTypeIndex, modeValue)
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
                if ((myKeyboardToggleNum && currentInputEditorInfo.inputType !in inputTypeNumbers) || (!myKeyboardToggleNum && (modeValue == 5 || modeValue == 6))) {
                    keyboardViewFirstLine.visibility = View.VISIBLE
                    keyboardViewLayout.layoutParams.height = changeDPtoPX(220 * myKeyboardHeight / 100).toInt() + myKeyboardBotMargin
                } else {
                    keyboardViewFirstLine.visibility = View.GONE
                    keyboardViewLayout.layoutParams.height = changeDPtoPX(180 * myKeyboardHeight / 100).toInt() + myKeyboardBotMargin
                }
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
                if (myKeyboardToggleNum || (!myKeyboardToggleNum && (modeValue == 5 || modeValue == 6))) {
                    keyboardViewFirstLine.visibility = View.VISIBLE
                    keyboardViewLayout.layoutParams.height = changeDPtoPX(220 * myKeyboardHeight / 100).toInt() + myKeyboardBotMargin
                } else {
                    keyboardViewFirstLine.visibility = View.GONE
                    keyboardViewLayout.layoutParams.height = changeDPtoPX(180 * myKeyboardHeight / 100).toInt() + myKeyboardBotMargin
                }
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
                if (myKeyboardToggleNum || (!myKeyboardToggleNum && (modeValue == 5 || modeValue == 6))) {
                    keyboardViewFirstLine.visibility = View.VISIBLE
                    keyboardViewLayout.layoutParams.height =
                        changeDPtoPX(220 * myKeyboardHeight / 100).toInt() + myKeyboardBotMargin
                } else {
                    keyboardViewFirstLine.visibility = View.GONE
                    keyboardViewLayout.layoutParams.height =
                        changeDPtoPX(180 * myKeyboardHeight / 100).toInt() + myKeyboardBotMargin
                }
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
                        else -> fragmentKeyboardQWERTY
                    })

                if (myKeyboardToggleNum || (!myKeyboardToggleNum && (modeValue == 5 || modeValue == 6))) {
                    keyboardViewFirstLine.visibility = View.VISIBLE
                    keyboardViewLayout.layoutParams.height = changeDPtoPX(220 * myKeyboardHeight / 100).toInt() + myKeyboardBotMargin
                } else {
                    keyboardViewFirstLine.visibility = View.GONE
                    keyboardViewLayout.layoutParams.height = changeDPtoPX(180 * myKeyboardHeight / 100).toInt() + myKeyboardBotMargin
                }
            }
            4 -> {
                for (index in btnCharList.indices) {
                    btnCharList[index].text = krChar[index]
                    btnCharList[index].setTypeface(typeface, Typeface.NORMAL)
                }
                for (index in btnShiftList.indices) btnShiftList[index].text = krShiftChar[index + 7]
                btnSHIFT.setImageResource(shiftIcon_activated)
                btnSHIFT.setColorFilter(myFunctionFontColor)
                btnSHIFT.setPadding(changeDPtoPX(10).toInt())
                btnSPACE.text = getString(R.string.korean)
                if (myKeyboardToggleNum || (!myKeyboardToggleNum && (modeValue == 5 || modeValue == 6))) {
                    keyboardViewFirstLine.visibility = View.VISIBLE
                    keyboardViewLayout.layoutParams.height = changeDPtoPX(220 * myKeyboardHeight / 100).toInt() + myKeyboardBotMargin
                } else {
                    keyboardViewFirstLine.visibility = View.GONE
                    keyboardViewLayout.layoutParams.height = changeDPtoPX(180 * myKeyboardHeight / 100).toInt() + myKeyboardBotMargin
                }
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
                keyboardViewLayout.layoutParams.height = changeDPtoPX(220 * myKeyboardHeight / 100).toInt() + myKeyboardBotMargin
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
                keyboardViewLayout.layoutParams.height = changeDPtoPX(220 * myKeyboardHeight / 100).toInt() + myKeyboardBotMargin
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
        if (myKeyboardVibration == 1 || myKeyboardVibration == 2) vibrateByButton(myKeyboardVibrationIntensity)
    }

    private fun delFunction(inputMethodKR: Int) {
        if (currentInputConnection == null) return
        delBoolean = true
        val selectedText = currentInputConnection.getSelectedText(GET_TEXT_WITH_STYLES)
        val cursorPosition = currentInputConnection?.getSelectedText(GET_TEXT_WITH_STYLES)?.get(0)
        if (TextUtils.isEmpty(selectedText)) {
            when (inputMethodKR) {
                0 -> qwertyHangul.delete(cursorPosition, currentInputConnection)
                1 -> chunjiinHangul.delete(cursorPosition, currentInputConnection)
                2 -> naratgulHangul.delete(cursorPosition, currentInputConnection)
                3 -> chunjiinHangul.delete(cursorPosition, currentInputConnection)
                else -> qwertyHangul.delete(cursorPosition, currentInputConnection)
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

    private fun vibrateByButton(int: Int) {
        val vibrator: Vibrator = getSystemService(VIBRATOR_SERVICE) as Vibrator
        if (vibrator.hasVibrator()) {
            if (SDK_INT >= Build.VERSION_CODES.O) {
                vibrator.vibrate(VibrationEffect.createOneShot((int + 25).toLong(), 25 + int))
            } else {
                vibrator.vibrate((int + 25).toLong())
            }
        }
    }

    private fun changeRightSize(resize: Int) {
        for (item in btnRight) {
            item.run {
                layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, resize / 100.toFloat())
            }
            btnL.layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, (1.3 * resize / 100).toFloat())
        }
    }

    private fun changeDPtoPX(dp: Int): Float {
        return dp * (Resources.getSystem().displayMetrics.density)
    }

    private fun startApp(string: String) {
        val intent =
            applicationContext.packageManager.getLaunchIntentForPackage(string)
        if (intent != null) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        } else {
            return
        }
    }

    @RequiresApi(Build.VERSION_CODES.R)
    private fun selectWord() {
        if (currentInputConnection == null) return
        if (!isSelectingActivated) {
            isSelectingActivated = true
            fragCursorSelectWord.text = "선택 취소"
        } else {
            isSelectingActivated = false
            fragCursorSelectWord.text = "선택"
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun selectAll() {
        if (currentInputConnection == null) return
        if (currentInputConnection.requestCursorUpdates(CURSOR_UPDATE_IMMEDIATE)) {
            val wholeText =
                currentInputConnection.getExtractedText(ExtractedTextRequest(), 0).text.length
            clearComposing()
            currentInputConnection.setSelection(0, wholeText)
            savedCursorPosition = -1
        }
    }

    private fun moveCursorToUp() {
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

    private fun moveCursorToDown() {
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

    private fun moveCursorToLeft() {
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

    private fun moveCursorToRight() {
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
    private fun moveCursorToFirst() {
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
    private fun moveCursorToLast() {
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

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun copyText() {
        if (currentInputConnection == null) return
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
    private fun cutText() {
        if (currentInputConnection == null) return
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
    private fun pasteText() {
        if (currentInputConnection == null) return
        if (currentInputConnection.requestCursorUpdates(CURSOR_UPDATE_IMMEDIATE)) {
            clearComposing()
            currentInputConnection.performContextMenuAction(android.R.id.paste)
        }
    }

    private fun clearComposing() {
        currentInputConnection.finishComposingText()
        qwertyHangul.initState()
        qwertyHangul.initChar()
        qwertyHangul.composeResult()
        qwertyHangul.initResult()
        chunjiinHangul.initState()
        chunjiinHangul.initChar()
        chunjiinHangul.composeResult()
        chunjiinHangul.initResult()
        naratgulHangul.initState()
        naratgulHangul.initChar()
        naratgulHangul.composeResult()
        naratgulHangul.initResult()
    }

    private fun addFunction(int: Int) {
        when (int) {
            0 -> {

            }
            1 -> {
                autoTextMode = 1

                isCursorFragment = false
                buttonCursorFragment.setImageResource(R.drawable.ic_move)

                isSelectingActivated = false
                fragCursorSelectWord.text = "선택"

                if (!isAutoTextFragment) {
                    isAutoTextFragment = true
                    keyboardView.keyboardViewFrameLayout.removeAllViews()
                    keyboardView.keyboardViewFrameLayout.addView(fragmentAutoText)
                    buttonBoilerPlateTxt.setImageResource(R.drawable.ic_keyboard_black)
                    fragAutoNEXT.text = "1/2"
                    autoText1.text = myKeyboardAutoText1
                    autoText2.text = myKeyboardAutoText2
                    autoText3.text = myKeyboardAutoText3
                    autoText4.text = myKeyboardAutoText4
                    autoText5.text = myKeyboardAutoText5
                    autoText6.text = myKeyboardAutoText6
                    autoText7.text = myKeyboardAutoText7
                    autoText8.text = myKeyboardAutoText8

                    if (myKeyboardToggleNum) {
                        keyboardViewFirstLine.visibility = View.VISIBLE
                        keyboardViewLayout.layoutParams.height = changeDPtoPX(220 * myKeyboardHeight / 100).toInt() + myKeyboardBotMargin
                    } else {
                        keyboardViewFirstLine.visibility = View.GONE
                        keyboardViewLayout.layoutParams.height = changeDPtoPX(180 * myKeyboardHeight / 100).toInt() + myKeyboardBotMargin
                    }
                }
            }
            2 -> {
                autoTextMode = 1

                isAutoTextFragment = false
                buttonBoilerPlateTxt.setImageResource(R.drawable.ic_boilerplatetext_black)

                isSelectingActivated = false
                fragCursorSelectWord.text = "선택"

                if (!isCursorFragment) {
                    isCursorFragment = true
                    keyboardView.keyboardViewFrameLayout.removeAllViews()
                    keyboardView.keyboardViewFrameLayout.addView(fragmentCursor)
                    buttonCursorFragment.setImageResource(R.drawable.ic_keyboard_black)
                    if (myKeyboardToggleNum) {
                        keyboardViewFirstLine.visibility = View.VISIBLE
                        keyboardViewLayout.layoutParams.height = changeDPtoPX(220 * myKeyboardHeight / 100).toInt() + myKeyboardBotMargin
                    } else {
                        keyboardViewFirstLine.visibility = View.GONE
                        keyboardViewLayout.layoutParams.height = changeDPtoPX(180 * myKeyboardHeight / 100).toInt() + myKeyboardBotMargin
                    }
                }
            }
            else -> {

            }
        }
    }

    private fun detNumKeyboard() {
        if ((myKeyboardToggleNum && currentInputEditorInfo.inputType !in inputTypeNumbers) || (!myKeyboardToggleNum && (modeValue == 5 || modeValue == 6))) {
            keyboardViewFragment.removeAllViews()
            when (myKeyboardInputMethodKR) {
                0 -> keyboardView.keyboardViewFrameLayout.addView(fragmentKeyboardQWERTY)
                1 -> keyboardView.keyboardViewFrameLayout.addView(fragmentKeyboardChunjiin)
                2 -> keyboardView.keyboardViewFrameLayout.addView(fragmentKeyboardNaratgul)
                3 -> keyboardView.keyboardViewFrameLayout.addView(fragmentKeyboardChunjiinA)
                else -> keyboardView.keyboardViewFrameLayout.addView(fragmentKeyboardQWERTY)
            }
            if (myKeyboardToggleNum || (!myKeyboardToggleNum && (modeValue == 5 || modeValue == 6))) {
                keyboardViewFirstLine.visibility = View.VISIBLE
                keyboardViewLayout.layoutParams.height = changeDPtoPX(220 * myKeyboardHeight / 100).toInt() + myKeyboardBotMargin
            } else {
                keyboardViewFirstLine.visibility = View.GONE
                keyboardViewLayout.layoutParams.height = changeDPtoPX(180 * myKeyboardHeight / 100).toInt() + myKeyboardBotMargin
            }
            keyboardViewLayout.layoutParams.height = changeDPtoPX(220 * myKeyboardHeight / 100).toInt() + myKeyboardBotMargin
            keyboardBackgroundImage.layoutParams.height = keyboardViewLayout.layoutParams.height
            keyboardViewFirstLine.visibility = View.VISIBLE
            keyboardViewFirstLine.layoutParams.height = changeDPtoPX(40 * myKeyboardHeight / 100).toInt()
            keyboardViewLinearLayout.layoutParams.height = changeDPtoPX(180 * myKeyboardHeight / 100).toInt() + myKeyboardBotMargin
        } else {
            keyboardViewFragment.removeAllViews()
            keyboardViewFragment.addView(fragmentKeyboardNumber)
            keyboardViewLayout.layoutParams.height = changeDPtoPX(180 * myKeyboardHeight / 100).toInt() + myKeyboardBotMargin
            keyboardBackgroundImage.layoutParams.height = keyboardViewLayout.layoutParams.height
            keyboardViewFirstLine.visibility = View.GONE
            keyboardViewLinearLayout.layoutParams.height = changeDPtoPX(180 * myKeyboardHeight / 100).toInt() + myKeyboardBotMargin
        }
    }

    override fun getLifecycle(): Lifecycle {
        return lifecycleRegistry
    }
}