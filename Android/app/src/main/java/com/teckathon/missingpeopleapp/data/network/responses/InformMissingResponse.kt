package com.teckathon.missingpeopleapp.data.network.responses

/**
 *
 * @property status String?
 * @property body String?
 * @property errorType String?
 * @property errorMessage String?
 * @property trace Array<String>?
 * @constructor
 */
data class InformMissingResponse (
    val status: String? = null,
    val body: String? = null,
    val errorType: String? = null,
    val errorMessage: String? = null,
    val trace: Array<String>? = null
)