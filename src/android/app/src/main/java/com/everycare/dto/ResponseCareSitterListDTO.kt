package com.everycare.dto

data class ResponseCareSitterListDTO(
    var header: String,
    var message: String,
    var body: ArrayList<SitterListDTO>
)
