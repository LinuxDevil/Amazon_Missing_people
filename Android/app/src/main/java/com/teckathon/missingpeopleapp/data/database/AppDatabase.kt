package com.teckathon.missingpeopleapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.teckathon.missingpeopleapp.data.database.dao.MissingDao
import com.teckathon.missingpeopleapp.data.database.dao.UserDao
import com.teckathon.missingpeopleapp.data.database.entities.Missing
import com.teckathon.missingpeopleapp.data.database.entities.User

@Database(
    entities = [User::class, Missing::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    /**
     * a function that will return the UserDao
     * @see UserDao
     * @return UserDao
     */
    abstract fun getUserDao() : UserDao

    /**
     * a function that will return the MissingDao
     * @see MissingDao
     * @return MissingDao
     */
    abstract fun getMissingDao(): MissingDao

    companion object {

        @Volatile
        private var instance: AppDatabase? = null
        private var LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "MissingPeople.db").build()

    }

}