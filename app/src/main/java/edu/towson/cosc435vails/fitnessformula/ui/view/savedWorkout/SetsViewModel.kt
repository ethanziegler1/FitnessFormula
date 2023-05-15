package edu.towson.cosc435vails.fitnessformula.ui.view.savedWorkout

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

//View model that holds value for sets field of each workout exercise.
class SetsViewModel() : ViewModel() {

    private val _setsNumber = mutableStateListOf<String>()
    val setsNumber: List<String> = _setsNumber


    fun setSetsNumber(name: String) {
        _setsNumber.add(name)

    }
    fun updateSetsNumber(index: Int, name: String) {
        _setsNumber[index] = name

    }
}