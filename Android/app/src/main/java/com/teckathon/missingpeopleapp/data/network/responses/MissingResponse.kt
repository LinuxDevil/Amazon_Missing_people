package com.teckathon.missingpeopleapp.data.network.responses

import com.teckathon.missingpeopleapp.data.database.entities.Missing

data class MissingResponse (
    var _id: String,
    val name_english: String? = null,
    var name_arabic: String? = null,
    val __v: String? = null,
    val birth_place: String? = null,
    val contact_number: String? = null,
    val last_known_location: String? = null
)