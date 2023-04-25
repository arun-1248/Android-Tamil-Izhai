package com.keyboard.keyboardapp

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.inputmethodservice.Keyboard
import android.inputmethodservice.KeyboardView
import android.util.AttributeSet
import android.util.Log


@Suppress("DEPRECATION")
class MyKeyboardView(context: Context, attrs: AttributeSet) : KeyboardView(context,attrs) {

    private val paint = Paint()

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        paint.textAlign = Paint.Align.CENTER
        paint.textSize = 54f
        paint.color = Color.RED

        val keys: List<Keyboard.Key> = keyboard.keys
        for (key in keys) {
            Log.e(";;-;", "onDraw: ${key.gap}", )
            if (key.label != null) canvas!!.drawText(
                key.label.toString(),
                (key.x + key.width / 2).toFloat(), (key.y + 2).toFloat(), paint
            )
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }
}