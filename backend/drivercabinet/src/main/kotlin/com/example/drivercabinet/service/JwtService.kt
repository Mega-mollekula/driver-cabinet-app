package com.example.drivercabinet.service

import com.example.drivercabinet.database.entity.Driver
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Service
import java.util.*

@Service
class JwtService {

    // В реальном проекте нужно хранить в конфигурации или .env
    private val jwtSecret = Keys.secretKeyFor(SignatureAlgorithm.HS256)

    fun generateToken(driver: Driver): String {
        return Jwts.builder()
            .setSubject(driver.email)
            .claim("role", driver.role.name)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // 24 часа
            .signWith(jwtSecret)
            .compact()
    }

    fun extractEmail(token: String): String? =
        Jwts.parserBuilder().setSigningKey(jwtSecret).build()
            .parseClaimsJws(token).body.subject

    fun isTokenValid(token: String, driver: Driver): Boolean {
        val email = extractEmail(token)
        return (email == driver.email) && !isTokenExpired(token)
    }

    private fun isTokenExpired(token: String): Boolean {
        val expiration = Jwts.parserBuilder().setSigningKey(jwtSecret).build()
            .parseClaimsJws(token).body.expiration
        return expiration.before(Date())
    }
}
