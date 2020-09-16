package com.teckathon.missingpeopleapp.data.database.entities

import retrofit2.http.Field

data class MissingO (
    val name: String,
    val mother_name: String,
    val national_number: String,
    val gender: String,
    val birth_date: String,
    val birth_place: String,
    val photo: String,
    val missing_since: String,
    val last_known_location: String,
    val contact_number: String
)