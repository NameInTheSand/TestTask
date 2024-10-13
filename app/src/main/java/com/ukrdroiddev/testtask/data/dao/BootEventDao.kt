package com.ukrdroiddev.testtask.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ukrdroiddev.testtask.data.entities.BootEventEntity
import java.util.Date

@Dao
interface BootEventDao {

    @Insert
    suspend fun insertBootEvent(bootEvent: BootEventEntity)

    @Query("SELECT * FROM boot_events ORDER BY bootTime DESC")
    suspend fun getAllBootEvents(): List<BootEventEntity>

    @Query("SELECT COUNT(*) FROM boot_events")
    suspend fun getBootEventCount(): Int

    @Query("SELECT bootTime FROM boot_events ORDER BY bootTime DESC LIMIT 1")
    suspend fun getLastBootTime(): Date?

    @Query("SELECT bootTime FROM boot_events ORDER BY bootTime DESC LIMIT 2")
    suspend fun getLastTwoBootTimes(): List<Date>


    @Query("SELECT * FROM boot_events ORDER BY bootTime DESC LIMIT 2")
    suspend fun getLastBootEvents(): List<BootEventEntity>

}