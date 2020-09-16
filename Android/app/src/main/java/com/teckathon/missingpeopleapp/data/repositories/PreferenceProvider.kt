package com.teckathon.missingpeopleapp.data.repositories

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

private const val TIME_KEY = "TIME_STAMP"

class PreferenceProvider(context: Context) {

    private val appContext = context.applicationContext
    private val preference: SharedPreferences get() = PreferenceManager.getDefaultSharedPreferences(appContext)

    /**
     * a function to save the time stamp into the sharedPreferences
     */
    fun storeTimeStamp(time: String) {
        preference.edit().putString(TIME_KEY, time).apply()
    }

    /**
     * a function to return the saved time stamp
     */
    fun getTimeStamp(): String? {
        return preference.getString(TIME_KEY, null)
    }

}