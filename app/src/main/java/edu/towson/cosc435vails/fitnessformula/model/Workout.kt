package edu.towson.cosc435vails.fitnessformula.model

import edu.towson.cosc435vails.fitnessformula.ui.view.exerciselist.ExerciseListViewModel

data class Workout(
    val name: String,
    val exercises: ExerciseListViewModel
        ){
}