package com.diten.tech.recyclerviewinsiderecyclerview.viewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.diten.tech.recyclerviewinsiderecyclerview.R
import com.diten.tech.recyclerviewinsiderecyclerview.model.LocationList
import com.google.gson.Gson

class MainActivityViewModel : ViewModel() {
    lateinit var loclist: MutableLiveData<LocationList>

    init {
        loclist = MutableLiveData()
    }

    fun getLocListObervable(): MutableLiveData<LocationList>{
        return loclist
    }
    fun loadData(context: Context){
        val json = context.resources.openRawResource(R.raw.data).bufferedReader().use { it.readText() }
        val list = Gson().fromJson<LocationList>(json,LocationList::class.java)
        loclist.postValue(list)
    }
}