package com.cs473.mda.assignmentweek3lesson5.ui

import android.app.Activity
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import com.cs473.mda.assignmentweek3lesson5.R
import com.cs473.mda.assignmentweek3lesson5.model.DataFactory
import com.cs473.mda.assignmentweek3lesson5.model.User

class MainActivity : AppCompatActivity() {
    private var emailEditText: EditText? = null
    private var passwordEditText: EditText? = null
    private var forgetPasswordTextView: TextView? = null
    private var signInButton: Button? = null
    private var createAccountButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupView()
        bindAction()
    }

    private fun bindAction() {
        signIn()
        createAccount()
        forgetPassword()
    }

    private fun setupView() {
        emailEditText = findViewById(R.id.sign_in_email_et)
        passwordEditText = findViewById(R.id.sign_in_password_et)
        forgetPasswordTextView = findViewById(R.id.sign_in_forget_password_tv)
        signInButton = findViewById(R.id.sign_in_button)
        createAccountButton = findViewById(R.id.sign_in_create_button)
    }

    private fun find(email: String, password: String): User? {
        val users = DataFactory.fetchUserData()
        return users.find { user -> user.email == email && user.password == password }
    }

    private fun signIn() {
        signInButton!!.setOnClickListener {
            val emailText = emailEditText!!.text.toString()
            val passwordText = passwordEditText!!.text.toString()
            val user = find(emailText, passwordText)
            if (user != null) {
                Intent(this, ShoppingActivity::class.java).also {
                    it.putExtra("email", user.email)
                    startActivity(it)
                }
            } else {
                Toast.makeText(this,"Email or Password is in correct", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun forgetPassword() {
        forgetPasswordTextView!!.setOnClickListener {
            val intent = Intent(this, ForgetPasswordActivity::class.java)
            startActivity(intent)
        }
    }

    private fun createAccount() {
        val startForResult = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                val intent = result.data
                val user: User? = intent?.getSerializableExtra("user") as User
                if (user != null) {
                    DataFactory.insertUser(user)
                }
            }
        }
        createAccountButton!!.setOnClickListener {
            val intent = Intent(this, RegisterPageActivity::class.java)
            startForResult.launch(intent)
        }
    }
}