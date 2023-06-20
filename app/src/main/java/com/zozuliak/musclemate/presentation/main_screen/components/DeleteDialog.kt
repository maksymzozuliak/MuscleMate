package com.zozuliak.musclemate.presentation.main_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun DeleteDialog(
    text: String,
    modifier : Modifier = Modifier,
    onDeletePressed : () -> Unit,
    setShowDialog: (Boolean) -> Unit,
    backgroundColor: Color = MaterialTheme.colorScheme.background,
    backgroundShape: RoundedCornerShape = RoundedCornerShape(20.dp),
    textStyle: TextStyle = MaterialTheme.typography.labelMedium,
    buttonShape: RoundedCornerShape = RoundedCornerShape(16.dp),
    buttonTextStyle: TextStyle = MaterialTheme.typography.labelSmall,
    buttonColor: Color = MaterialTheme.colorScheme.secondary
) {
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
                            bottom = 12.dp
                        )
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
                            onDeletePressed()
                            setShowDialog(false)
                                  },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = buttonColor
                        ),
                        shape = buttonShape
                    ) {
                        Text(
                            text = "Delete",
                            style = buttonTextStyle
                        )
                    }
                }
            }
        }
    }
}