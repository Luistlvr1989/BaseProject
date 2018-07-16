package com.belatrixsf.baseproject.managers.network.requests

import com.belatrixsf.baseproject.managers.network.ApiInterface
import io.reactivex.Observable

/**
 * Created by luis on 18/01/18.
 * basic request interface
 */
interface Request<T> {
    /**
     * Executes the request
     * @param endpoint The api endpoint
     * @return The call required by the ApiClient
     */
    fun execute(endpoint: ApiInterface): Observable<T>
}