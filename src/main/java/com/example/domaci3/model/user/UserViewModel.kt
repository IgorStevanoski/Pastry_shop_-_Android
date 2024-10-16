import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domaci3.domain.UserData
import com.example.domaci3.model.user.UserState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import rs.edu.raf.raf_vezbe3compose.passwords.repository.UserRepository
import java.io.IOException

class UserViewModel (
    private val repository: UserRepository = UserRepository
) : ViewModel() {

    private val _state = MutableStateFlow(UserState())
    val state = _state.asStateFlow()
    private fun setState(reducer: UserState.() -> UserState) = _state.getAndUpdate(reducer)

//    private val events = MutableSharedFlow<UserUiEvent>()
//    fun setEvent(event: UserUiEvent) {
//        viewModelScope.launch {
//            events.emit(event)
//        }
//    }

    init {
        observeUsers()
        //fetchUsers()
//        observeEvents()
    }

//    private fun observeEvents() {
//        viewModelScope.launch {
//            events.collect {
//                when (it) {
//                    is UserUiEvent.RequestLogin -> {
//                        loginUser(username = it.username)
//                    }
//                }
//            }
//        }
//    }


    private fun observeUsers() {
        viewModelScope.launch {
            repository.observeUsers().collect {
                setState { copy(users = it, logged = "") }
            }
        }
    }

    fun fetchUsers() {
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    repository.fetchUsers()
                }
            } catch (error: IOException) {
            }
        }
    }

    private fun registerUser(user: UserData){
        viewModelScope.launch {
            repository.updateOrInsertUser(user.username, user)
        }
    }

    private fun loginUser(username: String){
        viewModelScope.launch {
            setState { copy(logged = username) }
        }
    }

}