package com.example.newsapp.view

//import com.example.newsapp.viewModel.NewsViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.adapters.NewsAdapter
import com.example.newsapp.databinding.FragmentFavoriteNewsBinding
import com.example.newsapp.viewModel.NewsViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteNewsFragment : Fragment() {

    private var _binding: FragmentFavoriteNewsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: NewsViewModel by viewModels()
    private lateinit var newsAdapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentFavoriteNewsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()
        setOnItemClickListener()
        getAllArticleFromRoom()
        itemCallback()

    }

    private fun setOnItemClickListener() {

        newsAdapter.setOnItemClickListener {article ->

            findNavController().navigate(FavoriteNewsFragmentDirections.actionFavoriteToDetails(article))

        }

    }

    private fun setUpRecyclerView() {

        newsAdapter = NewsAdapter()

        binding.rvSavedNews.apply {
            adapter = newsAdapter
        }
    }

    private fun getAllArticleFromRoom() {

        viewModel.getSavedArticle().observe(viewLifecycleOwner, Observer {

            newsAdapter.differ.submitList(it)

        })

    }

    private fun itemCallback() {

        val itemTouchHelperCallBack = object : ItemTouchHelper.SimpleCallback(

            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT

        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {

                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                val position = viewHolder.adapterPosition
                val article = newsAdapter.differ.currentList[position]
                viewModel.deleteArticle(article)
                Snackbar.make(requireView(), "Successfully deleted article", Snackbar.LENGTH_SHORT)
                    .apply {

                        setAction("undo") {

                            viewModel.insertArticle(article)

                        }

                        show()

                    }
            }

        }

        ItemTouchHelper(itemTouchHelperCallBack).apply {
            attachToRecyclerView(binding.rvSavedNews)
        }
    }
}