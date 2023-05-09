package edu.towson.cosc435vails.fitnessformula.data

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.RoomDatabase
import androidx.room.Update
import edu.towson.cosc435vails.fitnessformula.model.Exercise

@Dao
interface ExerciseListDao {
    @Query("SELECT * FROM exercises")
    suspend fun getExercises(): List<Exercise>

    @Insert
    suspend fun addExercise(exercise: Exercise)

    @Delete
    suspend fun deleteExercise(exercise: Exercise)

    @Update
    suspend fun updateExercise(exercise: Exercise)

}

@Database(entities = [Exercise::class], version = 2, exportSchema = false)
abstract class ExerciseListDatabase : RoomDatabase() {
    abstract fun exerciseListDao(): ExerciseListDao
}