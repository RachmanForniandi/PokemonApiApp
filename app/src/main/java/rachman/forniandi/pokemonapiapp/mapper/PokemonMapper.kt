package rachman.forniandi.pokemonapiapp.mapper

import rachman.forniandi.pokemonapiapp.model.Pokemon
import rachman.forniandi.pokemonapiapp.model.PokemonDetailResponse
import rachman.forniandi.pokemonapiapp.model.PokemonResult

fun PokemonDetailResponse.toDomain(): Pokemon {
    return Pokemon(
        id = id,
        name = name,
        imageUrl = sprites.frontDefault ?: "",
        abilities = abilities.map { it.ability.name },
        types = types.map { it.type.name }
    )
}

fun List<PokemonResult>.toDomainList(): List<Pokemon> {
    return this.map { result ->
        Pokemon(
            name = result.name,
            imageUrl = result.url,
            id = 0,
            abilities = emptyList(),
            types = emptyList()
        )
    }
}