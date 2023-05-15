package edu.towson.cosc435vails.fitnessformula.ui.view.exerciselist

import android.app.Application
import android.graphics.Bitmap
import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import edu.towson.cosc435vails.fitnessformula.data.ExerciseDatabaseRepository
import edu.towson.cosc435vails.fitnessformula.data.IExerciseRepository
import edu.towson.cosc435vails.fitnessformula.data.IWorkoutRepository
import edu.towson.cosc435vails.fitnessformula.data.WorkoutDatabaseRepository
import edu.towson.cosc435vails.fitnessformula.model.Exercise
import edu.towson.cosc435vails.fitnessformula.model.Workout
import edu.towson.cosc435vails.fitnessformula.network.ExerciseFetcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
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

    private lateinit var repositoryW: IWorkoutRepository

//    private val _workouts: MutableState<List<Workout>> = mutableStateOf(listOf())
//    val workouts: State<List<Workout>> = _workouts

    private val _workouts: MutableStateFlow<List<Workout>> = MutableStateFlow(emptyList())
    val workouts: StateFlow<List<Workout>> = _workouts.asStateFlow()


    private var isDatabasePopulated = false


    // Fetch the database from custom API
    init {
        viewModelScope.launch {
            _waiting.value = true
            _repository = ExerciseDatabaseRepository(getApplication())
            repositoryW = WorkoutDatabaseRepository(getApplication())

            if(!isDatabasePopulated) {
                try {
                    val exerciseList = _exerciseFetcher.fetchExercises()
                    exerciseList.forEach { exercise -> _repository.addExercise(exercise) }
                } catch (e: Exception) {
                    Log.e(this@ExerciseListViewModel.javaClass.simpleName, e.message, e)
                }
                isDatabasePopulated = true
            }
            _exercises.value = _repository.getExercises()
            _workouts.value = repositoryW.getWorkouts()
            _waiting.value = false
        }
        _selectedExercise = mutableStateOf(null)
        selectedExercise = _selectedExercise
    }

    fun loadWorkouts() {
        viewModelScope.launch {
            _workouts.value = repositoryW.getWorkouts()
        }
    }
    override fun onCleared() {
        super.onCleared()
        viewModelScope.launch {
            _repository.clearDatabase()
        }
    }

    // Handles state for add checkbox
    fun onToggleAdd(exercise: Exercise) {
        viewModelScope.launch {
            _repository.onToggleAdd(exercise)
            _exercises.value = _repository.getExercises()
        }
    }

    // handles if the user clicks on a card
    fun setSelectedExercise(exercise: Exercise) {
        _selectedExercise.value = exercise
    }

    // Handles search
    fun onFilterExerciseList(name: String) {
        viewModelScope.launch {
            _exercises.value = _repository.getExercises().filter { a -> a.name.contains(name, true) }
        }
    }

    // Filter out exercises the user wants for workout
    fun filterCheckedExercises(exercises: List<Exercise>) {
        _checkedList.value = _exercises.value.filter { a -> a.addToWorkout }
        viewModelScope.launch {
            val workout = Workout(id = getNextWorkoutId(), exercise = _checkedList.value)
            repositoryW.addWorkout(workout)
        }
    }

    //Get images from the API
    suspend fun fetchImage(url: String): Bitmap? {
        return try {
            _exerciseFetcher.fetchImage(url)
        } catch (e: Exception) {
            null
        }
    }

    //Obtain the next workoutID to grab specific workouts
    private suspend fun getNextWorkoutId(): Int {
        val lastWorkout = repositoryW.getLastWorkout()
        return if (lastWorkout != null) {
            lastWorkout.id + 1
        } else {
            1
        }
    }

    //Grab the specific workout
    suspend fun getWorkoutById(workoutId: Int): Workout? {
        return repositoryW.getWorkoutByID(workoutId)
    }

    //Delete a workout
    suspend fun deleteWorkout(workoutId: Int) {
        viewModelScope.launch {
            val workoutToDelete = repositoryW.getWorkoutByID(workoutId)
            workoutToDelete?.let {
                repositoryW.deleteWorkout(it)
                _workouts.value = repositoryW.getWorkouts()
            }
        }
    }




}