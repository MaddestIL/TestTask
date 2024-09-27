package com.maddest.testtask.presentation

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.maddest.testtask.R
import com.maddest.testtask.databinding.ActivityMainBinding
import com.maddest.testtask.data.WorldThing
import dagger.hilt.android.AndroidEntryPoint



@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: WorldAdapter
    private  var countAlive = NULL_COUNT
    private  var countDead = NULL_COUNT
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = WorldAdapter(arrayListOf<WorldThing>())
        //LiveData подписка для получения данных от ViewModel
        viewModel.thingsList.observe(this) { data ->
            adapter.updateData(data)
            adapter.notifyItemInserted(data.size-1)
            // Обновление данных в адаптере
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = adapter

        // Наблюдение за данными из ViewModel

    }
    //onProduceButtonClick - callback,который срабатывает по нажатию кнопки
    //countAlive - Подсчет всех живых клеток
    //countDead - Подсчет всех мертвых клеток
    //

    fun onProduceButtonClick(view: View) {
        val isAlive = (0..1).random() == 1 //Равноверояное случайное булевое значение
        var newThing: WorldThing

        if (isAlive) {
            countAlive++ //Реализация подсчета клеток под ряд
            countDead = NULL_COUNT//(Другой счетчик зануляется после ++ другого счетчика)
            //Создание клетки
            newThing = WorldThing(
                title = getString(R.string.title_alive),
                description = getString(R.string.description_alive),
                isAlive = true
            )
            viewModel.addNewThing(newThing)
            if (countAlive == CELLS_IN_ROW) {
                // Создание существа
                newThing = WorldThing(
                    title = getString(R.string.title_creature),
                    description = getString(R.string.description_creature),
                    isAlive = true,
                    isCreature = true
                )
                viewModel.addNewThing(newThing)
                countAlive = NULL_COUNT
            }
        } else {
            countDead++   //Реализация подсчета клеток под ряд
            countAlive = NULL_COUNT//(Другой счетчик зануляется после ++ другого счетчика)
            //Создание мертвой клетки
            newThing = WorldThing(
                title = getString(R.string.title_dead),
                description = getString(R.string.description_dead),
                isAlive = false
            )
            if (countDead == CELLS_IN_ROW) {
                // Удаление существа
                viewModel.removeCreature()
                countDead = NULL_COUNT
            }
            viewModel.addNewThing(newThing)
        }


    }
    companion object{
        const val NULL_COUNT = 0
        const val CELLS_IN_ROW = 3
    }
}