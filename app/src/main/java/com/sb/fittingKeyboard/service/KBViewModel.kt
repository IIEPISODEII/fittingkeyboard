package com.sb.fittingKeyboard.service

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class KBViewModel<T>(val sp: SharedPreferences, private val key: String, private val dataType: T): ViewModel() {
    private val _height = MutableLiveData<Int>()
    val height: LiveData<Int> = _height
}