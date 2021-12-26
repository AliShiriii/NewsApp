package com.example.newsapp.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.newsapp.R
import com.example.newsapp.adapters.NewsAdapter
import com.example.newsapp.databinding.FragmentSearchNewsBinding
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

                        newsAdapter.differ.submitList(newsResponse.articles)

                    }
                }

                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->

                        Log.e(TAG, "An error ocoured $message")

                    }
                }

                is Resource.Loading -> {

                    showProgressBar()

                }
            }

        })
    }

    private fun searchItemInArticle(){

        var job: Job? = null

        binding.etSearch.addTextChangedListener { editable ->
            job?.cancel()

            job = MainScope().launch {
                delay(SEARCH_NEWS_THE_DELAY)
                editable?.let {

                    if (editable.toString().isNotEmpty()){

                        viewModel.searchNews(editable.toString())
                    }

                }
            }

        }

    }

    private fun setOnItemClickListener(){

        newsAdapter.setOnItemClickListener {

            val bundle = Bundle().apply {
                putSerializable("article", it)

            }
            findNavController().navigate(

                R.id.action_searchNewsFragment_to_articleNewsFragment, bundle
            )
        }

    }

    private fun setUpRecyclerView() {

        newsAdapter = NewsAdapter()

        binding.recyclerSearchNews.apply {
            adapter = newsAdapter
        }
    }

    private fun hideProgressBar() {

        binding.paginationProgressBar.visibility = View.GONE

    }

    private fun showProgressBar() {

        binding.paginationProgressBar.visibility = View.VISIBLE

    }

}