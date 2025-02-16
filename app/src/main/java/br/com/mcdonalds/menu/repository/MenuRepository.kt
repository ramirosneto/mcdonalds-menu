package br.com.mcdonalds.menu.repository

class MenuRepository(private val service: ApiService) {

    suspend fun getRestaurant() = service.getMenu()
}
