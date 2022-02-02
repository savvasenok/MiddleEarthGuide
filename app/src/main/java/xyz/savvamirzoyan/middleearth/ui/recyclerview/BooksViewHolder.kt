package xyz.savvamirzoyan.middleearth.ui.recyclerview

import android.view.View
import xyz.savvamirzoyan.middleearth.core.Clicker
import xyz.savvamirzoyan.middleearth.core.Retry
import xyz.savvamirzoyan.middleearth.databinding.ViewHolderBookBinding
import xyz.savvamirzoyan.middleearth.databinding.ViewHolderBookErrorBinding
import xyz.savvamirzoyan.middleearth.ui.core.CoreViewHolder
import xyz.savvamirzoyan.middleearth.ui.data.BookUi

sealed class BooksViewHolder(view: View) : CoreViewHolder<BookUi>(view) {

    override fun bind(item: BookUi) {
        when (item) {
            is BookUi.Book -> bind(item)
            is BookUi.Error -> bind(item)
            is BookUi.Loading -> {
            }
        }
    }

    open fun bind(item: BookUi.Book) {}
    open fun bind(item: BookUi.Error) {}

    class Book(
        private val clicker: Clicker<BookUi.Book>,
        view: View
    ) : BooksViewHolder(view) {
        private val binding = ViewHolderBookBinding.bind(view)

        override fun bind(item: BookUi.Book) {
            binding.root.setOnClickListener { clicker.onClick(item) }
            binding.textViewBookTitle.text = item.title
        }
    }

    class Error(
        private val retryListener: Retry,
        view: View
    ) : BooksViewHolder(view) {
        private val binding = ViewHolderBookErrorBinding.bind(view)

        override fun bind(item: BookUi.Error) {
            binding.textViewBookError.text = item.error
            binding.buttonRetry.setOnClickListener { retryListener.onRetry() }
        }
    }

    class Loading(view: View) : BooksViewHolder(view)
}