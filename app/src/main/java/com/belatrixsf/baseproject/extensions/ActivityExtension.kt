package com.belatrixsf.baseproject.extensions

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity

@Throws(ActivityNotFoundException::class)
fun Activity.startDialer(phoneNumber: String?) {
    startActivity(Intent(Intent.ACTION_DIAL).apply {
        data = Uri.parse("tel:$phoneNumber")
    })
}

fun AppCompatActivity.handleBackPressedInNestedFragment(tag: String): Boolean {
    val fragment = supportFragmentManager.findFragmentByTag(tag)
    val childFragmentManager = fragment.childFragmentManager
    val backStackCount = childFragmentManager.backStackEntryCount

    return if (backStackCount > 0) {
        childFragmentManager.popBackStack()
        true
    } else false
}