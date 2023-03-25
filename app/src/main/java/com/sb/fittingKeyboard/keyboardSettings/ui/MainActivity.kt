package com.sb.fittingKeyboard.keyboardSettings.ui

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.InputMethodInfo
import android.view.inputmethod.InputMethodManager
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.res.ResourcesCompat
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.sb.fittingKeyboard.R
import com.sb.fittingKeyboard.com.sb.fittingKeyboard.keyboardSettings.ui.FragmentSettingThemes
import com.sb.fittingKeyboard.keyboardSettings.ui.DialogAdminKeyboard
import com.sb.fittingKeyboard.keyboardSettings.ui.FragmentSettingBasic
import com.sb.fittingKeyboard.keyboardSettings.ui.FragmentSettingBoilerPlateText
import com.sb.fittingKeyboard.keyboardSettings.ui.FragmentSettingDetails
import com.sb.fittingKeyboard.keyboardSettings.ui.adapter.FragmentViewPagerAdapter
import com.sb.fittingKeyboard.keyboardSettings.util.Utilities

class MainActivity : AppCompatActivity() {
    private val currentIMEList = mutableListOf<InputMethodInfo>()
    private var currentIMM: InputMethodManager? = null
    private val settingFragmentList = listOf(Pair("기본 설정", FragmentSettingBasic()), Pair("세부 설정", FragmentSettingDetails()), Pair("상용구 설정", FragmentSettingBoilerPlateText()), Pair("테마 설정", FragmentSettingThemes()))

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        currentIMM = applicationContext.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        setContentView(R.layout.activity_main)
        findViewById<Toolbar>(R.id.toolbar_main).run {
            title = "키보드 설정"
            setTitleTextColor(ResourcesCompat.getColor(resources, R.color.white, null))
        }
        setSupportActionBar(findViewById(R.id.toolbar_main))

        val mainViewPager = findViewById<ViewPager2>(R.id.viewpager2_main)
        mainViewPager.adapter = FragmentViewPagerAdapter(this, settingFragmentList.map { it.second })
        mainViewPager.offscreenPageLimit = 3
        TabLayoutMediator(
            findViewById(R.id.tablayout_main),
            mainViewPager
        ) { tab, position ->
            tab.text = settingFragmentList[position].first
        }.attach()

        if (intent.hasExtra(INTENT_BOILERPLATE_TAB)) {
            mainViewPager.currentItem = 2
        }
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    override fun onResume() {
        super.onResume()

        // Inspect if fittingkeyboard is activated
        if (!isFittingKeyboardEnabled()) {
            currentIMM?.showInputMethodPicker()
            DialogAdminKeyboard().show(supportFragmentManager, "adminKeyboard")
        }

        // When user long-clicks boiler-plate text button in keyboard, intent guides user boiler-plate setting fragment.
        if (intent.hasExtra(INTENT_BOILERPLATE_TAB)) {
            findViewById<ViewPager2>(R.id.viewpager2_main).currentItem = 2
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        currentIMM = null
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
                if (isFittingKeyboardEnabled()) currentIMM?.showInputMethodPicker()
                else showIMESettingAlert("취소")
            }
            R.id.menu_help -> {
                Utilities.showHelpDialog(
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

    private fun showIMESetting() {
        startActivityForResult(Intent(Settings.ACTION_INPUT_METHOD_SETTINGS), 0)
    }

    private fun isFittingKeyboardEnabled(): Boolean {
        currentIMEList.clear()
        currentIMEList.addAll(currentIMM!!.enabledInputMethodList)
        return applicationInfo.packageName in currentIMEList.toString()
    }

    private fun showInformationPopup() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("어플리케이션 정보")
        builder.setMessage(resources.getString(R.string.applicationInfo))
        builder.setPositiveButton("확인") { _: DialogInterface?, _: Int -> }
        builder.show()
    }

    companion object {
        const val INTENT_BOILERPLATE_TAB = "boiler_plate"
    }
}
