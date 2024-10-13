package com.ukrdroiddev.testtask.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ukrdroiddev.testtask.R
import com.ukrdroiddev.testtask.data.convertors.DateConverter
import com.ukrdroiddev.testtask.data.dao.BootEventDao
import com.ukrdroiddev.testtask.data.entities.BootEventEntity

@Database(entities = [BootEventEntity::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun bootEventDao(): BootEventDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    context.getString(R.string.app_database_name)
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

}