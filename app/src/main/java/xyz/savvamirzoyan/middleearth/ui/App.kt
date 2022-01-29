package xyz.savvamirzoyan.middleearth.ui

import android.app.Application
import xyz.savvamirzoyan.middleearth.ui.viewmodel.BooksListViewModel

class App : Application() {

    val booksListVieModel = BooksListViewModel.Base()

}