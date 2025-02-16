package br.com.mcdonalds.menu.di

import br.com.mcdonalds.menu.repository.ApiService
import br.com.mcdonalds.menu.repository.MenuRepository
import br.com.mcdonalds.menu.viewmodel.MenuViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MenuModule {

    val instance = module {
        single {
            Retrofit.Builder()
                .baseUrl("https://mcdonalds.trio.dev/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        single { get<Retrofit>().create(ApiService::class.java) }

        single { MenuRepository(get()) }

        viewModel { MenuViewModel(get()) }
    }
}
