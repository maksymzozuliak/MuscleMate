package com.zozuliak.musclemate.presentation.add_edit_exercise_screen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.chargemap.compose.numberpicker.NumberPicker
import com.zozuliak.musclemate.repsRange
import com.zozuliak.musclemate.weightExpandedRange
import com.zozuliak.musclemate.weightRange

@Composable
fun SetDialog(
    text: String,
    buttonText: String,
    modifier : Modifier = Modifier,
    selectedReps: Int,
    selectedWeight: Int,
    selectedWeightExtended: Int,
    onAddPressed : (Int, Int, Int) -> Unit,
    setShowDialog: (Boolean) -> Unit,
    backgroundColor: Color = MaterialTheme.colorScheme.background,
    backgroundShape: RoundedCornerShape = RoundedCornerShape(20.dp),
    textStyle: TextStyle = MaterialTheme.typography.labelMedium,
    labelTextColor: Color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
    labelTextStyle: TextStyle = MaterialTheme.typography.labelSmall.copy(color = labelTextColor),
    buttonShape: RoundedCornerShape = RoundedCornerShape(16.dp),
    buttonTextStyle: TextStyle = MaterialTheme.typography.labelSmall,
    buttonColor: Color = MaterialTheme.colorScheme.secondary,
    dividersColor: Color = MaterialTheme.colorScheme.primary,
) {

    var selectedRepsState by remember { mutableStateOf(selectedReps) }
    var selectedWeightState by remember { mutableStateOf(selectedWeight) }
    var selectedWeightExtendedState by remember { mutableStateOf(selectedWeightExtended) }

    Dialog(onDismissRequest = { setShowDialog(false) }) {

        Surface(
            modifier = modifier,
            color = backgroundColor,
            shape = backgroundShape
        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = text,
                    style = textStyle,
                    modifier = Modifier
                        .padding(
                            top = 16.dp,
                            bottom = 18.dp
                        )
                )
                Row(
                    modifier = Modifier
                        .padding(
                            start = 16.dp,
                            end = 84.dp
                        )
                ) {
                    Text(
                        text = "Reps",
                        style = labelTextStyle,
                        modifier = Modifier
                            .padding(
                                start = 16.dp
                            )
                    )
                    Spacer(modifier = Modifier.width(90.dp))
                    Text(
                        text = "Weight",
                        style = labelTextStyle,
                        modifier = Modifier
                            .padding(
                                start = 16.dp
                            )
                    )
                }
                Row(
                    modifier = Modifier
                        .padding(
                            start = 24.dp,
                            end = 24.dp,
                            bottom = 4.dp
                        )
                ) {
                    NumberPicker(
                        value = selectedRepsState,
                        onValueChange = {selectedRepsState = it},
                        range = repsRange,
                        modifier = Modifier
                            .weight(0.2f),
                        dividersColor = dividersColor
                    )
                    Spacer(
                        modifier = Modifier
                            .weight(0.15f)
                    )
                    NumberPicker(
                        value = selectedWeightState,
                        onValueChange = {selectedWeightState = it},
                        range = weightRange,
                        modifier = Modifier
                            .weight(0.3f),
                        dividersColor = dividersColor
                    )
                    Text(
                        text = ".",
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .weight(0.05f)
                            .padding(
                                top = 56.dp
                            )
                    )
                    NumberPicker(
                        value = selectedWeightExtendedState,
                        onValueChange = {selectedWeightExtendedState = it},
                        range = weightExpandedRange,
                        modifier = Modifier
                            .weight(0.2f),
                        dividersColor = dividersColor
                    )
                }
                Row(
                    modifier = Modifier
                        .padding(
                            start = 16.dp,
                            end = 16.dp,
                            bottom = 4.dp
                        )
                ) {
                    TextButton(
                        onClick = { setShowDialog(false) }
                    ) {
                        Text(
                            text = "Cancel",
                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.75f),
                            style = buttonTextStyle
                        )
                    }
                    Spacer(modifier = Modifier.width(20.dp))
                    TextButton(
                        onClick = {
                            onAddPressed(selectedRepsState, selectedWeightState, selectedWeightExtendedState)
                            setShowDialog(false)
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = buttonColor
                        ),
                        shape = buttonShape,
                    ) {
                        Text(
                            text = buttonText,
                            style = buttonTextStyle,
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                        )
                    }
                }
            }
        }
    }
}