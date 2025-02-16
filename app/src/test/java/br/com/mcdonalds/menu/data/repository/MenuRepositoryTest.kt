package br.com.mcdonalds.menu.data.repository

import br.com.mcdonalds.menu.model.Restaurant
import br.com.mcdonalds.menu.repository.ApiService
import br.com.mcdonalds.menu.repository.MenuRepository
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class MenuRepositoryTest {

    lateinit var repository: MenuRepository

    @Mock
    lateinit var service: ApiService

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        repository = MenuRepository(service)
    }

    @Test
    fun `get menu test`() {
        runBlocking {
            val restaurantMock: Restaurant = mock()
            Mockito.`when`(service.getMenu()).thenReturn(restaurantMock)
            val response = repository.getRestaurant()
            assertEquals(restaurantMock, response)
        }
    }
}