package edu.towson.cosc435vails.fitnessformula.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import edu.towson.cosc435vails.fitnessformula.ui.view.savedWorkout.RepsViewModel
import edu.towson.cosc435vails.fitnessformula.ui.view.savedWorkout.SetsViewModel

@Entity(tableName = "workouts")
data class Workout(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo
    val exercise: List<Exercise>
) {
}