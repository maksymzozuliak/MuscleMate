package com.zozuliak.musclemate.presentation.main_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.chargemap.compose.numberpicker.NumberPicker
import com.zozuliak.musclemate.data.Workout

@Composable
fun BottomMenu(
    modifier: Modifier = Modifier,
    isRunning: Boolean,
    onStartClicked: () -> Unit,
    onContinueClicked: () -> Unit,
    onStopClicked: () -> Unit,
    onAddClicked: () -> Unit,
    onEditClicked: () -> Unit,
    backgroundShape: RoundedCornerShape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
    buttonShape: RoundedCornerShape = RoundedCornerShape(16.dp)
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .shadow(
                elevation = 2.dp,
                shape = backgroundShape
            )
            .padding(top = 4.dp)
            .background(
                color = MaterialTheme.colorScheme.background,
                shape = backgroundShape
            )
    ) {
        if (!isRunning) {
            Button(
                modifier = Modifier
                    .padding(
                        top = 8.dp,
                        start = 18.dp,
                        end = 18.dp
                    ),
                onClick = { onStartClicked() },
                shape = buttonShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondary
                )
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "START",
                    textAlign = TextAlign.Center
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            BottomMenuIconTextButton(
                onClick = { onAddClicked() },
                icon = Icons.Rounded.Add,
                text = "Add",
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.75f),
                textStyle = MaterialTheme.typography.labelMedium
            )
            Spacer(modifier = Modifier.width(100.dp))
            BottomMenuIconTextButton(
                onClick = { onEditClicked() },
                icon = Icons.Rounded.Edit,
                text = "Edit",
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.75f),
                textStyle = MaterialTheme.typography.labelMedium
            )
        }
    }
}

@Composable
private fun BottomMenuIconTextButton(
    modifier: Modifier = Modifier,
    onClick:() -> Unit,
    text: String,
    icon: ImageVector,
    color: Color,
    textStyle: TextStyle
) {
    TextButton(
        onClick = { onClick() },
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .padding(end = 2.dp),
                imageVector = icon,
                contentDescription = icon.toString(),
                tint = color,
            )
            Text(
                text = text,
                color = color,
                style = textStyle
            )
        }
    }
}