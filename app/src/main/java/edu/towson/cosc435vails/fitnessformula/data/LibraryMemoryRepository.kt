package edu.towson.cosc435vails.fitnessformula.data

import edu.towson.cosc435vails.fitnessformula.model.Exercise
import edu.towson.cosc435vails.fitnessformula.model.LibraryExercise

//class LibraryMemoryRepository(private var _exercises: List<LibraryExercise>): ILibraryRepository{
//
//    init {
//    }
//
//    override suspend fun getExercises(): List<LibraryExercise> {
//        return _exercises
//    }
//
//    override suspend fun addExercise(libraryExercise: LibraryExercise) {
//        _exercises = listOf(libraryExercise) + _exercises
//    }
//}