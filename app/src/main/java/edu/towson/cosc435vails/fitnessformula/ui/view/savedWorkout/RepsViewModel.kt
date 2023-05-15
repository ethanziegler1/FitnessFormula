package edu.towson.cosc435vails.fitnessformula.ui.view.savedWorkout

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

//View model that hopes state for reps field in workout exercise
class RepsViewModel : ViewModel() {

    private val _repsNumber = mutableStateListOf<String>()
    val repsNumber: List<String> = _repsNumber

    fun setRepsNumber(name: String) {
        _repsNumber.add(name)
    }
    fun updateRepsNumber(index: Int, name: String) {
        _repsNumber[index] = name
    }
}