package edu.towson.cosc435vails.fitnessformula.ui.view.savedWorkout

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import edu.towson.cosc435vails.fitnessformula.model.Exercise
import edu.towson.cosc435vails.fitnessformula.model.Workout
import edu.towson.cosc435vails.fitnessformula.ui.nav.Routes
import edu.towson.cosc435vails.fitnessformula.ui.view.exerciselist.ExerciseListViewModel
import androidx.compose.runtime.collectAsState


@Composable
fun SavedWorkoutsView(
    exercises: List<Exercise>,
) {
    Column(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyColumn {
            itemsIndexed(exercises) { idx, exercise ->
                Column() {
                    Card(
                        shape = RoundedCornerShape(5.dp),
                        elevation = 16.dp,
                        modifier = Modifier
                            .padding(start = 16.dp, end = 16.dp, top = 5.dp, bottom = 5.dp)
                            .fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier.weight(1.5f)
                        ) {
                            Row(
                                modifier = Modifier.padding(5.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text("Name:", modifier = Modifier.weight(1.0f))
                                Text(
                                    exercise.name,
                                    modifier = Modifier.weight(2.0f),
                                    fontSize = 20.sp,
                                    color = MaterialTheme.colors.primary
                                )
                            }
                            Row(
                                modifier = Modifier.padding(5.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    "Description: ",
                                    modifier = Modifier.weight(1.0f),
                                    softWrap = false,
                                    overflow = TextOverflow.Ellipsis
                                )
                                Text(exercise.description, modifier = Modifier.weight(2.0f))
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun WorkoutListScreen(
    navController: NavController,
    viewModel: ExerciseListViewModel
) {


    val workoutsState = viewModel.workouts.collectAsState()
    val workouts = workoutsState.value

    LaunchedEffect(true) {
        viewModel.loadWorkouts()
    }

    LazyColumn() {
        items(workouts) { workout ->
            WorkoutListItem(
                workout = workout,
                onClick = {
                    navController.navigate("${Routes.WorkoutDetail.route}/${workout.id}")
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun WorkoutListItem(workout: Workout, onClick: () -> Unit) {
    ListItem(
        modifier = Modifier.clickable(onClick = onClick),
        text = { Text("Workout ${workout.id}") }
    )
}



@Composable
fun WorkoutDetailView(
    workoutId: Int,
    viewModel: ExerciseListViewModel,
) {

    var workout by remember { mutableStateOf<Workout?>(null) }

    LaunchedEffect(key1 = workoutId) {
        viewModel.getWorkoutById(workoutId)?.let { workout = it }
    }

    workout?.let { workout ->
        val exercises = workout.exercise

    Column(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
            LazyColumn {
                itemsIndexed(exercises) { idx, exercise ->
                    Column() {
                        Card(
                            shape = RoundedCornerShape(5.dp),
                            elevation = 16.dp,
                            modifier = Modifier
                                .padding(start = 16.dp, end = 16.dp, top = 5.dp, bottom = 5.dp)
                                .fillMaxWidth()
                        ) {
                            Column(
                                modifier = Modifier.weight(1.5f)
                            ) {
                                Row(
                                    modifier = Modifier.padding(5.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text("Name:", modifier = Modifier.weight(1.0f))
                                    Text(
                                        exercise.name,
                                        modifier = Modifier.weight(2.0f),
                                        fontSize = 20.sp,
                                        color = MaterialTheme.colors.primary
                                    )
                                }
                                Row(
                                    modifier = Modifier.padding(5.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        "Description: ",
                                        modifier = Modifier.weight(1.0f),
                                        softWrap = false,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                    Text(exercise.description, modifier = Modifier.weight(2.0f))
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}



