package edu.towson.cosc435vails.fitnessformula.ui.view.exerciselibrary

import android.app.Application
import android.graphics.Bitmap
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import edu.towson.cosc435vails.fitnessformula.data.ILibraryRepository
import edu.towson.cosc435vails.fitnessformula.data.LibraryDatabaseRepository
import edu.towson.cosc435vails.fitnessformula.model.LibraryExercise
import edu.towson.cosc435vails.fitnessformula.network.ExerciseFetcher
import kotlinx.coroutines.launch
import java.lang.Exception


// View model for Exercise Library fetches its own repository
class ExerciseLibraryViewModel(app: Application): AndroidViewModel(app) {

    private val _exercises: MutableState<List<LibraryExercise>> = mutableStateOf(listOf())
    val exercises: State<List<LibraryExercise>> = _exercises

    //Initialize exercise fetcher from json db, and repository
    private val _exerciseFetcher = ExerciseFetcher(getApplication())
    private lateinit var _repository: ILibraryRepository

    //Track currently selected exercise
    private val _selectedExercise: MutableState<LibraryExercise?>
    val selectedExercise: State<LibraryExercise?>

    private val _waiting: MutableState<Boolean> = mutableStateOf(false)
    val waiting: State<Boolean> = _waiting



    init {
        viewModelScope.launch {
            _waiting.value = true
            _repository = LibraryDatabaseRepository(getApplication())
            try {
                val exerciseList = _exerciseFetcher.fetchLibraryExercises()
                exerciseList.forEach {exercise -> _repository.addExercise(exercise)}
            } catch (e: Exception) {
                Log.e(this@ExerciseLibraryViewModel.javaClass.simpleName, e.message, e)
            }
            _exercises.value = _repository.getExercises()
            _waiting.value = false
        }
        _selectedExercise = mutableStateOf(null)
        selectedExercise = _selectedExercise
    }


    fun setSelectedExercise(libraryExercise: LibraryExercise) {
        _selectedExercise.value = libraryExercise
    }

    fun onFilterExerciseList(name: String) {
        viewModelScope.launch {
            _exercises.value = _repository.getExercises().filter { a -> a.name.contains(name, true) }
        }
    }

    suspend fun fetchImage(url: String): Bitmap? {
        return try {
            _exerciseFetcher.fetchImage(url)
        } catch (e: Exception) {
            null
        }
    }
}