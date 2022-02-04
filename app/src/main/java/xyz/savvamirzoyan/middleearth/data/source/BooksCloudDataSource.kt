package xyz.savvamirzoyan.middleearth.data.source

import retrofit2.http.GET
import retrofit2.http.Path
import xyz.savvamirzoyan.middleearth.data.data.BooksCloudRaw
import xyz.savvamirzoyan.middleearth.data.data.ChaptersCloudRaw

interface BooksCloudDataSource {

    @GET("book")
    suspend fun getBooks(): BooksCloudRaw

    @GET("book/{bookId}/chapter")
    suspend fun getBookChapters(@Path("bookId") bookId: String): ChaptersCloudRaw
}