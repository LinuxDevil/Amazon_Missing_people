package com.teckathon.missingpeopleapp.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.teckathon.missingpeopleapp.data.database.entities.Missing
import com.teckathon.missingpeopleapp.data.network.responses.MissingResponse

@Dao
interface MissingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMissing(missing: Array<Missing>)

    @Query("SELECT * FROM Missing")
    fun getMissing(): LiveData<Array<Missing>>

}