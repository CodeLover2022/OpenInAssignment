package com.example.openinassignment.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.openinassignment.Data.dataClass1
import com.example.openinassignment.R

class DataAdapter1(val context:Context,val arr:ArrayList<dataClass1>):RecyclerView.Adapter<DataAdapter1.MyView>()
{
    class MyView(itemView: View):RecyclerView.ViewHolder(itemView) {
    val tv_change1=itemView.findViewById<TextView>(R.id.tv_dataChange1)
        val tv_change2=itemView.findViewById<TextView>(R.id.tv_dataChange2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyView {
        return MyView(LayoutInflater.from(parent.context).inflate(R.layout.card_layout,parent,false))
    }

    override fun getItemCount(): Int {
       return arr.size
    }

    override fun onBindViewHolder(holder: MyView, position: Int) {
        holder.tv_change1.text=arr[position].content
        holder.tv_change2.text=arr[position].name

    }

}
