package com.everycare.dto

import java.time.LocalDate

data class SitterContractListDTO(
    var id: Long,
    var title: String,
    var amount: Int,
    var startDate: LocalDate,
    var endDate: LocalDate
)
