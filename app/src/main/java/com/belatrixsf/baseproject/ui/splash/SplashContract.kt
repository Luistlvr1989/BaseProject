package com.belatrixsf.baseproject.ui.splash

import com.belatrixsf.baseproject.ui.base.BaseActivity
import com.belatrixsf.baseproject.ui.base.BasePresenter

/**
 * Created by ltalavera on 2/23/18.
 * The contract for the splash activity/presenter
 */
interface SplashContract {
    abstract class View : BaseActivity<View, Presenter>() {
        internal abstract fun goToMain()
        internal abstract fun goToLogin()
    }

    abstract class Presenter : BasePresenter<Presenter, View>()
}