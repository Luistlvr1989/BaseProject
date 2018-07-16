package com.belatrixsf.baseproject.extensions

fun String.isJsonObject(): Boolean {
    return get(0) == '{'
}

fun String.toPlural(): String {
    val end = substring(length - 2)
    return this + if (end == "sh" || end == "ch" || end.last() == 's' || end.last() == 'x' || end.last() == 'z') {
        "es"
    } else {
        "s"
    }
}