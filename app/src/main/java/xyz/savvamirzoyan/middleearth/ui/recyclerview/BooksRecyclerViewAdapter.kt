package xyz.savvamirzoyan.middleearth.ui.recyclerview

import android.view.ViewGroup
import xyz.savvamirzoyan.middleearth.R
import xyz.savvamirzoyan.middleearth.core.Clicker
import xyz.savvamirzoyan.middleearth.ui.core.CoreRecyclerViewAdapter
import xyz.savvamirzoyan.middleearth.ui.data.BookUi

private const val TYPE_BOOK = 0
private const val TYPE_ERROR = 1

class BooksRecyclerViewAdapter(
    private val clickListener: Clicker<BookUi.Book>
) : CoreRecyclerViewAdapter<BookUi, BooksViewHolder>() {
    override fun getItemViewType(position: Int) = when (items[position]) {
        is BookUi.Book -> TYPE_BOOK
        is BookUi.Error -> TYPE_ERROR
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksViewHolder {
        return when (viewType) {
            0 -> BooksViewHolder.Book(clickListener, R.layout.view_holder_book.makeView(parent))
            else -> BooksViewHolder.Error(R.layout.view_holder_book_error.makeView(parent))
        }
    }
}