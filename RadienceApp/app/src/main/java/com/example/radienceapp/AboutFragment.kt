package com.example.radienceapp

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioGroup
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment

class AboutFragment : Fragment() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var languageRadioGroup: RadioGroup
    private lateinit var logoutButton: Button

    companion object {
        private const val PREFS_NAME = "RadianceHubPrefs"
        private const val KEY_LANGUAGE = "selected_language"
        private const val KEY_IS_LOGGED_IN = "is_logged_in"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_about, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPreferences = requireContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        languageRadioGroup = view.findViewById(R.id.language_radio_group)
        logoutButton = view.findViewById(R.id.logout_button)

        loadLanguagePreference()

        // Profile Settings Card Click
        view.findViewById<CardView>(R.id.profile_settings_card)?.setOnClickListener {
            startActivity(Intent(requireContext(), ProfileActivity::class.java))
        }

        // About Card Click
        view.findViewById<CardView>(R.id.about_card)?.setOnClickListener {
            showAboutDialog()
        }

        // Language Selection Listener
        languageRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            handleLanguageChange(checkedId)
        }

        // Logout Button
        logoutButton.setOnClickListener { showLogoutConfirmationDialog() }
    }

    private fun loadLanguagePreference() {
        val savedLanguage = sharedPreferences.getString(KEY_LANGUAGE, "english") ?: "english"
        val radioButtonId = when (savedLanguage) {
            "sepedi" -> R.id.language_sepedi
            "venda" -> R.id.language_venda
            else -> R.id.language_english
        }
        languageRadioGroup.check(radioButtonId)
    }

    private fun handleLanguageChange(checkedId: Int) {
        val selectedLanguage = when (checkedId) {
            R.id.language_sepedi -> "sepedi"
            R.id.language_venda -> "venda"
            else -> "english"
        }

        sharedPreferences.edit().putString(KEY_LANGUAGE, selectedLanguage).apply()

        // Show confirmation toast
        val languageName = view?.findViewById<android.widget.RadioButton>(checkedId)?.text.toString()
        Toast.makeText(requireContext(), getString(R.string.language_changed, languageName), Toast.LENGTH_SHORT).show()

        // Restart the whole app to apply changes to all strings
        val intent = requireActivity().intent
        requireActivity().finish()
        startActivity(intent)
    }

    private fun showAboutDialog() {
        val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_about, null)
        val dialog = AlertDialog.Builder(requireContext()).setView(dialogView).create()
        dialogView.findViewById<View>(R.id.dialog_about_close_button).setOnClickListener { dialog.dismiss() }
        dialog.show()
    }

    private fun showLogoutConfirmationDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.logout))
            .setMessage(getString(R.string.logout_message))
            .setPositiveButton(getString(R.string.yes)) { dialog, _ ->
                performLogout()
                dialog.dismiss()
            }
            .setNegativeButton(getString(R.string.cancel)) { dialog, _ -> dialog.dismiss() }
            .create()
            .show()
    }

    private fun performLogout() {
        sharedPreferences.edit().putBoolean(KEY_IS_LOGGED_IN, false).apply()
        Toast.makeText(requireContext(), getString(R.string.logged_out), Toast.LENGTH_SHORT).show()
        val intent = Intent(requireContext(), LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        requireActivity().finish()
    }
}
