package edu.towson.cosc435vails.fitnessformula.model.viewmodels

import androidx.lifecycle.ViewModel
import edu.towson.cosc435vails.fitnessformula.model.Workout
import edu.towson.cosc435vails.fitnessformula.model.Exercise

class WorkoutListViewModel(exerciseList: ExerciseListViewModel) : ViewModel(){
    private var originalList : List<Workout> = (0..50).map{i ->
        Workout("Workout $i", exerciseList )
    }
}