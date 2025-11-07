package com.example.radienceapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {

    override fun attachBaseContext(newBase: Context) {
        val sharedPreferences = newBase.getSharedPreferences("RadianceHubPrefs", Context.MODE_PRIVATE)
        val language = sharedPreferences.getString("selected_language", "english") ?: "english"
        super.attachBaseContext(LocaleHelper.setLocale(newBase, language))
    }
}
