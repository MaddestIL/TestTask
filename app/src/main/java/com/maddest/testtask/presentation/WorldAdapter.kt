package com.maddest.testtask.presentation

import android.annotation.SuppressLint
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.maddest.testtask.R
import com.maddest.testtask.databinding.WorldItemBinding
import com.maddest.testtask.data.WorldThing
/*
* Класс для создания адаптера RecyclerView
*
*
* */
class WorldAdapter(private var itemsList: ArrayList<WorldThing>): RecyclerView.Adapter<WorldAdapter.WorldViewHolder>() {



    class WorldViewHolder(val binding:WorldItemBinding ): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorldViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = WorldItemBinding.inflate(inflater, parent, false)

        return WorldViewHolder(binding)
    }

    override fun getItemCount() = itemsList.size

    //Отрисовка клетки или существа
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
        //Выбор данных в зависимости от типа клетки
        with(holder.binding){

            titleTextView.text = cell.title
            descriptionTextView.text = cell.description

            when{
                cell.isCreature -> Glide.with(context)
                    .load(R.drawable.creature)
                    .into(holder.binding.label)
                cell.isAlive -> Glide.with(context)
                    .load(R.drawable.live)
                    .into(holder.binding.label)
                else -> Glide.with(context)
                    .load(R.drawable.dead)
                    .into(holder.binding.label)
            }
        }
    }
    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newData: ArrayList<WorldThing>) {
        itemsList = newData
        notifyDataSetChanged()
    }
}