package com.cs473.mda.assignmentweek3lesson5.model

class DataFactory {

     companion object {
         private var users: ArrayList<User>? = null
         fun fetchUserData(): List<User> {
             if (users == null) {
                 var user1 = User("kchanproseth@gmail.com","secure123","MyName", "MyLastName")
                 var user2 = User("kchanproseth2@gmail.com","secure123","MyName", "MyLastName")
                 var user3 = User("kchanproseth3@gmail.com", "secure123", "MyName", "MyLastName")
                 var user4 = User("kchanproseth4@gmail.com", "secure123", "MyName", "MyLastName")
                 var user5 = User("kchanproseth5@gmail.com", "secure123", "MyName", "MyLastName")
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