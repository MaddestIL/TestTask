package com.maddest.testtask

import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.maddest.testtask.databinding.WorldItemBinding
import com.maddest.testtask.models.WorldThing

class WorldAdapter(private val itemsList: ArrayList<WorldThing>): RecyclerView.Adapter<WorldAdapter.WorldViewHolder>() {



    class WorldViewHolder(val binding:WorldItemBinding ): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorldViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = WorldItemBinding.inflate(inflater, parent, false)

        return WorldViewHolder(binding)
    }

    override fun getItemCount() = itemsList.size

    override fun onBindViewHolder(holder: WorldViewHolder, position: Int) {
        val cell = itemsList[position]
        val context = holder.itemView.context
        holder.itemView.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        ).apply {
            gravity = Gravity.CENTER
            setMargins(40, 8, 40, 8)
        }
        with(holder.binding){

            titleTextView.text = cell.title
            descriptionTextView.text = cell.description
            if(cell.isAlive){

            }

        }
    }
}