package edu.towson.cosc435vails.fitnessformula.ui.view.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import edu.towson.cosc435vails.fitnessformula.ui.nav.Routes

@Composable
fun HomeView(navController: NavController) {
    Column(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Fitness Formula",
            color = MaterialTheme.colors.primary,
            fontSize = 45.sp,
        )
        Button(
            onClick = {
                navController.navigate(Routes.ExerciseList.route) {
                    launchSingleTop = true
                    popUpTo(Routes.ExerciseList.route) { inclusive = false }
                }
            },
            modifier = Modifier.padding(20.dp)
        ) {
            Text("New Workouts")
        }

    }
}