package com.teckathon.missingpeopleapp.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.teckathon.missingpeopleapp.data.database.entities.USER_ID
import com.teckathon.missingpeopleapp.data.database.entities.User

@Dao
interface UserDao  {

    /**
     * a function that will insert the user into the DB
     * @return the Database row ID
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User): Long

    /**
     * a function that will return the stored user
     * @return the stored user
     */
    @Query("Select * From User WHERE uid = $USER_ID")
    fun getUser(): LiveData<User>
}