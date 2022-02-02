package xyz.savvamirzoyan.middleearth.ui.data

import xyz.savvamirzoyan.middleearth.core.Model

sealed class BookUi : Model.Ui {
    data class Book(val id: String, val title: String) : BookUi()
    data class Error(val error: String) : BookUi()
    object Loading : BookUi()
}