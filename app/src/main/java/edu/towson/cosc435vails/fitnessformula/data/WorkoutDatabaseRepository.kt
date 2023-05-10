package edu.towson.cosc435vails.fitnessformula.data

import android.app.Application
import androidx.room.Room
import edu.towson.cosc435vails.fitnessformula.model.Exercise
import edu.towson.cosc435vails.fitnessformula.model.Workout

class WorkoutDatabaseRepository(app: Application) : IWorkoutRepository {

    private val db: WorkoutListDatabase

    init {
        db = Room.databaseBuilder(
            app,
            WorkoutListDatabase::class.java,
            "workouts.db"
        ).fallbackToDestructiveMigration()
            .build()
    }

    override suspend fun getWorkouts(): List<Workout> {
        return db.workoutDao().getWorkouts()
    }

    override suspend fun addWorkout(workout: Workout) {
        db.workoutDao().addWorkout(workout)
    }

    override suspend fun getLastWorkout(): Workout? {
        return db.workoutDao().getLastWorkout()
    }

    override suspend fun getWorkoutByID(workoutId: Int): Workout? {
        return db.workoutDao().getWorkoutById(workoutId)
    }

}