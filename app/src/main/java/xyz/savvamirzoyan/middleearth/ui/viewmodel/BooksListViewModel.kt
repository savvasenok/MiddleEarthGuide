package xyz.savvamirzoyan.middleearth.ui.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import xyz.savvamirzoyan.middleearth.ui.data.BookUi

interface BooksListViewModel {

    val booksListFlow: StateFlow<List<BookUi>>

    class Base : BooksListViewModel, ViewModel() {

        private val _booksListStatusFlow = MutableStateFlow<List<BookUi>>(listOf(BookUi.Loading))
        override val booksListFlow: StateFlow<List<BookUi>> = _booksListStatusFlow
    }
}