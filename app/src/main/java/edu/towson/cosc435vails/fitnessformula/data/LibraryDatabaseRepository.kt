package edu.towson.cosc435vails.fitnessformula.data

import android.app.Application
import android.util.Log
import androidx.room.Room
import edu.towson.cosc435vails.fitnessformula.model.Exercise
import edu.towson.cosc435vails.fitnessformula.model.LibraryExercise
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
class LibraryDatabaseRepository(app: Application) : ILibraryRepository {

    private val db: LibraryListDatabase

    init {
        db = Room.databaseBuilder(
            app,
            LibraryListDatabase::class.java,
            "libraryExercises.db"
        ).fallbackToDestructiveMigration()
            .build()
    }


    override suspend fun getExercises(): List<LibraryExercise> {
        return db.libraryListDao().getExercises()
    }

    override suspend fun addExercise(libraryExercise: LibraryExercise) {

        Log.d("LibraryExerciseDatabase", "Adding exercise: $libraryExercise")
        val existingExercise = withContext(Dispatchers.IO) {
            db.libraryListDao().getExerciseByName(libraryExercise.name)
        }
        if (existingExercise == null) {
            withContext(Dispatchers.IO) {
                db.libraryListDao().addExercise(libraryExercise)
            }
        } else {
            Log.d("LibraryExerciseRepo", "Exercise with id ${libraryExercise.id} already exists")
        }
    }

    override suspend fun getExerciseByName(name: String): LibraryExercise? {
        return db.libraryListDao().getExerciseByName(name)
    }


}