package com.example.drivercabinet.common.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.drivercabinet.domain.entity.DriverEntity

@Dao
interface DriverDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(driver: DriverEntity)

    @Query("SELECT * FROM drivers WHERE email = :email")
    suspend fun getDriverByEmail(email: String): DriverEntity?

    @Query("DELETE FROM drivers")
    suspend fun deleteAll()
}