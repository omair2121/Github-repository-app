package com.crazy.coder.ui

import android.content.ContextWrapper
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.crazy.coder.R
import com.crazy.coder.data.models.RepositoriesModelItem
import com.crazy.coder.databinding.ItemGitRepoBinding
import com.crazy.coder.domain.ViewSelection

class GitRepoViewHolder(
    private val binding: ItemGitRepoBinding,
    private val viewSelection: ViewSelection
) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun create(
            inflater: LayoutInflater,
            parent: ViewGroup,
            viewSelection: ViewSelection
        ): GitRepoViewHolder {
            val binding = DataBindingUtil.inflate<ItemGitRepoBinding>(
                inflater,
                R.layout.item_git_repo, parent, false
            )
            return GitRepoViewHolder(binding, viewSelection)
        }
    }

    fun bind(item: RepositoriesModelItem) {
        binding.apply {
//            roundedIv.setImageResource()
//            roundedIv.load(item.url) // url is not image
            nameTv.text = item.username
        }

        itemView.setOnClickListener {
            item.isSelected = !item.isSelected
//            viewSelection.onViewSelect(item)
            bindSelection(item.isSelected)
        }
        bindSelection(item.isSelected)
    }

    private fun bindSelection(isSelected: Boolean) {
        val bgColor = if (isSelected) R.color.red_transparent else R.color.white
        binding.root.setBackgroundColor(ContextCompat.getColor(binding.root.context, bgColor))
    }
}
