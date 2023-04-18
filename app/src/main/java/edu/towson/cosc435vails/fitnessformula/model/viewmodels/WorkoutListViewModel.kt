package edu.towson.cosc435vails.fitnessformula.model.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import edu.towson.cosc435vails.fitnessformula.model.Workout
import edu.towson.cosc435vails.fitnessformula.model.Exercise

class WorkoutListViewModel(exerciseList: ExerciseListViewModel) : ViewModel(){
    private var originalList : List<Workout> = (0..50).map{i ->
        Workout("Workout $i", exerciseList )
        //TODO- Move this list to a repo
    }
    private val _workouts: MutableState<List<Workout>> = mutableStateOf(listOf())
    val workouts: State<List<Workout>> = _workouts

    init {
        _workouts.value = originalList
    }
    fun addExercise(workout: Workout){
        _workouts.value = listOf(workout) + originalList
    }
    fun deleteExercise(){/*TODO*/}
}