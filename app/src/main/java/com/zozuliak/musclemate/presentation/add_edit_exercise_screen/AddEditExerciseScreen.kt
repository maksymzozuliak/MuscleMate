package com.zozuliak.musclemate.presentation.add_edit_exercise_screen

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.zozuliak.musclemate.presentation.add_edit_exercise_screen.components.SetDialog
import com.zozuliak.musclemate.presentation.add_edit_exercise_screen.components.MuscleGroupSelector
import com.zozuliak.musclemate.presentation.add_edit_exercise_screen.components.SetsField
import com.zozuliak.musclemate.presentation.add_edit_exercise_screen.components.TwoNumbersPickerSurface
import com.zozuliak.musclemate.presentation.global_components.BackButton
import com.zozuliak.musclemate.presentation.main_screen.components.ConfirmDialog
import com.zozuliak.musclemate.presentation.main_screen.components.ProfileButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditExerciseScreen(
    viewModel: AddEditExerciseScreenViewModel = hiltViewModel<AddEditExerciseScreenViewModel>(),
    navController: NavController,
    titleStyle: TextStyle = MaterialTheme.typography.titleMedium
) {

    val nameText = viewModel.name.value
    val group = viewModel.group.value
    val sets = viewModel.exerciseSets.value
    val restMinutes = viewModel.restMinutes.value
    val restSeconds = viewModel.restSeconds.value
    var showSetDialog by remember { mutableStateOf(ShowSetDialogState(false)) }
    var showUnsavedDialog by remember { mutableStateOf(false) }
    val isUnsaved = viewModel.isUnsaved.value
    val toastMessage = viewModel.toastMessage.value
    val personalRecord = viewModel.personalRecord.value

    val scrollState = rememberScrollState()


    if (toastMessage.isNotEmpty()) {
        Toast.makeText(LocalContext.current, toastMessage, Toast.LENGTH_SHORT).show()
        viewModel.onToastEnded()
    }

    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .fillMaxWidth()
            .heightIn(0.dp, 2000.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 24.dp,
                    end = 24.dp,
                    top = 18.dp,
                    bottom = 12.dp
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(40.dp))
            Text(
                text = "Exercise",
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge
            )
            ProfileButton(
                modifier = Modifier
                    .size(40.dp),
                onClick = { },
                color = MaterialTheme.colorScheme.onBackground
            )
        }

        Text(
            text = "Name",
            modifier = Modifier
                .padding(
                    start = 24.dp,
                    bottom = 4.dp
                ),
            style = titleStyle
        )

        OutlinedTextField(
            value = nameText,
            modifier = Modifier
                .padding(
                    start = 24.dp,
                    end = 24.dp,
                    bottom = 18.dp
                )
                .fillMaxWidth(),
            onValueChange = { viewModel.changeNameText(it) },
            singleLine = true,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = MaterialTheme.colorScheme.onBackground,
                unfocusedBorderColor = MaterialTheme.colorScheme.secondary,
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                cursorColor = MaterialTheme.colorScheme.onBackground,
            ),
            shape = RoundedCornerShape(12.dp)

        )

        Text(
            text = "Muscle",
            modifier = Modifier
                .padding(
                    start = 24.dp,
                    bottom = 2.dp
                ),
            style = titleStyle
        )

        MuscleGroupSelector(
            onGroupSelected = { viewModel.changeGroup(it) },
            modifier = Modifier
                .padding(
                    start = 24.dp,
                    bottom = 18.dp
                ),
            selected = group
        )

        Text(
            text = "Sets",
            modifier = Modifier
                .padding(
                    start = 24.dp,
                    bottom = 2.dp
                ),
            style = titleStyle
        )

        SetsField(
            modifier = Modifier
                .padding(
                    start = 24.dp,
                    end = 24.dp,
                    bottom = 8.dp
                ),
            sets = sets,
            personalRecord = personalRecord,
            onAddClicked = { showSetDialog = showSetDialog.copy(show = true) },
            onRemovePressed = { viewModel.deleteLastSet() },
            onClick = { showSetDialog = showSetDialog.copy(true, it, "Save", "Change ${it+1} set")}
        )

        Text(
            text = "Rest time",
            modifier = Modifier
                .padding(
                    start = 24.dp,
                    end = 24.dp
                ),
            style = titleStyle
        )
        TwoNumbersPickerSurface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 24.dp,
                    end = 24.dp
                ),
            firstNumber = restMinutes,
            firstRange = (0..10).toList(),
            onFirstChanged = {viewModel.changeRestMinutes(it)},
            firstTitle = "minutes",
            secondNumber = restSeconds,
            secondRange = (0..50 step 10).toList(),
            onSecondChanged = {viewModel.changeRestSeconds(it)},
            secondTitle = "seconds"
        )

        Button(
            modifier = Modifier
                .padding(
                    top = 24.dp,
                    start = 24.dp,
                    end = 24.dp,
                    bottom = 8.dp
                ),
            onClick = { viewModel.saveExercise() },
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary
            )
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "SAVE",
                textAlign = TextAlign.Center
            )
        }

    }
    Box(
        modifier = Modifier
            .offset(x = 24.dp, y = 18.dp)
            .shadow(4.dp, RoundedCornerShape(100.dp))
    ) {
        BackButton(
            modifier = Modifier
                .size(40.dp),
            onClick = {
                if (isUnsaved) {
                    showUnsavedDialog = true
                } else {
                    navController.popBackStack()
                }
            },
            color = MaterialTheme.colorScheme.onBackground,
            buttonColors = IconButtonDefaults.iconButtonColors(
                containerColor = MaterialTheme.colorScheme.background
            )
        )
    }

    if (showSetDialog.show) {
        var selectedReps: Int = 0
        var selectedWeight: Int = if (sets.isEmpty()) 0 else sets.last().weight.toInt()
        var selectedWeightExtended: Int = 0
        if (showSetDialog.position != null) {
            if (sets.isNotEmpty()) {
                val set = sets[showSetDialog.position!!]
                selectedReps = set.repetitions
                selectedWeight = set.weight.toInt()
                selectedWeightExtended = ((set.weight * 100) % 100).toInt()
            }
        }

        SetDialog(
            onAddPressed = { reps, weight, weightExtended ->
                viewModel.addSet(
                    position = showSetDialog.position,
                    reps = reps,
                    weight = weight,
                    weightExtended = weightExtended
                )
                showSetDialog = ShowSetDialogState(false)
            },
            setShowDialog = { showSetDialog = showSetDialog.copy(show = it)
                showSetDialog = ShowSetDialogState(false)},
            selectedReps = selectedReps,
            selectedWeight = selectedWeight,
            selectedWeightExtended = selectedWeightExtended,
            text = showSetDialog.text,
            buttonText = showSetDialog.buttonName
        )
    }

    if (showUnsavedDialog) {
        ConfirmDialog(
            text = "You have unsaved data",
            onConfirmPressed = {
                showUnsavedDialog = false
                               },
            setShowDialog = { showUnsavedDialog = it },
            onCancelPressed = {
                viewModel.changeUnsavedStatus(false)
                navController.popBackStack()
            },
            confirmButtonText = "Back",
            cancelButtonText = "Discard"
        )
    }

}

data class ShowSetDialogState (
    var show: Boolean,
    var position: Int? = null,
    var buttonName: String = "Add",
    var text: String = "Add new set"
)