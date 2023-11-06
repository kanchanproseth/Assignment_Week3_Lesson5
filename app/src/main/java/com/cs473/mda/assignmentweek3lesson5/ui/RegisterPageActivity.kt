package com.cs473.mda.assignmentweek3lesson5.ui

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cs473.mda.assignmentweek3lesson5.R
import com.cs473.mda.assignmentweek3lesson5.model.DataFactory
import com.cs473.mda.assignmentweek3lesson5.model.User

class RegisterPageActivity : AppCompatActivity() {
    private var firstNameEditText: EditText? = null
    private var lastNameEditText: EditText? = null
    private var emailEditText: EditText? = null
    private var passwordEditText: EditText? = null
    private var createAccountButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_page)
        setupView()
        bindAction()
    }

    private fun setupView() {
        firstNameEditText = findViewById(R.id.register_first_name_et)
        lastNameEditText = findViewById(R.id.register_last_name_et)
        emailEditText = findViewById(R.id.register_email_address_et)
        passwordEditText = findViewById(R.id.register_password_et)
        createAccountButton = findViewById(R.id.register_create_account_button)
    }

    private fun bindAction() {
        createAccountButton!!.setOnClickListener {
            val firstName = firstNameEditText!!.text.toString()
            val lastName = lastNameEditText!!.text.toString()
            val email = emailEditText!!.text.toString()
            val password = passwordEditText!!.text.toString()
            val user = DataFactory.find(email)
            if (user == null) {
                validateShowMessage(firstName, lastName, email, password)
                if ( firstName.isNotEmpty() || lastName.isNotEmpty() || email.isNotEmpty() || password.isNotEmpty()) {
                    Toast.makeText(this, "Account created successfully", Toast.LENGTH_SHORT).show()
                    intent.putExtra("user", User(email, password, firstName, lastName))
                    setResult(RESULT_OK, intent);
                    finish()
                }
            } else {
                Toast.makeText(this, "the emailed is already existed", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validateShowMessage(firstName: String, lastName: String, email: String, password: String) {
        if (firstName.isEmpty()) {
            Toast.makeText(this, "first name is empty", Toast.LENGTH_SHORT).show()
        }

        if (lastName.isEmpty()) {
            Toast.makeText(this, "last name is empty", Toast.LENGTH_SHORT).show()
        }

        if (email.isEmpty()) {
            Toast.makeText(this, "email is empty", Toast.LENGTH_SHORT).show()
        }

        if (password.isEmpty()) {
            Toast.makeText(this, "password is empty", Toast.LENGTH_SHORT).show()
        }
    }

}