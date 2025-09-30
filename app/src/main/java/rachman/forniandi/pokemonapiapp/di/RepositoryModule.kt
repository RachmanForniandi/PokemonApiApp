package rachman.forniandi.pokemonapiapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import rachman.forniandi.pokemonapiapp.data.repository.PokemonRepositoryImpl
import rachman.forniandi.pokemonapiapp.data.repository.UserRepository
import rachman.forniandi.pokemonapiapp.data.repository.UserRepositoryImpl
import rachman.forniandi.pokemonapiapp.domain.PokemonRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindPokemonRepository(
        impl: PokemonRepositoryImpl
    ): PokemonRepository

    @Binds
    @Singleton
    abstract fun bindUserRepository(
        impl: UserRepositoryImpl
    ): UserRepository
}