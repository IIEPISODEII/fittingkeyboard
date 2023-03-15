package com.sb.fittingKeyboard.keyboardSettings

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.text.InputType
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.InputMethodInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.textfield.TextInputEditText
import com.sb.fittingKeyboard.R
import com.sb.fittingKeyboard.keyboardSettings.util.UsualFunctions
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {
    private val tabTextList = arrayListOf("기본 설정", "세부 설정", "상용구", "테마 설정")
    private lateinit var currentIMEList: MutableList<InputMethodInfo>
    private var isMyIMEactivated by Delegates.notNull<Boolean>()
    private lateinit var currentIMM: InputMethodManager

    @SuppressLint("ClickableViewAccessibility")
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        currentIMM = applicationContext.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        setContentView(R.layout.activity_main)
        findViewById<Toolbar>(R.id.toolbar_main_setting_activity).run {
            title = "키보드 설정"
            setTitleTextColor(resources.getColor(R.color.white))
        }
        setSupportActionBar(findViewById(R.id.toolbar_main_setting_activity))
        findViewById<EditText>(R.id.edittext_main_setting_activity_test).imeOptions

        val mainViewPager = findViewById<ViewPager2>(R.id.viewpager2_main_setting_activity)
        mainViewPager.adapter = ViewPager(this)
        mainViewPager.offscreenPageLimit = 3
        TabLayoutMediator(
            findViewById(R.id.tablayout_main_setting_activity),
            mainViewPager
        ) { tab, position ->
            tab.text = tabTextList[position]
        }.attach()

        if (intent.hasExtra("Index")) {
            mainViewPager.currentItem = 2
        }

        findViewById<TextInputEditText>(R.id.edittext_main_setting_activity_test).inputType = InputType.TYPE_DATETIME_VARIATION_TIME
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    override fun onResume() {
        super.onResume()

        // Inspect if fittingkeyboard is activated
        isMyIMEenabled()
        if (!isMyIMEactivated) {
            currentIMM.showInputMethodPicker()
            AdminKeyboard().show(supportFragmentManager, "adminKeyboard")
        }

        // When user long-clicks boiler-plate text button in keyboard, intent guides user boiler-plate setting fragment.
        if (intent.hasExtra("Index")) {
            findViewById<ViewPager2>(R.id.viewpager2_main_setting_activity).currentItem = 2
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.mainact_appbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_information -> {
                showInformationPopup()
                return true
            }
            R.id.menu_showIMEpick -> {
                isMyIMEenabled()
                if (isMyIMEactivated) currentIMM.showInputMethodPicker()
                else showIMESettingAlert("취소")
            }
            R.id.menu_help -> {
                UsualFunctions().showHelpText(
                    resources.getString(R.string.keyboard_enable_help_text),
                    this
                )
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showIMESettingAlert(string: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("사용 중인 키보드 설정")
        builder.setMessage("키보드 관리화면에서 ${applicationInfo.loadLabel(applicationContext.packageManager)}를 활성화해주세요.")
        builder.setNegativeButton(string) { _: DialogInterface?, _: Int -> }
        builder.setPositiveButton("확인") { _: DialogInterface?, _: Int -> showIMESetting() }
        builder.show()
    }

    fun showIMESetting() {
        startActivityForResult(Intent(Settings.ACTION_INPUT_METHOD_SETTINGS), 0)
    }

    fun isMyIMEenabled() {
        currentIMEList = currentIMM.enabledInputMethodList
        isMyIMEactivated = applicationInfo.packageName in currentIMEList.toString()
    }

    private fun showInformationPopup() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("어플리케이션 정보")
        builder.setMessage(resources.getString(R.string.applicationInfo))
        builder.setPositiveButton("확인") { _: DialogInterface?, _: Int -> }
        builder.show()
    }
}
