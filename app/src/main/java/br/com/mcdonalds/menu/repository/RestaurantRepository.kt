package br.com.mcdonalds.menu.repository

class RestaurantRepository(private val service: RestaurantService) {

    suspend fun getRestaurant() = service.getRestaurant()
}
