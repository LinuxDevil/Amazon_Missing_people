package com.teckathon.missingpeopleapp.data.network.responses

import com.teckathon.missingpeopleapp.data.database.entities.Missing

/**
 *
 * @property _id String
 * @property name_english String?
 * @property name_arabic String?
 * @property __v String?
 * @property birth_place String?
 * @property contact_number String?
 * @property last_known_location String?
 * @constructor
 */
data class MissingResponse (
    var _id: String,
    val name_english: String? = null,
    var name_arabic: String? = null,
    val __v: String? = null,
    val birth_place: String? = null,
    val contact_number: String? = null,
    val last_known_location: String? = null
)