package edu.towson.cosc435vails.fitnessformula.ui.view.savedWorkout

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import edu.towson.cosc435vails.fitnessformula.model.Exercise
import edu.towson.cosc435vails.fitnessformula.model.Workout
import edu.towson.cosc435vails.fitnessformula.ui.nav.Routes
import edu.towson.cosc435vails.fitnessformula.ui.view.exerciselist.ExerciseListViewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import edu.towson.cosc435vails.fitnessformula.ui.DeleteDialog.DeleteDialog
import edu.towson.cosc435vails.fitnessformula.ui.DeleteDialog.DeleteViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch


// Initial view of the created workout from user. Navigated to the bottom right nav button.
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

// Lazy column of saved workouts via "Workout List"
@Composable
fun WorkoutListScreen(
    navController: NavController,
    viewModel: ExerciseListViewModel,
    workoutListVm: SavedWorkoutsViewModel,
    deleteViewModel: DeleteViewModel
) {

    val workoutsState = viewModel.workouts.collectAsState()
    val workouts = workoutsState.value

    LaunchedEffect(true) {
        viewModel.loadWorkouts()
    }

    LazyColumn() {
        itemsIndexed(workouts) { index, workout ->
            WorkoutListItem(
                workout = workout,
                onClick = {
                    navController.navigate("${Routes.WorkoutDetail.route}/${workout.id}")
                },
                workoutViewModel = viewModel,
                viewModel = workoutListVm.apply { setWorkoutName("") },
                index = index,
                deleteViewModel = deleteViewModel
            )
        }
    }
}


//Singular Saved Workout List Item
@Composable
fun WorkoutListItem(
    workout: Workout,
    onClick: () -> Unit,
    workoutViewModel: ExerciseListViewModel,
    viewModel: SavedWorkoutsViewModel,
    index: Int,
    deleteViewModel: DeleteViewModel
) {
    val coroutineScope = rememberCoroutineScope()

    Card(
        shape = RoundedCornerShape(5.dp),
        elevation = 16.dp,
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, top = 5.dp, bottom = 5.dp)
            .fillMaxWidth()
            .clickable(onClick = onClick)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            //Delete Dialog in case the button is mistakenly clicked
            DeleteDialog(
                title = "Delete Workout",
                text = "Are you sure you want to delete this workout?",
                onSubmit = {
                    //Delete Workout if yes
                    coroutineScope.launch {
                        workoutViewModel.deleteWorkout(workout.id)
                        viewModel.updateWorkoutName(index, "")
                    }
                    deleteViewModel.hideDialog()
                           },
                onCancel = {
                           deleteViewModel.hideDialog()
                },
                deleteViewModel = deleteViewModel
            )
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Workout ${workout.id}",
                    fontSize = 20.sp,
                    modifier = Modifier.padding(16.dp),
                    fontFamily = FontFamily.SansSerif
                )
                Button(
                    onClick = {
                        deleteViewModel.showDialog()
                        },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 5.dp)
                ) {
                    Text(text = "Delete")
                }
            }
            Column(
                modifier = Modifier
                    .weight(2f)
                    .padding(vertical = 8.dp, horizontal = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Text field to provide name to workout
                TextField(
                    value = viewModel.workoutName.getOrNull(index) ?: "",
                    onValueChange = {newName ->
                        if (index < viewModel.workoutName.size) {
                            viewModel.updateWorkoutName(index, newName)
                        } else {
                            viewModel.setWorkoutName(newName)
                        }
                                    },
                    label = { Text(text = "Workout Name") },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "Edit Icon" )
                    }
                )
            }
        }
    }
}



//Displays the lazy column of Exercises in each custom workout
@Composable
fun WorkoutDetailView(
    workoutId: Int,
    viewModel: ExerciseListViewModel,
    workoutDetailVm: SavedWorkoutsViewModel,
    setsVm: SetsViewModel,
    repsVm: RepsViewModel
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
                    // create a new ViewModelProvider for each card
                    val localViewModelStore = LocalViewModelStoreOwner.current
                    val setsVm = localViewModelStore?.let { ViewModelProvider(it)[setsVm::class.java] }
                    val repsVm = localViewModelStore?.let { ViewModelProvider(it)[repsVm::class.java] }

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
                                        fontSize = 22.sp,
                                        color = MaterialTheme.colors.primary
                                    )
                                    Text(text = "Complete")
                                    Checkbox(
                                        checked = workoutDetailVm.checkedState.getOrNull(idx) ?: false,
                                        onCheckedChange = { newValue ->
                                            if (idx < workoutDetailVm.checkedState.size) {
                                                workoutDetailVm.updateCheckedState(idx, newValue)
                                            } else {
                                                workoutDetailVm.setCheckedState(newValue)
                                            }
                                        },
                                        colors = CheckboxDefaults.colors(
                                            checkedColor = MaterialTheme.colors.primary
                                        )
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
                                Row(
                                    modifier = Modifier.padding(5.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text("Sets:", modifier = Modifier.weight(1.0f))
                                    if (setsVm != null) {
                                        TextField(
                                            value = setsVm.setsNumber.getOrNull(idx) ?: "",
                                            onValueChange = { newValue ->
                                                if (idx < setsVm.setsNumber.size) {
                                                    setsVm.updateSetsNumber(idx, newValue)
                                                } else {
                                                    setsVm.setSetsNumber(newValue)
                                                }
                                            },
                                            label = { Text(text = "Number of Sets")},
                                            trailingIcon = {
                                                Icon(
                                                    imageVector = Icons.Default.Edit,
                                                    contentDescription = "Edit Icon" )
                                            }

                                        )
                                    }
                                }
                                Row(
                                    modifier = Modifier.padding(5.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text("Reps:", modifier = Modifier.weight(1.0f))
                                    if (repsVm != null) {
                                        TextField(
                                            value = repsVm.repsNumber.getOrNull(idx) ?: "",
                                            onValueChange = { newValue ->
                                                if (idx < repsVm.repsNumber.size) {
                                                    repsVm.updateRepsNumber(idx, newValue)
                                                } else {
                                                    repsVm.setRepsNumber(newValue)
                                                }
                                            },
                                            label = { Text(text = "Number of Reps")},
                                            trailingIcon = {
                                                Icon(
                                                    imageVector = Icons.Default.Edit,
                                                    contentDescription = "Edit Icon" )
                                            }
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}



