package com.example.dashbordminiproject.recyclerViews

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dashbordminiproject.R
import kotlinx.android.synthetic.main.item_for_recyclerview_tasks.view.*

class MyRecyclerViewAdapterGrid( var context: Context) :
    RecyclerView.Adapter<MyRecyclerViewAdapterGrid.MyViewHolderGrid>() {

    inner class MyViewHolderGrid(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var i1: ImageView = itemView.gridi1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolderGrid {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_for_recyclerview_tasks,
            parent,
            false
        )
        return MyViewHolderGrid(view)
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
        return 6
    }

    override fun onBindViewHolder(holder: MyViewHolderGrid, position: Int) {
        Glide.with(holder.itemView)
            .load(
                "https://1000logos.net/wp-content/uploads/2016/10/batman-logo-1966-1999.jpg"
            ).into(holder.i1)
    }
}