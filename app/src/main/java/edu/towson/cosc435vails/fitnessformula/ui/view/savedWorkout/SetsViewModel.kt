package edu.towson.cosc435vails.fitnessformula.ui.view.savedWorkout

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class SetsViewModel : ViewModel() {

    private val _setsNumber = mutableStateListOf<String>()
    val setsNumber: List<String> = _setsNumber

    fun setSetsNumber(name: String) {
        _setsNumber.add(name)
    }
    fun updateSetsNumber(index: Int, name: String) {
        _setsNumber[index] = name
    }
}