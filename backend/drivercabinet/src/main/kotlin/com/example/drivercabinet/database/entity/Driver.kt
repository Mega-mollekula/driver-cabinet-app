package com.example.drivercabinet.database.entity

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonManagedReference
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime

@Entity
@Table(name = "driver")
class Driver(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    var name: String,

    @Column(nullable = false, unique = true)
    var phoneNumber: String,

    @Enumerated(EnumType.STRING)
    var status: DriverStatus = DriverStatus.OFFLINE,

    var rating: Double = 4.0,

    @OneToMany(mappedBy = "driver", cascade = [CascadeType.ALL], orphanRemoval = true)
    val orders: List<Order> = mutableListOf(),

    @ManyToOne
    @JsonBackReference
    var referrer: Driver? = null,

    @Column(nullable = false, unique = true)
    var email: String,

    @OneToMany(mappedBy = "referrer", cascade = [CascadeType.ALL], orphanRemoval = true)
    @JsonManagedReference
    var referrals: MutableList<Driver> = mutableListOf(),

    var points: Int = 0,

    @CreationTimestamp
    @Column(updatable = false)
    val createdAt: LocalDateTime? = null,
)

enum class DriverStatus {
    AVAILABLE, ON_ROUTE, OFFLINE, EMERGENCY
}
