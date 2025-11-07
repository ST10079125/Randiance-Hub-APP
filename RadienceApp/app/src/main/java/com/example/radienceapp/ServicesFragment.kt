package com.example.radienceapp

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class ServicesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_services, container, false)

        // Find all book now buttons
        val nailsBookButton = view.findViewById<Button>(R.id.nails_book_button)
        val hairBookButton = view.findViewById<Button>(R.id.hair_book_button)
        val facialBookButton = view.findViewById<Button>(R.id.facial_book_button)
        val massageBookButton = view.findViewById<Button>(R.id.massage_book_button)

        // Set click listeners
        nailsBookButton.setOnClickListener {
            navigateToBooking("Nails")
        }

        hairBookButton.setOnClickListener {
            navigateToBooking("Hair")
        }

        facialBookButton.setOnClickListener {
            navigateToBooking("Facial")
        }

        massageBookButton.setOnClickListener {
            navigateToBooking("Massage")
        }

        return view
    }

    private fun navigateToBooking(serviceName: String) {
        val intent = Intent(requireContext(), BookingActivity::class.java)
        intent.putExtra("SERVICE_NAME", serviceName)
        startActivity(intent)
    }
}