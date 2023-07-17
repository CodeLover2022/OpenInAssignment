package com.example.openinassignment.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.openinassignment.Data.TopLink
import com.example.openinassignment.R

class TopLinkAdapter(val context: Context,val arr:ArrayList<TopLink>):RecyclerView.Adapter<TopLinkAdapter.MyTopLink>() {
    class MyTopLink(itemView: View):RecyclerView.ViewHolder(itemView) {
         val title= itemView.findViewById<TextView>(R.id.rv_title)
         val clicks=itemView.findViewById<TextView>(R.id.rv_clicks)
         val customLink=itemView.findViewById<TextView>(R.id.customUrl)
         val time =itemView.findViewById<TextView>(R.id.rv_time)
         val img=itemView.findViewById<ImageView>(R.id.rv_img)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyTopLink {
        return MyTopLink(LayoutInflater.from(parent.context).inflate(R.layout.recy_layout,parent,false))
    }

    override fun getItemCount(): Int {
        return arr.size
    }

    override fun onBindViewHolder(holder: MyTopLink, position: Int) {
       holder.title.text=arr[position].title
        holder.clicks.text=arr[position].total_clicks.toString()
        holder.customLink.text=arr[position].web_link
        holder.time.text=arr[position].times_ago
        Glide.with(context).load(arr[position].original_image).into(holder.img)
    }
}