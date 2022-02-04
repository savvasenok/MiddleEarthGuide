package xyz.savvamirzoyan.middleearth.ui.recyclerview

import android.view.View
import xyz.savvamirzoyan.middleearth.core.Retry
import xyz.savvamirzoyan.middleearth.databinding.ViewHolderChapterBinding
import xyz.savvamirzoyan.middleearth.databinding.ViewHolderErrorBinding
import xyz.savvamirzoyan.middleearth.ui.core.CoreViewHolder
import xyz.savvamirzoyan.middleearth.ui.data.ChapterUi

sealed class ChaptersViewHolder(view: View) : CoreViewHolder<ChapterUi>(view) {
    override fun bind(item: ChapterUi) = when (item) {
        is ChapterUi.Chapter -> bind(item)
        is ChapterUi.Error -> bind(item)
        ChapterUi.Loading -> {}
    }

    open fun bind(item: ChapterUi.Chapter) {}
    open fun bind(item: ChapterUi.Error) {}

    class Chapter(view: View) : ChaptersViewHolder(view) {
        private val binding = ViewHolderChapterBinding.bind(view)

        override fun bind(item: ChapterUi.Chapter) {
            binding.textViewChapterTitle.text = item.title
        }
    }

    class Error(
        private val retryListener: Retry,
        view: View
    ) : ChaptersViewHolder(view) {
        private val binding = ViewHolderErrorBinding.bind(view)

        override fun bind(item: ChapterUi.Error) {
            binding.buttonRetry.setOnClickListener {
                retryListener.onRetry()
            }
        }
    }

    class Loading(view: View) : ChaptersViewHolder(view)
}