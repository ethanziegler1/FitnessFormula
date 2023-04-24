package edu.towson.cosc435vails.fitnessformula.ui.view.savedWorkout

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.towson.cosc435vails.fitnessformula.model.Exercise

@Composable
fun SavedWorkoutsView(
    exercises: List<Exercise>
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