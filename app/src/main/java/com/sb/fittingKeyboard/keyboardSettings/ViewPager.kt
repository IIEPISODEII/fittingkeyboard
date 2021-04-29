package com.sb.fittingKeyboard.keyboardSettings

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPager(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    val detailFragment = SettingDetailFragment()
    val boilerPlateTextFragment = SettingBoilerPlateTextFragment()
    val themeFragment = SettingThemeFragment()
    val basicFragment = SettingBasicFragment()

    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        return when ( position ) {
            1 -> detailFragment
            2 -> boilerPlateTextFragment
            3 -> themeFragment
            else -> basicFragment
        }
    }
}