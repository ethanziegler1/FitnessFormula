package edu.towson.cosc435vails.fitnessformula.data

import androidx.room.Insert
import androidx.room.Query
import edu.towson.cosc435vails.fitnessformula.model.Exercise
import edu.towson.cosc435vails.fitnessformula.model.Workout
import kotlinx.coroutines.flow.Flow

interface IWorkoutRepository {

    suspend fun getWorkouts(): List<Workout>

    suspend fun addWorkout(workout: Workout)

    suspend fun getLastWorkout(): Workout?

    suspend fun getWorkoutByID(workoutId: Int): Workout?

}