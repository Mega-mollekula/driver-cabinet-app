package com.example.drivercabinet.database.repository

import com.example.drivercabinet.database.entity.Manager
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository

interface ManagerDao : CrudRepository<Manager, Long> {
}
