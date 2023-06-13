package com.zozuliak.musclemate.presentation.main_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.zozuliak.musclemate.R
import com.zozuliak.musclemate.presentation.main_screen.components.ProfileButton

@Composable
fun MainScreen(
    //viewModel: MainScreenViewModel = hiltViewModel<MainScreenViewModel>()
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 24.dp,
                    end = 24.dp,
                    top = 24.dp,
                    bottom = 12.dp
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .weight(1f),
                text = "Workouts",
                style = MaterialTheme.typography.titleLarge
            )
            ProfileButton(
                modifier = Modifier
                    .size(36.dp),
                onClick = {},
                color = MaterialTheme.colorScheme.tertiary
            )
        }

    }
}

@Preview
@Composable
fun MainScreenPreview() {
    Surface(modifier = Modifier.background(Color.White)) {
        MainScreen()
    }
}