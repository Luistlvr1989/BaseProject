package com.belatrixsf.baseproject.managers.network.annotations

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class InnerKey(val value: String)
