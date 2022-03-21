package com.capou.application.ui.pickup_point.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.capou.application.databinding.ItemPickUpPointBinding
import com.capou.application.model.PickUpPoint

val diffUtils = object : DiffUtil.ItemCallback<PickUpPoint>() {
    override fun areItemsTheSame(oldItem: PickUpPoint, newItem: PickUpPoint): Boolean {
        return oldItem == newItem
    }


    override fun areContentsTheSame(oldItem: PickUpPoint, newItem: PickUpPoint): Boolean {
        return oldItem == newItem
    }


}

class ChuckNorrisQuoteViewHolder(
    val binding: ItemPickUpPointBinding,
    onItemClick: (objectDataSample: PickUpPoint, view: View) -> Unit
) : RecyclerView.ViewHolder(binding.root) {


    private lateinit var ui: PickUpPoint

    init {
        binding.root.setOnClickListener {
            onItemClick(ui, itemView,)
        }

    }


    fun bind(chuckNorrisUi: PickUpPoint) {
        ui = chuckNorrisUi

        binding.pickupPointAddress.text = ui.address
        /* Glide.with(itemView.context)
             .load(chuckNorrisUi.logo)
             .into(binding.itemChuckNorrisIcon)


         binding.itemChuckNorrisQuote.text = "Nom: "+chuckNorrisUi.name
         binding.type.text = "Type: "+chuckNorrisUi.type
         binding.address.text = chuckNorrisUi.address
         binding.description.text = chuckNorrisUi.description*/
    }
}
class PickUpPointAdapter (private val onItemClick: (quoteUi: PickUpPoint, view: View) -> Unit) : ListAdapter<PickUpPoint, ChuckNorrisQuoteViewHolder>(diffUtils) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChuckNorrisQuoteViewHolder {
        return ChuckNorrisQuoteViewHolder(
            ItemPickUpPointBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onItemClick
        )
    }


    override fun onBindViewHolder(holder: ChuckNorrisQuoteViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}



