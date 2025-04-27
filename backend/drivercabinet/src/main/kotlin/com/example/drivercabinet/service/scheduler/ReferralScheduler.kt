package com.example.drivercabinet.service.scheduler

import com.example.drivercabinet.service.ReferralService
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service


@Service
class ReferralScheduler(
    private val referralService: ReferralService
) {

    // Метод будет выполняться каждый день в полночь
    @Scheduled(cron = "0 0 0 * * ?")
    fun processReferrals() {
        referralService.processReferrals()  // Обработка рефералов
    }
}
