package br.com.mcdonalds.menu.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.mcdonalds.menu.R
import br.com.mcdonalds.menu.model.Menu
import br.com.mcdonalds.menu.model.MenuItem
import br.com.mcdonalds.menu.ui.view.MenuItemBottomSheet
import br.com.mcdonalds.menu.viewmodel.MenuViewModel
import coil.compose.rememberAsyncImagePainter

@Composable
fun McDonaldsMenuApp(viewModel: MenuViewModel) {
    val menu by viewModel.menu.collectAsState()

    if (menu == null) {
        ShimmerEffect()
    } else {
        menu?.menus?.apply { MainScreen(this) }
    }
}

@Composable
fun ShimmerEffect() {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        repeat(5) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun MainScreen(menuList: List<Menu>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
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
        HorizontalLists(menuList)
    }
}

@Composable
fun HorizontalLists(menuList: List<Menu>) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(start = 16.dp)
    ) {
        items(menuList) { menu ->
            HorizontalList(menu = menu)
            Spacer(modifier = Modifier.height(16.dp))
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
                //bottomSheet.show(supportFragmentManager, MenuItemBottomSheet.TAG)
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