package com.capou.application.comments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capou.application.databinding.ItemCommentLayoutBinding
import com.capou.application.model.CommentModel

val diffUtils = object : DiffUtil.ItemCallback<CommentModel>() {
    override fun areItemsTheSame(oldItem: CommentModel, newItem: CommentModel): Boolean {
        return oldItem == newItem
    }


    override fun areContentsTheSame(oldItem: CommentModel, newItem: CommentModel): Boolean {
        return oldItem == newItem
    }


}

class ChuckNorrisQuoteViewHolder(
    val binding: ItemCommentLayoutBinding,
    onItemClick: (objectDataSample: CommentModel, view: View, type:String) -> Unit
) : RecyclerView.ViewHolder(binding.root) {


    private lateinit var ui: CommentModel

    init {
        binding.root.setOnClickListener {
            onItemClick(ui, itemView,"display")
        }

    }


    fun bind(chuckNorrisUi: CommentModel) {
        ui = chuckNorrisUi

        binding.auteur.text = ui.auteur
        binding.message.text = ui.message
       /* Glide.with(itemView.context)
            .load(chuckNorrisUi.logo)
            .into(binding.itemChuckNorrisIcon)


        binding.itemChuckNorrisQuote.text = "Nom: "+chuckNorrisUi.name
        binding.type.text = "Type: "+chuckNorrisUi.type
        binding.address.text = chuckNorrisUi.address
        binding.description.text = chuckNorrisUi.description*/
    }
}

class ChuckNorrisAdapter(private val onItemClick: (quoteUi: CommentModel, view: View,type:String) -> Unit) : ListAdapter<CommentModel, ChuckNorrisQuoteViewHolder>(diffUtils) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChuckNorrisQuoteViewHolder {
        return ChuckNorrisQuoteViewHolder(
            ItemCommentLayoutBinding.inflate(
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



