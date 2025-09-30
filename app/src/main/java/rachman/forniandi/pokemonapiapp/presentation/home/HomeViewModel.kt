package rachman.forniandi.pokemonapiapp.presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import rachman.forniandi.pokemonapiapp.domain.PokemonRepository
import rachman.forniandi.pokemonapiapp.model.Pokemon
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: PokemonRepository
) : ViewModel() {

    var pokemonList: List<Pokemon> by mutableStateOf<List<Pokemon>>(emptyList())
        private set
    var isLoading by mutableStateOf(false)
        private set
    var errorMessage by mutableStateOf<String?>(null)

    private var offset = 0
    private val limit = 10

    init {
        loadPokemons()
    }

    fun loadPokemons() {
        viewModelScope.launch {
            try {
                isLoading = true
                val newPokemons = repository.getPokemonList(limit, offset)
                pokemonList = pokemonList + newPokemons
                offset += limit
            } catch (e: Exception) {
                errorMessage = e.message
            } finally {
                isLoading = false
            }
        }
    }

    fun searchPokemon(name: String, onResult: (Pokemon?) -> Unit) {
        viewModelScope.launch {
            try {
                val detail = repository.getPokemonDetail(name)
                onResult(detail)
            } catch (e: Exception) {
                errorMessage = "Pokémon tidak ditemukan"
                onResult(null)
            }
        }
    }

    var selectedPokemon by mutableStateOf<Pokemon?>(null)
        private set

    fun loadPokemonDetail(name: String) {
        viewModelScope.launch {
            selectedPokemon = repository.getPokemonDetail(name)
        }
    }


}