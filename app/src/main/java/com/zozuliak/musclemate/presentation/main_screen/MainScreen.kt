package com.zozuliak.musclemate.presentation.main_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.zozuliak.musclemate.R
import com.zozuliak.musclemate.data.Exercise
import com.zozuliak.musclemate.data.Workout
import com.zozuliak.musclemate.presentation.main_screen.components.ExerciseItem
import com.zozuliak.musclemate.presentation.main_screen.components.ProfileButton
import com.zozuliak.musclemate.presentation.main_screen.components.WorkoutsDropdownList

@Composable
fun MainScreen(
    viewModel: MainScreenViewModel = hiltViewModel<MainScreenViewModel>()
) {

    val workoutList = viewModel.workoutList.value
    val exerciseList = viewModel.exerciseList.value
    val currentWorkout = viewModel.currentWorkout.value
    var expandedId by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 24.dp,
                    end = 24.dp,
                    top = 18.dp,
                    bottom = 8.dp
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .weight(1f),
                text = "Workouts",
                style = MaterialTheme.typography.titleLarge
            )
            ProfileButton(
                modifier = Modifier
                    .size(36.dp),
                onClick = {},
                color = MaterialTheme.colorScheme.onBackground
            )
        }

        WorkoutsDropdownList(
            modifier = Modifier
                .padding(horizontal = 20.dp),
            workoutList = workoutList,
            currentWorkout = currentWorkout,
            color = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onBackground,
            onWorkoutSelected = {viewModel.changeCurrentWorkout(it)},
            iconSize = 40.dp,
            onAddPressed = {}
        )

        Text(
            modifier = Modifier
                .padding(start = 24.dp, top = 24.dp, bottom = 8.dp),
            text = "${exerciseList.size} exercises",
            style = MaterialTheme.typography.titleMedium
        )

        LazyColumn(
            modifier = Modifier
                .padding(horizontal = 21.dp)
                .fillMaxSize()
        ) {

            itemsIndexed(
                items = exerciseList,
                key = { index, exercise ->
                    exercise.hashCode()
                }
            ) {index, exercise ->
                ExerciseItem(
                    exercise = exercise,
                    expandedId = expandedId,
                    onClick = {},
                    iconSize = 32.dp,
                    onExpandedClicked = { expandedId = if (expandedId == it) null else it },
                    onArrowUpPressed = {viewModel.moveUp(it)},
                    onArrowDownPressed = {viewModel.moveDown(it)},
                    onDeletePressed = {}
                )
            }
        }
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    Surface(modifier = Modifier.background(Color.White)) {
        MainScreen()
    }
}