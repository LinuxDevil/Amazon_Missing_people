package com.teckathon.missingpeopleapp.ui.fragments.profile

import androidx.lifecycle.ViewModel
import com.teckathon.missingpeopleapp.data.repositories.UserRepository

class ProfileViewModel(private val repository: UserRepository) : ViewModel() {
    val user = repository.getUser()
}