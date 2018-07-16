package com.belatrixsf.baseproject.managers.network.requests

import com.belatrixsf.baseproject.managers.network.ApiInterface
import com.belatrixsf.baseproject.models.api.UserDto
import io.reactivex.Observable

class AuthenticationRequest(private val email: String, private val password: String) : Request<UserDto> {
    override fun execute(endpoint: ApiInterface): Observable<UserDto> {
        return endpoint.login()
    }
}