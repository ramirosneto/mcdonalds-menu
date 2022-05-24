package br.com.mcdonalds.menu.data.api

import br.com.mcdonalds.menu.data.model.Restaurant
import retrofit2.http.GET

interface MenuService {

    @GET("menu")
    suspend fun getRestaurants(): Restaurant
}
