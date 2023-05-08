package edu.towson.cosc435vails.fitnessformula.data

import edu.towson.cosc435vails.fitnessformula.model.Exercise

interface IExerciseRepository {

    fun getExercises(): List<Exercise>

    fun onToggleAdd(idx: Int)


}