# Assignment Week 3 Lesson 5

Course: Mobile Device Application CS473

Assignment Name: Assignment Week 3 Lesson 5


### App Flow

#### App started Sign in Screen (user filled email and password)
![alt text](https://github.com/kanchanproseth/Assignment_Week3_Lesson5/blob/main/screenshot/sign_in_enter_value.png?raw=true)

#### App started Sign in Screen (validate invalid)
![alt text](https://github.com/kanchanproseth/Assignment_Week3_Lesson5/blob/main/screenshot/sign_in_invalid.png?raw=true)

#### App started Sign in Screen (validate login success and move to Shopping screen)
![alt text](https://github.com/kanchanproseth/Assignment_Week3_Lesson5/blob/main/screenshot/sign_in_success.png?raw=true)



#### Register screen (user filled in all required field)
![alt text](https://github.com/kanchanproseth/Assignment_Week3_Lesson5/blob/main/screenshot/user_existed.png?raw=true)

#### Register screen (user create account successfully)
![alt text](https://github.com/kanchanproseth/Assignment_Week3_Lesson5/blob/main/screenshot/create_account_success.png?raw=true)

#### Forgot Password page 
![alt text](https://github.com/kanchanproseth/Assignment_Week3_Lesson5/blob/main/screenshot/invalid_forget_pw.png?raw=true)

#### category on click (when user click on category show toast message)
![alt text](https://github.com/kanchanproseth/Assignment_Week3_Lesson5/blob/main/screenshot/category_on_click.png?raw=true)

### Coding Section

#### DataFactory

```kotlin
class DataFactory {

     companion object {
         private var users: ArrayList<User>? = null
         fun fetchUserData(): List<User> {
             if (users == null) {
                 var user1 = User("kchanproseth@gmail.com","secure123","MyName", "MyLastName")
                 var user2 = User("kchanproseth2@gmail.com","secure123","MyName", "MyLastName")
                 var user3 = User("kchanproseth3@gmail.com","secure123","MyName", "MyLastName")
                 var user4 = User("kchanproseth4@gmail.com","secure123","MyName", "MyLastName")
                 var user5 = User("kchanproseth5@gmail.com","secure123","MyName", "MyLastName")
                 users = arrayListOf(user1, user2, user3, user4, user5)
             }
             return users!!
         }
         fun insertUser(user: User) {
             users!!.add(user)
         }

         fun find(email: String): User? {
             return DataFactory.fetchUserData().find { user -> user.email == email }
         }

         fun find(email: String, password: String): User? {
             val users = DataFactory.fetchUserData()
             return users.find { user -> user.email == email && user.password == password }
         }
     }
}
```

#### User data class

```kotlin
data class User(val email: String?, val password: String, val firstName: String, val lastName: String): Serializable
```



#### MainActivity [main function]

##### Sign in function

```kotlin
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
```

##### Forget password function

```kotlin
private fun forgetPassword() {
    forgetPasswordTextView!!.setOnClickListener {
        val intent = Intent(this, ForgetPasswordActivity::class.java)
        startActivity(intent)
    }
}
```

##### Create account function

```kotlin
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
```

#### MainActivity [main function]

##### Sign in function

```kotlin
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
```

##### Forget password function

```kotlin
    private fun forgetPassword() {
        forgetPasswordTextView!!.setOnClickListener {
            val intent = Intent(this, ForgetPasswordActivity::class.java)
            startActivity(intent)
        }
    }
```

##### Create account function

```kotlin
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
```

#### Register Activity [main function]

##### Create account button OnClick listener function

```kotlin
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
```


#### Shopping Activity [main function]

##### setup view 

```kotlin
private fun setupView() {
    welcomeTextView = findViewById(R.id.shopping_welcome_text)
    val email = intent.getStringExtra("email") ?: ""
    welcomeTextView!!.text = "${resources.getString(R.string.welcome)}  $email"
}
```

##### Category on click 

```kotlin
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
```

#### Forgot Password Activity [main function]

##### sendButton OnClick listener 

```kotlin
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
```




