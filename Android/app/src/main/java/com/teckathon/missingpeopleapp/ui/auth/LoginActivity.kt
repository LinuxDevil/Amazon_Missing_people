package com.teckathon.missingpeopleapp.ui.auth

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.teckathon.missingpeopleapp.R
import com.teckathon.missingpeopleapp.data.database.AppDatabase
import com.teckathon.missingpeopleapp.data.database.entities.User
import com.teckathon.missingpeopleapp.data.network.Api
import com.teckathon.missingpeopleapp.data.network.NetworkIntercepter
import com.teckathon.missingpeopleapp.data.repositories.UserRepository
import com.teckathon.missingpeopleapp.databinding.ActivityLoginBinding
import com.teckathon.missingpeopleapp.ui.home.HomeActivity
import com.teckathon.missingpeopleapp.util.toast

class LoginActivity : AppCompatActivity(), AuthListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val networkIntercepter = NetworkIntercepter(this)
        val api = Api(networkIntercepter)
        val database = AppDatabase(this)
        val repository = UserRepository(api, database)
        val factory = AuthViewModelFactory(repository)


        val binding: ActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        val viewModel = ViewModelProvider(this, factory).get(AuthViewModel::class.java)
        binding.viewmodel = viewModel
        viewModel.authListener = this

        viewModel.getLoggedInUser().observe(this, Observer { user ->
            if (user != null) {
                Intent(this, HomeActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }
        })
    }

    override fun onStarted() {
        toast("Started")
    }

    override fun onSuccess(user: User) {
        toast("$user.name is logged in!")
    }

    override fun onFailed(message: String) {
        toast(message)
        toast("Something went wrong")
    }
}