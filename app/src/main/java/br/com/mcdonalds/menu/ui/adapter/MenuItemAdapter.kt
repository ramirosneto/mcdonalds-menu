package br.com.mcdonalds.menu.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.mcdonalds.menu.data.model.MenuItem
import br.com.mcdonalds.menu.databinding.MenuListItemBinding
import com.squareup.picasso.Picasso

class MenuItemAdapter(
    private val dataSet: List<MenuItem>,
    private val onClickListener: MenuItemClickListener
) : RecyclerView.Adapter<MenuItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        MenuListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val menuItem = dataSet[position]
        holder.bind(menuItem)

        holder.itemView.rootView.setOnClickListener {
            onClickListener.onEventClickListener(menuItem)
        }
    }

    class ViewHolder(private val binding: MenuListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(menu: MenuItem) {
            Picasso.get()
                .load(menu.url)
                .into(binding.imgMenuItem)
            binding.textMenuItem.text = menu.name
        }
    }

    override fun getItemCount() = dataSet.count()
}
