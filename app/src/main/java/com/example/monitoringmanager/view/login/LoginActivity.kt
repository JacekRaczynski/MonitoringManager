package com.example.monitoringmanager.view.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Observer
import com.example.monitoringmanager.databinding.ActivityLoginBinding
import com.example.monitoringmanager.model.LoginRepositiory
import com.example.monitoringmanager.view.dashboard.DashboardActivityIntent
import com.example.monitoringmanager.viewmodel.login.LoginViewModel


class LoginActivity : AppCompatActivity() {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var binding: ActivityLoginBinding
    private var repositiory: LoginRepositiory = LoginRepositiory()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loginViewModel = LoginViewModel(repositiory)

        subcribe()
        binding.btnSignIn.setOnClickListener {
            loginViewModel.logIn(
                binding.usernameText.text.toString(),
                binding.passwordText.text.toString()
            )
        }
        binding.usernameText.doOnTextChanged { text, _, _, _ ->
            loginViewModel.usernameLiveData.value = text?.toString()
        }
        binding.passwordText.doOnTextChanged { text, _, _, _ ->
            loginViewModel.passwordLiveData.value = text?.toString()
        }
        loginViewModel.isValidLiveData.observe(this) { isValid ->
            binding.btnSignIn.isEnabled = isValid
            binding.btnSignIn.isClickable = isValid

        }
    }

    private fun subcribe() {
        val processAuthorization: Observer<String> = Observer<String>{ token ->
            if(token != null)
            startActivity(DashboardActivityIntent())
        }//todo token authorization
        loginViewModel.token.observe(this,processAuthorization)
    }
}