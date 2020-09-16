package com.teckathon.missingpeopleapp.ui.activities.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.teckathon.missingpeopleapp.data.repositories.UserRepository

@Suppress("UNCHECKED_CAST")
/**
 *
 * @property repository UserRepository
 * @constructor
 */
class AuthViewModelFactory(private val repository: UserRepository): ViewModelProvider.NewInstanceFactory() {

    /**
     *
     * @param modelClass Class<T>
     * @return T
     */
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AuthViewModel(repository) as T
    }

}