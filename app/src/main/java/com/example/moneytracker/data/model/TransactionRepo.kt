package com.example.moneytracker.data.model

import com.example.moneytracker.data.remote.RetrofitClientInstance
import com.example.moneytracker.data.remote.TransactionsApi

object TransactionRepo {

    val api = RetrofitClientInstance.getInstance().create(TransactionsApi::class.java)

    suspend fun getTransactions() = api.getTransactions()

    suspend fun getTransaction(id:String) = api.getTransaction(id)

    suspend fun addTransaction(transactionInfo: Transaction) = api.addTransaction(transactionInfo)

    suspend fun editTransaction(transactionInfo : Transaction) = api.editTransaction(transactionInfo)

    suspend fun deleteTransaction(id:String) = api.deleteTransaction(id)



}