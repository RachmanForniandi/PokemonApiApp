package rachman.forniandi.pokemonapiapp.data.repository

import rachman.forniandi.pokemonapiapp.data.remote.PokeApiService
import rachman.forniandi.pokemonapiapp.domain.PokemonRepository
import rachman.forniandi.pokemonapiapp.mapper.toDomain
import rachman.forniandi.pokemonapiapp.mapper.toDomainList
import rachman.forniandi.pokemonapiapp.model.Pokemon
import rachman.forniandi.pokemonapiapp.model.PokemonDetailResponse
import rachman.forniandi.pokemonapiapp.model.PokemonResult
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val pokeApi: PokeApiService
) : PokemonRepository {
    override suspend fun getPokemonList(
        limit: Int,
        offset: Int
    ): List<Pokemon> {
        val response= pokeApi.getPokemonList(limit,offset)
        return response.results.toDomainList()
    }

    override suspend fun getPokemonDetail(name: String): Pokemon {
        val detailResponse =pokeApi.getPokemonDetail(name)
        return detailResponse.toDomain()
    }
}