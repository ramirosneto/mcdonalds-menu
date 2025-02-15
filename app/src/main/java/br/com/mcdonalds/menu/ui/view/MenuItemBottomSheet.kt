package br.com.mcdonalds.menu.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.mcdonalds.menu.R
import br.com.mcdonalds.menu.model.MenuItem
import br.com.mcdonalds.menu.databinding.ModalBottomSheetContentBinding
import br.com.mcdonalds.menu.utils.CurrencyUtils
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.squareup.picasso.Picasso

class MenuItemBottomSheet(private val menuItem: MenuItem) : BottomSheetDialogFragment() {

    private lateinit var binding: ModalBottomSheetContentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ModalBottomSheetContentBinding.inflate(LayoutInflater.from(context), container, false)
        setupBottomSheet()

        return binding.root
    }

    private fun setupBottomSheet() {
        Picasso.get().load(menuItem.url).into(binding.menuItemImg)
        binding.menuItemText.text = menuItem.name
        binding.menuItemPrice.text = CurrencyUtils.formatPrice(menuItem.price)
        binding.menuItemDescription.text = menuItem.description
    }

    companion object {
        const val TAG = "ModalBottomSheet"
    }
}
