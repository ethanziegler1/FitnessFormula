package edu.towson.cosc435vails.fitnessformula.ui.view.exerciselist

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import edu.towson.cosc435vails.fitnessformula.model.Exercise
import edu.towson.cosc435vails.fitnessformula.ui.ExerciseListRow
import edu.towson.cosc435vails.fitnessformula.ui.LandscapeView
import edu.towson.cosc435vails.fitnessformula.ui.SearchBar
import edu.towson.cosc435vails.fitnessformula.ui.SubmitDialog.SubmitDialog
import edu.towson.cosc435vails.fitnessformula.ui.SubmitDialog.SubmitViewModel
import edu.towson.cosc435vails.fitnessformula.ui.nav.Routes
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@ExperimentalFoundationApi
@Composable
fun ExerciseListView(
    exercises: List<Exercise>,
    selectedExercise: Exercise?,
    onAddChecked: (Int) -> Unit,
    onFilter: (String) -> Unit,
    onExerciseClicked: (Exercise) -> Unit,
    navController: NavController,
    onSubmit: (List<Exercise>) -> Unit,
    submitViewModel: SubmitViewModel
) {

    val configuration = LocalConfiguration.current
    if(configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
        LandscapeView(selectedExercise = selectedExercise ) {
            Column(
            ) {
                SubmitDialog(title = "Confirm",
                    text = "Are you sure you want to add these exercises to a workout?",
                    submitViewModel = submitViewModel,
                    onSubmit = {
                        onSubmit(exercises)
                        navController.navigate(Routes.SavedWorkouts.route) {
                            launchSingleTop = true
                            popUpTo(Routes.SavedWorkouts.route) { inclusive = false }
                        }
                    },
                    onCancel = {}
                )
                SearchBar(onFilter = onFilter)
                Button(
                    onClick = { submitViewModel.showDialog() },
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(text = "Submit")
                }
                LazyColumn {
                    itemsIndexed(exercises) {idx, exercise ->
                        ExerciseListRow(
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
        Column {
            SubmitDialog(title = "Confirm",
                text = "Are you sure you want to submit",
                submitViewModel = submitViewModel,
                onSubmit = {
                        onSubmit(exercises)
                        navController.navigate(Routes.SavedWorkouts.route) {
                            launchSingleTop = true
                            popUpTo(Routes.SavedWorkouts.route) { inclusive = false }
                        }
                },
                onCancel = {}
            )
            SearchBar(onFilter = onFilter)
            Button(
                onClick = { submitViewModel.showDialog() },
                modifier = Modifier.padding(16.dp)
            ) {
                Text(text = "Submit")
            }
            LazyColumn {
                itemsIndexed(exercises) {idx, exercise ->
                    ExerciseListRow(
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
