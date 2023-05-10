package edu.towson.cosc435vails.fitnessformula.data

import androidx.compose.runtime.MutableState
import edu.towson.cosc435vails.fitnessformula.model.Exercise

class ExerciseMemoryRepository(private var _exercises: List<Exercise>): IExerciseRepository {

//    private var _exercises = listOf<Exercise>()

//    private val exerciseNames = listOf("Push-ups", "Sit-ups", "Squats", "Lunges", "Plank")
//    private val exerciseDescriptions = listOf(
//        "Do push-ups with your hands on the ground",
//        "Lie down and sit up, touching your toes",
//        "Stand with your feet shoulder-width apart and squat down",
//        "Step forward with one leg and bend the knee until the thigh is parallel to the ground",
//        "Hold your body in a straight line from head to toe, supported by your forearms and toes"
//    )
    
    init {
    }

    override suspend fun getExercises(): List<Exercise> {
        return _exercises
    }

    override suspend fun onToggleAdd(exercise: Exercise) {
        _exercises = _exercises.map { e ->
            if(e.name == exercise.name) {
                exercise.copy(addToWorkout = !exercise.addToWorkout)
            } else {
                e
            }
        }
//        val oldExercise = _exercises.get(idx)
//        val newExercise = oldExercise.copy(addToWorkout = !oldExercise.addToWorkout)
//        _exercises = _exercises.subList(0, idx) + listOf(newExercise) + _exercises.subList(idx+1, _exercises.size)
    }

    override suspend fun addExercise(exercise: Exercise) {
        _exercises = listOf(exercise) + _exercises
    }

    override suspend fun clearDatabase() {
        TODO("Not yet implemented")
    }

    override suspend fun getExerciseByName(name: String): Exercise? {
        TODO("Not yet implemented")
    }
}