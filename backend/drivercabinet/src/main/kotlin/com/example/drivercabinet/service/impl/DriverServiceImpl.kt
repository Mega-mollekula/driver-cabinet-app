package com.example.drivercabinet.service.impl

import com.example.drivercabinet.database.entity.DriverStatus
import com.example.drivercabinet.database.repository.DriverDao
import com.example.drivercabinet.model.request.DriverRequest
import com.example.drivercabinet.model.response.DriverResponse
import com.example.drivercabinet.service.DriverService
import com.example.drivercabinet.util.DriverMapper
import org.springframework.stereotype.Service

@Service
class DriverServiceImpl(
    val dao: DriverDao,
    val mapper: DriverMapper,
): DriverService {
    override fun updateStatus(driverId: Long, newStatus: DriverStatus): DriverResponse {
        val driver = dao.findById(driverId).orElseThrow {
            IllegalArgumentException("Driver not found with id $driverId")
        }

        driver.status = newStatus
        dao.save(driver)

        return mapper.entityToResponse(driver)
    }


    override fun getAll(): List<DriverResponse> =
        dao.findAll().map { mapper.entityToResponse(it) }



    override fun getById(driverId: Long): DriverResponse =
        mapper.entityToResponse(dao.findById(driverId).orElseThrow {
            IllegalArgumentException("Driver not found with id $driverId")
        })


    override fun updateProfile(driverId: Long, driverRequest: DriverRequest): DriverResponse {
        val driver = dao.findById(driverId).orElseThrow {
            IllegalArgumentException("Driver not found with id $driverId")
        }
        driver.phoneNumber = driverRequest.phoneNumber
        driver.email = driverRequest.email
        driver.status = driverRequest.status
        driver.rating = driverRequest.rating

        dao.save(driver)

        return mapper.entityToResponse(driver)
    }

    override fun create(driverRequest: DriverRequest): DriverResponse {
        val driver = mapper.requestToEntity(driverRequest)
        dao.save(driver)
        return mapper.entityToResponse(driver)
    }

    override fun delete(driverId: Long) {
        val driver = dao.findById(driverId).orElseThrow {
            IllegalArgumentException("Driver not found with id $driverId")
        }
        dao.delete(driver)
    }
}