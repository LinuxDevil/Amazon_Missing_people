package com.teckathon.missingpeopleapp.ui.fragments.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.teckathon.missingpeopleapp.data.repositories.UserRepository

@Suppress("UNCHECKED_CAST")
/**
 *
 * @property repository UserRepository
 * @constructor
 */
class ProfileViewModelFactory(private val repository: UserRepository): ViewModelProvider.NewInstanceFactory() {

    /**
     *
     * @param modelClass Class<T>
     * @return T
     */
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProfileViewModel(repository) as T
    }

}