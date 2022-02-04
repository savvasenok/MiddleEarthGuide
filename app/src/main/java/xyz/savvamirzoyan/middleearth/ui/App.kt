package xyz.savvamirzoyan.middleearth.ui

import android.app.Application
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import xyz.savvamirzoyan.middleearth.data.mapper.BooksAndChaptersCountCloudToDataMapper
import xyz.savvamirzoyan.middleearth.data.repository.BooksRepository
import xyz.savvamirzoyan.middleearth.data.source.BooksCloudDataSource
import xyz.savvamirzoyan.middleearth.domain.interactor.BooksListInteractor
import xyz.savvamirzoyan.middleearth.domain.mapper.BooksDataToDomainMapper
import xyz.savvamirzoyan.middleearth.ui.mapper.BooksDomainToUiMapper
import xyz.savvamirzoyan.middleearth.ui.viewmodel.BooksListViewModel

private const val BASE_URL = "https://the-one-api.dev/v2/"

@ExperimentalSerializationApi
class App : Application() {

    private val json = Json {
        ignoreUnknownKeys = true
    }

    lateinit var booksListVieModel: BooksListViewModel
        private set

    override fun onCreate() {
        super.onCreate()

        val client = OkHttpClient.Builder()
            .build()
        val contentType = "application/json".toMediaType()
        val converterFactory = json.asConverterFactory(contentType)
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(converterFactory)
            .build()

        // Other
        val resourceManager = ResourceManager.Base(applicationContext)

        // Mapper
        val booksCloudToDataMapper = BooksAndChaptersCountCloudToDataMapper.Base()
        val booksDataToDomainMapper = BooksDataToDomainMapper.Base()
        val booksDomainToUiMapper = BooksDomainToUiMapper.Base(resourceManager)

        // Source
        val booksCloudService = retrofit.create(BooksCloudDataSource::class.java)

        // Repository
        val booksRepository = BooksRepository.Base(booksCloudService, booksCloudToDataMapper)

        // Interactor
        val booksListInteractor = BooksListInteractor.Base(booksRepository, booksDataToDomainMapper)

        // ViewModel
        booksListVieModel = BooksListViewModel.Base(booksListInteractor, booksDomainToUiMapper)
    }
}