package com.teckathon.missingpeopleapp.ui.activities.auth

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