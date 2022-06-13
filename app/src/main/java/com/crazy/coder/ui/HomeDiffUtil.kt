package com.crazy.coder.ui

import androidx.recyclerview.widget.DiffUtil
import com.crazy.coder.data.models.Repositories

// diffutil help us to reload view holder if any data in list changes, overcomes notifyDataSetChange() problem
class HomeDiffUtil: DiffUtil.ItemCallback<Repositories>() {
    override fun areItemsTheSame(oldItem: Repositories, newItem: Repositories): Boolean {
        return true//oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Repositories, newItem: Repositories): Boolean {
        return newItem.username == oldItem.username && newItem.url == oldItem.url && newItem.rank == oldItem.rank && newItem.isSelected == oldItem.isSelected && newItem == oldItem
    }
}

