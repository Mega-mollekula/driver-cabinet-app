package com.example.drivercabinet.security

import com.example.drivercabinet.database.repository.DriverDao
import com.example.drivercabinet.service.JwtService
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.User
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthFilter(
    private val jwtService: JwtService,
    private val driverDao: DriverDao
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authHeader = request.getHeader("Authorization")
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response)
            return
        }

        val token = authHeader.substring(7)
        val email = jwtService.extractEmail(token)

        if (email != null && SecurityContextHolder.getContext().authentication == null) {
            val driver = driverDao.findByEmail(email)
            if (driver != null && jwtService.isTokenValid(token, driver)) {
                // Создаем роль в формате "ROLE_DRIVER", "ROLE_MANAGER", и т.д.
                val authorities = listOf(SimpleGrantedAuthority("ROLE_${driver.role.name}"))

                val user = User(
                    driver.email,
                    driver.password,
                    authorities
                )

                val authToken = UsernamePasswordAuthenticationToken(user, null, authorities)
                authToken.details = WebAuthenticationDetailsSource().buildDetails(request)

                // Устанавливаем пользователя в контекст безопасности
                SecurityContextHolder.getContext().authentication = authToken
            }
        }

        filterChain.doFilter(request, response)
    }
}
