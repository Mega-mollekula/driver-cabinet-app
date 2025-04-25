package com.example.drivercabinet.database.repository

import com.example.drivercabinet.database.entity.GasStation
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository

interface GasStationDao : CrudRepository<GasStation, Long> {
}
