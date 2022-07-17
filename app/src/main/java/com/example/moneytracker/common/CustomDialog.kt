package com.example.moneytracker.common

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.fragment.app.DialogFragment
import com.example.moneytracker.R

class CustomDialog : DialogFragment() {

 lateinit var arrayAdapter: ArrayAdapter<String>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arrayAdapter= context?.let { ArrayAdapter(it, R.array.listOfCategories,) }!!
    }

}