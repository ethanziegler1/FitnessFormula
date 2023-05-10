package edu.towson.cosc435vails.fitnessformula.data

import edu.towson.cosc435vails.fitnessformula.model.Exercise
import edu.towson.cosc435vails.fitnessformula.model.LibraryExercise

interface ILibraryRepository {

    suspend fun getExercises(): List<LibraryExercise>

    suspend fun addExercise(libraryExercise: LibraryExercise)
}