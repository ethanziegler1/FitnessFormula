package edu.towson.cosc435vails.fitnessformula.ui

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

class SavedWorkouts : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FitnessFormulaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    SavedWorkoutsPage()
                }
            }
        }
    }
}

@Composable
fun SavedWorkoutsPage() {
    val savedExercises = listOf(
        Exercise("Lateral Raises", "Stand slightly bent over with two low weight dumbells in front of you and raise your arms to the sides."),
        Exercise("Situps","Lay on the ground with your knees bent and your feet and back flat on the ground. Then sit up and slide your hands along the ground in front of you until your hands touch your heels.")
    )

    SavedExerciseLazyColumn(exercises = savedExercises)
}

@Composable
fun SavedExerciseLazyColumn(exercises: List<Exercise>) {
    val lazyListState = rememberLazyListState()


    LazyColumn(state = lazyListState) {
        itemsIndexed(exercises) { index, exercise ->
            if (index % 2 == 0) {
                // add a divider after every other item
                Divider(Modifier.padding(horizontal = 16.dp))
            }
            SavedExerciseCard(exercise = exercise)
        }
    }
}

@Composable
fun SavedExerciseCard(exercise: Exercise) {
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
fun DefaultPreview3() {
    FitnessFormulaTheme {
        SavedWorkoutsPage()
    }
}