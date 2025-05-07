package com.example.drivercabinet.common.api

import com.example.drivercabinet.data.model.DriverResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface DriverApi {

    @PATCH("drivers/{driverId}/status")
    suspend fun updateDriverStatus(
        @Path("driverId") driverId: Long,
        @Body newStatus: String
    ): DriverResponse

    @POST("/drivers/orders/{orderId}/complete")
    suspend fun completeOrder(
        @Path("orderId") orderId: Long
    ): DriverResponse

    @POST("drivers/{driverId}/orders/{orderId}/assign")
    suspend fun assignOrder(
        @Path("driverId") driverId: Long,
        @Path("orderId") orderId: Long
    ): DriverResponse

    @GET("api/v1/drivers/{driverId}")
    suspend fun getDriverById(
        @Path("driverId") driverId: Long
    ): DriverResponse
}
