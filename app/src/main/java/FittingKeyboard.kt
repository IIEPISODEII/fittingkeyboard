package com.sb.fittingkeyboard

import android.app.Application
import android.content.Context
import androidx.emoji.bundled.BundledEmojiCompatConfig
import androidx.emoji.text.EmojiCompat
import androidx.multidex.MultiDex

class FittingKeyboard : Application() {
    override fun onCreate() {
        super.onCreate()

        val config = BundledEmojiCompatConfig(this)
        config.setReplaceAll(true)
            .registerInitCallback(object : EmojiCompat.InitCallback() {
            })
        EmojiCompat.init(config)
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(base)
    }
}