package rachman.forniandi.pokemonapiapp.presentation.auth

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import rachman.forniandi.pokemonapiapp.data.local.SessionManager
import rachman.forniandi.pokemonapiapp.data.repository.UserRepository
import rachman.forniandi.pokemonapiapp.domain.User
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val sessionManager: SessionManager
) : ViewModel() {

    private val _loginState = MutableStateFlow<Boolean>(false)
    val loginState: StateFlow<Boolean> = _loginState

    var errorMessage by mutableStateOf<String?>(null)
        private set

    fun login(email: String, password: String) {
        viewModelScope.launch {
            val user = userRepository.login(email, password)
            if (user != null) {
                sessionManager.saveSession(user.username, user.email)
                _loginState.value = true
                //onSuccess()
            } else {
                errorMessage = "Invalid email or password"
            }
            sessionManager.saveSession(
                username = user?.username ?: "",
                email = user?.email ?: ""
            )
        }
    }

    fun register(username: String, email: String, password: String) {
        viewModelScope.launch {
            val newUser = User(id = 0, username = username, email = email)
            userRepository.register(newUser, password)
            //onSuccess()
        }
    }


    fun logout() {
        //loginState = null
        viewModelScope.launch {
            sessionManager.clearSession()
            _loginState.value = false
        }
    }


}