package com.zozuliak.musclemate.presentation.add_edit_exercise_screen.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.zozuliak.musclemate.data.Group


@Composable
fun MuscleGroupSelector(
    modifier: Modifier = Modifier,
    onGroupSelected: (String) -> Unit,
    selected: String,
    labelColor: Color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
    labelStyle: TextStyle = MaterialTheme.typography.labelSmall.copy(color = labelColor),
) {
    val upperBodyList = listOf<String>("Abdominal", "Chest", "Trapezius", "Lats")
    val legsList = listOf<String>("Quadricep", "Femoris", "Calves", "Gluteus")
    val handsList = listOf<String>("Biceps", "Triceps", "Brachialis", "Shoulder")

    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            text = "Upper-body",
            modifier = Modifier,
            style = labelStyle
        )
        Row(
            modifier = Modifier
                .padding(bottom = 8.dp)
        ) {
            upperBodyList.forEach { group ->
                GroupItem(
                    text = group,
                    selected = selected,
                    onGroupSelected = onGroupSelected,
                    textStyle = MaterialTheme.typography.bodySmall
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
        }

        Text(
            text = "Legs",
            modifier = Modifier,
            style = labelStyle
        )
        Row(
            modifier = Modifier
                .padding(bottom = 8.dp)
        ) {
            legsList.forEach { group ->
                GroupItem(
                    text = group,
                    selected = selected,
                    onGroupSelected = onGroupSelected
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
        }

        Text(
            text = "Hands",
            modifier = Modifier,
            style = labelStyle
        )
        Row() {
            handsList.forEach { group ->
                GroupItem(
                    text = group,
                    selected = selected,
                    onGroupSelected = onGroupSelected
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
        }
        GroupItem(
            text = "Forearm",
            selected = selected,
            onGroupSelected = onGroupSelected
        )
    }
}

@Composable
private fun GroupItem(
    text: String,
    modifier: Modifier = Modifier,
    selected: String,
    onGroupSelected: (String) -> Unit,
    shape: RoundedCornerShape = RoundedCornerShape(18.dp),
    textStyle: TextStyle = MaterialTheme.typography.bodySmall,
    selectedColor: Color = MaterialTheme.colorScheme.primary,
    onSelectedColor: Color = MaterialTheme.colorScheme.onPrimary,
    unselectedColor: Color = MaterialTheme.colorScheme.background,
    onUnselectedColor: Color = MaterialTheme.colorScheme.onBackground
) {
    val isSelected = selected == text
    val backgroundState by animateColorAsState(
        targetValue = if (isSelected) selectedColor else onSelectedColor,
        animationSpec = tween(
            durationMillis = 500
        )
    )
    val onBackgroundState by animateColorAsState(
        targetValue = if (isSelected) onSelectedColor else onUnselectedColor,
        animationSpec = tween(
            durationMillis = 500
        )
    )

    TextButton(
        onClick = { onGroupSelected(text) },
        modifier = modifier,
        shape = shape,
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = if (isSelected) 0.dp else 2.dp,
            hoveredElevation = 0.dp,
            pressedElevation = 0.dp
        ),
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundState
        )
    ) {
        Text(
            text = text,
            style = textStyle,
            color = onBackgroundState
        )
    }

}