package com.belatrixsf.baseproject.extensions

import android.content.Intent
import android.os.Bundle
import com.google.gson.Gson

private val gson = Gson()

fun Intent.getLongExtra(name: String): Long? {
    val value = getLongExtra(name, -1)
    return if (value == (-1).toLong()) null else value
}

fun Bundle.getLongOrNull(name: String): Long? {
    val value = getLong(name, -1)
    return if (value == (-1).toLong()) null else value
}

fun Bundle.putObject(key: String, value: Any?) {
    value?.let {
        putString(key, gson.toJson(it))
    }
}

fun <T> Bundle.getObject(key: String, classOfT: Class<T>): T {
    return getString(key).let {
        gson.fromJson(it, classOfT)
    }
}