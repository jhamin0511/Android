package com.github.jhamin0511.android.mvc.controller.keyword

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.github.jhamin0511.android.mvc.view.databinding.ItemKeywordBinding
import com.github.jhamin0511android.core.model.Keyword

class KeywordAdapter(
    private val listener: (Keyword) -> Unit
) : ListAdapter<Keyword, KeywordHolder>(KeywordDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KeywordHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemKeywordBinding.inflate(inflater, parent, false)

        return KeywordHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: KeywordHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}
