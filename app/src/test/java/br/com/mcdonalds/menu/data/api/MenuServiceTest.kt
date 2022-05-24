package br.com.mcdonalds.menu.data.api

import br.com.mcdonalds.menu.BuildConfig
import com.google.gson.Gson
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MenuServiceTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var apiService: MenuService
    private lateinit var gson: Gson

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        gson = Gson()
        mockWebServer = MockWebServer()
        apiService = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build().create(MenuService::class.java)
    }

    @Test
    fun `get menu api test`() {
        runBlocking {
            val mockResponse = MockResponse()
            mockWebServer.enqueue(mockResponse.setBody("[]"))
            val response = apiService.getRestaurants()
            assertNotNull(response)
        }
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
}
