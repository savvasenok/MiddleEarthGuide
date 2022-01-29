package xyz.savvamirzoyan.middleearth.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import xyz.savvamirzoyan.middleearth.core.Clicker
import xyz.savvamirzoyan.middleearth.databinding.FragmentBooksListBinding
import xyz.savvamirzoyan.middleearth.ui.data.BookUi
import xyz.savvamirzoyan.middleearth.ui.recyclerview.BooksRecyclerViewAdapter

class BooksListFragment : Fragment() {

    private lateinit var binding: FragmentBooksListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentBooksListBinding.inflate(inflater, container, false)

        val adapter = BooksRecyclerViewAdapter(
            object : Clicker<BookUi.Book> {
                override fun onClick(item: BookUi.Book) {
                    Toast.makeText(context, item.title, Toast.LENGTH_LONG).show()
                }
            }
        )

        binding.recyclerViewBooks.adapter = adapter
        binding.recyclerViewBooks.layoutManager = LinearLayoutManager(context)

        return binding.root
    }
}