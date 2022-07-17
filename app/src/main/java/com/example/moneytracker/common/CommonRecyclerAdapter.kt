package com.example.moneytracker.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

class CommonRecyclerAdapter( private val data: MutableList<BaseUiModel>,
                             private val listener: ClickListener)
    : RecyclerView.Adapter<BaseViewHolder<ViewBinding, BaseUiModel>>() {

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): BaseViewHolder<ViewBinding, BaseUiModel> {
            val inflater = LayoutInflater.from(parent.context)
            return when (viewType) {



                else -> throw Exception("Viewholder not registered!")
            } as BaseViewHolder<ViewBinding, BaseUiModel>
        }


        override fun getItemCount(): Int {
            return data.size
        }

        override fun getItemViewType(position: Int): Int {
            return data[position].getLayout()
        }

        fun setListData(data: List<BaseUiModel>){
            this.data.clear()
            this.data.addAll(data)
            notifyDataSetChanged()
        }

        override fun onBindViewHolder(holder: BaseViewHolder<ViewBinding, BaseUiModel>, position: Int) {
            holder.onBind(data[position])
        }

    }


}