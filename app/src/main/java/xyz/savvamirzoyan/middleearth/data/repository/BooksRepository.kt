package xyz.savvamirzoyan.middleearth.data.repository

import xyz.savvamirzoyan.middleearth.data.data.BookCloud
import xyz.savvamirzoyan.middleearth.data.data.BookData
import xyz.savvamirzoyan.middleearth.data.data.BooksAndChaptersCountCloud
import xyz.savvamirzoyan.middleearth.data.data.ChaptersCloudRaw
import xyz.savvamirzoyan.middleearth.data.mapper.BooksAndChaptersCountCloudToDataMapper
import xyz.savvamirzoyan.middleearth.data.source.BooksCloudDataSource

interface BooksRepository {

    suspend fun getBooks(): List<BookData>
    fun combineBooksAndChaptersCounts(
        books: List<BookCloud>,
        chapters: List<ChaptersCloudRaw>
    ): List<BooksAndChaptersCountCloud>

    class Base(
        private val booksCloudDataSource: BooksCloudDataSource,
        private val booksAndChaptersCountCloudToDataMapper: BooksAndChaptersCountCloudToDataMapper
    ) : BooksRepository {

        override suspend fun getBooks(): List<BookData> {
            try {
                val rawBooksCloud = booksCloudDataSource.getBooks().books
                val rawChaptersCloud =
                    rawBooksCloud.map { booksCloudDataSource.getBookChapters(it.id) }
                val rawBooksAndChaptersCountCloud =
                    combineBooksAndChaptersCounts(rawBooksCloud, rawChaptersCloud)
                return booksAndChaptersCountCloudToDataMapper.map(rawBooksAndChaptersCountCloud)
            } catch (e: Exception) {
                throw booksAndChaptersCountCloudToDataMapper.map(e)
            }
        }

        override fun combineBooksAndChaptersCounts(
            books: List<BookCloud>,
            chapters: List<ChaptersCloudRaw>
        ): List<BooksAndChaptersCountCloud> {
            if (books.size == chapters.size) {
                val result = mutableListOf<BooksAndChaptersCountCloud>()
                for (i in books.indices) {
                    result.add(BooksAndChaptersCountCloud(books[i], chapters[i].total))
                }

                return result
            }

            return emptyList()
        }
    }
}