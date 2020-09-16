package com.teckathon.missingpeopleapp.ui.fragments.home

import androidx.lifecycle.ViewModel
import com.teckathon.missingpeopleapp.data.repositories.MissingRepository
import com.teckathon.missingpeopleapp.util.lazyCall

/**
 *
 * @property repository MissingRepository
 * @property missing Deferred<LiveData<Array<Missing>>>
 * @constructor
 */
class MainViewModel(private val repository: MissingRepository) : ViewModel() {

    /**
     * Calling the getMissing function by our custom lazy call
     * @see lazyCall
     */
    val missing by lazyCall { repository.getMissing() }
}