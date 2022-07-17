package com.example.moneytracker.common

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseViewHolder<B : ViewBinding, M : BaseUiModel>(private val binding: B) :
    RecyclerView.ViewHolder(binding.root) {

    abstract fun onBind(data: M)

}
