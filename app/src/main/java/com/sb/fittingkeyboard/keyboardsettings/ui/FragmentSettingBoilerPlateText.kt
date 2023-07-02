package com.sb.fittingkeyboard.keyboardsettings.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.sb.fittingKeyboard.R
import com.sb.fittingkeyboard.Constants
import com.sb.fittingkeyboard.keyboardsettings.ui.adapter.BoilerplateTextSettingAdapter
import com.sb.fittingkeyboard.keyboardsettings.data.BoilerplateTextSettingDataHolder

class FragmentSettingBoilerPlateText : Fragment() {

    private lateinit var myView: View
    private val boilerplatesRecyclerview by lazy { myView.findViewById<RecyclerView>(R.id.boilerplate_setting_rv) }
    private val boilerplateDataHolderList = mutableListOf<BoilerplateTextSettingDataHolder>()
    private val prefSetting by lazy { requireContext().getSharedPreferences(Constants.KEYBOARD_SETTING, Context.MODE_PRIVATE) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        myView = inflater.inflate(R.layout.fragment_boilerplatesetting, container, false)
        loadData(activity?.intent?.getIntExtra("Index", -1) ?: -1)

        val textChangeListener = object: BoilerplateTextSettingAdapter.OnTextChangeListener {
            override fun onTextChange(boilerplateIndex: Int, boilerplateText: String) {
                saveData(boilerplateIndex, boilerplateText)
            }
        }
        val boilerplateTextSettingAdapter = BoilerplateTextSettingAdapter(boilerplateDataHolderList, textChangeListener)

        boilerplatesRecyclerview.apply {
            adapter = boilerplateTextSettingAdapter
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        }
        return myView
    }
    override fun onResume() {
        super.onResume()
        loadData(-1)
    }

    private fun saveData(boilerplateIndex: Int, boilerplateString: String) {
        prefSetting.edit().putString(Constants.KEYBOARD_BOILERPLATE_TEXTS_LIST[boilerplateIndex], boilerplateString).apply()
    }

    private fun loadData(focusedIndex: Int) {
        if (boilerplateDataHolderList.size >= Constants.KEYBOARD_BOILERPLATE_TEXTS_LIST.size) return
        Constants.KEYBOARD_BOILERPLATE_TEXTS_LIST.forEachIndexed { idx, key ->
            boilerplateDataHolderList.add(BoilerplateTextSettingDataHolder(prefSetting.getString(key, "")!!, idx==focusedIndex))
        }
    }
}