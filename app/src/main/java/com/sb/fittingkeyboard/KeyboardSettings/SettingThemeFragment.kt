package com.sb.fittingKeyboard.KeyboardSettings

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.switchmaterial.SwitchMaterial
import com.google.android.material.textfield.TextInputEditText
import com.sb.fittingKeyboard.R

class SettingThemeFragment : Fragment() {

    var selectedKbdTheme = 0
    companion object {
        var selectedFontColor = 0xFF000000.toInt()
        var selectedFunctionFontColor = 0xFF000000.toInt()
        var selectedFontType = 0

        fun setSelected(int1: Int, int2: Int) {
            selectedFontColor = int1
            selectedFunctionFontColor = int2
        }
    }
    val companion = Companion
    lateinit var myThemeList: ArrayList<KeyboardThemes>
    lateinit var myAdapter : ThemeRecyclerAdapter
    lateinit var myDefaultFontColor : DefaultFontColorPicker
    lateinit var myFunctionFontColorPicker : FunctionFontColorPicker
    lateinit var myFontType : FontTypePicker
    lateinit var themeList: ArrayList<KeyboardThemes>
    private var customizeThemeEnabled = false
    private lateinit var myView: View


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        myView = inflater.inflate(R.layout.setting_main_tab4, container, false)

        themeList = arrayListOf(
            KeyboardThemes(R.drawable.thumbnail_01),
            KeyboardThemes(R.drawable.thumbnail_02),
            KeyboardThemes(R.drawable.thumbnail_03),
            KeyboardThemes(R.drawable.thumbnail_04),
            KeyboardThemes(R.drawable.thumbnail_05),
            KeyboardThemes(R.drawable.thumbnail_06),
            KeyboardThemes(R.drawable.thumbnail_07),
            KeyboardThemes(R.drawable.thumbnail_08),
            KeyboardThemes(R.drawable.thumbnail_09),
            KeyboardThemes(R.drawable.thumbnail_10),
            KeyboardThemes(R.drawable.thumbnail_11),
            KeyboardThemes(R.drawable.thumbnail_12),
            KeyboardThemes(R.drawable.thumbnail_13),
            KeyboardThemes(R.drawable.thumbnail_14),
            KeyboardThemes(R.drawable.thumbnail_15),
            KeyboardThemes(R.drawable.thumbnail_16),
            KeyboardThemes(R.drawable.thumbnail_17),
            KeyboardThemes(R.drawable.thumbnail_18),
            KeyboardThemes(R.drawable.thumbnail_19)
        )
        myThemeList = themeList


        myAdapter = ThemeRecyclerAdapter(myThemeList)
        myDefaultFontColor = DefaultFontColorPicker()
        myFunctionFontColorPicker = FunctionFontColorPicker()
        myFontType = FontTypePicker()

        val myRecyclerView = myView.findViewById<RecyclerView>(R.id.rv_Theme)


        activity?.findViewById<TextInputEditText>(R.id.textInputEditText)?.setOnClickListener {
            saveData()
        }

        loadData()
        customizeThemeEnabled = myView.findViewById<SwitchMaterial>(R.id.applyCustomTheme).isChecked
        myAdapter.setPosition(selectedKbdTheme)
        DefaultFontColorPicker.setFontColour(selectedFontColor)
        FunctionFontColorPicker.setFontColour(selectedFunctionFontColor)
        myFontType.setFontType(selectedFontType)
        myView.findViewById<Button>(R.id.setting_default_font_color).setOnClickListener {
            myDefaultFontColor.show(fragmentManager!!, "colorPicker")
            saveData()
        }

        myView.findViewById<Button>(R.id.setting_function_font_color).setOnClickListener {
            myFunctionFontColorPicker.show(fragmentManager!!, "functionFontColorPicker")
            saveData()
        }

        myView.findViewById<Button>(R.id.setting_font_type).setOnClickListener {
            myFontType.show(fragmentManager!!, "fontTypePicker")
            saveData()
        }
        myRecyclerView.layoutManager = GridLayoutManager(activity, 3)
        myRecyclerView.setHasFixedSize(true)
        myRecyclerView.adapter = myAdapter
        myAdapter.notifyItemChanged(myAdapter.newPosition)
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
        selectedKbdTheme = myAdapter.newPosition
        selectedFontColor = DefaultFontColorPicker.fontColor
        selectedFunctionFontColor = FunctionFontColorPicker.fontColor
        selectedFontType = myFontType.checkedFontType
        prefSetting?.edit()?.putInt("KeyboardTheme", selectedKbdTheme)?.apply()
        prefSetting?.edit()?.putInt("KeyboardDefaultFontColor", selectedFontColor)?.apply()
        prefSetting?.edit()?.putInt("KeyboardFunctionFontColor", selectedFunctionFontColor)?.apply()
        prefSetting?.edit()?.putInt("KeyboardFontType", selectedFontType)?.apply()
        prefSetting?.edit()?.putBoolean("customTheme", customizeThemeEnabled)?.apply()
    }

    fun loadData() {
        val prefSetting = activity?.getSharedPreferences("keyboardSetting", Context.MODE_PRIVATE)
        if (prefSetting != null) selectedKbdTheme = prefSetting.getInt("KeyboardTheme", 0)
        if (prefSetting != null) selectedFontColor = prefSetting.getInt("KeyboardDefaultFontColor", 0xFF000000.toInt())
        if (prefSetting != null) selectedFunctionFontColor = prefSetting.getInt("KeyboardFunctionFontColor", 0xFF000000.toInt())
        if (prefSetting != null) selectedFontType = prefSetting.getInt("KeyboardFontType", 0)
        if (prefSetting != null) customizeThemeEnabled = prefSetting.getBoolean("customTheme", false)
    }
}

class ThemeRecyclerAdapter(private val themeList: ArrayList<KeyboardThemes>) : RecyclerView.Adapter<ThemeRecyclerAdapter.CustomViewHolder>() {
    var newPosition = 0
    var oldPosition = 0

    private val themeFragment = SettingThemeFragment()

    fun setPosition(position: Int) {
        this.newPosition = position
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_setting_theme_recyclerview, parent, false)
        return CustomViewHolder(view)
    }

    override fun getItemCount(): Int {
        return themeList.size
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val item = holder.itemView.findViewById<ImageView>(R.id.iv_ischecked)
        holder.themeImage.setImageResource(themeList[position].themeImage)

        if (newPosition == position) {
            item.setBackgroundResource(R.drawable.ic_ischecked)
            item.alpha = 0.8F
        } else {
            item.setBackgroundResource(0)
            item.alpha = 1.0F
        }

        holder.itemView.setOnClickListener {
            newPosition = position
            oldPosition = newPosition
            notifyDataSetChanged()
            /** themeFragment = Fragment() **/
            val prefSetting = it.context.getSharedPreferences("keyboardSetting", Context.MODE_PRIVATE)

            prefSetting.edit().putInt("KeyboardTheme", newPosition).apply()
            when ( newPosition ) {
                0 -> {
                    SettingThemeFragment.setSelected(0xFF000000.toInt(), 0xFF000000.toInt())
                    DefaultFontColorPicker.setFontColour(0xFF000000.toInt())
                    FunctionFontColorPicker.setFontColour(0xFF000000.toInt())
                    prefSetting.edit().putInt("KeyboardDefaultFontColor", 0xFF000000.toInt()).apply()
                    prefSetting.edit().putInt("KeyboardFunctionFontColor", 0xFF000000.toInt()).apply()
                }
                1 -> {
                    SettingThemeFragment.setSelected(0xFFFFFFFF.toInt(), 0xFFFFFFFF.toInt())
                    DefaultFontColorPicker.setFontColour(0xFFFFFFFF.toInt())
                    FunctionFontColorPicker.setFontColour(0xFFFFFFFF.toInt())
                    prefSetting.edit().putInt("KeyboardDefaultFontColor", 0xFFFFFFFF.toInt()).apply()
                    prefSetting.edit().putInt("KeyboardFunctionFontColor", 0xFFFFFFFF.toInt()).apply()
                }
                2 -> {
                    SettingThemeFragment.setSelected(0xFF343434.toInt(), 0xFFFFFFFF.toInt())
                    DefaultFontColorPicker.setFontColour(0xFF343434.toInt())
                    FunctionFontColorPicker.setFontColour(0xFFFFFFFF.toInt())
                    prefSetting.edit().putInt("KeyboardDefaultFontColor", 0xFF343434.toInt()).apply()
                    prefSetting.edit().putInt("KeyboardFunctionFontColor", 0xFFFFFFFF.toInt()).apply()
                }
                3 -> {
                    SettingThemeFragment.setSelected(0xFF000000.toInt(), 0xFFFF0000.toInt())
                    DefaultFontColorPicker.setFontColour(0xFF000000.toInt())
                    FunctionFontColorPicker.setFontColour(0xFFFF0000.toInt())
                    prefSetting.edit().putInt("KeyboardDefaultFontColor", 0xFF000000.toInt()).apply()
                    prefSetting.edit().putInt("KeyboardFunctionFontColor", 0xFFFF0000.toInt()).apply()
                }
                4 -> {
                    SettingThemeFragment.setSelected(0xFF000000.toInt(), 0xFFFF0000.toInt())
                    DefaultFontColorPicker.setFontColour(0xFF000000.toInt())
                    FunctionFontColorPicker.setFontColour(0xFFFF0000.toInt())
                    prefSetting.edit().putInt("KeyboardDefaultFontColor", 0xFF000000.toInt()).apply()
                    prefSetting.edit().putInt("KeyboardFunctionFontColor", 0xFFFF0000.toInt()).apply()
                }
                5 -> {
                    SettingThemeFragment.setSelected(0xFF000000.toInt(), 0xFFFFFFFF.toInt())
                    DefaultFontColorPicker.setFontColour(0xFF000000.toInt())
                    FunctionFontColorPicker.setFontColour(0xFFFFFFFF.toInt())
                    prefSetting.edit().putInt("KeyboardDefaultFontColor", 0xFF000000.toInt()).apply()
                    prefSetting.edit().putInt("KeyboardFunctionFontColor", 0xFFFFFFFF.toInt()).apply()
                }
                6 -> {
                    SettingThemeFragment.setSelected(0xFF000000.toInt(), 0xFF0000FF.toInt())
                    DefaultFontColorPicker.setFontColour(0xFF000000.toInt())
                    FunctionFontColorPicker.setFontColour(0xFF0000FF.toInt())
                    prefSetting.edit().putInt("KeyboardDefaultFontColor", 0xFF000000.toInt()).apply()
                    prefSetting.edit().putInt("KeyboardFunctionFontColor", 0xFF0000FF.toInt()).apply()
                }
                7, 8, 9 -> {
                    SettingThemeFragment.setSelected(0xFFFFFFFF.toInt(), 0xFFFFFFFF.toInt())
                    DefaultFontColorPicker.setFontColour(0xFFFFFFFF.toInt())
                    FunctionFontColorPicker.setFontColour(0xFFFFFFFF.toInt())
                    prefSetting.edit().putInt("KeyboardDefaultFontColor", 0xFFFFFFFF.toInt()).apply()
                    prefSetting.edit().putInt("KeyboardFunctionFontColor", 0xFFFFFFFF.toInt()).apply()
                }
                10, 11 -> {
                    SettingThemeFragment.setSelected(0xFF000000.toInt(), 0xFF000000.toInt())
                    DefaultFontColorPicker.setFontColour(0xFF000000.toInt())
                    FunctionFontColorPicker.setFontColour(0xFF000000.toInt())
                    prefSetting.edit().putInt("KeyboardDefaultFontColor", 0xFF000000.toInt()).apply()
                    prefSetting.edit().putInt("KeyboardFunctionFontColor", 0xFF000000.toInt()).apply()
                }
                12 -> {
                    SettingThemeFragment.setSelected(0xFF000000.toInt(), 0xFFFFFFFF.toInt())
                    DefaultFontColorPicker.setFontColour(0xFF000000.toInt())
                    FunctionFontColorPicker.setFontColour(0xFFFFFFFF.toInt())
                    prefSetting.edit().putInt("KeyboardDefaultFontColor", 0xFF000000.toInt()).apply()
                    prefSetting.edit().putInt("KeyboardFunctionFontColor", 0xFFFFFFFF.toInt()).apply()
                }
                13 -> {
                    SettingThemeFragment.setSelected(0xFF0000FF.toInt(), 0xFF000000.toInt())
                    DefaultFontColorPicker.setFontColour(0xFF0000FF.toInt())
                    FunctionFontColorPicker.setFontColour(0xFF000000.toInt())
                    prefSetting.edit().putInt("KeyboardDefaultFontColor", 0xFF0000FF.toInt()).apply()
                    prefSetting.edit().putInt("KeyboardFunctionFontColor", 0xFF000000.toInt()).apply()
                }
                14 -> {
                    SettingThemeFragment.setSelected(0xFF7F00FF.toInt(), 0xFF000000.toInt())
                    DefaultFontColorPicker.setFontColour(0xFF7F00FF.toInt())
                    FunctionFontColorPicker.setFontColour(0xFF000000.toInt())
                    prefSetting.edit().putInt("KeyboardDefaultFontColor", 0xFF7F00FF.toInt()).apply()
                    prefSetting.edit().putInt("KeyboardFunctionFontColor", 0xFF000000.toInt()).apply()
                }
                15 -> {
                    SettingThemeFragment.setSelected(0xFFFFFFFF.toInt(), 0xFFFFFFFF.toInt())
                    DefaultFontColorPicker.setFontColour(0xFFFFFFFF.toInt())
                    FunctionFontColorPicker.setFontColour(0xFFFFFFFF.toInt())
                    prefSetting.edit().putInt("KeyboardDefaultFontColor", 0xFFFFFFFF.toInt()).apply()
                    prefSetting.edit().putInt("KeyboardFunctionFontColor", 0xFFFFFFFF.toInt()).apply()
                }
                16 -> {
                    SettingThemeFragment.setSelected(0xFF000000.toInt(), 0xFF000000.toInt())
                    DefaultFontColorPicker.setFontColour(0xFF000000.toInt())
                    FunctionFontColorPicker.setFontColour(0xFF000000.toInt())
                    prefSetting.edit().putInt("KeyboardDefaultFontColor", 0xFF000000.toInt()).apply()
                    prefSetting.edit().putInt("KeyboardFunctionFontColor", 0xFF000000.toInt()).apply()
                }
                17 -> {
                    SettingThemeFragment.setSelected(0xFFFFFFFF.toInt(), 0xFFFFFFFF.toInt())
                    DefaultFontColorPicker.setFontColour(0xFFFFFFFF.toInt())
                    FunctionFontColorPicker.setFontColour(0xFFFFFFFF.toInt())
                    prefSetting.edit().putInt("KeyboardDefaultFontColor", 0xFFFFFFFF.toInt()).apply()
                    prefSetting.edit().putInt("KeyboardFunctionFontColor", 0xFFFFFFFF.toInt()).apply()
                }
                18 -> {
                    SettingThemeFragment.setSelected(0xFF000000.toInt(), 0xFF000000.toInt())
                    DefaultFontColorPicker.setFontColour(0xFF000000.toInt())
                    FunctionFontColorPicker.setFontColour(0xFF000000.toInt())
                    prefSetting.edit().putInt("KeyboardDefaultFontColor", 0xFF000000.toInt()).apply()
                    prefSetting.edit().putInt("KeyboardFunctionFontColor", 0xFF000000.toInt()).apply()
                }
                else -> {
                }
            }
            prefSetting.edit().putInt("KeyboardFontType", SettingThemeFragment.selectedFontType).apply()
        }
    }

    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val themeImage: ImageView = itemView.findViewById(R.id.iv_theme)
    }
}
