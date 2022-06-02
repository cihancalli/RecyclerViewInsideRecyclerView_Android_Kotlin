package com.diten.tech.recyclerviewinsiderecyclerview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.diten.tech.recyclerviewinsiderecyclerview.R
import com.diten.tech.recyclerviewinsiderecyclerview.model.LocationData
import kotlinx.android.synthetic.main.recycler_list_row.view.*

class RVAdapter: RecyclerView.Adapter<RVAdapter.MyViewHolder>()  {

    var localListData = mutableListOf<LocationData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.recycler_list_row,parent,false)
        return MyViewHolder(inflater)
    }

    override fun getItemCount(): Int {
        return localListData.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(localListData[position])
    }

    class MyViewHolder(view: View): RecyclerView.ViewHolder(view){

        val imageview = view.imageview
        val textViewName = view.textViewName
        val textViewAddress = view.textViewAddress

        fun bind(data : LocationData) {
            textViewName.text = data.locationName
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
            textViewAddress.text = address

            Glide.with(imageview).load(data.url).into(imageview)

        }
    }


}