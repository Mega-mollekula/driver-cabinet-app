package com.example.drivercabinet.database.entity

import com.fasterxml.jackson.annotation.JsonManagedReference
import jakarta.persistence.*

@Entity
data class Route(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    var origin: String,

    @Column(nullable = false)
    var destination: String,

    @Column(nullable = false)
    @ManyToMany
    @JoinTable(
        name = "route_gas_station",
        joinColumns = [JoinColumn(name = "route_id")],
        inverseJoinColumns = [JoinColumn(name = "gas_station_id")]
    )
    @JsonManagedReference
    var gasStations: MutableList<GasStation> = mutableListOf()
)
