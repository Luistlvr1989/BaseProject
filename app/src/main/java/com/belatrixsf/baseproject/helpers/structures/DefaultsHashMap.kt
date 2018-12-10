package com.belatrixsf.baseproject.helpers.structures

class DefaultsHashMap<K, V> : HashMap<K, V>() {
    fun getOrDefault(key: K, default: V): V = if (containsKey(key)) get(key)!! else default
}
