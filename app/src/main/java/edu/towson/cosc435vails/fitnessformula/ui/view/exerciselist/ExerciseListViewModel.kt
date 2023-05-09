package edu.towson.cosc435vails.fitnessformula.ui.view.exerciselist

import android.app.Application
import android.graphics.Bitmap
import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import edu.towson.cosc435vails.fitnessformula.data.ExerciseDatabaseRepository
import edu.towson.cosc435vails.fitnessformula.data.ExerciseMemoryRepository
import edu.towson.cosc435vails.fitnessformula.data.IExerciseRepository
import edu.towson.cosc435vails.fitnessformula.model.Exercise
import edu.towson.cosc435vails.fitnessformula.network.ExerciseFetcher
import kotlinx.coroutines.launch
import java.lang.Exception


class ExerciseListViewModel(app: Application): AndroidViewModel(app) {

    private val _exercises: MutableState<List<Exercise>> = mutableStateOf(listOf())
    val exercises: State<List<Exercise>> = _exercises

    //Initialize exercise fetcher from json db, and repository
    private val _exerciseFetcher = ExerciseFetcher(getApplication())
    private lateinit var _repository: IExerciseRepository

    //Track currently selected exercise
    private val _selectedExercise: MutableState<Exercise?>
    val selectedExercise: State<Exercise?>

    private val _waiting: MutableState<Boolean> = mutableStateOf(false)
    val waiting: State<Boolean> = _waiting

    //Track exercise to add to workout page
    private val _checkedList: MutableState<List<Exercise>> = mutableStateOf(listOf())
    val checkList: State<List<Exercise>> = _checkedList


    init {
        viewModelScope.launch {
            _waiting.value = true
            _repository = ExerciseDatabaseRepository(getApplication())
            try {
                val exerciseList = _exerciseFetcher.fetchExercises()
                exerciseList.forEach {exercise -> _repository.addExercise(exercise) }
            } catch (e: Exception) {
                Log.e(this@ExerciseListViewModel.javaClass.simpleName, e.message, e)
            }
            _exercises.value = _repository.getExercises()
            _waiting.value = false
        }
        _selectedExercise = mutableStateOf(null)
        selectedExercise = _selectedExercise
    }

    fun onToggleAdd(exercise: Exercise) {
        viewModelScope.launch {
            _repository.onToggleAdd(exercise)
            _exercises.value = _repository.getExercises()
        }
    }

    fun setSelectedExercise(exercise: Exercise) {
        _selectedExercise.value = exercise
    }

    fun onFilterExerciseList(name: String) {
        viewModelScope.launch {
            _exercises.value = _repository.getExercises().filter { a -> a.name.contains(name, true) }
        }
    }

    fun filterCheckedExercises(exercises: List<Exercise>) {
        _checkedList.value = _exercises.value.filter { a -> a.addToWorkout }

    }

    suspend fun fetchImage(url: String): Bitmap? {
        return try {
            _exerciseFetcher.fetchImage(url)
        } catch (e: Exception) {
            null
        }
    }

}