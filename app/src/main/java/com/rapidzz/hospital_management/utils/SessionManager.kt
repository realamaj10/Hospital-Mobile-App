package com.rapidzz.hospital_management.utils

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.rapidzz.hospital_management.models.UserModel

class SessionManager {
    var context: Context? = null
    var pref: SharedPreferences

    constructor(context: Context) {
        this.context = context
        pref = context.getSharedPreferences("hospital", Context.MODE_PRIVATE)
    }

    fun isLoggedIn(): Boolean {
        return pref.getBoolean("IS_LOGGED_IN", false)
    }

    fun setLoggedIn(isLoggedIn: Boolean) {
        with(pref.edit()) {
            putBoolean("IS_LOGGED_IN", isLoggedIn)
            apply()
        }
    }


    fun logout() {
        setLoggedIn(false)
        clearAppPreferences()
    }

    fun saveUser(u: UserModel) {
        setLoggedIn(true)

        with(pref.edit()) {
            putString("KEY_USER", Gson().toJson(u))
            apply()
        }
    }

    fun getUser(): UserModel? {
        val jsonUserData = pref.getString("KEY_USER", "")

        return if (jsonUserData!!.isNotEmpty()) {
            Gson().fromJson(jsonUserData, UserModel::class.java)
        } else
            null
    }

    private fun clearAppPreferences() {
        with(pref.edit()) {
            clear().apply()
            apply()
            commit()
        }
    }
}