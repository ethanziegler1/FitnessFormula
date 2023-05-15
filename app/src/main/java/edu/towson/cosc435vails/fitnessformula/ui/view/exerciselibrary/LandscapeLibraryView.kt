package edu.towson.cosc435vails.fitnessformula.ui.view.exerciselibrary

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.towson.cosc435vails.fitnessformula.R
import edu.towson.cosc435vails.fitnessformula.model.LibraryExercise

// Landscape view for Exercise Library same as for new workouts
@Composable
fun LandscapeLibraryView(
    selectedExercise: LibraryExercise?,
    onFetchImage: suspend (String) -> Bitmap?,
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
                var icon by remember { mutableStateOf<Bitmap?>(null) }
                var noImage by remember { mutableStateOf(false) }

                if(selectedExercise != null) {

                    Text(
                        selectedExercise.name,
                        modifier = Modifier.padding(16.dp),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )

                    LaunchedEffect(key1 = selectedExercise.name) {
                        if(selectedExercise.iconURL != null) {
                            icon = onFetchImage(selectedExercise.iconURL)
                        } else {
                            noImage = true
                        }
                    }
                    if(icon == null && !noImage) {
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
                }
            }
        }
        content()
    }
}