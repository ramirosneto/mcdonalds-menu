package br.com.mcdonalds.menu

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import br.com.mcdonalds.menu.data.model.Restaurant
import br.com.mcdonalds.menu.databinding.ActivityMainBinding
import br.com.mcdonalds.menu.ui.view.MenuItemView
import br.com.mcdonalds.menu.ui.viewmodel.MenuViewModel
import br.com.mcdonalds.utils.NetworkStatus
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val menuViewModel: MenuViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setupViews()
    }

    private fun setupViews() {
        menuViewModel.getMenu()
        menuViewModel.progressLiveStatus.observe(this) {
            when (it) {
                is NetworkStatus.Loading -> showLoading()
                is NetworkStatus.Success<*> -> displayData(it.data as Restaurant)
                is NetworkStatus.Error -> displayError(it.errorMessage)
            }
        }
    }

    private fun showLoading() {
        binding.shimmer.startShimmer()
        binding.shimmer.showShimmer(true)
        binding.fragmentSkeleton.skeletonList.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        binding.shimmer.stopShimmer()
        binding.shimmer.hideShimmer()
        binding.fragmentSkeleton.skeletonList.visibility = View.GONE
    }

    private fun displayData(restaurant: Restaurant) {
        hideLoading()

        restaurant.menus.forEach { menu ->
            val customView = MenuItemView(menu, supportFragmentManager, this)
            binding.content.addView(customView)
        }
    }

    private fun displayError(errorMessage: String) {
        Toast.makeText(this@MainActivity, errorMessage, Toast.LENGTH_LONG).show()
        hideLoading()
    }
}
