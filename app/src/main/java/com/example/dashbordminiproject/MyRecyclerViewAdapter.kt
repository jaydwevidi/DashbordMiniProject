package com.example.dashbordminiproject

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.custom_item_for_recyclerview.view.*

class MyRecyclerAdapter( var context: Context) :
    RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder>() {

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var t1: TextView = itemView.t1
        var i1: ImageView = itemView.i1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.custom_item_for_recyclerview,
            parent,
            false
        )
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.t1.text = "dataList[position]"

        Glide.with(holder.itemView)
            .load("https://static.scientificamerican.com/sciam/cache/file/92E141F8-36E4-4331-BB2EE42AC8674DD3_source.jpg")
            .into(holder.i1)

    }

    fun getImageURL(name: String): String {
        return when (name) {
            "Delhi" -> "https://in.bmscdn.com/m6/images/common-modules/regions/ncr.png"
            "Mumbai" -> "https://in.bmscdn.com/m6/images/common-modules/regions/mumbai.png"
            "Banglore" -> "https://in.bmscdn.com/m6/images/common-modules/regions/bang.png"
            "Hyderabad" -> "https://in.bmscdn.com/m6/images/common-modules/regions/hyd.png"
            "Ahmedabad" -> "https://in.bmscdn.com/m6/images/common-modules/regions/ahd.png"
            "Kolkatta" -> "https://in.bmscdn.com/m6/images/common-modules/regions/kolk.png"
            else -> "https://in.bmscdn.com/m6/images/common-modules/regions/koch.png"
        }
    }

    override fun getItemCount(): Int {
        return 50
    }
}