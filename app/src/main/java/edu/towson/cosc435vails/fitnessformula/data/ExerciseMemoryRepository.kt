package edu.towson.cosc435vails.fitnessformula.data

import androidx.compose.runtime.MutableState
import edu.towson.cosc435vails.fitnessformula.model.Exercise

class ExerciseMemoryRepository: IExerciseRepository {

    private var _exercises = listOf<Exercise>()

    private val exerciseNames = listOf("Push-ups", "Sit-ups", "Squats", "Lunges", "Plank")
    private val exerciseDescriptions = listOf(
        "Do push-ups with your hands on the ground",
        "Lie down and sit up, touching your toes",
        "Stand with your feet shoulder-width apart and squat down",
        "Step forward with one leg and bend the knee until the thigh is parallel to the ground",
        "Hold your body in a straight line from head to toe, supported by your forearms and toes"
    )
    
    init {
         _exercises = exerciseNames.mapIndexed { index, name ->
            Exercise(name, exerciseDescriptions[index], false)
        }
    }

    override fun getExercises(): List<Exercise> {
        return _exercises
    }

    override fun onToggleAdd(idx: Int) {
        val oldExercise = _exercises.get(idx)
        val newExercise = oldExercise.copy(addToWorkout = !oldExercise.addToWorkout)
        _exercises = _exercises.subList(0, idx) + listOf(newExercise) + _exercises.subList(idx+1, _exercises.size)
    }
}