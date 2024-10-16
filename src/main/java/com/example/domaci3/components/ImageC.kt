package com.example.domaci3.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.domaci3.R
import com.example.domaci3.ui.theme.darkblue

@Composable
fun ImageC(
    painter: Painter,
    imageSize: Dp = 350.dp,
    onClick: () -> Unit = {},
    contentScale: ContentScale = ContentScale.FillBounds,
    paddingValues: PaddingValues = PaddingValues()
) {

    Image(
        painter = painter,
        contentScale = contentScale,
        contentDescription = stringResource(id = R.string.image_description),
        modifier = Modifier
            .padding(paddingValues)
            .size(imageSize)
            .background(Color.Transparent)
            .clickable {
                onClick()
            }
            .clip(RoundedCornerShape(20.dp))
            .border(5.dp, darkblue, RoundedCornerShape(20.dp))
    )
}