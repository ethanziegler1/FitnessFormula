package edu.towson.cosc435vails.fitnessformula.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import edu.towson.cosc435vails.fitnessformula.model.Exercise

@Composable
fun LandscapeView(
    selectedExercise: Exercise?,
    content: @Composable () -> Unit
) {
    Row(
    ) {
        Card(
            modifier = Modifier
                .size(250.dp)
                .align(Alignment.CenterVertically)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if(selectedExercise != null) {
                    Text(selectedExercise.name)
                }
            }
        }
        content()
    }
}