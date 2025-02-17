package br.com.mcdonalds.menu.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.mcdonalds.menu.model.MenuItem
import br.com.mcdonalds.menu.utils.CurrencyUtils
import coil.compose.rememberAsyncImagePainter
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuItemBottomSheet(menuItem: MenuItem) {
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberBottomSheetScaffoldState()

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetContent = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = rememberAsyncImagePainter(model = menuItem.url),
                    modifier = Modifier
                        .size(100.dp)
                        .align(Alignment.Center),
                    contentDescription = menuItem.name
                )
                Text(
                    text = menuItem.name,
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .width(120.dp)
                        .align(Alignment.Center),
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Light,
                    fontSize = 12.sp
                )
                Text(
                    text = CurrencyUtils.formatPrice(menuItem.price),
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .width(120.dp)
                        .align(Alignment.Center),
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Light,
                    fontSize = 12.sp
                )
                Text(
                    text = menuItem.description,
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .width(120.dp)
                        .align(Alignment.Center),
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Light,
                    fontSize = 12.sp
                )
            }
        },
        sheetPeekHeight = 0.dp,
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Button(onClick = {
                scope.launch {
                    scaffoldState.bottomSheetState.expand()
                }
            }) {
                Text("Show Bottom Sheet")
            }
        }
    }
}
