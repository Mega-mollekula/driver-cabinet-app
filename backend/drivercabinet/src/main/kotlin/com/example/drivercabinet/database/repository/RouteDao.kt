package com.example.drivercabinet.database.repository

import com.example.drivercabinet.database.entity.Route
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface RouteDao : CrudRepository<Route, Long> {
}
