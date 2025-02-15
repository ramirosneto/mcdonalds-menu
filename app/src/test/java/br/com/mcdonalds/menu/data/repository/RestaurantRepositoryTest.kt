package br.com.mcdonalds.menu.data.repository

import br.com.mcdonalds.menu.model.Restaurant
import br.com.mcdonalds.menu.repository.RestaurantRepository
import br.com.mcdonalds.menu.repository.RestaurantService
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class RestaurantRepositoryTest {

    lateinit var repository: RestaurantRepository

    @Mock
    lateinit var service: RestaurantService

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        repository = RestaurantRepository(service)
    }

    @Test
    fun `get menu test`() {
        runBlocking {
            val restaurantMock : Restaurant = mockk(relaxed = true)
            Mockito.`when`(service.getRestaurant()).thenReturn(restaurantMock)
            val response = repository.getRestaurant()
            assertEquals(restaurantMock, response)
        }
    }
}