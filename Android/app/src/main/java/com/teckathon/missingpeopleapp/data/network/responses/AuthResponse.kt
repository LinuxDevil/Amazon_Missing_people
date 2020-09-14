package com.teckathon.missingpeopleapp.data.network.responses

import com.teckathon.missingpeopleapp.data.database.entities.User

data class AuthResponse (
    val isSuccessful: Boolean?,
    val message: String?,
    val user: User?
)