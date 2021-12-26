package com.example.newsapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.newsapp.databinding.FragmentNewsArticleBinding
import com.example.newsapp.viewModel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint
//
//@AndroidEntryPoint
//class ArticleNewsFragment : Fragment() {
//
//    private var _binding: FragmentNewsArticleBinding? = null
//    private val binding get() = _binding!!
//
//    private val viewModel: NewsViewModel by viewModels()
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//
//        _binding = FragmentNewsArticleBinding.inflate(inflater, container, false)
//
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//
//    }
//
//}