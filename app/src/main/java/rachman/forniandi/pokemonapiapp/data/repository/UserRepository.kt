package rachman.forniandi.pokemonapiapp.data.repository

import rachman.forniandi.pokemonapiapp.data.local.UserEntity
import rachman.forniandi.pokemonapiapp.domain.User

interface UserRepository {
    suspend fun register(user: User, password: String)
    suspend fun login(email: String, password: String): User?
    suspend fun getUser(email: String): User?
}