package com.example.third_app

import android.content.Context
import android.content.SharedPreferences
import com.example.third_app.PreferenceHelper.set
import com.example.third_app.PreferenceHelper.get

class SharedManager(context:Context) {
    private val prefs:SharedPreferences = PreferenceHelper.defaultPrefs(context)
    fun saveCurrentUser(user: LoginData){
        prefs["id"] = user.id
        prefs["isActive"] = user.isActive
        prefs["name"] = user.name
        prefs["password"] = user.password
    }
    fun getCurrentUser() : LoginData{
        return LoginData(
            id = prefs["id", ""],
            isActive = prefs["isActive", false],
            name = prefs["name", ""],
            password = prefs["password", ""],
        )
    }
}