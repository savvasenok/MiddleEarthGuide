package xyz.savvamirzoyan.middleearth.ui.recyclerview

import android.view.ViewGroup
import xyz.savvamirzoyan.middleearth.R
import xyz.savvamirzoyan.middleearth.core.Retry
import xyz.savvamirzoyan.middleearth.ui.core.CoreDiffCallback
import xyz.savvamirzoyan.middleearth.ui.core.CoreRecyclerViewAdapter
import xyz.savvamirzoyan.middleearth.ui.data.ChapterUi

private const val TYPE_CHAPTER = 0
private const val TYPE_ERROR = 1
private const val TYPE_LOADING = 2

class ChaptersRecyclerViewAdapter(
    private val retryListener: Retry,
    diffCallback: CoreDiffCallback<ChapterUi>
) : CoreRecyclerViewAdapter<ChapterUi, ChaptersViewHolder>(diffCallback) {

    override fun getItemViewType(position: Int) = when (items[position]) {
        is ChapterUi.Chapter -> TYPE_CHAPTER
        is ChapterUi.Error -> TYPE_ERROR
        ChapterUi.Loading -> TYPE_LOADING
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        TYPE_CHAPTER -> ChaptersViewHolder.Chapter(R.layout.view_holder_chapter.makeView(parent))
        TYPE_ERROR -> ChaptersViewHolder.Error(
            retryListener,
            R.layout.view_holder_error.makeView(parent)
        )
        else -> ChaptersViewHolder.Loading(R.layout.view_holder_loading.makeView(parent))
    }
}