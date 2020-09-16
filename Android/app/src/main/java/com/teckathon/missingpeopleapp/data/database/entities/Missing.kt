package com.teckathon.missingpeopleapp.data.database.entities

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NonNls

@Entity
data class Missing(
    @PrimaryKey(autoGenerate = false)
    var _id: String,
    val name_english: String? = null,
    var name_arabic: String? = null,
    val __v: String? = null,
    val birth_place: String? = null,
    val contact_number: String? = null,
    val last_known_location: String? = null
)