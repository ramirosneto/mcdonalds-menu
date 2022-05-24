package br.com.mcdonalds.menu.data.api

import br.com.mcdonalds.menu.data.model.Restaurant
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
class MenuRepositoryTest {

    lateinit var repository: MenuRepository

    @Mock
    lateinit var service: MenuService

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        repository = MenuRepository(service)
    }

    @Test
    fun `get menu test`() {
        runBlocking {
            val restaurantMock : Restaurant = mockk(relaxed = true)
            Mockito.`when`(service.getRestaurants()).thenReturn(restaurantMock)
            val response = repository.getMenu()
            assertEquals(restaurantMock, response)
        }
    }
}