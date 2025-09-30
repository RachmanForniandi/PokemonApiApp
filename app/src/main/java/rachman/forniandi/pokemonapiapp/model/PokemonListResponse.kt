package rachman.forniandi.pokemonapiapp.model

import com.google.gson.annotations.SerializedName

data class PokemonListResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<PokemonResult>
)

data class PokemonResult(
    val name: String,
    val url: String
)

data class PokemonDetailResponse(
    val id: Int,
    val name: String,
    val abilities: List<AbilitySlot>,
    val sprites: Sprites,
    val types: List<PokemonTypeSlot>
)

data class AbilitySlot(
    val ability: Ability,
    @SerializedName("is_hidden") val isHidden: Boolean,
    val slot: Int
)

data class Ability(
    val name: String,
    val url: String
)

data class Sprites(
    @SerializedName("front_default") val frontDefault: String?
)

data class PokemonTypeSlot(
    val slot: Int,
    val type: PokemonType
)

data class PokemonType(
    val name: String,
    val url: String
)