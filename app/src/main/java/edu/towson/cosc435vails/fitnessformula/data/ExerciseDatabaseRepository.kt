package edu.towson.cosc435vails.fitnessformula.data

import android.app.Application
import android.util.Log
import androidx.room.Room
import edu.towson.cosc435vails.fitnessformula.model.Exercise
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ExerciseDatabaseRepository(app: Application) : IExerciseRepository {

    private val db: ExerciseListDatabase

    init {
        Log.d("ExerciseDatabaseRepo", "Initializing database")
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
        val existingExercise = withContext(Dispatchers.IO) {
            db.exerciseListDao().getExerciseByName(exercise.name)
        }
        if (existingExercise == null) {
            withContext(Dispatchers.IO) {
                db.exerciseListDao().addExercise(exercise)
            }
        } else {
            Log.d("ExerciseDatabaseRepo", "Exercise with id ${exercise.id} already exists")
        }
    }

    override suspend fun clearDatabase() {
        db.exerciseListDao().clearDatabase()
    }

    override suspend fun getExerciseByName(name: String): Exercise? {
        return db.exerciseListDao().getExerciseByName(name)
    }


}