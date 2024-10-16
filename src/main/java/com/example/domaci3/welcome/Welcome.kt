package com.example.domaci3.welcome

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.domaci3.R
import com.example.domaci3.components.ButtonC
import com.example.domaci3.components.ClickableTextC
import com.example.domaci3.components.ColumnC
import com.example.domaci3.components.TextCHeadline
import com.example.domaci3.components.TopBar
import com.example.domaci3.components.TransparentBox

fun NavGraphBuilder.welcome(
    route: String,
    navController: NavHostController,
    onLoginClick: () -> Unit,
    onRegisterClick: (Int) -> Unit,
) = composable(
    route = route
) {

    WelcomeScreen(
        onLoginClick = onLoginClick,
        onRegisterClick = onRegisterClick
    )

    TopBar(navHostController = navController)
}

@Composable
fun WelcomeScreen(
    onLoginClick: () -> Unit = {},
    onRegisterClick: (Int) -> Unit = {},
) {
    Scaffold(
        containerColor = Color.Transparent,
        content = { paddingValues ->
            ColumnC(
                content = {
                    TransparentBox()

                    TextCHeadline(
                        paddingValues = paddingValues,
                        text = stringResource(id = R.string.welcome)
                    )

                    ButtonC(
                        onClick = { onLoginClick() },
                        text = stringResource(id = R.string.login_button),
                        paddingValues = paddingValues
                    )

                    ClickableTextC(
                        onClick = onRegisterClick,
                        paddingValues = paddingValues,
                        text = stringResource(id = R.string.noacc)
                    )
                }
            )
        },
    )
}

@Preview
@Composable
fun PreviewWelcome () {
    WelcomeScreen()
}