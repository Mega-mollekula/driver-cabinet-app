package com.example.drivercabinet.database.entity

import com.fasterxml.jackson.annotation.JsonBackReference
import jakarta.persistence.*

@Entity
@Table(name = "gas_station")
class GasStation(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false, unique = true)
    var address: String,

    @Column(name = "fuel_type")
    @Enumerated(EnumType.STRING)
    var fuelTypes: Set<FuelType> = mutableSetOf(),

    @ManyToMany(mappedBy = "gasStations")
    @JsonBackReference
    val routes: List<Route> = mutableListOf()
)

enum class FuelType {
    PETROL_92,
    PETROL_95,
    PETROL_98,
    PETROL_100,
    DIESEL,
    ELECTRIC
}
