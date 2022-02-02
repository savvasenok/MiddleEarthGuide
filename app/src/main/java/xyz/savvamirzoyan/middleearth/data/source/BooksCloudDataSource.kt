package xyz.savvamirzoyan.middleearth.data.source

import retrofit2.http.GET
import xyz.savvamirzoyan.middleearth.data.data.BooksCloudRaw

interface BooksCloudDataSource {

    @GET("book")
    suspend fun getBooks(): BooksCloudRaw
}