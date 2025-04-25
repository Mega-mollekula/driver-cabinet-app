package com.example.drivercabinet.database.entity

import jakarta.persistence.*

@Entity
@Table(name = "gas_station")
class GasStation(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false, unique = true)
    val name: String,

    @Column(nullable = false, unique = true)
    val address: String,

    val fuelTypes: String,

    @ManyToMany(mappedBy = "gasStations")
    val routes: List<Route> = mutableListOf()
)
