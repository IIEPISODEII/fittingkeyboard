package com.sb.fittingKeyboard.KeyboardSettings

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.SeekBar
import android.widget.Switch
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.sb.fittingKeyboard.R

class SettingDetailFragment : Fragment() {

    private var keyboardDivision: Boolean = true
    private var keyboardMoSize: Int = 20
    private var keyboardVibration: Int = 2
    private var keyboardVibrationIntensity: Int = 50
    private var keyboardHoldingTime: Int = 0
    private var keyboardToggleNum: Boolean = true
    private var keyboardBotMargin: Int = 1
    private var keyboardLeftSideMargin: Int = 0
    private var keyboardRightSideMargin: Int = 0
    private var keyboardToggleToolbar: Boolean = true
    private var keyboardFontSize: Int = 1
    private var keyboardAutoCapital: Boolean = true
    private var keyboardAutoModeChange: Boolean = true
    private lateinit var myView: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        myView = inflater.inflate(R.layout.setting_main_tab2, container, false)

        loadData()

        keyboardMoSize = myView.findViewById<SeekBar>(R.id.setting_keyboard_rightsize).progress
        keyboardDivision = myView.findViewById<Switch>(R.id.setting_keyboard_division_bool).isChecked
        keyboardHoldingTime = myView.findViewById<SeekBar>(R.id.setting_keyboard_holding).progress
        keyboardToggleNum = myView.findViewById<Switch>(R.id.setting_keyboard_toggleNumber).isChecked
        keyboardVibration = myView.findViewById<SeekBar>(R.id.setting_keyboard_vibration).progress
        keyboardVibrationIntensity = myView.findViewById<SeekBar>(R.id.setting_keyboard_vibration_intensity).progress
        keyboardBotMargin = myView.findViewById<SeekBar>(R.id.setting_keyboard_bot_margin).progress
        keyboardLeftSideMargin = myView.findViewById<SeekBar>(R.id.setting_keyboard_leftside_margin).progress
        keyboardRightSideMargin = myView.findViewById<SeekBar>(R.id.setting_keyboard_rightside_margin).progress
        keyboardToggleToolbar = myView.findViewById<Switch>(R.id.setting_keyboard_toggleToolbar).isChecked
        keyboardFontSize = myView.findViewById<SeekBar>(R.id.setting_keyboard_fontsize).progress
        keyboardAutoCapital = myView.findViewById<Switch>(R.id.setting_keyboard_autoCapital).isChecked
        keyboardAutoModeChange = myView.findViewById<Switch>(R.id.setting_keyboard_autoModeChange).isChecked
        myView.findViewById<TextView>(R.id.tv_keyboard_rightsize_progress).text = "${keyboardMoSize + 80}%"
        myView.findViewById<TextView>(R.id.tv_keyboard_holding_progress).text = "${keyboardHoldingTime + 100}ms"
        myView.findViewById<TextView>(R.id.tv_keyboard_vibration_intensity).text = "$keyboardVibrationIntensity"
        myView.findViewById<TextView>(R.id.tv_keyboard_bot_margin_progress).text = "$keyboardBotMargin"
        myView.findViewById<TextView>(R.id.tv_keyboard_leftside_margin_progress).text = "$keyboardLeftSideMargin%"
        myView.findViewById<TextView>(R.id.tv_keyboard_rightside_margin_progress).text = "$keyboardRightSideMargin%"


        myView.findViewById<SeekBar>(R.id.setting_keyboard_rightsize).setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                keyboardMoSize = seekBar?.progress!!
                myView.findViewById<TextView>(R.id.tv_keyboard_rightsize_progress).text = "${keyboardMoSize + 80}%"
                saveData()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                keyboardMoSize = seekBar?.progress!!
                saveData()
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                keyboardMoSize = seekBar?.progress!!
                saveData()
            }
        })
        myView.findViewById<Switch>(R.id.setting_keyboard_division_bool).setOnCheckedChangeListener { buttonView, _ ->
            keyboardDivision = buttonView.isChecked
            saveData()
        }
        myView.findViewById<Switch>(R.id.setting_keyboard_toggleToolbar).setOnCheckedChangeListener { buttonView, _ ->
            keyboardToggleToolbar = buttonView.isChecked
            saveData()
        }
        myView.findViewById<SeekBar>(R.id.setting_keyboard_bot_margin).setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                keyboardBotMargin = seekBar?.progress!! + 1
                myView.findViewById<TextView>(R.id.tv_keyboard_bot_margin_progress).text = "$keyboardBotMargin"
                saveData()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                keyboardHoldingTime = seekBar?.progress!!
                saveData()
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                keyboardHoldingTime = seekBar?.progress!!
                saveData()
            }
        })
        myView.findViewById<SeekBar>(R.id.setting_keyboard_holding).setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                keyboardHoldingTime = seekBar?.progress!!
                myView.findViewById<TextView>(R.id.tv_keyboard_holding_progress).text = "${keyboardHoldingTime + 100}ms"
                saveData()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                keyboardHoldingTime = seekBar?.progress!!
                saveData()
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                keyboardHoldingTime = seekBar?.progress!!
                saveData()
            }
        })
        myView.findViewById<Switch>(R.id.setting_keyboard_toggleNumber).setOnCheckedChangeListener { buttonView, _ ->
            keyboardToggleNum = buttonView.isChecked
            saveData()
        }
        myView.findViewById<SeekBar>(R.id.setting_keyboard_vibration).setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                keyboardVibration = seekBar?.progress!!
                saveData()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                keyboardVibration = seekBar?.progress!!
                saveData()
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                keyboardVibration = seekBar?.progress!!
                saveData()
            }
        })
        myView.findViewById<SeekBar>(R.id.setting_keyboard_vibration_intensity).setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                keyboardVibrationIntensity = seekBar?.progress!!
                myView.findViewById<TextView>(R.id.tv_keyboard_vibration_intensity).text = "$keyboardVibrationIntensity"
                saveData()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                keyboardVibrationIntensity = seekBar?.progress!!
                saveData()
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                keyboardVibrationIntensity = seekBar?.progress!!
                saveData()
            }
        })
        myView.findViewById<SeekBar>(R.id.setting_keyboard_leftside_margin).setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                keyboardLeftSideMargin = seekBar?.progress!!
                myView.findViewById<TextView>(R.id.tv_keyboard_leftside_margin_progress).text = "$keyboardLeftSideMargin%"
                saveData()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                keyboardLeftSideMargin = seekBar?.progress!!
                saveData()
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                keyboardLeftSideMargin = seekBar?.progress!!
                saveData()
            }
        })
        myView.findViewById<SeekBar>(R.id.setting_keyboard_rightside_margin).setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                keyboardRightSideMargin = seekBar?.progress!!
                myView.findViewById<TextView>(R.id.tv_keyboard_rightside_margin_progress).text = "$keyboardRightSideMargin%"
                saveData()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                keyboardRightSideMargin = seekBar?.progress!!
                saveData()
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                keyboardRightSideMargin = seekBar?.progress!!
                saveData()
            }
        })
        myView.findViewById<SeekBar>(R.id.setting_keyboard_fontsize).setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                keyboardFontSize = seekBar?.progress!!
                saveData()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                keyboardFontSize = seekBar?.progress!!
                saveData()
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                keyboardFontSize = seekBar?.progress!!
                saveData()
            }
        })
        myView.findViewById<Switch>(R.id.setting_keyboard_autoCapital).setOnCheckedChangeListener { buttonView, _ ->
            keyboardAutoCapital = buttonView.isChecked
            saveData()
        }
        myView.findViewById<Switch>(R.id.setting_keyboard_autoModeChange).setOnCheckedChangeListener { buttonView, _ ->
            keyboardAutoModeChange = buttonView.isChecked
            saveData()
        }

        myView.findViewById<Button>(R.id.tv_keyboard_division_bool).setOnClickListener {
            UsualFunctions().showHelpText(getString(R.string.keyboard_division_help_text), this.activity!!.window!!.context)
        }
        myView.findViewById<Button>(R.id.tv_keyboard_rightsize).setOnClickListener {
            UsualFunctions().showHelpText(getString(R.string.keyboard_right_size_help_text), this.activity!!.window!!.context)
        }
        myView.findViewById<Button>(R.id.tv_keyboard_bot_margin).setOnClickListener {
            UsualFunctions().showHelpText(getString(R.string.keyboard_bot_margin_help_text), this.activity!!.window!!.context)
        }
        myView.findViewById<Button>(R.id.tv_keyboard_holding).setOnClickListener {
            UsualFunctions().showHelpText(getString(R.string.keyboard_holding_help_text), this.activity!!.window!!.context)
        }
        myView.findViewById<Button>(R.id.tv_keyboard_leftside_margin).setOnClickListener {
            UsualFunctions().showHelpText(getString(R.string.keyboard_leftside_margin_help_text), this.activity!!.window!!.context)
        }
        myView.findViewById<Button>(R.id.tv_keyboard_rightside_margin).setOnClickListener {
            UsualFunctions().showHelpText(getString(R.string.keyboard_rightside_margin_help_text), this.activity!!.window!!.context)
        }
        myView.findViewById<Button>(R.id.tv_keyboard_vibration).setOnClickListener {
            UsualFunctions().showHelpText(getString(R.string.keyboard_vibration_help_text), this.activity!!.window!!.context)
        }
        myView.findViewById<Button>(R.id.tv_keyboard_toggleToolbar).setOnClickListener {
            UsualFunctions().showHelpText(getString(R.string.keyboard_toggleSet_help_text), this.activity!!.window!!.context)
        }
        myView.findViewById<Button>(R.id.tv_keyboard_autoCapital).setOnClickListener {
            UsualFunctions().showHelpText(getString(R.string.keyboard_autoCapital_help_text), this.activity!!.window!!.context)
        }
        myView.findViewById<Button>(R.id.tv_keyboard_autoModeChange).setOnClickListener {
            UsualFunctions().showHelpText(getString(R.string.keyboard_autoModeChange_help_text), this.activity!!.window!!.context)
        }
        return myView
    }

    private fun saveData() {
        val prefSetting = activity?.getSharedPreferences("keyboardSetting", Context.MODE_PRIVATE)

        prefSetting?.edit()?.putInt("KeyboardMoSize", keyboardMoSize)?.apply()
        prefSetting?.edit()?.putBoolean("KeyboardDivision", keyboardDivision)?.apply()
        prefSetting?.edit()?.putInt("KeyboardHolding", keyboardHoldingTime)?.apply()
        prefSetting?.edit()?.putBoolean("KeyboardToggleNum", keyboardToggleNum)?.apply()
        prefSetting?.edit()?.putInt("KeyboardVibration", keyboardVibration)?.apply()
        prefSetting?.edit()?.putInt("KeyboardVibrationIntensity", keyboardVibrationIntensity)?.apply()
        prefSetting?.edit()?.putInt("KeyboardBottomMargin", keyboardBotMargin)?.apply()
        prefSetting?.edit()?.putInt("KeyboardLeftSideMargin", keyboardLeftSideMargin)?.apply()
        prefSetting?.edit()?.putInt("KeyboardRightSideMargin", keyboardRightSideMargin)?.apply()
        prefSetting?.edit()?.putBoolean("KeyboardToggleToolBar", keyboardToggleToolbar)?.apply()
        prefSetting?.edit()?.putInt("KeyboardFontSize", keyboardFontSize)?.apply()
        prefSetting?.edit()?.putBoolean("KeyboardAutoCapital", keyboardAutoCapital)?.apply()
        prefSetting?.edit()?.putBoolean("KeyboardAutoModeChange", keyboardAutoModeChange)?.apply()
    }

    fun loadData() {
        val prefSetting = activity?.getSharedPreferences("keyboardSetting", Context.MODE_PRIVATE)

        if (prefSetting != null) myView.findViewById<SeekBar>(R.id.setting_keyboard_rightsize).progress = prefSetting.getInt("KeyboardMoSize", 20)
        if (prefSetting != null) myView.findViewById<SeekBar>(R.id.setting_keyboard_holding).progress = prefSetting.getInt("KeyboardHolding", 200)
        if (prefSetting != null) myView.findViewById<SeekBar>(R.id.setting_keyboard_vibration).progress = prefSetting.getInt("KeyboardVibration", 2)
        if (prefSetting != null) myView.findViewById<SeekBar>(R.id.setting_keyboard_vibration_intensity).progress = prefSetting.getInt("KeyboardVibrationIntensity", 0)
        if (prefSetting != null) myView.findViewById<SeekBar>(R.id.setting_keyboard_bot_margin).progress = prefSetting.getInt("KeyboardBottomMargin", 1)
        if (prefSetting != null) myView.findViewById<SeekBar>(R.id.setting_keyboard_leftside_margin).progress = prefSetting.getInt("KeyboardLeftSideMargin", 0)
        if (prefSetting != null) myView.findViewById<SeekBar>(R.id.setting_keyboard_rightside_margin).progress = prefSetting.getInt("KeyboardRightSideMargin", 0)
        if (prefSetting != null) myView.findViewById<SeekBar>(R.id.setting_keyboard_fontsize).progress = prefSetting.getInt("KeyboardFontSize", 1)
        if (prefSetting != null) myView.findViewById<Switch>(R.id.setting_keyboard_division_bool).isChecked = prefSetting.getBoolean("KeyboardDivision", true)
        if (prefSetting != null) myView.findViewById<Switch>(R.id.setting_keyboard_toggleNumber).isChecked = prefSetting.getBoolean("KeyboardToggleNum", true)
        if (prefSetting != null) myView.findViewById<Switch>(R.id.setting_keyboard_toggleToolbar).isChecked = prefSetting.getBoolean("KeyboardToggleToolBar", true)
        if (prefSetting != null) myView.findViewById<Switch>(R.id.setting_keyboard_autoCapital).isChecked = prefSetting.getBoolean("KeyboardAutoCapital", true)
        if (prefSetting != null) myView.findViewById<Switch>(R.id.setting_keyboard_autoModeChange).isChecked = prefSetting.getBoolean("KeyboardAutoModeChange", true)
    }
}