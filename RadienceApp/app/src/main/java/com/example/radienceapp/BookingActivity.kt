package com.example.radienceapp

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class BookingActivity : AppCompatActivity() {

    private lateinit var fullNameInput: EditText
    private lateinit var emailInput: EditText
    private lateinit var phoneInput: EditText
    private lateinit var appointmentDateInput: EditText
    private lateinit var appointmentTimeInput: EditText
    private lateinit var serviceCheckboxGroup: LinearLayout
    private lateinit var paymentGroup: RadioGroup
    private lateinit var payButton: Button
    private lateinit var bookingManager: BookingManager

    private var selectedService = ""
    private var selectedDate: Calendar = Calendar.getInstance()
    private var selectedTime: Calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking)

        selectedService = intent.getStringExtra("SERVICE_NAME") ?: ""

        // Initialize booking manager
        bookingManager = BookingManager(this)

        // Initialize views
        fullNameInput = findViewById(R.id.full_name_input)
        emailInput = findViewById(R.id.email_input)
        phoneInput = findViewById(R.id.phone_input)
        appointmentDateInput = findViewById(R.id.appointment_date_input)
        appointmentTimeInput = findViewById(R.id.appointment_time_input)
        serviceCheckboxGroup = findViewById(R.id.service_checkbox_group)
        paymentGroup = findViewById(R.id.payment_group)
        payButton = findViewById(R.id.pay_button)

        // Setup service checkboxes
        setupServiceCheckboxes()

        // Setup date picker
        appointmentDateInput.setOnClickListener {
            showDatePicker()
        }

        // Setup time picker
        appointmentTimeInput.setOnClickListener {
            showTimePicker()
        }

        // Pay button
        payButton.setOnClickListener {
            processBooking()
        }
    }

    private fun showDatePicker() {
        val year = selectedDate.get(Calendar.YEAR)
        val month = selectedDate.get(Calendar.MONTH)
        val day = selectedDate.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDay ->
                selectedDate.set(selectedYear, selectedMonth, selectedDay)
                updateDateDisplay()
            },
            year,
            month,
            day
        )

        // Set minimum date to today
        datePickerDialog.datePicker.minDate = System.currentTimeMillis()
        datePickerDialog.show()
    }

    private fun showTimePicker() {
        val hour = selectedTime.get(Calendar.HOUR_OF_DAY)
        val minute = selectedTime.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(
            this,
            { _, selectedHour, selectedMinute ->
                selectedTime.set(Calendar.HOUR_OF_DAY, selectedHour)
                selectedTime.set(Calendar.MINUTE, selectedMinute)
                updateTimeDisplay()
            },
            hour,
            minute,
            true // 24-hour format
        )
        timePickerDialog.show()
    }

    private fun updateDateDisplay() {
        val dateFormat = SimpleDateFormat("EEEE, dd MMMM yyyy", Locale.getDefault())
        appointmentDateInput.setText(dateFormat.format(selectedDate.time))
    }

    private fun updateTimeDisplay() {
        val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        appointmentTimeInput.setText(timeFormat.format(selectedTime.time))
    }

    private fun setupServiceCheckboxes() {
        val services = arrayOf("Nails", "Hair", "Facial", "Massage", "Hair (Formula / Sew-in)")

        for (service in services) {
            val checkBox = CheckBox(this)
            checkBox.text = service
            checkBox.textSize = 16f
            checkBox.setPadding(8, 8, 8, 8)

            if (service == selectedService) {
                checkBox.isChecked = true
            }

            serviceCheckboxGroup.addView(checkBox)
        }
    }

    private fun processBooking() {
        val fullName = fullNameInput.text.toString().trim()
        val email = emailInput.text.toString().trim()
        val phone = phoneInput.text.toString().trim()
        val appointmentDate = appointmentDateInput.text.toString().trim()
        val appointmentTime = appointmentTimeInput.text.toString().trim()

        // Validation
        if (fullName.isEmpty()) {
            fullNameInput.error = "Full name is required"
            fullNameInput.requestFocus()
            return
        }

        if (email.isEmpty()) {
            emailInput.error = "Email is required"
            emailInput.requestFocus()
            return
        }

        if (phone.isEmpty()) {
            phoneInput.error = "Phone number is required"
            phoneInput.requestFocus()
            return
        }

        if (appointmentDate.isEmpty()) {
            Toast.makeText(this, "Please select an appointment date", Toast.LENGTH_SHORT).show()
            return
        }

        if (appointmentTime.isEmpty()) {
            Toast.makeText(this, "Please select an appointment time", Toast.LENGTH_SHORT).show()
            return
        }

        // Get selected services
        val selectedServices = mutableListOf<String>()
        for (i in 0 until serviceCheckboxGroup.childCount) {
            val checkBox = serviceCheckboxGroup.getChildAt(i) as CheckBox
            if (checkBox.isChecked) {
                selectedServices.add(checkBox.text.toString())
            }
        }

        if (selectedServices.isEmpty()) {
            Toast.makeText(this, "Please select at least one service", Toast.LENGTH_SHORT).show()
            return
        }

        // Get payment method
        val selectedPaymentId = paymentGroup.checkedRadioButtonId
        if (selectedPaymentId == -1) {
            Toast.makeText(this, "Please select a payment method", Toast.LENGTH_SHORT).show()
            return
        }

        val paymentMethod = findViewById<RadioButton>(selectedPaymentId).text.toString()

        // Save booking data
        val bookingData = BookingData(
            customerName = fullName,
            email = email,
            phone = phone,
            appointmentDate = appointmentDate,
            appointmentTime = appointmentTime,
            services = selectedServices,
            paymentMethod = paymentMethod
        )

        bookingManager.saveBooking(bookingData)

        // Show success message
        Toast.makeText(
            this,
            "Booking confirmed! Check notifications for details.",
            Toast.LENGTH_LONG
        ).show()

        finish()
    }
}