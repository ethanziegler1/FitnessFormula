package edu.towson.cosc435vails.fitnessformula.data

import android.app.Application
import androidx.room.Room
import edu.towson.cosc435vails.fitnessformula.model.LibraryExercise

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
        db.libraryListDao().addExercise(libraryExercise)
    }
}