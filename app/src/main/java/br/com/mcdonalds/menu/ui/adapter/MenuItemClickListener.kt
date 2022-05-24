package br.com.mcdonalds.menu.ui.adapter

import br.com.mcdonalds.menu.data.model.MenuItem

interface MenuItemClickListener {

    fun onEventClickListener(menuItem: MenuItem)
}
