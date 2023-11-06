package com.cs473.mda.assignmentweek3lesson5.ui

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.cs473.mda.assignmentweek3lesson5.R
import com.cs473.mda.assignmentweek3lesson5.model.User

class ShoppingActivity : AppCompatActivity() {
    private var welcomeTextView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping)
        setupView()
    }

    private fun setupView() {
        welcomeTextView = findViewById(R.id.shopping_welcome_text)
        val email = intent.getStringExtra("email") ?: ""
        welcomeTextView!!.text = "${resources.getString(R.string.welcome)}  $email"
    }


}