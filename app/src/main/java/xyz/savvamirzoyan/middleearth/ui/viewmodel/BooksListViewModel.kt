package xyz.savvamirzoyan.middleearth.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import xyz.savvamirzoyan.middleearth.domain.error.ErrorDomain
import xyz.savvamirzoyan.middleearth.domain.interactor.BooksListInteractor
import xyz.savvamirzoyan.middleearth.ui.data.BookUi
import xyz.savvamirzoyan.middleearth.ui.mapper.BooksDomainToUiMapper

interface BooksListViewModel {

    val booksListFlow: StateFlow<List<BookUi>>
    val openChaptersFlow: SharedFlow<String>

    fun onBookClick(book: BookUi.Book)
    fun retry()

    class Base(
        private val booksListInteractor: BooksListInteractor,
        private val booksDomainToUiMapper: BooksDomainToUiMapper
    ) : BooksListViewModel, ViewModel() {

        private val _booksListStatusFlow = MutableStateFlow<List<BookUi>>(listOf())
        override val booksListFlow: StateFlow<List<BookUi>> = _booksListStatusFlow

        private val _openChaptersFlow = MutableSharedFlow<String>()
        override val openChaptersFlow: SharedFlow<String> = _openChaptersFlow

        init {
            retry()
        }

        override fun onBookClick(book: BookUi.Book) {
            viewModelScope.launch {
                _openChaptersFlow.emit(book.id)
            }
        }

        override fun retry() {
            viewModelScope.launch(Dispatchers.IO) {
                _booksListStatusFlow.emit(listOf(BookUi.Loading))

                try {
                    val booksUi = booksDomainToUiMapper.map(booksListInteractor.fetchBooks())
                    _booksListStatusFlow.emit(booksUi)
                } catch (e: ErrorDomain) {
                    val booksUi = listOf(booksDomainToUiMapper.map(e))
                    _booksListStatusFlow.emit(booksUi)
                }
            }
        }
    }
}