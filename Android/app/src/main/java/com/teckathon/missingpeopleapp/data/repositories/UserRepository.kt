package com.teckathon.missingpeopleapp.data.repositories

import androidx.lifecycle.LiveData
import com.teckathon.missingpeopleapp.data.database.AppDatabase
import com.teckathon.missingpeopleapp.data.database.entities.User
import com.teckathon.missingpeopleapp.data.network.Api
import com.teckathon.missingpeopleapp.data.network.SafeApi
import com.teckathon.missingpeopleapp.data.network.responses.AuthResponse
import retrofit2.Response

/**
 *
 * @property api Api
 * @property database AppDatabase
 * @constructor
 */
class UserRepository (private val api: Api, private val database: AppDatabase): SafeApi() {


    /**
     *
     * @param email String
     * @param password String
     * @return AuthResponse
     */
    suspend fun userLogin(email: String, password: String): AuthResponse {
        return apiRequest { api.userLogin(email, password) }
    }

    /**
     *
     * @param name String
     * @param email String
     * @param password String
     * @return AuthResponse
     */
    suspend fun userSignUp(name: String, email: String, password: String): AuthResponse {
        return apiRequest { api.userSignUp(name, email, password) }
    }

    /**
     *
     * @param user User
     * @return Long
     */
    suspend fun saveUser(user: User) = database.getUserDao().insertUser(user)

    /**
     *
     * @return LiveData<User>
     */
    fun getUser() = database.getUserDao().getUser()

}