package com.example.baguette.recyclerviewdatabinding.views

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.baguette.recyclerviewdatabinding.AppApplication
import com.example.baguette.recyclerviewdatabinding.R
import com.example.baguette.recyclerviewdatabinding.databinding.ActivityMainBinding
import com.example.baguette.recyclerviewdatabinding.model.EvenNumberListItem
import com.example.baguette.recyclerviewdatabinding.model.OddNumberListItem
import com.example.baguette.recyclerviewdatabinding.viewmovel.MainViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var mainViewModel: MainViewModel

    private lateinit var recyclerViewAdapter: MainRecyclerViewAdapter

    val binding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as AppApplication).appComponent.inject(this)

        binding.viewModel = mainViewModel

        initRecyclerView()
    }

    private fun initRecyclerView() {
        recyclerViewAdapter = MainRecyclerViewAdapter(this)
        binding.mainRecyclerView.apply {
            adapter = recyclerViewAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        recyclerViewAdapter.upDateItems(
                EvenNumberListItem(2),
                OddNumberListItem(1)
        )
        recyclerViewAdapter.notifyDataSetChanged()
    }
}
