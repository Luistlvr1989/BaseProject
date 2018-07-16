package com.belatrixsf.baseproject.managers.network

import com.belatrixsf.baseproject.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

object RetrofitFactory {
    private var interceptors: List<Interceptor>? = null
    private var client: OkHttpClient? = null
    private var retrofit: Retrofit? = null

    val endpoint: ApiInterface
        get() {
            val okHttpClient = getOkHttpClient(getInterceptors())
            val retrofit = getRetrofit(okHttpClient)
            return retrofit.create(ApiInterface::class.java)
        }

    private fun getInterceptors(): List<Interceptor> {
        if (interceptors == null) {
            val logging = HttpLoggingInterceptor().apply {
                level = if (BuildConfig.DEBUG) {
                    HttpLoggingInterceptor.Level.BODY
                } else {
                    HttpLoggingInterceptor.Level.NONE
                }
            }
            interceptors = ArrayList<Interceptor>(listOf(logging))
        }
        return interceptors!!
    }

    private fun getOkHttpClient(interceptors: List<Interceptor>): OkHttpClient {
        if (client == null) {
            val builder = OkHttpClient.Builder()
            for (interceptor in interceptors) {
                builder.addInterceptor(interceptor)
            }
            client = builder.build()
        }
        return client!!
    }

    private fun getRetrofit(client: OkHttpClient): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BuildConfig.BASE_URL)
                    .client(client)
                    .build()
        }
        return retrofit!!
    }
}
