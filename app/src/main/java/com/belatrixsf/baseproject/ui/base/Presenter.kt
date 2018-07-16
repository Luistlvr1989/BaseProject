package com.belatrixsf.baseproject.ui.base

import android.arch.lifecycle.Lifecycle

/**
 * Created by luis on 25/11/17.
 * Every presenter must at least implement this interface
 */
interface Presenter<Self: Presenter<Self, T>, T: BaseView<T, Self>> {
    /**
     * Attach a new view to the presenter
     * @param view The view
     * @param lifecycle The lifecycle use to detach
     */
    fun attach(view: T, lifecycle: Lifecycle)

    /**
     * Removes the view reference from the presenter
     */
    fun detach()

    /**
     * Subscribes any listener or restores state
     */
    fun subscribe()

    /**
     * Unsubscribe listeners or save state
     */
    fun unsubscribe()

    /**
     * Gets the attached view
     * @return The current view
     */
    fun getView(): T?

    /**
     * Gets the attached view or throws an exception
     * @return The current view
     */
    fun getViewOrThrow(): T

    /**
     * If the view is attached to the presenter
     * @return true if the view is not null
     */
    fun isViewAttached(): Boolean
}