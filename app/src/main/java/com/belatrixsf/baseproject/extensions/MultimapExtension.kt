package com.belatrixsf.baseproject.extensions

import java.util.*

fun <K, V> HashMap<K, MutableList<V>>.addMultiValue(key: K, element: V) {
    if (!containsKey(key)) {
        put(key, ArrayList(Collections.singleton(element)))
    } else {
        get(key)!!.add(element)
    }
}