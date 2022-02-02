package xyz.savvamirzoyan.middleearth.domain.interactor

import xyz.savvamirzoyan.middleearth.data.error.ErrorData
import xyz.savvamirzoyan.middleearth.data.repository.BooksRepository
import xyz.savvamirzoyan.middleearth.domain.data.BookDomain
import xyz.savvamirzoyan.middleearth.domain.mapper.BooksDataToDomainMapper

interface BooksListInteractor {

    suspend fun fetchBooks(): List<BookDomain>

    class Base(
        private val booksRepository: BooksRepository,
        private val booksDataToDomainMapper: BooksDataToDomainMapper
    ) : BooksListInteractor {
        override suspend fun fetchBooks(): List<BookDomain> = try {
            booksDataToDomainMapper.map(booksRepository.getBooks())
        } catch (e: ErrorData) {
            throw booksDataToDomainMapper.map(e)
        }
    }
}