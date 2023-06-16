package com.zozuliak.musclemate.presentation.main_screen.components

import android.util.Log
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.magnifier
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.zozuliak.musclemate.R
import com.zozuliak.musclemate.data.Exercise
import com.zozuliak.musclemate.data.toGroup

@Composable
fun ExerciseItem(
    modifier: Modifier = Modifier,
    exercise: Exercise,
    expandedId: String? = null,
    onClick: (Exercise) -> Unit,
    iconSize: Dp = 36.dp,
    onExpandedClicked: (String?) -> Unit,
    shape: RoundedCornerShape = RoundedCornerShape(12.dp),
    onArrowUpPressed: (Exercise) -> Unit,
    onArrowDownPressed: (Exercise) -> Unit,
    onDeletePressed: (String) -> Unit,
) {

    val expanded = expandedId == exercise.id
    val widthState by animateFloatAsState(
        targetValue = if (expanded) 0.78f else 1f,
        animationSpec = tween(
            durationMillis = 500
        )
    )


    Box(
        modifier = Modifier
            .shadow(
                elevation = 36.dp,
                shape = shape,
                spotColor = Color.Black.copy(alpha = 0.2f)
            )
            .padding(horizontal = 3.dp, vertical = 6.dp)
            .height(110.dp)
    ) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.LightGray, shape),
        contentAlignment = Alignment.CenterEnd
    ) {
        if (widthState != 1f) {
            Column(
                modifier = Modifier
                    .padding(vertical = 3.dp)
            ) {
                IconButton(
                    onClick = { onArrowUpPressed(exercise) },
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Icon(
                        modifier = Modifier
                            .rotate(180f),
                        painter = painterResource(id = R.drawable.expand_more),
                        contentDescription = "Up",
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }
                IconButton(
                    onClick = { onArrowDownPressed(exercise) },
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Icon(
                        modifier = Modifier,
                        painter = painterResource(id = R.drawable.expand_more),
                        contentDescription = "Down",
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }
                IconButton(
                    onClick = { onDeletePressed(exercise.id!!) },
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Icon(
                        modifier = Modifier,
                        imageVector = Icons.Rounded.Delete,
                        contentDescription = "Delete",
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        }
    }

    Box(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background, shape)
            .fillMaxHeight()
            .width((LocalConfiguration.current.screenWidthDp * widthState).dp)
    ) {
        Row(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background, shape),
            verticalAlignment = Alignment.CenterVertically,
        )
        {
            Image(
                modifier = Modifier
                    .padding(
                        start = 12.dp,
                        top = 16.dp,
                        bottom = 16.dp
                    ),
                painter = painterResource(
                    id = exercise.group.toGroup()?.img_id ?: R.drawable.expand_more
                ),
                contentDescription = "Group img"
            )
            Column(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .weight(1f),
            ) {
                Text(
                    text = exercise.name,
                    style = MaterialTheme.typography.bodyLarge,
                    maxLines = 1
                )
                Text(
                    text = "${exercise.sets?.size ?: 0} sets",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
                )
            }
            IconButton(
                onClick = { onExpandedClicked(exercise.id) },
                modifier = Modifier
            ) {
                Icon(
                    modifier = Modifier
                        .size(iconSize),
                    imageVector = Icons.Rounded.MoreVert,
                    contentDescription = "Expand",
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
        }
    }
    }
}