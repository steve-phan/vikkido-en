package com.vikiddo.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain

@Configuration
@EnableWebFluxSecurity
class SecurityConfig {
    @Bean
    fun securityWebFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        return http
            .csrf { it.disable() } // Disable CSRF for simplicity
            .authorizeExchange { exchanges ->
                exchanges
                    .pathMatchers("/**").permitAll() // Allow all requests
                    .anyExchange().permitAll()
            }
            .build()
    }
}