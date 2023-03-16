package com.sb.fittingKeyboard.com.sb.fittingKeyboard.keyboardSettings.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.sb.fittingKeyboard.R
import com.sb.fittingKeyboard.keyboardSettings.util.UsualFunctions

class FragmentSettingBasic : Fragment() {

    private var keyboardHeight: Int = 0

    var selectedSpecialKeyAddon: Int = 0
    var selectedEnterKeyAddon: Int = 0
    var selectedInputMethodKR: Int = 0

    private lateinit var dialogSettingSpecialKeyAddon: DialogSettingSpecialKeyAddon
    private lateinit var dialogSettingEnterKeyAddon: DialogSettingEnterKeyAddon
    private lateinit var dialogSettingInputMethodKR: DialogSettingInputMethodKR

    private lateinit var myView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dialogSettingSpecialKeyAddon = DialogSettingSpecialKeyAddon()
        dialogSettingEnterKeyAddon = DialogSettingEnterKeyAddon()
        dialogSettingInputMethodKR = DialogSettingInputMethodKR()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        myView = inflater.inflate(R.layout.fragment_setting_main, container, false)


        loadData()

        dialogSettingSpecialKeyAddon.setFunction(selectedSpecialKeyAddon)
        dialogSettingEnterKeyAddon.setFunction(selectedEnterKeyAddon)
        dialogSettingInputMethodKR.setFunction(selectedInputMethodKR)

        keyboardHeight = myView.findViewById<SeekBar>(R.id.setting_keyboard_height).progress
        myView.findViewById<TextView>(R.id.tv_keyboard_height_progress).text = "${keyboardHeight+75}%"
        myView.findViewById<Button>(R.id.setting_specialkeyAddon).text = when (selectedSpecialKeyAddon) {
            0 -> "없음"
            1 -> "상용구"
            2 -> "커서이동 및 편집"
            else -> "없음"
        }
        myView.findViewById<Button>(R.id.setting_enterkeyAddon).text = when (selectedEnterKeyAddon) {
            0 -> "없음"
            1 -> "상용구"
            2 -> "커서이동 및 편집"
            else -> "없음"
        }
        myView.findViewById<Button>(R.id.setting_inputMethodKR).text = when (selectedInputMethodKR) {
            0 -> "쿼티(기본)"
            1 -> "천지인"
            2 -> "나랏글"
            3 -> "천지인 양손"
            4 -> " 단모음"
            else -> "쿼티(기본)"
        }

        myView.findViewById<SeekBar>(R.id.setting_keyboard_height).setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                keyboardHeight = seekBar?.progress!!
                myView.findViewById<TextView>(R.id.tv_keyboard_height_progress).text = "${keyboardHeight + 75}%"
                saveData()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                keyboardHeight = seekBar?.progress!!
                saveData()
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                keyboardHeight = seekBar?.progress!!
                saveData()
            }
        })
        myView.findViewById<Button>(R.id.setting_specialkeyAddon).setOnClickListener {
            dialogSettingSpecialKeyAddon.show(fragmentManager!!, "SpecialKeyAddonSelector")
            saveData()
        }
        myView.findViewById<Button>(R.id.setting_enterkeyAddon).setOnClickListener {
            dialogSettingEnterKeyAddon.show(fragmentManager!!, "EnterKeyAddonSelector")
            saveData()
        }
        myView.findViewById<Button>(R.id.setting_inputMethodKR).setOnClickListener {
            dialogSettingInputMethodKR.show(fragmentManager!!, "InputMethodKRSelector")
            saveData()
        }

        myView.findViewById<Button>(R.id.tv_keyboard_height).setOnClickListener {
            UsualFunctions().showHelpText(getString(R.string.keyboard_height_help_text), this.activity!!.window!!.context)
        }
        myView.findViewById<Button>(R.id.tv_specialkeyAddon).setOnClickListener {
            UsualFunctions().showHelpText(getString(R.string.specialkeyAddon_help_text), this.activity!!.window!!.context)
        }
        myView.findViewById<Button>(R.id.tv_enterkeyAddon).setOnClickListener {
            UsualFunctions().showHelpText(getString(R.string.enterkeyAddon_help_text), this.activity!!.window!!.context)
        }
        myView.findViewById<Button>(R.id.tv_inputMethodKR).setOnClickListener {
            UsualFunctions().showHelpText(getString(R.string.inputMethodKR_help_text), this.activity!!.window!!.context)
        }


        return myView
    }

    override fun onPause() {
        super.onPause()
        saveData()
    }

    override fun onDestroy() {
        super.onDestroy()
        saveData()
    }

    override fun onResume() {
        super.onResume()
        loadData()
    }

    fun saveData() {
        val prefSetting = activity?.getSharedPreferences("keyboardSetting", Context.MODE_PRIVATE)

        selectedSpecialKeyAddon = dialogSettingSpecialKeyAddon.checkedFunction
        selectedEnterKeyAddon = dialogSettingEnterKeyAddon.checkedFunction
        selectedInputMethodKR = dialogSettingInputMethodKR.checkedFunction
        prefSetting?.edit()?.putInt("KeyboardHeight", keyboardHeight)?.apply()
        prefSetting?.edit()?.putInt("KeyboardSpecialkeyAddon", selectedSpecialKeyAddon)?.apply()
        prefSetting?.edit()?.putInt("KeyboardEnterkeyAddon", selectedEnterKeyAddon)?.apply()
        prefSetting?.edit()?.putInt("KeyboardInputMethodKR", selectedInputMethodKR)?.apply()
    }

    fun loadData() {

        val prefSetting = activity?.getSharedPreferences("keyboardSetting", Context.MODE_PRIVATE)

        if (prefSetting != null) myView.findViewById<SeekBar>(R.id.setting_keyboard_height).progress = prefSetting.getInt("KeyboardHeight", 25)
        if (prefSetting != null) selectedSpecialKeyAddon = prefSetting.getInt("KeyboardSpecialkeyAddon", 0)
        if (prefSetting != null) selectedEnterKeyAddon = prefSetting.getInt("KeyboardEnterkeyAddon", 0)
        if (prefSetting != null) selectedInputMethodKR = prefSetting.getInt("KeyboardInputMethodKR", 0)
    }
}