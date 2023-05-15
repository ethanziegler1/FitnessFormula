package edu.towson.cosc435vails.fitnessformula.ui.view.savedWorkout


import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel


class SavedWorkoutsViewModel : ViewModel() {

    private val _workoutName = mutableStateListOf<String>()
    var workoutName: List<String> = _workoutName


    private val _checkedState = mutableStateListOf(false)
    val checkedState = _checkedState



    fun setWorkoutName(name: String) {
        _workoutName.add(name)
    }
    fun updateWorkoutName(index: Int, name: String) {
        _workoutName[index] = name
    }

    fun setCheckedState(checked: Boolean) {
        _checkedState.add(checked)
    }

    fun updateCheckedState(index: Int, checked: Boolean) {
        _checkedState[index] = checked

    }

}


