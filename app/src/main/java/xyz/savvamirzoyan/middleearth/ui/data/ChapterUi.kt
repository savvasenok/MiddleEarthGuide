package xyz.savvamirzoyan.middleearth.ui.data

import xyz.savvamirzoyan.middleearth.core.Model

sealed class ChapterUi : Model.Ui {
    data class Chapter(val id: String, val title: String) : ChapterUi()
    data class Error(val error: String) : ChapterUi()
    object Loading : ChapterUi()
}
