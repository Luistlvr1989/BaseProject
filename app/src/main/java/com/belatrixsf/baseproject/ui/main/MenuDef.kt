package com.belatrixsf.baseproject.ui.main

import android.support.annotation.StringDef
import com.belatrixsf.baseproject.ui.main.MainActivity.Companion.TAG_ABOUT
import com.belatrixsf.baseproject.ui.main.MainActivity.Companion.TAG_HOME

@Retention(AnnotationRetention.SOURCE)
@StringDef(
        TAG_HOME,
        TAG_ABOUT
)
internal annotation class MenuDef