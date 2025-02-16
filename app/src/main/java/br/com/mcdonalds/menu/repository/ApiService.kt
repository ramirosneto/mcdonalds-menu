package br.com.mcdonalds.menu.repository

import br.com.mcdonalds.menu.model.Restaurant
import retrofit2.http.GET

interface ApiService {

    @GET("menu")
    suspend fun getMenu(): Restaurant
}
