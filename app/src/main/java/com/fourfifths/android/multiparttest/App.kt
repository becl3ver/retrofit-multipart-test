package com.fourfifths.android.multiparttest

import android.app.Application

class App: Application() {
    companion object {
        lateinit var pref: PreferencesUtils
    }

    override fun onCreate() {
        super.onCreate()
        pref = PreferencesUtils(applicationContext)
    }
}