package xyz.savvamirzoyan.middleearth.data.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.savvamirzoyan.middleearth.core.Model

@Serializable
data class BooksCloudRaw(
    @SerialName("docs") val books: List<BookCloud>
)

@Serializable
data class BookCloud(
    @SerialName("_id") val id: String,
    val name: String
) : Model.Cloud