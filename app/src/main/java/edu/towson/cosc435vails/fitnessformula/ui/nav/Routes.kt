package edu.towson.cosc435vails.fitnessformula.ui.nav

sealed class Routes(val route: String) {
    object WorkoutList : Routes("workoutList")
    object Settings : Routes("settings")
    object SavedWorkouts : Routes("savedWorkouts")
    object Home : Routes ("home")
    object ExerciseList : Routes("exerciseList")
}