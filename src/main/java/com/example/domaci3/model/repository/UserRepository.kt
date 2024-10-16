package rs.edu.raf.raf_vezbe3compose.passwords.repository

import com.example.domaci3.domain.BasketData
import com.example.domaci3.domain.MessageData
import com.example.domaci3.domain.UserData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update

object UserRepository {

    private val users = MutableStateFlow(listOf<UserData>())
    private val logged = MutableStateFlow("")

    fun allusers(): List<UserData> = users.value
    fun logged(): String = logged.value

    suspend fun fetchUsers() {
        users.update { UserSampleData.toMutableList() }
        logged.update {""}
    }

    fun observeUsers(): Flow<List<UserData>> = users.asStateFlow()

    fun observeUserDetails(username: String): Flow<UserData?> {
        return observeUsers()
            .map { users -> users.find { it.username == username } }
            .distinctUntilChanged()
    }

    fun getUserByUsername(username: String): UserData? {
        return users.value.find { it.username == username }
    }

    fun updateOrInsertUser(username: String, data: UserData) {
        users.update { list ->
            val index = list.indexOfFirst { it.username == username }
            if (index != -1) {
                list.toMutableList().apply {
                    this[index] = data
                }
            } else {
                list.toMutableList().apply {
                    add(data)
                }
            }
        }
        if (logged.value == username && logged.value != data.username) {
            logged.update { data.username }
        }
    }

    fun updateUserBasketItem(username: String, data: BasketData) {
        val user = getUserByUsername(username)
        val basket = mutableListOf<BasketData>()
        if (user != null) {
            basket.addAll(user.basket)
        }
        basket.add(data)

        val updatedUser = user?.let {
            UserData(
                user.username,
                user.password,
                user.firstname,
                user.lastname,
                user.phoneNumber,
                user.address,
                basket,
                user.messages
            )
        }

        if (updatedUser != null) {
            updateOrInsertUser(username, updatedUser)
        }
    }

    fun updateUserBasket(username: String, data: MutableList<BasketData>) {
        val user = getUserByUsername(username)

        val updatedUser = user?.let {
            UserData(
                user.username,
                user.password,
                user.firstname,
                user.lastname,
                user.phoneNumber,
                user.address,
                data,
                user.messages
            )
        }

        if (updatedUser != null) {
            updateOrInsertUser(username, updatedUser)
        }
    }

    fun updateUserMessage(username: String, data: MessageData) {
        val user = getUserByUsername(username)
        val messages = mutableListOf<MessageData>()
        if (user != null) {
            val newMessage = MessageData(
                user.messages.size + 1,
                        data.price,
                data.date,
                data.status,
            )
            messages.addAll(user.messages)
            messages.add(newMessage)
        }

        val updatedUser = user?.let {
            UserData(
                user.username,
                user.password,
                user.firstname,
                user.lastname,
                user.phoneNumber,
                user.address,
                user.basket,
                messages
            )
        }

        if (updatedUser != null) {
            updateOrInsertUser(username, updatedUser)
        }
    }

    fun setLoggedUser(username: String) {
        logged.update { username }
    }

//    fun loginUser(username: String, password: String): Boolean {
//        val user = users.value.find { it.username == username }
//        if (user != null){
//            if (user.password == password)
//                return true
//        }
//        return false
//    }
}
