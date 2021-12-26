package com.example.newsapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.newsapp.R
import com.example.newsapp.adapters.NewsAdapter
import com.example.newsapp.databinding.FragmentNewsArticleBinding
import com.example.newsapp.databinding.FragmentSavedNewsBinding
import com.example.newsapp.viewModel.NewsViewModel
//import com.example.newsapp.viewModel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SavedNewsFragment : Fragment() {

    private var _binding: FragmentSavedNewsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: NewsViewModel by viewModels()
    private lateinit var newsAdapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentSavedNewsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()
        setOnItemClickListener()

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

        binding.rvSavedNews.apply {
            adapter = newsAdapter
        }
    }
}