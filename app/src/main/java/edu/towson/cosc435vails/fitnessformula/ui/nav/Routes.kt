package edu.towson.cosc435vails.fitnessformula.ui.nav

sealed class Routes(val route: String) {
    object Home : Routes ("home")
    object ExerciseList : Routes("exerciseList")
    object SavedWorkouts : Routes("savedWorkouts")

    object LibraryExercises : Routes("libraryExercises")

    object WorkoutList : Routes("workoutList")

    object WorkoutDetail : Routes("workoutDetail")

}