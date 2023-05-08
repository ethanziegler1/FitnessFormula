package edu.towson.cosc435vails.fitnessformula.ui.nav

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import edu.towson.cosc435vails.fitnessformula.ui.SubmitDialog.SubmitViewModel
import edu.towson.cosc435vails.fitnessformula.ui.view.exerciselibrary.ExerciseLibraryView
import edu.towson.cosc435vails.fitnessformula.ui.view.exerciselibrary.ExerciseLibraryViewModel
import edu.towson.cosc435vails.fitnessformula.ui.view.exerciselist.ExerciseListView
import edu.towson.cosc435vails.fitnessformula.ui.view.exerciselist.ExerciseListViewModel
import edu.towson.cosc435vails.fitnessformula.ui.view.home.HomeView
import edu.towson.cosc435vails.fitnessformula.ui.view.savedWorkout.SavedWorkoutsView


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
                exercises = exerciseListViewModel.exerciseList.value,
                selectedExercise = exerciseListViewModel.selectedExercise.value,
                onAddChecked = exerciseListViewModel::onToggleAdd,
                onFilter = exerciseListViewModel::onFilterExerciseList,
                onExerciseClicked = exerciseListViewModel::setSelectedExercise,
                onSubmit = exerciseListViewModel::filterCheckedExercises,
                navController = navController,
                submitViewModel = submitDialogViewModel
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
                exercises = exerciseLibraryViewModel.exerciseList.value,
                selectedExercise = exerciseLibraryViewModel.selectedExercise.value,
                onFilter = exerciseLibraryViewModel::onFilterExerciseList,
                onExerciseClicked = exerciseLibraryViewModel::setSelectedExercise
            )
        }
    }
}