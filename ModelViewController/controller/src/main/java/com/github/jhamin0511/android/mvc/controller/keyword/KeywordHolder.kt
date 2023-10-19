package com.github.jhamin0511.android.mvc.controller.keyword

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.github.jhamin0511.android.mvc.view.databinding.ItemKeywordBinding
import com.github.jhamin0511android.core.model.Keyword

class KeywordHolder(
    private val binding: ItemKeywordBinding,
    private val listener: (Keyword) -> Unit
) : ViewHolder(binding.root) {
    private lateinit var item: Keyword

    init {
        binding.root.setOnClickListener {
            listener(item)
        }
    }

    fun bind(item: Keyword) {
        this.item = item
        binding.tvTitle.text = item.title
        binding.tvAuthor.text = item.author
    }
}
