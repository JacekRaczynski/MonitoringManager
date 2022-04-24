package com.example.monitoringmanager.viewmodel.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.monitoringmanager.model.LoginRepositiory
import androidx.lifecycle.MediatorLiveData

class LoginViewModel (private val loginRepository: LoginRepositiory) : ViewModel() {

 val usernameLiveData = MutableLiveData<String>()
 val passwordLiveData = MutableLiveData<String>()
 val tokenLiveData = MutableLiveData<String>()
    val token: LiveData<String>
    get() = tokenLiveData

 val isValidLiveData = MediatorLiveData<Boolean>().apply {
     this.value = false
     addSource(usernameLiveData){username ->
         val password =  passwordLiveData.value
         this.value = validateForm(username = username, password = password)
     }
     addSource(passwordLiveData){password ->
         val username =  usernameLiveData.value
         this.value = validateForm(username = username, password = password)
     }

 }
    private fun validateForm(username: String?, password: String?) :Boolean{
        val isValidUsername = username != null
                && username.isNotBlank()
        val isValidPassword = password != null
                && password.isNotBlank()
        return isValidUsername && isValidPassword
    }
    fun logIn(username: String?,password: String?){
        if(isValidLiveData.value == true){
            tokenLiveData.postValue(java.util.UUID.randomUUID().toString())
            Log.d("Login", "logIn() called with: username = $username, password = $password")
        }
    }
}