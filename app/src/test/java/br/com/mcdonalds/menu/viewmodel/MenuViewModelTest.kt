package br.com.mcdonalds.menu.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.mcdonalds.menu.model.Menu
import br.com.mcdonalds.menu.model.MenuItem
import br.com.mcdonalds.menu.model.OperationDay
import br.com.mcdonalds.menu.model.Restaurant
import br.com.mcdonalds.menu.repository.MenuRepository
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

@ExperimentalCoroutinesApi
class MenuViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var repository: MenuRepository
    private lateinit var viewModel: MenuViewModel

    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        repository = mock(MenuRepository::class.java)
        viewModel = MenuViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `fetchMenu should update menu LiveData`() = runTest {
        val testMenu = Restaurant(
            currency = "USD",
            description = "Test Description",
            id = "1",
            location = mock(),
            menus = listOf(
                Menu(
                    name = "Breakfast",
                    items = listOf(
                        MenuItem(
                            name = "Egg McMuffin",
                            description = "description",
                            price = 3.5,
                            url = "mcdonalds.com"
                        ),
                        MenuItem(
                            name = "Sausage McMuffin",
                            description = "description",
                            price = 3.0,
                            url = "mcdonalds.com"
                        )
                    )
                )
            ),
            name = "McDonald's",
            operationDays = listOf(
                OperationDay(day = "Monday", endAt = "06:00", startAt = "23:00")
            ),
            phone = "123-456-7890",
            privateParking = true
        )

        `when`(repository.getRestaurant()).thenReturn(testMenu)

        viewModel.menu

        advanceUntilIdle()

        val result = viewModel.menu.value
        assertEquals(testMenu, result)
    }
}