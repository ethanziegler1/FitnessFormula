package edu.towson.cosc435vails.fitnessformula.data

import androidx.room.*
import edu.towson.cosc435vails.fitnessformula.model.Exercise
import java.util.*

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

    @Query("DELETE FROM exercises")
    suspend fun clearDatabase()

    @Query("SELECT * FROM exercises WHERE name = :name LIMIT 1")
    fun getExerciseByName(name: String): Exercise?

}


@Database(entities = [Exercise::class], version = 2, exportSchema = false)
abstract class ExerciseListDatabase : RoomDatabase() {
    abstract fun exerciseListDao(): ExerciseListDao
}