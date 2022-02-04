package xyz.savvamirzoyan.middleearth.data.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import xyz.savvamirzoyan.middleearth.core.Model

@Serializable
data class ChaptersCloudRaw(
    @SerialName("docs") val chapters: List<ChapterCloud>,
    val total: Int
) : Model.Cloud

@Serializable
data class ChapterCloud(
    @SerialName("_id") val id: String,
    @SerialName("chapterName") val title: String
) : Model.Cloud
