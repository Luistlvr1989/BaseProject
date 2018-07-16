package com.belatrixsf.baseproject.helpers

import com.belatrixsf.baseproject.models.database.User
import com.orhanobut.hawk.Hawk

object PrefsHelper {
    private const val PREF_USER = "PREF_USER"
    private const val PREF_PERMISSION_FIRST_TIME = "PREF_PERMISSION_FIRST_TIME"

    fun setUser(user: User?) {
        Hawk.put(PREF_USER, user)
    }

    fun getUser(): User? {
        return Hawk.get(PREF_USER)
    }

    /* Only for permissions (to know if it is the first time we request this permission) */

    fun setPermissionNotFirstTime(permission: String) {
        Hawk.put(PREF_PERMISSION_FIRST_TIME + permission, false)
    }

    fun isPermissionFirstTime(permission: String): Boolean {
        return Hawk.get(PREF_PERMISSION_FIRST_TIME + permission, true)
    }
}