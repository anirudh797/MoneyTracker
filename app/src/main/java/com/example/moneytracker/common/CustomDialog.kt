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
import androidx.lifecycle.ViewModelProvider
import com.example.moneytracker.R
import com.example.moneytracker.data.model.Transaction
import com.example.moneytracker.data.model.TransactionType
import com.example.moneytracker.databinding.AddIncomeExpenseDialogBinding
import com.example.moneytracker.ui.home.HomeViewModel
import java.text.SimpleDateFormat
import java.util.*


class CustomDialog : DialogFragment() {

    lateinit var binding : AddIncomeExpenseDialogBinding

    lateinit var myCalendar : Calendar

    lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    companion object{

        const val Type = "type"
        fun newInstance(type: TransactionType): CustomDialog? {
            val dialog = CustomDialog()

            // Supply num input as an argument.
            val args = Bundle()
            args.putString("type",type.toString())
            dialog.arguments = args
            return dialog
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = AddIncomeExpenseDialogBinding.inflate(inflater,container,false)
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
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
        binding.btnSave.setOnClickListener {
            viewModel.addTransaction(prepareTransactionData())
        }


    }

    private fun prepareTransactionData(): Any {

        var type = arguments?.getString(Type)
        val transactionType = if(type?.equals("INCOME") == true) TransactionType.INCOME else
            TransactionType.EXPENSE
        val category =  if(type?.equals("INCOME") == true) "income" else
            "expense"
        return Transaction(transactionType,
                            binding.tvDesc.text.toString(),
                            binding.tvDate.text.toString(),
                            binding.tvAmount.toString(),
                            category)

    }

    private fun updateLabel() {
        val myFormat = "MM/dd/yy"
        val dateFormat = SimpleDateFormat(myFormat, Locale.US)
        binding.tvDate.text = dateFormat.format(myCalendar.time)

    }

}