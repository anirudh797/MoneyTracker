package com.example.moneytracker.common

interface ClickListener {

    fun onClicked(data: BaseUiModel? = null)

    fun onLongClicked(data: BaseUiModel? = null) {}
}