package com.zozuliak.musclemate.presentation.main_screen.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.zozuliak.musclemate.R
import com.zozuliak.musclemate.data.Workout

@Composable
fun WorkoutsDropdownList(
    modifier: Modifier,
    workoutList: List<Workout>,
    currentWorkout: Workout?,
    color: Color = MaterialTheme.colorScheme.primary,
    contentColor : Color = MaterialTheme.colorScheme.onBackground,
    onWorkoutSelected: (Workout) -> Unit,
    onAddPressed: () -> Unit,
    iconSize: Dp = 40.dp,
    elementHeight: Dp = 40.dp,
    shape: RoundedCornerShape = RoundedCornerShape(20.dp)
) {
    var expanded by remember { mutableStateOf(false) }
    val scaleState by animateFloatAsState(
        targetValue = if (expanded) -1f else 1f,
        animationSpec = tween(
            durationMillis = 500
        )
    )

    val heightState by animateDpAsState(
        targetValue = if (expanded) ((workoutList.size+1) * elementHeight.value).dp else 0.dp,
        animationSpec = tween(
            durationMillis = 500
        )
    )


    Column(
        modifier = modifier
            .shadow(
                elevation = 5.dp,
                shape = shape,
                spotColor = Color.Black.copy(alpha = 0.3f)
            )
            .padding(
                start = 4.dp,
                end = 4.dp,
                bottom = 4.dp
            )
            .background(MaterialTheme.colorScheme.background, shape)
    ) {
        OutlinedButton(
            contentPadding = PaddingValues(3.dp),
            onClick = { expanded = !expanded },
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background, shape),
            shape = shape,
            border = BorderStroke(3.dp, color)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Spacer(modifier = Modifier.size(iconSize))
                Text(
                    text = currentWorkout?.name ?: "Loading",
                    style = MaterialTheme.typography.labelLarge,
                    color = contentColor,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center
                )
                Icon(
                    modifier = Modifier
                        .size(iconSize)
                        .scale(scaleX = 1f, scaleY = scaleState),
                    painter = painterResource(id = R.drawable.expand_more),
                    contentDescription = "Expand more",
                    tint = contentColor
                )
            }
        }
        Column(
            modifier = Modifier
                .height(heightState)
        ) {

            LazyColumn() {
                items(workoutList) { workout ->
                    TextButton(
                        modifier = Modifier
                            .height(elementHeight),
                        onClick = {
                            onWorkoutSelected(workout)
                            expanded = !expanded
                                  },
                        contentPadding = PaddingValues(1.dp)
                    ) {
                        Text(
                            style = MaterialTheme.typography.labelMedium,
                            text = workout.name,
                            modifier = Modifier
                                .fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            color = contentColor
                        )
                    }
                }
            }
            Button(
                onClick = onAddPressed,
                modifier = Modifier
                    .background(
                        color, shape.copy(
                            topStart = CornerSize(0.dp),
                            topEnd = CornerSize(0.dp)
                        )
                    )
                    .height(elementHeight)
                    .fillMaxWidth(),
                contentPadding = PaddingValues(1.dp)
            ){
                Icon(
                    modifier = Modifier
                        .size(iconSize),
                    imageVector = Icons.Rounded.Add,
                    contentDescription = "Add Workout",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        }

    }
}