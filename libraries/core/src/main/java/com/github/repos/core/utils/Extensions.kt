package com.github.repos.core.utils

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment

fun Context.color(@ColorRes resId: Int) = ResourcesCompat.getColor(resources, resId, theme)
fun Fragment.color(@ColorRes resId: Int) = context?.color(resId) ?: Color.TRANSPARENT
fun View.color(@ColorRes resId: Int) = context.color(resId)

fun Context.showToast(message: String, length: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(this, message, length).show()

fun Fragment.showToast(message: String, length: Int = Toast.LENGTH_SHORT) =
    context?.showToast(message, length)

fun Activity.hideSystemUI() {
    window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            or View.SYSTEM_UI_FLAG_FULLSCREEN)
}

fun String.getRawResId(res: Resources, packageName: String?): Int =
    res.getIdentifier(this, "raw", packageName)

/**
 * Closes the keyboard only if has been opened by this view and clears the focus from the view.
 */
fun View.closeKeyboard() {
    clearFocus()
    val inputMethodManager =
        context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
    inputMethodManager?.hideSoftInputFromWindow(windowToken, 0)
}

/**
 * Shows the keyboard on this view and requests focus.
 */
fun View.showKeyboard() {
    post {
        requestFocus()
        val inputMethodManager =
            context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        inputMethodManager?.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
    }
}
