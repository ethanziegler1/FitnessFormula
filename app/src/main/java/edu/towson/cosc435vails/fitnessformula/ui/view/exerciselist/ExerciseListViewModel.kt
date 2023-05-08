package edu.towson.cosc435vails.fitnessformula.ui.view.exerciselist

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import edu.towson.cosc435vails.fitnessformula.data.ExerciseMemoryRepository
import edu.towson.cosc435vails.fitnessformula.data.IExerciseRepository
import edu.towson.cosc435vails.fitnessformula.model.Exercise

class ExerciseListViewModel: ViewModel() {

    private val _repository: IExerciseRepository = ExerciseMemoryRepository()

    private val _exerciseList: MutableState<List<Exercise>> = mutableStateOf(listOf())
    val exerciseList: State<List<Exercise>> = _exerciseList

    //Track currently selected exercise
    private val _selectedExercise: MutableState<Exercise?>
    val selectedExercise: State<Exercise?>

    //Track exercise to add to workout page
    private val _checkedList: MutableState<List<Exercise>> = mutableStateOf(listOf())
    val checkList: State<List<Exercise>> = _checkedList

    init {
        _exerciseList.value = _repository.getExercises()
        _selectedExercise = mutableStateOf(null)
        selectedExercise = _selectedExercise
        _checkedList.value = _repository.getExercises()
    }

    fun onToggleAdd(idx: Int) {
        _repository.onToggleAdd(idx)
        _exerciseList.value = _repository.getExercises()
    }

    fun setSelectedExercise(exercise: Exercise) {
        _selectedExercise.value = exercise
    }

    fun onFilterExerciseList(name: String) {
        _exerciseList.value = _repository.getExercises().filter { a -> a.name.contains(name, true) }
    }

    fun filterCheckedExercises(exercises: List<Exercise>) {
        _checkedList.value = _exerciseList.value.filter { a -> a.addToWorkout }

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
//        Exercise(name, exerciseDescriptions[index], false)
//    }

//    private val _exerciseList: MutableState<List<Exercise>> = mutableStateOf(exercises)
//    val exerciseList: State<List<Exercise>> = _exerciseList


//    //Track currently selected exercise
//    private val _selectedExercise: MutableState<Exercise?> = mutableStateOf(null)
//    val selectedExercise: State<Exercise?> = _selectedExercise

//    //Track exercise to add to workout page
//    private val _checkedList: MutableState<List<Exercise>> = mutableStateOf(exercises)
//    val checkList: State<List<Exercise>> = _checkedList


//    fun setExerciseList(exerciseList: List<Exercise>) {
//        _exerciseList.value = exerciseList
//    }

//    fun setSelectedExercise(exercise: Exercise) {
//        _selectedExercise.value = exercise
//    }

//    fun onToggleAdd(idx: Int) {
//        _exerciseList.value = _exerciseList.value.mapIndexed { index, exercise ->
//            if(idx == index) {
//                exercise.copy(addToWorkout = !exercise.addToWorkout)
//            } else {
//                exercise
//            }
//        }
//        exercises = _exerciseList.value
//    }

//    fun onToggleDelete(idx: Int) {
//        _checkedList.value = _checkedList.value.mapIndexed { index, exercise ->
//            if(idx == index) {
//                exercise.copy(addToWorkout = !exercise.addToWorkout)
//            } else {
//                exercise
//            }
//        }
//        exercises = _checkedList.value
//    }

//    fun onFilterExerciseList(name: String) {
//        _exerciseList.value = exercises.filter { a -> a.name.contains(name, true) }
//    }

//    //Write a function that filters the exercises based on checked box
//    fun filterCheckedExercises(exercises: List<Exercise>) {
//        _checkedList.value = _exerciseList.value.filter { a -> a.addToWorkout }
//
//    }
}