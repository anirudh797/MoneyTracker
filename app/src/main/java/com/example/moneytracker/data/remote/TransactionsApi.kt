package com.example.moneytracker.data.remote

import com.example.moneytracker.data.model.Transaction
import com.example.moneytracker.data.model.TransactionType
import retrofit2.http.*

interface TransactionsApi {

    @GET("/transactions")
    suspend fun getTransactions() : List<Transaction>

    @GET("/transactions/{id}")
    suspend fun getTransaction(@Path("id") id : String) : Transaction

    @POST("/transaction_add/{id}")
    suspend fun addTransaction(@Body transactionInfo: Transaction)

    @POST("/transaction_edit/{id}")
    suspend fun editTransaction(@Body transactionInfo : Transaction)

    @DELETE("/transaction/{id}")
    suspend fun deleteTransaction(@Path("id") id: String)

}