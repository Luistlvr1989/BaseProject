package com.belatrixsf.baseproject.ui.base

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import com.belatrixsf.baseproject.extensions.plusAssign
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BasePresenter<Self: BasePresenter<Self, T>, T : BaseView<T, Self>> : Presenter<Self, T>, LifecycleObserver {
    private var view: T? = null
    private val disposable: CompositeDisposable by lazy {
        CompositeDisposable()
    }

    override fun attach(view: T, lifecycle: Lifecycle) {
        this.view = view
        lifecycle.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    override fun detach() {
        this.view = null
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    override fun subscribe() {}

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    override fun unsubscribe() {
        // We dispose onStop because if we need to display an error, we need the view
        disposable.clear()
    }

    override fun getView() = view

    override fun getViewOrThrow() = getView() ?: throw IllegalStateException("view not attached")

    override fun isViewAttached() = view != null

    fun Disposable.autoDispose() {
        disposable += this
    }
}