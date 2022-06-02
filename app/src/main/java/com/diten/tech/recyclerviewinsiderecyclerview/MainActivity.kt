package com.diten.tech.recyclerviewinsiderecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.diten.tech.recyclerviewinsiderecyclerview.adapter.RVAdapter
import com.diten.tech.recyclerviewinsiderecyclerview.model.LocationList
import com.diten.tech.recyclerviewinsiderecyclerview.viewModel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var rvAdapter: RVAdapter
    lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()
        loadData()
    }

    private fun initRecyclerView() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            val decoration = DividerItemDecoration(applicationContext,DividerItemDecoration.VERTICAL)
            addItemDecoration(decoration)
            rvAdapter = RVAdapter()
            adapter = rvAdapter
        }
    }

    private fun loadData(){
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getLocListObervable().observe(this, Observer<LocationList> {
            rvAdapter.localListData = it.data.toMutableList()
            rvAdapter.notifyDataSetChanged()
        })
        viewModel.loadData(this@MainActivity)
    }
}