package com.belatrixsf.baseproject.extensions

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.annotation.ColorRes
import android.support.annotation.DrawableRes
import android.support.annotation.IdRes
import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.AppCompatEditText
import android.support.v7.widget.Toolbar
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

fun View.show() { visibility = View.VISIBLE }

fun View.hide(isGone: Boolean = true) { visibility = if (isGone) View.GONE else View.INVISIBLE }

fun Fragment.finish() {
    activity?.finish()
}

fun Fragment.onBackPressed() {
    activity?.onBackPressed()
}

fun FragmentTransaction.addOrShow(@IdRes containerViewId: Int, fragment: Fragment, tag: String) {
    if (fragment.isAdded) {
        show(fragment)
    } else {
        add(containerViewId, fragment, tag)
    }
}

fun FragmentTransaction.hideIfAdded(fragment: Fragment?) {
    if (fragment != null && fragment.isAdded) {
        hide(fragment)
    }
}

fun FragmentTransaction.addToBackStack(flag: Boolean, tag: String? = null) {
    if (flag) {
        addToBackStack(tag)
    }
}

fun Intent.getLongExtra(name: String): Long? {
    val value = getLongExtra(name, -1)
    return if (value == (-1).toLong()) null else value
}

fun Bundle.getLongOrNull(name: String): Long? {
    val value = getLong(name, -1)
    return if (value == (-1).toLong()) null else value
}

fun Fragment.getColor(@ColorRes color: Int): Int {
    return ContextCompat.getColor(context!!, color)
}