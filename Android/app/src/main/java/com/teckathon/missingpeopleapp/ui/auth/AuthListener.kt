package com.teckathon.missingpeopleapp.ui.auth

import androidx.lifecycle.LiveData
import com.teckathon.missingpeopleapp.data.database.entities.User

interface AuthListener {

    /**
     *
     */
    fun onStarted()

    /**
     *
     */
    fun onSuccess(user: User)

    /**
     *
     */
    fun onFailed(message: String)

}