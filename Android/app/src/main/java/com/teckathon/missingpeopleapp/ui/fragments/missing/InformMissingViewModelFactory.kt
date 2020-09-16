package com.teckathon.missingpeopleapp.ui.fragments.missing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.teckathon.missingpeopleapp.data.repositories.MissingRepository

@Suppress("UNCHECKED_CAST")
/**
 *
 * @property repository MissingRepository
 * @constructor
 */
class InformMissingViewModelFactory(private val repository: MissingRepository) : ViewModelProvider.NewInstanceFactory() {

    /**
     *
     * @param modelClass Class<T>
     * @return T
     */
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return InformMissingViewModel(repository) as T
    }
}