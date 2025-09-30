package rachman.forniandi.pokemonapiapp.data.repository

import rachman.forniandi.pokemonapiapp.data.local.UserDao
import rachman.forniandi.pokemonapiapp.data.local.UserEntity
import rachman.forniandi.pokemonapiapp.domain.User
import rachman.forniandi.pokemonapiapp.mapper.toDomain
import rachman.forniandi.pokemonapiapp.mapper.toEntity
import javax.inject.Inject


class UserRepositoryImpl @Inject constructor(private val userDao: UserDao) : UserRepository {
    override suspend fun register(
        user: User,
        password: String
    ) = userDao.registerUser(user.toEntity(password))

    override suspend fun login(
        email: String,
        password: String
    ): User? = userDao.login(email, password)?.toDomain()

    override suspend fun getUser(email: String): User?
    = userDao.getUserByEmail(email)?.toDomain()

}