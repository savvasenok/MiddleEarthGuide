package xyz.savvamirzoyan.middleearth.domain.mapper

import xyz.savvamirzoyan.middleearth.core.Mapper
import xyz.savvamirzoyan.middleearth.data.data.BookData
import xyz.savvamirzoyan.middleearth.data.error.ErrorData
import xyz.savvamirzoyan.middleearth.domain.data.BookDomain
import xyz.savvamirzoyan.middleearth.domain.error.ErrorDomain

interface BooksDataToDomainMapper : Mapper<BookData, BookDomain> {

    fun map(e: ErrorData): ErrorDomain

    class Base : BooksDataToDomainMapper {
        override fun map(model: BookData): BookDomain = BookDomain(model.id, model.title)
        override fun map(models: List<BookData>) = models.map { map(it) }
        override fun map(e: ErrorData): ErrorDomain = when (e) {
            is ErrorData.ApiError -> ErrorDomain.Network()
            is ErrorData.OtherError -> ErrorDomain.Other()
        }
    }
}