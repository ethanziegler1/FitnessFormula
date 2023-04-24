package edu.towson.cosc435vails.fitnessformula.ui

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.towson.cosc435vails.fitnessformula.model.Exercise

@ExperimentalFoundationApi
@Composable
fun ExerciseListRow(
    idx: Int,
    exercise: Exercise,
    onAddChecked: (Int) -> Unit,
    onExerciseClicked: (Exercise) -> Unit
) {
    Log.d("TAG", exercise.name)
    Card(
        shape = RoundedCornerShape(5.dp),
        elevation = 16.dp,
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, top = 5.dp, bottom = 5.dp)
            .fillMaxWidth()
            .clickable {
                onExerciseClicked(exercise)
            }
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Column(
                modifier = Modifier.weight(1.5f)
            ) {
                Row(
                    modifier = Modifier.padding(5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Name:", modifier = Modifier.weight(5.0f))
                    Text(
                        exercise.name,
                        modifier = Modifier.weight(5.0f),
                        fontSize = 20.sp,
                        color = MaterialTheme.colors.primary
                    )
                }
                Row(
                    modifier = Modifier.padding(5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Description: ", modifier = Modifier.weight(5.0f), softWrap = false, overflow = TextOverflow.Ellipsis )
                    Text(exercise.description, modifier = Modifier.weight(5.0f))
                }
            }
            Column(
                modifier = Modifier.weight(1.0f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row() {
                    Checkbox(
                        checked = exercise.addToWorkout,
                        onCheckedChange = {
                            onAddChecked(idx)
                        },
                        modifier = Modifier.padding(end = 5.dp),
                        colors = CheckboxDefaults.colors(checkmarkColor = MaterialTheme.colors.primary)
                    )
                    Text(
                        "Add",
                        modifier = Modifier
                            .padding(5.dp)
                            .fillMaxWidth()
                    )
                }
            }
        }
    }
}