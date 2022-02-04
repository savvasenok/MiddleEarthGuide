package xyz.savvamirzoyan.middleearth.data.mapper

import xyz.savvamirzoyan.middleearth.core.Mapper
import xyz.savvamirzoyan.middleearth.data.data.BookData
import xyz.savvamirzoyan.middleearth.data.data.BooksAndChaptersCountCloud
import xyz.savvamirzoyan.middleearth.data.error.ErrorData
import java.net.UnknownHostException

interface BooksAndChaptersCountCloudToDataMapper : Mapper<BooksAndChaptersCountCloud, BookData> {

    fun map(exception: Exception): ErrorData

    class Base : BooksAndChaptersCountCloudToDataMapper {

        override fun map(model: BooksAndChaptersCountCloud): BookData =
            BookData(model.book.id, model.book.name, model.chaptersCount)

        override fun map(models: List<BooksAndChaptersCountCloud>): List<BookData> =
            models.map { map(it) }

        override fun map(exception: Exception) = when (exception) {
            is UnknownHostException -> ErrorData.ApiError()
            else -> {
                ErrorData.OtherError()
            }
        }
    }
}