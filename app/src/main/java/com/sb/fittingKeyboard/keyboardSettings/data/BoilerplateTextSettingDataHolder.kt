package com.sb.fittingKeyboard.com.sb.fittingKeyboard.keyboardSettings.data

/**
 * TY
 *
 * @property iconDrawable 아이콘 드로어블 id값
 * @property descriptionText 세팅 설명
 * @property isActivated 세팅 사용 여부
 */
data class BoilerplateTextSettingDataHolder(
    val boilerplateText: String,
    var isFocused: Boolean
)