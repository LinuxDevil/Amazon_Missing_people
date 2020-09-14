package com.teckathon.missingpeopleapp.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel

class AuthViewModel: ViewModel() {

    var email: String? =  null
    var password: String? = null
    var authListener: AuthListener? = null

    fun onLoginClicked(view: View) {
        authListener?.onStarted()
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            authListener?.onFailed("Please enter valid data")
            return
        }
        authListener?.onSuccess()
    }

}