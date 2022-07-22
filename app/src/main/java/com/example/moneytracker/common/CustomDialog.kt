package com.example.moneytracker.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.DialogFragment
import com.example.moneytracker.R
import com.example.moneytracker.databinding.AddIncomeExpenseDialogBinding

class CustomDialog : DialogFragment() {

    lateinit var binding : AddIncomeExpenseDialogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = AddIncomeExpenseDialogBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            spinner.adapter = CustomAdapter(requireContext(),R.layout.spinner_list_item,
                resources.getStringArray(R.array.listOfCategories).toList())
        }
        binding.spinner.prompt = "Please select one "

        setupListeners()

    }

    private fun setupListeners() {

         binding.spinner.apply {
             onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                 override fun onItemSelected(
                     parent: AdapterView<*>?,
                     view: View?,
                     position: Int,
                     id: Long
                 ) {
                     var view = parent?.selectedView
//                 ((view as ConstraintLayout).getViewById(R.id.tv_category_name) as TextView).setText()
                 }

                 override fun onNothingSelected(parent: AdapterView<*>?) {
                     binding.spinner.setSelection(0)
                 }

             }
         }


    }

}