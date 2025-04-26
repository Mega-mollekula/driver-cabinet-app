package com.example.drivercabinet.database.repository

import com.example.drivercabinet.database.entity.GasStation
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface GasStationDao : CrudRepository<GasStation, Long> {
}
