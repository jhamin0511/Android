package com.github.jhamin0511.android.mvc.controller.list

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.github.jhamin0511android.core.model.bookTestData
import com.github.jhamin0511.android.mvc.controller.keyword.KeywordAdapter
import com.github.jhamin0511.android.mvc.view.databinding.ActivityListBinding
import com.github.jhamin0511android.core.model.Keyword
import com.google.android.material.search.SearchView

class ListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListBinding
    private val keywordAdapter: KeywordAdapter by lazy {
        KeywordAdapter {
            binding.setKeyword(it.title)
        }
    }
    private val bookAdapter: BookAdapter by lazy { BookAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.setUpSearchView(this)
        keywordAdapter.submitList(
            listOf(
                Keyword("1", "Kotlin1", "Kotlin1"),
                Keyword("2", "Kotlin2", "Kotlin2")
            )
        )
        binding.rvKeywordList.adapter = keywordAdapter

        bookAdapter.submitList(bookTestData)
        binding.rvBookList.adapter = bookAdapter
    }
}

fun ActivityListBinding.setKeyword(keyword: String) {
    searchBar.setText(keyword)
    searchView.hide()
}

fun ActivityListBinding.setUpSearchView(activity: ListActivity) {
    searchView.editText.setOnEditorActionListener { _, _, _ ->
        false
    }
    val onBackPressedCallback = object : OnBackPressedCallback(false) {
        override fun handleOnBackPressed() {
            searchView.hide()
        }
    }
    activity.onBackPressedDispatcher.addCallback(activity, onBackPressedCallback)
    searchView.addTransitionListener { _, _, newState ->
        onBackPressedCallback.isEnabled = newState == SearchView.TransitionState.SHOWN
    }
}
