package br.com.mcdonalds.menu.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import br.com.mcdonalds.menu.repository.RestaurantRepository
import br.com.mcdonalds.menu.repository.RestaurantService
import br.com.mcdonalds.menu.model.Restaurant
import br.com.mcdonalds.menu.utils.TestCoroutineRule
import br.com.mcdonalds.utils.NetworkStatus
import io.mockk.mockk
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MenuViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var service: RestaurantService

    @Mock
    private lateinit var observer: Observer<NetworkStatus>

    private lateinit var repository: RestaurantRepository
    private lateinit var viewModel: MenuViewModel

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        repository = RestaurantRepository(service)
        viewModel = MenuViewModel(repository)
    }

    @Test
    fun `WHEN fetching menu ok THEN return an object successfully`() {
        val response: Restaurant = mockk(relaxed = true)
        testCoroutineRule.runBlockingTest {
            viewModel.progressLiveStatus.observeForever(observer)
            `when`(repository.getRestaurant()).thenReturn(response)

            viewModel.getMenu()
            assertNotNull(viewModel.getMenu())
        }
    }
}