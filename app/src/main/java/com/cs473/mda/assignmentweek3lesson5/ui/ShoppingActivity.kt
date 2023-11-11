package com.cs473.mda.assignmentweek3lesson5.ui

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cs473.mda.assignmentweek3lesson5.R
import com.cs473.mda.assignmentweek3lesson5.model.WalmartCategory

class ShoppingActivity : AppCompatActivity() {
    private var welcomeTextView: TextView? = null
    private var electronicImage: ImageView? = null
    private var clothingImage: ImageView? = null
    private var beautyImage: ImageView? = null
    private var foodImage: ImageView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping)
        setupView()
        bindElectronicOnclick()
        bindClothingOnclick()
        bindBeautyOnclick()
        bindFoodOnclick()
    }

    private fun setupView() {
        welcomeTextView = findViewById(R.id.shopping_welcome_text)
        val email = intent.getStringExtra("email") ?: ""
        welcomeTextView!!.text = "${resources.getString(R.string.welcome)}  $email"

        electronicImage = findViewById(R.id.electronic_img)
        clothingImage = findViewById(R.id.clothing_img)
        beautyImage = findViewById(R.id.beauty_img)
        foodImage = findViewById(R.id.food_img)
    }

    private fun bindElectronicOnclick() {
        electronicImage?.setOnClickListener {
            showCategory(WalmartCategory.ELECTRONIC)
        }
    }

    private fun bindClothingOnclick() {
        clothingImage?.setOnClickListener {
            showCategory(WalmartCategory.CLOTHING)
        }
    }

    private fun bindBeautyOnclick() {
        beautyImage?.setOnClickListener {
            showCategory(WalmartCategory.BEAUTY)
        }
    }

    private fun bindFoodOnclick() {
        foodImage?.setOnClickListener {
            showCategory(WalmartCategory.FOOD)
        }
    }


    private fun showCategory(category: WalmartCategory) {
        Toast.makeText(
            this,
            " You have chosen the ${category.toString().lowercase()} category of shopping",
            Toast.LENGTH_SHORT
        )
            .show()
    }


}