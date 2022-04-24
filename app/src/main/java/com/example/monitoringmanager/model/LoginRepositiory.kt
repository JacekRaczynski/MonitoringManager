package com.example.monitoringmanager.model

import java.io.IOException

class LoginRepositiory() {
    data class User(
        val username: String,
        val password: String
    )
    var isLogged: Boolean? = null
       get() = user != null

    var user: User?
        private set

    init {
        user = null
    }
    fun login(username: String, password: String){
        user = User(username,password)

    }

    fun logout() {
        user = null
        //todo logout
    }
}