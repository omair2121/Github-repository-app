package com.crazy.coder.domain

import com.crazy.coder.data.models.RepositoriesModelItem

interface ViewSelection {
    fun onViewSelect(selectedItem: RepositoriesModelItem)
}