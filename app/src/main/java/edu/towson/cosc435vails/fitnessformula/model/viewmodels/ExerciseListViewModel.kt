package edu.towson.cosc435vails.fitnessformula.model.viewmodels

import edu.towson.cosc435vails.fitnessformula.model.Exercise

class ExerciseListViewModel {
    private var originalList: List<Exercise> = (0..10).map{i->
    Exercise("Exercise $i", "Exercise Description $i")
    }
}