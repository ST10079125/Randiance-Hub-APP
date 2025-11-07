package com.example.radienceapp

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment

class HomeFragment : Fragment() {

    private lateinit var bookingManager: BookingManager
    private var notificationBadge: View? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bookingManager = BookingManager(requireContext())
        notificationBadge = view.findViewById(R.id.notification_badge)

        // Show badge immediately if there are unread notifications
        updateNotificationBadge()

        // Profile icon click
        view.findViewById<ImageView>(R.id.profile_icon)?.setOnClickListener {
            val intent = Intent(requireContext(), ProfileActivity::class.java)
            startActivity(intent)
        }

        // Notification icon click - shows dialog and then hides badge
        view.findViewById<ImageView>(R.id.notification_icon)?.setOnClickListener {
            showNotificationDialog()
        }

        // Setup all service cards
        setupServiceCards(view)
    }

    override fun onResume() {
        super.onResume()
        // Update badge when returning to fragment (e.g., after booking)
        updateNotificationBadge()
    }

    private fun updateNotificationBadge() {
        // Show green dot if there are unread notifications
        if (bookingManager.hasUnreadNotifications()) {
            notificationBadge?.visibility = View.VISIBLE
        } else {
            notificationBadge?.visibility = View.GONE
        }
    }

    private fun showNotificationDialog() {
        val latestBooking = bookingManager.getLatestBooking()

        if (latestBooking == null) {
            Toast.makeText(requireContext(), "No bookings yet", Toast.LENGTH_SHORT).show()
            return
        }

        // Create dialog first
        val dialogView = LayoutInflater.from(requireContext())
            .inflate(R.layout.dialog_notification_details, null)

        // Populate dialog with booking data
        dialogView.findViewById<TextView>(R.id.dialog_customer_name).text = latestBooking.customerName
        dialogView.findViewById<TextView>(R.id.dialog_appointment_date).text = latestBooking.appointmentDate
        dialogView.findViewById<TextView>(R.id.dialog_appointment_time).text = latestBooking.appointmentTime
        dialogView.findViewById<TextView>(R.id.dialog_services).text = latestBooking.services.joinToString(", ")
        dialogView.findViewById<TextView>(R.id.dialog_email).text = latestBooking.email
        dialogView.findViewById<TextView>(R.id.dialog_phone).text = latestBooking.phone
        dialogView.findViewById<TextView>(R.id.dialog_payment).text = latestBooking.paymentMethod

        val dialog = AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .create()

        dialogView.findViewById<View>(R.id.dialog_close_button).setOnClickListener {
            dialog.dismiss()
        }

        // Show dialog
        dialog.show()

        // Mark as read AFTER showing dialog and hide badge
        bookingManager.markAsRead()
        updateNotificationBadge()
    }

    private fun setupServiceCards(view: View) {
        // Nails cards
        setupCardClick(view, R.id.nails_card_1, "Basic Manicure")
        setupCardClick(view, R.id.nails_card_2, "Gel Overlay")
        setupCardClick(view, R.id.nails_card_3, "Full Set Acrylic")
        setupCardClick(view, R.id.nails_card_4, "Nail Art")

        // Hair cards
        setupCardClick(view, R.id.hair_card_1, "Dry Install (Short)")
        setupCardClick(view, R.id.hair_card_2, "Cornrow/Extension")
        setupCardClick(view, R.id.hair_card_3, "Blow and Curl")
        setupCardClick(view, R.id.hair_card_4, "Treatment and Style")

        // Facial cards
        setupCardClick(view, R.id.facial_card_1, "Basic Facial")
        setupCardClick(view, R.id.facial_card_2, "Deep Cleansing")
        setupCardClick(view, R.id.facial_card_3, "Glow Facial")
        setupCardClick(view, R.id.facial_card_4, "Anti-Ageing")

        // Massage cards
        setupCardClick(view, R.id.massage_card_1, "Swedish Massage")
        setupCardClick(view, R.id.massage_card_2, "Back and Neck")
        setupCardClick(view, R.id.massage_card_3, "Full Body")
        setupCardClick(view, R.id.massage_card_4, "Hot Stone")
    }

    private fun setupCardClick(view: View, cardId: Int, serviceName: String) {
        view.findViewById<CardView>(cardId)?.setOnClickListener {
            val intent = Intent(requireContext(), BookingActivity::class.java)
            intent.putExtra("SERVICE_NAME", serviceName)
            startActivity(intent)
        }
    }
}