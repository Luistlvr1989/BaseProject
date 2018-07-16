package com.belatrixsf.baseproject.ui.splash

import android.arch.lifecycle.Lifecycle

/**
 * Created by ltalavera on 1/19/18.
 * Implements the presenter for the mvp splash
 */
internal class SplashPresenter : SplashContract.Presenter() {
    override fun attach(view: SplashContract.View, lifecycle: Lifecycle) {
        super.attach(view, lifecycle)
        getViewOrThrow().goToMain()
    }
}