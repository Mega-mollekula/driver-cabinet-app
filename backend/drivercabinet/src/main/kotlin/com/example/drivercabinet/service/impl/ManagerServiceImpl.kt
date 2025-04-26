package com.example.drivercabinet.service.impl

import com.example.drivercabinet.database.repository.ManagerDao
import com.example.drivercabinet.model.request.ManagerRequest
import com.example.drivercabinet.model.response.ManagerResponse
import com.example.drivercabinet.service.ManagerService
import com.example.drivercabinet.util.ManagerMapper
import org.springframework.stereotype.Service

@Service
class ManagerServiceImpl (
    val dao: ManagerDao,
    val mapper: ManagerMapper,
) : ManagerService {

    override fun getAll(): List<ManagerResponse> =
        dao.findAll().map { mapper.entityToResponse(it) }

    override fun getById(managerId: Long): ManagerResponse =
        mapper.entityToResponse(dao.findById(managerId).orElseThrow {
            IllegalArgumentException("Driver not found with id $managerId")
        })

    override fun updateProfile(managerId: Long, managerRequest: ManagerRequest): ManagerResponse {
        val manager = dao.findById(managerId).orElseThrow {
            IllegalArgumentException("Driver not found with id $managerId")
        }
        manager.phoneNumber = managerRequest.phoneNumber
        manager.email = managerRequest.email
        manager.name = managerRequest.name

        dao.save(manager)

        return mapper.entityToResponse(manager)
    }

        override fun create(managerRequest: ManagerRequest): ManagerResponse {
        val manager = mapper.requestToEntity(managerRequest)
        dao.save(manager)
        return mapper.entityToResponse(manager)
    }

    override fun delete(managerId: Long) {
        val manager = dao.findById(managerId).orElseThrow {
            IllegalArgumentException("Driver not found with id $managerId")
        }
        dao.delete(manager)
    }
}



