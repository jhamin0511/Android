package com.github.jhamin0511.android.mvc.controller.list

import androidx.recyclerview.widget.DiffUtil
import com.github.jhamin0511android.core.model.Book

class BookDiffUtil : DiffUtil.ItemCallback<Book>() {
    override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
        return oldItem.isbn == newItem.isbn
    }

    override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
        return oldItem == newItem
    }
}
