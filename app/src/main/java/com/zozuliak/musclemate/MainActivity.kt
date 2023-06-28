package com.zozuliak.musclemate

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.zozuliak.musclemate.presentation.add_edit_exercise_screen.AddEditExerciseScreen
import com.zozuliak.musclemate.presentation.main_screen.MainScreen
import com.zozuliak.musclemate.presentation.utils.Screen
import com.zozuliak.musclemate.ui.theme.MuscleMateTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MuscleMateTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Screen.MainScreen.route
                ) {
                    composable(
                        route = Screen.MainScreen.route
                    ) {
                        MainScreen(
                            navController = navController
                        )
                    }
                    composable(
                        route = Screen.AddEditExerciseScreen.route +
                                "?exerciseId={exerciseId}&position={position}&workoutId={workoutId}",
                        arguments = listOf(
                            navArgument(
                                name = "exerciseId"
                            ) {
                                type = NavType.StringType
                                defaultValue = "noId"
                            },
                            navArgument(
                                name = "position"
                            ) {
                                type = NavType.IntType
                                defaultValue = -1
                            },
                            navArgument(
                                name = "workoutId"
                            ) {
                                type = NavType.StringType
                                defaultValue = "noId"
                            },

                        )
                    ) {
                        AddEditExerciseScreen(
                            navController = navController
                        )
                    }
                }
            }
        }
    }
}
