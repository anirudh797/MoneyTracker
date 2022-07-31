package com.example.moneytracker.data.model

import androidx.versionedparcelable.ParcelField
import com.google.gson.annotations.SerializedName

const val INCOME = "income"
const val EXPENSE = "expense"


data class Transaction(

    @SerializedName("type")
    val type: TransactionType,
    @SerializedName("description")
    val description:String,
    @SerializedName("date")
    val date : String,
    @SerializedName("amount")
    val amount : String,
    @SerializedName("category")
    val category: String
)


enum class TransactionType {
    INCOME,
    EXPENSE
}