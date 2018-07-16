package com.belatrixsf.baseproject.ui.main

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.ViewModelProviders
import com.belatrixsf.baseproject.ui.main.MainActivity.Companion.TAG_ABOUT
import com.belatrixsf.baseproject.ui.main.MainActivity.Companion.TAG_HOME

internal class MainPresenter : MainContract.Presenter() {
    private lateinit var viewModel: MainViewModel

    override fun attach(view: MainContract.View, lifecycle: Lifecycle) {
        super.attach(view, lifecycle)
        viewModel = ViewModelProviders.of(view).get(MainViewModel::class.java)
    }

    override fun prepareHomeFragment() {
        setCurrentFragment(TAG_HOME)
    }

    override fun setCurrentFragment(@MenuDef currentTag: String) {
        viewModel.navItemIndex = when (currentTag) {
            TAG_ABOUT -> 1
            else -> 0
        }
        getViewOrThrow().loadCurrentFragment(currentTag, viewModel.navItemIndex)
    }
}