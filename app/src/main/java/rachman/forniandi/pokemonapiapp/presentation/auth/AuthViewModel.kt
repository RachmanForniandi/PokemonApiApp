package rachman.forniandi.pokemonapiapp.presentation.auth

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import rachman.forniandi.pokemonapiapp.data.repository.UserRepository
import rachman.forniandi.pokemonapiapp.domain.User
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    var loginState by mutableStateOf<User?>(null)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    fun login(email: String, password: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            val user = userRepository.login(email, password)
            if (user != null) {
                loginState = user
                onSuccess()
            }
        }
    }

    fun register(username: String, email: String, password: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            val newUser = User(id = 0, username = username, email = email)
            userRepository.register(newUser, password)
            onSuccess()
        }
    }


    fun logout() {
        loginState = null
    }


}