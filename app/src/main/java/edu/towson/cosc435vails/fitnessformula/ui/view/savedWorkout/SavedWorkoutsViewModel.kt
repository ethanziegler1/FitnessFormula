package edu.towson.cosc435vails.fitnessformula.ui.view.savedWorkout

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.*
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import edu.towson.cosc435vails.fitnessformula.model.Exercise

class SavedWorkoutsViewModel : ViewModel() {

    private val _workoutName = mutableStateListOf<String>()
    val workoutName: List<String> = _workoutName


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

//    private val exerciseNames = listOf("Push-ups", "Sit-ups", "Squats", "Lunges", "Plank")
//    private val exerciseDescriptions = listOf(
//        "Do push-ups with your hands on the ground",
//        "Lie down and sit up, touching your toes",
//        "Stand with your feet shoulder-width apart and squat down",
//        "Step forward with one leg and bend the knee until the thigh is parallel to the ground",
//        "Hold your body in a straight line from head to toe, supported by your forearms and toes"
//    )
//    private var exercises = exerciseNames.mapIndexed { index, name ->
//        Exercise(name, exerciseDescriptions[index], false, "")
//    }
//
//    private var _workouts = mutableListOf<Workout>()
//
//
//    init {
//        // Initialize the _workouts list with some dummy data
//        _workouts.add(Workout(id = 1, exercises))
//    }


