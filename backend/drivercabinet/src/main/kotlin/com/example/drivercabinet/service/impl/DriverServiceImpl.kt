package com.example.drivercabinet.service.impl

import com.example.drivercabinet.database.entity.DriverStatus
import com.example.drivercabinet.database.repository.DriverDao
import com.example.drivercabinet.model.request.DriverRequest
import com.example.drivercabinet.model.response.DriverResponse
import com.example.drivercabinet.service.DriverService
import com.example.drivercabinet.util.DriverMapper
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class DriverServiceImpl(
    val driverDao: DriverDao,
    val mapper: DriverMapper,
): DriverService {
    override fun updateStatus(driverId: Long, newStatus: DriverStatus): DriverResponse {
        val driver = driverDao.findById(driverId).orElseThrow {
            IllegalArgumentException("Driver not found with id $driverId")
        }
        driver.status = newStatus
        if (newStatus == DriverStatus.EMERGENCY) {
            driver.rating -= 0.1
        }
        driverDao.save(driver)
        return mapper.entityToResponse(driver)
    }

    override fun getAll(): List<DriverResponse> =
        driverDao.findAll().map { mapper.entityToResponse(it) }

    override fun getById(driverId: Long): DriverResponse =
        mapper.entityToResponse(driverDao.findById(driverId).orElseThrow {
            IllegalArgumentException("Driver not found with id $driverId")
        })

    override fun updateRating(driverId: Long, rating: Double): DriverResponse {
        val driver = driverDao.findById(driverId).orElseThrow {
            IllegalArgumentException("Driver not found with id $driverId")
        }
        driver.rating = rating
        driverDao.save(driver)

        return mapper.entityToResponse(driver)
    }

    override fun delete(driverId: Long) {
        val driver = driverDao.findById(driverId).orElseThrow {
            IllegalArgumentException("Driver not found with id $driverId")
        }
        driverDao.delete(driver)
    }

    @Transactional
    override fun create(driverRequest: DriverRequest, referrerId: Long?): DriverResponse {
        val driver = mapper.requestToEntity(driverRequest)

        if (referrerId != null) {
            val referrer = driverDao.findById(referrerId).orElseThrow {
                IllegalArgumentException("Referrer not found with id $referrerId")
            }
            driver.referrer = referrer
            referrer.referrals.add(driver)
        }
        driverDao.save(driver)
        return mapper.entityToResponse(driver)
    }

}
