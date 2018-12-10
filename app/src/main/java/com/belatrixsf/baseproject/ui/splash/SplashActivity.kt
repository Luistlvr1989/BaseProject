package com.belatrixsf.baseproject.ui.splash

import com.belatrixsf.baseproject.ui.main.MainActivity

class SplashActivity : SplashContract.View() {
    override fun goToMain() {
        val intent = MainActivity.newInstance()
        startActivity(intent)
        finish()
    }

    override fun goToLogin() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun createPresenter(): SplashContract.Presenter {
        return SplashPresenter()
    }
}