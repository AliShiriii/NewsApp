package com.example.newsapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.adapters.NewsAdapter
import com.example.newsapp.databinding.FragmentNewsBreackingBinding
import com.example.newsapp.utils.Constants.Companion.QUERY_PAGE_SIZE
import com.example.newsapp.utils.Resource
import com.example.newsapp.viewModel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BreakingNewsFragment : Fragment() {

    private var _binding: FragmentNewsBreackingBinding? = null
    private val binding get() = _binding!!
    private val viewModel: NewsViewModel by viewModels()
    private lateinit var newsAdapter: NewsAdapter

    var isLoading = false
    var isLastPage = false
    var isScrolling = false

    var TAG = "BreakingNewsFragment"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentNewsBreackingBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()
        getData()
        setOnItemClickListener()

    }

    private fun setOnItemClickListener() {

        newsAdapter.setOnItemClickListener { article ->

            findNavController().navigate(BreakingNewsFragmentDirections.actionNewsToArticleNewsFragment(article))

        }

    }

    private fun setUpRecyclerView() {

        newsAdapter = NewsAdapter()

        binding.breakingRecyclerView.apply {
            adapter = newsAdapter

            addOnScrollListener(this@BreakingNewsFragment.scrollListener)
        }
    }

    private fun getData() {

        viewModel.breakingNews.observe(viewLifecycleOwner, Observer { response ->

            when (response) {

                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { newsResponse ->

                        newsAdapter.differ.submitList(newsResponse.articles.toList())

                        val totalPages = newsResponse.totalResults / QUERY_PAGE_SIZE + 2
                        isLastPage = viewModel.breakingNewsPage == totalPages

                        if (isLastPage){

                                binding.breakingRecyclerView.setPadding(0, 0, 0, 0)

                        }
                    }

                }

                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->

                        Toast.makeText(
                            requireContext(),
                            "An error ocoured $message",
                            Toast.LENGTH_LONG
                        ).show()

                    }
                }

                is Resource.Loading -> {

                    showProgressBar()

                }
            }

        })

    }


    val scrollListener = object : RecyclerView.OnScrollListener() {

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
            val visibleItemCont = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount

            val isNotLoadingAndNotLastPage = !isLoading && !isLastPage
            val isAtLastItem = firstVisibleItemPosition + visibleItemCont >= totalItemCount
            val isNotAtBeginning = firstVisibleItemPosition >= 0
            val isTotalMoreThanVisible = totalItemCount >= QUERY_PAGE_SIZE
            val shouldPaginate = isNotLoadingAndNotLastPage && isAtLastItem
                    && isNotAtBeginning && isTotalMoreThanVisible && isScrolling

            if (shouldPaginate) {

                viewModel.getBreakingNews("us")
                isScrolling = false

            }

        }

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)

            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {

                isScrolling = true

            }
        }

    }

    private fun hideProgressBar() {

        binding.newsProgressBar.visibility = View.GONE
        isLoading = false

    }

    private fun showProgressBar() {

        binding.newsProgressBar.visibility = View.VISIBLE
        isLoading = true

    }
}