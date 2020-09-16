package com.teckathon.missingpeopleapp.ui.fragments.missing

import androidx.lifecycle.ViewModel
import com.teckathon.missingpeopleapp.data.repositories.MissingRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 *
 * @property repository MissingRepository
 * @constructor
 */
class InformMissingViewModel(private val repository: MissingRepository) : ViewModel() {

    /**
     *
     * @param name String
     * @param motherName String?
     * @param nationalNumber String?
     * @param gender String?
     * @param birthDate String?
     * @param birthPlace String?
     * @param photoUrl String?
     * @param missingSince String?
     * @param lastKnownLocation String?
     * @param contactNumber String?
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
        contactNumber: String,
    ) = withContext(Dispatchers.IO) {
        return@withContext repository.addMissing(
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
    }

}