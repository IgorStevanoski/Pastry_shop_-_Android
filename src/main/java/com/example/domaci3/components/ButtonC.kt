package com.example.domaci3.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.domaci3.R

@Composable
fun ButtonC(
    onClick: () -> Unit,
    text: String,
    paddingValues: PaddingValues,
    buttonWidth: Dp = 300.dp,
    buttonHeight: Dp = 65.dp
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .padding(0.dp, paddingValues.calculateTopPadding(), 0.dp, 0.dp)
            .height(buttonHeight)
            .width(buttonWidth),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(id = R.color.darkblue),
            contentColor = colorResource(id = R.color.lightblue)
        ),
        shape = RoundedCornerShape(50),
    ) {
        Text(
            modifier = Modifier,
            text = text,
            style = MaterialTheme.typography.labelSmall
        )
    }
}