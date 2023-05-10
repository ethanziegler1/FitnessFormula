package edu.towson.cosc435vails.fitnessformula.data

import androidx.room.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import edu.towson.cosc435vails.fitnessformula.model.Exercise
import edu.towson.cosc435vails.fitnessformula.model.Workout
import java.lang.reflect.Type

@Dao
interface WorkoutDao {

    @Query("SELECT * FROM workouts")
    suspend fun getWorkouts(): List<Workout>

    @Insert
    suspend fun addWorkout(workout: Workout)

    @Query("SELECT * FROM workouts ORDER BY id DESC LIMIT 1")
    suspend fun getLastWorkout(): Workout?

    @Query("SELECT * FROM workouts WHERE id = :workoutId")
    suspend fun getWorkoutById(workoutId: Int): Workout?

}

public class DataConverter {

    @TypeConverter
    fun fromExerciseList(exercise: List<Exercise>): String? {
        val gson = Gson()
        val type: Type = object : TypeToken<List<Exercise?>?>() {}.type
        return gson.toJson(exercise, type)
    }

    @TypeConverter
    fun toExerciseList(exerciseString: String?): List<Exercise>? {
        val gson = Gson()
        val type = object : TypeToken<List<Exercise?>?>() {}.type
        return gson.fromJson<List<Exercise>>(exerciseString, type)
    }

}


@Database(entities = [Workout::class], version = 2, exportSchema = false)
@TypeConverters(DataConverter::class)
abstract class WorkoutListDatabase : RoomDatabase() {
    abstract fun workoutDao(): WorkoutDao
}