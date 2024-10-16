package com.example.domaci3.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.domaci3.ui.theme.brown
import com.example.domaci3.ui.theme.darkbrown

val textWidth = 300.dp

@Composable
fun TextSmallC(
    paddingValues: PaddingValues,
    text: String,
) {
    Text(
        modifier = Modifier
            .padding(0.dp, paddingValues.calculateTopPadding(), 0.dp, 0.dp),
        text = text,
        style = MaterialTheme.typography.bodySmall
    )
}

@Composable
fun TextMediumC(
    paddingValues: PaddingValues,
    text: String,
) {
    Text(
        modifier = Modifier
            .padding(paddingValues),
        text = text,
        style = MaterialTheme.typography.bodyMedium
    )
}

/** paddingValues se ne koristi. */
@Composable
fun TextCHeadline(
    paddingValues: PaddingValues = PaddingValues(0.dp),
    text: String,
) {
    Text(
        modifier = Modifier
//            .padding(paddingValues)
            ,
        text = text,
        style = MaterialTheme.typography.headlineMedium
    )
}

@Composable
fun ClickableTextC(
    onClick: (Int) -> Unit,
    paddingValues: PaddingValues,
    text: String,
) {
    ClickableText(
        modifier = Modifier
            .padding(paddingValues)
            .width(textWidth),
        text = AnnotatedString(text),
        onClick = onClick,
        style = MaterialTheme.typography.bodySmall,
    )

    TransparentBox(50.dp)
}

@Composable
fun TextFieldC(
    value: TextFieldValue,
    lines: Int = 1,
    isPassword: Boolean = false,
    onValueChange: (TextFieldValue) -> Unit,
){

    if (!isPassword) {
        TextField(
            textStyle = MaterialTheme.typography.bodySmall,
            modifier = Modifier
                .width(textWidth)
                .border(10.dp, brown, RoundedCornerShape(50)),
            value = value,
            onValueChange = onValueChange,
            shape = RoundedCornerShape(50),
            colors = TextFieldDefaults.colors(
                cursorColor = darkbrown,
                focusedLabelColor = brown,
                unfocusedLabelColor = brown,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent
            ),
            minLines = lines,
            maxLines = lines,
        )
    } else {
        TextField(
            textStyle = MaterialTheme.typography.bodySmall,
            modifier = Modifier
                .width(textWidth)
                .border(10.dp, brown, RoundedCornerShape(50)),
            value = value,
            onValueChange = onValueChange,
            shape = RoundedCornerShape(50),
            colors = TextFieldDefaults.colors(
                cursorColor = darkbrown,
                focusedLabelColor = brown,
                unfocusedLabelColor = brown,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent
            ),
            minLines = lines,
            maxLines = lines,
            visualTransformation = PasswordVisualTransformation()
        )
    }

}