package com.belatrixsf.baseproject.managers

import com.belatrixsf.baseproject.helpers.PrefsHelper
import com.belatrixsf.baseproject.managers.network.requests.AuthenticationRequest
import com.belatrixsf.baseproject.models.database.User
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import java.io.Serializable

object AuthManager : Serializable {
    private val listeners: MutableList<AuthStateListener> = ArrayList()
    var user: User? = PrefsHelper.getUser()
        private set(value) {
            field = value
            PrefsHelper.setUser(field)
            for (listener in listeners) {
                listener.onAuthStateChanged(user)
            }
        }

    fun addAuthStateListener(listener: AuthStateListener) {
        listeners.add(listener)
        listener.onAuthStateChanged(user)
    }

    fun removeAuthStateListener(listener: AuthStateListener) {
        listeners.remove(listener)
    }

    fun login(email: String, password: String): Observable<User> {
        return ApiClient.execute(AuthenticationRequest(email, password))
                .map { result ->
                    user = User.fromDto(result)
                    user!!
                }
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun logout() {
        user = null
    }

    interface AuthStateListener {
        fun onAuthStateChanged(user: User?)
    }
}