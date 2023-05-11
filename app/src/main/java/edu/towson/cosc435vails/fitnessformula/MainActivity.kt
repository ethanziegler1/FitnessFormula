package edu.towson.cosc435vails.fitnessformula

import android.content.Intent
import android.graphics.fonts.FontStyle
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.towson.cosc435vails.fitnessformula.ui.MainScreen
import edu.towson.cosc435vails.fitnessformula.ui.theme.FitnessFormulaTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalComposeUiApi::class, ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            FitnessFormulaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}



//@Composable
//@Preview(showBackground = true)
//fun DefaultPreview() {
//    FitnessFormulaTheme {
//        MainScreen()
//    }
//}
/*

TODO-5. Utilize Intents (Im thinking a new button on home screen that just links to Youtube Videos of premade Workouts)
TODO-6. Add more exercises to the API
TODO-7 Clean up the UI especially the landscape view of images etc.
*/