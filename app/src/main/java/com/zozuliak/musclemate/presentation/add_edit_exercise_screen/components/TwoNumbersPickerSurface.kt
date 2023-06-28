package com.zozuliak.musclemate.presentation.add_edit_exercise_screen.components

import android.util.Range
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chargemap.compose.numberpicker.NumberPicker
import com.zozuliak.musclemate.repsRange

@Composable
fun TwoNumbersPickerSurface(
    modifier: Modifier = Modifier,
    firstNumber: Int,
    firstRange: List<Int>,
    onFirstChanged: (Int) -> Unit,
    firstTitle: String,
    secondNumber: Int,
    secondRange: List<Int>,
    onSecondChanged: (Int) -> Unit,
    secondTitle: String,
    shape: RoundedCornerShape = RoundedCornerShape(16.dp),
    backgroundColor: Color = MaterialTheme.colorScheme.background,
    titleColor: Color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
    titleStyle: TextStyle = MaterialTheme.typography.labelSmall.copy(color = titleColor),
    dividersColor: Color = Color.Transparent,
    textTopPadding: Dp = 0.dp,
    pickerStyle: TextStyle = MaterialTheme.typography.bodyLarge.copy(fontSize = 36.sp,
     color = MaterialTheme.colorScheme.primary)

) {
    Surface(
        modifier = modifier
            .fillMaxWidth(),
        color = backgroundColor,
        shape = shape,
        shadowElevation = 2.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 12.dp,
                    end = 24.dp
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            NumberPicker(
                value = firstNumber,
                onValueChange = { onFirstChanged(it) },
                range = firstRange,
                modifier = Modifier
                    .weight(1f),
                dividersColor = dividersColor,
                textStyle = pickerStyle
            )
            Text(
                text = firstTitle,
                style = titleStyle,
                modifier = Modifier
                    .weight(0.8f)
                    .padding(
                        top = textTopPadding
                    ),
            )

            NumberPicker(
                value = secondNumber,
                onValueChange = {onSecondChanged(it)},
                range = secondRange,
                modifier = Modifier
                    .weight(1f),
                dividersColor = dividersColor,
                textStyle = pickerStyle
            )
            Text(
                text = secondTitle,
                style = titleStyle,
                modifier = Modifier
                    .weight(0.8f)
                    .padding(
                        top = textTopPadding
                    ),
            )
        }
    }
}