package com.teckathon.missingpeopleapp.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel
import com.teckathon.missingpeopleapp.data.repositories.UserRepository
import com.teckathon.missingpeopleapp.util.ApiException
import com.teckathon.missingpeopleapp.util.Coroutines
import com.teckathon.missingpeopleapp.util.NoInternetException

class AuthViewModel(private val repository: UserRepository) : ViewModel() {

    var email: String? = null
    var password: String? = null
    var authListener: AuthListener? = null

    fun getLoggedInUser() = repository.getUser()

    fun onLoginClicked(view: View) {
        authListener?.onStarted()
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            authListener?.onFailed("Please enter valid data")
            return
        }

        Coroutines.main {
            try {
                val authResponse = repository.userLogin(email!!, password!!)
                authResponse.user?.let {
                    authListener?.onSuccess(it)
                    repository.saveUser(it)
                    return@main
                }
                authListener?.onFailed(authResponse.message!!)
            } catch (error: ApiException) {
                authListener?.onFailed(error.message!!)
            } catch (error: NoInternetException) {
                authListener?.onFailed(error.message!!)
            }
        }

    }

}