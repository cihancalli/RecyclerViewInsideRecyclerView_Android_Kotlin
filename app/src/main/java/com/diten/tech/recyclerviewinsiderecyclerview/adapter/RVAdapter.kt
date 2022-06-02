package com.diten.tech.recyclerviewinsiderecyclerview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.diten.tech.recyclerviewinsiderecyclerview.R
import com.diten.tech.recyclerviewinsiderecyclerview.model.LocationData
import kotlinx.android.synthetic.main.recycler_list_row.view.*

class RVAdapter(val clickListener: OnRecyclerItemClick): RecyclerView.Adapter<RVAdapter.MyViewHolder>()  {

    var localListData = mutableListOf<LocationData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.recycler_list_row,parent,false)
        return MyViewHolder(inflater,clickListener)
    }

    override fun getItemCount(): Int {
        return localListData.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(localListData[position])
        holder.itemView.setOnClickListener {
            clickListener.onItemClikListener(localListData[position])
        }
    }

    class MyViewHolder(view: View, val clickListener: OnRecyclerItemClick): RecyclerView.ViewHolder(view){

        val imageview = view.imageview
        val textViewName = view.textViewName
        val textViewAddress = view.textViewAddress
        val tvChildCount = view.tvChildCount
        val childRecyclerView  =view.childRecyclerView

        fun bind(data : LocationData) {
            //textViewName.text = data.locationName
            var address = ""

            data.address?.let {
                address += it
            }

            data.city?.let {
                address += it
            }

            data.state?.let {
                address += it
            }

            data.zip?.let {
                address += it
            }

            data.country?.let {
                address += it
            }
            //textViewAddress.text = address

            Glide.with(imageview).load(data.url).into(imageview)

            if (data.childLocations != null && data.childLocations.size > 0){
                tvChildCount.visibility = VISIBLE
                childRecyclerView.visibility = VISIBLE
                childRecyclerView.apply {
                    //layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
                    layoutManager = GridLayoutManager(context,3)
                    val decoration = DividerItemDecoration(context,DividerItemDecoration.HORIZONTAL)
                    addItemDecoration(decoration)
                    val rvAdapter = RVAdapter(clickListener)
                    rvAdapter.localListData = data.childLocations.toMutableList()
                    adapter = rvAdapter
                }
                tvChildCount.text = "Child Location " + data.childLocations.size
            }else{
                tvChildCount.visibility = GONE
                childRecyclerView.visibility = GONE
            }

        }
    }

    interface OnRecyclerItemClick {
        fun onItemClikListener(data : LocationData)
    }


}
