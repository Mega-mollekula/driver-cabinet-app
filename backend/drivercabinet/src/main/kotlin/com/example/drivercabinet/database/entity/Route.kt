package com.example.drivercabinet.database.entity

import jakarta.persistence.*

@Entity
data class Route(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    val origin: String,

    @Column(nullable = false)
    val destination: String,

    @ManyToMany
    @JoinTable(
        name = "route_gas_station",
        joinColumns = [JoinColumn(name = "route_id")],
        inverseJoinColumns = [JoinColumn(name = "gas_station_id")]
    )
    val gasStations: List<GasStation> = mutableListOf()
)
