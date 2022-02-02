package xyz.savvamirzoyan.middleearth.data.repository

import android.util.Log
import xyz.savvamirzoyan.middleearth.data.data.BookData
import xyz.savvamirzoyan.middleearth.data.mapper.BooksCloudToDataMapper
import xyz.savvamirzoyan.middleearth.data.source.BooksCloudDataSource

interface BooksRepository {

    suspend fun getBooks(): List<BookData>

    class Base(
        private val booksCloudDataSource: BooksCloudDataSource,
        private val booksCloudToDataMapper: BooksCloudToDataMapper
    ) : BooksRepository {

        override suspend fun getBooks(): List<BookData> {
            try {
                val rawBooksCloud = booksCloudDataSource.getBooks().books
                return booksCloudToDataMapper.map(rawBooksCloud)
            } catch (e: Exception) {
                Log.d("ERROR", "Exception: $e")
                throw booksCloudToDataMapper.map(e)
            }
        }
    }
}