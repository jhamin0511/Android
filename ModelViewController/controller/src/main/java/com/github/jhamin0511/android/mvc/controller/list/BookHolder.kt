package com.github.jhamin0511.android.mvc.controller.list

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.github.jhamin0511.android.mvc.view.databinding.ItemBookBinding
import com.github.jhamin0511android.core.model.Book

class BookHolder(
    private val binding: ItemBookBinding
) : ViewHolder(binding.root) {

    fun bind(item: Book) {
        Glide.with(binding.ivBook)
            .load(item.thumbnail)
            .into(binding.ivBook)
        binding.tvSource.text = item.source
        binding.tvTitle.text = item.title
        binding.tvPrice.text = item.price
        binding.tvAuthor.text = item.author
        binding.tvTranslator.text = item.translator
        binding.tvPublisher.text = item.publisher
        binding.tvDescription.text = item.description
    }
}
