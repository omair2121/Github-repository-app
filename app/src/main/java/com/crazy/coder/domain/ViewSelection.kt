package com.crazy.coder.domain

import com.crazy.coder.data.models.Repositories

interface ViewSelection {
    fun onViewSelect(selectedItem: Repositories)
}