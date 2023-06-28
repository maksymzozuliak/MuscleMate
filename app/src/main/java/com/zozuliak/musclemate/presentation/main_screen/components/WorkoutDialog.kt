package com.zozuliak.musclemate.presentation.main_screen.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.zozuliak.musclemate.data.Workout

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkoutDialog(
    modifier : Modifier = Modifier,
    text: String,
    workoutName: String?,
    isCreating: Boolean,
    confirmButtonText: String,
    onCancelPressed: () -> Unit = {},
    cancelButtonText: String,
    onConfirmPressed : (String) -> Unit,
    onDeleteButtonPressed: () -> Unit,
    setShowDialog: (Boolean) -> Unit,
    backgroundColor: Color = MaterialTheme.colorScheme.background,
    backgroundShape: RoundedCornerShape = RoundedCornerShape(20.dp),
    textStyle: TextStyle = MaterialTheme.typography.labelMedium,
    buttonShape: RoundedCornerShape = RoundedCornerShape(16.dp),
    buttonTextStyle: TextStyle = MaterialTheme.typography.labelSmall,
    buttonColor: Color = MaterialTheme.colorScheme.secondary,
    deleteButtonColor: Color = Color(0xFFB30000)
) {
    var nameState by remember { mutableStateOf(workoutName?: "")}

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
                            bottom = 12.dp,
                            start = 12.dp,
                            end = 12.dp
                        )
                )
                OutlinedTextField(
                    value = nameState,
                    modifier = Modifier
                        .padding(
                            start = 24.dp,
                            end = 24.dp,
                            bottom = 18.dp
                        )
                        .fillMaxWidth(),
                    onValueChange = { nameState = it },
                    singleLine = true,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        textColor = MaterialTheme.colorScheme.onBackground,
                        unfocusedBorderColor = MaterialTheme.colorScheme.secondary,
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        cursorColor = MaterialTheme.colorScheme.onBackground,
                    ),
                    shape = RoundedCornerShape(12.dp),
                    label = {
                        Text(text = "Name")
                    }

                )
                Row(
                    modifier = Modifier
                        .padding(
                            start = 16.dp,
                            end = 16.dp,
                            bottom = 4.dp
                        )
                ) {
                    TextButton(
                        onClick = {
                            onCancelPressed()
                            setShowDialog(false)
                        },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = cancelButtonText,
                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.75f),
                            style = buttonTextStyle
                        )
                    }
                    if (!isCreating) {
                        OutlinedButton(
                            onClick = {
                                onDeleteButtonPressed()
                                setShowDialog(false)
                            },
                            shape = buttonShape,
                            border = BorderStroke(2.dp, deleteButtonColor),
                            modifier = Modifier.weight(1f),
                            contentPadding = PaddingValues(0.dp)
                        ) {
                            Text(
                                text = "Delete",
                                color = deleteButtonColor,
                                style = buttonTextStyle
                            )
                        }
                        Spacer(modifier = Modifier.weight(0.1f))
                    } else {
                        Spacer(modifier = Modifier.weight(0.2f))
                    }
                    TextButton(
                        onClick = {
                            onConfirmPressed(nameState)
                            setShowDialog(false)
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = buttonColor
                        ),
                        shape = buttonShape,
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = confirmButtonText,
                            style = buttonTextStyle
                        )
                    }
                }
            }
        }
    }
}