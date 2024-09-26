package com.maddest.testtask.presentation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.maddest.testtask.R
import com.maddest.testtask.databinding.ActivityMainBinding
import com.maddest.testtask.data.WorldThing

class MainActivity : AppCompatActivity() {

    private lateinit var thingsList: ArrayList<WorldThing>
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: WorldAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        thingsList = ArrayList()
        adapter = WorldAdapter(thingsList)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = adapter
    }

    fun onProduceButtonClick(view: View){
        val newThing = if(Math.random() < 0.5){
            WorldThing(
                title = getString(R.string.title_alive),
                description = getString(R.string.description_alive),
                isAlive = true)
        } else {
            WorldThing(
                title = getString(R.string.title_dead),
                description = getString(R.string.description_dead)
            )
        }
        thingsList.add(newThing)
        adapter.notifyItemInserted(thingsList.size - 1)
    }
}