package com.example.moneytracker.common

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.moneytracker.R
import com.example.moneytracker.databinding.SpinnerListItemBinding

class CustomAdapter(context: Context, resourceId: Int, var list: List<String>) :
    ArrayAdapter<String>(context,resourceId,list) {

    lateinit var  binding: SpinnerListItemBinding
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getCustomView(position,convertView,parent)
    }


    private fun getCustomView(position: Int, convertView: View?, parent: ViewGroup): View {

        var layoutInflater = LayoutInflater.from(context)
        binding = SpinnerListItemBinding.inflate(layoutInflater,parent,false)
        var categoryList = listOf(R.array.listOfCategories)
        binding.apply {
            tvCategoryName.setText(list[position])
        }
        return binding.root
    }

}