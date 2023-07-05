package com.sb.fittingkeyboard.keyboardsettings.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.sb.fittingKeyboard.R
import com.sb.fittingkeyboard.data.KEYBOARD_ENTERKEY_LONGCLICK
import com.sb.fittingkeyboard.data.KEYBOARD_HEIGHT
import com.sb.fittingkeyboard.data.KEYBOARD_IME_KR
import com.sb.fittingkeyboard.data.KEYBOARD_SETTING
import com.sb.fittingkeyboard.data.KEYBOARD_SPECIALKEY_LONGCLICK
import com.sb.fittingkeyboard.keyboardsettings.util.showHelpDialog

class FragmentSettingBasic : Fragment() {
    private val prefSetting by lazy { requireContext().getSharedPreferences(KEYBOARD_SETTING, Context.MODE_PRIVATE) }

    private var keyboardHeight: Int = 0
    private var specialkeyLongClickFunction: Int = 0
    private var enterkeyLongClickFunction: Int = 0
    private var selectedInputMethodKR: Int = 0

    private val dialogSettingSpecialkeyLongClick = DialogSettingLongClickFunction()
    private val dialogSettingEnterkeyLongClick = DialogSettingLongClickFunction()
    private val dialogSettingInputMethodKR = DialogSettingInputMethodKR()

    private var fragmentView: View? = null
    private lateinit var seekbarKeyboardHeightSetting: SeekBar
    private lateinit var tvKeyboardHeightSetting: TextView
    private lateinit var btnSpecialKeyLongClickSetting: Button
    private lateinit var tvSpecialKeyLongClickSetting: TextView
    private lateinit var btnEnterKeyLongClickSetting: Button
    private lateinit var tvEnterKeyLongClickSetting: TextView
    private lateinit var btnIMEKRSetting: Button
    private lateinit var tvIMEKRSetting: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentView = inflater.inflate(R.layout.fragment_basicsetting, container, false)
        seekbarKeyboardHeightSetting = fragmentView!!.findViewById(R.id.seekbar_basicsetting_keyboard_height)
        tvKeyboardHeightSetting = fragmentView!!.findViewById(R.id.tv_basicsetting_keyboard_height_progress)
        btnSpecialKeyLongClickSetting = fragmentView!!.findViewById(R.id.btn_basicsetting_specialkey_longclick)
        tvSpecialKeyLongClickSetting = fragmentView!!.findViewById(R.id.tv_basicsetting_description_for_specialkey_longclick)
        btnEnterKeyLongClickSetting = fragmentView!!.findViewById(R.id.btn_basicsetting_enterkey_longclick)
        tvEnterKeyLongClickSetting = fragmentView!!.findViewById(R.id.tv_basicsetting_description_for_enterkey_longclick)
        btnIMEKRSetting = fragmentView!!.findViewById(R.id.btn_basicsetting_ime_kr)
        tvIMEKRSetting = fragmentView!!.findViewById(R.id.tv_basicsetting_description_for_ime_kr)

        initializeSetting()
        loadData()
        dialogSettingInputMethodKR.setFunction(selectedInputMethodKR)

        keyboardHeight = seekbarKeyboardHeightSetting.progress
        tvKeyboardHeightSetting.text = "${keyboardHeight+75}%"
        btnEnterKeyLongClickSetting.text = requireActivity().resources.getString(
            when(enterkeyLongClickFunction) {
                1 -> R.string.longclick_funciton_boilerplate
                2 -> R.string.longclick_funciton_cursor_pad
                3 -> R.string.longclick_funciton_number_pad
                4 -> R.string.longclick_funciton_emoji
                else -> R.string.longclick_function_none
            }
        )
        btnSpecialKeyLongClickSetting.text = requireActivity().resources.getString(
            when(specialkeyLongClickFunction) {
                1 -> R.string.longclick_funciton_boilerplate
                2 -> R.string.longclick_funciton_cursor_pad
                3 -> R.string.longclick_funciton_number_pad
                4 -> R.string.longclick_funciton_emoji
                else -> R.string.longclick_function_none
            }
        )
        btnIMEKRSetting.text = when (selectedInputMethodKR) {
            0 -> "쿼티(기본)"
            1 -> "천지인"
            2 -> "나랏글"
            3 -> "천지인 양손"
            4 -> " 단모음"
            else -> "쿼티(기본)"
        }

        seekbarKeyboardHeightSetting.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                keyboardHeight = seekBar?.progress!!
                tvKeyboardHeightSetting.text = "${keyboardHeight + 75}%"
                prefSetting.edit().putInt(KEYBOARD_HEIGHT, keyboardHeight).apply()

            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                keyboardHeight = seekBar?.progress!!
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                keyboardHeight = seekBar?.progress!!
                prefSetting.edit().putInt(KEYBOARD_HEIGHT, keyboardHeight).apply()

            }
        })
        btnSpecialKeyLongClickSetting.setOnClickListener {
            dialogSettingSpecialkeyLongClick.show(requireActivity().supportFragmentManager, "SpecialKeyAddonSelector")
        }
        btnEnterKeyLongClickSetting.setOnClickListener {
            dialogSettingEnterkeyLongClick.show(requireActivity().supportFragmentManager, "EnterKeyAddonSelector")
        }
        btnIMEKRSetting.setOnClickListener {
            dialogSettingInputMethodKR.show(requireActivity().supportFragmentManager, "InputMethodKRSelector")
        }

        tvKeyboardHeightSetting.setOnClickListener {
            showHelpDialog(getString(R.string.keyboard_height_help_text), this.requireActivity().window.context)
        }
        tvSpecialKeyLongClickSetting.setOnClickListener {
            showHelpDialog(getString(R.string.specialkeyAddon_help_text), this.requireActivity().window.context)
        }
        tvEnterKeyLongClickSetting.setOnClickListener {
            showHelpDialog(getString(R.string.enterkeyAddon_help_text), this.requireActivity().window.context)
        }
        tvIMEKRSetting.setOnClickListener {
            showHelpDialog(getString(R.string.inputMethodKR_help_text), this.requireActivity().window.context)
        }

        return fragmentView
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentView = null
    }

    override fun onResume() {
        super.onResume()
        loadData()
    }

    private fun initializeSetting() {
        dialogSettingSpecialkeyLongClick.apply {
            initializeSetting(prefSetting.getInt(KEYBOARD_ENTERKEY_LONGCLICK, 0))

            setOnRadioButtonSelectListener(object: DialogSettingLongClickFunction.OnRadioButtonSelectListener {
                override fun onSelect(functionIndex: Int) {
                    specialkeyLongClickFunction = functionIndex
                    prefSetting.edit().putInt(KEYBOARD_SPECIALKEY_LONGCLICK, functionIndex).apply()

                    btnSpecialKeyLongClickSetting.text = requireActivity().resources.getString(
                        when(functionIndex) {
                            1 -> R.string.longclick_funciton_boilerplate
                            2 -> R.string.longclick_funciton_cursor_pad
                            3 -> R.string.longclick_funciton_number_pad
                            4 -> R.string.longclick_funciton_emoji
                            else -> R.string.longclick_function_none
                        }
                    )
                    Toast.makeText(requireContext(), "저장되었습니다.", Toast.LENGTH_SHORT).show()
                }
            })
            setTitleText("특수키 부가기능")
        }
        dialogSettingEnterkeyLongClick.apply {
            initializeSetting(prefSetting.getInt(KEYBOARD_ENTERKEY_LONGCLICK, 0))

            setOnRadioButtonSelectListener(object: DialogSettingLongClickFunction.OnRadioButtonSelectListener {
                override fun onSelect(functionIndex: Int) {
                    enterkeyLongClickFunction = functionIndex
                    prefSetting.edit().putInt(KEYBOARD_ENTERKEY_LONGCLICK, functionIndex).apply()

                    btnEnterKeyLongClickSetting.text = requireActivity().resources.getString(
                        when(functionIndex) {
                            1 -> R.string.longclick_funciton_boilerplate
                            2 -> R.string.longclick_funciton_cursor_pad
                            3 -> R.string.longclick_funciton_number_pad
                            4 -> R.string.longclick_funciton_emoji
                            else -> R.string.longclick_function_none
                        }
                    )
                    Toast.makeText(requireContext(), "저장되었습니다.", Toast.LENGTH_SHORT).show()
                }
            })
            setTitleText("엔터키 부가기능")
        }
    }

    private fun loadData() {
        keyboardHeight = prefSetting.getInt(KEYBOARD_HEIGHT, 25)

        enterkeyLongClickFunction = prefSetting.getInt(KEYBOARD_ENTERKEY_LONGCLICK, 0)

        specialkeyLongClickFunction = prefSetting.getInt(KEYBOARD_SPECIALKEY_LONGCLICK, 0)

        selectedInputMethodKR = prefSetting.getInt(KEYBOARD_IME_KR, 0)


        dialogSettingSpecialkeyLongClick.initializeSetting(specialkeyLongClickFunction)
        dialogSettingEnterkeyLongClick.initializeSetting(enterkeyLongClickFunction)
        btnEnterKeyLongClickSetting.text = requireActivity().resources.getString(
            when(enterkeyLongClickFunction) {
                1 -> R.string.longclick_funciton_boilerplate
                2 -> R.string.longclick_funciton_cursor_pad
                3 -> R.string.longclick_funciton_number_pad
                4 -> R.string.longclick_funciton_emoji
                else -> R.string.longclick_function_none
            }
        )
        btnSpecialKeyLongClickSetting.text = requireActivity().resources.getString(
            when(specialkeyLongClickFunction) {
                1 -> R.string.longclick_funciton_boilerplate
                2 -> R.string.longclick_funciton_cursor_pad
                3 -> R.string.longclick_funciton_number_pad
                4 -> R.string.longclick_funciton_emoji
                else -> R.string.longclick_function_none
            }
        )
    }
}