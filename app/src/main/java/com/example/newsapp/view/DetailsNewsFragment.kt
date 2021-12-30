package com.example.newsapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.newsapp.adapters.NewsAdapter
import com.example.newsapp.databinding.FragmentDetailsNewsBinding
import com.example.newsapp.viewModel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsNewsFragment : Fragment() {

    private var _binding: FragmentDetailsNewsBinding? = null
    private val binding get() = _binding!!
    private lateinit var newsAdapter: NewsAdapter
    private val viewModel: NewsViewModel by viewModels()
    val args: DetailsNewsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentDetailsNewsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setData()
        insertArticleToDataBase()

    }

    private fun setData() {

        binding.webView.apply {

            webViewClient = WebViewClient()
            loadUrl(args.details.url!!)

        }

    }

    private fun insertArticleToDataBase(){

        binding.fab.setOnClickListener {

            viewModel.insertArticle(args.details)

        }
    }
}