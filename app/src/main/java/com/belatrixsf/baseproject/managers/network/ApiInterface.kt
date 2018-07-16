package com.belatrixsf.baseproject.managers.network

import com.belatrixsf.baseproject.models.api.UserDto
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiInterface {
    @GET("/v2/5b4c01353100001e04a7de73")
    fun login(): Observable<UserDto>
}