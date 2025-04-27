package com.example.drivercabinet.database.entity

import com.fasterxml.jackson.annotation.JsonBackReference
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime

@Entity
@Table(name = "driver_order")
data class Order(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val description: String? = null,

    @CreationTimestamp
    val startTime: LocalDateTime?,

    var endTime: LocalDateTime? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "driver_id")
    var driver: Driver? = null,

    @OneToOne(cascade = [CascadeType.ALL], orphanRemoval = true)
    @JoinColumn(name = "route_id", nullable = false)
    var route: Route,

    @Enumerated(EnumType.STRING)
    var status: OrderStatus = OrderStatus.PENDING
)

enum class OrderStatus {
    PENDING, IN_PROGRESS, COMPLETED  // Статусы заказа
}
