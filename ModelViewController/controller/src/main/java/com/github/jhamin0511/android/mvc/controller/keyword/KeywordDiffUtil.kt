package com.github.jhamin0511.android.mvc.controller.keyword

import androidx.recyclerview.widget.DiffUtil
import com.github.jhamin0511android.core.model.Keyword

class KeywordDiffUtil : DiffUtil.ItemCallback<Keyword>() {
    override fun areItemsTheSame(oldItem: Keyword, newItem: Keyword): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Keyword, newItem: Keyword): Boolean {
        return oldItem == newItem
    }
}
