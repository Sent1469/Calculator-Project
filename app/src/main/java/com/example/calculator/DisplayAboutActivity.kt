package com.example.calculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class DisplayAboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_about)
        // Code for the about page and back button to return to the calculator
        val backBtn = findViewById<Button>(R.id.backBtn)
        val backIntent = Intent(this, MainActivity::class.java).apply {}
        backBtn.setOnClickListener {
            startActivity(backIntent)
        }
    }
}