package com.belatrixsf.baseproject.ui.main

import com.belatrixsf.baseproject.ui.base.BaseActivity
import com.belatrixsf.baseproject.ui.base.BasePresenter

interface MainContract {
    abstract class View : BaseActivity<View, Presenter>() {
        internal abstract fun loadCurrentFragment(currentTag: String, navItemIndex: Int)
    }

    abstract class Presenter : BasePresenter<Presenter, View>() {
        internal abstract fun prepareHomeFragment()
        internal abstract fun setCurrentFragment(currentTag: String)
    }
}