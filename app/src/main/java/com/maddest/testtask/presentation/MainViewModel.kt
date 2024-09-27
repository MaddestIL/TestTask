package com.maddest.testtask.presentation


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.maddest.testtask.data.WorldThing
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
): ViewModel(){

    //Создание LiveData
    private val _thingsList = MutableLiveData<ArrayList<WorldThing>>()
    val thingsList: MutableLiveData<ArrayList<WorldThing>>
        get() = _thingsList

   //Добавление существа в мир
    fun addNewThing(worldThing: WorldThing){
        val updateList = _thingsList.value ?: arrayListOf()
        updateList.add(worldThing)
        _thingsList.value = updateList
    }
    //Удалить существо из списка
    fun removeCreature(){
        _thingsList.value?.removeIf { it.isCreature }
    }

}