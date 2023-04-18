package edu.towson.cosc435vails.fitnessformula.model.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.towson.cosc435vails.fitnessformula.model.Exercise

class ExerciseListViewModel : ViewModel() {
    private var originalList: List<Exercise> = (0..10).map { i ->
        Exercise("Exercise $i", "Exercise Description $i")
        //TODO- Move this list to a repo
    }
    private val _exercises: MutableState<List<Exercise>> = mutableStateOf(listOf())
    val exercises: State<List<Exercise>> = _exercises

    init {
        _exercises.value = originalList
    }
    fun addExercise(exercise: Exercise){
        _exercises.value = listOf(exercise) + originalList
    }
    fun deleteExercise(){/*TODO*/}
}