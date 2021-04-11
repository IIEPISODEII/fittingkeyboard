package com.sb.fittingKeyboard;

import android.content.SharedPreferences
import androidx.lifecycle.LiveData

abstract class SharedPreferenceLiveData2<T>(
    val sharedPrefs: SharedPreferences,
    private val key1: String,
    private val key2: String,
    private val key3: String,
    private val def1: Boolean,
    private val def2: Int,
    private val def3: Int) : LiveData<Array<T>>() {

    init {
        value = this.getValueFromPreferences(key1, key2, key3, def1, def2, def3)
    }

    private val preferenceChangeListener = SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
        if (key in arrayOf(key1, key2, key3)) {
            value = getValueFromPreferences(key1, key2, key3, def1, def2, def3)
        }
    }

    abstract fun getValueFromPreferences(key1: String, key2: String, key3: String, def1: Boolean, def2: Int, def3: Int): Array<T>

    override fun onActive() {
        super.onActive()
        value = getValueFromPreferences(key1, key2, key3, def1, def2, def3)
        sharedPrefs.registerOnSharedPreferenceChangeListener(preferenceChangeListener)
    }

    override fun onInactive() {
        sharedPrefs.unregisterOnSharedPreferenceChangeListener(preferenceChangeListener)
        super.onInactive()
    }
}

class SharedPreferenceAnySetLiveData(sharedPrefs: SharedPreferences, key1: String, key2: String, key3: String, def1: Boolean, def2: Int, def3: Int) :
    SharedPreferenceLiveData2<Any>(sharedPrefs, key1, key2, key3, def1, def2, def3) {
    override fun getValueFromPreferences(key1: String, key2: String, key3: String, def1: Boolean, def2: Int, def3: Int): Array<Any>
        = arrayOf(sharedPrefs.getBoolean(key1, def1), sharedPrefs.getInt(key2, def2), sharedPrefs.getInt(key3, def3))
}

fun SharedPreferences.viewLivedata(key1: String, key2: String, key3: String, def1: Boolean, def2: Int, def3: Int): SharedPreferenceLiveData2<Any> {
    return SharedPreferenceAnySetLiveData(this, key1, key2, key3, def1, def2, def3)
}

//변수를 추가하고 싶다면 key4, key5, ... def4, def5 ...만 추가하세요