package com.github.jhamin0511.android.mvc.controller.list

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.github.jhamin0511.android.mvc.controller.keyword.KeywordAdapter
import com.github.jhamin0511.android.mvc.view.databinding.ActivityListBinding
import com.github.jhamin0511android.core.model.Book
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

        bookAdapter.submitList(
            listOf(
                Book(
                    "1",
                    "https://shopping-phinf.pstatic.net/main_3244145/32441454690.20221230074521.jpg",
                    "Kotlin IN ACTION",
                    "코틀린",
                    "번역자",
                    "34,500원",
                    "에어콘출판",
                    "201720",
                    "코틀린이 안드로이드 공식 언어가 되면서 관심이 커졌다. 이 책은 코틀린 언어를 개발한 젯브레인의 코틀린 컴파일러 개발자들이 직접 쓴 일종의 공식 서적이라 할 수 있다. 코틀린 언어의 가장 큰 특징이라면 실용성을 들 수 있을 것이다. 이 책에서도 실용성을 강조하는 입장에서 쓰였다. \\n\\n\\n\\n코틀린 기초를 소개하고, 고차함수, 객체지향, 제네릭스 등의 내용을 설명한다. 그리고 코틀린이 자바 언어를 어떻게 개선했고 기존 자바 프로젝트에서 코틀린을 함께 사용할 때 어떤 부분을 조심해야 할지를 중심으로 코틀린 언어를 설명한다. 후반에는 애노테이션과 리플렉션, DSL에 대해 실제 라이브러리 예제를 다루면서 설계 기법과 구현기법을 자세히 설명한다. 특히 이 주제는 이 책의 백미로, 다른 코틀린 책이나 공식 문서에서는 찾아보기 힘들며 실전에서 크게 도움이 받을 수 있다. \\n\\n\\n\\n한국어판 부록에서는 코틀린 버전 1.3.3까지의 변화를 정리한 내용과 코루틴, 코틀린/JS에 대한 간단한 소개를 덧붙였다",
                    "https://search.shopping.naver.com/book/catalog/32441454690"
                )
            )
        )
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
