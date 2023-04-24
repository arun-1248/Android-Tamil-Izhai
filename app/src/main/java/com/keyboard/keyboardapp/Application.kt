package com.keyboard.keyboardapp

import android.app.Application

class Application : Application() {
    override fun onCreate() {
        super.onCreate()
        setDefaultFont(this, "DEFAULT", "fonts/tamilnet.ttf")
        setDefaultFont(this, "MONOSPACE", "fonts/tamilnet.ttf")
    }
}