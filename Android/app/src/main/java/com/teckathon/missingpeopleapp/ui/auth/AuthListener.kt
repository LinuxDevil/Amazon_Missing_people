package com.teckathon.missingpeopleapp.ui.auth

interface AuthListener {

    /**
     *
     */
    fun onStarted()

    /**
     *
     */
    fun onSuccess()

    /**
     *
     */
    fun onFailed(message: String)

}