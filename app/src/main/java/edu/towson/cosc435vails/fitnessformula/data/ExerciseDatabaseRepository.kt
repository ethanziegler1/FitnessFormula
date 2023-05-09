package edu.towson.cosc435vails.fitnessformula.data

import android.app.Application
import androidx.room.Room
import edu.towson.cosc435vails.fitnessformula.model.Exercise

class ExerciseDatabaseRepository(app: Application) : IExerciseRepository {

    private val db: ExerciseListDatabase

    init {
        db = Room.databaseBuilder(
            app,
            ExerciseListDatabase::class.java,
            "exercises.db"
        ).fallbackToDestructiveMigration()
            .build()
    }

    override suspend fun getExercises(): List<Exercise> {
        return db.exerciseListDao().getExercises()
    }

    override suspend fun onToggleAdd(exercise: Exercise) {
        db.exerciseListDao().updateExercise(exercise.copy(addToWorkout = !exercise.addToWorkout))
    }

    override suspend fun addExercise(exercise: Exercise) {
        db.exerciseListDao().addExercise(exercise)
    }
}