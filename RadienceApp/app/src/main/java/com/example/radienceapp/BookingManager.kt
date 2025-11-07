package com.example.radienceapp

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class BookingManager(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("BookingPrefs", Context.MODE_PRIVATE)
    private val gson = Gson()

    companion object {
        private const val KEY_BOOKINGS = "bookings"
        private const val KEY_HAS_UNREAD = "has_unread"
    }

    fun saveBooking(booking: BookingData) {
        val bookings = getAllBookings().toMutableList()
        bookings.add(0, booking) // Add at beginning

        val json = gson.toJson(bookings)
        sharedPreferences.edit().apply {
            putString(KEY_BOOKINGS, json)
            putBoolean(KEY_HAS_UNREAD, true)
            apply()
        }
    }

    fun getAllBookings(): List<BookingData> {
        val json = sharedPreferences.getString(KEY_BOOKINGS, null) ?: return emptyList()
        val type = object : TypeToken<List<BookingData>>() {}.type
        return gson.fromJson(json, type)
    }

    fun getLatestBooking(): BookingData? {
        return getAllBookings().firstOrNull()
    }

    fun hasUnreadNotifications(): Boolean {
        return sharedPreferences.getBoolean(KEY_HAS_UNREAD, false)
    }

    fun markAsRead() {
        sharedPreferences.edit().putBoolean(KEY_HAS_UNREAD, false).apply()
    }

    fun clearAllBookings() {
        sharedPreferences.edit().clear().apply()
    }
}