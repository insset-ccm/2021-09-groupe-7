package com.capou.application.ui.my_food.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.capou.application.databinding.ItemPickUpPointBinding
import com.capou.application.helper.FonctionHelper
import com.capou.application.model.AlimentModel
import com.capou.application.model.AlimentPointVentes


val diffUtils = object : DiffUtil.ItemCallback<AlimentPointVentes>() {
    override fun areItemsTheSame(oldItem: AlimentPointVentes, newItem: AlimentPointVentes): Boolean {
        return oldItem == newItem
    }


    override fun areContentsTheSame(oldItem: AlimentPointVentes, newItem: AlimentPointVentes): Boolean {
        return oldItem == newItem
    }


}

class ChuckNorrisQuoteViewHolder(
    val binding: ItemPickUpPointBinding,
    onItemClick: (objectDataSample: AlimentPointVentes, view: View) -> Unit
) : RecyclerView.ViewHolder(binding.root) {


   private lateinit var ui: AlimentPointVentes

    init {
       binding.root.setOnClickListener {
            onItemClick(ui, itemView)
        }

    }


    fun bind(alimentPointVentes: AlimentPointVentes) {
        ui = alimentPointVentes
        val text = ui.name?.let { FonctionHelper().helpterText(it) }
        binding.pickupPointAddress.text = text

    }
}
class MyFoodAdapter(private val onItemClick: (quoteUi: AlimentPointVentes, view: View) -> Unit) : ListAdapter<AlimentPointVentes, ChuckNorrisQuoteViewHolder>(diffUtils) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChuckNorrisQuoteViewHolder {
        return ChuckNorrisQuoteViewHolder(
            ItemPickUpPointBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),onItemClick
        )
    }

    override fun onBindViewHolder(holder: ChuckNorrisQuoteViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


}
