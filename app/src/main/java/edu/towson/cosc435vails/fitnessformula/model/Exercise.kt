package edu.towson.cosc435vails.fitnessformula.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity(tableName = "Exercises")
data class Exercise (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo
    val name: String,
    @ColumnInfo
    val description: String,
    @ColumnInfo
    var addToWorkout: Boolean,
    @ColumnInfo
    @SerializedName("icon_url")
    val iconURL: String? = null
    ){

}