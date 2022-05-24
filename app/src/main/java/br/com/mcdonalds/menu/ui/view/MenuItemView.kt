package br.com.mcdonalds.menu.ui.view

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.HORIZONTAL
import br.com.mcdonalds.menu.R
import br.com.mcdonalds.menu.data.model.Menu
import br.com.mcdonalds.menu.data.model.MenuItem
import br.com.mcdonalds.menu.ui.adapter.MenuItemAdapter
import br.com.mcdonalds.menu.ui.adapter.MenuItemClickListener

@SuppressLint("ViewConstructor")
class MenuItemView(
    private var menu: Menu,
    private var fragmentManager: FragmentManager,
    context: Context
) : FrameLayout(context), MenuItemClickListener {

    init {
        View.inflate(context, R.layout.menu_item_view, this)
        setupView()
    }

    private fun setupView() {
        val adapter = MenuItemAdapter(menu.items, this)
        val layoutManager = LinearLayoutManager(context, HORIZONTAL, false)

        val categoryText = findViewById<TextView>(R.id.category_text)
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)

        categoryText.text = menu.name
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
    }

    override fun onEventClickListener(menuItem: MenuItem) {
        val bottomSheet = MenuItemBottomSheet(menuItem)
        bottomSheet.show(fragmentManager, MenuItemBottomSheet.TAG)
    }
}
