package rachman.forniandi.pokemonapiapp.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import rachman.forniandi.pokemonapiapp.data.local.UserEntity
import rachman.forniandi.pokemonapiapp.data.repository.UserRepository
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userRepository: UserRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var currentUser by mutableStateOf<UserEntity?>(null)
        private set

    fun setUser(user: UserEntity) {
        currentUser = user
    }
}