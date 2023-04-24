package com.keyboard.keyboardapp

import android.content.Context
import android.graphics.Typeface
import java.lang.reflect.Field


public fun setDefaultFont(context:Context, staticTypefaceName:String, fontName:String) {
    val regular = Typeface.createFromAsset(context.assets,fontName)
    replaceFont(staticTypefaceName,regular)
}

fun replaceFont(staticTypefaceName: String, newTypeface: Typeface?) {
    try {
        val staticField: Field = Typeface::class.java
            .getDeclaredField(staticTypefaceName)
        staticField.isAccessible = true
        staticField.set(null, newTypeface)
    } catch (e: NoSuchFieldException) {
        e.printStackTrace()
    } catch (e: IllegalAccessException) {
        e.printStackTrace()
    }
}
