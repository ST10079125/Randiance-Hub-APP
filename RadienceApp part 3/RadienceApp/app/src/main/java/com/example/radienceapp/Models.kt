package com.example.radienceapp

// Service data class
data class Service(
    val name: String = "",
    val price: String = "",
    val category: String = "",
    val description: String = ""
)

// User data class
data class User(
    val username: String = "",
    val email: String = "",
    val phone: String = "",
    val createdAt: Long = 0
)

// Booking data class
data class Booking(
    val id: String = "",
    val userId: String = "",
    val userName: String = "",
    val userEmail: String = "",
    val userPhone: String = "",
    val service: String = "",
    val date: Long = 0,
    val paymentMethod: String = "",
    val status: String = "pending"
)