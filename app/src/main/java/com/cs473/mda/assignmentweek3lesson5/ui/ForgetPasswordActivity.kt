package com.cs473.mda.assignmentweek3lesson5.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cs473.mda.assignmentweek3lesson5.R
import com.cs473.mda.assignmentweek3lesson5.model.DataFactory
import com.cs473.mda.assignmentweek3lesson5.model.User


class ForgetPasswordActivity : AppCompatActivity() {
    private var emailEditText: EditText? = null
    private var sendButton: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_password)
        setupView()
        bindAction()
    }

    private fun setupView() {
        emailEditText = findViewById(R.id.forgot_password_email_et)
        sendButton = findViewById(R.id.forget_password_send_button)
    }

    private fun bindAction() {
        sendButton!!.setOnClickListener {
            val emailText = emailEditText!!.text.toString()
            val user = find(emailText)
            if (user != null) {
                val intent = Intent(Intent.ACTION_SEND)
                intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(user.email));
                intent.putExtra(Intent.EXTRA_EMAIL, emailText)
                intent.putExtra(Intent.EXTRA_SUBJECT, "Fogot paswword")
                intent.putExtra(Intent.EXTRA_TEXT, "Your Password is ${user.password}")
                intent.type = "message/rfc822";
                startActivity(intent);
            } else {
                Toast.makeText(this, "Emails is invalid", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun find(email: String): User? {
        val users = DataFactory.fetchUserData()
        return users.find { user -> user.email == email }
    }
}