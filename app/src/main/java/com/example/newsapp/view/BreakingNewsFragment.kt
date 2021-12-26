package com.example.newsapp.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.newsapp.adapters.NewsAdapter
import com.example.newsapp.databinding.FragmentNewsBreackingBinding
import com.example.newsapp.utils.Resource
//import com.example.newsapp.viewModel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BreakingNewsFragment : Fragment() {

    private var _binding: FragmentNewsBreackingBinding? = null
    private val binding get() = _binding!!
//    private val viewModel: NewsViewModel by viewModels()
//    private lateinit var newsAdapter: NewsAdapter

    var TAG = "ERROR ARTICLE"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentNewsBreackingBinding.inflate(inflater, container, false)

        return binding.root
    }
}

//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        setUpRecyclerView()
//    }
//
//    private fun setUpRecyclerView() {
//
//        newsAdapter = NewsAdapter()
//
//        binding.breakingRecyclerView.apply {
//            adapter = newsAdapter
//        }
//    }

//    private fun getData() {
//
//        viewModel.breakingNews.observe(viewLifecycleOwner, Observer{ response ->
//
//            when(response){
//
//                is Resource.Success ->{
//                    hideProgressBar()
//                    response.data?.let {  newsResponse ->
//
//                        newsAdapter.differ.submitList(newsResponse.articles)
//
//                    }
//                }
//
//                is Resource.Error ->{
//                    hideProgressBar()
//                    response.message?.let { message ->
//
//                        Toast.makeText(requireContext(), "An error ocoured $message", Toast.LENGTH_LONG).show()
//
//                    }
//                }
//
//                is Resource.Loading ->{
//
//                    showProgressBar()
//
//                }
//            }
//
//        })
//
//    }

//    private fun hideProgressBar(){
//
//        binding.newsProgressBar.visibility = View.GONE
//
//    }
//
//    private fun showProgressBar(){
//
//        binding.newsProgressBar.visibility = View.VISIBLE
//
//    }
//}