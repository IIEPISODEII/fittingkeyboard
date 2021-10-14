package com.sb.fittingKeyboard.keyboardSettings

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.sb.fittingKeyboard.R
import com.sb.fittingKeyboard.databinding.SettingMainTab2Binding
import com.sb.fittingKeyboard.keyboardSettings.util.UsualFunctions
import com.sb.fittingKeyboard.service.util.KeyboardUtil
import com.sb.fittingKeyboard.service.viewmodel.SharedKBViewModel

class SettingDetailFragment : Fragment() {

    lateinit var prefSetting: SharedPreferences
    lateinit var binding: SettingMainTab2Binding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.setting_main_tab2, container, false)

        prefSetting = requireContext().getSharedPreferences(KeyboardUtil.KEYBOARD_SETTING, Context.MODE_PRIVATE)

        val vm = ViewModelProvider(this).get(SharedKBViewModel::class.java)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.kbviewmodel = vm

        vm.observeKBMoSize.observe(viewLifecycleOwner, {})
        vm.observeKBDivision.observe(viewLifecycleOwner, {})
        vm.observeKBHoldingTime.observe(viewLifecycleOwner, {})
        vm.observeNumberVisibility.observe(viewLifecycleOwner, {})
        vm.observeKBVibrationUse.observe(viewLifecycleOwner, {})
        vm.observeKBVibrationIntensity.observe(viewLifecycleOwner, {})
        vm.observeKBBottomMargin.observe(viewLifecycleOwner, {})
        vm.observeKBLeftSideMargin.observe(viewLifecycleOwner, {})
        vm.observeKBRightSideMargin.observe(viewLifecycleOwner, {})
        vm.observeKBFontSize.observe(viewLifecycleOwner, {})
        vm.observeKBAutoCapitalization.observe(viewLifecycleOwner, {})
        vm.observeKBAutoModeChange.observe(viewLifecycleOwner, {})

        binding.settingKeyboardRightsize.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                prefSetting.edit().putInt(KeyboardUtil.KEYBOARD_MO_SIZE, seekBar?.progress!!).apply()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                prefSetting.edit().putInt(KeyboardUtil.KEYBOARD_MO_SIZE, seekBar?.progress!!).apply()
            }
        })
        binding.settingKeyboardDivisionBool.setOnCheckedChangeListener { _, it ->
            prefSetting.edit().putBoolean(KeyboardUtil.KEYBOARD_DIVISION, it).apply()
        }
        binding.settingKeyboardToggleToolbar.setOnCheckedChangeListener { _, it ->
            prefSetting.edit()?.putInt(KeyboardUtil.KEYBOARD_TOGGLE_TOOLBAR, if (it) View.VISIBLE else View.GONE)?.apply()
        }
        binding.settingKeyboardBotMargin.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                prefSetting.edit()?.putInt(KeyboardUtil.KEYBOARD_BOTTOM_MARGIN, seekBar?.progress!!)?.apply()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                prefSetting.edit()?.putInt(KeyboardUtil.KEYBOARD_BOTTOM_MARGIN, seekBar?.progress!!)?.apply()
            }
        })
        binding.settingKeyboardHolding.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                prefSetting.edit()?.putInt(KeyboardUtil.KEYBOARD_HOLDING_TIME, seekBar?.progress!!)?.apply()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                prefSetting.edit()?.putInt(KeyboardUtil.KEYBOARD_HOLDING_TIME, seekBar?.progress!!)?.apply()
            }
        })
        binding.settingKeyboardToggleNumber.setOnCheckedChangeListener { _, it ->
            prefSetting.edit()?.putInt(KeyboardUtil.KEYBOARD_TOGGLE_NUMBER, if (it) View.VISIBLE else View.GONE)?.apply()
        }
        binding.settingKeyboardVibration.setOnCheckedChangeListener { _, it ->
            prefSetting.edit()?.putBoolean(KeyboardUtil.KEYBOARD_VIBRATION_USE, it)?.apply()
        }
        binding.settingKeyboardVibrationIntensity.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                prefSetting.edit()?.putInt(KeyboardUtil.KEYBOARD_VIBRATION_INTENSITY, seekBar?.progress!!)?.apply()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                prefSetting.edit()?.putInt(KeyboardUtil.KEYBOARD_VIBRATION_INTENSITY, seekBar?.progress!!)?.apply()
            }
        })
        binding.settingKeyboardLeftsideMargin.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                prefSetting.edit()?.putInt(KeyboardUtil.KEYBOARD_LEFTSIDE_MARGIN, seekBar?.progress!!)?.apply()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                prefSetting.edit()?.putInt(KeyboardUtil.KEYBOARD_LEFTSIDE_MARGIN, seekBar?.progress!!)?.apply()
            }
        })
        binding.settingKeyboardRightsideMargin.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                prefSetting.edit()?.putInt(KeyboardUtil.KEYBOARD_RIGHTSIDE_MARGIN, seekBar?.progress!!)?.apply()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                prefSetting.edit()?.putInt(KeyboardUtil.KEYBOARD_RIGHTSIDE_MARGIN, seekBar?.progress!!)?.apply()
            }
        })
        binding.settingKeyboardFontsize.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                prefSetting.edit()?.putInt(KeyboardUtil.KEYBOARD_FONT_SIZE, seekBar?.progress!!*2 + 14)?.apply()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                prefSetting.edit()?.putInt(KeyboardUtil.KEYBOARD_FONT_SIZE, seekBar?.progress!!*2 + 14)?.apply()
            }
        })
        binding.settingKeyboardAutoCapital.setOnCheckedChangeListener { _, it ->
            prefSetting.edit()?.putBoolean(KeyboardUtil.KEYBOARD_AUTO_CAPITALIZATION, it)?.apply()
        }
        binding.settingKeyboardAutoModeChange.setOnCheckedChangeListener { _, it ->
            prefSetting.edit()?.putBoolean(KeyboardUtil.KEYBOARD_AUTO_MODE_CHANGE, it)?.apply()
        }

        binding.tvKeyboardDivisionBool.setOnClickListener {
            UsualFunctions().showHelpText(getString(R.string.keyboard_division_help_text), this.requireActivity().window!!.context)
        }
        binding.tvKeyboardRightsize.setOnClickListener {
            UsualFunctions().showHelpText(getString(R.string.keyboard_right_size_help_text), this.requireActivity().window!!.context)
        }
        binding.tvKeyboardBotMargin.setOnClickListener {
            UsualFunctions().showHelpText(getString(R.string.keyboard_bot_margin_help_text), this.requireActivity().window!!.context)
        }
        binding.tvKeyboardHolding.setOnClickListener {
            UsualFunctions().showHelpText(getString(R.string.keyboard_holding_help_text), this.requireActivity().window!!.context)
        }
        binding.tvKeyboardLeftsideMargin.setOnClickListener {
            UsualFunctions().showHelpText(getString(R.string.keyboard_leftside_margin_help_text), this.requireActivity().window!!.context)
        }
        binding.tvKeyboardRightsideMargin.setOnClickListener {
            UsualFunctions().showHelpText(getString(R.string.keyboard_rightside_margin_help_text), this.requireActivity().window!!.context)
        }
        binding.tvKeyboardVibration.setOnClickListener {
            UsualFunctions().showHelpText(getString(R.string.keyboard_vibration_help_text), this.requireActivity().window!!.context)
        }
        binding.tvKeyboardToggleToolbar.setOnClickListener {

            UsualFunctions().showHelpText(getString(R.string.keyboard_toggleSet_help_text), this.requireActivity().window!!.context)
        }
        binding.tvKeyboardAutoCapital.setOnClickListener {
            UsualFunctions().showHelpText(getString(R.string.keyboard_autoCapital_help_text), this.requireActivity().window!!.context)
        }
        binding.tvKeyboardAutoModeChange.setOnClickListener {
            UsualFunctions().showHelpText(getString(R.string.keyboard_autoModeChange_help_text), this.requireActivity().window!!.context)
        }
        return binding.root
    }
}