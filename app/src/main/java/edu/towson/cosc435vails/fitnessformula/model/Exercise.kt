package edu.towson.cosc435vails.fitnessformula.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Exercises")
data class Exercise (
    @PrimaryKey
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