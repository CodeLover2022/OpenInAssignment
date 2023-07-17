package com.example.openinassignment.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.openinassignment.Data.RecentLink
import com.example.openinassignment.R

class RecentLinkAdapter(val context:Context,val arr:ArrayList<RecentLink>):RecyclerView.Adapter<RecentLinkAdapter.MyRecentLink>() {
    class MyRecentLink(itemView: View): RecyclerView.ViewHolder(itemView) {
        val title= itemView.findViewById<TextView>(R.id.rv_title)
        val clicks=itemView.findViewById<TextView>(R.id.rv_clicks)
        val customLink=itemView.findViewById<TextView>(R.id.customUrl)
        val time =itemView.findViewById<TextView>(R.id.rv_time)
        val img=itemView.findViewById<ImageView>(R.id.rv_img)
    }





    override fun onBindViewHolder(holder: MyRecentLink, position: Int) {
        holder.title.text=arr[position].title
        holder.clicks.text=arr[position].total_clicks.toString()
        holder.customLink.text=arr[position].web_link
        holder.time.text=arr[position].times_ago
        Glide.with(context).load(arr[position].original_image).into(holder.img)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyRecentLink {
       return MyRecentLink(LayoutInflater.from(parent.context).inflate(R.layout.recy_layout,parent,false))
    }

    override fun getItemCount(): Int {
       return arr.size
    }
}