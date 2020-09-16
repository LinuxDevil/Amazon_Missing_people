package com.teckathon.missingpeopleapp.data.network.responses

import com.teckathon.missingpeopleapp.data.database.entities.User

/**
 *
 * @property isSuccessful Boolean?
 * @property message String?
 * @property user User?
 * @constructor
 */
data class AuthResponse (
    val isSuccessful: Boolean?,
    val message: String?,
    val user: User?
)