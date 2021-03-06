package com.example.moneytracker.common

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.moneytracker.R
import com.example.moneytracker.databinding.SpinnerListItemBinding

class CustomAdapter(context: Context, resourceId: Int, var list: List<String>) :
    ArrayAdapter<String>(context,resourceId,list) {

    var initialText = "Please Select"
    var initialTextWasShown : Boolean = false


    fun anySelectionMade(spinner: Spinner) : Boolean{
        return !((spinner.selectedView as? ConstraintLayout)?.getViewById(R.id.tv_category_name) as TextView).text.toString().equals(initialText)
    }

    fun TextView.getText() : String{
        return text.toString()
    }

    lateinit var  binding: SpinnerListItemBinding
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getCustomView(position,convertView,parent)
    }


    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getCustomView(position,convertView,parent)

    }

    private fun getCustomView(position: Int, convertView: View?, parent: ViewGroup): View {

        var layoutInflater = LayoutInflater.from(context)
        binding = SpinnerListItemBinding.inflate(layoutInflater,parent,false)
        var categoryList = listOf(R.array.listOfCategories)
        binding.apply {
            tvCategoryName.text = list[position]
        }
        return binding.root
    }


}