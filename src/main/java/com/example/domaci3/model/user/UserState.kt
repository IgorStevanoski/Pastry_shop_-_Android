package com.example.domaci3.model.user

import com.example.domaci3.domain.UserData

data class UserState(
    val users: List<UserData> = emptyList(),
    var logged: String = "",
){
    fun loginUser(
        username: String,
        password: String
    ): Boolean{
        val foundUser = users.filter { it.username == username }

        println(users)

        if (foundUser.isNotEmpty()){
            val user = foundUser[0]
//            logged = username
//            println(logged)
            return password == user.password
        }
        return false
    }

//    fun registerUser(
//        firstname: String,
//        lastname: String,
//        phone: String,
//        address: String,
//        username: String,
//        password: String,
//    ): Boolean{
//        val foundUser = users.filter { it.username == username }
//
//        if (foundUser.isEmpty()){
//            val user = foundUser[0]
//            logged = username
//            return true
//        }
//        return false
//    }
}
