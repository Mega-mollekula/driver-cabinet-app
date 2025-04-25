package com.example.drivercabinet.database.entity

import jakarta.persistence.*

@Entity
@Table(name = "driver")
class Driver(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    val name: String,

    @Column(nullable = false, unique = true)
    var phoneNumber: String,

    @Enumerated(EnumType.STRING)
    var status: DriverStatus = DriverStatus.OFFLINE,

    var rating: Double = 5.0,

    @OneToMany(mappedBy = "driver", cascade = [CascadeType.ALL], orphanRemoval = true)
    val orders: List<Order> = mutableListOf(),

    @ManyToOne
    val referrer: Driver? = null,

    @Column(nullable = false, unique = true)
    var email: String,

    @OneToMany(mappedBy = "referrer")
    val referrals: List<Driver> = emptyList()
)

enum class DriverStatus {
    AVAILABLE, ON_ROUTE, OFFLINE, EMERGENCY
}
