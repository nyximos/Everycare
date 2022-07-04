package com.everycare.dto

import java.time.LocalDate

data class SitterListDTO(
    var id: Long,
    var name: String,
    var age: Int,
    var cctvAgreement: Int,
    var isVaccinated: Int,
    var desiredDayWeek: String,
    var activityTime: String,
    var hourlyWage: String,
    var monthlyWage: String,
    var hopefulRegion: String,
    var preferredType: String,
    var birth: String,
    var gender: String,
    var storeFileNames: List<String>,
    var badge: List<BadgeNameDTO>,


)
