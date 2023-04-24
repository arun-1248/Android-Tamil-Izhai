package com.keyboard.keyboardapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.inputmethod.InputMethodManager
import com.keyboard.keyboardapp.databinding.ActivityKeyboardSettingsBinding
import com.keyboard.keyboardapp.databinding.ActivityMainBinding

class KeyboardSettings : AppCompatActivity() {

    lateinit var binding: ActivityKeyboardSettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityKeyboardSettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val im = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager

        binding.addKeyboard.setOnClickListener {
            startActivity(Intent(Settings.ACTION_INPUT_METHOD_SETTINGS))
        }

        binding.selectKeyboard.setOnClickListener {
            val currentKeyboard = Settings.Secure.getString(
                contentResolver, Settings.Secure.DEFAULT_INPUT_METHOD
            )

            if (currentKeyboard.contains("MyInputMethodService")) {
                binding.selectKeyboard.isEnabled = false
            }
            Log.e(";;-;", "onCreate: current key $currentKeyboard", )
            im.showInputMethodPicker()
        }
    }
}