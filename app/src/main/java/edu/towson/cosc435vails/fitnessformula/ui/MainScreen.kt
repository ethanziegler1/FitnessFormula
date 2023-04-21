package edu.towson.cosc435vails.fitnessformula.ui

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import edu.towson.cosc435vails.fitnessformula.ui.nav.NavGraph
import edu.towson.cosc435vails.fitnessformula.ui.nav.Routes

@Composable
fun MainScreen() {
    val nav = rememberNavController()
    Scaffold(
        topBar = { TopBar(nav) },
        bottomBar = { BottomBar(nav) }
    ) {
        //NavGraph(nav) //nav graph in this place crashes app for unknown reason
    }
}

@Composable
fun TopBar(nav: NavHostController) {
    TopAppBar(/*TODO This is wrong version or something)*/) {
        IconButton(
            onClick = {
                nav.navigate(Routes.Settings.route) {
                    popUpTo(Routes.Settings.route)
                }
            }
        ) {
            Icon(Icons.Default.Settings, contentDescription = "Settings Icon")
        }
    }
}

@Composable
fun BottomBar(nav: NavHostController) {
    val backStateEntry = nav.currentBackStackEntryAsState()
    val currentDestination = backStateEntry.value?.destination
    BottomAppBar(/*TODO This is wrong version or something)*/){
        BottomNavigationItem(
            selected = currentDestination?.route == Routes.WorkoutList.route,
            onClick = {
                nav.navigate(Routes.WorkoutList.route) {
                    popUpTo(Routes.WorkoutList.route)
                }
            },
            //TODO Get and icon
            icon = {Icon(Icons.Default.AddCircle, "")}
        )
        BottomNavigationItem(
            selected = currentDestination?.route == Routes.SavedWorkouts.route,
            onClick = {
                nav.navigate(Routes.SavedWorkouts.route) {
                    popUpTo(Routes.SavedWorkouts.route)
                }
            },
            //TODO Get and icon
            icon = {Icon(Icons.Default.List, "")}
        )

    }

}