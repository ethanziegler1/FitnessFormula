package edu.towson.cosc435vails.fitnessformula.ui

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import edu.towson.cosc435vails.fitnessformula.R
import edu.towson.cosc435vails.fitnessformula.model.Exercise

@Composable
fun LandscapeView(
    selectedExercise: Exercise?,
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

                    Text(selectedExercise.name)

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
                            painter = painterResource(id = R.drawable.ic_launcher_foreground),
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