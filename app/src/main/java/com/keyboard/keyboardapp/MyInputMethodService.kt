package com.keyboard.keyboardapp

import android.R.id.keyboardView
import android.inputmethodservice.InputMethodService
import android.inputmethodservice.Keyboard
import android.inputmethodservice.KeyboardView
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.view.inputmethod.InputConnection


class MyInputMethodService : InputMethodService(), KeyboardView.OnKeyboardActionListener {

    private var keyboard:Keyboard? = null
    private var keyboardView:KeyboardView? = null
    private var shiftToggle = false
    private var numberToggle = false

    override fun onCreateInputView(): View? {
        keyboardView = layoutInflater.inflate(R.layout.keyboard_view, null) as KeyboardView
        keyboard = Keyboard(this, R.xml.tamil_keypad)
        keyboardView?.keyboard = keyboard
        keyboardView?.setOnKeyboardActionListener(this)
        return keyboardView
    }

    public override fun onKey(primaryCode: Int, keyCodes: IntArray?) {
        val ic = currentInputConnection ?: return
        Log.e(";;-;", "onKey: $primaryCode")
        switchKeyboardView(ic,primaryCode)
        when (primaryCode) {
            Keyboard.KEYCODE_DELETE -> {
                val selectedText = ic.getSelectedText(0)
                if (TextUtils.isEmpty(selectedText)) {
                    // no selection, so delete previous character
                    ic.deleteSurroundingText(1, 0)
                } else {
                    // delete the selection
                    ic.commitText("", 1)
                }
            }
            32,10 -> {
                val code = primaryCode.toChar()
                ic.commitText(code.toString(), 1)
            }
        }
    }

    private fun switchKeyboardView(ic: InputConnection, primaryCode: Int) {

        when (primaryCode) {
            swiftKeyCode -> {
                if (!shiftToggle) {
                    shiftToggle = true
                    keyboard = Keyboard(this, R.xml.shift_tamil_keypad)
                    keyboardView?.keyboard = keyboard
                    keyboardView?.setOnKeyboardActionListener(this)
                } else {
                    shiftToggle = false
                    keyboard = Keyboard(this, R.xml.tamil_keypad)
                    keyboardView?.keyboard = keyboard
                    keyboardView?.setOnKeyboardActionListener(this)
                }
            }
            numberKeyCode -> {
                if (!numberToggle) {
                    numberToggle = true
                    keyboard = Keyboard(this, R.xml.number_pad)
                    keyboardView?.keyboard = keyboard
                    keyboardView?.setOnKeyboardActionListener(this)
                } else {
                    numberToggle = false
                    keyboard = Keyboard(this, R.xml.tamil_keypad)
                    keyboardView?.keyboard = keyboard
                    keyboardView?.setOnKeyboardActionListener(this)
                }
            }
        }
    }

    override fun onPress(primaryCode: Int) {
    }

    override fun onRelease(primaryCode: Int) {
    }

    override fun onText(text: CharSequence?) {
        Log.e(";;-;", "onText: $text")
        val ic = currentInputConnection ?: return
        ic.commitText(text.toString(), 1)
    }

    override fun swipeLeft() {
    }

    override fun swipeRight() {
    }

    override fun swipeDown() {
    }

    override fun swipeUp() {
    }


}