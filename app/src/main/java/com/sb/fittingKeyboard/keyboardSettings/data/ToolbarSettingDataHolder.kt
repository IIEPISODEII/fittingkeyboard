package com.sb.fittingKeyboard.com.sb.fittingKeyboard.keyboardSettings.data

/**
 * TODO
 *
 * @property iconDrawable 아이콘 드로어블 id값
 * @property descriptionText 세팅 설명
 * @property isActivated 세팅 사용 여부
 */
data class ToolbarSettingDataHolder(
    val settingId: String,
    val iconDrawable: Int,
    val descriptionText: String,
    val isActivated: Boolean
)