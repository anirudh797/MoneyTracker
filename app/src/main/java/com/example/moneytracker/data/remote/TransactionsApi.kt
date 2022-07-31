package com.example.moneytracker.data.remote

import com.example.moneytracker.data.model.Transaction
import com.example.moneytracker.data.model.TransactionType
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface TransactionsApi {

    @GET("/transactions")
    suspend fun getTransactions() : List<Transaction>

    @GET("/transactions/{id}")
    suspend fun getTransaction(@Path("id") id : String) : Transaction

    @POST("/transaction/{id}")
    suspend fun editTransaction(@Path("id") id : String)

    @DELETE("/transaction/{id}")
    suspend fun deleteTransaction(@Path("id") id: String)

}