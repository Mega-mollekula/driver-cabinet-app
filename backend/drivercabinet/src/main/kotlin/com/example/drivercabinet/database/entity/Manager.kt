package com.example.drivercabinet.database.entity

import jakarta.persistence.*

@Entity
@Table(name = "manager")
data class Manager(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    var name: String,

    @Column(nullable = false, unique = true)
    var email: String,

    @Column(nullable = false, unique = true)
    var phoneNumber: String
)
