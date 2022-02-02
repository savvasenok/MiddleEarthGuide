package xyz.savvamirzoyan.middleearth.data.mapper

import android.util.Log
import xyz.savvamirzoyan.middleearth.core.Mapper
import xyz.savvamirzoyan.middleearth.data.data.BookCloud
import xyz.savvamirzoyan.middleearth.data.data.BookData
import xyz.savvamirzoyan.middleearth.data.error.ErrorData
import java.net.UnknownHostException

interface BooksCloudToDataMapper : Mapper<BookCloud, BookData> {

    fun map(exception: Exception): ErrorData

    class Base : BooksCloudToDataMapper {

        override fun map(model: BookCloud): BookData = BookData(model.id, model.name)

        override fun map(models: List<BookCloud>): List<BookData> = models.map { map(it) }

        override fun map(exception: Exception) = when (exception) {
            is UnknownHostException -> ErrorData.ApiError()
            else -> {
                Log.d("SPAMEGGS", "error: $exception")
                ErrorData.OtherError()
            }
        }
    }
}