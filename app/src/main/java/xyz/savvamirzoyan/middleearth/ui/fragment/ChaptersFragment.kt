package xyz.savvamirzoyan.middleearth.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import xyz.savvamirzoyan.middleearth.core.Retry
import xyz.savvamirzoyan.middleearth.databinding.FragmentChaptersBinding
import xyz.savvamirzoyan.middleearth.ui.diffcallback.ChapterUiDiffCallback
import xyz.savvamirzoyan.middleearth.ui.recyclerview.ChaptersRecyclerViewAdapter

class ChaptersFragment : Fragment() {

    private lateinit var binding: FragmentChaptersBinding
    private lateinit var viewModel: Unit//TODO()
    private lateinit var chaptersAdapter: ChaptersRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentChaptersBinding.inflate(inflater, container, false)

        chaptersAdapter = ChaptersRecyclerViewAdapter(
            object : Retry {
                override fun onRetry() {}
            },
            ChapterUiDiffCallback()
        )

        binding.root.adapter = chaptersAdapter
        binding.root.layoutManager = LinearLayoutManager(context)

        return binding.root
    }
}