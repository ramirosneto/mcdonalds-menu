package br.com.mcdonalds.menu.repository

import br.com.mcdonalds.menu.model.Restaurant
import retrofit2.http.GET

interface RestaurantService {

    @GET("menu")
    suspend fun getRestaurant(): Restaurant
}
