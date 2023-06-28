package com.zozuliak.musclemate.presentation.utils

sealed class Screen(val route: String) {

    object MainScreen: Screen("main_screen")

    object AddEditExerciseScreen: Screen("add_edit_exercise_screen")

}
