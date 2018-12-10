package com.belatrixsf.baseproject.extensions

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.support.annotation.ColorRes
import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.view.inputmethod.InputMethodManager

fun FragmentManager.clearBackStack() {
    for (i in 0 until backStackEntryCount) {
        popBackStack()
    }
}

fun Fragment.openUrl(url: String) {
    val intent = Intent(Intent.ACTION_VIEW).apply { data = Uri.parse(url) }
    startActivity(intent)
}

fun Fragment.requestPermission(message: Int, permission: String, requestCode: Int): Boolean {
    val permissionCheck = ContextCompat.checkSelfPermission(activity!!, permission)
    if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
        if (shouldShowRequestPermissionRationale(permission)) {
            val onClick = DialogInterface.OnClickListener { _, _ ->
                requestPermissions(
                        arrayOf(permission),
                        requestCode
                )
            }

            // TODO replace it with a FragmentDialog
            AlertDialog.Builder(activity!!)
                    .setCancelable(false)
                    .setMessage(message)
                    .setPositiveButton(android.R.string.ok, onClick)
                    .show()
        } else {
            // No explanation needed, we can request the permission.
            requestPermissions(arrayOf(permission), requestCode)
        }
        return false
    }
    return true
}

fun Fragment.hideSoftKeyboard() {
    activity?.currentFocus?.let { view ->
        val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}

fun Fragment.getColor(@ColorRes color: Int): Int {
    return ContextCompat.getColor(context!!, color)
}

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