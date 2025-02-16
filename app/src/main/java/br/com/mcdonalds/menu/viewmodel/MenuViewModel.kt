package br.com.mcdonalds.menu.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.mcdonalds.menu.model.Restaurant
import br.com.mcdonalds.menu.repository.MenuRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MenuViewModel(private val repository: MenuRepository) : ViewModel() {

    private val _menu = MutableStateFlow<Restaurant?>(null)
    val menu: StateFlow<Restaurant?> get() = _menu

    init {
        fetchMenu()
    }

    private fun fetchMenu() {
        viewModelScope.launch {
            _menu.value = repository.getRestaurant()
        }
    }
}
