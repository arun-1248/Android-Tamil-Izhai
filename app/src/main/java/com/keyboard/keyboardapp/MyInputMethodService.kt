package com.keyboard.keyboardapp

import android.inputmethodservice.InputMethodService
import android.inputmethodservice.Keyboard
import android.inputmethodservice.KeyboardView
import android.text.TextUtils
import android.util.Log
import android.view.View


class MyInputMethodService : InputMethodService(), KeyboardView.OnKeyboardActionListener {

    private var keyboard:Keyboard? = null
    private var keyboardView:KeyboardView? = null
    private var shiftToggleLetters = false

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
        switchKeyboardView(primaryCode)
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

    private fun switchKeyboardView(primaryCode: Int) {
        val res = convertPixelsToDp(spToPx(14f,this).toFloat(),this)
        Log.e(";;-;", "switchKeyboardView: $res", )
        when (primaryCode.toString()) {
            getString(R.string.letters_swift),getString(R.string.letters_keypad) -> {
                if (primaryCode.toString() == getString(R.string.letters_keypad)) {
                    shiftToggleLetters = false
                    keyboard = Keyboard(this, R.xml.tamil_keypad)
                    keyboardView?.keyboard = keyboard
                    keyboardView?.setOnKeyboardActionListener(this)
                    return
                }
                if (!shiftToggleLetters) {
                    shiftToggleLetters = true
                    keyboard = Keyboard(this, R.xml.shift_tamil_keypad)
                    keyboardView?.keyboard = keyboard
                    keyboardView?.setOnKeyboardActionListener(this)
                } else {
                    shiftToggleLetters = false
                    keyboard = Keyboard(this, R.xml.tamil_keypad)
                    keyboardView?.keyboard = keyboard
                    keyboardView?.setOnKeyboardActionListener(this)
                }
            }
            getString(R.string.number_keypad) -> {
                keyboard = Keyboard(this, R.xml.number_pad)
                keyboardView?.keyboard = keyboard
                keyboardView?.setOnKeyboardActionListener(this)
            }
            getString(R.string.symbols_keypad) -> {
                keyboard = Keyboard(this, R.xml.symbols_pad)
                keyboardView?.keyboard = keyboard
                keyboardView?.setOnKeyboardActionListener(this)
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