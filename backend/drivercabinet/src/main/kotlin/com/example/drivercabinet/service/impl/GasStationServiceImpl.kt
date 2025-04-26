package com.example.drivercabinet.service.impl

import com.example.drivercabinet.database.repository.GasStationDao
import com.example.drivercabinet.model.request.GasStationRequest
import com.example.drivercabinet.model.response.GasStationResponse
import com.example.drivercabinet.service.GasStationService
import com.example.drivercabinet.util.GasStationMapper
import org.springframework.stereotype.Service

@Service
class GasStationServiceImpl (
    val dao: GasStationDao,
    val mapper: GasStationMapper,
) : GasStationService {

    override fun getAll(): List<GasStationResponse> =
        dao.findAll().map { mapper.entityToResponse(it) }

    override fun getById(gasStationId: Long): GasStationResponse =
        mapper.entityToResponse(dao.findById(gasStationId).orElseThrow {
            IllegalArgumentException("Driver not found with id $gasStationId")
        })

    override fun update(gasStationId: Long, gasStationRequest: GasStationRequest): GasStationResponse {
        val gasStation = dao.findById(gasStationId).orElseThrow {
            IllegalArgumentException("Driver not found with id $gasStationId")
        }
        gasStation.address = gasStationRequest.address
        gasStation.fuelTypes = gasStationRequest.fuelTypes
        dao.save(gasStation)

        return mapper.entityToResponse(gasStation)
    }

    override fun create(gasStationRequest: GasStationRequest): GasStationResponse {
        val gasStation = mapper.requestToEntity(gasStationRequest)
        dao.save(gasStation)
        return mapper.entityToResponse(gasStation)
    }

    override fun delete(gasStationId: Long) {
        val gasStation = dao.findById(gasStationId).orElseThrow {
            IllegalArgumentException("Driver not found with id $gasStationId")
        }
        dao.delete(gasStation)
    }


}






