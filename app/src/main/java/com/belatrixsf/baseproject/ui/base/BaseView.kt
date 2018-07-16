package com.belatrixsf.baseproject.ui.base

import android.support.annotation.StringRes

/**
 * Created by luis on 2/23/18.
 * Base for Views MVP
 */
interface BaseView<Self: BaseView<Self, T>, T: Presenter<T, Self>> {
    fun createPresenter(): T
    fun showProgress(show: Boolean)
    fun showError(error: String?)
    fun showError(@StringRes error: Int)
}