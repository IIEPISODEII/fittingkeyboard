package com.sb.fittingkeyboard.keyboardsettings.ui

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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sb.fittingKeyboard.R
import com.sb.fittingKeyboard.databinding.FragmentDetailedsettingBinding
import com.sb.fittingkeyboard.data.KEYBOARD_AUTO_CAPITALIZATION
import com.sb.fittingkeyboard.data.KEYBOARD_AUTO_MODE_CHANGE
import com.sb.fittingkeyboard.data.KEYBOARD_BOTTOM_MARGIN
import com.sb.fittingkeyboard.data.KEYBOARD_CHUN_SPACEBAR_POSITION
import com.sb.fittingkeyboard.data.KEYBOARD_DIVISION
import com.sb.fittingkeyboard.data.KEYBOARD_FONT_SIZE
import com.sb.fittingkeyboard.data.KEYBOARD_HOLDING_TIME
import com.sb.fittingkeyboard.data.KEYBOARD_LEFTSIDE_MARGIN
import com.sb.fittingkeyboard.data.KEYBOARD_MO_SIZE
import com.sb.fittingkeyboard.data.KEYBOARD_RIGHTSIDE_MARGIN
import com.sb.fittingkeyboard.data.KEYBOARD_SETTING
import com.sb.fittingkeyboard.data.KEYBOARD_SWIPEABLE_SPACE
import com.sb.fittingkeyboard.data.KEYBOARD_TOGGLE_NUMBER
import com.sb.fittingkeyboard.data.KEYBOARD_TOGGLE_TOOLBAR
import com.sb.fittingkeyboard.data.KEYBOARD_TOOLBAR_ACTIVE_COPY
import com.sb.fittingkeyboard.data.KEYBOARD_TOOLBAR_ACTIVE_CUT
import com.sb.fittingkeyboard.data.KEYBOARD_TOOLBAR_ACTIVE_GO_SETTING
import com.sb.fittingkeyboard.data.KEYBOARD_TOOLBAR_ACTIVE_PASTE
import com.sb.fittingkeyboard.data.KEYBOARD_TOOLBAR_ACTIVE_SELECT_ALL
import com.sb.fittingkeyboard.data.KEYBOARD_TOOLBAR_ACTIVE_SHOW_BOILERPLATE
import com.sb.fittingkeyboard.data.KEYBOARD_TOOLBAR_ACTIVE_SHOW_CURSOR
import com.sb.fittingkeyboard.data.KEYBOARD_TOOLBAR_ACTIVE_SHOW_EMOJI
import com.sb.fittingkeyboard.data.KEYBOARD_TOOLBAR_ACTIVE_SHOW_NUMBER
import com.sb.fittingkeyboard.data.KEYBOARD_TOP_ROW_GAP
import com.sb.fittingkeyboard.data.KEYBOARD_VIBRATION_INTENSITY
import com.sb.fittingkeyboard.data.KEYBOARD_VIBRATION_USE
import com.sb.fittingkeyboard.keyboardsettings.ui.adapter.ItemTouchHelperListener
import com.sb.fittingkeyboard.keyboardsettings.ui.adapter.ToolbarSettingAdapter
import com.sb.fittingkeyboard.keyboardsettings.ui.adapter.dataholder.ToolbarSettingDataHolder
import com.sb.fittingkeyboard.keyboardsettings.util.showHelpDialog
import com.sb.fittingkeyboard.keyboardsettings.viewmodel.KeyboardSettingViewModel

class FragmentSettingDetails : Fragment() {

    private lateinit var prefSetting: SharedPreferences
    private lateinit var binding: FragmentDetailedsettingBinding
    private lateinit var viewModel: KeyboardSettingViewModel

    private lateinit var toolbarSettingRecyclerView: RecyclerView
    private val toolbarSettingKeys = listOf(
        KEYBOARD_TOOLBAR_ACTIVE_GO_SETTING,
        KEYBOARD_TOOLBAR_ACTIVE_SHOW_BOILERPLATE,
        KEYBOARD_TOOLBAR_ACTIVE_SELECT_ALL,
        KEYBOARD_TOOLBAR_ACTIVE_COPY,
        KEYBOARD_TOOLBAR_ACTIVE_CUT,
        KEYBOARD_TOOLBAR_ACTIVE_PASTE,
        KEYBOARD_TOOLBAR_ACTIVE_SHOW_CURSOR,
        KEYBOARD_TOOLBAR_ACTIVE_SHOW_NUMBER,
        KEYBOARD_TOOLBAR_ACTIVE_SHOW_EMOJI
    )

    private val onItemChanged = object: ToolbarSettingAdapter.OnToolbarChanged {
        override fun onToolbarChange(id: String, value: Int) {
            prefSetting.edit().putInt(id, value).apply()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detailedsetting, container, false)

        prefSetting = requireContext().getSharedPreferences(KEYBOARD_SETTING, Context.MODE_PRIVATE)

        viewModel = ViewModelProvider(this)[KeyboardSettingViewModel::class.java]
        binding.lifecycleOwner = viewLifecycleOwner
        binding.kbSettingViewmodel = viewModel

        binding.seekbarDetailedsettingRightsize.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                prefSetting.edit().putInt(KEYBOARD_MO_SIZE, seekBar?.progress!!).apply()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                prefSetting.edit().putInt(KEYBOARD_MO_SIZE, seekBar?.progress!!).apply()
            }
        })
        binding.switchDetailedsettingDivision.setOnCheckedChangeListener { _, it ->
            prefSetting.edit().putBoolean(KEYBOARD_DIVISION, it).apply()
        }
        binding.switchDetailedsettingGap.setOnCheckedChangeListener { _, it ->
            prefSetting.edit().putInt(KEYBOARD_TOP_ROW_GAP, if (it) View.VISIBLE else View.GONE).apply()
        }
        binding.switchDetailedsettingTogglingToolbar.setOnCheckedChangeListener { _, it ->
            prefSetting.edit()?.putInt(KEYBOARD_TOGGLE_TOOLBAR, if (it) View.VISIBLE else View.GONE)?.apply()
        }
        binding.seekbarDetailedsettingBottomMargin.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                prefSetting.edit()?.putInt(KEYBOARD_BOTTOM_MARGIN, seekBar?.progress!!)?.apply()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                prefSetting.edit()?.putInt(KEYBOARD_BOTTOM_MARGIN, seekBar?.progress!!)?.apply()
            }
        })
        binding.seekbarDetailedsettingLongclickDuration.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                prefSetting.edit()?.putInt(KEYBOARD_HOLDING_TIME, seekBar?.progress!!)?.apply()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                prefSetting.edit()?.putInt(KEYBOARD_HOLDING_TIME, seekBar?.progress!!)?.apply()
            }
        })
        binding.switchDetailedsettingTogglingNumberRow.setOnCheckedChangeListener { _, it ->
            prefSetting.edit()?.putInt(KEYBOARD_TOGGLE_NUMBER, if (it) View.VISIBLE else View.GONE)?.apply()
        }
        binding.switchDetailedsettingVibration.setOnCheckedChangeListener { _, it ->
            prefSetting.edit()?.putBoolean(KEYBOARD_VIBRATION_USE, it)?.apply()
        }
        binding.seekbarDetailedsettingVibrationIntensity.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                prefSetting.edit()?.putInt(KEYBOARD_VIBRATION_INTENSITY, seekBar?.progress!!)?.apply()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                prefSetting.edit()?.putInt(KEYBOARD_VIBRATION_INTENSITY, seekBar?.progress!!)?.apply()
            }
        })
        binding.seekbarDetailedsettingLeftsideMargin.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                prefSetting.edit()?.putInt(KEYBOARD_LEFTSIDE_MARGIN, seekBar?.progress!!)?.apply()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                prefSetting.edit()?.putInt(KEYBOARD_LEFTSIDE_MARGIN, seekBar?.progress!!)?.apply()
            }
        })
        binding.seekbarDetailedsettingRightsideMargin.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                prefSetting.edit()?.putInt(KEYBOARD_RIGHTSIDE_MARGIN, seekBar?.progress!!)?.apply()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                prefSetting.edit()?.putInt(KEYBOARD_RIGHTSIDE_MARGIN, seekBar?.progress!!)?.apply()
            }
        })
        binding.seekbarDetailedsettingFontsize.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                prefSetting.edit()?.putInt(KEYBOARD_FONT_SIZE, seekBar?.progress!!*2 + 14)?.apply()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                prefSetting.edit()?.putInt(KEYBOARD_FONT_SIZE, seekBar?.progress!!*2 + 14)?.apply()
            }
        })
        binding.switchDetailedsettingAutoCapitalization.setOnCheckedChangeListener { _, it ->
            prefSetting.edit()?.putBoolean(KEYBOARD_AUTO_CAPITALIZATION, it)?.apply()
        }
        binding.switchDetailedsettingAutoModeChange.setOnCheckedChangeListener { _, it ->
            prefSetting.edit()?.putBoolean(KEYBOARD_AUTO_MODE_CHANGE, it)?.apply()
        }
        binding.switchDetailedsettingSwipeableSpace.setOnCheckedChangeListener { _, it ->
            prefSetting.edit()?.putBoolean(KEYBOARD_SWIPEABLE_SPACE, it)?.apply()
        }
        binding.switchDetailedsettingTogglingSpacebarPosition.setOnCheckedChangeListener { _, it ->
            prefSetting.edit()?.putBoolean(KEYBOARD_CHUN_SPACEBAR_POSITION, it)?.apply()
        }

        binding.tvDetailedsettingDescriptionForDivision.setOnClickListener {
            showHelpDialog(getString(R.string.keyboard_division_help_text), this.requireActivity().window!!.context)
        }
        binding.tvDetailedsettingDescriptionForRightsize.setOnClickListener {
            showHelpDialog(getString(R.string.keyboard_right_size_help_text), this.requireActivity().window!!.context)
        }
        binding.tvDetailedsettingDescriptionForGap.setOnClickListener {
            showHelpDialog(getString(R.string.keyboard_top_row_gap_help_text), this.requireActivity().window!!.context)
        }
        binding.tvDetailedsettingDescriptionForBottomMargin.setOnClickListener {
            showHelpDialog(getString(R.string.keyboard_bot_margin_help_text), this.requireActivity().window!!.context)
        }
        binding.tvDetailedsettingDescriptionForLongclickDuration.setOnClickListener {
            showHelpDialog(getString(R.string.keyboard_holding_help_text), this.requireActivity().window!!.context)
        }
        binding.tvDetailedsettingDescriptionForLeftsideMargin.setOnClickListener {
            showHelpDialog(getString(R.string.keyboard_leftside_margin_help_text), this.requireActivity().window!!.context)
        }
        binding.tvDetailedsettingDescriptionForRightsideMargin.setOnClickListener {
            showHelpDialog(getString(R.string.keyboard_rightside_margin_help_text), this.requireActivity().window!!.context)
        }
        binding.tvDetailedsettingDescriptionForVibration.setOnClickListener {
            showHelpDialog(getString(R.string.keyboard_vibration_help_text), this.requireActivity().window!!.context)
        }
        binding.tvDetailedsettingDescriptionForToolbar.setOnClickListener {
            showHelpDialog(getString(R.string.keyboard_toggleSet_help_text), this.requireActivity().window!!.context)
        }
        binding.tvDetailedsettingDescriptionForAutoCapitalization.setOnClickListener {
            showHelpDialog(getString(R.string.keyboard_autoCapital_help_text), this.requireActivity().window!!.context)
        }
        binding.tvDetailedsettingDescriptionForAutoModeChange.setOnClickListener {
            showHelpDialog(getString(R.string.keyboard_autoModeChange_help_text), this.requireActivity().window!!.context)
        }
        binding.tvDetailedsettingDescriptionForSwipeableSpace.setOnClickListener {
            showHelpDialog(getString(R.string.keyboard_swipeable_space_help_text), this.requireActivity().window!!.context)
        }
        binding.tvDetailedsettingDescriptionForSpacebarPosition.setOnClickListener {
            showHelpDialog(getString(R.string.keyboard_swap_space_position_help_text), this.requireActivity().window!!.context)
        }
        binding.btnDetailedsettingTitleForCustomizingToolbar.setOnClickListener {
            showHelpDialog(getString(R.string.keyboard_toolbar_setting_help_text), this.requireActivity().window!!.context)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val toolbarSettingDataHolderList = mutableListOf(
            ToolbarSettingDataHolder(
                KEYBOARD_TOOLBAR_ACTIVE_GO_SETTING,
                R.drawable.ic_settings,
                "설정 바로가기",
                prefSetting.getInt(KEYBOARD_TOOLBAR_ACTIVE_GO_SETTING, 1) >= 0
            ),
            ToolbarSettingDataHolder(
                KEYBOARD_TOOLBAR_ACTIVE_SHOW_BOILERPLATE,
                R.drawable.ic_boilerplatetext_black,
                "상용구창 바로가기",
                prefSetting.getInt(KEYBOARD_TOOLBAR_ACTIVE_SHOW_BOILERPLATE, 2) >= 0
            ),
            ToolbarSettingDataHolder(
                KEYBOARD_TOOLBAR_ACTIVE_SELECT_ALL,
                R.drawable.ic_select,
                "전체 선택",
                prefSetting.getInt(KEYBOARD_TOOLBAR_ACTIVE_SELECT_ALL, 3) >= 0
            ),
            ToolbarSettingDataHolder(
                KEYBOARD_TOOLBAR_ACTIVE_COPY,
                R.drawable.ic_copy,
                "복사",
                prefSetting.getInt(KEYBOARD_TOOLBAR_ACTIVE_COPY, 4) >= 0
            ),
            ToolbarSettingDataHolder(
                KEYBOARD_TOOLBAR_ACTIVE_CUT,
                R.drawable.ic_cut,
                "잘라내기",
                prefSetting.getInt(KEYBOARD_TOOLBAR_ACTIVE_CUT, 5) >= 0
            ),
            ToolbarSettingDataHolder(
                KEYBOARD_TOOLBAR_ACTIVE_PASTE,
                R.drawable.ic_paste,
                "붙여넣기",
                prefSetting.getInt(KEYBOARD_TOOLBAR_ACTIVE_PASTE, 6) >= 0
            ),
            ToolbarSettingDataHolder(
                KEYBOARD_TOOLBAR_ACTIVE_SHOW_CURSOR,
                R.drawable.ic_move,
                "커서창 바로가기",
                prefSetting.getInt(KEYBOARD_TOOLBAR_ACTIVE_SHOW_CURSOR, 7) >= 0
            ),
            ToolbarSettingDataHolder(
                KEYBOARD_TOOLBAR_ACTIVE_SHOW_NUMBER,
                R.drawable.ic_number_keypad,
                "숫자창 바로가기",
                prefSetting.getInt(KEYBOARD_TOOLBAR_ACTIVE_SHOW_NUMBER, 8) >= 0
            ),
            ToolbarSettingDataHolder(
                KEYBOARD_TOOLBAR_ACTIVE_SHOW_EMOJI,
                R.drawable.ic_outline_emoji_emotions_24,
                "이모티콘창 바로가기",
                prefSetting.getInt(KEYBOARD_TOOLBAR_ACTIVE_SHOW_EMOJI, 9) >= 0
            )
        )
            .sortedWith(
                Comparator { mine, other ->
                    return@Comparator if (kotlin.math.abs(prefSetting.getInt(
                            mine.settingId,
                            toolbarSettingKeys.indexOf(mine.settingId))
                        ) > kotlin.math.abs(prefSetting.getInt(
                            other.settingId,
                            toolbarSettingKeys.indexOf(other.settingId))
                        )
                    ) 1 else -1
                }
            ).toMutableList()

        binding.seekbarDetailedsettingFontsize.progress = (prefSetting.getInt(KEYBOARD_FONT_SIZE, 16)-14)/2

        val toolbarSettingRecyclerViewAdapter = ToolbarSettingAdapter(toolbarSettingDataHolderList)
        toolbarSettingRecyclerViewAdapter.setOnToolbarChangeListener(onItemChanged)
        toolbarSettingRecyclerView = binding.recyviewDetailedsettingCustomizingToolbar
        toolbarSettingRecyclerView.apply {
            adapter = toolbarSettingRecyclerViewAdapter
            layoutManager = LinearLayoutManager(this@FragmentSettingDetails.requireContext())
            isNestedScrollingEnabled = false
        }
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