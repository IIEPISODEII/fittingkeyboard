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
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.sb.fittingKeyboard.R
import com.sb.fittingKeyboard.com.sb.fittingKeyboard.keyboardSettings.ItemTouchHelperListener
import com.sb.fittingKeyboard.com.sb.fittingKeyboard.keyboardSettings.ToolbarSettingAdapter
import com.sb.fittingKeyboard.com.sb.fittingKeyboard.keyboardSettings.data.ToolbarSettingDataHolder
import com.sb.fittingKeyboard.databinding.SettingMainTab2Binding
import com.sb.fittingKeyboard.keyboardSettings.util.UsualFunctions
import com.sb.fittingKeyboard.service.util.KeyboardUtil
import com.sb.fittingKeyboard.service.viewmodel.SharedKBViewModel
import java.lang.Math.abs

class SettingDetailFragment : Fragment() {

    private lateinit var prefSetting: SharedPreferences
    private lateinit var binding: SettingMainTab2Binding
    private lateinit var vm: SharedKBViewModel

    private lateinit var toolbarSettingRecyclerView: RecyclerView

    private val onItemChanged = object: ToolbarSettingAdapter.OnToolbarChanged {
        override fun onToolbarChange(id: String, value: Int) {
            prefSetting.edit().putInt(id, value).apply()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.setting_main_tab2, container, false)

        prefSetting = requireContext().getSharedPreferences(KeyboardUtil.KEYBOARD_SETTING, Context.MODE_PRIVATE)

        vm = ViewModelProvider(this)[SharedKBViewModel::class.java]
        binding.lifecycleOwner = viewLifecycleOwner
        binding.kbviewmodel = vm

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val toolbarSettingKeys = listOf(
            KeyboardUtil.KEYBOARD_TOOLBAR_ACTIVE_GO_SETTING,
            KeyboardUtil.KEYBOARD_TOOLBAR_ACTIVE_SHOW_BOILERPLATE,
            KeyboardUtil.KEYBOARD_TOOLBAR_ACTIVE_SELECT_ALL,
            KeyboardUtil.KEYBOARD_TOOLBAR_ACTIVE_COPY,
            KeyboardUtil.KEYBOARD_TOOLBAR_ACTIVE_CUT,
            KeyboardUtil.KEYBOARD_TOOLBAR_ACTIVE_PASTE,
            KeyboardUtil.KEYBOARD_TOOLBAR_ACTIVE_SHOW_CURSOR,
            KeyboardUtil.KEYBOARD_TOOLBAR_ACTIVE_SHOW_NUMBER,
            KeyboardUtil.KEYBOARD_TOOLBAR_ACTIVE_SHOW_EMOJI
        )
        val toolbarSettingDataHolderList = mutableListOf(
            ToolbarSettingDataHolder(
                KeyboardUtil.KEYBOARD_TOOLBAR_ACTIVE_GO_SETTING,
                R.drawable.ic_settings,
                "설정",
                prefSetting.getInt(KeyboardUtil.KEYBOARD_TOOLBAR_ACTIVE_GO_SETTING, 1) >= 0
            ),
            ToolbarSettingDataHolder(
                KeyboardUtil.KEYBOARD_TOOLBAR_ACTIVE_SHOW_BOILERPLATE,
                R.drawable.ic_boilerplatetext_black,
                "상용구",
                prefSetting.getInt(KeyboardUtil.KEYBOARD_TOOLBAR_ACTIVE_SHOW_BOILERPLATE, 2) >= 0
            ),
            ToolbarSettingDataHolder(
                KeyboardUtil.KEYBOARD_TOOLBAR_ACTIVE_SELECT_ALL,
                R.drawable.ic_select,
                "전체 선택",
                prefSetting.getInt(KeyboardUtil.KEYBOARD_TOOLBAR_ACTIVE_SELECT_ALL, 3) >= 0
            ),
            ToolbarSettingDataHolder(
                KeyboardUtil.KEYBOARD_TOOLBAR_ACTIVE_COPY,
                R.drawable.ic_copy,
                "복사",
                prefSetting.getInt(KeyboardUtil.KEYBOARD_TOOLBAR_ACTIVE_COPY, 4) >= 0
            ),
            ToolbarSettingDataHolder(
                KeyboardUtil.KEYBOARD_TOOLBAR_ACTIVE_CUT,
                R.drawable.ic_cut,
                "잘라내기",
                prefSetting.getInt(KeyboardUtil.KEYBOARD_TOOLBAR_ACTIVE_CUT, 5) >= 0
            ),
            ToolbarSettingDataHolder(
                KeyboardUtil.KEYBOARD_TOOLBAR_ACTIVE_PASTE,
                R.drawable.ic_paste,
                "붙여넣기",
                prefSetting.getInt(KeyboardUtil.KEYBOARD_TOOLBAR_ACTIVE_PASTE, 6) >= 0
            ),
            ToolbarSettingDataHolder(
                KeyboardUtil.KEYBOARD_TOOLBAR_ACTIVE_SHOW_CURSOR,
                R.drawable.ic_move,
                "커서창 표시",
                prefSetting.getInt(KeyboardUtil.KEYBOARD_TOOLBAR_ACTIVE_SHOW_CURSOR, 7) >= 0
            ),
            ToolbarSettingDataHolder(
                KeyboardUtil.KEYBOARD_TOOLBAR_ACTIVE_SHOW_NUMBER,
                R.drawable.ic_number_keypad,
                "숫자창 표시",
                prefSetting.getInt(KeyboardUtil.KEYBOARD_TOOLBAR_ACTIVE_SHOW_NUMBER, 8) >= 0
            ),
            ToolbarSettingDataHolder(
                KeyboardUtil.KEYBOARD_TOOLBAR_ACTIVE_SHOW_EMOJI,
                R.drawable.ic_number_keypad,
                "이모티콘창 표시",
                prefSetting.getInt(KeyboardUtil.KEYBOARD_TOOLBAR_ACTIVE_SHOW_EMOJI, 9) >= 0
            ),
        )
            .sortedWith(
                Comparator { mine, other ->
                    return@Comparator if (prefSetting.getInt(
                            mine.settingId,
                            toolbarSettingKeys.indexOf(mine.settingId)
                        ) > prefSetting.getInt(
                            other.settingId,
                            toolbarSettingKeys.indexOf(other.settingId)
                        )
                    ) 1 else -1
                }
            ).toMutableList()

        val toolbarSettingRecyclerViewAdapter = ToolbarSettingAdapter(toolbarSettingDataHolderList)
        toolbarSettingRecyclerViewAdapter.setOnToolbarChangeListener(onItemChanged)
        toolbarSettingRecyclerView = binding.recyclerviewKeyboardToolbarSetting
        toolbarSettingRecyclerView.adapter = toolbarSettingRecyclerViewAdapter
        val itemTouchHelperCallback = ItemTouchHelperCallback(toolbarSettingRecyclerViewAdapter)
        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(toolbarSettingRecyclerView)
    }

    class ItemTouchHelperCallback(private val listener: ItemTouchHelperListener) :
        ItemTouchHelper.Callback() {
        override fun getMovementFlags(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder
        ): Int {
            val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
            val swipeFlags = 0

            return makeMovementFlags(dragFlags, swipeFlags)
        }

        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return listener.onItemMove(viewHolder.adapterPosition, target.adapterPosition)
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            listener.onItemSwipe(viewHolder.adapterPosition)
        }
    }
}