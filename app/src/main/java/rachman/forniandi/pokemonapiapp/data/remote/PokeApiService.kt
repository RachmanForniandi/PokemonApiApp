package rachman.forniandi.pokemonapiapp.data.remote

import rachman.forniandi.pokemonapiapp.model.PokemonDetailResponse
import rachman.forniandi.pokemonapiapp.model.PokemonListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApiService {
    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): PokemonListResponse

    @GET("pokemon/{name}")
    suspend fun getPokemonDetail(
        @Path("name") name: String
    ): PokemonDetailResponse
}