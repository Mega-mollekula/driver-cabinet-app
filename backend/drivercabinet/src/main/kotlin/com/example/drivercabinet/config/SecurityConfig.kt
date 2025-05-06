package com.example.drivercabinet.config

import com.example.drivercabinet.security.JwtAuthFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableMethodSecurity
class SecurityConfig(
    private val jwtAuthFilter: JwtAuthFilter
) {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .csrf { it.disable() }
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .authorizeHttpRequests {
                it
                    // Открытые маршруты
                    .requestMatchers("/auth/**", "/register").permitAll()

                    // Роуты, доступные только администраторам
                    .requestMatchers(HttpMethod.POST, "/admin/**").hasRole("ADMIN")

                    //Доступ для менеджеров и админов
                    .requestMatchers("/manager/**").hasAnyRole("MANAGER", "ADMIN")

                    // Доступ для всех водителей, менеджеров и админов
                    .requestMatchers("/drivers/**").hasAnyRole("DRIVER", "MANAGER", "ADMIN")

                    // Остальные маршруты требуют авторизации
                    .anyRequest().authenticated()
            }
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter::class.java)
            .build()
    }

    @Bean
    fun authenticationManager(configuration: AuthenticationConfiguration): AuthenticationManager =
        configuration.authenticationManager
}
