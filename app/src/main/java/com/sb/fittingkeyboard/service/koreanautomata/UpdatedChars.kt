package com.sb.fittingkeyboard.service.koreanautomata

/** "commited" carries what android's ime should "commit", and "composing" carries what android's ime should "compose", not "commit".**/
data class UpdatedChars(val commited: String?, val composing: String?)
