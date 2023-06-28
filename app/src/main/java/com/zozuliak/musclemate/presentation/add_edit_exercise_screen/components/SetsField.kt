package com.zozuliak.musclemate.presentation.add_edit_exercise_screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.zozuliak.musclemate.R
import com.zozuliak.musclemate.data.Set
import com.zozuliak.musclemate.presentation.add_edit_exercise_screen.toWeightString

@Composable
fun SetsField(
    modifier: Modifier = Modifier,
    sets: List<Set>,
    onAddClicked: () -> Unit,
    onRemovePressed: () -> Unit,
    addButtonShape: RoundedCornerShape = RoundedCornerShape(18.dp),
    shape: RoundedCornerShape = RoundedCornerShape(16.dp),
    backgroundColor: Color = MaterialTheme.colorScheme.background,
    onClick: (Int) -> Unit
) {

    if (sets.isEmpty()) {
        TextButton(
            onClick = { onAddClicked() },
            modifier = modifier,
            shape = addButtonShape,
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 2.dp
            ),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.background
            )
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier
                        .padding(end = 2.dp),
                    imageVector = Icons.Rounded.Add,
                    contentDescription = "Add set",
                    tint = MaterialTheme.colorScheme.onBackground,
                )
                Text(
                    text = "Add",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }
    } else {

        Surface(
            modifier = modifier
                .fillMaxWidth(),
            color = backgroundColor,
            shape = shape,
            shadowElevation = 1.dp
        ) {
            Column(modifier = Modifier) {
                Surface(
                    color = backgroundColor,
                    shape = shape,
                    shadowElevation = 1.dp
                ) {
                    Column(
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(
                                    start = 12.dp,
                                    end = 12.dp,
                                    top = 8.dp
                                )
                        ) {
                            SetText(
                                text = "№",
                                modifier = Modifier
                                    .weight(2f)
                            )
                            SetText(
                                text = "reps",
                                modifier = Modifier
                                    .weight(3f)
                            )
                            SetText(
                                text = "weight",
                                modifier = Modifier
                                    .weight(5f)
                            )
                        }
                        LazyColumn(
                            modifier = Modifier
                                .padding(bottom = 8.dp)
                        ) {
                            itemsIndexed(sets) { index, set ->

                                Row(
                                    modifier = Modifier
                                        .padding(
                                            start = 12.dp,
                                            end = 12.dp,
                                            top = 4.dp
                                        )
                                        .clickable {
                                            onClick(index)
                                        }
                                ) {
                                    SetText(
                                        text = (index + 1).toString(),
                                        modifier = Modifier
                                            .weight(2f),
                                        style = MaterialTheme.typography.bodyLarge
                                    )
                                    SetText(
                                        text = set.repetitions.toString(),
                                        modifier = Modifier
                                            .weight(3f),
                                        style = MaterialTheme.typography.bodyLarge
                                    )
                                    SetText(
                                        text = set.weight.toWeightString(),
                                        modifier = Modifier
                                            .weight(5f),
                                        style = MaterialTheme.typography.bodyLarge
                                    )
                                }
                            }
                        }
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    SetButton(
                        onClick = { onRemovePressed() },
                        painter = painterResource(id = R.drawable.remove_icon),
                        modifier = Modifier.weight(1f)
                    )
                    Divider(
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.2f),
                        modifier = Modifier
                            .width(1.dp)
                            .fillMaxHeight()
                    )
                    SetButton(
                        onClick = { onAddClicked() },
                        painter = painterResource(id = R.drawable.add_icon),
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}

@Composable
private fun SetText(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
    style: TextStyle = MaterialTheme.typography.labelSmall.copy(color = color)
) {
    Text(
        text = text,
        modifier = modifier
            .fillMaxWidth(),
        textAlign = TextAlign.Center,
        style = style
    )
}

@Composable
private fun SetButton(
    modifier: Modifier = Modifier,
    iconModifier: Modifier = Modifier,
    onClick: () -> Unit,
    painter: Painter,
    color: Color = MaterialTheme.colorScheme.onBackground
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
    ) {
        Icon(
            modifier = iconModifier,
            painter = painter,
            contentDescription = painter.toString(),
            tint = color
        )

    }
}
