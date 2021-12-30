package com.example.newsapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.adapters.NewsAdapter
import com.example.newsapp.databinding.FragmentSearchNewsBinding
import com.example.newsapp.utils.Constants
import com.example.newsapp.utils.Constants.Companion.SEARCH_NEWS_THE_DELAY
import com.example.newsapp.utils.Resource
import com.example.newsapp.viewModel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchNewsFragment : Fragment() {

    private var _binding: FragmentSearchNewsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: NewsViewModel by viewModels()
    private lateinit var newsAdapter: NewsAdapter

    var isLoading = false
    var isLastPage = false
    var isScrolling = false

    var TAG = "SearchNewsFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentSearchNewsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()
        getData()
        searchItemInArticle()
        setOnItemClickListener()

    }

    private fun getData() {

        viewModel.searchNews.observe(viewLifecycleOwner, Observer { response ->

            when (response) {

                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { newsResponse ->

                        newsAdapter.differ.submitList(newsResponse.articles.toList())

                        val totalPages = newsResponse.totalResults / Constants.QUERY_PAGE_SIZE + 2
                        isLastPage = viewModel.breakingNewsPage == totalPages
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

    private fun searchItemInArticle() {

        var job: Job? = null

        binding.etSearch.addTextChangedListener { editable ->
            job?.cancel()

            job = MainScope().launch {
                delay(SEARCH_NEWS_THE_DELAY)
                editable?.let {

                    if (editable.toString().isNotEmpty()) {

                        viewModel.searchNews(editable.toString())
                    }

                }
            }

        }

    }

    private fun setOnItemClickListener() {

        newsAdapter.setOnItemClickListener { details ->

            findNavController().navigate(SearchNewsFragmentDirections.actionSearchToDetails(details))

        }

    }

    private fun setUpRecyclerView() {

        newsAdapter = NewsAdapter()

        binding.recyclerSearchNews.apply {
            adapter = newsAdapter

            addOnScrollListener(this@SearchNewsFragment.scrollListener)

        }
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
            val isTotalMoreThanVisible = totalItemCount >= Constants.QUERY_PAGE_SIZE
            val shouldPaginate = isNotLoadingAndNotLastPage && isAtLastItem
                    && isNotAtBeginning && isTotalMoreThanVisible && isScrolling

            if (shouldPaginate) {

                viewModel.searchNews(binding.etSearch.toString())
                isScrolling = false

            } else {

                binding.recyclerSearchNews.setPadding(0, 0, 0, 0)
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

        binding.paginationProgressBar.visibility = View.GONE
        isLoading = false
    }

    private fun showProgressBar() {

        binding.paginationProgressBar.visibility = View.VISIBLE
        isLoading = true
    }

}