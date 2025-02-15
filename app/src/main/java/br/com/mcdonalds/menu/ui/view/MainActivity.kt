package br.com.mcdonalds.menu.ui.view

import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.mcdonalds.menu.R
import br.com.mcdonalds.menu.model.Menu
import br.com.mcdonalds.menu.model.MenuItem
import br.com.mcdonalds.menu.viewmodel.MenuViewModel
import coil.compose.rememberAsyncImagePainter
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val menuViewModel: MenuViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                MainScreen()
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun MainScreen() {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .width(108.dp)
                    .height(108.dp)
                    .padding(top = 32.dp),
                painter = painterResource(R.drawable.ic_mcdonalds_logo),
                contentDescription = "MC Donald's"
            )
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 32.dp),
            )
            HorizontalLists(menuViewModel)
        }
    }

    @Composable
    fun HorizontalLists(viewModel: MenuViewModel) {
        viewModel.state.restaurant?.let { restaurant ->
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(start = 16.dp)
            ) {
                items(restaurant.menus) { menu ->
                    HorizontalList(menu = menu)
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }

    @Composable
    fun HorizontalList(menu: Menu) {
        Column {
            Text(
                text = menu.name,
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .fillMaxWidth(),
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 36.sp
            )
            LazyRow {
                items(menu.items) { menuItem ->
                    ItemCard(menuItem = menuItem)
                }
            }
        }
    }

    @Composable
    fun ItemCard(menuItem: MenuItem) {
        Card(
            border = BorderStroke(1.dp, Color.Gray),
            colors = CardColors(Color.White, Color.White, Color.White, Color.White),
            modifier = Modifier
                .padding(8.dp)
                .clickable {
                    val bottomSheet = MenuItemBottomSheet(menuItem)
                    bottomSheet.show(supportFragmentManager, MenuItemBottomSheet.TAG)
                }
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Image(
                    painter = rememberAsyncImagePainter(model = menuItem.url),
                    modifier = Modifier
                        .size(100.dp)
                        .align(Alignment.CenterHorizontally),
                    contentDescription = menuItem.name
                )
                Text(
                    text = menuItem.name,
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .width(120.dp)
                        .align(Alignment.CenterHorizontally),
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Light,
                    fontSize = 12.sp
                )
            }
        }
    }

    private fun displayError(errorMessage: String) {
        Snackbar.make(window.decorView.rootView, errorMessage, Toast.LENGTH_LONG).show()
    }
}
