package edu.towson.cosc435vails.fitnessformula.ui

import android.graphics.Bitmap
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.towson.cosc435vails.fitnessformula.R
import edu.towson.cosc435vails.fitnessformula.model.Exercise
import edu.towson.cosc435vails.fitnessformula.model.LibraryExercise


// Displays a lazycolumn of the database of exercises, no add functionality.
@ExperimentalFoundationApi
@Composable
fun LibraryExerciseRow(
    exercise: LibraryExercise,
    onExerciseClicked: (LibraryExercise) -> Unit,
    onFetchImage: suspend (String) -> Bitmap?
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
                Column(
                    modifier = Modifier.weight(1.0f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    var icon by remember { mutableStateOf<Bitmap?>(null) }
                    var noImage by remember { mutableStateOf(false) }
                    LaunchedEffect(key1 = exercise.name) {
                        if (exercise.iconURL != null) {
                            icon = onFetchImage(exercise.iconURL)
                        } else {
                            noImage = true
                        }
                    }
                    if (icon == null && !noImage) {
                        CircularProgressIndicator()
                    } else if (noImage) {
                        Image(
                            painter = painterResource(id = android.R.drawable.spinner_background),
                            contentDescription = ""
                        )
                    } else {
                        Image(
                            bitmap = icon!!.asImageBitmap(),
                            contentDescription = ""
                        )
                    }
                    Text("Test text")
                }
            }
        }
    }
}