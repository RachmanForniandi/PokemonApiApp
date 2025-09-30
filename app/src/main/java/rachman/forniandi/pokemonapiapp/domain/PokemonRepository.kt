package rachman.forniandi.pokemonapiapp.domain

import rachman.forniandi.pokemonapiapp.model.Pokemon
import rachman.forniandi.pokemonapiapp.model.PokemonDetailResponse
import rachman.forniandi.pokemonapiapp.model.PokemonResult

interface PokemonRepository {
    suspend fun getPokemonList(limit: Int, offset: Int): List<Pokemon>
    suspend fun getPokemonDetail(name: String): Pokemon
}