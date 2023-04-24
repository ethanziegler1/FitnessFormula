package edu.towson.cosc435vails.fitnessformula.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import edu.towson.cosc435vails.fitnessformula.ui.nav.FitnessNavGraph
import edu.towson.cosc435vails.fitnessformula.ui.nav.Routes

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@Composable
fun MainScreen() {
    val nav = rememberNavController()
    Scaffold(
        topBar = {
            TopBar()
        },
        bottomBar = {
            BottomBar(nav = nav)
        }
    ) {
        FitnessNavGraph(nav)
    }
}

@Composable
private fun TopBar() {
    TopAppBar(
        title = { Text("Fitness Formula") },
    )
}

@Composable
private fun BottomBar(
    nav: NavHostController
) {
    BottomNavigation(
        elevation = 16.dp
    ) {
        val backStack by nav.currentBackStackEntryAsState()
        val destination = backStack?.destination
        BottomNavigationItem(
            selected = destination?.route == Routes.ExerciseList.route,
            onClick = {
                nav.navigate(Routes.ExerciseList.route) {
                    launchSingleTop = true
                    popUpTo(Routes.ExerciseList.route) { inclusive = false}
                }
            },
            icon = {
                Icon(Icons.Rounded.AddCircle, "")
            },
            label = {
                Text("Exercises")
            }
        )
        BottomNavigationItem(
            selected = destination?.route == Routes.Home.route,
            onClick = {
                nav.navigate(Routes.Home.route) {
                    launchSingleTop = true
                }
            },
            icon = {
                Icon(Icons.Default.Home, "")
            },
            label = {
                Text("Home")
            }
        )
        BottomNavigationItem(
            selected = destination?.route == Routes.SavedWorkouts.route,
            onClick = {
                nav.navigate(Routes.SavedWorkouts.route) {
                    launchSingleTop = true
                }
            },
            icon = {
                Icon(Icons.Default.Star, "")
            },
            label = {
                Text("Workout")
            }
        )
    }
}