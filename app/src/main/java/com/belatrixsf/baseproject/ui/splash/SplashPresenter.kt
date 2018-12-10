package com.belatrixsf.baseproject.ui.splash

import android.arch.lifecycle.Lifecycle
import com.belatrixsf.baseproject.managers.AuthManager
import com.belatrixsf.baseproject.models.database.User

/**
 * Created by ltalavera on 1/19/18.
 * Implements the presenter for the mvp splash
 */
internal class SplashPresenter : SplashContract.Presenter() {
    private val authStateListener: AuthManager.AuthStateListener = object : AuthManager.AuthStateListener {
        override fun onAuthStateChanged(user: User?) {
            if (isViewAttached()) {
                getViewOrThrow().run {
                    if (user != null) {
                        goToMain()
                    } else {
                        goToLogin()
                    }
                    finish()
                }
            }
        }
    }

    override fun attach(view: SplashContract.View, lifecycle: Lifecycle) {
        super.attach(view, lifecycle)
        getViewOrThrow().goToMain()
    }

    override fun subscribe() = AuthManager.addAuthStateListener(authStateListener)

    override fun unsubscribe() = AuthManager.removeAuthStateListener(authStateListener)
}