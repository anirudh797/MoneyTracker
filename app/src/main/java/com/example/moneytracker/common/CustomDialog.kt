package com.example.moneytracker.common

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.moneytracker.R
import com.example.moneytracker.databinding.AddIncomeExpenseDialogBinding
import java.text.SimpleDateFormat
import java.util.*


class CustomDialog : DialogFragment() {

    lateinit var binding : AddIncomeExpenseDialogBinding

    lateinit var myCalendar : Calendar

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
        myCalendar = Calendar.getInstance()

        setupListeners()

    }

    private fun setupListeners() {


        val date = OnDateSetListener { view, year, month, day ->
                myCalendar.set(Calendar.YEAR, year)
                myCalendar.set(Calendar.MONTH, month)
                myCalendar.set(Calendar.DAY_OF_MONTH, day)
                updateLabel()
            }
        binding.ivPickDate.setOnClickListener {
            DatePickerDialog(requireContext(), date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show()
        }

         binding.spinner.apply {
             onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                 override fun onItemSelected(
                     parent: AdapterView<*>?,
                     view: View?,
                     position: Int,
                     id: Long
                 ) {
                     if (parent?.getItemAtPosition(position)?.equals("Choose Category") == true){
                     }else {
                         var item = parent?.getItemAtPosition(position).toString();
                         Toast.makeText(parent?.context, "Selected: $item", Toast.LENGTH_SHORT).show();
                     }
                 }

                 override fun onNothingSelected(parent: AdapterView<*>?) {
//                     binding.spinner.setSelection(0)
                 }

             }
         }


    }

    private fun updateLabel() {
        val myFormat = "MM/dd/yy"
        val dateFormat = SimpleDateFormat(myFormat, Locale.US)
        binding.tvDate.text = dateFormat.format(myCalendar.time)

    }

}