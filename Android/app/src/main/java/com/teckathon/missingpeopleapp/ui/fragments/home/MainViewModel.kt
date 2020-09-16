package com.teckathon.missingpeopleapp.ui.fragments.home

import androidx.lifecycle.ViewModel
import com.teckathon.missingpeopleapp.data.repositories.MissingRepository
import com.teckathon.missingpeopleapp.util.lazyCall

class MainViewModel(private val repository: MissingRepository) : ViewModel() {
    val missing by lazyCall { repository.getMissing() }
}