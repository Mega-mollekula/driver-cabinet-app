package com.example.drivercabinet.database.repository

import com.example.drivercabinet.database.entity.Route
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository

interface RouteDao : CrudRepository<Route, Long> {
}
