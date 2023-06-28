package com.zozuliak.musclemate.presentation.main_screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.zozuliak.musclemate.presentation.main_screen.components.BottomMenu
import com.zozuliak.musclemate.presentation.main_screen.components.ConfirmDialog
import com.zozuliak.musclemate.presentation.main_screen.components.ExerciseItem
import com.zozuliak.musclemate.presentation.main_screen.components.ProfileButton
import com.zozuliak.musclemate.presentation.main_screen.components.WorkoutDialog
import com.zozuliak.musclemate.presentation.main_screen.components.WorkoutsDropdownList
import com.zozuliak.musclemate.presentation.utils.Screen

@Composable
fun MainScreen(
    viewModel: MainScreenViewModel = hiltViewModel<MainScreenViewModel>(),
    navController: NavController
) {

    val workoutList = viewModel.workoutList.value
    val exerciseList = viewModel.exerciseList.value
    val currentWorkout = viewModel.currentWorkout.value
    var expandedId by remember { mutableStateOf<String?>(null) }
    var showDeleteExerciseDialog by remember { mutableStateOf(false) }
    var showWorkoutDialog by remember { mutableStateOf(ShowWorkoutState(false)) }
    var showDeleteWorkoutDialog by remember { mutableStateOf(false) }
    val toastMessage = viewModel.toastMessage.value

    if (toastMessage.isNotEmpty()) {
        Toast.makeText(LocalContext.current, toastMessage, Toast.LENGTH_SHORT).show()
        viewModel.onToastEnded()
    }

    LaunchedEffect(Unit) {
        viewModel.getExercisesForWorkout(currentWorkout?.id?: "")
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
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
                        .size(40.dp),
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
                onWorkoutSelected = { viewModel.changeCurrentWorkout(it) },
                iconSize = 40.dp,
                onAddPressed = { showWorkoutDialog = showWorkoutDialog.copy(true) }
            )

            Text(
                modifier = Modifier
                    .padding(start = 24.dp, top = 24.dp),
                text = "${exerciseList.size} exercises",
                style = MaterialTheme.typography.titleMedium
            )

            LazyColumn(
                modifier = Modifier
                    .padding(
                        start = 21.dp,
                        end = 21.dp
                    )
                    .fillMaxSize()
            ) {

                itemsIndexed(
                    items = exerciseList,
                    key = { index, exercise ->
                        exercise.hashCode()
                    }
                ) { index, exercise ->

                    if (showDeleteExerciseDialog) {
                        ConfirmDialog(
                            text = "Are you sure?",
                            onConfirmPressed = { viewModel.deleteExerciseById(expandedId?:"") },
                            setShowDialog = { showDeleteExerciseDialog = it },
                            confirmButtonText = "Delete",
                            cancelButtonText = "Cancel"
                        )
                    }

                    ExerciseItem(
                        exercise = exercise,
                        expandedId = expandedId,
                        onClick = {
                            navController.navigate(
                            Screen.AddEditExerciseScreen.route +
                                    "?exerciseId=${exercise.id}"
                        )},
                        iconSize = 32.dp,
                        onExpandedClicked = { expandedId = if (expandedId == it) null else it },
                        onArrowUpPressed = { viewModel.moveUp(it) },
                        onArrowDownPressed = { viewModel.moveDown(it) },
                        onDeletePressed = {showDeleteExerciseDialog = true}
                    )
                }
            }
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent),
            contentAlignment = Alignment.BottomCenter
        ) {
            BottomMenu(
                isRunning = false,
                onStartClicked = {},
                onContinueClicked = {},
                onStopClicked = { },
                onAddClicked = {
                    navController.navigate(
                        Screen.AddEditExerciseScreen.route +
                                "?position=${exerciseList.size+1}&workoutId=${currentWorkout?.id?:"noId"}"
                    )},
                onEditClicked = {
                    showWorkoutDialog = showWorkoutDialog.copy(
                        show = true,
                        text = "Edit workout",
                        confirmButtonText = "Save",
                        workoutName = currentWorkout?.name
                    )
                }
            )
        }
    }

    if (showWorkoutDialog.show) {
        WorkoutDialog(
            text = showWorkoutDialog.text,
            workoutName = showWorkoutDialog.workoutName,
            isCreating = showWorkoutDialog.workoutName == null,
            confirmButtonText = showWorkoutDialog.confirmButtonText,
            cancelButtonText = "Cancel",
            onConfirmPressed = {
                if (showWorkoutDialog.workoutName == null) {
                    viewModel.createWorkout(it)
                } else {
                    viewModel.updateWorkout(currentWorkout!!.copy(name = it))
                }
            },
            onDeleteButtonPressed = { showDeleteWorkoutDialog = true },
            setShowDialog = { showWorkoutDialog = ShowWorkoutState(it) }
        )
    }

    if (showDeleteWorkoutDialog) {
        ConfirmDialog(
            text = "All exercises for this workout will also be deleted",
            confirmButtonText = "Delete",
            cancelButtonText = "Cancel",
            onConfirmPressed = { viewModel.deleteWorkout(currentWorkout?.id?:"") },
            setShowDialog = {showDeleteWorkoutDialog = it}
        )
    }
}

data class ShowWorkoutState(
    var show : Boolean,
    var workoutName: String? = null,
    var text : String = "Add new workout",
    var confirmButtonText: String = "Add"
)
