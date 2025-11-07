package com.example.radienceapp

import org.junit.Test
import org.junit.Assert.*

class BookingDataTest {

    @Test
    fun testBookingData_creation() {
        val booking = BookingData(
            customerName = "Jane Smith",
            email = "jane@example.com",
            phone = "0987654321",
            appointmentDate = "Tuesday, 16 January 2025",
            appointmentTime = "10:00",
            services = listOf("Facial", "Massage"),
            paymentMethod = "Card Payment"
        )

        assertEquals("Jane Smith", booking.customerName)
        assertEquals("jane@example.com", booking.email)
        assertEquals("0987654321", booking.phone)
        assertEquals("Tuesday, 16 January 2025", booking.appointmentDate)
        assertEquals("10:00", booking.appointmentTime)
        assertEquals(2, booking.services.size)
        assertTrue(booking.services.contains("Facial"))
        assertTrue(booking.services.contains("Massage"))
        assertEquals("Card Payment", booking.paymentMethod)
    }

    @Test
    fun testBookingData_servicesListEmpty() {
        val booking = BookingData(
            customerName = "Test User",
            email = "test@example.com",
            phone = "1234567890",
            appointmentDate = "Monday, 15 January 2025",
            appointmentTime = "09:00",
            services = emptyList(),
            paymentMethod = "EFT"
        )

        assertTrue("Services list should be empty", booking.services.isEmpty())
    }
}