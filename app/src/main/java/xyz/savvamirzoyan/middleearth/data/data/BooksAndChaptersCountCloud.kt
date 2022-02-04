package xyz.savvamirzoyan.middleearth.data.data

import xyz.savvamirzoyan.middleearth.core.Model

data class BooksAndChaptersCountCloud(
    val book: BookCloud,
    val chaptersCount: Int
) : Model.Cloud
