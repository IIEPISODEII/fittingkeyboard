package com.sb.fittingKeyboard.KeyboardSettings

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.sb.fittingKeyboard.R

class SettingBoilerPlateTextFragment : Fragment() {

    private lateinit var myView: View
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        myView = inflater.inflate(R.layout.setting_main_tab3, container, false)

        loadData()

        val settingAutoTextArray = arrayOf(myView.findViewById(R.id.setting_autoText_1), myView.findViewById<EditText>(
            R.id.setting_autoText_2
        ), myView.findViewById(R.id.setting_autoText_3), myView.findViewById(R.id.setting_autoText_4), myView.findViewById(
            R.id.setting_autoText_5
        ), myView.findViewById(R.id.setting_autoText_6), myView.findViewById(R.id.setting_autoText_7), myView.findViewById(
            R.id.setting_autoText_8
        ), myView.findViewById(R.id.setting_autoText_9), myView.findViewById(R.id.setting_autoText_10), myView.findViewById(
            R.id.setting_autoText_11
        ), myView.findViewById(R.id.setting_autoText_12), myView.findViewById(R.id.setting_autoText_13), myView.findViewById(
            R.id.setting_autoText_14
        ), myView.findViewById(R.id.setting_autoText_15), myView.findViewById(R.id.setting_autoText_16))


        for ( i in settingAutoTextArray.indices ) {
            settingAutoTextArray[i].addTextChangedListener(object: TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    saveData()
                }

                override fun afterTextChanged(p0: Editable?) {
                }
            })
        }

        if ( activity?.intent?.hasExtra("Index") != null ) {
            settingAutoTextArray[activity?.intent!!.getIntExtra("Index", 1)].requestFocus()
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
    private fun saveData() {
        val prefSetting = activity?.getSharedPreferences("keyboardSetting", Context.MODE_PRIVATE)
        prefSetting?.edit()?.putString("AutoText0", myView.findViewById<EditText>(R.id.setting_autoText_1).text.toString())?.apply()
        prefSetting?.edit()?.putString("AutoText1", myView.findViewById<EditText>(R.id.setting_autoText_2).text.toString())?.apply()
        prefSetting?.edit()?.putString("AutoText2", myView.findViewById<EditText>(R.id.setting_autoText_3).text.toString())?.apply()
        prefSetting?.edit()?.putString("AutoText3", myView.findViewById<EditText>(R.id.setting_autoText_4).text.toString())?.apply()
        prefSetting?.edit()?.putString("AutoText4", myView.findViewById<EditText>(R.id.setting_autoText_5).text.toString())?.apply()
        prefSetting?.edit()?.putString("AutoText5", myView.findViewById<EditText>(R.id.setting_autoText_6).text.toString())?.apply()
        prefSetting?.edit()?.putString("AutoText6", myView.findViewById<EditText>(R.id.setting_autoText_7).text.toString())?.apply()
        prefSetting?.edit()?.putString("AutoText7", myView.findViewById<EditText>(R.id.setting_autoText_8).text.toString())?.apply()
        prefSetting?.edit()?.putString("AutoText8", myView.findViewById<EditText>(R.id.setting_autoText_9).text.toString())?.apply()
        prefSetting?.edit()?.putString("AutoText9", myView.findViewById<EditText>(R.id.setting_autoText_10).text.toString())?.apply()
        prefSetting?.edit()?.putString("AutoText10", myView.findViewById<EditText>(R.id.setting_autoText_11).text.toString())?.apply()
        prefSetting?.edit()?.putString("AutoText11", myView.findViewById<EditText>(R.id.setting_autoText_12).text.toString())?.apply()
        prefSetting?.edit()?.putString("AutoText12", myView.findViewById<EditText>(R.id.setting_autoText_13).text.toString())?.apply()
        prefSetting?.edit()?.putString("AutoText13", myView.findViewById<EditText>(R.id.setting_autoText_14).text.toString())?.apply()
        prefSetting?.edit()?.putString("AutoText14", myView.findViewById<EditText>(R.id.setting_autoText_15).text.toString())?.apply()
        prefSetting?.edit()?.putString("AutoText15", myView.findViewById<EditText>(R.id.setting_autoText_16).text.toString())?.apply()
    }

    fun loadData() {
        val prefSetting = activity?.getSharedPreferences("keyboardSetting", Context.MODE_PRIVATE)
        if (prefSetting != null) myView.findViewById<EditText>(R.id.setting_autoText_1).setText(prefSetting.getString("AutoText0", ""))
        if (prefSetting != null) myView.findViewById<EditText>(R.id.setting_autoText_2).setText(prefSetting.getString("AutoText1", ""))
        if (prefSetting != null) myView.findViewById<EditText>(R.id.setting_autoText_3).setText(prefSetting.getString("AutoText2", ""))
        if (prefSetting != null) myView.findViewById<EditText>(R.id.setting_autoText_4).setText(prefSetting.getString("AutoText3", ""))
        if (prefSetting != null) myView.findViewById<EditText>(R.id.setting_autoText_5).setText(prefSetting.getString("AutoText4", ""))
        if (prefSetting != null) myView.findViewById<EditText>(R.id.setting_autoText_6).setText(prefSetting.getString("AutoText5", ""))
        if (prefSetting != null) myView.findViewById<EditText>(R.id.setting_autoText_7).setText(prefSetting.getString("AutoText6", ""))
        if (prefSetting != null) myView.findViewById<EditText>(R.id.setting_autoText_8).setText(prefSetting.getString("AutoText7", ""))
        if (prefSetting != null) myView.findViewById<EditText>(R.id.setting_autoText_9).setText(prefSetting.getString("AutoText8", ""))
        if (prefSetting != null) myView.findViewById<EditText>(R.id.setting_autoText_10).setText(prefSetting.getString("AutoText9", ""))
        if (prefSetting != null) myView.findViewById<EditText>(R.id.setting_autoText_11).setText(prefSetting.getString("AutoText10", ""))
        if (prefSetting != null) myView.findViewById<EditText>(R.id.setting_autoText_12).setText(prefSetting.getString("AutoText11", ""))
        if (prefSetting != null) myView.findViewById<EditText>(R.id.setting_autoText_13).setText(prefSetting.getString("AutoText12", ""))
        if (prefSetting != null) myView.findViewById<EditText>(R.id.setting_autoText_14).setText(prefSetting.getString("AutoText13", ""))
        if (prefSetting != null) myView.findViewById<EditText>(R.id.setting_autoText_15).setText(prefSetting.getString("AutoText14", ""))
        if (prefSetting != null) myView.findViewById<EditText>(R.id.setting_autoText_16).setText(prefSetting.getString("AutoText15", ""))
    }
}