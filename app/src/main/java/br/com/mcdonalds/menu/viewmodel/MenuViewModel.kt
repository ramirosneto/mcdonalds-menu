package br.com.mcdonalds.menu.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.mcdonalds.menu.model.Restaurant
import br.com.mcdonalds.menu.repository.RestaurantRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MenuViewModel(private val repository: RestaurantRepository) : ViewModel() {

    var state by mutableStateOf(ScreenState())

    init {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val result = repository.getRestaurant()
                    state = state.copy(
                        restaurant = result
                    )
                } catch (throwable: Throwable) {
                    state = state.copy(
                        error = throwable.message
                    )
                }
            }
        }
    }
}

data class ScreenState(
    val restaurant: Restaurant? = null,
    val error: String? = null
)
