package edu.towson.cosc435vails.fitnessformula.ui.view.exerciselist

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import edu.towson.cosc435vails.fitnessformula.model.Exercise

class ExerciseListViewModel: ViewModel() {

    private val exerciseNames = listOf("Push-ups", "Sit-ups", "Squats", "Lunges", "Plank")
    private val exerciseDescriptions = listOf(
        "Do push-ups with your hands on the ground",
        "Lie down and sit up, touching your toes",
        "Stand with your feet shoulder-width apart and squat down",
        "Step forward with one leg and bend the knee until the thigh is parallel to the ground",
        "Hold your body in a straight line from head to toe, supported by your forearms and toes"
    )
    private var exercises = exerciseNames.mapIndexed { index, name ->
        Exercise(name, exerciseDescriptions[index], false)
    }
    private val _exerciseList: MutableState<List<Exercise>> = mutableStateOf(exercises)
    val exerciseList: State<List<Exercise>> = _exerciseList


    //Track currently selected exercise
    private val _selectedExercise: MutableState<Exercise?> = mutableStateOf(null)
    val selectedExercise: State<Exercise?> = _selectedExercise

    //Track exercise to add to workout page
    private val _checkedList: MutableState<List<Exercise>> = mutableStateOf(exercises)
    val checkList: State<List<Exercise>> = _checkedList


    fun setExerciseList(exerciseList: List<Exercise>) {
        _exerciseList.value = exerciseList
    }

    fun setSelectedExercise(exercise: Exercise) {
        _selectedExercise.value = exercise
    }

    fun onToggleAdd(idx: Int) {
        _exerciseList.value = _exerciseList.value.mapIndexed { index, exercise ->
            if(idx == index) {
                exercise.copy(addToWorkout = !exercise.addToWorkout)
            } else {
                exercise
            }
        }
        exercises = _exerciseList.value
    }

    fun onFilterExerciseList(name: String) {
        _exerciseList.value = exercises.filter { a -> a.name.contains(name, true) }
    }

    //Write a function that filters the exercises based on checked box
    fun filterCheckedExercises(exercises: List<Exercise>) {
        _checkedList.value = _exerciseList.value.filter { a -> a.addToWorkout }
    }
}