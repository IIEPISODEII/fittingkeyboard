package com.sb.fittingkeyboard.keyboardsettings.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sb.fittingKeyboard.R
import com.sb.fittingkeyboard.keyboardsettings.data.KeyboardThemesDataHolder
import com.sb.fittingkeyboard.keyboardsettings.ui.adapter.ThemeRecyclerAdapter
import com.sb.fittingkeyboard.Constants

class FragmentSettingThemes: Fragment() {
    private val prefSetting by lazy { requireContext().getSharedPreferences(Constants.KEYBOARD_SETTING, Context.MODE_PRIVATE) }

    private var selectedKbdTheme = 0
    private var selectedFontColor = 0xFF000000.toInt()
    private var selectedFunctionFontColor = 0xFF000000.toInt()
    private var selectedFontType = 0
    private val themeList = listOf(
        KeyboardThemesDataHolder(R.drawable.thumbnail_01),
        KeyboardThemesDataHolder(R.drawable.thumbnail_02),
        KeyboardThemesDataHolder(R.drawable.thumbnail_03),
        KeyboardThemesDataHolder(R.drawable.thumbnail_04),
        KeyboardThemesDataHolder(R.drawable.thumbnail_05),
        KeyboardThemesDataHolder(R.drawable.thumbnail_06),
        KeyboardThemesDataHolder(R.drawable.thumbnail_07),
        KeyboardThemesDataHolder(R.drawable.thumbnail_08),
        KeyboardThemesDataHolder(R.drawable.thumbnail_09),
        KeyboardThemesDataHolder(R.drawable.thumbnail_10),
        KeyboardThemesDataHolder(R.drawable.thumbnail_11),
        KeyboardThemesDataHolder(R.drawable.thumbnail_12),
        KeyboardThemesDataHolder(R.drawable.thumbnail_13),
        KeyboardThemesDataHolder(R.drawable.thumbnail_14),
        KeyboardThemesDataHolder(R.drawable.thumbnail_15),
        KeyboardThemesDataHolder(R.drawable.thumbnail_16),
        KeyboardThemesDataHolder(R.drawable.thumbnail_17),
        KeyboardThemesDataHolder(R.drawable.thumbnail_18),
        KeyboardThemesDataHolder(R.drawable.thumbnail_19)
    )
    private val themeRecyclerviewAdapter by lazy { ThemeRecyclerAdapter(themeList) }

    private val dialogDefaultFontColor = DialogSettingFontColorPicker()
    private val dialogFunctionFontColor = DialogSettingFontColorPicker()
    private val dialogFontTypePicker    by lazy { DialogSettingFontTypePicker(selectedFontType) }
    private var fragmentView: View? = null
    private val themeRecyclerview               by lazy { fragmentView!!.findViewById<RecyclerView>(R.id.recyview_themesetting_theme_container) }
    private val btnShowDefaultFontColorPicker   by lazy { fragmentView!!.findViewById<Button>(R.id.btn_themesetting_normalkey_font_color) }
    private val btnShowFunctionFontColorPicker  by lazy { fragmentView!!.findViewById<Button>(R.id.btn_themesetting_functionkey_font_color) }
    private val btnShowFontTypePicker           by lazy { fragmentView!!.findViewById<Button>(R.id.btn_themesetting_font_type) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentView = inflater.inflate(R.layout.fragment_themesetting, container, false)

        loadData()
        initializeSettingValue()
        btnShowDefaultFontColorPicker.setOnClickListener {
            dialogDefaultFontColor.show(requireActivity().supportFragmentManager, "colorPicker")
        }

        btnShowFunctionFontColorPicker.setOnClickListener {
            dialogFunctionFontColor.show(requireActivity().supportFragmentManager, "functionFontColorPicker")
        }

        btnShowFontTypePicker.setOnClickListener {
            dialogFontTypePicker.show(requireActivity().supportFragmentManager, "fontTypePicker")
        }
        themeRecyclerview.apply {
            layoutManager = GridLayoutManager(requireActivity(), 3)
            setHasFixedSize(true)
            adapter = themeRecyclerviewAdapter
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

    private fun initializeSettingValue() {
        themeRecyclerviewAdapter.initializeCurrentTheme(selectedKbdTheme)
        dialogDefaultFontColor.setFontColor(selectedFontColor)
        dialogFunctionFontColor.setFontColor(selectedFunctionFontColor)
        themeRecyclerviewAdapter.setOnPickThemeListener(object: ThemeRecyclerAdapter.OnPickThemeListener {
            override fun onPick(themeIndex: Int) {
                prefSetting.edit().putInt(Constants.KEYBOARD_THEME, themeIndex).apply()
                when (themeIndex) {
                    0 -> {
                        selectedFontColor = 0xFF000000.toInt()
                        selectedFunctionFontColor = 0xFF000000.toInt()
                        dialogDefaultFontColor.setFontColor(selectedFontColor)
                        dialogFunctionFontColor.setFontColor(selectedFunctionFontColor)
                        prefSetting.edit().putInt(Constants.KEYBOARD_FONT_COLOR, selectedFontColor).apply()
                        prefSetting.edit().putInt(Constants.KEYBOARD_FUNCTION_FONT_COLOR, selectedFunctionFontColor).apply()
                    }
                    1 -> {
                        selectedFontColor = 0xFFFFFFFF.toInt()
                        selectedFunctionFontColor = 0xFFFFFFFF.toInt()
                        dialogDefaultFontColor.setFontColor(selectedFontColor)
                        dialogFunctionFontColor.setFontColor(selectedFunctionFontColor)
                        prefSetting.edit().putInt(Constants.KEYBOARD_FONT_COLOR, selectedFontColor).apply()
                        prefSetting.edit().putInt(Constants.KEYBOARD_FUNCTION_FONT_COLOR, selectedFunctionFontColor).apply()
                    }
                    2 -> {
                        selectedFontColor = 0xFF343434.toInt()
                        selectedFunctionFontColor = 0xFFFFFFFF.toInt()
                        dialogDefaultFontColor.setFontColor(selectedFontColor)
                        dialogFunctionFontColor.setFontColor(selectedFunctionFontColor)
                        prefSetting.edit().putInt(Constants.KEYBOARD_FONT_COLOR, selectedFontColor).apply()
                        prefSetting.edit().putInt(Constants.KEYBOARD_FUNCTION_FONT_COLOR, selectedFunctionFontColor).apply()
                    }
                    3 -> {
                        selectedFontColor = 0xFF000000.toInt()
                        selectedFunctionFontColor = 0xFFFF0000.toInt()
                        dialogDefaultFontColor.setFontColor(selectedFontColor)
                        dialogFunctionFontColor.setFontColor(selectedFunctionFontColor)
                        prefSetting.edit().putInt(Constants.KEYBOARD_FONT_COLOR, selectedFontColor).apply()
                        prefSetting.edit().putInt(Constants.KEYBOARD_FUNCTION_FONT_COLOR, selectedFunctionFontColor).apply()
                    }
                    4 -> {
                        selectedFontColor = 0xFF000000.toInt()
                        selectedFunctionFontColor = 0xFFFF0000.toInt()
                        dialogDefaultFontColor.setFontColor(selectedFontColor)
                        dialogFunctionFontColor.setFontColor(selectedFunctionFontColor)
                        prefSetting.edit().putInt(Constants.KEYBOARD_FONT_COLOR, selectedFontColor).apply()
                        prefSetting.edit().putInt(Constants.KEYBOARD_FUNCTION_FONT_COLOR, selectedFunctionFontColor).apply()
                    }
                    5 -> {
                        selectedFontColor = 0xFF000000.toInt()
                        selectedFunctionFontColor = 0xFFFFFFFF.toInt()
                        dialogDefaultFontColor.setFontColor(selectedFontColor)
                        dialogFunctionFontColor.setFontColor(selectedFunctionFontColor)
                        prefSetting.edit().putInt(Constants.KEYBOARD_FONT_COLOR, 0xFF000000.toInt()).apply()
                        prefSetting.edit().putInt(Constants.KEYBOARD_FUNCTION_FONT_COLOR, selectedFunctionFontColor).apply()
                    }
                    6 -> {
                        selectedFontColor = 0xFF000000.toInt()
                        selectedFunctionFontColor = 0xFF0000FF.toInt()
                        dialogDefaultFontColor.setFontColor(selectedFontColor)
                        dialogFunctionFontColor.setFontColor(selectedFunctionFontColor)
                        prefSetting.edit().putInt(Constants.KEYBOARD_FONT_COLOR, selectedFontColor).apply()
                        prefSetting.edit().putInt(Constants.KEYBOARD_FUNCTION_FONT_COLOR, selectedFunctionFontColor).apply()
                    }
                    7, 8, 9 -> {
                        selectedFontColor = 0xFFFFFFFF.toInt()
                        selectedFunctionFontColor = 0xFFFFFFFF.toInt()
                        dialogDefaultFontColor.setFontColor(selectedFontColor)
                        dialogFunctionFontColor.setFontColor(selectedFunctionFontColor)
                        prefSetting.edit().putInt(Constants.KEYBOARD_FONT_COLOR, selectedFontColor).apply()
                        prefSetting.edit().putInt(Constants.KEYBOARD_FUNCTION_FONT_COLOR, selectedFunctionFontColor).apply()
                    }
                    10, 11 -> {
                        selectedFontColor = 0xFF000000.toInt()
                        selectedFunctionFontColor = 0xFF000000.toInt()
                        dialogDefaultFontColor.setFontColor(selectedFontColor)
                        dialogFunctionFontColor.setFontColor(selectedFunctionFontColor)
                        prefSetting.edit().putInt(Constants.KEYBOARD_FONT_COLOR, selectedFontColor).apply()
                        prefSetting.edit().putInt(Constants.KEYBOARD_FUNCTION_FONT_COLOR, selectedFunctionFontColor).apply()
                    }
                    12 -> {
                        selectedFontColor = 0xFF000000.toInt()
                        selectedFunctionFontColor = 0xFFFFFFFF.toInt()
                        dialogDefaultFontColor.setFontColor(selectedFontColor)
                        dialogFunctionFontColor.setFontColor(selectedFunctionFontColor)
                        prefSetting.edit().putInt(Constants.KEYBOARD_FONT_COLOR, selectedFontColor).apply()
                        prefSetting.edit().putInt(Constants.KEYBOARD_FUNCTION_FONT_COLOR, selectedFunctionFontColor).apply()
                    }
                    13 -> {
                        selectedFontColor = 0xFF0000FF.toInt()
                        selectedFunctionFontColor = 0xFF000000.toInt()
                        dialogDefaultFontColor.setFontColor(selectedFontColor)
                        dialogFunctionFontColor.setFontColor(selectedFunctionFontColor)
                        prefSetting.edit().putInt(Constants.KEYBOARD_FONT_COLOR, selectedFontColor).apply()
                        prefSetting.edit().putInt(Constants.KEYBOARD_FUNCTION_FONT_COLOR, selectedFunctionFontColor).apply()
                    }
                    14 -> {
                        selectedFontColor = 0xFF7F00FF.toInt()
                        selectedFunctionFontColor = 0xFF000000.toInt()
                        dialogDefaultFontColor.setFontColor(selectedFontColor)
                        dialogFunctionFontColor.setFontColor(selectedFunctionFontColor)
                        prefSetting.edit().putInt(Constants.KEYBOARD_FONT_COLOR, selectedFontColor).apply()
                        prefSetting.edit().putInt(Constants.KEYBOARD_FUNCTION_FONT_COLOR, selectedFunctionFontColor).apply()
                    }
                    15 -> {
                        selectedFontColor = 0xFFFFFFFF.toInt()
                        selectedFunctionFontColor = 0xFFFFFFFF.toInt()
                        dialogDefaultFontColor.setFontColor(selectedFontColor)
                        dialogFunctionFontColor.setFontColor(selectedFunctionFontColor)
                        prefSetting.edit().putInt(Constants.KEYBOARD_FONT_COLOR, selectedFontColor).apply()
                        prefSetting.edit().putInt(Constants.KEYBOARD_FUNCTION_FONT_COLOR, selectedFunctionFontColor).apply()
                    }
                    16 -> {
                        selectedFontColor = 0xFF000000.toInt()
                        selectedFunctionFontColor = 0xFF000000.toInt()
                        dialogDefaultFontColor.setFontColor(selectedFontColor)
                        dialogFunctionFontColor.setFontColor(selectedFunctionFontColor)
                        prefSetting.edit().putInt(Constants.KEYBOARD_FONT_COLOR, selectedFontColor).apply()
                        prefSetting.edit().putInt(Constants.KEYBOARD_FUNCTION_FONT_COLOR, selectedFunctionFontColor).apply()
                    }
                    17 -> {
                        selectedFontColor = 0xFFFFFFFF.toInt()
                        selectedFunctionFontColor = 0xFFFFFFFF.toInt()
                        dialogDefaultFontColor.setFontColor(selectedFontColor)
                        dialogFunctionFontColor.setFontColor(selectedFunctionFontColor)
                        prefSetting.edit().putInt(Constants.KEYBOARD_FONT_COLOR, selectedFontColor).apply()
                        prefSetting.edit().putInt(Constants.KEYBOARD_FUNCTION_FONT_COLOR, selectedFunctionFontColor).apply()
                    }
                    18 -> {
                        selectedFontColor = 0xFF000000.toInt()
                        selectedFunctionFontColor = 0xFF000000.toInt()
                        dialogDefaultFontColor.setFontColor(selectedFontColor)
                        dialogFunctionFontColor.setFontColor(selectedFunctionFontColor)
                        prefSetting.edit().putInt(Constants.KEYBOARD_FONT_COLOR, selectedFontColor).apply()
                        prefSetting.edit().putInt(Constants.KEYBOARD_FUNCTION_FONT_COLOR, selectedFunctionFontColor).apply()
                    }
                    else -> {
                    }
                }
            }
        })
        dialogDefaultFontColor.setOnPickColorListener(object: DialogSettingFontColorPicker.OnPickColorListener {
            override fun onPick(color: Int) {
                prefSetting.edit().putInt(Constants.KEYBOARD_FONT_COLOR, color).apply()
            }
        })
        dialogFunctionFontColor.setOnPickColorListener(object: DialogSettingFontColorPicker.OnPickColorListener {
            override fun onPick(color: Int) {
                prefSetting.edit().putInt(Constants.KEYBOARD_FUNCTION_FONT_COLOR, color).apply()
            }
        })
        dialogFontTypePicker.setOnCheckFontTypeListener(object: DialogSettingFontTypePicker.OnCheckFontTypeListener {
            override fun onCheck(index: Int) {
                prefSetting.edit().putInt(Constants.KEYBOARD_FONT_TYPE, index).apply()
            }
        })
    }

    private fun loadData() {
        selectedKbdTheme = prefSetting.getInt(Constants.KEYBOARD_THEME, 0)
        selectedFontColor = prefSetting.getInt(Constants.KEYBOARD_FONT_COLOR, 0xFF000000.toInt())
        selectedFunctionFontColor = prefSetting.getInt(Constants.KEYBOARD_FUNCTION_FONT_COLOR, 0xFF000000.toInt())
        dialogDefaultFontColor.setFontColor(selectedFontColor)
        dialogFunctionFontColor.setFontColor(selectedFunctionFontColor)
        selectedFontType = prefSetting.getInt(Constants.KEYBOARD_FONT_TYPE, 0)
    }
}