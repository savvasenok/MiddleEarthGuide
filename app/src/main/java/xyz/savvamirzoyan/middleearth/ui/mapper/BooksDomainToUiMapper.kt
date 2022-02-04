package xyz.savvamirzoyan.middleearth.ui.mapper

import xyz.savvamirzoyan.middleearth.R
import xyz.savvamirzoyan.middleearth.core.Mapper
import xyz.savvamirzoyan.middleearth.domain.data.BookDomain
import xyz.savvamirzoyan.middleearth.domain.error.ErrorDomain
import xyz.savvamirzoyan.middleearth.ui.ResourceManager
import xyz.savvamirzoyan.middleearth.ui.data.BookUi

interface BooksDomainToUiMapper : Mapper<BookDomain, BookUi> {

    fun map(error: ErrorDomain): BookUi

    class Base(private val resourceManager: ResourceManager) : BooksDomainToUiMapper {
        override fun map(model: BookDomain): BookUi = BookUi.Book(
            model.id,
            model.title,
            resourceManager.getString(R.string.chapters, model.chaptersCount)
        )

        override fun map(error: ErrorDomain): BookUi = when (error) {
            is ErrorDomain.Network -> BookUi.Error(resourceManager.getString(R.string.error_network))
            is ErrorDomain.Other -> BookUi.Error(resourceManager.getString(R.string.generic_error))
        }

        override fun map(models: List<BookDomain>): List<BookUi> = models.map { map(it) }
    }
}
