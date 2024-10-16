package com.example.domaci3.domain


//const newUser = { username: "a", password: "a", firstname: "a", lastname: "a", phoneNumber: "a", address: "a", basket: [], messages: [], type: UserType.User }

data class UserData (
    val username: String,
    val password: String,
    val firstname: String,
    val lastname: String,
    val phoneNumber: String,
    val address: String,
    val basket: List<BasketData> = emptyList(),
    val messages: List<MessageData> = emptyList(),
) {
//    fun isValid(): Boolean {
//        return Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.isNotEmpty() && website.isNotEmpty()
//    }
}

//val id: String,
//val type: String,
//val name: String,
//val description: String,
//val price: String,
//val composition: String,
//val image: String,
//val comments: List<CommentData>,

data class BasketData(
    val productName: String,
    val price: Int,
    val count: Int,
    val image: String,
)

data class MessageData(
    val messageId: Int = 0,
    val price: Int,
    val date: String,
    val status: String
)
