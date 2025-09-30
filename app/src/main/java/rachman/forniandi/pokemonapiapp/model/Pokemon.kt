package rachman.forniandi.pokemonapiapp.model

data class Pokemon(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val abilities: List<String>,
    val types: List<String>
)
