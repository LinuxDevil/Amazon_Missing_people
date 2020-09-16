package com.teckathon.missingpeopleapp.ui.activities.auth

import android.content.Intent
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import com.teckathon.missingpeopleapp.data.database.entities.User
import com.teckathon.missingpeopleapp.data.repositories.UserRepository
import com.teckathon.missingpeopleapp.util.ApiException
import com.teckathon.missingpeopleapp.util.Coroutines
import com.teckathon.missingpeopleapp.util.NoInternetException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class AuthViewModel(private val repository: UserRepository) : ViewModel() {
    
    fun getLoggedInUser() = repository.getUser()

    suspend fun userLogin(email: String, password: String) = withContext(Dispatchers.IO) { repository.userLogin(email, password) }

    suspend fun userSignUp(email: String, password: String) = withContext(Dispatchers.IO) { repository.userSignUp("Ali", email, password) }

    suspend fun storeUser(user: User) =  repository.saveUser(user)

}