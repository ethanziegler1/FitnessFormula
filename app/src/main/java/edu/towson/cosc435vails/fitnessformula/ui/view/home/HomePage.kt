package edu.towson.cosc435vails.fitnessformula.ui.view.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import edu.towson.cosc435vails.fitnessformula.R
import edu.towson.cosc435vails.fitnessformula.ui.nav.Routes

// Has all buttons for navigation
@Composable
fun HomeView(navController: NavController) {

    val titleCustomFontFamily = FontFamily(
        Font(R.font.rosmatika, FontWeight.Normal),
        Font(R.font.rosmatika, FontWeight.Bold)
    )

    val customFontFamily = FontFamily(
        Font(R.font.mercusarbold, FontWeight.Normal),
        Font(R.font.mercusarbold, FontWeight.Bold)
    )

    Column(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Fitness Formula",
            color = MaterialTheme.colors.primary,
            fontSize = 50.sp,
            fontFamily = titleCustomFontFamily,
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Button(
                onClick = {
                    navController.navigate(Routes.ExerciseList.route) {
                        launchSingleTop = true
                        popUpTo(Routes.ExerciseList.route) { inclusive = false }
                    }
                },
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .padding(20.dp)
                    .height(60.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    "New Workouts",
                    fontFamily = customFontFamily,
                    fontSize = 35.sp,
                    )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Button(
                onClick = {
                    navController.navigate(Routes.LibraryExercises.route) {
                        launchSingleTop = true
                        popUpTo(Routes.LibraryExercises.route) { inclusive = false }
                    }
                },
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .padding(20.dp)
                    .height(60.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    "Exercise Library",
                    fontFamily = customFontFamily,
                    fontSize = 35.sp,
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Button(
                onClick = {
                    navController.navigate(Routes.WorkoutList.route) {
                        launchSingleTop = true
                        popUpTo(Routes.WorkoutList.route) { inclusive = false }
                    }
                },
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .padding(20.dp)
                    .height(60.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    "Workout List",
                    fontFamily = customFontFamily,
                    fontSize = 35.sp,
                )
            }
        }
    }
}

