package br.com.mcdonalds.menu.data.api

class MenuRepository(private val service: MenuService) {

    suspend fun getMenu() = service.getRestaurants()
}
