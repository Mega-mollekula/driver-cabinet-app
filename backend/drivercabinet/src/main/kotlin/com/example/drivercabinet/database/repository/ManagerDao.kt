package com.example.drivercabinet.database.repository

import com.example.drivercabinet.database.entity.Manager
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ManagerDao : CrudRepository<Manager, Long> {
}
