package com.crazy.coder.ui

import androidx.recyclerview.widget.DiffUtil
import com.crazy.coder.data.models.RepositoriesModelItem

class HomeDiffUtil: DiffUtil.ItemCallback<RepositoriesModelItem>() {
    override fun areItemsTheSame(oldItem: RepositoriesModelItem, newItem: RepositoriesModelItem): Boolean {
        return true//oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: RepositoriesModelItem, newItem: RepositoriesModelItem): Boolean {
        return newItem.username == oldItem.username && newItem.url == oldItem.url && newItem.rank == oldItem.rank && newItem.isSelected == oldItem.isSelected && newItem == oldItem
    }
}

//class HomeDiffUtil2(private val oldList: MutableList<RepositoriesModelItem>, private val newList: MutableList<RepositoriesModelItem>): DiffUtil.Callback() {
//    override fun getOldListSize(): Int {
//        return oldList.size
//    }
//
//    override fun getNewListSize(): Int {
//        return newList.size
//    }
//
//    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
//        return true
//    }
//
//    override fun areContentsTheSame(oldPosition: Int, newPosition: Int): Boolean {
//        val newItem = newList.get(newPosition)
//        val oldItem = oldList.get(oldPosition)
//        return return newItem.username == oldItem.username && newItem.url == oldItem.url && newItem.rank == oldItem.rank && newItem.isSelected == oldItem.isSelected && newItem == oldItem
//
//    }
//
//}

