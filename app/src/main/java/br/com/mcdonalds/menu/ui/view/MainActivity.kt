package br.com.mcdonalds.menu.ui.view

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import br.com.mcdonalds.menu.ui.McDonaldsMenuApp
import br.com.mcdonalds.menu.viewmodel.MenuViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val menuViewModel: MenuViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            McDonaldsMenuApp(menuViewModel)
        }
    }
}
