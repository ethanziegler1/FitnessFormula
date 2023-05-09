package edu.towson.cosc435vails.fitnessformula.data

import edu.towson.cosc435vails.fitnessformula.model.Exercise

interface IExerciseRepository {

    suspend fun getExercises(): List<Exercise>

    suspend fun onToggleAdd(exercise: Exercise)

    suspend fun addExercise(exercise: Exercise)


}