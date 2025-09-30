package rachman.forniandi.pokemonapiapp.mapper

import rachman.forniandi.pokemonapiapp.data.local.UserEntity
import rachman.forniandi.pokemonapiapp.domain.User

fun UserEntity.toDomain(): User {
    return User(
        id = id,
        username = username,
        email = email
    )
}

fun User.toEntity(password: String): UserEntity {
    return UserEntity(
        id = id,
        username = username,
        email = email,
        password = password
    )
}