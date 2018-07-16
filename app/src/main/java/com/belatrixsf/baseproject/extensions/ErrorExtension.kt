package com.belatrixsf.baseproject.extensions

import com.belatrixsf.baseproject.BaseProjectApplication
import com.belatrixsf.baseproject.R
import com.google.gson.stream.MalformedJsonException
import org.xmlpull.v1.XmlPullParserException
import timber.log.Timber
import java.io.IOException

fun Throwable.fromApi(): Exception {
    Timber.e(this)
    val context = BaseProjectApplication.context
    return when (this) {
        is RuntimeException -> RuntimeException(context.getString(R.string.error_parsing))
        is MalformedJsonException -> MalformedJsonException(context.getString(R.string.error_parsing))
        is IOException -> IOException(context.getString(R.string.error_network))
        is NullPointerException -> NullPointerException(context.getString(R.string.error_internal_server))
        is XmlPullParserException -> XmlPullParserException(context.getString(R.string.error_parsing))
        else -> Exception(context.getString(R.string.error_general))
    }
}