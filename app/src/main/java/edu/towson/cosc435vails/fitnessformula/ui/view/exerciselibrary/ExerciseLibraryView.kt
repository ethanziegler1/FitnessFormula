package edu.towson.cosc435vails.fitnessformula.ui.view.exerciselibrary

import android.content.res.Configuration
import android.graphics.Bitmap
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import edu.towson.cosc435vails.fitnessformula.model.LibraryExercise
import edu.towson.cosc435vails.fitnessformula.ui.LandscapeView
import edu.towson.cosc435vails.fitnessformula.ui.LibraryExerciseRow
import edu.towson.cosc435vails.fitnessformula.ui.SearchBar

// View for Exercise Library cards, same as new workout.
@ExperimentalFoundationApi
@Composable
fun ExerciseLibraryView(
    exercises: List<LibraryExercise>,
    selectedExercise: LibraryExercise?,
    onFilter: (String) -> Unit,
    onExerciseClicked: (LibraryExercise) -> Unit,
    onFetchImage: suspend (String) -> Bitmap?

) {
    val configuration = LocalConfiguration.current
    if(configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
        LandscapeLibraryView(selectedExercise = selectedExercise, onFetchImage = onFetchImage ) {
            Column(
            ) {
                SearchBar(onFilter = onFilter)
                LazyColumn {
                    itemsIndexed(exercises) {idx, exercise ->
                        LibraryExerciseRow(
                            exercise = exercise,
                            onExerciseClicked = onExerciseClicked,
                            onFetchImage = onFetchImage
                        )
                    }
                }
            }
        }
    } else {
        Column(
        ) {
            SearchBar(onFilter = onFilter)
            LazyColumn {
                itemsIndexed(exercises) {idx, exercise ->
                    LibraryExerciseRow(
                        exercise = exercise,
                        onExerciseClicked = onExerciseClicked,
                        onFetchImage = onFetchImage
                    )
                }
            }
        }
    }
}