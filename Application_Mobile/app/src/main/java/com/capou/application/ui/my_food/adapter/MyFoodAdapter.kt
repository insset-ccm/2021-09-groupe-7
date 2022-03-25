package com.capou.application.ui.my_food.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.capou.application.databinding.ItemPickUpPointBinding
import com.capou.application.helper.FonctionHelper


val diffUtils = object : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }


    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }


}

class ChuckNorrisQuoteViewHolder(
    val binding: ItemPickUpPointBinding,
) : RecyclerView.ViewHolder(binding.root) {


   // private lateinit var ui: String

    init {
       /* binding.root.setOnClickListener {
            onItemClick(ui, itemView,)
        }
        */

    }


    fun bind(name: String) {
        var text = FonctionHelper().helpterText(name)
        binding.pickupPointAddress.text = text

    }
}
class MyFoodAdapter() : ListAdapter<String, ChuckNorrisQuoteViewHolder>(diffUtils) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChuckNorrisQuoteViewHolder {
        return ChuckNorrisQuoteViewHolder(
            ItemPickUpPointBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ChuckNorrisQuoteViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


}
