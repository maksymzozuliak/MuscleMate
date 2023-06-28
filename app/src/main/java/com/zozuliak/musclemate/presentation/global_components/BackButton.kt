package com.zozuliak.musclemate.presentation.global_components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.zozuliak.musclemate.R

@Composable
fun BackButton(
    modifier: Modifier,
    onClick: () -> Unit,
    color: Color,
    buttonColors: IconButtonColors = IconButtonDefaults.iconButtonColors()
) {
    IconButton(
        onClick = onClick,
        modifier = modifier,
        colors = buttonColors
    ) {
        Icon(
            modifier = Modifier
                .fillMaxSize()
                .rotate(90f),
            painter = painterResource(id = R.drawable.expand_more),
            contentDescription = "Back",
            tint = color
        )
    }
}