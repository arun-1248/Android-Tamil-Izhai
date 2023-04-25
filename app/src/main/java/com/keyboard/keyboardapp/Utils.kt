package com.keyboard.keyboardapp

import android.content.Context
import android.graphics.Typeface
import android.util.DisplayMetrics
import android.util.TypedValue
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

fun spToPx(sp: Float, context: Context): Int {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_SP,
        sp,
        context.resources.displayMetrics
    ).toInt()
}

fun dpToPx(dp: Float, context: Context): Int {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dp,
        context.resources.displayMetrics
    ).toInt()
}

fun convertPixelsToDp(px: Float, context: Context): Float {
    return px / (context.resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
}