package com.example.moneytracker.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.moneytracker.R
import com.example.moneytracker.common.BaseUiModel
import com.example.moneytracker.common.ClickListener
import com.example.moneytracker.common.CommonRecyclerAdapter
import com.example.moneytracker.common.CustomDialog
import com.example.moneytracker.data.model.EXPENSE
import com.example.moneytracker.data.model.INCOME
import com.example.moneytracker.data.model.TransactionType
import com.example.moneytracker.databinding.FragmentHomeBinding
import com.google.android.gms.common.internal.service.Common

class HomeFragment : Fragment(), ClickListener {

    private var _binding: FragmentHomeBinding? = null
    val rotateOpen : Animation by lazy { AnimationUtils.loadAnimation(activity, R.anim.rotate_open_anim) }
    val rotateClose : Animation by lazy { AnimationUtils.loadAnimation(activity, R.anim.rotate_close_anim) }
    val fromBottom : Animation by lazy { AnimationUtils.loadAnimation(activity, R.anim.from_bottom_anim) }
    val toBottom : Animation by lazy { AnimationUtils.loadAnimation(activity, R.anim.to_bottom_anim) }


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    var fbClicked = false
    lateinit var customDialog: CustomDialog
    lateinit var viewModel: HomeViewModel
    lateinit var  adapter : CommonRecyclerAdapter

    companion object{
        const val ADD_EXPENSE = "Add expense"
        const val ADD_INCOME = "Add Income"
        const val TAG ="tag"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
        setupListeners()
    }

    private fun setupView() {
        customDialog = CustomDialog()
        customDialog.isCancelable = true

    }

    private fun setupListeners() {
        binding.fb.setOnClickListener {
            onFbClicked()
        }

        binding.fbExpense.setOnClickListener {
        CustomDialog.newInstance(TransactionType.EXPENSE)?.show(parentFragmentManager, EXPENSE)
        }

        binding.fbAdd.setOnClickListener {
            CustomDialog.newInstance(TransactionType.INCOME)?.show(parentFragmentManager, INCOME)
        }
    }

    private fun onFbClicked() {
        setAnimation(fbClicked)
        setVisibility(fbClicked)
        setClickable(fbClicked)
        fbClicked = !fbClicked
    }

    private fun setVisibility(clicked: Boolean) {
        if(!clicked){
            binding.rlExpense.visibility = View.VISIBLE
            binding.rlIncome.visibility = View.VISIBLE
        }

        else{
            binding.rlExpense.visibility = View.INVISIBLE
            binding.rlIncome.visibility = View.INVISIBLE
        }

    }

    private fun setAnimation(clicked: Boolean) {

        if(!clicked){
            binding.rlExpense.startAnimation(fromBottom)
            binding.rlIncome.startAnimation(fromBottom)
            binding.fb.startAnimation(rotateOpen)
        }
        else{
            binding.rlExpense.startAnimation(toBottom)
            binding.rlIncome.startAnimation(toBottom)
            binding.fb.startAnimation(rotateClose)
        }
        Log.d("New changes","")

    }

    private fun setClickable(clicked: Boolean){
        if(!fbClicked){
            binding.rlExpense.isClickable = false
            binding.rlIncome.isClickable = false
        }
        else{
            binding.rlExpense.isClickable = true
            binding.rlIncome.isClickable = true
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClicked(data: BaseUiModel?) {
        //send data for edit
    }
}