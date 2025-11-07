package com.example.radienceapp

data class BookingData(
    val customerName: String,
    val email: String,
    val phone: String,
    val appointmentDate: String,
    val appointmentTime: String,
    val services: List<String>,
    val paymentMethod: String
)