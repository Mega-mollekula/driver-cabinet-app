package com.example.drivercabinet.database.entity

import jakarta.persistence.*

@Entity
@Table(name = "manager")
data class Manager(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    val name: String,

    @Column(nullable = false, unique = true)
    val email: String,

    @Column(nullable = false, unique = true)
    var phoneNumber: String
)
