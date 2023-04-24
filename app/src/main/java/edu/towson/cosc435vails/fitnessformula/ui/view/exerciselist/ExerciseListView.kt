package edu.towson.cosc435vails.fitnessformula.ui.view.exerciselist

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import edu.towson.cosc435vails.fitnessformula.model.Exercise
import edu.towson.cosc435vails.fitnessformula.ui.ExerciseRow
import edu.towson.cosc435vails.fitnessformula.ui.LandscapeView
import edu.towson.cosc435vails.fitnessformula.ui.SearchBar
import edu.towson.cosc435vails.fitnessformula.ui.nav.Routes

@ExperimentalFoundationApi
@Composable
fun ExerciseListView(
    exercises: List<Exercise>,
    selectedExercise: Exercise?,
    onAddChecked: (Int) -> Unit,
    onFilter: (String) -> Unit,
    onExerciseClicked: (Exercise) -> Unit,
    navController: NavController,
    onSubmit: (List<Exercise>) -> Unit
) {
    val configuration = LocalConfiguration.current
    if(configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
        LandscapeView(selectedExercise = selectedExercise ) {
            Column(
            ) {
                SearchBar(onFilter = onFilter)
                LazyColumn {
                    itemsIndexed(exercises) {idx, exercise ->
                        ExerciseRow(
                            idx = idx,
                            exercise = exercise,
                            onAddChecked = onAddChecked,
                            onExerciseClicked = onExerciseClicked
                        )
                    }
                }
            }
        }
    } else {
        Column(
        ) {
            SearchBar(onFilter = onFilter)
            Button(
                onClick = { onSubmit(exercises)
                    navController.navigate(Routes.SavedWorkouts.route) {
                        launchSingleTop = true
                        popUpTo(Routes.SavedWorkouts.route) { inclusive = false }
                    }
                },
                modifier = Modifier.padding(16.dp)
            ) {
                Text(text = "Submit")
            }
            LazyColumn {
                itemsIndexed(exercises) {idx, exercise ->
                    ExerciseRow(
                        idx = idx,
                        exercise = exercise,
                        onAddChecked = onAddChecked,
                        onExerciseClicked = onExerciseClicked
                    )
                }
            }
        }
    }
}