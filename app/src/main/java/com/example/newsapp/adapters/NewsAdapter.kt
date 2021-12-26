package com.example.newsapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.databinding.ItemArticleNewsBinding
import com.example.newsapp.model.Article

//class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
//
//    class NewsViewHolder constructor(binding: ItemArticleNewsBinding) :
//        RecyclerView.ViewHolder(binding.root)
//
//    private val diffCallBack = object : DiffUtil.ItemCallback<Article>() {
//        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
//            return oldItem.url == newItem.url
//        }
//
//        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
//
//            return oldItem == newItem
//
//        }
//
//    }
//
//    val differ = AsyncListDiffer(this, diffCallBack)
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
//
//        return NewsViewHolder(
//            ItemArticleNewsBinding.inflate(
//                LayoutInflater.from(parent.context),
//                parent,
//                false
//            )
//        )
//
//
//    }
//
//    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
//
//        val article = differ.currentList[position]
//        holder.itemView.apply {
//
////            Glide.with(this).load(article.urlToImage).into(image)
////            tvSource.text = article.source.name
////            tvTitle.text = article.title
////            tvDescription.text = article.description
////            tvPublishedAt.text = article.publishedAt
//
//            setOnClickListener {
//
//                onClickListener?.let {
//                    it(article)
//                }
//            }
//        }
//
//    }
//
//    private var onClickListener: ((Article) -> Unit)? = null
//
//    fun setOnItemClickListener(listener: (Article) -> Unit) {
//
//        onClickListener = listener
//    }
//
//    override fun getItemCount(): Int {
//        return differ.currentList.size
//    }
//}