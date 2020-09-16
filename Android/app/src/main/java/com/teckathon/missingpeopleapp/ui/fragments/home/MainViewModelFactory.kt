package com.teckathon.missingpeopleapp.ui.fragments.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.teckathon.missingpeopleapp.data.repositories.MissingRepository

@Suppress("UNCHECKED_CAST")
/**
 *
 * @property repository MissingRepository
 * @constructor
 */
class MainViewModelFactory(private val repository: MissingRepository) : ViewModelProvider.NewInstanceFactory() {
    /**
     *
     * @param modelClass Class<T>
     * @return T
     */
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}