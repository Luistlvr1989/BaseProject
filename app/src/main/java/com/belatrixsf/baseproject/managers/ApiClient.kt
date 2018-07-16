package com.belatrixsf.baseproject.managers

import com.belatrixsf.baseproject.extensions.fromApi
import com.belatrixsf.baseproject.managers.network.ApiInterface
import com.belatrixsf.baseproject.managers.network.RetrofitFactory
import com.belatrixsf.baseproject.managers.network.requests.Request
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.io.Serializable

object ApiClient : Serializable {
    private val endpoint: ApiInterface = RetrofitFactory.endpoint

    internal fun <T> execute(request: Request<T>): Observable<T> {
        return request.execute(endpoint)
                .subscribeOn(Schedulers.io())
                .onErrorResumeNext{ throwable: Throwable ->
                    Timber.e(throwable)
                    Observable.error(throwable.fromApi())
                }
                .replay(1)
                .autoConnect(1)
    }
}