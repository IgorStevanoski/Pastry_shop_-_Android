package com.example.domaci3.welcome

import UserViewModel
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.domaci3.R
import com.example.domaci3.components.ButtonC
import com.example.domaci3.components.ClickableTextC
import com.example.domaci3.components.ColumnC
import com.example.domaci3.components.TextCHeadline
import com.example.domaci3.components.TextFieldC
import com.example.domaci3.components.TextSmallC
import com.example.domaci3.components.TopBar
import com.example.domaci3.components.TransparentBox
import com.example.domaci3.domain.UserData
import com.example.domaci3.model.user.UserState
import rs.edu.raf.raf_vezbe3compose.passwords.repository.UserRepository

fun NavGraphBuilder.login(
    route: String,
    navController: NavHostController,
    onLoginClick: () -> Unit,
    onRegisterClick: (Int) -> Unit,
) = composable(
    route = route
) {

    LoginScreen(
        onLoginClick = onLoginClick,
        onRegisterClick = onRegisterClick
    )

    TopBar(navHostController = navController)
}

@Composable
fun LoginScreen(
    onLoginClick: () -> Unit = {},
    onRegisterClick: (Int) -> Unit = {},
) {
    val userViewModel = viewModel<UserViewModel>()
    val state by userViewModel.state.collectAsState()

    var username by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }

    Scaffold(
        containerColor = Color.Transparent,
    ) { paddingValues ->
        ColumnC(
        ) {
            TransparentBox()

            TextCHeadline(
                paddingValues = paddingValues,
                text = stringResource(id = R.string.login),
            )

            TextSmallC(
                paddingValues = paddingValues,
                text = stringResource(id = R.string.username)
            )


            TextFieldC(
                value = username,
                onValueChange = { newText ->
                    username = newText
                }
            )

            TextSmallC(
                paddingValues = paddingValues,
                text = stringResource(id = R.string.password)
            )

            TextFieldC(
                value = password,
                onValueChange = { newText ->
                    password = newText
                }
            )

            ButtonC(
                onClick = { login(
                    state,
                    onLoginClick,
                    username = username.text,
                    password = password.text,
                )},
                text = stringResource(id = R.string.enter),
                paddingValues = PaddingValues(0.dp, paddingValues.calculateTopPadding() * 2, 0.dp, 0.dp),
            )

            ClickableTextC(
                onClick = onRegisterClick,
                paddingValues = paddingValues,
                text = stringResource(id = R.string.noacc)
            )

        }
    }
}

fun login(
    state: UserState,
    onLoginClick: () -> Unit = {},
    username: String,
    password: String,
){
    if (state.loginUser(username, password)){
        UserRepository.setLoggedUser(username)

        onLoginClick()
    }
}


@Preview
@Composable
fun PreviewLogin () {
    LoginScreen()
}