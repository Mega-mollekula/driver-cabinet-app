package com.example.drivercabinet.service

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class EmailService {
    private val logger = LoggerFactory.getLogger(EmailService::class.java)

    fun sendVerificationCode(email: String, code: String) {
        // В реальной ситуации здесь будет отправка через JavaMailSender
        logger.info("Письмо отправлено на $email с кодом подтверждения: $code")
    }
}
