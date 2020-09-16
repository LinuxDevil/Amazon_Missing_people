package com.teckathon.missingpeopleapp.ui.activities.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.teckathon.missingpeopleapp.R
import com.teckathon.missingpeopleapp.data.database.entities.User
import com.teckathon.missingpeopleapp.databinding.ActivityLoginBinding
import com.teckathon.missingpeopleapp.ui.activities.home.HomeActivity
import com.teckathon.missingpeopleapp.util.ApiException
import com.teckathon.missingpeopleapp.util.NoInternetException
import com.teckathon.missingpeopleapp.util.snackbar
import com.teckathon.missingpeopleapp.util.toast
import kotlinx.coroutines.launch
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.instance

class LoginActivity : AppCompatActivity(), DIAware{

    override val di: DI by lazy { (applicationContext as DIAware).di }

    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: AuthViewModel

    private val factory: AuthViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        viewModel = ViewModelProvider(this, factory).get(AuthViewModel::class.java)

        viewModel.getLoggedInUser().observe(this, Observer { user ->
            if (user != null) {
                Intent(this, HomeActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }
        })

        binding.loginButton.setOnClickListener{
            loginUser()
        }

        binding.signUp.setOnClickListener {
            Intent(this, RegisterActivity::class.java).also {
                it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(it)
            }
        }
    }

    private fun loginUser() {
        val email = binding.email.text.toString()
        val password = binding.password.text.toString()

        lifecycleScope.launch {
            try {
                val response = viewModel.userLogin(email, password)
                if (response.user != null)
                    viewModel.storeUser(response.user)
                else
                    binding.root.snackbar(response.message!!)
            } catch (error: ApiException) {
                error.printStackTrace()
            } catch (error: NoInternetException) {
                error.printStackTrace()
            }

        }

    }

}