package edu.towson.cosc435vails.fitnessformula.ui.nav

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import edu.towson.cosc435vails.fitnessformula.model.Workout
import edu.towson.cosc435vails.fitnessformula.model.viewmodels.WorkoutListViewModel
import edu.towson.cosc435vails.fitnessformula.ui.view.SavedWorkoutsView
import edu.towson.cosc435vails.fitnessformula.ui.view.SettingsView
import edu.towson.cosc435vails.fitnessformula.ui.view.WorkoutView


@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController()
){
    val vm: WorkoutListViewModel = viewModel()
    NavHost(
        navController= navController,
        startDestination = Routes.WorkoutList.route
    ){
        composable(Routes.WorkoutList.route){
            val workouts by vm.workouts
            WorkoutView(workouts)
        }
        composable(Routes.SavedWorkouts.route){
            val savedWorkouts by vm.workouts
            SavedWorkoutsView(savedWorkouts)
        }
        composable(Routes.Settings.route){
            SettingsView()
        }

    }

}