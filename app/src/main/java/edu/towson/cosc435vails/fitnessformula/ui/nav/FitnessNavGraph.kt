package edu.towson.cosc435vails.fitnessformula.ui.nav

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import edu.towson.cosc435vails.fitnessformula.model.Workout
import edu.towson.cosc435vails.fitnessformula.ui.SubmitDialog.SubmitViewModel
import edu.towson.cosc435vails.fitnessformula.ui.view.exerciselibrary.ExerciseLibraryView
import edu.towson.cosc435vails.fitnessformula.ui.view.exerciselibrary.ExerciseLibraryViewModel
import edu.towson.cosc435vails.fitnessformula.ui.view.exerciselist.ExerciseListView
import edu.towson.cosc435vails.fitnessformula.ui.view.exerciselist.ExerciseListViewModel
import edu.towson.cosc435vails.fitnessformula.ui.view.home.HomeView
import edu.towson.cosc435vails.fitnessformula.ui.view.savedWorkout.SavedWorkoutsView
import edu.towson.cosc435vails.fitnessformula.ui.view.savedWorkout.WorkoutDetailView
import edu.towson.cosc435vails.fitnessformula.ui.view.savedWorkout.WorkoutListScreen
import okhttp3.Route


@OptIn(ExperimentalFoundationApi::class)
@ExperimentalFoundationApi
@Composable
fun FitnessNavGraph(
    navController: NavHostController = rememberNavController()
) {
    val exerciseListViewModel: ExerciseListViewModel = viewModel()
    val submitDialogViewModel: SubmitViewModel = viewModel()
    NavHost(
        navController = navController,
        startDestination = Routes.Home.route
    ) {
        composable(Routes.Home.route) {
            HomeView(navController)
        }
        composable(Routes.ExerciseList.route) {

            // Place exercise list screen here
            ExerciseListView(
                exercises = exerciseListViewModel.exercises.value,
                selectedExercise = exerciseListViewModel.selectedExercise.value,
                onAddChecked = exerciseListViewModel::onToggleAdd,
                onFilter = exerciseListViewModel::onFilterExerciseList,
                onExerciseClicked = exerciseListViewModel::setSelectedExercise,
                onSubmit = exerciseListViewModel::filterCheckedExercises,
                navController = navController,
                submitViewModel = submitDialogViewModel,
                onFetchImage = exerciseListViewModel::fetchImage,
            )
        }
        composable(Routes.SavedWorkouts.route) {
            SavedWorkoutsView(
                exercises = exerciseListViewModel.checkList.value,
            )
        }
        composable(Routes.LibraryExercises.route) {
            val exerciseLibraryViewModel: ExerciseLibraryViewModel = viewModel()
            ExerciseLibraryView(
                exercises = exerciseLibraryViewModel.exercises.value,
                selectedExercise = exerciseLibraryViewModel.selectedExercise.value,
                onFilter = exerciseLibraryViewModel::onFilterExerciseList,
                onExerciseClicked = exerciseLibraryViewModel::setSelectedExercise,
                onFetchImage = exerciseLibraryViewModel::fetchImage
            )
        }
        composable(Routes.WorkoutList.route) {
            WorkoutListScreen(
                navController = navController,
                viewModel = exerciseListViewModel
//                workouts = exerciseListViewModel.workouts.value
            )
        }
        composable("${Routes.WorkoutDetail.route}/{workoutId}") { navBackStackEntry  ->
            val workoutId = navBackStackEntry.arguments?.getString("workoutId")?.toIntOrNull()
            Log.d("WorkoutDetailView", "Workout ID: $workoutId")
            if (workoutId != null) {
                WorkoutDetailView(
                    workoutId = workoutId,
                    viewModel = exerciseListViewModel
                )
            }
        }

    }
}
