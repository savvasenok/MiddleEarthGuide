package xyz.savvamirzoyan.middleearth.domain.data

import xyz.savvamirzoyan.middleearth.core.Model

data class BookDomain(
    val id: String,
    val title: String,
    val chaptersCount: Int
) : Model.Domain