package com.teckathon.missingpeopleapp.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.teckathon.missingpeopleapp.data.database.AppDatabase
import com.teckathon.missingpeopleapp.data.database.entities.Missing
import com.teckathon.missingpeopleapp.data.database.entities.MissingO
import com.teckathon.missingpeopleapp.data.network.PeopleApi
import com.teckathon.missingpeopleapp.data.network.SafeApi
import com.teckathon.missingpeopleapp.data.network.responses.InformMissingResponse
import com.teckathon.missingpeopleapp.data.preferences.PreferenceProvider
import com.teckathon.missingpeopleapp.util.Coroutines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

private const val INTERVAL = 1

/**
 *
 * @property api PeopleApi
 * @property database AppDatabase
 * @property preferences PreferenceProvider
 * @property missing MutableLiveData<Array<Missing>>
 * @constructor
 */
class MissingRepository(
    private val api: PeopleApi,
    private val database: AppDatabase,
    private val preferences: PreferenceProvider
) : SafeApi() {

    private val missing = MutableLiveData<Array<Missing>>()

    init {
        missing.observeForever {
            saveMissing(it)
        }
    }

    /**
     *
     * @param name String
     * @param motherName String
     * @param nationalNumber String
     * @param gender String
     * @param birthDate String
     * @param birthPlace String
     * @param photoUrl String
     * @param missingSince String
     * @param lastKnownLocation String
     * @param contactNumber String
     * @return InformMissingResponse
     */
    suspend fun addMissing(
        name: String,
        motherName: String,
        nationalNumber: String,
        gender: String,
        birthDate: String,
        birthPlace: String,
        photoUrl: String,
        missingSince: String,
        lastKnownLocation: String,
        contactNumber: String
    ): InformMissingResponse {
        return apiRequest {
        api.addMissing(
            MissingO(
                name,
                motherName,
                nationalNumber,
                gender,
                birthDate,
                birthPlace,
                photoUrl,
                missingSince,
                lastKnownLocation,
                contactNumber
            )
        )
    }
    }

    /**
     *
     * @param name String
     * @param motherName String
     * @param nationalNumber String
     * @param gender String
     * @param birthDate String
     * @param birthPlace String
     * @param photoUrl String
     * @param missingSince String
     * @param lastKnownLocation String
     * @param contactNumber String
     * @return InformMissingResponse
     */
    suspend fun addFound(
        name: String,
        motherName: String,
        nationalNumber: String,
        gender: String,
        birthDate: String,
        birthPlace: String,
        photoUrl: String,
        missingSince: String,
        lastKnownLocation: String,
        contactNumber: String
    ): InformMissingResponse {
        val response = apiRequest {
            api.addFound(
                MissingO(
                    name,
                    motherName,
                    nationalNumber,
                    gender,
                    birthDate,
                    birthPlace,
                    photoUrl,
                    missingSince,
                    lastKnownLocation,
                    contactNumber
                )
            )
        }
        return response;
    }


    /**
     *
     * @return LiveData<Array<Missing>>
     */
    suspend fun getMissing(): LiveData<Array<Missing>> {
        return withContext(Dispatchers.IO) {
            fetchMissing()
            database.getMissingDao().getMissing()
        }
    }

    /**
     *
     */
    private suspend fun fetchMissing() {
        val timeStamp = preferences.getTimeStamp()

        if (timeStamp == null || !isFound(LocalDateTime.parse(timeStamp))) {
            val response = apiRequest { api.getMissing() }
            missing.postValue(response)
        }
    }

    /**
     * a function that will check if the data has been saved more than INTERVAL hour ago
     * @see INTERVAL
     * @see ChronoUnit
     * @see LocalDateTime
     */
    private fun isFound(timestamp: LocalDateTime): Boolean {
        return ChronoUnit.HOURS.between(timestamp, LocalDateTime.now()) > INTERVAL
    }

    /**
     *
     * @param missing Array<Missing>
     */
    private fun saveMissing(missing: Array<Missing>) {
        Coroutines.readWrite {
            preferences.storeTimeStamp(LocalDateTime.now().toString())
            database.getMissingDao().insertMissing(missing)
        }
    }

}