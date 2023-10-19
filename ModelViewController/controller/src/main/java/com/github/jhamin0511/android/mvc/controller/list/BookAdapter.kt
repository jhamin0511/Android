package com.github.jhamin0511.android.mvc.controller.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.github.jhamin0511.android.mvc.view.databinding.ItemBookBinding
import com.github.jhamin0511android.core.model.Book

class BookAdapter : ListAdapter<Book, BookHolder>(BookDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemBookBinding.inflate(inflater)

        return BookHolder(binding)
    }

    override fun onBindViewHolder(holder: BookHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}
