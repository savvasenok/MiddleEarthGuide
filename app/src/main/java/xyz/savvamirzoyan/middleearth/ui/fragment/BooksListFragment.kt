package xyz.savvamirzoyan.middleearth.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.serialization.ExperimentalSerializationApi
import xyz.savvamirzoyan.middleearth.core.Clicker
import xyz.savvamirzoyan.middleearth.core.Retry
import xyz.savvamirzoyan.middleearth.databinding.FragmentBooksListBinding
import xyz.savvamirzoyan.middleearth.ui.App
import xyz.savvamirzoyan.middleearth.ui.activity.MainActivity
import xyz.savvamirzoyan.middleearth.ui.data.BookUi
import xyz.savvamirzoyan.middleearth.ui.diffcallback.BookUiDiffCallback
import xyz.savvamirzoyan.middleearth.ui.recyclerview.BooksRecyclerViewAdapter
import xyz.savvamirzoyan.middleearth.ui.viewmodel.BooksListViewModel

@ExperimentalSerializationApi
class BooksListFragment : Fragment() {

    private lateinit var binding: FragmentBooksListBinding
    private lateinit var viewModel: BooksListViewModel
    private lateinit var booksAdapter: BooksRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentBooksListBinding.inflate(inflater, container, false)
        viewModel = ((activity as MainActivity).application as App).booksListVieModel


        booksAdapter = BooksRecyclerViewAdapter(
            object : Clicker<BookUi.Book> {
                override fun onClick(item: BookUi.Book) {
                    viewModel.onBookClick(item)
                }
            },
            object : Retry {
                override fun onRetry() {
                    viewModel.retry()
                }
            },
            BookUiDiffCallback()
        )

        binding.recyclerViewBooks.adapter = booksAdapter
        binding.recyclerViewBooks.layoutManager = LinearLayoutManager(context)

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                booksListStatusListener()
            }
        }

        return binding.root
    }

    private suspend fun booksListStatusListener() {
        viewModel.booksListFlow.collect {
            booksAdapter.update(it)
        }
    }
}