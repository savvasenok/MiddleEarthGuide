package xyz.savvamirzoyan.middleearth.ui.data

sealed class BookUi {
    data class Book(val title: String) : BookUi()
    data class Error(val errorText: String) : BookUi()
}