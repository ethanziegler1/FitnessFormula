package edu.towson.cosc435vails.fitnessformula

import android.graphics.fonts.FontStyle
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.towson.cosc435vails.fitnessformula.ui.theme.FitnessFormulaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FitnessFormulaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainPage()
                }
            }
        }
    }
}

@Composable
fun MainPage() {
    Column(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Fitness Formula",
            color = MaterialTheme.colors.primary,
            fontSize = 45.sp,
        )
        Button(
            {},
            modifier = Modifier.padding(20.dp)
        ) {
            Text("New Workouts")
        }
        Button(
            {},
            modifier = Modifier.padding(20.dp)
        ) {
            Text("Saved Workouts")
        }
        Button(
            {},
            modifier = Modifier.padding(20.dp),
        ) {
            Text("Settings")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FitnessFormulaTheme {
        MainPage()
    }
}
/*
TODO-1. MainPage
TODO-1.a. A help button or tutorial description to describe how to use the app.
TODO-1.b. A button to take you to a page that provides search fields for workouts.
TODO-1.c. Format original theme including colors, images, and fonts.
TODO-1.d. Handle landscape view.
TODO-1.e. A button to take you to a page where you have stored workout lists.
TODO-2. Create ViewModels.
TODO-3. Create Class for Workout objects.
TODO-4. Create Page for displaying workouts.
*/