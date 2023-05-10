package edu.towson.cosc435vails.fitnessformula.data

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.Query
import androidx.room.RoomDatabase
import edu.towson.cosc435vails.fitnessformula.model.LibraryExercise


@Dao
interface LibraryListDao {

    @Query("SELECT * FROM library")
    suspend fun getExercises(): List<LibraryExercise>

    @Insert
    suspend fun addExercise(libraryExercise: LibraryExercise)


}

@Database(entities = [LibraryExercise::class], version = 2, exportSchema = false)
abstract class LibraryListDatabase : RoomDatabase() {
    abstract fun libraryListDao(): LibraryListDao
}