package edu.towson.cosc435vails.fitnessformula

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import edu.towson.cosc435vails.fitnessformula.ui.theme.FitnessFormulaTheme

class WorkoutDisplay : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FitnessFormulaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    WorkoutDisplayPage()
                }
            }
        }
    }
}

data class Exercise(val name: String, val description: String, var selected: Boolean = false)

@Composable
fun WorkoutDisplayPage() {
    val exercises = listOf(
        Exercise("Squats", "Stand with your feet shoulder-width apart and lower your hips until your thighs are parallel to the floor."),
        Exercise("Push-ups", "Start in a plank position with your hands shoulder-width apart. Lower your body until your chest touches the floor, then push back up."),
        Exercise("Lunges", "Stand with your feet hip-width apart and take a big step forward with one foot. Lower your body until your front knee is at a 90-degree angle, then push back up.")
    )

    ExerciseLazyColumn(exercises = exercises)
}

@Composable
fun ExerciseLazyColumn(exercises: List<Exercise>) {
    val lazyListState = rememberLazyListState()


    LazyColumn(state = lazyListState) {
        itemsIndexed(exercises) { index, exercise ->
            if (index % 2 == 0) {
                // add a divider after every other item
                Divider(Modifier.padding(horizontal = 16.dp))
            }
            ExerciseCard(exercise = exercise)
        }
    }
}

@Composable
fun ExerciseCard(exercise: Exercise) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = 8.dp
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = exercise.name,
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.Bold
                )
                Checkbox(
                    checked = false,
                    onCheckedChange = {}
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = exercise.description, style = MaterialTheme.typography.body1)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    FitnessFormulaTheme {
        WorkoutDisplayPage()
    }
}