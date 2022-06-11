package com.crazy.coder.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.crazy.coder.data.models.RepositoriesModelItem
import com.crazy.coder.domain.ViewSelection

class HomeAdapter(private val viewSelection: ViewSelection) :
//    RecyclerView.Adapter<GitRepoViewHolder>() {
    ListAdapter<RepositoriesModelItem, GitRepoViewHolder>(HomeDiffUtil()) {
    var createHolderCount = 0
    var bindHolderCount = 0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GitRepoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        createHolderCount++
        Log.d("Omair", "onCreateHolder: $createHolderCount")

        return GitRepoViewHolder.create(inflater, parent, viewSelection)
    }

    override fun onBindViewHolder(holder: GitRepoViewHolder, position: Int) {
        bindHolderCount++
        Log.d("Omair", "onBindViewHolder: $bindHolderCount")
//        holder.bind(list.get(position))
        holder.bind(getItem(position))
    }

//    override fun getItemCount() = list.size
//
//
//    private var list: List<RepositoriesModelItem> = emptyList()
//    fun submitList(newList: List<RepositoriesModelItem>) {
//        list = newList
//    }
//
//    fun currentList() = list
}